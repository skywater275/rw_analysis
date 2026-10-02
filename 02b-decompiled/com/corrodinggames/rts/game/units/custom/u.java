package com.corrodinggames.rts.game.units.custom;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import java.util.Iterator;

public class u {

   com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();
   com.corrodinggames.rts.gameFramework.utility.m b;
   String c;
   String d;


   public strictfp void a(l var1) {}

   public strictfp void b(l var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = new com.corrodinggames.rts.gameFramework.utility.m();
      Iterator var3 = this.a.iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         com.corrodinggames.rts.game.units.a.s var5 = var1.h(var4);
         if(var5 == null) {
            throw new bo("[" + this.d + "]" + this.c + " Could not find action:" + var4 + " on unit: " + var1.b());
         }

         if(!(var5 instanceof com.corrodinggames.rts.game.units.a.w)) {
            throw new bo("[" + this.d + "]" + this.c + " Action:" + var4 + " on unit: " + var1.b() + " doesn\'t have the right type");
         }

         var2.add((com.corrodinggames.rts.game.units.a.w)var5);
      }

      this.b = var2;
   }

   public strictfp void a(j var1, PointF var2, com.corrodinggames.rts.game.units.am var3, int var4, int var5) {
      if(this.b == null) {
         com.corrodinggames.rts.gameFramework.j.ad.g("Action on " + var1.dt().i() + " has not been linked");
      } else {
         Iterator var6 = this.b.iterator();

         while(var6.hasNext()) {
            com.corrodinggames.rts.game.units.a.s var7 = (com.corrodinggames.rts.game.units.a.s)var6.next();
            var1.a(var7, var2, var3, var4, var5);
         }

      }
   }

   public strictfp com.corrodinggames.rts.gameFramework.utility.m a() {
      if(this.b == null) {
         com.corrodinggames.rts.gameFramework.j.ad.g("Action on [" + this.d + "]" + this.c + " has not been linked");
         return new com.corrodinggames.rts.gameFramework.utility.m();
      } else {
         return this.b;
      }
   }

   public strictfp void a(j var1, PointF var2, com.corrodinggames.rts.game.units.am var3) {
      if(this.b == null) {
         com.corrodinggames.rts.gameFramework.j.ad.g("Action on " + var1.dt().i() + " has not been linked");
      } else {
         Iterator var4 = this.b.iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.game.units.a.s var5 = (com.corrodinggames.rts.game.units.a.s)var4.next();
            var1.dL.a((com.corrodinggames.rts.game.units.a.w)var5, false, var2, var3);
         }

      }
   }
}
