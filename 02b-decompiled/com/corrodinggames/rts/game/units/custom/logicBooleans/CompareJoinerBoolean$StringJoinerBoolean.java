package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StaticString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;

public final class CompareJoinerBoolean$StringJoinerBoolean extends CompareJoinerBoolean {

   public String type() {
      return "+";
   }

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      for(int var6 = 0; var6 < this.children.length; ++var6) {
         if(this.children[var6].getReturnType() != LogicBoolean$ReturnType.string) {
            this.children[var6] = LogicString$StringCast.createStringCast(this.children[var6]);
         }
      }

      boolean var11 = true;
      LogicBoolean[] var7 = this.children;
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         LogicBoolean var10 = var7[var9];
         if(!(var10 instanceof LogicString$StaticString)) {
            var11 = false;
         }
      }

      if(var11) {
         String var12 = this.readString((y)null);
         return new LogicString$StaticString(var12);
      } else {
         return super.validateAndOptimize(var1, var2, var3, var4, var5);
      }
   }

   public String readString(y var1) {
      String var2 = this.children[0].readString(var1);

      for(int var3 = 1; var3 < this.children.length; ++var3) {
         var2 = var2 + this.children[var3].readString(var1);
      }

      return var2;
   }

   public boolean read(y var1) {
      return false;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.string;
   }
}
