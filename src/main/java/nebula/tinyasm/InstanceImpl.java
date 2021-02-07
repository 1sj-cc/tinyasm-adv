package nebula.tinyasm;

import static nebula.tinyasm.TypeUtils.*;

import java.util.function.Consumer;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class InstanceImpl implements Instance {
	final MethodCode mv;
	final Type instanceType;

	public InstanceImpl(MethodCode mv, Type instanceType) {
		super();
		this.mv = mv;
		this.instanceType = instanceType;
	}

	@Override
	public InvokerPrepare inter(String methodName) {
		return new InvokerImpl(mv, Opcodes.INVOKEINTERFACE, instanceType, methodName);
	}

	@Override
	public InvokerPrepare virtual(String methodName) {
		return new InvokerImpl(mv, Opcodes.INVOKEVIRTUAL, instanceType, methodName);
	}

	@Override
	public InvokerPrepare special(String methodName) {
		return new InvokerImpl(mv, Opcodes.INVOKESPECIAL, instanceType, methodName);
	}

	@Override
	public void setTo(String varname) {
		mv.store(varname);
	}

	@Override
	public void setElement(int index, Consumer<MethodCode> p0) {
		Type arrayType = mv.stackTypeOf(0);
		assert arrayType.getSort() == Type.ARRAY;

		mv.LOADConst(index);
		p0.accept(mv);
		mv.ARRAYSTORE();
	}

	@Override
	public Instance loadElement(int index) {
		Type arrayType = mv.stackTypeOf(0);
		assert arrayType.getSort() == Type.ARRAY;

		mv.LOADConst(index);
		mv.ARRAYLOAD(arrayType.getElementType());
		return mv.topInstance();
	}

	@Override
	public Instance boxWhenNeed() {
		return mv.boxTop();
	}

	@Override
	public Instance checkcastAndUnbox(Clazz clazz) {
		return mv.checkcastAndUnbox(clazz);
	}

	@Override
	public Instance unbox() {
		return mv.unboxTop();
	}

	@Override
	public void pop() {
		mv.POP();
	}

	@Override
	public void returnValue() {
		mv.RETURNTop();
	}

	@Override
	public Instance checkcast(Clazz clazz) {
		mv.CHECKCAST(clazz.getType());
		return mv.topInstance();
	}

	@Override
	public Instance add(String varname) {
		mv.load(varname);
		mv.ADD();
		return mv.topInstance();
	}

	@Override
	public Instance add(int intvalue) {
		mv.LOADConst(intvalue);
		mv.ADD();
		return mv.topInstance();
	}

	@Override
	public Instance add(long longValue) {
		mv.LOADConst(longValue);
		mv.ADD();
		return mv.topInstance();
	}

	@Override
	public Instance add(Consumer<MethodCode> p0) {
		p0.accept(mv);
		mv.ADD();
		return mv.topInstance();
	}

	@Override
	public Instance convertTo(String clazz) {
		mv.CONVERTTO(clazz);
		return mv.topInstance();
	}

	@Override
	public Instance convertTo(Class<?> clazz) {
		mv.CONVERTTO(clazz);
		return mv.topInstance();
	}

}
