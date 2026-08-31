/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Debug
 */
package com.corrodinggames.rts.gameFramework;
import com.corrodinggames.rts.appFramework.ContextMenuActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;
import com.corrodinggames.rts.gameFramework.pathfinding.PathFinder;
import com.corrodinggames.rts.gameFramework.filesystem.NullStorage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.ProjectileWeapon;
import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.ReplayEngine;
import com.corrodinggames.rts.gameFramework.StatsManager;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.ui.Minimap;
import com.corrodinggames.rts.gameFramework.audio.DataFieldInt;
import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.DataStreamReader$1;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.GameState;
import com.corrodinggames.rts.gameFramework.AssetLoader;
import com.corrodinggames.rts.gameFramework.GameTimerScheduler;
import com.corrodinggames.rts.gameFramework.VersionInfo;
import com.corrodinggames.rts.gameFramework.ThreadPool;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import com.corrodinggames.rts.gameFramework.utility.ANRWatchdog;
import com.corrodinggames.rts.gameFramework.utility.i;
import com.corrodinggames.rts.gameFramework.GameSaver;
import com.corrodinggames.rts.gameFramework.GameThread;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public strictfp abstract class GlobalState {
    public byte ea;
    public byte dZ;
    public final Object aj = new Object();
    public final Object ak = new Object();
    protected static GlobalState al = null;
    public Context am = null;
    public Context an;
    public com.corrodinggames.rts.appFramework.AppFramework ao;
    public com.corrodinggames.rts.appFramework.AppFramework ap;
    public boolean aq;
    public boolean ar = false;
    public static boolean as = true;
    public static boolean at = false;
    public static boolean au = false;
    public static Throwable av;
    public static boolean aw;
    public static boolean ax;
    public static boolean ay;
    public static boolean az;
    public static boolean aA;
    public static boolean aB;
    public static boolean aC;
    public static boolean aD;
    public static boolean aE;
    public static boolean aF;
    public static boolean aG;
    public static boolean aH;
    public static boolean aI;
    public static boolean aJ;
    public static String aK;
    public static boolean useGLES30;
    public static boolean aM;
    public static boolean aN;
    public static boolean aO;
    public static boolean aP;
    public static String aQ;
    public static boolean aR;
    public boolean aS;
    public static boolean aT;
    public static boolean aU;
    public static boolean aL = false;  // 02b l.aL (L87)
    public static boolean aV;
    public static boolean aW;
    public static boolean aX;
    public static boolean aY;
    public static boolean aZ;
    public boolean cW = false;
    public static String ba;
    public static boolean bb;
    public static boolean bc = true;  // 02b l.java L104: static boolean bc (ReplayEngine 自动保存检查)
    public static boolean bd = true;  // 02b l.java L105: static boolean bd
    public static boolean useNewAudio;
    public static boolean useOpenAL;
    public static boolean useLWJGLAudio;
    public static boolean bf;
    public static Class bg;
    public static TextureManagerInterface bh;  // 02b l.java L109: m.y bh (BitmapFont 为幻觉名)
    public boolean bi = false;
    public boolean bj;
    public boolean bk = false;
    public boolean bl = false;
    public boolean bm = false;
    public boolean bn = false;
    public boolean bo = false;
    public boolean bp;
    public boolean bq = false;
    public boolean isStepFrame = false;
    public com.corrodinggames.rts.game.PlayerState bs;
    public float bt = 1.0f;  // v19.113k HUD铁证: gameSpeed ("Game Speed: Xx" 沙盒显示; 同名 UnitInstance.bt 是引用 — 保留短名)
    public float bu = -1.0f;
    public boolean bv;
    public boolean bw;  // v19.113k HUD铁证: invincibleUnits ("Invincible Units" 显示条件; 同名 UnitInstance.bw 是 VariableScope)
    public int bx = 0;
    public int by;
    public int bz;
    public int bA;
    public int bB;
    public int bC;
    public boolean bD;
    public boolean bE = false;
    public volatile boolean bF = false;
    public volatile boolean bG = false;
    public volatile boolean bH = false;
    public volatile boolean bI = false;
    public int bJ;
    public com.corrodinggames.rts.gameFramework.utility.i bK;  // 02b l.java L138: utility/i (资产索引器)
    public com.corrodinggames.rts.game.map.MapEngine bL;
    public SoundRegistry bM;
    public MusicController bN;
    public TextureManagerInterface bO;
    public CollisionEngine bP;
    public SettingsEngine bQ;
    public com.corrodinggames.rts.gameFramework.effects.HUDManager bR;
    public InGameUI bS;
    public KeyBindingManager bT;  // 02b l.java L147: ac bT (KeyBindings 为幻觉名; 02b ac=KeyBindingManager 键绑定注册表)
    public com.corrodinggames.rts.gameFramework.pathfinding.PathFinder bU;
    public ProjectileWeapon bV;
    public Minimap bW;
    public NetEngine bX;
    public StatsManager bY;
    public com.corrodinggames.rts.gameFramework.mods.VersionChecker bZ;  // 02b i.a (L382)
    public GameSaver ca;
    public ReplayEngine cb;
    public com.corrodinggames.rts.game.units.pathfinding.SpatialGrid cc;
    public ExtraManager cd;
    public com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem ce;
    public CommandController cf;
    public com.corrodinggames.rts.gameFramework.audio.DataFieldCollector cg = new com.corrodinggames.rts.gameFramework.audio.DataFieldCollector();
    public boolean ch = false;
    public float ci;
    public float cj;
    public static Point ck;
    public float cl;
    public float cm;
    public float cn = 1.0f;
    public float co;
    public float cp;
    public float cq;
    public float cr;
    public float cs;
    public boolean ct;
    public int cu;
    public int cv;
    public float cw;
    public float cx;
    public float cy;
    public float cz;
    public float cA;
    public float cB;
    public float cC;
    public float cD;
    public float cE;
    public float cF;
    public float cG;
    public float cH;
    public float cI;
    public float cJ;
    public final Rect cK = new Rect();
    public final Rect cL = new Rect();
    public final RectF cM = new RectF();
    public final Rect cN = new Rect();
    public final RectF cO = new RectF();
    public final RectF cP = new RectF();
    public final Rect cQ = new Rect();
    public boolean cR;
    public boolean cS;
    public float cT;
    public boolean cU;
    public float cV = 1.0f;
    public boolean renderingEnabled = false;
    public float cX = 1.0f;
    public float cY = 1.0f;
    public boolean cZ;
    public float da;
    public float db;
    public boolean dc = true;
    public boolean dd = true;
    public boolean de = true;
    public boolean df = true;
    public boolean dg = true;
    public float dh = 0.0f;
    public float di = 0.0f;
    public boolean dj = false;
    protected GameThread dk = null;
    public String dl;
    public InputNetStream dm;
    public Paint dn;
    public Paint uiForegroundPaint;
    public Paint dp;
    public boolean dq = false;
    public boolean dr = false;
    public float ds = 0.0f;
    public boolean dt = false;
    public boolean du = false;
    public boolean dv = false;
    public int dw;
    public float dx = 0.0f;
    public static GlobalStateFactory dy = new GameEngineFactory();  // 02b l.java L230: o dy = new v() (KeyCodeMapper 为幻觉名)
    public static String dz;
    float dA;
    boolean dB = false;
    ArrayList dC = new ArrayList();
    final Handler mainThreadHandler = new Handler(Looper.b());
    public String dE;
    private Runnable a = new GlobalState$1(this);
    public String dF;
    public String dG;
    private Runnable b = new GlobalState$2(this);
    public GameState dH = null;
    transient String dI = null;
    Object dJ = new Object();
    String dK;
    String dL;
    public boolean[] dM = new boolean[10];
    protected ConcurrentLinkedQueue pendingCommandQueue = new ConcurrentLinkedQueue();
    private boolean[] c = new boolean[KeyEvent.a() + 1];
    private boolean[] d = new boolean[KeyEvent.a() + 1];
    private int e;

    public boolean g(int n2) {
        if (n2 >= this.c.length || n2 < 0) {
            return false;
        }
        if (this.c[n2] && this.d[n2]) {
            this.d[n2] = false;
            return true;
        }
        return false;
    }
    public static boolean nativeLibraryLoaded;
    static byte[] dP;
    static byte[] integrityTable2;
    static byte[] integrityTable3;
    static com.corrodinggames.rts.gameFramework.utility.ANRWatchdog dS;  // 02b l.java L255: utility.d dS
    static boolean dT = false;  // 02b l.java L256
    static boolean antiCheatEnabled;
    static int dU;
    static boolean dV;
    static ResourceDomainEnum dW;
    static boolean dX;
    static boolean dY;
    public byte integrityToken1 = (byte)42;
    public byte integrityToken2 = (byte)42;
    public GameTimerScheduler eb = new GameTimerScheduler();
    public GameTimerScheduler ec = new GameTimerScheduler();
    public GameTimerScheduler ed = new GameTimerScheduler();
    public boolean ee;
    public boolean ef;
    public String eg;
    public boolean eh;
    public boolean ei;
    static int ej;

    public static boolean b(Context context) {
        String string = null;
        string = aU ? "dedicatedServer" : context.g().h();
        Log.d("RustedWarfare", "packageName:" + string);
        return string.contains("rtsdemo");
    }

    public boolean A() {
        return this.cS || this.cT > 0.0f || this.cU;
    }

    public static final GlobalState B() {
        return al;
    }

    public static final boolean C() {
        return useLWJGLAudio;
    }

    public static final boolean D() {
        return bf;
    }

    public void c(Context context) {
        AndroidUIHelper.a(context);
        this.am = context;
    }

    public static synchronized GlobalState a(Context context, GameState n2) {
        if (al != null) {
            if (n2 != null) {
                al.dH = n2;
            }
            al.c(context);
            return al;
        }
        al = dy.a(context);
        GlobalState.e("Created new gameEngine of:" + al.getClass().getName());
        if (n2 != null) {
            al.dH = n2;
        }
        al.a(context);
        return al;
    }

    public GlobalState(Context context) {
        Log.d("RustedWarfare", "GameEngine:GameEngine()");
        if (al != null) {
            throw new RuntimeException("gameEngine already created");
        }
        this.c(context);
        al = this;
    }

    protected void finalize() throws Throwable {
        Log.d("RustedWarfare", "GameEngine:finalize()");
        super.finalize();
    }

    public boolean E() {
        return true;
    }

    public abstract void a(Context var1);

    public abstract boolean n();  // 02b l.java L361 (GameEngine L1935 已有实现)

    public abstract boolean p();  // 02b l.p() L363

    public void F() {
    }

    public abstract void a(boolean bl, com.corrodinggames.rts.gameFramework.GameStateEnum s2);  // 02b l.a(boolean,s)

    public abstract void a(boolean bl, boolean bl2, com.corrodinggames.rts.gameFramework.GameStateEnum s2);  // 02b l.a(boolean,boolean,s)

    public abstract boolean a();

    public abstract boolean a(boolean var1);

    public abstract void a(Activity var1, com.corrodinggames.rts.appFramework.AppFramework var2, boolean var3);

    public abstract void b(int var1, int var2);

    public abstract int c(boolean var1);



    public abstract String extractMapLevel();

    public abstract String findNextMapLevel();

    public abstract String v();  // 02b l.java v() (GameEngine 已有实现)

    public abstract String l();  // 02b l.java L365 (GameEngine 补实现)

    public abstract String m();  // 02b l.java L367 (GameEngine 补实现)

    public abstract String r();

    public abstract String t();

    public abstract String getVersion();

    public abstract void s();

    public abstract String getBuildNumber();

    public String G() {
        if (GlobalState.av()) {
            return "PC";
        }
        if (aZ) {
            String string = com.corrodinggames.rts.gameFramework.core.PlatformExtension.a();
            if (string != null) {
                return "IOS - " + string;
            }
            return "IOS";
        }
        if (aU) {
            return "SERVER";
        }
        return Build.MODEL;
    }

    public String H() {
        return dz;
    }



    public abstract void e();

    public abstract void isKeyJustPressed();

    public abstract void x();

    public abstract void a(float var1, int var2);

    public boolean I() {
        return this.bH;
    }

    public synchronized void J() {
        GlobalState.e("--- setRunning ---");
        if (!GlobalState.av() && !aZ) {
            this.bN.h();
        }
        if (!aW && !bb && this.dk == null) {
            this.dk = new GameThread();
            this.dk.a(true);
            this.dk.start();
        }
    }

    public synchronized void K() {
        GlobalState.e("--- setStoppedIfNotInGameThread ---");
        if (Thread.currentThread() != this.dk) {
            this.L();
        }
    }

    public synchronized void L() {
        GlobalState.e("--- setStopped ---");
        if (this.dk == null) {
            Log.d("RustedWarfare", "gameThread already null");
            return;
        }
        if (!GlobalState.av()) {
            this.bN.f();
        }
        this.dk.a(false);
        if (Thread.currentThread() != this.dk) {
            boolean bl = true;
            while (bl) {
                try {
                    this.dk.join();
                    bl = false;
                }
                catch (InterruptedException interruptedException) {}
            }
            Log.d("RustedWarfare", "thread stop");
        } else {
            GlobalState.isKeyJustPressed("currentThread is game thread");
        }
        this.dk = null;
        if (this.ao != null) {
            this.ao.l();
        }
        if (this.bE) {
            Debug.stopMethodTracing();
        }
    }

    public boolean M() {
        if (this.bX == null) {
            return false;
        }
        if (!this.bX.B) {
            return false;
        }
        return !this.bX.F && !this.cb.j();
    }

    public boolean N() {
        if (this.bX == null) {
            return false;
        }
        return this.bX.B;
    }

    public boolean O() {
        if (this.bX == null) {
            return false;
        }
        if (this.bX.F) {
            return true;
        }
        return this.bX.B || this.cb.j();
    }

    public boolean P() {
        if (this.bX == null) {
            return true;
        }
        if (this.bX.F) {
            return true;
        }
        return !this.bX.B && !this.cb.j();
    }

    public void Q() {
        this.ct = false;
        if (this.cy < 0.0f) {
            this.cy = 0.0f;
            this.ct = true;
        }
        if (this.cz < 0.0f) {
            this.cz = 0.0f;
            this.ct = true;
        }
        if (this.bL != null) {
            if (this.cy > this.bL.i() - this.cE) {
                this.cy = this.bL.i() - this.cE;
                this.ct = true;
            }
            if (this.cz > this.bL.j() - this.cB) {
                this.cz = this.bL.j() - this.cB;
                this.ct = true;
            }
            if (this.cE > this.bL.i()) {
                this.cy = this.bL.i() / 2.0f - this.cE / 2.0f;
                this.ct = true;
            }
            if (this.cB > this.bL.j()) {
                this.cz = this.bL.j() / 2.0f - this.cB / 2.0f;
                this.ct = true;
            }
        }
        this.a(this.cy, this.cz);
    }

    public void a(float f2, float f3) {
        this.cy = f2;
        this.cz = f3;
        this.cu = (int)this.cy;
        this.cv = (int)this.cz;
        this.cw = (float)((int)(this.cy * this.cX)) / this.cX;
        this.cx = (float)((int)(this.cz * this.cX)) / this.cX;
        int n2 = 90;
        if (GlobalState.C()) {
            n2 = 210;
        }
        this.cN.a((int)(this.cy - (float)n2), (int)(this.cz - (float)n2), (int)(this.cy + this.cA + (float)n2), (int)(this.cz + this.cB + (float)n2));
        this.cO.a(this.cN);
        this.cQ.a((int)this.cy, (int)this.cz, (int)(this.cy + this.cA), (int)(this.cz + this.cB));
        int n3 = 300;
        this.cP.a((int)(this.cy - (float)n3), (int)(this.cz - (float)n3), (int)(this.cy + this.cA + (float)n3), (int)(this.cz + this.cB + (float)n3));
    }

    public void b(float f2, float f3) {
        this.a(f2 - this.cE / 2.0f, f3 - this.cB / 2.0f);
    }

    public static boolean d(Context context) {
        if (aU) {
            return false;
        }
        if (Build.MODEL.equals("GT-I9100") || Build.MODEL.equals("GT-I9300")) {
            try {
                WifiManager wifiManager = (WifiManager)context.c("wifi");
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                if (wifiInfo != null && "BlueStacks".equals(wifiInfo.getSSID())) {
                    return true;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return false;
    }

    public void R() {
        if (this.cX != 1.0f) {
            this.bO.a(this.cX, this.cX);
        }
    }

    public void S() {
        if (this.cX != 1.0f) {
            this.bO.a(1.0f / this.cX, 1.0f / this.cX);
        }
    }

    public static void a(String string, Exception exception) {
        GlobalState.e(string);
        exception.printStackTrace();
    }

    public static String a(String string, String string2) {
        if (ax && !string.contains("\u001b[0m")) {
            string = string2 + string + "\u001b[0m";
        }
        return string;
    }

    public static void a(String string) {
        GlobalState.e(GlobalState.a("--- ERROR: " + string, "\u001b[31m"));
    }

    public static void b(String string) {
        GlobalState.e(GlobalState.a(string, "\u001b[33m"));
    }

    public static void g(String string) {
        GlobalState.b(string);
        GlobalState.T();
    }

    public static void a(String string, Throwable throwable) {
        GlobalState.b(string);
        GlobalState.e("" + throwable.toString());
        GlobalState.e("cause:" + throwable.getCause());
        throwable.printStackTrace();
    }

    public static void c(String string) {
        if (aX) {
            Log.b("RustedWarfare", string);
            return;
        }
        Log.b("RustedWarfare", string);
    }

    public static void d(String string) {
        GlobalState.c(string);
    }

    public static void e(String string) {
        GlobalState.c(string);
    }

    public static void b(String string, String string2) {
        GlobalState.c(string + ":" + string2);
    }

    public static synchronized void f(String string) {
        GlobalState.c(string + " (at " + System.nanoTime() + ")");
    }

    public static void T() {
        StackTraceElement[] stackTraceElementArray;
        for (StackTraceElement stackTraceElement : stackTraceElementArray = new Throwable().getStackTrace()) {
            GlobalState.e(stackTraceElement.toString());
        }
    }

    public static String U() {
        StackTraceElement[] stackTraceElementArray;
        String string = "";
        for (StackTraceElement stackTraceElement : stackTraceElementArray = new Throwable().getStackTrace()) {
            string = string + stackTraceElement.toString() + "\n";
        }
        return string;
    }

    public static void isKeyJustPressed(String string) {
        GlobalState.b(string);
        GlobalState.T();
    }

    public static long V() {
        return System.currentTimeMillis();
    }

    public static final boolean a(long l2, long l3) {
        long l4 = GlobalState.V();
        if (l2 + l3 < l4) {
            return true;
        }
        return l4 < l2 - 1000L;
    }

    public float W() {
        float f2 = this.ci;
        if (this.bQ != null) {
            f2 *= this.bQ.renderDensity;
            f2 *= this.bQ.uiRenderScale;
            if (this.bQ.renderDoubleScale) {
                return f2 / 2.0f;
            }
        }
        return f2;
    }

    public int e(float f2) {
        int n2 = (int)(f2 * this.cj + 0.5f);
        return n2;
    }

    public int a(int n2) {
        return (int)((float)n2 * this.cj + 0.5f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void X() {
        if (this.dA != this.cj) {
            GlobalState.e("Density size changed now: " + this.cj + ", refreshing fonts");
            ArrayList arrayList = this.dC;
            synchronized (arrayList) {
                for (PaintRegistration m2 : (java.util.Collection<PaintRegistration>) (java.util.Collection) this.dC) {
                    m2.a();
                }
            }
            this.dA = this.cj;
            if (this.bO != null) {
                // empty if block
            }
        }
    }

    protected void Y() {
        for (PaintRegistration m2 : (java.util.Collection<PaintRegistration>) (java.util.Collection) this.dC) {
            this.bO.a(m2.b);
        }
        this.dB = true;
    }

    public void a(Paint paint) {
        this.a(paint, 16.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(Paint paint, float f2) {
        PaintRegistration m2 = new PaintRegistration(this);
        m2.a = f2;
        m2.b = paint;
        m2.a();
        ArrayList arrayList = this.dC;
        synchronized (arrayList) {
            this.dC.add(m2);
        }
        if (this.dB) {
            this.bO.a(m2.b);
        }
    }

    public void b(Paint paint, float f2) {
        float f3 = this.e(f2);
        if (paint.k() != f3) {
            paint.b(f3);
        }
    }

    public void isKeyDown(String string) {
        this.a(string, true);
    }

    public void h(String string) {
        this.a(string, true);
    }

    public void a(String string, boolean bl) {
        this.dI = string;
        if (this.dH != null) {
            this.dH.a(string, bl);
        }
    }

    public void Z() {
        this.dI = null;
    }

    public void i(String string) {
        this.a(string, 1);
    }

    public void a(String string, int n2) {
        if (aU) {
            GlobalState.e("alert:" + string);
        } else if (string == null) {
            GlobalState.isKeyJustPressed("Cannot show alert, no message text");
        } else {
            this.dE = string;
            this.mainThreadHandler.a(this.a);
        }
        if (this.dH != null) {
            this.dH.a(string, n2);
        }
    }

    public boolean aa() {
        if (this.dH != null) {
            return this.dH.c();
        }
        return false;
    }

    public void a(String string, LocalizedString bb2) {
        String string2 = null;
        if (bb2 != null) {
            string2 = bb2.getLocalizedText();
        }
        this.c(string, string2);
    }

    public void c(String string, String string2) {
        if (this.dH != null) {
            this.dH.a(string, string2);
        }
        if (aU) {
            if (this.dH == null) {
                GlobalState.b("showMessageBox: not showing due to non-android:" + string2);
            }
            return;
        }
        this.bp = true;
        this.dF = string;
        this.dG = string2;
        this.mainThreadHandler.a(this.b);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void ab() {
        Object object = this.dJ;
        synchronized (object) {
            if (this.dK != null) {
                this.c(this.dL, this.dK);
                this.dK = null;
                this.dL = null;
            }
        }
    }

    public void d(String string, String string2) {
        this.dL = string;
        this.dK = string2;
        if (aW) {
            this.ab();
            return;
        }
        GlobalState$3 l$3 = new GlobalState$3(this);
        l$3.start();
    }

    public boolean ac() {
        if (this.aq) {
            return false;
        }
        if (this.ao.k() == null) {
            return false;
        }
        return this.ao.k().b();
    }

    public void ad() {
        if (this.ao.k() == null) {
            return;
        }
        this.ao.k().c();
    }

    public int ae() {
        if (this.aq) {
            return 0;
        }
        return this.ao.k().a();
    }

    public float af() {
        return this.b(0);
    }

    public float ag() {
        return this.c(0);
    }

    public float b(int n2) {
        if (this.ao == null) {
            return 0.0f;
        }
        if (this.bQ.renderDoubleScale) {
            return this.ao.k().d()[n2] / 2.0f;
        }
        return this.ao.k().d()[n2];
    }

    public float c(int n2) {
        if (this.ao == null) {
            return 0.0f;
        }
        if (this.bQ.renderDoubleScale) {
            return this.ao.k().f()[n2] / 2.0f;
        }
        return this.ao.k().f()[n2];
    }

    public int d(int n2) {
        return this.ao.k().e()[n2];
    }

    public boolean e(int n2) {
        if (n2 != 1 && n2 != 2 && n2 != 3) {
            throw new RuntimeException("Unknown mouseButton:" + n2);
        }
        int n3 = this.f(n2);
        return n3 != -1;
    }

    public int f(int n2) {
        if (n2 == 0) {
            throw new RuntimeException("finding state of 0 doesn'ThreadPool make sense");
        }
        int[] nArray = this.ao.k().e();
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            if (nArray[i2] != n2) continue;
            return i2;
        }
        return -1;
    }

    public boolean isKeyJustPressed(int n2) {
        if (n2 >= this.c.length || n2 < 0) {
            return false;
        }
        if (this.c[n2] && this.d[n2]) {
            this.d[n2] = false;
            return true;
        }
        return false;
    }

    public boolean isKeyDown(int n2) {
        if (n2 >= this.c.length || n2 < 0) {
            return false;
        }
        return this.c[n2];
    }

    public boolean a(int n2, boolean bl) {
        boolean bl2 = true;
        boolean bl3 = true;
        int n3 = this.getModifierMask();
        if ((n2 & 2) != 0) {
            if ((n3 & 2) == 0) {
                bl2 = false;
            }
        } else if ((n3 & 2) != 0) {
            bl3 = false;
        }
        if ((n2 & 1) != 0) {
            if ((n3 & 1) == 0) {
                bl2 = false;
            }
        } else if ((n3 & 1) != 0) {
            bl3 = false;
        }
        if ((n2 & 4) != 0) {
            if ((n3 & 4) == 0) {
                bl2 = false;
            }
        } else if ((n3 & 4) != 0) {
            bl3 = false;
        }
        if (bl) {
            return bl2;
        }
        return bl2 && bl3;
    }

    public boolean i(int n2) {
        boolean bl = true;
        if (n2 == 59 || n2 == 60) {
            return true;
        }
        if (n2 == 113 || n2 == 114) {
            return true;
        }
        return n2 == 57 || n2 == 58;
    }

    public static String modifierMaskToString(int n2) {
        String string = "";
        if ((n2 & 2) != 0) {
            string = string + "shift+";
        }
        if ((n2 & 1) != 0) {
            string = string + "ctrl+";
        }
        if ((n2 & 4) != 0) {
            string = string + "alt+";
        }
        return string;
    }

    public int getModifierMask() {
        int n2 = 0;
        if (this.isKeyDown(59) || this.isKeyDown(60)) {
            n2 += 2;
        }
        if (this.isKeyDown(113) || this.isKeyDown(114)) {
            ++n2;
        }
        if (this.isKeyDown(57) || this.isKeyDown(58)) {
            n2 += 4;
        }
        return n2;
    }

    public boolean c(int n2, int n3) {
        boolean bl = false;
        boolean bl2 = false;
        if (n2 >= 0 && n2 < this.c.length) {
            bl = this.c[n2];  // 02b: var3 = this.c[var1]
        }
        if (n3 >= 0 && n3 < this.c.length) {
            bl2 = this.c[n3];
        }
        return bl || bl2;
    }

    public void b(int n2, boolean bl) {
        if (n2 >= 0 && n2 < this.c.length) {
            this.c[n2] = bl;  // 02b: setKeyState var2
            if (bl) {
                this.d[n2] = bl;
            }
        } else {
            GlobalState.e("setKeyState: Key out of range:" + n2);
        }
    }

    public void enqueueKeyEvent(int n2) {
        this.pendingCommandQueue.add(new ThreadPool(this, n2));
    }

    public int getAndroidApiLevel() {
        return this.e;
    }

    protected void processPendingCommands() {
        AssetLoader p2;
        this.e = 0;
        while ((p2 = (AssetLoader) this.pendingCommandQueue.poll()) != null) {
            VersionInfo p3;  // 02b l.java L1040: r var2
            if (p2 instanceof VersionInfo) {
                p3 = (VersionInfo) p2;
                if (p3.c >= this.c.length || p3.c < 0) {
                    GlobalState.b("updateKeyState", "keyCode (" + p3.c + ") is out of range");
                    continue;
                }
                this.c[p3.c] = !p3.d;
                this.d[p3.c] = !p3.d;
                continue;
            }
            if (!(p2 instanceof ThreadPool)) continue;
            ThreadPool threadPool = (ThreadPool) p2;  // 02b l.java L1047: t var3
            this.e += threadPool.c;
        }
    }

    public static String modifierMaskToString(String string) {
        int n2 = string.lastIndexOf("/");
        if (n2 == -1) {
            n2 = string.length();
        }
        return string.substring(0, n2);
    }

    public static String enqueueKeyEvent(String string) {
        int n2 = string.lastIndexOf("/");
        n2 = n2 == -1 ? 0 : ++n2;
        return string.substring(n2);
    }

    public static Integer extractMapLevel(String string) {
        String string2 = GlobalState.enqueueKeyEvent(string);
        GlobalState.e("getMapLevel for :" + string + " file:" + string2);
        Pattern pattern = Pattern.compile("^l(\\d*);.*");
        Matcher matcher = pattern.matcher(string2);
        if (matcher.matches()) {
            GlobalState.e("getMapLevel:" + string + ":" + Integer.parseInt(matcher.group(1)));
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }

    public static String findNextMapLevel(String string) {
        String string2;
        String[] stringArray;
        GlobalState l2 = GlobalState.B();
        Integer n2 = GlobalState.extractMapLevel(string);
        if (n2 == null) {
            return null;
        }
        int n3 = string.lastIndexOf("/");
        if (n3 == -1) {
            n3 = string.length();
        }
        if ((stringArray = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(string2 = string.substring(0, n3), true)) == null) {
            return null;
        }
        for (String string3 : stringArray) {
            Integer n4 = GlobalState.extractMapLevel(string3);
            if (n4 == null || n4 <= n2 || l2.ar && !com.corrodinggames.rts.appFramework.ContextMenuActivity.a(string3, string2 + "/" + string3)) continue;
            return string2 + "/" + string3;
        }
        return null;
    }

    public String getCurrentMapName() {
        return this.dl;
    }

    public String getDisplayMapName() {
        String string = this.dl;
        if ((this.dl == null || "".equals(this.dl)) && this.N()) {
            string = this.bX.prepareChatMessage();
        }
        return com.corrodinggames.rts.appFramework.ContextMenuActivity.e(com.corrodinggames.rts.appFramework.ContextMenuActivity.d(string));
    }

    public String getSanitizedMapName() {
        return com.corrodinggames.rts.appFramework.ContextMenuActivity.d(this.dl);
    }

    public GameModeEnum getMapType() {
        if (com.corrodinggames.rts.appFramework.ContextMenuActivity.g(this.dl)) {
            return com.corrodinggames.rts.gameFramework.network.GameModeEnum.b;
        }
        return com.corrodinggames.rts.gameFramework.network.GameModeEnum.a;
    }

    public static String a(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        String string = ((Object)stringWriter).toString();
        printWriter.close();
        return string;
    }

    public static String b(Throwable throwable) {
        Object object;
        String string = throwable.getMessage();
        if (string == null) {
            string = throwable.getClass().getName();
        } else {
            string = string.replace("java.lang.RuntimeException: ", "");
            string = string.replace("java.lang.RuntimeException: ", "");
        }
        Object object2 = throwable;
        while (object2 != null && (object = ((Throwable)object2).getCause()) != null && object != throwable && object != object2) {
            object2 = object;
        }
        object = null;
        if (object2 != null && object2 != throwable) {
            String string2;
            object = ((Throwable)object2).getMessage();
            if (object == null) {
                object = object2.getClass().getName();
            }
            if (!((String)object).equals(string2 = string)) {
                string = string + " caused by (" + (String)object + ")";
            }
        }
        return string;
    }

    public static File getCrashLogFile() {
        com.corrodinggames.rts.gameFramework.filesystem.FileLoader.d();
        String string = "/SD/rustedWarfare/crashes.txt";
        if (GlobalState.at()) {
            string = "/SD/rustedWarfare/crashes.txt";
        }
        String string2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(string);
        File file = new File(string2);
        return file;
    }

    public static void e(String string, String string2) {
        File file = GlobalState.getCrashLogFile();
        try {
            OutputStream outputStream = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(file, true);
            PrintWriter printWriter = new PrintWriter(outputStream);
            String string3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            printWriter.write("\r\n" + string + " (at " + string3 + " - " + "1.15" + "" + ")\n");
            printWriter.write(string2 + "\r\n");
            printWriter.close();
        }
        catch (Throwable throwable) {
            GlobalState.e("Exception in writeCrashToFile");
            throwable.printStackTrace();
        }
    }

    public static void setupANRWatchdog() {
        if (!at) {
            return;
        }
        if (aU) {
            return;
        }
        if (dS != null) {
            GlobalState.b("setupANRWatchDog: activeANRWatchDog!=null");
            return;
        }
        dS = new com.corrodinggames.rts.gameFramework.utility.ANRWatchdog(4000);
        dS.a(new GlobalState$4());
        dS.start();
        GlobalState.b("setupANRWatchDog: running");
    }

    public static void initIntegrityAndCrashHandler() {
        if (dP == null && GlobalState.av()) {
            dP = new byte[2500000];
            dP[0] = 2;
            dP[dP.length - 1] = 5;
        }
        if (aA) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
            if (!(uncaughtExceptionHandler instanceof CrashHandler)) {
                Thread.currentThread().setUncaughtExceptionHandler(new CrashHandler(uncaughtExceptionHandler));
            }
        } else {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (!(uncaughtExceptionHandler instanceof CrashHandler)) {
                Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(uncaughtExceptionHandler));
            }
        }
    }

    public static strictfp void aC() {  // 02b l.java: 内存报告 (v19.133f2 补缺)
        System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
        long l2 = Runtime.getRuntime().maxMemory();
        System.out.println("Maximum memory (bytes): " + (l2 == Long.MAX_VALUE ? "no limit" : Long.valueOf(l2)));
        System.out.println("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
    }

    public static strictfp void n(String string) {  // 02b l.java L1257: reportProblem (v19.133f2 补缺)
        GlobalState l2 = GlobalState.B();
        if (l2 != null) {
            ++dU;
            if (dU < 1000) {
                b("reportProblem: " + string);
            }
            if (dU < 10) {
                l2.a(string, 1);
            }
        }
    }

    public abstract int z();

    public boolean ar() {
        return true;
    }

    public boolean as() {
        return true;
    }

    public static void reportProblem(String string) {
        GlobalState l2 = GlobalState.B();
        if (l2 != null) {
            if (++dU < 1000) {
                GlobalState.b("reportProblem: " + string);
            }
            if (dU < 10) {
                l2.a(string, 1);
            }
        }
    }

    public static boolean isNotDedicatedServer() {
        return !aU;
    }

    public static boolean au() {
        return !aW || aZ;  // 02b L1277: !aW || aZ
    }

    public static boolean isDesktopPlatform() {
        return aW && !aZ;
    }

    public int ai() {
        return this.e;
    }

    public static boolean at() {
        return !aU;
    }

    public static boolean av() {
        return aW && !aZ;
    }

    public static boolean aw() {
        return aW && !aZ;
    }

    public static boolean aU() {
        return aU && !aW;
    }

    public boolean isNetworkedOrReplay() {
        return this.bX.B || this.cb.j();
    }

    public void a(UnitInstance am2, float f2) {
        this.bW.a((int)am2.eo, (int)am2.ep, f2, am2);
        this.bS.i.c(am2);
    }

    public static boolean isTeamShadersSupported() {
        GlobalState l2 = GlobalState.B();
        if (l2 != null && l2.bQ.teamShaders && (l2.bQ.newRender || !GlobalState.at())) {
            return true;
        }
        return aN;
    }

    public static boolean isShaderEffectsSupported() {
        GlobalState l2 = GlobalState.B();
        if (l2 != null && l2.bQ.shaderEffects && (l2.bQ.newRender || !GlobalState.at())) {
            return true;
        }
        return aM;
    }

    public static boolean aB() {
        GlobalState l2 = GlobalState.B();
        if (l2 != null && l2.bQ.shaderEffects && (l2.bQ.newRender || !GlobalState.at())) {
            return true;
        }
        return aM;
    }

    public abstract int b();

    public static void printMemoryInfo() {
        System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
        long l2 = Runtime.getRuntime().maxMemory();
        System.out.println("Maximum memory (bytes): " + (l2 == Long.MAX_VALUE ? "no limit" : Long.valueOf(l2)));
        System.out.println("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
    }

    public Context getContext() {
        return this.am;
    }

    public static void f(String string, String string2) {
        GlobalState l2 = GlobalState.B();
        if (l2 == null) {
            return;
        }
        String string3 = string2;
        if (l2.bS != null && l2.bS.h != null) {
            l2.bS.h.a(string, string3);
        } else {
            GlobalState.isKeyJustPressed("addMessage: interfaceEngine/messageInterface==null");
        }
    }

    public static void a(ResourceDomainEnum u2, Throwable throwable) {
        integrityTable2 = null;
        GlobalState.e("reportCaughtOutOfMemory:" + (Object)((Object)dW));
        if (dW != null) {
            return;
        }
        dW = u2;
        if (throwable != null) {
            GlobalState.c(throwable);
        }
        GlobalState.printMemoryInfo();
    }

    public static void c(Throwable throwable) {
        try {
            throwable.printStackTrace();
        }
        catch (Throwable throwable2) {
            GlobalState.e("Failed to print stacktrace");
        }
    }

    public void checkMemoryAndShowWarnings() {
        String string;
        if (dX && !dY) {
            dY = true;
            string = "Warning game has less than 5mb of free space remaining. A larger battle might cause ByteIndexedMap crash. ";
            int n2 = this.bZ.h();
            if (n2 > 1) {
                string = string + "This is often caused by large mods, you currently have: " + n2 + " mods loaded. ";
            }
            this.c("Warning: Low memory detected", string);
        }
        if (!dV && dW != null) {
            GlobalState.e("Showing out of memory message");
            dV = true;
            string = "";
            String string2 = "trying to load data";
            if (dW == ResourceDomainEnum.a) {
                string2 = "trying to load game textures";
            } else if (dW == ResourceDomainEnum.b) {
                string2 = "trying to create ByteIndexedMap texture";
            } else if (dW == ResourceDomainEnum.c) {
                string2 = "trying to colour new texture";
            } else if (dW == ResourceDomainEnum.d) {
                string2 = "trying to create texture buffer for on-screen fog fading";
            } else if (dW == ResourceDomainEnum.e) {
                string2 = "trying to create game fonts";
            } else if (dW == ResourceDomainEnum.f) {
                string2 = "trying to load game sounds";
            } else if (dW == ResourceDomainEnum.g) {
                string2 = "trying to load UI textures";
            }
            string = "The game ran out of memory " + string2 + ". ";
            int n3 = this.bZ.h();
            if (n3 > 1) {
                string = string + "This is often caused by large mods, you currently have: " + n3 + " mods. ";
            }
            if (GlobalState.av() && !com.corrodinggames.rts.game.GameEngine.b) {
                string = string + "You are also using the 32 bit version, switching to the 64 bit version might help. ";
            }
            this.c("Warning: Out Of Memory", string);
        }
    }

    public void testMemoryIntegrity() {
        try {
            byte[] byArray = new byte[5000000];
            byArray[0] = this.integrityToken1;
            this.integrityToken2 = byArray[1];
            byArray = null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
            GlobalState.e("Low memory detected");
            outOfMemoryError.printStackTrace();
            dX = true;
        }
    }

    public void a(Runnable runnable) {
        this.ec.a(runnable);
    }

    public final boolean a(float f2, float f3, float f4) {
        return this.cM.a < f2 + f4 && f2 - f4 < this.cM.c && this.cM.b < f3 + f4 && f3 - f4 < this.cM.d;
    }

    public abstract boolean c();

    public abstract boolean d();

    public static boolean hasPermission(String string) {
        if (aQ == null) {
            return false;
        }
        return aQ.contains(string);
    }

    public static void logAndNetworkSend(String string) {
        GlobalState l2 = GlobalState.B();
        NetEngine ad2 = l2.bX;
        String string2 = "" + string;
        GlobalState.b(string2);
        GlobalState.T();
        if (++ej < 10) {
            String string3 = string2;
            if (ad2 != null) {
                ad2.m(string3);
            }
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.audio.DataFieldProvider f2, com.corrodinggames.rts.gameFramework.audio.DataFieldInt c2) {  // 02b g.c = DataFieldInt
        this.cg = new com.corrodinggames.rts.gameFramework.audio.DataFieldCollector(f2, c2);
        this.cg.a();
    }
    static {
        // 02b l.java L67-107 静态字段声明处初始化对照 (幻觉名赋值删除)
        aw = false;
        useGLES30 = false;
        aM = false;
        aN = false;
        aQ = null;
        aU = false;
        aV = false;
        aW = false;
        aX = false;
        aY = false;
        aZ = false;
        useNewAudio = true;
        useOpenAL = true;
        useLWJGLAudio = false;

        nativeLibraryLoaded = false;
        dz = Build.VERSION.RELEASE;  // 02b l.java L231: dz = VERSION.RELEASE
        integrityTable2 = new byte[1000];
        integrityTable3 = new byte[1000];
        antiCheatEnabled = false;
        dW = null;
    }

    public String ak() {  // 02b l.ak() L1118-1120: return this.dl
        return this.dl;
    }


    public static void ap() {  // 02b l.ap() L1210-1223: ANR WatchDog 设置 (utility.d/e 链留待战役)
        // 02b 体: if(at && !aU) { dS = new utility.d(4000); dS.a(new l$4()); dS.start(); }
    }


    public static boolean ax() {  // 02b l.ax() L1288-1290: return aU && !aW
        return aU && !aW;
    }


    public void aF() {  // 02b l.aF() L1412-1425: 内存预分配
        try {
            byte[] bArr = new byte[5000000];
            bArr[0] = this.dZ;
            this.ea = bArr[1];
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
            e("Low memory detected");
            outOfMemoryError.printStackTrace();
            dX = true;
        }
    }


    public void aE() {  // 02b l.aE() L1363-1374: 低空间警告 (简化)
        String string;
        if (dX && !dY) {
            dY = true;
            string = "Warning game has less than 5mb of free space remaining. A larger battle might cause a crash. ";
            int n2 = this.bZ.h();
            if (n2 > 1) {
                string = string + "This is often caused by large mods, you currently have: " + n2 + " mods loaded. ";
            }
            this.c("Warning: Low memory detected", string);
        }
    }


    public boolean ay() {  // 02b l.java L1292-1294
        return this.bX.B || this.cb.j();
    }


    public boolean h(int n2) {  // 02b l.java L921-923: 按键按下查询
        return n2 < this.c.length && n2 >= 0 ? this.c[n2] : false;
    }


    public void k(int var1) {  // 02b l.k(int) 简化 TODO (v19.133f)
    }

}
