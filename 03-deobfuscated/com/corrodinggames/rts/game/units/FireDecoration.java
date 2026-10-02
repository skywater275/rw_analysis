/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.DecorType4;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.DecorUnit;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.IOException;

public class FireDecoration
extends UnitInstance {  // 02b al/ai extends v extends am
    static Texture[] a = new Texture[2];
    Texture b;
    int c;
    int d = 0;
    float e;
    float f;
    int g = 0;
    int h = 0;
    float i;
    float j;
    boolean k = false;
    float l;
    float m;
    float n;
    float o;
    float p;
    float q;
    boolean r;
    static Point s = new Point();
    public static DecorType4 t = new DecorType4();
    Rect u = new Rect();

    public static void loadTextures() {  // 02b ai 静态 b() L40-43 (aj 与 UnitInstance.aj() 冲突改名)
        GlobalState l2 = GlobalState.B();
        FireDecoration.a[0] = l2.bO.a(R$drawable.fire);  // 02b L42: a[0] (ai 为幻觉类名)
    }


    /* 覆写链 super.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.c(0);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.c = k2.readInt();
        this.d = k2.readInt();
        this.e = k2.readFloat();
        k2.d();
        super.a(k2);
    }

    public Texture d() {
        return this.b;
    }


    public boolean e() {
        return false;
    }

    public FireDecoration(boolean bl) {
        super(bl);
        this.a(0);
        this.cj = 20.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 100.0f;
        this.cg = -90.0f;
        this.bT = false;
        this.o = 0.05f;
        this.p = 120.0f;
        this.S(3);
    }


    public void f_() {
        this.bT = false;
    }

    public void a(int n2) {
        this.c = n2;
        if (this.c != 0) {
            throw new RuntimeException("Fire type:" + this.c + " is not supported");
        }
        this.T(20);
        this.U(20);
        this.g = 0;
        this.h = 0;
        this.b = a[0];
    }

    public void f() {
        this.k = true;
        this.i = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, -5, 5, 1);  // 02b ai L102
        this.j = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, -5, 5, 2);  // 02b L103
        this.e = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 1, 10, 3);  // 02b L104
        this.d = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 0, 2, 4);  // 02b L105
        this.f = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 7, 13, 5);  // 02b L106
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;  // 02b ai L108: b.b (MapEngine)
        l2.bL.a(this.eo, this.ep);
        int n2 = l2.bL.T;
        int n3 = l2.bL.U;
        if (!b2.c(n2, n3)) {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 2.0f;
            return;
        }
        MapLayer g2 = l2.bL.groundLayer.a(n2, n3);  // 02b L117: bL.u.a (u=b.e=TMXMapLoader=groundLayer)
        boolean bl = false;
        if (g2.layerVisible || g2.isImageLayer || g2.needsRedraw || g2.layerLocked) {  // 02b b/g e/h/k/f
            bl = true;
        }
        if (bl) {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 2.0f;
            return;
        }
        this.l = 5.0E-4f;
        this.m = 1.0f;
        this.n = 0.3f;
        this.o += (float)GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 0, 10, 10) / 1000.0f;  // 02b L131
    }


    public void a(float f2) {
        super.a(f2);  // 02b ai L137: super.a(var1) (isVisibleTo 为幻觉)
        if (!this.k) {
            this.f();
        }
        if (this.o < this.m) {
            this.o += this.l * f2;
            if (this.o > this.m) {
                this.o = this.m;
            }
        }
        if (this.o > this.n) {
            this.q = (float)((double)this.q + 0.01 * (double)f2);
            if (!this.r && this.q > 1.0f || this.q > 8.0f) {
                this.q = (float)GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 0, 10, 10) / 1000.0f;  // 02b L152
                this.k();
            }
        }
        this.e += f2;
        if (this.e > 10.0f) {
            this.e = 0.0f;
            ++this.d;
            if (this.d > 3) {
                this.d = 0;
            }
        }
        if (this.o < 0.0f) {
            this.bv();
        }
    }

    public void k() {
        this.r = true;
        this.b(-1, -1);  // 02b ai L174: b(var1,var2)
        this.b(0, -1);
        this.b(1, -1);
        this.b(-1, 0);
        this.b(1, 0);
        this.b(-1, 1);
        this.b(0, 1);
        this.b(1, 1);
    }

    public void b(int n2, int n3) {  // 02b ai L184: b(int,int) 扩散邻居
        GlobalState l2 = GlobalState.B();
        float f2 = (int)(this.eo + (float)(n2 * l2.bL.tilePixelWidth));  // 02b b.b.n
        float f3 = (int)(this.ep + (float)(n3 * l2.bL.tilePixelHeight));  // 02b b.b.o
        FireDecoration ai2 = FireDecoration.a(f2, f3);
        if (ai2 == null) {
            FireDecoration ai3 = new FireDecoration(false);
            ai3.eo = f2;
            ai3.ep = f3;
            ai3.b(this.player);  // 02b L193: var7.b(this.bX)
            l2.cc.a(ai3);
            PlayerState.c(ai3);
            this.r = false;
        }
    }

    public static FireDecoration a(float f2, float f3) {
        GlobalState l2 = GlobalState.B();
        t.a(f2, f3);  // 02b ai L203: t.a(var0,var1) (t=DecorType4, AmphibiousUnit 为幻觉)
        l2.cc.a(f2, f3, 30.0f, null, 1.0f, t);
        return t.c;  // 02b L205: t.c (ai 为幻觉类名)
    }


    public Rect a_(boolean bl) {
        int n2 = this.g;
        int n3 = this.h;
        dC.a(n2 += this.d * this.es, n3, n2 + this.es, n3 + this.et);
        return dC;
    }


    public boolean c(float f2) {
        Texture e2 = this.d();  // 02b L217: m.e (Texture)
        GlobalState l2 = GlobalState.B();
        du.a(this.cF());
        du.a(0.0f, (int)(-this.eq));
        du.a(this.i, this.j);
        dv.a(this.a_(false));
        l2.bO.k();
        float f3 = du.d();
        float f4 = du.e();
        l2.bO.a(this.d(false), f3, f4);
        l2.bO.a(this.o * 2.7f, this.o * 2.7f, f3, f4);
        l2.bO.a(e2, dv, du, null);
        l2.bO.l();
        return true;
    }


    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.a;  // 02b ao.a (ao=MovementTypeEnum)
    }


    public boolean i() {
        return false;
    }


    public boolean setTeamInternalById() {  // 03 UnitInstance 抽象 (02b 对应方法; FireDecoration 固定 false)
        return false;
    }

    public boolean Q() {  // 02b ai L241: 覆盖 am.Q() 抽象
        return false;
    }

    public boolean aj() {  // 02b ai L249: 覆盖 am.aj() 抽象
        return false;
    }

    public boolean isOnScreen() {  // 02b am.Q() = 03 isOnScreen 语义名 (02b ai L241)
        return false;
    }


    public boolean ak() {
        return false;
    }


    public boolean s_() {
        return true;
    }


    public boolean c_() {
        return false;
    }

    public UnitRegistry s() {
        return UnitRegistry.S;
    }


    public void n() {
        super.n();
    }


    public float x() {
        return -1.0f;
    }


    public boolean l() {
        return false;
    }


    public boolean P() {
        return true;
    }


    public float a(UnitInstance am2, float f2, com.corrodinggames.rts.game.MovementController f3) {  // 02b ai L281: a(am,float,f) (f=MovementController, WaterUnit 为幻觉)
        this.o -= f2 / 100.0f;
        f2 = 0.0f;
        return super.a(am2, f2, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.s();
    }
}
