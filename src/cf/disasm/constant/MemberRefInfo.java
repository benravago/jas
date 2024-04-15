package cf.disasm.constant;

import java.lang.classfile.constantpool.MemberRefEntry;
import cf.util.Printer;

class MemberRefInfo {

  static void of(MemberRefEntry a, Printer f) {
    f.print("#%d  %s  #%d.#%d  // %s.%s:%s",
        a.index(),
        CpInfo.kind(a.tag()),
        a.owner().index(), a.nameAndType().index(),
        a.owner().asInternalName(), a.nameAndType().name().stringValue(), a.nameAndType().type().stringValue()
      );
  }
}
