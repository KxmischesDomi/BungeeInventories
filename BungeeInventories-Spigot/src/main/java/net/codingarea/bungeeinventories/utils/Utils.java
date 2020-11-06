package net.codingarea.bungeeinventories.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ⠀
 * .d88b          8 w                  db
 * 8P    .d8b. .d88 w 8d8b. .d88      dPYb   8d8b .d88b .d88
 * 8b    8' .8 8  8 8 8P Y8 8  8     dPwwYb  8P   8.dP' 8  8
 * `Y88P `Y8P' `Y88 8 8   8 `Y88    dP    Yb 8    `Y88P `Y88
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀wwdP
 *
 * @author Dominik https://github.com/kxmischesdomi
 * @since 1.0
 */
public class Utils {


	public static String getEnumName(final Enum<?> enun) {
		return getEnumName(enun.name());
	}

	public static String getEnumName(final String enumName) {

		if (enumName == null) return "";

		final StringBuilder builder = new StringBuilder();
		final String[] chars = enumName.split("");
		chars[0] = chars[0].toUpperCase();
		boolean nextUp = true;
		for (String currentChar : chars) {
			if (currentChar.equals("_")) {
				nextUp = true;
				builder.append(" ");
				continue;
			}
			if (nextUp) {
				builder.append(currentChar.toUpperCase());
				nextUp = false;
			} else {
				builder.append(currentChar.toLowerCase());
			}
		}

		return builder.toString()
				.replace(" And ", " and ")
				.replace(" The ", " the ")
				.replace(" Or ", " or ")
				.replace(" Of " , " of")
				.replace(" In ", " in ")
				.replace(" On " , " on ")
				.replace(" Off ", " off ");
	}

	public static <T> T[] addElememtToArray(T[] array, T t) {
		final List<T> list = new ArrayList<T>(Arrays.asList(array));
		list.add(t);
		return toArray(list);
	}

	public static <T> T[] removeElememtFromArray(T[] array, T t) {
		final List<T> list = new ArrayList<T>(Arrays.asList(array));
		list.remove(t);
		return toArray(list);
	}


	public static <T> T[] toArray(List<T> list) {
		T[] toR = (T[]) java.lang.reflect.Array.newInstance(list.get(0)
				.getClass(), list.size());
		for (int i = 0; i < list.size(); i++) {
			toR[i] = list.get(i);
		}
		return toR;
	}

	public static Object[] objectToArray(Object object) {
		return (Object[]) object;
	}

}