/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class LocalizedString {
    public static final LocalizedString a = isEmpty("");
    public bc[] b;
    public String c;
    public int d = -1;
    public String e;

    public static LocalizedString isEmpty(String string) {
        LocalizedString bb2 = new LocalizedString();
        ArrayList<bc> arrayList = new ArrayList<bc>();
        bc bc2 = new bc();
        bc2.localeCode = null;
        bc2.translatedText = string;
        arrayList.add(bc2);
        bb2.b = arrayList.toArray(new bc[0]);
        bb2.getLocalizedText();
        return bb2;
    }

    public LocalizedString() {
    }

    public LocalizedString(bc[] bcArray) {
        this.b = bcArray;
    }

    public boolean isEmpty() {
        if (this.b != null) {
            for (bc bc2 : this.b) {
                if (bc2.translatedText == null || "".equals(bc2.translatedText)) continue;
                return false;
            }
        }
        return true;
    }

    public void isEmpty(String string, String string2) {
        if (this.b != null) {
            for (bc bc2 : this.b) {
                bc2.a(string, string2);
            }
        } else {
            GlobalState.b("LocaleString: replaceAll with null strings");
        }
        this.d = -1;
    }

    public String getLocalizedText() {  // 02b bb.b(): 解析本地化文本
        if (this.d == -1) {
            return this.c;
        }
        if (this.e != null) {
            this.d = -1;
            this.c = com.corrodinggames.rts.gameFramework.steam.Localization.a(this.e, new Object[0]);
            return this.c;
        }
        String string = com.corrodinggames.rts.gameFramework.steam.Localization.c();
        if (this.b != null) {
            for (bc bc2 : this.b) {
                if (!string.equals(bc2.localeCode)) continue;
                this.d = -1;
                this.c = bc2.translatedText;
                return this.c;
            }
        }
        return this.c;
    }
}
