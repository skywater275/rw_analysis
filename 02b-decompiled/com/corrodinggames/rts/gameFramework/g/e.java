package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.g.d;
import com.corrodinggames.rts.gameFramework.g.f;

public class e extends d {

   private final n a;


   public e(n var1) {
      this.a = var1;
   }

   public boolean a() {
      return false;
   }

   public String b() {
      return this.a.v == null?"":this.a.v;
   }

   public int c() {
      return this.a.K();
   }

   public int d() {
      return -1;
   }

   public int a(f var1) {
      return var1.a(this.a);
   }
}
