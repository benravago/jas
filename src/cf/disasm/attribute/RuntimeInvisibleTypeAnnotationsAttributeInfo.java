package cf.disasm.attribute;

import java.lang.classfile.attribute.RuntimeInvisibleTypeAnnotationsAttribute;
import cf.util.Printer;

class RuntimeInvisibleTypeAnnotationsAttributeInfo {

  static void of(RuntimeInvisibleTypeAnnotationsAttribute a, Printer d) {
    d.print(".RuntimeInvisibleTypeAnnotationsAttribute -> "+a);
  }
}
