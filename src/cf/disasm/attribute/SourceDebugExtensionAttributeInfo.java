package cf.disasm.attribute;

import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
import cf.util.Printer;

class SourceDebugExtensionAttributeInfo {

  static void of(SourceDebugExtensionAttribute a, Printer d) {
    d.print(".SourceDebugExtensionAttribute -> "+a);
  }
}
