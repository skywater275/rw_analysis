/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

public class bo
extends Exception {
    public String b;
    public String c;
    public String d;

    public bo(String string, String string2) {
        super(string);
        this.b = string2;
    }

    public bo(String string) {
        super(string);
    }

    public bo(String string, String string2, String string3) {
        super(string);
        this.c = string2;
        this.d = string3;
    }

    public bo(String string, Exception exception) {
        super(string, exception);
    }
}
