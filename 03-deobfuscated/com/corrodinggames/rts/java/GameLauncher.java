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
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.network.NetworkAuth;
import com.corrodinggames.rts.game.units.DecorType1;

import android.content.ServerContext;
import android.graphics.Point;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.platform.NullSoundFactory;
import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.PingTimer;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.rendering.TextureManager;
import com.corrodinggames.rts.gameFramework.utility.aj;
import com.corrodinggames.rts.java.GameLauncher$1;
import com.corrodinggames.rts.java.GameLauncher$2;
import com.corrodinggames.rts.java.GameLauncher$3;
import com.corrodinggames.rts.java.GameLauncher$4;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.GameWindow;
import com.corrodinggames.rts.java.graphics.a;
import com.corrodinggames.rts.java.DesktopAppFramework;
import com.corrodinggames.rts.java.Slick2DRenderer;
import com.corrodinggames.rts.java.DesktopPlatform;
import com.corrodinggames.rts.java.LWJGLDisplayThread;
import com.corrodinggames.rts.java.GameConfig;
import com.corrodinggames.rts.java.ResourceLoader;
import com.corrodinggames.rts.java.UpdateChecker;
import com.corrodinggames.rts.java.SlickTexture;
import com.corrodinggames.rts.java.DesktopGameContainer;
import com.corrodinggames.rts.java.ModDownloader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;  // v19.133: 02b Main 鍚?import
import org.newdawn.slick.Game;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;  // 02b Main L46: slick Renderer (setRenderer/get)
import org.newdawn.slick.opengl.renderer.SGL;  // v19.133: 02b Main 鍚?import
import org.newdawn.slick.opengl.renderer.VBORenderer;

public class GameLauncher
extends com.corrodinggames.rts.gameFramework.network.NetworkAuth {
    public com.corrodinggames.rts.java.DesktopGameContainer j;  // 02b Main.j (v19.133f)
    public boolean u;  // 02b Main.u (v19.133f)
    public int v;  // 02b Main.v (v19.133f)  // 02b j/ac; 闁告瑥鑻晶鐘电磽閳ь剟鐛弰蹇ｆ綍濞ｅ浂鍠楅?
    public static boolean isRunning = false;
    public static boolean useSlick2D = true;
    public static String windowTitle = "Rusted Warfare";
    public DesktopAppFramework platformConfig;
    public String versionString = "#28";
    aj f = new aj();
    boolean g = true;
    public NetEngine gameInstance;
    com.corrodinggames.rts.java.graphics.a i;  // 02b: b.a i (TextureProxy 妤犵偞妲掗～搴ㄥ触瀹ュ嫭鍙忔慨? graphics/a=02b b.a)
    public DesktopGameContainer gameSettings;
    GameWindow k;
    String[] l;
    static GameLauncher m;
    int n;
    long o = System.nanoTime();
    com.corrodinggames.rts.java.filesystem.a p;
    com.corrodinggames.rts.gameFramework.GameState q = new DesktopPlatform(this);
    Thread r;
    boolean s = true;
    Object t = new Object();
    public boolean fullscreenMode;
    public int targetFPS;

    public static void main(String[] stringArray) {
        m = new GameLauncher();
        m.a(stringArray);  // 02b Main L92: m.a(String[]) 闂備礁鎲￠悷銉╁磹瑜版帒姹查柣鏃傚劋閸犲棝鏌ㄥ┑鍡橆棤闁?(CrashHandler 婵°倗濮烽崑鐐参ｉ幒妤嬬稏閹兼番鍔岀憴锔锯偓骞垮劚鐎氼參宕ｈ箛鏃€鍙?
        GameLauncher$1 main$1 = new GameLauncher$1();
    }

    public static void a(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e(string);
    }

    public void f() {
        com.corrodinggames.rts.gameFramework.utility.l l2 = new com.corrodinggames.rts.gameFramework.utility.l(new InputStreamReader(System.in));
        while (this.g) {
            try {
                String string = l2.getString();  // 02b: utility/l.a() (LineReader 鐠囨槒顢?
                if (string == null) {
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    continue;
                }
                this.a((com.corrodinggames.rts.gameFramework.network.PlayerConnect)null, "ADMIN", string, true);  // 02b Main L96: a(j.c,String,String,boolean) (j.c=PlayerConnect); isRunning 闂佽瀛╃粙鎺椼€冮崱娑辨晩鐎光偓閳ь剟骞忛悩娲绘晬婵犲﹤瀚▓銏＄箾閿濆懏绁╅柛鐘愁殜椤?
            }
            catch (IOException iOException) {
                if (this.n < 3) {  // $3 缂傚倷绶￠崑澶愵敋瑜旈獮鍐即閻愭剬鍥х労闁告劦浜滅敮顒勬⒑鏉炴壆鍔嶆繝銏★耿閹缚顦崇紒宀勪憾椤㈡瑧绮电€ｎ亜螚
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Error while reading stdin: " + iOException.toString());
                    ++this.n;
                    if (this.n == 3) {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("Too many stdin errors, ignoring");
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
        Thread thread = new Thread(new GameLauncher$2(this, semaphore));
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
        com.corrodinggames.rts.gameFramework.GlobalState.e("Reading args");
        String string3 = null;
        String string4 = null;
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            object = stringArray[n2].trim().toLowerCase(Locale.ENGLISH);
            if (string3 != null) {
                if (string3.equals("+connect_lobby")) {
                    GameLauncher.a("connect lobby:" + (String)object);
                    com.corrodinggames.rts.gameFramework.GlobalState.aK = (String)object;  // 02b L154: l.aK = var15
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
                GameLauncher.a("Unknown two_part_arg: " + string3);
                string3 = null;
                continue;
            }
            if (((String)object).equals("-debug")) {
                if (++n2 >= stringArray.length) {
                    GameLauncher.a("-debug requires parameters");
                    System.exit(1);
                }
                string2 = stringArray[n2];
                int n5 = Integer.parseInt(string2.split(":")[0]);
                string = string2.split(":")[1];
                com.corrodinggames.rts.gameFramework.commands.DebugServer.a(n5, string);  // 02b: a.a.a(int,String) (rts/a/a=DebugServer)
                continue;
            }
            if (((String)object).equals("-debugscript")) {
                if (++n2 >= stringArray.length) {
                    GameLauncher.a("-debugscript requires parameters");
                    System.exit(1);
                }
                string2 = stringArray[n2];
                com.corrodinggames.rts.gameFramework.commands.DebugServer.a(string2);  // 02b: a.a.a(String)
                continue;
            }
            if (((String)object).equals("-log")) {
                if (++n2 >= stringArray.length) {
                    GameLauncher.a("-log requires parameters");
                    System.exit(1);
                }
                string2 = stringArray[n2];
                try {
                    PrintStream printStream = new PrintStream(string2);
                    System.setOut(printStream);
                    System.setErr(printStream);
                    com.corrodinggames.rts.gameFramework.GlobalState.e("File logging started");
                }
                catch (FileNotFoundException fileNotFoundException) {
                    com.corrodinggames.rts.gameFramework.GlobalState.a("Cannot open log file:" + string2);
                    fileNotFoundException.printStackTrace();
                }
                continue;
            }
            if (((String)object).equals("-nologfile")) continue;
            if (((String)object).equals("-lang")) {
                if (++n2 >= stringArray.length) {
                    GameLauncher.a("-lang requires parameters");
                    System.exit(1);
                }
                com.corrodinggames.rts.gameFramework.steam.Localization.d = string2 = stringArray[n2];
                continue;
            }
            if (((String)object).equals("-logcolor")) {
                com.corrodinggames.rts.gameFramework.GlobalState.ax = true;
                continue;
            }
            if (((String)object).equals("-nodisplay")) {
                bl = true;
                continue;
            }
            if (((String)object).equals("-canvasgl")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aD = true;
                continue;
            }
            if (((String)object).equals("-replay_debug")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aw = true;
                continue;
            }
            if (((String)object).equals("-nopreferipv4")) {
                bl4 = true;
                continue;
            }
            if (((String)object).equals("-noresources")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aB = true;
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
                com.corrodinggames.rts.gameFramework.GlobalState.aO = true;
                continue;
            }
            if (((String)object).equals("-extrasafemode")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aP = true;
                continue;
            }
            if (((String)object).equals("-disable_vbos")) {
                bl7 = true;
                continue;
            }
            if (((String)object).equals("-disable_atlas")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aC = true;
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
                com.corrodinggames.rts.gameFramework.GlobalState.ay = true;
                continue;
            }
            if (((String)object).equals("-nomods")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aJ = true;
                continue;
            }
            if (((String)object).equals("-printunits")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aE = true;
                continue;
            }
            if (((String)object).equals("-outputunitimages")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aF = true;
                continue;
            }
            if (((String)object).equals("-oldreplays")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aG = true;
                continue;
            }
            if (((String)object).equals("-teamshaders")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aN = true;
                continue;
            }
            if (((String)object).equals("-noteamshaders")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aN = false;
                continue;
            }
            if (((String)object).equals("-devdebug")) {
                if (++n2 >= stringArray.length) {
                    GameLauncher.a("-debugscript requires parameters");
                    System.exit(1);
                }
                com.corrodinggames.rts.gameFramework.GlobalState.aQ = string2 = stringArray[n2];
                continue;
            }
            if (((String)object).equals("-postprocessing")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aM = true;
                continue;
            }
            if (((String)object).equals("-nopostprocessing")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aM = false;
                continue;
            }
            if (((String)object).equals("-disabletextureread")) {
                SlickTexture.F = false;
                continue;
            }
            if (((String)object).equals("-sandbox")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aI = true;
                continue;
            }
            if (((String)object).equals("-steam")) {
                com.corrodinggames.rts.gameFramework.GlobalState.aH = true;
                continue;
            }
            if (((String)object).equals("-width") || ((String)object).equals("-height")) {
                string3 = (String)object;  // 02b L291: var12 = var15
                continue;
            }
            if (((String)object).startsWith("+")) {
                if (((String)object).equals("+connect_lobby")) {
                    string3 = (String)object;  // 02b L282: var12 = var15
                    continue;
                }
                GameLauncher.a("Unknown steam option: " + (String)object);
                continue;
            }
            if (((String)object).trim().length() == 0) continue;
            GameLauncher.a("Unknown option: " + (String)object);
            string4 = "Unknown option: " + (String)object;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Game arguments:");
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            object = stringArray[n2].trim().toLowerCase(Locale.ENGLISH);
            GameLauncher.a("arg: " + (String)object);
        }
        if (string4 != null) {
            if (com.corrodinggames.rts.gameFramework.GlobalState.aH) {
                GameLauncher.a("Unknown options but running anyway due to being in steam");
            } else {
                GameLauncher.a("Exiting due to unknown option: " + string4);
                System.exit(1);
            }
        }
        com.corrodinggames.rts.gameFramework.GlobalState.aU = true;
        com.corrodinggames.rts.gameFramework.GlobalState.initIntegrityAndCrashHandler();
        String string5 = System.getProperty("os.name");
        com.corrodinggames.rts.gameFramework.GlobalState.e("OS name: " + string5);
        com.corrodinggames.rts.gameFramework.GlobalState.e("OS version: " + System.getProperty("os.version"));
        com.corrodinggames.rts.gameFramework.GlobalState.e("LWJGL version: " + Sys.getVersion());
        com.corrodinggames.rts.gameFramework.GlobalState.e("Build Number: " + this.versionString);
        com.corrodinggames.rts.gameFramework.GlobalState.e("Game Version: 1.15");
        com.corrodinggames.rts.gameFramework.GlobalState.e("Game Code: 176");
        com.corrodinggames.rts.game.GameEngine.b = Sys.is64Bit();  // 02b: game/i.b (screens 濡ょ姷鍋炲Σ鎺楋綖鎼淬劌绀岄柛娑卞墯閸欏繑鎱?
        com.corrodinggames.rts.gameFramework.GlobalState.e("Is 64bit: " + com.corrodinggames.rts.game.GameEngine.b);
        com.corrodinggames.rts.gameFramework.GlobalState.e("JVM maxMemory:" + Runtime.getRuntime().maxMemory());
        com.corrodinggames.rts.gameFramework.GlobalState.e("JVM totalMemory:" + Runtime.getRuntime().totalMemory());
        com.corrodinggames.rts.gameFramework.GlobalState.e("JVM freeMemory:" + Runtime.getRuntime().freeMemory());
        if (string5 != null && string5.toLowerCase().contains("mac os")) {
            com.corrodinggames.rts.game.GameEngine.c = true;  // 02b: game/i.c (isMac)
        }
        if (bl4) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping preferIPv4Stack=true");
        } else {
            System.setProperty("java.net.preferIPv4Stack", "true");
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aH) {
            com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a = new com.corrodinggames.rts.java.input.SteamManager();  // 02b: o.a.a = new c.b()
            com.corrodinggames.rts.gameFramework.GlobalState.e("Early steam init");
            com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().b();
            com.corrodinggames.rts.gameFramework.GlobalState.e("Early steam init done.");
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.e("steam not requested");
        }
        this.g();
        object = windowTitle;
        if (bl) {
            object = "";
        }
        Input.disableControllers();
        if (isRunning) {  // 02b L353: if(a) (a=闈欐€?isRunning 瀛楁)
            Renderer.setRenderer(2);  // $2 缂備緡鍋夐褔骞冮弴鐐愌囧焺閸愵亜甯梺杞扮劍濠㈡﹢鎮鸿缁岄亶顢欑粵瀣Η (02b L354)
        }
        if (!bl8 && com.corrodinggames.rts.game.GameEngine.c) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Disabling vbo on mac (without force option)");
            bl7 = true;
        }
        if (bl7) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("disable_vbos requested");
            SGL sgl = Renderer.get();  // 02b L364: SGL var28 (String 闂備焦瀵ч悷锕傛偉閿濆棛鈹嶆い鏃傜摂閸斺偓)
            if (sgl instanceof VBORenderer) {
                VBORenderer vBORenderer = (VBORenderer)sgl;
                vBORenderer.disableVBOs();
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Failed to disable VBOs, wrong class");
            }
        }
        com.corrodinggames.rts.java.Slick2DRenderer.c();  // 02b: e.c() (java/e=Slick2DRenderer)
        this.gameSettings = new DesktopGameContainer((String)object);
        this.gameSettings.b = this;
        this.gameSettings.i = bl;
        this.gameSettings.j = bl2;
        this.gameSettings.k = bl3;
        if (bl) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("Skipping display mode call");
            f3 = 800.0f;
            f2 = 600.0f;
        } else {
            try {
                DisplayMode displayMode = Display.getDisplayMode();  // 02b L387: DisplayMode var32 (String 闂備焦瀵ч悷锕傛偉閿濆棛鈹嶆い鏃傜摂閸斺偓)
                f3 = displayMode.getHeight();
                f2 = displayMode.getWidth();
            }
            catch (Exception exception) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("Failed to get display mode, defaulting to min size");
                exception.printStackTrace();
                f3 = 800.0f;
                f2 = 600.0f;
            }
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("screenHeight:" + f3);
        com.corrodinggames.rts.gameFramework.GlobalState.e("screenWidth:" + f2);
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
            com.corrodinggames.rts.gameFramework.GlobalState.e("Overriding width to:" + n3);
            n6 = n3;
        }
        if (n4 != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Overriding height to:" + n4);
            n7 = n4;
        }
        if (bl5) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("allowSoftwareOpenGL is now on");
            System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        }
        this.gameSettings.l = false;
        boolean bl9 = bl6;
        try {
            if (this.gameSettings.l) {
                this.gameSettings.a(n6 * 2, n7 * 2);
                this.k = new GameWindow((Game)new ScalableGame((Game)this.gameSettings, n6, n7), n6, n7, bl9);
            } else {
                this.k = new GameWindow((Game)this.gameSettings, n6, n7, bl9);
            }
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
        this.gameSettings.c = this.k;
        Display.setResizable((boolean)true);
        object = new LWJGLDisplayThread(this);
        this.r = new Thread((Runnable)object);
        this.r.setDaemon(false);
        this.r.start();
    }

    public void b(String string) {
        this.q.a(string, true);
    }

    public synchronized void h() {
        this.b("displayModes");  // 02b L459: this.b(String) 闂佸搫鍟ㄩ崕杈╂崲? useSlick2D 闁诲孩绋掗〃鍡涱敊瀹€鈧幏鐘活敍濠婂嫭娈㈡繛锝呮祩閸犳顢?
        this.b("starting controllers");
        this.o = System.nanoTime();
        com.corrodinggames.rts.gameFramework.GlobalState.aU = true;
        com.corrodinggames.rts.gameFramework.GlobalState.bb = true;
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aB) {
            if (com.corrodinggames.rts.gameFramework.GlobalState.aD) {
                com.corrodinggames.rts.gameFramework.GlobalState.aX = true;
                com.corrodinggames.rts.gameFramework.GlobalState.aW = true;
                com.corrodinggames.rts.gameFramework.GlobalState.bg = com.corrodinggames.rts.gameFramework.rendering.TextureManager.class;  // 02b L468: l.bg = x.class (m/x=TextureManager)
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.aX = true;
                com.corrodinggames.rts.gameFramework.GlobalState.aW = true;
                com.corrodinggames.rts.gameFramework.GlobalState.bg = com.corrodinggames.rts.java.Slick2DRenderer.class;  // 02b L472: l.bg = e.class (java/e=Slick2DRenderer)
            }
        }
        if (this.gameSettings != null && !this.gameSettings.j) {
            int n2 = 20;
            OpenALAudio openALAudio = new OpenALAudio(n2, 9, 512);
            com.corrodinggames.rts.gameFramework.GlobalState.e("openALAudio hasDevice:" + openALAudio.hasDevice());
            com.corrodinggames.rts.gameFramework.platform.SoundRegistry.c = new UpdateChecker(openALAudio);  // 02b: a.e.c = new o() (a/e=SoundRegistry); PlatformAudio 濡ょ姷鍋炲Σ鎺楋綖鎼淬劌瑙︾€广儱瀚崣蹇旀叏?
            if (this.gameSettings.k) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Music disabled");
                com.corrodinggames.rts.gameFramework.MusicController.a = new PingTimer();  // 02b: am.a = new av() (am=MusicController, av=PingTimer)
            } else {
                com.corrodinggames.rts.gameFramework.MusicController.a = new ResourceLoader(openALAudio);  // 02b: am.a = new l() (java/l=ResourceLoader)
            }
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Disabling sound with NullSoundFactory");
            com.corrodinggames.rts.gameFramework.platform.SoundRegistry.c = new com.corrodinggames.rts.gameFramework.platform.NullSoundFactory();  // 02b: a.e.c = new a.f() (a/f=NullSoundFactory); FontKey 濡ょ姷鍋炲Σ鎺楋綖鎼淬劌瑙︾€广儱瀚崣蹇旀叏?
            com.corrodinggames.rts.gameFramework.MusicController.a = new PingTimer();
        }
        com.corrodinggames.rts.gameFramework.network.WebAPIClient.d = new GameConfig();
        com.corrodinggames.rts.gameFramework.KeyBindingManager.b = new ModDownloader();  // 02b: ac.b = new v() (ac=KeyBindingManager, java/v=ModDownloader extends InputProvider)
        long l2 = ExtraManager.a();
        this.b("loading libRocket");  // 02b L496: this.b(String) 闂佸搫鍟ㄩ崕杈╂崲?
        com.corrodinggames.rts.gameFramework.GlobalState.e("start libRocket setup");
        this.platformConfig = new DesktopAppFramework();
        this.i = com.corrodinggames.rts.java.graphics.a.p();
        this.i.f = this;
        this.p = new com.corrodinggames.rts.java.filesystem.a();
        this.i.a(this.p, this.platformConfig);
        this.p.debug = false;
        this.p.setup();
        this.b("libRocket - fonts");  // 02b L505: 闁哄啨鍎辩换? useSlick2D 閻庢稒顨嗛宀€鎷犻婊勬殢濞ｅ浂鍠楅?
        this.p.loadFont("font/Delicious-Roman.otf");
        this.p.loadFont("font/Delicious-Italic.otf");
        this.p.loadFont("font/Delicious-Bold.otf");
        this.p.loadFont("font/Delicious-BoldItalic.otf");
        this.p.loadFont("font/Roboto-Regular.ttf");
        this.p.loadFont("font/Roboto-Bold.ttf");
        com.corrodinggames.rts.gameFramework.GlobalState.e("NotoSansCJKsc start");
        this.p.loadFont("font/NotoSansCJKsc-Regular.otf", "notoSans");
        this.p.loadFont("font/DroidSansFallback.ttf", "fallback");
        com.corrodinggames.rts.gameFramework.GlobalState.e("NotoSansCJKsc end");
        this.i.c();
        com.corrodinggames.rts.gameFramework.GlobalState.e("end libRocket setup");
        this.b("GuiEngine");  // 02b L518: 闁哄啨鍎辩换? useSlick2D 閻庢稒顨嗛宀€鎷犻婊勬殢濞ｅ浂鍠楅?
        ExtraManager.a("libRocket setup took:", l2);
        com.corrodinggames.rts.gameFramework.GlobalState.dz = this.versionString;
        ServerContext serverContext = new ServerContext();
        this.b("GameEngine");  // 02b L522: 闁哄啨鍎辩换? useSlick2D 閻庢稒顨嗛宀€鎷犻婊勬殢濞ｅ浂鍠楅?
        int n3 = this.gameSettings.a.getWidth();
        int n4 = this.gameSettings.a.getHeight();
        com.corrodinggames.rts.gameFramework.GlobalState.ck = new Point(n3, n4);
        GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.a(serverContext, this.q);
        this.b("GameEngine ready");  // 02b L527: 闁哄啨鍎辩换? useSlick2D 閻庢稒顨嗛宀€鎷犻婊勬殢濞ｅ浂鍠楅?
        com.corrodinggames.rts.gameFramework.GlobalState.e("version: " + l3.getVersion() + " " + l3.c(false) + ":" + this.versionString);  // 02b L528: var6.u() = getVersion
        this.i.b();
        com.corrodinggames.rts.gameFramework.commands.DebugServer.b();  // 02b: a.a.b() (reliableudp 妤犵偞妲掗～搴ㄥ礌閸涱剚鍙忔慨?
        this.gameInstance = l3.bX;  // 02b L531: this.h = var6.bX (player 妤犵偞妲掗～搴ㄥ触瀹ュ嫭鍙忔慨?
        l3.bQ.showZoomButton = false;
        l3.bQ.showUnitGroups = false;
        this.gameSettings.a(this.platformConfig);
        this.gameSettings.a(1000, 800);
        long l4 = System.nanoTime();
        com.corrodinggames.rts.gameFramework.GlobalState.e("-----------------------------");
        com.corrodinggames.rts.gameFramework.GlobalState.e("----- Game init finished in:" + (double)(l4 - this.o) / 1000000.0 + " ms");
        l3.bX.d = this;  // 02b L539: var6.bX.d = this (player 妤犵偞妲掗～搴ㄥ触瀹ュ嫭鍙忔慨?
        l3.bX.y = "unset";  // 02b L540: var6.bX.y
        if (!com.corrodinggames.rts.gameFramework.GlobalState.ay) {
            // empty if block
        }
    }

    @Override
    public void b() {
        GameLauncher$3 main$3 = new GameLauncher$3(this);
        this.f.a(main$3);
    }

    public void a(float f2) {
        this.f.a();
    }

    public void a(boolean bl) {
        this.g = false;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!bl) {
            l2.bX.waitForSyncComplete();
        } else {
            l2.bX.m("shutdownServer");
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

    public GameLauncher() {  // 02b Main 构造 (v19.133f)
    }


    public synchronized boolean a(com.corrodinggames.rts.gameFramework.network.PlayerConnect c2, String string, String string2) {
        return true;
    }


    public synchronized void b(com.corrodinggames.rts.gameFramework.network.PlayerConnect c2, String string, String string2) {
        this.a(c2, string, string2, false);  // 02b L583-585: b(j.c,String,String) 閳?a(...,false); isRunning 鐎涙顔岀拠顖滄暏娣囶喗顒?
    }

    @Override
    public void c() {
        ScriptEngine scriptEngine;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!l2.bX.aW && (scriptEngine = ScriptEngine.getInstance()) != null) {
            scriptEngine.addScriptToQueueIfNotAlreadyQueued("mp.refreshUI()");
        }
    }


    public synchronized void a(int n2, String string, String string2, com.corrodinggames.rts.gameFramework.network.PacketDecoder c2) {  // com.corrodinggames.rts.gameFramework.network.PlayerConnect 濡ょ姷鍋炲Σ鎺楋綖鎼淬劌瑙︾€广儱瀚崣蹇旀叏?(02b j.c)
        if (this.p != null && this.p.c != null) {
            this.p.c.addRunnableToQueue(new GameLauncher$4(this, n2, string, string2, c2));
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.T();
        }
    }

    public synchronized void a(com.corrodinggames.rts.gameFramework.network.PlayerConnect c2, String string, String string2, boolean bl) {
        if (!bl) {
            GameLauncher.a(string + ": " + string2);
        }
        if (!this.s) {
            // 02b L612: if(!this.s) 缁屽搫娼?(03 return 闁槒绶惄绋垮冀娣囶喗顒?
        }
    }


    public String a(com.corrodinggames.rts.gameFramework.network.PlayerConnect c2, String string) {
        return null;
    }


    public synchronized void c(com.corrodinggames.rts.gameFramework.network.PlayerConnect c2, String string, String string2) {
    }


    public synchronized void b(com.corrodinggames.rts.gameFramework.network.PlayerConnect c2, String string) {
    }

    public void i() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.gameSettings.g();
    }

    @Override
    public void d() {
        com.corrodinggames.librocket.LibRocketContext.a().o();
    }

    @Override
    public void a(PasswordManager ae2) {
        com.corrodinggames.librocket.LibRocketContext.a().a(ae2);
    }
}
