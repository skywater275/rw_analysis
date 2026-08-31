package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public class ah implements Cloneable {

   public ai a;
   public String b;
   public int c;
   public int d;
   public boolean e;
   public int f;
   public int g;
   public float h;
   public boolean i;
   public boolean j;
   public boolean k;
   public boolean l;
   public boolean m;
   public boolean n;
   public boolean o;
   public boolean p;
   public int q;


   public strictfp ah() {
      this.a = ai.a;
      this.b = "[z;p10]Crossing Large (10p).tmx";
      this.c = 0;
      this.d = 2;
      this.e = true;
      this.f = 1;
      this.g = 1;
      this.h = 1.0F;
      this.i = false;
      this.j = false;
      this.k = false;
      this.m = false;
      this.n = false;
      this.o = true;
      this.p = false;
   }

   public strictfp void a() {
      this.a = ai.a;
      this.b = "[z;p10]Crossing Large (10p).tmx";
   }

   public strictfp String b() {
      String var1 = "";
      String var2 = "\n";
      var1 = var1 + "startingCredits: " + this.c + var2;
      var1 = var1 + "fogMode: " + this.d + var2;
      var1 = var1 + "revealedMap: " + this.e + var2;
      var1 = var1 + "aiDifficulty: " + this.f + var2;
      var1 = var1 + "startingUnits: " + this.g + var2;
      var1 = var1 + "incomeMultiplier: " + this.h + var2;
      var1 = var1 + "noNukes: " + this.i + var2;
      var1 = var1 + "sharedControl: " + this.l + var2;
      var1 = var1 + "allowSpectators: " + this.o + var2;
      var1 = var1 + "lockedRoom: " + this.p + var2;
      var1 = var1 + "randomSeed: " + this.q + var2;
      return var1;
   }

   public strictfp ah c() {
      try {
         return (ah)super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new RuntimeException(var2);
      }
   }

   public strictfp void a(as var1) {
      var1.c(4);
      var1.a(this.d);
      var1.a(this.c);
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.h);
      var1.a(this.i);
      var1.a(this.j);
      var1.a(this.l);
      var1.a(this.m);
      var1.a(this.n);
      var1.a(this.o);
      var1.a(this.p);
      var1.a(this.q);
   }

   public strictfp void a(k var1) {
      byte var2 = var1.d();
      this.d = var1.f();
      this.c = var1.f();
      this.e = var1.e();
      this.f = var1.f();
      this.g = var1.f();
      this.h = var1.g();
      this.i = var1.e();
      this.j = var1.e();
      this.l = var1.e();
      if(var2 >= 1) {
         this.m = var1.e();
      }

      if(var2 >= 2) {
         this.n = var1.e();
      }

      if(var2 >= 3) {
         this.o = var1.e();
         this.p = var1.e();
      }

      if(var2 >= 4) {
         this.q = var1.f();
      }

   }

   // $FF: synthetic method
   public Object clone() {
      return this.c();
   }
}
