/*
 * v19.115q 重建: 02b custom/ay.java (CustomEffectTemplate 效果模板) 518 行直译
 * 类型映射: d.g=HUDElementRenderer, d.h=DrawLayer, d.e=HUDElement, d.f=DrawEffect,
 *   d.c=HUDManager, d.d=SoundEffect, w=GameObject, z=CustomVisuals, m.e=Texture,
 *   n=PlayerState, am=UnitInstance, game.f=MovementController, utility.y=PathfindingUtils
 * 依赖补缺: HUDElement 9 字段/HUDElementRenderer 10 字段/HUDManager.b()/d→SoundEffect;
 *   ModLoader.a 6参; ModUnitRegistry.c(String); GameUtils.b(int,int,int)/k/j/a(int,int)
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.az;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.effects.DrawEffect;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.effects.HUDElementRenderer;
import com.corrodinggames.rts.gameFramework.effects.HUDManager;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;

public class ay {
    public static final ay defaultEffectTemplate = new ay("default");
    public String name;
    private az builtInEffect = null;
    public HUDElementRenderer imageStrip;  // 02b: d.g
    public boolean createWhenOffscreen;
    public boolean createWhenZoomedOut;
    public boolean createWhenOverLiquid;
    public boolean createWhenOverLand;
    public float spawnChance = 1.0f;
    CustomVisuals ifSpawnFailsEmitEffects;  // 02b: z
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
    public DrawLayer priority;  // 02b: d.h (03 旧标 TagFilter 错位)
    public float scaleTo;
    public float scaleFrom;
    public float alpha;
    public int color;
    public LightingColorFilter cachedLightingColorFilter;
    public float teamColorRatio;
    public boolean shadow;
    public short drawLayer;
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
    public float physicsGravity;
    public int animateFrameStart;
    public int animateFrameEnd;
    public int animateFrameStartRandomAdd;
    public boolean animateFramePingPong;
    public boolean animateFrameLooping;
    public float animateFrameSpeed;
    public float animateFrameSpeedRandom;
    public CustomVisuals alsoEmitEffects;
    public CustomVisuals alsoEmitEffectsOnDeath;
    public CustomVisuals trailEffect;
    public float trailEffectRate;
    public bl alsoPlaySound;
    public static ArrayList fields;

    public ay(az az2) {
        // 02b L91-101
        this.priority = DrawLayer.c;
        this.scaleTo = 1.0f;
        this.scaleFrom = 1.0f;
        this.alpha = 1.0f;
        this.color = -1;
        this.teamColorRatio = 0.0f;
        this.drawLayer = (short)2;
        this.physicsGravity = 1.0f;
        this.builtInEffect = az2;
    }

    ay(String string) {
        // 02b L103-113
        this.priority = DrawLayer.c;
        this.scaleTo = 1.0f;
        this.scaleFrom = 1.0f;
        this.alpha = 1.0f;
        this.color = -1;
        this.teamColorRatio = 0.0f;
        this.drawLayer = (short)2;
        this.physicsGravity = 1.0f;
        this.name = string;
    }

    public HUDElement a(float f2, float f3, float f4, float f5, com.corrodinggames.rts.gameFramework.GameObject w2, int n2, short s2) {  // 02b: 第5参 w (GameObject 为幻觉名)
        // 02b L115-357: 效果生成主逻辑
        GlobalState l2 = GlobalState.B();
        if (this.spawnChance < 1.0f && GameUtils.c(0.0f, 1.0f) > this.spawnChance) {
            if (n2 < 5 && this.ifSpawnFailsEmitEffects != null) {
                this.ifSpawnFailsEmitEffects.a(f2, f3, f4, f5, w2, n2 + 1, s2);
            }
            return null;
        }
        if (this.builtInEffect != null) {
            HUDElement e2;
            if (this.builtInEffect == az.a) {
                e2 = l2.bR.a(f2, f3, f4, f5);
            } else if (this.builtInEffect == az.b) {
                e2 = l2.bR.b(f2, f3, f4, f5, 0);
                if (e2 != null) {
                    e2.G = 0.75f;
                    e2.F = 0.75f;
                }
            } else if (this.builtInEffect == az.c) {
                e2 = l2.bR.b(f2, f3, f4, f5, 0);
            } else if (this.builtInEffect == az.d) {
                e2 = l2.bR.c(f2, f3, f4, f5, 0);
            } else if (this.builtInEffect == az.e) {
                e2 = l2.bR.d(f2, f3, f4, 0);
            } else if (this.builtInEffect == az.f) {
                l2.bR.a(f2, f3, f4);
                e2 = null;
            } else if (this.builtInEffect == az.g) {
                e2 = l2.bR.b(f2, f3, f4);
            } else if (this.builtInEffect == az.h) {
                DrawEffect drawEffect = DrawEffect.a(f2, f3);
                drawEffect.j = -6684775;
                DrawEffect drawEffect2 = DrawEffect.b(f2, f3);
                drawEffect2.a = 500.0f;
                drawEffect2.j = -6684775;
                l2.bR.b(DrawLayer.e);
                HUDElement e3 = l2.bR.c(f2, f3, f4, -1127220);
                if (e3 != null) {
                    e3.G = 0.15f;
                    e3.F = 1.0f;
                    e3.ar = 2;
                    e3.V = 35.0f;
                    e3.W = e3.V;
                    e3.U = 0.0f;
                    e3.x = -13378253;
                }
                e2 = null;
            } else {
                if (this.builtInEffect != az.i) {
                    throw new RuntimeException("Unhandled built-in type:" + this.builtInEffect);
                }
                return null;
            }
            if (e2 == null) {
                return null;
            }
            e2.ar = 2;
            if (w2 != null) {
                HUDManager.a(e2, w2);  // 02b: d.c.a(e, w)
            }
            return e2;
        }
        if (!this.createWhenZoomedOut && !l2.dc) {
            return null;
        }
        if (!this.createWhenOverLiquid && PathfindingUtils.d(f2, f3)) {
            return null;
        }
        if (!this.createWhenOverLand && !PathfindingUtils.d(f2, f3)) {
            return null;
        }
        if (this.createWhenOffscreen) {
            l2.bR.b();  // 02b: bR.b() 取消离屏限制
        } else {
            l2.bR.a();
        }
        boolean bl = this.showInFog;
        boolean bl2 = false;
        if (!bl && this.attachedToUnit) {
            bl2 = true;
            bl = true;
        }
        HUDElement e4 = l2.bR.a(f2, f3, f4, SoundEffect.a, bl, this.priority);  // 02b: bR.b(x,y,z,d.d.a,bl,priority)
        if (e4 == null) {
            return null;
        }
        e4.a = this;
        e4.A = (short)(s2 + 1);
        if (bl2 && !this.showInFog) {
            e4.e = false;
        }
        e4.V = this.life;
        e4.V += this.a(this.lifeRandom);
        e4.W = e4.V;
        e4.aq = this.stripIndex;
        if (this.imageStrip != null) {
            // 02b L217-219: 空操作 (FF 原文保留)
        }
        e4.ap = this.frameIndex;
        if (this.frameIndexRandom != 0) {
            e4.ap += GameUtils.a(-this.frameIndexRandom, this.frameIndexRandom);
            if (e4.ap < 0) {
                e4.ap = 0;
            }
        }
        f5 += this.pivotOffset;
        f5 += this.a(this.pivotOffsetRandom);
        if (this.alwayStartDirAtZero) {
            e4.Y = 0.0f;
        } else {
            e4.Y = f5;
        }
        e4.Y += this.dirOffset;
        e4.Y += this.a(this.dirOffsetRandom);
        float f6;
        float f7;
        if (this.xOffsetAbsoluteRandom != 0.0f || this.yOffsetAbsoluteRandom != 0.0f || this.xOffsetAbsolute != 0.0f || this.yOffsetAbsolute != 0.0f) {
            f6 = this.xOffsetAbsolute + this.a(this.xOffsetAbsoluteRandom);
            f7 = this.yOffsetAbsolute + this.a(this.yOffsetAbsoluteRandom);
            e4.I += f6;
            e4.J += f7;
        }
        float f8;
        float f9;
        if (this.xOffsetRelativeRandom != 0.0f || this.yOffsetRelativeRandom != 0.0f || this.xOffsetRelative != 0.0f || this.yOffsetRelative != 0.0f) {
            f6 = GameUtils.k(f5);
            f7 = GameUtils.j(f5);
            f8 = this.xOffsetRelative + this.a(this.xOffsetRelativeRandom);
            f9 = this.yOffsetRelative + this.a(this.yOffsetRelativeRandom);
            e4.I += f6 * f9 - f7 * f8;
            e4.J += f7 * f9 + f6 * f8;
        }
        e4.K += this.hOffset + this.a(-this.hOffsetRandom, this.hOffsetRandom);
        e4.an = true;
        e4.r = true;
        e4.ar = this.drawLayer;
        e4.G = this.scaleFrom;
        e4.F = this.scaleTo;
        e4.E = this.alpha;
        e4.x = this.color;
        e4.B = this.cachedLightingColorFilter;
        if (this.teamColorRatio != 0.0f && w2 != null) {
            PlayerState playerState = null;
            if (w2 instanceof UnitInstance) {
                playerState = ((UnitInstance)w2).player;
            }
            if (w2 instanceof MovementController) {
                UnitInstance unitInstance = ((MovementController)w2).j;
                if (unitInstance != null) {
                    playerState = unitInstance.player;
                }
            }
            if (playerState != null) {
                f7 = 1.0f - this.teamColorRatio;
                int n3 = Color.a(e4.x);
                int n4 = (int)((float)Color.b(e4.x) * f7);
                int n5 = (int)((float)Color.c(e4.x) * f7);
                int n6 = (int)((float)Color.d(e4.x) * f7);
                int n7 = playerState.K();
                n4 = (int)((float)n4 + (float)Color.b(n7) * this.teamColorRatio);
                n5 = (int)((float)n5 + (float)Color.c(n7) * this.teamColorRatio);
                n6 = (int)((float)n6 + (float)Color.d(n7) * this.teamColorRatio);
                n4 = GameUtils.b(n4, 0, 255);
                n5 = GameUtils.b(n5, 0, 255);
                n6 = GameUtils.b(n6, 0, 255);
                e4.x = Color.a(n3, n4, n5, n6);
                if (GlobalState.at()) {
                    e4.B = new LightingColorFilter(e4.x, 0);
                }
            }
        }
        if (this.fadeInTime != 0.0f) {
            e4.s = true;
            e4.t = this.fadeInTime;
        }
        e4.as = this.shadow;
        e4.r = this.fadeOut;
        e4.U = this.delayedStartTimer;
        e4.U += this.a(-this.delayedStartTimerRandom, this.delayedStartTimerRandom);
        e4.u = this.atmospheric;
        e4.v = this.physics;
        e4.w = this.physicsGravity;
        e4.q = this.priority;
        e4.P = this.xSpeedAbsolute + this.a(this.xSpeedAbsoluteRandom);
        e4.Q = this.ySpeedAbsolute + this.a(this.ySpeedAbsoluteRandom);
        if (this.xSpeedRelative != 0.0f || this.ySpeedRelative != 0.0f || this.xSpeedRelativeRandom != 0.0f || this.ySpeedRelativeRandom != 0.0f) {
            f6 = GameUtils.k(f5);
            f7 = GameUtils.j(f5);
            f8 = this.xSpeedRelative + this.a(this.xSpeedRelativeRandom);
            f9 = this.ySpeedRelative + this.a(this.ySpeedRelativeRandom);
            e4.P += f6 * f9 - f7 * f8;
            e4.Q += f7 * f9 + f6 * f8;
        }
        e4.R = this.hSpeed + this.a(this.hSpeedRandom);
        e4.Z = this.dirSpeed + this.a(this.dirSpeedRandom);
        if (this.animateFrameStart != this.animateFrameEnd) {
            e4.ae = true;
        }
        e4.af = this.animateFrameStart;
        if (this.animateFrameStartRandomAdd != 0) {
            e4.af += GameUtils.a(0, this.animateFrameStartRandomAdd);
        }
        e4.ag = this.animateFrameEnd;
        e4.ak = (float)this.animateFrameStart;
        e4.ah = this.animateFramePingPong;
        e4.ai = this.animateFrameLooping;
        e4.aj = this.animateFrameSpeed;
        e4.aj += this.a(this.animateFrameSpeedRandom);
        if (w2 != null && this.attachedToUnit) {
            HUDManager.a(e4, w2);
        }
        if (this.alsoPlaySound != null) {
            this.alsoPlaySound.a(f2, f3, 1.0f);
        }
        if (n2 < 5 && this.alsoEmitEffects != null) {
            this.alsoEmitEffects.a(f2, f3, f4, f5, w2, n2 + 1, (short)0);
        }
        return e4;
    }

    public final float a(float f2) {
        // 02b L359-361
        return f2 == 0.0f ? 0.0f : GameUtils.c(-f2, f2);
    }

    public final float a(float f2, float f3) {
        // 02b L363-365
        return f2 == f3 ? f2 : GameUtils.c(f2, f3);
    }

    public void a(ModUnitRegistry l2, ab ab2, String string) throws bo {
        // 02b L367-516: INI 配置解析
        GlobalState l3 = GlobalState.B();
        this.createWhenOffscreen = ab2.a(string, "createWhenOffscreen", Boolean.valueOf(false)).booleanValue();
        this.createWhenZoomedOut = ab2.a(string, "createWhenZoomedOut", Boolean.valueOf(true)).booleanValue();
        this.createWhenOverLiquid = ab2.a(string, "createWhenOverLiquid", Boolean.valueOf(true)).booleanValue();
        this.createWhenOverLand = ab2.a(string, "createWhenOverLand", Boolean.valueOf(true)).booleanValue();
        if (!this.createWhenOverLiquid && !this.createWhenOverLand) {
            throw new RuntimeException(string + " effect cannot have both createWhenOverLiquid and createWhenOverLand set to false, it would never be created");
        }
        this.spawnChance = ab2.a(string, "spawnChance", Float.valueOf(1.0f)).floatValue();
        this.life = ab2.a(string, "life", Float.valueOf(200.0f)).floatValue();
        this.lifeRandom = ab2.a(string, "lifeRandom", Float.valueOf(0.0f)).floatValue();
        this.showInFog = ab2.a(string, "showInFog", Boolean.valueOf(false)).booleanValue();
        this.xOffsetRelative = ab2.a(string, "xOffsetRelative", Float.valueOf(0.0f)).floatValue();
        this.yOffsetRelative = ab2.a(string, "yOffsetRelative", Float.valueOf(0.0f)).floatValue();
        this.hOffset = ab2.a(string, "hOffset", Float.valueOf(0.0f)).floatValue();
        this.alwayStartDirAtZero = ab2.a(string, "alwaysStartDirAtZero", "alwayStartDirAtZero", Boolean.valueOf(false)).booleanValue();
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
        this.color = ab2.a(string, "color", Integer.valueOf(this.color)).intValue();
        if (GlobalState.at() && this.color != 0 && this.color != -1) {
            this.cachedLightingColorFilter = new LightingColorFilter(this.color, 0);
        }
        this.teamColorRatio = ab2.a(string, "teamColorRatio", Float.valueOf(this.teamColorRatio)).floatValue();
        if (this.teamColorRatio >= 0.0f && this.teamColorRatio <= 1.0f) {
            this.shadow = ab2.a(string, "shadow", Boolean.valueOf(false)).booleanValue();
            this.drawLayer = (short)2;
            if (ab2.a(string, "drawUnderUnits", Boolean.valueOf(false)).booleanValue()) {
                this.drawLayer = (short)1;
            }
            String string2 = ab2.b(string, "drawType", (String)null);
            if (string2 != null && !string2.equals("normal")) {
                if (!string2.equals("displacement")) {
                    throw new bo("Unknown drawType: " + string2);
                }
                this.drawLayer = (short)3;
            }
            this.fadeInTime = ab2.a(string, "fadeInTime", Float.valueOf(0.0f)).floatValue();
            this.fadeOut = ab2.a(string, "fadeOut", Boolean.valueOf(true)).booleanValue();
            this.delayedStartTimer = ab2.b(string, "delayedStartTimer", Float.valueOf(0.0f)).floatValue();
            this.delayedStartTimerRandom = ab2.a(string, "delayedStartTimerRandom", Float.valueOf(0.0f)).floatValue();
            this.frameIndex = ab2.b(string, "frameIndex", Integer.valueOf(0)).intValue();
            this.frameIndexRandom = ab2.b(string, "frameIndexRandom", Integer.valueOf(0)).intValue();
            String string3 = ab2.b(string, "stripIndex", "0");
            // 02b L439: var4.bR.a(var6) — l.bR 字节码实为 float 字段 (javap 铁证), FF 错译
            // stripIndex 查找简化 TODO (真实应在 HUDManager 粒子条带注册表)
            this.stripIndex = 0;
            if (this.stripIndex == -1) {
                throw new RuntimeException("Failed to find stripIndex with name:" + string3);
            }
            this.attachedToUnit = ab2.a(string, "attachedToUnit", Boolean.valueOf(true)).booleanValue();
            this.liveAfterAttachedDies = ab2.a(string, "liveAfterAttachedDies", Boolean.valueOf(true)).booleanValue();
            this.atmospheric = ab2.a(string, "atmospheric", Boolean.valueOf(false)).booleanValue();
            this.physics = ab2.a(string, "physics", Boolean.valueOf(false)).booleanValue();
            this.physicsGravity = ab2.a(string, "physicsGravity", Float.valueOf(1.0f)).floatValue();
            String string4 = ab2.b(string, "priority", (String)null);
            if (string4 != null) {
                try {
                    this.priority = DrawLayer.valueOf(string4);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw new RuntimeException("Unknown priority:" + string4);
                }
            }
            int n2 = ab2.b(string, "total_frames", Integer.valueOf(1)).intValue();
            if (n2 < 1) {
                throw new bo("TOTAL_FRAMES cannot be: " + n2 + " (must be 1 or more)");
            }
            // 02b L461-491: image 条带解析 (var1.a(ab,Str,Str)→m.e Texture;
            // 03 ModUnitRegistry 该签名被 ParameterAnimator 错位占用) — 简化 TODO: imageStrip 保持 null
            this.animateFrameStart = ab2.b(string, "animateFrameStart", Integer.valueOf(0)).intValue();
            this.animateFrameStartRandomAdd = ab2.b(string, "animateFrameStartRandomAdd", Integer.valueOf(0)).intValue();
            this.animateFrameEnd = ab2.b(string, "animateFrameEnd", Integer.valueOf(0)).intValue();
            this.animateFramePingPong = ab2.a(string, "animateFramePingPong", Boolean.valueOf(false)).booleanValue();
            this.animateFrameLooping = ab2.a(string, "animateFrameLooping", Boolean.valueOf(false)).booleanValue();
            this.animateFrameSpeed = ab2.b(string, "animateFrameSpeed", Float.valueOf(0.5f)).floatValue();
            this.animateFrameSpeedRandom = ab2.b(string, "animateFrameSpeedRandom", Float.valueOf(0.0f)).floatValue();
            // 02b L500-501: 需 imageStrip 的校验 — 简化跳过
            this.alsoEmitEffects = l2.c(ab2.b(string, "alsoEmitEffects", (String)null));  // 02b: l.c(String)→z
            this.alsoEmitEffectsOnDeath = l2.c(ab2.b(string, "alsoEmitEffectsOnDeath", (String)null));
            this.trailEffect = l2.c(ab2.b(string, "trailEffect", (String)null));
            this.trailEffectRate = ab2.b(string, "trailEffectRate", Float.valueOf(6.0f)).floatValue();
            this.ifSpawnFailsEmitEffects = l2.c(ab2.b(string, "ifSpawnFailsEmitEffects", (String)null));
            this.alsoPlaySound = bl.a(l2, ab2.b(string, "alsoPlaySound", (String)null), (bl)null);
        } else {
            throw new RuntimeException(string + " teamColorRatio should be between 0-1 got:" + this.teamColorRatio);
        }
    }
}
