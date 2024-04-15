package cf.disasm.type;

import java.lang.classfile.constantpool.ClassEntry;
import java.util.List;

import cf.util.More;
import cf.util.Printer;

public class Interface {

  public static void of(List<ClassEntry> list, Printer d) {
    var s = More.of(list);
    d.print(".interfaces")
     .indent(() -> {
        for (var ce:list) {
          d.print("#%d%s  // %s", ce.index(), s.comma(), ce.asInternalName() );
        }
      });
  }
}
