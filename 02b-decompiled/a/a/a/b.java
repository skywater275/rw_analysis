package a.a.a;

import a.a.a.h;

public class b extends h {

   private byte[] a;


   protected b() {}

   public b(int var1, int var2, byte[] var3, int var4, int var5) {
      this.a(64, var1, 6);
      this.a(var2);
      this.a = new byte[var5];
      System.arraycopy(var3, var4, this.a, 0, var5);
   }

   public int b() {
      return this.a.length + super.b();
   }

   public String a() {
      return "DAT";
   }

   public byte[] c() {
      return this.a;
   }

   public byte[] d() {
      byte[] var1 = super.d();
      System.arraycopy(this.a, 0, var1, 6, this.a.length);
      return var1;
   }

   public void a(byte[] var1, int var2, int var3) {
      super.a(var1, var2, var3);
      this.a = new byte[var3 - 6];
      System.arraycopy(var1, var2 + 6, this.a, 0, this.a.length);
   }
}
