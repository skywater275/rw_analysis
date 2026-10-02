package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;

class m extends com.corrodinggames.rts.game.units.a.x {

   boolean a;
   boolean b;


   public strictfp m(boolean var1, boolean var2) {
      super("changeUnitTab" + var1 + "d:" + var2);
      this.a = var1;
      this.b = var2;
   }

   public strictfp String b() {
      return this.d();
   }

   public strictfp String d() {
      h var1 = h.L();
      if(var1 == null) {
         return "<NULL>";
      } else if(this.b) {
         return var1.G.a();
      } else {
         String var2 = "";
         if(this.a) {
            var2 = var2 + "<- ";
         }

         if(!this.a) {
            var2 = var2 + " ->";
         }

         return var2;
      }
   }

   public strictfp void n() {
      h var1 = h.L();
      if(var1 == null) {
         com.corrodinggames.rts.gameFramework.l.b("Editor not active");
      } else if(!this.b) {
         var1.G = var1.G.a(this.a);
      }
   }

   public strictfp String a() {
      return "Change unit tab in editor";
   }

   public strictfp float l() {
      return !com.corrodinggames.rts.gameFramework.f.g.bP?0.8F:0.5F;
   }

   public strictfp int m() {
      return this.b?2:4;
   }

   public strictfp com.corrodinggames.rts.game.units.a.t f() {
      return this.b?com.corrodinggames.rts.game.units.a.t.g:super.f();
   }

   public strictfp com.corrodinggames.rts.game.units.a.u e() {
      return this.b?com.corrodinggames.rts.game.units.a.u.i:super.e();
   }
}
