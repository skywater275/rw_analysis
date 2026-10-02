package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;
import com.corrodinggames.rts.game.units.r;

class q extends com.corrodinggames.rts.game.units.a.s {

   r a;


   public q(r var1) {
      super("SetTerrainType" + var1.ordinal());
      this.a = var1;
   }

   public boolean b(am var1) {
      h var2 = h.L();
      return var2 != null?var2.G == n.c:true;
   }

   public String a() {
      return "Set terrain type to: " + this.a.name();
   }

   public String b() {
      return "Set " + this.a.name();
   }

   public boolean h_() {
      return false;
   }

   public int c() {
      return 0;
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public ar n() {
      return null;
   }

   public com.corrodinggames.rts.game.units.a.u e() {
      return com.corrodinggames.rts.game.units.a.u.g;
   }

   public com.corrodinggames.rts.game.units.a.t f() {
      return com.corrodinggames.rts.game.units.a.t.f;
   }

   public boolean g() {
      return false;
   }

   public boolean a(am var1, boolean var2) {
      return true;
   }

   public boolean h() {
      return true;
   }

   public boolean o() {
      return true;
   }

   public boolean a(float var1, float var2) {
      return true;
   }

   public boolean p() {
      return true;
   }

   // $FF: synthetic method
   public as i() {
      return this.n();
   }
}
