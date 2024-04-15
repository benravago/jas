import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

import cf.disasm.Disassembler;
import cf.util.Tokenizer;

class Main {
  public static void main(String...args) throws Exception {
    if (args.length > 1) switch (args[0]) {
      case "disasm" -> run(Main::disasm, args[1]);
      case "tokens" -> run(Main::tokens, args[1]);
    }
    System.out.println("usage: Main [disasm|tokens] <filename>");
  }

  static void run(Consumer<byte[]> consumer, String filename) {
    try { consumer.accept(Files.readAllBytes(Paths.get(filename))); }
    catch (IOException e) { throw new UncheckedIOException(e); }
    System.exit(0);
  }

  static void disasm(byte[] b) {
    Disassembler.of().parse(b).writeTo(System.out);
  }

  static void tokens(byte[] b) {
    var t = new Tokenizer(new String(b).toCharArray());
    for (var k:t) System.out.println("%s  // %s %d %d".formatted(k, kind(t.type()), t.line(), t.offset()) );
  }


  static String kind(byte b) {
    return switch(b) {
      case Tokenizer.TOKEN_COMMENT -> "comment";
      case Tokenizer.TOKEN_QUOTE, '"', '\'' -> "quote";
      case Tokenizer.TOKEN_WHITE -> "space";
      case Tokenizer.TOKEN_WORD -> "word";
      case Tokenizer.TOKEN_SPECIAL -> "special";
      case Tokenizer.TOKEN_EOF -> "eof";
      default -> "unknown "+b;
    };
  }

}