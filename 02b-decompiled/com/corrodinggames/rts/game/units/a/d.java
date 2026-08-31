package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import java.util.Iterator;

public class d extends s {

   int a;
   com.corrodinggames.rts.game.units.a b;


   public d() {
      super("c_7");
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public int c() {
      return 0;
   }

   public ar n() {
      return null;
   }

   public u e() {
      return u.k;
   }

   public t f() {
      return t.a;
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return "Attack Mode";
   }

   public String b() {
      com.corrodinggames.rts.game.units.a var1 = this.q();
      return var1 != null?var1.name():"NA";
   }

   public boolean h_() {
      return false;
   }

   public void c(am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.a var3 = this.r();
      com.corrodinggames.rts.game.units.a var4 = this.a(var3);
      com.corrodinggames.rts.game.n var5 = null;
      var5 = var1.bX;
      com.corrodinggames.rts.gameFramework.e var6 = var2.cf.b(var5);
      Iterator var7 = am.bE.iterator();

      while(var7.hasNext()) {
         am var8 = (am)var7.next();
         if(var8 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var9 = (com.corrodinggames.rts.game.units.y)var8;
            if(var9.cG) {
               var6.a(var9);
            }
         }
      }

      var6.a(var4);
      this.a = var2.bS.Y;
      this.b = var4;
   }

   public com.corrodinggames.rts.game.units.a a(com.corrodinggames.rts.game.units.a var1) {
      return var1 == com.corrodinggames.rts.game.units.a.b?com.corrodinggames.rts.game.units.a.e:(var1 == com.corrodinggames.rts.game.units.a.b?com.corrodinggames.rts.game.units.a.f:com.corrodinggames.rts.game.units.a.b);
   }

   public com.corrodinggames.rts.game.units.a q() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.a var2 = this.r();
      this.a = var1.bS.Y;
      this.b = var2;
      return var2;
   }

   public com.corrodinggames.rts.game.units.a r() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.a == var1.bS.Y && this.b != null) {
         return this.b;
      } else {
         com.corrodinggames.rts.game.units.a var2 = null;
         boolean var3 = false;
         boolean var4 = false;
         Iterator var5 = am.bE.iterator();

         while(var5.hasNext()) {
            am var6 = (am)var5.next();
            if(var6 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var7 = (com.corrodinggames.rts.game.units.y)var6;
               if(var7.cG) {
                  if(var2 != null && var2 != var7.P) {
                     var2 = com.corrodinggames.rts.game.units.a.g;
                  } else {
                     var2 = var7.P;
                  }
               }
            }
         }

         return var2;
      }
   }

   public boolean b(am var1) {
      return true;
   }

   public String d() {
      return this.b();
   }

   public boolean s() {
      return true;
   }

   // $FF: synthetic method
   public as i() {
      return this.n();
   }
}
