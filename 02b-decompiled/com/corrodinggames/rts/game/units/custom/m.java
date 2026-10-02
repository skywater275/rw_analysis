package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;

public class m {

   public boolean a;
   public float b;
   public int c;
   public int d;
   public float e;


   public static strictfp m a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, boolean var4) {
      m var5 = new m();
      var5.a = var1.a(var2, var3 + "direction_useMainTurret", Boolean.valueOf(false)).booleanValue();
      var5.b = var1.a(var2, var3 + "direction_units", Float.valueOf(0.0F)).floatValue();
      var5.c = var1.b(var2, var3 + "direction_strideX", Integer.valueOf(-1)).intValue();
      var5.d = var1.b(var2, var3 + "direction_strideY", Integer.valueOf(-1)).intValue();
      var5.e = var1.a(var2, var3 + "direction_starting", Float.valueOf(0.0F)).floatValue();
      return var5.b == 0.0F?null:var5;
   }
}
