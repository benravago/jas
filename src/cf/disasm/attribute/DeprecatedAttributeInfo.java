package cf.disasm.attribute;

import java.lang.classfile.attribute.DeprecatedAttribute;
import cf.util.Printer;

class DeprecatedAttributeInfo {

  static void of(DeprecatedAttribute a, Printer d) {
    d.print(".DeprecatedAttribute -> "+a);
  }
}
