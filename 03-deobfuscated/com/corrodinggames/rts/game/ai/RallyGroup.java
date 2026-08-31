/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.units.actions.StopAction;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.AIUnitGroupBase;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.util.Iterator;
import java.io.IOException;

public class RallyGroup
extends AIUnitGroupBase {
    float a = 0.0f;


    /* 覆写链 super.a 抛 IOException */
    public void a(OutputNetStream as2) throws IOException {
        int n2 = this.F.size();
        as2.a(n2);
        for (UnitType y2 : (java.util.Collection<UnitType>) (java.util.Collection) this.F) {
            as2.a(y2);
        }
        as2.c(1);
        as2.a(this.G.size());
        for (UnitType y2 : (java.util.Collection<UnitType>) (java.util.Collection) this.G) {
            as2.a(y2);
        }
        as2.a(this.a);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        int n2;
        this.q();
        int n3 = k2.f();
        for (n2 = 0; n2 < n3; ++n2) {
            UnitType y2 = k2.p();
            if (y2 == null) continue;
            this.a(y2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            this.G.clear();
            int n4 = k2.f();
            for (int j = 0; j < n4; ++j) {
                UnitType y2 = k2.p();
                if (y2 == null) continue;
                this.G.add(y2);
            }
            this.a = k2.readFloat();
        }
        super.a(k2);
    }

    public RallyGroup(AIStrategy a2) {
        super(a2);
    }


    public void c(float f2) {
        this.n();
        if (!this.m()) {
            this.a += f2;
        }
        Iterator iterator = this.F.iterator();
        while (iterator.hasNext()) {
            UnitType y2 = (UnitType)iterator.next();
            if (!(this.c((UnitInstance) y2) < 3600.0f) || y2.cN != null) continue;
            if (y2.aB == this) {
                y2.aB = null;
            }
            iterator.remove();
        }
        if (this.F.size() == 0 || this.a > 5000.0f) {
            this.p();
        }
    }

    public void c(UnitType y2) {
        this.a(y2);
        this.G.add(y2);
    }
}
