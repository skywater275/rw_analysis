package android.graphics;

import com.a.a.a.a;

public class TemporaryBuffer {

   private static char[] a = null;


   public static char[] a(int var0) {
      Class var2 = TemporaryBuffer.class;
      char[] var1;
      synchronized(TemporaryBuffer.class) {
         var1 = a;
         a = null;
      }

      if(var1 == null || var1.length < var0) {
         var1 = new char[a.b(var0)];
      }

      return var1;
   }

   public static void a(char[] var0) {
      if(var0.length <= 1000) {
         Class var1 = TemporaryBuffer.class;
         synchronized(TemporaryBuffer.class) {
            a = var0;
         }
      }
   }

}
