package com.corrodinggames.rts.gameFramework;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.f$a;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

public final class f {

   static final Random a = new Random();
   static final Random b = new Random();
   public static final PointF c = new PointF();
   private static final byte[] j = new byte[1001];
   static final PointF d;
   static final PointF e;
   static final PointF f;
   static final PointF g;
   static final PointF h;
   private static final char[] k;
   private static final float[] l;
   private static final float[] m;
   private static final float[] n;
   private static final float[] o;
   private static final float[] p;
   private static final float[] q;
   private static final float[] r;
   private static final float[] s;
   static int i;
   private static final float[] t;
   private static final float[] u;


   public static final strictfp void a() {
      b.setSeed(0L);
   }

   public static final strictfp int a(com.corrodinggames.rts.game.units.am var0, int var1, int var2) {
      return a(var0, var1, var2, 0);
   }

   public static final strictfp float a(com.corrodinggames.rts.game.units.am var0, float var1, float var2, int var3) {
      return var0 == null?(float)a((int)(var1 * 1000.0F), (int)(var2 * 1000.0F), var3) * 0.001F:(float)a(var0, (int)(var1 * 1000.0F), (int)(var2 * 1000.0F), var3) * 0.001F;
   }

   public static final strictfp float b(com.corrodinggames.rts.game.units.am var0, float var1, float var2, int var3) {
      return (float)a(var0, (int)(var1 * 1000.0F), (int)(var2 * 1000.0F), var3) * 0.001F;
   }

   public static final strictfp int a(w var0, int var1, int var2, int var3) {
      l var4 = l.B();
      if(var1 >= var2) {
         if(var1 > var2) {
            l.b("min>max");
         }

         return var1;
      } else {
         int var5 = var4.bx + 1;
         int var6 = var4.bJ;
         var6 = (int)((long)var6 + var0.eh * 1313L);
         var6 = (int)((float)var6 + var0.eo * 13.0F);
         var6 = (int)((float)var6 + var0.ep * 13.0F);
         var6 = (int)((float)var6 + var0.eo * 130.0F);
         var6 = (int)((float)var6 + var0.ep * 130.0F);
         if(var0 instanceof com.corrodinggames.rts.game.units.am) {
            int var7 = ((com.corrodinggames.rts.game.units.am)var0).bC;
            var6 += var7 * 13131;
            var6 += var7 * var5;
         }

         var6 += var3 * 133 * var2;
         var6 = (int)((long)var6 + (long)var3 * var0.eh + (long)var3);
         var6 += var3 * var5 * 1313;
         var6 += var5 * 13 + var5 % 10;
         var6 %= var2 - var1;
         if(var6 < 0) {
            var6 = -var6;
         }

         var6 += var1;
         return var6;
      }
   }

   public static final strictfp float a(float var0, float var1, int var2) {
      return (float)a((int)(var0 * 100.0F), (int)(var1 * 100.0F), var2) / 100.0F;
   }

   public static final strictfp float b(float var0, float var1, int var2) {
      return (float)a((int)(var0 * 1000.0F), (int)(var1 * 1000.0F), var2) / 1000.0F;
   }

   public static final strictfp int a(int var0, int var1, int var2) {
      l var3 = l.B();
      if(var0 >= var1) {
         if(var0 > var1) {
            l.b("min>max");
         }

         return var0;
      } else {
         int var4 = var1 - var0;
         int var5 = var3.bJ;
         var5 += var2 * 133333333 * var4;
         var5 += var2 * 13131313;
         var5 += var2 * var3.bx * 13131313;
         var5 += var3.bx * 1313131313 + var3.bx % 10;
         var5 %= var4;
         if(var5 < 0) {
            var5 = -var5;
         }

         var5 += var0;
         if(var5 < var0 || var5 > var1) {
            l.b("notRandInt number not in range: " + var5 + " min:" + var0 + " max:" + var1);
         }

         return var5;
      }
   }

   public static strictfp String a(String var0) {
      Calendar var1 = Calendar.getInstance();
      SimpleDateFormat var2 = new SimpleDateFormat(var0);
      return var2.format(var1.getTime());
   }

   public static final strictfp void a(InputStream var0, OutputStream var1) {
      byte[] var2 = new byte[8192];

      int var3;
      while((var3 = var0.read(var2)) != -1) {
         var1.write(var2, 0, var3);
      }

   }

   public static final strictfp String a(InputStream var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      byte[] var2 = new byte[8192];

      try {
         int var3;
         while((var3 = var0.read(var2)) != -1) {
            var1.write(var2, 0, var3);
         }

         var1.close();
         var0.close();
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      return var1.toString();
   }

   public static final strictfp float a(float var0) {
      return (float)StrictMath.sqrt((double)var0);
   }

   public static final strictfp int a(int var0) {
      return var0 <= 1000 && var0 >= 0?j[var0]:StrictMath.round(a((float)var0));
   }

   public static final strictfp float a(float var0, float var1) {
      return var0 > var1?var0 - var1:(var0 < -var1?var0 + var1:0.0F);
   }

   public static final strictfp float a(float var0, float var1, float var2) {
      return var0 > var1 + var2?var0 - var2:(var0 < var1 - var2?var0 + var2:var1);
   }

   public static final strictfp float b(float var0, float var1) {
      return var0 > var1?var1:(var0 < -var1?-var1:var0);
   }

   public static final strictfp float b(float var0, float var1, float var2) {
      return var0 > var2?var2:(var0 < var1?var1:var0);
   }

   public static final strictfp int b(int var0, int var1, int var2) {
      return var0 > var2?var2:(var0 < var1?var1:var0);
   }

   public static final strictfp int b(int var0) {
      return var0 > 255?255:(var0 < 0?0:var0);
   }

   public static final strictfp void a(float var0, float var1, float var2, PointF var3) {
      float var4 = j(var2);
      float var5 = k(var2);
      var3.a -= var0;
      var3.b -= var1;
      float var6 = var3.a * var5 - var3.b * var4;
      float var7 = var3.a * var4 + var3.b * var5;
      var3.a = var6 + var0;
      var3.b = var7 + var1;
   }

   public static final strictfp float a(float var0, float var1, float var2, float var3) {
      return (var0 - var2) * (var0 - var2) + (var1 - var3) * (var1 - var3);
   }

   public static final strictfp float b(float var0, float var1, float var2, float var3) {
      return (float)StrictMath.sqrt((double)((var0 - var2) * (var0 - var2) + (var1 - var3) * (var1 - var3)));
   }

   public static final strictfp int c(float var0, float var1, float var2, float var3) {
      return a((int)((var0 - var2) * (var0 - var2) + (var1 - var3) * (var1 - var3)));
   }

   public static final strictfp int a(int var0, int var1, int var2, int var3) {
      int var4 = var0 - var2;
      int var5 = var1 - var3;
      if(var4 < 0) {
         var4 = -var4;
      }

      if(var5 < 0) {
         var5 = -var5;
      }

      return var4 > var5?var4:var5;
   }

   public static final strictfp float a(float var0, boolean var1) {
      if(var1) {
         while(var0 > 360.0F || var0 < 0.0F) {
            if(var0 > 360.0F) {
               var0 -= 360.0F;
            }

            if(var0 < 0.0F) {
               var0 += 360.0F;
            }
         }
      } else {
         while(var0 > 180.0F || var0 < -180.0F) {
            if(var0 > 180.0F) {
               var0 -= 360.0F;
            }

            if(var0 < -180.0F) {
               var0 += 360.0F;
            }
         }
      }

      return var0;
   }

   public static final strictfp float c(float var0, float var1, float var2) {
      var0 %= 360.0F;
      var1 %= 360.0F;
      float var3 = var1 - var0;
      if(var3 > 180.0F) {
         var3 -= 360.0F;
      }

      if(var3 < -180.0F) {
         var3 += 360.0F;
      }

      return var3 > var2?var2:(var3 < -var2?-var2:var3);
   }

   public static final strictfp float d(float var0, float var1, float var2, float var3) {
      return b(i(var3 - var1, var2 - var0));
   }

   public static final strictfp boolean a(PointF var0, PointF var1, PointF var2, PointF var3) {
      float var4 = (var3.b - var2.b) * (var1.a - var0.a) - (var3.a - var2.a) * (var1.b - var0.b);
      float var5 = (var3.a - var2.a) * (var0.b - var2.b) - (var3.b - var2.b) * (var0.a - var2.a);
      float var6 = (var1.a - var0.a) * (var0.b - var2.b) - (var1.b - var0.b) * (var0.a - var2.a);
      if(var4 == 0.0F) {
         return var5 == 0.0F && var6 == 0.0F?false:false;
      } else {
         float var7 = var5 / var4;
         float var8 = var6 / var4;
         return var7 >= 0.0F && var7 <= 1.0F && var8 >= 0.0F && var8 <= 1.0F;
      }
   }

   public static final strictfp float c(float var0, float var1) {
      return a.nextFloat() * (var1 - var0) + var0;
   }

   public static final strictfp float d(float var0, float var1) {
      return a.nextFloat() * (var1 - var0) + var0;
   }

   public static final strictfp int c(int var0) {
      return var0 == 0?0:a.nextInt(var0);
   }

   public static strictfp int a(int var0, int var1) {
      int var2;
      if(var1 == var0) {
         var2 = 0;
      } else {
         var2 = a.nextInt(var1 - var0 + 1);
      }

      return var0 + var2;
   }

   public static final strictfp void a(Rect var0) {
      int var1;
      if(var0.c < var0.a) {
         var1 = var0.c;
         var0.c = var0.a;
         var0.a = var1;
      }

      if(var0.d < var0.b) {
         var1 = var0.d;
         var0.d = var0.b;
         var0.b = var1;
      }

   }

   public static final strictfp void a(RectF var0) {
      float var1;
      if(var0.c < var0.a) {
         var1 = var0.c;
         var0.c = var0.a;
         var0.a = var1;
      }

      if(var0.d < var0.b) {
         var1 = var0.d;
         var0.d = var0.b;
         var0.b = var1;
      }

   }

   public static final strictfp PointF d(float var0, float var1, float var2) {
      h.a(var0, var1 - var2);
      return h;
   }

   public static final strictfp float b(float var0) {
      return var0 * 57.29578F;
   }

   public static final strictfp float e(float var0, float var1) {
      return (float)StrictMath.pow((double)var0, (double)var1);
   }

   public static final strictfp double a(double var0) {
      return var0 < 0.0D?-var0:var0;
   }

   public static final strictfp float c(float var0) {
      return var0 < 0.0F?-var0:var0;
   }

   public static final strictfp int d(int var0) {
      return var0 < 0?-var0:var0;
   }

   public static final strictfp int b(int var0, int var1) {
      return var0 > var1?var0:var1;
   }

   public static final strictfp int c(int var0, int var1) {
      return var0 < var1?var0:var1;
   }

   public static final strictfp float f(float var0, float var1) {
      return var0 > var1?var0:var1;
   }

   public static final strictfp float g(float var0, float var1) {
      return var0 < var1?var0:var1;
   }

   public static final strictfp boolean h(float var0, float var1) {
      return c(var0 - var1) < 0.05F;
   }

   public static final strictfp double a(double var0, double var2) {
      return var0 < var2?var0:var2;
   }

   public static strictfp boolean e(float var0, float var1, float var2) {
      return c(c(var0) - c(var1)) < var2;
   }

   public static strictfp float d(float var0) {
      return (float)((int)(var0 + 0.5F));
   }

   public static strictfp float e(float var0) {
      return (float)StrictMath.ceil((double)var0);
   }

   public static final strictfp int f(float var0) {
      return var0 > 0.0F?(int)var0:(var0 < 0.0F?(int)var0 - 1:0);
   }

   public static strictfp void a(RectF var0, float var1) {
      var0.a -= var1;
      var0.b -= var1;
      var0.c += var1;
      var0.d += var1;
   }

   public static strictfp void a(Rect var0, float var1) {
      var0.a = (int)((float)var0.a - var1);
      var0.b = (int)((float)var0.b - var1);
      var0.c = (int)((float)var0.c + var1);
      var0.d = (int)((float)var0.d + var1);
   }

   public static strictfp void b(Rect var0, float var1) {
      var0.a = (int)((float)var0.a - var1);
      var0.b = (int)((float)var0.b - var1);
      var0.c = (int)((float)var0.c + var1 * 2.0F);
      var0.d = (int)((float)var0.d + var1 * 2.0F);
   }

   public static strictfp String e(int var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 0; var2 < var0; ++var2) {
         char var3 = k[a.nextInt(k.length)];
         var1.append(var3);
      }

      return var1.toString();
   }

   public static strictfp String b() {
      return UUID.randomUUID().toString();
   }

   public static strictfp String a(boolean var0) {
      return var0?"true":"false";
   }

   public static strictfp String b(double var0) {
      return var0 == (double)((int)var0)?"" + (int)var0:"" + var0;
   }

   public static strictfp String g(float var0) {
      return a(var0, 2);
   }

   public static strictfp String c(double var0) {
      return var0 == (double)((int)var0)?"" + (int)var0:b(var0, 2);
   }

   public static strictfp String a(float var0, int var1) {
      return var0 == (float)((int)var0)?"" + (int)var0:b((double)var0, var1);
   }

   public static strictfp String a(double var0, int var2) {
      return var0 == (double)((int)var0)?"" + (int)var0:b(var0, var2);
   }

   public static strictfp String h(float var0) {
      return (int)(var0 * 10.0F) == (int)var0 * 10?"" + (int)var0 + "s":b((double)var0, 1) + "s";
   }

   public static strictfp String b(double var0, int var2) {
      String var3 = "" + var0;
      int var4 = var3.indexOf(".");
      if(var4 == -1) {
         return var3;
      } else if(var3.indexOf("E") != -1) {
         return String.format("%." + var2 + "f", new Object[]{Double.valueOf(var0)});
      } else {
         int var5 = var4 + var2 + 1;
         if(var5 > var3.length()) {
            var5 = var3.length();
         }

         var3 = var3.substring(0, var5);
         return var3;
      }
   }

   public static strictfp String a(String var0, int var1) {
      return var0 == null?null:(var0.length() < var1?var0:var0.substring(0, Math.min(var0.length(), var1)));
   }

   public static strictfp String b(String var0, int var1) {
      if(var0 == null) {
         return null;
      } else if(var0.length() < var1) {
         return var0;
      } else {
         var1 -= 3;
         if(var1 < 1) {
            var1 = 1;
         }

         return var0.substring(0, Math.min(var0.length(), var1)) + "...";
      }
   }

   public static strictfp String b(String var0) {
      byte[] var1;
      try {
         var1 = MessageDigest.getInstance("MD5").digest(var0.getBytes("UTF-8"));
      } catch (NoSuchAlgorithmException var8) {
         throw new RuntimeException("MD5 should be supported", var8);
      } catch (UnsupportedEncodingException var9) {
         throw new RuntimeException("UTF-8 should be supported", var9);
      }

      StringBuilder var2 = new StringBuilder(var1.length * 2);
      byte[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         byte var6 = var3[var5];
         int var7 = var6 & 255;
         if(var7 < 16) {
            var2.append('0');
         }

         var2.append(Integer.toHexString(var7));
      }

      return var2.toString();
   }

   public static strictfp String c(String var0) {
      String var1 = a(f(var0));
      var1 = a(var1, (int)14);
      return var1;
   }

   public static strictfp String d(String var0) {
      String var1 = a(f(var0));
      var1 = a(var1, (int)4);
      return var1;
   }

   public static strictfp String c(String var0, int var1) {
      String var2 = a(f(var0));

      for(int var3 = 0; var3 < var1; ++var3) {
         var2 = a(f(var2));
      }

      return var2;
   }

   public static strictfp String e(String var0) {
      return a(f(var0));
   }

   static strictfp byte[] f(String var0) {
      MessageDigest var1 = null;

      try {
         var1 = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException var3) {
         throw new RuntimeException(var3);
      }

      var1.reset();
      return var1.digest(var0.getBytes());
   }

   static strictfp String a(byte[] var0) {
      return String.format("%0" + var0.length * 2 + "X", new Object[]{new BigInteger(1, var0)});
   }

   public static strictfp String b(byte[] var0) {
      return a(c(var0));
   }

   static strictfp byte[] c(byte[] var0) {
      MessageDigest var1 = null;

      try {
         var1 = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException var3) {
         throw new RuntimeException(var3);
      }

      var1.reset();
      return var1.digest(var0);
   }

   public static strictfp int c() {
      int var0 = 1;

      try {
         File var1 = new File("/sys/devices/system/cpu/");
         if(var1.exists()) {
            File[] var2 = var1.listFiles(new f$a());
            var0 = var2.length;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         var0 = 1;
      }

      if(var0 == 1) {
         int var4 = Runtime.getRuntime().availableProcessors();
         if(var4 > 1) {
            var0 = var4;
         }
      }

      return var0;
   }

   public static strictfp void a(byte[] var0, byte[] var1) {
      System.arraycopy(var0, 0, var1, 0, var0.length);
   }

   public static strictfp float f(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public static strictfp float i(float var0) {
      float var1 = var0 - 1.0F;
      float var2 = var0 * 2.0F;
      return var2 < 1.0F?var0 * var2:1.0F - var1 * var1 * 2.0F;
   }

   public static strictfp int a(int var0, int var1, float var2) {
      int var3 = Color.a(var0);
      int var4 = Color.b(var0);
      int var5 = Color.c(var0);
      int var6 = Color.d(var0);
      int var7 = Color.a(var1);
      int var8 = Color.b(var1);
      int var9 = Color.c(var1);
      int var10 = Color.d(var1);
      return Color.a((int)f((float)var3, (float)var7, var2), (int)f((float)var4, (float)var8, var2), (int)f((float)var5, (float)var9, var2), (int)f((float)var6, (float)var10, var2));
   }

   public static strictfp String d(String var0, int var1) {
      String var2 = "";

      for(int var3 = 0; var3 <= var1; ++var3) {
         var2 = var2 + var0;
      }

      return var2;
   }

   public static strictfp String e(String var0, int var1) {
      for(int var2 = var0.length(); var2 < var1; ++var2) {
         var0 = var0 + " ";
      }

      return var0;
   }

   public static strictfp String a(String var0, int var1, String var2) {
      for(int var3 = var0.length(); var3 < var1; ++var3) {
         var0 = var2 + var0;
      }

      return var0;
   }

   public static strictfp String f(String var0, int var1) {
      return String.format("%1$-" + var1 + "s", new Object[]{var0});
   }

   public static strictfp String a(Class var0, int var1) {
      try {
         Field[] var2 = var0.getFields();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Field var5 = var2[var4];
            int var6 = var5.getInt((Object)null);
            if(var6 == var1) {
               return var5.getName();
            }
         }

         return null;
      } catch (IllegalArgumentException var7) {
         throw new RuntimeException(var7);
      } catch (IllegalAccessException var8) {
         throw new RuntimeException(var8);
      }
   }

   public static strictfp String f(int var0) {
      String var1 = a(R$drawable.class, var0);
      if(var1 != null) {
         return com.corrodinggames.rts.gameFramework.e.a.a("res/drawable", var1);
      } else {
         var1 = a(R$raw.class, var0);
         return var1 != null?com.corrodinggames.rts.gameFramework.e.a.a("res/raw", var1):null;
      }
   }

   public static final strictfp String g(int var0) {
      if(-1000 < var0 && var0 < 1000) {
         return var0 + " B";
      } else {
         String var1 = "kMGTPE";

         int var2;
         for(var2 = 0; var2 < var1.length() && (var0 <= -999950 || var0 >= 999950); ++var2) {
            var0 /= 1000;
         }

         return String.format("%.1f %cB", new Object[]{Double.valueOf((double)var0 / 1000.0D), Character.valueOf(var1.charAt(var2))});
      }
   }

   public static final strictfp String h(int var0) {
      String var1 = String.format("#%06X", new Object[]{Integer.valueOf(16777215 & var0)});
      return var1;
   }

   public static final strictfp String g(String var0) {
      if(var0 == null) {
         return null;
      } else {
         File var1 = new File(var0);
         String var2 = var1.getName();
         var2 = var2.replaceFirst("[.][^.]+$", "");
         return var2;
      }
   }

   public static final strictfp String h(String var0) {
      if(var0.contains("\\")) {
         var0 = var0.replace('\\', '/');
      }

      File var1 = new File(var0);
      return var1.getParent();
   }

   public static final strictfp boolean a(Rect var0, RectF var1) {
      return (float)var0.a < var1.c && var1.a < (float)var0.c && (float)var0.b < var1.d && var1.b < (float)var0.d;
   }

   public static final strictfp boolean a(RectF var0, RectF var1) {
      return var0.a < var1.c && var1.a < var0.c && var0.b < var1.d && var1.b < var0.d;
   }

   public static final strictfp int b(int var0, int var1, int var2, int var3) {
      return var0 << 24 | var1 << 16 | var2 << 8 | var3;
   }

   public static final strictfp long a(long var0, long var2) {
      return (var2 - var0) / 1000000L;
   }

   public static final strictfp int a(String var0, char var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < var0.length(); ++var3) {
         if(var0.charAt(var3) == var1) {
            ++var2;
         }
      }

      return var2;
   }

   public static final strictfp String i(String var0) {
      var0 = var0.replace("&", "&amp;");
      var0 = var0.replace("<", "&lt;");
      var0 = var0.replace(">", "&gt;");
      var0 = var0.replace("${", "$ {");
      return var0;
   }

   public static strictfp String a(File var0) {
      try {
         FileInputStream var1 = new FileInputStream(var0);

         try {
            byte[] var2 = new byte[(int)var0.length()];
            int var3 = var2.length;
            int var4 = 0;

            while(true) {
               if(var4 < var3) {
                  int var5 = var1.read(var2, var4, var3 - var4);
                  if(var5 != -1) {
                     var4 += var5;
                     continue;
                  }
               }

               String var13 = new String(var2, Charset.forName("UTF-8"));
               return var13;
            }
         } finally {
            var1.close();
         }
      } catch (FileNotFoundException var11) {
         throw new RuntimeException(var11);
      } catch (IOException var12) {
         throw new RuntimeException(var12);
      }
   }

   public static strictfp String b(InputStream var0) {
      try {
         try {
            byte[] var1 = new byte[var0.available()];
            int var2 = var1.length;
            int var3 = 0;

            while(true) {
               if(var3 < var2) {
                  int var4 = var0.read(var1, var3, var2 - var3);
                  if(var4 != -1) {
                     var3 += var4;
                     continue;
                  }
               }

               String var12 = new String(var1, Charset.forName("UTF-8"));
               return var12;
            }
         } finally {
            var0.close();
         }
      } catch (FileNotFoundException var10) {
         throw new RuntimeException(var10);
      } catch (IOException var11) {
         throw new RuntimeException(var11);
      }
   }

   public static final strictfp String a(Exception var0) {
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter(var1);
      var0.printStackTrace(var2);
      return var1.toString();
   }

   public static final strictfp String b(Exception var0) {
      return a(var0, false);
   }

   public static final strictfp String a(Exception var0, boolean var1) {
      String var2 = var0.getMessage();
      if(var2 == null) {
         var2 = var0.getClass().getName();
      } else {
         boolean var3 = false;
         if(var0 instanceof NumberFormatException) {
            var3 = true;
         }

         if(var0 instanceof ArrayIndexOutOfBoundsException) {
            var3 = true;
         }

         if(var3 || var1) {
            var2 = var0.getClass().getName() + " - " + var2;
         }
      }

      if(var2 != null && var2.startsWith("java.io.IOException")) {
         var2 = var2.substring("java.io.".length());
      }

      Throwable var4;
      Object var6;
      for(var6 = var0; var6 != null; var6 = var4) {
         var4 = ((Throwable)var6).getCause();
         if(var4 == null || var4 == var0 || var4 == var6) {
            break;
         }
      }

      var4 = null;
      if(var6 != null && var6 != var0) {
         String var7 = ((Throwable)var6).getMessage();
         if(var7 == null) {
            var7 = var6.getClass().getName();
         }

         boolean var5 = true;
         if(var7.equals(var2)) {
            var5 = false;
         }

         if(var2 != null && var2.contains(var7)) {
            var5 = false;
         }

         if(var5) {
            var2 = var2 + " caused by (" + var7 + ")";
         }
      }

      return var2;
   }

   public static strictfp String j(String var0) {
      return var0.endsWith("\n")?var0.substring(0, var0.length() - 1):var0;
   }

   public static strictfp String a(String var0, String var1) {
      return var0.endsWith(var1)?var0.substring(0, var0.length() - var1.length()):var0;
   }

   public static strictfp String k(String var0) {
      File var1 = new File(var0);
      return var1.getName();
   }

   public static strictfp String b(String var0, String var1) {
      if(var1.startsWith("/") || var1.startsWith("\\")) {
         var1 = var1.substring(1);
      }

      if(var0.endsWith("/")) {
         return var0 + var1;
      } else {
         if(var0.endsWith("\\")) {
            var0 = var0.substring(0, var0.length() - 1);
         }

         return var0 + "/" + var1;
      }
   }

   public static strictfp String a(CharSequence var0, Iterable var1) {
      StringBuilder var2 = new StringBuilder();
      boolean var3 = true;

      String var5;
      for(Iterator var4 = var1.iterator(); var4.hasNext(); var2.append(var5)) {
         var5 = (String)var4.next();
         if(var3) {
            var3 = false;
         } else {
            var2.append(var0);
         }
      }

      return var2.toString();
   }

   public static strictfp Integer l(String var0) {
      try {
         return Integer.valueOf(var0);
      } catch (NumberFormatException var2) {
         l.e(var2.toString());
         return null;
      }
   }

   public static strictfp Long m(String var0) {
      try {
         return Long.valueOf(var0);
      } catch (NumberFormatException var2) {
         l.e(var2.toString());
         return null;
      }
   }

   public static strictfp boolean n(String var0) {
      int var1 = var0.length();

      int var3;
      for(int var2 = 0; var2 < var1; var2 += Character.charCount(var3)) {
         var3 = var0.codePointAt(var2);
         if(var3 > 128) {
            return true;
         }
      }

      return false;
   }

   public static strictfp String a(long var0) {
      int[] var3 = b(var0);
      String var2;
      if(var3[0] == 0) {
         var2 = a("" + var3[1], 2, "0") + ":" + a("" + var3[2], 2, "0");
      } else {
         var2 = a("" + var3[0], 2, "0") + ":" + a("" + var3[1], 2, "0") + ":" + a("" + var3[2], 2, "0");
      }

      return var2;
   }

   public static strictfp int[] b(long var0) {
      int var2 = (int)var0 / 3600;
      int var3 = (int)var0 - var2 * 3600;
      int var4 = var3 / 60;
      var3 -= var4 * 60;
      int[] var6 = new int[]{var2, var4, var3};
      return var6;
   }

   public static final strictfp float i(float var0, float var1) {
      try {
         return var1 >= 0.0F?(var0 >= 0.0F?(var1 >= var0?l[(int)((double)(1024.0F * var0 / var1) + 0.5D)]:m[(int)((double)(1024.0F * var1 / var0) + 0.5D)]):(var1 >= -var0?n[(int)((double)(-1024.0F * var0 / var1) + 0.5D)]:o[(int)((double)(-1024.0F * var1 / var0) + 0.5D)])):(var0 >= 0.0F?(-var1 >= var0?p[(int)((double)(-1024.0F * var0 / var1) + 0.5D)]:q[(int)((double)(-1024.0F * var1 / var0) + 0.5D)]):(var1 <= var0?r[(int)((double)(1024.0F * var0 / var1) + 0.5D)]:s[(int)((double)(1024.0F * var1 / var0) + 0.5D)]));
      } catch (ArrayIndexOutOfBoundsException var3) {
         if(i < 100) {
            l.e("atan2 slow fallback for y:" + var0 + " x:" + var1);
            ++i;
         }

         return (float)StrictMath.atan2((double)var0, (double)var1);
      }
   }

   public static final strictfp float j(float var0) {
      return t[(int)(var0 * 22.755556F) & 8191];
   }

   public static final strictfp float k(float var0) {
      return u[(int)(var0 * 22.755556F) & 8191];
   }

   public static strictfp String o(String var0) {
      if(var0.contains("&")) {
         var0 = var0.replace("&lt;", "<");
         var0 = var0.replace("&gt;", ">");
         var0 = var0.replace("&apos;", "\'");
         var0 = var0.replace("&quot;", "\"");
         var0 = var0.replace("&amp;", "&");
      }

      return var0;
   }

   public static strictfp String p(String var0) {
      if(var0 != null && var0.length() >= 2) {
         char var1 = var0.charAt(0);
         if(var1 != 34 && var1 != 39) {
            return null;
         } else {
            char var2 = var0.charAt(var0.length() - 1);
            if(var2 != var1) {
               return null;
            } else {
               boolean var3 = false;
               StringBuilder var4 = new StringBuilder();

               for(int var5 = 1; var5 < var0.length() - 1; ++var5) {
                  char var6 = var0.charAt(var5);
                  boolean var7 = var3;
                  var3 = false;
                  if(!var7) {
                     if(var6 == 92) {
                        var3 = true;
                        continue;
                     }

                     if(var6 == var1) {
                        return null;
                     }
                  }

                  var4.append(var6);
               }

               return var4.toString();
            }
         }
      } else {
         return null;
      }
   }

   public static strictfp String q(String var0) {
      boolean var1 = false;
      StringBuilder var2 = new StringBuilder();
      char[] var3 = var0.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char var6 = var3[var5];
         boolean var7 = var1;
         var1 = false;
         if(!var7 && var6 == 92) {
            var1 = true;
         } else {
            var2.append(var6);
         }
      }

      return var2.toString();
   }

   public static final strictfp String a(String var0, String var1, String var2) {
      return !c(var0, var1)?var0:var0.replace(var1, var2);
   }

   public static final strictfp boolean c(String var0, String var1) {
      return var0.indexOf(var1) > -1;
   }

   public static final strictfp boolean b(String var0, char var1) {
      return var0.indexOf(var1) > -1;
   }

   public static strictfp String[] c(String var0, char var1) {
      if(var0.length() == 0) {
         return new String[]{""};
      } else {
         int var2 = 0;

         int var3;
         int var4;
         for(var3 = 0; (var4 = var0.indexOf(var1, var3)) != -1; var3 = var4 + 1) {
            ++var2;
         }

         if(var2 == 0) {
            return new String[]{var0};
         } else {
            int var5 = var0.length();
            if(var3 == var5) {
               if(var2 == var5) {
                  return new String[0];
               }

               do {
                  --var3;
               } while(var0.charAt(var3 - 1) == var1);

               var2 -= var0.length() - var3;
               var5 = var3;
            }

            String[] var6 = new String[var2 + 1];
            var3 = 0;

            for(int var7 = 0; var7 != var2; ++var7) {
               var4 = var0.indexOf(var1, var3);
               var6[var7] = var0.substring(var3, var4);
               var3 = var4 + 1;
            }

            var6[var2] = var0.substring(var3, var5);
            return var6;
         }
      }
   }

   public static strictfp boolean r(String var0) {
      for(int var1 = 0; var1 < var0.length(); ++var1) {
         char var2 = var0.charAt(var1);
         if(!Character.isDigit(var2) && var2 != 46 && (var2 != 45 || var1 != 0)) {
            return false;
         }
      }

      return true;
   }

   public static strictfp boolean s(String var0) {
      boolean var1 = false;

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         if(!Character.isDigit(var3) && (var3 != 45 || var2 != 0)) {
            if(var1 || var3 != 46) {
               return false;
            }

            var1 = true;
         }
      }

      return true;
   }

   public static final strictfp boolean j(float var0, float var1) {
      return c(var0 - var1) < 1.0E-4F;
   }

   public static final strictfp boolean k(float var0, float var1) {
      return c(var0 - var1) < 1.0E-7F;
   }

   public static strictfp boolean b(double var0, double var2) {
      return a(var0 - var2) < 1.0000000116860974E-7D;
   }

   public static final strictfp boolean d(String var0, String var1) {
      return var0 == null?var1 == null:var0.equals(var1);
   }

   public static final strictfp boolean a(Integer var0, Integer var1) {
      return var0 == null?var1 == null:var0.equals(var1);
   }

   static {
      int var0;
      for(var0 = 0; var0 < j.length; ++var0) {
         j[var0] = (byte)StrictMath.round(a((float)var0));
      }

      d = new PointF();
      e = new PointF();
      f = new PointF();
      g = new PointF();
      h = new PointF();
      k = new char[36];

      for(var0 = 0; var0 < 10; ++var0) {
         k[var0] = (char)(48 + var0);
      }

      for(var0 = 10; var0 < 36; ++var0) {
         k[var0] = (char)(97 + var0 - 10);
      }

      l = new float[1025];
      m = new float[1025];
      n = new float[1025];
      o = new float[1025];
      p = new float[1025];
      q = new float[1025];
      r = new float[1025];
      s = new float[1025];

      for(var0 = 0; var0 <= 1024; ++var0) {
         float var1 = (float)var0 / 1024.0F;
         l[var0] = (float)(StrictMath.atan((double)var1) * 3.1415927410125732D / 3.141592653589793D);
         m[var0] = 1.5707964F - l[var0];
         n[var0] = -l[var0];
         o[var0] = l[var0] - 1.5707964F;
         p[var0] = 3.1415927F - l[var0];
         q[var0] = l[var0] + 1.5707964F;
         r[var0] = l[var0] - 3.1415927F;
         s[var0] = -1.5707964F - l[var0];
      }

      i = 0;
      t = new float[8192];
      u = new float[8192];

      for(var0 = 0; var0 < 8192; ++var0) {
         t[var0] = (float)StrictMath.sin((double)(((float)var0 + 0.5F) / 8192.0F * 6.2831855F));
         u[var0] = (float)StrictMath.cos((double)(((float)var0 + 0.5F) / 8192.0F * 6.2831855F));
      }

   }
}
