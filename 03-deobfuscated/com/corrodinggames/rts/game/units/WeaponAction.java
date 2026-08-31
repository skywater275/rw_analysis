/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ProjectileManager;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.IOException;

public strictfp final class WeaponAction {
    WeaponTypeEnum a;  // 02b au.a = av (WeaponTypeEnum)
    UnitTypeHandle b;  // 02b au.b = as (UnitTypeHandle)
    ActionId c;  // 02b au.c = a.c (ActionId)
    int d;
    float e = 1.0f;
    float f = 1.0f;
    long g = -1L;
    UnitInstance h;  // 02b au.h = am
    public com.corrodinggames.rts.gameFramework.ProjectileManager i;  // 02b au.i = ab (v19.115i: ab=ProjectileManager)
    public boolean j;
    public float k = -1.0f;
    public float l = -1.0f;
    public boolean m;
    public boolean n;

    public boolean a(WeaponAction au2) {
        return !(GameUtils.c(this.e - au2.e) > 3.0f) && !(GameUtils.c(this.f - au2.f) > 3.0f);
    }

    public boolean b(WeaponAction au2) {
        if (au2 == null) {
            return false;
        }
        if (this.a != au2.a) {
            return false;
        }
        if (this.b != au2.b) {
            return false;
        }
        if (GameUtils.c(this.e - au2.e) > 1.0f || GameUtils.c(this.f - au2.f) > 1.0f) {
            return false;
        }
        if (this.d != au2.d) {
            return false;
        }
        return this.h == au2.h;
    }

    public UnitTypeHandle a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.e);
        as2.a(this.f);
        if (this.g != -1L) {
            as2.a(this.g);
        } else {
            as2.a(this.h);
        }
        as2.c(this.d);
        as2.a(this.k);
        as2.a(this.l);
        as2.a(this.m);
        as2.a(this.j);
        as2.a(this.n);
        ActionId.a(as2, this.c);
    }

    public void a(InputNetStream k2) {
        this.a = (WeaponTypeEnum) k2.b(WeaponTypeEnum.class);
        this.b = k2.q();
        this.e = k2.readFloat();
        this.f = k2.readFloat();
        this.g = k2.n();
        this.h = null;
        if (k2.b() >= 40) {
            this.d = k2.d();
        }
        if (k2.b() >= 46) {
            this.k = k2.readFloat();
            this.l = k2.readFloat();
        }
        if (k2.b() >= 58) {
            this.m = k2.readBoolean();
        }
        if (k2.b() >= 65) {
            this.j = k2.readBoolean();
        }
        if (k2.b() >= 79) {
            this.n = k2.readBoolean();
        }
        if (k2.b() >= 82) {
            this.c = ActionId.a(k2);
        }
    }

    public void c() {
        if (this.g != -1L) {
            this.h = CustomUnitBase.a(this.g, true);
            if (this.h == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("convertUnitIds failed");
                if (this.a != null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("convertUnitIds: type:" + this.a.toString());
                }
                if (this.b != null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("convertUnitIds: build:" + this.b.toString());
                }
                com.corrodinggames.rts.gameFramework.GlobalState.b("convertUnitIds: x:" + this.e + ", y:" + this.f);
            }
            this.g = -1L;
        }
    }

    public WeaponTypeEnum d() {
        return this.a;
    }

    public void e() {
        this.a = WeaponTypeEnum.a;
        this.b = null;
        this.d = 1;
        this.e = 2.0f;
        this.f = 2.0f;
        this.g = -1L;
        this.h = null;
        this.i = null;
        this.k = -1.0f;
        this.l = -1.0f;
        this.m = false;
        this.j = false;
        this.n = false;
        this.c = null;
    }

    public boolean f() {
        return this.a == WeaponTypeEnum.b || this.a == WeaponTypeEnum.d || this.a == WeaponTypeEnum.g || this.a == WeaponTypeEnum.e || this.a == WeaponTypeEnum.i || this.a == WeaponTypeEnum.k || this.a == WeaponTypeEnum.m || this.a == WeaponTypeEnum.n;
    }

    public float g() {
        if (this.f() && this.h != null) {
            return this.h.eo;
        }
        return this.e;
    }

    public float h() {
        if (this.f() && this.h != null) {
            return this.h.ep;
        }
        return this.f;
    }

    public UnitInstance i() {
        return this.h;
    }

    public void a(float f2, float f3) {
        this.e();
        this.a = WeaponTypeEnum.a;
        this.e = f2;
        this.f = f3;
    }

    public void b(float f2, float f3) {
        this.e();
        this.a = WeaponTypeEnum.h;
        this.e = f2;
        this.f = f3;
    }

    public void a(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.b;
        this.h = am2;
    }

    public void a(float f2, float f3, UnitTypeHandle as2, int n2) {
        this.e();
        this.a = WeaponTypeEnum.c;
        this.e = f2;
        this.f = f3;
        this.b = as2;
        this.d = n2 = (int)((byte)n2);
    }

    public void b(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.d;
        this.h = am2;
    }

    public void c(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.k;
        this.h = am2;
    }

    public void d(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.m;
        this.h = am2;
    }

    public void e(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.n;
        this.h = am2;
    }

    public void c(float f2, float f3) {
        this.e();
        this.a = WeaponTypeEnum.j;
        this.e = f2;
        this.f = f3;
    }

    public void f(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.g;
        this.h = am2;
    }

    public void g(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.e;
        this.h = am2;
    }

    public void h(UnitInstance am2) {
        this.e();
        this.a = WeaponTypeEnum.i;
        this.h = am2;
    }

    public void c(WeaponAction au2) {
        this.e();
        this.a = au2.a;
        this.b = au2.b;
        this.e = au2.e;
        this.f = au2.f;
        this.h = au2.h;
        this.i = au2.i;
        this.d = au2.d;
        this.j = au2.j;
        this.c = au2.c;
    }

    public long j() {
        long l2 = 0L;
        if (this.a != null) {
            l2 += (long)this.a.ordinal();
        }
        return l2;
    }

    public void k() {
        if (this.h != null) {
            this.g = this.h.eh;
            this.h = null;
        }
        this.i = null;
    }

    public UnitInstance l() {
        if (this.f()) {
            UnitInstance am2 = this.i();
            return am2;
        }
        UnitType y2 = PlayerState.i.t;
        y2.cg = 0.0f;
        y2.eo = this.e;
        y2.ep = this.f;
        y2.eq = 0.0f;
        return y2;
    }
}
