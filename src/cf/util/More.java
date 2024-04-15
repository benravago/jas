package cf.util;

import java.util.List;

public class More {

  final int limit;
  int count = 0;

  public More(int limit) { this.limit = limit; }
  public boolean more() { return ++count < limit; }
  public String comma() { return more() ? "," : ""; }

  public static More of(List<?> list) { return new More(list.size()); }
}
