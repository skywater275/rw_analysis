package com.corrodinggames.rts.appFramework;

import android.util.Log;
import android.view.MotionEvent;
import java.lang.reflect.Method;

public class l {

   public static final boolean a;
   public static final boolean b;
   private static Method c;
   private static Method d;
   private static Method e;
   private static Method f;
   private static Method g;
   private static Method h;
   private static Method i;
   private static Method j;
   private static Method k;
   private static int l = 6;
   private static int m = 8;
   private static final float[] n;
   private static final float[] o;
   private static final float[] p;
   private static final int[] q;
   private static final int[] r;


   // $FF: synthetic method
   static int[] a() {
      return r;
   }

   static {
      boolean var0 = false;

      try {
         d = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
         e = MotionEvent.class.getMethod("findPointerIndex", new Class[]{Integer.TYPE});
         f = MotionEvent.class.getMethod("getPressure", new Class[]{Integer.TYPE});
         g = MotionEvent.class.getMethod("getHistoricalX", new Class[]{Integer.TYPE, Integer.TYPE});
         h = MotionEvent.class.getMethod("getHistoricalY", new Class[]{Integer.TYPE, Integer.TYPE});
         i = MotionEvent.class.getMethod("getHistoricalPressure", new Class[]{Integer.TYPE, Integer.TYPE});
         j = MotionEvent.class.getMethod("getX", new Class[]{Integer.TYPE});
         k = MotionEvent.class.getMethod("getY", new Class[]{Integer.TYPE});
         var0 = true;
      } catch (Exception var5) {
         Log.b("MultiTouchController", "static initializer failed", var5);
      }

      a = var0;
      if(a) {
         try {
            l = MotionEvent.class.getField("ACTION_POINTER_UP").getInt((Object)null);
            m = MotionEvent.class.getField("ACTION_POINTER_INDEX_SHIFT").getInt((Object)null);
         } catch (Exception var4) {
            ;
         }
      }

      boolean var1 = false;

      try {
         c = MotionEvent.class.getMethod("getButtonState", new Class[0]);
         var1 = true;
         Log.b("MultiTouchController", "--- Mouse API succeeded");
      } catch (Exception var3) {
         Log.b("MultiTouchController", "static initializer for mouse failed", var3);
      }

      b = var1;
      n = new float[10];
      o = new float[10];
      p = new float[10];
      q = new int[10];
      r = new int[10];
   }
}
