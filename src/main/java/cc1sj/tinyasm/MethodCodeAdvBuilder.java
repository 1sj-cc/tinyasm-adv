package cc1sj.tinyasm;

import org.objectweb.asm.MethodVisitor;

public class MethodCodeAdvBuilder extends MethodCodeAdv {
	public MethodCodeAdvBuilder(MethodVisitor mv, MethodHeaderBuilder mh, LocalsStack locals) {
		super(mv, mh, locals);
	}


}