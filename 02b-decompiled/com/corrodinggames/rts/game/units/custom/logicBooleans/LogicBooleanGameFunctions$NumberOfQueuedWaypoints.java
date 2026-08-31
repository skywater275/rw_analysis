package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicBooleanGameFunctions$NumberOfQueuedWaypoints extends LogicBoolean$AbstractNumberBoolean {

   av type;


   @LogicBoolean$Parameter
   public void type(String var1) {
      try {
         this.type = (av)ab.a(var1, (Enum)null, av.class);
      } catch (bo var3) {
         throw new am(var3.getMessage(), var3);
      }
   }

   public String getName() {
      return "NumberOfQueuedWaypoints";
   }

   public float getValue(y var1) {
      if(this.type == null) {
         return (float)var1.av();
      } else {
         int var2 = 0;
         int var3 = var1.av();

         for(int var4 = 0; var4 < var3; ++var4) {
            au var5 = var1.k(var4);
            if(var5 != null) {
               boolean var6 = var5.d() == this.type;
               if(var6) {
                  ++var2;
               }
            }
         }

         return (float)var2;
      }
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
