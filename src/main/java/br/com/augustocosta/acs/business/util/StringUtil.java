package br.com.augustocosta.acs.business.util;

public class StringUtil {
    public static String cleanString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.replaceAll("[\"'#%*;:?/|\\\\!]", "");
    }
}