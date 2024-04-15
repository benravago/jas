package cf.disasm.attribute;

import java.lang.classfile.attribute.RecordAttribute;
import cf.util.Printer;

class RecordAttributeInfo {

  static void of(RecordAttribute a, Printer d) {
    d.print(".RecordAttribute -> "+a);
  }
}
