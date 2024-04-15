package cf.disasm.constant;

import java.lang.classfile.constantpool.MethodRefEntry;
import cf.util.Printer;

class MethodRefInfo {

  static void of(MethodRefEntry a, Printer f) {
    MemberRefInfo.of(a,f);
  }
}
