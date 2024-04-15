package cf.disasm.attribute;

import java.lang.classfile.attribute.NestMembersAttribute;
import cf.util.Printer;

class NestMembersAttributeInfo {

  static void of(NestMembersAttribute a, Printer d) {
    d.print(".NestMembersAttribute -> "+a);
  }
}
