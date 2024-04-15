package cf.disasm.constant;

import java.lang.classfile.constantpool.FloatEntry;
import cf.util.Printer;

class FloatInfo {

  static void of(FloatEntry a, Printer f) {
    f.print("#Float -> "+a);
  }
}
