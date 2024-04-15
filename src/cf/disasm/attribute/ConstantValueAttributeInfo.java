package cf.disasm.attribute;

import java.lang.classfile.attribute.ConstantValueAttribute;
import cf.util.Printer;

class ConstantValueAttributeInfo {

  static void of(ConstantValueAttribute a, Printer d) {
    d.print(".ConstantValueAttribute -> "+a);
  }
}
