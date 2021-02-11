package nebula.tinyasm;

import java.util.function.Consumer;

public interface InvokerPrepare {
	InvokerPrepare parameter(Clazz clazz);

	default InvokerPrepare parameter(Class<?> clazz,boolean array) {
		return parameter(Clazz.of(clazz, array));
	}
	default InvokerPrepare parameter(Class<?> clazz) {
		return parameter(Clazz.of(clazz));
	}

	default InvokerPrepare parameter(String clazz) {
		String[] genericParameterClazz = {};
		return parameter(Clazz.of(clazz, genericParameterClazz));
	}

	@SuppressWarnings("unchecked")
	default void invokeVoid(Consumer<MethodCodeAdv> p0) {
		invokeVoid(new Consumer[] { p0 });
	}

	@SuppressWarnings("unchecked")
	default void invokeVoid(Consumer<MethodCodeAdv> p0, Consumer<MethodCodeAdv> p1) {
		invokeVoid(new Consumer[] { p0, p1, });
	}

	@SuppressWarnings("unchecked")
	default void invokeVoid(Consumer<MethodCodeAdv> p0, Consumer<MethodCodeAdv> p1, Consumer<MethodCodeAdv> p2) {
		invokeVoid(new Consumer[] { p0, p1, p2 });
	}

	@SuppressWarnings("unchecked")
	default void invokeVoid(Consumer<MethodCodeAdv> p0, Consumer<MethodCodeAdv> p1, Consumer<MethodCodeAdv> p2, Consumer<MethodCodeAdv> p3) {
		invokeVoid(new Consumer[] { p0, p1, p2, p3 });
	}

	@SuppressWarnings("unchecked")
	default void invokeVoid(Consumer<MethodCodeAdv> p0, Consumer<MethodCodeAdv> p1, Consumer<MethodCodeAdv> p2, Consumer<MethodCodeAdv> p3,
			Consumer<MethodCodeAdv> p4) {
		invokeVoid(new Consumer[] { p0, p1, p2, p3, p4 });
	}

	void invokeVoid(Consumer<MethodCodeAdv>[] invocations);

	void invokeVoid(String... varname);

	Invoker returnObject(Clazz returnClazz);

	default Invoker reTurn(Class<?> returnClazz) {
		return returnObject(Clazz.of(returnClazz));
	}

	default Invoker reTurn(String returnClazz) {
		String[] genericParameterClazz = {};
		return returnObject(Clazz.of(returnClazz, genericParameterClazz));
	}

}