package cf.disasm.attribute;

import java.lang.classfile.Attribute;
import java.lang.classfile.attribute.*;

import cf.util.Printer;

public class Attributes {

  public static void of(Attribute<?> a, Printer d) { // jvms-4.7 Attributes
    switch (a) {
      case ConstantValueAttribute b -> ConstantValueAttributeInfo.of(b,d);
      case CodeAttribute b -> CodeAttributeInfo.of(b,d);
      case StackMapTableAttribute b -> StackMapTableAttributeInfo.of(b,d);
      case ExceptionsAttribute b -> ExceptionsAttributeInfo.of(b,d);
      case InnerClassesAttribute b -> InnerClassesAttributeInfo.of(b,d);
      case EnclosingMethodAttribute b -> EnclosingMethodAttributeInfo.of(b,d);
      case SyntheticAttribute b -> SyntheticAttributeInfo.of(b,d);
      case SignatureAttribute b -> SignatureAttributeInfo.of(b,d);
      case SourceFileAttribute b -> SourceFileAttributeInfo.of(b,d);
      case SourceDebugExtensionAttribute b -> SourceDebugExtensionAttributeInfo.of(b,d);
      case LineNumberTableAttribute b -> LineNumberTableAttributeInfo.of(b,d);
      case LocalVariableTableAttribute b -> LocalVariableTableAttributeInfo.of(b,d);
      case LocalVariableTypeTableAttribute b -> LocalVariableTypeTableAttributeInfo.of(b,d);
      case DeprecatedAttribute b -> DeprecatedAttributeInfo.of(b,d);
      case RuntimeVisibleAnnotationsAttribute b -> RuntimeVisibleAnnotationsAttributeInfo.of(b,d);
      case RuntimeInvisibleAnnotationsAttribute b -> RuntimeInvisibleAnnotationsAttributeInfo.of(b,d);
      case RuntimeVisibleParameterAnnotationsAttribute b -> RuntimeVisibleParameterAnnotationsAttributeInfo.of(b,d);
      case RuntimeInvisibleParameterAnnotationsAttribute b -> RuntimeInvisibleParameterAnnotationsAttributeInfo.of(b,d);
      case RuntimeVisibleTypeAnnotationsAttribute b -> RuntimeVisibleTypeAnnotationsAttributeInfo.of(b,d);
      case RuntimeInvisibleTypeAnnotationsAttribute b -> RuntimeInvisibleTypeAnnotationsAttributeInfo.of(b,d);
      case AnnotationDefaultAttribute b -> AnnotationDefaultAttributeInfo.of(b,d);
      case BootstrapMethodsAttribute b -> BootstrapMethodsAttributeInfo.of(b,d);
      case MethodParametersAttribute b -> MethodParametersAttributeInfo.of(b,d);
      case ModuleAttribute b -> ModuleAttributeInfo.of(b,d);
      case ModulePackagesAttribute b -> ModulePackagesAttributeInfo.of(b,d);
      case ModuleMainClassAttribute b -> ModuleMainClassAttributeInfo.of(b,d);
      case NestHostAttribute b -> NestHostAttributeInfo.of(b,d);
      case NestMembersAttribute b -> NestMembersAttributeInfo.of(b,d);
      case RecordAttribute b -> RecordAttributeInfo.of(b,d);
      case PermittedSubclassesAttribute b -> PermittedSubclassesAttributeInfo.of(b,d);
      default -> d.print("unexpected attribute: "+a);
    };
  }

  //  case CharacterRangeTableAttribute b -> CharacterRangeTableAttributeInfo.of(b,f);
  //  case CompilationIDAttribute b -> CompilationIDAttributeInfo.of(b,f);
  //  case ModuleHashesAttribute b -> ModuleHashesAttributeInfo.of(b,f);
  //  case ModuleResolutionAttribute b -> ModuleResolutionAttributeInfo.of(b,f);
  //  case ModuleTargetAttribute b -> ModuleTargetAttributeInfo.of(b,f);
  //  case SourceIDAttribute b -> SourceIDAttributeInfo.of(b,f);
  //  case UnknownAttribute b -> UnknownAttributeInfo.of(b,f);

  //  TODO: java.lang.classfile.CustomAttribute<T>
}
