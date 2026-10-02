/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.game.units;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class BuildingBase
extends com.corrodinggames.rts.game.units.commands.BuildSlot {
    static Texture a = null;
    public static Texture b = null;  // 02b e/b.java L21: public static m.e b
    static Texture c = null;  // 02b e/b.java L22

    public UnitRegistry b() {
        return UnitRegistry.H;
    }

    public static void a_() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.crystal);
    }


    public Texture d() {
        return a;
    }


    public boolean e() {
        return true;
    }


    public void a(int n2) {
    }

    public BuildingBase(boolean bl) {
        super(bl);
        this.M = a;
        this.b(a);
        this.cj = 11.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 600.0f;
        this.S(1);
        this.n.a(0, -1, 0, 0);
        this.o.a(this.n);
    }


    public Paint f() {
        Paint paint = super.f();
        return paint;
    }


    public void a(float f2) {
        super.a(f2);
        // 02b L194-200: if(!this.bV) { a(f2, this); } 静态动画辅助 (03 侧缺失, PENDING)
    }


    public float g() {
        return 0.02f;
    }


    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.a;
    }


    public boolean i() {
        return false;
    }


    public boolean s_() {
        GlobalState l2 = GlobalState.B();
        du.a(this.cE());
        return RectF.a(l2.cM, du);
    }


    public Texture k() {
        return null;
    }


    public boolean l() {
        return false;
    }


    public float getMaxMoveDistance() {
        return 0.0f;
    }

    public strictfp float m() {  // 02b e/b.java L246: UnitType 抽象覆写
        return 30.0f;
    }


    public float b(int n2) {
        return 0.0f;
    }


    public float c(int n2) {
        return 0.0f;
    }


    public void a(UnitInstance am2, int n2) {
    }


    public void n() {
        super.n();
    }


    public boolean o() {
        return true;
    }


    public boolean p() {
        return true;
    }


    public boolean q() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
