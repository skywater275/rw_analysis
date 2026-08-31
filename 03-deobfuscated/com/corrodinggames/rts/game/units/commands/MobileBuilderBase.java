/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.AirUnit;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.UnitFactoryHelper;
import com.corrodinggames.rts.game.units.commands.CarrierUnit;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.io.IOException;

public strictfp abstract class MobileBuilderBase
extends BuildSlot
implements CarrierUnit {
    public static final Paint y = new Paint();
    UnitFactoryHelper z = this.du();
    Rect A = new Rect();
    Rect B = new Rect();

    public MobileBuilderBase(boolean bl) {
        super(bl);
    }


    /* 02b d/i.java L25: this.z.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.r);
        this.z.a(as2);
        super.a(as2);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        if (k2.b() >= 69) {
            int n2 = k2.readInt();
            this.R(n2);
        }
        this.z.a(k2);
        super.a(k2);
    }

    public UnitFactoryHelper du() {
        return new UnitFactoryHelper(this);
    }

    public final int a(com.corrodinggames.rts.game.units.actions.ActionId var1, boolean var2) {  // 02b d/i.java L78-80: a(c,boolean) 杩斿洖 int (浠锋牸)
        return this.z.a(var1, var2);
    }

    @Override
    public void b(BuilderUnit j2) {
    }

    @Override
    public boolean c(BuilderUnit j2) {
        return true;
    }

    @Override
    public void a(BuilderUnit j2) {
        float f2 = this.z.b != null ? this.cj * 2.0f : this.cj * 3.0f;
        UnitInstance am2 = this.z.a(j2, f2, false, 0.0f);
        if (am2 != null) {
            if (am2.ep - am2.cj < this.ep + (float)this.dv()) {
                am2.ep = this.ep + (float)this.dv() + am2.cj;
            }
            com.corrodinggames.rts.game.PlayerState.c(am2);
        }
    }

    public int dv() {
        return -100;
    }

    @Override
    public int f(boolean bl) {
        return this.z.a(com.corrodinggames.rts.game.units.actions.GameAction.i, bl, true);
    }


    @Override
    public BuilderUnit dw() {
        return this.z.b();
    }


    @Override
    public CustomActionBase bD() {  // 02b i.java L86-88: custom.d.b = CustomActionBase
        return this.z.c();
    }

    @Override
    public CustomArrayList dx() {  // 02b i.java L90-92: utility.m = CustomArrayList
        return this.z.c;
    }

    @Override
    public int h(UnitTypeHandle as2) {
        return this.z.a(as2);
    }

    @Override
    public boolean dy() {
        return this.z.a();
    }

    @Override
    public void dz() {
        this.z.e = 1.0f;
    }

    @Override
    public void a(PointF pointF) {
        this.z.b = pointF;
    }

    @Override
    public boolean dA() {
        return false;
    }


    public float bV() {  // 02b i.java L114-116
        if (this.bT() && !this.z.a()) {
            return this.z.e;
        }
        return super.bV();
    }


    @Override
    public GameAction e(UnitTypeHandle as2) {  // 02b i.java L118-120: a.s = GameAction
        return this.z.b(as2);
    }


    @Override
    public void a(GameAction s2, boolean bl2) {  // 02b i.java L122-124: a(s,bl)
        this.z.a(s2, bl2, null, null);
    }


    @Override
    public void b(GameAction s2, boolean bl2) {  // 02b i.java L126-128: b(s,bl)
        this.z.a(s2, bl2);
    }


    @Override
    public void a(GameAction s2) {  // 02b i.java L130-132: a(s)
        this.z.a(s2);
    }


    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        this.z.a(f2);
    }


    public boolean c(float f2) {
        return super.c(f2);
    }


    public void bv() {
        com.corrodinggames.rts.game.PlayerState.a((UnitInstance) this);  // 02b i.java L146: n.a((am)this)
        this.z.a(true);
        super.bv();
    }


    public void a() {
        com.corrodinggames.rts.game.PlayerState.a((UnitInstance) this);  // 02b i.java L152: n.a((am)this)
        this.z.a(true);
        super.a();
    }


    public boolean l() {
        return false;
    }


    public void a(UnitInstance am2, int n2) {
        throw new RuntimeException("Unit cannot shoot");
    }


    public float m() {
        return 0.0f;
    }


    public float b(int n2) {
        return 0.0f;
    }


    public float c(int n2) {
        return 0.0f;
    }


    public void ca() {
        if (this.z.b != null) {
            com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            float f2 = (int)(this.eo - l2.cw);
            float f3 = (int)(this.ep - l2.cx);
            float f4 = (int)(this.z.b.a - l2.cw);
            float f5 = (int)(this.z.b.b - l2.cx);
            l2.bO.a(f2, f3, f4, f5, y);
        }
    }


    public int a(TeamTag g2) {  // 02b i.java L189-191: a(custom.g) = a(TeamTag)
        return this.z.a(g2);
    }

    static {
        y.a(255, 0, 255, 0);
        y.a(1.5f);
        y.a(true);
    }
}
