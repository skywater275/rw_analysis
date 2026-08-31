/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.units.actions.StopAction;

import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitType;
import java.util.HashMap;

public class BuildPreferenceCache {
    HashMap a = new HashMap();
    HashMap cachedCountsNoTransport = new HashMap();
    HashMap cachedUnitTypeCounts = new HashMap();

    public Integer clearAllCounts(boolean bl, UnitTypeHandle as2, boolean bl2) {
        if (bl) {
            return (Integer)this.cachedUnitTypeCounts.get(as2);
        }
        if (!bl2) {
            return (Integer)this.cachedCountsNoTransport.get(as2);
        }
        return (Integer)this.a.get(as2);
    }

    public void clearAllCounts(boolean bl, UnitTypeHandle as2, boolean bl2, Integer n) {
        if (bl) {
            this.cachedUnitTypeCounts.put(as2, n);
        } else if (!bl2) {
            this.cachedCountsNoTransport.put(as2, n);
        } else {
            this.a.put(as2, n);
        }
    }

    public void clearAllCounts() {
        this.a.clear();
        this.cachedCountsNoTransport.clear();
    }

    public void clearAllCounts(UnitTypeHandle as2) {
        this.a.put(as2, null);
        this.cachedCountsNoTransport.put(as2, null);
    }

    public void clearAllCounts(UnitType y2) {
        this.cachedUnitTypeCounts.put(y2.dz, null);
    }

    public void clearCombatCounts() {
        this.cachedUnitTypeCounts.clear();
    }
}
