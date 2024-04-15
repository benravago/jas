package cf.disasm;

import java.io.PrintStream;

import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;

import java.util.List;
import java.util.function.Consumer;

import cf.disasm.attribute.Attributes;
import cf.disasm.constant.CpInfo;
import cf.disasm.member.FieldInfo;
import cf.disasm.member.MethodInfo;
import cf.disasm.type.Access;
import cf.disasm.type.Interface;
import cf.util.Printer;

public class Disassembler {

  Disassembler() {}

  public static Disassembler of(ClassFile.Option...options) {
    var dis = new Disassembler();
    dis.cf = ClassFile.of(options);
    return dis;
  }

  public Disassembler parse(byte[] bytes) {
    cm = cf.parse(bytes);
    return this;
  }

  public void writeTo(PrintStream out) {
    f = new Printer(out);
    class_file();
  }

  ClassFile cf;
  ClassModel cm;
  Printer f;

  void class_file() {
    f.print(".class")
     .indent(() -> magic())
     .print(".end");
  }
                      //  ClassFile {
  void magic() {      //    u4             magic;
                      //    u2             minor_version;
    version();        //    u2             major_version;
                      //    u2             constant_pool_count;
    constant_pool();  //    cp_info        constant_pool[constant_pool_count-1];
    access_flags();   //    u2             access_flags;
    this_class();     //    u2             this_class;
    super_class();    //    u2             super_class;
                      //    u2             interfaces_count;
    interfaces();     //    u2             interfaces[interfaces_count];
                      //    u2             fields_count;
    fields();         //    field_info     fields[fields_count];
                      //    u2             methods_count;
    methods();        //    method_info    methods[methods_count];
                      //    u2             attributes_count;
    attributes();     //    attribute_info attributes[attributes_count];
  }                   //  }

  void version() {
    f.print(".version  %d.%d", cm.majorVersion(), cm.minorVersion());
  }
  void constant_pool() {
    CpInfo.of(cm.constantPool(), f);
  }
  void access_flags() {
    f.print(".flags  %s", Access.of(cm.flags()));
  }
  void this_class() {
    f.print(".this  #%d  // %s", cm.thisClass().index(), cm.thisClass().asInternalName() );
  }
  void super_class() {
    cm.superclass().ifPresent(s -> f.print(".super  #%d  // %s", s.index(), s.asInternalName()));
  }
  void interfaces() {
    ifPresent(cm.interfaces(), interfaces -> Interface.of(interfaces, f) );
  }
  void fields() {
    cm.fields().forEach(m -> FieldInfo.of(m,f));
  }
  void methods() {
    cm.methods().forEach(m -> MethodInfo.of(m,f));
  }
  void attributes() {
    cm.attributes().forEach(a -> Attributes.of(a,f));
  }

  static <T> void ifPresent(List<T> list, Consumer<List<T>> consumer) {
    if (list != null && ! list.isEmpty()) consumer.accept(list);
  }
}