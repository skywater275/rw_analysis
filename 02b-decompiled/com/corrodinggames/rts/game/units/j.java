package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;

class j extends com.corrodinggames.rts.game.units.a.x {

   boolean a;
   boolean b;


   public strictfp j(boolean var1, boolean var2) {
      super("changeModFilter" + var1 + "d:" + var2);
      this.a = var1;
      this.b = var2;
   }

   public strictfp boolean b(am var1) {
      h var2 = h.L();
      return var2 != null?var2.G == n.d:true;
   }

   public strictfp String b() {
      if(this.b) {
         h var1 = h.L();
         return var1 != null?(var1.E != null?var1.E.a():"All mods"):"Mod Filter";
      } else {
         return this.a?"<- Set mod":"Set mod ->";
      }
   }

   public strictfp String d() {
      if(!this.b) {
         return this.a?"<-":"->";
      } else {
         h var1 = h.L();
         return var1 == null?"NA":(var1.E == null?"All mods":var1.E.b());
      }
   }

   public strictfp String a() {
      return "Change filtered mod";
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
