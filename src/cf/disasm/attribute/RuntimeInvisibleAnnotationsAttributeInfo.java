package cf.disasm.attribute;

import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import cf.util.Printer;

class RuntimeInvisibleAnnotationsAttributeInfo {

  static void of(RuntimeInvisibleAnnotationsAttribute a, Printer d) {
    d.print(".RuntimeInvisibleAnnotationsAttribute -> "+a);
  }
}
