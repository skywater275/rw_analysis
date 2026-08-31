/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.io.IOException;

public class PowerGeneratorUnit
extends MobileBuilderBase {
    int r;  // 02 d.d (BuildSlot) 闂佽瀛╃粙鎺椼€冮崱娑辨晩? 闁诲海鍋ｉ崐鏍ь渻娴犲鐒垫い鎺戝€告牎闁撅箑娴风槐鎺戔槈濮楀棙楔婵?1-3
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    static Texture[] e = new Texture[10];
    static Texture[] f = new Texture[10];
    static Texture g = null;
    float h;
    float i = 0.0f;
    int j = 0;
    static GameAction k = new PowerGeneratorUnit$1(102);
    static GameAction l = new PowerGeneratorUnit$2(103);
    static ArrayList t = new ArrayList();


    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.h);
        as2.a(this.r == 2);
        super.a(as2);  // 02b d/h L33: super.a(var1)
    }




    public void R(int n2) {
        this.a(n2);
    }

    public UnitRegistry getar() {
        return com.corrodinggames.rts.game.units.UnitRegistry.J;
    }

    public static void initTextures() {  // 02b h.K() L54-63: 静态纹理加载 (loadTextures 与 UnitType 冲突)
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        a = l2.bO.a(R$drawable.power);
        b = l2.bO.a(R$drawable.power_t2);
        c = l2.bO.a(R$drawable.power_t3);
        d = com.corrodinggames.rts.game.PlayerState.a(a);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
        f = com.corrodinggames.rts.game.PlayerState.a(c);
        g = l2.bO.a(R$drawable.power_dead);  // 02b d/h L62: g = ... (generatorPowerOutput 婵炴垶鎸搁幖顐ｅ緞閻旂儤鍠?
    }


    public boolean L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = g;  // 02b d/h L68: this.M = g
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.p, 0.8f, this.eo, this.ep);
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.c(this.eo, this.ep, this.eq, -1127220);
        if (e2 != null) {
            e2.G = 0.15f;
            e2.F = 1.0f;
            e2.ar = (short)2;
            e2.W = e2.V = 35.0f;
            e2.U = 0.0f;
            e2.x = -14492382;
        }
        this.bo();
        return true;
    }


    public Texture d() {  // 02b h.d() L88-103: 閸楁洑缍呯拹鏉戞禈 (閺冄嗩嚖閸?gete)
        if (this.isDead) {
            return g;
        }
        if (this.player == null) {
            return d[d.length - 1];
        }
        if (this.r == 1) {
            return d[this.player.R()];
        }
        if (this.r == 2) {
            return e[this.player.R()];  // 02b d/h L96: r==2 -> e
        }
        if (this.r == 3) {
            return f[this.player.R()];  // 02b d/h L98: r==3 -> f
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Unknown tech level:" + this.r);
        return d[this.player.R()];
    }


    public Texture k() {  // 02b d/h L105-107: k() (UnitType 闂佺娉涢埀顒傚枙閺変粙鏌￠崒婵愭綈缁?
        return null;
    }

    public Texture gete2() {
        return null;
    }

    public PowerGeneratorUnit(boolean bl) {
        super(bl);
        this.M = a;
        this.a(this.M, 3);
        this.ck = this.cj = 25.0f;
        this.hp = this.maxHp = 800.0f;
        this.n.a(-1, -1, 1, 1);
        this.o.a(this.n);
    }


    public void a(float f2) {
        super.a(f2);  // 02b d/h L122: super.a(var1)
        if (!this.bT() || this.isDead) {
            return;
        }
        this.i = com.corrodinggames.rts.gameFramework.GameUtils.a(this.i, f2);
        if (this.i == 0.0f) {
            this.i = 17.0f;
            ++this.j;
            if (this.j > 5) {
                this.j = 0;
            }
            this.s = this.j <= 2 ? this.j : 5 - this.j;
        }
        this.h += f2;
        if (this.h > com.corrodinggames.rts.game.PlayerState.ap - 0.1f) {
            this.h -= com.corrodinggames.rts.game.PlayerState.ap;
            this.player.b(this.cy() * (com.corrodinggames.rts.game.PlayerState.ap / com.corrodinggames.rts.game.PlayerState.ao));
        }
    }


    public float cy() {
        if (this.r == 1) {
            return 2.0f;
        }
        if (this.r == 2) {
            return 7.0f;
        }
        return 14.0f;
    }


    public void a(BuilderUnit j2) {
        if (j2.j.equals(k.N())) {
            this.a(2);
            this.W();
        }
        if (j2.j.equals(l.N())) {
            this.a(3);
            this.W();
        }
    }


    public ActionId cm() {
        if (this.r == 1) {
            return k.N();
        }
        if (this.r == 2) {
            return l.N();
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public int V() {
        return this.r;
    }


    public void a(int n2) {
        com.corrodinggames.rts.game.PlayerState.b((UnitInstance) this);
        if (this.r > n2) {
            this.r = 1;
            this.maxHp = 800.0f;
            if (this.hp > this.maxHp) {
                this.hp = this.maxHp;
            }
        }
        if (this.r < 2 && n2 >= 2) {
            this.maxHp += 500.0f;
            this.hp += 500.0f;
        }
        if (this.r < 3 && n2 >= 3) {
            this.maxHp += 1300.0f;
            this.hp += 1300.0f;
        }
        this.r = n2;
        com.corrodinggames.rts.game.PlayerState.c(this);
        this.S();
    }


    public ArrayList N() {
        return t;
    }


    public float getfloat2() {
        return super.db() - 8.0f;  // 02b d/h L203: super.db() (getfloat 婵炴垶鎸搁幖顐ｅ緞閻旂儤鍠?
    }


    public int getint2() {
        return 12;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.getar();
    }

    static {
        t.add(k);
        t.add(l);
    }


    // v19.112d 闂佽崵鍋炵粙蹇涘磿閼艰翰浜?(02b units/d/h.java)
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        this.h = var1.readFloat();  // 02b d/h L37: var1.g() = readFloat
        boolean var2 = var1.e();
        if(var1.b() < 51 && var2) {
           this.a(2);
        }

        super.a(var1);
   }
}




