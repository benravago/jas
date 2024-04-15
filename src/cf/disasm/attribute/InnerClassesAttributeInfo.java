package cf.disasm.attribute;

import java.lang.classfile.attribute.InnerClassesAttribute;
import cf.util.Printer;

class InnerClassesAttributeInfo {

  static void of(InnerClassesAttribute a, Printer d) {
    d.print(".InnerClassesAttribute -> "+a);
  }
}
