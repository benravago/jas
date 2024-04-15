package cf.disasm.attribute;

import java.lang.classfile.attribute.MethodParametersAttribute;
import cf.util.Printer;

class MethodParametersAttributeInfo {

  static void of(MethodParametersAttribute a, Printer d) {
    d.print(".MethodParametersAttribute -> "+a);
  }
}
