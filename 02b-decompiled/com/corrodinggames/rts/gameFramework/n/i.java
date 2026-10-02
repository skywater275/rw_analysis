package com.corrodinggames.rts.gameFramework.n;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.n.f;
import com.corrodinggames.rts.gameFramework.n.j;
import java.util.Iterator;

class i {

   boolean a;
   com.corrodinggames.rts.gameFramework.utility.m b;
   // $FF: synthetic field
   final f c;


   i(f var1) {
      this.c = var1;
      this.b = new com.corrodinggames.rts.gameFramework.utility.m();
   }

   public void a(as var1, int var2) {
      as var3 = com.corrodinggames.rts.game.units.custom.l.c(var1);
      if(var3 != null) {
         var1 = var3;
      }

      this.b(var1, var2);
   }

   public void b(as var1, int var2) {
      Iterator var3 = this.b.iterator();

      j var4;
      do {
         if(!var3.hasNext()) {
            j var5 = new j(this);
            var5.a = var1;
            var5.b = var2;
            this.b.add(var5);
            return;
         }

         var4 = (j)var3.next();
      } while(var4.a != var1);

      var4.b += var2;
   }

   public void a(float var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      int var4 = 0;
      Object var5 = n.k(1);
      if(var5 == null) {
         com.corrodinggames.rts.gameFramework.l.e("Warning: Creating missing wave team AI");
         var5 = new com.corrodinggames.rts.game.a.a(1);
         ((n)var5).r = 100;
         ((n)var5).U = true;
      }

      Iterator var6 = this.b.iterator();

      while(var6.hasNext()) {
         j var7 = (j)var6.next();

         for(int var8 = 0; var8 < var7.b; ++var8) {
            am var9 = var7.a.a();
            byte var10 = 85;
            var9.eo = var1 + (float)com.corrodinggames.rts.gameFramework.f.a(-var10, var10, var4 + 0);
            var9.ep = var2 + (float)com.corrodinggames.rts.gameFramework.f.a(-var10, var10, var4 + 1);
            var9.cg = (float)com.corrodinggames.rts.gameFramework.f.a(-180, 180, var4 + 2);
            var4 += 3;
            var9.b((n)var5);
            if(var9.eo < 0.0F) {
               var9.eo = 0.0F;
            }

            if(var9.ep < 0.0F) {
               var9.ep = 0.0F;
            }

            if(var9.eo > var3.bL.i()) {
               var9.eo = var3.bL.i();
            }

            if(var9.ep > var3.bL.j()) {
               var9.ep = var3.bL.j();
            }

            if(var8 == 0) {
               var3.bW.a(var9);
            }
         }
      }

   }

   public String toString() {
      if(this.b.size() == 0) {
         return "No units";
      } else {
         String var1 = "";
         boolean var2 = true;

         j var4;
         for(Iterator var3 = this.b.iterator(); var3.hasNext(); var1 = var1 + var4.a.e()) {
            var4 = (j)var3.next();
            if(!var2) {
               var1 = var1 + ", ";
            }

            var2 = false;
            var1 = var1 + var4.b + "x ";
         }

         return var1;
      }
   }
}
