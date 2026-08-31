package com.corrodinggames.rts.gameFramework.utility;

import java.io.OutputStream;

public class w extends OutputStream {

   public byte[] a;
   protected int b;


   public w() {
      this.a = new byte[32];
   }

   public w(int var1) {
      if(var1 >= 0) {
         this.a = new byte[var1];
      } else {
         throw new IllegalArgumentException("size < 0");
      }
   }

   public void close() {
      super.close();
   }

   private void a(int var1) {
      if(this.b + var1 > this.a.length) {
         byte[] var2 = new byte[(this.b + var1) * 2];
         System.arraycopy(this.a, 0, var2, 0, this.b);
         this.a = var2;
      }
   }

   public synchronized void a() {
      this.b = 0;
   }

   public int b() {
      return this.b;
   }

   public String toString() {
      return new String(this.a, 0, this.b);
   }

   public static void a(int var0, int var1, int var2) {
      if((var1 | var2) < 0 || var1 > var0 || var0 - var1 < var2) {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public synchronized void write(byte[] var1, int var2, int var3) {
      a(var1.length, var2, var3);
      if(var3 != 0) {
         this.a(var3);
         System.arraycopy(var1, var2, this.a, this.b, var3);
         this.b += var3;
      }
   }

   public synchronized void write(int var1) {
      if(this.b == this.a.length) {
         this.a(1);
      }

      this.a[this.b++] = (byte)var1;
   }
}
