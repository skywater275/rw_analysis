package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;

public final class ap {

   public float a;
   public float b;
   public float c;
   public float d;
   public float e;
   public float f;
   public boolean g;
   public float h;
   public float i;
   public am j;
   public float k;
   public float l;
   public boolean m;


   public strictfp void a(float var1) {
      this.a = var1;
      this.b = this.a;
      this.c = 0.0F;
      this.d = 0.0F;
      this.e = 0.0F;
      this.f = 0.0F;
      this.g = false;
      this.h = 0.0F;
      this.i = 0.0F;
      this.j = null;
      this.k = 0.0F;
      this.l = 0.0F;
      this.m = false;
   }

   public final strictfp void a(int var1) {
      if(this.d < (float)var1 && this.d >= 0.0F) {
         this.d = (float)var1;
      }

   }

   public final strictfp void b(int var1) {
      if(this.d > (float)(-var1)) {
         this.d = (float)(-var1);
      }

   }

   public final strictfp boolean a() {
      return this.d == 0.0F;
   }

   public final strictfp boolean b() {
      return this.d < 0.0F;
   }
}
