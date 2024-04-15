package cf.disasm.attribute;

import java.lang.classfile.attribute.ExceptionsAttribute;
import cf.util.Printer;

class ExceptionsAttributeInfo {

  // Exceptions_attribute {  -- jvms-4.7.5 The Exceptions Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 number_of_exceptions;
  //   u2 exception_index_table[number_of_exceptions];
  // }

  static void of(ExceptionsAttribute a, Printer d) {
    d.print(".%s", a.attributeName());
    var c = a.exceptions().size();
    for (var e:a.exceptions()) {
      d.print("   #%d%c  // %s", e.index(), (--c > 0 ? ',':' '), e.asInternalName());
    }
  }
}