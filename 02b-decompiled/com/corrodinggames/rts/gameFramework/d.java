package com.corrodinggames.rts.gameFramework;


public class d {

   public com.corrodinggames.rts.gameFramework.k.k a;
   public long b;
   public float c;
   public float d;
   public float e;
   public float f;
   public int g;
   public com.corrodinggames.rts.game.units.ao h;


   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a(this.b);
      var1.a(this.c);
      var1.a(this.d);
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a((Enum)this.h);
      var1.a(this.a != null);
      if(this.a != null) {
         this.a.a(var1);
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.b = var1.i();
      this.c = var1.g();
      this.d = var1.g();
      this.e = var1.g();
      this.f = var1.g();
      this.g = var1.f();
      this.h = (com.corrodinggames.rts.game.units.ao)var1.b(com.corrodinggames.rts.game.units.ao.class);
      boolean var2 = var1.e();
      if(var2) {
         boolean var3 = false;
         this.a = new com.corrodinggames.rts.gameFramework.k.k((com.corrodinggames.rts.gameFramework.k.l)null, var3);
         this.a.a(var1);
      }

   }
}
