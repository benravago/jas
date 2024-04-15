package cf.disasm.attribute;

import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import cf.util.Printer;

class RuntimeVisibleAnnotationsAttributeInfo {

  // RuntimeVisibleAnnotations_attribute {  -- jvms-4.7.16 The RuntimeVisibleAnnotations Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 num_annotations;
  //   annotation annotations[num_annotations];
  // }

  static void of(RuntimeVisibleAnnotationsAttribute a, Printer d) {
    d.print(".%s ", a.attributeName())
     .indent(() -> Annotations.print(a.annotations(),d))
     .print(".end");
  }
}