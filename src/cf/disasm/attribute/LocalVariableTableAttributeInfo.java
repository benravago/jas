package cf.disasm.attribute;

import java.lang.classfile.attribute.LocalVariableTableAttribute;
import cf.util.Printer;

class LocalVariableTableAttributeInfo {

  // LocalVariableTable_attribute {  -- jvms-4.7.13 The LocalVariableTable Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 local_variable_table_length;
  //    {  u2 start_pc;
  //       u2 length;
  //       u2 name_index;
  //       u2 descriptor_index;
  //       u2 index;
  //    } local_variable_table[local_variable_table_length];
  //  }

  static void of(LocalVariableTableAttribute a, Printer d) {
    d.print(".%s", a.attributeName())
     .indent(() -> {
        for (var v:a.localVariables())
          d.print(" _%d  %d:%d, #%d, #%d  // %s %s", v.slot(), v.startPc(), v.length(), v.name().index(), v.type().index(), v.name().stringValue(), v.type().stringValue() );
        })
     .print(".end");
  }
}