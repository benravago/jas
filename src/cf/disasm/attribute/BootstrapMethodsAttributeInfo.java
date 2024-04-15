package cf.disasm.attribute;

import java.lang.classfile.attribute.BootstrapMethodsAttribute;
import cf.util.Printer;

class BootstrapMethodsAttributeInfo {

  static void of(BootstrapMethodsAttribute a, Printer d) {
    d.print(".BootstrapMethodsAttribute -> "+a);
  }
}
