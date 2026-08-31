/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.actions.GameAction;
import java.util.Iterator;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.game.units.projectiles.ActionAddCredits$1;
import com.corrodinggames.rts.game.units.projectiles.ActionAddCredits$2;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class SpecialBuilding
extends AbstractSubBuilding
implements UnitShield {
    static Texture detonateAction = null;
    static Texture upgradeAction = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    float e = 0.0f;
    float f;
    boolean g;
    CustomArrayList h = new CustomArrayList();
    public static final GameAction i = new SpecialBuilding$1(109);
    public static final GameAction j = new SpecialBuilding$2(110);
    static ArrayList k = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h.size());
        Iterator var2 = this.h.iterator();
        while (var2.hasNext()) {
            UnitInstance am2 = (UnitInstance)var2.next();
            as2.a(am2);
        }
        super.a(as2);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        this.e = k2.readFloat();
        this.f = k2.readFloat();
        this.g = k2.e();
        this.h.clear();
        int n2 = k2.f();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = k2.o();
            if (am2 == null) continue;
            this.h.add(am2);
        }
        super.a(k2);
    }

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.s;
    }

    public static void L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        detonateAction = l2.bO.a(R$drawable.hovercraft);
        c = l2.bO.a(R$drawable.hovercraft_shadow);
        upgradeAction = l2.bO.a(R$drawable.hovercraft_dead);
        d = com.corrodinggames.rts.game.PlayerState.a(detonateAction);
    }


    public Texture d() {
        if (this.isDead) {
            return upgradeAction;
        }
        return d[this.player.getTeamIndex()];
    }


    public Texture k() {
        return c;
    }


    public Texture d(int n2) {
        return null;
    }


    public boolean e() {
        this.M = upgradeAction;
        this.S(0);
        this.bT = false;
        this.f(true);
        this.a(com.corrodinggames.rts.game.units.UnitState.b);
        return true;
    }

    @Override
    public void a() {
        this.f(true);
        super.a();
    }

    public static strictfp boolean a(UnitInstance am2, UnitInstance am3, boolean bl) {  // 02b e/i L154-161
        float f2 = 9.0f;
        float f3 = -180.0f;
        float f4 = 70.0f;
        float f5 = 0.0f;
        float f6 = 7.0f;
        return a(am2, am3, bl, f2, f3, f4, f5, f6);
    }

    public static strictfp boolean a(UnitInstance am2, UnitInstance am3, boolean bl, float f2, float f3, float f4, float f5, float f6) {  // 02b e/i L163-205
        float f7 = am2.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(am2.cg + f3) * f6 - com.corrodinggames.rts.gameFramework.GameUtils.j(am2.cg + f3) * f5;
        float f8 = am2.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(am2.cg + f3) * f6 + com.corrodinggames.rts.gameFramework.GameUtils.k(am2.cg + f3) * f5;
        f7 += com.corrodinggames.rts.gameFramework.GameUtils.k(am2.cg + 90.0f) * (bl ? -f2 : f2);
        f8 += com.corrodinggames.rts.gameFramework.GameUtils.j(am2.cg + 90.0f) * (bl ? -f2 : f2);
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f7 += 10.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f7 -= 20.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f7 -= 10.0f;
            f8 += 10.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f8 -= 20.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            return false;
        }
        am3.cN = null;
        am3.eo = f7;
        am3.ep = f8;
        am3.bZ += 0.1f;
        am3.cg = am2.cg + f3;
        am3.bR = am2;
        am3.bS = 45.0f;
        if (am3 instanceof UnitType) {
            UnitType unitType = (UnitType) am3;
            unitType.j(am3.cg);
            unitType.az();
            unitType.d(am3.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(am3.cg + (bl ? -f2 : f2)) * f4, am3.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(am3.cg + (bl ? -f2 : f2)) * f4);
            unitType.ac = 0;
        }
        return true;
    }

    public void f(boolean bl) {
        Iterator iterator = this.h.iterator();
        while (iterator.hasNext()) {
            UnitInstance am2 = (UnitInstance) iterator.next();
            am2.cN = null;
            am2.eo = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg) * -9.0f;
            am2.ep = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg) * -9.0f;
            if (!bl) continue;
            am2.getAttackRange();
        }
        this.h.clear();
    }

    public SpecialBuilding(boolean bl) {
        super(bl);
        this.T(20);
        this.U(32);
        this.ck = this.cj = 15.0f;
        this.hp = this.maxHp = 450.0f;
        this.M = detonateAction;
        this.N = c;
    }

    public static int m(CustomArrayList m2) {
        int n2 = 0;
        Iterator var2 = m2.iterator();
        while (var2.hasNext()) {
            UnitInstance am2 = (UnitInstance)var2.next();
            n2 += am2.getMaxUnitGroupSize();
        }
        return n2;
    }

    public static boolean m(CustomArrayList m2, int n2, UnitInstance am2) {
        int n3 = m(m2);
        return n3 + am2.getMaxUnitGroupSize() <= n2;
    }


    public int bY() {
        return m(this.h);
    }


    public int bZ() {
        return 4;
    }

    public static boolean m(UnitInstance am2, UnitInstance am3, boolean bl) {
        float f2 = 9.0f;
        float f3 = -180.0f;
        float f4 = 70.0f;
        float f5 = 0.0f;
        float f6 = 7.0f;
        return m(am2, am3, bl, f2, f3, f4, f5, f6);
    }

    public static boolean m(UnitInstance am2, UnitInstance am3, boolean bl, float f2, float f3, float f4, float f5, float f6) {
        float f7 = am2.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(am2.cg + f3) * f6 - com.corrodinggames.rts.gameFramework.GameUtils.sinFast(am2.cg + f3) * f5;
        float f8 = am2.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(am2.cg + f3) * f6 + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(am2.cg + f3) * f5;
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(am2.cg + 90.0f) * (bl ? -f2 : f2), f8 += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(am2.cg + 90.0f) * (bl ? -f2 : f2))) {
            f7 += 10.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f7 -= 20.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f7 -= 10.0f;
            f8 += 10.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            f8 -= 20.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am3, f7, f8)) {
            return false;
        }
        am3.cN = null;
        am3.eo = f7;
        am3.ep = f8;
        am3.bZ += 0.1f;
        am3.cg = am2.cg + f3;
        am3.bR = am2;
        am3.bS = 45.0f;
        if (am3 instanceof UnitType) {
            UnitType y2 = (UnitType)am3;
            y2.j(am3.cg);
            y2.az();
            y2.d(am3.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(am3.cg + (bl ? -f2 : f2)) * f4, am3.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(am3.cg + (bl ? -f2 : f2)) * f4);
            y2.ac = 0;
        }
        return true;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead || !this.bT()) {
            return;
        }
        if (this.cl == 0.0f && this.em != 3) {
            this.S(3);
        }
        if (this.g && !this.cK() && !this.cK) {
            this.f = com.corrodinggames.rts.gameFramework.GameUtils.a(this.f, f2);
            if (this.f == 0.0f) {
                this.f = 30.0f;
                if (this.h.size() == 0) {
                    this.g = false;
                } else {
                    boolean bl = this.h.size() % 2 == 0;
                    UnitInstance am2 = (UnitInstance) this.h.remove(this.h.size() - 1);
                    boolean bl2 = a(this, am2, bl);
                    if (!bl2) {
                        this.h.add(am2);
                    }
                    if (this.h.size() == 0) {
                        this.g = false;
                    }
                }
            }
        }
        this.e += 4.0f * f2;
        if (this.e > 360.0f) {
            this.e -= 360.0f;
        }
        this.eq = !this.g ? com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, 3.0f + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.e) * 1.5f, 0.1f * f2) : com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, 0.0f, 0.1f * f2);
    }


    public void a(UnitInstance am2, int n2) {
    }


    public float m() {
        return 30.0f;
    }


    public float b(int n2) {
        return 100.0f;
    }


    public float z() {
        if (this.cK()) {
            return 1.2f;
        }
        return 0.9f;
    }


    public float A() {
        if (this.cK()) {
            return 1.8f;
        }
        return 1.4f;
    }


    public float B() {
        return 0.1f;
    }


    public float C() {
        return 0.03f;
    }


    public float D() {
        return 0.05f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean l() {
        return false;
    }


    public boolean d(UnitInstance am2, boolean bl) {
        if (this.g) {
            return false;
        }
        if (!m(this.h, 4, am2)) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        if (this.player != am2.player && !bl) {
            return false;
        }
        return com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, true, true);
    }


    public boolean e(UnitInstance am2, boolean bl) {
        if (!this.d(am2, bl)) {
            return false;
        }
        this.C(am2);
        return true;
    }

    public void C(UnitInstance am2) {
        am2.cN = this;
        this.h.add(am2);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bS.l(am2);
    }


    public void e(UnitInstance am2) {
        if (am2.cN == this) {
            this.h.remove(am2);
            am2.cN = null;
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("Unit is not being transported");
        }
    }


    public float bN() {
        return 12000.0f;
    }

    @Override
    public boolean bA() {
        return this.g;
    }

    public void M() {
        this.g = true;
        this.f = 30.0f;
    }

    public void ds() {
        this.g = false;
    }


    public void m(GameAction s2, boolean bl) {
        if (s2 == i) {
            this.M();
        }
        if (s2 == j) {
            this.ds();
        }
    }


    public boolean cr() {
        return true;
    }

    @Override
    public int bB() {
        return this.h.size();
    }


    public ActionId cp() {
        return i.N();
    }


    public ArrayList N() {
        return k;
    }


    public boolean f() {
        return !this.cK();
    }


    public boolean j() {
        return true;
    }


    public CustomArrayList bz() {
        return this.h;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }

    static {
        k.add(i);
        k.add(j);
    }
}
