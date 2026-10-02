package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.a$1;
import java.io.Serializable;

class b implements Serializable {

   private final String a;
   private final StackTraceElement[] b;


   private b(String var1, StackTraceElement[] var2) {
      this.a = var1;
      this.b = var2;
   }

   // $FF: synthetic method
   static String a(b var0) {
      return var0.a;
   }

   // $FF: synthetic method
   static StackTraceElement[] b(b var0) {
      return var0.b;
   }

   // $FF: synthetic method
   b(String var1, StackTraceElement[] var2, a$1 var3) {
      this(var1, var2);
   }
}
