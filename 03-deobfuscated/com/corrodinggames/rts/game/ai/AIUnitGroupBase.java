/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.units.actions.StopAction;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.TransporterGroup;
import com.corrodinggames.rts.game.ai.AIStrategyNode;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AIUnitGroupBase
extends AIStrategyNode {
    ArrayList F = new ArrayList();
    ArrayList G = new ArrayList();

    public int l() {
        return this.F.size();
    }

    public boolean addUnitHook() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public AIUnitGroupBase(AIStrategy a2) {
        super(a2);
    }

    public boolean m() {
        Iterator iterator = this.R.zoneQueue.iterator();
        while (iterator.hasNext()) {
            AIStrategyNode o2 = (AIStrategyNode) iterator.next();
            if (!(o2 instanceof TransporterGroup)) continue;
            TransporterGroup n2 = (TransporterGroup) o2;
            if (n2.m != this) continue;
            return true;
        }
        return false;
    }

    public void n() {
        Iterator iterator = this.F.iterator();
        while (iterator.hasNext()) {
            UnitType y2 = (UnitType)iterator.next();
            if (y2 != null && !y2.isDead) continue;
            if (y2 != null && y2.aB == this) {
                y2.aB = null;
            }
            if (y2 != null) {
                this.G.remove(y2);
            }
            iterator.remove();
        }
    }

    public void o() {
        Iterator iterator = this.G.iterator();
        while (iterator.hasNext()) {
            UnitType y2 = (UnitType)iterator.next();
            if (y2 != null && !y2.isDead && y2.cN == null && y2.cO == null) continue;
            iterator.remove();
        }
    }

    @Override
    public void p() {
        this.q();
        this.G.clear();
        super.p();  // 02b o.java L51-55 p(): R.bm/bn.remove + V=true
    }

    protected void addUnitHook(UnitType y2) {
        if (y2.aB != null) {
            y2.aB.b(y2);
        }
        if (y2.player != null && y2.player != this.R) {
            GlobalState.isKeyJustPressed("unit.team:" + y2.player.k + ", ai:" + this.R.k);
        }
        this.F.add(y2);
        y2.aB = this;
    }

    public void b(UnitType y2) {
        this.F.remove(y2);
        this.G.remove(y2);
        if (y2.aB == this) {
            y2.aB = null;
        }
    }

    public void q() {
        for (UnitType y2 : (java.util.Collection<UnitType>) (java.util.Collection) this.F) {
            if (y2 == null || y2.aB != this) continue;
            y2.aB = null;
        }
        this.F.clear();
    }

    public void b(float f2) {
    }

    public abstract void c(float var1);

    // v19.115u 补缺: javap h.a(y) protected 铁证 (02b 反编译漏方法, CombatAction super.a(y) 使用) — 简化空实现
    protected void a(UnitType y2) {
    }

}
