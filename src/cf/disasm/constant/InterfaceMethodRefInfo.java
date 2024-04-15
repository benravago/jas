package cf.disasm.constant;

import java.lang.classfile.constantpool.InterfaceMethodRefEntry;
import cf.util.Printer;

class InterfaceMethodRefInfo {

  static void of(InterfaceMethodRefEntry a, Printer f) {
    f.print("#InterfaceMethodRef -> "+a);
  }
}
