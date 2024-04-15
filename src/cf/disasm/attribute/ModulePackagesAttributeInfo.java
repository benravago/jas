package cf.disasm.attribute;

import java.lang.classfile.attribute.ModulePackagesAttribute;
import cf.util.Printer;

class ModulePackagesAttributeInfo {

  static void of(ModulePackagesAttribute a, Printer d) {
    d.print(".ModulePackagesAttribute -> "+a);
  }
}
