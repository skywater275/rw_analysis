package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

public class f {

   static boolean a(y var0) {
      boolean var1 = false;
      if(var0.aq()) {
         var1 = true;
      }

      if(!var1) {
         au var2 = var0.ar();
         if(var2 != null && var2.d() == av.g) {
            var1 = true;
         }
      }

      return var1;
   }

   static boolean b(y var0) {
      boolean var1 = false;
      if(var0.aq()) {
         var1 = true;
      }

      return var1;
   }

   public static Object a(AbstractList var0) {
      int var1 = var0.size();
      return var1 == 0?null:var0.get(com.corrodinggames.rts.gameFramework.f.a(0, var1 - 1));
   }

   public static boolean a(y var0, com.corrodinggames.rts.game.units.custom.g var1) {
      as var2 = var0.r();
      if(var2 instanceof com.corrodinggames.rts.game.units.custom.l) {
         com.corrodinggames.rts.game.units.custom.h var3 = ((com.corrodinggames.rts.game.units.custom.l)var2).fv;
         if(com.corrodinggames.rts.game.units.custom.g.a(var1, var3)) {
            return true;
         }
      }

      return false;
   }

   public static s a(a var0, y var1, com.corrodinggames.rts.game.units.custom.a.e var2) {
      ArrayList var3 = var1.N();
      ArrayList var4 = var0.ap();
      Iterator var5 = var3.iterator();

      while(var5.hasNext()) {
         s var6 = (s)var5.next();
         if(var6.v(var1) == var2) {
            var4.add(var6);
         }
      }

      if(var4.size() > 0) {
         return (s)a((AbstractList)var4);
      } else {
         return null;
      }
   }
}
