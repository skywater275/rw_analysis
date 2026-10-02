package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$NumberOfUnitsInTeam$HandleCallbackCount;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.gameFramework.l;

public final class LogicBooleanGameFunctions$NumberOfUnitsInTeam extends LogicBoolean$AbstractNumberBoolean {

   public g _withTag;
   @LogicBoolean$Parameter
   public float withinRange = -1.0F;
   public float withinRangeSq = -1.0F;
   @LogicBoolean$Parameter
   public boolean incompleteBuildings;
   @LogicBoolean$Parameter
   public boolean factoryQueue;
   @LogicBoolean$Parameter
   public boolean neutralTeam;
   @LogicBoolean$Parameter
   public boolean allTeams;
   public boolean useAggressiveTeamInsteadOfNeutralTeam;
   public static final LogicBooleanGameFunctions$NumberOfUnitsInTeam$HandleCallbackCount handleCallbackCount = new LogicBooleanGameFunctions$NumberOfUnitsInTeam$HandleCallbackCount();


   @LogicBoolean$Parameter
   public void aggressiveTeam(boolean var1) {
      if(var1) {
         this.neutralTeam = true;
         this.useAggressiveTeamInsteadOfNeutralTeam = true;
      }

   }

   @LogicBoolean$Parameter
   public void withTag(String var1) {
      this._withTag = g.c(var1);
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.withinRange > 1000.0F) {
         throw new BooleanParseException("For CPU reasons withinRange argument cannot be over 1000 (but unlimited range is fine) in function:" + var1);
      } else {
         if(this.withinRange > 0.0F) {
            this.withinRangeSq = this.withinRange * this.withinRange;
            if(this.factoryQueue) {
               throw new BooleanParseException("\'factoryQueue\' and \'withinRange\' are not supported at the same time in function:" + var1);
            }
         }

      }
   }

   public String getName() {
      return "Unit count of " + this._withTag + (this.withinRange < 0.0F?"":" (within range " + this.withinRange + ")");
   }

   public float getValue(y var1) {
      g var2 = this._withTag;
      n var3;
      if(this.allTeams) {
         var3 = null;
      } else if(this.neutralTeam) {
         if(!this.useAggressiveTeamInsteadOfNeutralTeam) {
            var3 = n.i;
         } else {
            var3 = n.h;
         }
      } else {
         var3 = var1.bX;
      }

      int var4;
      if(var3 == null) {
         var4 = 0;
         n[] var5 = n.d();
         n[] var6 = var5;
         int var7 = var5.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            n var9 = var6[var8];
            if(var2 == null) {
               var4 += var9.a(this.incompleteBuildings, this.factoryQueue);
            } else {
               var4 += var9.a(var2, this.incompleteBuildings, this.factoryQueue);
            }
         }
      } else if(var2 == null) {
         var4 = var3.a(this.incompleteBuildings, this.factoryQueue);
      } else {
         var4 = var3.a(var2, this.incompleteBuildings, this.factoryQueue);
      }

      if(this.withinRange >= 0.0F && var4 != 0) {
         handleCallbackCount.withinRangeSq = this.withinRangeSq;
         handleCallbackCount.count = 0;
         handleCallbackCount.tag = var2;
         handleCallbackCount.incompleteBuildings = this.incompleteBuildings;
         handleCallbackCount.targetTeam = var3;
         l var10 = l.B();
         var10.cc.a(var1.eo, var1.ep, this.withinRange, var1, 0.0F, handleCallbackCount);
         return (float)handleCallbackCount.count;
      } else {
         return (float)var4;
      }
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }

}
