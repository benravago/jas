package cf.disasm.constant;

import java.lang.classfile.constantpool.NameAndTypeEntry;
import cf.util.Printer;

class NameAndTypeInfo {

  static void of(NameAndTypeEntry a, Printer f) {
    f.print("#%d  %s  #%d:#%d  // %s:%s", a.index(), CpInfo.kind(a.tag()), a.name().index(), a.type().index(), a.name().stringValue(), a.type().stringValue() );
  }
}
