package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import java.util.ArrayList;

public class ba {

   int a;
   String b;
   boolean c;
   public float d;
   public float e;
   public float f = 2.0F;
   public float g = 0.0F;
   public boolean h = true;
   public float i;
   public float j;
   public float k;
   public boolean l;
   public float m = 1.0F;
   public boolean n = true;
   public boolean o = false;
   public boolean p;
   public LogicBoolean q;
   public float r = 1.0F;
   public float s = 1.0F;
   public float t = 0.0F;
   public float u = 3.0F;
   public float v = 0.3F;
   public float w;
   public com.corrodinggames.rts.gameFramework.m.e x;
   public com.corrodinggames.rts.gameFramework.m.e[] y;
   public boolean z;
   public float A;
   public com.corrodinggames.rts.gameFramework.m.e B;
   public com.corrodinggames.rts.gameFramework.m.e[] C;
   public com.corrodinggames.rts.gameFramework.m.e D;
   public boolean E;
   public boolean F = true;
   public boolean G = true;
   public boolean H;
   public boolean I = true;
   public boolean J = true;
   public float K = 7.0F;
   public int L = 3;
   public boolean M = true;
   public float N = 16.0F;
   public float O = 50.0F;
   public boolean P = false;
   public boolean Q = false;
   public float R = 0.0F;
   public int[] S;
   public float T;


   public void a(ba var1) {
      this.d = var1.d;
      this.e = var1.e;
      this.i = var1.i;
      this.j = var1.j;
      this.k = var1.k;
      this.f = var1.f;
      this.g = var1.g;
      this.h = var1.h;
      this.l = var1.l;
      this.m = var1.m;
      this.n = var1.n;
      this.o = var1.o;
      this.t = var1.t;
      this.p = var1.p;
      this.r = var1.r;
      this.s = var1.s;
      this.u = var1.u;
      this.w = var1.w;
      this.x = var1.x;
      this.y = var1.y;
      this.A = var1.A;
      this.z = var1.z;
      this.B = var1.B;
      this.C = var1.C;
      this.D = var1.D;
      this.E = var1.E;
      this.F = var1.F;
      this.G = var1.G;
      this.v = var1.v;
      this.H = var1.H;
      this.I = var1.I;
      this.J = var1.J;
      this.K = var1.K;
      this.L = var1.L;
      this.M = var1.M;
      this.N = var1.N;
      this.O = var1.O;
      this.P = var1.P;
      this.Q = var1.Q;
      this.R = var1.R;
      this.T = var1.T;
   }

   public static void a(ba var0, l var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3, boolean var4, ArrayList var5) {
      if(!var4) {
         var0.l = true;
         var0.J = false;
      }

      int var6 = var2.b(var3, "copyFrom", Integer.valueOf(0)).intValue();
      if(var6 != 0) {
         if(var6 - 1 >= var5.size()) {
            throw new RuntimeException("copyFrom: Leg/Arm copy target not loaded yet: " + var6);
         }

         ba var7 = (ba)var5.get(var6 - 1);
         var0.a(var7);
      }

      var0.d = var2.i(var3, "x");
      var0.e = var2.i(var3, "y");
      var0.b = var3.replace("_", "");
      var0.c = var4;
      Float var28 = var2.a(var3, "attach_x", (Float)null);
      if(var28 != null) {
         var0.j = var28.floatValue();
      }

      Float var8 = var2.a(var3, "attach_y", (Float)null);
      if(var8 != null) {
         var0.k = var8.floatValue();
      }

      var0.f = var2.a(var3, "liftingHeightOffset", Float.valueOf(var0.f)).floatValue();
      var0.g = var2.a(var3, "targetHeight", Float.valueOf(var0.g)).floatValue();
      var0.h = var2.a(var3, "targetHeightRelative", Boolean.valueOf(var0.h)).booleanValue();
      var0.i = var2.a(var3, "endDirOffset", Float.valueOf(0.0F)).floatValue();
      var0.l = var2.a(var3, "lockMovement", Boolean.valueOf(var0.l)).booleanValue();
      var0.m = var2.a(var3, "estimatingPositionMultiplier", Float.valueOf(var0.m)).floatValue();
      var0.q = var2.a(var1, var3, "hidden", var0.q);
      var0.p = var0.q == LogicBoolean.trueBoolean;
      var0.r = var2.a(var3, "alpha", Float.valueOf(var0.r)).floatValue();
      Float var9 = var2.a(var3, "moveSpeed", (Float)null);
      if(var9 != null) {
         var0.s = var9.floatValue();
      }

      var0.t = var2.b(var3, "moveWarmUp", Float.valueOf(var0.t)).floatValue();
      var0.u = var2.a(var3, "rotateSpeed", Float.valueOf(var0.u)).floatValue();
      Float var10 = var2.a(var3, "resetAngle", (Float)null);
      if(var10 != null) {
         var0.w = var10.floatValue();
      }

      boolean var11 = var2.a(var3, "image_end_teamColors", Boolean.valueOf(false)).booleanValue();
      com.corrodinggames.rts.gameFramework.m.e var12 = var1.a(var2, var3, "image_foot");
      if(var12 != null) {
         var0.B = var12;
         if(var11) {
            var0.C = var1.a(var0.B, var1.ac);
         } else {
            var0.C = null;
         }
      }

      com.corrodinggames.rts.gameFramework.m.e var13 = var1.a(var2, var3, "image_end");
      if(var13 != null) {
         var0.B = var13;
         if(var11) {
            var0.C = var1.a(var0.B, var1.ac);
         } else {
            var0.C = null;
         }
      }

      com.corrodinggames.rts.gameFramework.m.e var14 = var1.a(var2, var3, "image_foot_shadow");
      if(var14 != null) {
         var0.D = var14;
      }

      com.corrodinggames.rts.gameFramework.m.e var15 = var1.a(var2, var3, "image_end_shadow");
      if(var15 != null) {
         var0.D = var15;
      }

      com.corrodinggames.rts.gameFramework.m.e var16 = var1.a(var2, var3, "image_leg");
      if(var16 != null) {
         var0.x = var16;
      }

      com.corrodinggames.rts.gameFramework.m.e var17 = var1.a(var2, var3, "image_middle");
      if(var17 != null) {
         var0.x = var17;
      }

      boolean var18 = var2.a(var3, "image_middle_teamColors", Boolean.valueOf(false)).booleanValue();
      if(var0.x != null && var18) {
         var0.y = var1.a(var0.x, var1.ac);
      } else {
         var0.y = null;
      }

      Float var19 = var2.a(var3, "heightSpeed", (Float)null);
      if(var19 != null) {
         var0.v = var19.floatValue();
      }

      Boolean var20 = var2.a(var3, "draw_foot_on_top", (Boolean)null);
      if(var20 != null) {
         var0.H = var20.booleanValue();
      }

      Boolean var21 = var2.a(var3, "dust_effect", (Boolean)null);
      if(var21 != null) {
         var0.I = var21.booleanValue();
      }

      Boolean var22 = var2.a(var3, "drawLegWhenZoomedOut", (Boolean)null);
      if(var22 != null) {
         var0.F = var22.booleanValue();
         var0.E = true;
      }

      Boolean var23 = var2.a(var3, "drawFootWhenZoomedOut", (Boolean)null);
      if(var23 != null) {
         var0.G = var23.booleanValue();
         var0.E = true;
      }

      if(!var0.E && !var0.l && !var0.P) {
         if(var1.cW < 30) {
            var0.F = false;
         }

         if(var1.cW < 20) {
            var0.G = false;
         }
      }

      Boolean var24 = var2.a(var3, "explodeOnDeath", (Boolean)null);
      if(var24 != null) {
         var0.J = var24.booleanValue();
      }

      Float var25 = var2.a(var3, "holdDisMin", (Float)null);
      if(var25 != null) {
         var0.K = var25.floatValue();
      }

      var0.L = var2.b(var3, "holdDisMin_maxMovingLegs", Integer.valueOf(var0.L)).intValue();
      var0.M = var2.a(var3, "hold_moveOnlyIfFurthest", Boolean.valueOf(var0.M)).booleanValue();
      var0.n = var2.a(var3, "holdDisMin_checkNeighbours", Boolean.valueOf(var0.n)).booleanValue();
      var0.o = var2.a(var3, "favourOppositeSideNeighbours", Boolean.valueOf(var0.o)).booleanValue();
      Float var26 = var2.a(var3, "holdDisMax", (Float)null);
      if(var26 != null) {
         var0.N = var26.floatValue();
      }

      Float var27 = var2.a(var3, "hardLimit", (Float)null);
      if(var27 != null) {
         var0.O = var27.floatValue();
      }

      var0.P = var2.a(var3, "drawOverBody", Boolean.valueOf(var0.P)).booleanValue();
      if(var0.P) {
         var1.ay = true;
      }

      var0.Q = var2.a(var3, "drawUnderAllUnits", Boolean.valueOf(var0.Q)).booleanValue();
      if(var0.Q) {
         var1.az = true;
      }

      if(var0.Q && var0.P) {
         throw new RuntimeException("Both drawUnderAllUnits and drawOverBody can not be set true at the same time in leg/arm");
      } else {
         var0.R = var2.a(var3, "drawDirOffset", Float.valueOf(var0.R)).floatValue();
         var0.T = var2.a(var3, "spinRate", Float.valueOf(var0.T)).floatValue();
      }
   }
}
