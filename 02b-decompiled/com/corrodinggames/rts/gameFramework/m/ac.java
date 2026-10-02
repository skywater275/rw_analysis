package com.corrodinggames.rts.gameFramework.m;

import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

class ac {

   private FloatBuffer b;
   private FloatBuffer c;
   private FloatBuffer d;
   private IntBuffer e;
   private IntBuffer f;
   private IntBuffer g;
   private CharBuffer h;
   private Buffer i;
   private Buffer j;
   private Buffer k;
   private int l;
   private int m;
   private int n;
   private int o;
   private boolean p;
   private int q;
   private int r;
   private int s;
   private int t;
   // $FF: synthetic field
   static final boolean a = !ac.class.desiredAssertionStatus();


   void a(int var1, int var2, float var3, float var4) {
      if(var1 >= 0 && var1 < this.m) {
         if(var2 >= 0 && var2 < this.n) {
            int var5 = this.m * var2 + var1;
            int var6 = var5 * 2;
            if(this.l == 5126) {
               this.c.put(var6, var3);
               this.c.put(var6 + 1, var4);
            } else {
               this.f.put(var6, (int)(var3 * 65536.0F));
               this.f.put(var6 + 1, (int)(var4 * 65536.0F));
            }

         } else {
            throw new IllegalArgumentException("j");
         }
      } else {
         throw new IllegalArgumentException("i");
      }
   }

   void a(int var1, int var2, float var3, float var4, float var5, float var6, float var7, float[] var8) {
      if(var1 >= 0 && var1 < this.m) {
         if(var2 >= 0 && var2 < this.n) {
            int var9 = this.m * var2 + var1;
            int var10 = var9 * 3;
            int var11 = var9 * 2;
            int var12 = var9 * 4;
            if(this.l == 5126) {
               this.b.put(var10, var3);
               this.b.put(var10 + 1, var4);
               this.b.put(var10 + 2, var5);
               this.c.put(var11, var6);
               this.c.put(var11 + 1, var7);
               if(var8 != null) {
                  this.d.put(var12, var8[0]);
                  this.d.put(var12 + 1, var8[1]);
                  this.d.put(var12 + 2, var8[2]);
                  this.d.put(var12 + 3, var8[3]);
               }
            } else {
               this.e.put(var10, (int)(var3 * 65536.0F));
               this.e.put(var10 + 1, (int)(var4 * 65536.0F));
               this.e.put(var10 + 2, (int)(var5 * 65536.0F));
               this.f.put(var11, (int)(var6 * 65536.0F));
               this.f.put(var11 + 1, (int)(var7 * 65536.0F));
               if(var8 != null) {
                  this.g.put(var12, (int)(var8[0] * 65536.0F));
                  this.g.put(var12 + 1, (int)(var8[1] * 65536.0F));
                  this.g.put(var12 + 2, (int)(var8[2] * 65536.0F));
                  this.g.put(var12 + 3, (int)(var8[3] * 65536.0F));
               }
            }

         } else {
            throw new IllegalArgumentException("j");
         }
      } else {
         throw new IllegalArgumentException("i");
      }
   }

   public static void a(GL10 var0, boolean var1, boolean var2) {
      var0.glEnableClientState('\u8074');
      if(var1) {
         var0.glEnableClientState('\u8078');
         var0.glEnable(3553);
      } else {
         var0.glDisableClientState('\u8078');
         var0.glDisable(3553);
      }

      if(var2) {
         var0.glEnableClientState('\u8076');
      } else {
         var0.glDisableClientState('\u8076');
      }

   }

   public void b(GL10 var1, boolean var2, boolean var3) {
      if(!this.p) {
         var1.glVertexPointer(3, this.l, 0, this.i);
         if(var2) {
            var1.glTexCoordPointer(2, this.l, 0, this.j);
         }

         if(var3) {
            var1.glColorPointer(4, this.l, 0, this.k);
         }

         var1.glDrawElements(4, this.o, 5123, this.h);
      } else {
         GL11 var4 = (GL11)var1;
         var4.glBindBuffer('\u8892', this.q);
         var4.glVertexPointer(3, this.l, 0, 0);
         if(var2) {
            var4.glBindBuffer('\u8892', this.s);
            var4.glTexCoordPointer(2, this.l, 0, 0);
         }

         if(var3) {
            var4.glBindBuffer('\u8892', this.t);
            var4.glColorPointer(4, this.l, 0, 0);
         }

         var4.glBindBuffer('\u8893', this.r);
         var4.glDrawElements(4, this.o, 5123, 0);
         var4.glBindBuffer('\u8892', 0);
         var4.glBindBuffer('\u8893', 0);
      }

   }

   public static void a(GL10 var0) {
      var0.glDisableClientState('\u8074');
   }

}
