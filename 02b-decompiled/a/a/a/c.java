package a.a.a;

import a.a.a.a;

public class c extends a {

   private int[] a;


   protected c() {}

   public c(int var1, int var2, int[] var3) {
      this.a(32, var1, 6 + var3.length);
      this.a(var2);
      this.a = var3;
   }

   public String a() {
      return "EAK";
   }

   public int[] c() {
      return this.a;
   }

   public byte[] d() {
      byte[] var1 = super.d();

      for(int var2 = 0; var2 < this.a.length; ++var2) {
         var1[4 + var2] = (byte)(this.a[var2] & 255);
      }

      return var1;
   }

   protected void a(byte[] var1, int var2, int var3) {
      super.a(var1, var2, var3);
      this.a = new int[var3 - 6];

      for(int var4 = 0; var4 < this.a.length; ++var4) {
         this.a[var4] = var1[var2 + 4 + var4] & 255;
      }

   }
}
