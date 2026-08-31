package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.a;
import com.corrodinggames.rts.game.units.custom.b;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.d;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import java.util.Collections;
import java.util.Iterator;

public final class c {

   d a;
   int b;
   String c;
   float d;
   public a[] e;
   private com.corrodinggames.rts.gameFramework.utility.m g = new com.corrodinggames.rts.gameFramework.utility.m();
   public final boolean f = false;


   public b a() {
      b var1 = null;
      if(this.g.size() > 0) {
         var1 = (b)((b)this.g.get(this.g.size() - 1));
         if(var1.h) {
            var1 = null;
         }
      }

      return var1;
   }

   public void a(j var1, float var2, float var3, boolean var4) {
      if(!var4) {
         if(var3 > var2) {
            a[] var5 = this.e;
            int var6 = var5.length;

            int var7;
            for(var7 = -1; var7 + 1 < var6 && var2 > var5[var7 + 1].a; ++var7) {
               ;
            }

            int var8;
            for(var8 = var7; var8 + 1 < var6 && var3 > var5[var8 + 1].a; ++var8) {
               ;
            }

            if(var8 > var7) {
               for(int var9 = var7 + 1; var9 <= var8; ++var9) {
                  b var10 = (b)var5[var9];
                  var10.a(var1);
               }

            }
         }
      }
   }

   public void b() {
      if(this.a == d.i) {
         b var1 = this.a();
         if(var1 != null) {
            var1.finalize();
         }
      }

   }

   public void a(l var1, float var2, String var3, String var4) {
      if(this.a == d.i) {
         b var8 = this.a();
         if(var8 == null) {
            var8 = new b(var2, 0.0F);
            this.g.add(var8);
         }

         var8.a(var1, var3, var4);
      } else {
         float var5;
         try {
            var5 = Float.parseFloat(var4);
         } catch (NumberFormatException var7) {
            throw new bo("Failed to parse float:" + var4);
         }

         this.a(var2, var5);
      }
   }

   public void a(float var1, float var2) {
      if(this.a == d.i) {
         throw new bo("Adding key frame value to event set");
      } else {
         a var3;
         if(this.g.size() == 0 && var1 > 0.0F && var2 != 0.0F) {
            var3 = new a(0.0F, 0.0F);
            this.g.add(var3);
         }

         var3 = new a(var1, var2);
         this.g.add(var3);
      }
   }

   public void a(float var1) {
      a var3;
      for(Iterator var2 = this.g.iterator(); var2.hasNext(); var3.a *= var1) {
         var3 = (a)var2.next();
      }

   }

   public void c() {
      Collections.sort(this.g);
      a var1 = null;

      a var3;
      for(Iterator var2 = this.g.iterator(); var2.hasNext(); this.d = var3.a) {
         var3 = (a)var2.next();
         if(var1 != null) {
            var3.c = 1.0F / (var3.a - var1.a);
            var3.d = var3.b - var1.b;
         }

         var1 = var3;
      }

      this.e = (a[])this.g.toArray(new a[0]);
      this.g = null;
   }

   public float b(float var1) {
      a[] var2 = this.e;
      int var3 = var2.length;
      if(var3 != 1 && var1 > var2[0].a) {
         if(var1 >= this.d) {
            return var2[var3 - 1].b;
         } else {
            int var4 = 1;

            do {
               if(var1 <= var2[var4].a) {
                  a var5 = var2[var4 - 1];
                  a var6 = var2[var4];
                  float var7 = (var1 - var5.a) * var6.c;
                  float var8 = var5.b + var6.d * var7;
                  return var8;
               }

               ++var4;
            } while(var4 < var3);

            return var2[var3 - 1].b;
         }
      } else {
         return var2[0].b;
      }
   }
}
