package cf.disasm.attribute;

import java.lang.classfile.attribute.ModuleAttribute;
import cf.util.Printer;

class ModuleAttributeInfo {

  static void of(ModuleAttribute a, Printer d) {
    d.print(".ModuleAttribute -> "+a);
  }
}
