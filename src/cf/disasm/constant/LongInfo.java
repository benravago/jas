package cf.disasm.constant;

import java.lang.classfile.constantpool.LongEntry;
import cf.util.Printer;

class LongInfo {

  static void of(LongEntry a, Printer f) {
    f.print("#Long -> "+a);
  }
}
