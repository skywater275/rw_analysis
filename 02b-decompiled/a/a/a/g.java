package a.a.a;

import a.a.a.h;
import java.io.IOException;

public class g extends h {

   private int a;
   private int b;
   private int c;
   private int d;
   private int e;
   private int f;
   private int g;
   private int h;
   private int i;
   private int j;
   private int k;


   protected g() {}

   public g(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      this.a(-128, var1, 22);
      this.a = 1;
      this.b = var2;
      this.c = 1;
      this.d = var3;
      this.e = var4;
      this.f = var5;
      this.g = var6;
      this.h = var7;
      this.i = var8;
      this.j = var9;
      this.k = var10;
   }

   public String a() {
      return "SYN";
   }

   public int c() {
      return this.b;
   }

   public int e() {
      return this.d;
   }

   public int f() {
      return this.e;
   }

   public int g() {
      return this.f;
   }

   public int h() {
      return this.g;
   }

   public int i() {
      return this.h;
   }

   public int j() {
      return this.i;
   }

   public int k() {
      return this.j;
   }

   public int l() {
      return this.k;
   }

   public byte[] d() {
      byte[] var1 = super.d();
      var1[4] = (byte)(this.a << 4 & 255);
      var1[5] = (byte)(this.b & 255);
      var1[6] = (byte)(this.c & 255);
      var1[7] = 0;
      var1[8] = (byte)(this.d >>> 8 & 255);
      var1[9] = (byte)(this.d >>> 0 & 255);
      var1[10] = (byte)(this.e >>> 8 & 255);
      var1[11] = (byte)(this.e >>> 0 & 255);
      var1[12] = (byte)(this.f >>> 8 & 255);
      var1[13] = (byte)(this.f >>> 0 & 255);
      var1[14] = (byte)(this.g >>> 8 & 255);
      var1[15] = (byte)(this.g >>> 0 & 255);
      var1[16] = (byte)(this.h & 255);
      var1[17] = (byte)(this.i & 255);
      var1[18] = (byte)(this.j & 255);
      var1[19] = (byte)(this.k & 255);
      return var1;
   }

   protected void a(byte[] var1, int var2, int var3) {
      super.a(var1, var2, var3);
      if(var3 < 22) {
         throw new IOException("Invalid SYN segment");
      } else {
         this.a = (var1[var2 + 4] & 255) >>> 4;
         if(this.a != 1) {
            throw new IOException("Invalid RUDP version:" + this.a);
         } else {
            this.b = var1[var2 + 5] & 255;
            this.c = var1[var2 + 6] & 255;
            this.d = (var1[var2 + 8] & 255) << 8 | (var1[var2 + 9] & 255) << 0;
            this.e = (var1[var2 + 10] & 255) << 8 | (var1[var2 + 11] & 255) << 0;
            this.f = (var1[var2 + 12] & 255) << 8 | (var1[var2 + 13] & 255) << 0;
            this.g = (var1[var2 + 14] & 255) << 8 | (var1[var2 + 15] & 255) << 0;
            this.h = var1[var2 + 16] & 255;
            this.i = var1[var2 + 17] & 255;
            this.j = var1[var2 + 18] & 255;
            this.k = var1[var2 + 19] & 255;
         }
      }
   }
}
