package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.gameFramework.f;

public class LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly extends i {

   public n ally;
   public g tag;
   public int count;
   public float withinRangeSq;
   public boolean incompleteBuildings;


   public void setup(y var1, float var2) {}

   public int excludeTeam(y var1) {
      return -1;
   }

   public n onlyEnemiesOfTeam(y var1) {
      return null;
   }

   public n onlyTeam(y var1) {
      return null;
   }

   public void callback(y var1, float var2, am var3) {
      if(this.ally != var3.bX) {
         if(this.ally.d(var3.bX)) {
            h var4 = var3.de();
            if(this.tag == null || var4 != null && g.a(this.tag, var4)) {
               float var5 = f.a(var1.eo, var1.ep, var3.eo, var3.ep);
               if(var5 < this.withinRangeSq) {
                  if(var3.cm < 1.0F && !this.incompleteBuildings) {
                     return;
                  }

                  ++this.count;
               }
            }

         }
      }
   }
}
