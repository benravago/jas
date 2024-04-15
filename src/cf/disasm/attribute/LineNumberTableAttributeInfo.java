package cf.disasm.attribute;

import java.lang.classfile.attribute.LineNumberTableAttribute;
import cf.util.Printer;

class LineNumberTableAttributeInfo {

  // LineNumberTable_attribute {  -- jvms-4.7.12  The LineNumberTable Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 line_number_table_length;
  //    {  u2 start_pc;
  //       u2 line_number;
  //    } line_number_table[line_number_table_length];
  // }

  static void of(LineNumberTableAttribute a, Printer d) {
    d.print(".%s", a.attributeName());
    var c = a.lineNumbers().size();
    for (var l:a.lineNumbers()) {
      d.print("   $%d:%d%c", l.startPc(), l.lineNumber(), (--c > 0 ? ',':' '));
    }
  }
}