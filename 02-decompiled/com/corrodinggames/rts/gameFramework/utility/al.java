/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Locale;

public class al {
    public static ArrayList a(String string, String string2, String string3, boolean bl) {
        int n = 0;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> arrayList = new ArrayList<String>();
        char c = string2.charAt(0);
        char c2 = string3.charAt(0);
        int n2 = string2.length();
        int n3 = string3.length();
        int n4 = string.length();
        for (int i = 0; i < n4; ++i) {
            char c3 = string.charAt(i);
            if (c3 == '(') {
                ++n;
            } else if (c3 == ')') {
                --n;
            }
            if (n == 0) {
                if (!(c != c3 || n2 != 1 && string.indexOf(string2, i) != i || bl && (al.b(string, i - 1) || al.b(string, i + string2.length())))) {
                    arrayList.add(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                    i += string2.length() - 1;
                    continue;
                }
                if (!(c2 != c3 || n3 != 1 && string.indexOf(string3, i) != i || bl && (al.b(string, i - 1) || al.b(string, i + string3.length())))) {
                    arrayList.add(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                    i += string3.length() - 1;
                    continue;
                }
            }
            stringBuffer.append(c3);
        }
        arrayList.add(stringBuffer.toString());
        return arrayList;
    }

    public static ArrayList a(String string, String string2, boolean bl, boolean bl2) {
        int n = 0;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> arrayList = new ArrayList<String>();
        char c = string2.charAt(0);
        int n2 = string2.length();
        int n3 = string.length();
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = false;
        if (string2.equals("-")) {
            bl6 = true;
        }
        int n4 = 0;
        int n5 = 0;
        String string3 = string;
        if (bl2) {
            string3 = string.toLowerCase(Locale.ROOT);
        }
        for (int i = 0; i < n3; ++i) {
            boolean bl7;
            char c2 = string3.charAt(i);
            char c3 = string.charAt(i);
            if (n5 != 32) {
                n4 = n5;
            }
            n5 = c2;
            boolean bl8 = bl3;
            bl3 = false;
            if (!bl8) {
                if (c2 == '\\') {
                    bl3 = true;
                }
                if (!bl5 && c2 == '\'') {
                    boolean bl9 = bl4 = !bl4;
                }
                if (!bl4 && c2 == '\"') {
                    bl5 = !bl5;
                }
            }
            boolean bl10 = bl7 = bl4 || bl5;
            if (!bl7) {
                if (c2 == '(') {
                    ++n;
                } else if (c2 == ')') {
                    --n;
                }
                if (!(n != 0 || c != c2 || n2 != 1 && string3.indexOf(string2, i) != i || bl && (al.b(string, i - 1) || al.b(string, i + string2.length())) || bl6 && (n4 == 42 || n4 == 47 || n4 == 43))) {
                    arrayList.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                    i += string2.length() - 1;
                    continue;
                }
            }
            stringBuffer.append(c3);
        }
        arrayList.add(stringBuffer.toString());
        return arrayList;
    }

    public static ArrayList a(String string, String string2, boolean bl) {
        int n2 = 0;
        char[] cArray = new char[5];
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> arrayList = new ArrayList<String>();
        char c2 = string2.charAt(0);
        int n3 = string2.length();
        int n4 = string.length();
        for (int i = 0; i < n4; ++i) {
            int n5;
            char c3 = string.charAt(i);
            int n6 = 0;
            char c4 = '\u0000';
            if (c3 == '(') {
                n6 = 40;
            } else if (c3 == ')') {
                c4 = '(';
            } else if (c3 == '[') {
                n6 = 91;
            } else if (c3 == ']') {
                c4 = '[';
            }
            if (n6 != 0) {
                if (++n2 >= cArray.length) {
                    n5 = cArray.length;
                    int n7 = n5 + 5;
                    char[] cArray2 = new char[n7];
                    System.arraycopy(cArray, 0, cArray2, 0, n5);
                    cArray = cArray2;
                }
                cArray[n2] = n6;
            } else if (c4 != '\u0000') {
                if (cArray[n2] == c4) {
                    --n2;
                } else {
                    l.e("Bad bracket order: '" + string + "' at index:" + i + " got " + c4 + " type expected: " + cArray[n2]);
                }
            }
            if (n2 == 0) {
                n5 = 0;
                if (c2 == c3 && (n3 == 1 || string.indexOf(string2, i) == i)) {
                    n5 = 1;
                }
                if (!(n5 == 0 || bl && (al.b(string, i - 1) || al.b(string, i + string2.length())))) {
                    arrayList.add(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                    i += string2.length() - 1;
                    continue;
                }
            }
            stringBuffer.append(c3);
        }
        arrayList.add(stringBuffer.toString());
        return arrayList;
    }

    public static String[] b(String string, String string2, boolean bl) {
        if (!string.contains(string2)) {
            return new String[]{string};
        }
        return al.a(string, string2, bl).toArray(new String[0]);
    }

    public static int a(String string, int n2) {
        char c2 = string.charAt(n2);
        if (c2 != '(') {
            l.b("getBracketEnd: Did not start on a bracket");
            return -1;
        }
        if (n2 + 1 >= string.length()) {
            return -1;
        }
        int n3 = 1;
        for (int i = n2 + 1; i < string.length(); ++i) {
            char c3 = string.charAt(i);
            if (c3 == '(') {
                ++n3;
            } else if (c3 == ')') {
                --n3;
            }
            if (n3 != 0) continue;
            return i;
        }
        return -1;
    }

    public static int a(String string) {
        int n2 = 0;
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        for (char c2 : string.toCharArray()) {
            boolean bl4 = bl;
            bl = false;
            if (!bl4) {
                if (c2 == '\\') {
                    bl = true;
                }
                if (!bl3 && c2 == '\'') {
                    boolean bl5 = bl2 = !bl2;
                }
                if (!bl2 && c2 == '\"') {
                    boolean bl6 = bl3 = !bl3;
                }
            }
            if (bl2 || bl3) continue;
            if (c2 == '(') {
                ++n2;
                continue;
            }
            if (c2 != ')') continue;
            --n2;
        }
        return n2;
    }

    public static int b(String string) {
        int n2 = 0;
        for (int i = 0; i < string.length(); ++i) {
            char c2 = string.charAt(i);
            if (c2 == '(') {
                ++n2;
                continue;
            }
            if (c2 != ')') continue;
            --n2;
        }
        return n2;
    }

    public static String[] a(String string, char c2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c3 : string.toCharArray()) {
            boolean bl4 = bl;
            bl = false;
            if (!bl4) {
                if (c3 == '\\') {
                    bl = true;
                }
                if (!bl3 && c3 == '\'') {
                    boolean bl5 = bl2 = !bl2;
                }
                if (!bl2 && c3 == '\"') {
                    boolean bl6 = bl3 = !bl3;
                }
            }
            if (!(c3 != c2 || bl2 || bl3 || bl2)) {
                arrayList.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                continue;
            }
            stringBuilder.append(c3);
        }
        if (stringBuilder.length() != 0) {
            arrayList.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return arrayList.toArray(new String[0]);
    }

    public static String[] b(String string, char c2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        boolean bl = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c3 : string.toCharArray()) {
            boolean bl2 = bl;
            bl = false;
            if (!bl2) {
                if (c3 == '\\') {
                    bl = true;
                    continue;
                }
                if (c3 == c2) {
                    arrayList.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                    continue;
                }
            }
            stringBuilder.append(c3);
        }
        if (stringBuilder.length() != 0) {
            arrayList.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        if (arrayList.size() == 0) {
            arrayList.add("");
        }
        return arrayList.toArray(new String[0]);
    }

    public static String a(String[] stringArray) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean bl = true;
        for (String string : stringArray) {
            if (bl) {
                bl = false;
            } else {
                stringBuffer.append(",");
            }
            if (string.contains("\\")) {
                string = string.replace("\\", "\\\\");
            }
            if (string.contains(",")) {
                string = string.replace(",", "\\,");
            }
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static int a(String string, String string2) {
        return al.a(string, string2, 0);
    }

    public static int a(String string, String string2, int n2) {
        int n3 = 0;
        char c2 = string2.charAt(0);
        int n4 = string2.length();
        for (int i = n2; i < string.length(); ++i) {
            char c3 = string.charAt(i);
            if (c3 == '(') {
                ++n3;
            } else if (c3 == ')') {
                --n3;
            }
            if (n3 != 0 || c2 != c3 || n4 != 1 && string.indexOf(string2, i) != i) continue;
            return i;
        }
        return -1;
    }

    public static int b(String string, String string2, int n2) {
        int n3 = 0;
        char[] cArray = new char[5];
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        char c2 = string2.charAt(0);
        int n4 = string2.length();
        for (int i = n2; i < string.length(); ++i) {
            boolean bl4;
            char c3 = string.charAt(i);
            boolean bl5 = bl;
            bl = false;
            if (!bl5) {
                if (c3 == '\\') {
                    bl = true;
                }
                if (!bl3 && c3 == '\'') {
                    boolean bl6 = bl2 = !bl2;
                }
                if (!bl2 && c3 == '\"') {
                    bl3 = !bl3;
                }
            }
            boolean bl7 = bl4 = bl2 || bl3;
            if (bl4) continue;
            int n5 = n3;
            int n6 = 0;
            char c4 = '\u0000';
            if (c3 == '(') {
                n6 = 40;
            } else if (c3 == ')') {
                c4 = '(';
            } else if (c3 == '[') {
                n6 = 91;
            } else if (c3 == ']') {
                c4 = '[';
            }
            if (n6 != 0) {
                if (++n3 >= cArray.length) {
                    int n7 = cArray.length;
                    int n8 = n7 + 5;
                    char[] cArray2 = new char[n8];
                    System.arraycopy(cArray, 0, cArray2, 0, n7);
                    cArray = cArray2;
                }
                cArray[n3] = n6;
            } else if (c4 != '\u0000') {
                if (cArray[n3] == c4) {
                    --n3;
                } else {
                    l.e("Bad bracket order: '" + string + "' at index:" + i + " got " + c4 + " type expected: " + cArray[n3]);
                }
            }
            if (n5 != 0 && n3 != 0 || c2 != c3 || n4 != 1 && string.indexOf(string2, i) != i) continue;
            return i;
        }
        return -1;
    }

    public static boolean b(String string, int n2) {
        if (n2 < 0 || n2 >= string.length()) {
            return false;
        }
        char c2 = string.charAt(n2);
        return Character.isLetter(c2) || Character.isDigit(c2);
    }

    public static int a(String string, String string2, String string3) {
        int n2 = string.indexOf(string2);
        int n3 = string.indexOf(string3);
        if (n2 == -1) {
            return n3;
        }
        if (n3 == -1) {
            return n2;
        }
        if (n2 < n3) {
            return n2;
        }
        return n3;
    }

    public static int a(String string, int n2, String[] stringArray) {
        int n3 = -1;
        for (String string2 : stringArray) {
            int n4 = al.a(string, string2, n2);
            if (n4 == -1 || n3 <= n4 && n3 != -1) continue;
            n3 = n4;
        }
        return n3;
    }

    public static String c(String string) {
        boolean bl = false;
        for (int i = 0; i < string.length(); ++i) {
            char c2 = string.charAt(i);
            if (c2 == '-') {
                bl = !bl;
                continue;
            }
            if (c2 == '+' || c2 == ' ') continue;
            if (bl) {
                return "-" + string.substring(i);
            }
            if (i == 0) {
                return string;
            }
            return string.substring(i);
        }
        return string;
    }

    public static String[] b(String string, String string2) {
        int n2 = string.indexOf(string2);
        if (n2 == -1) {
            return null;
        }
        String string3 = string.substring(0, n2);
        String string4 = string.substring(n2 + string2.length());
        return new String[]{string3, string4};
    }

    public static String[] c(String string, String string2) {
        int n2 = al.b(string, string2, 0);
        if (n2 == -1) {
            return null;
        }
        String string3 = string.substring(0, n2);
        String string4 = string.substring(n2 + string2.length());
        return new String[]{string3, string4};
    }

    public static final String d(String string) {
        if (string == null) {
            return null;
        }
        if (string.length() < 1) {
            return string.toUpperCase();
        }
        return string.substring(0, 1).toUpperCase(Locale.ROOT) + string.substring(1).toLowerCase(Locale.ROOT);
    }

    public static String[] e(String string) {
        return al.b(string, ',');
    }
}
