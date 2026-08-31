package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.as;

public class j extends bq {

   public int a;
   public float b;
   public com.corrodinggames.rts.game.units.custom.d.b c;
   public com.corrodinggames.rts.game.units.custom.d.b d;
   public com.corrodinggames.rts.game.units.custom.h e;
   public boolean f;
   public com.corrodinggames.rts.game.units.as g;
   public PointF h;
   public am i;
   public com.corrodinggames.rts.game.units.a.c j;
   public boolean k;
   public boolean l;
   public float m;
   public double n;


   public strictfp j() {
      this.c = com.corrodinggames.rts.game.units.custom.d.b.a;
      this.d = null;
      this.j = com.corrodinggames.rts.game.units.a.s.i;
      this.m = -1.0F;
      this.n = 0.0D;
   }

   public strictfp void a(as var1) {
      var1.a((int)-1);
      var1.a(this.a);
      var1.a(this.b);
      var1.a((int)-1);
      var1.a(this.c.a());
      var1.a(this.f);
      var1.c(this.j.a());
      var1.c(this.j.a());
      var1.b(this.i);
      var1.a(this.h);
      var1.a(this.l);
      var1.a(this.m);
      var1.a(this.g);
      this.c.a(var1);
      com.corrodinggames.rts.game.units.custom.d.b.a(var1, this.d);
      com.corrodinggames.rts.game.units.custom.g.a(this.e, var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      String.valueOf(var1.f());
      this.a = var1.f();
      this.b = var1.g();
      int var2 = 0;
      if(var1.b() >= 4) {
         this.j = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(var1.f()));
      }

      if(var1.b() >= 6) {
         var2 = var1.f();
      }

      if(var1.b() >= 25) {
         this.f = var1.e();
      }

      if(var1.b() >= 33) {
         var1.l();
         this.j = com.corrodinggames.rts.game.units.a.c.a(var1.l());
      }

      if(var1.b() >= 61) {
         this.i = var1.o();
         this.h = var1.y();
      }

      if(var1.b() >= 64) {
         this.l = var1.e();
         this.m = var1.g();
      }

      if(var1.b() >= 71) {
         this.g = var1.q();
      }

      if(var1.b() >= 73) {
         this.c = com.corrodinggames.rts.game.units.custom.d.b.b(var1);
      } else {
         this.c = com.corrodinggames.rts.game.units.custom.d.b.a(var2);
      }

      if(var1.b() >= 91) {
         this.d = com.corrodinggames.rts.game.units.custom.d.b.a(var1);
      }

      if(var1.b() >= 95) {
         this.e = com.corrodinggames.rts.game.units.custom.g.a(var1);
      }

   }
}
