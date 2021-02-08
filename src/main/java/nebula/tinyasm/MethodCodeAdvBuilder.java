package nebula.tinyasm;

import org.objectweb.asm.MethodVisitor;

public class MethodCodeAdvBuilder extends MethodCodeBuilder {
	public MethodCodeAdvBuilder(MethodVisitor mv, MethodHeaderBuilder mh, LocalsStack locals) {
		super(mv, mh, locals);
	}


}