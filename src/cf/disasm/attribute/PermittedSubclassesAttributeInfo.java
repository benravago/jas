package cf.disasm.attribute;

import java.lang.classfile.attribute.PermittedSubclassesAttribute;
import cf.util.Printer;

class PermittedSubclassesAttributeInfo {

  static void of(PermittedSubclassesAttribute a, Printer d) {
    d.print(".PermittedSubclassesAttribute -> "+a);
  }
}
