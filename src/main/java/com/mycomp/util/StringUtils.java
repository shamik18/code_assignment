package com.mycomp.util;

import org.apache.commons.lang3.CharUtils;

public class StringUtils {
    public static final String SPACE = " ";
    public static final String EMPTY = "";

    public static final char NULL_CHAR = '\0';

    private StringUtils(){
    }

    public static int length(String str){
        return str == null ? 0 : str.length();
    }

    public static boolean blankOrEmpty(String str){
        final int strLen = length(str);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean notBlankOrEmpty(String str){
        return ! blankOrEmpty(str);
    }

    public static String getStringFromStringArray(String[] arrStr, String after){
        return getStringFromStringArray(arrStr,after,null);
    }

    public static String getStringFromStringArray(String[] arrStr, String after, String before){
        return getStringFromStringArray(0,arrStr,after,before);
    }
    public static String getStringFromStringArray(int startIndex, String[] arrStr, String after, String before){
        StringBuilder stringBuilder = new StringBuilder();
        boolean flagStart = false;
        for(int index=0; index < arrStr.length;index++){
            if(flagStart && arrStr[index].equals(before)){
                break;
            }
            if(flagStart){
                stringBuilder.append(arrStr[index]).append(StringUtils.SPACE);
            }
            if(arrStr[index].equals(after)){
                flagStart = true;
            }
        }
        return stringBuilder.toString().trim();
    }
    public static String removeQuotesString(String quotesString){
        String targetStr = removeSplChar(quotesString,'\'');
        if(targetStr.length()!=quotesString.length())
            return targetStr;
        return removeSplChar(targetStr,'\"');
    }
    private static String removeSplChar(final String str, final char wrapChar) {
        if (str.isEmpty() || wrapChar == NULL_CHAR || str.length() == 1) {
            return str;
        }

        if (str.charAt(0) == wrapChar && str.charAt(str.length() - 1) == wrapChar) {
            final int startIndex = 0;
            final int endIndex = str.length() - 1;

            return str.substring(startIndex + 1, endIndex);
        }

        return str;
    }

    public static String getStringFromStringArray(String[] arrStr, int startIndex, int endIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flagStart = false;
        for(int index=startIndex+1; index < endIndex;index++){
            stringBuilder.append(arrStr[index]).append(StringUtils.SPACE);
        }
        return stringBuilder.toString().trim();
    }

    public static String getStringFromStringArray(String[] arrStr, int startIndex) {
        return getStringFromStringArray(arrStr,startIndex,arrStr.length);
    }

    public static boolean beginWith(String str, char ch){
        if(str == null)
            return false;
        if(str != null && str.isEmpty())
            return false;
        if(str.length() < 2)
            return false;
        if(str.charAt(0)==ch)
            return true;
        return false;
    }
}
