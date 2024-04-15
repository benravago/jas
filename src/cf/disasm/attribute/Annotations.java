package cf.disasm.attribute;

import java.lang.classfile.Annotation;
import java.util.List;

import cf.util.Printer;
import cf.util.More;

class Annotations {

  static void print(List<Annotation> annotations, Printer d) {
    for (var a:annotations) print(a,d);
  }

  static void print(Annotation a, Printer d) {
    var s = More.of(a.elements());
    d.print("#%d  // %s", a.className().index(), a.className().stringValue())
     .indent(() -> {
        for (var e:a.elements()) {
          d.print("%s:%s%s", e.name().stringValue(), e.value().toString(), s.comma() );
        }
      });
  }
}