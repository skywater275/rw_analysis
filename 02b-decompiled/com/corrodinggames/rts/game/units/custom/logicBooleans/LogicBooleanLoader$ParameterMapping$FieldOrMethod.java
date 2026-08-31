package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class LogicBooleanLoader$ParameterMapping$FieldOrMethod {

   Field field;
   Method method;
   Class type;
   LogicBoolean$ReturnType returnType;
   int positionalOffset = -1;
   boolean required;


   public LogicBooleanLoader$ParameterMapping$FieldOrMethod(Field var1) {
      this.field = var1;
      this.type = var1.getType();
   }

   public LogicBooleanLoader$ParameterMapping$FieldOrMethod(Method var1) {
      this.method = var1;
      this.type = var1.getParameterTypes()[0];
   }
}
