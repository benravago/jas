package cf.disasm.constant;

import java.lang.classfile.constantpool.ClassEntry;
import cf.util.Printer;

class ClassInfo {

  static void of(ClassEntry a, Printer f) {
    f.print("#%d  %s  #%d  // %s", a.index(), CpInfo.kind(a.tag()), a.name().index(), a.name().stringValue() );
  }
}
