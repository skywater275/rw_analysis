package com.corrodinggames.rts.game.units.custom.f;

import com.corrodinggames.rts.game.units.custom.bo;

public class e {

   public static void a(String var0) {
      if(var0.length() == 0) {
         throw new bo("name cannot be empty");
      } else if(!var0.contains(" ") && !var0.contains("}") && !var0.contains("$") && !var0.contains(".") && !var0.contains("{") && !var0.contains("-") && !var0.contains("+") && !var0.contains(":") && !var0.contains("(")) {
         if(Character.isDigit(var0.charAt(0))) {
            throw new bo("name cannot start with a digit");
         }
      } else {
         throw new bo("invalid character in name");
      }
   }
}
