package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class i extends com.corrodinggames.rts.game.units.custom.a.a {

   VariableScope$MemoryWriter a;
   LogicBoolean b;
   com.corrodinggames.rts.game.units.custom.h c;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      LogicBoolean var7 = var1.b(var0, var2, var3 + "sendMessageTo", (LogicBoolean)null);
      VariableScope$MemoryWriter var8 = null;
      String var9 = var1.b(var2, var3 + "sendMessageWithData", (String)null);
      if(var9 != null) {
         var8 = VariableScope.createGenericKeyValueWriter(var9, var0, var2, var3 + "sendMessageWithData");
      }

      com.corrodinggames.rts.game.units.custom.h var10 = var1.a(var0, var2, var3 + "sendMessageWithTags", (com.corrodinggames.rts.game.units.custom.h)null);
      if(var7 != null) {
         i var11 = new i();
         var11.b = var7;
         var11.a = var8;
         var11.c = var10;
         var4.ac.add(var11);
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.b != null) {
         am var6 = this.b.readUnit(var1);
         if(var6 != null) {
            VariableScope var7 = null;
            if(this.a != null) {
               var7 = new VariableScope();
               this.a.writeToMemory(var7, var1);
            }

            var6.a(af.q, var1, this.c, var7);
         }
      }

      return true;
   }
}
