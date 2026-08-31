package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;

public class m extends s {

   boolean a;


   public m(boolean var1) {
      super("c_2");
      this.a = var1;
   }

   public String a() {
      return !this.a?com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget.description", new Object[0]):com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget.description", new Object[0]);
   }

   public String b() {
      return !this.a?com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget", new Object[0]):com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget", new Object[0]);
   }

   public int c() {
      return 0;
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public ar K() {
      return null;
   }

   public u e() {
      return u.e;
   }

   public t f() {
      return t.f;
   }

   public boolean g() {
      return false;
   }

   public boolean h() {
      return true;
   }

   public boolean o(am var1) {
      return var1 == null?true:(!this.a?var1.bI():true);
   }

   public float l() {
      return !com.corrodinggames.rts.gameFramework.f.g.bP?0.6F:1.0F;
   }

   // $FF: synthetic method
   public as i() {
      return this.K();
   }
}
