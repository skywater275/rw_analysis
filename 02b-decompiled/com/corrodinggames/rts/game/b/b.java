package com.corrodinggames.rts.game.b;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.b.a;
import com.corrodinggames.rts.game.b.b$1;
import com.corrodinggames.rts.game.b.b$2;
import com.corrodinggames.rts.game.b.c;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.b.h;
import com.corrodinggames.rts.game.b.i;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.o;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class b {

   static final boolean a = false;
   static final boolean b = false;
   static final boolean c = false;
   public static boolean d = false;
   static ReentrantLock e = new ReentrantLock();
   static boolean f;
   static Paint g = new Paint();
   static Paint h = new Paint();
   static Paint i = new Paint();
   static Paint j = new Paint();
   boolean[] k = new boolean[256];
   public static h l;
   public static h m;
   public int n = 20;
   public int o = 20;
   public int p;
   public int q;
   public float r;
   public float s;
   public ArrayList t = new ArrayList();
   public e u = null;
   public e v = null;
   public e w = null;
   public e x;
   public e y = null;
   public ArrayList z = new ArrayList();
   public ArrayList A = new ArrayList();
   private int as = 1;
   public g[] B = new g[0];
   public int C;
   public int D;
   public boolean E = true;
   public boolean F = false;
   public boolean G = false;
   public static boolean H = false;
   public static boolean I = false;
   public static boolean J = false;
   public static com.corrodinggames.rts.gameFramework.m.e K;
   public static y L;
   public byte[][] M;
   public byte[][] N;
   Rect O = new Rect();
   protected ArrayList P = new ArrayList();
   public i Q;
   public boolean R;
   public boolean S;
   public int T;
   public int U;
   public PointF V = new PointF();
   public boolean W;
   public boolean X;
   public int Y;
   public int Z;
   float aa = 0.0F;
   Paint ab;
   Paint ac;
   Paint ad;
   Paint ae;
   Paint af;
   Paint ag;
   HashMap ah;
   float ai;
   float aj = 1.0F;
   int ak = 0;
   public static c al = new c();
   Paint am = new Paint();
   Rect an = new Rect();
   Rect ao = new Rect();
   long ap;
   float aq;
   float ar;


   public static strictfp void a() {
      if(!f) {
         e.lock();
      }
   }

   public static strictfp void b() {
      if(!f) {
         e.unlock();
      }
   }

   public static strictfp void c() {
      l var0 = l.B();
      g.a(150, 255, 255, 255);
      g.a(Paint$Style.b);
      g.a(1.0F);
      var0.a(g, 16.0F);
      h.a(150, 255, 0, 0);
      h.a(Paint$Style.b);
      h.a(1.0F);
      i.a(150, 0, 255, 0);
      i.a(Paint$Style.b);
      i.a(1.0F);
      j.a(150, 255, 0, 0);
      long var1 = br.a();
      com.corrodinggames.rts.gameFramework.m.e var3 = var0.bO.a(R$drawable.fog_smooth);
      byte var4 = 20;
      byte var5 = 20;
      byte var6 = 1;
      K = var0.bO.b((var4 + 2) * 16 + 1, (var5 + 2) * 16 + 1, true);
      K.m = true;
      K.b(true);
      L = var0.bO.b(K);
      com.corrodinggames.rts.gameFramework.m.e var7 = var0.bO.b(var4 + var6, var5 + var6, true);
      y var8 = var0.bO.b(var7);
      byte var9 = 1;
      byte var10 = 2;
      byte var11 = 4;
      byte var12 = 8;
      byte var13 = 16;
      byte var14 = 32;
      byte var15 = 64;
      byte var16 = -128;
      a(b(var9), 2, 5, true, var7, var8, var3);
      a(b(var10), 0, 5, true, var7, var8, var3);
      a(b(var11), 0, 3, true, var7, var8, var3);
      a(b(var12), 2, 3, true, var7, var8, var3);
      a(a(var13, new int[]{var9, var10}), 1, 0, true, var7, var8, var3);
      a(a(var14, new int[]{var10, var11}), 2, 1, true, var7, var8, var3);
      a(a(var15, new int[]{var12, var11}), 1, 2, true, var7, var8, var3);
      a(a(var16, new int[]{var9, var12}), 0, 1, true, var7, var8, var3);
      a(a(var13 + var14, new int[]{var10, var9, var11}), 2, 0, true, var7, var8, var3);
      a(a(var14 + var15, new int[]{var11, var12, var10}), 2, 2, true, var7, var8, var3);
      a(a(var15 + var16, new int[]{var12, var11, var9}), 0, 2, true, var7, var8, var3);
      a(a(var16 + var13, new int[]{var9, var12, var10}), 0, 0, true, var7, var8, var3);
      a(b(var9 + var10), a(new int[]{2, 5, 0, 5}), true, var7, var8, var3);
      a(b(var10 + var11), a(new int[]{0, 5, 0, 3}), true, var7, var8, var3);
      a(b(var11 + var12), a(new int[]{0, 3, 2, 3}), true, var7, var8, var3);
      a(b(var12 + var9), a(new int[]{2, 3, 2, 5}), true, var7, var8, var3);
      a(a(var13 + var14 + var15, new int[]{var9, var10, var11, var12}), a(new int[]{2, 0, 2, 2}), true, var7, var8, var3);
      a(a(var14 + var15 + var16, new int[]{var9, var10, var11, var12}), a(new int[]{2, 2, 0, 2}), true, var7, var8, var3);
      a(a(var15 + var16 + var13, new int[]{var9, var10, var11, var12}), a(new int[]{0, 2, 0, 0}), true, var7, var8, var3);
      a(a(var16 + var13 + var14, new int[]{var9, var10, var11, var12}), a(new int[]{0, 0, 2, 0}), true, var7, var8, var3);
      a(b(var13 + var15), a(new int[]{1, 0, 1, 2}), true, var7, var8, var3);
      a(b(var16 + var14), a(new int[]{0, 1, 2, 1}), true, var7, var8, var3);
      a(b(var9 + var11), a(new int[]{2, 5, 0, 3}), true, var7, var8, var3);
      a(b(var10 + var12), a(new int[]{0, 5, 2, 3}), true, var7, var8, var3);
      a(a(var13 + var11, new int[]{var10, var9}), a(new int[]{1, 0, 0, 3}), true, var7, var8, var3);
      a(a(var15 + var10, new int[]{var11, var12}), a(new int[]{1, 2, 0, 5}), true, var7, var8, var3);
      a(a(var16 + var10, new int[]{var9, var12}), a(new int[]{0, 1, 0, 5}), true, var7, var8, var3);
      a(a(var14 + var9, new int[]{var10, var11}), a(new int[]{2, 1, 2, 5}), true, var7, var8, var3);
      a(a(var13 + var12, new int[]{var10, var9}), a(new int[]{1, 0, 2, 3}), true, var7, var8, var3);
      a(a(var15 + var9, new int[]{var11, var12}), a(new int[]{1, 2, 2, 5}), true, var7, var8, var3);
      a(a(var16 + var11, new int[]{var9, var12}), a(new int[]{0, 1, 0, 3}), true, var7, var8, var3);
      a(a(var14 + var12, new int[]{var10, var11}), a(new int[]{2, 1, 2, 3}), true, var7, var8, var3);
      a(a(var13 + var11 + var12, new int[]{var10, var9}), a(new int[]{1, 0, 0, 3, 2, 3}), true, var7, var8, var3);
      a(a(var15 + var10 + var9, new int[]{var11, var12}), a(new int[]{1, 2, 0, 5, 2, 5}), true, var7, var8, var3);
      a(a(var16 + var10 + var11, new int[]{var9, var12}), a(new int[]{0, 1, 2, 5, 2, 3}), true, var7, var8, var3);
      a(a(var14 + var9 + var12, new int[]{var10, var11}), a(new int[]{2, 1, 0, 5, 0, 3}), true, var7, var8, var3);
      a(b(-1), a(new int[]{1, 4}), true, var7, var8, var3);
      L.p();
      L.q();
      L = null;
      var8.q();
      var8 = null;
      br.a("smoothFog load took:", var1);
      d();
      l = new h(1.0F, false);
      l.a();
      m = new h(0.5F, false);
      m.a();
   }

   public static strictfp void d() {
      if(!H) {
         H = true;
         l var0 = l.B();
         I = var0.bQ.softFogFading;
         if(l.at() && VERSION.SDK_INT > 26) {
            long var1 = Runtime.getRuntime().maxMemory() / 1048576L;
            l.e("MaxHeapSizeInMB:" + var1);
            if(var1 > 200L) {
               l.e("enabling softFades");
               I = true;
            }
         }

      }
   }

   private static strictfp int[] b(int var0) {
      return new int[]{var0};
   }

   private static strictfp int[] a(int var0, int ... var1) {
      ArrayList var2 = new ArrayList();
      var2.add(Integer.valueOf(var0));
      if(var1.length == 1) {
         var2.add(Integer.valueOf(var0 + var1[0]));
      } else if(var1.length == 2) {
         var2.add(Integer.valueOf(var0 + var1[0]));
         var2.add(Integer.valueOf(var0 + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1]));
      } else if(var1.length == 3) {
         var2.add(Integer.valueOf(var0 + var1[0]));
         var2.add(Integer.valueOf(var0 + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[2]));
      } else {
         if(var1.length != 4) {
            throw new RuntimeException("unhandled:" + var1.length);
         }

         var2.add(Integer.valueOf(var0 + var1[0]));
         var2.add(Integer.valueOf(var0 + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[2] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[2] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[2] + var1[3]));
      }

      int[] var3 = new int[var2.size()];

      for(int var4 = 0; var4 < var2.size(); ++var4) {
         if(var2.get(var4) != null) {
            var3[var4] = ((Integer)var2.get(var4)).intValue();
         }
      }

      return var3;
   }

   private static strictfp int[] a(int ... var0) {
      return var0;
   }

   private static strictfp void a(int[] var0, int var1, int var2, boolean var3, com.corrodinggames.rts.gameFramework.m.e var4, y var5, com.corrodinggames.rts.gameFramework.m.e var6) {
      a(var0, a(new int[]{var1, var2}), var3, var4, var5, var6);
   }

   private static strictfp void a(int[] var0, int[] var1, boolean var2, com.corrodinggames.rts.gameFramework.m.e var3, y var4, com.corrodinggames.rts.gameFramework.m.e var5) {
      if(var2) {
         var4.o();
      }

      Rect var6 = new Rect();
      Rect var7 = new Rect();
      Rect var8 = new Rect();
      Rect var9 = new Rect();
      byte var10 = 20;
      byte var11 = 20;
      var6.a(0, 0, var10, var11);
      y var12 = var4;

      int var13;
      int var14;
      for(var13 = 0; var13 < var1.length; var13 += 2) {
         var14 = var1[var13 + 0] * 20;
         int var15 = var1[var13 + 1] * 20;
         var7.a(var14, var15, var14 + var10, var15 + var11);
         var12.a(var5, var7, var6, (Paint)null);
         var9.a(var7.c - 1, var7.b, var7.c, var7.d);
         var8.a(var6.c, var6.b, var6.c + 1, var6.d);
         var12.a(var5, var9, var8, (Paint)null);
         var9.a(var7.a, var7.d - 1, var7.c, var7.d);
         var8.a(var6.a, var6.d, var6.c, var6.d + 1);
         var12.a(var5, var9, var8, (Paint)null);
      }

      var12.p();

      for(var13 = 0; var13 < var0.length; ++var13) {
         var14 = var0[var13] + 128;
         a(var14, var3);
      }

   }

   public static strictfp void a(int var0, com.corrodinggames.rts.gameFramework.m.e var1) {
      Rect var2 = new Rect();
      Rect var3 = new Rect();
      var3.a(0, 0, 20, 20);
      a(var0, var2);
      h.a(L, var1, var3, var2, (Paint)null);
   }

   public static strictfp void a(int var0, Rect var1) {
      byte var2 = 20;
      byte var3 = 20;
      int var4 = var0 % 16;
      int var5 = (int)((float)var0 * 0.0625F);
      int var6 = var4 * (var2 + 2) + 1;
      int var7 = var5 * (var3 + 2) + 1;
      var1.a = var6;
      var1.b = var7;
      var1.c = var6 + var2;
      var1.d = var7 + var3;
   }

   public final strictfp short a(g var1) {
      if(this.as >= this.B.length) {
         g[] var2 = new g[com.corrodinggames.rts.gameFramework.f.c(this.B.length + 100, 32767)];
         System.arraycopy(this.B, 0, var2, 0, this.B.length);
         this.B = var2;
      }

      int var3 = this.as;
      if(this.as < 32766) {
         ++this.as;
      } else {
         l.b("Max unique tile limit reached at: " + this.as);
      }

      this.B[var3] = var1;
      return (short)var3;
   }

   public final strictfp g a(short var1) {
      return this.B[var1];
   }

   public strictfp g a(g var1, int var2, int var3) {
      if(var1 != null && var1.m != null) {
         int var4 = (var2 * 13 + var3 * 1313) % (var1.m.length + 1);
         --var4;
         if(var4 >= 0) {
            return var1.m[var4];
         }
      }

      return var1;
   }

   public strictfp boolean a(float var1, float var2, n var3) {
      if(this.E) {
         int var4 = (int)(var1 * this.r);
         int var5 = (int)(var2 * this.s);
         if(var3.N != null && this.c(var4, var5) && var3.N[var4][var5] >= 5) {
            return false;
         }
      }

      return true;
   }

   public strictfp boolean a(int var1, int var2, n var3) {
      return !this.E || var3.N == null || !this.c(var1, var2) || var3.N[var1][var2] < 5;
   }

   public strictfp void a(float var1, float var2) {
      this.T = (int)(var1 * this.r);
      this.U = (int)(var2 * this.s);
   }

   public strictfp void a(int var1, int var2) {
      this.T = var1 * this.n;
      this.U = var2 * this.o;
   }

   public strictfp void b(int var1, int var2) {
      this.T = var1 * this.n + this.p;
      this.U = var2 * this.o + this.q;
   }

   public strictfp PointF a(Point var1) {
      this.V.a((float)(var1.a * this.n), (float)(var1.b * this.o));
      return this.V;
   }

   public strictfp void b(float var1, float var2) {
      this.a(var1, var2);
      this.a(this.T, this.U);
   }

   public strictfp float a(float var1) {
      if(var1 < 0.0F) {
         var1 = 0.0F;
      }

      if(var1 > this.i()) {
         var1 = this.i();
      }

      return var1;
   }

   public strictfp float b(float var1) {
      if(var1 < 0.0F) {
         var1 = 0.0F;
      }

      if(var1 > this.j()) {
         var1 = this.j();
      }

      return var1;
   }

   public final strictfp boolean c(int var1, int var2) {
      return var1 >= 0 && var1 < this.C && var2 >= 0 && var2 < this.D;
   }

   public strictfp g c(float var1, float var2) {
      int var3 = (int)(var1 * this.r);
      int var4 = (int)(var2 * this.s);
      return var3 >= 0 && var3 < this.C && var4 >= 0 && var4 < this.D?this.u.a(var3, var4):null;
   }

   public strictfp g d(int var1, int var2) {
      return !this.c(var1, var2)?null:this.u.a(var1, var2);
   }

   public strictfp g e(int var1, int var2) {
      return !this.c(var1, var2)?null:(this.y == null?null:this.y.a(var1, var2));
   }

   strictfp void a(RectF var1) {
      if(l.C()) {
         var1.a *= (float)(this.n / 20);
         var1.c *= (float)(this.n / 20);
         var1.b *= (float)(this.o / 20);
         var1.d *= (float)(this.o / 20);
      }

   }

   public strictfp b() {
      if(l.C()) {
         this.n = 60;
         this.o = 60;
      }

      this.p = this.n / 2;
      this.q = this.o / 2;
      this.r = 1.0F / (float)this.n;
      this.s = 1.0F / (float)this.o;
      this.ab = new ag();
      this.ab.a(100, 255, 0, 0);
      this.ab.b(16.0F);
      this.ac = new ag();
      this.ac.a(Paint$Style.b);
      this.ac.a(1.0F);
      this.ac.a(255, 0, 225, 0);
      this.ad = new ag();
      this.ad.a(Paint$Style.b);
      this.ad.a(1.0F);
      this.ad.a(100, 0, 185, 0);
      this.ae = new ag();
      this.ae.a(Paint$Style.b);
      this.ae.a(1.0F);
      this.ae.a(255, 175, 0, 0);
      this.af = new ag();
      this.af.a(155, 175, 0, 0);
      this.ag = new ag();
      this.ag.a((Xfermode)(new PorterDuffXfermode(Mode.CLEAR)));
   }

   public static strictfp void a(String var0, as var1) {
      InputStream var2 = b(var0);
      if(var2 == null) {
         throw new IOException("writeMapStream: Could not find map:" + var0);
      } else {
         int var3 = (int)a(var0);
         if(var3 == -1) {
            new IOException("writeMapStream: Failed to get map size");
         }

         if(var3 == 0) {
            new IOException("writeMapStream: Got empty map size");
         }

         l.e("Sending map stream of size: " + var3);
         var1.a(var2, var3);
      }
   }

   public static strictfp long a(String var0) {
      String var1 = "" + var0;
      String var2 = com.corrodinggames.rts.gameFramework.e.a.e(var1);
      af var3 = ae.a(var2);
      if(var3 != null && !var2.endsWith(".rwmod")) {
         long var8 = var3.a(var2, false);
         if(var8 == -1L) {
            ;
         }

         return var8;
      } else if(com.corrodinggames.rts.gameFramework.e.a.c(var1)) {
         AssetManager var7 = l.B().am.d();

         try {
            AssetFileDescriptor var5 = var7.b(var2);
            return var5.getLength();
         } catch (IOException var6) {
            throw new RuntimeException(var6);
         }
      } else {
         File var4 = new File(var2);
         return var4.length();
      }
   }

   public static strictfp InputStream b(String var0) {
      InputStream var1 = d(var0);
      if(var1 == null) {
         InputStream var2 = d(var0.replace(".tmx", "") + "_moved");
         if(var2 != null) {
            String var3 = com.corrodinggames.rts.gameFramework.f.a(var2);
            var3 = var3.trim();
            l.e("Found moved map at:" + var3);
            var1 = d(var3);
         }
      }

      return var1;
   }

   public static strictfp String c(String var0) {
      if(var0 == null) {
         return null;
      } else {
         String var1 = com.corrodinggames.rts.gameFramework.e.a.e(var0);
         return var1;
      }
   }

   public static strictfp InputStream d(String var0) {
      String var1 = c("" + var0);
      l.e("Mapfile: " + var1);
      com.corrodinggames.rts.gameFramework.utility.j var2 = com.corrodinggames.rts.gameFramework.e.a.k(var1);
      return var2;
   }

   public strictfp void a(Document var1, OutputStream var2) {
      TransformerFactory var3 = TransformerFactory.newInstance();
      Transformer var4 = var3.newTransformer();
      var4.setOutputProperty("indent", "yes");
      DOMSource var5 = new DOMSource(var1);
      StreamResult var6 = new StreamResult(var2);
      var4.transform(var5, var6);
   }

   public strictfp void a(InputStream var1, OutputStream var2) {
      DocumentBuilderFactory var3 = DocumentBuilderFactory.newInstance();
      var3.setValidating(false);
      DocumentBuilder var4 = var3.newDocumentBuilder();
      var4.setEntityResolver(new b$1(this));
      Document var5 = var4.parse(var1);
      Element var6 = var5.getDocumentElement();
      String var7 = var6.getAttribute("orientation");
      if(!var7.equals("orthogonal")) {
         throw new f("Only orthogonal maps are supported, found: " + var7);
      } else {
         NodeList var8 = var6.getElementsByTagName("SOMETHING");

         for(int var9 = 0; var9 < var8.getLength(); ++var9) {
            Element var10 = (Element)var8.item(var9);
         }

         NodeList var23 = var6.getElementsByTagName("layer");

         Element var11;
         for(int var24 = 0; var24 < var23.getLength(); ++var24) {
            var11 = (Element)var23.item(var24);
            String var12 = var11.getAttribute("name");
            if("units".equalsIgnoreCase(var12)) {
               var11.getParentNode().removeChild(var11);
            }
         }

         NodeList var25 = var6.getElementsByTagName("objectgroup");

         for(int var26 = 0; var26 < var25.getLength(); ++var26) {
            Element var27 = (Element)var25.item(var26);
            String var13 = var27.getAttribute("name");
            if("UnitObjects".equalsIgnoreCase(var13)) {
               var27.getParentNode().removeChild(var27);
            }
         }

         var11 = var5.createElement("objectgroup");
         var11.setAttribute("name", "UnitObjects");
         o var28 = am.bF();
         Iterator var29 = var28.iterator();

         while(var29.hasNext()) {
            am var14 = (am)var29.next();
            if(var14 instanceof am && (!(var14 instanceof al) || !((al)var14).bM) && !var14.bV && !var14.u()) {
               com.corrodinggames.rts.game.units.custom.b.n var16 = var14.dn();
               if(var14.cO != null && var16 != null) {
                  if(!var16.D) {
                     ;
                  }
               } else {
                  Element var17 = var5.createElement("object");
                  int var18 = 20;
                  if((float)var18 < var14.cj) {
                     var18 = (int)var14.cj;
                  }

                  var17.setAttribute("name", var14.r().i() + " (t:" + var14.bX.k + ")");
                  var17.setAttribute("x", "" + (var14.eo - (float)(var18 / 2)));
                  var17.setAttribute("y", "" + (var14.ep - (float)(var18 / 2)));
                  var17.setAttribute("width", "" + var18);
                  var17.setAttribute("height", "" + var18);
                  float var19;
                  if(var14.bI()) {
                     var19 = var14.cg;
                  } else {
                     var19 = var14.cg + 90.0F;
                  }

                  var17.setAttribute("rotation", "" + var19);
                  Integer var20 = this.a(var14.r());
                  if(var20 != null) {
                     var17.setAttribute("gid", "" + var20);
                  }

                  Element var21 = var5.createElement("properties");
                  Element var22 = var5.createElement("property");
                  var22.setAttribute("name", "unit");
                  var22.setAttribute("value", var14.r().i());
                  var21.appendChild(var22);
                  var22 = var5.createElement("property");
                  var22.setAttribute("name", "team");
                  var22.setAttribute("value", "" + var14.bX.k);
                  var21.appendChild(var22);
                  var17.appendChild(var21);
                  var11.appendChild(var17);
               }
            }
         }

         var6.appendChild(var11);
         this.a(var5, var2);
      }
   }

   public strictfp boolean a(String var1, String var2) {
      l var3 = l.B();

      try {
         this.b(var1, var2);
         var3.bS.h.a((String)null, "Map exported.");
         return true;
      } catch (f var5) {
         var3.c("Error exporting map", "Failed to export map. error: " + var5.getMessage());
         return false;
      } catch (IOException var6) {
         var6.printStackTrace();
         var3.c("Error exporting map", "Failed to export map. IO error: " + var6.getMessage());
         return false;
      } catch (NoClassDefFoundError var7) {
         var7.printStackTrace();
         var3.c("Error exporting map", "Failed to export map. Class not found: " + var7.getMessage());
         return false;
      }
   }

   public strictfp void b(String var1, String var2) {
      l.e(" --- Saving map:" + var1 + " to: " + var2);
      InputStream var3 = b(var1);
      if(var3 == null) {
         throw new IOException("Could not find orginal map: " + var1);
      } else {
         BufferedInputStream var4 = new BufferedInputStream(var3);
         var2 = com.corrodinggames.rts.gameFramework.e.a.e(var2);
         File var6 = (new File(var2)).getParentFile();
         if(!com.corrodinggames.rts.gameFramework.e.a.i(var6.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.e.a.l(var6.getAbsolutePath());
         }

         if(!com.corrodinggames.rts.gameFramework.e.a.f(var6.getAbsolutePath())) {
            l.b("Save Map: Could not create parent directory");
         }

         OutputStream var5;
         try {
            var5 = com.corrodinggames.rts.gameFramework.e.a.b(var2, false);
            if(var5 == null) {
               throw new IOException("Failed to get save target:" + var2);
            }
         } catch (FileNotFoundException var13) {
            throw new IOException("Failed to open save target:" + var2);
         }

         try {
            this.a((InputStream)var4, var5);
         } catch (ParserConfigurationException var9) {
            throw new IOException(var9);
         } catch (SAXException var10) {
            throw new IOException(var10);
         } catch (IOException var11) {
            throw new IOException(var11);
         } catch (TransformerException var12) {
            throw new IOException(var12);
         }

         try {
            var5.close();
            var4.close();
            var3.close();
         } catch (IOException var8) {
            var8.printStackTrace();
         }

      }
   }

   public strictfp void a(String var1, boolean var2) {
      l.e(" --- Loading map ---");
      InputStream var3 = b(var1);
      if(var3 == null) {
         String var7 = c(var1);
         throw new f("Could not find map: " + com.corrodinggames.rts.gameFramework.e.a.d(var7));
      } else {
         BufferedInputStream var4 = new BufferedInputStream(var3);
         this.a((InputStream)var4, var2);

         try {
            var4.close();
            var3.close();
         } catch (IOException var6) {
            var6.printStackTrace();
         }

      }
   }

   public strictfp j e(String var1) {
      j var2 = null;
      Iterator var3 = this.t.iterator();

      j var4;
      while(var3.hasNext()) {
         var4 = (j)var3.next();
         if(var1.equals(var4.a)) {
            var2 = var4;
         }
      }

      if(var2 == null) {
         int var5 = 1;
         if(this.t.size() > 0) {
            var4 = (j)this.t.get(this.t.size() - 1);
            var5 = var4.l + 100;
            var4.c(var5);
         }

         var4 = new j(this, var1, var5 + 1);
         this.t.add(var4);
         var2 = var4;
      }

      if(var2.b == null) {
         var2.c();
      }

      return var2;
   }

   public strictfp g a(String var1, int var2, int var3) {
      j var4 = this.e(var1);
      if(this.ah == null) {
         this.ah = new HashMap();
      }

      boolean var5 = true;
      int var6 = var4.a(var2, var3);
      int var7 = var4.l + var6;
      g var8 = (g)this.ah.get(Integer.valueOf(var7));
      if(var8 != null) {
         return var8;
      } else {
         g var9 = g.a(this, this.u, var4, var7 - var4.l, (short)0, (short)0, var5);
         this.ah.put(Integer.valueOf(var7), var9);
         return var9;
      }
   }

   public strictfp void a(InputStream var1, boolean var2) {
      this.A.clear();
      l.b();
      m.b();

      String var7;
      try {
         l.e("---- Loading map data ----");
         DocumentBuilderFactory var3 = DocumentBuilderFactory.newInstance();
         var3.setValidating(false);
         DocumentBuilder var4 = var3.newDocumentBuilder();
         var4.setEntityResolver(new b$2(this));
         Document var5 = var4.parse(var1);
         Element var6 = var5.getDocumentElement();
         var7 = var6.getAttribute("orientation");
         if(!var7.equals("orthogonal")) {
            throw new f("Only orthogonal maps are supported, found: " + var7);
         }

         int var9 = Integer.parseInt(var6.getAttribute("width"));
         int var10 = Integer.parseInt(var6.getAttribute("height"));
         this.C = var9;
         this.D = var10;
         l.e("Map size: " + this.C + ", " + this.D);
         this.ar = 150.0F;
         int var11;
         n var12;
         int var13;
         if(this.E) {
            l.e("Setting up team fog..");

            for(var11 = 0; var11 < n.c; ++var11) {
               var12 = n.k(var11);
               if(var12 != null) {
                  var12.L = this.C;
                  var12.M = this.D;
                  var12.N = new byte[this.C][this.D];

                  for(var13 = 0; var13 < this.C; ++var13) {
                     for(int var14 = 0; var14 < this.D; ++var14) {
                        var12.N[var13][var14] = 10;
                     }
                  }
               }
            }
         } else {
            l.e("No team fog on this map..");

            for(var11 = 0; var11 < n.c; ++var11) {
               var12 = n.k(var11);
               if(var12 != null) {
                  var12.N = (byte[][])null;
               }
            }
         }

         Element var35 = (Element)var6.getElementsByTagName("properties").item(0);
         if(var35 != null) {
            NodeList var36 = var35.getElementsByTagName("property");
            if(var36 != null) {
               Properties var8 = new Properties();

               for(var13 = 0; var13 < var36.getLength(); ++var13) {
                  Element var39 = (Element)var36.item(var13);
                  String var15 = var39.getAttribute("name");
                  String var16 = var39.getAttribute("value");
                  var8.setProperty(var15, var16);
               }
            }
         }

         j var37 = null;
         NodeList var38 = var6.getElementsByTagName("tileset");

         for(short var40 = 0; var40 < var38.getLength(); ++var40) {
            Element var41 = (Element)var38.item(var40);
            j var44 = new j(this, var41);
            var44.n = var40;
            if(var37 != null) {
               var37.c(var44.l - 1);
            }

            var37 = var44;
            this.t.add(var44);
         }

         NodeList var42 = var6.getElementsByTagName("layer");

         e var18;
         int var43;
         for(var43 = 0; var43 < var42.getLength(); ++var43) {
            Element var46 = (Element)var42.item(var43);
            String var17 = var46.getAttribute("name");
            if(!"set".equalsIgnoreCase(var17) && !"set-disabled".equalsIgnoreCase(var17)) {
               var18 = new e(this, var46);
               var18.j = var43;
               this.z.add(var18);
            }
         }

         Iterator var45 = this.z.iterator();

         while(var45.hasNext()) {
            e var47 = (e)var45.next();
            if(var47.r) {
               this.u = var47;
            }

            if(var47.k.equalsIgnoreCase("grounddetails")) {
               this.v = var47;
            }

            if(var47.k.equalsIgnoreCase("grounddetails2")) {
               this.w = var47;
            }

            if(var47.k.equalsIgnoreCase("Items") || var47.k.equalsIgnoreCase("Objects")) {
               this.y = var47;
            }

            if(var47.k.equalsIgnoreCase("PathingOverride")) {
               this.x = var47;
            }
         }

         if(this.u == null) {
            throw new f("\'Ground\' layer was not found in map, this layer is required");
         }

         if(this.B == null || this.B.length == 0) {
            throw new f("Invalid map, no tiles have been set");
         }

         int var49;
         if(!l.C() && !l.D()) {
            for(var43 = 0; var43 < this.C; ++var43) {
               for(var49 = 0; var49 < this.D; ++var49) {
                  if(this.u.a(var43, var49) == null) {
                     throw new f("An empty tile on the Ground layer at " + var43 + "," + var49 + " all tiles must be filled");
                  }
               }
            }
         }

         if(this.y == null) {
            throw new f("\'Items\' layer was not found in map, this layer is required");
         }

         NodeList var48 = var6.getElementsByTagName("objectgroup");

         for(var49 = 0; var49 < var48.getLength(); ++var49) {
            Element var50 = (Element)var48.item(var49);
            i var53 = new i(var50, this);
            var53.a = var49;
            this.P.add(var53);
         }

         j.a();
         Iterator var54 = this.t.iterator();

         while(true) {
            if(!var54.hasNext()) {
               j.b();

               for(var49 = 0; var49 <= 1; ++var49) {
                  Iterator var52 = this.z.iterator();

                  while(var52.hasNext()) {
                     var18 = (e)var52.next();
                     boolean var19 = var18 == this.u;
                     boolean var20 = var49 == 0;
                     if(var19 == var20) {
                        var18.w = false;
                        if(var18.s) {
                           for(int var21 = 0; var21 < this.C; ++var21) {
                              for(int var22 = 0; var22 < this.D; ++var22) {
                                 g var23 = var18.a(var21, var22);
                                 if(var23 != null && var23.c == -2) {
                                    var23.c = l.a(var23.a, var23.b);
                                    if(var23.c >= 0) {
                                       int var24 = m.a(var23.a, var23.b);
                                       if(var24 != var23.c) {
                                          throw new RuntimeException("Meta index mismatch: " + var24 + " vs " + var23.c);
                                       }
                                    }

                                    if(var23.c < 0) {
                                       var18.w = true;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               l.c();
               m.c();
               this.Q = this.f("triggers");
               break;
            }

            j var51 = (j)var54.next();
            if(var51.q) {
               var51.c();
            }
         }
      } catch (IOException var26) {
         throw new f("Failed to parse map", var26);
      } catch (ParserConfigurationException var27) {
         throw new RuntimeException("Failed to parse map", var27);
      } catch (SAXException var28) {
         l.e(" --- SAXException: Failed to parse map - " + var28.getMessage() + " ---");

         try {
            l.e("available:" + var1.available());
            var1.reset();
            l.e("after reset:" + var1.available());
         } catch (IOException var25) {
            l.e("-- error writing debug info --");
            var25.printStackTrace();
         }

         throw new f("Failed to parse map - " + var28.getMessage(), var28);
      }

      a var29 = null;
      if(this.Q != null) {
         var29 = this.Q.a("map_info");
      }

      boolean var30 = false;
      boolean var31 = false;
      l var32 = l.B();
      var32.ce = null;
      var7 = null;
      String var33 = null;
      if(var29 != null) {
         String var34 = var29.b("type");
         var33 = var29.b("fog");
         if(!"mission".equalsIgnoreCase(var34) && !"survival".equalsIgnoreCase(var34) && !"challenge".equalsIgnoreCase(var34) && !"skirmish".equalsIgnoreCase(var34)) {
            l.b("Unknown map type:" + var34);
         } else {
            var7 = var34;
         }
      } else {
         l.b("Map type not found on mapInfo");
      }

      if(var7 == null) {
         l.b("Defaulting to skirmish map type");
         var7 = "skirmish";
      } else {
         l.b("Map type: " + var7);
      }

      var32.ce = new com.corrodinggames.rts.gameFramework.n.f();
      var32.ce.a(var2);
      if(var33 != null && !"".equals(var33)) {
         if(!var33.equalsIgnoreCase("none")) {
            var30 = true;
            if(var33.equalsIgnoreCase("los")) {
               var31 = true;
            } else if(!var33.equalsIgnoreCase("map")) {
               l.e("Unknown map fog type: " + var33);
            }
         }
      } else if(l.av() && !var32.N()) {
         var30 = true;
         if(var7 != null && var7.equalsIgnoreCase("skirmish")) {
            var31 = true;
         }
      }

      if(!var30) {
         this.E = false;
      }

      if(var30 && var31) {
         this.F = true;
      }

      this.W = true;
   }

   public strictfp void e() {}

   public strictfp void a(com.corrodinggames.rts.game.l var1) {
      if(!l.aU || l.aW) {
         al.a(var1);
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.y var1, int var2, int var3, int var4, int var5, int var6, int var7, y var8, boolean var9, int var10) {
      l var11 = l.B();
      b var12 = var11.bL;
      com.corrodinggames.rts.game.units.as var13 = var11.bS.ac.i();
      ao var14 = var13.o();

      for(int var15 = var2; var15 <= var4; ++var15) {
         for(int var16 = var3; var16 <= var5; ++var16) {
            boolean var17 = com.corrodinggames.rts.game.units.d.d.a(var1, var13, var14, var15, var16, var10);
            int var18 = var15 * var12.n - var6;
            int var19 = var16 * var12.o - var7;
            this.an.a(var18, var19, var18 + var12.n - 1, var19 + var12.o - 1);
            if(var9) {
               if(var17) {
                  var8.b(this.an, var12.ad);
               } else {
                  var8.b(this.an, var12.af);
                  var8.b(this.an, var12.ae);
               }
            } else if(var17) {
               var8.b(this.an, var12.ac);
            } else {
               var8.b(this.an, var12.ae);
            }
         }
      }

   }

   public static strictfp void f() {
      al.d();
   }

   public strictfp void c(float var1) {
      al.a(var1);
   }

   public strictfp void g() {
      al.c();
   }

   public strictfp void d(float var1) {
      l var2 = l.B();
      boolean var3 = l.at();
      if(var3) {
         var2.bO.a((Lock)e);
      }

      this.c(var1);
      if(var3) {
         var2.bO.b((Lock)e);
      }

      if(this.X) {
         new Rect();
         Rect var5 = new Rect();
         int var6 = this.Y * this.n;
         int var7 = this.Z * this.o;
         var5.a(var6, var7, var6 + this.n, var7 + this.o);
         var5.a(-l.B().cu, -l.B().cv);
      }

   }

   public strictfp void e(float var1) {
      l var2 = l.B();
      this.f(var1);
   }

   public strictfp void h() {
      Iterator var1 = this.t.iterator();

      while(var1.hasNext()) {
         j var2 = (j)var1.next();
         var2.d();
      }

      this.t.clear();
      var1 = this.z.iterator();

      while(var1.hasNext()) {
         e var3 = (e)var1.next();
         var3.b();
      }

      this.z.clear();
      this.P.clear();
      this.Q = null;
      al.c();
   }

   public strictfp j a(int var1) {
      for(int var2 = 0; var2 < this.t.size(); ++var2) {
         j var3 = (j)this.t.get(var2);
         if(var3.d(var1)) {
            return var3;
         }
      }

      return null;
   }

   public strictfp Integer a(com.corrodinggames.rts.game.units.as var1) {
      String var2 = var1.i();
      Integer var3 = this.c("unit", var2);
      if(var3 == null) {
         var3 = this.c("customUnit", var2);
      }

      return var3;
   }

   public strictfp Integer c(String var1, String var2) {
      for(int var3 = 0; var3 < this.t.size(); ++var3) {
         j var4 = (j)this.t.get(var3);
         Integer var5 = var4.b(var1, var2);
         if(var5 != null) {
            return var5;
         }
      }

      return null;
   }

   public strictfp i f(String var1) {
      Iterator var2 = this.P.iterator();

      i var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (i)var2.next();
      } while(!var1.equalsIgnoreCase(var3.b));

      return var3;
   }

   public strictfp float i() {
      return (float)(this.C * this.n);
   }

   public strictfp float j() {
      return (float)(this.D * this.o);
   }

   public strictfp void a(float var1, float var2, int var3, n var4, boolean var5) {
      l var6 = l.B();
      if(this.E) {
         long var7 = 0L;
         if(a) {
            var7 = br.a();
         }

         boolean var9 = true;
         boolean var10 = var4.E;
         if(!var6.ay()) {
            com.corrodinggames.rts.gameFramework.n.f var11 = var6.ce;
            if(var11 != null && !var11.a() && !var11.b()) {
               var9 = false;
            }
         }

         if(!var9) {
            this.b(var1, var2, var3, var4, var5);
         } else {
            for(int var13 = 0; var13 < n.c; ++var13) {
               n var12 = n.k(var13);
               if(var12 != null && (var12 == var4 || !var12.w && (var12.d(var4) || var10))) {
                  this.b(var1, var2, var3, var12, var5);
               }
            }
         }

         if(a) {
            this.ap += br.a() - var7;
         }
      }

   }

   public strictfp byte a(int var1, int var2, byte[][] var3, byte var4) {
      byte var5 = 0;
      int var6 = this.C;
      int var7 = this.D;
      if(var1 >= 1) {
         if(var3[var1 - 1][var2] >= var4) {
            var5 = (byte)(var5 - 128);
         }

         if(var2 >= 1 && var3[var1 - 1][var2 - 1] >= var4) {
            ++var5;
         }

         if(var2 < var7 - 1 && var3[var1 - 1][var2 + 1] >= var4) {
            var5 = (byte)(var5 + 8);
         }
      }

      if(var2 >= 1) {
         if(var3[var1][var2 - 1] >= var4) {
            var5 = (byte)(var5 + 16);
         }

         if(var1 < var6 - 1 && var3[var1 + 1][var2 - 1] >= var4) {
            var5 = (byte)(var5 + 2);
         }
      }

      if(var1 < var6 - 1 && var3[var1 + 1][var2] >= var4) {
         var5 = (byte)(var5 + 32);
      }

      if(var2 < var7 - 1) {
         if(var3[var1][var2 + 1] >= var4) {
            var5 = (byte)(var5 + 64);
         }

         if(var1 < var6 - 1 && var3[var1 + 1][var2 + 1] >= var4) {
            var5 = (byte)(var5 + 4);
         }
      }

      if(var5 == 127) {
         var5 = -1;
      }

      return var5;
   }

   public strictfp void k() {
      this.l();

      for(int var1 = 0; var1 < this.C; ++var1) {
         for(int var2 = 0; var2 < this.D; ++var2) {
            this.M[var1][var2] = 0;
            this.N[var1][var2] = 0;
         }
      }

   }

   public strictfp void f(int var1, int var2) {
      this.M[var1][var2] = 0;
      this.N[var1][var2] = 0;
   }

   public strictfp void g(int var1, int var2) {
      int var3 = var1 - 1;
      int var4 = var2 - 1;
      if(var3 < 0) {
         var3 = 0;
      }

      if(var4 < 0) {
         var4 = 0;
      }

      int var5 = var1 + 1;
      int var6 = var2 + 1;
      if(var5 > this.C - 1) {
         var5 = this.C - 1;
      }

      if(var6 > this.D - 1) {
         var6 = this.D - 1;
      }

      for(int var7 = var3; var7 <= var5; ++var7) {
         for(int var8 = var4; var8 <= var6; ++var8) {
            if(this.M[var7][var8] != 0) {
               this.M[var7][var8] = 127;
            }

            if(this.N[var7][var8] != 0) {
               this.N[var7][var8] = 127;
            }
         }
      }

   }

   public strictfp void l() {
      boolean var1 = false;
      if(this.M == null) {
         var1 = true;
      } else if(this.M.length != this.C || this.M[0].length != this.D) {
         l.e("smoothFog_cache: Size mismatch");
         var1 = true;
      }

      if(var1) {
         l.e("Building smoothFog_cache");
         this.M = new byte[this.C][this.D];
         this.N = new byte[this.C][this.D];

         for(int var2 = 0; var2 < this.C; ++var2) {
            for(int var3 = 0; var3 < this.D; ++var3) {
               this.M[var2][var3] = 127;
               this.N[var2][var3] = 127;
            }
         }
      }

   }

   public strictfp void b(float var1, float var2, int var3, n var4, boolean var5) {
      l var6 = l.B();
      if(this.E && var4.N != null) {
         this.l();
         float var8 = (float)((var3 - 5) * (var3 - 5));
         float var9 = (float)((var3 - 3) * (var3 - 3));
         float var10 = (float)(var3 * var3);
         float var11 = 1.0F / (var10 - var9) * 10.0F;
         this.a(var1, var2);
         int var13 = this.T;
         int var14 = this.U;
         float var15 = var1 * this.r;
         float var16 = var2 * this.s;
         byte[][] var17 = var4.N;
         int var18 = var3 - 1;
         int var19 = var13 - var18;
         int var20 = var14 - var18;
         if(var19 < 0) {
            var19 = 0;
         }

         if(var20 < 0) {
            var20 = 0;
         }

         int var21 = var13 + var18;
         int var22 = var14 + var18;
         if(var21 > this.C - 1) {
            var21 = this.C - 1;
         }

         if(var22 > this.D - 1) {
            var22 = this.D - 1;
         }

         c var23 = al;
         boolean var24 = false;
         boolean var25 = var4.q();

         for(int var26 = var19; var26 <= var21; ++var26) {
            for(int var27 = var20; var27 <= var22; ++var27) {
               byte var28 = var17[var26][var27];
               if(var28 != 0) {
                  float var29 = com.corrodinggames.rts.gameFramework.f.a(var15, var16, (float)var26, (float)var27);
                  if(var29 <= var9) {
                     if(var28 > 0) {
                        var17[var26][var27] = 0;
                        if(var25) {
                           var23.a(var26, var27, true);
                           var24 = true;
                           if(var29 <= var8 && var5) {
                              this.f(var26, var27);
                           } else {
                              this.g(var26, var27);
                           }
                        }
                     }
                  } else if(var29 <= var10) {
                     byte var30 = (byte)((int)((var29 - var9) * var11));
                     if(var28 > var30) {
                        var17[var26][var27] = var30;
                        if(var25) {
                           var23.a(var26, var27, true);
                           var24 = true;
                           this.g(var26, var27);
                        }
                     }
                  }
               }
            }
         }

         if(var24) {
            var6.bW.O = true;
         }
      }

   }

   public strictfp void f(float var1) {
      l var2 = l.B();
      if(a) {
         this.aq += var1;
         if(this.aq > 60.0F) {
            this.aq = 0.0F;
            if(this.ap > 0L) {
               l.e("seeThoughFogOfWarTimes: " + br.b((double)this.ap));
               this.ap = 0L;
            }

            if(this.ap < 0L) {
               l.e("seeThoughFogOfWarTimes negative: " + br.b((double)this.ap));
               this.ap = 0L;
            }
         }
      }

      if(this.E && this.F) {
         this.l();
         this.ar += var1;
         if(this.ar > 260.0F) {
            this.ar = 0.0F;
            w[] var5 = am.er.a();
            int var6 = w.er.size();
            boolean var7 = false;

            int var8;
            for(var8 = 0; var8 < n.c; ++var8) {
               n var9 = n.k(var8);
               if(var9 != null && !var9.G) {
                  var7 = true;

                  for(int var10 = 0; var10 < var6; ++var10) {
                     w var11 = var5[var10];
                     if(var11 instanceof com.corrodinggames.rts.game.units.y) {
                        com.corrodinggames.rts.game.units.y var12 = (com.corrodinggames.rts.game.units.y)var11;
                        if(var12.bI()) {
                           var12.g(var9);
                        }
                     }
                  }

                  if(var9.N == null) {
                     l.b("fogOfWar_map==null for:" + var8);
                  }

                  boolean var17 = false;
                  boolean var19 = var9.q();
                  byte[][] var20 = var9.N;
                  byte[][] var13 = this.N;

                  for(int var14 = 0; var14 < this.C; ++var14) {
                     for(int var15 = 0; var15 < this.D; ++var15) {
                        if(var20[var14][var15] < 5) {
                           var20[var14][var15] = 5;
                           if(var19) {
                              al.a(var14, var15, true);
                              var17 = true;
                              var13[var14][var15] = 127;
                           }
                        }
                     }
                  }

                  if(var17) {
                     var2.bW.O = true;
                  }
               }
            }

            w var16;
            com.corrodinggames.rts.game.units.y var18;
            for(var8 = 0; var8 < var6; ++var8) {
               var16 = var5[var8];
               if(var16 instanceof com.corrodinggames.rts.game.units.y) {
                  var18 = (com.corrodinggames.rts.game.units.y)var16;
                  if(!var18.bV) {
                     var18.c(false);
                  }
               }
            }

            if(var7) {
               for(var8 = 0; var8 < var6; ++var8) {
                  var16 = var5[var8];
                  if(var16 instanceof com.corrodinggames.rts.game.units.y) {
                     var18 = (com.corrodinggames.rts.game.units.y)var16;
                     if(var18.bI()) {
                        var18.cX();
                     }
                  }
               }
            }
         }
      }

   }

   public strictfp void a(as var1) {
      var1.a(false);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      boolean var2 = var1.e();
      if(var2) {
         int var3 = var1.f();
         int var4 = var1.f();

         for(int var5 = 0; var5 < var3; ++var5) {
            for(int var6 = 0; var6 < var4; ++var6) {
               var1.d();
            }
         }
      }

   }

   private strictfp InputStream a(String var1, String var2, int var3) {
      String[] var4 = var2.split("/");
      if(var4.length >= var3) {
         String var5 = "";
         boolean var6 = true;

         for(int var7 = var4.length - var3; var7 < var4.length; ++var7) {
            if(!var6) {
               var5 = var5 + "/";
            }

            var6 = false;
            var5 = var5 + var4[var7];
         }

         return com.corrodinggames.rts.gameFramework.e.a.j(var1 + var5);
      } else {
         return null;
      }
   }

   public strictfp InputStream d(String var1, String var2) {
      Object var3 = null;
      var3 = com.corrodinggames.rts.gameFramework.e.a.j(var1 + var2);
      if(var3 == null) {
         var3 = this.a(var1, var2, 3);
      }

      if(var3 == null) {
         var3 = this.a(var1, var2, 2);
      }

      if(var3 == null) {
         var3 = this.a(var1, var2, 1);
      }

      if(var3 == null) {
         throw new IOException("File could not be found:" + var1 + var2);
      } else {
         return (InputStream)var3;
      }
   }

   public strictfp boolean a(n var1, int var2, int var3) {
      return this.G || !this.E || var1.N == null || !this.c(var2, var3) || var1.N[var2][var3] != 10;
   }

}
