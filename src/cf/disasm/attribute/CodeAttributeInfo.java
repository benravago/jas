package cf.disasm.attribute;

import java.lang.classfile.attribute.CodeAttribute;

import cf.util.Printer;

class CodeAttributeInfo {

  // Code_attribute { -- jvms-4.7.3 The Code Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 max_stack;
  //   u2 max_locals;
  //   u4 code_length;
  //   u1 code[code_length];
  //   u2 exception_table_length;
  //    {  u2 start_pc;
  //       u2 end_pc;
  //       u2 handler_pc;
  //       u2 catch_type;
  //    } exception_table[exception_table_length];
  //   u2 attributes_count;
  //   attribute_info attributes[attributes_count];
  // }

  static void of(CodeAttribute a, Printer d) {
    d.print(".%s", a.attributeName())
     .indent(() -> {
      d.print(".stack  %d", a.maxStack())
       .print(".locals  %d", a.maxLocals())
       .print(".code  // [%d]", a.codeLength())
       .indent(() -> new Decoder(d).decode(a))
       .print(".end")
       .indent(() -> a.attributes().forEach(b -> Attributes.of(b,d)) );
      })
     .print(".end");
  }
}