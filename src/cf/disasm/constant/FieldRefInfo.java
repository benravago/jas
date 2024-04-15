package cf.disasm.constant;

import java.lang.classfile.constantpool.FieldRefEntry;
import cf.util.Printer;

class FieldRefInfo {

  static void of(FieldRefEntry a, Printer f) {
    MemberRefInfo.of(a,f);
  }
}
