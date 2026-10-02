package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountEnemies;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.gameFramework.l;

public final class LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam extends LogicBoolean$AbstractNumberBoolean {

   public g _withTag;
   @LogicBoolean$Parameter
   public float withinRange = -1.0F;
   public float withinRangeSq = -1.0F;
   @LogicBoolean$Parameter
   public boolean incompleteBuildings;
   @LogicBoolean$Parameter
   public boolean factoryQueue;
   @LogicBoolean$Parameter
   public boolean ally;
   public static final LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountEnemies handleCallbackCountEnemies = new LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountEnemies();
   public static final LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly handleCallbackCountAlly = new LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly();


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
      return "Enemy Unit count of " + this._withTag + (this.withinRange < 0.0F?"":" (within range " + this.withinRange + ")");
   }

   public float getValue(y var1) {
      n var2 = var1.bX;
      byte var3 = 0;
      int var5;
      if(!this.ally) {
         var5 = var3 + var2.b(this._withTag, this.incompleteBuildings, this.factoryQueue);
      } else {
         var5 = var3 + var2.c(this._withTag, this.incompleteBuildings, this.factoryQueue);
      }

      if(this.withinRange >= 0.0F && var5 != 0) {
         l var4;
         if(!this.ally) {
            handleCallbackCountEnemies.withinRangeSq = this.withinRangeSq;
            handleCallbackCountEnemies.count = 0;
            handleCallbackCountEnemies.tag = this._withTag;
            handleCallbackCountEnemies.incompleteBuildings = this.incompleteBuildings;
            var4 = l.B();
            var4.cc.a(var1.eo, var1.ep, this.withinRange, var1, 0.0F, handleCallbackCountEnemies);
            return (float)handleCallbackCountEnemies.count;
         } else {
            handleCallbackCountAlly.withinRangeSq = this.withinRangeSq;
            handleCallbackCountAlly.count = 0;
            handleCallbackCountAlly.tag = this._withTag;
            handleCallbackCountAlly.incompleteBuildings = this.incompleteBuildings;
            handleCallbackCountAlly.ally = var1.bX;
            var4 = l.B();
            var4.cc.a(var1.eo, var1.ep, this.withinRange, var1, 0.0F, handleCallbackCountAlly);
            return (float)handleCallbackCountAlly.count;
         }
      } else {
         return (float)var5;
      }
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }

}
