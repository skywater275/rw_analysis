/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;
import com.corrodinggames.rts.R;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.MovableUnit;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;

public strictfp class TimedBomb
extends MovableUnit {
    public int a = 14;
    public float b = 60.0f;

    @Override
    /* 覆写链 super.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.c(0);
        as2.a(this.a);
        as2.a(this.b);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        k2.d();
        this.a = k2.readInt();
        this.b = k2.readFloat();
        super.a(k2);
    }

    public UnitRegistry b() {
        return UnitRegistry.R;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
    }

    public TimedBomb(boolean bl) {
        super(bl);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        this.b -= f2;
        if (this.b < 0.0f) {
            this.ci();
        }
    }


    public int s() {
        return this.a;
    }


    public boolean t() {
        return true;
    }


    public boolean u() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
