package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import java.util.Iterator;

public abstract class p extends s {

   public p(String var1) {
      super("c__cut_" + var1);
      this.g = 0.0F;
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public int c() {
      return 0;
   }

   public as i() {
      return null;
   }

   public u e() {
      return u.i;
   }

   public t f() {
      return t.g;
   }

   public boolean g() {
      return false;
   }

   public com.corrodinggames.rts.game.units.y K() {
      com.corrodinggames.rts.game.units.y var1 = null;
      Iterator var2 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var3 = (com.corrodinggames.rts.gameFramework.w)var2.next();
         if(var3 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
            if(var4.cG) {
               var1 = var4;
            }
         }
      }

      return var1;
   }

   public boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.y var2 = this.K();
      return var2 != null?(var2 instanceof com.corrodinggames.rts.game.units.h?true:var1.bs == var2.bX):false;
   }

   public String d() {
      return this.b();
   }

   public boolean h_() {
      return false;
   }

   public boolean s() {
      return !this.L();
   }

   public boolean G() {
      return false;
   }

   public float l() {
      return !com.corrodinggames.rts.gameFramework.f.g.bP?1.0F:1.0F;
   }
}
