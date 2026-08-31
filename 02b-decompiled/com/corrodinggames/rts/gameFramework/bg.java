package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bl;
import com.corrodinggames.rts.gameFramework.bn;
import com.corrodinggames.rts.gameFramework.bo;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class bg {

   public static boolean a = true;
   bo b = new bo();
   bo[] c;
   int d;
   boolean e;
   public static bl f = new bl();


   public strictfp bg() {
      this.c = new bo[com.corrodinggames.rts.game.n.e];
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.e("stats");
      var1.c(0);
      int var2 = com.corrodinggames.rts.game.n.c;
      var1.a(var2);

      for(int var3 = 0; var3 < var2; ++var3) {
         this.c[var3].a(var1);
      }

      var1.a("stats");
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1, boolean var2) {
      var1.b("stats");
      byte var3 = var1.d();
      int var4 = var1.f();
      this.c = new bo[com.corrodinggames.rts.game.n.e];

      for(int var5 = 0; var5 < var4; ++var5) {
         this.c[var5] = new bo();
         this.c[var5].a(var1);
      }

      var1.d("stats");
   }

   public strictfp void a() {
      this.b = new bo();
      this.c = new bo[com.corrodinggames.rts.game.n.e];

      for(int var1 = 0; var1 < this.c.length; ++var1) {
         this.c[var1] = new bo();
      }

      this.d = 0;
      this.e = a;
   }

   public strictfp void b() {
      int var1 = l.B().by;
      if(this.e && this.d <= var1) {
         short var2 = 5000;
         if(var1 < '\uea60') {
            var2 = 1000;
         }

         if(var1 > 1800000) {
            var2 = 15000;
         }

         if(var1 > 3600000) {
            var2 = 30000;
         }

         int var3 = var2 + var2;
         this.a(var1, false, false);
      }

   }

   private strictfp void a(int var1, boolean var2, boolean var3) {
      for(int var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
         com.corrodinggames.rts.game.n var5 = com.corrodinggames.rts.game.n.k(var4);
         if(var5 != null) {
            bn var6 = this.c[var4].l;
            if(!var2 || var6.c()) {
               var6.a(var5, var1, var3);
               var6.a(var4);
            }
         }
      }

   }

   public strictfp void c() {
      this.e = false;
      this.a(l.B().by, true, true);
   }

   public strictfp ArrayList d() {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < com.corrodinggames.rts.game.n.c; ++var2) {
         if(this.c[var2].l.c()) {
            var1.add(this.c[var2]);
         }
      }

      return var1;
   }

   public strictfp bo a(com.corrodinggames.rts.game.units.am var1) {
      return this.a(var1.bX);
   }

   public strictfp bo a(com.corrodinggames.rts.game.n var1) {
      int var2 = var1.k;
      if(var2 >= 0 && var2 < this.c.length) {
         bo var3 = this.c[var2];
         return var3 == null?this.b:var3;
      } else {
         return this.b;
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.am var1, com.corrodinggames.rts.game.units.am var2, float var3) {
      if(var1 != null) {
         boolean var4 = var2.bV;
         bo var5 = this.a(var1);
         bo var6 = this.a(var2);
         if(var4) {
            f.a(var1, var2);
            if(var2.bI()) {
               ++var5.d;
               ++var6.g;
            } else if(var2.dd()) {
               ++var5.e;
               ++var6.h;
            } else {
               ++var5.c;
               ++var6.f;
            }
         }
      }

      l var7 = l.B();
      if(var2.bX == var7.bs) {
         var7.a(var2, var3);
      }

   }

}
