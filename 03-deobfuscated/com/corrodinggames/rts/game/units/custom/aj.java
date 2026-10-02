/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.ak;
import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.ar;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import java.util.ArrayList;

public class aj {
    public static final aj a = aj.getString("");
    public ak[] effectName;
    public bc[] effectDuration;
    public String effectInterval;
    public int effectScript = -1;
    public String effectFlags;
    public String effectStackLimit;
    ModUnitRegistry h;

    public static aj getString(String string) {
        aj aj2 = new aj();
        ArrayList<bc> arrayList = new ArrayList<bc>();
        bc bc2 = new bc();
        bc2.localeCode = null;
        bc2.translatedText = string;
        arrayList.add(bc2);
        aj2.effectDuration = arrayList.toArray(new bc[0]);
        aj2.getString();
        return aj2;
    }

    public static aj getString(LocalizedString bb2) {
        if (bb2 == null) {
            return null;
        }
        aj aj2 = new aj();
        aj2.h = ModUnitRegistry.b;
        aj2.effectDuration = bb2.b;
        aj2.effectFlags = bb2.e;
        if (aj2.effectDuration != null) {
            for (bc bc2 : aj2.effectDuration) {
                if (bc2.translatedText != null && !bc2.translatedText.contains("%{")) continue;
            }
        }
        aj2.c();
        return aj2;
    }

    public aj() {
    }

    public aj(ModUnitRegistry l2, LocalizedString bb2) {
        this.h = l2;
        this.effectDuration = bb2.b;
        this.effectFlags = bb2.e;
        if (this.effectDuration != null) {
            for (bc bc2 : this.effectDuration) {
                if (bc2.translatedText == null || !bc2.translatedText.contains("%{")) continue;
                this.getString(bc2.translatedText, true);
            }
        }
        this.getString();
    }

    public void getString() {
        this.getString(true);
    }

    public void getString(boolean bl2) {
        this.c();
        this.effectName = this.effectInterval != null && this.effectInterval.contains("%{") ? this.getString(this.effectInterval, bl2) : null;
    }

    public ak[] getString(String string, boolean bl2) {
        ArrayList arrayList = new ArrayList();
        int n2 = 0;
        boolean bl3 = false;
        while (true) {
            int n3;
            block7: {
                String string2;
                if (!bl3) {
                    n3 = string.indexOf("%{", n2);
                    if (n3 == -1) {
                        string2 = string.substring(n2, string.length());
                        if (string2.equals("")) break;
                        arrayList.add(new ar(string2));
                        break;
                    }
                    string2 = string.substring(n2, n3);
                    if (!string2.equals("")) {
                        arrayList.add(new ar(string2));
                    }
                    bl3 = true;
                    n2 = n3 + 2;
                    continue;
                }
                n3 = string.indexOf("}", n2);
                if (n3 == -1) {
                    arrayList.add(new ar("< %{ NOT CLOSED >"));
                    break;
                }
                string2 = string.substring(n2, n3);
                try {
                    LogicBoolean logicBoolean = LogicBooleanLoader.parseBooleanBlock(this.h, string2, false);
                    arrayList.add(aq.a(logicBoolean));
                }
                catch (RuntimeException runtimeException) {
                    String string3;
                    this.effectStackLimit = string3 = "Error: " + runtimeException.getMessage() + ", [parsing: '" + string2 + "']";
                    arrayList.add(new ar("Error:< " + string3 + " >"));
                    if (!bl2) break block7;
                    throw runtimeException;
                }
            }
            n2 = n3 + 1;
            bl3 = false;
        }
        return (ak[]) arrayList.toArray(new ak[0]);
    }

    public String getString(UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            return "<No unit>:" + this.effectInterval;
        }
        UnitType y2 = (UnitType) am2;
        StringBuffer stringBuffer = new StringBuffer();
        for (ak ak2 : this.effectName) {
            stringBuffer.append(ak2.a(y2));
        }
        return stringBuffer.toString();
    }

    public String b(UnitInstance am2) {
        if (this.effectScript == com.corrodinggames.rts.gameFramework.steam.Localization.c) {
            if (this.effectName != null) {
                return this.getString(am2);
            }
            return this.effectInterval;
        }
        this.getString(false);
        if (this.effectName != null) {
            return this.getString(am2);
        }
        return this.effectInterval;
    }

    public String b() {
        if (this.effectScript == com.corrodinggames.rts.gameFramework.steam.Localization.c) {
            return this.effectInterval;
        }
        this.getString(false);
        return this.effectInterval;
    }

    public void c() {
        if (this.effectFlags != null) {
            this.effectScript = com.corrodinggames.rts.gameFramework.steam.Localization.c;
            this.effectInterval = com.corrodinggames.rts.gameFramework.steam.Localization.a(this.effectFlags, new Object[0]);
            return;
        }
        String string = com.corrodinggames.rts.gameFramework.steam.Localization.c();
        for (bc bc2 : this.effectDuration) {
            if (!string.equals(bc2.localeCode)) continue;
            this.effectScript = com.corrodinggames.rts.gameFramework.steam.Localization.c;
            this.effectInterval = bc2.translatedText;
            return;
        }
        for (bc bc2 : this.effectDuration) {
            if (bc2.localeCode != null) continue;
            this.effectScript = com.corrodinggames.rts.gameFramework.steam.Localization.c;
            this.effectInterval = bc2.translatedText;
            return;
        }
        this.effectScript = com.corrodinggames.rts.gameFramework.steam.Localization.c;
        this.effectInterval = "<NO DEFAULT TEXT FOUND>";
    }
}
