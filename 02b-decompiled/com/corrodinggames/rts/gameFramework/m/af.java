package com.corrodinggames.rts.gameFramework.m;

import com.corrodinggames.rts.gameFramework.m.e;

public class af {

   public String a;
   public int b = -1;
   public boolean c;
   public boolean d;
   public float[] e = new float[1];
   public e f;
   public boolean g;


   public void a(float var1) {
      if(this.e.length != 1) {
         this.e = new float[1];
      }

      if(this.e[0] != var1) {
         this.e[0] = var1;
         this.c = true;
      }
   }

   public void a(float var1, float var2) {
      if(this.e.length != 2) {
         this.e = new float[2];
      }

      if(this.e[0] != var1 || this.e[1] != var2) {
         this.e[0] = var1;
         this.e[1] = var2;
         this.c = true;
      }
   }

   public void a(float var1, float var2, float var3, float var4) {
      if(this.e.length != 4) {
         this.e = new float[4];
      }

      if(this.e[0] != var1 || this.e[1] != var2 || this.e[2] != var3 || this.e[3] != var4) {
         this.e[0] = var1;
         this.e[1] = var2;
         this.e[2] = var3;
         this.e[3] = var4;
         this.c = true;
      }
   }

   public void a(e var1) {
      if(this.f != var1) {
         this.f = var1;
         this.c = true;
      }

   }

   public void b(e var1) {
      this.g = true;
      if(this.f != var1) {
         this.f = var1;
         this.c = true;
      }

   }
}
