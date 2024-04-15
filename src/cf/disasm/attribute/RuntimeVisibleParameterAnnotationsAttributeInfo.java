package cf.disasm.attribute;

import java.lang.classfile.attribute.RuntimeVisibleParameterAnnotationsAttribute;
import cf.util.Printer;

class RuntimeVisibleParameterAnnotationsAttributeInfo {

  static void of(RuntimeVisibleParameterAnnotationsAttribute a, Printer d) {
    d.print(".RuntimeVisibleParameterAnnotationsAttribute -> "+a);
  }
}
