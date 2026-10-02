/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import java.io.IOException;

public class DebugPacketBuilder
extends OutputNetStream {
    public String a = "";


    public strictfp void a(int n2) {
        this.a = this.a + "|" + n2;
        super.a(n2);
    }


    public strictfp void a(float f2) {
        this.a = this.a + "|" + f2;
        super.a(f2);
    }


    public strictfp void a(short s) {
        this.a = this.a + "|" + s;
        super.a(s);
    }


    public strictfp void a(boolean bl) {
        this.a = this.a + "|" + bl;
        super.a(bl);
    }


    /* 覆写 OutputNetStream.a(String,boolean) (已 throws) */
    public strictfp void a(String string, boolean bl) throws IOException {
        this.a = this.a + "<" + string + ">";
        super.a(string, bl);
    }


    public strictfp void a(String string) {
        this.a = this.a + "</" + string + ">";
        super.a(string);
    }


    public strictfp void a(ConnectionState am2) {
        this.a = this.a + "|u:" + am2;
        super.a(am2);
    }
}
