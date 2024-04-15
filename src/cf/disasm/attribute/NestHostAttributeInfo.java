package cf.disasm.attribute;

import java.lang.classfile.attribute.NestHostAttribute;
import cf.util.Printer;

class NestHostAttributeInfo {

  static void of(NestHostAttribute a, Printer d) {
    d.print(".NestHostAttribute -> "+a);
  }
}
