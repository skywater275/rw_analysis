package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.regex.Pattern;

public class f extends com.corrodinggames.rts.game.units.custom.a.a {

   aj a;
   aj b;
   aj c;
   aj d;
   aj e;
   aj f;
   static final Pattern g = Pattern.compile("%\\{([^\\]]*?)\\}");


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      aj var7 = ag.a(var0, var1, var2, "showMessageToPlayer", (String)null);
      aj var8 = ag.a(var0, var1, var2, "showMessageToAllPlayers", (String)null);
      aj var9 = ag.a(var0, var1, var2, "showMessageToAllEnemyPlayers", (String)null);
      aj var10 = ag.a(var0, var1, var2, "showQuickWarLogToPlayer", (String)null);
      aj var11 = ag.a(var0, var1, var2, "showQuickWarLogToAllPlayers", (String)null);
      aj var12 = ag.a(var0, var1, var2, "debugMessage", (String)null);
      if(var7 != null || var8 != null || var9 != null || var10 != null || var11 != null || var12 != null) {
         f var13 = new f();
         var13.a = var7;
         var13.b = var8;
         var13.c = var9;
         var13.d = var10;
         var13.e = var11;
         var13.f = var12;
         var4.ac.add(var13);
      }

   }

   public String a(com.corrodinggames.rts.game.units.custom.j var1, String var2) {
      if(var2 == null) {
         var2 = null;
      }

      return var2;
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.a != null && var1.bX == var6.bs) {
         ad.a((String)null, this.a(var1, this.a.b(var1)));
      }

      if(this.b != null) {
         ad.a((String)null, this.a(var1, this.b.b(var1)));
      }

      if(this.c != null && var6.bs != null && var1.bX.c(var6.bs)) {
         ad.a((String)null, this.a(var1, this.c.b(var1)));
      }

      if(this.d != null && var1.bX == var6.bs) {
         var6.bS.i.a(this.a(var1, this.d.b(var1)));
      }

      if(this.e != null) {
         var6.bS.i.a(this.a(var1, this.e.b(var1)));
      }

      if(this.f != null && var6.bv && var6.bl) {
         String var7 = var1.dt().i() + "(" + var1.eh + ") Debug: " + this.a(var1, this.f.b(var1));
         ad.a((String)null, var7);
      }

      return true;
   }

}
