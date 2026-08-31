/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import java.util.Iterator;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.MovableUnit;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.FontRenderer;
import java.io.IOException;

public strictfp class WaterUnit
extends MovableUnit {
    public float buoyancyForce = 2000.0f;
    public float waterSurfaceLevel = 0.0f;
    public float waveAmplitude = 0.0f;
    public float maxWaterDepth = 2000.0f;
    public float waterDrag;
    public float angularDrag;
    public boolean floatOnSurface = true;
    public float submersionDepth = 1.0f;
    public boolean canSubmerge;
    public float sonarRange;
    static Paint k = new Paint();
    static Paint l;
    static Paint m;
    static Paint n;
    static Paint o;
    static Paint p;
    boolean q;
    static final PointF r;

    @Override
    /* 覆写链 super.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.c(0);
        as2.a(this.buoyancyForce);
        as2.a(this.waterSurfaceLevel);
        as2.a(this.waveAmplitude);
        as2.a(this.maxWaterDepth);
        as2.a(this.waterDrag);
        as2.a(this.angularDrag);
        as2.a(this.floatOnSurface);
        as2.a(this.submersionDepth);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        k2.d();
        this.buoyancyForce = k2.readFloat();
        this.waterSurfaceLevel = k2.readFloat();
        this.waveAmplitude = k2.readFloat();
        this.maxWaterDepth = k2.readFloat();
        this.waterDrag = k2.readFloat();
        this.angularDrag = k2.readFloat();
        this.floatOnSurface = (boolean) k2.readBoolean();
        this.submersionDepth = k2.readFloat();
        super.a(k2);
        if (!this.isDead) {
            com.corrodinggames.rts.gameFramework.GlobalState.B().bW.a(this);
        }
    }

    public UnitRegistry b() {
        if (this.q) {
            return UnitRegistry.X;
        }
        return UnitRegistry.W;
    }

    public static void d_() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    }

    public WaterUnit(boolean bl) {
        super(bl);
    }

    public WaterUnit f() {
        Iterator var1 = UnitInstance.getUnitPool().iterator();
        while (var1.hasNext()) {
            UnitInstance am2 = (UnitInstance)var1.next();
            if (am2 instanceof WaterUnit && !am2.isDead && am2 != this) {
                WaterUnit f2 = (WaterUnit) am2;
                if (f2.q == this.q) {
                    return f2;
                }
            }
        }
        return null;
    }

    @Override
    public void a(float f2) {
        float f3;
        float f4;
        float f5;
        Object object;
        super.a(f2);
        if (this.isDead) {
            return;
        }
        if (this.floatOnSurface) {
            this.floatOnSurface = false;
            object = this.f();
            if (object != null) {
                ((WaterUnit) object).waterDrag = this.eo;
                ((WaterUnit) object).angularDrag = this.ep;
                ((WaterUnit) object).maxWaterDepth = this.maxWaterDepth;
                this.ci();
            } else {
                this.waterDrag = this.eo;
                this.angularDrag = this.ep;
                if (!this.q) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("DamagingBorder created " + this.waterDrag + "," + this.angularDrag + " size:" + this.maxWaterDepth);
                }
                com.corrodinggames.rts.gameFramework.GlobalState.B().bW.a(this);
            }
        }
        if (this.q) {
            this.buoyancyForce = this.maxWaterDepth;
            this.eo = this.waterDrag;
            this.ep = this.angularDrag;
        } else if (this.buoyancyForce > this.maxWaterDepth) {
            this.waterSurfaceLevel += 2.5E-4f * f2;
            this.buoyancyForce -= this.waterSurfaceLevel;
            this.canSubmerge = true;
            float f6 = GameUtils.b(this.eo, this.ep, this.waterDrag, this.angularDrag);
            f5 = com.corrodinggames.rts.gameFramework.GameUtils.d(this.eo, this.ep, this.waterDrag, this.angularDrag);
            if (f6 > 1.0f) {
                f4 = this.waterSurfaceLevel;
                if (f4 > f6 * f2) {
                    f4 = f6 * f2;
                }
                this.eo += f4 * GameUtils.cosFast(f5) * f2;
                this.ep += f4 * GameUtils.sinFast(f5) * f2;
            }
        } else {
            this.canSubmerge = false;
            this.eo = (float)((double)this.eo + (double)(this.waterDrag - this.eo) * 0.003 * (double)f2);
            this.ep = (float)((double)this.ep + (double)(this.angularDrag - this.ep) * 0.003 * (double)f2);
        }
        if (this.buoyancyForce < this.maxWaterDepth) {
            this.buoyancyForce = this.maxWaterDepth;
            this.waterSurfaceLevel = 0.0f;
        }
        if (this.maxWaterDepth < 0.0f) {
            this.ci();
            return;
        }
        this.waveAmplitude -= f2;
        if (!this.isDead && this.waveAmplitude <= 0.0f && !this.q) {
            this.waveAmplitude = 2.0f;
            float f7 = this.buoyancyForce * GameUtils.cosFast(45.0f);
            f5 = this.eo - f7;
            f4 = this.eo + f7;
            f3 = this.ep - f7;
            float f8 = this.ep + f7;
            float f9 = this.buoyancyForce * this.buoyancyForce;
            Iterator var8 = UnitInstance.getUnitPool().iterator();
            while (var8.hasNext()) {
                UnitInstance am9 = (UnitInstance)var8.next();
                if (am9.eo > f5 && am9.eo < f4 && am9.ep > f3 && am9.ep < f8) {
                    continue;
                }
                float f10 = GameUtils.a(this.eo, this.ep, am9.eo, am9.ep);
                if (f10 < f9 || am9.isDead || am9 instanceof TreeDecoration || am9.u() || am9.cN != null) {
                    continue;
                }
                float f11 = 0.5f + am9.cu * 0.002f + am9.cv * 0.001f;
                am9.a(this, f11 *= this.submersionDepth, null);
            }
        }
        if (!this.q) {
            object = com.corrodinggames.rts.gameFramework.GlobalState.B();
            this.sonarRange += f2;
            if (this.sonarRange > 3.0f) {
                int n2;
                this.sonarRange = 0.0f;
                int n3 = ((GlobalState) object).cu + GameUtils.a(0, (int)((GlobalState) object).cA);
                f3 = GameUtils.a(this.eo, this.ep, (float)n3, (float)(n2 = ((GlobalState) object).cv + GameUtils.a(0, (int)((GlobalState) object).cB)));
                if (f3 > (this.buoyancyForce + 30.0f) * (this.buoyancyForce + 30.0f)) {
                    ((GlobalState) object).bL.a((float)n3, (float)n2);
                    int n4 = ((GlobalState) object).bL.scrollPixelX;
                    int n5 = ((GlobalState) object).bL.scrollPixelY;
                    ((GlobalState) object).bL.a(n4, n5);
                    com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = ((GlobalState) object).bR.b(((GlobalState) object).bL.scrollPixelX + 10, ((GlobalState) object).bL.scrollPixelY - 10 + 10, 0.0f, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, true, DrawLayer.a);
                    if (e2 != null) {
                        e2.aq = 19;
                        e2.Y = GameUtils.c(-180.0f, 180.0f);
                        e2.r = true;
                        e2.ar = 1;
                        e2.E = 0.7f;
                        e2.W = e2.V = 30.0f;
                        e2.G = 0.2f;
                        e2.F = 1.2f;
                        e2.x = Color.a(255, 173, 12, 12);
                    }
                }
            }
        }
    }


    public int s() {
        return 0;
    }


    public boolean t() {
        return true;
    }


    public boolean u() {
        return true;
    }


    public boolean a(com.corrodinggames.rts.gameFramework.GlobalState l2) {
        return true;
    }

    @Override
    public void a(float f2, boolean bl) {
        WaterUnit f3;
        Paint paint;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f4 = this.eo - l2.cw;
        float f5 = this.ep - l2.cx;
        Paint paint2 = paint = this.canSubmerge ? m : k;
        if (this.q) {
            paint = o;
        }
        float f6 = this.buoyancyForce;
        if (this.floatOnSurface && (f3 = this.f()) != null) {
            f6 = f3.maxWaterDepth - 300.0f;
        }
        l2.bO.a(f4, f5, f6, paint);
    }


    public boolean a(int n2, int n3) {
        Paint paint;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bO.i();
        l2.bO.a(l2.bW.boundsRect);  // 02b f/o L45: w (v19.133f4 语义名修正)
        float f2 = l2.bW.b(this.buoyancyForce);
        Paint paint2 = paint = this.canSubmerge ? n : l;
        if (this.q) {
            paint = p;
        }
        com.corrodinggames.rts.gameFramework.rendering.FontRenderer.a(l2.bO, n2, n3, f2, paint);
        l2.bO.j();
        return true;
    }


    public void a(int n2) {
        this.buoyancyForce = n2 * 100;
        this.maxWaterDepth = n2 * 100;
    }

    public boolean a(float f2, float f3) {
        float f4 = this.maxWaterDepth * this.maxWaterDepth;
        float f5 = GameUtils.a(this.waterDrag, this.angularDrag, f2, f3);
        return f5 >= f4;
    }

    public PointF a(float f2, float f3, float f4) {
        if (f4 > this.maxWaterDepth) {
            f4 = this.maxWaterDepth;
        }
        float f5 = com.corrodinggames.rts.gameFramework.GameUtils.d(this.eo, this.ep, f2, f3);
        float f6 = this.maxWaterDepth - f4;
        float f7 = this.eo + GameUtils.cosFast(f5) * f6;
        float f8 = this.ep + GameUtils.sinFast(f5) * f6;
        r.a = f7;
        r.b = f8;
        return r;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }

    static {
        k.a(10.0f);
        k.b(Color.a(100, 160, 0, 0));
        k.a(android.graphics.Paint$Style.b);
        m = new Paint();
        m.a(k);
        m.b(Color.a(180, 160, 0, 0));
        l = new Paint();
        l.a(2.0f);
        l.b(Color.a(100, 160, 0, 0));
        l.a(android.graphics.Paint$Style.b);
        n = new Paint();
        n.a(l);
        n.b(Color.a(180, 160, 0, 0));
        o = new Paint();
        o.a(2.0f);
        o.b(Color.a(50, 255, 255, 255));
        o.a(android.graphics.Paint$Style.b);
        p = new Paint(o);
        r = new PointF();
    }
}
