package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterFactory;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElementIndex;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;

public class VariableScope$MemoryWriterFactory extends VariableScope$CachedWriter$WriterFactory {

   l meta;
   VariableScope$VariableMapping target;
   boolean noValues;


   public VariableScope$MemoryWriterFactory(l var1, VariableScope$VariableMapping var2) {
      this.meta = var1;
      this.target = var2;
   }

   public VariableScope$MemoryWriterFactory(l var1) {
      this.meta = var1;
      if(var1 != null) {
         this.target = var1.r;
      }

   }

   public VariableScope$CachedWriter$WriterElement createWriterElement(String var1, String var2, String var3, String var4) {
      if(!var2.equals("=")) {
         throw new bo("Only \'=\' is supported here, got:" + var2);
      } else {
         if(!this.noValues) {
            if(var3 == null) {
               throw new bo("Expected a value for: " + var1 + " (likely missing \'=\')");
            }
         } else if(var3 != null) {
            throw new bo("Expected no value for: " + var1 + " (Remove \'=" + var3 + "\')");
         }

         LogicBoolean var5 = null;
         if(var3 != null) {
            try {
               var5 = LogicBooleanLoader.parseBooleanBlock(this.meta, var3, false);
            } catch (RuntimeException var13) {
               throw new RuntimeException("LogicBoolean - Error: " + var13.getMessage() + ", [parsing: \'" + var3 + "\']", var13);
            }
         }

         VariableScope$VariableName var6;
         LogicBoolean$ReturnType var7;
         if(this.target != null) {
            VariableScope$VariableDefinition var8 = this.target.get(var1);
            if(var8 == null) {
               throw new bo("Unknown variable: " + var1 + " (has it been defined in this unit?)");
            }

            var6 = var8.name;
            var7 = var8.type;
         } else {
            var6 = VariableScope$VariableName.get(var1);
            var7 = LogicBoolean$ReturnType.undefined;
         }

         LogicBoolean$ReturnType var14 = var7;
         LogicBoolean var9 = null;
         if(var4 != null) {
            if(!LogicBoolean$ReturnType.isArrayType(var7)) {
               if(var7 != LogicBoolean$ReturnType.undefined) {
                  throw new bo("Variable: " + var1 + " is not an array type " + var7 + " cannot use [] index on it.");
               }
            } else {
               var14 = LogicBoolean$ReturnType.getArrayBaseType(var7);
            }

            try {
               var9 = LogicBooleanLoader.parseBooleanBlock(this.meta, var4, false);
            } catch (RuntimeException var12) {
               throw new RuntimeException("Error reading " + var1 + "[] array index: " + var12.getMessage() + ", [parsing: \'" + var4 + "\']", var12);
            }

            if(var9 != null && var9.getReturnType() != LogicBoolean$ReturnType.number) {
               throw new RuntimeException("Expected " + var1 + "[] array index to be a number got " + var9.getReturnType() + " type, [parsing: \'" + var4 + "\']");
            }

            if(var9 == null) {
               throw new RuntimeException("Missing " + var1 + "[] array index");
            }
         } else if(!this.noValues && LogicBoolean$ReturnType.isArrayType(var7) && (var3 == null || !"null".equalsIgnoreCase(var3.trim()))) {
            throw new bo("Variable " + var1 + " is an array type. Expected: NAME[INDEX]=VALUE format (or NAME=null)");
         }

         Object var10;
         if(var9 == null) {
            var10 = new VariableScope$MemoryWriterFactory$MemoryWriterElement();
            ((VariableScope$MemoryWriterFactory$MemoryWriterElement)var10).name = var6;
            ((VariableScope$MemoryWriterFactory$MemoryWriterElement)var10).value = var5;
         } else {
            VariableScope$MemoryWriterFactory$MemoryWriterElementIndex var11 = new VariableScope$MemoryWriterFactory$MemoryWriterElementIndex();
            var10 = var11;
            var11.name = var6;
            var11.value = var5;
            var11.nameIndex = var9;
         }

         if(var14 != LogicBoolean$ReturnType.undefined && var5 != null && var5.getReturnType() != var14) {
            if(!LogicBoolean.isStaticNull(var5)) {
               throw new bo("Variable: " + var1 + " expects " + LogicBoolean$ReturnType.toUserString(var14) + " type getting: " + LogicBoolean$ReturnType.toUserString(var5.getReturnType()) + " from: " + var3);
            }

            boolean var15 = LogicBoolean$ReturnType.canBeNull(var14);
            if(!var15) {
               throw new bo("Variable: " + var1 + " of type " + var14 + " cannot be set to null.");
            }
         }

         return (VariableScope$CachedWriter$WriterElement)var10;
      }
   }
}
