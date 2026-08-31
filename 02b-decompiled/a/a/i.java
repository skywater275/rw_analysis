package a.a;

import a.a.h;

class i {

   private int a;
   private int b;
   private int c;
   private int d;
   private int e;


   public synchronized int a() {
      return this.a = h.a(this.a);
   }

   public synchronized int a(int var1) {
      this.a = var1;
      return this.a;
   }

   public synchronized int b(int var1) {
      this.b = var1;
      return this.b;
   }

   public synchronized int b() {
      return this.b;
   }

   public synchronized void c() {
      ++this.c;
   }

   public synchronized int d() {
      return this.c;
   }

   public synchronized int e() {
      int var1 = this.c;
      this.c = 0;
      return var1;
   }

   public synchronized void f() {
      ++this.d;
   }

   public synchronized int g() {
      return this.d;
   }

   public synchronized int h() {
      int var1 = this.d;
      this.d = 0;
      return var1;
   }

   public synchronized void i() {
      ++this.e;
   }

   public synchronized int j() {
      return this.e;
   }

   public synchronized int k() {
      int var1 = this.e;
      this.e = 0;
      return var1;
   }

   public synchronized void l() {
      this.d = 0;
      this.e = 0;
      this.c = 0;
   }
}
