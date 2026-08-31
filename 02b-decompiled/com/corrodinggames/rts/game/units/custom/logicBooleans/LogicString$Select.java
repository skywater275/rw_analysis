package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicString$Select extends LogicString {

   LogicBoolean$ReturnType commonType;
   LogicBoolean selector;
   LogicBoolean childA;
   LogicBoolean childB;


   public void setArgumentsRaw(String var1, l var2, String var3) {
      if(var1 != null && !"".equals(var1)) {
         ArrayList var4 = al.a(var1, ",", false);
         this.validateNumberOfArguments(var4.size());
         this.selector = LogicBooleanLoader.parseBooleanBlock(var2, (String)var4.get(0), true);
         if(this.selector == null) {
            throw new BooleanParseException("Expected non-null argument");
         } else {
            this.childA = LogicBooleanLoader.parseBooleanBlock(var2, (String)var4.get(1), false);
            if(this.childA == null) {
               throw new BooleanParseException("Expected non-null argument");
            } else {
               this.childB = LogicBooleanLoader.parseBooleanBlock(var2, (String)var4.get(2), false);
               if(this.childB == null) {
                  throw new BooleanParseException("Expected non-null argument");
               } else {
                  this.commonType = this.childA.getReturnType();
                  if(this.commonType != this.childB.getReturnType()) {
                     throw new BooleanParseException("Select() expected 2 and 3 argument to be the same type, got: " + this.commonType.name() + " and " + this.childB.getReturnType().name());
                  }
               }
            }
         }
      } else {
         this.validateNumberOfArguments(0);
      }
   }

   public void validateNumberOfArguments(int var1) {
      if(var1 != 3) {
         throw new BooleanParseException("Expected 3 arguments");
      }
   }

   public LogicBoolean$ReturnType getReturnType() {
      return this.commonType;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "(selector if:(" + this.selector.getMatchFailReasonForPlayer(var1) + ") then:(" + this.childA.getMatchFailReasonForPlayer(var1) + ") ) else:(" + this.childB.getMatchFailReasonForPlayer(var1) + ") )";
   }

   public String readString(y var1) {
      boolean var2 = this.selector.read(var1);
      return var2?this.childA.readString(var1):this.childB.readString(var1);
   }

   public boolean read(y var1) {
      boolean var2 = this.selector.read(var1);
      return var2?this.childA.read(var1):this.childB.read(var1);
   }

   public float readNumber(y var1) {
      boolean var2 = this.selector.read(var1);
      return var2?this.childA.readNumber(var1):this.childB.readNumber(var1);
   }

   public am readUnit(y var1) {
      boolean var2 = this.selector.read(var1);
      return var2?this.childA.readUnit(var1):this.childB.readUnit(var1);
   }
}
