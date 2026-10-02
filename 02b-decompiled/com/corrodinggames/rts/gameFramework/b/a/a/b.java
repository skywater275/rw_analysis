package com.corrodinggames.rts.gameFramework.b.a.a;

import com.corrodinggames.rts.gameFramework.b.a.f;

public abstract class b {

   private int a;
   private int b;
   private int c;
   private boolean d = false;


   public void a() {
      this.a((String)null, (String)null, (com.corrodinggames.rts.gameFramework.b.a.a[])null);
   }

   public void a(String var1, String var2, com.corrodinggames.rts.gameFramework.b.a.a[] var3) {
      this.b = f.a('\u8b31', var1);
      this.c = f.a('\u8b30', var2);
      this.a = f.a(this.b, this.c, var3);
      this.d = true;
   }

   public int b() {
      return this.a;
   }
}
