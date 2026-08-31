package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.a;
import com.corrodinggames.rts.a.a.c;
import com.corrodinggames.rts.a.a.d;
import com.corrodinggames.rts.a.a.e;
import com.corrodinggames.rts.a.a.f;
import com.corrodinggames.rts.a.a.k;
import com.corrodinggames.rts.a.a.m;

public class n {

   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("Running unit tests");
      (new c()).a();
      (new d()).a();
      (new k()).a();
      (new e()).a();
      (new a()).a();
      (new f()).a();
      (new m()).a();
   }

   public static void a(boolean var0) {
      if(!var0) {
         throw new RuntimeException("Asset failed");
      }
   }

   public static void b(boolean var0) {
      if(var0) {
         throw new RuntimeException("Asset failed");
      }
   }

   public static void a(int var0, int var1) {
      if(var0 != var1) {
         throw new RuntimeException("Asset failed (int):" + var0 + "!=" + var1);
      }
   }

   public static void a(float var0, float var1) {
      if(com.corrodinggames.rts.gameFramework.f.c(var0 - var1) > 0.001F) {
         throw new RuntimeException("Asset failed (float):" + var0 + "!=" + var1);
      }
   }

   public static void a(String var0, String var1) {
      if(!var0.equals(var1)) {
         throw new RuntimeException("Asset failed:" + var0 + "!=" + var1);
      }
   }

   public static void b(String var0, String var1) {
      com.corrodinggames.rts.gameFramework.l.e("assertEqualDebug:\'" + var0 + "\' vs \'" + var1 + "\'");
      a(var0, var1);
   }

   public static void c(String var0, String var1) {
      Float var2 = Float.valueOf(Float.parseFloat(var0));
      Float var3 = Float.valueOf(Float.parseFloat(var1));
      a(var2.floatValue(), var3.floatValue());
   }

   public static void a(Object var0, Object var1) {
      if(var0 != var1) {
         throw new RuntimeException("Asset failed:" + var0 + "!=" + var1);
      }
   }
}
