package a.a;

import java.io.IOException;

public class r {

   public static final r a = new r();
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
   private int l;


   public r() {
      try {
         this.a(32, 32, 300, 70, 0, 3, 3, 3, 2000, 600, 300);
      } catch (IOException var2) {
         throw new RuntimeException("IOException on ReliableSocketProfile default:" + var2);
      }
   }

   public r(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      this.a(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
   }

   private void a(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      this.a("maxSendQueueSize", var1, 1, 255);
      this.a("maxRecvQueueSize", var2, 1, 255);
      this.a("maxSegmentSize", var3, 22, 6535);
      this.a("maxOutstandingSegs", var4, 1, 255);
      this.a("maxRetrans", var5, 0, 255);
      this.a("maxCumulativeAcks", var6, 0, 255);
      this.a("maxOutOfSequence", var7, 0, 255);
      this.a("maxAutoReset", var8, 0, 255);
      this.a("nullSegmentTimeout", var9, 0, '\uffff');
      this.a("retransmissionTimeout", var10, 100, '\uffff');
      this.a("cumulativeAckTimeout", var11, 100, '\uffff');
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.f = var5;
      this.g = var6;
      this.h = var7;
      this.i = var8;
      this.j = var9;
      this.k = var10;
      this.l = var11;
   }

   public int a() {
      return this.d;
   }

   public int b() {
      return this.e;
   }

   public int c() {
      return this.f;
   }

   public int d() {
      return this.g;
   }

   public int e() {
      return this.h;
   }

   public int f() {
      return this.i;
   }

   public int g() {
      return this.j;
   }

   public int h() {
      return this.k;
   }

   public int i() {
      return this.l;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("[");
      var1.append(this.b).append(", ");
      var1.append(this.c).append(", ");
      var1.append(this.d).append(", ");
      var1.append(this.e).append(", ");
      var1.append(this.f).append(", ");
      var1.append(this.g).append(", ");
      var1.append(this.h).append(", ");
      var1.append(this.i).append(", ");
      var1.append(this.j).append(", ");
      var1.append(this.k).append(", ");
      var1.append(this.l);
      var1.append("]");
      return var1.toString();
   }

   private void a(String var1, int var2, int var3, int var4) {
      if(var2 < var3 || var2 > var4) {
         throw new IOException(var1 + " out of range");
      }
   }

}
