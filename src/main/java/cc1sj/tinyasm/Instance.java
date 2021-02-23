package cc1sj.tinyasm;

import java.util.function.Consumer;

public interface Instance {
	InvokerPrepare inter(String methodName);

	InvokerPrepare virtual(String methodName);

	InvokerPrepare special(String methodName);

	void setTo(String varname);

	void setElement(int index, Consumer<MethodCode> p0);

	Instance loadElement(int index);

	Instance boxWhenNeed();

	Instance unbox();

	void returnValue();

	void pop();

	default Instance checkcast(Class<?> clazz) {
		return checkcast(Clazz.of(clazz));
	}

	default Instance checkcast(String clazz) {
		String[] genericParameterClazz = {};
		return checkcast(Clazz.of(clazz, genericParameterClazz));
	}

	Instance checkcastAndUnbox(Clazz clazz);

	default Instance checkcastAndUnbox(Class<?> clazz) {
		return checkcastAndUnbox(Clazz.of(clazz));
	}

	default Instance checkcastAndUnbox(String clazz) {
		String[] genericParameterClazz = {};
		return checkcastAndUnbox(Clazz.of(clazz, genericParameterClazz));
	}

	Instance checkcast(Clazz clazz);

	Instance add(String varname);

	Instance add(int intvalue);

	Instance add(long longValue);

	Instance add(Consumer<MethodCode> p0);

	Instance convertTo(String clazz);

	Instance convertTo(Class<?> clazz);

}
