package cf.disasm.constant;

import java.lang.classfile.constantpool.*;

import cf.util.Printer;

public class CpInfo {

  // cp_info {  -- jvms-4.4 The Constant Pool
  //   u1 tag;
  //   u1 info[];
  // }

  public static void of(ConstantPool cp, Printer d) {
    d.print(".constants")
     .indent(() -> cp.forEach(e -> of(e,d)))
     .print(".end");
  }

  public static void of(PoolEntry a, Printer d) {
    switch (a) {
      case ClassEntry b -> ClassInfo.of(b,d);
      case FieldRefEntry b -> FieldRefInfo.of(b,d);
      case MethodRefEntry b -> MethodRefInfo.of(b,d);
      case InterfaceMethodRefEntry b -> InterfaceMethodRefInfo.of(b,d);
      case StringEntry b -> StringInfo.of(b,d);
      case IntegerEntry b -> IntegerInfo.of(b,d);
      case FloatEntry b -> FloatInfo.of(b,d);
      case LongEntry b -> LongInfo.of(b,d);
      case DoubleEntry b -> DoubleInfo.of(b,d);
      case NameAndTypeEntry b -> NameAndTypeInfo.of(b,d);
      case Utf8Entry b -> Utf8Info.of(b,d);
      case MethodHandleEntry b -> MethodHandleInfo.of(b,d);
      case MethodTypeEntry b -> MethodTypeInfo.of(b,d);
      case ConstantDynamicEntry b -> DynamicInfo.of(b,d);
      case InvokeDynamicEntry b -> InvokeDynamicInfo.of(b,d);
      case ModuleEntry b -> ModuleInfo.of(b,d);
      case PackageEntry b -> PackageInfo.of(b,d);
      default -> d.print("unexpected cp_info -> "+a);
    };
  }
   // case AnnotationConstantValueEntry b -> AnnotationConstantValueInfo.of(b);
   // case ConstantValueEntry b -> ConstantValueInfo.of(b);
   // case DynamicConstantPoolEntry b -> DynamicConstantPoolInfo.of(b);
   // case LoadableConstantEntry b -> LoadableConstantInfo.of(b);

  static String kind(byte tag) { // jvms-4.4-B Constant pool tags
    return switch (tag) {
      case  1 -> "Utf8";
      case  3 -> "Integer";
      case  4 -> "Float";
      case  5 -> "Long";
      case  6 -> "Double";
      case  7 -> "Class";
      case  8 -> "String";
      case  9 -> "Field";
      case 10 -> "Method";
      case 11 -> "InterfaceMethod";
      case 12 -> "NameAndType";
      case 15 -> "MethodHandle";
      case 16 -> "MethodType";
      case 17 -> "Dynamic";
      case 18 -> "InvokeDynamic";
      case 19 -> "Module";
      case 20 -> "Package";
      default -> "0x"+Integer.toHexString(tag);
    };
  }

  static String quote(String s) {
    return s.indexOf(' ') < 0 ? s
         : s.indexOf('"') < 0 ? '"'+s+'"'
         : "'"+s+"'";
  }
}
