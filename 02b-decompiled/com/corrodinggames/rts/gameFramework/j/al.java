package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ak;

public class al {

   public String a;
   public long b;
   boolean c;
   // $FF: synthetic field
   final ak d;


   public strictfp al(ak var1, String var2) {
      this(var1, var2, true);
   }

   public strictfp al(ak var1, String var2, boolean var3) {
      this.d = var1;
      this.a = var2;
      this.c = var3;
      var1.b.add(this);
   }
}
