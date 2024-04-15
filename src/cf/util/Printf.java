package cf.util;

import java.util.Formatter;

public class Printf implements CharSequence {

  public Printf() {
    this(new StringBuilder());
  }

  public Printf(CharSequence cs) {
    fmt = new Formatter(cs instanceof StringBuilder sb ? sb : new StringBuilder(cs));
  }

  final Formatter fmt;

  public Printf format(String format, Object... args) {
    fmt.format(format, args);
    return this;
  }

  public StringBuilder out() {
    return (StringBuilder) fmt.out();
  }

  @Override
  public int length() {
    return out().length();
  }

  @Override
  public char charAt(int index) {
    return out().charAt(index);
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return new Printf(out().subSequence(start, end));
  }

  @Override
  public String toString() {
    return fmt.toString();
  }

}
