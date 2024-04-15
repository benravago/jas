package cf.disasm.constant;

import java.lang.classfile.constantpool.DoubleEntry;
import cf.util.Printer;

class DoubleInfo {

  static void of(DoubleEntry a, Printer f) {
    f.print("#Double -> "+a);
  }
}
