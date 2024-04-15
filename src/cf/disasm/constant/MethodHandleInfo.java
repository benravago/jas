package cf.disasm.constant;

import java.lang.classfile.constantpool.MethodHandleEntry;
import cf.util.Printer;

class MethodHandleInfo {

  static void of(MethodHandleEntry a, Printer f) {
    f.print("#MethodHandle -> "+a);
  }
}
