package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bc;
import java.util.ArrayList;

public class bb {

   public static final bb a = a("");
   public bc[] b;
   public String c;
   public int d = -1;
   public String e;


   public static bb a(String var0) {
      bb var1 = new bb();
      ArrayList var2 = new ArrayList();
      bc var3 = new bc();
      var3.a = null;
      var3.b = var0;
      var2.add(var3);
      var1.b = (bc[])var2.toArray(new bc[0]);
      var1.b();
      return var1;
   }

   public static bb b(String var0) {
      bb var1 = new bb();
      var1.e = var0;
      var1.b();
      return var1;
   }

   public bb() {}

   public bb(bc[] var1) {
      this.b = var1;
   }

   public boolean a() {
      if(this.b != null) {
         bc[] var1 = this.b;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            bc var4 = var1[var3];
            if(var4.b != null && !"".equals(var4.b)) {
               return false;
            }
         }
      }

      return true;
   }

   public void a(String var1, String var2) {
      if(this.b != null) {
         bc[] var3 = this.b;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            bc var6 = var3[var5];
            var6.a(var1, var2);
         }
      } else {
         com.corrodinggames.rts.gameFramework.l.b("LocaleString: replaceAll with null strings");
      }

      this.d = -1;
   }

   public String b() {
      if(this.d == com.corrodinggames.rts.gameFramework.h.a.c) {
         return this.c;
      } else if(this.e != null) {
         this.d = com.corrodinggames.rts.gameFramework.h.a.c;
         this.c = com.corrodinggames.rts.gameFramework.h.a.a(this.e, new Object[0]);
         return this.c;
      } else {
         String var1 = com.corrodinggames.rts.gameFramework.h.a.c();
         bc[] var2 = this.b;
         int var3 = var2.length;

         int var4;
         bc var5;
         for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            if(var1.equals(var5.a)) {
               this.d = com.corrodinggames.rts.gameFramework.h.a.c;
               this.c = var5.b;
               return this.c;
            }
         }

         var2 = this.b;
         var3 = var2.length;

         for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            if(var5.a == null) {
               this.d = com.corrodinggames.rts.gameFramework.h.a.c;
               this.c = var5.b;
               return this.c;
            }
         }

         this.d = com.corrodinggames.rts.gameFramework.h.a.c;
         this.c = "<NO DEFAULT TEXT FOUND>";
         return this.c;
      }
   }

}
