/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.j.as;

public class f
extends as {
    public String a = "";

    @Override
    public strictfp void a(int n2) {
        this.a = this.a + "|" + n2;
        super.a(n2);
    }

    @Override
    public strictfp void a(float f2) {
        this.a = this.a + "|" + f2;
        super.a(f2);
    }

    @Override
    public strictfp void a(short s) {
        this.a = this.a + "|" + s;
        super.a(s);
    }

    @Override
    public strictfp void a(boolean bl) {
        this.a = this.a + "|" + bl;
        super.a(bl);
    }

    @Override
    public strictfp void a(String string, boolean bl) {
        this.a = this.a + "<" + string + ">";
        super.a(string, bl);
    }

    @Override
    public strictfp void a(String string) {
        this.a = this.a + "</" + string + ">";
        super.a(string);
    }

    @Override
    public strictfp void a(am am2) {
        this.a = this.a + "|u:" + am2;
        super.a(am2);
    }
}
