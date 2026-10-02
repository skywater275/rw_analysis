package com.corrodinggames.rts.gameFramework.e;

import android.content.Context;
import android.os.Build.VERSION;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.e.b;
import com.corrodinggames.rts.gameFramework.e.c;
import com.corrodinggames.rts.gameFramework.e.d;
import com.corrodinggames.rts.gameFramework.e.e;
import com.corrodinggames.rts.gameFramework.e.f;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class a {

   public static final c a = new c();
   public static c b = a;
   public static Boolean c;
   public static String d;
   public static String e;


   protected static String a() {
      Context var0 = com.corrodinggames.rts.appFramework.c.a();
      File var1 = var0.b((String)null);
      if(var1 != null) {
         return var1.getAbsolutePath();
      } else {
         l.b("Failed to get an internal path.");
         return null;
      }
   }

   public static void b() {
      e = null;
      if(l.at()) {
         if(VERSION.SDK_INT < 19) {
            e = "Android version too old for new file system support";
            l.e("FileLoader: SDK too old, not changing FileLoader");
            return;
         }

         int var0 = l.B().bQ.storageType;
         l.e("FileLoader: storageBehaviour:" + var0);
         c var1 = a(var0);
         l.e("Using file loader: " + var1.d());
         b = var1;
      }

   }

   public static boolean a(String var0) {
      return b.p(var0);
   }

   public static b a(boolean var0) {
      b var1 = new b();
      if(!l.at()) {
         var1.b = false;
         var1.c = true;
         return var1;
      } else if(VERSION.SDK_INT < 19) {
         var1.b = false;
         var1.c = true;
         return var1;
      } else {
         var1.b = true;
         var1.a = false;
         if(d != null) {
            var1.a = true;
         }

         if(c != null && !c.booleanValue()) {
            var1.c = true;
            var1.b = false;
            var1.a = false;
         }

         if(VERSION.SDK_INT <= 28 && c == null) {
            l.b("FileLoader using direct external access due to sdk: " + VERSION.SDK_INT);
            var1.c = true;
            var1.b = false;
            var1.a = false;
         }

         return var1;
      }
   }

   public static c a(int var0) {
      if(!l.at()) {
         return new c();
      } else if(VERSION.SDK_INT >= 19) {
         String var1 = a();
         d var3 = null;
         if(var1 == null) {
            e = "Failed to get internal app path (is it unmounted?).";
            var0 = 3;
         } else {
            var3 = new d(var1, "internal");
            var3.i = "Internal: ";
         }

         b var4 = a(false);
         Object var5;
         if(!var4.a) {
            if(!var4.c) {
               l.b("Not using direct external backend: As direct reads will cause problems");
               var5 = null;
               var0 = 0;
            } else {
               l.b("FileLoader using direct external file access! SDK:" + VERSION.SDK_INT);
               var5 = new c();
            }
         } else {
            l.e("FileLoader using overriddenExternalPath:" + d);
            var5 = new d(d, "external");
         }

         f var6 = new f();
         if(var0 != 3 && var3 == null) {
            l.b("No available file backends!!");
            return var6;
         } else {
            e var2;
            if(var0 == 1) {
               var2 = new e(var3, "[INTERNAL-PATH]/", (c)var5, "[EXTERNAL-PATH]/");
            } else if(var0 == 2) {
               var2 = new e((c)var5, "[EXTERNAL-PATH]/", var3, "[INTERNAL-PATH]/");
            } else if(var0 == 3) {
               var2 = new e((c)var5, "[EXTERNAL-PATH]/", var6, "[NULL-PATH]/");
            } else {
               var2 = new e(var3, "[INTERNAL-PATH]/", var6, "[NULL-PATH]/");
            }

            var2.h.d = true;
            return var2;
         }
      } else {
         l.e("FileLoader: SDK too old, not changing FileLoader");
         return new c();
      }
   }

   public static String c() {
      return b.a();
   }

   public static void b(String var0) {
      b.a(var0);
   }

   public static String a(String var0, String var1) {
      return b.a(var0, var1);
   }

   public static boolean c(String var0) {
      return b.b(var0);
   }

   public static String d(String var0) {
      return b.e(var0);
   }

   public static String e(String var0) {
      return b.f(var0);
   }

   public static boolean f(String var0) {
      return b.a(var0, false);
   }

   public static boolean g(String var0) {
      return b.a(var0, true);
   }

   public static String[] h(String var0) {
      return b.b(var0, false);
   }

   public static String[] a(String var0, boolean var1) {
      return b.b(var0, var1);
   }

   public static boolean i(String var0) {
      return b.g(var0);
   }

   public static j j(String var0) {
      return b.i(var0);
   }

   public static j a(File var0) {
      return b.j(var0.getAbsolutePath());
   }

   public static j k(String var0) {
      return b.j(var0);
   }

   public static OutputStream a(File var0, boolean var1) {
      return b.c(var0.getAbsolutePath(), var1);
   }

   public static OutputStream b(String var0, boolean var1) {
      return b.c(var0, var1);
   }

   public static boolean l(String var0) {
      return b.k(var0);
   }

   public static String d() {
      return b.b();
   }

   public static String e() {
      return b.c();
   }

   public static long m(String var0) {
      return b.l(var0);
   }

   public static File a(String var0, String var1, boolean var2) {
      return b.a(var0, var1, var2);
   }

   public static boolean a(File var0, File var1) {
      if(l.av() && var1.exists()) {
         var1.delete();
      }

      return var0.renameTo(var1);
   }

   public static boolean b(File var0, File var1) {
      return b.a(var0, var1);
   }

   public static boolean b(File var0) {
      return b.b(var0);
   }

   public static String n(String var0) {
      return b.m(var0);
   }

   public static boolean f() {
      return b.e();
   }

   public static String o(String var0) {
      return b.n(var0);
   }

   public static String p(String var0) {
      return b.o(var0);
   }

   public static File a(Context var0, String var1, String var2) {
      File var4;
      try {
         File var3 = var0.i();
         var4 = File.createTempFile(var1, var2, var3);
         return var4;
      } catch (IOException var7) {
         try {
            var4 = var0.j();
            File var5 = File.createTempFile(var1, var2, var4);
            return var5;
         } catch (IOException var6) {
            var7.printStackTrace();
            throw var6;
         }
      }
   }

   public static void c(File var0) {
      b.a(var0);
   }

}
