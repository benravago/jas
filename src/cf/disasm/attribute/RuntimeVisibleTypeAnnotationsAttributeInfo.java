package cf.disasm.attribute;

import java.lang.classfile.attribute.RuntimeVisibleTypeAnnotationsAttribute;
import cf.util.Printer;

class RuntimeVisibleTypeAnnotationsAttributeInfo {

  static void of(RuntimeVisibleTypeAnnotationsAttribute a, Printer d) {
    d.print(".RuntimeVisibleTypeAnnotationsAttribute -> "+a);
  }
}
