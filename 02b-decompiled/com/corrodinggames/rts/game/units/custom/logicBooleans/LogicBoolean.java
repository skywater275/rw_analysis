package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.k;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$CallContext_self;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$CallContext_selfAndTarget;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBooleanFalse;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBooleanTrue;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping$FieldOrMethod;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$CreateMarker;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionCos;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionDirection;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionDirectionBetween;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionDistance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionDistanceBetween;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionDistanceBetweenSquared;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionDistanceSquared;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionInt;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionLength;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionMax;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionMin;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionRnd;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionSin;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$FunctionSquareRoot;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$Debug;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$DebugPassthrough;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$LowerString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$Playername;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$Select;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$Substring;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$TeamName;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$UpperString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$NullUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$KnownMemoryScopeLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$ReadEventMemoryLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$ReadUnitMemoryLogicBoolean;
import com.corrodinggames.rts.gameFramework.f;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public abstract class LogicBoolean implements Cloneable {

   public static final boolean not = false;
   public static final LogicBoolean$StaticBoolean trueBoolean = new LogicBoolean$StaticBooleanTrue();
   public static final LogicBoolean$StaticBoolean falseBoolean = new LogicBoolean$StaticBooleanFalse();
   static LogicBoolean$CallContext_self callContext_self = new LogicBoolean$CallContext_self();
   static LogicBoolean$CallContext_selfAndTarget callContext_selfAndTarget = new LogicBoolean$CallContext_selfAndTarget();
   static HashMap booleans = new HashMap();
   static k activeEvent;
   static y outerUnitParameterContext;
   static final HashMap parameterMappings = new HashMap();


   public static final y getParameterContext(y var0) {
      y var1 = outerUnitParameterContext;
      return var1 != null?var1:var0;
   }

   public static final void setOuterUnitParameterContext(y var0) {
      outerUnitParameterContext = var0;
   }

   public static final void clearOuterUnitParameterContext() {
      outerUnitParameterContext = null;
   }

   public static void enableContextEventSource() {}

   public static void setContextEventSource(k var0) {
      activeEvent = var0;
   }

   public static void clearContext() {
      activeEvent = null;
   }

   static void addBooleanType(LogicBoolean var0, String ... var1) {
      String[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         var5 = var5.toLowerCase(Locale.ROOT);
         if(booleans.get(var5) != null) {
            throw new RuntimeException("logicBoolean: " + var5 + " already exists");
         }

         booleans.put(var5, var0);
      }

   }

   public void setArgumentsRaw(String var1, l var2, String var3) {
      LogicBooleanLoader$ParameterMapping var4 = this.getParameters();
      LogicBooleanLoader.setArgumentsWithMapping(var4, this, var1, var2, var3);
   }

   public LogicBooleanLoader$ParameterMapping getParameters() {
      LogicBooleanLoader$ParameterMapping var1 = (LogicBooleanLoader$ParameterMapping)parameterMappings.get(this.getClass());
      if(var1 == null) {
         var1 = new LogicBooleanLoader$ParameterMapping(this.getClass());
         parameterMappings.put(var1.type, var1);
      }

      return var1;
   }

   public boolean isLocked() {
      return false;
   }

   public LogicBoolean createLocked() {
      throw new RuntimeException("Not implemented");
   }

   public void forMeta(l var1) {}

   public LogicBoolean with(String var1) {
      return this.with((l)null, var1, (String)null);
   }

   public LogicBoolean with(l var1, String var2, String var3) {
      LogicBoolean var4;
      try {
         var4 = (LogicBoolean)this.clone();
      } catch (CloneNotSupportedException var6) {
         throw new RuntimeException(var6);
      }

      var4.forMeta(var1);
      if(this.isLocked()) {
         if(var2 != null && !var2.trim().equals("")) {
            throw new BooleanParseException("No parameters accepted for " + this.getClass().getSimpleName());
         }
      } else {
         var4.setArgumentsRaw(var2, var1, var3);
      }

      return var4;
   }

   public static LogicBoolean convertAlwaysTrueToNull(LogicBoolean var0) {
      return var0 == trueBoolean?null:var0;
   }

   public static boolean isStaticFalse(LogicBoolean var0) {
      return var0 == falseBoolean;
   }

   public static boolean isStaticTrue(LogicBoolean var0) {
      return var0 == trueBoolean;
   }

   public static boolean isStaticNull(LogicBoolean var0) {
      return var0 instanceof UnitReference$NullUnitReference;
   }

   public static boolean isStaticNumber(LogicBoolean var0) {
      return var0 instanceof LogicBoolean$StaticValueBoolean;
   }

   public static Float getStaticNumber(LogicBoolean var0) {
      return var0 instanceof LogicBoolean$StaticValueBoolean?Float.valueOf(((LogicBoolean$StaticValueBoolean)var0).staticNumber):null;
   }

   public static float getKnownStaticNumber(LogicBoolean var0) {
      return ((LogicBoolean$StaticValueBoolean)var0).staticNumber;
   }

   public static LogicBoolean create(l var0, String var1) {
      return create(var0, var1, (LogicBoolean)null);
   }

   public static LogicBoolean create(l var0, String var1, LogicBoolean var2) {
      String var3 = var1;

      try {
         if(var3 == null) {
            return var2;
         } else {
            String var4 = var3.toLowerCase(Locale.ENGLISH);
            if(var4.equals("true")) {
               return trueBoolean;
            } else if(var4.equals("false")) {
               return falseBoolean;
            } else if(var4.startsWith("if ")) {
               var3 = var3.substring("if ".length());
               return LogicBooleanLoader.parseBooleanBlock(var0, var3, true);
            } else {
               throw new BooleanParseException("Cannot parse:\'" + var1 + "\' expected true, false or statement starting with \'if\'");
            }
         }
      } catch (RuntimeException var5) {
         throw new RuntimeException("LogicBoolean - Error: " + var5.getMessage() + ", [parsing: \'" + var1 + "\']", var5);
      }
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.bool;
   }

   public String valueToStringDebug(y var1) {
      LogicBoolean$ReturnType var2 = this.getReturnType();
      if(var2 == LogicBoolean$ReturnType.number) {
         float var5 = this.readNumber(var1);
         return f.a(var5, 2);
      } else if(var2 == LogicBoolean$ReturnType.unit) {
         return am.A(this.readUnit(var1));
      } else if(var2 == LogicBoolean$ReturnType.string) {
         String var4 = this.readString(var1);
         return var4;
      } else if(LogicBoolean$ReturnType.isArrayType(var2)) {
         return LogicString.arraySummaryToString(var1, this);
      } else {
         boolean var3 = this.read(var1);
         return var3?"true":"false";
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {}

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      this.validate(var1, var2, var3, var4, var5);
      return this;
   }

   public abstract boolean read(y var1);

   public float readNumber(y var1) {
      return -9999.0F;
   }

   public String readString(y var1) {
      return null;
   }

   public am readUnit(y var1) {
      return null;
   }

   public int getArraySize(y var1) {
      return 0;
   }

   public LogicBoolean readArrayElement(y var1, int var2) {
      return null;
   }

   public abstract String getMatchFailReasonForPlayer(y var1);

   public String getDebugDetails(j var1) {
      boolean var2 = this.read(var1);
      return this.getMatchFailReasonForPlayer(var1) + "==" + (var2?"true":"false");
   }

   public static String getAllParametersDebug(LogicBoolean var0, y var1) {
      String var2 = "";
      LogicBooleanLoader$ParameterMapping var3 = var0.getParameters();
      Iterator var4 = var3.parameters.keySet().iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         LogicBooleanLoader$ParameterMapping$FieldOrMethod var6 = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)var3.parameters.get(var5);
         if(var6.field != null) {
            if(!var2.equals("")) {
               var2 = var2 + ",";
            }

            String var7 = null;
            Object var8 = LogicBooleanLoader.getArgumentTextWithMapping(var6, var0);
            if(var8 instanceof String) {
               var7 = var8.toString();
            }

            if(var8 instanceof LogicBoolean) {
               var7 = ((LogicBoolean)var8).valueToStringDebug(var1);
            }

            var2 = var2 + var5 + "=" + var7;
         }
      }

      return var2;
   }

   public LogicBooleanLoader$LogicBooleanContext createContext() {
      return LogicBooleanLoader.voidContextReader;
   }

   public void throwVoidReturnError(String var1) {
      throw new RuntimeException("Function or object:\'" + var1 + "\' cannot be returned");
   }

   public LogicBoolean setChild(LogicBoolean var1) {
      throw new RuntimeException("setChild not supported on: " + this.getClass().getSimpleName());
   }

   public void setParent(LogicBoolean var1) {}

   static {
      LogicBooleanGameFunctions.loadTypes();
      addBooleanType(new VariableScope$KnownMemoryScopeLogicBoolean(), new String[]{"memory"});
      addBooleanType(new VariableScope$ReadUnitMemoryLogicBoolean(), new String[]{"self.readUnitMemory"});
      addBooleanType(new VariableScope$ReadEventMemoryLogicBoolean(), new String[]{"eventData"});
      addBooleanType(new LogicString$Playername(), new String[]{"self.playername"});
      addBooleanType(new LogicString$TeamName(), new String[]{"self.teamname"});
      addBooleanType(new LogicString$Debug(), new String[]{"debug"});
      addBooleanType(new LogicString$DebugPassthrough(), new String[]{"debugPassthrough"});
      addBooleanType(new LogicString$Select(), new String[]{"select"});
      addBooleanType(new LogicString$StringCast(), new String[]{"str"});
      addBooleanType(new LogicString$Substring(), new String[]{"substring"});
      addBooleanType(new LogicString$LowerString(), new String[]{"lowercase"});
      addBooleanType(new LogicString$UpperString(), new String[]{"uppercase"});
      addBooleanType(new LogicNumberFunction$FunctionInt(), new String[]{"int"});
      addBooleanType(new LogicNumberFunction$FunctionRnd(), new String[]{"rnd"});
      addBooleanType(new LogicNumberFunction$FunctionMax(), new String[]{"max"});
      addBooleanType(new LogicNumberFunction$FunctionMin(), new String[]{"min"});
      addBooleanType(new LogicNumberFunction$FunctionLength(), new String[]{"length"});
      addBooleanType(new LogicNumberFunction$FunctionSquareRoot(), new String[]{"squareRoot"});
      addBooleanType(new LogicNumberFunction$FunctionDistanceSquared(), new String[]{"distanceSquared"});
      addBooleanType(new LogicNumberFunction$FunctionCos(), new String[]{"cos"});
      addBooleanType(new LogicNumberFunction$FunctionSin(), new String[]{"sin"});
      addBooleanType(new LogicNumberFunction$FunctionDistance(), new String[]{"distance"});
      addBooleanType(new LogicNumberFunction$FunctionDirection(), new String[]{"direction"});
      addBooleanType(new LogicNumberFunction$FunctionDistanceBetweenSquared(), new String[]{"distanceBetweenSquared"});
      addBooleanType(new LogicNumberFunction$FunctionDistanceBetween(), new String[]{"distanceBetween"});
      addBooleanType(new LogicNumberFunction$FunctionDirectionBetween(), new String[]{"directionBetween"});
      addBooleanType(new LogicNumberFunction$CreateMarker(), new String[]{"createMarker"});
      addBooleanType(trueBoolean, new String[]{"true"});
      addBooleanType(falseBoolean, new String[]{"false"});
   }
}
