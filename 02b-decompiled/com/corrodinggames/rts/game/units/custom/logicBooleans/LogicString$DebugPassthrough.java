package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$WrappingLogicString;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.ad;

public class LogicString$DebugPassthrough extends LogicString$WrappingLogicString {

   public void addMessage(y var1) {
      l var2 = l.B();
      if(var2.bv && var2.bl) {
         String var3 = "";
         if(var1 != null) {
            var3 = var1.r().i() + "(" + var1.eh + ") ";
         }

         String var4 = var3 + "DebugPassthrough: " + this.children[0].getMatchFailReasonForPlayer(var1);
         ad.a((String)null, var4);
      }

   }

   public LogicBoolean$ReturnType getReturnType() {
      return this.children[0].getReturnType();
   }

   public boolean read(y var1) {
      this.addMessage(var1);
      return this.children[0].read(var1);
   }

   public float readNumber(y var1) {
      this.addMessage(var1);
      return this.children[0].readNumber(var1);
   }

   public String readString(y var1) {
      this.addMessage(var1);
      return this.children[0].readString(var1);
   }

   public am readUnit(y var1) {
      this.addMessage(var1);
      return this.children[0].readUnit(var1);
   }
}
