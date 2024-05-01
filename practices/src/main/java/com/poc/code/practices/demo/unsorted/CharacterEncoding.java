/* (C)2024 */
package com.poc.code.practices.demo.unsorted;

import java.nio.charset.StandardCharsets;

public class CharacterEncoding {
    public static byte[] toAscii(char ch) {
        return String.valueOf(ch).getBytes(StandardCharsets.US_ASCII);
    }

    public static byte[] toUTF_8(char ch) {
        return String.valueOf(ch).getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] toUTF_16(char ch) {
        return String.valueOf(ch).getBytes(StandardCharsets.UTF_16);
    }

    public static byte[] toUTF_16LE(char ch) {
        return String.valueOf(ch).getBytes(StandardCharsets.UTF_16LE);
    }
}
