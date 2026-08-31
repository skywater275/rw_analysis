/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

public class bc {
    public String localeCode;
    public String translatedText;

    public bc() {
    }

    public bc(String string, String string2) {
        this.localeCode = string;
        this.translatedText = string2;
    }

    public void a(String string, String string2) {
        if (this.translatedText != null) {
            this.translatedText = this.translatedText.replaceAll(string, string2);
        }
    }
}
