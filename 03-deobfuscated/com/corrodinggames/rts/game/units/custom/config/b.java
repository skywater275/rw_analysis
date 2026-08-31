/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.config;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.config.a;
import com.corrodinggames.rts.game.units.custom.config.ActionFilter$1;
import com.corrodinggames.rts.game.units.custom.config.d;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.regex.Matcher;

public class b {
    public d fieldDefinition;
    public d fieldValue;

    public b() {
        this.fieldDefinition = new d();
        this.fieldValue = new d();
    }

    public b(d d2, d d3) {
        this.fieldDefinition = d2;
        this.fieldValue = d3;
    }

    public void a() {
        this.fieldDefinition.a();
        this.fieldValue.a();
    }

    public b b() {
        b b2 = new b(this.fieldDefinition, new d());
        return b2;
    }

    public static double a(String string) {
        return new ActionFilter$1(string).parseDouble();
    }

    public boolean b(String string) {
        if (string.contains("*")) {
            return true;
        }
        if (string.contains("/")) {
            return true;
        }
        if (string.contains("+")) {
            return true;
        }
        if (string.contains("-")) {
            return true;
        }
        if (string.contains("(")) {
            return true;
        }
        if (string.contains(")")) {
            return true;
        }
        if (string.contains("^")) {
            return true;
        }
        return string.contains("%");
    }

    /* 02b f/b.java L46: 抛 checked bo (R8 移除 throws) */
    public String a(ModUnitRegistry l2, ab ab2, String string, String string2) throws bo {
        string2 = string2.trim();
        boolean bl = this.b(string2);
        int n = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = com.corrodinggames.rts.game.units.custom.config.a.b.matcher(string2);
        while (matcher.find()) {
            if (++n > 100) {
                throw new bo("Too many loops while parsing");
            }
            String string3 = matcher.group(0);
            if (GameUtils.r(string3) || string3.equals("int") || string3.equals("cos") || string3.equals("sin") || string3.equals("sqrt")) continue;
            String string4 = this.b(l2, ab2, string, string3);  // 02b f/b.java L79: this.b(l,ab,String,String)
            if (bl && !GameUtils.r(string4)) {
                throw new bo("Cannot do maths on '" + string4 + "' from " + string3 + " (not a number)");
            }
            matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(string4));
        }
        matcher.appendTail(stringBuffer);
        string2 = stringBuffer.toString();
        if (bl) {
            string2 = GameUtils.b(com.corrodinggames.rts.game.units.custom.config.b.a(string2));
        }
        return string2;
    }

    /* 02b f/b.java L79: 抛 checked bo (R8 移除 throws) */
    public String b(ModUnitRegistry l2, ab ab2, String string, String string2) throws bo {
        if (string2.contains(".")) {
            String string3;
            String[] stringArray = GameUtils.c(string2, '.');
            if (stringArray.length != 2) {
                throw new bo("Unexpected key format: " + string2);
            }
            String string4 = stringArray[0];
            String string5 = stringArray[1];
            if (string4.equals("section")) {
                string4 = string;
            }
            if ((string3 = ab2.b(string4, string5, (String)null)) == null) {
                if (string4.equalsIgnoreCase("self")) {
                    throw new bo("Static $ block: Could not find: [" + string4 + "]" + string5 + " in this conf file. Hint: You might have wanted % instead of $ for a dynamic string");
                }
                throw new bo("Static $ block: Could not find: [" + string4 + "]" + string5 + " in this conf file");
            }
            if (string3.contains("${")) {
                throw new bo("Reference [" + string4 + "]" + string5 + " is dynamic, chaining is not yet supported");
            }
            return string3;
        }
        String string6 = this.fieldValue.a(string2);
        if (string6 != null) {
            return string6;
        }
        string6 = this.fieldDefinition.a(string2);
        if (string6 != null) {
            return string6;
        }
        throw new bo("Could not find variable with name: " + string2);
    }
}
