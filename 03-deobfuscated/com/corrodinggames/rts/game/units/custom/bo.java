/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

public class bo
extends Exception {
    public String amountValue;
    public String sectionName;
    public String keyName;

    public bo(String string, String string2) {
        super(string);
        this.amountValue = string2;
    }

    public bo(String string) {
        super(string);
    }

    public bo(String string, String string2, String string3) {
        super(string);
        this.sectionName = string2;
        this.keyName = string3;
    }

    public bo(String string, Exception exception) {
        super(string, exception);
    }
}
