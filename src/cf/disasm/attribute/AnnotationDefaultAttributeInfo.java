package cf.disasm.attribute;

import java.lang.classfile.attribute.AnnotationDefaultAttribute;
import cf.util.Printer;

class AnnotationDefaultAttributeInfo {

  static void of(AnnotationDefaultAttribute a, Printer d) {
    d.print(".AnnotationDefaultAttribute -> "+a);
  }
}
