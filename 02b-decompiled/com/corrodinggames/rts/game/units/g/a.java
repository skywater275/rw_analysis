package com.corrodinggames.rts.game.units.g;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.g.b;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public abstract class a {

   int a;


   public a() {}

   public a(int var1) {
      this.a = var1;
   }

   public int a() {
      return this.a;
   }

   public abstract b b();

   public void a(y var1, float var2) {}

   public void a(y var1, as var2) {
      var2.a(this.a);
   }

   public void a(y var1, k var2) {
      this.a = var2.f();
   }
}
