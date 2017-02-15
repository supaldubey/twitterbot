/**
 * 
 */
package com.cubestack.social.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Internet
 *
 * @Source: http://theopentutorials.com/tutorials/java/util/generating-a-random-password-with-restriction-in-java/
 */
public class PasswordGenerator {
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SPL_CHARS = "!@#$%^&*_=+/";

    public static char[] generatePswd(int minimum, int max, int caps, int digits, int special) {
	if (minimum > max)
	    throw new IllegalArgumentException("Min. Length > Max. Length!");
	if ((caps + digits + special) > minimum)
	    throw new IllegalArgumentException(
		    "Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
	Random rnd = new SecureRandom();
	int len = rnd.nextInt(max - minimum + 1) + minimum;
	char[] pswd = new char[len];
	int index = 0;
	for (int i = 0; i < caps; i++) {
	    index = getNextIndex(rnd, len, pswd);
	    pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
	}
	for (int i = 0; i < digits; i++) {
	    index = getNextIndex(rnd, len, pswd);
	    pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
	}
	for (int i = 0; i < special; i++) {
	    index = getNextIndex(rnd, len, pswd);
	    pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
	}
	for (int i = 0; i < len; i++) {
	    if (pswd[i] == 0) {
		pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
	    }
	}
	return pswd;
    }

    public static char[] generatePswd() {
	return generatePswd(8, 12, 2, 1, 1);
    }

    private static int getNextIndex(Random rnd, int len, char[] pswd) {
	int index = rnd.nextInt(len);
	while (pswd[index = rnd.nextInt(len)] != 0)
	    ;
	return index;
    }

    public static void main(String[] args) {
	int noOfCAPSAlpha = 1;
	int noOfDigits = 1;
	int noOfSplChars = 1;
	int minLen = 8;
	int maxLen = 12;

	for (int i = 0; i < 10; i++) {
	    char[] pswd = generatePswd(minLen, maxLen, noOfCAPSAlpha, noOfDigits, noOfSplChars);
	    System.out.println("Len = " + pswd.length + ", " + new String(pswd));
	}
    }
}
