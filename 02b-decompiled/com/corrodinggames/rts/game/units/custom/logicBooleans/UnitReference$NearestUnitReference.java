package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$NearestUnitReference$HandleCallbackNearest;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class UnitReference$NearestUnitReference extends UnitReference {

   public float withinRange = 500.0F;
   public float withinRangeSq;
   public g _withTag;
   public g _withoutTag;
   public q relation;
   @LogicBoolean$Parameter
   public boolean incompleteBuildings;
   public static final UnitReference$NearestUnitReference$HandleCallbackNearest handleCallbackNearest = new UnitReference$NearestUnitReference$HandleCallbackNearest();


   public UnitReference$NearestUnitReference() {
      this.withinRangeSq = this.withinRange * this.withinRange;
      this.relation = q.f;
   }

   public String getClassDebugName() {
      return "NearestUnit";
   }

   @LogicBoolean$Parameter
   public void withinRange(float var1) {
      if(var1 > 1500.0F) {
         throw new am("NearestUnit distance cannot be over 1500 is: " + var1);
      } else {
         this.withinRange = var1;
         this.withinRangeSq = var1 * var1;
      }
   }

   @LogicBoolean$Parameter
   public void withTag(String var1) {
      this._withTag = g.c(var1);
   }

   @LogicBoolean$Parameter
   public void withoutTag(String var1) {
      this._withoutTag = g.c(var1);
   }

   @LogicBoolean$Parameter
   public void relation(String var1) {
      try {
         this.relation = (q)ab.a(var1, (Enum)q.f, q.class);
      } catch (bo var3) {
         throw new am(var3.getMessage(), var3);
      }
   }

   public com.corrodinggames.rts.game.units.am getSingleRaw(y var1) {
      handleCallbackNearest.nearest = null;
      handleCallbackNearest.withinRangeSq = this.withinRangeSq;
      handleCallbackNearest.tag = this._withTag;
      handleCallbackNearest.withoutTag = this._withoutTag;
      handleCallbackNearest.incompleteBuildings = this.incompleteBuildings;
      handleCallbackNearest.relation = this.relation;
      l var2 = l.B();
      var2.cc.a(var1.eo, var1.ep, this.withinRange, var1, 0.0F, handleCallbackNearest);
      return handleCallbackNearest.nearest;
   }

}
