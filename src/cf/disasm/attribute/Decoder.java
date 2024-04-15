package cf.disasm.attribute;

import java.lang.classfile.CodeElement;
import java.lang.classfile.CodeModel;
import java.lang.classfile.Instruction;
import java.lang.classfile.Label;
import java.lang.classfile.instruction.*;
import java.lang.classfile.PseudoInstruction;
import java.lang.classfile.constantpool.PoolEntry;

import cf.util.More;
import cf.util.Printer;

class Decoder {
  Decoder(Printer d) {
    this.d = d;
  }

  Printer d; // new Decoder(d).decode(m);

  void decode(CodeModel m) {
    d.indent(() -> {
      m.forEach(this::decode);
      label();
    });
  }

  void decode(CodeElement e) {
    switch (e) {
      case Instruction r -> { switch (r) {
        case ArrayLoadInstruction i -> i_(i);
        case ArrayStoreInstruction i -> i_(i);
        case BranchInstruction i -> i_2j(i);
          // ConstantInstruction
        case ConstantInstruction.ArgumentConstantInstruction i -> push(i);
        case ConstantInstruction.IntrinsicConstantInstruction i -> i_(i);
        case ConstantInstruction.LoadConstantInstruction i -> push(i);
        case ConvertInstruction i -> i_(i);
          // DiscontinuedInstruction
        case DiscontinuedInstruction.JsrInstruction i -> op(i);
        case DiscontinuedInstruction.RetInstruction i -> op(i);
        case FieldInstruction i -> i_2c(i, i.field());
        case IncrementInstruction i -> incr(i);
        case InvokeDynamicInstruction i -> i_2c_0_0(i);
        case InvokeInstruction i -> call(i);
        case LoadInstruction i -> load(i);
        case LookupSwitchInstruction i -> i_p_4d_4n_x(i);
        case MonitorInstruction i -> i_(i);
        case NewMultiArrayInstruction i -> i_2c_1d(i);
        case NewObjectInstruction i -> i_2c(i, i.className());
        case NewPrimitiveArrayInstruction i -> i_1t(i);
        case NewReferenceArrayInstruction i -> i_2c(i, i.componentType());
        case NopInstruction i -> i_(i);
        case OperatorInstruction i -> i_(i);
        case ReturnInstruction i -> i_(i);
        case StackInstruction i -> i_(i);
        case StoreInstruction i -> store(i);
        case TableSwitchInstruction i -> i_p_4d_4l_4h_x(i);
        case ThrowInstruction i -> i_(i);
        case TypeCheckInstruction i -> i_2c(i, i.type());
        default -> {}
      }}
      case PseudoInstruction p -> { switch (p) {
        case CharacterRange i -> pi(i);
        case ExceptionCatch i -> pi(i);
        case LabelTarget i -> target(i);
        case LineNumber i -> line(i);
        case LocalVariable i -> local(i);
        case LocalVariableType i -> pi(i);
        default -> {}
      }}
      default -> ca(e);
        // RuntimeInvisibleTypeAnnotationsAttribute
        // RuntimeVisibleTypeAnnotationsAttribute
        // StackMapTableAttribute
    }
  }

  void load(LoadInstruction i) {
    switch (i.opcode()) {
      case ILOAD, LLOAD, FLOAD, DLOAD, ALOAD -> i_1v(i, i.slot());
      case ILOAD_W, LLOAD_W, FLOAD_W, DLOAD_W, ALOAD_W -> i_1w_2v(i, i.slot());
    }
  }
  void store(StoreInstruction i) {
    switch (i.opcode()) {
      case ISTORE, LSTORE, FSTORE, DSTORE, ASTORE -> i_1v(i, i.slot());
      case ISTORE_W, LSTORE_W, FSTORE_W, DSTORE_W, ASTORE_W -> i_1w_2v(i, i.slot());
    }
  }
  void incr(IncrementInstruction i) {
    switch (i.opcode()) {
      case IINC -> i_1v_1d(i);
      case IINC_W -> i_1w_2v_2d(i);
    }
  }
  void push(ConstantInstruction.ArgumentConstantInstruction i) {
    switch (i.opcode()) {
      case BIPUSH -> i_1b(i, i.constantValue());
      case SIPUSH -> i_2s(i, i.constantValue());
    }
  }
  void push(ConstantInstruction.LoadConstantInstruction i) {
    switch (i.opcode()) {
      case LDC -> i_1c(i, i.constantEntry());
      case LDC_W, LDC2_W -> i_2c(i, i.constantEntry());
    }
  }
  void call(InvokeInstruction i) {
    switch (i.opcode()) {
      case INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC -> i_2c(i, i.method());
      case INVOKEINTERFACE -> i_2c_1d_0(i);
    }
  }

  void i_(Instruction i) {
    label(); d.print("%s", name(i));
  }
  void i_1v(Instruction i, int slot) {
    label(); d.print("%s  _%d", name(i), slot);
  }
  void i_1v_1d(IncrementInstruction i) {
    label(); d.print("%s  _%d, %d", name(i), i.slot(), i.constant());
  }
  void i_1c(Instruction i, PoolEntry e) {
    label(); d.print("%s  #%d  // %s ", name(i), e.index(), string(e));
  }
  void i_2c(Instruction i, PoolEntry e) {
    label(); d.print("%s  #%d  // %s", name(i), e.index(), string(e));
  }
  void i_1b(Instruction i, Integer b) {
    label(); d.print("%s  x%02x  // %d", name(i), b.byteValue(), b.intValue());
  }
  void i_2s(Instruction i, Integer s) {
    label(); d.print("%s  x%04x  // %d", name(i), s.shortValue(), s.intValue());
  }
  void i_2j(BranchInstruction i) {
    label(); d.print("%s  $%s  // %s", name(i), bci(i.target()), i.target().toString());
  }
  void i_1t(NewPrimitiveArrayInstruction i) {
    label(); d.print("%s  %s  // %s", name(i), i.typeKind().typeName(), i.typeKind().toString());
  }
  void i_2c_1d(NewMultiArrayInstruction i) {
    label(); d.print("%s  #%d, %d  // %s", name(i), i.arrayType().index(), i.dimensions(),  i.arrayType().toString());
  }
  void i_2c_1d_0(InvokeInstruction i) {
    label(); d.print("%s  #%d, %d  // %s", name(i),  i.method().index(), i.count(), i.method().toString());
  }
  void i_2c_0_0(InvokeDynamicInstruction i) {
    label(); d.print("%s  #%d  // %s", name(i), i.invokedynamic(), i.invokedynamic().toString());
  }

  void i_1w_2v(Instruction i, int slot) {
    label(); d.print("wide  x%02x, _%d  // %s", bc(i), slot, name(i));
  }
  void i_1w_2v_2d(IncrementInstruction i) {
    label(); d.print("wide  x%02x, _%d, %d  // %s", bc(i), i.slot(), i.constant(), name(i));
  }

  void i_p_4d_4n_x(LookupSwitchInstruction i) {    // lookupswitch  defaultOffset,
    label(); var s = More.of(i.cases());           //   match:offset, ...
    d.print("%s  $%s,", name(i), bci(i.defaultTarget()) )
     .indent(() -> { for (var c:i.cases()) d.print("%d:$%s%s", c.caseValue(), bci(c.target()), s.comma()); });
  }
  void i_p_4d_4l_4h_x(TableSwitchInstruction i) {  // tableswitch defaultOffset, low, high,
    label();var s = More.of(i.cases());            //   jumpOffset, ...
    d.print("%s  $%s, %d, %d,", name(i), bci(i.defaultTarget()), i.lowValue(), i.highValue() )
     .indent(() -> { for (var c:i.cases()) d.print("$%s%s", bci(c.target()), s.comma()); });
  }

  void local(LocalVariable i) {
    label(); d.print("_%d  #%d  // %s %s", i.slot(), i.type().index(), i.name().stringValue(), i.type().stringValue());
  }

  static int bc(Instruction i) { return i.opcode().bytecode(); }
  static String name(Instruction i) { return i.opcode().name().toLowerCase(); }
  static String string(PoolEntry e) { var s = e.toString(); return s.substring(s.indexOf(' ')); }

  String target, line;

  void label() {
    if (target != null) {
      if (line != null) {
        target += line;
      }
      d.indent(-2).print(target).indent(2);
      target = line = null;
    }
  }
  void target(LabelTarget i) {
    target = "$" + bci(i.label());
  }
  void line(LineNumber i) {
    line = ":" + i.line();
  }

  /**
   * NOTE: opportunistic; from, e.g., Label[context=CodeModel[id=770947228], bci=3]
   * otherwise, Label offsets will have to be computed via CodeElement scan
   */
  static String bci(Label l) {
    var s = l.toString();
    var p = s.lastIndexOf('=')+1;
    var q = s.indexOf(']',p);
    return s.substring(p,q);
  }

  void op(Instruction i) {
    label();
    d.print("-> %s", i.toString());
  }
  void pi(PseudoInstruction i) {
    d.indent(-2).print("-> %s", i.toString()).indent(2);
  }
  void ca(CodeElement e) {
    d.indent(-2).print("=> %s", e.toString()).indent(2);
  }
}