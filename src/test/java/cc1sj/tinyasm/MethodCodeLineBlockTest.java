package cc1sj.tinyasm;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc1sj.tinyasm.sample.ClassBody.MethodCodeMethodCallerSample;
import cc1sj.tinyasm.util.TinyAsmTestUtils;

public class MethodCodeLineBlockTest extends TestBase {

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

		cw.method(/* V */ "<init>" /**/).friendly(mv -> {
			mv.line(line -> {
				line.LOAD(0);
				line.SPECIAL(Object.class, "<init>").INVOKE();
				line.line();
				line.LOAD(0);
				line.LOADConstByte(10);
				line.PUTFIELD_OF_THIS("i");
			}).line(line -> {
				line.LOAD(0);
				line.LOADConstByte(100);
				line.PUTFIELD_OF_THIS("i");
			});

			mv.LINE();
			mv.RETURN();

		});

		cw.method("method").ACC_PUBLIC().ACC_STATIC().parameter("data", String.class).friendly(mv -> {
			mv.define("i", int.class);
			mv.define("l", Long.class);
			mv.define("s", String.class);
			Class<?>[] genericParameterClazz = { String.class };
			mv.define("ls", Clazz.of(List.class.getName(), TypeUtils.classnamesOf(genericParameterClazz)));

			mv.line(line -> {
				line.LOADConstByte(10);
				line.STORE("i");
			}).line(line -> {
				line.LOADConst(Long.valueOf(10L));
				line.STATIC(Long.class, "valueOf").parameter(long.class).reTurn(Long.class).INVOKE();
				line.STORE("l");

			}).line(line -> {
				line.LOAD(1);
				line.STATIC(String.class, "valueOf").parameter(int.class).reTurn(String.class).INVOKE();
				line.STORE("s");

			}).line(line -> {
				line.NEW("java/lang/StringBuilder");
				line.DUP();
				line.SPECIAL(StringBuilder.class, "<init>").INVOKE();
				line.LOAD("s");
				line.VIRTUAL(StringBuilder.class, "append").parameter(String.class).reTurn(StringBuilder.class).INVOKE();
				line.LOAD("i");
				line.VIRTUAL(StringBuilder.class, "append").parameter(int.class).reTurn(StringBuilder.class).INVOKE();
				line.LOAD("l");
				line.VIRTUAL(StringBuilder.class, "append").parameter(Object.class).reTurn(StringBuilder.class).INVOKE();
				line.VIRTUAL(StringBuilder.class, "toString").reTurn(String.class).INVOKE();
				line.STORE("s");

			}).line(line -> {
				line.NEW("java/util/ArrayList");
				line.DUP();
				line.SPECIAL("java/util/ArrayList", "<init>").INVOKE();
				line.STORE("ls");

			}).line(line -> {
				line.LOAD("ls");
				line.LOADConst("first");
				line.INTERFACE("java/util/List", "add").parameter(Object.class).reTurn(boolean.class).INVOKE();
				line.POP();

			}).line(line -> {
				line.LOAD("ls");
				line.LOADConst("second");
				line.INTERFACE("java/util/List", "add").parameter(Object.class).reTurn(boolean.class).INVOKE();
				line.POP();
			});

			mv.LINE();
			mv.RETURN();
		});

		// @formatter:on

		String codeActual = TinyAsmTestUtils.toString(clazz,cw.end().toByteArray());
		String codeExpected = TinyAsmTestUtils.toString(clazz);
		assertEquals("Code", codeExpected, codeActual);
	}

//	@Test
//	public void printClass() {
//		System.out.println(RefineCode.refineCode(toString(clazz)));
//	}

}
