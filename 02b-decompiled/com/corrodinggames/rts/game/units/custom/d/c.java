package com.corrodinggames.rts.game.units.custom.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.d.a;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.d.d;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends a {

   public final m a = new m();
   boolean b;
   public int c;
   public int d;
   public int e;
   public int f;


   public static c a(l var0, ab var1, String var2, String var3, c var4) {
      String var5 = var1.b(var2, var3, (String)null);
      if(var5 == null) {
         return var4;
      } else {
         try {
            c var6 = a(var0, var5);
            return var6;
         } catch (RuntimeException var7) {
            var7.printStackTrace();
            throw new bo("[" + var2 + "]" + var3 + ": " + var7.getMessage());
         }
      }
   }

   public static c a(l var0, String var1) {
      return a(var0, var1, false);
   }

   public static c a(l var0, String var1, boolean var2) {
      c var3 = new c();
      ArrayList var4 = al.a(var1, ",", "|", false);
      Iterator var5 = var4.iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         int var7 = al.a(var6, "=", ":");
         String var8;
         String var9;
         if(var7 == -1) {
            if(!var2) {
               throw new bo("Unknown price format:" + var1);
            }

            var8 = "credits";
            var9 = var6;
         } else {
            var8 = var6.substring(0, var7).trim();
            var9 = var6.substring(var7 + 1);
         }

         if(var8.equals("hasFlag")) {
            var3.e = b.a(var3.e, var9);
         } else if(var8.equals("hasMissingFlag")) {
            var3.f = b.a(var3.f, var9);
         } else if(var8.equals("setFlag")) {
            var3.c = b.a(var3.c, var9);
         } else if(var8.equals("unsetFlag")) {
            var3.d = b.a(var3.d, var9);
         } else {
            com.corrodinggames.rts.game.units.custom.e.a var10 = var0.j(var8);
            if(var10 == null) {
               throw new bo("Could not find resource type:" + var8 + " from [" + var1 + "]");
            }

            LogicBoolean var11 = LogicBooleanLoader.parseNumberBlock(var0, var9);
            if(var11 == null) {
               throw new bo("Value missing for:" + var8 + " from [" + var1 + "]");
            }

            if(!(var11 instanceof LogicBoolean$StaticValueBoolean)) {
               var3.b = true;
            }

            d var12 = new d(var10, var11);
            var3.a.add(var12);
         }
      }

      return var3;
   }

   public boolean b(am var1) {
      return this.b(var1, 1.0D);
   }

   public boolean b(am var1, double var2) {
      if(!(var1 instanceof y)) {
         return false;
      } else {
         y var4 = (y)var1;
         int var5 = this.a.a;
         Object[] var6 = this.a.a();

         for(int var7 = 0; var7 < var5; ++var7) {
            d var8 = (d)var6[var7];
            double var9;
            if(var8.c != null) {
               var9 = (double)var8.c.readNumber(var4) * var2;
            } else {
               var9 = var8.b * var2;
            }

            if(var9 > 0.0D) {
               double var11 = var8.a.a((am)var4);
               if(var11 < var9) {
                  return false;
               }
            }
         }

         if(!this.g(var4)) {
            return false;
         } else {
            return true;
         }
      }
   }

   public void d(am var1) {
      if(!(var1 instanceof y)) {
         com.corrodinggames.rts.gameFramework.l.n("DynamicResourcePrice doesn\'t work on: " + var1.c());
      } else {
         y var2 = (y)var1;
         int var3 = this.a.a;
         Object[] var4 = this.a.a();

         for(int var5 = 0; var5 < var3; ++var5) {
            d var6 = (d)var4[var5];
            double var7;
            if(var6.c != null) {
               var7 = (double)var6.c.readNumber(var2);
            } else {
               var7 = var6.b;
            }

            var6.a.a(var2, var7);
         }

         this.f(var2);
         b.d(var2);
      }
   }

   public void a(am var1) {
      this.a(var1, 1.0D);
   }

   public void a(am var1, double var2) {
      if(!(var1 instanceof y)) {
         com.corrodinggames.rts.gameFramework.l.n("DynamicResourcePrice doesn\'t work on: " + var1.c());
      } else {
         y var4 = (y)var1;
         int var5 = this.a.a;
         Object[] var6 = this.a.a();

         for(int var7 = 0; var7 < var5; ++var7) {
            d var8 = (d)var6[var7];
            double var9;
            if(var8.c != null) {
               var9 = (double)var8.c.readNumber(var4);
            } else {
               var9 = var8.b;
            }

            var8.a.b(var4, -var9 * var2);
         }

         this.f(var4);
         b.d(var4);
      }
   }

   public void e(am var1) {
      if(!(var1 instanceof y)) {
         com.corrodinggames.rts.gameFramework.l.n("DynamicResourcePrice doesn\'t work on: " + var1.c());
      } else {
         y var2 = (y)var1;
         int var3 = this.a.a;
         Object[] var4 = this.a.a();

         for(int var5 = 0; var5 < var3; ++var5) {
            d var6 = (d)var4[var5];
            double var7;
            if(var6.c != null) {
               var7 = (double)var6.c.readNumber(var2);
            } else {
               var7 = var6.b;
            }

            var6.a.b(var2, var7);
         }

         this.f(var2);
         b.d(var2);
      }
   }

   public void f(am var1) {
      if(this.d != 0) {
         var1.cF &= ~this.d;
      }

      if(this.c != 0) {
         var1.cF |= this.c;
      }

   }

   public boolean g(am var1) {
      return this.e != 0 && !a(var1.cF, this.e)?false:this.f == 0 || !b(var1.cF, this.f);
   }

   public static boolean a(int var0, int var1) {
      return (var1 & var0) == var1;
   }

   public static boolean b(int var0, int var1) {
      return (var1 & var0) != 0;
   }
}
