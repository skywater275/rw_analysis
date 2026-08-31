/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.game.units.actions.StopAction;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.ProjectileType;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.IOException;

public strictfp class Projectile
extends GameObject {
    int a;
    int b;
    int c = 50;
    int d = 40;
    ProjectileType e;
    int f;
    int g = -1;
    static final Rect h = new Rect();
    static final Rect i = new Rect();
    static final Paint j = PathfindingUtils.b();
    static Texture k = null;
    static Texture l = null;
    static Texture m = null;
    static final RectF n = new RectF();

    public static void b() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        k = l2.bO.a(R$drawable.scorch_mark, true);
        Projectile.k.m = true;
        l = l2.bO.a(R$drawable.scorch_mark_nuke, true);
        com.corrodinggames.rts.game.l.l.m = true;
        m = l2.bO.a(R$drawable.blood_mark, true);
        Projectile.m.m = true;
    }

    public Projectile() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.S(-1);
        this.f = l2.by;
    }

    public static void a(float f2, float f3) {
        Projectile.a(f2, f3, ProjectileType.normal);
    }

    public static void a(float f2, float f3, ProjectileType m2) {
        if (!Projectile.b(f2, f3, m2)) {
            return;
        }
        Projectile l2 = new Projectile();
        l2.eo = f2;
        l2.ep = f3;
        if (m2 == ProjectileType.normal) {
            l2.a = 0;
            l2.b = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) l2, 0, 3, 0);
        } else {
            l2.a = 2;
        }
        if (l2.a == 2) {
            l2.c = Projectile.l.m();
            l2.d = Projectile.l.l();
        }
        l2.e = m2;
        l2.d();
    }

    public static void a(com.corrodinggames.rts.game.units.UnitType y2, int n2) {
        if (!y2.cJ()) {
            ProjectileType m2;
            ProjectileType m3 = m2 = n2 == 2 ? ProjectileType.nuke : ProjectileType.normal;
            if (!Projectile.b(y2.eo, y2.ep, m2)) {
                return;
            }
            Projectile l2 = new Projectile();
            l2.a = n2;
            if (l2.a == 2) {
                l2.c = Projectile.l.m();
                l2.d = Projectile.l.l();
            }
            l2.eo = y2.eo;
            l2.ep = y2.ep;
            l2.e = m2;
            l2.b = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) l2, 0, 3, 0);
            l2.d();
        }
    }

    public static boolean b(float f2, float f3, ProjectileType m2) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 5;
        int n5 = 25;
        if (m2 == ProjectileType.nuke) {
            n5 = 45;
        }
        GameObject[] wArray = com.corrodinggames.rts.gameFramework.GameObject.er.a();
        int n6 = com.corrodinggames.rts.gameFramework.GameObject.er.size();
        for (int i2 = 0; i2 < n6; ++i2) {
            com.corrodinggames.rts.gameFramework.GameObject w2 = wArray[i2];  // 02 铁证: l.java w var10 = er.a()[i]
            if (!(w2 instanceof Projectile)) continue;
            Projectile l2 = (Projectile) w2;
            if (!(GameUtils.c(l2.eo - f2) < (float)n5) || !(GameUtils.c(l2.ep - f3) < (float)n5) || l2.e != m2) continue;
            ++n2;
            if (!(GameUtils.c(l2.eo - f2) < (float)n4) || !(GameUtils.c(l2.ep - f3) < (float)n4)) continue;
            ++n3;
        }
        if (n2 >= 3) {
            return false;
        }
        if (n3 >= 1) {
            return false;
        }
        return true;
    }


    public boolean a(GlobalState l2) {
        return false;
    }


    public boolean f(float f2) {
        return false;
    }


    public boolean c(float f2) {
        return true;
    }

    public RectF c() {
        Projectile.n.a = this.eo - (float)this.c * 0.5f;
        Projectile.n.c = this.eo + (float)this.c * 0.5f;
        Projectile.n.b = this.ep - (float)this.d * 0.5f;
        Projectile.n.d = this.ep + (float)this.d * 0.5f;
        return n;
    }

    public void a(com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2, int n2, int n3, float f2) {
        int n4 = this.b * this.c;
        int n5 = 0;
        Texture e2 = null;
        int n6 = this.c;
        int n7 = this.d;
        if (this.a == 0) {
            e2 = k;
        } else if (this.a == 1) {
            e2 = m;
        } else if (this.a == 2) {
            e2 = l;
        }
        Rect rect = h;
        Rect rect2 = i;
        rect2.a = n4;
        rect2.b = n5;
        rect2.c = n4 + n6;
        rect2.d = n5 + n7;
        int n8 = (int)this.eo;
        int n9 = (int)this.ep;
        int n10 = n6 >> 1;
        int n11 = n7 >> 1;
        float f3 = (n8 -= n2) - n10;
        float f4 = (n9 -= n3) - n11;
        float f5 = n8 + n10;
        float f6 = n9 + n11;
        rect.a = (int)(f3 * f2);
        rect.b = (int)(f4 * f2);
        rect.c = (int)(f5 * f2);
        rect.d = (int)(f6 * f2);
        y2.a(e2, rect2, rect, j);
    }

    private void d() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bL.a(this);
    }


    public void e(float f2) {
    }


    public void a(float f2, boolean bl) {
    }


    public void d(float f2) {
    }


    public void a(float f2) {
    }


    @Override
    /* 覆写 BaseGameObject.serializeToStream (已 throws IOException) */
    public void serializeToStream(OutputNetStream as2) throws IOException {
        as2.a(this.eo);
        as2.a(this.ep);
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.eo = k2.readFloat();
        this.ep = k2.readFloat();
        this.a = k2.readInt();
        this.b = k2.readInt();
        this.c = k2.readInt();
        this.d = k2.readInt();
        if (k2.b() >= 87) {
            this.e = (ProjectileType) k2.b(ProjectileType.class);  // v19.113g: m.class 旧类名残留 (字段 m 是 HumanPlayer!)
            this.f = k2.readInt();
        } else {
            ProjectileType m2 = this.e = this.a == 2 ? ProjectileType.nuke : ProjectileType.normal;  // v19.113g: 枚举改名同步 (a=2→nuke 铁证)
            if (this.a == 2) {
                this.c = Projectile.l.m();
                this.d = Projectile.l.l();
            }
        }
        super.a(k2);
    }
}
