/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.i.b;

public strictfp class ab {
    String a;
    String b;
    int c;
    int d;
    String e;
    l f;

    public String a() {
        b b2;
        String string = "from internal units";
        if (this.a != null) {
            string = "from mod:'" + this.a + "'";
        }
        if ((b2 = com.corrodinggames.rts.gameFramework.l.B().bZ.f(this.a)) != null) {
            string = !b2.m() ? string + " (You seem to have this mod but it is not enabled)" : string + " (You seem to have this mod but it might be a different version)";
        }
        String string2 = "";
        if (this.f != null && this.e != null) {
            if (this.f.I == null) {
                string2 = " (Extra debug not enabled)";
            } else {
                String[] stringArray = this.e.split("\n");
                String[] stringArray2 = this.f.I.split("\n");
                int n2 = com.corrodinggames.rts.gameFramework.f.c(stringArray.length, stringArray2.length);
                if (stringArray.length != stringArray2.length) {
                    string2 = string2 + "Line length difference: " + stringArray.length + " vs " + stringArray2.length;
                }
                for (int i2 = 0; i2 < n2; ++i2) {
                    if (stringArray[i2].equals(stringArray2[i2])) continue;
                    string2 = string2 + "Difference on line " + i2 + ": '" + stringArray[i2] + "' vs '" + stringArray2[i2] + "'";
                    break;
                }
            }
        }
        if (this.d == -1) {
            return "The server requires the unit:" + this.b + " that was not found " + string + string2;
        }
        return "Found unit:" + this.b + " but it does not match the server's copy " + string + string2 + " (checksum c:" + this.d + " s:" + this.c + ")";
    }
}
