/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.e;

import com.corrodinggames.rts.gameFramework.e.c;

public class d
extends c {
    String g;
    String h;
    String i;

    public d(String string, String string2) {
        this.g = string;
        this.h = string2;
        if (!this.g.endsWith("/") && !this.g.endsWith("\\")) {
            this.g = this.g + "/";
        }
    }

    @Override
    public String b() {
        return this.g;
    }

    @Override
    public String d() {
        return this.h;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public String e(String string) {
        String string2 = super.e(string);
        if (this.i != null && string2 != null && string2.startsWith(this.g)) {
            if ((string2 = string2.substring(this.g.length())).startsWith("/") || string2.startsWith("\\")) {
                string2 = string2.substring(1);
            }
            string2 = this.i + string2;
        }
        return string2;
    }
}
