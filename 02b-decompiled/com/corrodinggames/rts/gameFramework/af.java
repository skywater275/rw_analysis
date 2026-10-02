package com.corrodinggames.rts.gameFramework;


public abstract class af {

   public int a = -1;
   public int b = 0;
   protected boolean c;
   public boolean d;


   public abstract boolean a();

   public boolean a(af var1) {
      return this.b != var1.b?false:this.a == var1.a;
   }

   public abstract boolean b();

   public abstract String c();

   public abstract boolean d();
}
