package org.ayush.problemsolving;

public class PalindromeRecursion {
    public static void main(String[] args) {
        System.out.println(isPalindrome("MADAM", 0));
        System.out.println(isPalindrome("MADAMA", 0));
    }

    private static boolean isPalindrome(String str, int i) {
        if(i>=str.length()/2) return true;
        if(str.charAt(i) != str.charAt(str.length()-i-1)) return false;
        return isPalindrome(str, i+1);
    }
}