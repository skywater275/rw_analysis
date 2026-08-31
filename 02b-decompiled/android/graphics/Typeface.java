package android.graphics;

import android.util.SparseArray;

public class Typeface {

   public static final Typeface a = a((String)null, 0);
   public static final Typeface b = a((String)null, 1);
   public static final Typeface c = a("sans-serif", 0);
   public static final Typeface d = a("serif", 0);
   public static final Typeface e = a("monospace", 0);
   static Typeface[] f = new Typeface[]{a, b, a((String)null, 2), a((String)null, 3)};
   private static final SparseArray i = new SparseArray(3);
   int g;
   private int j = 0;
   String h;


   public final boolean a() {
      return (this.j & 1) != 0;
   }

   public static Typeface a(String var0, int var1) {
      Typeface var2 = new Typeface(0);
      var2.j = var1;
      var2.h = var0;
      return var2;
   }

   public static Typeface a(Typeface var0, int var1) {
      int var2 = 0;
      if(var0 != null) {
         if(var0.j == var1) {
            return var0;
         }

         var2 = var0.g;
      }

      SparseArray var4 = (SparseArray)i.a(var2);
      Typeface var3;
      if(var4 != null) {
         var3 = (Typeface)var4.a(var1);
         if(var3 != null) {
            return var3;
         }
      }

      var3 = new Typeface(0);
      var3.j = var1;
      var3.h = var0.h;
      if(var4 == null) {
         var4 = new SparseArray(4);
         i.b(var2, var4);
      }

      var4.b(var1, var3);
      return var3;
   }

   private Typeface(int var1) {
      this.g = var1;
      this.j = b(var1);
   }

   protected void finalize() {
      try {
         a(this.g);
      } finally {
         super.finalize();
      }

   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(var1 != null && this.getClass() == var1.getClass()) {
         Typeface var2 = (Typeface)var1;
         return this.j == var2.j && this.g == var2.g;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = this.g;
      var1 = 31 * var1 + this.j;
      return var1;
   }

   private static void a(int var0) {}

   private static int b(int var0) {
      return 0;
   }

}
