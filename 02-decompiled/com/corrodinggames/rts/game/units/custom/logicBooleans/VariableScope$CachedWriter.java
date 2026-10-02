/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterFactory;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;
import java.util.Locale;

public class VariableScope$CachedWriter {
    m writers = new m();

    public void writeToUnit(y y2) {
        for (VariableScope$CachedWriter$WriterElement variableScope$CachedWriter$WriterElement : this.writers) {
            variableScope$CachedWriter$WriterElement.writeToUnit(y2);
        }
    }

    public static VariableScope$CachedWriter create(String string, VariableScope$CachedWriter$WriterFactory writerFactory) {
        VariableScope$CachedWriter variableScope$CachedWriter = new VariableScope$CachedWriter();
        variableScope$CachedWriter.addWriterElements(string, writerFactory);
        return variableScope$CachedWriter;
    }

    public void addWriterElements(String string, VariableScope$CachedWriter$WriterFactory variableScope$CachedWriter$WriterFactory) {
        ArrayList arrayList = al.a(string, ",", false, false);
        for (String string2 : arrayList) {
            String string3;
            String string4;
            String[] stringArray = al.c(string2, "=");
            if (stringArray == null) {
                string4 = string2;
                string3 = null;
            } else {
                string4 = stringArray[0];
                string3 = stringArray[1];
            }
            String string5 = null;
            if (f.c(string4, "[")) {
                int n = string4.indexOf(91);
                int n2 = al.b(string4, "]", n);
                if (n == -1 || n2 == -1) {
                    throw new bo("Unexpected array[] format for: " + string4);
                }
                string5 = string4.substring(n + 1, n2);
                if (string5.trim().equals("")) {
                    throw new bo("Array [] index in: " + string4 + " is empty");
                }
                String string6 = string4.substring(n2 + 1, string4.length());
                for (int i = 0; i < string6.length(); ++i) {
                    char c = string6.charAt(i);
                    if (c != '+' && c != '=' && c != '-' && c != '*' && c != '/' && c != ' ') {
                        throw new bo("Unexpected text:'" + string6 + "' after [] index of: " + string4);
                    }
                    if (c == '=') break;
                }
                string4 = string4.substring(0, n) + string6;
            }
            string4 = string4.toLowerCase(Locale.ROOT).trim();
            String string7 = "=";
            if (string4.endsWith("+") || string4.endsWith("-") || string4.endsWith("*") || string4.endsWith("/")) {
                string7 = string4.substring(string4.length() - 1, string4.length()) + "=";
                string4 = string4.substring(0, string4.length() - 1).trim();
            }
            if (string4.contains(" ")) {
                throw new bo("Key cannot contain spaces for: " + string2);
            }
            if (string4.contains("[")) {
                throw new bo("Key cannot contain [ for: " + string2);
            }
            if (string4.contains("]")) {
                throw new bo("Key cannot contain ] for: " + string2);
            }
            if (string4.contains("(")) {
                throw new bo("Key cannot contain ( for: " + string2);
            }
            if (string4.contains(")")) {
                throw new bo("Key cannot contain ) for: " + string2);
            }
            if (string4.contains(".")) {
                throw new bo("Key cannot contain . for: " + string2);
            }
            VariableScope$CachedWriter$WriterElement variableScope$CachedWriter$WriterElement = variableScope$CachedWriter$WriterFactory.createWriterElement(string4, string7, string3, string5);
            this.writers.add(variableScope$CachedWriter$WriterElement);
        }
    }
}
