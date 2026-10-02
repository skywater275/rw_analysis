package com.corrodinggames.rts.java.audio.a;


public class b {

   public float[] a;
   public int b;
   public boolean c;


   public b() {
      this(true, 16);
   }

   public b(int var1) {
      this(true, var1);
   }

   public b(boolean var1, int var2) {
      this.c = var1;
      this.a = new float[var2];
   }

   public void a(float var1) {
      float[] var2 = this.a;
      if(this.b == var2.length) {
         var2 = this.a(Math.max(8, (int)((float)this.b * 1.75F)));
      }

      var2[this.b++] = var1;
   }

   public void a(int var1, float var2) {
      if(var1 >= this.b) {
         throw new IndexOutOfBoundsException("index can\'t be >= size: " + var1 + " >= " + this.b);
      } else {
         this.a[var1] = var2;
      }
   }

   public void b(int var1, float var2) {
      if(var1 > this.b) {
         throw new IndexOutOfBoundsException("index can\'t be > size: " + var1 + " > " + this.b);
      } else {
         float[] var3 = this.a;
         if(this.b == var3.length) {
            var3 = this.a(Math.max(8, (int)((float)this.b * 1.75F)));
         }

         if(this.c) {
            System.arraycopy(var3, var1, var3, var1 + 1, this.b - var1);
         } else {
            var3[this.b] = var3[var1];
         }

         ++this.b;
         var3[var1] = var2;
      }
   }

   public float a() {
      return this.a[--this.b];
   }

   public float b() {
      if(this.b == 0) {
         throw new IllegalStateException("Array is empty.");
      } else {
         return this.a[0];
      }
   }

   public void c() {
      this.b = 0;
   }

   protected float[] a(int var1) {
      float[] var2 = new float[var1];
      float[] var3 = this.a;
      System.arraycopy(var3, 0, var2, 0, Math.min(this.b, var2.length));
      this.a = var2;
      return var2;
   }

   public int hashCode() {
      if(!this.c) {
         return super.hashCode();
      } else {
         float[] var1 = this.a;
         int var2 = 1;
         int var3 = 0;

         for(int var4 = this.b; var3 < var4; ++var3) {
            var2 = var2 * 31 + Float.floatToIntBits(var1[var3]);
         }

         return var2;
      }
   }

   public boolean equals(Object var1) {
      if(var1 == this) {
         return true;
      } else if(!this.c) {
         return false;
      } else if(!(var1 instanceof b)) {
         return false;
      } else {
         b var2 = (b)var1;
         if(!var2.c) {
            return false;
         } else {
            int var3 = this.b;
            if(var3 != var2.b) {
               return false;
            } else {
               float[] var4 = this.a;
               float[] var5 = var2.a;

               for(int var6 = 0; var6 < var3; ++var6) {
                  if(var4[var6] != var5[var6]) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   public String toString() {
      if(this.b == 0) {
         return "[]";
      } else {
         float[] var1 = this.a;
         StringBuilder var2 = new StringBuilder(32);
         var2.append('[');
         var2.append(var1[0]);

         for(int var3 = 1; var3 < this.b; ++var3) {
            var2.append(", ");
            var2.append(var1[var3]);
         }

         var2.append(']');
         return var2.toString();
      }
   }
}
