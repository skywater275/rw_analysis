package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.f.al$1;
import com.corrodinggames.rts.gameFramework.f.al$2;
import com.corrodinggames.rts.gameFramework.f.al$3;
import com.corrodinggames.rts.gameFramework.f.al$4;
import com.corrodinggames.rts.gameFramework.f.al$5;
import com.corrodinggames.rts.gameFramework.f.al$6;
import java.util.ArrayList;
import java.util.Iterator;

abstract class al {

   static al a = new al$1();
   static al b = new al$2();
   static al c = new al$3();
   static al d = new al$4();
   static al e = new al$5();
   static al f = new al$6();


   public abstract boolean a(com.corrodinggames.rts.game.units.y var1);

   public static void a(ArrayList var0, al var1, al var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.bS.q() != 1) {
         var0.clear();
      }

      com.corrodinggames.rts.game.units.y var4 = var3.bS.t();
      if(var4 != null) {
         if(!var1.a(var4) && (var2 == null || !var2.a(var4))) {
            var0.clear();
         } else if(!var0.contains(var4)) {
            var0.add(var4);
         }
      }

      com.corrodinggames.rts.game.units.y var5 = a(var0, var1);
      if(var5 == null && var2 != null) {
         var5 = a(var0, var2);
      }

      if(var5 == null) {
         var0.clear();
         if(var4 != null) {
            var0.add(var4);
         }

         var5 = a(var0, var1);
         if(var5 == null && var2 != null) {
            var5 = a(var0, var2);
         }
      }

      if(var5 != null) {
         var3.bS.y();
         var3.bS.j(var5);
         var3.b(var5.eo, var5.ep);
         var0.add(var5);
      }

   }

   public static com.corrodinggames.rts.game.units.y a(ArrayList var0, al var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.y var3 = null;
      float var4 = -1.0F;
      Iterator var5 = com.corrodinggames.rts.game.units.am.bE.iterator();

      while(var5.hasNext()) {
         com.corrodinggames.rts.game.units.am var6 = (com.corrodinggames.rts.game.units.am)var5.next();
         if(var6 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var7 = (com.corrodinggames.rts.game.units.y)var6;
            if(var2.bS.m(var7) && var1.a(var7) && !var0.contains(var7)) {
               float var8 = com.corrodinggames.rts.gameFramework.f.a(var2.cy + var2.cI, var2.cz + var2.cJ, var7.eo, var7.ep);
               if(var3 == null || var8 < var4) {
                  var4 = var8;
                  var3 = var7;
               }
            }
         }
      }

      return var3;
   }

}
