package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.w;

public abstract class az extends w {

   public int ex = 0;


   protected strictfp az(boolean var1) {
      super(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.d("xy is:");
      var1.a(this.eo);
      var1.a(this.ep);
      var1.a(this.eq);
      var1.a(this.ex);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.eo = var1.g();
      this.ep = var1.g();
      this.eq = var1.g();
      this.ex = var1.f();
      super.a(var1);
   }
}
