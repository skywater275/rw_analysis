/*
 * v19.115m 重建: 02b custom/a/a/h.java 120 行直译 (CustomRepairAction 自定义目标/内存动作)
 * 依赖: VariableScope 系列 03 已有; var0.r(var15)/var0.bw 03 待战役 (简化)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryNames;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.Iterator;

public class CustomRepairAction extends ActionBase {

   VariableScope$CachedWriter a;
   boolean b;
   LogicBoolean c;
   LogicBoolean d;
   VariableScope$MemoryNames e;

   public static void a(ModUnitRegistry var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.actions.d var4, String var5, boolean var6) throws bo {
      boolean var7 = var1.a(var2, var3 + "swapCustomTarget1And2", Boolean.valueOf(false)).booleanValue();
      LogicBoolean var8 = var1.b(var0, var2, var3 + "setCustomTarget1", (LogicBoolean)null);
      LogicBoolean var9 = var1.b(var0, var2, var3 + "setCustomTarget2", (LogicBoolean)null);
      VariableScope$MemoryWriter var10 = null;
      String var11 = var1.b(var2, var3 + "setUnitMemory", (String)null);
      if(var11 != null) {
         var10 = VariableScope.createMemoryWriter(var11, var0, var2, var3 + "setUnitMemory");
      }
      String var12 = var1.b(var2, var3 + "shrinkArrays", (String)null);
      VariableScope$MemoryNames var13 = null;
      if(var12 != null) {
         var13 = VariableScope.createMemoryNameList(var12, var0, (LogicBoolean$ReturnType)null, var2, var3 + "shrinkArrays");
         Iterator var14 = var13.names.iterator();
         while(var14.hasNext()) {
            VariableScope$VariableName var15 = (VariableScope$VariableName)var14.next();
            // 02b: VariableScope$VariableDefinition var16 = var0.r.get(var15); -- ModUnitRegistry.r 03 待战役, 简化
            if(!LogicBoolean$ReturnType.isArrayType(LogicBoolean$ReturnType.numberArray)) {
               throw new bo("Memory: " + var15 + " is type: " + LogicBoolean$ReturnType.numberArray + " expected an array type", var2, var3 + "shrinkArrays");
            }
            if(LogicBoolean$ReturnType.numberArray != LogicBoolean$ReturnType.numberArray && LogicBoolean$ReturnType.numberArray != LogicBoolean$ReturnType.unitArray) {
               throw new bo("Memory: " + var15 + " is type: " + LogicBoolean$ReturnType.numberArray + " only number and unit arrays are supported by shrinkArrays", var2, var3 + "shrinkArrays");
            }
         }
      }
      if(var7 || var8 != null || var9 != null || var10 != null || var13 != null) {
         CustomRepairAction var17 = new CustomRepairAction();
         var17.a = var10;
         var17.b = var7;
         var17.c = var8;
         var17.d = var9;
         var17.e = var13;
         var4.ac.add(var17);
      }
   }

   public boolean a(CustomUnitType var1, GameAction var2, PointF var3, UnitInstance var4, int var5) {
      if(this.a != null) {
         try {
            this.a.writeToUnit(var1);
         } catch (bo var6) {
            throw new RuntimeException(var6);
         }
      }
      UnitInstance var6;
      if(this.b) {
         var6 = var1.bu;
         var1.bu = var1.bv;
         var1.bv = var6;
      }
      if(this.c != null) {
         var6 = this.c.readUnit(var1);
         var6 = VariableScope.getSafeUnitReferenceForStorage(var6);
         var1.bu = var6;
      }
      if(this.d != null) {
         var6 = this.d.readUnit(var1);
         var6 = VariableScope.getSafeUnitReferenceForStorage(var6);
         var1.bv = var6;
      }
      if(this.e != null) {
         a(var1, this.e);
      }
      return true;
   }

   public static void a(CustomUnitType var0, VariableScope$MemoryNames var1) {
      // 02b: var0.bw 内存容器 03 待战役, 简化
   }
}
