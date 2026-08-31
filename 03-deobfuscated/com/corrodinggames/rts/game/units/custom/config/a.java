/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.config;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.config.b;
import com.corrodinggames.rts.game.units.custom.config.c;
import com.corrodinggames.rts.game.units.custom.config.e;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a {
    static final Pattern a = Pattern.compile("\\$\\{([^\\}]*)\\}");
    static final Pattern b = Pattern.compile("[A-Za-z_][A-Za-z_.0-9]*");
    static b c = new b();

    /* 02b f/a.java L24: 大量抛 checked bo (R8 移除 throws) */
    public static void a(ModUnitRegistry l2, ab ab2) throws bo {
        Object object;
        Object object2;
        Object object32;
        int n = 0;
        c.a();
        CustomArrayList m2 = ab2.d("@global ");
        for (Object object32_32 : m2) {
            for (Object object4 : ab2.k((String)object32_32, "@global ")) {
                object2 = ((String)object4).substring("@global ".length()).trim();
                try {
                    e.reset((String)object2);  // 02b f/e: a(String)=reset (03 语义名)
                }
                catch (bo bo2) {
                    throw new bo("[" + (String)object32_32 + "]" + (String)object4 + ": " + bo2.getMessage());
                }
                if (ab2.f((String)object2)) {
                    throw new bo("[" + (String)object32_32 + "]" + (String)object4 + ": A section already has that name");
                }
                object = ab2.e((String)object32_32, (String)object4);
                if (((String)object).contains("${")) {
                    throw new bo("[" + (String)object32_32 + "]" + (String)object4 + " has dynamic value: '" + (String)object + "', this is not yet supported");
                }
                com.corrodinggames.rts.game.units.custom.config.a.c.fieldDefinition.a((String)object2, (String)object);  // 02b f/a L55: c.a.a (b 字段 a=fieldDefinition)
            }
        }
        ArrayList arrayList = new ArrayList();
        LinkedHashMap object32_32 = ab2.d();  // 02b f/a L60: LinkedHashMap var25
        for (Object object4 : ((LinkedHashMap)object32_32).keySet()) {
            String string;
            String string2;
            Object object52;
            if (object4 == null || ((String)object4).startsWith("comment_") || ((String)object4).startsWith("template_")) continue;
            object2 = c.b();
            for (Object object52_59 : ab2.k((String)object4, "@define ")) {
                string2 = ((String)object52_59).substring("@define ".length()).trim();
                try {
                    e.reset(string2);  // 02b f/e: a(String)=reset
                }
                catch (bo bo3) {
                    throw new bo("[" + (String)object4 + "]" + (String)object52_59 + ": " + bo3.getMessage());
                }
                if (ab2.f(string2)) {
                    throw new bo("[" + (String)object4 + "]" + (String)object52_59 + ": A section already has that name");
                }
                string = ab2.e((String)object4, (String)object52_59);
                if (string.contains("${")) {
                    throw new bo("[" + (String)object4 + "]" + (String)object52_59 + " has dynamic value: '" + string + "', this is not yet supported");
                }
                ((b)object2).fieldValue.a(string2, string);  // 02b f/a L90: var27.b=fieldValue
            }
            object = (Map)((LinkedHashMap)object32_32).get(object4);
            Iterator object52_59 = ((Map)object).keySet().iterator();  // 02b f/a L93-94: Map var29 + Iterator var30
            while (object52_59.hasNext()) {
                string2 = (String)object52_59.next();
                string = (String)((Map)object).get(string2);
                if (string == null || !string.contains("${")) continue;
                int n2 = 0;
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = a.matcher(string);
                while (matcher.find()) {
                    String string3;
                    if (++n2 > 100) {
                        throw new bo("[" + (String)object4 + "]" + string2 + ": Too many loops while parsing");
                    }
                    String string4 = matcher.group(1);
                    ++n;
                    try {
                        string3 = ((b)object2).a(l2, ab2, (String)object4, string4);
                    }
                    catch (bo bo4) {
                        bo4.printStackTrace();
                        throw new bo("[" + (String)object4 + "]" + string2 + ": " + bo4.getMessage());
                    }
                    if (!string4.equals(string3)) {
                        string4 = string3;
                    }
                    matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(string3));
                }
                matcher.appendTail(stringBuffer);
                string = stringBuffer.toString();
                arrayList.add(new c((String)object4, string2, string));
            }
        }
        Iterator<Object> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object object4;
            object4 = (c)iterator.next();
            ab2.e(((c)object4).a, ((c)object4).b, ((c)object4).c);
        }
        arrayList.clear();
    }
}
