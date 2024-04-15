package cf.disasm.attribute;

import java.lang.classfile.attribute.StackMapTableAttribute;
import cf.util.Printer;

class StackMapTableAttributeInfo {

  static void of(StackMapTableAttribute a, Printer d) {
    d.print(".StackMapTableAttribute -> "+a);
  }
}
