package cf.util;

import java.text.CharacterIterator;

public class CharArray implements CharSequence, CharacterIterator {

  final char[] array;
  final int offset, count;
  int pos;

  public CharArray(char[] array) {
    this(array, 0, array.length);
  }

  public CharArray(char[] array, int offset, int count) {
    this.array = array;
    this.offset = offset;
    this.count = count;
    this.pos = -1;
  }

  @Override
  public int getBeginIndex() {
    return offset;
  }

  @Override
  public int getEndIndex() {
    return offset + count;
  }

  @Override
  public int getIndex() {
    return pos;
  }

  @Override
  public char setIndex(int position) {
    if (position < 0 || position > offset + count) {
      throw new IndexOutOfBoundsException("position:"+position);
    }
    pos = position;
    return (position == offset + count) ? DONE : array[pos];
  }

  @Override
  public char current() {
    return (pos < 0 || pos >= count + offset) ? DONE : array[pos];
  }

  @Override
  public char first() {
    pos = offset;
    return isEmpty() ? DONE : array[pos];
  }

  @Override
  public char last() {
    if (isEmpty()) {
      pos = offset + count;
      return DONE;
    }
    pos = offset + count - 1;
    return array[pos];
  }

  @Override
  public char next() {
    pos++;
    if (pos >= offset + count) {
      pos = offset + count;
      return DONE;
    }
    return array[pos];
  }

  @Override
  public char previous() {
    return (pos == offset) ? DONE : array[--pos];
  }

  @Override
  public boolean isEmpty() {
    return (count == 0 || array == null || array.length == 0);
  }

  @Override
  public int length() {
    return count;
  }

  @Override
  public char charAt(int index) {
    return index < count ? array[offset+index] : DONE;
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    if (0 <= start && start <= end && end <= count) {
      return new CharArray(array, offset + start, end - start);
    }
    throw new IndexOutOfBoundsException("start:"+start+", end:"+end);
  }

  @Override
  public String toString() {
    return array != null ? new String(array, offset, count) : "";
  }

  @Override
  public Object clone() {
    return array != null ? new CharArray(array, offset, count) : null;
  }

  int mark = -1;

  public void mark() {
    mark = pos;
  }
  public void reset() {
    if (mark > -1) { pos = mark; mark = -1; }
  }

}
