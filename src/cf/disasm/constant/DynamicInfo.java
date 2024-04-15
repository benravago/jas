package cf.disasm.constant;

import java.lang.classfile.constantpool.ConstantDynamicEntry;
import cf.util.Printer;

class DynamicInfo {

  static void of(ConstantDynamicEntry a, Printer f) {
    f.print("#ConstantDynamic -> "+a);
  }
}
