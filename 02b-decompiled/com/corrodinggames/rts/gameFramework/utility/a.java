package com.corrodinggames.rts.gameFramework.utility;

import android.os.Looper;
import com.corrodinggames.rts.gameFramework.utility.a$1;
import com.corrodinggames.rts.gameFramework.utility.b;
import com.corrodinggames.rts.gameFramework.utility.c;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;

public class a extends Error {

   private a(c var1) {
      super("Application Not Responding", var1);
   }

   public Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }

   static a a(String var0, boolean var1) {
      Thread var2 = Looper.b().e();
      TreeMap var3 = new TreeMap(new a$1(var2));
      Iterator var4 = Thread.getAllStackTraces().entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         if(var5.getKey() == var2 || ((Thread)var5.getKey()).getName().startsWith(var0) && (var1 || ((StackTraceElement[])var5.getValue()).length > 0)) {
            var3.put(var5.getKey(), var5.getValue());
         }
      }

      if(!var3.containsKey(var2)) {
         var3.put(var2, var2.getStackTrace());
      }

      c var7 = null;

      c var10000;
      for(Iterator var8 = var3.entrySet().iterator(); var8.hasNext(); var7 = var10000) {
         Entry var6 = (Entry)var8.next();
         var10000 = new c;
         b var10002 = new b(a((Thread)var6.getKey()), (StackTraceElement[])var6.getValue(), (a$1)null);
         var10002.getClass();
         var10000.<init>(var10002, var7, (a$1)null);
      }

      return new a(var7);
   }

   static a a() {
      Thread var0 = Looper.b().e();
      StackTraceElement[] var1 = var0.getStackTrace();
      a var10000 = new a;
      c var10002 = new c;
      b var10004 = new b(a(var0), var1, (a$1)null);
      var10004.getClass();
      var10002.<init>(var10004, (c)null, (a$1)null);
      var10000.<init>(var10002);
      return var10000;
   }

   private static String a(Thread var0) {
      return var0.getName() + " (state = " + var0.getState() + ")";
   }
}
