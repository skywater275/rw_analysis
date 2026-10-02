package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicString$WrappingLogicString extends LogicString {

   LogicBoolean[] children;


   public void setArgumentsRaw(String var1, l var2, String var3) {
      if(var1 != null && !"".equals(var1)) {
         ArrayList var4 = al.a(var1, ",", false);
         this.validateNumberOfArguments(var4.size());
         this.children = new LogicBoolean[var4.size()];

         for(int var5 = 0; var5 < var4.size(); ++var5) {
            this.children[var5] = LogicBooleanLoader.parseBooleanBlock(var2, (String)var4.get(var5), false);
            if(this.children == null) {
               throw new BooleanParseException("Expected non-null argument");
            }
         }

      } else {
         this.validateNumberOfArguments(0);
      }
   }

   public void validateNumberOfArguments(int var1) {
      if(var1 != 1) {
         throw new BooleanParseException("Expected 1 argument");
      }
   }
}
