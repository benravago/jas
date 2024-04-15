package cf.disasm.constant;

import java.lang.classfile.constantpool.PackageEntry;
import cf.util.Printer;

class PackageInfo {

  static void of(PackageEntry a, Printer f) {
    f.print("#Package -> "+a);
  }
}
