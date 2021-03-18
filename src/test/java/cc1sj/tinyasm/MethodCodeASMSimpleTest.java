package cc1sj.tinyasm;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc1sj.tinyasm.util.TinyAsmTestUtils;

public class MethodCodeASMSimpleTest extends TestBase {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMath() throws Exception {
		String clazz = MethodCodeASMSimpleSample.class.getName();
		ClassBody cw = ClassBuilder.class_(clazz).body();

		// @formatter:off
		cw.privateField("b",  byte.class);
		cw.privateField("c", char.class);
		cw.privateField("s",short.class);
		cw.privateField("i",int.class);
		cw.privateField("l",long.class);
		cw.privateField("f",float.class);
		cw.privateField("d",double.class);
		cw.privateField("str",String.class);
		
		cw.publicMethod("<init>").code(mc -> {
			mc.LINE(3);
			mc.INIT_OBJECT();
			mc.RETURN();
		});
		
		cw.method(int.class, "getField").code(mc->{
			mc.define("x", int.class);
			mc.LINE(14);mc.LOAD("this");
			mc.GETFIELD_OF_THIS("b");
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("i");
			mc.ADD();
			mc.STORE("x");
			mc.LINE(15);mc.LOAD("x");
			mc.RETURNTop();
		});

		cw.method(int.class, "getFieldAll").friendly(mc->{
			mc.define("x", int.class);
			mc.LINE(19);
			mc.loadThisField("b");
			mc.loadThisField("c");
			mc.ADD();
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("s");
			mc.ADD();
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("i");
			mc.ADD();
			mc.CONVERTTO(long.class);
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("l");
			mc.ADD();
			mc.CONVERTTO(float.class);
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("f");
			mc.ADD();
			mc.CONVERTTO(double.class);
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("d");
			mc.ADD();
			mc.CONVERTTO(int.class);
			mc.STORE("x");
			mc.LINE(20);mc.LOAD("x");
			mc.RETURNTop();
		});

		cw.publicMethod(int.class,"getFieldConst").code(mc->{
			mc.LINE(24);mc.LOAD("this");
			mc.LOADConstByte(100);
			mc.PUTFIELD_OF_THIS("b");
			mc.LINE(25);mc.LOAD("this");
			mc.LOADConstShort(1000);
			mc.PUTFIELD_OF_THIS("c");
			mc.LINE(26);mc.LOAD("this");
			mc.LOADConstShort(10000);
			mc.PUTFIELD_OF_THIS("s");

			mc.LINE(27);mc.LOAD("this");
			mc.LOADConst(100000);
			mc.PUTFIELD_OF_THIS("i");

			mc.LINE(28);mc.LOAD("this");
			mc.LOADConst(1000000L);
			mc.PUTFIELD_OF_THIS("l");
			
			mc.LINE(29);mc.LOAD("this");
			mc.LOADConst(1.0E7F);
			mc.PUTFIELD_OF_THIS("f");


			mc.LINE(30);mc.LOAD("this");
			mc.LOADConst(1.0E8D);
			mc.PUTFIELD_OF_THIS("d");
			
			mc.LINE(31);mc.LOAD("this");
			mc.LOADConst("1000000000s");
			mc.PUTFIELD_OF_THIS("str");
			
			mc.LINE(32);mc.LOAD("this");
			mc.GETFIELD_OF_THIS("b");
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("c");
			mc.ADD();
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("s");
			mc.ADD();
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("i");
			mc.ADD();
			mc.CONVERTTO(long.class);
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("l");
			mc.ADD();
			mc.CONVERTTO(float.class);
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("f");
			mc.ADD();
			mc.CONVERTTO(double.class);
			mc.LOAD("this");
			mc.GETFIELD_OF_THIS("d");
			mc.ADD();

			mc.CONVERTTO(int.class);
			mc.RETURNTop();

		});		
		
		cw.method(int.class, "getFieldIConst").code(mc->{

			int i = 36;
			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(0);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(1);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(2);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(3);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(4);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(5);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(6);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(7);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(8);
			mc.PUTFIELD_OF_THIS("b");
			
			
			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(-4);
			mc.PUTFIELD_OF_THIS("b");

			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(-3);
			mc.PUTFIELD_OF_THIS("b");
			
			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(-2);
			mc.PUTFIELD_OF_THIS("b");


			mc.LINE(i++);mc.LOAD("this");
			mc.LOADConstByte(-1);
			mc.PUTFIELD_OF_THIS("b");
			
			mc.LINE(i++);mc.LOAD("this");
			mc.GETFIELD_OF_THIS("b");
			mc.RETURNTop();

		});

		cw.method(byte.class, "retByte").code(mc->{
			mc.define("x", byte.class);
			mc.define("y", byte.class);
			mc.LINE(59);mc.LOADConstByte(1);
			mc.STORE("x");
			mc.LINE();mc.LOAD("x");
			mc.LOADConstByte(1);
			mc.ADD();
			mc.CONVERTTO(byte.class);
			mc.STORE("x");
			
			mc.LINE();mc.LOAD_THIS();
			mc.LOADConstByte(10);
			mc.PUTFIELD_OF_THIS("b");
			mc.LINE();mc.LOAD_THIS();
			mc.GETFIELD_OF_THIS("b");
			mc.STORE("x");
			mc.LINE();mc.LOAD_THIS();
			mc.GETFIELD_OF_THIS("b");
			mc.LOAD("x");
			mc.ADD();
			mc.CONVERTTO(byte.class);
			mc.STORE("y");
			mc.LINE();
			mc.RETURN("y");			
		});
		
		// @formatter:on

		String codeActual = TinyAsmTestUtils.toString(clazz,cw.end().toByteArray());
		String codeExpected = TinyAsmTestUtils.toString(clazz);
		assertEquals("Code", codeExpected, codeActual);
	}

//	@Test
//	public void printClass() throws IOException {
//		System.out.println(toString(ClassBuilderField.class.getName()));
//	}
}
