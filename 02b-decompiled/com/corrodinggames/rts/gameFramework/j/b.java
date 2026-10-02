package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.a;
import com.corrodinggames.rts.gameFramework.j.c;

public class b {

   int a;
   String b;
   String c;
   int d;
   long e;
   // $FF: synthetic field
   final a f;


   b(a var1, int var2, String var3, String var4, c var5) {
      this.f = var1;
      this.a = var2;
      this.b = var3;
      this.c = var4;
      if(var5 != null) {
         this.d = var5.c;
      }

      this.e = System.nanoTime();
   }

   public String a() {
      String var1;
      if(this.b != null) {
         var1 = this.b + ": " + this.c;
      } else {
         var1 = this.c;
      }

      return var1;
   }

   public String b() {
      String var1 = "";
      if(this.b != null) {
         int var2 = -1;
         if(this.a != -1) {
            var2 = com.corrodinggames.rts.game.n.i(this.a);
         }

         var1 = "<strong> <font color=\'" + com.corrodinggames.rts.gameFramework.f.h(var2) + "\'>" + this.f.a(this.b) + ": </font></strong>";
      }

      String[] var8 = this.c.split("\n");
      boolean var3 = true;
      String[] var4 = var8;
      int var5 = var8.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String var7 = var4[var6];
         if(!var7.trim().equals("")) {
            if(var3) {
               var3 = false;
            } else {
               var1 = var1 + "<br/>";
            }

            var1 = var1 + this.f.a(var7);
         }
      }

      return var1;
   }
}
