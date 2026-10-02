package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.ag;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ah {

   String a = "";
   String b;
   ZipFile c;
   String[] d;
   boolean e;


   public ah(String var1, String var2) {
      this.b = var1;
      com.corrodinggames.rts.gameFramework.l.g("Opening new zip at: " + var2);
      af var3 = ae.b(var2);
      if(var3 != null) {
         com.corrodinggames.rts.gameFramework.l.e("Temp file needed for zip with SAF interface");
         if(!com.corrodinggames.rts.gameFramework.l.at()) {
            throw new IOException("Failed to open source zip with mapper: " + var2);
         }

         long var4 = br.a();
         j var6 = var3.b(var2, true);
         if(var6 == null) {
            throw new IOException("Failed to open file of zip: " + var2);
         }

         this.c = a((InputStream)var6, (Charset)null);
         double var7 = (double)br.a(var4);
         com.corrodinggames.rts.gameFramework.l.e("Streamed zip open took:" + br.a(var7));
      } else {
         this.c = new ZipFile(var2);
      }

      try {
         this.b();
      } catch (IllegalArgumentException var12) {
         ag.h("Failed to open source zip with unicode encoding, attempting with ISO-8859-1");
         Charset var5 = Charset.forName("ISO-8859-1");

         try {
            if(var3 != null) {
               com.corrodinggames.rts.gameFramework.l.e("Temp file needed for zip with SAF interface");
               if(!com.corrodinggames.rts.gameFramework.l.at()) {
                  throw new IOException("Failed to open source zip with mapper: " + var2);
               }

               long var13 = br.a();
               j var8 = var3.b(var2, true);
               this.c = a((InputStream)var8, var5);
               double var9 = (double)br.a(var13);
               com.corrodinggames.rts.gameFramework.l.e("Streamed zip open took:" + br.a(var9));
            } else {
               this.c = a(var2, var5);
            }
         } catch (RuntimeException var11) {
            var12.printStackTrace();
            throw new IOException("Failed to open source zip with unicode and ISO-8859-1 encoding", var11);
         }

         this.b();
      }

   }

   public void a() {
      if(!this.e) {
         this.e = true;
         if(this.c != null) {
            try {
               this.c.close();
            } catch (IOException var2) {
               var2.printStackTrace();
            }
         }
      }

   }

   public static ZipFile a(InputStream var0, Charset var1) {
      File var2 = com.corrodinggames.rts.gameFramework.e.a.a(com.corrodinggames.rts.appFramework.c.a(), "safMod", "zip");

      ZipFile var4;
      try {
         FileOutputStream var3 = new FileOutputStream(var2);
         com.corrodinggames.rts.gameFramework.f.a(var0, (OutputStream)var3);
         var3.close();
         var0.close();
         if(var1 != null) {
            var4 = a(var2.getAbsolutePath(), var1);
            return var4;
         }

         var4 = new ZipFile(var2);
      } finally {
         var2.delete();
      }

      return var4;
   }

   public static ZipFile a(String var0, Charset var1) {
      Class[] var2 = new Class[]{String.class, Charset.class};
      Constructor var3 = null;

      try {
         var3 = ZipFile.class.getDeclaredConstructor(var2);
      } catch (NoSuchMethodException var10) {
         var10.printStackTrace();
      } catch (SecurityException var11) {
         var11.printStackTrace();
      }

      if(var3 == null) {
         throw new IOException("Failed to open source zip with unicode encoding, and no method for ISO-8859-1");
      } else {
         Object[] var4 = new Object[]{var0, var1};

         try {
            return (ZipFile)var3.newInstance(var4);
         } catch (InstantiationException var6) {
            throw new IOException(var6);
         } catch (IllegalAccessException var7) {
            throw new IOException(var7);
         } catch (InvocationTargetException var8) {
            throw new IOException(var8);
         } catch (IllegalArgumentException var9) {
            throw new IOException(var9);
         }
      }
   }

   public void b() {
      long var1 = br.a();
      ArrayList var3 = new ArrayList();
      Enumeration var4 = this.c.entries();

      while(var4.hasMoreElements()) {
         ZipEntry var5 = (ZipEntry)var4.nextElement();
         String var6 = var5.getName();
         if(var6 == null) {
            throw new RuntimeException("filePath==null");
         }

         var3.add(var6);
      }

      this.d = (String[])var3.toArray(new String[0]);
      this.a = "";
      String[] var8 = this.e("");
      if(var8.length == 1 && this.d(var8[0])) {
         this.a = var8[0] + "/";

         for(int var9 = 0; var9 < this.d.length; ++var9) {
            if(this.d[var9].startsWith(this.a)) {
               this.d[var9] = this.d[var9].substring(this.a.length());
            }
         }
      }

      double var10 = (double)br.a(var1);
      if(var10 > 3.0D) {
         com.corrodinggames.rts.gameFramework.l.e("zip: buildCache for: " + this.b + ", took:" + br.a(var10));
      }

   }

   public void a(String var1) {
      com.corrodinggames.rts.gameFramework.l.e("Zip: " + var1);
   }

   public boolean b(String var1) {
      String[] var2 = this.d;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         if(var5.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public boolean c(String var1) {
      String[] var2 = this.d;
      int var3 = var2.length;

      int var4;
      String var5;
      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5.equals(var1)) {
            return true;
         }
      }

      var2 = this.d;
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5.equalsIgnoreCase(var1)) {
            return true;
         }
      }

      return false;
   }

   public boolean d(String var1) {
      if(!var1.endsWith("/")) {
         var1 = var1 + "/";
      }

      if(var1.equals("/")) {
         return true;
      } else {
         String[] var2 = this.d;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if(var5.contains(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public String[] e(String var1) {
      if(!var1.equals("") && !var1.equals("/") && !var1.equals("\\")) {
         if(!var1.endsWith("/")) {
            var1 = var1 + "/";
         }
      } else {
         var1 = "";
      }

      ArrayList var2 = new ArrayList();
      String[] var3 = this.d;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String var6 = var3[var5];
         if(var1.equals("") || var6.startsWith(var1)) {
            String var7 = var6.substring(var1.length());
            if(var7.length() != 0 && !var7.equals("..")) {
               if(var7.contains("/")) {
                  var7 = var7.substring(0, var7.indexOf("/"));
                  if(!var2.contains(var7)) {
                     var2.add(var7);
                  }
               } else {
                  var2.add(var7);
               }
            }
         }
      }

      return (String[])var2.toArray(new String[0]);
   }

   public ZipEntry f(String var1) {
      String var2 = this.a + var1;
      ZipEntry var3 = null;
      IllegalArgumentException var4 = null;

      try {
         var3 = this.c.getEntry(var2);
      } catch (IllegalArgumentException var8) {
         var4 = var8;
      }

      if(var3 == null && this.b(var1) && !this.d(var1)) {
         Enumeration var5 = this.c.entries();

         while(var5.hasMoreElements()) {
            ZipEntry var6;
            try {
               var6 = (ZipEntry)var5.nextElement();
            } catch (IllegalArgumentException var9) {
               var9.printStackTrace();
               continue;
            }

            String var7 = var6.getName();
            if(var7.equals(var2)) {
               return var6;
            }
         }

         this.a("getEntry: Still did not find file after workaround");
      }

      if(var4 != null) {
         throw new RuntimeException("Failed to decode data in zip: " + var1 + " (Check zip encoding, utf-8 is recommended)", var4);
      } else {
         return var3;
      }
   }

   public String g(String var1) {
      String var2 = var1;
      if(!var1.endsWith("/")) {
         var2 = var1 + "/";
      }

      String[] var3 = this.d;
      int var4 = var3.length;

      int var5;
      String var6;
      for(var5 = 0; var5 < var4; ++var5) {
         var6 = var3[var5];
         if(var6.equals(var1)) {
            return var6;
         }
      }

      var3 = this.d;
      var4 = var3.length;

      for(var5 = 0; var5 < var4; ++var5) {
         var6 = var3[var5];
         if(var6.equals(var2)) {
            return var6;
         }
      }

      var3 = this.d;
      var4 = var3.length;

      for(var5 = 0; var5 < var4; ++var5) {
         var6 = var3[var5];
         if(var6.equalsIgnoreCase(var1)) {
            return var6;
         }
      }

      var3 = this.d;
      var4 = var3.length;

      for(var5 = 0; var5 < var4; ++var5) {
         var6 = var3[var5];
         if(var6.equalsIgnoreCase(var2)) {
            return var6;
         }
      }

      return var1;
   }

   public long h(String var1) {
      ZipEntry var2 = this.f(var1);
      if(var2 == null) {
         this.a("getEntrySize: File not found: " + var1);
         return -1L;
      } else {
         return var2.getSize();
      }
   }

   public j i(String var1) {
      ZipEntry var2 = this.f(var1);
      if(var2 == null) {
         var2 = this.f(this.g(var1));
      }

      if(var2 == null) {
         return null;
      } else {
         InputStream var3;
         try {
            var3 = this.c.getInputStream(var2);
         } catch (IOException var7) {
            var7.printStackTrace();
            return null;
         }

         try {
            j var4 = new j(var3, this.b + "/" + var1);
            return var4;
         } catch (FileNotFoundException var6) {
            var6.printStackTrace();
            return null;
         }
      }
   }
}
