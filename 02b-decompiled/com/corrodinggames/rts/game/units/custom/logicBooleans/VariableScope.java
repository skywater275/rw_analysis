package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.t;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$EmptyVariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryNames;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElementIndex;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataBoolArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNull;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNumber;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNumberArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataUnit;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataUnitArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import java.io.IOException;
import java.util.Iterator;

public class VariableScope {

   public static final VariableScope emptyVariableScope = new VariableScope$EmptyVariableScope();
   public static final String nullOrMissingString = "";
   static final VariableScope$VariableName[] emptyNames = new VariableScope$VariableName[0];
   static final VariableScope$VariableData[] emptyData = new VariableScope$VariableData[0];
   VariableScope$VariableName[] variableNames;
   VariableScope$VariableData[] variableData;
   public static final VariableScope$VariableDataNull variableDataNull = new VariableScope$VariableDataNull();


   public VariableScope() {
      this.variableNames = emptyNames;
      this.variableData = emptyData;
   }

   public boolean isEmpty() {
      for(int var1 = 0; var1 < this.variableData.length; ++var1) {
         VariableScope$VariableData var2 = this.variableData[var1];
         if(var2 != null) {
            return false;
         }
      }

      return true;
   }

   public String debugMemory(boolean var1, boolean var2) {
      String var3 = "";

      for(int var4 = 0; var4 < this.variableData.length; ++var4) {
         VariableScope$VariableData var5 = this.variableData[var4];
         if(var5 != null) {
            var3 = var3 + VariableScope$VariableName.access$000(this.variableNames[var4]) + "=" + var5.valueToStringDebug((y)null);
            if(var2) {
               var3 = var3 + " (" + var5.getReturnType().name() + ")";
            }

            if(var1) {
               var3 = var3 + "\n";
            } else {
               var3 = var3 + "|";
            }
         }
      }

      return var3;
   }

   public VariableScope$VariableData getDataObjectRaw(VariableScope$VariableName var1) {
      for(int var2 = 0; var2 < this.variableData.length; ++var2) {
         if(this.variableNames[var2] == var1) {
            return this.variableData[var2];
         }
      }

      return variableDataNull;
   }

   public void setArrayDataRaw(VariableScope$VariableName var1, VariableScope$VariableData var2, int var3) {
      Object var4 = null;
      LogicBoolean$ReturnType var5 = LogicBoolean$ReturnType.undefined;
      if(var2 != null) {
         var5 = var2.getReturnType();
      }

      for(int var6 = 0; var6 < this.variableData.length; ++var6) {
         if(this.variableNames[var6] == var1 && this.variableData[var6] instanceof VariableScope$VariableDataArray) {
            VariableScope$VariableDataArray var7 = (VariableScope$VariableDataArray)this.variableData[var6];
            if(var5 == LogicBoolean$ReturnType.undefined || var7.getElementReturnType() == var5) {
               var4 = var7;
            }
         }
      }

      if(var4 != null || var2 != null) {
         if(var4 == null) {
            if(var5 == LogicBoolean$ReturnType.number) {
               var4 = new VariableScope$VariableDataNumberArray();
            } else if(var5 == LogicBoolean$ReturnType.bool) {
               var4 = new VariableScope$VariableDataBoolArray();
            } else {
               if(var5 != LogicBoolean$ReturnType.unit) {
                  l.b("Unhandled array type: " + var5);
                  return;
               }

               var4 = new VariableScope$VariableDataUnitArray();
            }

            this.setDataRaw(var1, (VariableScope$VariableData)var4);
         }

         ((VariableScope$VariableDataArray)var4).setDataAtIndex(var2, var3);
      }
   }

   public void setDataRaw(VariableScope$VariableName var1, VariableScope$VariableData var2) {
      if(var2 == null) {
         var2 = variableDataNull;
      }

      for(int var3 = 0; var3 < this.variableData.length; ++var3) {
         if(this.variableNames[var3] == var1) {
            this.variableData[var3] = (VariableScope$VariableData)var2;
            return;
         }
      }

      VariableScope$VariableName[] var6 = new VariableScope$VariableName[this.variableData.length + 1];
      VariableScope$VariableData[] var4 = new VariableScope$VariableData[this.variableData.length + 1];

      for(int var5 = 0; var5 < this.variableData.length; ++var5) {
         var4[var5] = this.variableData[var5];
         var6[var5] = this.variableNames[var5];
      }

      var4[var4.length - 1] = (VariableScope$VariableData)var2;
      var6[var6.length - 1] = var1;
      this.variableData = var4;
      this.variableNames = var6;
   }

   public void clearAllData() {
      this.variableData = emptyData;
      this.variableNames = emptyNames;
   }

   public void setUnit(VariableScope$VariableDefinition var1, am var2) {
      if(var1.type != LogicBoolean$ReturnType.unit) {
         ;
      }

      this.setDataRaw(var1.name, new VariableScope$VariableDataUnit(var2));
   }

   am getUnit(VariableScope$VariableName var1) {
      return this.getDataObjectRaw(var1).readUnit((y)null);
   }

   LogicBoolean getAsLogicBoolean(VariableScope$VariableName var1) {
      return this.getDataObjectRaw(var1);
   }

   public void setFromLogicBoolean(VariableScope$VariableName var1, y var2, LogicBoolean var3, LogicBoolean var4) {
      Object var5 = null;
      if(var3 != null) {
         LogicBoolean$ReturnType var6 = var3.getReturnType();
         if(var6 == LogicBoolean$ReturnType.bool) {
            var5 = new VariableScope$VariableDataBoolean(var3.read(var2));
         } else if(var6 == LogicBoolean$ReturnType.unit) {
            am var7 = var3.readUnit(var2);
            var7 = getSafeUnitReferenceForStorage(var7);
            var5 = new VariableScope$VariableDataUnit(var7);
         } else if(var6 == LogicBoolean$ReturnType.number) {
            var5 = new VariableScope$VariableDataNumber((double)var3.readNumber(var2));
         } else if(var6 == LogicBoolean$ReturnType.string) {
            var5 = new VariableScope$VariableDataString(var3.readString(var2));
         }
      }

      if(var4 != null) {
         int var8 = (int)var4.readNumber(var2);
         this.setArrayDataRaw(var1, (VariableScope$VariableData)var5, var8);
      } else {
         this.setDataRaw(var1, (VariableScope$VariableData)var5);
      }

   }

   double getNumber(VariableScope$VariableName var1) {
      return (double)this.getDataObjectRaw(var1).readNumber((y)null);
   }

   String getString(VariableScope$VariableName var1) {
      return this.getDataObjectRaw(var1).readString((y)null);
   }

   boolean getBoolean(VariableScope$VariableName var1) {
      return this.getDataObjectRaw(var1).read((y)null);
   }

   public static void writeOut(as var0, VariableScope var1) {
      if(var1 == null) {
         var0.c(-2);
      } else if(var1.variableData.length == 0) {
         var0.c(-1);
      } else {
         var0.c(0);
         var0.a((short)var1.variableData.length);
         int var2 = var1.variableData.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            VariableScope$VariableData var4 = var1.variableData[var3];
            var0.c(VariableScope$VariableName.access$000(var1.variableNames[var3]));
            boolean var5 = false;
            var0.a(var5);
            if(!var5) {
               writeOutDynamicData(var0, var4);
            }
         }

      }
   }

   public static VariableScope readIn(k var0) {
      byte var1 = var0.d();
      if(var1 == -2) {
         return null;
      } else if(var1 == -1) {
         return null;
      } else {
         short var2 = var0.v();
         VariableScope var3 = new VariableScope();

         for(int var4 = 0; var4 < var2; ++var4) {
            VariableScope$VariableName var5 = VariableScope$VariableName.get(var0.l());
            boolean var6 = var0.e();
            if(!var6) {
               VariableScope$VariableData var7 = readInDynamicData(var0);
               var3.setDataRaw(var5, var7);
            }
         }

         return var3;
      }
   }

   public static void writeOutUnitOrPlaceholder(as var0, am var1) {
      if(var1 instanceof t) {
         var0.c(1);
         var0.a(var1.eo);
         var0.a(var1.ep);
         var0.a(var1.eq);
         var0.a(var1.cg);
         var0.a(var1.bX);
      } else {
         var0.c(0);
         var0.b(var1);
      }

   }

   public static void writeOutDynamicData(as var0, VariableScope$VariableData var1) {
      if(var1 == null) {
         var0.a((Enum)null);
      } else {
         LogicBoolean$ReturnType var2 = var1.getReturnType();
         var0.a((Enum)var2);
         if(var1 instanceof VariableScope$VariableDataUnit) {
            VariableScope$VariableDataUnit var3 = (VariableScope$VariableDataUnit)var1;
            am var4 = var3.unit;
            writeOutUnitOrPlaceholder(var0, var4);
         } else if(var1 instanceof VariableScope$VariableDataBoolean) {
            var0.a(((VariableScope$VariableDataBoolean)var1).bool);
         } else if(var1 instanceof VariableScope$VariableDataString) {
            var0.b(((VariableScope$VariableDataString)var1).text);
         } else if(var1 instanceof VariableScope$VariableDataNumber) {
            var0.a(((VariableScope$VariableDataNumber)var1).number);
         } else if(var1 instanceof VariableScope$VariableDataArray) {
            VariableScope$VariableDataArray var7 = (VariableScope$VariableDataArray)var1;
            var0.a(var7.size);
            int var5;
            if(var7 instanceof VariableScope$VariableDataBoolArray) {
               VariableScope$VariableDataBoolArray var8 = (VariableScope$VariableDataBoolArray)var7;

               for(var5 = 0; var5 < var8.size; ++var5) {
                  var0.a(var8.dataArray[var5]);
               }
            } else if(var7 instanceof VariableScope$VariableDataNumberArray) {
               VariableScope$VariableDataNumberArray var9 = (VariableScope$VariableDataNumberArray)var7;

               for(var5 = 0; var5 < var9.size; ++var5) {
                  var0.a(var9.dataArray[var5]);
               }
            } else {
               if(!(var7 instanceof VariableScope$VariableDataUnitArray)) {
                  throw new RuntimeException("Unhandled array type: " + var2.name());
               }

               VariableScope$VariableDataUnitArray var10 = (VariableScope$VariableDataUnitArray)var7;

               for(var5 = 0; var5 < var10.size; ++var5) {
                  am var6 = var10.dataArray[var5];
                  writeOutUnitOrPlaceholder(var0, var6);
               }
            }
         } else if(var2 != LogicBoolean$ReturnType.undefined) {
            throw new RuntimeException("Unhandled type: " + var2.name());
         }

      }
   }

   public static am readInUnitOrPlaceholder(k var0) {
      byte var1 = var0.d();
      Object var2;
      if(var1 == 1) {
         float var3 = var0.g();
         float var4 = var0.g();
         float var5 = var0.g();
         float var6 = var0.g();
         n var7 = var0.s();
         var2 = t.a(var7);
         ((am)var2).eo = var3;
         ((am)var2).ep = var4;
         ((am)var2).eq = var5;
         ((am)var2).cg = var6;
      } else {
         if(var1 != 0) {
            throw new IOException("Unhandled unit type: " + var1);
         }

         var2 = var0.o();
      }

      return (am)var2;
   }

   public static VariableScope$VariableData readInDynamicData(k var0) {
      LogicBoolean$ReturnType var1 = (LogicBoolean$ReturnType)var0.b(LogicBoolean$ReturnType.class);
      if(var1 == null) {
         return null;
      } else if(var1 == LogicBoolean$ReturnType.unit) {
         am var6 = readInUnitOrPlaceholder(var0);
         VariableScope$VariableDataUnit var9 = new VariableScope$VariableDataUnit(var6);
         return var9;
      } else if(var1 == LogicBoolean$ReturnType.bool) {
         return new VariableScope$VariableDataBoolean(var0.e());
      } else if(var1 == LogicBoolean$ReturnType.string) {
         return new VariableScope$VariableDataString(var0.j());
      } else if(var1 == LogicBoolean$ReturnType.number) {
         return new VariableScope$VariableDataNumber(var0.h());
      } else if(var1 != LogicBoolean$ReturnType.boolArray && var1 != LogicBoolean$ReturnType.numberArray && var1 != LogicBoolean$ReturnType.unitArray) {
         if(var1 == LogicBoolean$ReturnType.undefined) {
            throw new RuntimeException("Undefined type: " + var1.name());
         } else {
            throw new RuntimeException("Unhandled type: " + var1.name());
         }
      } else {
         int var2 = var0.f();
         int var4;
         if(var1 == LogicBoolean$ReturnType.boolArray) {
            VariableScope$VariableDataBoolArray var8 = new VariableScope$VariableDataBoolArray();

            for(var4 = 0; var4 < var2; ++var4) {
               var8.setBooleanIndex(var4, var0.e());
            }

            return var8;
         } else if(var1 == LogicBoolean$ReturnType.numberArray) {
            VariableScope$VariableDataNumberArray var7 = new VariableScope$VariableDataNumberArray();

            for(var4 = 0; var4 < var2; ++var4) {
               var7.setNumberIndex(var4, var0.g());
            }

            return var7;
         } else if(var1 != LogicBoolean$ReturnType.unitArray) {
            throw new RuntimeException("Unhandled array type: " + var1.name());
         } else {
            VariableScope$VariableDataUnitArray var3 = new VariableScope$VariableDataUnitArray();

            for(var4 = 0; var4 < var2; ++var4) {
               am var5 = readInUnitOrPlaceholder(var0);
               var3.setUnitIndex(var4, var5);
            }

            return var3;
         }
      }
   }

   public static LogicBoolean$ReturnType getUserType(String var0) {
      LogicBoolean$ReturnType var1 = null;
      if(var0.equals("boolean")) {
         var1 = LogicBoolean$ReturnType.bool;
      } else if(var0.equals("bool")) {
         var1 = LogicBoolean$ReturnType.bool;
      } else if(var0.equals("unit")) {
         var1 = LogicBoolean$ReturnType.unit;
      } else if(var0.equals("number")) {
         var1 = LogicBoolean$ReturnType.number;
      } else if(var0.equals("float")) {
         var1 = LogicBoolean$ReturnType.number;
      } else if(var0.equals("text")) {
         var1 = LogicBoolean$ReturnType.string;
      } else if(var0.equals("string")) {
         var1 = LogicBoolean$ReturnType.string;
      } else if(var0.equals("number[]")) {
         var1 = LogicBoolean$ReturnType.numberArray;
      } else if(var0.equals("float[]")) {
         var1 = LogicBoolean$ReturnType.numberArray;
      } else if(var0.equals("bool[]")) {
         var1 = LogicBoolean$ReturnType.boolArray;
      } else if(var0.equals("boolean[]")) {
         var1 = LogicBoolean$ReturnType.boolArray;
      } else if(var0.equals("unit[]")) {
         var1 = LogicBoolean$ReturnType.unitArray;
      }

      return var1;
   }

   public static VariableScope$MemoryWriter createGenericKeyValueWriter(String var0, com.corrodinggames.rts.game.units.custom.l var1, String var2, String var3) {
      try {
         VariableScope$MemoryWriter var4 = new VariableScope$MemoryWriter();
         Object var5 = null;
         var4.addWriterElements(var0, new VariableScope$MemoryWriterFactory(var1, (VariableScope$VariableMapping)var5));
         return var4;
      } catch (bo var6) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": " + var6.getMessage(), var6);
      }
   }

   public static VariableScope$MemoryWriter createMemoryWriter(String var0, com.corrodinggames.rts.game.units.custom.l var1, String var2, String var3) {
      try {
         VariableScope$MemoryWriter var4 = new VariableScope$MemoryWriter();
         var4.addWriterElements(var0, new VariableScope$MemoryWriterFactory(var1));
         return var4;
      } catch (bo var5) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": " + var5.getMessage(), var5);
      }
   }

   public static VariableScope$MemoryNames createMemoryNameList(String var0, com.corrodinggames.rts.game.units.custom.l var1, LogicBoolean$ReturnType var2, String var3, String var4) {
      try {
         VariableScope$MemoryWriter var5 = new VariableScope$MemoryWriter();
         VariableScope$MemoryWriterFactory var6 = new VariableScope$MemoryWriterFactory(var1);
         var6.noValues = true;
         var5.addWriterElements(var0, var6);
         VariableScope$MemoryNames var7 = new VariableScope$MemoryNames();

         VariableScope$MemoryWriterFactory$MemoryWriterElement var10;
         for(Iterator var8 = var5.writers.iterator(); var8.hasNext(); var7.names.add(var10.name)) {
            VariableScope$CachedWriter$WriterElement var9 = (VariableScope$CachedWriter$WriterElement)var8.next();
            if(!(var9 instanceof VariableScope$MemoryWriterFactory$MemoryWriterElement)) {
               throw new bo("Unexpected element reading: " + var0, var3, var4);
            }

            var10 = (VariableScope$MemoryWriterFactory$MemoryWriterElement)var9;
            if(var10 instanceof VariableScope$MemoryWriterFactory$MemoryWriterElementIndex) {
               throw new bo("Expected memory name without an index got: " + var0, var3, var4);
            }

            if(var2 != null) {
               VariableScope$VariableDefinition var11 = var1.r.get(var10.name);
               if(var11 == null) {
                  throw new bo("Failed to find defined memory: " + var0, var3, var4);
               }

               if(var11.type != var2) {
                  throw new bo("Memory: " + var0 + " is type: " + var11.type + " expected: " + var2, var3, var4);
               }
            }
         }

         return var7;
      } catch (bo var12) {
         throw new RuntimeException("[" + var3 + "]" + var4 + ": " + var12.getMessage(), var12);
      }
   }

   public static boolean isMarker(am var0) {
      return var0 == null?false:var0 instanceof t;
   }

   public static am getSafeUnitReferenceForStorage(am var0) {
      if(var0 == null) {
         return null;
      } else if(var0 instanceof t) {
         t var1 = t.a(var0.bX);
         var1.eo = var0.eo;
         var1.ep = var0.ep;
         var1.eq = var0.eq;
         var1.cg = var0.cg;
         return var1;
      } else {
         return var0;
      }
   }

}
