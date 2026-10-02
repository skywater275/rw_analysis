package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import java.util.Iterator;

class k extends com.corrodinggames.rts.game.units.a.x {

   boolean a;
   boolean b;


   public strictfp k(boolean var1, boolean var2) {
      super("changeTeam" + var1 + "d:" + var2);
      this.a = var1;
      this.b = var2;
   }

   public strictfp String b() {
      return this.b?"Selected player":(this.a?"<- Set player":"Set player ->");
   }

   public strictfp String d() {
      if(!this.b) {
         return this.a?"<-":"->";
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         com.corrodinggames.rts.game.n var2 = null;
         Iterator var3 = var1.bS.bZ.iterator();

         while(var3.hasNext()) {
            am var4 = (am)var3.next();
            if(var4 instanceof y) {
               y var5 = (y)var4;
               if(var5.cG && var1.bS.m(var5)) {
                  var2 = var5.bX;
               }
            }
         }

         String var6 = "";
         if(var2 != null) {
            var6 = var6 + "Team - " + (var2.k + 1) + "";
         }

         return var6;
      }
   }

   public strictfp String a() {
      return "Change targeted player for editor";
   }

   public strictfp float l() {
      return !com.corrodinggames.rts.gameFramework.f.g.bP?0.8F:0.5F;
   }

   public strictfp int m() {
      return this.b?2:4;
   }

   public strictfp com.corrodinggames.rts.game.units.a.t f() {
      return this.b?com.corrodinggames.rts.game.units.a.t.g:super.f();
   }

   public strictfp com.corrodinggames.rts.game.units.a.u e() {
      return this.b?com.corrodinggames.rts.game.units.a.u.i:super.e();
   }
}
