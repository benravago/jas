package cf.disasm.attribute;

import java.lang.classfile.attribute.RuntimeInvisibleParameterAnnotationsAttribute;
import cf.util.Printer;

class RuntimeInvisibleParameterAnnotationsAttributeInfo {

  static void of(RuntimeInvisibleParameterAnnotationsAttribute a, Printer d) {
    d.print(".RuntimeInvisibleParameterAnnotationsAttribute -> "+a);
  }
}
