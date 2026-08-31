package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.Iterator;

public class k extends com.corrodinggames.rts.game.units.custom.a.a {

   public bp a;
   public bp b;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      bp var7 = bp.a(var0, var1, var2, var3 + "produceUnits");
      if(!var7.b()) {
         k var8 = new k();
         var8.a = var7;
         var4.ac.add(var8);
      }

      bp var10 = bp.a(var0, var1, var2, var3 + "spawnUnits");
      if(!var10.b()) {
         k var9 = new k();
         var9.b = var10;
         var4.ac.add(var9);
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.a != null) {
         com.corrodinggames.rts.gameFramework.utility.m var6 = new com.corrodinggames.rts.gameFramework.utility.m();
         this.a.a(var6, var1.bX, var1, false);
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            am var8 = (am)var7.next();
            var1.E(var8);
            var1.F(var8);
         }
      }

      if(this.b != null) {
         this.b.a(var1.eo, var1.ep, var1.eq, var1.cg, var1.bX, false, var1);
      }

      return true;
   }
}
