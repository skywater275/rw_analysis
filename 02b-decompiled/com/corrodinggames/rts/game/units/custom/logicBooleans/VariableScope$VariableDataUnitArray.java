package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataUnit;

public class VariableScope$VariableDataUnitArray extends VariableScope$VariableDataArray {

   am[] dataArray;


   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.unitArray;
   }

   public LogicBoolean$ReturnType getElementReturnType() {
      return LogicBoolean$ReturnType.unit;
   }

   public am readUnitIndex(int var1) {
      return var1 >= 0 && var1 < this.size?this.dataArray[var1]:null;
   }

   public void setUnitIndex(int var1, am var2) {
      if(var1 >= 0) {
         if(var1 <= 10000) {
            if(this.dataArray == null) {
               int var3 = var1 + 1;
               this.dataArray = new am[var3];
            }

            if(var1 >= this.dataArray.length) {
               byte var7 = 12;
               int var4 = this.dataArray.length;
               int var5 = var4 + (var4 < var7 / 2?var7:var4 >> 1);
               if(var5 < var1 + 1) {
                  var5 = var1 + 1;
               }

               am[] var6 = new am[var5];
               System.arraycopy(this.dataArray, 0, var6, 0, var4);
               this.dataArray = var6;
            }

            if(this.size < var1 + 1) {
               this.size = var1 + 1;
               if(this.size > this.dataArray.length) {
                  throw new RuntimeException("size:" + this.size + ", dataArray.length:" + this.dataArray.length);
               }
            }

            this.dataArray[var1] = var2;
         }
      }
   }

   public void setDataAtIndex(VariableScope$VariableData var1, int var2) {
      this.setUnitIndex(var2, var1.readUnit((y)null));
   }

   public VariableScope$VariableData readDataAtIndex(int var1) {
      return new VariableScope$VariableDataUnit(this.readUnitIndex(var1));
   }

   public void shrink() {
      for(int var1 = 0; var1 < this.size; ++var1) {
         am var2 = this.dataArray[var1];
         if(var2 == null || !VariableScope.isMarker(var2) && var2.bV) {
            for(int var3 = var1 + 1; var3 < this.size; ++var3) {
               this.dataArray[var3 - 1] = this.dataArray[var3];
            }

            this.dataArray[this.size - 1] = null;
            --this.size;
            --var1;
         }
      }

   }
}
