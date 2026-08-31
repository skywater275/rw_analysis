package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.h;
import java.util.Locale;

public final class g {

   protected static h a;


   public static strictfp h a() {
      if(a == null) {
         String var0 = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
         if(var0.indexOf("mac") < 0 && var0.indexOf("darwin") < 0) {
            if(var0.indexOf("win") >= 0) {
               a = h.a;
            } else if(var0.indexOf("nux") >= 0) {
               a = h.c;
            } else {
               a = h.d;
            }
         } else {
            a = h.b;
         }
      }

      return a;
   }
}
