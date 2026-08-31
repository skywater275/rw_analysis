package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.f.ao;
import com.corrodinggames.rts.gameFramework.f.x;

public class an {

   static com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();
   static final x b = new x();


   public static ao a(long var0) {
      Object[] var2 = a.a();

      for(int var3 = a.a - 1; var3 >= 0; --var3) {
         ao var4 = (ao)var2[var3];
         if(var4.a == var0) {
            return var4;
         }
      }

      return null;
   }

   public static ao a(com.corrodinggames.rts.game.units.am var0) {
      long var1 = var0.eh;
      ao var3 = a(var1);
      if(var3 == null) {
         var3 = new ao();
         var3.a = var0.eh;
         var3.b = var0.cE;
         var3.c = var0.cF;
         var3.d = com.corrodinggames.rts.gameFramework.l.B().bX.X;
         a.add(var3);
      }

      return var3;
   }

   public static void a(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.custom.d.b var1) {
      if(com.corrodinggames.rts.gameFramework.l.B().bX.B) {
         ao var2 = a(var0);
         var2.b += var1.f;
         var2.c = var1.c(var2.c);
         if(!var1.k.c()) {
            var2.e = com.corrodinggames.rts.game.units.custom.e.f.b(var2.e, var1.k);
         }

      }
   }

   public static void b(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.custom.d.b var1) {
      if(com.corrodinggames.rts.gameFramework.l.B().bX.B) {
         ao var2 = a(var0);
         var2.b -= var1.f;
         var2.c = var1.c(var2.c);
         if(!var1.k.c()) {
            var2.e = com.corrodinggames.rts.game.units.custom.e.f.a(var2.e, var1.k);
         }

         if(a.a > 0) {
            ;
         }

      }
   }

   public static boolean c(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.custom.d.b var1) {
      ao var2 = a(var0.eh);
      if(var2 != null) {
         b.bX = var0.bX;
         b.cE = var2.b;
         b.cF = var2.c;
         com.corrodinggames.rts.game.units.custom.e.f var3 = b.df();
         b.a(var2.e);
         boolean var4 = var1.b((com.corrodinggames.rts.game.units.am)b);
         b.a(var3);
         return var4;
      } else {
         return var1.b(var0);
      }
   }

   public static boolean a(LogicBoolean var0, com.corrodinggames.rts.game.units.y var1) {
      ao var2 = a(var1.eh);
      if(var2 != null) {
         int var3 = var1.cE;
         int var4 = var1.cF;
         var1.cE = var2.b;
         var1.cF = var2.c;
         boolean var5 = var0.read(var1);
         var1.cE = var3;
         var1.cF = var4;
         return var5;
      } else {
         return var0.read(var1);
      }
   }

   public static void a() {
      if(a.a > 0) {
         com.corrodinggames.rts.gameFramework.l.e("LagHiding: clearing: " + a.a);
      }

      a.clear();
   }

   public static void a(com.corrodinggames.rts.game.units.y var0, com.corrodinggames.rts.game.units.a.s var1) {
      if(a.size() != 0) {
         int var2 = com.corrodinggames.rts.gameFramework.l.B().bX.X;

         for(int var3 = a.size() - 1; var3 >= 0; --var3) {
            ao var4 = (ao)a.get(var3);
            if(var4.a == var0.eh) {
               a.remove(var3);
               break;
            }

            if(var4.d < var2 + 80) {
               a.remove(var3);
               break;
            }
         }

      }
   }

}
