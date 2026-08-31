/*
 * v19.133f58 整写: 02b gameFramework/n/a/c.java 直译 (单位数量条件, 迁移 aicore 包)
 * 修复: c→PlayerState/d→UnitTypeHandle/o→TeamTag 类型还原; am→UnitInstance; teams.b→TeamTag.parseSingleTag;
 *       deserializeTags→TeamTag.a (02b g.a(g,h)); 删旧 n/a 包 (game-lib n 类冲突)
 */
package com.corrodinggames.rts.gameFramework.aicore;

import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.bo;

public class UnitCountCondition
extends TaskCondition {
    Integer a;
    Integer b;
    PlayerState c;
    UnitTypeHandle d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    int k;
    boolean l;
    boolean m;
    boolean n;
    TeamTag o;
    boolean p;

    public static UnitCountCondition d(AITask a2) throws MapException {
        UnitCountCondition c2 = new UnitCountCondition();
        c2.a = a2.d("maxUnits");
        int n2 = 1;
        if (c2.a != null) {
            n2 = 0;
        }
        c2.b = a2.a("minUnits", n2);
        c2.c = a2.a();
        UnitTypeHandle as2 = null;
        String string = a2.b("unitType");
        if (string != null && (as2 = UnitRegistry.a(string)) == null) {
            a2.g("Cound not find unitType:" + string);
        }
        c2.d = as2;
        c2.e = a2.a("onlybuildings", "onlyBuildings", false);
        c2.g = a2.a("onlyMainBuildings", false);
        c2.h = a2.a("onlyOnResourcePool", false);
        c2.f = a2.a("onlyidle", "onlyIdle", false);
        c2.k = a2.a("onlyTechLevel", -1);
        c2.j = a2.a("onlyBuilders", false);
        c2.i = a2.a("onlyEmptyQueue", false);
        c2.l = a2.a("onlyAttack", false);
        c2.m = a2.a("onlyAttackAir", false);
        c2.n = a2.a("onlyIfEmpty", false);
        String string2 = a2.b("onlyWithTag");
        if (string2 != null && !string2.equals("")) {
            try {
                c2.o = TeamTag.parseSingleTag(string2);
            }
            catch (bo bo2) {
                throw new MapException(bo2.getMessage());
            }
        }
        c2.p = a2.a("includeIncomplete", false);
        return c2;
    }

    public boolean b(AITask a2) {
        return this.e(a2);
    }

    public boolean e(AITask a2) {
        int n2 = 0;
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n3 = UnitInstance.bE.size();
        for (int i = 0; i < n3; ++i) {
            UnitInstance am2 = amArray[i];
            if (this.c != null && am2.player != this.c || !(am2 instanceof UnitType) || am2.cN != null || !a2.a(am2) || this.d != null && am2.r() != this.d) continue;
            UnitType y2 = (UnitType)am2;
            if (!this.p && !am2.isAlive() || this.l && !am2.l() || this.m && (!am2.l() || !y2.af()) || this.e && !am2.isFactoryBuilding() || this.g && (!am2.isFactoryBuilding() || !am2.isNeutralTeam()) || this.h && !am2.r().p() || this.j && !am2.ak() || this.f && !y2.aq() || this.i && y2.a((TeamTag) null) > 0 || this.k != -1 && am2.V() != this.k || this.o != null && !TeamTag.a(this.o, am2.getStatusEffects()) || this.n && y2.bB() > 0) continue;
            ++n2;
        }
        return n2 >= this.b && (this.a == null || n2 <= this.a);
    }
}
