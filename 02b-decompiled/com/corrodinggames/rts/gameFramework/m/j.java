package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.m;
import java.util.concurrent.locks.Lock;

public class j implements l {

   public Canvas a;
   boolean b = false;


   public j(Canvas var1) {
      this.a = var1;
   }

   public void a(Rect var1) {
      this.a.a(var1);
   }

   public void a(RectF var1) {
      this.a.a(var1);
   }

   public void a(e var1, float var2, float var3, Paint var4) {
      this.a.a(aa.a(var1), var2, var3, var4);
   }

   public void a(e var1, Rect var2, Rect var3, Paint var4) {
      this.a.a(aa.a(var1), var2, var3, var4);
   }

   public void a(e var1, Rect var2, RectF var3, Paint var4) {
      this.a.a(aa.a(var1), var2, var3, var4);
   }

   public void a(float var1, float var2, float var3, Paint var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public void a(int var1, Mode var2) {
      this.a.a(var1, var2);
   }

   public void a(int var1) {
      this.a.a(var1);
   }

   public void a(float var1, float var2, float var3, float var4, Paint var5) {
      this.a.a(var1, var2, var3, var4, var5);
   }

   public void a(float[] var1, int var2, int var3, Paint var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public void a(Rect var1, Paint var2) {
      this.a.a(var1, var2);
   }

   public void a(RectF var1, Paint var2) {
      this.a.a(var1, var2);
   }

   public void a(String var1, float var2, float var3, Paint var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public boolean equals(Object var1) {
      return this.a.equals(var1);
   }

   public int hashCode() {
      return this.a.hashCode();
   }

   public void a() {
      this.a.b();
   }

   public void a(float var1, float var2, float var3) {
      this.a.a(var1, var2, var3);
   }

   public void b() {
      this.a.a();
   }

   public void a(float var1, float var2, float var3, float var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public void a(float var1, float var2) {
      this.a.b(var1, var2);
   }

   public void a(e var1) {
      this.a.a(var1.b());
   }

   public String toString() {
      return this.a.toString();
   }

   public void b(float var1, float var2) {
      this.a.a(var1, var2);
   }

   public void a(boolean var1) {
      this.b = var1;
   }

   public boolean c() {
      return this.b;
   }

   public void a(m var1) {
      var1.a(com.corrodinggames.rts.gameFramework.l.B().bO);
   }

   public void a(Bitmap var1) {}

   public void a(Lock var1) {}

   public void b(Lock var1) {}

   public boolean a(ae var1) {
      return false;
   }
}
