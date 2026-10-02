package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.l$1;
import java.util.ArrayList;

public class z {

   public String a;
   public ay[] b;
   // $FF: synthetic field
   final l c;


   public strictfp boolean a() {
      return this.b != null && this.b.length != 0;
   }

   public strictfp boolean b() {
      return this.b != null && (this.b.length != 0 || this.b == l.gf);
   }

   private strictfp z(l var1, String var2) {
      this.c = var1;
      this.a = var2;
      var1.gc.add(this);
   }

   public strictfp void c() {
      if(this.a != null && !this.a.equals("")) {
         if(this.a.equalsIgnoreCase("NONE")) {
            this.b = l.gf;
         } else {
            ArrayList var1 = new ArrayList();
            String[] var2 = this.a.split(",");
            String[] var3 = var2;
            int var4 = var2.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               String var6 = var3[var5];
               var6 = var6.trim();
               String[] var7 = var6.split("\\*");
               String var8 = var7[0];
               int var9 = 1;
               if(var7.length >= 2) {
                  var9 = Integer.parseInt(var7[1]);
               }

               ay var10 = this.c.d(var8);

               for(int var11 = 0; var11 < var9; ++var11) {
                  var1.add(var10);
               }
            }

            this.b = (ay[])var1.toArray(l.ge);
         }
      } else {
         this.b = l.ge;
      }
   }

   public strictfp com.corrodinggames.rts.gameFramework.d.e a(float var1, float var2, float var3, float var4, com.corrodinggames.rts.gameFramework.w var5) {
      return this.a(var1, var2, var3, var4, var5, 0, (short)0);
   }

   public strictfp com.corrodinggames.rts.gameFramework.d.e a(float var1, float var2, float var3, float var4, com.corrodinggames.rts.gameFramework.w var5, int var6, short var7) {
      com.corrodinggames.rts.gameFramework.d.e var8 = null;
      ay[] var9 = this.b;
      int var10 = var9.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         ay var12 = var9[var11];
         com.corrodinggames.rts.gameFramework.d.e var13 = var12.a(var1, var2, var3, var4, var5, var6, var7);
         if(var13 != null && var8 == null) {
            var8 = var13;
         }
      }

      return var8;
   }

   // $FF: synthetic method
   z(l var1, String var2, l$1 var3) {
      this(var1, var2);
   }
}
