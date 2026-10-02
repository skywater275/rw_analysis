package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.az;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.z;
import java.util.ArrayList;

public class ay {

   public static final ay defaultEffectTemplate = new ay("default");
   public String name;
   private az builtInEffect = null;
   public com.corrodinggames.rts.gameFramework.d.g imageStrip;
   public boolean createWhenOffscreen;
   public boolean createWhenZoomedOut;
   public boolean createWhenOverLiquid;
   public boolean createWhenOverLand;
   public float spawnChance = 1.0F;
   z ifSpawnFailsEmitEffects;
   public float life = 200.0F;
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
   public com.corrodinggames.rts.gameFramework.d.h priority;
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
   public z alsoEmitEffects;
   public z alsoEmitEffectsOnDeath;
   public z trailEffect;
   public float trailEffectRate;
   public bl alsoPlaySound;
   public static ArrayList fields;


   public ay(az var1) {
      this.priority = com.corrodinggames.rts.gameFramework.d.h.c;
      this.scaleTo = 1.0F;
      this.scaleFrom = 1.0F;
      this.alpha = 1.0F;
      this.color = -1;
      this.teamColorRatio = 0.0F;
      this.drawLayer = 2;
      this.physicsGravity = 1.0F;
      this.builtInEffect = var1;
   }

   ay(String var1) {
      this.priority = com.corrodinggames.rts.gameFramework.d.h.c;
      this.scaleTo = 1.0F;
      this.scaleFrom = 1.0F;
      this.alpha = 1.0F;
      this.color = -1;
      this.teamColorRatio = 0.0F;
      this.drawLayer = 2;
      this.physicsGravity = 1.0F;
      this.name = var1;
   }

   public com.corrodinggames.rts.gameFramework.d.e a(float var1, float var2, float var3, float var4, com.corrodinggames.rts.gameFramework.w var5, int var6, short var7) {
      com.corrodinggames.rts.gameFramework.l var8 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.spawnChance < 1.0F && com.corrodinggames.rts.gameFramework.f.c(0.0F, 1.0F) > this.spawnChance) {
         if(var6 < 5 && this.ifSpawnFailsEmitEffects != null) {
            this.ifSpawnFailsEmitEffects.a(var1, var2, var3, var4, var5, var6 + 1, var7);
         }

         return null;
      } else if(this.builtInEffect != null) {
         com.corrodinggames.rts.gameFramework.d.e var22;
         if(this.builtInEffect == az.a) {
            var22 = var8.bR.a(var1, var2, var3, var4);
         } else if(this.builtInEffect == az.b) {
            var22 = var8.bR.b(var1, var2, var3, var4, 0);
            if(var22 != null) {
               var22.G = 0.75F;
               var22.F = 0.75F;
            }
         } else if(this.builtInEffect == az.c) {
            var22 = var8.bR.b(var1, var2, var3, var4, 0);
         } else if(this.builtInEffect == az.d) {
            var22 = var8.bR.c(var1, var2, var3, var4, 0);
         } else if(this.builtInEffect == az.e) {
            var22 = var8.bR.d(var1, var2, var3, 0);
         } else if(this.builtInEffect == az.f) {
            var8.bR.a(var1, var2, var3);
            var22 = null;
         } else if(this.builtInEffect == az.g) {
            var22 = var8.bR.b(var1, var2, var3);
         } else {
            if(this.builtInEffect != az.h) {
               if(this.builtInEffect == az.i) {
                  return null;
               }

               throw new RuntimeException("Unhandled built-in type:" + this.builtInEffect);
            }

            com.corrodinggames.rts.gameFramework.d.f var23 = com.corrodinggames.rts.gameFramework.d.f.a(var1, var2);
            var23.j = -6684775;
            com.corrodinggames.rts.gameFramework.d.f var24 = com.corrodinggames.rts.gameFramework.d.f.b(var1, var2);
            var24.a = 500.0F;
            var24.j = -6684775;
            var8.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
            com.corrodinggames.rts.gameFramework.d.e var27 = var8.bR.c(var1, var2, var3, -1127220);
            if(var27 != null) {
               var27.G = 0.15F;
               var27.F = 1.0F;
               var27.ar = 2;
               var27.V = 35.0F;
               var27.W = var27.V;
               var27.U = 0.0F;
               var27.x = -13378253;
            }

            var22 = null;
         }

         if(var22 == null) {
            return null;
         } else {
            var22.ar = 2;
            if(var5 != null) {
               com.corrodinggames.rts.gameFramework.d.c.a(var22, var5);
            }

            return var22;
         }
      } else if(!this.createWhenZoomedOut && !var8.dc) {
         return null;
      } else if(!this.createWhenOverLiquid && com.corrodinggames.rts.gameFramework.utility.y.d(var1, var2)) {
         return null;
      } else if(!this.createWhenOverLand && !com.corrodinggames.rts.gameFramework.utility.y.d(var1, var2)) {
         return null;
      } else {
         if(this.createWhenOffscreen) {
            var8.bR.b();
         } else {
            var8.bR.a();
         }

         boolean var12 = this.showInFog;
         boolean var13 = false;
         if(!var12 && this.attachedToUnit) {
            var13 = true;
            var12 = true;
         }

         com.corrodinggames.rts.gameFramework.d.e var14 = var8.bR.b(var1, var2, var3, com.corrodinggames.rts.gameFramework.d.d.a, var12, this.priority);
         if(var14 == null) {
            return null;
         } else {
            var14.a = this;
            var14.A = (short)(var7 + 1);
            if(var13 && !this.showInFog) {
               var14.e = false;
            }

            var14.V = this.life;
            var14.V += this.a(this.lifeRandom);
            var14.W = var14.V;
            var14.aq = this.stripIndex;
            if(this.imageStrip != null) {
               ;
            }

            var14.ap = this.frameIndex;
            if(this.frameIndexRandom != 0) {
               var14.ap += com.corrodinggames.rts.gameFramework.f.a(-this.frameIndexRandom, this.frameIndexRandom);
               if(var14.ap < 0) {
                  var14.ap = 0;
               }
            }

            var4 += this.pivotOffset;
            var4 += this.a(this.pivotOffsetRandom);
            if(this.alwayStartDirAtZero) {
               var14.Y = 0.0F;
            } else {
               var14.Y = var4;
            }

            var14.Y += this.dirOffset;
            var14.Y += this.a(this.dirOffsetRandom);
            float var15;
            float var16;
            if(this.xOffsetAbsoluteRandom != 0.0F || this.yOffsetAbsoluteRandom != 0.0F || this.xOffsetAbsolute != 0.0F || this.yOffsetAbsolute != 0.0F) {
               var15 = this.xOffsetAbsolute + this.a(this.xOffsetAbsoluteRandom);
               var16 = this.yOffsetAbsolute + this.a(this.yOffsetAbsoluteRandom);
               var14.I += var15;
               var14.J += var16;
            }

            float var17;
            float var18;
            if(this.xOffsetRelativeRandom != 0.0F || this.yOffsetRelativeRandom != 0.0F || this.xOffsetRelative != 0.0F || this.yOffsetRelative != 0.0F) {
               var15 = com.corrodinggames.rts.gameFramework.f.k(var4);
               var16 = com.corrodinggames.rts.gameFramework.f.j(var4);
               var17 = this.xOffsetRelative + this.a(this.xOffsetRelativeRandom);
               var18 = this.yOffsetRelative + this.a(this.yOffsetRelativeRandom);
               var14.I += var15 * var18 - var16 * var17;
               var14.J += var16 * var18 + var15 * var17;
            }

            var14.K += this.hOffset + this.a(-this.hOffsetRandom, this.hOffsetRandom);
            var14.an = true;
            var14.r = true;
            var14.ar = this.drawLayer;
            var14.G = this.scaleFrom;
            var14.F = this.scaleTo;
            var14.E = this.alpha;
            var14.x = this.color;
            var14.B = this.cachedLightingColorFilter;
            if(this.teamColorRatio != 0.0F && var5 != null) {
               com.corrodinggames.rts.game.n var25 = null;
               if(var5 instanceof com.corrodinggames.rts.game.units.am) {
                  var25 = ((com.corrodinggames.rts.game.units.am)var5).bX;
               }

               if(var5 instanceof com.corrodinggames.rts.game.f) {
                  com.corrodinggames.rts.game.units.am var26 = ((com.corrodinggames.rts.game.f)var5).j;
                  if(var26 != null) {
                     var25 = var26.bX;
                  }
               }

               if(var25 != null) {
                  var16 = 1.0F - this.teamColorRatio;
                  int var28 = Color.a(var14.x);
                  int var29 = (int)((float)Color.b(var14.x) * var16);
                  int var19 = (int)((float)Color.c(var14.x) * var16);
                  int var20 = (int)((float)Color.d(var14.x) * var16);
                  int var21 = var25.K();
                  var29 = (int)((float)var29 + (float)Color.b(var21) * this.teamColorRatio);
                  var19 = (int)((float)var19 + (float)Color.c(var21) * this.teamColorRatio);
                  var20 = (int)((float)var20 + (float)Color.d(var21) * this.teamColorRatio);
                  var29 = com.corrodinggames.rts.gameFramework.f.b(var29, 0, 255);
                  var19 = com.corrodinggames.rts.gameFramework.f.b(var19, 0, 255);
                  var20 = com.corrodinggames.rts.gameFramework.f.b(var20, 0, 255);
                  var14.x = Color.a(var28, var29, var19, var20);
                  if(com.corrodinggames.rts.gameFramework.l.at()) {
                     var14.B = new LightingColorFilter(var14.x, 0);
                  }
               }
            }

            if(this.fadeInTime != 0.0F) {
               var14.s = true;
               var14.t = this.fadeInTime;
            }

            var14.as = this.shadow;
            var14.r = this.fadeOut;
            var14.U = this.delayedStartTimer;
            var14.U += this.a(-this.delayedStartTimerRandom, this.delayedStartTimerRandom);
            var14.u = this.atmospheric;
            var14.v = this.physics;
            var14.w = this.physicsGravity;
            var14.q = this.priority;
            var14.P = this.xSpeedAbsolute + this.a(this.xSpeedAbsoluteRandom);
            var14.Q = this.ySpeedAbsolute + this.a(this.ySpeedAbsoluteRandom);
            if(this.xSpeedRelative != 0.0F || this.ySpeedRelative != 0.0F || this.xSpeedRelativeRandom != 0.0F || this.ySpeedRelativeRandom != 0.0F) {
               var15 = com.corrodinggames.rts.gameFramework.f.k(var4);
               var16 = com.corrodinggames.rts.gameFramework.f.j(var4);
               var17 = this.xSpeedRelative + this.a(this.xSpeedRelativeRandom);
               var18 = this.ySpeedRelative + this.a(this.ySpeedRelativeRandom);
               var14.P += var15 * var18 - var16 * var17;
               var14.Q += var16 * var18 + var15 * var17;
            }

            var14.R = this.hSpeed + this.a(this.hSpeedRandom);
            var14.Z = this.dirSpeed + this.a(this.dirSpeedRandom);
            if(this.animateFrameStart != this.animateFrameEnd) {
               var14.ae = true;
            }

            var14.af = this.animateFrameStart;
            if(this.animateFrameStartRandomAdd != 0) {
               var14.af += com.corrodinggames.rts.gameFramework.f.a(0, this.animateFrameStartRandomAdd);
            }

            var14.ag = this.animateFrameEnd;
            var14.ak = (float)this.animateFrameStart;
            var14.ah = this.animateFramePingPong;
            var14.ai = this.animateFrameLooping;
            var14.aj = this.animateFrameSpeed;
            var14.aj += this.a(this.animateFrameSpeedRandom);
            if(var5 != null && this.attachedToUnit) {
               com.corrodinggames.rts.gameFramework.d.c.a(var14, var5);
            }

            if(this.alsoPlaySound != null) {
               this.alsoPlaySound.a(var1, var2, 1.0F);
            }

            if(var6 < 5 && this.alsoEmitEffects != null) {
               this.alsoEmitEffects.a(var1, var2, var3, var4, var5, var6 + 1, (short)0);
            }

            return var14;
         }
      }
   }

   public final float a(float var1) {
      return var1 == 0.0F?0.0F:com.corrodinggames.rts.gameFramework.f.c(-var1, var1);
   }

   public final float a(float var1, float var2) {
      return var1 == var2?var1:com.corrodinggames.rts.gameFramework.f.c(var1, var2);
   }

   public void a(l var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      this.createWhenOffscreen = var2.a(var3, "createWhenOffscreen", Boolean.valueOf(false)).booleanValue();
      this.createWhenZoomedOut = var2.a(var3, "createWhenZoomedOut", Boolean.valueOf(true)).booleanValue();
      this.createWhenOverLiquid = var2.a(var3, "createWhenOverLiquid", Boolean.valueOf(true)).booleanValue();
      this.createWhenOverLand = var2.a(var3, "createWhenOverLand", Boolean.valueOf(true)).booleanValue();
      if(!this.createWhenOverLiquid && !this.createWhenOverLand) {
         throw new RuntimeException(var3 + " effect cannot have both createWhenOverLiquid and createWhenOverLand set to false, it would never be created");
      } else {
         this.spawnChance = var2.a(var3, "spawnChance", Float.valueOf(1.0F)).floatValue();
         this.life = var2.a(var3, "life", Float.valueOf(200.0F)).floatValue();
         this.lifeRandom = var2.a(var3, "lifeRandom", Float.valueOf(0.0F)).floatValue();
         this.showInFog = var2.a(var3, "showInFog", Boolean.valueOf(false)).booleanValue();
         this.xOffsetRelative = var2.a(var3, "xOffsetRelative", Float.valueOf(0.0F)).floatValue();
         this.yOffsetRelative = var2.a(var3, "yOffsetRelative", Float.valueOf(0.0F)).floatValue();
         this.hOffset = var2.a(var3, "hOffset", Float.valueOf(0.0F)).floatValue();
         this.alwayStartDirAtZero = var2.a(var3, "alwaysStartDirAtZero", "alwayStartDirAtZero", Boolean.valueOf(false)).booleanValue();
         this.pivotOffset = var2.a(var3, "pivotOffset", Float.valueOf(0.0F)).floatValue();
         this.pivotOffsetRandom = var2.a(var3, "pivotOffsetRandom", Float.valueOf(0.0F)).floatValue();
         this.dirOffset = var2.a(var3, "dirOffset", Float.valueOf(0.0F)).floatValue();
         this.xOffsetRelativeRandom = var2.a(var3, "xOffsetRelativeRandom", Float.valueOf(0.0F)).floatValue();
         this.yOffsetRelativeRandom = var2.a(var3, "yOffsetRelativeRandom", Float.valueOf(0.0F)).floatValue();
         this.hOffsetRandom = var2.a(var3, "hOffsetRandom", Float.valueOf(0.0F)).floatValue();
         this.dirOffsetRandom = var2.a(var3, "dirOffsetRandom", Float.valueOf(0.0F)).floatValue();
         this.xOffsetAbsolute = var2.a(var3, "xOffsetAbsolute", Float.valueOf(0.0F)).floatValue();
         this.yOffsetAbsolute = var2.a(var3, "yOffsetAbsolute", Float.valueOf(0.0F)).floatValue();
         this.xOffsetAbsoluteRandom = var2.a(var3, "xOffsetAbsoluteRandom", Float.valueOf(0.0F)).floatValue();
         this.yOffsetAbsoluteRandom = var2.a(var3, "yOffsetAbsoluteRandom", Float.valueOf(0.0F)).floatValue();
         this.xSpeedRelative = var2.a(var3, "xSpeedRelative", Float.valueOf(0.0F)).floatValue();
         this.ySpeedRelative = var2.a(var3, "ySpeedRelative", Float.valueOf(0.0F)).floatValue();
         this.hSpeed = var2.a(var3, "hSpeed", Float.valueOf(0.0F)).floatValue();
         this.dirSpeed = var2.a(var3, "dirSpeed", Float.valueOf(0.0F)).floatValue();
         this.xSpeedRelativeRandom = var2.a(var3, "xSpeedRelativeRandom", Float.valueOf(0.0F)).floatValue();
         this.ySpeedRelativeRandom = var2.a(var3, "ySpeedRelativeRandom", Float.valueOf(0.0F)).floatValue();
         this.hSpeedRandom = var2.a(var3, "hSpeedRandom", Float.valueOf(0.0F)).floatValue();
         this.dirSpeedRandom = var2.a(var3, "dirSpeedRandom", Float.valueOf(0.0F)).floatValue();
         this.xSpeedAbsolute = var2.a(var3, "xSpeedAbsolute", Float.valueOf(0.0F)).floatValue();
         this.ySpeedAbsolute = var2.a(var3, "ySpeedAbsolute", Float.valueOf(0.0F)).floatValue();
         this.xSpeedAbsoluteRandom = var2.a(var3, "xSpeedAbsoluteRandom", Float.valueOf(0.0F)).floatValue();
         this.ySpeedAbsoluteRandom = var2.a(var3, "ySpeedAbsoluteRandom", Float.valueOf(0.0F)).floatValue();
         this.scaleTo = var2.a(var3, "scaleTo", Float.valueOf(this.scaleTo)).floatValue();
         this.scaleFrom = var2.a(var3, "scaleFrom", Float.valueOf(this.scaleFrom)).floatValue();
         this.alpha = var2.a(var3, "alpha", Float.valueOf(this.alpha)).floatValue();
         this.color = var2.a(var3, "color", Integer.valueOf(this.color)).intValue();
         if(com.corrodinggames.rts.gameFramework.l.at() && this.color != 0 && this.color != -1) {
            this.cachedLightingColorFilter = new LightingColorFilter(this.color, 0);
         }

         this.teamColorRatio = var2.a(var3, "teamColorRatio", Float.valueOf(this.teamColorRatio)).floatValue();
         if(this.teamColorRatio >= 0.0F && this.teamColorRatio <= 1.0F) {
            this.shadow = var2.a(var3, "shadow", Boolean.valueOf(false)).booleanValue();
            this.drawLayer = 2;
            if(var2.a(var3, "drawUnderUnits", Boolean.valueOf(false)).booleanValue()) {
               this.drawLayer = 1;
            }

            String var5 = var2.b(var3, "drawType", (String)null);
            if(var5 != null && !var5.equals("normal")) {
               if(!var5.equals("displacement")) {
                  throw new bo("Unknown drawType: " + var5);
               }

               this.drawLayer = 3;
            }

            this.fadeInTime = var2.a(var3, "fadeInTime", Float.valueOf(0.0F)).floatValue();
            this.fadeOut = var2.a(var3, "fadeOut", Boolean.valueOf(true)).booleanValue();
            this.delayedStartTimer = var2.b(var3, "delayedStartTimer", Float.valueOf(0.0F)).floatValue();
            this.delayedStartTimerRandom = var2.a(var3, "delayedStartTimerRandom", Float.valueOf(0.0F)).floatValue();
            this.frameIndex = var2.b(var3, "frameIndex", Integer.valueOf(0)).intValue();
            this.frameIndexRandom = var2.b(var3, "frameIndexRandom", Integer.valueOf(0)).intValue();
            String var6 = var2.b(var3, "stripIndex", "0");
            this.stripIndex = var4.bR.a(var6);
            if(this.stripIndex == -1) {
               throw new RuntimeException("Failed to find stripIndex with name:" + var6);
            } else {
               this.attachedToUnit = var2.a(var3, "attachedToUnit", Boolean.valueOf(true)).booleanValue();
               this.liveAfterAttachedDies = var2.a(var3, "liveAfterAttachedDies", Boolean.valueOf(true)).booleanValue();
               this.atmospheric = var2.a(var3, "atmospheric", Boolean.valueOf(false)).booleanValue();
               this.physics = var2.a(var3, "physics", Boolean.valueOf(false)).booleanValue();
               this.physicsGravity = var2.a(var3, "physicsGravity", Float.valueOf(1.0F)).floatValue();
               String var7 = var2.b(var3, "priority", (String)null);
               if(var7 != null) {
                  try {
                     this.priority = com.corrodinggames.rts.gameFramework.d.h.valueOf(var7);
                  } catch (IllegalArgumentException var11) {
                     throw new RuntimeException("Unknown priority:" + var7);
                  }
               }

               int var8 = var2.b(var3, "total_frames", Integer.valueOf(1)).intValue();
               if(var8 < 1) {
                  throw new bo("TOTAL_FRAMES cannot be: " + var8 + " (must be 1 or more)");
               } else {
                  com.corrodinggames.rts.gameFramework.m.e var9 = var1.a(var2, var3, "image");
                  if(var9 != null) {
                     this.imageStrip = new com.corrodinggames.rts.gameFramework.d.g();
                     this.imageStrip.i = var9;
                     this.imageStrip.b = this.imageStrip.i.m() / var8;
                     this.imageStrip.c = this.imageStrip.i.l();
                     this.imageStrip.b = var2.b(var3, "frame_width", Integer.valueOf(this.imageStrip.b)).intValue();
                     this.imageStrip.c = var2.b(var3, "frame_height", Integer.valueOf(this.imageStrip.c)).intValue();
                     if(var8 == 1 && this.imageStrip.b >= this.imageStrip.i.m()) {
                        this.imageStrip.k = true;
                     } else if(this.imageStrip.c < this.imageStrip.i.l()) {
                        this.imageStrip.h = this.imageStrip.i.m() / this.imageStrip.b;
                        if(this.imageStrip.h < 1) {
                           this.imageStrip.h = 1;
                        }
                     }

                     this.imageStrip.d = 0;
                     this.imageStrip.e = 0;
                     this.imageStrip.f = this.imageStrip.b;
                     this.imageStrip.g = this.imageStrip.c;
                     String var10 = var2.b(var3, "imageShadow", (String)null);
                     if(var10 != null) {
                        this.imageStrip.j = ag.a(var1.F, var10, var1.ab, var1, var3, "imageShadow");
                        this.shadow = true;
                     }

                     if(this.shadow && this.imageStrip.j == null) {
                        throw new bo("imageShadow is required if image and shadow:true is used");
                     }
                  }

                  this.animateFrameStart = var2.b(var3, "animateFrameStart", Integer.valueOf(0)).intValue();
                  this.animateFrameStartRandomAdd = var2.b(var3, "animateFrameStartRandomAdd", Integer.valueOf(0)).intValue();
                  this.animateFrameEnd = var2.b(var3, "animateFrameEnd", Integer.valueOf(0)).intValue();
                  this.animateFramePingPong = var2.a(var3, "animateFramePingPong", Boolean.valueOf(false)).booleanValue();
                  this.animateFrameLooping = var2.a(var3, "animateFrameLooping", Boolean.valueOf(false)).booleanValue();
                  this.animateFrameSpeed = var2.b(var3, "animateFrameSpeed", Float.valueOf(0.5F)).floatValue();
                  this.animateFrameSpeedRandom = var2.b(var3, "animateFrameSpeedRandom", Float.valueOf(0.0F)).floatValue();
                  if(var9 != null && (this.imageStrip.b >= this.imageStrip.i.m() || var8 != 1) && this.animateFrameEnd > var8) {
                     throw new bo("animateFrameEnd:" + this.animateFrameEnd + " cannot be larger than TOTAL_FRAMES: " + var8 + " (when using custom image)");
                  } else {
                     this.alsoEmitEffects = var1.c(var2.b(var3, "alsoEmitEffects", (String)null));
                     this.alsoEmitEffectsOnDeath = var1.c(var2.b(var3, "alsoEmitEffectsOnDeath", (String)null));
                     this.trailEffect = var1.c(var2.b(var3, "trailEffect", (String)null));
                     this.trailEffectRate = var2.b(var3, "trailEffectRate", Float.valueOf(6.0F)).floatValue();
                     this.ifSpawnFailsEmitEffects = var1.c(var2.b(var3, "ifSpawnFailsEmitEffects", (String)null));
                     this.alsoPlaySound = bl.a(var1, var2.b(var3, "alsoPlaySound", (String)null), (bl)null);
                  }
               }
            }
         } else {
            throw new RuntimeException(var3 + " teamColorRatio should be between 0-1 got:" + this.teamColorRatio);
         }
      }
   }

}
