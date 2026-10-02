package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class l extends com.corrodinggames.rts.game.units.custom.a.a {

   boolean a;
   com.corrodinggames.rts.game.units.custom.h b;
   com.corrodinggames.rts.game.units.custom.h c;
   com.corrodinggames.rts.game.units.custom.h d;
   com.corrodinggames.rts.game.units.custom.h e;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      boolean var7 = var1.a(var2, var3 + "resetToDefaultTags", Boolean.valueOf(false)).booleanValue();
      com.corrodinggames.rts.game.units.custom.h var8 = var1.a(var0, var2, var3 + "temporarilyAddTags", (com.corrodinggames.rts.game.units.custom.h)null);
      com.corrodinggames.rts.game.units.custom.h var9 = var1.a(var0, var2, var3 + "temporarilyRemoveTags", (com.corrodinggames.rts.game.units.custom.h)null);
      if(var7 || var8 != null || var9 != null) {
         l var10 = new l();
         var10.a = var7;
         var10.b = var8;
         var10.c = var9;
         var4.ac.add(var10);
      }

      com.corrodinggames.rts.game.units.custom.h var13 = var1.a(var0, var2, var3 + "addGlobalTeamTags", (com.corrodinggames.rts.game.units.custom.h)null);
      com.corrodinggames.rts.game.units.custom.h var11 = var1.a(var0, var2, var3 + "removeGlobalTeamTags", (com.corrodinggames.rts.game.units.custom.h)null);
      if(var13 != null || var11 != null) {
         l var12 = new l();
         var12.d = var13;
         var12.e = var11;
         var4.ac.add(var12);
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.a) {
         var1.j(false);
      }

      if(this.c != null) {
         var1.b(this.c);
      }

      if(this.b != null) {
         var1.a(this.b);
      }

      if(this.d != null) {
         var1.bX.b(this.d);
      }

      if(this.e != null) {
         var1.bX.c(this.e);
      }

      return true;
   }
}
