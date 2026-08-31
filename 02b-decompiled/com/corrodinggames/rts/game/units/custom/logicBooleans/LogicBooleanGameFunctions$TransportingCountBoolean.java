package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.gameFramework.utility.m;

public class LogicBooleanGameFunctions$TransportingCountBoolean extends LogicBoolean$AbstractNumberBoolean {

   public g _withTag;
   public boolean filtered;
   @LogicBoolean$Parameter
   public int slot = -1;


   public String getName() {
      return "TransportingCount";
   }

   @LogicBoolean$Parameter
   public void withTag(String var1) {
      this._withTag = g.c(var1);
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this._withTag != null || this.slot != -1) {
         this.filtered = true;
      }

   }

   public float getValue(y var1) {
      int var2;
      if(!this.filtered) {
         var2 = var1.bB();
      } else {
         var2 = 0;
         m var3 = var1.bz();
         if(var3 != null) {
            Object[] var4 = var3.a();

            for(int var5 = var3.a - 1; var5 >= 0; --var5) {
               y var6 = (y)var4[var5];
               if(var6 != null && (this.slot == -1 || var5 == this.slot)) {
                  if(this._withTag != null) {
                     h var7 = var6.de();
                     if(!g.a(this._withTag, var7)) {
                        continue;
                     }
                  }

                  ++var2;
               }
            }
         }
      }

      return (float)var2;
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
