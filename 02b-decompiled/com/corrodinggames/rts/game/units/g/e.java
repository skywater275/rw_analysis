package com.corrodinggames.rts.game.units.g;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.g.a;
import com.corrodinggames.rts.game.units.g.b;
import com.corrodinggames.rts.game.units.g.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.m;

public class e extends a {

   int b;
   com.corrodinggames.rts.game.units.a.c c;


   public e() {
      this.c = com.corrodinggames.rts.game.units.a.c.a;
   }

   public e(int var1, com.corrodinggames.rts.game.units.a.c var2) {
      super(var1);
      this.c = com.corrodinggames.rts.game.units.a.c.a;
      this.c = var2;
      l var3 = l.B();
      int var4 = var3.by;
      this.b = var4;
   }

   public b b() {
      return b.b;
   }

   public boolean a(com.corrodinggames.rts.game.units.a.c var1) {
      return this.c == com.corrodinggames.rts.game.units.a.c.a?true:this.c == var1;
   }

   public float c() {
      int var1 = this.a - this.b;
      if(var1 <= 0) {
         return 1.0F;
      } else {
         l var2 = l.B();
         int var3 = var2.by;
         int var4 = this.a - var3;
         return (float)var4 / (float)var1;
      }
   }

   public static void a(y var0, com.corrodinggames.rts.game.units.a.c var1, int var2) {
      l var3 = l.B();
      int var4 = var3.by + var2;
      e var5 = new e(var4, var1);
      c.a(var0, (a)var5);
   }

   public static int a(am var0, com.corrodinggames.rts.game.units.a.c var1) {
      if(!(var0 instanceof y)) {
         return 0;
      } else {
         y var2 = (y)var0;
         m var3 = var2.bp;
         if(var3 == null) {
            return 0;
         } else {
            e var4 = b(var0, var1);
            if(var4 == null) {
               return 0;
            } else {
               int var5 = var4.d();
               return var5;
            }
         }
      }
   }

   public int d() {
      l var1 = l.B();
      int var2 = var1.by;
      int var3 = this.a - var2;
      return var3;
   }

   public static e b(am var0, com.corrodinggames.rts.game.units.a.c var1) {
      if(!(var0 instanceof y)) {
         return null;
      } else {
         y var2 = (y)var0;
         m var3 = var2.bp;
         if(var3 == null) {
            return null;
         } else {
            l var4 = l.B();
            int var5 = var4.by;
            e var6 = null;
            Object[] var7 = var3.a();

            for(int var8 = var3.a - 1; var8 >= 0; --var8) {
               a var9 = (a)var7[var8];
               if(var9 instanceof e) {
                  e var10 = (e)var9;
                  if(var10.a(var1) && var10.a > var5) {
                     var5 = var10.a;
                     var6 = var10;
                  }
               }
            }

            if(var6 == null) {
               return null;
            } else {
               return var6;
            }
         }
      }
   }

   public void a(y var1, as var2) {
      com.corrodinggames.rts.game.units.a.c.a(var2, this.c);
      var2.a(this.b);
      super.a(var1, var2);
   }

   public void a(y var1, k var2) {
      this.c = com.corrodinggames.rts.game.units.a.c.a(var2);
      if(this.c == null) {
         this.c = com.corrodinggames.rts.game.units.a.c.a;
      }

      this.b = var2.f();
      super.a(var1, var2);
   }

   public static void c(am var0, com.corrodinggames.rts.game.units.a.c var1) {
      if(var0 instanceof y) {
         y var2 = (y)var0;
         m var3 = var2.bp;
         if(var3 != null) {
            l var4 = l.B();
            int var5 = var4.by;
            Object[] var6 = var3.a();

            for(int var7 = var3.a - 1; var7 >= 0; --var7) {
               a var8 = (a)var6[var7];
               if(var8 instanceof e) {
                  e var9 = (e)var8;
                  if(var1 == com.corrodinggames.rts.game.units.a.c.a || var9.a(var1)) {
                     var9.a = var5 - 1;
                  }
               }
            }

         }
      }
   }
}
