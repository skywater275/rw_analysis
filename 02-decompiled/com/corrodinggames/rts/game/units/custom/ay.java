/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.az;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.gameFramework.d.c;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.d.e;
import com.corrodinggames.rts.gameFramework.d.g;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.y;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ArrayList;

public class ay {
    public static final ay defaultEffectTemplate = new ay("default");
    public String name;
    private az builtInEffect = null;
    public g imageStrip;
    public boolean createWhenOffscreen;
    public boolean createWhenZoomedOut;
    public boolean createWhenOverLiquid;
    public boolean createWhenOverLand;
    public float spawnChance = 1.0f;
    z ifSpawnFailsEmitEffects;
    public float life = 200.0f;
    public float lifeRandom;
    public boolean showInFog;
    public float xOffsetRelative;
    public float yOffsetRelative;
    public float hOffset;
    public boolean alwayStartDirAtZero;
    public float pivotOffset;
    public float pivotOffsetRandom;
    public float dirOffset;
    public float xOffsetRelativeRandom;
    public float yOffsetRelativeRandom;
    public float hOffsetRandom;
    public float dirOffsetRandom;
    public float xOffsetAbsolute;
    public float yOffsetAbsolute;
    public float xOffsetAbsoluteRandom;
    public float yOffsetAbsoluteRandom;
    public float xSpeedRelative;
    public float ySpeedRelative;
    public float hSpeed;
    public float dirSpeed;
    public float xSpeedRelativeRandom;
    public float ySpeedRelativeRandom;
    public float hSpeedRandom;
    public float dirSpeedRandom;
    public float xSpeedAbsolute;
    public float ySpeedAbsolute;
    public float xSpeedAbsoluteRandom;
    public float ySpeedAbsoluteRandom;
    public h priority = h.c;
    public float scaleTo = 1.0f;
    public float scaleFrom = 1.0f;
    public float alpha = 1.0f;
    public int color = -1;
    public LightingColorFilter cachedLightingColorFilter;
    public float teamColorRatio = 0.0f;
    public boolean shadow;
    public short drawLayer = (short)2;
    public float fadeInTime;
    public boolean fadeOut;
    public float delayedStartTimer;
    public float delayedStartTimerRandom;
    public int frameIndex;
    public int frameIndexRandom;
    public int stripIndex;
    public boolean attachedToUnit;
    public boolean liveAfterAttachedDies;
    public boolean atmospheric;
    public boolean physics;
    public float physicsGravity = 1.0f;
    public int animateFrameStart;
    public int animateFrameEnd;
    public int animateFrameStartRandomAdd;
    public boolean animateFramePingPong;
    public boolean animateFrameLooping;
    public float animateFrameSpeed;
    public float animateFrameSpeedRandom;
    public z alsoEmitEffects;
    public z alsoEmitEffectsOnDeath;
    public z trailEffect;
    public float trailEffectRate;
    public bl alsoPlaySound;
    public static ArrayList fields;

    public ay(az az2) {
        this.builtInEffect = az2;
    }

    ay(String string) {
        this.name = string;
    }

    public e a(float f2, float f3, float f4, float f5, w w2, int n2, short s2) {
        float f6;
        float f7;
        float f8;
        float f9;
        e e2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.spawnChance < 1.0f && com.corrodinggames.rts.gameFramework.f.c(0.0f, 1.0f) > this.spawnChance) {
            if (n2 < 5 && this.ifSpawnFailsEmitEffects != null) {
                this.ifSpawnFailsEmitEffects.a(f2, f3, f4, f5, w2, n2 + 1, s2);
            }
            return null;
        }
        float f10 = f2;
        float f11 = f3;
        float f12 = f4;
        if (this.builtInEffect != null) {
            e e3;
            if (this.builtInEffect == az.a) {
                e3 = l2.bR.a(f10, f11, f12, f5);
            } else if (this.builtInEffect == az.b) {
                e3 = l2.bR.b(f10, f11, f12, f5, 0);
                if (e3 != null) {
                    e3.G = 0.75f;
                    e3.F = 0.75f;
                }
            } else if (this.builtInEffect == az.c) {
                e3 = l2.bR.b(f10, f11, f12, f5, 0);
            } else if (this.builtInEffect == az.d) {
                e3 = l2.bR.c(f10, f11, f12, f5, 0);
            } else if (this.builtInEffect == az.e) {
                e3 = l2.bR.d(f10, f11, f12, 0);
            } else if (this.builtInEffect == az.f) {
                l2.bR.a(f10, f11, f12);
                e3 = null;
            } else if (this.builtInEffect == az.g) {
                e3 = l2.bR.b(f10, f11, f12);
            } else if (this.builtInEffect == az.h) {
                com.corrodinggames.rts.gameFramework.d.f f13 = com.corrodinggames.rts.gameFramework.d.f.a(f10, f11);
                f13.j = -6684775;
                com.corrodinggames.rts.gameFramework.d.f f14 = com.corrodinggames.rts.gameFramework.d.f.b(f10, f11);
                f14.a = 500.0f;
                f14.j = -6684775;
                l2.bR.b(h.e);
                e e4 = l2.bR.c(f10, f11, f12, -1127220);
                if (e4 != null) {
                    e4.G = 0.15f;
                    e4.F = 1.0f;
                    e4.ar = (short)2;
                    e4.W = e4.V = 35.0f;
                    e4.U = 0.0f;
                    e4.x = -13378253;
                }
                e3 = null;
            } else {
                if (this.builtInEffect == az.i) {
                    return null;
                }
                throw new RuntimeException("Unhandled built-in type:" + (Object)((Object)this.builtInEffect));
            }
            if (e3 == null) {
                return null;
            }
            e3.ar = (short)2;
            if (w2 != null) {
                c.a(e3, w2);
            }
            return e3;
        }
        if (!this.createWhenZoomedOut && !l2.dc) {
            return null;
        }
        if (!this.createWhenOverLiquid && y.d(f10, f11)) {
            return null;
        }
        if (!this.createWhenOverLand && !y.d(f10, f11)) {
            return null;
        }
        if (this.createWhenOffscreen) {
            l2.bR.b();
        } else {
            l2.bR.a();
        }
        boolean bl2 = this.showInFog;
        boolean bl3 = false;
        if (!bl2 && this.attachedToUnit) {
            bl3 = true;
            bl2 = true;
        }
        if ((e2 = l2.bR.b(f10, f11, f12, d.a, bl2, this.priority)) == null) {
            return null;
        }
        e2.a = this;
        e2.A = (short)(s2 + 1);
        if (bl3 && !this.showInFog) {
            e2.e = false;
        }
        e2.V = this.life;
        e2.V += this.a(this.lifeRandom);
        e2.W = e2.V;
        e2.aq = this.stripIndex;
        if (this.imageStrip != null) {
            // empty if block
        }
        e2.ap = this.frameIndex;
        if (this.frameIndexRandom != 0) {
            e2.ap += com.corrodinggames.rts.gameFramework.f.a(-this.frameIndexRandom, this.frameIndexRandom);
            if (e2.ap < 0) {
                e2.ap = 0;
            }
        }
        f5 += this.pivotOffset;
        e2.Y = this.alwayStartDirAtZero ? 0.0f : (f5 += this.a(this.pivotOffsetRandom));
        e2.Y += this.dirOffset;
        e2.Y += this.a(this.dirOffsetRandom);
        if (this.xOffsetAbsoluteRandom != 0.0f || this.yOffsetAbsoluteRandom != 0.0f || this.xOffsetAbsolute != 0.0f || this.yOffsetAbsolute != 0.0f) {
            f9 = this.xOffsetAbsolute + this.a(this.xOffsetAbsoluteRandom);
            f8 = this.yOffsetAbsolute + this.a(this.yOffsetAbsoluteRandom);
            e2.I += f9;
            e2.J += f8;
        }
        if (this.xOffsetRelativeRandom != 0.0f || this.yOffsetRelativeRandom != 0.0f || this.xOffsetRelative != 0.0f || this.yOffsetRelative != 0.0f) {
            f9 = com.corrodinggames.rts.gameFramework.f.k(f5);
            f8 = com.corrodinggames.rts.gameFramework.f.j(f5);
            f7 = this.xOffsetRelative + this.a(this.xOffsetRelativeRandom);
            f6 = this.yOffsetRelative + this.a(this.yOffsetRelativeRandom);
            e2.I += f9 * f6 - f8 * f7;
            e2.J += f8 * f6 + f9 * f7;
        }
        e2.K += this.hOffset + this.a(-this.hOffsetRandom, this.hOffsetRandom);
        e2.an = true;
        e2.r = true;
        e2.ar = this.drawLayer;
        e2.G = this.scaleFrom;
        e2.F = this.scaleTo;
        e2.E = this.alpha;
        e2.x = this.color;
        e2.B = this.cachedLightingColorFilter;
        if (this.teamColorRatio != 0.0f && w2 != null) {
            am am2;
            n n3 = null;
            if (w2 instanceof am) {
                n3 = ((am)w2).bX;
            }
            if (w2 instanceof f && (am2 = ((f)w2).j) != null) {
                n3 = am2.bX;
            }
            if (n3 != null) {
                float f15 = 1.0f - this.teamColorRatio;
                int n4 = Color.a(e2.x);
                int n5 = (int)((float)Color.b(e2.x) * f15);
                int n6 = (int)((float)Color.c(e2.x) * f15);
                int n7 = (int)((float)Color.d(e2.x) * f15);
                int n8 = n3.K();
                n5 = (int)((float)n5 + (float)Color.b(n8) * this.teamColorRatio);
                n6 = (int)((float)n6 + (float)Color.c(n8) * this.teamColorRatio);
                n7 = (int)((float)n7 + (float)Color.d(n8) * this.teamColorRatio);
                n5 = com.corrodinggames.rts.gameFramework.f.b(n5, 0, 255);
                n6 = com.corrodinggames.rts.gameFramework.f.b(n6, 0, 255);
                n7 = com.corrodinggames.rts.gameFramework.f.b(n7, 0, 255);
                e2.x = Color.a(n4, n5, n6, n7);
                if (com.corrodinggames.rts.gameFramework.l.at()) {
                    e2.B = new LightingColorFilter(e2.x, 0);
                }
            }
        }
        if (this.fadeInTime != 0.0f) {
            e2.s = true;
            e2.t = this.fadeInTime;
        }
        e2.as = this.shadow;
        e2.r = this.fadeOut;
        e2.U = this.delayedStartTimer;
        e2.U += this.a(-this.delayedStartTimerRandom, this.delayedStartTimerRandom);
        e2.u = this.atmospheric;
        e2.v = this.physics;
        e2.w = this.physicsGravity;
        e2.q = this.priority;
        e2.P = this.xSpeedAbsolute + this.a(this.xSpeedAbsoluteRandom);
        e2.Q = this.ySpeedAbsolute + this.a(this.ySpeedAbsoluteRandom);
        if (this.xSpeedRelative != 0.0f || this.ySpeedRelative != 0.0f || this.xSpeedRelativeRandom != 0.0f || this.ySpeedRelativeRandom != 0.0f) {
            float f16 = com.corrodinggames.rts.gameFramework.f.k(f5);
            float f17 = com.corrodinggames.rts.gameFramework.f.j(f5);
            f7 = this.xSpeedRelative + this.a(this.xSpeedRelativeRandom);
            f6 = this.ySpeedRelative + this.a(this.ySpeedRelativeRandom);
            e2.P += f16 * f6 - f17 * f7;
            e2.Q += f17 * f6 + f16 * f7;
        }
        e2.R = this.hSpeed + this.a(this.hSpeedRandom);
        e2.Z = this.dirSpeed + this.a(this.dirSpeedRandom);
        if (this.animateFrameStart != this.animateFrameEnd) {
            e2.ae = true;
        }
        e2.af = this.animateFrameStart;
        if (this.animateFrameStartRandomAdd != 0) {
            e2.af += com.corrodinggames.rts.gameFramework.f.a(0, this.animateFrameStartRandomAdd);
        }
        e2.ag = this.animateFrameEnd;
        e2.ak = this.animateFrameStart;
        e2.ah = this.animateFramePingPong;
        e2.ai = this.animateFrameLooping;
        e2.aj = this.animateFrameSpeed;
        e2.aj += this.a(this.animateFrameSpeedRandom);
        if (w2 != null && this.attachedToUnit) {
            c.a(e2, w2);
        }
        if (this.alsoPlaySound != null) {
            this.alsoPlaySound.a(f2, f3, 1.0f);
        }
        if (n2 < 5 && this.alsoEmitEffects != null) {
            this.alsoEmitEffects.a(f2, f3, f4, f5, w2, n2 + 1, (short)0);
        }
        return e2;
    }

    public final float a(float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return com.corrodinggames.rts.gameFramework.f.c(-f2, f2);
    }

    public final float a(float f2, float f3) {
        if (f2 == f3) {
            return f2;
        }
        return com.corrodinggames.rts.gameFramework.f.c(f2, f3);
    }

    public void a(l l2, ab ab2, String string) {
        int n2;
        String string2;
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        this.createWhenOffscreen = ab2.a(string, "createWhenOffscreen", (Boolean)false);
        this.createWhenZoomedOut = ab2.a(string, "createWhenZoomedOut", (Boolean)true);
        this.createWhenOverLiquid = ab2.a(string, "createWhenOverLiquid", (Boolean)true);
        this.createWhenOverLand = ab2.a(string, "createWhenOverLand", (Boolean)true);
        if (!this.createWhenOverLiquid && !this.createWhenOverLand) {
            throw new RuntimeException(string + " effect cannot have both createWhenOverLiquid and createWhenOverLand set to false, it would never be created");
        }
        this.spawnChance = ab2.a(string, "spawnChance", Float.valueOf(1.0f)).floatValue();
        this.life = ab2.a(string, "life", Float.valueOf(200.0f)).floatValue();
        this.lifeRandom = ab2.a(string, "lifeRandom", Float.valueOf(0.0f)).floatValue();
        this.showInFog = ab2.a(string, "showInFog", (Boolean)false);
        this.xOffsetRelative = ab2.a(string, "xOffsetRelative", Float.valueOf(0.0f)).floatValue();
        this.yOffsetRelative = ab2.a(string, "yOffsetRelative", Float.valueOf(0.0f)).floatValue();
        this.hOffset = ab2.a(string, "hOffset", Float.valueOf(0.0f)).floatValue();
        this.alwayStartDirAtZero = ab2.a(string, "alwaysStartDirAtZero", "alwayStartDirAtZero", (Boolean)false);
        this.pivotOffset = ab2.a(string, "pivotOffset", Float.valueOf(0.0f)).floatValue();
        this.pivotOffsetRandom = ab2.a(string, "pivotOffsetRandom", Float.valueOf(0.0f)).floatValue();
        this.dirOffset = ab2.a(string, "dirOffset", Float.valueOf(0.0f)).floatValue();
        this.xOffsetRelativeRandom = ab2.a(string, "xOffsetRelativeRandom", Float.valueOf(0.0f)).floatValue();
        this.yOffsetRelativeRandom = ab2.a(string, "yOffsetRelativeRandom", Float.valueOf(0.0f)).floatValue();
        this.hOffsetRandom = ab2.a(string, "hOffsetRandom", Float.valueOf(0.0f)).floatValue();
        this.dirOffsetRandom = ab2.a(string, "dirOffsetRandom", Float.valueOf(0.0f)).floatValue();
        this.xOffsetAbsolute = ab2.a(string, "xOffsetAbsolute", Float.valueOf(0.0f)).floatValue();
        this.yOffsetAbsolute = ab2.a(string, "yOffsetAbsolute", Float.valueOf(0.0f)).floatValue();
        this.xOffsetAbsoluteRandom = ab2.a(string, "xOffsetAbsoluteRandom", Float.valueOf(0.0f)).floatValue();
        this.yOffsetAbsoluteRandom = ab2.a(string, "yOffsetAbsoluteRandom", Float.valueOf(0.0f)).floatValue();
        this.xSpeedRelative = ab2.a(string, "xSpeedRelative", Float.valueOf(0.0f)).floatValue();
        this.ySpeedRelative = ab2.a(string, "ySpeedRelative", Float.valueOf(0.0f)).floatValue();
        this.hSpeed = ab2.a(string, "hSpeed", Float.valueOf(0.0f)).floatValue();
        this.dirSpeed = ab2.a(string, "dirSpeed", Float.valueOf(0.0f)).floatValue();
        this.xSpeedRelativeRandom = ab2.a(string, "xSpeedRelativeRandom", Float.valueOf(0.0f)).floatValue();
        this.ySpeedRelativeRandom = ab2.a(string, "ySpeedRelativeRandom", Float.valueOf(0.0f)).floatValue();
        this.hSpeedRandom = ab2.a(string, "hSpeedRandom", Float.valueOf(0.0f)).floatValue();
        this.dirSpeedRandom = ab2.a(string, "dirSpeedRandom", Float.valueOf(0.0f)).floatValue();
        this.xSpeedAbsolute = ab2.a(string, "xSpeedAbsolute", Float.valueOf(0.0f)).floatValue();
        this.ySpeedAbsolute = ab2.a(string, "ySpeedAbsolute", Float.valueOf(0.0f)).floatValue();
        this.xSpeedAbsoluteRandom = ab2.a(string, "xSpeedAbsoluteRandom", Float.valueOf(0.0f)).floatValue();
        this.ySpeedAbsoluteRandom = ab2.a(string, "ySpeedAbsoluteRandom", Float.valueOf(0.0f)).floatValue();
        this.scaleTo = ab2.a(string, "scaleTo", Float.valueOf(this.scaleTo)).floatValue();
        this.scaleFrom = ab2.a(string, "scaleFrom", Float.valueOf(this.scaleFrom)).floatValue();
        this.alpha = ab2.a(string, "alpha", Float.valueOf(this.alpha)).floatValue();
        this.color = ab2.a(string, "color", (Integer)this.color);
        if (com.corrodinggames.rts.gameFramework.l.at() && this.color != 0 && this.color != -1) {
            this.cachedLightingColorFilter = new LightingColorFilter(this.color, 0);
        }
        this.teamColorRatio = ab2.a(string, "teamColorRatio", Float.valueOf(this.teamColorRatio)).floatValue();
        if (this.teamColorRatio < 0.0f || this.teamColorRatio > 1.0f) {
            throw new RuntimeException(string + " teamColorRatio should be between 0-1 got:" + this.teamColorRatio);
        }
        this.shadow = ab2.a(string, "shadow", (Boolean)false);
        this.drawLayer = (short)2;
        if (ab2.a(string, "drawUnderUnits", (Boolean)false).booleanValue()) {
            this.drawLayer = 1;
        }
        if ((string2 = ab2.b(string, "drawType", (String)null)) != null && !string2.equals("normal")) {
            if (string2.equals("displacement")) {
                this.drawLayer = (short)3;
            } else {
                throw new bo("Unknown drawType: " + string2);
            }
        }
        this.fadeInTime = ab2.a(string, "fadeInTime", Float.valueOf(0.0f)).floatValue();
        this.fadeOut = ab2.a(string, "fadeOut", (Boolean)true);
        this.delayedStartTimer = ab2.b(string, "delayedStartTimer", Float.valueOf(0.0f)).floatValue();
        this.delayedStartTimerRandom = ab2.a(string, "delayedStartTimerRandom", Float.valueOf(0.0f)).floatValue();
        this.frameIndex = ab2.b(string, "frameIndex", 0);
        this.frameIndexRandom = ab2.b(string, "frameIndexRandom", 0);
        String string3 = ab2.b(string, "stripIndex", "0");
        this.stripIndex = l3.bR.a(string3);
        if (this.stripIndex == -1) {
            throw new RuntimeException("Failed to find stripIndex with name:" + string3);
        }
        this.attachedToUnit = ab2.a(string, "attachedToUnit", (Boolean)true);
        this.liveAfterAttachedDies = ab2.a(string, "liveAfterAttachedDies", (Boolean)true);
        this.atmospheric = ab2.a(string, "atmospheric", (Boolean)false);
        this.physics = ab2.a(string, "physics", (Boolean)false);
        this.physicsGravity = ab2.a(string, "physicsGravity", Float.valueOf(1.0f)).floatValue();
        String string4 = ab2.b(string, "priority", (String)null);
        if (string4 != null) {
            try {
                this.priority = h.valueOf(string4);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw new RuntimeException("Unknown priority:" + string4);
            }
        }
        if ((n2 = ab2.b(string, "total_frames", 1).intValue()) < 1) {
            throw new bo("TOTAL_FRAMES cannot be: " + n2 + " (must be 1 or more)");
        }
        com.corrodinggames.rts.gameFramework.m.e e2 = l2.a(ab2, string, "image");
        if (e2 != null) {
            this.imageStrip = new g();
            this.imageStrip.i = e2;
            this.imageStrip.b = this.imageStrip.i.m() / n2;
            this.imageStrip.c = this.imageStrip.i.l();
            this.imageStrip.b = ab2.b(string, "frame_width", this.imageStrip.b);
            this.imageStrip.c = ab2.b(string, "frame_height", this.imageStrip.c);
            if (n2 == 1 && this.imageStrip.b >= this.imageStrip.i.m()) {
                this.imageStrip.k = true;
            } else if (this.imageStrip.c < this.imageStrip.i.l()) {
                this.imageStrip.h = this.imageStrip.i.m() / this.imageStrip.b;
                if (this.imageStrip.h < 1) {
                    this.imageStrip.h = 1;
                }
            }
            this.imageStrip.d = 0;
            this.imageStrip.e = 0;
            this.imageStrip.f = this.imageStrip.b;
            this.imageStrip.g = this.imageStrip.c;
            String string5 = ab2.b(string, "imageShadow", (String)null);
            if (string5 != null) {
                this.imageStrip.j = ag.a(l2.F, string5, l2.ab, l2, string, "imageShadow");
                this.shadow = true;
            }
            if (this.shadow && this.imageStrip.j == null) {
                throw new bo("imageShadow is required if image and shadow:true is used");
            }
        }
        this.animateFrameStart = ab2.b(string, "animateFrameStart", 0);
        this.animateFrameStartRandomAdd = ab2.b(string, "animateFrameStartRandomAdd", 0);
        this.animateFrameEnd = ab2.b(string, "animateFrameEnd", 0);
        this.animateFramePingPong = ab2.a(string, "animateFramePingPong", (Boolean)false);
        this.animateFrameLooping = ab2.a(string, "animateFrameLooping", (Boolean)false);
        this.animateFrameSpeed = ab2.b(string, "animateFrameSpeed", Float.valueOf(0.5f)).floatValue();
        this.animateFrameSpeedRandom = ab2.b(string, "animateFrameSpeedRandom", Float.valueOf(0.0f)).floatValue();
        if (e2 != null && (this.imageStrip.b >= this.imageStrip.i.m() || n2 != 1) && this.animateFrameEnd > n2) {
            throw new bo("animateFrameEnd:" + this.animateFrameEnd + " cannot be larger than TOTAL_FRAMES: " + n2 + " (when using custom image)");
        }
        this.alsoEmitEffects = l2.c(ab2.b(string, "alsoEmitEffects", (String)null));
        this.alsoEmitEffectsOnDeath = l2.c(ab2.b(string, "alsoEmitEffectsOnDeath", (String)null));
        this.trailEffect = l2.c(ab2.b(string, "trailEffect", (String)null));
        this.trailEffectRate = ab2.b(string, "trailEffectRate", Float.valueOf(6.0f)).floatValue();
        this.ifSpawnFailsEmitEffects = l2.c(ab2.b(string, "ifSpawnFailsEmitEffects", (String)null));
        this.alsoPlaySound = bl.a(l2, ab2.b(string, "alsoPlaySound", (String)null), null);
    }
}
