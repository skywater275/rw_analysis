package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import java.io.IOException;

public class y extends s {

   public boolean a;


   public y(boolean var1) {
      super("c_5");
      this.g = -9990.0F;
      this.a = var1;
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
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      am[] var2 = var1.bS.bZ.a();
      int var3 = 0;

      for(int var4 = var1.bS.bZ.size(); var3 < var4; ++var3) {
         am var5 = var2[var3];
         if(var5 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var6 = (com.corrodinggames.rts.game.units.y)var5;
            if(var6.cG) {
               return var6;
            }
         }
      }

      return null;
   }

   public boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.y var2 = this.K();
      return var2 != null?(var2 instanceof com.corrodinggames.rts.game.units.h?true:var1.bs == var2.bX):false;
   }

   public String d() {
      String var1 = "UnitInfo";
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.y var3 = this.K();
      if(var3 != null) {
         if(var3 instanceof com.corrodinggames.rts.game.units.h) {
            return "Editor";
         }

         if(!this.a) {
            var1 = var2.bS.g.a((am)var3, false);
         } else {
            com.corrodinggames.rts.game.n var4 = var3.bX;
            var1 = var2.bS.g.a(var4);
         }
      }

      return var1;
   }

   public boolean h_() {
      return true;
   }

   public String b() {
      return "UnitInfo";
   }

   public String d(am var1) {
      return this.a?"":(var1 != null?var1.r().e():"UnitInfo");
   }

   public boolean s() {
      return this.a?!this.L():true;
   }

   public boolean u() {
      return !this.a;
   }

   public boolean C() {
      return true;
   }

   public String a() {
      if(this.a) {
         return "";
      } else {
         com.corrodinggames.rts.game.units.y var1 = this.K();
         if(var1 != null) {
            boolean var2 = false;
            String var3 = com.corrodinggames.rts.gameFramework.f.a.a(var1, false, true, var2);
            boolean var4 = false;
            if(var4) {
               com.corrodinggames.rts.gameFramework.j.f var5 = new com.corrodinggames.rts.gameFramework.j.f();

               try {
                  var1.a((com.corrodinggames.rts.gameFramework.j.as)var5);
               } catch (IOException var7) {
                  var7.printStackTrace();
               }

               var3 = var3 + "\n" + var5.a;
            }

            return var3;
         } else {
            return "";
         }
      }
   }

   public boolean G() {
      return true;
   }
}
