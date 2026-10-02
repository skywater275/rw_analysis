/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$AndroidCodes;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$GdxCodes;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$MissingKey;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$SlickCodes;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Locale;

public class SlickToAndroidKeycodes {
    static HashMap a;
    static HashMap b;
    static HashMap c;
    static HashMap d;
    static HashMap e;
    static HashMap f;
    static HashMap g;

    static HashMap a(Class clazz) {
        Field[] fieldArray;
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        for (Field field : fieldArray = clazz.getFields()) {
            int n;
            String string = field.getName();
            string = string.replace("KEYCODE_", "");
            string = string.replace("KEY_", "");
            string = string.replace("NUMPAD_", "NUMPAD");
            string = string.replace("NUM_", "NUMPAD");
            try {
                n = field.getInt(null);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw new RuntimeException(illegalArgumentException);
            }
            catch (IllegalAccessException illegalAccessException) {
                throw new RuntimeException(illegalAccessException);
            }
            hashMap.put(string, n);
        }
        return hashMap;
    }

    public static void a() {
        a = SlickToAndroidKeycodes.a("slickToAndroidCodes", d, e);
        g = new HashMap();
        ArrayList<String> arrayList = new ArrayList<String>();
        Iterator iterator = d.keySet().iterator();
        while (iterator.hasNext()) {
            String string = (String)iterator.next();
            int n = (Integer)d.get(string);
            Integer n2 = (Integer)e.get(string);
            if (n2 == null) {
                arrayList.add(string);
            }
            if (a.get(n) == null) {
                // empty if block
            }
            String string2 = string;
            string2 = string2.toLowerCase(Locale.ENGLISH);
            if (n2 == null) continue;
            g.put(n2, string2);
        }
    }

    public static void b() {
        b = SlickToAndroidKeycodes.a("gdxToAndroidCodes", f, e);
    }

    public static void c() {
        c = SlickToAndroidKeycodes.a("gdxToSlickCodes", f, d);
    }

    public static HashMap a(String string, HashMap hashMap, HashMap hashMap2) {
        HashMap<Integer, Integer> hashMap3 = new HashMap<Integer, Integer>();
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (Object object : hashMap.keySet()) {
            int n2 = (Integer)hashMap.get(object);
            Integer n3 = (Integer)hashMap2.get(object);
            if (n3 == null) {
                arrayList.add(object);
            }
            if (hashMap3.get(n2) == null) {
                hashMap3.put(n2, n3);
            }
            Object object2 = object;
            object2 = ((String)object2).toLowerCase(Locale.ENGLISH);
            if (n3 == null) continue;
        }
        if (arrayList.size() != 0) {
            Object object = "";
            for (String string2 : (java.util.Collection<String>) (java.util.Collection) arrayList) {
                int n4 = (Integer)hashMap.get(string2);
                if (hashMap3.get(n4) != null) continue;
                object = (String)object + string2 + ", ";
            }
            GlobalState.e(string + ": Could not find keycode for: " + (String)object);
        }
        return hashMap3;
    }

    public static int a(String string) throws SlickToAndroidKeycodes$MissingKey {
        Integer n2 = (Integer)e.get(string = string.toUpperCase());
        if (n2 == null) {
            throw new SlickToAndroidKeycodes$MissingKey("Could not find key:" + string);
        }
        return n2;
    }

    public static String a(int n2) {
        String string = (String)g.get(n2);
        if (string == null) {
            return "unknown";
        }
        return string;
    }

    public static int b(int n2) {
        Integer n3 = (Integer)a.get(n2);
        if (n3 == null) {
            return 0;
        }
        return n3;
    }

    public static Integer c(int n2) {
        Object var1_1 = null;
        if (n2 == 14) {
            return 69;
        }
        if (n2 == 211) {
            return 99;
        }
        if (n2 == 28) {
            return 72;
        }
        if (n2 == 203) {
            return 90;
        }
        if (n2 == 205) {
            return 92;
        }
        if (n2 == 200) {
            return 91;
        }
        if (n2 == 208) {
            return 93;
        }
        if (n2 == 15) {
            return 70;
        }
        if (n2 == 42) {
            return 138;
        }
        if (n2 == 54) {
            return 139;
        }
        if (n2 == 29) {
            return 140;
        }
        if (n2 == 157) {
            return 141;
        }
        return null;
    }

    static {
        d = SlickToAndroidKeycodes.a(SlickToAndroidKeycodes$SlickCodes.class);
        e = SlickToAndroidKeycodes.a(SlickToAndroidKeycodes$AndroidCodes.class);
        f = SlickToAndroidKeycodes.a(SlickToAndroidKeycodes$GdxCodes.class);
        SlickToAndroidKeycodes.a();
        SlickToAndroidKeycodes.b();
        SlickToAndroidKeycodes.c();
    }
}
