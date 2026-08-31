package com.corrodinggames.rts.gameFramework.b.a;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.a.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class g {

   public final int a = 2;
   public final int b;
   public final int c;
   public final IntBuffer d;
   public final ShortBuffer e;
   public int f;
   public int g;
   final int[] h;
   private int i;
   private int j;


   public g(int var1, int var2) {
      this.b = this.a + 2;
      this.c = this.b * 4;
      ByteBuffer var3 = ByteBuffer.allocateDirect(var1 * this.c);
      var3.order(ByteOrder.nativeOrder());
      this.d = var3.asIntBuffer();
      if(var2 > 0) {
         var3 = ByteBuffer.allocateDirect(var2 * 2);
         var3.order(ByteOrder.nativeOrder());
         this.e = var3.asShortBuffer();
      } else {
         this.e = null;
      }

      this.f = 0;
      this.g = 0;
      this.h = new int[var1 * this.c / 4];
      this.i = a.b.a();
      this.j = a.a.a();
   }

   public void a(float[] var1, int var2, int var3) {
      this.d.clear();
      int var4 = var2 + var3;
      int var5 = var2;

      for(int var6 = 0; var5 < var4; ++var6) {
         this.h[var6] = Float.floatToRawIntBits(var1[var5]);
         ++var5;
      }

      this.d.put(this.h, 0, var3);
      this.d.flip();
      this.f = var3 / this.b;
   }

   public void a(short[] var1, int var2, int var3) {
      this.e.clear();
      this.e.put(var1, var2, var3);
      this.e.flip();
      this.g = var3;
   }

   public void a() {
      this.d.position(0);
      GLES20.glVertexAttribPointer(this.j, this.a, 5126, false, this.c, this.d);
      GLES20.glEnableVertexAttribArray(this.j);
      this.d.position(this.a);
      GLES20.glVertexAttribPointer(this.i, 2, 5126, false, this.c, this.d);
      GLES20.glEnableVertexAttribArray(this.i);
   }

   public void a(int var1, int var2, int var3) {
      if(this.e != null) {
         this.e.position(var2);
         GLES20.glDrawElements(var1, var3, 5123, this.e);
      } else {
         GLES20.glDrawArrays(var1, var2, var3);
      }

   }

   public void b() {
      GLES20.glDisableVertexAttribArray(this.i);
   }
}
