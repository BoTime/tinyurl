package com.example.servingwebcontent;

import org.apache.commons.codec.digest.MurmurHash2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Murmurhash3 reference:
 * https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/digest/MurmurHash3.html
 * 
 */
public class TinyUrlGenerator {
	private final static Logger LOG = LoggerFactory.getLogger(TinyUrlGenerator.class);
    private static char[] lookup;
    private final static long BASE = 62;

    static {
        lookup = new char[(int) BASE];
        int i = 0;
        for (int j = 0; j < 26; j++) {
            lookup[i] = (char)('a' + j);
            i++;
        }
        for (int j = 0; j < 26; j++) {
            lookup[i] = (char)('A' + j);
            i++;
        }
        for (int j = 0; j < 10; j++) {
            lookup[i] = (char) ('0' + j);
            i++;
        }
    }
    public static String generate(String originalUrl) {
        long hashedValue = Math.abs(MurmurHash2.hash32(originalUrl));
        String tinyUrl = toBase62(hashedValue);
        return tinyUrl;
    }

    /**
     * 
     * Why base62?
     * 26 lowercase letter + 26 uppercase letters + 10 digits = 62
     */
    private static String toBase62(long hashedValue) {
        StringBuilder sb = new StringBuilder();
        while (hashedValue >= 62) {
            int rem = (int) (hashedValue % BASE);
            hashedValue /= BASE;
            sb.append(lookup[rem]);
        }
        sb.append(hashedValue);
        return sb.toString();
    }
}