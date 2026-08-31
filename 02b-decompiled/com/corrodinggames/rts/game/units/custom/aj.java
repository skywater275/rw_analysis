package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ak;
import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.ar;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import java.util.ArrayList;

public class aj {

   public static final aj a = a("");
   public ak[] b;
   public bc[] c;
   public String d;
   public int e = -1;
   public String f;
   public String g;
   l h;


   public static aj a(String var0) {
      aj var1 = new aj();
      ArrayList var2 = new ArrayList();
      bc var3 = new bc();
      var3.a = null;
      var3.b = var0;
      var2.add(var3);
      var1.c = (bc[])var2.toArray(new bc[0]);
      var1.a();
      return var1;
   }

   public static aj a(bb var0) {
      if(var0 == null) {
         return null;
      } else {
         aj var1 = new aj();
         var1.h = l.b;
         var1.c = var0.b;
         var1.f = var0.e;
         if(var1.c != null) {
            bc[] var2 = var1.c;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               bc var5 = var2[var4];
               if(var5.b != null && var5.b.contains("%{")) {
                  ;
               }
            }
         }

         var1.c();
         return var1;
      }
   }

   public aj() {}

   public aj(l var1, bb var2) {
      this.h = var1;
      this.c = var2.b;
      this.f = var2.e;
      if(this.c != null) {
         bc[] var3 = this.c;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            bc var6 = var3[var5];
            if(var6.b != null && var6.b.contains("%{")) {
               this.a(var6.b, true);
            }
         }
      }

      this.a();
   }

   public void a() {
      this.a(true);
   }

   public void a(boolean var1) {
      this.c();
      if(this.d != null && this.d.contains("%{")) {
         this.b = this.a(this.d, var1);
      } else {
         this.b = null;
      }

   }

   public ak[] a(String var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      int var4 = 0;
      boolean var5 = false;

      while(true) {
         int var6;
         String var7;
         if(!var5) {
            var6 = var1.indexOf("%{", var4);
            if(var6 == -1) {
               var7 = var1.substring(var4, var1.length());
               if(!var7.equals("")) {
                  var3.add(new ar(var7));
               }
               break;
            }

            var7 = var1.substring(var4, var6);
            if(!var7.equals("")) {
               var3.add(new ar(var7));
            }

            var5 = true;
            var4 = var6 + 2;
         } else {
            var6 = var1.indexOf("}", var4);
            if(var6 == -1) {
               var3.add(new ar("< %{ NOT CLOSED >"));
               break;
            }

            var7 = var1.substring(var4, var6);

            try {
               LogicBoolean var8 = LogicBooleanLoader.parseBooleanBlock(this.h, var7, false);
               var3.add(aq.a(var8));
            } catch (RuntimeException var10) {
               String var9 = "Error: " + var10.getMessage() + ", [parsing: \'" + var7 + "\']";
               this.g = var9;
               var3.add(new ar("Error:< " + var9 + " >"));
               if(var2) {
                  throw var10;
               }
            }

            var4 = var6 + 1;
            var5 = false;
         }
      }

      return (ak[])var3.toArray(new ak[0]);
   }

   public String a(com.corrodinggames.rts.game.units.am var1) {
      if(!(var1 instanceof com.corrodinggames.rts.game.units.y)) {
         return "<No unit>:" + this.d;
      } else {
         com.corrodinggames.rts.game.units.y var2 = (com.corrodinggames.rts.game.units.y)var1;
         StringBuffer var3 = new StringBuffer();
         ak[] var4 = this.b;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            ak var7 = var4[var6];
            var3.append(var7.a(var2));
         }

         return var3.toString();
      }
   }

   public String b(com.corrodinggames.rts.game.units.am var1) {
      if(this.e == com.corrodinggames.rts.gameFramework.h.a.c) {
         return this.b != null?this.a(var1):this.d;
      } else {
         this.a(false);
         return this.b != null?this.a(var1):this.d;
      }
   }

   public String b() {
      if(this.e == com.corrodinggames.rts.gameFramework.h.a.c) {
         return this.d;
      } else {
         this.a(false);
         return this.d;
      }
   }

   public void c() {
      if(this.f != null) {
         this.e = com.corrodinggames.rts.gameFramework.h.a.c;
         this.d = com.corrodinggames.rts.gameFramework.h.a.a(this.f, new Object[0]);
      } else {
         String var1 = com.corrodinggames.rts.gameFramework.h.a.c();
         bc[] var2 = this.c;
         int var3 = var2.length;

         int var4;
         bc var5;
         for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            if(var1.equals(var5.a)) {
               this.e = com.corrodinggames.rts.gameFramework.h.a.c;
               this.d = var5.b;
               return;
            }
         }

         var2 = this.c;
         var3 = var2.length;

         for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            if(var5.a == null) {
               this.e = com.corrodinggames.rts.gameFramework.h.a.c;
               this.d = var5.b;
               return;
            }
         }

         this.e = com.corrodinggames.rts.gameFramework.h.a.c;
         this.d = "<NO DEFAULT TEXT FOUND>";
      }
   }

}
