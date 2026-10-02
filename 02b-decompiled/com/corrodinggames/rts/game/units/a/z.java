package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import java.util.ArrayList;
import java.util.Iterator;

public class z extends s {

   as a;
   ArrayList b = new ArrayList();
   int c = 0;
   boolean d;
   com.corrodinggames.rts.game.units.y e = null;
   int f;


   public z(as var1) {
      super("s_" + var1.v());
      this.g = -9999.0F;
      this.a = var1;
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public int c() {
      return 0;
   }

   public as i() {
      return this.a;
   }

   public u e() {
      return u.i;
   }

   public t f() {
      return com.corrodinggames.rts.gameFramework.l.at() && !com.corrodinggames.rts.gameFramework.f.g.bO?t.h:t.g;
   }

   public boolean g() {
      return false;
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var2) {
         if(var3.bS.q() == 1) {
            return false;
         }

         boolean var4 = false;
         Iterator var5 = am.bE.iterator();

         while(var5.hasNext()) {
            am var6 = (am)var5.next();
            if(var6.cG && var6.r() != this.a) {
               var3.bS.l(var6);
               var4 = true;
            }
         }

         if(!var4) {
            return false;
         }
      } else {
         Iterator var7 = am.bE.iterator();

         while(var7.hasNext()) {
            am var8 = (am)var7.next();
            if(var8.cG && var8.r() == this.a) {
               var3.bS.l(var8);
            }
         }
      }

      return true;
   }

   public String d() {
      String var1 = "UnitInfo";
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.e instanceof com.corrodinggames.rts.game.units.h) {
         return "Editor";
      } else {
         var1 = "" + this.a.e() + " x" + this.c;
         return var1;
      }
   }

   public String b() {
      return "UnitInfo";
   }

   public String w(am var1) {
      return this.e instanceof com.corrodinggames.rts.game.units.h?"Editor":this.a.e();
   }

   public boolean h_() {
      return true;
   }

   public boolean s() {
      return true;
   }

   public boolean u() {
      return true;
   }

   public boolean C() {
      return true;
   }

   public String a() {
      String var1 = "";
      if(this.e instanceof com.corrodinggames.rts.game.units.h) {
         return "";
      } else {
         if(this.d) {
            var1 = "(Left click to exclusively select / Right click to unselect)\n";
         }

         return var1 + this.a.f();
      }
   }

   public void K() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.f != var1.bS.Y) {
         this.f = var1.bS.Y;
         this.c = 0;
         this.d = false;
         this.e = null;
         am[] var2 = var1.bS.bZ.a();
         int var3 = 0;

         for(int var4 = var1.bS.bZ.size(); var3 < var4; ++var3) {
            am var5 = var2[var3];
            if(var5 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var6 = (com.corrodinggames.rts.game.units.y)var5;
               if(var6.cG) {
                  if(var6.r() == this.a) {
                     ++this.c;
                     if(this.e == null) {
                        this.e = var6;
                     }
                  } else {
                     this.d = true;
                  }
               }
            }
         }

      }
   }

   public float m_() {
      return this.g - (float)this.c;
   }

   public boolean G() {
      return true;
   }

   public boolean o_() {
      return true;
   }
}
