package com.corrodinggames.rts.gameFramework.n;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Typeface;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.n.a;
import com.corrodinggames.rts.gameFramework.n.e;
import com.corrodinggames.rts.gameFramework.n.f;
import java.util.Iterator;

public class c {

   public static a a(f var0, com.corrodinggames.rts.game.b.a var1) {
      try {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         String var3 = var1.b;
         if(var3 == null) {
            var3 = "NULL";
         }

         String var4 = var1.b("id");
         if(var4 != null && !var4.equals("")) {
            var3 = var4;
         }

         var3 = var3.trim();
         String var6 = var1.d;
         if(var6 == null) {
            f.c("Error: no type field set for: " + var3);
            return null;
         } else {
            e var5 = e.a(var6);
            if(var5 == null) {
               f.c("Error: Unknown type:" + var6 + " found on " + var3);
               return null;
            } else {
               a var7 = new a();
               var7.t = var1;
               var7.g = var5;
               var7.b = var3;
               int var8 = 0;
               Iterator var9 = var0.J.iterator();

               while(var9.hasNext()) {
                  a var10 = (a)var9.next();
                  if(var10.b.equalsIgnoreCase(var7.b)) {
                     ++var8;
                  }
               }

               var7.c = var7.b;
               if(var8 != 0) {
                  var7.c = var7.c + "_" + var8;
               }

               var7.a = var1.b;
               Integer var16 = var7.d("team");
               if(var16 != null) {
                  var7.y = n.k(var16.intValue());
                  if(var7.y == null) {
                     var7.g("Cannot find team:" + var16);
                     return null;
                  }
               }

               var7.r = var7.b("delay", var7.r);
               var7.p = var7.b("repeatDelay", var7.p);
               var7.o = var7.a("repeatCount", var7.o);
               var7.q = var7.b("resetActivationAfter", var7.q);
               var7.h = var7.a("allToActivate", false);
               var7.d.b = var7.h;
               var7.s = var7.b("warmup", var7.s);
               var7.A = var7.a("globalMessage", (bb)null);
               var7.w = var7.a("textOffsetX", 0.0F);
               var7.x = var7.a("textOffsetY", 0.0F);
               if(var7.g == e.g || var7.g == e.a) {
                  var7.z = var7.a("text", (bb)null);
               }

               String var12;
               if(var7.g == e.g) {
                  var0.i = true;
                  var7.B = new Paint();
                  var7.B.a(true);
                  var7.B.a(Paint$Align.b);
                  var7.B.a(Typeface.a(Typeface.c, 1));
                  int var17 = var7.c("textColor", -1);
                  var7.B.b(var17);
                  int var11 = var7.a("textSize", 20);
                  var2.b(var7.B, (float)var11);
                  if(var7.B.f() == 0) {
                     var7.g("Text has an alpha of 0");
                  }

                  var12 = var7.b("style");
                  if(var12 != null && !var12.equals("")) {
                     if(var12.equalsIgnoreCase("arrow")) {
                        var7.C = true;
                     } else {
                        var7.g("Unknown style: " + var12);
                     }
                  }
               }

               if(var7.g == e.e) {
                  String var18 = var7.b("spawnUnits");
                  String var19 = "<unitAdd>";
                  var12 = "spawnUnits";

                  try {
                     var7.v = bp.a(var18, var19, var12);
                  } catch (bo var14) {
                     f.c(var14.getMessage());
                     return null;
                  }

                  if(var7.a() == null) {
                     var7.g("No team set");
                  }
               }

               if(var7.g == e.d) {
                  var7.a("addTeamTags");
                  var7.a("removeTeamTags");
               }

               if(var7.g == e.c) {
                  var7.a("add");
                  var7.a("set");
               }

               if(var7.g == e.i) {
                  var7.a((com.corrodinggames.rts.gameFramework.n.a.a)com.corrodinggames.rts.gameFramework.n.a.c.d(var7));
               }

               if(var7.g == e.j) {
                  var7.a((com.corrodinggames.rts.gameFramework.n.a.a)com.corrodinggames.rts.gameFramework.n.a.b.d(var7));
               }

               var7.a("comment");
               var7.a("team");
               var7.a("globalMessage");
               var7.a("globalMessage_delayPerChar");
               var7.a("globalMessage_textColor");
               var7.a("debugMessage");
               var7.a("showOnMap");
               var7.a("text");
               var7.a("target");
               var7.a("onlyIfEmpty");
               if(var7.g == e.b) {
                  var7.a("unload");
               }

               if(var7.g == e.f) {
                  var7.a("onlyIfEmpty");
               }

               return var7;
            }
         }
      } catch (RuntimeException var15) {
         throw new com.corrodinggames.rts.game.b.f("Error while reading: " + var1.b(), var15);
      }
   }
}
