package org.ayush.problemsolving;

import java.util.HashMap;
import java.util.Map;

/*
* Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.



Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true


Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
*
* Lets check examples -

EGG -> ADD [ E->A, G->D] --- true

FOO -> BAR [F->B, O->A|B?] --- here O has different choice either A or B ; false

BADC -> BABA [B->B, A->A, D->B, C-> A] --> here B has mapping to B & D so false; To think more clearly think from mapping t to s [B->B, A->A, B->D!, A->C!] now if you see from t to s then both B & A has multiple map --- false

PEAR -> TILE [P->T, E->I, A->L, R->E] && t to s [T->P, I->E, L->A,E->R]; so from both side no single character has to be mapped multiple value so its isomorphic -- true;

BBBAAABA -> AAABBBBA [B->A, A->B, 7th B->B!, 8th A->A!] --- first 6 char has no problem to map B->A, A->B but when you come to 7th B the mapping become B instead of A so its not isomorphic. --- false

ABC -> CAC [A->C, B->A, C-> C] && from t to s [C->A, A->B, last C->C! instead of A] so not isomorphic -- false
* */
public class IsomorphicStrings {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));
        System.out.println(isIsomorphic("BADC", "BABA"));
        System.out.println(isIsomorphic("BBBAAABA", "AAABBBBA"));
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> mp = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if (!mp.containsKey(ch1)) {
                if (mp.containsValue(ch2)) {
                    return false;
                } else {
                    mp.put(ch1, ch2);
                }
            } else {
                if (ch2 != mp.get(ch1)) return false;
            }
        }
        return true;
    }
}
