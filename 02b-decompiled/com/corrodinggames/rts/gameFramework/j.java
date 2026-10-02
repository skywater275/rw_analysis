package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.k;
import com.corrodinggames.rts.gameFramework.l;
import java.io.File;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class j {

   static ConcurrentHashMap a = new ConcurrentHashMap();
   static k b;


   public static long a(String var0, boolean var1) {
      Long var2 = (Long)a.get(var0);
      if(var2 != null) {
         return var2.longValue();
      } else {
         var2 = Long.valueOf(a(var0));
         if(!var1) {
            a.put(var0, var2);
            if(b == null) {
               ;
            }
         }

         return var2.longValue();
      }
   }

   private static long a(String var0) {
      File var1 = new File(var0);
      return var1.lastModified();
   }

   public static synchronized void a() {
      a(l.B().bQ.liveReloading);
   }

   public static synchronized void a(boolean var0) {
      if(l.av()) {
         if(var0) {
            if(b != null) {
               l.e("FileChangeEngine: Already running");
               return;
            }

            l.e("FileChangeEngine: Starting");
            b = new k();
            b.start();
         } else if(b != null) {
            b.a = false;
            b = null;
         }

      }
   }

   public static void b() {
      int var0 = 0;
      Enumeration var1 = a.keys();

      while(var1.hasMoreElements()) {
         String var2 = (String)var1.nextElement();
         long var3 = a(var2);
         Long var5 = (Long)a.get(var2);
         if(var5 == null) {
            l.e("FileChangeEngine: old lastModified null for " + var2);
         } else if(var5.longValue() != var3) {
            l.e("FileChangeEngine: Detected change to:" + var2 + " now " + var3);
         }

         a.put(var2, Long.valueOf(var3));
         ++var0;
         if(var0 > 50) {
            var0 = 0;

            try {
               Thread.sleep(2L);
            } catch (InterruptedException var7) {
               ;
            }
         }
      }

   }

}
