package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.utility.am;
import com.corrodinggames.rts.gameFramework.utility.m;

public class UnitReference$TransportingUnitReference extends UnitReference {

   l meta;
   @LogicBoolean$Parameter
   public int slot = -1;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new am("TransportingUnitReference requires metadata");
      } else {
         this.meta = var1;
      }
   }

   public com.corrodinggames.rts.game.units.am getSingleRaw(y var1) {
      com.corrodinggames.rts.game.units.am var2 = null;
      m var3 = var1.bz();
      if(var3 != null) {
         if(this.slot == -1) {
            if(var3.size() > 0) {
               var2 = (com.corrodinggames.rts.game.units.am)var3.get(0);
            }
         } else if(this.slot >= 0 && this.slot < var3.size()) {
            var2 = (com.corrodinggames.rts.game.units.am)var3.get(this.slot);
         }
      }

      return var2;
   }

   public String getClassDebugName() {
      return "transporting";
   }
}
