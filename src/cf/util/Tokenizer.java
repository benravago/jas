// SPDX-License-Identifier: Apache 2.0
// SPDX-sourceInfo: Adapted from the Apache Harmony implementation of java.io.StreamTokenizer */

package cf.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import static java.text.CharacterIterator.DONE;

public class Tokenizer implements Iterable<String>, Iterator<String> {

  public Tokenizer(char[] src) {
    this.src = new CharArray(src);
  }

  final CharArray src;
  volatile String pending;

  @Override
  public boolean hasNext() {
    if (pending == null) {
      pending = nextToken();
    }
    return pending != null;
  }

  @Override
  public String next() {
    if (pending != null) {
      var current = pending;
      pending = null;
      return current;
    }
    throw new NoSuchElementException();
  }

  @Override
  public Iterator<String> iterator() {
    return this;
  }

  public Stream<String> stream() {
    return StreamSupport.stream(spliterator(), false);
  }

  final static byte[] tokenTypes = {
    4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,  // 00:0f 0123456789abcdef
    4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,  // 10:1f
    4, 0, 2, 8, 8, 0, 0, 2, 8, 8, 0, 0, 0, 0, 8, 1,  // 20:2f   "#$  '()  , ./
    8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 0,  // 30:3f 0123456789:;<=>
    0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,  // 40:4f  ABCDEFGHIJKLMNO
    8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 0, 8,  // 50:5f PQRSTUVWXYZ[ ] _
    2, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,  // 60:6f `abcdefghijklmno
    8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0   // 70:7f pqrstuvwxyz
  };

  public final static byte
    TOKEN_COMMENT = 1,   // NOTES:
    TOKEN_QUOTE = 2,     //   comma is the list separator
    TOKEN_WHITE = 4,     //   slash can also be a word character
    TOKEN_WORD = 8,      //   backslash is escape character
    TOKEN_SPECIAL = 16,  //   !%&*+,-?@^{|}~ are special characters
    TOKEN_EOF = -1;

  byte ttype = TOKEN_EOF;
  int lineNumber = 1;
  boolean lastCr;
  char peekChar; // -2

  /** Parses the next token from this tokenizer's source. */
  String nextToken() {

    //  sval = null; // Always reset sval to null
    char currentChar = peekChar > 0 ? peekChar : src.next();

    if (lastCr && currentChar == '\n') {
      lastCr = false;
      currentChar = src.next();
    }
    if (currentChar == DONE) {
      ttype = TOKEN_EOF;
      return null;
    }

    var currentType = currentChar > 0x7f ? TOKEN_WORD : tokenTypes[currentChar];
    while (currentType == TOKEN_WHITE) {
      /**
       * Skip over white space until we hit a new line or a real token
      */
      switch (currentChar) {
        case 'r' -> {
          lineNumber++;
          if ((currentChar = src.next()) == '\n') {
            currentChar = src.next();
          }
        }
        case '\n' -> {
          lineNumber++;
          currentChar = src.next();
        }
        default -> {
          // Advance over this white space character and try again.
          currentChar = src.next();
        }
      }
      if (currentChar == DONE) {
        ttype = TOKEN_EOF;
        return null;
      }
      currentType = currentChar > 0x7f ? TOKEN_WORD : tokenTypes[currentChar];
    }

    // Check for words
    if (currentType == TOKEN_WORD) {
      var word = new StringBuilder(20);
      for (;;) {
        word.append(currentChar);
        currentChar = src.next();
        if (currentChar == DONE || currentChar >= 0x7f) break;
        if (tokenTypes[currentChar] != TOKEN_WORD) {
          if (tokenTypes[currentChar] == TOKEN_COMMENT && tokenTypes[src.charAt(src.getIndex()+1)] == TOKEN_WORD) continue;
          break; // awkward !! continue if '/'+word_char
        }
      }
      peekChar = currentChar;
      // sval = forceLowercase ? word.toString().toLowerCase() : word.toString();
      ttype = TOKEN_WORD;
      return word.toString();
    }
    // Check for quoted character
    if (currentType == TOKEN_QUOTE) {
      var matchQuote = currentChar;
      var quoteString = new StringBuilder();
      var peekOne = src.next();
      while (peekOne >= 0 && peekOne != matchQuote && peekOne != '\r' && peekOne != '\n') {
        var readPeek = true;
        if (peekOne == '\\') {
          var c1 = src.next();
          // Check for quoted octal IE: \377
          if (c1 <= '7' && c1 >= '0') {
            var digitValue = c1 - '0';
            c1 = src.next();
            if (c1 > '7' || c1 < '0') {
              readPeek = false;
            } else {
              digitValue = digitValue * 8 + (c1 - '0');
              c1 = src.next();
              // limit the digit value to a byte
              if (digitValue > 037 || c1 > '7' || c1 < '0') {
                readPeek = false;
              } else {
                digitValue = digitValue * 8 + (c1 - '0');
              }
            }
            if (!readPeek) {
              // We've consumed one too many
              quoteString.append((char) digitValue);
              peekOne = c1;
            } else {
              peekOne = (char)digitValue;
            }
          } else {
            peekOne = switch (c1) {
              case 'a' -> 0x07;
              case 'b' -> 0x08;
              case 't' -> 0x09;
              case 'n' -> 0x0a;
              case 'v' -> 0x0b;
              case 'f' -> 0x0c;
              case 'r' -> 0x0d;
              default  -> c1;
            };
          }
        }
        if (readPeek) {
          quoteString.append((char) peekOne);
          peekOne = src.next();
        }
      }
      if (peekOne == matchQuote) {
        peekOne = src.next();
      }
      peekChar = peekOne;
      ttype = (byte)matchQuote;
      // sval = quoteString.toString();
      return quoteString.toString();
    }
    // Do comments, both "//" and "/*stuff*/"
    if (currentChar == '/') { // && (slashSlashComments || slashStarComments)
      if ((currentChar = src.next()) == '*') { // && slashStarComments
        var peekOne = src.next();
        for (;;) {
          currentChar = peekOne;
          peekOne = src.next();
          if (currentChar == DONE) {
            peekChar = 0;
            ttype = TOKEN_EOF;
            return null;
          }
          if (currentChar == '\r') {
            if (peekOne == '\n') {
              peekOne = src.next();
            }
            lineNumber++;
          } else if (currentChar == '\n') {
            lineNumber++;
          } else if (currentChar == '*' && peekOne == '/') {
            peekChar = src.next();
            return nextToken();
          }
        }
      } else if (currentChar == '/') { // && slashSlashComments
        // Skip to EOF or new line then return the next token
        while ((currentChar = src.next()) >= 0 && currentChar != '\r' && currentChar != '\n') {
          // Intentionally empty
        }
        peekChar = currentChar;
        return nextToken();
      } else if (currentType != TOKEN_COMMENT) {
        // Was just a slash by itself
        peekChar = currentChar;
        ttype = TOKEN_COMMENT;
        return Character.toString('/');
      }
    }
    // Check for comment character
    if (currentType == TOKEN_COMMENT) {
      // Skip to EOF or new line then return the next token
      while ((currentChar = src.next()) >= 0 && currentChar != '\r' && currentChar != '\n') {
        // Intentionally empty
      }
      peekChar = currentChar;
      return nextToken();
    }

    peekChar = src.next();
    ttype = TOKEN_SPECIAL;
    return Character.toString(currentChar);
  }

  public int line() { return lineNumber; }
  // column() -> src.getIndex() - lineOffset
  public int offset() { return src.getIndex(); }
  public byte type() { return ttype; }

}
