package cf.disasm.attribute;

import java.lang.classfile.attribute.SignatureAttribute;
import cf.util.Printer;

class SignatureAttributeInfo {

  // Signature_attribute {  -- jvms-4.7.9 The Signature Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 signature_index;
  // }

  static void of(SignatureAttribute a, Printer d) {
    d.print(".%s  #%d  // %s", a.attributeName(), a.signature().index(), a.signature().stringValue());
  }
}