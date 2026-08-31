package com.corrodinggames.rts.gameFramework.m;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import com.corrodinggames.rts.gameFramework.m.a;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.y;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;

public class z implements y {

   e a;
   e b;


   public y a(e var1) {
      return this.b(var1);
   }

   public y b(e var1) {
      return new z();
   }

   public boolean a() {
      return false;
   }

   public void a(Context var1) {}

   public void b() {
      this.a = new e();
      this.b = new e();
   }

   public l d() {
      return null;
   }

   public void a(l var1) {}

   public void a(a var1) {}

   public e a(int var1) {
      return this.a(var1, true);
   }

   public e a(int var1, boolean var2) {
      e var3 = new e();
      return var3;
   }

   public e a(InputStream var1, boolean var2) {
      e var3 = new e();
      return var3;
   }

   public e a(int var1, int var2, boolean var3) {
      return this.b(var1, var2, var3);
   }

   public e b(int var1, int var2, boolean var3) {
      e var4 = new e();
      return var4;
   }

   public void e() {}

   public void a(e var1, float var2, float var3, float var4, Paint var5) {}

   public void a(e var1, Rect var2, float var3, float var4, float var5, Paint var6) {}

   public void a(e var1, Rect var2, Rect var3, Paint var4) {}

   public void b(e var1, Rect var2, Rect var3, Paint var4) {
      this.a(var1, var2, var3, var4);
   }

   public void a(Rect var1, Paint var2) {
      this.b(var1, var2);
   }

   public void a(boolean var1) {}

   public void f() {}

   public void a(e var1, Rect var2, RectF var3, Paint var4) {}

   public void a(e var1, float var2, float var3, Paint var4) {}

   public void a(e var1, float var2, float var3, Paint var4, float var5, float var6) {}

   public void b(e var1, float var2, float var3, Paint var4) {}

   public void a(e var1, Rect var2, Paint var3) {}

   public void a(e var1, Rect var2, Paint var3, int var4, int var5, int var6, int var7) {}

   public void a(e var1, RectF var2, Paint var3, float var4, float var5, int var6, int var7) {}

   public void b(int var1) {}

   public void a(int var1, Mode var2) {}

   public void a(String var1, float var2, float var3, Paint var4, Paint var5, float var6) {}

   public void a(String var1, float var2, float var3, Paint var4) {}

   public void b(Rect var1, Paint var2) {}

   public void a(RectF var1, Paint var2) {}

   public void g() {}

   public void h() {}

   public void c(Rect var1, Paint var2) {}

   public void a(Rect var1) {}

   public void a(RectF var1) {}

   public void a(float var1, float var2, float var3, Paint var4) {}

   public void b(float var1, float var2, float var3, Paint var4) {
      this.a(var1, var2, var3, var4);
   }

   public void a(float[] var1, int var2, int var3, Paint var4) {}

   public void i() {}

   public void j() {}

   public void k() {
      this.i();
   }

   public void l() {
      this.j();
   }

   public void a(float var1, float var2, float var3) {}

   public void a(float var1, float var2) {}

   public void a(float var1, float var2, float var3, float var4) {}

   public void b(float var1, float var2) {}

   public void a(m var1) {
      var1.a(this);
   }

   public void a(float var1, float var2, float var3, float var4, Paint var5) {}

   public int m() {
      return 0;
   }

   public int n() {
      return 0;
   }

   public void a(int var1, int var2) {}

   public void o() {}

   public void a(Paint var1) {}

   public void a(ae var1) {}

   public void p() {}

   public void q() {}

   public int a(String var1, Paint var2) {
      return 1;
   }

   public int b(String var1, Paint var2) {
      return 1;
   }

   public e r() {
      return this.b;
   }

   public void a(e var1, File var2) {
      throw new RuntimeException("writeImageToFile not yet supported");
   }

   public void a(Lock var1) {}

   public void b(Lock var1) {}

   public float s() {
      return 1.0F;
   }
}
