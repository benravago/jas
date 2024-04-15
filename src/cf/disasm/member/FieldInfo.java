package cf.disasm.member;

import java.lang.classfile.FieldModel;

import cf.disasm.attribute.Attributes;
import cf.disasm.type.Access;
import cf.util.Printer;

public class FieldInfo {

  // field_info {  -- jvms-4.5 Fields
  //   u2 access_flags;
  //   u2 name_index;
  //   u2 descriptor_index;
  //   u2 attributes_count;
  //   attribute_info attributes[attributes_count];
  // }

  public static void of(FieldModel f, Printer d) {
    d.print(".field  #%d  // %s", f.fieldName().index(), f.fieldName().stringValue())
     .indent(() -> {
       d.print(".descriptor  #%d  // %s", f.fieldType().index(), f.fieldType().stringValue())
        .print(".flags  %s",  Access.of(f.flags()));
       f.attributes().forEach(a -> Attributes.of(a,d));
      })
     .print(".end");
  }
}
