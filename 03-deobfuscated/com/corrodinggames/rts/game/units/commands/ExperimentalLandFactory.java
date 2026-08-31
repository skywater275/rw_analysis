/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class ExperimentalLandFactory
extends MobileBuilderBase {
    float a;
    int b = 1;
    float c = 0.0f;
    int d = 0;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] e = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] f = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] g = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture h = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture i = null;
    Rect j = new Rect();
    Rect k = new Rect();
    public static int l = 0;
    static GameAction t = new ExperimentalLandFactory$1(102);
    static GameAction u = new ExperimentalLandFactory$2(103);
    static ArrayList v = new ArrayList();
    static ArrayList w;
    static ArrayList x;

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.a);
        as2.a(this.b > 1);
        as2.a(this.b);
        super.a(as2);
    }



    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.a;
    }


    public boolean c(BuilderUnit n2) {  // 02b g 鏃犳鏂规硶, super.c(BuilderUnit) 鍖归厤
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bL.a(this.eo, this.ep);
        com.corrodinggames.rts.game.map.MapLayer g2 = l2.bL.e(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
        if (g2 == null || !g2.isTileLayer) {  // 02b b/g.i
            return false;
        }
        return super.c(n2);
    }

    public static void K() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.a(R$drawable.extractor);
        com.corrodinggames.rts.gameFramework.rendering.Texture e3 = l2.bO.a(R$drawable.extractor_t2);
        com.corrodinggames.rts.gameFramework.rendering.Texture e4 = l2.bO.a(R$drawable.extractor_t3);
        i = l2.bO.a(R$drawable.extractor_dead);
        e = com.corrodinggames.rts.game.PlayerState.a(e2);
        f = com.corrodinggames.rts.game.PlayerState.a(e3);
        g = com.corrodinggames.rts.game.PlayerState.a(e4);
        e2.n();
        e3.n();
        e4.n();
        h = l2.bO.a(R$drawable.extractor_back);
    }


    public boolean L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = i;
        this.m = null;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.p, 0.8f, this.eo, this.ep);
        com.corrodinggames.rts.gameFramework.effects.DrawEffect f2 = com.corrodinggames.rts.gameFramework.effects.DrawEffect.a(this.eo, this.ep);
        f2.j = -6684775;
        com.corrodinggames.rts.gameFramework.effects.DrawEffect f3 = com.corrodinggames.rts.gameFramework.effects.DrawEffect.b(this.eo, this.ep);
        f3.a = 500.0f;
        f3.j = -6684775;
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.c(this.eo, this.ep, this.eq, -1127220);
        if (e2 != null) {
            e2.G = 0.15f;
            e2.F = 1.0f;
            e2.ar = (short)2;
            e2.W = e2.V = 35.0f;
            e2.U = 0.0f;
            e2.x = -13378253;
        }
        this.bo();
        return false;
    }


    public int bp() {
        return 16;
    }


    public void S() {
        super.S();
        this.m = this.isDead ? null : h;
    }


    public boolean ds() {
        return true;
    }


    @Override
    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {  // 02b g.java L134-136: d() 鍗曚綅璐村浘 (鏃ц鍚?k())
        if (this.isDead) {
            return i;
        }
        if (this.player == null) {
            return e[e.length - 1];  // 02b: e[e.length-1]
        }
        if (this.b == 3) {
            return g[this.player.R()];  // 02b: g[bX.R()]
        }
        if (this.b == 2) {
            return f[this.player.R()];  // 02b d/g L136: b==2 -> f[bX.R()] (删掉误插的 return w)
        }
        return e[this.player.R()];  // 02b: e[bX.R()]
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return null;
    }

    public ExperimentalLandFactory(boolean bl) {
        super(bl);
        this.M = e[9];  // 02b g L144: e[9]
        this.T(37);
        this.U(56);
        this.ck = this.cj = 18.0f;
        this.hp = this.maxHp = 800.0f;
        this.n.a(0, -1, 0, 0);
        this.o.a(this.n);
        this.S();
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        this.c = com.corrodinggames.rts.gameFramework.GameUtils.a(this.c, f2 * (float)this.b);
        if (this.c == 0.0f) {
            this.c = 17.0f;
            ++this.d;
            if (this.d > 7) {
                this.d = 0;
            }
            this.s = this.d <= 3 ? this.d : 7 - this.d;
        }
        this.a += f2;
        if (this.a > com.corrodinggames.rts.game.PlayerState.ap - 0.1f) {
            this.a -= com.corrodinggames.rts.game.PlayerState.ap;
            this.player.b(this.cy() * (com.corrodinggames.rts.game.PlayerState.ap / com.corrodinggames.rts.game.PlayerState.ao));
        }
    }


    public float cy() {
        if (this.b == 3) {
            return 18.0f;
        }
        if (this.b == 2) {
            return 12.0f;
        }
        return 8.0f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        throw new RuntimeException("Unit cannot shoot");
    }

    @Override
    public float m() {
        return 0.0f;
    }

    @Override
    public float b(int n2) {
        return 0.0f;
    }

    @Override
    public float c(int n2) {
        return 0.0f;
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(t.N())) {
            this.a(2);
            this.W();
        }
        if (j2.j.equals(u.N())) {
            this.a(3);
            this.W();
        }
    }


    public ActionId cm() {
        if (this.b == 1) {
            return t.N();
        }
        if (this.b == 2) {
            return u.N();
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public int V() {
        return this.b;
    }


    public void a(int n2) {
        com.corrodinggames.rts.game.PlayerState.b((UnitInstance) this);
        if (this.b > n2) {
            this.b = 1;
            this.maxHp = 800.0f;
            if (this.hp > this.maxHp) {
                this.hp = this.maxHp;
            }
        }
        if (this.b < 2 && n2 >= 2) {
            this.maxHp += 200.0f;
            this.hp += 200.0f;
        }
        if (this.b < 3 && n2 >= 3) {
            this.maxHp += 1000.0f;
            this.hp += 1000.0f;
        }
        this.b = n2;
        com.corrodinggames.rts.game.PlayerState.c(this);
        this.S();
    }


    public ArrayList N() {
        if (this.b == 1) {
            return v;
        }
        if (this.b == 2) {
            return w;
        }
        return x;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }

    static {
        v.add(t);
        w = new ArrayList();  // 02b g L268
        w.add(u);
        x = new ArrayList();  // 02b g L270
    }


    // v19.112d 琛ユ彃 (02b units/d/g.java 璇绘柟娉?a(j.k))
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        this.a = var1.readFloat();  // 02b g L43: var1.g()
        int var2 = 1;
        boolean var3 = var1.e();
        if(var3) {
           var2 = 2;
        }

        if(var1.b() >= 31) {
           var2 = var1.f();
        }

        if(var2 != 1) {
           this.a(var2);
        }

        super.a(var1);
   }
}
