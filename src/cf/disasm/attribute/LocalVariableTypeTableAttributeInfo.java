package cf.disasm.attribute;

import java.lang.classfile.attribute.LocalVariableTypeTableAttribute;
import cf.util.Printer;

class LocalVariableTypeTableAttributeInfo {

  static void of(LocalVariableTypeTableAttribute a, Printer d) {
    d.print(".LocalVariableTypeTableAttribute -> "+a);
  }
}
