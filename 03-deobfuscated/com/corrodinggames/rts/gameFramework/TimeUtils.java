/*
 * v19.115i 新建: 02b gameFramework.br.java 60 行直译 (class-discoveries: br=TimeUtils)
 * 依赖映射: l=GlobalState, f=GameUtils, bu=FrameCounter(03 构造签名不同, e 字段省略), bs=GamePhase
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.GlobalState;
import android.graphics.Rect;

public final class TimeUtils {
   GlobalState a;
   public int b = 0;
   public static int c = 40;
   public int d = 0;
   // 02b br.e (bu=FrameCounter) 依赖 03 FrameCounter(ExtraManager) 构造, 省略
   Paint f = new Paint();
   Rect g = new Rect();
   int h = -1;

   public TimeUtils(GlobalState var1) {
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
      GlobalState.e(var0 + "" + a((double)a(var1)));
   }

   public final void a(GamePhase var1) {}

   public final void b(GamePhase var1) {}

   public static final String a(double var0) {
      return "" + GameUtils.a(var0, 3) + "ms";
   }

   public static final String b(double var0) {
      return "" + var0 / 1000000.0D + "ms";
   }

   public final void b() {}

   public final void c() {}

   public final void a(boolean var1, boolean var2) {}
}
