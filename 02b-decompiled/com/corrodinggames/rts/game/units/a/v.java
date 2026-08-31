package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;

public class v extends s {

   as a;
   int b;


   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(var1 != null && var1.getClass() == this.getClass()) {
         v var2 = (v)var1;
         return this.b != var2.b?false:(this.a != var2.a?false:super.equals(var1));
      } else {
         return false;
      }
   }

   public v(as var1) {
      this(var1, 1, (Integer)null);
   }

   public v(as var1, int var2, Integer var3) {
      super("b_" + var1.v());
      this.b = 1;
      as var4 = com.corrodinggames.rts.game.units.custom.l.c(var1);
      if(var4 != null) {
         var1 = var4;
         this.a("b_" + var4.v());
      }

      if(var2 != 1) {
         this.a(this.N() + "_" + var2);
      }

      this.a = var1;
      this.b = var2;
      if(var3 != null) {
         this.g = (float)var3.intValue();
      }

   }

   public as i() {
      return this.a;
   }

   public as y() {
      return this.a;
   }

   public int t() {
      return this.b;
   }

   public String a() {
      String var1 = this.i().f();
      boolean var2 = false;
      boolean var3 = true;
      am var4 = am.c(this.i());
      if(this.b != 1 && var4 instanceof com.corrodinggames.rts.game.units.y) {
         ((com.corrodinggames.rts.game.units.y)var4).a(this.b);
      }

      var1 = var1 + "\n\n" + com.corrodinggames.rts.gameFramework.f.a.a(var4, false, var2, var3);
      if(this.b != 1 && var4 instanceof com.corrodinggames.rts.game.units.y) {
         ((com.corrodinggames.rts.game.units.y)var4).a((int)1);
      }

      return var1;
   }

   public String b() {
      as var1 = this.i();
      String var2 = this.i().e();
      if(!(var1 instanceof com.corrodinggames.rts.game.units.custom.l)) {
         if(this.t() == 2) {
            var2 = var2 + " T-2";
         }

         if(this.t() == 3) {
            var2 = var2 + " T-3";
         }
      }

      return var2;
   }

   public int c() {
      return this.B().a();
   }

   public com.corrodinggames.rts.game.units.custom.d.b B() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.a();
      return var1 != null?var1:this.i().d(this.t());
   }

   public com.corrodinggames.rts.game.units.custom.d.b r_() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.b();
      return var1 != null?var1:this.i().B();
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public u e() {
      return u.b;
   }

   public t f() {
      return t.e;
   }

   public boolean n_() {
      return !this.i().C();
   }

   public boolean g(am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      return (this.i() == ar.D || this.i() == ar.C) && var2.O() && var2.bX.ay.i?true:(this.i().w()?true:super.g(var1));
   }

   public boolean g() {
      return false;
   }

   public boolean u() {
      return true;
   }

   public boolean D() {
      return false;
   }

   public float p(am var1) {
      if(!(var1 instanceof com.corrodinggames.rts.game.units.y)) {
         return -1.0F;
      } else {
         com.corrodinggames.rts.game.units.y var2 = (com.corrodinggames.rts.game.units.y)var1;
         am var3 = var2.X();
         return var3 != null && var3.cm < 1.0F && var3.r() == this.i()?var3.cm:-1.0F;
      }
   }

   public boolean r(am var1) {
      return this.h.a(var1, true);
   }

   public boolean b(am var1) {
      return this.h.a(var1, false);
   }
}
