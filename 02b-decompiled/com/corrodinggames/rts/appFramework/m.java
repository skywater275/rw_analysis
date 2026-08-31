package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.l;

public class m {

   private int a;
   private float[] b = new float[10];
   private float[] c = new float[10];
   private float[] d = new float[10];
   private int[] e = new int[10];
   private float f;
   private float g;
   private float h;
   private float i;
   private float j;
   private boolean k;
   private boolean l;
   private boolean m;
   private int n;
   private boolean o;
   private boolean p;
   private boolean q;
   private int r;


   public m() {
      int var1;
      for(var1 = 0; var1 < this.b.length; ++var1) {
         this.b[var1] = 40.0F;
      }

      for(var1 = 0; var1 < this.c.length; ++var1) {
         this.c[var1] = 40.0F;
      }

   }

   public int a() {
      return this.n;
   }

   public boolean b() {
      return this.m;
   }

   public void c() {
      this.m = this.k;
      this.n = this.a;
   }

   public void a(float var1, float var2) {
      this.b[0] = var1;
      this.c[0] = var2;
      this.f = this.b[0];
      this.g = this.c[0];
      this.i = this.j = 0.0F;
   }

   public void a(float var1, float var2, boolean var3, int var4) {
      this.r = 0;
      this.a = var3?1:0;
      if(var4 != -1) {
         l.a()[0] = var4;
      }

      this.b[0] = var1;
      this.c[0] = var2;
      this.d[0] = 0.0F;
      this.e[0] = 0;
      this.k = var3;
      this.l = false;
      if(this.k) {
         this.m = this.k;
      }

      if(this.a > 0) {
         this.n = this.a;
      }

      this.f = this.b[0];
      this.g = this.c[0];
      this.h = this.d[0];
      this.i = this.j = 0.0F;
      this.o = this.p = this.q = false;
   }

   public float[] d() {
      return this.b;
   }

   public int[] e() {
      return l.a();
   }

   public float[] f() {
      return this.c;
   }
}
