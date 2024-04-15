package cf.disasm.constant;

import java.lang.classfile.constantpool.IntegerEntry;
import cf.util.Printer;

class IntegerInfo {

  static void of(IntegerEntry a, Printer f) {
    f.print("#Integer -> "+a);
  }
}
