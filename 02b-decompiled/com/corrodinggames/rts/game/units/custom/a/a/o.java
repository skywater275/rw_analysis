package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.Iterator;

public class o extends com.corrodinggames.rts.game.units.custom.a.a {

   public bp a;
   public int b;
   public com.corrodinggames.rts.game.units.custom.h c;
   public boolean d;
   public boolean e;
   public int f = -1;
   public LogicBoolean g;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      bp var7 = bp.a(var0, var1, var2, var3 + "addUnitsIntoTransport");
      int var8 = var1.b(var2, var3 + "deleteNumUnitsFromTransport", Integer.valueOf(0)).intValue();
      com.corrodinggames.rts.game.units.custom.h var9 = com.corrodinggames.rts.game.units.custom.g.a(var1.b(var2, "deleteNumUnitsFromTransport_onlyWithTags", (String)null), (com.corrodinggames.rts.game.units.custom.h)null);
      boolean var10 = var1.a(var2, var3 + "startUnloadingTransport", Boolean.valueOf(false)).booleanValue();
      boolean var11 = var1.a(var2, var3 + "forceUnloadTransportNow", Boolean.valueOf(false)).booleanValue();
      int var12 = var1.b(var2, var3 + "forceUnloadTransportNow_onlyOnSlot", Integer.valueOf(-1)).intValue();
      LogicBoolean var13 = var1.b(var0, var2, var3 + "transportTargetNow", (LogicBoolean)null);
      if(var12 != -1 && !var11) {
         throw new bo("forceUnloadTransportNow_onlyOnSlot expects forceUnloadTransportNow");
      } else {
         if(!var7.b() || var8 > 0 || var10 || var11 || var13 != null) {
            o var14 = new o();
            if(!var7.b()) {
               var14.a = var7;
            }

            if(var8 > 0) {
               var14.b = var8;
               var14.c = var9;
            }

            var14.d = var10;
            var14.e = var11;
            var14.f = var12;
            var14.g = var13;
            var4.ac.add(var14);
         }

      }
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      int var6;
      am var8;
      if(this.b != 0) {
         for(var6 = 0; var6 < this.b; ++var6) {
            if(var1.B.size() > 0) {
               for(int var7 = var1.B.size() - 1; var7 >= 0; --var7) {
                  var8 = (am)var1.B.get(var7);
                  if(var8 == null) {
                     com.corrodinggames.rts.gameFramework.l.b("deleteNumUnitsFromTransport unit==null");
                  } else if(this.c == null || com.corrodinggames.rts.game.units.custom.g.a(this.c, var8.de())) {
                     var1.B.remove(var7);
                     var1.D(var8);
                     if(var8 != null) {
                        var8.ci();
                     }
                     break;
                  }
               }
            }
         }
      }

      if(this.a != null) {
         com.corrodinggames.rts.gameFramework.utility.m var9 = new com.corrodinggames.rts.gameFramework.utility.m();
         this.a.a(var9, var1.bX, var1, false);
         Iterator var10 = var9.iterator();

         while(var10.hasNext()) {
            var8 = (am)var10.next();
            var8.eo = var1.eo;
            var8.ep = var1.ep;
            var8.eq = var1.eq;
            var1.C(var8);
         }
      }

      if(this.d) {
         var1.L();
      }

      if(this.e) {
         for(var6 = var1.B.size() - 1; var6 >= 0; --var6) {
            if(this.f == -1 || this.f == var6) {
               boolean var12 = var1.B.size() % 2 == 0;
               var1.a((am)var1.B.get(var6), true, var12);
            }
         }
      }

      if(this.g != null) {
         am var11 = this.g.readUnit(var1);
         if(var11 != null && var11.bL && var1.d(var11, true)) {
            var1.C(var1);
         }
      }

      return true;
   }
}
