package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.ai;
import java.util.Iterator;

public class j extends com.corrodinggames.rts.game.units.custom.a.a {

   boolean a;
   boolean b;
   boolean c;
   LogicBoolean d;
   LogicBoolean e;
   LogicBoolean f;
   LogicBoolean g;
   boolean h;
   float i;
   float j;
   u k;
   boolean l;
   boolean m;
   float n = -1.0F;
   ai o;
   boolean p;
   VariableScope$CachedWriter q;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      boolean var7 = var1.a(var2, var3 + "resetUnitStats", Boolean.valueOf(false)).booleanValue();
      String var8 = var1.b(var2, var3 + "setUnitStats", (String)null);
      if(var7 || var8 != null) {
         j var9 = new j();
         var9.p = var7;
         if(var8 != null) {
            var9.q = as.a(var8, var0, var2, var3 + "setUnitStats");
         }

         var4.ac.add(var9);
      }

      boolean var25 = var1.a(var2, var3 + "deleteSelf", Boolean.valueOf(false)).booleanValue();
      if(var25) {
         j var10 = new j();
         var10.a = var25;
         var4.ac.add(var10);
      }

      boolean var26 = var1.a(var2, var3 + "switchToNeutralTeam", Boolean.valueOf(false)).booleanValue();
      boolean var11 = var1.a(var2, var3 + "switchToAggressiveTeam", Boolean.valueOf(false)).booleanValue();
      LogicBoolean var12 = var1.a(var0, var2, var3 + "switchToTeam", (LogicBoolean)null, LogicBoolean$ReturnType.number);
      if(var26 || var11 || var12 != null) {
         j var13 = new j();
         var13.b = var26;
         var13.c = var11;
         var13.d = var12;
         var4.ac.add(var13);
      }

      LogicBoolean var27 = var1.c(var0, var2, var3 + "setBodyRotation", (LogicBoolean)null);
      if(var27 != null) {
         j var14 = new j();
         var14.e = var27;
         var4.ac.add(var14);
      }

      LogicBoolean var28 = var1.c(var0, var2, var3 + "setHeight", (LogicBoolean)null);
      if(var28 != null) {
         j var15 = new j();
         var15.f = var28;
         var4.ac.add(var15);
      }

      LogicBoolean var29 = var1.b(var0, var2, var3 + "teleportTo", (LogicBoolean)null);
      if(var29 != null) {
         j var16 = new j();
         var16.g = var29;
         var4.ac.add(var16);
      }

      float var30 = var1.a(var2, var3 + "setBuilt", Float.valueOf(-1.0F)).floatValue();
      if(var30 > 1.0F) {
         throw new bo("[" + var2 + "] setBuilt cannot be greater than 1");
      } else {
         boolean var17 = var1.a(var2, var3 + "clearAllActionCooldowns", Boolean.valueOf(false)).booleanValue();
         float var18 = var1.c(var2, var3 + "addAllActionCooldownsTime", Float.valueOf(0.0F)).floatValue();
         if(var18 == 0.0F) {
            var18 = var1.c(var2, var3 + "addAllActionCooldownsFor", Float.valueOf(0.0F)).floatValue();
         }

         float var19 = var1.c(var2, var3 + "addActionCooldownTime", Float.valueOf(0.0F)).floatValue();
         if(var19 == 0.0F) {
            var19 = var1.c(var2, var3 + "addActionCooldownFor", Float.valueOf(0.0F)).floatValue();
         }

         u var20 = var1.a(var0, var2, var3 + "addActionCooldownApplyToActions", (u)null);
         ai var21 = var1.a(var2, var3 + "offsetSelfAbsolute", (ai)null);
         if(var20 != null && var19 <= 0.0F) {
            throw new bo("[" + var2 + "]addActionCooldownApplyToActions requires addActionCooldownTime to be set");
         } else {
            boolean var22 = var1.a(var2, var3 + "removeAllQueuedItemsWithoutRefund", Boolean.valueOf(false)).booleanValue();
            boolean var23 = var1.a(var2, var3 + "refundAllQueuedItems", Boolean.valueOf(false)).booleanValue();
            if(var22 && var23) {
               throw new bo("[" + var2 + "]Cannot set removeAllQueuedActionsWithoutRefund and refundAllQueuedActions at the same time, pick one.");
            } else {
               if(var19 > 0.0F || var18 > 0.0F || var17 || var30 >= 0.0F || var21 != null || var22 || var23) {
                  j var24 = new j();
                  var24.h = var17;
                  var24.i = var18;
                  var24.j = var19;
                  var24.k = var20;
                  var24.n = var30;
                  var24.o = var21;
                  var24.l = var22;
                  var24.m = var23;
                  var4.ac.add(var24);
               }

            }
         }
      }
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.p) {
         var1.y = var1.x.cL;
         var1.cv = (float)var1.y.c;
         if(var1.cu > var1.cv) {
            var1.o(var1.cv);
         }

         var1.cA = (float)var1.y.g;
         if(var1.cx > var1.cA) {
            var1.cx = var1.cA;
         }
      }

      if(this.q != null) {
         this.q.writeToUnit(var1);
         com.corrodinggames.rts.game.units.custom.d.b.d(var1);
      }

      if(this.a) {
         var1.ci();
         if(var1.bI()) {
            com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
            var6.bU.a((y)var1);
         }
      }

      if(this.b) {
         var1.e(com.corrodinggames.rts.game.n.i);
      }

      if(this.c) {
         var1.e(com.corrodinggames.rts.game.n.h);
      }

      if(this.d != null) {
         int var8 = (int)this.d.readNumber(var1);
         com.corrodinggames.rts.game.n var7 = com.corrodinggames.rts.game.n.k(var8);
         if(var7 != null) {
            var1.e(var7);
         }
      }

      float var9;
      if(this.e != null) {
         var9 = this.e.readNumber(var1);
         var1.h(var9);
      }

      if(this.f != null) {
         var9 = this.f.readNumber(var1);
         var1.eq = var9;
      }

      if(this.g != null) {
         am var10 = this.g.readUnit(var1);
         if(var10 != null) {
            var1.f(var10.eo, var10.ep);
         }
      }

      if(this.h) {
         com.corrodinggames.rts.game.units.g.e.c(var1, s.i);
      }

      if(this.l) {
         var1.i(false);
      }

      if(this.m) {
         var1.i(true);
      }

      if(this.i > 0.0F) {
         com.corrodinggames.rts.game.units.g.e.a(var1, s.i, (int)this.i);
      }

      if(this.j > 0.0F) {
         if(this.k == null) {
            com.corrodinggames.rts.game.units.g.e.a(var1, var2.N(), (int)this.j);
         } else {
            Iterator var12 = this.k.a().iterator();

            while(var12.hasNext()) {
               s var11 = (s)var12.next();
               com.corrodinggames.rts.game.units.g.e.a(var1, var11.N(), (int)this.j);
            }
         }
      }

      if(this.n >= 0.0F) {
         var1.r(this.n);
         var1.cn = this.n;
      }

      if(this.o != null) {
         var1.b(var1.eo + this.o.a, var1.ep + this.o.b);
         var1.eq += this.o.c;
         var1.cK = true;
      }

      return true;
   }
}
