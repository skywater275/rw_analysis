package com.corrodinggames.rts.java.audio.a;


public class d {

   public int[] a;
   public int b;
   public boolean c;


   public d() {
      this(true, 16);
   }

   public d(boolean var1, int var2) {
      this.c = var1;
      this.a = new int[var2];
   }

   public d(d var1) {
      this.c = var1.c;
      this.b = var1.b;
      this.a = new int[this.b];
      System.arraycopy(var1.a, 0, this.a, 0, this.b);
   }

   public void a(int var1) {
      int[] var2 = this.a;
      if(this.b == var2.length) {
         var2 = this.d(Math.max(8, (int)((float)this.b * 1.75F)));
      }

      var2[this.b++] = var1;
   }

   public int b(int var1) {
      if(var1 >= this.b) {
         throw new IndexOutOfBoundsException("index can\'t be >= size: " + var1 + " >= " + this.b);
      } else {
         return this.a[var1];
      }
   }

   public int c(int var1) {
      if(var1 >= this.b) {
         throw new IndexOutOfBoundsException("index can\'t be >= size: " + var1 + " >= " + this.b);
      } else {
         int[] var2 = this.a;
         int var3 = var2[var1];
         --this.b;
         if(this.c) {
            System.arraycopy(var2, var1 + 1, var2, var1, this.b - var1);
         } else {
            var2[var1] = var2[this.b];
         }

         return var3;
      }
   }

   protected int[] d(int var1) {
      int[] var2 = new int[var1];
      int[] var3 = this.a;
      System.arraycopy(var3, 0, var2, 0, Math.min(this.b, var2.length));
      this.a = var2;
      return var2;
   }

   public int hashCode() {
      if(!this.c) {
         return super.hashCode();
      } else {
         int[] var1 = this.a;
         int var2 = 1;
         int var3 = 0;

         for(int var4 = this.b; var3 < var4; ++var3) {
            var2 = var2 * 31 + var1[var3];
         }

         return var2;
      }
   }

   public boolean equals(Object var1) {
      if(var1 == this) {
         return true;
      } else if(!this.c) {
         return false;
      } else if(!(var1 instanceof d)) {
         return false;
      } else {
         d var2 = (d)var1;
         if(!var2.c) {
            return false;
         } else {
            int var3 = this.b;
            if(var3 != var2.b) {
               return false;
            } else {
               int[] var4 = this.a;
               int[] var5 = var2.a;

               for(int var6 = 0; var6 < var3; ++var6) {
                  if(this.a[var6] != var2.a[var6]) {
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
         int[] var1 = this.a;
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
