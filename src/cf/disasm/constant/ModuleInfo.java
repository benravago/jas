package cf.disasm.constant;

import java.lang.classfile.constantpool.ModuleEntry;
import cf.util.Printer;

class ModuleInfo {

  static void of(ModuleEntry a, Printer f) {
    f.print("#Module -> "+a);
  }
}
