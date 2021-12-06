package de.chaos.mc.lobbysystem.utils.megaUtils.itemskulls;

import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reflections {

	private String OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
	private String NMS_PREFIX = OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");
	private String VERSION = OBC_PREFIX.replace("org.bukkit.craftbukkit", "").replace(".", "");
	private Pattern MATCH_VARIABLE = Pattern.compile("\\{([^\\}]+)\\}");


	private String expandVariables(String name) {
		StringBuffer output = new StringBuffer();
		Matcher matcher = MATCH_VARIABLE.matcher(name);

		while (matcher.find()) {
			String variable = matcher.group(1);
			String replacement;

			if ("nms".equalsIgnoreCase(variable)) replacement = NMS_PREFIX;
			else if ("obc".equalsIgnoreCase(variable)) replacement = OBC_PREFIX;
			else if ("version".equalsIgnoreCase(variable)) replacement = VERSION;
			else throw new IllegalArgumentException("Unknown variable: " + variable);

			if (replacement.length() > 0 && matcher.end() < name.length() && name.charAt(matcher.end()) != '.') replacement += ".";
			matcher.appendReplacement(output, Matcher.quoteReplacement(replacement));
		}

		matcher.appendTail(output);
		return output.toString();
	}

	private Class<?> getCanonicalClass(String canonicalName) {
		try {
			return Class.forName(canonicalName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Cannot find " + canonicalName, e);
		}
	}

	public Class<?> getClass(String lookupName) {
		return getCanonicalClass(expandVariables(lookupName));
	}

	public ConstructorInvoker getConstructor(String className, Class<?>... params) {
		return getConstructor(getClass(className), params);
	}

	public ConstructorInvoker getConstructor(Class<?> clazz, Class<?>... params) {
		for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			if (Arrays.equals(constructor.getParameterTypes(), params)) {

				constructor.setAccessible(true);
				return new ConstructorInvoker() {
					@Override
					public Object invoke(Object... arguments) {
						try {
							return constructor.newInstance(arguments);
						} catch (Exception e) {
							throw new RuntimeException("Cannot invoke constructor " + constructor, e);
						}
					}
				};
			}
		}
		throw new IllegalStateException(String.format("Unable to find constructor for %s (%s).", clazz, Arrays.asList(params)));
	}

	public Class<?> getCraftBukkitClass(String name) {
		return getCanonicalClass(OBC_PREFIX + "." + name);
	}

	public <T> FieldAccessor<T> getField(Class<?> target, String name, Class<T> fieldType) {
		return getField(target, name, fieldType, 0);
	}

	public <T> FieldAccessor<T> getField(String className, String name, Class<T> fieldType) {
		return getField(getClass(className), name, fieldType, 0);
	}

	public <T> FieldAccessor<T> getField(Class<?> target, Class<T> fieldType, int index) {
		return getField(target, null, fieldType, index);
	}

	public <T> FieldAccessor<T> getField(String className, Class<T> fieldType, int index) {
		return getField(getClass(className), fieldType, index);
	}

	private <T> FieldAccessor<T> getField(Class<?> target, String name, Class<T> fieldType, int index) {
		for (final Field field : target.getDeclaredFields()) {
			if ((name == null || field.getName().equals(name)) && fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
				field.setAccessible(true);

				return new FieldAccessor<T>() {
					@SuppressWarnings("unchecked")
					@Override
					public T get(Object target) {
						try {
							return (T) field.get(target);
						} catch (IllegalAccessException e) {
							throw new RuntimeException("Cannot access reflection.", e);
						}
					}

					@Override
					public void set(Object target, Object value) {
						try {
							field.set(target, value);
						} catch (IllegalAccessException e) {
							throw new RuntimeException("Cannot access reflection.", e);
						}
					}

					@Override
					public boolean hasField(Object target) {
						return field.getDeclaringClass().isAssignableFrom(target.getClass());
					}
				};
			}
		}

		// Search in parent classes
		if (target.getSuperclass() != null) return getField(target.getSuperclass(), name, fieldType, index);
		throw new IllegalArgumentException("Cannot find field with type " + fieldType);
	}

	public MethodInvoker getMethod(String className, String methodName, Class<?>... params) {
		return getTypedMethod(getClass(className), methodName, null, params);
	}

	public MethodInvoker getMethod(Class<?> clazz, String methodName, Class<?>... params) {
		return getTypedMethod(clazz, methodName, null, params);
	}

	public Method getMethodSimply(Class<?> clazz, String method) {
		for (Method m : clazz.getMethods()) if (m.getName().equals(method)) return m;
		return null;
	}

	public Class<?> getMinecraftClass(String name) {
		return getCanonicalClass(NMS_PREFIX + "." + name);
	}

	public MethodInvoker getTypedMethod(Class<?> clazz, String methodName, Class<?> returnType, Class<?>... params) {
		for (final Method method : clazz.getDeclaredMethods()) {
			if ((methodName == null || method.getName().equals(methodName)) && (returnType == null)
					|| method.getReturnType().equals(returnType) && Arrays.equals(method.getParameterTypes(), params)) {

				method.setAccessible(true);
				return new MethodInvoker() {
					@Override
					public Object invoke(Object target, Object... arguments) {
						try {
							return method.invoke(target, arguments);
						} catch (Exception e) {
							throw new RuntimeException("Cannot invoke method " + method, e);
						}
					}
				};
			}
		}

		if (clazz.getSuperclass() != null) return getMethod(clazz.getSuperclass(), methodName, params);
		throw new IllegalStateException(
				String.format("Unable to find method %s (%s).", methodName, Arrays.asList(params)));
	}

	public Class<Object> getUntypedClass(String lookupName) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Class<Object> clazz = (Class<Object>) (Class) getClass(lookupName);
		return clazz;
	}

	public <T> T newInstance(Class<T> type) {
		try {
			return type.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public interface ConstructorInvoker {
		public Object invoke(Object... arguments);
	}

	public interface MethodInvoker {
		public Object invoke(Object target, Object... arguments);
	}

	public interface FieldAccessor<T> {
		public T get(Object target);

		public void set(Object target, Object value);

		public boolean hasField(Object target);
	}

}