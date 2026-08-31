/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.OpenGLException
 *  org.newdawn.slick.BasicGame
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.imageout.ImageOut
 *  org.newdawn.slick.opengl.renderer.Renderer
 *  org.newdawn.slick.opengl.renderer.SGL
 */
package com.corrodinggames.rts.java;

import android.graphics.Color;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.platform.a;
import com.corrodinggames.rts.appFramework.TouchState;
import com.corrodinggames.rts.game.GameEngine;
import com.corrodinggames.rts.game.ResourceLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.NullRenderer;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import com.corrodinggames.rts.java.GameLauncher;
import com.corrodinggames.rts.java.GameWindow;
import com.corrodinggames.rts.java.DesktopAppFramework;
import com.corrodinggames.rts.java.Slick2DRenderer;
import com.corrodinggames.rts.java.SlickTexture;
import com.corrodinggames.rts.java.RenderBatch$1;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.OpenGLException;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.imageout.ImageOut;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class DesktopGameContainer
extends BasicGame {
    GameContainer a;
    GameLauncher b;
    GameWindow c;
    NullRenderer d;  // 02b u.java: gameFramework/m/n d (NullRenderer) (v19.133f2 修正)
    GlobalState e;
    DesktopAppFramework f;
    boolean g = false;
    Object h = new Object();
    boolean i = false;
    boolean j = false;
    boolean k = false;
    boolean l = false;
    SlickTexture m;
    SlickTexture n;
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
    float A = 0.0f;
    float B = 0.0f;
    int C = 0;
    int D = 0;
    int E;
    int F;
    int G;
    int H;
    boolean I;
    boolean J;
    float K = 0.0f;
    int L = 0;
    String M = "";
    String N = "";
    long O = -9999L;
    float P = 1.0f;
    float Q = 1.0f;
    float R = 1.0f;
    int S = 100;
    int T = 100;
    long U;
    float V = 0.0f;
    float W = 0.0f;
    int X;
    ScreenshotLayer Y;  // 02b game/j (v19.133f)

    public DesktopGameContainer(String string) {
        super(string);
    }

    public boolean closeRequested() {
        if (this.i) {
            return true;
        }
        if (com.corrodinggames.rts.platform.a.a()) {  // 02b u L92: a/a.a() (v19.133f)
            return true;
        }
        if (this.c != null && !this.c.isFullscreen()) {
            ScriptEngine.getInstance().addScriptToQueue("askQuitGame();");
            return false;
        }
        return true;
    }

    public void init(GameContainer gameContainer) throws SlickException {
        this.a = gameContainer;
        gameContainer.setAlwaysRender(true);
        gameContainer.setForceExit(true);
        gameContainer.setShowFPS(false);
        gameContainer.setTargetFrameRate(300);
        if (com.corrodinggames.rts.gameFramework.GlobalState.ay) {
            gameContainer.setShowFPS(true);
            gameContainer.setTargetFrameRate(30);
        }
        gameContainer.setIcons(new String[]{"res/R$drawable/icon_window.png", "res/R$drawable/icon_window128.png", "res/R$drawable/icon_window24.png", "res/R$drawable/icon_window16.png"});
        gameContainer.setUpdateOnlyWhenVisible(false);
        gameContainer.getInput().enableKeyRepeat();
        this.m = com.corrodinggames.rts.java.Slick2DRenderer.b(R$drawable.logo, true);  // 02b u L116: e.b (v19.133f)
        this.n = com.corrodinggames.rts.java.Slick2DRenderer.b(R$drawable.pointer, true);  // 02b u L117: e.b (v19.133f)
        gameContainer.setMouseCursor(this.n.C(), 0, 0);
        this.U = System.currentTimeMillis();
    }

    public void a() {
        this.e = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.a.isVSyncRequested() != this.e.bQ.renderVsync) {
            try {
                this.a.setVSync(this.e.bQ.renderVsync);
            }
            catch (OpenGLException openGLException) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("Error while changing vsync setting", (Throwable)openGLException);
            }
        }
        if (this.e.bQ.highRefreshRate) {
            this.a.setTargetFrameRate(300);
        } else {
            this.a.setTargetFrameRate(120);
        }
        boolean bl = false;
        if (this.e.bQ.enableMouseCapture && (!this.e.bQ.slick2dFullScreen || this.e.cU) && !this.e.aq && !this.e.a(false) && this.e.bq) {
            bl = true;
        }
        if (bl != this.v) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Grabbing mouse:" + bl);
            this.v = bl;
            if (!this.u) {
                this.a.setMouseGrabbed(this.v);
            }
            if (this.v) {
                // empty if block
            }
            if (!this.v) {
                Mouse.setCursorPosition((int)((int)(this.w * this.P)), (int)((int)((float)Display.getHeight() - this.x * this.P)));
            }
            com.corrodinggames.rts.gameFramework.GlobalState.aR = this.v;
        }
        this.a.setSmoothDeltas(this.e.bQ.renderSmoothDelta);
        if (this.o != Display.isActive()) {
            this.o = Display.isActive();
            if (this.o) {
                this.f();
            }
        }
    }

    public void b() {
        if (this.p) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("loadingStartedThreaded");
            return;
        }
        this.p = true;
        this.c();
    }

    public void c() {
        if (this.q) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("loadingStartedNonThreaded");
            return;
        }
        this.q = true;
        if (this.b == null) {
            this.b = new GameLauncher();
        }
        this.b.h();
        if (com.corrodinggames.rts.gameFramework.GlobalState.aI) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("switching to sandbox");
            ScriptEngine.getInstance().addScriptToQueue("open('sandboxOptions.rml', 'maps/skirmish/[z;p10]Crossing Large (10p).tmx'); loadConfigAndStartNewSandbox('maps/skirmish/[z;p10]Crossing Large (10p).tmx');");
        }
        this.r = true;
    }

    public void a(DesktopAppFramework d2) {  // 02b u.java L199: a(d) = java/d (DesktopAppFramework) (v19.133f2 修正)
        com.corrodinggames.rts.gameFramework.GlobalState.b("SlickContainer:setup");
        this.e = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.f = d2;
        this.f.d = new com.corrodinggames.rts.appFramework.TouchState();  // 02b u L203: new appFramework/m() (v19.133f2 修正)
        this.f.a = this.a.getWidth();
        this.f.b = this.a.getHeight();
        this.e.b(this.f.a, this.f.b);
        this.d = new NullRenderer();  // 02b u L207: new gameFramework/m/n() (v19.133f2 修正)
    }

    public void update(GameContainer gameContainer, int n2) {
        this.t = n2;
    }

    public int a(int n2) {
        if (n2 == 0) {
            return 1;  // 02b u L216: F25 数字污染 (v19.133f)
        }
        if (n2 == 1) {  // 02b u L217 (v19.133f)
            return 2;
        }
        if (n2 == 2) {
            return 3;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Unknown mouse button:" + n2);
        return 0;
    }

    public void a(int n2, int n3, boolean bl) {
        if (this.y) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("mouseGrab:m:" + bl + ",newX:" + n2 + ",newY:" + n3);
        }
        if (!this.v) {
            this.w = (int)((float)n2 / this.P);
            this.x = (int)((float)n3 / this.P);
        } else {
            if (bl) {
                int n4;
                int n5;
                if (this.u) {
                    n5 = n2 - this.S;
                    n4 = n3 - this.T;
                } else {
                    n5 = n2;
                    n4 = n3;
                }
                this.w += (float)n5 / this.P;
                this.x += (float)n4 / this.P;
            } else {
                this.w = (int)((float)n2 / this.P);
                this.x = (int)((float)n3 / this.P);
            }
            if (this.w < 0.0f) {
                this.w = 0.0f;
            }
            if (this.x < 0.0f) {
                this.x = 0.0f;
            }
            if (this.f != null) {
                if (this.w > (float)(this.f.o() - 1)) {
                    this.w = this.f.o() - 1;
                }
                if (this.x > (float)(this.f.p() - 1)) {
                    this.x = this.f.p() - 1;
                }
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("processMouseGrab gameView==null");
            }
        }
    }

    public void mousePressed(int n2, int n3, int n4) {
        this.a(n3, n4, false);
        if (this.d()) {
            this.a(this.w, this.x);
            this.b.p.processMouseButtonDown(0, 0);
            return;
        }
        if (this.f != null) {
            int n5 = this.a(n2);
            if (n5 != 0) {
                this.f.d.a(this.w, this.x, true, n5);
            }
            return;
        }
    }

    public void a(float f2, float f3) {
        f2 *= this.P;
        f3 *= this.P;
        this.b.p.mouseMove((int)(f2 /= this.R), (int)(f3 /= this.R), 0);
    }

    public void mouseDragged(int n2, int n3, int n4, int n5) {
        this.a(n4, n5, true);
        if (this.d()) {
            this.a(this.w, this.x);
            return;
        }
        if (this.f != null) {
            this.f.d.a(this.w, this.x);
            return;
        }
    }

    public void mouseMoved(int n2, int n3, int n4, int n5) {
        this.a(n4, n5, true);
        if (this.d()) {
            this.a(this.w, this.x);
            return;
        }
        if (this.f != null) {
            this.f.d.a(this.w, this.x);
            return;
        }
    }

    public void mouseReleased(int n2, int n3, int n4) {
        this.a(n3, n4, false);
        if (this.d()) {
            this.b.p.processMouseButtonUp(0, 0);
            return;
        }
        if (this.f != null) {
            int n5 = this.a(n2);
            if (n5 != 0) {
                this.f.d.a(this.w, this.x, false, n5);
            }
            return;
        }
    }

    public void mouseWheelMoved(int n2) {
        if (this.d()) {
            this.b.p.processMouseWheel(-(n2 / 120) * 2, 0);
            return;
        }
        if (this.e != null) {
            this.e.k(n2);
            return;
        }
    }

    boolean d() {
        return this.b != null && this.b.p != null && !this.b.p.b();
    }

    public boolean b(int n2) {
        if (n2 >= this.Z.length || n2 < 0) {
            return false;
        }
        return this.Z[n2];
    }

    public int e() {
        int n2 = 0;
        if (this.b(42) || this.b(54)) {
            n2 += 2;
        }
        if (this.b(29) || this.b(157)) {
            ++n2;
        }
        if (this.b(56) || this.b(184)) {
            n2 += 4;
        }
        return n2;
    }

    public void a(int n2, boolean bl) {
        if (n2 >= this.Z.length || n2 < 0) {
            return;
        }
        this.Z[n2] = bl;
    }

    public void f() {
        for (int i2 = 0; i2 < this.Z.length; ++i2) {
            if (!this.Z[i2]) continue;
            this.c(i2);
        }
    }

    public void keyPressed(int n2, char c2) {
        this.a(n2, true);
        if (this.b.i == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("keyPressed: guiEngine==null");
            return;
        }
        this.b.i.a(n2, c2);
    }

    public void c(int n2) {
        this.keyReleased(n2, '\u0000');
    }

    public void keyReleased(int n2, char c2) {
        this.a(n2, false);
        if (this.d()) {
            Integer n3 = SlickToAndroidKeycodes.c(n2);
            if (n3 != null) {
                this.b.p.processKeyUp(n3, this.e());
            } else if (!Character.isISOControl(c2)) {
                // empty if block
            }
        }
        if (this.e != null) {
            this.e.b(SlickToAndroidKeycodes.b(n2), false);
            return;
        }
    }

    public void a(int n2, int n3) {
        this.E = n2;
        this.F = n3;
        this.g();
    }

    public void g() {
        if (this.l) {
            return;
        }
        if (this.i) {
            return;
        }
        try {
            int n2;
            boolean bl = this.e.bQ.slick2dFullScreen;
            boolean bl2 = this.e.bQ.slick2dBorderless;
            if (bl2) {
                bl = true;
            }
            int n3 = this.E;
            int n4 = this.F;
            if (bl) {
                n3 = this.c.getScreenWidth();
                n4 = this.c.getScreenHeight();
                String string = this.e.bQ.slick2dFullScreenResolution;
                if (string == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("fullScreenResolutionString is null");
                    string = "native";
                }
                if (!string.equals("native")) {
                    String[] stringArray = string.split("x");
                    if (stringArray.length != 2) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("Unknown resolution format:" + string);
                    } else {
                        Integer n5 = com.corrodinggames.rts.gameFramework.GameUtils.parseInt(stringArray[0]);
                        Integer n6 = com.corrodinggames.rts.gameFramework.GameUtils.parseInt(stringArray[1]);
                        if (n5 == null || n6 == null) {
                            com.corrodinggames.rts.gameFramework.GlobalState.b("Bad resolution int:" + string);
                        } else {
                            n3 = n5;
                            n4 = n6;
                        }
                    }
                }
            }
            float f2 = 1.0f;
            if ((float)n3 > 3360.0f || (float)n4 > 1890.0f) {
                f2 = 2.0f;
            } else if ((float)n3 > 2688.0f || (float)n4 > 1512.0f) {
                f2 = 1.5f;
            }
            float f3 = this.e.bQ.renderDensity;
            if (this.I == bl && this.J == bl2 && this.E == this.G && this.F == this.H && com.corrodinggames.rts.gameFramework.GameUtils.h(f2 *= this.e.bQ.uiRenderScale, this.P) && com.corrodinggames.rts.gameFramework.GameUtils.h(f3, this.Q)) {
                return;
            }
            int n7 = n3;
            int n8 = n4;
            boolean bl3 = this.J != bl2;
            this.P = f2;
            this.Q = f3;
            this.R = this.P;
            this.R *= (this.Q - 1.0f) * 0.5f + 1.0f;
            this.G = this.E;
            this.H = this.F;
            this.I = bl;
            this.J = bl2;
            boolean bl4 = false;
            if (bl2 && bl) {
                bl4 = true;
            }
            System.setProperty("org.lwjgl.opengl.Window.undecorated", bl4 ? "true" : "false");
            if (bl) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("screen: " + this.c.getScreenWidth() + ", " + this.c.getScreenHeight());
                com.corrodinggames.rts.gameFramework.GlobalState.e("container: " + this.a.getWidth() + ", " + this.a.getHeight());
                if (bl2) {
                    this.c.setDisplayMode(n7, n8, false);
                } else {
                    this.c.setDisplayMode(n7, n8, true);
                }
            } else {
                n2 = 0;
                if (this.c.isFullscreen()) {
                    n2 = 1;  // 02b u L216 (v19.133f)
                }
                if (bl3) {
                    n2 = 1;  // 02b u L216 (v19.133f)
                    if (n7 > 2 && n8 > 2) {
                        this.c.setDisplayMode(n7 - 1, n8 - 1, false);
                    }
                }
                if (n2 != 0) {
                    this.c.setDisplayMode(n7, n8, false);
                    Display.setResizable((boolean)false);
                    Display.setResizable((boolean)true);
                } else {
                    SGL sGL = org.newdawn.slick.opengl.renderer.Renderer.get();  // 全限定防遮蔽 (v19.133f)
                    sGL.initDisplay(n7, n8);
                    sGL.enterOrtho(n7, n8);
                    Field field = GameContainer.class.getDeclaredField("width");
                    field.setAccessible(true);
                    field.set((Object)this.c, n7);
                    Field field2 = GameContainer.class.getDeclaredField("height");
                    field2.setAccessible(true);
                    field2.set((Object)this.c, n8);
                }
            }
            this.S = Display.getWidth() / 2;
            this.T = Display.getHeight() / 2;
            n2 = (int)((float)n7 / this.P);
            int n9 = (int)((float)n8 / this.P);
            int n10 = (int)((float)n7 / this.R);
            int n11 = (int)((float)n8 / this.R);
            if (this.f != null) {
                this.f.a = n2;
                this.f.b = n9;
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("setResolution: gameView=null");
            }
            if (this.e != null) {
                this.e.b(n2, n9);
                this.e.X();
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("setResolution: game=null");
            }
            if (this.b != null && this.b.p != null) {
                this.b.p.setDimensionsWrap(n10, n11);
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("setResolution: main.libRocket=null");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(String string, boolean bl) {
        boolean bl2 = true;
        if (!string.startsWith("Loading units ")) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("--Now loading:" + string);
            bl2 = false;
        }
        if (bl) {
            this.N = this.M;
            this.M = string;
        }
        long l2 = System.currentTimeMillis();
        if (bl2 && l2 < this.O + 10L) {
            return;
        }
        this.O = l2;
        Graphics graphics = this.c.a();
        this.a((GameContainer)this.c, graphics);
        this.c.a(graphics);
    }

    public void a(GameContainer gameContainer, Graphics graphics) {
        int n2;
        this.K += (float)this.t;
        ++this.L;
        graphics.setColor(org.newdawn.slick.Color.black);
        graphics.clear();
        if (this.m != null) {
            graphics.drawImage(this.m.C(), (float)(Display.getWidth() / 2 - this.m.p / 2), (float)(Display.getHeight() / 2 - this.m.q / 2));
        }
        String string = "Loading";
        int n3 = this.L % 4;
        for (n2 = 0; n2 <= n3; ++n2) {
            string = string + ".";
        }
        string = com.corrodinggames.rts.gameFramework.GameUtils.e("    " + string, 17);
        n2 = graphics.getFont().getWidth(string);
        int n4 = Display.getHeight() - 70;
        graphics.setColor(org.newdawn.slick.Color.white);
        graphics.drawString(string, (float)(Display.getWidth() / 2 - n2 / 2), (float)n4);
        graphics.setColor(new org.newdawn.slick.Color(1.0f, 1.0f, 1.0f, 0.6f));
        n2 = graphics.getFont().getWidth(this.M);
        graphics.drawString(this.M, (float)(Display.getWidth() / 2 - n2 / 2), (float)(n4 + 20));
    }

    public void a(Slick2DRenderer e2) {
        e2.k();
        if (this.P != 1.0f) {
            e2.a(this.P, this.P);
        }
    }

    public void b(Slick2DRenderer e2) {
        e2.l();
    }

    public void a(Graphics graphics, float f2) {
        graphics.pushTransform();
        graphics.scale(f2, f2);
    }

    public void a(Graphics graphics) {
        graphics.popTransform();
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        boolean bl;
        Object object2;
        long l2 = System.currentTimeMillis();
        float f2 = (float)(l2 - this.U) * 0.060000002f;
        int n2 = (int)(l2 - this.U);
        this.U = l2;
        if (this.v && this.u && gameContainer.hasFocus()) {
            Mouse.setCursorPosition((int)this.S, (int)this.T);
        }
        if (!this.r) {
            this.a(gameContainer, graphics);
            if (!this.s) {
                this.s = true;
                return;
            }
            if (!this.p) {
                this.b();
            }
            return;
        }
        if (this.e == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("render: game==null");
            return;
        }
        this.a();
        float f3 = (float)this.t * 0.060000002f;
        int n3 = Display.getWidth();
        int n4 = Display.getHeight();
        if (n3 != this.G || n4 != this.H) {
            if (this.C != n3 || this.D != n4) {
                this.C = n3;
                this.D = n4;
                this.B = 0.0f;
            }
            this.B += f3;
            this.A += f3;
            if (this.A > 300.0f || this.B > 20.0f || this.A > 0.0f) {
                this.A = 0.0f;
                this.B = 0.0f;
                this.a(n3, n4);
            }
        }
        float f4 = this.R;
        this.b.a(f3);
        if (this.e.bQ.liveReloading) {
            ++this.z;
            if (this.z > 30) {
                this.z = 0;
                if (SlickTexture.y != null) {
                    for (Object object2_674 : SlickTexture.y) {
                        ((SlickTexture) object2_674).t();
                    }
                }
                if (!this.e.h(48)) {
                    this.b.p.detectChangesAndReload();
                }
            }
        }
        boolean bl2 = false;
        object2 = null;
        if (!this.e.bG) {
            graphics.setColor(org.newdawn.slick.Color.gray);
            graphics.resetTransform();
            graphics.clearClip();
            graphics.clear();
            if (!this.d() && !this.e.bI) {
                ++this.X;
                if (this.X > 100) {
                    this.X = 0;
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Fail safe menu");
                    com.corrodinggames.librocket.LibRocketContext.a().b();
                }
            }
        } else {
            this.X = 0;
        }
        boolean bl3 = this.e.bT.ad53.a();  // 02b ac.ae = Debug Take Screenshot (v19.133f)
        boolean bl4 = bl = this.e.bl && this.e.bT.ad54.a();  // 02b ac.af = Debug Take Screenshot High (v19.133f)
        if (bl) {
            bl3 = true;
        }
        if (this.e.bG) {
            graphics.resetTransform();
            if (!this.e.dv) {
                graphics.clearClip();
                graphics.clear();
            }
            graphics.setColor(org.newdawn.slick.Color.black);
            if (!com.corrodinggames.rts.gameFramework.GlobalState.aB && (object2 = (Slick2DRenderer) this.e.bO) != null) {
                ((Slick2DRenderer) object2).f = graphics;
                ((Slick2DRenderer) object2).L = this.P;
                bl2 = true;
                this.a((Slick2DRenderer) object2);
            }
            boolean bl5 = this.e.cS;
            if (bl3) {
                this.e.cS = true;
            }
            this.e.a(f3, this.t);
            if (bl3) {
                this.e.cS = bl5;
            }
            this.t = 0;
            if (!com.corrodinggames.rts.gameFramework.GlobalState.aB && bl2) {
                this.b((Slick2DRenderer) object2);
            }
        } else {
            this.e.bX.b(f3);  // 02b u L742: bX (NetEngine) (v19.133f)
            this.e.bN.a(f3);
        }
        this.a(graphics, f4);
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().a(0.0f);  // 02b u L747: o.a (v19.133f)
        this.b.p.c.update(f3);
        if (!this.b.p.b()) {
            this.b.p.a(graphics);
            this.b.p.update();
            this.b.p.render();
            this.b.p.c.checkForErrors();
            this.b.p.debug = false;
        }
        this.b.p.postUpdate();
        this.a(graphics);
        if (this.i) {
            graphics.clear();
        }
        if (this.v && !bl3 && !this.e.cU) {
            graphics.drawImage(this.n.C(), this.w * this.P, this.x * this.P);
        }
        if (bl3) {
            this.a(graphics, bl);
        }
        if (this.b.u) {
            ++this.b.v;
            if (this.b.v > 2) {
                this.b.u = false;
                this.b.v = 0;
                com.corrodinggames.rts.gameFramework.GlobalState.e("Saving settings (queued)");
                this.e.bQ.save();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(Graphics graphics, boolean bl) {
        DesktopAppFramework d2 = this.f;
        boolean bl2 = false;
        int n2 = 10;
        int n3 = 10;
        if (d2 != null) {
            n2 = d2.a;
            n3 = d2.b;
        }
        try {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Saving screenshot");
            String string = "screenshots";
            File file = new File("screenshots");
            if (!file.exists()) {
                file.mkdir();
            }
            String string2 = "screenshot_" + GameUtils.a("DesktopAppFramework MMM yyyy HH.mm.ss") + ".png";
            String string3 = "screenshots" + File.separator + string2;
            if (bl) {
                if (this.Y == null) {
                    this.Y = new ScreenshotLayer();  // 02b u L817: new game/j() (v19.133f)
                }
                bl2 = true;
                float f2 = 2.0f;
                int n4 = (int)((float)d2.a * f2);
                int n5 = (int)((float)d2.b * f2);
                TextureManagerInterface y2 = this.e.bO;
                this.Y.a(y2, n4, n5, 0);
                DesktopPlatform i2 = (DesktopPlatform) (Object) this.e;  // 02b (game/i)this.e 双cast (v19.133f)
                boolean bl3 = this.e.cS;
                i2.a(this.Y);
                this.e.cS = true;
                try {
                    this.e.bO.b(Color.a(0, 0, 0));
                    Renderer l2 = d2.b(true);
                    i2.b(n4, n5);
                    i2.k();
                    i2.a(0.0f, 0);
                    i2.b(n2, n3);
                    i2.k();
                }
                finally {
                    i2.b(this.Y);
                    this.e.cS = bl3;
                }
                y2.a(this.Y.a, new File(string3));  // 02b u L844: y.a(e,File) (v19.133f)
                com.corrodinggames.rts.gameFramework.GlobalState.f(null, "Saving large screenshot: " + string2);
                return;
            }
            Image image = new Image(this.c.getWidth(), this.c.getHeight());
            graphics.copyArea(image, 0, 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageOut.write((Image)image, (String)"png", (OutputStream)byteArrayOutputStream);
            com.corrodinggames.rts.gameFramework.GlobalState.f(null, "Saving screenshot: " + string2);
            DesktopGameContainer$1 u$1 = new DesktopGameContainer$1(this, string3, byteArrayOutputStream, string2);
            Thread thread = new Thread(u$1);
            thread.start();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            com.corrodinggames.rts.gameFramework.GlobalState.reportProblem("Failed to take screenshot:" + exception.getMessage());
            return;
        }
        finally {
            if (d2 != null) {
                d2.a = n2;
                d2.b = n3;
            }
        }
    }
}
