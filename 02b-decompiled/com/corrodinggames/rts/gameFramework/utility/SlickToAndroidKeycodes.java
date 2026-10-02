package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$AndroidCodes;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$GdxCodes;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$MissingKey;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$SlickCodes;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class SlickToAndroidKeycodes {

   static HashMap a;
   static HashMap b;
   static HashMap c;
   static HashMap d = a(SlickToAndroidKeycodes$SlickCodes.class);
   static HashMap e = a(SlickToAndroidKeycodes$AndroidCodes.class);
   static HashMap f = a(SlickToAndroidKeycodes$GdxCodes.class);
   static HashMap g;


   static HashMap a(Class var0) {
      HashMap var1 = new HashMap();
      Field[] var2 = var0.getFields();
      Field[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Field var6 = var3[var5];
         String var7 = var6.getName();
         var7 = var7.replace("KEYCODE_", "");
         var7 = var7.replace("KEY_", "");
         var7 = var7.replace("NUMPAD_", "NUMPAD");
         var7 = var7.replace("NUM_", "NUMPAD");

         int var8;
         try {
            var8 = var6.getInt((Object)null);
         } catch (IllegalArgumentException var10) {
            throw new RuntimeException(var10);
         } catch (IllegalAccessException var11) {
            throw new RuntimeException(var11);
         }

         var1.put(var7, Integer.valueOf(var8));
      }

      return var1;
   }

   public static void a() {
      a = a("slickToAndroidCodes", d, e);
      g = new HashMap();
      ArrayList var0 = new ArrayList();
      Iterator var1 = d.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();
         int var3 = ((Integer)d.get(var2)).intValue();
         Integer var4 = (Integer)e.get(var2);
         if(var4 == null) {
            var0.add(var2);
         }

         if(a.get(Integer.valueOf(var3)) == null) {
            ;
         }

         String var5 = var2.toLowerCase(Locale.ENGLISH);
         if(var4 != null) {
            g.put(var4, var5);
         }
      }

   }

   public static void b() {
      b = a("gdxToAndroidCodes", f, e);
   }

   public static void c() {
      c = a("gdxToSlickCodes", f, d);
   }

   public static HashMap a(String var0, HashMap var1, HashMap var2) {
      HashMap var3 = new HashMap();
      ArrayList var4 = new ArrayList();
      Iterator var5 = var1.keySet().iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         int var7 = ((Integer)var1.get(var6)).intValue();
         Integer var8 = (Integer)var2.get(var6);
         if(var8 == null) {
            var4.add(var6);
         }

         if(var3.get(Integer.valueOf(var7)) == null) {
            var3.put(Integer.valueOf(var7), var8);
         }

         String var9 = var6.toLowerCase(Locale.ENGLISH);
         if(var8 != null) {
            ;
         }
      }

      if(var4.size() != 0) {
         String var10 = "";
         Iterator var11 = var4.iterator();

         while(var11.hasNext()) {
            String var12 = (String)var11.next();
            int var13 = ((Integer)var1.get(var12)).intValue();
            if(var3.get(Integer.valueOf(var13)) == null) {
               var10 = var10 + var12 + ", ";
            }
         }

         com.corrodinggames.rts.gameFramework.l.e(var0 + ": Could not find keycode for: " + var10);
      }

      return var3;
   }

   public static int a(String var0) {
      var0 = var0.toUpperCase();
      Integer var1 = (Integer)e.get(var0);
      if(var1 == null) {
         throw new SlickToAndroidKeycodes$MissingKey("Could not find key:" + var0);
      } else {
         return var1.intValue();
      }
   }

   public static String a(int var0) {
      String var1 = (String)g.get(Integer.valueOf(var0));
      return var1 == null?"unknown":var1;
   }

   public static int b(int var0) {
      Integer var1 = (Integer)a.get(Integer.valueOf(var0));
      return var1 == null?0:var1.intValue();
   }

   public static Integer c(int var0) {
      Object var1 = null;
      return var0 == 14?Integer.valueOf(69):(var0 == 211?Integer.valueOf(99):(var0 == 28?Integer.valueOf(72):(var0 == 203?Integer.valueOf(90):(var0 == 205?Integer.valueOf(92):(var0 == 200?Integer.valueOf(91):(var0 == 208?Integer.valueOf(93):(var0 == 15?Integer.valueOf(70):(var0 == 42?Integer.valueOf(138):(var0 == 54?Integer.valueOf(139):(var0 == 29?Integer.valueOf(140):(var0 == 157?Integer.valueOf(141):null)))))))))));
   }

   static {
      a();
      b();
      c();
   }
}
