package cf.disasm.constant;

import java.lang.classfile.constantpool.InvokeDynamicEntry;
import cf.util.Printer;

class InvokeDynamicInfo {

  static void of(InvokeDynamicEntry a, Printer f) {
    f.print("#InvokeDynamic -> "+a);
  }
}
