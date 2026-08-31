/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Rect;
import android.graphics.Paint;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.DecorUnit;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.io.IOException;

public class TreeDecoration
extends UnitInstance {  // 02b al/ai extends v extends am
    static Texture[] a = new Texture[3];
    static Texture b = null;
    Texture c;
    int d;
    int e;
    int f = 0;
    float g;
    boolean h;
    float i;
    int j = 0;
    int k = 0;
    float l = 1.0f;
    boolean m = false;

    public static void b() {
        GlobalState l2 = GlobalState.B();
        a[0] = l2.bO.a(R$drawable.palm_tree);
        a[1] = l2.bO.a(R$drawable.trees);
        a[2] = l2.bO.a(R$drawable.trees_snow);
        b = l2.bO.a(R$drawable.palm_leaves);
    }


    /* 覆写链 super.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.d);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h);
        as2.a(this.i);
        as2.c(2);
        as2.a(this.l);
        as2.a(this.e);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.d = k2.readInt();
        this.f = k2.readInt();
        this.g = k2.readFloat();
        this.h = k2.readBoolean();
        this.i = k2.readFloat();
        byte by = k2.d();
        this.l = by >= 1 ? k2.readFloat() : 1.0f;
        this.e = by >= 2 ? k2.readInt() : 0;
        this.n(this.d, this.e);
        super.a(k2);
        if (this.isDead) {
            this.m = false;
        }
    }

    public Texture d() {
        return this.c;
    }


    public boolean e() {
        this.k();
        return true;
    }

    public TreeDecoration(boolean bl) {
        super(bl);
        this.n(1, -1);
        this.cj = 3.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 100.0f;
        this.cg = -90.0f;
        this.S(3);
    }


    public void a_(String string) {
        int n2;
        int n3 = -1;
        String[] stringArray = string.split("\\.");
        if (stringArray.length != 0 && stringArray.length != 1) {
            if (stringArray.length == 2) {
                string = stringArray[0];
                try {
                    n3 = Integer.parseInt(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    throw new RuntimeException("Tree sub type format error:" + stringArray[1]);
                }
            } else {
                throw new RuntimeException("Tree sub unknown format with parts:" + stringArray.length);
            }
        }
        try {
            n2 = Integer.parseInt(string);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Tree type format error:" + string);
        }
        this.n(n2, n3);
    }

    public void n(int n2, int n3) {
        this.d = n2;
        this.e = n3;
        if (this.d == 0) {
            this.T(27);
            this.U(41);
            this.j = 1;
            this.k = 1;
            this.c = a[0];
        } else if (this.d == 1 || this.d == 2) {
            if (n3 == -1) {
                n3 = GameUtils.a(0, 4, (int)this.eh);
            }
            if (n3 < 0 || n3 > 4) {
                throw new RuntimeException("Tree subType out of range:" + n3);
            }
            this.T(25);
            this.U(30);
            this.c = this.d == 1 ? a[1] : a[2];
            this.j = 0;
            this.k = 30 * n3;
            this.l = n3 == 0 ? GameUtils.a(1.0f, 1.2f, (int)this.eh + 1) : GameUtils.a(1.0f, 2.0f, (int)this.eh + 1);
            this.m = true;
        } else {
            throw new RuntimeException("Tree type:" + this.d + " is not supported");
        }
    }


    public void a(float f2) {
        if (this.d == 0) {
            if (this.h) {
                if (this.f < 4) {
                    this.g += f2;
                    if (this.g > 5.0f) {
                        this.g = 0.0f;
                        ++this.f;
                    }
                }
            } else if (this.i != 0.0f) {
                this.i = GameUtils.a(this.i, 0.1f * f2);
                this.f = 2;
            } else if (this.f > 1) {
                this.f = 1;
            }
        }
    }


    public Rect a_(boolean bl) {
        int n2 = this.j;
        int n3 = this.k;
        dC.a(n2 += this.f * (this.es + 1), n3, n2 + this.es, n3 + this.et);
        return dC;
    }


    public boolean c(float f2) {
        Texture e2 = this.d();
        GlobalState l2 = GlobalState.B();
        if ((double)l2.cX < 0.15) {
            return false;
        }
        du.a(this.cF());
        du.a(0.0f, (int)(-this.eq));
        float f3 = du.d();
        float f4 = du.e();
        dv.a(this.a_(false));
        TextureManagerInterface y2 = l2.bO;
        y2.k();
        if (this.l != 1.0f) {
            y2.a(this.l, this.l, f3, f4);
        }
        if (this.m) {
            dv.a(this.es, 0);
            l2.bO.a(e2, dv, du, (Paint)null);
            dv.a(-this.es, 0);
        }
        y2.a(this.d(false), f3, f4);
        y2.a(e2, dv, du, (Paint)null);
        y2.l();
        return true;
    }


    public boolean isOnScreen() {
        return false;
    }


    public MovementTypeEnum h() {
        return MovementTypeEnum.a;
    }


    public boolean i() {
        return false;
    }


    public boolean Q() {
        return false;
    }


    public boolean aj() {
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

    public UnitRegistry f() {
        return UnitRegistry.A;
    }


    public boolean a(UnitInstance am2, float f2) {
        if (!this.h) {
            if (this.i == 0.0f) {
                // empty if block
            }
            this.hp -= am2.getBuildDuration() / 3000.0f * this.maxHp * 0.06f * f2;
            this.i = 1.0f;
            this.dp = 1000.0f;
            if (this.hp <= 0.0f) {
                float f3;
                this.cg = f3 = com.corrodinggames.rts.gameFramework.GameUtils.d(this.eo, this.ep, am2.eo, am2.ep) + 180.0f;
                this.k();
            }
            if (!this.h) {
                return false;
            }
        }
        return true;
    }

    public void k() {
        if (!this.h) {
            GlobalState l2 = GlobalState.B();
            this.f = 2;
            this.g = 0.0f;
            this.S(0);
            this.bT = false;
            this.isDead = true;
            this.bW = l2.by;
            this.h = true;
            this.m = false;
            for (int j = 0; j < 1; ++j) {
                l2.bR.a();
                HUDElement e2 = l2.bR.a(this.eo + GameUtils.c(-12.0f, 12.0f), this.ep + GameUtils.c(-12.0f, 12.0f), this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.c);
                if (e2 == null) continue;
                e2.aq = 9;
                e2.ap = GameUtils.a(4, 5);
                e2.Y = GameUtils.c(-180.0f, 180.0f);
                e2.an = true;
                e2.K = 5.0f + GameUtils.c(0.0f, 3.0f);
                e2.P = GameUtils.c(-0.05f, 0.05f) + GameUtils.cosFast(this.cg) * 0.4f;
                e2.Q = GameUtils.c(-0.05f, 0.05f) + GameUtils.sinFast(this.cg) * 0.4f;
                e2.v = true;
                e2.w = 0.2f;
                e2.G = 0.4f * this.l;
                e2.F = 0.4f * this.l;
                e2.W = e2.V = (float)(90 + GameUtils.a(0, 40));
                e2.r = true;
                e2.ar = (short)2;
            }
            float f2 = this.eo + GameUtils.cosFast(this.cg) * (float)(this.et - 5);
            float f3 = this.ep + GameUtils.sinFast(this.cg) * (float)(this.et - 5);
            boolean bl = true;
            for (int j = 0; j < 1; ++j) {
                int n2 = 17;
                l2.bR.a();
                HUDElement e3 = l2.bR.a(f2 + GameUtils.c((float)(-n2), (float)n2), f3 + GameUtils.c((float)(-n2), (float)n2), this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.c);
                if (e3 == null) continue;
                e3.aq = 9;
                e3.ap = GameUtils.a(4, 5);
                if (bl) {
                    bl = false;
                    e3.ap = 3;
                }
                e3.Y = GameUtils.c(-180.0f, 180.0f);
                e3.an = true;
                if (e3.ap == 3) {
                    e3.P = GameUtils.c(-0.05f, 0.05f);
                    e3.Q = GameUtils.c(-0.05f, 0.05f);
                    e3.G = 1.5f * this.l;
                    e3.F = 2.2f * this.l;
                    e3.V = 90 + GameUtils.a(0, 40);
                    e3.ar = (short)2;
                } else {
                    e3.P = GameUtils.c(-0.05f, 0.05f);
                    e3.Q = GameUtils.c(-0.05f, 0.0f);
                    e3.G = 1.3f;
                    e3.F = 1.3f;
                    e3.V = 60 + GameUtils.a(0, 40);
                    e3.ar = 1;
                }
                e3.W = e3.V;
                e3.r = true;
            }
            if (this.d == 1 || this.d == 2) {
                this.eo += GameUtils.cosFast(this.cg) * (float)(this.et / 2 - 3);
                this.ep += GameUtils.sinFast(this.cg) * (float)(this.et / 2 - 3);
            }
        }
    }


    public void n() {
        super.n();
        this.cg = GameUtils.a(this.ep * 5.0f + this.eo * 3.0f, false);
        if (this.d == 0) {
            this.f = (int)(this.ep * 5.0f + this.eo * 3.0f) % 1;
        }
        if (this.d == 1) {
            // empty if block
        }
    }


    public float x() {
        return -1.0f;
    }


    public boolean l() {
        return false;
    }


    public float a(UnitInstance am2, float f2, MovementController f3) {
        boolean bl = this.isDead;
        float f4 = super.a(am2, f2, f3);
        if (!bl && this.isDead && f3 != null) {
            float f5;
            this.cg = f5 = com.corrodinggames.rts.gameFramework.GameUtils.d(this.eo, this.ep, f3.eo, f3.ep) + 180.0f;
        }
        return f4;
    }


    public boolean q() {
        return true;
    }


    public boolean t() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.f();
    }
}
