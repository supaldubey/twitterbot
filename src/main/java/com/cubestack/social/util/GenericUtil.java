/**
 * 
 */
package com.cubestack.social.util;

/**
 * @author Supal Dubey
 *
 */
public class GenericUtil {

	public static int parseSafe(String text) {
		// Default
		int number = 0;
		try {
			number = Integer.parseInt(text);
		} catch (Exception e) {
			// Print to console for now.
			e.printStackTrace();
		}
		return number;
	}
}
