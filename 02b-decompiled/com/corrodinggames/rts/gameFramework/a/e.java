package com.corrodinggames.rts.gameFramework.a;

import android.content.Context;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.a.a;
import com.corrodinggames.rts.gameFramework.a.f;
import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.util.ArrayList;

public class e {

   ArrayList a = new ArrayList();
   public boolean b;
   public static h c = new a();
   public static i d;
   public static i e;
   public static i f;
   public static i g;
   public static i h;
   public static i i;
   public static i j;
   public static i k;
   public static i l;
   public static i m;
   public static i n;
   public static i o;
   public static i p;
   public static i q;
   public static i r;
   public static i s;
   public static i t;
   public static i u;
   public static i v;
   public static i w;
   public static i x;
   public static i y;
   public static i z;
   public static i A;
   public static i B;
   public static i C;
   public static i D;
   public static i E;
   public static i F;


   public boolean a(i var1, float var2) {
      if(this.a.contains(var1)) {
         return false;
      } else {
         this.a.add(var1);
         return true;
      }
   }

   public boolean a() {
      l var1 = l.B();
      return this.a(var1.bQ.masterVolume * var1.bQ.gameVolume);
   }

   public boolean a(float var1) {
      l var2 = l.B();
      return var1 < 0.01F?false:(this.b?false:var2.bQ.enableSounds);
   }

   public static void b() {}

   public void a(Context var1) {
      c.a(var1);
      e = c.a(R$raw.attack);
      e.d = 0.2F;
      d = c.a(R$raw.attack2);
      f = c.a(R$raw.move);
      g = c.a(R$raw.click);
      h = c.a(R$raw.click_add);
      i = c.a(R$raw.click_remove);
      j = c.a(R$raw.warning);
      k = c.a(R$raw.message);
      m = c.a(R$raw.missile_fire);
      n = c.a(R$raw.missile_hit);
      o = c.a(R$raw.unit_explode);
      p = c.a(R$raw.buiding_explode);
      q = c.a(R$raw.tank_firing);
      r = c.a(R$raw.cannon_firing);
      s = c.a(R$raw.gun_fire);
      x = c.a(R$raw.lighting_burst);
      y = c.a(R$raw.plasma_fire);
      z = c.a(R$raw.plasma_fire2);
      t = c.a(R$raw.firing3);
      u = c.a(R$raw.firing4);
      v = c.a(R$raw.large_gun_fire1);
      w = c.a(R$raw.large_gun_fire2);
      A = c.a(R$raw.bug_die);
      B = c.a(R$raw.bug_attack);
      l = c.a(R$raw.interface_error);
      C = c.a(R$raw.nuke_explode);
      D = c.a(R$raw.nuke_launch);
      E = c.a(R$raw.laser_deflect);
      F = c.a(R$raw.laser_deflect2);
      c.a();
   }

   public i a(String var1) {
      i var2 = (i)c.h.get(var1);
      if(var2 == null) {
         throw new RuntimeException("Could not find sound:" + var1);
      } else {
         return var2;
      }
   }

   public void b(i var1, float var2) {
      l var3 = l.B();
      var2 *= var3.bQ.masterVolume * var3.bQ.interfaceVolume;
      var2 *= var1.d;
      if(this.a(var2)) {
         if((double)var2 >= 0.01D) {
            if(this.a(var1, var2)) {
               if(var3.aq) {
                  var2 /= 20.0F;
               }

               var1.a(var2, var2, 1, 0, 1.0F);
            }
         }
      }
   }

   public void c(i var1, float var2) {
      l var3 = l.B();
      var2 *= var3.bQ.masterVolume * var3.bQ.gameVolume;
      var2 *= var1.d;
      if(this.a(var2)) {
         if(var3.aq) {
            var2 /= 20.0F;
         }

         if(this.a(var1, var2)) {
            var1.a(var2, var2, 1, 0, 1.0F);
         }
      }
   }

   public void a(i var1, float var2, float var3, float var4) {
      this.a(var1, var2, 1.0F, var3, var4);
   }

   public void a(i var1, float var2, float var3, float var4, float var5) {
      if(this.a()) {
         l var6 = l.B();
         if(var6.aq) {
            var2 /= 20.0F;
         }

         if(var6.cb.j() && (double)var6.bt > 1.5D) {
            var2 /= var6.bt;
         }

         int var7 = (int)(var6.cw + var6.cI);
         int var8 = (int)(var6.cx + var6.cJ);
         float var9 = com.corrodinggames.rts.gameFramework.f.a((float)var7, (float)var8, var4, var5);
         float var10 = var6.cI * 1.72F;
         if((double)var6.cX < 0.5D) {
            var2 *= 4.0F;
            var2 *= var6.cX * var6.cX;
         }

         if(var2 > 1.0F || var1.f || var9 <= var10 * var10) {
            float var11 = (float)Math.sqrt((double)var9);
            float var12 = 1.0F;
            if(var11 > var6.cI) {
               var12 = 1.0F - (var11 - var6.cI) / var6.cI;
            }

            float var13 = var12 * var2;
            if((double)var13 > 0.05D || var1.f) {
               if(var13 > 1.0F) {
                  var13 = 1.0F;
               }

               var13 *= var6.bQ.masterVolume * var6.bQ.gameVolume;
               var13 *= var1.d;
               if(this.a(var1, var13)) {
                  var1.a(var13, var13, 1, 0, var3);
               }
            }
         }
      }
   }

   public i a(String var1, j var2, boolean var3) {
      try {
         return c.a(var1, var2, var3);
      } catch (OutOfMemoryError var5) {
         l.a(u.f, (Throwable)var5);
         return f.b();
      }
   }

   public i b(String var1) {
      return f.a(var1);
   }

   public void b(float var1) {
      this.a.clear();
   }

}
