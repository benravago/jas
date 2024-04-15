package cf.disasm.constant;

import java.lang.classfile.constantpool.MethodTypeEntry;
import cf.util.Printer;

class MethodTypeInfo {

  static void of(MethodTypeEntry a, Printer f) {
    f.print("#MethodType -> "+a);
  }
}
