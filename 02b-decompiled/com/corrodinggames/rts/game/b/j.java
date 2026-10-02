package com.corrodinggames.rts.game.b;

import android.graphics.Rect;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.k;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class j {

   public String a;
   public com.corrodinggames.rts.gameFramework.m.e b;
   public String c;
   int d;
   int e;
   int f;
   int g;
   int h = 0;
   int i = 0;
   int j;
   float k;
   public int l;
   public int m = Integer.MAX_VALUE;
   public short n;
   public b o;
   public boolean p = false;
   public boolean q = false;
   public boolean r = false;
   public boolean s = false;
   private HashMap x = new HashMap();
   static String t = "[EMBED]";
   static ArrayList u = new ArrayList();
   Rect v = new Rect();
   int w = -1;


   public strictfp String a(b var1, Element var2) {
      Element var3 = (Element)var2.getElementsByTagName("properties").item(0);
      if(var3 != null) {
         NodeList var4 = var3.getElementsByTagName("property");

         for(int var5 = 0; var5 < var4.getLength(); ++var5) {
            Element var6 = (Element)var4.item(var5);
            String var7 = var6.getAttribute("name");
            if(var7.equals("embedded_png")) {
               String var8 = var6.getAttribute("value");
               if(var8 != null && !var8.equals("")) {
                  return var8;
               }

               Node var9 = var6.getFirstChild();
               if(var9 != null) {
                  String var10 = var9.getNodeValue();
                  return var10;
               }
            }
         }
      }

      return null;
   }

   public static strictfp Element a(b var0, String var1) {
      try {
         InputStream var2 = var0.d("tilesets/", var1);
         DocumentBuilder var3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document var4 = var3.parse(var2);
         Element var5 = var4.getDocumentElement();
         return var5;
      } catch (Exception var6) {
         l.B().a("Unable to load or parse sourced tileset: tilesets/" + var1, 1);
         throw new f("Unable to load or parse sourced tileset: tilesets/" + var1, var6);
      }
   }

   public strictfp j(b var1, String var2, int var3) {
      this.o = var1;
      this.l = var3;
      Element var4 = a(var1, var2);
      this.a = var2;
      this.a(var4);
   }

   public strictfp j(b var1, Element var2) {
      this.o = var1;
      this.l = Integer.parseInt(var2.getAttribute("firstgid"));
      String var3 = var2.getAttribute("source");
      if(var3 != null && !var3.equals("")) {
         var2 = a(var1, var3);
         this.a = var3;
      }

      this.a(var2);
   }

   public strictfp void a(Element var1) {
      NodeList var2 = var1.getElementsByTagName("image");
      if(var2.getLength() > 0) {
         Element var3 = (Element)var2.item(0);
         String var4 = var3.getAttribute("source");
         var4 = var4.trim();
         this.c = l.k(var4);
      }

      String var16 = this.a(this.o, var1);
      if(var16 != null) {
         this.c = a(var16, this.c);
      }

      if(this.c == null) {
         throw new f("Map tileset is missing an image tag or embedded image data");
      } else {
         this.d = this.o.n;
         this.e = this.o.o;
         if(var1.hasAttribute("tilewidth")) {
            this.d = Integer.parseInt(var1.getAttribute("tilewidth"));
            this.e = Integer.parseInt(var1.getAttribute("tileheight"));
         }

         if(l.C()) {
            this.d = this.o.n;
            this.e = this.o.o;
         }

         int var17 = 0;
         if(var1.hasAttribute("spacing")) {
            var17 = Integer.parseInt(var1.getAttribute("spacing"));
         }

         this.f = this.d + var17;
         this.g = this.e + var17;
         NodeList var5 = var1.getElementsByTagName("tile");

         for(int var6 = 0; var6 < var5.getLength(); ++var6) {
            Element var7 = (Element)var5.item(var6);
            int var8 = Integer.parseInt(var7.getAttribute("id"));
            var8 += this.l;
            Properties var9 = new Properties();
            Element var10 = (Element)var7.getElementsByTagName("properties").item(0);
            if(var10 != null) {
               NodeList var11 = var10.getElementsByTagName("property");

               for(int var12 = 0; var12 < var11.getLength(); ++var12) {
                  Element var13 = (Element)var11.item(var12);
                  String var14 = var13.getAttribute("name");
                  String var15 = var13.getAttribute("value");
                  if("unit".equalsIgnoreCase(var14) || "customUnit".equalsIgnoreCase(var14)) {
                     this.s = true;
                  }

                  var9.setProperty(var14, var15);
               }
            }

            this.x.put(new Integer(var8), var9);
         }

      }
   }

   public static strictfp String a(String var0, String var1) {
      Iterator var2 = u.iterator();

      k var3;
      do {
         if(!var2.hasNext()) {
            k var4 = new k();
            var4.b = false;
            var4.e = null;
            var4.f = var0;
            var4.c = t;
            var4.d = t + k.a;
            var4.g = var1;
            ++k.a;
            u.add(var4);
            return var4.d;
         }

         var3 = (k)var2.next();
      } while(!var0.equalsIgnoreCase(var3.f));

      return var3.d;
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e a(String var0) {
      l var1 = l.B();
      String var2 = "tilesets/bitmaps/";
      if(var0.startsWith(t)) {
         var2 = t;
      }

      k var3 = null;
      Iterator var4 = u.iterator();

      while(var4.hasNext()) {
         k var5 = (k)var4.next();
         if(var0.equalsIgnoreCase(var5.d) && var2.equalsIgnoreCase(var5.c)) {
            var3 = var5;
            break;
         }
      }

      InputStream var12;
      if(var3 != null) {
         if(var3.f != null) {
            var12 = e.a(var3.f, "base64", "");
            BufferedInputStream var14 = new BufferedInputStream(var12);
            boolean var15 = false;

            com.corrodinggames.rts.gameFramework.m.e var16;
            try {
               var16 = var1.bO.a(var14, var15);
            } catch (RuntimeException var9) {
               var9.printStackTrace();
               throw new f("Error loading embedded base64 image:" + var3.g + " - " + var9.getMessage());
            }

            if(var16 == null) {
               throw new f("Embedded tilesetBitmap is null for: " + var0);
            }

            var3.e = var16;
            var3.f = null;
         }

         var3.b = true;
         return var3.e;
      } else {
         try {
            var12 = var1.bL.d(var2, var0);
         } catch (IOException var11) {
            throw new f("Image file could not be found or loaded: " + var2 + var0, var11);
         }

         boolean var13 = false;
         com.corrodinggames.rts.gameFramework.m.e var6 = var1.bO.a(var12, var13);

         try {
            if(var12 != null) {
               var12.close();
            }
         } catch (IOException var10) {
            var10.printStackTrace();
         }

         if(var6 == null) {
            throw new RuntimeException("tilesetBitmap is null for: " + var0);
         } else {
            var6.a("tilesets/" + var0);
            k var7 = new k();
            var7.b = true;
            var7.e = var6;
            var7.c = var2;
            var7.d = var0;
            u.add(var7);
            return var7.e;
         }
      }
   }

   public static strictfp void a() {
      k var1;
      for(Iterator var0 = u.iterator(); var0.hasNext(); var1.b = false) {
         var1 = (k)var0.next();
      }

   }

   public static strictfp void b() {
      Iterator var0 = u.iterator();

      while(var0.hasNext()) {
         k var1 = (k)var0.next();
         if(!var1.b) {
            if(var1.e != null) {
               var1.e.o();
               var1.e = null;
            }

            var1.f = null;
            var0.remove();
         }
      }

   }

   strictfp void c() {
      this.b = a(this.c);
      this.j = this.b.m() / this.f;
      if(this.j == 0) {
         this.j = 1;
      }

      this.k = 1.0F / (float)this.j;
   }

   public strictfp Properties a(int var1) {
      return (Properties)this.x.get(new Integer(var1));
   }

   public final strictfp void a(int var1, Rect var2) {
      int var3 = var1 % this.j;
      int var4 = (int)((float)var1 * this.k);
      int var5 = this.h + var3 * this.f;
      int var6 = this.i + var4 * this.g;
      var2.a = var5;
      var2.b = var6;
      var2.c = var5 + this.d;
      var2.d = var6 + this.e;
   }

   public final strictfp Rect b(int var1) {
      if(this.w == var1) {
         return this.v;
      } else {
         this.w = var1;
         this.a(var1, this.v);
         return this.v;
      }
   }

   public strictfp void c(int var1) {
      this.m = var1;
   }

   public strictfp boolean d(int var1) {
      return var1 >= this.l && var1 <= this.m;
   }

   public strictfp void d() {
      this.b = null;
      this.o = null;
      this.x = null;
   }

   public strictfp Integer b(String var1, String var2) {
      Iterator var3 = this.x.entrySet().iterator();

      Integer var5;
      String var7;
      do {
         if(!var3.hasNext()) {
            return null;
         }

         Entry var4 = (Entry)var3.next();
         var5 = (Integer)var4.getKey();
         Properties var6 = (Properties)var4.getValue();
         var7 = var6.getProperty(var1);
      } while(var7 == null || !var7.equals(var2));

      return var5;
   }

   public strictfp int a(int var1, int var2) {
      int var3;
      if(this.b == null) {
         l.e("getIndexOffsetByPosition tilesetBitmap == null");
         var3 = 3;
      } else if(this.d == 0) {
         l.e("getIndexOffsetByPosition tileWidth==0");
         var3 = 3;
      } else {
         var3 = this.b.m() / this.d;
      }

      return var1 + var2 * var3;
   }

}
