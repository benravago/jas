package cf.disasm.attribute;

import java.lang.classfile.attribute.ModuleMainClassAttribute;
import cf.util.Printer;

class ModuleMainClassAttributeInfo {

  static void of(ModuleMainClassAttribute a, Printer d) {
    d.print(".ModuleMainClassAttribute -> "+a);
  }
}
