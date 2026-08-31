package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.bs;
import com.corrodinggames.rts.gameFramework.bu;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public final class br {

   l a;
   public int b = 0;
   public static int c = 40;
   public int d = 0;
   bu e = new bu(this);
   Paint f = new Paint();
   Rect g = new Rect();
   int h = -1;


   public br(l var1) {
      this.a = var1;
   }

   public static final long a() {
      return System.nanoTime();
   }

   public static final float a(long var0) {
      return (float)(System.nanoTime() - var0) / 1000000.0F;
   }

   public static final double a(long var0, long var2) {
      return (double)(var2 - var0) / 1000000.0D;
   }

   public static final void a(String var0, long var1) {
      l.e(var0 + "" + a((double)a(var1)));
   }

   public final void a(bs var1) {}

   public final void b(bs var1) {}

   public static final String a(double var0) {
      return "" + f.a(var0, 3) + "ms";
   }

   public static final String b(double var0) {
      return "" + var0 / 1000000.0D + "ms";
   }

   public final void b() {}

   public final void c() {}

   public final void a(boolean var1, boolean var2) {}

}
