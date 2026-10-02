/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;

import com.corrodinggames.rts.game.UnitManager;
import com.corrodinggames.rts.game.BuildQueue;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.CarrierUnit;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public final class TeamUnitTracker {
    public int a = 5;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager h = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager i = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager j = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager k = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager l = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
    public boolean m;
    public int n;
    public int o;
    public BuildQueue p = new BuildQueue();
    public BuildQueue q = new BuildQueue();

    public void incrementUnitCount(UnitInstance am2) {
        EffectManager f2;
        CustomActionBase b2;
        ++this.d;
        if (am2.cm < 1.0f) {
            ++this.f;
        } else {
            ++this.c;
        }
        UnitTypeHandle as2 = am2.r();
        if (!as2.k()) {
            ++this.b;
        }
        if (!this.m && !am2.u() && am2.r().y()) {
            this.m = true;
        }
        if ((b2 = am2.getCustomResourceHolder()) != null) {
            this.k.a(b2, 0.0, Double.MAX_VALUE);
            this.l.a(b2, -1.7976931348623157E308, 0.0);
        }
        if (am2 instanceof CarrierUnit) {
            CarrierUnit l2 = (CarrierUnit) ((Object)am2);
            int n2 = l2.f(false);
            this.b += n2;
            this.e += n2;
            if (n2 != 0) {
                this.incrementUnitCount(l2);
            }
        }
        this.incrementTypeCount(am2);
        float f3 = am2.getSpeedMultiplier();
        if (f3 != 0.0f && am2.cm >= 1.0f) {
            this.g = (int)((float)this.g + f3);
        }
        if (!(f2 = am2.getDefaultStatModifiers()).c() && am2.cm >= 1.0f) {
            this.h.do_b(f2);
            this.i.a(f2, 0.0, Double.MAX_VALUE);
            this.j.a(f2, -1.7976931348623157E308, 0.0);
        }
        if (am2.isNotCapturable()) {
            int n3 = am2.getResourceProduction().b();
            CustomActionBase b3 = as2.B();  // 02b game/s.java L78: custom.d.b var7 = var2.B()
            if (b3 != null) {
                n3 += b3.b();  // 02b L80: var7.b()
            }
            if (as2.j()) {
                this.o += n3;
            } else {
                this.n += n3;
            }
        }
    }

    public void decrementUnitCount(UnitInstance am2) {
        EffectManager f2;
        CustomActionBase b2;
        --this.d;
        if (am2.cm < 1.0f) {
            --this.f;
        } else {
            --this.c;
        }
        UnitTypeHandle as2 = am2.r();
        if (!as2.k()) {
            --this.b;
        }
        if ((b2 = am2.getCustomResourceHolder()) != null) {
            this.k.do_b(b2, 0.0, Double.MAX_VALUE);
            this.l.do_b(b2, -1.7976931348623157E308, 0.0);
        }
        if (am2 instanceof CarrierUnit) {
            CarrierUnit l2 = (CarrierUnit) ((Object)am2);
            int n2 = l2.f(false);
            this.b -= n2;
            this.e -= n2;
            if (n2 != 0) {
                this.decrementUnitCount(l2);
            }
        }
        this.decrementTypeCount(am2);
        float f3 = am2.getSpeedMultiplier();
        if (f3 != 0.0f && am2.cm >= 1.0f) {
            this.g = (int)((float)this.g - f3);
        }
        if (!(f2 = am2.getDefaultStatModifiers()).c() && am2.cm >= 1.0f) {
            this.h.c(f2);
            this.i.do_b(f2, 0.0, Double.MAX_VALUE);
            this.j.do_b(f2, -1.7976931348623157E308, 0.0);
        }
        if (am2.isNotCapturable()) {
            int n3 = am2.getResourceProduction().b();
            CustomActionBase b3 = as2.B();  // 02b game/s.java L78: custom.d.b var7 = var2.B()
            if (b3 != null) {
                n3 += b3.b();  // 02b L80: var7.b()
            }
            if (as2.j()) {
                this.o -= n3;
            } else {
                this.n -= n3;
            }
        }
    }

    private final void incrementTypeCount(UnitInstance am2) {
        com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
        if (h2 != null) {
            for (com.corrodinggames.rts.game.units.custom.TeamTag g2 : h2.a) {
                UnitManager p2 = this.incrementUnitCount(g2);
                if (am2.cm < 1.0f) {
                    ++p2.activeUnitCount;
                    continue;
                }
                ++p2.totalUnitCount;
            }
        }
    }

    private final void decrementTypeCount(UnitInstance am2) {
        com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
        if (h2 != null) {
            for (com.corrodinggames.rts.game.units.custom.TeamTag g2 : h2.a) {
                UnitManager p2 = this.incrementUnitCount(g2);
                if (am2.cm < 1.0f) {
                    --p2.activeUnitCount;
                    continue;
                }
                --p2.totalUnitCount;
            }
        }
    }

    private final void incrementUnitCount(CarrierUnit l2) {
        CustomArrayList m2 = l2.dx();
        if (m2.a != 0) {
            for (java.util.Iterator iterator = m2.iterator(); iterator.hasNext();) {
                BuilderUnit j2 = (BuilderUnit)iterator.next();
                com.corrodinggames.rts.game.units.custom.UnitConfig h2;
                com.corrodinggames.rts.game.units.UnitTypeHandle as2;
                if (!j2.isCurrentlyBuilding || (as2 = j2.serializer) == null || (h2 = as2.x()) == null) continue;
                for (com.corrodinggames.rts.game.units.custom.TeamTag g2 : h2.a) {
                    UnitManager p2 = this.incrementUnitCount(g2);
                    p2.maxAllowedUnits += j2.builderId;
                }
            }
        }
    }

    private final void decrementUnitCount(CarrierUnit l2) {
        CustomArrayList m2 = l2.dx();
        if (m2.a != 0) {
            for (java.util.Iterator iterator = m2.iterator(); iterator.hasNext();) {
                BuilderUnit j2 = (BuilderUnit)iterator.next();
                com.corrodinggames.rts.game.units.custom.UnitConfig h2;
                com.corrodinggames.rts.game.units.UnitTypeHandle as2;
                if (!j2.isCurrentlyBuilding || (as2 = j2.serializer) == null || (h2 = as2.x()) == null) continue;
                for (com.corrodinggames.rts.game.units.custom.TeamTag g2 : h2.a) {
                    UnitManager p2 = this.incrementUnitCount(g2);
                    p2.maxAllowedUnits -= j2.builderId;
                }
            }
        }
    }

    public final UnitManager incrementUnitCount(com.corrodinggames.rts.game.units.custom.TeamTag g2) {
        UnitManager[] pArray = this.q.b;
        int n2 = this.q.c;
        for (int resourceType2 = 0; resourceType2 < n2; ++resourceType2) {
            UnitManager p2 = pArray[resourceType2];
            if (p2.teamTag == g2) {
                return p2;
            }
            if (p2.teamTag != null) continue;
            p2.teamTag = g2;
            return p2;
        }
        UnitManager p3 = new UnitManager();
        p3.teamTag = g2;
        this.q.a(p3);
        return p3;
    }
}
