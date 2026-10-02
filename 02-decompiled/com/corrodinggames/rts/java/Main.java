/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.Sys
 *  org.lwjgl.opengl.Display
 *  org.newdawn.slick.Game
 *  org.newdawn.slick.Input
 *  org.newdawn.slick.ScalableGame
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.opengl.renderer.Renderer
 *  org.newdawn.slick.opengl.renderer.VBORenderer
 */
package com.corrodinggames.rts.java;

import android.content.ServerContext;
import android.graphics.Point;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.a.f;
import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.av;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.m.x;
import com.corrodinggames.rts.gameFramework.utility.aj;
import com.corrodinggames.rts.java.Main$1;
import com.corrodinggames.rts.java.Main$2;
import com.corrodinggames.rts.java.Main$3;
import com.corrodinggames.rts.java.Main$4;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.b;
import com.corrodinggames.rts.java.b.a;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Game;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.VBORenderer;

public class Main
extends com.corrodinggames.rts.gameFramework.j.ac {
    public static boolean a = false;
    public static boolean b = true;
    public static String c = "Rusted Warfare";
    public d d;
    public String e = "#28";
    aj f = new aj();
    boolean g = true;
    public ad h;
    a i;
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

    public static void main(String[] stringArray) {
        m = new Main();
        m.a(stringArray);
        Main$1 main$1 = new Main$1();
    }

    public static void a(String string) {
        com.corrodinggames.rts.gameFramework.l.e(string);
    }

    public void f() {
        com.corrodinggames.rts.gameFramework.utility.l l2 = new com.corrodinggames.rts.gameFramework.utility.l(new InputStreamReader(System.in));
        while (this.g) {
            try {
                String string = l2.a();
                if (string == null) {
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    continue;
                }
                this.a(null, "ADMIN", string, true);
            }
            catch (IOException iOException) {
                if (this.n < 3) {
                    com.corrodinggames.rts.gameFramework.l.e("Error while reading stdin: " + iOException.toString());
                    ++this.n;
                    if (this.n == 3) {
                        com.corrodinggames.rts.gameFramework.l.e("Too many stdin errors, ignoring");
                    }
                }
                try {
                    Thread.sleep(700L);
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    public void g() {
        Semaphore semaphore = new Semaphore(0);
        Thread thread = new Thread(new Main$2(this, semaphore));
        thread.setDaemon(true);
        thread.start();
        try {
            semaphore.acquire();
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public synchronized void a(String[] stringArray) {
        float f2;
        float f3;
        String string;
        String string2;
        Object object;
        int n2;
        this.l = stringArray;
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = false;
        boolean bl7 = false;
        boolean bl8 = false;
        Integer n3 = null;
        Integer n4 = null;
        com.corrodinggames.rts.gameFramework.l.e("Reading args");
        String string3 = null;
        String string4 = null;
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            object = stringArray[n2].trim().toLowerCase(Locale.ENGLISH);
            if (string3 != null) {
                if (string3.equals("+connect_lobby")) {
                    Main.a("connect lobby:" + (String)object);
                    com.corrodinggames.rts.gameFramework.l.aK = object;
                    string3 = null;
                    continue;
                }
                if (string3.equals("-width")) {
                    n3 = Integer.parseInt((String)object);
                    string3 = null;
                    continue;
                }
                if (string3.equals("-height")) {
                    n4 = Integer.parseInt((String)object);
                    string3 = null;
                    continue;
                }
                Main.a("Unknown two_part_arg: " + string3);
                string3 = null;
                continue;
            }
            if (((String)object).equals("-debug")) {
                if (++n2 >= stringArray.length) {
                    Main.a("-debug requires parameters");
                    System.exit(1);
                }
                string2 = stringArray[n2];
                int n5 = Integer.parseInt(string2.split(":")[0]);
                string = string2.split(":")[1];
                com.corrodinggames.rts.a.a.a(n5, string);
                continue;
            }
            if (((String)object).equals("-debugscript")) {
                if (++n2 >= stringArray.length) {
                    Main.a("-debugscript requires parameters");
                    System.exit(1);
                }
                string2 = stringArray[n2];
                com.corrodinggames.rts.a.a.a(string2);
                continue;
            }
            if (((String)object).equals("-log")) {
                if (++n2 >= stringArray.length) {
                    Main.a("-log requires parameters");
                    System.exit(1);
                }
                string2 = stringArray[n2];
                try {
                    PrintStream printStream = new PrintStream(string2);
                    System.setOut(printStream);
                    System.setErr(printStream);
                    com.corrodinggames.rts.gameFramework.l.e("File logging started");
                }
                catch (FileNotFoundException fileNotFoundException) {
                    com.corrodinggames.rts.gameFramework.l.a("Cannot open log file:" + string2);
                    fileNotFoundException.printStackTrace();
                }
                continue;
            }
            if (((String)object).equals("-nologfile")) continue;
            if (((String)object).equals("-lang")) {
                if (++n2 >= stringArray.length) {
                    Main.a("-lang requires parameters");
                    System.exit(1);
                }
                com.corrodinggames.rts.gameFramework.h.a.d = string2 = stringArray[n2];
                continue;
            }
            if (((String)object).equals("-logcolor")) {
                com.corrodinggames.rts.gameFramework.l.ax = true;
                continue;
            }
            if (((String)object).equals("-nodisplay")) {
                bl = true;
                continue;
            }
            if (((String)object).equals("-canvasgl")) {
                com.corrodinggames.rts.gameFramework.l.aD = true;
                continue;
            }
            if (((String)object).equals("-replay_debug")) {
                com.corrodinggames.rts.gameFramework.l.aw = true;
                continue;
            }
            if (((String)object).equals("-nopreferipv4")) {
                bl4 = true;
                continue;
            }
            if (((String)object).equals("-noresources")) {
                com.corrodinggames.rts.gameFramework.l.aB = true;
                continue;
            }
            if (((String)object).equals("-nosound")) {
                bl2 = true;
                continue;
            }
            if (((String)object).equals("-nomusic")) {
                bl3 = true;
                continue;
            }
            if (((String)object).equals("-safemode")) {
                com.corrodinggames.rts.gameFramework.l.aO = true;
                continue;
            }
            if (((String)object).equals("-extrasafemode")) {
                com.corrodinggames.rts.gameFramework.l.aP = true;
                continue;
            }
            if (((String)object).equals("-disable_vbos")) {
                bl7 = true;
                continue;
            }
            if (((String)object).equals("-disable_atlas")) {
                com.corrodinggames.rts.gameFramework.l.aC = true;
                continue;
            }
            if (((String)object).equals("-force_vbos")) {
                bl8 = true;
                continue;
            }
            if (((String)object).equals("-allowsoftwarerender")) {
                bl5 = true;
                continue;
            }
            if (((String)object).equals("-fullscreen")) {
                bl6 = true;
                continue;
            }
            if (((String)object).equals("-nobackground")) {
                com.corrodinggames.rts.gameFramework.l.ay = true;
                continue;
            }
            if (((String)object).equals("-nomods")) {
                com.corrodinggames.rts.gameFramework.l.aJ = true;
                continue;
            }
            if (((String)object).equals("-printunits")) {
                com.corrodinggames.rts.gameFramework.l.aE = true;
                continue;
            }
            if (((String)object).equals("-outputunitimages")) {
                com.corrodinggames.rts.gameFramework.l.aF = true;
                continue;
            }
            if (((String)object).equals("-oldreplays")) {
                com.corrodinggames.rts.gameFramework.l.aG = true;
                continue;
            }
            if (((String)object).equals("-teamshaders")) {
                com.corrodinggames.rts.gameFramework.l.aN = true;
                continue;
            }
            if (((String)object).equals("-noteamshaders")) {
                com.corrodinggames.rts.gameFramework.l.aN = false;
                continue;
            }
            if (((String)object).equals("-devdebug")) {
                if (++n2 >= stringArray.length) {
                    Main.a("-debugscript requires parameters");
                    System.exit(1);
                }
                com.corrodinggames.rts.gameFramework.l.aQ = string2 = stringArray[n2];
                continue;
            }
            if (((String)object).equals("-postprocessing")) {
                com.corrodinggames.rts.gameFramework.l.aM = true;
                continue;
            }
            if (((String)object).equals("-nopostprocessing")) {
                com.corrodinggames.rts.gameFramework.l.aM = false;
                continue;
            }
            if (((String)object).equals("-disabletextureread")) {
                com.corrodinggames.rts.java.s.F = false;
                continue;
            }
            if (((String)object).equals("-sandbox")) {
                com.corrodinggames.rts.gameFramework.l.aI = true;
                continue;
            }
            if (((String)object).equals("-steam")) {
                com.corrodinggames.rts.gameFramework.l.aH = true;
                continue;
            }
            if (((String)object).equals("-width") || ((String)object).equals("-height")) {
                string3 = object;
                continue;
            }
            if (((String)object).startsWith("+")) {
                if (((String)object).equals("+connect_lobby")) {
                    string3 = object;
                    continue;
                }
                Main.a("Unknown steam option: " + (String)object);
                continue;
            }
            if (((String)object).trim().length() == 0) continue;
            Main.a("Unknown option: " + (String)object);
            string4 = "Unknown option: " + (String)object;
        }
        com.corrodinggames.rts.gameFramework.l.e("Game arguments:");
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            object = stringArray[n2].trim().toLowerCase(Locale.ENGLISH);
            Main.a("arg: " + (String)object);
        }
        if (string4 != null) {
            if (com.corrodinggames.rts.gameFramework.l.aH) {
                Main.a("Unknown options but running anyway due to being in steam");
            } else {
                Main.a("Exiting due to unknown option: " + string4);
                System.exit(1);
            }
        }
        com.corrodinggames.rts.gameFramework.l.aU = true;
        com.corrodinggames.rts.gameFramework.l.aq();
        String string5 = System.getProperty("os.name");
        com.corrodinggames.rts.gameFramework.l.e("OS name: " + string5);
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
        if (string5 != null && string5.toLowerCase().contains("mac os")) {
            com.corrodinggames.rts.game.i.c = true;
        }
        if (bl4) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping preferIPv4Stack=true");
        } else {
            System.setProperty("java.net.preferIPv4Stack", "true");
        }
        if (com.corrodinggames.rts.gameFramework.l.aH) {
            com.corrodinggames.rts.gameFramework.o.a.a = new com.corrodinggames.rts.java.c.b();
            com.corrodinggames.rts.gameFramework.l.e("Early steam init");
            com.corrodinggames.rts.gameFramework.o.a.a().b();
            com.corrodinggames.rts.gameFramework.l.e("Early steam init done.");
        } else {
            com.corrodinggames.rts.gameFramework.l.e("steam not requested");
        }
        this.g();
        object = c;
        if (bl) {
            object = "";
        }
        Input.disableControllers();
        if (a) {
            Renderer.setRenderer((int)2);
        }
        if (!bl8 && com.corrodinggames.rts.game.i.c) {
            com.corrodinggames.rts.gameFramework.l.e("Disabling vbo on mac (without force option)");
            bl7 = true;
        }
        if (bl7) {
            com.corrodinggames.rts.gameFramework.l.e("disable_vbos requested");
            string2 = Renderer.get();
            if (string2 instanceof VBORenderer) {
                VBORenderer vBORenderer = (VBORenderer)string2;
                vBORenderer.disableVBOs();
            } else {
                com.corrodinggames.rts.gameFramework.l.e("Failed to disable VBOs, wrong class");
            }
        }
        com.corrodinggames.rts.java.e.c();
        this.j = new u((String)object);
        this.j.b = this;
        this.j.i = bl;
        this.j.j = bl2;
        this.j.k = bl3;
        if (bl) {
            com.corrodinggames.rts.gameFramework.l.a("Skipping display mode call");
            f3 = 800.0f;
            f2 = 600.0f;
        } else {
            try {
                string = Display.getDisplayMode();
                f3 = string.getHeight();
                f2 = string.getWidth();
            }
            catch (Exception exception) {
                com.corrodinggames.rts.gameFramework.l.a("Failed to get display mode, defaulting to min size");
                exception.printStackTrace();
                f3 = 800.0f;
                f2 = 600.0f;
            }
        }
        com.corrodinggames.rts.gameFramework.l.e("screenHeight:" + f3);
        com.corrodinggames.rts.gameFramework.l.e("screenWidth:" + f2);
        int n6 = 1000;
        int n7 = 733;
        if (f3 > 800.0f) {
            n6 = 1000;
            n7 = 800;
        }
        if (f3 > 900.0f) {
            n6 = 1600;
            n7 = 900;
        }
        if (bl) {
            n6 = 10;
            n7 = 10;
        }
        if (n3 != null) {
            com.corrodinggames.rts.gameFramework.l.e("Overriding width to:" + n3);
            n6 = n3;
        }
        if (n4 != null) {
            com.corrodinggames.rts.gameFramework.l.e("Overriding height to:" + n4);
            n7 = n4;
        }
        if (bl5) {
            com.corrodinggames.rts.gameFramework.l.e("allowSoftwareOpenGL is now on");
            System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        }
        this.j.l = false;
        boolean bl9 = bl6;
        try {
            if (this.j.l) {
                this.j.a(n6 * 2, n7 * 2);
                this.k = new b((Game)new ScalableGame((Game)this.j, n6, n7), n6, n7, bl9);
            } else {
                this.k = new b((Game)this.j, n6, n7, bl9);
            }
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
        this.j.c = this.k;
        Display.setResizable((boolean)true);
        object = new j(this);
        this.r = new Thread((Runnable)object);
        this.r.setDaemon(false);
        this.r.start();
    }

    public void b(String string) {
        this.q.a(string, true);
    }

    public synchronized void h() {
        this.b("displayModes");
        this.b("starting controllers");
        this.o = System.nanoTime();
        com.corrodinggames.rts.gameFramework.l.aU = true;
        com.corrodinggames.rts.gameFramework.l.bb = true;
        if (!com.corrodinggames.rts.gameFramework.l.aB) {
            if (com.corrodinggames.rts.gameFramework.l.aD) {
                com.corrodinggames.rts.gameFramework.l.aX = true;
                com.corrodinggames.rts.gameFramework.l.aW = true;
                com.corrodinggames.rts.gameFramework.l.bg = x.class;
            } else {
                com.corrodinggames.rts.gameFramework.l.aX = true;
                com.corrodinggames.rts.gameFramework.l.aW = true;
                com.corrodinggames.rts.gameFramework.l.bg = e.class;
            }
        }
        if (this.j != null && !this.j.j) {
            int n2 = 20;
            OpenALAudio openALAudio = new OpenALAudio(n2, 9, 512);
            com.corrodinggames.rts.gameFramework.l.e("openALAudio hasDevice:" + openALAudio.hasDevice());
            com.corrodinggames.rts.gameFramework.a.e.c = new o(openALAudio);
            if (this.j.k) {
                com.corrodinggames.rts.gameFramework.l.e("Music disabled");
                am.a = new av();
            } else {
                am.a = new l(openALAudio);
            }
        } else {
            com.corrodinggames.rts.gameFramework.l.b("Disabling sound with NullSoundFactory");
            com.corrodinggames.rts.gameFramework.a.e.c = new f();
            am.a = new av();
        }
        com.corrodinggames.rts.gameFramework.j.n.d = new k();
        ac.b = new v();
        long l2 = br.a();
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
        br.a("libRocket setup took:", l2);
        com.corrodinggames.rts.gameFramework.l.dz = this.e;
        ServerContext serverContext = new ServerContext();
        this.b("GameEngine");
        int n3 = this.j.a.getWidth();
        int n4 = this.j.a.getHeight();
        com.corrodinggames.rts.gameFramework.l.ck = new Point(n3, n4);
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.a(serverContext, this.q);
        this.b("GameEngine ready");
        com.corrodinggames.rts.gameFramework.l.e("version: " + l3.u() + " " + l3.c(false) + ":" + this.e);
        this.i.b();
        com.corrodinggames.rts.a.a.b();
        this.h = l3.bX;
        l3.bQ.showZoomButton = false;
        l3.bQ.showUnitGroups = false;
        this.j.a(this.d);
        this.j.a(1000, 800);
        long l4 = System.nanoTime();
        com.corrodinggames.rts.gameFramework.l.e("-----------------------------");
        com.corrodinggames.rts.gameFramework.l.e("----- Game init finished in:" + (double)(l4 - this.o) / 1000000.0 + " ms");
        l3.bX.d = this;
        l3.bX.y = "unset";
        if (!com.corrodinggames.rts.gameFramework.l.ay) {
            // empty if block
        }
    }

    @Override
    public void b() {
        Main$3 main$3 = new Main$3(this);
        this.f.a(main$3);
    }

    public void a(float f2) {
        this.f.a();
    }

    public void a(boolean bl) {
        this.g = false;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!bl) {
            l2.bX.u();
        } else {
            l2.bX.b("shutdownServer");
        }
        try {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.exit(0);
        }
        catch (SecurityException securityException) {
            securityException.printStackTrace();
        }
    }

    Main() {
    }

    @Override
    public synchronized boolean a(c c2, String string, String string2) {
        return true;
    }

    @Override
    public synchronized void b(c c2, String string, String string2) {
        this.a(c2, string, string2, false);
    }

    @Override
    public void c() {
        ScriptEngine scriptEngine;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!l2.bX.aW && (scriptEngine = ScriptEngine.getInstance()) != null) {
            scriptEngine.addScriptToQueueIfNotAlreadyQueued("mp.refreshUI()");
        }
    }

    @Override
    public synchronized void a(int n2, String string, String string2, c c2) {
        if (this.p != null && this.p.c != null) {
            this.p.c.addRunnableToQueue(new Main$4(this, n2, string, string2, c2));
        } else {
            com.corrodinggames.rts.gameFramework.l.T();
        }
    }

    public synchronized void a(c c2, String string, String string2, boolean bl) {
        if (!bl) {
            Main.a(string + ": " + string2);
        }
        if (this.s) {
            return;
        }
    }

    @Override
    public String a(c c2, String string) {
        return null;
    }

    @Override
    public synchronized void c(c c2, String string, String string2) {
    }

    @Override
    public synchronized void b(c c2, String string) {
    }

    public void i() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.j.g();
    }

    @Override
    public void d() {
        com.corrodinggames.librocket.a.a().o();
    }

    @Override
    public void a(ae ae2) {
        com.corrodinggames.librocket.a.a().a(ae2);
    }
}
