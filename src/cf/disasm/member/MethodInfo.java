package cf.disasm.member;

import java.lang.classfile.MethodModel;

import cf.disasm.attribute.Attributes;
import cf.disasm.type.Access;

import cf.util.Printer;

public class MethodInfo {

  // method_info {  -- jvms-4.6 Methods
  //   u2 access_flags;
  //   u2 name_index;
  //   u2 descriptor_index;
  //   u2 attributes_count;
  //   attribute_info attributes[attributes_count];
  // }

  public static void of(MethodModel m, Printer d) {
    d.print(".method  #%d  // %s", m.methodName().index(), m.methodName().stringValue())
     .indent(() -> {
       d.print(".descriptor  #%d  // %s", m.methodType().index(), m.methodType().stringValue())
        .print(".flags  %s",  Access.of(m.flags()));
       m.attributes().forEach(a -> Attributes.of(a,d));
      })
     .print(".end");
  }
}