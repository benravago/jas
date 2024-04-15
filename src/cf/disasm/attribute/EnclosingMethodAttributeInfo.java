package cf.disasm.attribute;

import java.lang.classfile.attribute.EnclosingMethodAttribute;
import cf.util.Printer;

class EnclosingMethodAttributeInfo {

  static void of(EnclosingMethodAttribute a, Printer d) {
    d.print(".EnclosingMethodAttribute -> "+a);
  }
}
