package cf.disasm.constant;

import java.lang.classfile.constantpool.Utf8Entry;
import cf.util.Printer;

class Utf8Info {

  static void of(Utf8Entry a, Printer f) {
    f.print("#%d  %s  %s", a.index(), CpInfo.kind(a.tag()), CpInfo.quote(a.stringValue()) );
  }
}
