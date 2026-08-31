/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.GameFlag;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.UnitBuildStrategyEntry;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Collections;

public abstract class UnitBuildStrategy {
    String b;
    public ArrayList c = new ArrayList();
    private ArrayList a = new ArrayList();
    final /* synthetic */ AIStrategy d;

    public UnitBuildStrategy(AIStrategy a2, String string) {
        this.d = a2;
        this.b = string;
        a2.aiBehaviorList.add(this);
    }

    public boolean containsUnitType(UnitTypeHandle as2) {
        for (UnitBuildStrategyEntry e2 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) this.c) {
            if (e2.a != as2) continue;
            return true;
        }
        return false;
    }

    public abstract boolean isUnitTypeAllowed(UnitTypeHandle var1);

    public boolean a(UnitTypeHandle var1, MovementTypeEnum var2) {  // 02b a/d L84-99
        if (var2 == null) {
            return true;
        }
        MovementTypeEnum var3 = var1.o();
        if (var3 == MovementTypeEnum.g) {
            var3 = MovementTypeEnum.b;
        }
        if (var3 == MovementTypeEnum.h) {
            var3 = MovementTypeEnum.f;
        }
        return var3 == var2;
    }


    public float getBuildWeight(UnitTypeHandle as2) {
        return 10.0f;
    }

    public ArrayList isUnitTypeAllowed() {
        Collections.shuffle(this.a);
        return this.a;
    }

    public void containsUnitType() {
        this.c = new ArrayList();
        float f2 = 0.0f;
        for (UnitTypeHandle as2 : UnitRegistry.ae) {
            if (!this.isUnitTypeAllowed(as2)) continue;
            float f3 = this.getBuildWeight(as2);
            f2 += f3;
            this.c.add(new UnitBuildStrategyEntry(this, as2, f3));
        }
        this.a = new ArrayList(this.c);
        if (this.c.size() == 0) {
            GlobalState.e("AI: rebuildUnitMix: no units in unitMix:" + this.b);
        }
    }

    public UnitTypeHandle getBuildWeight() {
        return this.isUnitTypeAllowed(null, -1);
    }

    public UnitTypeHandle isUnitTypeAllowed(MovementTypeEnum ao2) {
        return this.isUnitTypeAllowed(ao2, -1);
    }

    public boolean isUnitTypeAllowed(UnitTypeHandle as2, MovementTypeEnum ao2) {
        if (ao2 == null) {
            return true;
        }
        MovementTypeEnum ao3 = as2.o();
        if (ao3 == MovementTypeEnum.g) {
            ao3 = MovementTypeEnum.b;
        }
        if (ao3 == MovementTypeEnum.h) {
            ao3 = MovementTypeEnum.f;
        }
        return ao3 == ao2;
    }

    public UnitTypeHandle isUnitTypeAllowed(MovementTypeEnum ao2, int n2) {
        if (this.c.size() == 0) {
            GlobalState.e("AI: getRandomUnitType: no units in unitMix:" + this.b);
            return null;
        }
        float f2 = 0.0f;
        int n3 = 0;
        for (UnitBuildStrategyEntry e2 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) this.c) {
            if (!this.isUnitTypeAllowed(e2.a, ao2) || n2 != -1 && e2.a.c() > n2) continue;
            f2 += e2.b;
            ++n3;
        }
        if (n3 == 0) {
            return null;
        }
        float f3 = GameUtils.c(0.0f, f2);
        float f4 = 0.0f;
        for (UnitBuildStrategyEntry e3 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) this.c) {
            if (!this.isUnitTypeAllowed(e3.a, ao2) || n2 != -1 && e3.a.c() > n2 || !((f4 += e3.b) > f3)) continue;
            return e3.a;
        }
        GlobalState.e("Did not find getRandomUnit, this should only happen very rarely, name:" + this.b + " unitMix.size:" + this.c.size() + " minPrice:" + n2 + " movementType:" + (Object)((Object)ao2) + " totalUnits:" + n3);
        return ((UnitBuildStrategyEntry) this.c.get((int)(this.c.size() - 1))).a;
    }
}
