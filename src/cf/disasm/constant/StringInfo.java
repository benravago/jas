package cf.disasm.constant;

import java.lang.classfile.constantpool.StringEntry;
import cf.util.Printer;

class StringInfo {

  static void of(StringEntry a, Printer f) {
    f.print("#%d  %s  #%d  // %s", a.index(), CpInfo.kind(a.tag()), a.utf8().index(), a.utf8().stringValue() );
  }
}
