package cc1sj.tinyasm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc1sj.tinyasm.sample.ClassBody.MethodCodeMethodCallerSample;
import cc1sj.tinyasm.util.TinyAsmTestUtils;

public class MethodCodeMethodCallerSimpleTest extends TestBase {

	String clazz = MethodCodeMethodCallerSample.class.getName();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMath() throws Exception {
		ClassBody cw = ClassBuilder.make(clazz).body();

		cw.field("i", int.class);

		cw.method("<init>").friendly(mv -> {
			mv.line().initThis();
			mv.line().set("i", m -> m.LOADConstByte(10));
			mv.line().set("i", m -> m.LOADConstByte(100));
			mv.line().returnVoid();

		});

		cw.method("method").ACC_PUBLIC().ACC_STATIC().parameter("data", String.class).friendly(mv -> {
			mv.define("i", int.class);
			mv.define("l", Long.class);
			mv.define("s", String.class);
			Class<?>[] genericParameterClazz = { String.class };
			mv.define("ls", Clazz.of(List.class.getName(), TypeUtils.classnamesOf(genericParameterClazz)));

			mv.line().set("i", m -> m.LOADConstByte(10));

			mv.line()
				.clazz(Long.class)
				.call("valueOf")
				.parameter(long.class)
				.reTurn(Long.class)
				.invoke(m -> m.LOADConst(Long.valueOf(10L)))
				.setTo("l");

			mv.line()
				.clazz(String.class)
				.call("valueOf")
				.parameter(int.class)
				.reTurn(String.class)
				.invoke(m -> m.LOAD(1))
				.setTo("s");

			// @formatter:off
			mv.line().init(StringBuilder.class)
				.virtual("append").parameter(String.class).reTurn(StringBuilder.class).invoke("s")
				.virtual("append").reTurn(StringBuilder.class).invoke("i")
				.virtual("append").parameter(Object.class).reTurn(StringBuilder.class).invoke("l")
				.virtual("toString").reTurn(String.class).invoke()
				.setTo("s");
			// @formatter:on

			mv.line().init(ArrayList.class).setTo("ls");

			mv.line()
				.load("ls")
				.inter("add")
				.parameter(Object.class)
				.reTurn(boolean.class)
				.invoke(m -> m.LOADConst("first"));
			mv.POP();

			mv.line()
				.load("ls")
				.inter("add")
				.parameter(Object.class)
				.reTurn(boolean.class)
				.invoke(m -> m.LOADConst("second"));
			mv.POP();

			mv.line().returnVoid();
		});

		// @formatter:on

		String codeActual = TinyAsmTestUtils.toString(cw.end().toByteArray());
		String codeExpected = TinyAsmTestUtils.toString(clazz);
		assertEquals("Code", codeExpected, codeActual);
	}

//	@Test
//	public void printClass() {
//		System.out.println(RefineCode.refineCode(toString(clazz)));
//	}

}
