package a.a.a;

import a.a.a.a;
import a.a.a.b;
import a.a.a.c;
import a.a.a.d;
import a.a.a.e;
import a.a.a.f;
import a.a.a.g;
import java.io.IOException;

public abstract class h {

   private int a;
   private int b;
   private int c;
   private int d = -1;
   private int e = 0;


   public abstract String a();

   public int m() {
      return this.c;
   }

   public int b() {
      return this.b;
   }

   public void a(int var1) {
      this.a |= 64;
      this.d = var1;
   }

   public int n() {
      return (this.a & 64) == 64?this.d:-1;
   }

   public int o() {
      return this.e;
   }

   public void b(int var1) {
      this.e = var1;
   }

   public byte[] d() {
      byte[] var1 = new byte[this.b()];
      var1[0] = (byte)(this.a & 255);
      var1[1] = (byte)(this.b & 255);
      var1[2] = (byte)(this.c & 255);
      var1[3] = (byte)(this.d & 255);
      return var1;
   }

   public String toString() {
      return this.a() + " [ SEQ = " + this.m() + ", ACK = " + (this.n() >= 0?"" + this.n():"N/A") + ", LEN = " + this.b() + " ]";
   }

   public static h b(byte[] var0, int var1, int var2) {
      Object var3 = null;
      if(var2 < 6) {
         throw new IOException("Invalid segment:" + var2);
      } else {
         byte var4 = var0[var1];
         if((var4 & -128) != 0) {
            var3 = new g();
         } else if((var4 & 8) != 0) {
            var3 = new e();
         } else if((var4 & 32) != 0) {
            var3 = new c();
         } else if((var4 & 16) != 0) {
            var3 = new f();
         } else if((var4 & 2) != 0) {
            var3 = new d();
         } else if((var4 & 64) != 0) {
            if(var2 == 6) {
               var3 = new a();
            } else {
               var3 = new b();
            }
         }

         if(var3 == null) {
            throw new IOException("Invalid segment");
         } else {
            ((h)var3).a(var0, var1, var2);
            return (h)var3;
         }
      }
   }

   protected void a(int var1, int var2, int var3) {
      this.a = var1;
      this.c = var2;
      this.b = var3;
   }

   protected void a(byte[] var1, int var2, int var3) {
      this.a = var1[var2] & 255;
      this.b = var1[var2 + 1] & 255;
      this.c = var1[var2 + 2] & 255;
      this.d = var1[var2 + 3] & 255;
   }
}
