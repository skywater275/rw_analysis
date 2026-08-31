package android.graphics;

import java.util.HashMap;
import java.util.Locale;

public class Color {

   private static final HashMap a = new HashMap();


   public static int a(int var0) {
      return var0 >>> 24;
   }

   public static int b(int var0) {
      return var0 >> 16 & 255;
   }

   public static int c(int var0) {
      return var0 >> 8 & 255;
   }

   public static int d(int var0) {
      return var0 & 255;
   }

   public static int a(int var0, int var1, int var2) {
      return -16777216 | var0 << 16 | var1 << 8 | var2;
   }

   public static int a(int var0, int var1, int var2, int var3) {
      return var0 << 24 | var1 << 16 | var2 << 8 | var3;
   }

   public static int a(String var0) {
      if(var0.charAt(0) == 35) {
         long var3 = Long.parseLong(var0.substring(1), 16);
         if(var0.length() == 7) {
            var3 |= -16777216L;
         } else if(var0.length() != 9) {
            throw new IllegalArgumentException("Unknown color");
         }

         return (int)var3;
      } else {
         Integer var1 = (Integer)a.get(var0.toLowerCase(Locale.ROOT));
         if(var1 != null) {
            return var1.intValue();
         } else {
            throw new IllegalArgumentException("Unknown color");
         }
      }
   }

   static {
      a.put("black", Integer.valueOf(-16777216));
      a.put("darkgray", Integer.valueOf(-12303292));
      a.put("gray", Integer.valueOf(-7829368));
      a.put("lightgray", Integer.valueOf(-3355444));
      a.put("white", Integer.valueOf(-1));
      a.put("red", Integer.valueOf(-65536));
      a.put("green", Integer.valueOf(-16711936));
      a.put("blue", Integer.valueOf(-16776961));
      a.put("yellow", Integer.valueOf(-256));
      a.put("cyan", Integer.valueOf(-16711681));
      a.put("magenta", Integer.valueOf(-65281));
      a.put("aqua", Integer.valueOf(-16711681));
      a.put("fuchsia", Integer.valueOf(-65281));
      a.put("darkgrey", Integer.valueOf(-12303292));
      a.put("grey", Integer.valueOf(-7829368));
      a.put("lightgrey", Integer.valueOf(-3355444));
      a.put("lime", Integer.valueOf(-16711936));
      a.put("maroon", Integer.valueOf(-8388608));
      a.put("navy", Integer.valueOf(-16777088));
      a.put("olive", Integer.valueOf(-8355840));
      a.put("purple", Integer.valueOf(-8388480));
      a.put("silver", Integer.valueOf(-4144960));
      a.put("teal", Integer.valueOf(-16744320));
   }
}
