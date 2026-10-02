package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.az;

public abstract class ay extends az {

   public int es;
   public int et;
   public float eu;
   public float ev;
   public boolean ew;


   public strictfp void b(com.corrodinggames.rts.gameFramework.m.e var1) {
      this.T(var1.p);
      this.U(var1.q);
      this.ew = true;
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.m.e var1, int var2) {
      this.T(var1.p / var2);
      this.U(var1.q);
      this.ew = false;
   }

   public strictfp void T(int var1) {
      this.es = var1;
      this.eu = (float)(var1 / 2);
   }

   public strictfp void U(int var1) {
      this.et = var1;
      this.ev = (float)(var1 / 2);
   }

   public strictfp void V(int var1) {
      this.es = var1;
      this.eu = (float)var1 / 2.0F;
   }

   public strictfp void W(int var1) {
      this.et = var1;
      this.ev = (float)var1 / 2.0F;
   }

   protected strictfp ay(boolean var1) {
      super(var1);
   }

   public strictfp void a() {
      super.a();
   }
}
