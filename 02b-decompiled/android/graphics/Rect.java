package android.graphics;


public final class Rect {

   public int a;
   public int b;
   public int c;
   public int d;


   public Rect() {}

   public Rect(int var1, int var2, int var3, int var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public Rect(Rect var1) {
      this.a = var1.a;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
   }

   public boolean equals(Object var1) {
      Rect var2 = (Rect)var1;
      return var2 == null?false:this.a == var2.a && this.b == var2.b && this.c == var2.c && this.d == var2.d;
   }

   public String toString() {
      return "Rect(" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ")";
   }

   public final boolean a() {
      return this.a >= this.c || this.b >= this.d;
   }

   public final int b() {
      return this.c - this.a;
   }

   public final int c() {
      return this.d - this.b;
   }

   public final int d() {
      return this.a + this.c >> 1;
   }

   public final int e() {
      return this.b + this.d >> 1;
   }

   public final float f() {
      return (float)(this.a + this.c) * 0.5F;
   }

   public final float g() {
      return (float)(this.b + this.d) * 0.5F;
   }

   public void h() {
      this.a = this.c = this.b = this.d = 0;
   }

   public void a(int var1, int var2, int var3, int var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public void a(Rect var1) {
      this.a = var1.a;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
   }

   public void a(int var1, int var2) {
      this.a += var1;
      this.b += var2;
      this.c += var1;
      this.d += var2;
   }

   public boolean b(int var1, int var2) {
      return this.a < this.c && this.b < this.d && var1 >= this.a && var1 < this.c && var2 >= this.b && var2 < this.d;
   }

   public boolean b(Rect var1) {
      return this.a < this.c && this.b < this.d && this.a <= var1.a && this.b <= var1.b && this.c >= var1.c && this.d >= var1.d;
   }

   public boolean b(int var1, int var2, int var3, int var4) {
      return this.a < var3 && var1 < this.c && this.b < var4 && var2 < this.d;
   }
}
