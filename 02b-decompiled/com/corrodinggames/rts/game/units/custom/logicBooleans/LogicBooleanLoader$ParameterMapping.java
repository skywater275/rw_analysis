package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping$FieldOrMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class LogicBooleanLoader$ParameterMapping {

   public HashMap parameters = new HashMap();
   int numberOfPositionalParameters = 0;
   public Class type;
   public String allParametersString;


   public LogicBooleanLoader$ParameterMapping(Class var1) {
      this.type = var1;
      ArrayList var2 = new ArrayList();
      LogicBooleanLoader.getAllFieldsInherited(var2, var1);
      this.allParametersString = "";
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         Field var4 = (Field)var3.next();
         if(var4.isAnnotationPresent(LogicBoolean$Parameter.class)) {
            LogicBoolean$Parameter var5 = (LogicBoolean$Parameter)var4.getAnnotation(LogicBoolean$Parameter.class);
            String var6 = var4.getName().toLowerCase(Locale.ROOT);
            this.addParameter(var6, new LogicBooleanLoader$ParameterMapping$FieldOrMethod(var4), var5);
         }
      }

      Method[] var10 = var1.getMethods();
      Method[] var11 = var10;
      int var12 = var10.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         Method var7 = var11[var13];
         if(var7.isAnnotationPresent(LogicBoolean$Parameter.class)) {
            LogicBoolean$Parameter var8 = (LogicBoolean$Parameter)var7.getAnnotation(LogicBoolean$Parameter.class);
            String var9 = var7.getName().toLowerCase(Locale.ROOT);
            this.addParameter(var9, new LogicBooleanLoader$ParameterMapping$FieldOrMethod(var7), var8);
         }
      }

   }

   public void addParameter(String var1, LogicBooleanLoader$ParameterMapping$FieldOrMethod var2, LogicBoolean$Parameter var3) {
      if(var3.type() != LogicBoolean$ReturnType.undefined) {
         var2.returnType = var3.type();
      }

      if(var3.positional() != -1) {
         var2.positionalOffset = var3.positional();
         ++this.numberOfPositionalParameters;
      }

      if(var3.required()) {
         var2.required = true;
      }

      if(var3.key() != null && !var3.key().equals("")) {
         var1 = var3.key();
      }

      this.parameters.put(var1, var2);
      if(!this.allParametersString.equals("")) {
         this.allParametersString = this.allParametersString + ", ";
      }

      this.allParametersString = this.allParametersString + var1;
   }
}
