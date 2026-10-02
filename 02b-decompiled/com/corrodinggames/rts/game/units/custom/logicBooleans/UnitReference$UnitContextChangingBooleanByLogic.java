package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import java.util.ArrayList;

public class UnitReference$UnitContextChangingBooleanByLogic extends LogicBoolean {

   LogicBoolean targetBoolean;
   LogicBoolean dynamicContext;
   LogicBoolean[] dynamicContextChain;


   public static UnitReference$UnitContextChangingBooleanByLogic create(LogicBoolean var0, LogicBoolean var1) {
      ArrayList var2 = null;
      UnitReference$UnitContextChangingBooleanByLogic var3;
      if(var1 instanceof UnitReference$UnitContextChangingBooleanByLogic) {
         var3 = (UnitReference$UnitContextChangingBooleanByLogic)var1;
         var2 = new ArrayList();
         var2.add(var0);
         if(var3.dynamicContextChain != null) {
            LogicBoolean[] var4 = var3.dynamicContextChain;
            LogicBoolean[] var5 = var4;
            int var6 = var4.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               LogicBoolean var8 = var5[var7];
               var2.add(var8);
            }
         } else {
            var2.add(var3.dynamicContext);
         }

         var1 = var3.targetBoolean;
      }

      var3 = new UnitReference$UnitContextChangingBooleanByLogic();
      if(var2 != null) {
         var3.dynamicContextChain = (LogicBoolean[])var2.toArray(new LogicBoolean[0]);
      } else {
         var3.dynamicContext = var0;
      }

      var3.targetBoolean = var1;
      if(var0 == null) {
         throw new RuntimeException("dynamicContext==null");
      } else if(var0.getReturnType() != LogicBoolean$ReturnType.unit) {
         throw new RuntimeException("dynamicContext type!=unit. Got:" + var0.getReturnType());
      } else {
         return var3;
      }
   }

   public LogicBoolean setChild(LogicBoolean var1) {
      return super.setChild(var1);
   }

   public y getUnitContext(y var1) {
      if(this.dynamicContextChain != null) {
         y var8 = var1;
         LogicBoolean[] var3 = this.dynamicContextChain;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            LogicBoolean var6 = var3[var5];
            am var7 = var6.readUnit(var8);
            if(!(var7 instanceof y)) {
               return null;
            }

            var8 = (y)var7;
         }

         return var8;
      } else {
         am var2 = this.dynamicContext.readUnit(var1);
         return !(var2 instanceof y)?null:(y)var2;
      }
   }

   public boolean read(y var1) {
      LogicBoolean.setOuterUnitParameterContext(var1);

      boolean var3;
      try {
         y var2 = this.getUnitContext(var1);
         if(var2 == null) {
            var3 = false;
            return var3;
         }

         var3 = this.targetBoolean.read(var2);
      } finally {
         LogicBoolean.clearOuterUnitParameterContext();
      }

      return var3;
   }

   public float readNumber(y var1) {
      LogicBoolean.setOuterUnitParameterContext(var1);

      float var3;
      try {
         y var2 = this.getUnitContext(var1);
         if(var2 != null) {
            var3 = this.targetBoolean.readNumber(var2);
            return var3;
         }

         var3 = 0.0F;
      } finally {
         LogicBoolean.clearOuterUnitParameterContext();
      }

      return var3;
   }

   public String readString(y var1) {
      LogicBoolean.setOuterUnitParameterContext(var1);

      String var3;
      try {
         y var2 = this.getUnitContext(var1);
         if(var2 == null) {
            var3 = "<unit not found>";
            return var3;
         }

         var3 = this.targetBoolean.readString(var2);
      } finally {
         LogicBoolean.clearOuterUnitParameterContext();
      }

      return var3;
   }

   public am readUnit(y var1) {
      LogicBoolean.setOuterUnitParameterContext(var1);

      am var3;
      try {
         y var2 = this.getUnitContext(var1);
         if(var2 == null) {
            var3 = null;
            return var3;
         }

         var3 = this.targetBoolean.readUnit(var2);
      } finally {
         LogicBoolean.clearOuterUnitParameterContext();
      }

      return var3;
   }

   public void forMeta(l var1) {}

   public UnitReference$UnitContextChangingBooleanByLogic with(l var1, String var2, String var3) {
      return this;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return this.targetBoolean.getReturnType();
   }

   public String getMatchFailReasonForPlayer(y var1) {
      if(this.dynamicContextChain != null) {
         String var18 = "";
         y var19 = var1;
         LogicBoolean.setOuterUnitParameterContext(var1);

         try {
            LogicBoolean[] var20 = this.dynamicContextChain;
            int var5 = var20.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               LogicBoolean var7 = var20[var6];
               var18 = var18 + var7.getMatchFailReasonForPlayer(var19);
               am var8 = var7.readUnit(var19);
               if(!(var8 instanceof y)) {
                  var18 = var18 + "<unit not found>";
                  String var9 = var18;
                  return var9;
               }

               var18 = var18 + ".";
               var19 = (y)var8;
            }

            var18 = var18 + this.targetBoolean.getMatchFailReasonForPlayer(var19);
            return var18;
         } finally {
            LogicBoolean.clearOuterUnitParameterContext();
         }
      } else {
         LogicBoolean.setOuterUnitParameterContext(var1);

         String var4;
         try {
            am var2 = this.dynamicContext.readUnit(var1);
            String var3;
            if(!(var2 instanceof y)) {
               var3 = "=<unit not found> (type:" + this.dynamicContext.getReturnType() + ")";
            } else {
               var3 = "." + this.targetBoolean.getMatchFailReasonForPlayer((y)var2);
            }

            var4 = this.dynamicContext.getMatchFailReasonForPlayer(var1) + var3;
         } finally {
            LogicBoolean.clearOuterUnitParameterContext();
         }

         return var4;
      }
   }
}
