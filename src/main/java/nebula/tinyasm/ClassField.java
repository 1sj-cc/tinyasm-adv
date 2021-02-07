package nebula.tinyasm;

import static nebula.tinyasm.TypeUtils.*;

import java.util.List;

import org.objectweb.asm.Type;

public class ClassField extends Field {
	public ClassField(int access, String name, GenericClazz clazz, Object defaultValue) {
		super(name, clazz);
		this.access = access;
		this.defaultValue = defaultValue;
		this.type = typeOf(clazz);
	}
//
//	@Override
//	public String toString() {
//		return "Field [name=" + name + ", type=" + clazz + "]";
//	}

	final public int access;

	// TODO
	static public <T extends ClassField> Type[] typesOf(List<T> fields) {
		Type[] types = new Type[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			types[i] = fields.get(i).type;
		}
		return types;
	}

//	static public <T extends ClassField> GenericClazz[] genericOf(List<T> fields) {
//		GenericClazz[] generics = new GenericClazz[fields.size()];
//		for (int i = 0; i < fields.size(); i++) {
//			generics[i] = fields.get(i).clazz;
//		}
//		return generics;
//	}

	final public Type type;
	public boolean identifier;
	Object defaultValue;
}