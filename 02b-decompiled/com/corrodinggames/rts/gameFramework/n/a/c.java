package com.corrodinggames.rts.gameFramework.n.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.gameFramework.n.a.a;

public class c extends a {

   Integer a;
   Integer b;
   n c;
   as d;
   boolean e;
   boolean f;
   boolean g;
   boolean h;
   boolean i;
   boolean j;
   int k;
   boolean l;
   boolean m;
   boolean n;
   g o;
   boolean p;


   public static c d(com.corrodinggames.rts.gameFramework.n.a var0) {
      c var1 = new c();
      var1.a = var0.d("maxUnits");
      byte var2 = 1;
      if(var1.a != null) {
         var2 = 0;
      }

      var1.b = Integer.valueOf(var0.a("minUnits", var2));
      var1.c = var0.a();
      as var3 = null;
      String var4 = var0.b("unitType");
      if(var4 != null) {
         var3 = ar.a(var4);
         if(var3 == null) {
            var0.g("Cound not find unitType:" + var4);
         }
      }

      var1.d = var3;
      var1.e = var0.a("onlybuildings", "onlyBuildings", false);
      var1.g = var0.a("onlyMainBuildings", false);
      var1.h = var0.a("onlyOnResourcePool", false);
      var1.f = var0.a("onlyidle", "onlyIdle", false);
      var1.k = var0.a("onlyTechLevel", -1);
      var1.j = var0.a("onlyBuilders", false);
      var1.i = var0.a("onlyEmptyQueue", false);
      var1.l = var0.a("onlyAttack", false);
      var1.m = var0.a("onlyAttackAir", false);
      var1.n = var0.a("onlyIfEmpty", false);
      String var5 = var0.b("onlyWithTag");
      if(var5 != null && !var5.equals("")) {
         try {
            var1.o = g.b(var5);
         } catch (bo var7) {
            throw new f(var7.getMessage());
         }
      }

      var1.p = var0.a("includeIncomplete", false);
      return var1;
   }

   public boolean b(com.corrodinggames.rts.gameFramework.n.a var1) {
      return this.e(var1);
   }

   public boolean e(com.corrodinggames.rts.gameFramework.n.a var1) {
      int var2 = 0;
      am[] var3 = am.bE.a();
      int var4 = 0;

      for(int var5 = am.bE.size(); var4 < var5; ++var4) {
         am var6 = var3[var4];
         if((this.c == null || var6.bX == this.c) && var6 instanceof y && var6.cN == null && var1.a(var6) && (this.d == null || var6.r() == this.d)) {
            y var7 = (y)var6;
            if((this.p || var6.bT()) && (!this.l || var6.l()) && (!this.m || var6.l() && var7.af()) && (!this.e || var6.bI()) && (!this.g || var6.bI() && var6.bJ()) && (!this.h || var6.r().p()) && (!this.j || var6.ak()) && (!this.f || var7.aq()) && (!this.i || var7.a((g)null) <= 0) && (this.k == -1 || var6.V() == this.k) && (this.o == null || g.a(this.o, var6.de())) && (!this.n || var7.bB() <= 0)) {
               ++var2;
            }
         }
      }

      if(var2 >= this.b.intValue() && (this.a == null || var2 <= this.a.intValue())) {
         return true;
      } else {
         return false;
      }
   }
}
