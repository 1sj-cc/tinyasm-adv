package cc1sj.tinyasm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc1sj.tinyasm.sample.ClassBody.MethodCodeMethodCallerSample;
import cc1sj.tinyasm.sample.MethodCode.InvokeSample;
import cc1sj.tinyasm.util.TinyAsmTestUtils;

public class MethodCodeInvokeTest extends TestBase {

	String clazz = MethodCodeMethodCallerSample.class.getName();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMath() throws Exception {
		ClassBody cw = ClassBuilder.class_(clazz).body();

		cw.privateField("i", int.class);

		cw.method("<init>").friendly(mv -> {
			mv.line().initThis();
			mv.line().setConst("i", 10);
			mv.line().setConst("i", 100);
			mv.line().returnVoid();
		});

		// @formatter:off
		cw.method("method").public_().static_().parameter("data", String.class).friendly(mv -> {
			mv.define("i", int.class);
			mv.define("l", Long.class);
			mv.define("s", String.class);
			Class<?>[] genericParameterClazz = { String.class };
			mv.define("ls", Clazz.of(List.class.getName(), TypeUtils.classnamesOf(genericParameterClazz)));

			mv.line().setConst("i", 10);
			mv.line().clazz(Long.class).call("valueOf").parameter(long.class).Return(Long.class).Invoke(p->p.LOADConst(Long.valueOf(10L))).setTo("l");

			mv.line().clazz(String.class).call("valueOf").parameter(int.class).Return(String.class).invoke("i").setTo("s");
			
			mv.line().init(StringBuilder.class).Virtual("append").Return(StringBuilder.class).invoke("s")
				.Virtual("append").parameter(int.class).Return(StringBuilder.class).invoke("i")
				.Virtual("append").parameter(Object.class).Return(StringBuilder.class).invoke("l")
				.Virtual("toString").Return(String.class).Invoke().setTo("s");
			
			mv.line().init(ArrayList.class).setTo("ls");
			mv.line().Var("ls").Interface("add").parameter(Object.class).Return(boolean.class).Invoke(p->p.LOADConst("first")).pop();
			mv.line().Var("ls").Interface("add").parameter(Object.class).Return(boolean.class).Invoke(p->p.LOADConst("second")).pop();


			mv.line().returnVoid();
		});

		// @formatter:on

		String codeActual = TinyAsmTestUtils.toString(clazz,cw.end().toByteArray());
		String codeExpected = TinyAsmTestUtils.toString(clazz);
		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void testInvoke() throws Exception {
		String clazz = InvokeSample.class.getName();
		ClassBody cw = ClassBuilder.class_(clazz).body();

		cw.privateField("i", int.class);
		cw.privateField("j", int.class);
		cw.privateField("l", long.class);
		cw.privateField("L", Long.class);

		cw.method("<init>").friendly(mv -> {
			mv.line().initThis();
			mv.line().setConst("i", 0);
			mv.line().setConst("j", 0);
			mv.line().setConst("l", 0L);
			mv.returnVoid();
		});

		cw.method("run").friendly(mv -> {

			// long kl = 0L;
			mv.define("kl", long.class);
			mv.line().setConst("kl", 0L);
			// Long kL = 0L;
			mv.define("kL", Long.class);
			mv.line().set("kL", p -> p.loadConst(0L).boxWhenNeed());
			// L = kl;
			mv.line().set("L", p -> p.Var("kl").boxWhenNeed());
			// l = kL;
			mv.line().set("l", p -> p.Var("kL").unbox());

			// invokeVoid();
			mv.line().loadThis().Virtual("invokeVoid").InvokeVoid();
			// invokeVoid(i);
			mv.line().loadThis().Virtual("invokeVoid").InvokeVoid("i");
			// invokeVoid(j, i);
			mv.line().loadThis().Virtual("invokeVoid").InvokeVoid("j", "i");
			// invokeVoid(i);
			mv.line().loadThis().Virtual("invokeVoid").parameter(int.class).InvokeVoid("i");
			// invokeVoid(j, i);
			mv.line().loadThis().Virtual("invokeVoid").parameter(int.class).parameter(int.class).InvokeVoid("j", "i");
			// invokeVoid(i);
			mv.line().loadThis().Virtual("invokeVoid").invokeVoid(p -> p.loadThisField("i"));
			// invokeVoid(j, i);
			mv.line().loadThis().Virtual("invokeVoid").invokeVoid(p -> p.loadThisField("j"), p -> p.loadThisField("i"));
			// invokeVoid(i);
			mv.line().loadThis().Virtual("invokeVoid").parameter(int.class).invokeVoid(p -> p.loadThisField("i"));
			// invokeVoid(j, i);
			mv.line().loadThis().Virtual("invokeVoid").parameter(int.class).parameter(int.class).invokeVoid(p -> p.loadThisField("j"), p -> p.loadThisField("i"));

			mv.line().returnVoid(); 
		});

		cw.method("boxUnbox").friendly(mv -> {
//			//boolean z=false;
			mv.define("z", boolean.class);
			mv.line().set("z", p -> p.loadConst(0));
			// byte b=0;
			mv.define("b", byte.class);
			mv.line().set("b", p -> p.loadConst(0));
			// char c='a';
			mv.define("c", char.class);
			mv.line().set("c", p -> p.loadConst('a'));
			// short s = (short)1;
			mv.define("s", short.class);
			mv.line().set("s", p -> p.loadConst(1));
			// int i = 1;
			mv.define("i", int.class);
			mv.line().set("i", p -> p.loadConst(1));
			// long l=1L;
			mv.define("l", long.class);
			mv.line().set("l", p -> p.loadConst(1L));
			// float f = 0f;
			mv.define("f", float.class);
			mv.line().set("f", p -> p.loadConst(0.1f));
			// double d=0d;
			mv.define("d", double.class);
			mv.line().set("d", p -> p.loadConst(0.1d));
			// String str = "str";
			mv.define("str", String.class);
			mv.line().set("str", p -> p.loadConst("str"));
			// Boolean Z = z;
			mv.define("Z", Boolean.class);
			mv.line().set("Z", p -> p.Var("z").boxWhenNeed());
			// Byte B = b;
			mv.define("B", Byte.class);
			mv.line().set("B", p -> p.Var("b").boxWhenNeed());
			// Character C =c;
			mv.define("C", Character.class);
			mv.line().set("C", p -> p.Var("c").boxWhenNeed());
			// Short S=s;
			mv.define("S", Short.class);
			mv.line().set("S", p -> p.Var("s").boxWhenNeed());
			// Integer I=i;
			mv.define("I", Integer.class);
			mv.line().set("I", p -> p.Var("i").boxWhenNeed());
			// Long L=l;
			mv.define("L", Long.class);
			mv.line().set("L", p -> p.Var("l").boxWhenNeed());
			// Float F=f;
			mv.define("F", Float.class);
			mv.line().set("F", p -> p.Var("f").boxWhenNeed());
			// Double D=d;
			mv.define("D", Double.class);
			mv.line().set("D", p -> p.Var("d").boxWhenNeed());
			// String STR = str;
			mv.define("STR", String.class);
			mv.line().set("STR", p -> p.Var("str").boxWhenNeed());
			
			// z=Z;
			mv.line().set("z", p -> p.Var("Z").unbox());
			// b = B;
			mv.line().set("b", p -> p.Var("B").unbox());
			// c = C;
			mv.line().set("c", p -> p.Var("C").unbox());
			// s = S;
			mv.line().set("s", p -> p.Var("S").unbox());
			// i = I;
			mv.line().set("i", p -> p.Var("I").unbox());
			// l= L;
			mv.line().set("l", p -> p.Var("L").unbox());
			// f = F;
			mv.line().set("f", p -> p.Var("F").unbox());
			// d = D;
			mv.line().set("d", p -> p.Var("D").unbox());
			//str = STR;
			mv.line().set("str", p -> p.Var("STR").unbox());
			mv.line().returnVoid();
		});
		cw.method("invokeVoid").friendly(mv -> {
			mv.define("k", int.class);
			mv.line().setConst("k", 10);
			mv.line().set("i", p -> p.Var("k").add("i"));
			mv.line().returnVoid();
		});
		cw.method("invokeVoid").parameter("p1", int.class).friendly(mv -> {
			mv.line().set("i", p -> p.Var("p1").add("i"));
			mv.line().returnVoid();
		});
		cw.method("invokeVoid").parameter("p1", int.class).parameter("p2", int.class).friendly(mv -> {
			mv.line().set("i", p -> p.Var("p1").add("i").add("p2"));
			mv.line().returnVoid();
		});
//
//		// @formatter:off
//		cw.method("method").ACC_PUBLIC().ACC_STATIC().parameter("data", String.class).friendly(mv -> {
//			mv.define("i", int.class);
//			mv.define("l", Long.class);
//			mv.define("s", String.class);
//			mv.define("ls", GenericClazz.generic(List.class, String.class));
//
//			mv.line().setConst("i", 10);
//			mv.line().clazz(Long.class).call("valueOf").parameter(long.class).reTurn(Long.class).invoke(p->p.LOADConst(new Long(10L))).setTo("l");
//
//			mv.line().clazz(String.class).call("valueOf").parameter(int.class).reTurn(String.class).invoke("i").setTo("s");
//			
//			mv.line().init(StringBuilder.class).virtual("append").reTurn(StringBuilder.class).invoke("s")
//				.virtual("append").parameter(int.class).reTurn(StringBuilder.class).invoke("i")
//				.virtual("append").parameter(Object.class).reTurn(StringBuilder.class).invoke("l")
//				.virtual("toString").reTurn(String.class).invoke().setTo("s");
//			
//			mv.line().init(ArrayList.class).setTo("ls");
//			mv.line().load("ls").inter("add").parameter(Object.class).reTurn(boolean.class).invoke(p->p.LOADConst("first")).pop();
//			mv.line().load("ls").inter("add").parameter(Object.class).reTurn(boolean.class).invoke(p->p.LOADConst("second")).pop();
//
//
//			mv.line().returnVoid();
//		});

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
