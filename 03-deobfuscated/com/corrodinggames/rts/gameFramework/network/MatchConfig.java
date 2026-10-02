/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.InputProvider;

import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;

public strictfp class MatchConfig
implements Cloneable {
    public GameModeEnum a = GameModeEnum.a;
    public String b = "[z;p10]Crossing Large (10p).tmx";
    public int c = 0;
    public int d = 2;
    public boolean e = true;
    public int f = 1;
    public int g = 1;
    public float h = 1.0f;
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public boolean l;
    public boolean m = false;
    public boolean n = false;
    public boolean o = true;
    public boolean tournamentMode = false;
    public int q;

    public void a() {
        this.a = GameModeEnum.a;
        this.b = "[z;p10]Crossing Large (10p).tmx";
    }

    public String getString() {
        String string = "";
        String string2 = "\n";
        string = string + "startingCredits: " + this.c + string2;
        string = string + "fogMode: " + this.d + string2;
        string = string + "revealedMap: " + this.e + string2;
        string = string + "aiDifficulty: " + this.f + string2;
        string = string + "startingUnits: " + this.g + string2;
        string = string + "incomeMultiplier: " + this.h + string2;
        string = string + "noNukes: " + this.i + string2;
        string = string + "sharedControl: " + this.l + string2;
        string = string + "allowSpectators: " + this.o + string2;
        string = string + "lockedRoom: " + this.tournamentMode + string2;
        string = string + "randomSeed: " + this.q + string2;
        return string;
    }

    public MatchConfig getah() {
        try {
            return (MatchConfig) super.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new RuntimeException(cloneNotSupportedException);
        }
    }

    public void a(OutputNetStream as2) {
        as2.c(4);
        as2.a(this.d);
        as2.a(this.c);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h);
        as2.a(this.i);
        as2.a(this.j);
        as2.a(this.l);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.tournamentMode);
        as2.a(this.q);
    }

    public void a(InputNetStream k2) {
        byte by = k2.d();
        this.d = k2.readInt();
        this.c = k2.readInt();
        this.e = k2.readBoolean();
        this.f = k2.readInt();
        this.g = k2.readInt();
        this.h = k2.readFloat();
        this.i = k2.readBoolean();
        this.j = k2.readBoolean();
        this.l = k2.readBoolean();
        if (by >= 1) {
            this.m = k2.readBoolean();
        }
        if (by >= 2) {
            this.n = k2.readBoolean();
        }
        if (by >= 3) {
            this.o = k2.readBoolean();
            this.tournamentMode = k2.readBoolean();
        }
        if (by >= 4) {
            this.q = k2.readInt();
        }
    }

    public /* synthetic */ Object clone() {
        return this.getah();
    }
}
