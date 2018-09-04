package nebula.tinyasm.data;

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
		return checkcast(GenericClazz.generic(clazz));
	}

	default Instance checkcast(String clazz) {
		return checkcast(GenericClazz.generic(clazz));
	}

	Instance checkcastAndUnbox(GenericClazz clazz);

	default Instance checkcastAndUnbox(Class<?> clazz) {
		return checkcastAndUnbox(GenericClazz.generic(clazz));
	}

	default Instance checkcastAndUnbox(String clazz) {
		return checkcastAndUnbox(GenericClazz.generic(clazz));
	}

	Instance checkcast(GenericClazz clazz);

	Instance add(String varname);

	Instance add(int intvalue);

	Instance add(long longValue);

	Instance add(Consumer<MethodCode> p0);

	Instance convertTo(String clazz);

	Instance convertTo(Class<?> clazz);

}
