package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNumber;

public class VariableScope$VariableDataNumberArray extends VariableScope$VariableDataArray {

   float[] dataArray;
   public static final boolean trace = false;


   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.numberArray;
   }

   public LogicBoolean$ReturnType getElementReturnType() {
      return LogicBoolean$ReturnType.number;
   }

   public float readNumberIndex(int var1) {
      return var1 >= 0 && var1 < this.size?this.dataArray[var1]:0.0F;
   }

   public void setNumberIndex(int var1, float var2) {
      if(var1 >= 0) {
         if(var1 <= 10000) {
            if(this.dataArray == null) {
               int var3 = var1 + 1;
               this.dataArray = new float[var3];
            }

            if(var1 >= this.dataArray.length) {
               byte var7 = 12;
               int var4 = this.dataArray.length;
               int var5 = var4 + (var4 < var7 / 2?var7:var4 >> 1);
               if(var5 < var1 + 1) {
                  var5 = var1 + 1;
               }

               float[] var6 = new float[var5];
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

   public void shrink() {
      for(int var1 = 0; var1 < this.size; ++var1) {
         if(this.dataArray[var1] == 0.0F) {
            for(int var2 = var1 + 1; var2 < this.size; ++var2) {
               this.dataArray[var2 - 1] = this.dataArray[var2];
            }

            this.dataArray[this.size - 1] = 0.0F;
            --this.size;
            --var1;
         }
      }

   }

   public void setDataAtIndex(VariableScope$VariableData var1, int var2) {
      this.setNumberIndex(var2, var1.readNumber((y)null));
   }

   public VariableScope$VariableData readDataAtIndex(int var1) {
      return new VariableScope$VariableDataNumber((double)this.readNumberIndex(var1));
   }
}
