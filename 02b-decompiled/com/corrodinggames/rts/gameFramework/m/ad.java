package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;

public class ad extends e {

   e x;


   public ad(e var1) {
      this.x = var1;
      this.k = var1.k;
   }

   public String a() {
      return this.x.a();
   }

   public Bitmap b() {
      return this.x.b();
   }

   public e c() {
      return this.x.c();
   }

   public void a(boolean var1) {}

   public void a(Bitmap var1) {}

   public void g() {
      this.x.g();
   }

   public void a(e var1) {
      this.x.a(var1);
   }

   public e h() {
      return this;
   }

   public e a(int var1, int var2, boolean var3) {
      return this;
   }

   public void i() {}

   public void j() {}

   public int a(int var1, int var2) {
      return this.x.a(var1, var2);
   }

   public void a(int var1, int var2, int var3) {}

   public int l() {
      return this.x.l();
   }

   public int m() {
      return this.x.m();
   }

   public void n() {}

   public void o() {}

   public void p() {}

   public void r() {}

   public void t() {}

   public int u() {
      return this.x.u();
   }

   public void v() {}

   public void w() {}

   public String toString() {
      return "MutableBitmapOrTexture(" + this.x.toString() + ")";
   }

   public ae B() {
      return this.x.i;
   }

   public void a(ae var1) {}

   // $FF: synthetic method
   public Object clone() {
      return this.h();
   }
}
