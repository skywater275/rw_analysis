package android.util;

import android.util.Log$1;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Log {

   private static final ThreadLocal a = new Log$1();


   public static int a(String var0, String var1) {
      return a(0, 2, var0, var1);
   }

   public static int b(String var0, String var1) {
      return a(0, 3, var0, var1);
   }

   public static int c(String var0, String var1) {
      return a(0, 5, var0, var1);
   }

   public static int a(String var0, String var1, Throwable var2) {
      return a(0, 5, var0, var1 + '\n' + a(var2));
   }

   public static native boolean isLoggable(String var0, int var1);

   public static int d(String var0, String var1) {
      return a(0, 6, var0, var1);
   }

   public static int b(String var0, String var1, Throwable var2) {
      return a(0, 6, var0, var1 + '\n' + a(var2));
   }

   public static int c(String var0, String var1, Throwable var2) {
      return a(0, var0, var1, var2, false);
   }

   static int a(int var0, String var1, String var2, Throwable var3, boolean var4) {
      throw new RuntimeException("removed");
   }

   public static String a(Throwable var0) {
      StringWriter var1 = new StringWriter();
      StackTraceElement[] var2 = (new Throwable()).getStackTrace();
      StackTraceElement[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         StackTraceElement var6 = var3[var5];
         var1.write(var6.toString() + "\n");
      }

      return var1.toString();
   }

   public static int a(int var0, int var1, String var2, String var3) {
      a(var0, var2, var3);
      return 0;
   }

   public static int a(int var0, String var1, String var2) {
      String var3 = ((SimpleDateFormat)a.get()).format(new Date());
      System.out.println(var3 + ": " + var2);
      return 0;
   }

}
