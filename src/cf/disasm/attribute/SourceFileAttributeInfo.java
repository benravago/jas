package cf.disasm.attribute;

import java.lang.classfile.attribute.SourceFileAttribute;
import cf.util.Printer;

class SourceFileAttributeInfo {

  // SourceFile_attribute {  -- jvms-4.7.10 The SourceFile Attribute
  //   u2 attribute_name_index;
  //   u4 attribute_length;
  //   u2 sourcefile_index;
  // }

  static void of(SourceFileAttribute a, Printer d) {
    d.print(".%s  #%d  // %s", a.attributeName(), a.sourceFile().index(), a.sourceFile().stringValue() );
  }
}