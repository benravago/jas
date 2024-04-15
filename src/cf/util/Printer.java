package cf.util;

import java.io.PrintStream;

public class Printer {

  public Printer(PrintStream out) {
    this.out = out;
  }

  public final PrintStream out;
  public final StringBuilder indent = new StringBuilder();

  public Printer indent(int n) {
    if (n < 0) {
      indent.setLength(indent.length()+n);
    } else {
      while (n > 0) { indent.append(' '); n--; }
    }
    return this;
  }

  public Printer indent(Runnable run, int n) {
    indent(n); run.run(); indent(-n);
    return this;
  }

  public Printer indent(Runnable run) {
    return indent(run, 2);
  }

  public Printer print(String format, Object...args) {
    out.append(indent).format(format, args).append('\n');
    return this;
  }

}
