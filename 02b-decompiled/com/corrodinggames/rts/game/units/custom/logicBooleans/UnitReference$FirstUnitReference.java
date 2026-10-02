package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class UnitReference$FirstUnitReference extends UnitReference {

   public g _withTag;
   public q relation;
   @LogicBoolean$Parameter
   public boolean incompleteBuildings;


   public UnitReference$FirstUnitReference() {
      this.relation = q.f;
   }

   public String getClassDebugName() {
      return "globalSearchForFirstUnit";
   }

   @LogicBoolean$Parameter
   public void withTag(String var1) {
      this._withTag = g.c(var1);
   }

   @LogicBoolean$Parameter
   public void relation(String var1) {
      try {
         this.relation = (q)ab.a(var1, (Enum)null, q.class);
      } catch (bo var3) {
         throw new am(var3.getMessage(), var3);
      }
   }

   public com.corrodinggames.rts.game.units.am getSingleRaw(y var1) {
      com.corrodinggames.rts.game.units.am[] var2 = com.corrodinggames.rts.game.units.am.bE.a();
      int var3 = 0;

      for(int var4 = com.corrodinggames.rts.game.units.am.bE.size(); var3 < var4; ++var3) {
         com.corrodinggames.rts.game.units.am var5 = var2[var3];
         if((this.relation == q.f || var1.bX.a(this.relation, var5.bX)) && var1 != var5) {
            h var6 = var5.de();
            if((this._withTag == null || var6 != null && g.a(this._withTag, var6)) && (var5.cm >= 1.0F || this.incompleteBuildings)) {
               return var5;
            }
         }
      }

      return null;
   }
}
