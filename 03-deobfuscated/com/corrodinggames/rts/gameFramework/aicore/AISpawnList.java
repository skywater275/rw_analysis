/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.aicore.SpawnEntry;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

class AISpawnList {
    boolean a;
    CustomArrayList<SpawnEntry> b = new CustomArrayList<SpawnEntry>();
    final /* synthetic */ AIWaveSystem c;

    AISpawnList(AIWaveSystem f2) {
        this.c = f2;
    }

    public void a(UnitTypeHandle as2, int n2) {
        UnitTypeHandle as3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(as2);
        if (as3 != null) {
            as2 = as3;
        }
        this.b(as2, n2);
    }

    public void b(UnitTypeHandle as2, int n2) {
        for (SpawnEntry j2 : this.b) {
            if (j2.a != as2) continue;
            j2.b += n2;
            return;
        }
        SpawnEntry j3 = new SpawnEntry(this);
        j3.a = as2;
        j3.b = n2;
        this.b.add(j3);
    }

    public void a(float f2, float f3) {
        GlobalState l2 = GlobalState.B();
        int n2 = 0;
        PlayerState n3 = PlayerState.u(1);
        if (n3 == null) {
            GlobalState.e("Warning: Creating missing wave team AI");
            n3 = new AIStrategy(1);
            n3.r = 100;
            n3.U = true;
        }
        for (SpawnEntry j2 : this.b) {
            for (int i2 = 0; i2 < j2.b; ++i2) {
                UnitInstance am2 = j2.a.a();
                int n4 = 85;
                am2.eo = f2 + (float)GameUtils.a(-n4, n4, n2 + 0);
                am2.ep = f3 + (float)GameUtils.a(-n4, n4, n2 + 1);
                am2.cg = GameUtils.a(-180, 180, n2 + 2);
                n2 += 3;
                am2.setTeamInternal(n3);
                if (am2.eo < 0.0f) {
                    am2.eo = 0.0f;
                }
                if (am2.ep < 0.0f) {
                    am2.ep = 0.0f;
                }
                if (am2.eo > l2.bL.i()) {
                    am2.eo = l2.bL.i();
                }
                if (am2.ep > l2.bL.j()) {
                    am2.ep = l2.bL.j();
                }
                if (i2 != 0) continue;
                l2.bW.a(am2);
            }
        }
    }

    public String toString() {
        if (this.b.size() == 0) {
            return "No units";
        }
        String string = "";
        boolean bl = true;
        for (SpawnEntry j2 : this.b) {
            if (!bl) {
                string = string + ", ";
            }
            bl = false;
            string = string + j2.b + "x ";
            string = string + j2.a.e();
        }
        return string;
    }
}
