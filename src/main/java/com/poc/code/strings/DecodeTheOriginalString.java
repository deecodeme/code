package com.poc.code.strings;

/*
You are given an encoded string  and number of rows, Convert it to original string

 Input: mnesi___ya__k____mime  N = 3

 Output : my name is mike

 Explanation : Read the matrix in a diagonal way starting from [0][0] index until the end of row and start from the top
 again to decode it. _ are treated as space.

 m n e s i _  _

  _ y a _ _ k _

  _ _ _ m i m e
 */
public class DecodeTheOriginalString {

    /*
    Intuition:
    The core of the problem is find a way to navigate
     */
    public String decodeString(String encodedString, int R) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] matrix = encodedString.toCharArray();
        int l = encodedString.length();
        int C = l / R;
        int r = 0;
        int c = 0;
        while (true) {
            int pos = r * C + c;
            if (matrix[pos] == '_') {
                stringBuilder.append(" ");
            } else {
                stringBuilder.append(matrix[pos]);
            }
            if (r == R - 1 && c == C - 1) {
                break;
            } else if (r == R - 1 || c == C - 1) {
                c = c - r + 1;
                r = 0;
            } else {
                r++;
                c++;
            }
        }
        return stringBuilder.toString();
    }

    public String decodeStringSimplified(String encodedString, int R) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] matrix = encodedString.toCharArray();
        int l = encodedString.length();
        int C = l / R;
        for (int c = 0; c < C - R + 1; c++) {
            for (int r = 0; r < R; r++) {
                int i = r;
                int j = c + r;
                int pos = i * C + j; // get linear position of an element in matrix
                if (matrix[pos] == '_') {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(matrix[pos]);
                }
            }
        }
        return stringBuilder.toString();
    }
}
