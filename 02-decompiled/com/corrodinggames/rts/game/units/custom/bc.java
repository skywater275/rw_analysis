/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

public class bc {
    public String a;
    public String b;

    public bc() {
    }

    public bc(String string, String string2) {
        this.a = string;
        this.b = string2;
    }

    public void a(String string, String string2) {
        if (this.b != null) {
            this.b = this.b.replaceAll(string, string2);
        }
    }
}
