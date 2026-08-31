package com.corrodinggames.rts.game;

import android.graphics.Color;
import com.corrodinggames.rts.game.h;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bi;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.gameFramework.j.as;
import java.io.IOException;
import java.util.Iterator;

public class g {

   public static final g a = new g();
   public int b;
   public int c;
   public boolean d;
   public boolean e;
   public boolean f;
   public boolean g;
   public boolean h;
   public int i = 35;
   public float j = -1.0F;
   public boolean k = false;
   public boolean l = false;
   public boolean m = false;
   public boolean n = false;
   public float o = 1.0F;
   public boolean p = false;
   public boolean q = false;
   public boolean r = false;
   public boolean s = false;
   public boolean t = false;
   public float u;
   public float v;
   public float w = 5.0F;
   public short x = -1;
   public short y;
   public short z = -1;
   public boolean A;
   public com.corrodinggames.rts.gameFramework.m.e B;
   public com.corrodinggames.rts.gameFramework.m.e C;
   public float D;
   public float E;
   public float F;
   public float G;
   public float H;
   public boolean I = false;
   public boolean J = false;
   public float K = -1.0F;
   public boolean L = false;
   public boolean M = false;
   public boolean N = false;
   public float O = -1.0F;
   public float P = -1.0F;
   public float Q;
   public float R;
   public float S;
   public boolean T;
   public boolean U = false;
   public boolean V = false;
   public boolean W = false;
   public boolean X = false;
   public com.corrodinggames.rts.gameFramework.m.e Y;
   public com.corrodinggames.rts.gameFramework.m.e Z;
   public boolean aa;
   public com.corrodinggames.rts.gameFramework.m.e ab;
   public boolean ac;
   public float ad;
   public boolean ae = false;
   public boolean af = false;
   public float ag = 3.0F;
   public z ah;
   public z ai;
   public bi aj;
   public bi ak;
   public bi al;
   public float am;
   public float an = 5.0F;
   public int ao = -1;
   public float ap = 0.5F;
   public boolean aq;
   public boolean ar = false;
   public float as = -1.0F;
   public float at = -1.0F;
   public float au = -1.0F;
   public float av = 0.1F;
   public boolean aw = false;
   public float ax = 120.0F;
   public float ay = 15.0F;
   public boolean az;
   public float aA = 5.0F;
   public float aB = 120.0F;
   public float aC = 15.0F;
   public com.corrodinggames.rts.game.units.custom.h aD;
   public int aE = Color.a(255, 255, 255, 255);
   public float aF = 1.0F;
   public float aG = 0.0F;
   public float aH = 1.0F;
   public boolean aI;
   public boolean aJ = true;
   public float aK;
   public float aL;
   public float aM;
   public boolean aN;
   public boolean aO;
   public float aP;
   public float aQ;
   public float aR = 1.0F;
   public float aS = 1.0F;
   public float aT = 1.0F;
   public float aU = 1.0F;
   public float aV;
   public float aW = -1.0F;
   public z aX;
   public z aY;
   public bp aZ;
   public int ba;
   public boolean bb;
   public boolean bc;
   public com.corrodinggames.rts.game.units.custom.h bd;
   public com.corrodinggames.rts.gameFramework.utility.m be = null;
   public com.corrodinggames.rts.gameFramework.utility.m bf = null;
   public com.corrodinggames.rts.gameFramework.utility.m bg = null;


   public z a(am var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = this.bg;
      if(var2 != null && var2.a > 0) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            h var4 = (h)var3.next();
            if(var4.a(var1) && var4.g != null) {
               return var4.g;
            }
         }
      }

      return null;
   }

   public float a(am var1, float var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.utility.m var4;
      if(!var3) {
         var4 = this.be;
      } else {
         var4 = this.bf;
      }

      if(var4 != null && var4.a > 0) {
         Iterator var5 = var4.iterator();

         while(var5.hasNext()) {
            h var6 = (h)var5.next();
            if(var6.a(var1)) {
               float var7;
               if(!var3) {
                  if(var6.e != null) {
                     var6.e.h(var1);
                  }

                  var7 = var6.c;
               } else {
                  if(var6.f != null) {
                     var6.f.h(var1);
                  }

                  var7 = var6.d;
               }

               var2 *= var7;
            }
         }
      }

      return var2;
   }

   public static void a(g var0, as var1) {
      if(var0 == a) {
         var1.c(0);
      } else if(var0 instanceof bh) {
         var1.c(1);
         bh.a((bh)var0, var1);
      } else {
         com.corrodinggames.rts.gameFramework.l.g("writeOutLink: Unhandled projectile type");
         var1.c(0);
      }
   }

   public static g a(com.corrodinggames.rts.gameFramework.j.k var0) {
      byte var1 = var0.d();
      if(var1 == 0) {
         return a;
      } else if(var1 == 1) {
         g var2 = bh.b(var0);
         return var2 == null?a:var2;
      } else {
         throw new IOException("Unknown projectile type:" + var1);
      }
   }

}
