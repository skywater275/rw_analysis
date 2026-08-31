package android.util;

import android.util.a;

public class SparseArray implements Cloneable {

   private static final Object a = new Object();
   private boolean b;
   private int[] c;
   private Object[] d;
   private int e;


   public SparseArray() {
      this(10);
   }

   public SparseArray(int var1) {
      this.b = false;
      if(var1 == 0) {
         this.c = a.b;
         this.d = a.d;
      } else {
         var1 = com.a.a.a.a.c(var1);
         this.c = new int[var1];
         this.d = new Object[var1];
      }

      this.e = 0;
   }

   public SparseArray a() {
      SparseArray var1 = null;

      try {
         var1 = (SparseArray)super.clone();
         var1.c = (int[])this.c.clone();
         var1.d = (Object[])this.d.clone();
      } catch (CloneNotSupportedException var3) {
         ;
      }

      return var1;
   }

   public Object a(int var1) {
      return this.a(var1, (Object)null);
   }

   public Object a(int var1, Object var2) {
      int var3 = a.a(this.c, this.e, var1);
      return var3 >= 0 && this.d[var3] != a?this.d[var3]:var2;
   }

   private void c() {
      int var1 = this.e;
      int var2 = 0;
      int[] var3 = this.c;
      Object[] var4 = this.d;

      for(int var5 = 0; var5 < var1; ++var5) {
         Object var6 = var4[var5];
         if(var6 != a) {
            if(var5 != var2) {
               var3[var2] = var3[var5];
               var4[var2] = var6;
               var4[var5] = null;
            }

            ++var2;
         }
      }

      this.b = false;
      this.e = var2;
   }

   public void b(int var1, Object var2) {
      int var3 = a.a(this.c, this.e, var1);
      if(var3 >= 0) {
         this.d[var3] = var2;
      } else {
         var3 = ~var3;
         if(var3 < this.e && this.d[var3] == a) {
            this.c[var3] = var1;
            this.d[var3] = var2;
            return;
         }

         if(this.b && this.e >= this.c.length) {
            this.c();
            var3 = ~a.a(this.c, this.e, var1);
         }

         if(this.e >= this.c.length) {
            int var4 = com.a.a.a.a.c(this.e + 1);
            int[] var5 = new int[var4];
            Object[] var6 = new Object[var4];
            System.arraycopy(this.c, 0, var5, 0, this.c.length);
            System.arraycopy(this.d, 0, var6, 0, this.d.length);
            this.c = var5;
            this.d = var6;
         }

         if(this.e - var3 != 0) {
            System.arraycopy(this.c, var3, this.c, var3 + 1, this.e - var3);
            System.arraycopy(this.d, var3, this.d, var3 + 1, this.e - var3);
         }

         this.c[var3] = var1;
         this.d[var3] = var2;
         ++this.e;
      }

   }

   public int b() {
      if(this.b) {
         this.c();
      }

      return this.e;
   }

   public int b(int var1) {
      if(this.b) {
         this.c();
      }

      return this.c[var1];
   }

   public Object c(int var1) {
      if(this.b) {
         this.c();
      }

      return this.d[var1];
   }

   public void c(int var1, Object var2) {
      if(this.e != 0 && var1 <= this.c[this.e - 1]) {
         this.b(var1, var2);
      } else {
         if(this.b && this.e >= this.c.length) {
            this.c();
         }

         int var3 = this.e;
         if(var3 >= this.c.length) {
            int var4 = com.a.a.a.a.c(var3 + 1);
            int[] var5 = new int[var4];
            Object[] var6 = new Object[var4];
            System.arraycopy(this.c, 0, var5, 0, this.c.length);
            System.arraycopy(this.d, 0, var6, 0, this.d.length);
            this.c = var5;
            this.d = var6;
         }

         this.c[var3] = var1;
         this.d[var3] = var2;
         this.e = var3 + 1;
      }
   }

   public String toString() {
      if(this.b() <= 0) {
         return "{}";
      } else {
         StringBuilder var1 = new StringBuilder(this.e * 28);
         var1.append('{');

         for(int var2 = 0; var2 < this.e; ++var2) {
            if(var2 > 0) {
               var1.append(", ");
            }

            int var3 = this.b(var2);
            var1.append(var3);
            var1.append('=');
            Object var4 = this.c(var2);
            if(var4 != this) {
               var1.append(var4);
            } else {
               var1.append("(this Map)");
            }
         }

         var1.append('}');
         return var1.toString();
      }
   }

   // $FF: synthetic method
   public Object clone() {
      return this.a();
   }

}
