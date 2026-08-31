package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.ag;

public class ae {

   static Object a = new Object();
   static ag b = new ag();
   static af c;


   public static boolean a() {
      return com.corrodinggames.rts.gameFramework.l.at();
   }

   public static af a(String var0) {
      if(ag.i(var0)) {
         return b;
      } else if(a() && com.corrodinggames.rts.gameFramework.utility.a.a.l(var0)) {
         if(c == null) {
            Object var1 = a;
            synchronized(a) {
               if(c == null) {
                  c = new com.corrodinggames.rts.gameFramework.utility.a.a();
               }
            }
         }

         return c;
      } else {
         return null;
      }
   }

   public static af b(String var0) {
      if(a() && com.corrodinggames.rts.gameFramework.utility.a.a.l(var0)) {
         if(c == null) {
            Object var1 = a;
            synchronized(a) {
               if(c == null) {
                  c = new com.corrodinggames.rts.gameFramework.utility.a.a();
               }
            }
         }

         return c;
      } else {
         return null;
      }
   }

   public static void c(String var0) {
      if(b != null && ag.i(var0)) {
         b.k(var0);
      }

   }

   public static void b() {
      if(b != null) {
         b.a();
      }

      if(c != null) {
         c.a();
      }

   }

}
