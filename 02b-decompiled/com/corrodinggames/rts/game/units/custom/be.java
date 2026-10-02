package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bf;
import com.corrodinggames.rts.game.units.custom.bg;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.l;
import java.util.Iterator;

public final class be {

   com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();
   com.corrodinggames.rts.gameFramework.utility.m b = new com.corrodinggames.rts.gameFramework.utility.m();
   boolean c;
   boolean d;
   public static final bf e = new bf();


   public static be a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1) {
      be var2 = new be();
      var2.b(var0, var1);
      if(var2.b.size() == 0) {
         return null;
      } else {
         Iterator var3 = var2.a.iterator();

         while(var3.hasNext()) {
            g var4 = (g)var3.next();
            if(var4 != null) {
               int var5 = 0;
               bg var6 = null;
               Iterator var7 = var2.b.iterator();

               while(var7.hasNext()) {
                  bg var8 = (bg)var7.next();
                  if(var8.b == var4) {
                     ++var5;
                     var6 = var8;
                  }
               }

               if(var5 == 1) {
                  var0.r("[placementRule_" + var6.a + "]anyRuleInGroup: No other rule with this same group name found");
               }
            }
         }

         return var2;
      }
   }

   public void b(l var1, com.corrodinggames.rts.gameFramework.utility.ab var2) {
      Iterator var3 = var2.e("placementRule_").iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         String var5 = var4.substring("placementRule_".length());
         bg var6 = new bg();
         var6.a = var5;
         var6.a(var1, var2, var4);
         if(var6.a()) {
            if(!this.a.contains(var6.b)) {
               this.a.add(var6.b);
            }

            if(var6.n) {
               if(!var6.p) {
                  this.c = true;
               } else {
                  this.d = true;
               }
            }

            this.b.add(var6);
         }
      }

   }

   public String a(com.corrodinggames.rts.game.units.y var1, float var2, float var3) {
      return !this.c?null:this.b(var1, var2, var3);
   }

   public String a(com.corrodinggames.rts.game.units.y var1, int var2, int var3) {
      if(!this.d) {
         return null;
      } else {
         com.corrodinggames.rts.game.b.b var4 = com.corrodinggames.rts.gameFramework.l.B().bL;
         var4.b(var2, var3);
         return this.b(var1, (float)var4.T, (float)var4.U);
      }
   }

   public String b(com.corrodinggames.rts.game.units.y var1, float var2, float var3) {
      Iterator var4 = this.a.iterator();

      bg var8;
      boolean var12;
      do {
         if(!var4.hasNext()) {
            return null;
         }

         g var5 = (g)var4.next();
         boolean var6 = false;
         boolean var7 = false;
         var8 = null;
         Iterator var9 = this.b.iterator();

         while(var9.hasNext()) {
            bg var10 = (bg)var9.next();
            if(var10.b == var5 && var10.n) {
               boolean var11 = a(var1, var10, var2, var3);
               if(!var11) {
                  if(var8 == null) {
                     var8 = var10;
                  }

                  var7 = true;
               } else {
                  var6 = true;
               }
            }
         }

         if(var5 == null) {
            var12 = !var7;
         } else {
            var12 = var6;
         }
      } while(var12 || var8 == null);

      if(var8.o != null) {
         return var8.o.b();
      } else {
         return "{0}";
      }
   }

   private static boolean a(com.corrodinggames.rts.game.units.y var0, bg var1, float var2, float var3) {
      e.a = var2 + var1.g;
      e.b = var3 + var1.h;
      e.c = var1;
      e.d = 0;
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      var4.cc.a(e.a, e.b, var1.e, var0, 0.0F, e);
      return e.d >= var1.k && e.d <= var1.l;
   }

}
