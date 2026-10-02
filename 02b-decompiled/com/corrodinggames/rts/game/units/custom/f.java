package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.c;
import com.corrodinggames.rts.game.units.custom.d;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.m;
import com.corrodinggames.rts.game.units.custom.n;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import java.util.ArrayList;
import java.util.Iterator;

public class f {

   public String a;
   public int b;
   public int c;
   public float d;
   public float e;
   public float f;
   public boolean g;
   public float h;
   public float i;
   public LogicBoolean j;
   public m k;
   public com.corrodinggames.rts.gameFramework.utility.m l = new com.corrodinggames.rts.gameFramework.utility.m();
   public boolean m = true;
   public float n;
   public boolean o;
   public ArrayList p = new ArrayList();
   public float q;


   public f(String var1) {
      this.a = var1;
   }

   public void a(l var1) {
      Iterator var2 = this.l.iterator();

      c var3;
      do {
         if(!var2.hasNext()) {
            return;
         }

         var3 = (c)var2.next();
         if(var3.a == d.c || var3.a == d.d || var3.a == d.f || var3.a == d.e || var3.a == d.j) {
            boolean var4 = false;
            ba[] var5 = var1.ax;
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               ba var8 = var5[var7];
               if(var3.c.equals(var8.b)) {
                  var3.b = var8.a;
                  var4 = true;
                  break;
               }
            }

            if(!var4) {
               throw new bo("Cannot find leg:" + var3.c + " for animation:" + this.a);
            }
         }
      } while(var3.b >= 0);

      throw new bo("Cannot find target for:" + var3.c + " for animation:" + this.a);
   }

   public boolean a(n var1) {
      Iterator var2 = this.p.iterator();

      n var3;
      do {
         if(!var2.hasNext()) {
            return false;
         }

         var3 = (n)var2.next();
      } while(var3 != var1);

      return true;
   }

   public boolean a() {
      return this.o;
   }

   public void a(l var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3, String var4) {
      boolean var5 = false;
      String var6 = null;
      String var7 = var2.b(var3, var4 + "onActions", (String)null);
      String var11;
      if(var7 != null) {
         String[] var8 = var7.split(",");
         int var9 = var8.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            var11 = var8[var10];
            var11 = var11.trim();
            if(!var11.equals("")) {
               n var12 = n.a(var11);
               if(var12 == null) {
                  throw new bo("Unknown action type: " + var11 + " on animation:" + this.a);
               }

               f var13 = var1.a(var12);
               if(var13 != null) {
                  throw new bo("Cannot add action: " + var11 + " to:" + this.a + " it already exists on:" + var13.a);
               }

               this.p.add(var12);
            }
         }
      }

      this.q = var2.a(var3, var4 + "onActionsQueuedUnitPlayAt", Float.valueOf(0.0F)).floatValue();
      this.b = var2.b(var3, var4 + "start", Integer.valueOf(0)).intValue();
      this.c = var2.b(var3, var4 + "end", Integer.valueOf(-1)).intValue();
      if(this.c != -1 && this.c < this.b) {
         throw new RuntimeException("animationEnd cannot before animationStart on animation:" + this.a);
      } else {
         this.k = m.a(var1, var2, var3, "", true);
         this.h = var2.d(var3, var4 + "blendIn", Float.valueOf(-1.0F)).floatValue();
         this.i = var2.d(var3, var4 + "blendOut", Float.valueOf(-1.0F)).floatValue();
         this.j = var2.a(var1, var3, var4 + "playbackRate", (LogicBoolean)null, LogicBoolean$ReturnType.number);
         this.d = var2.a(var3, var4 + "scale_start", Float.valueOf(1.0F)).floatValue();
         this.e = var2.a(var3, var4 + "scale_end", Float.valueOf(1.0F)).floatValue();
         Float var17 = var2.a(var3, var4 + "speed", (Float)null);
         if(var17 != null) {
            this.f = var17.floatValue();
            var5 = true;
            var6 = "speed";
         } else {
            this.f = 40.0F;
         }

         this.g = var2.a(var3, var4 + "pingPong", Boolean.valueOf(false)).booleanValue();
         float var18 = 1.0F * this.f;
         float var19 = var2.a(var3, var4 + "KeyframeTimeScale", Float.valueOf(1.0F)).floatValue();
         c var20;
         if(this.c != -1) {
            var5 = true;
            var6 = "animationEnd";
            var20 = new c();
            var20.a = d.a;
            this.l.add(var20);
            int var21 = this.c - this.b + 1;
            var18 *= (float)var21;
            var20.a(0.0F, (float)this.b);
            var20.a(var18, (float)this.c + 0.99F);
         }

         if(this.d != 1.0F || this.e != 1.0F) {
            var5 = true;
            var6 = "animationScaleX";
            var20 = new c();
            var20.a = d.b;
            this.l.add(var20);
            var20.a(0.0F, this.d);
            var20.a(var18, this.e);
         }

         if(var5) {
            this.n = var18;
         }

         var11 = var4 + "leg";
         String var22 = var4 + "arm";
         com.corrodinggames.rts.gameFramework.utility.m var23 = var2.f(var3, var11, var22);
         var23.addAll(var2.k(var3, var4 + "turret"));
         var23.addAll(var2.k(var3, var4 + "body"));
         var23.addAll(var2.k(var3, var4 + "effect"));
         Iterator var14 = var23.iterator();

         while(var14.hasNext()) {
            String var15 = (String)var14.next();
            boolean var16 = false;
            if(!var16) {
               if(var5) {
                  throw new bo("Cannot mix new (" + var15 + ") and old style (" + var6 + ") animations on:" + this.a);
               }

               this.a(var1, var2, var3, var4, var15);
            }
         }

         com.corrodinggames.rts.gameFramework.utility.m var24 = new com.corrodinggames.rts.gameFramework.utility.m();
         this.m = false;
         Iterator var25 = this.l.iterator();

         while(var25.hasNext()) {
            c var26 = (c)var25.next();
            var26.a(var19);
            var26.c();
            if(this.n < var26.d) {
               this.n = var26.d;
            }

            if(var26.e.length > 0) {
               this.o = true;
               if(var26.a != d.a && var26.a != d.b) {
                  this.m = true;
               }

               var24.add(var26);
            }
         }

         this.l = var24;
      }
   }

   public c a(String var1, String var2) {
      Object var5 = null;
      d var3;
      int var4;
      if(!var2.startsWith("leg") && !var2.startsWith("arm")) {
         if(var2.startsWith("turret")) {
            var4 = Integer.parseInt(var2.substring("turret".length()));
            --var4;
            if(var1.equalsIgnoreCase("x")) {
               var3 = d.g;
            } else {
               if(!var1.equalsIgnoreCase("y")) {
                  throw new bo("Unknown turret animation type:" + var1 + " on animation:" + this.a);
               }

               var3 = d.h;
            }
         } else if(var2.startsWith("body")) {
            var4 = 0;
            if(var1.equalsIgnoreCase("scale")) {
               var3 = d.b;
            } else {
               if(!var1.equalsIgnoreCase("frame")) {
                  throw new bo("Unknown body animation type:" + var1 + " on animation:" + this.a);
               }

               var3 = d.a;
            }
         } else {
            if(!var2.startsWith("effect")) {
               throw new bo("Unknown animation target:" + var2 + " on animation:" + this.a);
            }

            var4 = 0;
            var3 = d.i;
            var2 = "event";
         }
      } else {
         var4 = -1;
         if(var1.equalsIgnoreCase("x")) {
            var3 = d.c;
         } else if(var1.equalsIgnoreCase("y")) {
            var3 = d.d;
         } else if(var1.equalsIgnoreCase("dir")) {
            var3 = d.e;
         } else if(var1.equalsIgnoreCase("height")) {
            var3 = d.f;
         } else {
            if(!var1.equalsIgnoreCase("alpha")) {
               throw new bo("Unknown leg/arm animation type:" + var1 + " on animation:" + this.a);
            }

            var3 = d.j;
         }
      }

      Iterator var6 = this.l.iterator();

      c var7;
      do {
         if(!var6.hasNext()) {
            c var8 = new c();
            var8.a = var3;
            var8.b = var4;
            var8.c = var2;
            this.l.add(var8);
            return var8;
         }

         var7 = (c)var6.next();
      } while(var7.a != var3 || !var2.equals(var7.c));

      return var7;
   }

   public void a(l var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3, String var4, String var5) {
      String var6 = var5.substring(var4.length());
      String var7 = var6.split("_")[0];
      String var8 = var4 + var7 + "_";
      String var9 = var5;
      String var11 = var5.substring(var8.length());

      float var10;
      try {
         var10 = com.corrodinggames.rts.gameFramework.utility.ab.a(var11, false, var3, var9);
      } catch (NumberFormatException var25) {
         throw new bo("Failed to read time:" + var11 + " in key:" + var5 + " section:" + var3 + " expected a float with optional \'s\' or \'ms\' postfix");
      }

      String var12 = var2.e(var3, var5);
      if(var12.startsWith("{") && var12.endsWith("}")) {
         var12 = var12.substring(1, var12.length() - 1);
         String[] var13 = var12.split(",");
         c var14 = null;
         String[] var15 = var13;
         int var16 = var13.length;

         for(int var17 = 0; var17 < var16; ++var17) {
            String var18 = var15[var17];
            String[] var19 = var18.split(":");
            if(var19.length != 2) {
               throw new bo("Unknown format on part:" + var18 + " of: " + var12, var3, var5);
            }

            String var20 = var19[0].trim();
            String var21 = var19[1].trim();
            c var22 = this.a(var20, var7);
            if(var14 != var22) {
               if(var14 != null) {
                  var14.b();
               }

               var14 = var22;
            }

            try {
               var22.a(var1, var10, var20, var21);
            } catch (bo var24) {
               throw new bo(var24.getMessage() + " (as part of key:" + var5 + " section:" + var3 + ")", var24);
            }
         }

         if(var14 != null) {
            var14.b();
         }

      } else {
         throw new bo("Unknown format:" + var12, var3, var5);
      }
   }
}
