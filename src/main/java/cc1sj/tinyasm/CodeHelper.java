package cc1sj.tinyasm;

import java.util.function.Consumer;

public class CodeHelper {
	public static Consumer<MethodCodeAdv> Const(int cst) {
		return p -> p.loadConst(cst);
	}

	public static Consumer<MethodCodeAdv> Const(long cst) {
		return p -> p.loadConst(cst);
	}

	public static Consumer<MethodCodeAdv> Const(String cst) {
		return p -> p.loadConst(cst);
	}

	public static Consumer<MethodCodeAdv> Var(String name) {
		return p -> p.Var(name);
	}
}
