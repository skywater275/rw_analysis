package com.corrodinggames.rts.java;

import android.content.Context;
import android.content.ServerContext;
import android.graphics.Point;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.av;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.j.ac;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.m.x;
import com.corrodinggames.rts.gameFramework.utility.aj;
import com.corrodinggames.rts.java.Main$1;
import com.corrodinggames.rts.java.Main$2;
import com.corrodinggames.rts.java.Main$3;
import com.corrodinggames.rts.java.Main$4;
import com.corrodinggames.rts.java.b;
import com.corrodinggames.rts.java.d;
import com.corrodinggames.rts.java.e;
import com.corrodinggames.rts.java.i;
import com.corrodinggames.rts.java.j;
import com.corrodinggames.rts.java.k;
import com.corrodinggames.rts.java.l;
import com.corrodinggames.rts.java.o;
import com.corrodinggames.rts.java.s;
import com.corrodinggames.rts.java.u;
import com.corrodinggames.rts.java.v;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.opengl.renderer.VBORenderer;

public class Main extends ac {

   public static boolean a = false;
   public static boolean b = true;
   public static String c = "Rusted Warfare";
   public d d;
   public String e = "#28";
   aj f = new aj();
   boolean g = true;
   public ad h;
   com.corrodinggames.rts.java.b.a i;
   public u j;
   b k;
   String[] l;
   static Main m;
   int n;
   long o = System.nanoTime();
   com.corrodinggames.rts.java.d.a p;
   com.corrodinggames.rts.gameFramework.n q = new i(this);
   Thread r;
   boolean s = true;
   Object t = new Object();
   public boolean u;
   public int v;


   public static void main(String[] var0) {
      m = new Main();
      m.a(var0);
      new Main$1();
   }

   public static void a(String var0) {
      com.corrodinggames.rts.gameFramework.l.e(var0);
   }

   public void f() {
      com.corrodinggames.rts.gameFramework.utility.l var1 = new com.corrodinggames.rts.gameFramework.utility.l(new InputStreamReader(System.in));

      while(this.g) {
         try {
            String var2 = var1.a();
            if(var2 == null) {
               try {
                  Thread.sleep(200L);
               } catch (InterruptedException var5) {
                  var5.printStackTrace();
               }
            } else {
               this.a((com.corrodinggames.rts.gameFramework.j.c)null, "ADMIN", var2, true);
            }
         } catch (IOException var7) {
            if(this.n < 3) {
               com.corrodinggames.rts.gameFramework.l.e("Error while reading stdin: " + var7.toString());
               ++this.n;
               if(this.n == 3) {
                  com.corrodinggames.rts.gameFramework.l.e("Too many stdin errors, ignoring");
               }
            }

            try {
               Thread.sleep(700L);
            } catch (InterruptedException var6) {
               var6.printStackTrace();
            }
         }
      }

   }

   public void g() {
      Semaphore var1 = new Semaphore(0);
      Thread var2 = new Thread(new Main$2(this, var1));
      var2.setDaemon(true);
      var2.start();

      try {
         var1.acquire();
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

   }

   public synchronized void a(String[] var1) {
      this.l = var1;
      boolean var2 = false;
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      Integer var10 = null;
      Integer var11 = null;
      com.corrodinggames.rts.gameFramework.l.e("Reading args");
      String var12 = null;
      String var13 = null;

      int var14;
      String var15;
      for(var14 = 0; var14 < var1.length; ++var14) {
         var15 = var1[var14].trim().toLowerCase(Locale.ENGLISH);
         if(var12 != null) {
            if(var12.equals("+connect_lobby")) {
               a("connect lobby:" + var15);
               com.corrodinggames.rts.gameFramework.l.aK = var15;
               var12 = null;
            } else if(var12.equals("-width")) {
               var10 = Integer.valueOf(Integer.parseInt(var15));
               var12 = null;
            } else if(var12.equals("-height")) {
               var11 = Integer.valueOf(Integer.parseInt(var15));
               var12 = null;
            } else {
               a("Unknown two_part_arg: " + var12);
               var12 = null;
            }
         } else {
            String var16;
            if(var15.equals("-debug")) {
               ++var14;
               if(var14 >= var1.length) {
                  a("-debug requires parameters");
                  System.exit(1);
               }

               var16 = var1[var14];
               int var17 = Integer.parseInt(var16.split(":")[0]);
               String var18 = var16.split(":")[1];
               com.corrodinggames.rts.a.a.a(var17, var18);
            } else if(var15.equals("-debugscript")) {
               ++var14;
               if(var14 >= var1.length) {
                  a("-debugscript requires parameters");
                  System.exit(1);
               }

               var16 = var1[var14];
               com.corrodinggames.rts.a.a.a(var16);
            } else if(var15.equals("-log")) {
               ++var14;
               if(var14 >= var1.length) {
                  a("-log requires parameters");
                  System.exit(1);
               }

               var16 = var1[var14];

               try {
                  PrintStream var27 = new PrintStream(var16);
                  System.setOut(var27);
                  System.setErr(var27);
                  com.corrodinggames.rts.gameFramework.l.e("File logging started");
               } catch (FileNotFoundException var24) {
                  com.corrodinggames.rts.gameFramework.l.a("Cannot open log file:" + var16);
                  var24.printStackTrace();
               }
            } else if(!var15.equals("-nologfile")) {
               if(var15.equals("-lang")) {
                  ++var14;
                  if(var14 >= var1.length) {
                     a("-lang requires parameters");
                     System.exit(1);
                  }

                  var16 = var1[var14];
                  com.corrodinggames.rts.gameFramework.h.a.d = var16;
               } else if(var15.equals("-logcolor")) {
                  com.corrodinggames.rts.gameFramework.l.ax = true;
               } else if(var15.equals("-nodisplay")) {
                  var2 = true;
               } else if(var15.equals("-canvasgl")) {
                  com.corrodinggames.rts.gameFramework.l.aD = true;
               } else if(var15.equals("-replay_debug")) {
                  com.corrodinggames.rts.gameFramework.l.aw = true;
               } else if(var15.equals("-nopreferipv4")) {
                  var5 = true;
               } else if(var15.equals("-noresources")) {
                  com.corrodinggames.rts.gameFramework.l.aB = true;
               } else if(var15.equals("-nosound")) {
                  var3 = true;
               } else if(var15.equals("-nomusic")) {
                  var4 = true;
               } else if(var15.equals("-safemode")) {
                  com.corrodinggames.rts.gameFramework.l.aO = true;
               } else if(var15.equals("-extrasafemode")) {
                  com.corrodinggames.rts.gameFramework.l.aP = true;
               } else if(var15.equals("-disable_vbos")) {
                  var8 = true;
               } else if(var15.equals("-disable_atlas")) {
                  com.corrodinggames.rts.gameFramework.l.aC = true;
               } else if(var15.equals("-force_vbos")) {
                  var9 = true;
               } else if(var15.equals("-allowsoftwarerender")) {
                  var6 = true;
               } else if(var15.equals("-fullscreen")) {
                  var7 = true;
               } else if(var15.equals("-nobackground")) {
                  com.corrodinggames.rts.gameFramework.l.ay = true;
               } else if(var15.equals("-nomods")) {
                  com.corrodinggames.rts.gameFramework.l.aJ = true;
               } else if(var15.equals("-printunits")) {
                  com.corrodinggames.rts.gameFramework.l.aE = true;
               } else if(var15.equals("-outputunitimages")) {
                  com.corrodinggames.rts.gameFramework.l.aF = true;
               } else if(var15.equals("-oldreplays")) {
                  com.corrodinggames.rts.gameFramework.l.aG = true;
               } else if(var15.equals("-teamshaders")) {
                  com.corrodinggames.rts.gameFramework.l.aN = true;
               } else if(var15.equals("-noteamshaders")) {
                  com.corrodinggames.rts.gameFramework.l.aN = false;
               } else if(var15.equals("-devdebug")) {
                  ++var14;
                  if(var14 >= var1.length) {
                     a("-debugscript requires parameters");
                     System.exit(1);
                  }

                  var16 = var1[var14];
                  com.corrodinggames.rts.gameFramework.l.aQ = var16;
               } else if(var15.equals("-postprocessing")) {
                  com.corrodinggames.rts.gameFramework.l.aM = true;
               } else if(var15.equals("-nopostprocessing")) {
                  com.corrodinggames.rts.gameFramework.l.aM = false;
               } else if(var15.equals("-disabletextureread")) {
                  s.F = false;
               } else if(var15.equals("-sandbox")) {
                  com.corrodinggames.rts.gameFramework.l.aI = true;
               } else if(var15.equals("-steam")) {
                  com.corrodinggames.rts.gameFramework.l.aH = true;
               } else if(!var15.equals("-width") && !var15.equals("-height")) {
                  if(var15.startsWith("+")) {
                     if(var15.equals("+connect_lobby")) {
                        var12 = var15;
                     } else {
                        a("Unknown steam option: " + var15);
                     }
                  } else if(var15.trim().length() != 0) {
                     a("Unknown option: " + var15);
                     var13 = "Unknown option: " + var15;
                  }
               } else {
                  var12 = var15;
               }
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("Game arguments:");

      for(var14 = 0; var14 < var1.length; ++var14) {
         var15 = var1[var14].trim().toLowerCase(Locale.ENGLISH);
         a("arg: " + var15);
      }

      if(var13 != null) {
         if(com.corrodinggames.rts.gameFramework.l.aH) {
            a("Unknown options but running anyway due to being in steam");
         } else {
            a("Exiting due to unknown option: " + var13);
            System.exit(1);
         }
      }

      com.corrodinggames.rts.gameFramework.l.aU = true;
      com.corrodinggames.rts.gameFramework.l.aq();
      String var26 = System.getProperty("os.name");
      com.corrodinggames.rts.gameFramework.l.e("OS name: " + var26);
      com.corrodinggames.rts.gameFramework.l.e("OS version: " + System.getProperty("os.version"));
      com.corrodinggames.rts.gameFramework.l.e("LWJGL version: " + Sys.getVersion());
      com.corrodinggames.rts.gameFramework.l.e("Build Number: " + this.e);
      com.corrodinggames.rts.gameFramework.l.e("Game Version: 1.15");
      com.corrodinggames.rts.gameFramework.l.e("Game Code: 176");
      com.corrodinggames.rts.game.i.b = Sys.is64Bit();
      com.corrodinggames.rts.gameFramework.l.e("Is 64bit: " + com.corrodinggames.rts.game.i.b);
      com.corrodinggames.rts.gameFramework.l.e("JVM maxMemory:" + Runtime.getRuntime().maxMemory());
      com.corrodinggames.rts.gameFramework.l.e("JVM totalMemory:" + Runtime.getRuntime().totalMemory());
      com.corrodinggames.rts.gameFramework.l.e("JVM freeMemory:" + Runtime.getRuntime().freeMemory());
      if(var26 != null && var26.toLowerCase().contains("mac os")) {
         com.corrodinggames.rts.game.i.c = true;
      }

      if(var5) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping preferIPv4Stack=true");
      } else {
         System.setProperty("java.net.preferIPv4Stack", "true");
      }

      if(com.corrodinggames.rts.gameFramework.l.aH) {
         com.corrodinggames.rts.gameFramework.o.a.a = new com.corrodinggames.rts.java.c.b();
         com.corrodinggames.rts.gameFramework.l.e("Early steam init");
         com.corrodinggames.rts.gameFramework.o.a.a().b();
         com.corrodinggames.rts.gameFramework.l.e("Early steam init done.");
      } else {
         com.corrodinggames.rts.gameFramework.l.e("steam not requested");
      }

      this.g();
      var15 = c;
      if(var2) {
         var15 = "";
      }

      Input.disableControllers();
      if(a) {
         Renderer.setRenderer(2);
      }

      if(!var9 && com.corrodinggames.rts.game.i.c) {
         com.corrodinggames.rts.gameFramework.l.e("Disabling vbo on mac (without force option)");
         var8 = true;
      }

      if(var8) {
         com.corrodinggames.rts.gameFramework.l.e("disable_vbos requested");
         SGL var28 = Renderer.get();
         if(var28 instanceof VBORenderer) {
            VBORenderer var29 = (VBORenderer)var28;
            var29.disableVBOs();
         } else {
            com.corrodinggames.rts.gameFramework.l.e("Failed to disable VBOs, wrong class");
         }
      }

      e.c();
      this.j = new u(var15);
      this.j.b = this;
      this.j.i = var2;
      this.j.j = var3;
      this.j.k = var4;
      float var30;
      float var31;
      if(var2) {
         com.corrodinggames.rts.gameFramework.l.a("Skipping display mode call");
         var30 = 800.0F;
         var31 = 600.0F;
      } else {
         try {
            DisplayMode var32 = Display.getDisplayMode();
            var30 = (float)var32.getHeight();
            var31 = (float)var32.getWidth();
         } catch (Exception var23) {
            com.corrodinggames.rts.gameFramework.l.a("Failed to get display mode, defaulting to min size");
            var23.printStackTrace();
            var30 = 800.0F;
            var31 = 600.0F;
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("screenHeight:" + var30);
      com.corrodinggames.rts.gameFramework.l.e("screenWidth:" + var31);
      int var33 = 1000;
      int var19 = 733;
      if(var30 > 800.0F) {
         var33 = 1000;
         var19 = 800;
      }

      if(var30 > 900.0F) {
         var33 = 1600;
         var19 = 900;
      }

      if(var2) {
         var33 = 10;
         var19 = 10;
      }

      if(var10 != null) {
         com.corrodinggames.rts.gameFramework.l.e("Overriding width to:" + var10);
         var33 = var10.intValue();
      }

      if(var11 != null) {
         com.corrodinggames.rts.gameFramework.l.e("Overriding height to:" + var11);
         var19 = var11.intValue();
      }

      if(var6) {
         com.corrodinggames.rts.gameFramework.l.e("allowSoftwareOpenGL is now on");
         System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
      }

      this.j.l = false;
      boolean var20 = var7;

      try {
         if(this.j.l) {
            this.j.a(var33 * 2, var19 * 2);
            this.k = new b(new ScalableGame(this.j, var33, var19), var33, var19, var20);
         } else {
            this.k = new b(this.j, var33, var19, var20);
         }
      } catch (SlickException var22) {
         throw new RuntimeException(var22);
      }

      this.j.c = this.k;
      Display.setResizable(true);
      j var25 = new j(this);
      this.r = new Thread(var25);
      this.r.setDaemon(false);
      this.r.start();
   }

   public void b(String var1) {
      this.q.a(var1, true);
   }

   public synchronized void h() {
      this.b("displayModes");
      this.b("starting controllers");
      this.o = System.nanoTime();
      com.corrodinggames.rts.gameFramework.l.aU = true;
      com.corrodinggames.rts.gameFramework.l.bb = true;
      if(!com.corrodinggames.rts.gameFramework.l.aB) {
         if(com.corrodinggames.rts.gameFramework.l.aD) {
            com.corrodinggames.rts.gameFramework.l.aX = true;
            com.corrodinggames.rts.gameFramework.l.aW = true;
            com.corrodinggames.rts.gameFramework.l.bg = x.class;
         } else {
            com.corrodinggames.rts.gameFramework.l.aX = true;
            com.corrodinggames.rts.gameFramework.l.aW = true;
            com.corrodinggames.rts.gameFramework.l.bg = e.class;
         }
      }

      if(this.j != null && !this.j.j) {
         byte var1 = 20;
         OpenALAudio var2 = new OpenALAudio(var1, 9, 512);
         com.corrodinggames.rts.gameFramework.l.e("openALAudio hasDevice:" + var2.hasDevice());
         com.corrodinggames.rts.gameFramework.a.e.c = new o(var2);
         if(this.j.k) {
            com.corrodinggames.rts.gameFramework.l.e("Music disabled");
            am.a = new av();
         } else {
            am.a = new l(var2);
         }
      } else {
         com.corrodinggames.rts.gameFramework.l.b("Disabling sound with NullSoundFactory");
         com.corrodinggames.rts.gameFramework.a.e.c = new com.corrodinggames.rts.gameFramework.a.f();
         am.a = new av();
      }

      com.corrodinggames.rts.gameFramework.j.n.d = new k();
      com.corrodinggames.rts.gameFramework.ac.b = new v();
      long var9 = br.a();
      this.b("loading libRocket");
      com.corrodinggames.rts.gameFramework.l.e("start libRocket setup");
      this.d = new d();
      this.i = com.corrodinggames.rts.java.b.a.p();
      this.i.f = this;
      this.p = new com.corrodinggames.rts.java.d.a();
      this.i.a(this.p, this.d);
      this.p.debug = false;
      this.p.setup();
      this.b("libRocket - fonts");
      this.p.loadFont("font/Delicious-Roman.otf");
      this.p.loadFont("font/Delicious-Italic.otf");
      this.p.loadFont("font/Delicious-Bold.otf");
      this.p.loadFont("font/Delicious-BoldItalic.otf");
      this.p.loadFont("font/Roboto-Regular.ttf");
      this.p.loadFont("font/Roboto-Bold.ttf");
      com.corrodinggames.rts.gameFramework.l.e("NotoSansCJKsc start");
      this.p.loadFont("font/NotoSansCJKsc-Regular.otf", "notoSans");
      this.p.loadFont("font/DroidSansFallback.ttf", "fallback");
      com.corrodinggames.rts.gameFramework.l.e("NotoSansCJKsc end");
      this.i.c();
      com.corrodinggames.rts.gameFramework.l.e("end libRocket setup");
      this.b("GuiEngine");
      br.a("libRocket setup took:", var9);
      com.corrodinggames.rts.gameFramework.l.dz = this.e;
      ServerContext var3 = new ServerContext();
      this.b("GameEngine");
      int var4 = this.j.a.getWidth();
      int var5 = this.j.a.getHeight();
      com.corrodinggames.rts.gameFramework.l.ck = new Point(var4, var5);
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.a((Context)var3, this.q);
      this.b("GameEngine ready");
      com.corrodinggames.rts.gameFramework.l.e("version: " + var6.u() + " " + var6.c(false) + ":" + this.e);
      this.i.b();
      com.corrodinggames.rts.a.a.b();
      this.h = var6.bX;
      var6.bQ.showZoomButton = false;
      var6.bQ.showUnitGroups = false;
      this.j.a(this.d);
      this.j.a(1000, 800);
      long var7 = System.nanoTime();
      com.corrodinggames.rts.gameFramework.l.e("-----------------------------");
      com.corrodinggames.rts.gameFramework.l.e("----- Game init finished in:" + (double)(var7 - this.o) / 1000000.0D + " ms");
      var6.bX.d = this;
      var6.bX.y = "unset";
      if(!com.corrodinggames.rts.gameFramework.l.ay) {
         ;
      }

   }

   public void b() {
      Main$3 var1 = new Main$3(this);
      this.f.a(var1);
   }

   public void a(float var1) {
      this.f.a();
   }

   public void a(boolean var1) {
      this.g = false;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var1) {
         var2.bX.u();
      } else {
         var2.bX.b("shutdownServer");
      }

      try {
         try {
            Thread.sleep(100L);
         } catch (InterruptedException var4) {
            var4.printStackTrace();
         }

         System.exit(0);
      } catch (SecurityException var5) {
         var5.printStackTrace();
      }

   }

   public synchronized boolean a(com.corrodinggames.rts.gameFramework.j.c var1, String var2, String var3) {
      return true;
   }

   public synchronized void b(com.corrodinggames.rts.gameFramework.j.c var1, String var2, String var3) {
      this.a(var1, var2, var3, false);
   }

   public void c() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var1.bX.aW) {
         ScriptEngine var2 = ScriptEngine.getInstance();
         if(var2 != null) {
            var2.addScriptToQueueIfNotAlreadyQueued("mp.refreshUI()");
         }
      }

   }

   public synchronized void a(int var1, String var2, String var3, com.corrodinggames.rts.gameFramework.j.c var4) {
      if(this.p != null && this.p.c != null) {
         this.p.c.addRunnableToQueue(new Main$4(this, var1, var2, var3, var4));
      } else {
         com.corrodinggames.rts.gameFramework.l.T();
      }

   }

   public synchronized void a(com.corrodinggames.rts.gameFramework.j.c var1, String var2, String var3, boolean var4) {
      if(!var4) {
         a(var2 + ": " + var3);
      }

      if(!this.s) {
         ;
      }
   }

   public String a(com.corrodinggames.rts.gameFramework.j.c var1, String var2) {
      return null;
   }

   public synchronized void c(com.corrodinggames.rts.gameFramework.j.c var1, String var2, String var3) {}

   public synchronized void b(com.corrodinggames.rts.gameFramework.j.c var1, String var2) {}

   public void i() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.j.g();
   }

   public void d() {
      com.corrodinggames.librocket.a.a().o();
   }

   public void a(ae var1) {
      com.corrodinggames.librocket.a.a().a(var1);
   }

}
