package android.graphics;

import android.graphics.Matrix$1;

public class Matrix {

   public static Matrix a = new Matrix$1();
   public int b = a(0);


   public boolean equals(Object var1) {
      return !(var1 instanceof Matrix)?false:a(this.b, ((Matrix)var1).b);
   }

   public int hashCode() {
      return 44;
   }

   public void a(float[] var1) {
      if(var1.length < 9) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         a(this.b, var1);
      }
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(64);
      var1.append("Matrix{");
      this.a(var1);
      var1.append('}');
      return var1.toString();
   }

   public void a(StringBuilder var1) {
      float[] var2 = new float[9];
      this.a(var2);
      var1.append('[');
      var1.append(var2[0]);
      var1.append(", ");
      var1.append(var2[1]);
      var1.append(", ");
      var1.append(var2[2]);
      var1.append("][");
      var1.append(var2[3]);
      var1.append(", ");
      var1.append(var2[4]);
      var1.append(", ");
      var1.append(var2[5]);
      var1.append("][");
      var1.append(var2[6]);
      var1.append(", ");
      var1.append(var2[7]);
      var1.append(", ");
      var1.append(var2[8]);
      var1.append(']');
   }

   protected void finalize() {
      try {
         b(this.b);
      } finally {
         super.finalize();
      }

   }

   private static int a(int var0) {
      return 0;
   }

   private static void a(int var0, float[] var1) {}

   private static boolean a(int var0, int var1) {
      return false;
   }

   private static void b(int var0) {}

}
