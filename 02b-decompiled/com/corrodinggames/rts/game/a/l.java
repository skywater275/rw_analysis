package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.Iterator;

public class l extends h {

   float a = 0.0F;


   public void a(as var1) {
      int var2 = this.F.size();
      var1.a(var2);
      Iterator var3 = this.F.iterator();

      y var4;
      while(var3.hasNext()) {
         var4 = (y)var3.next();
         var1.a(var4);
      }

      var1.c(1);
      var1.a(this.G.size());
      var3 = this.G.iterator();

      while(var3.hasNext()) {
         var4 = (y)var3.next();
         var1.a(var4);
      }

      var1.a(this.a);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.q();
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         y var4 = var1.p();
         if(var4 != null) {
            this.a(var4);
         }
      }

      byte var7 = var1.d();
      if(var7 >= 1) {
         this.G.clear();
         int var8 = var1.f();

         for(int var5 = 0; var5 < var8; ++var5) {
            y var6 = var1.p();
            if(var6 != null) {
               this.G.add(var6);
            }
         }

         this.a = var1.g();
      }

      super.a(var1);
   }

   public l(a var1) {
      super(var1);
   }

   public void c(float var1) {
      this.n();
      if(!this.m()) {
         this.a += var1;
      }

      Iterator var2 = this.F.iterator();

      while(var2.hasNext()) {
         y var3 = (y)var2.next();
         if(this.c(var3) < 3600.0F && var3.cN == null) {
            if(var3.aB == this) {
               var3.aB = null;
            }

            var2.remove();
         }
      }

      if(this.F.size() == 0 || this.a > 5000.0F) {
         this.p();
      }

   }

   public void c(y var1) {
      this.a(var1);
      this.G.add(var1);
   }
}
