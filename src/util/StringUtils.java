package util;

/**
 * Contains utility methods for strings.
 *
 * @author Daniel Kleebinder
 */
public class StringUtils {

	/**
	 * Computes the hash code of the given string.
	 *
	 * @param str String.
	 * @return Hash code.
	 */
	public static int hashCode(String str) {
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			result += str.charAt(i) * i;
		}
		return result;
	}
}
