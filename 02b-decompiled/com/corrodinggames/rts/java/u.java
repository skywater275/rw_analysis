package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import com.corrodinggames.rts.java.Main;
import com.corrodinggames.rts.java.b;
import com.corrodinggames.rts.java.d;
import com.corrodinggames.rts.java.e;
import com.corrodinggames.rts.java.s;
import com.corrodinggames.rts.java.u$1;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.OpenGLException;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class u extends BasicGame {

   GameContainer a;
   Main b;
   b c;
   com.corrodinggames.rts.gameFramework.m.n d;
   com.corrodinggames.rts.gameFramework.l e;
   d f;
   boolean g = false;
   Object h = new Object();
   boolean i = false;
   boolean j = false;
   boolean k = false;
   boolean l = false;
   s m;
   s n;
   boolean o;
   boolean p = false;
   boolean q = false;
   boolean r = false;
   boolean s = false;
   int t;
   boolean u = false;
   boolean v;
   float w;
   float x;
   boolean y = false;
   private boolean[] Z = new boolean[224];
   int z = 0;
   float A = 0.0F;
   float B = 0.0F;
   int C = 0;
   int D = 0;
   int E;
   int F;
   int G;
   int H;
   boolean I;
   boolean J;
   float K = 0.0F;
   int L = 0;
   String M = "";
   String N = "";
   long O = -9999L;
   float P = 1.0F;
   float Q = 1.0F;
   float R = 1.0F;
   int S = 100;
   int T = 100;
   long U;
   float V = 0.0F;
   float W = 0.0F;
   int X;
   com.corrodinggames.rts.game.j Y;


   public u(String var1) {
      super(var1);
   }

   public boolean closeRequested() {
      if(this.i) {
         return true;
      } else if(com.corrodinggames.rts.a.a.a()) {
         return true;
      } else if(this.c != null && !this.c.isFullscreen()) {
         ScriptEngine.getInstance().addScriptToQueue("askQuitGame();");
         return false;
      } else {
         return true;
      }
   }

   public void init(GameContainer var1) {
      this.a = var1;
      var1.setAlwaysRender(true);
      var1.setForceExit(true);
      var1.setShowFPS(false);
      var1.setTargetFrameRate(300);
      if(com.corrodinggames.rts.gameFramework.l.ay) {
         var1.setShowFPS(true);
         var1.setTargetFrameRate(30);
      }

      var1.setIcons(new String[]{"res/drawable/icon_window.png", "res/drawable/icon_window128.png", "res/drawable/icon_window24.png", "res/drawable/icon_window16.png"});
      var1.setUpdateOnlyWhenVisible(false);
      var1.getInput().enableKeyRepeat();
      this.m = e.b(R$drawable.logo, true);
      this.n = e.b(R$drawable.pointer, true);
      var1.setMouseCursor(this.n.C(), 0, 0);
      this.U = System.currentTimeMillis();
   }

   public void a() {
      this.e = com.corrodinggames.rts.gameFramework.l.B();
      if(this.a.isVSyncRequested() != this.e.bQ.renderVsync) {
         try {
            this.a.setVSync(this.e.bQ.renderVsync);
         } catch (OpenGLException var2) {
            com.corrodinggames.rts.gameFramework.l.a("Error while changing vsync setting", (Throwable)var2);
         }
      }

      if(this.e.bQ.highRefreshRate) {
         this.a.setTargetFrameRate(300);
      } else {
         this.a.setTargetFrameRate(120);
      }

      boolean var1 = false;
      if(this.e.bQ.enableMouseCapture && (!this.e.bQ.slick2dFullScreen || this.e.cU) && !this.e.aq && !this.e.a(false) && this.e.bq) {
         var1 = true;
      }

      if(var1 != this.v) {
         com.corrodinggames.rts.gameFramework.l.e("Grabbing mouse:" + var1);
         this.v = var1;
         if(!this.u) {
            this.a.setMouseGrabbed(this.v);
         }

         if(this.v) {
            ;
         }

         if(!this.v) {
            Mouse.setCursorPosition((int)(this.w * this.P), (int)((float)Display.getHeight() - this.x * this.P));
         }

         com.corrodinggames.rts.gameFramework.l.aR = this.v;
      }

      this.a.setSmoothDeltas(this.e.bQ.renderSmoothDelta);
      if(this.o != Display.isActive()) {
         this.o = Display.isActive();
         if(this.o) {
            this.f();
         }
      }

   }

   public void b() {
      if(this.p) {
         com.corrodinggames.rts.gameFramework.l.g("loadingStartedThreaded");
      } else {
         this.p = true;
         this.c();
      }
   }

   public void c() {
      if(this.q) {
         com.corrodinggames.rts.gameFramework.l.g("loadingStartedNonThreaded");
      } else {
         this.q = true;
         if(this.b == null) {
            this.b = new Main();
         }

         this.b.h();
         if(com.corrodinggames.rts.gameFramework.l.aI) {
            com.corrodinggames.rts.gameFramework.l.e("switching to sandbox");
            ScriptEngine.getInstance().addScriptToQueue("open(\'sandboxOptions.rml\', \'maps/skirmish/[z;p10]Crossing Large (10p).tmx\'); loadConfigAndStartNewSandbox(\'maps/skirmish/[z;p10]Crossing Large (10p).tmx\');");
         }

         this.r = true;
      }
   }

   public void a(d var1) {
      com.corrodinggames.rts.gameFramework.l.b("SlickContainer:setup");
      this.e = com.corrodinggames.rts.gameFramework.l.B();
      this.f = var1;
      this.f.d = new com.corrodinggames.rts.appFramework.m();
      this.f.a = this.a.getWidth();
      this.f.b = this.a.getHeight();
      this.e.b(this.f.a, this.f.b);
      this.d = new com.corrodinggames.rts.gameFramework.m.n();
   }

   public void update(GameContainer var1, int var2) {
      this.t = var2;
   }

   public int a(int var1) {
      if(var1 == 0) {
         return 1;
      } else if(var1 == 1) {
         return 2;
      } else if(var1 == 2) {
         return 3;
      } else {
         com.corrodinggames.rts.gameFramework.l.e("Unknown mouse button:" + var1);
         return 0;
      }
   }

   public void a(int var1, int var2, boolean var3) {
      if(this.y) {
         com.corrodinggames.rts.gameFramework.l.e("mouseGrab:m:" + var3 + ",newX:" + var1 + ",newY:" + var2);
      }

      if(!this.v) {
         this.w = (float)((int)((float)var1 / this.P));
         this.x = (float)((int)((float)var2 / this.P));
      } else {
         if(var3) {
            int var4;
            int var5;
            if(this.u) {
               var4 = var1 - this.S;
               var5 = var2 - this.T;
            } else {
               var4 = var1;
               var5 = var2;
            }

            this.w += (float)var4 / this.P;
            this.x += (float)var5 / this.P;
         } else {
            this.w = (float)((int)((float)var1 / this.P));
            this.x = (float)((int)((float)var2 / this.P));
         }

         if(this.w < 0.0F) {
            this.w = 0.0F;
         }

         if(this.x < 0.0F) {
            this.x = 0.0F;
         }

         if(this.f != null) {
            if(this.w > (float)(this.f.o() - 1)) {
               this.w = (float)(this.f.o() - 1);
            }

            if(this.x > (float)(this.f.p() - 1)) {
               this.x = (float)(this.f.p() - 1);
            }
         } else {
            com.corrodinggames.rts.gameFramework.l.e("processMouseGrab gameView==null");
         }
      }

   }

   public void mousePressed(int var1, int var2, int var3) {
      this.a(var2, var3, false);
      if(this.d()) {
         this.a(this.w, this.x);
         this.b.p.processMouseButtonDown(0, 0);
      } else if(this.f != null) {
         int var4 = this.a(var1);
         if(var4 != 0) {
            this.f.d.a(this.w, this.x, true, var4);
         }

      }
   }

   public void a(float var1, float var2) {
      var1 *= this.P;
      var2 *= this.P;
      var1 /= this.R;
      var2 /= this.R;
      this.b.p.mouseMove((int)var1, (int)var2, 0);
   }

   public void mouseDragged(int var1, int var2, int var3, int var4) {
      this.a(var3, var4, true);
      if(this.d()) {
         this.a(this.w, this.x);
      } else if(this.f != null) {
         this.f.d.a(this.w, this.x);
      }
   }

   public void mouseMoved(int var1, int var2, int var3, int var4) {
      this.a(var3, var4, true);
      if(this.d()) {
         this.a(this.w, this.x);
      } else if(this.f != null) {
         this.f.d.a(this.w, this.x);
      }
   }

   public void mouseReleased(int var1, int var2, int var3) {
      this.a(var2, var3, false);
      if(this.d()) {
         this.b.p.processMouseButtonUp(0, 0);
      } else if(this.f != null) {
         int var4 = this.a(var1);
         if(var4 != 0) {
            this.f.d.a(this.w, this.x, false, var4);
         }

      }
   }

   public void mouseWheelMoved(int var1) {
      if(this.d()) {
         this.b.p.processMouseWheel(-(var1 / 120) * 2, 0);
      } else if(this.e != null) {
         this.e.k(var1);
      }
   }

   boolean d() {
      return this.b != null && this.b.p != null && !this.b.p.b();
   }

   public boolean b(int var1) {
      return var1 < this.Z.length && var1 >= 0?this.Z[var1]:false;
   }

   public int e() {
      int var1 = 0;
      if(this.b(42) || this.b(54)) {
         var1 += 2;
      }

      if(this.b(29) || this.b(157)) {
         ++var1;
      }

      if(this.b(56) || this.b(184)) {
         var1 += 4;
      }

      return var1;
   }

   public void a(int var1, boolean var2) {
      if(var1 < this.Z.length && var1 >= 0) {
         this.Z[var1] = var2;
      }
   }

   public void f() {
      for(int var1 = 0; var1 < this.Z.length; ++var1) {
         if(this.Z[var1]) {
            this.c(var1);
         }
      }

   }

   public void keyPressed(int var1, char var2) {
      this.a(var1, true);
      if(this.b.i == null) {
         com.corrodinggames.rts.gameFramework.l.e("keyPressed: guiEngine==null");
      } else {
         this.b.i.a(var1, var2);
      }
   }

   public void c(int var1) {
      this.keyReleased(var1, '\u0000');
   }

   public void keyReleased(int var1, char var2) {
      this.a(var1, false);
      if(this.d()) {
         Integer var3 = SlickToAndroidKeycodes.c(var1);
         if(var3 != null) {
            this.b.p.processKeyUp(var3.intValue(), this.e());
         } else if(!Character.isISOControl(var2)) {
            ;
         }
      }

      if(this.e != null) {
         this.e.b(SlickToAndroidKeycodes.b(var1), false);
      }
   }

   public void a(int var1, int var2) {
      this.E = var1;
      this.F = var2;
      this.g();
   }

   public void g() {
      if(!this.l) {
         if(!this.i) {
            try {
               boolean var1 = this.e.bQ.slick2dFullScreen;
               boolean var2 = this.e.bQ.slick2dBorderless;
               if(var2) {
                  var1 = true;
               }

               int var3 = this.E;
               int var4 = this.F;
               if(var1) {
                  var3 = this.c.getScreenWidth();
                  var4 = this.c.getScreenHeight();
                  String var5 = this.e.bQ.slick2dFullScreenResolution;
                  if(var5 == null) {
                     com.corrodinggames.rts.gameFramework.l.e("fullScreenResolutionString is null");
                     var5 = "native";
                  }

                  if(!var5.equals("native")) {
                     String[] var6 = var5.split("x");
                     if(var6.length != 2) {
                        com.corrodinggames.rts.gameFramework.l.b("Unknown resolution format:" + var5);
                     } else {
                        Integer var7 = com.corrodinggames.rts.gameFramework.f.l(var6[0]);
                        Integer var8 = com.corrodinggames.rts.gameFramework.f.l(var6[1]);
                        if(var7 != null && var8 != null) {
                           var3 = var7.intValue();
                           var4 = var8.intValue();
                        } else {
                           com.corrodinggames.rts.gameFramework.l.b("Bad resolution int:" + var5);
                        }
                     }
                  }
               }

               float var16 = 1.0F;
               if((float)var3 <= 3360.0F && (float)var4 <= 1890.0F) {
                  if((float)var3 > 2688.0F || (float)var4 > 1512.0F) {
                     var16 = 1.5F;
                  }
               } else {
                  var16 = 2.0F;
               }

               var16 *= this.e.bQ.uiRenderScale;
               float var17 = this.e.bQ.renderDensity;
               if(this.I == var1 && this.J == var2 && this.E == this.G && this.F == this.H && com.corrodinggames.rts.gameFramework.f.h(var16, this.P) && com.corrodinggames.rts.gameFramework.f.h(var17, this.Q)) {
                  return;
               }

               boolean var9 = this.J != var2;
               this.P = var16;
               this.Q = var17;
               this.R = this.P;
               this.R *= (this.Q - 1.0F) * 0.5F + 1.0F;
               this.G = this.E;
               this.H = this.F;
               this.I = var1;
               this.J = var2;
               boolean var10 = false;
               if(var2 && var1) {
                  var10 = true;
               }

               System.setProperty("org.lwjgl.opengl.Window.undecorated", var10?"true":"false");
               if(var1) {
                  com.corrodinggames.rts.gameFramework.l.e("screen: " + this.c.getScreenWidth() + ", " + this.c.getScreenHeight());
                  com.corrodinggames.rts.gameFramework.l.e("container: " + this.a.getWidth() + ", " + this.a.getHeight());
                  if(var2) {
                     this.c.setDisplayMode(var3, var4, false);
                  } else {
                     this.c.setDisplayMode(var3, var4, true);
                  }
               } else {
                  boolean var11 = false;
                  if(this.c.isFullscreen()) {
                     var11 = true;
                  }

                  if(var9) {
                     var11 = true;
                     if(var3 > 2 && var4 > 2) {
                        this.c.setDisplayMode(var3 - 1, var4 - 1, false);
                     }
                  }

                  if(var11) {
                     this.c.setDisplayMode(var3, var4, false);
                     Display.setResizable(false);
                     Display.setResizable(true);
                  } else {
                     SGL var12 = Renderer.get();
                     var12.initDisplay(var3, var4);
                     var12.enterOrtho(var3, var4);
                     Field var13 = GameContainer.class.getDeclaredField("width");
                     var13.setAccessible(true);
                     var13.set(this.c, Integer.valueOf(var3));
                     Field var14 = GameContainer.class.getDeclaredField("height");
                     var14.setAccessible(true);
                     var14.set(this.c, Integer.valueOf(var4));
                  }
               }

               this.S = Display.getWidth() / 2;
               this.T = Display.getHeight() / 2;
               int var18 = (int)((float)var3 / this.P);
               int var19 = (int)((float)var4 / this.P);
               int var20 = (int)((float)var3 / this.R);
               int var21 = (int)((float)var4 / this.R);
               if(this.f != null) {
                  this.f.a = var18;
                  this.f.b = var19;
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("setResolution: gameView=null");
               }

               if(this.e != null) {
                  this.e.b(var18, var19);
                  this.e.X();
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("setResolution: game=null");
               }

               if(this.b != null && this.b.p != null) {
                  this.b.p.setDimensionsWrap(var20, var21);
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("setResolution: main.libRocket=null");
               }
            } catch (Exception var15) {
               var15.printStackTrace();
            }

         }
      }
   }

   public void a(String var1, boolean var2) {
      boolean var3 = true;
      if(!var1.startsWith("Loading units ")) {
         com.corrodinggames.rts.gameFramework.l.e("--Now loading:" + var1);
         var3 = false;
      }

      if(var2) {
         this.N = this.M;
         this.M = var1;
      }

      long var4 = System.currentTimeMillis();
      if(!var3 || var4 >= this.O + 10L) {
         this.O = var4;
         Graphics var6 = this.c.a();
         this.a(this.c, var6);
         this.c.a(var6);
      }
   }

   public void a(GameContainer var1, Graphics var2) {
      this.K += (float)this.t;
      ++this.L;
      var2.setColor(Color.black);
      var2.clear();
      if(this.m != null) {
         var2.drawImage(this.m.C(), (float)(Display.getWidth() / 2 - this.m.p / 2), (float)(Display.getHeight() / 2 - this.m.q / 2));
      }

      String var3 = "Loading";
      int var4 = this.L % 4;

      int var5;
      for(var5 = 0; var5 <= var4; ++var5) {
         var3 = var3 + ".";
      }

      var3 = com.corrodinggames.rts.gameFramework.f.e("    " + var3, 17);
      var5 = var2.getFont().getWidth(var3);
      int var6 = Display.getHeight() - 70;
      var2.setColor(Color.white);
      var2.drawString(var3, (float)(Display.getWidth() / 2 - var5 / 2), (float)var6);
      var2.setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F));
      var5 = var2.getFont().getWidth(this.M);
      var2.drawString(this.M, (float)(Display.getWidth() / 2 - var5 / 2), (float)(var6 + 20));
   }

   public void a(e var1) {
      var1.k();
      if(this.P != 1.0F) {
         var1.a(this.P, this.P);
      }

   }

   public void b(e var1) {
      var1.l();
   }

   public void a(Graphics var1, float var2) {
      var1.pushTransform();
      var1.scale(var2, var2);
   }

   public void a(Graphics var1) {
      var1.popTransform();
   }

   public void render(GameContainer var1, Graphics var2) {
      long var3 = System.currentTimeMillis();
      float var5 = (float)(var3 - this.U) * 0.060000002F;
      int var6 = (int)(var3 - this.U);
      this.U = var3;
      if(this.v && this.u && var1.hasFocus()) {
         Mouse.setCursorPosition(this.S, this.T);
      }

      if(!this.r) {
         this.a(var1, var2);
         if(!this.s) {
            this.s = true;
         } else {
            if(!this.p) {
               this.b();
            }

         }
      } else if(this.e == null) {
         com.corrodinggames.rts.gameFramework.l.b("render: game==null");
      } else {
         this.a();
         float var7 = (float)this.t * 0.060000002F;
         int var8 = Display.getWidth();
         int var9 = Display.getHeight();
         if(var8 != this.G || var9 != this.H) {
            if(this.C != var8 || this.D != var9) {
               this.C = var8;
               this.D = var9;
               this.B = 0.0F;
            }

            this.B += var7;
            this.A += var7;
            if(this.A > 300.0F || this.B > 20.0F || this.A > 0.0F) {
               this.A = 0.0F;
               this.B = 0.0F;
               this.a(var8, var9);
            }
         }

         float var10 = this.R;
         this.b.a(var7);
         if(this.e.bQ.liveReloading) {
            ++this.z;
            if(this.z > 30) {
               this.z = 0;
               if(s.y != null) {
                  Iterator var11 = s.y.iterator();

                  while(var11.hasNext()) {
                     s var12 = (s)var11.next();
                     var12.t();
                  }
               }

               if(!this.e.h(48)) {
                  this.b.p.detectChangesAndReload();
               }
            }
         }

         boolean var16 = false;
         e var17 = null;
         if(!this.e.bG) {
            var2.setColor(Color.gray);
            var2.resetTransform();
            var2.clearClip();
            var2.clear();
            if(!this.d() && !this.e.bI) {
               ++this.X;
               if(this.X > 100) {
                  this.X = 0;
                  com.corrodinggames.rts.gameFramework.l.e("Fail safe menu");
                  com.corrodinggames.librocket.a.a().b();
               }
            }
         } else {
            this.X = 0;
         }

         boolean var13 = this.e.bT.ae.a();
         boolean var14 = this.e.bl && this.e.bT.af.a();
         if(var14) {
            var13 = true;
         }

         if(this.e.bG) {
            var2.resetTransform();
            if(!this.e.dv) {
               var2.clearClip();
               var2.clear();
            }

            var2.setColor(Color.black);
            if(!com.corrodinggames.rts.gameFramework.l.aB) {
               var17 = (e)this.e.bO;
               if(var17 != null) {
                  var17.f = var2;
                  var17.L = this.P;
                  var16 = true;
                  this.a(var17);
               }
            }

            boolean var15 = this.e.cS;
            if(var13) {
               this.e.cS = true;
            }

            this.e.a(var7, this.t);
            if(var13) {
               this.e.cS = var15;
            }

            this.t = 0;
            if(!com.corrodinggames.rts.gameFramework.l.aB && var16) {
               this.b(var17);
            }
         } else {
            this.e.bX.b(var7);
            this.e.bN.a(var7);
         }

         this.a(var2, var10);
         com.corrodinggames.rts.gameFramework.o.a.a().a(0.0F);
         this.b.p.c.update(var7);
         if(!this.b.p.b()) {
            this.b.p.a(var2);
            this.b.p.update();
            this.b.p.render();
            this.b.p.c.checkForErrors();
            this.b.p.debug = false;
         }

         this.b.p.postUpdate();
         this.a(var2);
         if(this.i) {
            var2.clear();
         }

         if(this.v && !var13 && !this.e.cU) {
            var2.drawImage(this.n.C(), this.w * this.P, this.x * this.P);
         }

         if(var13) {
            this.a(var2, var14);
         }

         if(this.b.u) {
            ++this.b.v;
            if(this.b.v > 2) {
               this.b.u = false;
               this.b.v = 0;
               com.corrodinggames.rts.gameFramework.l.e("Saving settings (queued)");
               this.e.bQ.save();
            }
         }

      }
   }

   public void a(Graphics var1, boolean var2) {
      d var3 = this.f;
      boolean var4 = false;
      int var5 = 10;
      int var6 = 10;
      if(var3 != null) {
         var5 = var3.a;
         var6 = var3.b;
      }

      try {
         com.corrodinggames.rts.gameFramework.l.e("Saving screenshot");
         String var7 = "screenshots";
         File var8 = new File("screenshots");
         if(!var8.exists()) {
            var8.mkdir();
         }

         String var9 = "screenshot_" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH.mm.ss") + ".png";
         String var10 = "screenshots" + File.separator + var9;
         if(!var2) {
            Image var29 = new Image(this.c.getWidth(), this.c.getHeight());
            var1.copyArea(var29, 0, 0);
            ByteArrayOutputStream var30 = new ByteArrayOutputStream();
            ImageOut.write(var29, "png", var30);
            com.corrodinggames.rts.gameFramework.l.f((String)null, "Saving screenshot: " + var9);
            u$1 var31 = new u$1(this, var10, var30, var9);
            Thread var32 = new Thread(var31);
            var32.start();
            return;
         }

         if(this.Y == null) {
            this.Y = new com.corrodinggames.rts.game.j();
         }

         var4 = true;
         float var11 = 2.0F;
         int var12 = (int)((float)var3.a * var11);
         int var13 = (int)((float)var3.b * var11);
         y var14 = this.e.bO;
         this.Y.a(var14, var12, var13, 0);
         com.corrodinggames.rts.game.i var15 = (com.corrodinggames.rts.game.i)this.e;
         boolean var16 = this.e.cS;
         var15.a(this.Y);
         this.e.cS = true;

         try {
            this.e.bO.b(android.graphics.Color.a(0, 0, 0));
            com.corrodinggames.rts.gameFramework.m.l var17 = var3.b(true);
            var15.b(var12, var13);
            var15.k();
            var15.a(0.0F, 0);
            var15.b(var5, var6);
            var15.k();
         } finally {
            var15.b(this.Y);
            this.e.cS = var16;
         }

         var14.a(this.Y.a, new File(var10));
         com.corrodinggames.rts.gameFramework.l.f((String)null, "Saving large screenshot: " + var9);
         return;
      } catch (Exception var27) {
         var27.printStackTrace();
         com.corrodinggames.rts.gameFramework.l.n("Failed to take screenshot:" + var27.getMessage());
      } finally {
         if(var3 != null) {
            var3.a = var5;
            var3.b = var6;
         }

      }

   }
}
