package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.n;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.y;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class h extends o {

   ArrayList F = new ArrayList();
   ArrayList G = new ArrayList();


   public int l() {
      return this.F.size();
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public h(a var1) {
      super(var1);
   }

   public boolean m() {
      Iterator var1 = this.R.bm.iterator();

      while(var1.hasNext()) {
         o var2 = (o)var1.next();
         if(var2 instanceof n) {
            n var3 = (n)var2;
            if(var3.m == this) {
               return true;
            }
         }
      }

      return false;
   }

   public void n() {
      Iterator var1 = this.F.iterator();

      while(var1.hasNext()) {
         y var2 = (y)var1.next();
         if(var2 == null || var2.bV) {
            if(var2 != null && var2.aB == this) {
               var2.aB = null;
            }

            if(var2 != null) {
               this.G.remove(var2);
            }

            var1.remove();
         }
      }

   }

   public void o() {
      Iterator var1 = this.G.iterator();

      while(var1.hasNext()) {
         y var2 = (y)var1.next();
         if(var2 == null || var2.bV || var2.cN != null || var2.cO != null) {
            var1.remove();
         }
      }

   }

   public void p() {
      this.q();
      this.G.clear();
      super.p();
   }

   protected void a(y var1) {
      if(var1.aB != null) {
         var1.aB.b(var1);
      }

      if(var1.bX != null && var1.bX != this.R) {
         com.corrodinggames.rts.gameFramework.l.g("unit.team:" + var1.bX.k + ", ai:" + this.R.k);
      }

      this.F.add(var1);
      var1.aB = this;
   }

   public void b(y var1) {
      this.F.remove(var1);
      this.G.remove(var1);
      if(var1.aB == this) {
         var1.aB = null;
      }

   }

   public void q() {
      Iterator var1 = this.F.iterator();

      while(var1.hasNext()) {
         y var2 = (y)var1.next();
         if(var2 != null && var2.aB == this) {
            var2.aB = null;
         }
      }

      this.F.clear();
   }

   public void b(float var1) {}

   public abstract void c(float var1);
}
