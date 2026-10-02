package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$UnitReferenceOrUnitType {

   v unitType;
   UnitReference unitReference;


   UnitReference$UnitReferenceOrUnitType(v var1) {
      this.unitType = var1;
   }

   UnitReference$UnitReferenceOrUnitType(UnitReference var1) {
      this.unitReference = var1;
   }

   public am getUnitOrSharedUnit(am var1) {
      if(this.unitType != null) {
         return am.c(this.unitType.c());
      } else {
         if(this.unitReference != null) {
            am var2 = this.unitReference.get(var1);
            if(var2 != null) {
               return var2;
            }
         }

         return null;
      }
   }

   public am getUnitReferenceOrNull(am var1) {
      if(this.unitReference != null) {
         am var2 = this.unitReference.get(var1);
         if(var2 != null) {
            return var2;
         }
      }

      return null;
   }

   public as getTypeOrNull(am var1) {
      if(this.unitType != null) {
         return this.unitType.c();
      } else {
         if(this.unitReference != null) {
            am var2 = this.unitReference.get(var1);
            if(var2 != null) {
               return var2.r();
            }
         }

         return null;
      }
   }
}
