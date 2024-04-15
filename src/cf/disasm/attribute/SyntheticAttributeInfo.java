package cf.disasm.attribute;

import java.lang.classfile.attribute.SyntheticAttribute;
import cf.util.Printer;

class SyntheticAttributeInfo {

  static void of(SyntheticAttribute a, Printer d) {
    d.print(".SyntheticAttribute -> "+a);
  }
}
