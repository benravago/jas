package cf.disasm.type;

import java.lang.classfile.AccessFlags;

public class Access {

  public static String of(AccessFlags access) {
    var buf = new StringBuilder();
    for (var flag:access.flags()) {
      buf.append(", ").append(flag.name().toLowerCase());
    }
    buf.append(" // 0x").append(Integer.toHexString(access.flagsMask()));
    return buf.substring(2);
  }
}
