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
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.a.e;
import com.corrodinggames.rts.gameFramework.aa;
import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.ba;
import com.corrodinggames.rts.gameFramework.bg;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.o;
import com.corrodinggames.rts.gameFramework.g.c;
import com.corrodinggames.rts.gameFramework.g.f;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l$1;
import com.corrodinggames.rts.gameFramework.l$2;
import com.corrodinggames.rts.gameFramework.l$3;
import com.corrodinggames.rts.gameFramework.l$4;
import com.corrodinggames.rts.gameFramework.m;
import com.corrodinggames.rts.gameFramework.n;
import com.corrodinggames.rts.gameFramework.p;
import com.corrodinggames.rts.gameFramework.q;
import com.corrodinggames.rts.gameFramework.r;
import com.corrodinggames.rts.gameFramework.s;
import com.corrodinggames.rts.gameFramework.t;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.utility.d;
import com.corrodinggames.rts.gameFramework.utility.i;
import com.corrodinggames.rts.gameFramework.v;
import com.corrodinggames.rts.gameFramework.y;
import com.corrodinggames.rts.gameFramework.z;
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
public strictfp abstract class l {
    public final Object aj = new Object();
    public final Object ak = new Object();
    protected static l al = null;
    public Context am = null;
    public Context an;
    public com.corrodinggames.rts.appFramework.f ao;
    public com.corrodinggames.rts.appFramework.f ap;
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
    public static boolean aL;
    public static boolean aM;
    public static boolean aN;
    public static boolean aO;
    public static boolean aP;
    public static String aQ;
    public static boolean aR;
    public boolean aS;
    public static boolean aT;
    public static boolean aU;
    public static boolean aV;
    public static boolean aW;
    public static boolean aX;
    public static boolean aY;
    public static boolean aZ;
    public static String ba;
    public static boolean bb;
    public static boolean bc;
    public static boolean bd;
    public static boolean be;
    public static boolean bf;
    public static Class bg;
    public static com.corrodinggames.rts.gameFramework.m.y bh;
    public boolean bi = false;
    public boolean bj;
    public boolean bk = false;
    public boolean bl = false;
    public boolean bm = false;
    public boolean bn = false;
    public boolean bo = false;
    public boolean bp;
    public boolean bq = false;
    public boolean br = false;
    public com.corrodinggames.rts.game.n bs;
    public float bt = 1.0f;
    public float bu = -1.0f;
    public boolean bv;
    public boolean bw;
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
    public i bK;
    public b bL;
    public e bM;
    public am bN;
    public com.corrodinggames.rts.gameFramework.m.y bO;
    public com.corrodinggames.rts.gameFramework.a bP;
    public SettingsEngine bQ;
    public com.corrodinggames.rts.gameFramework.d.c bR;
    public g bS;
    public ac bT;
    public com.corrodinggames.rts.gameFramework.k.l bU;
    public aa bV;
    public o bW;
    public ad bX;
    public bg bY;
    public com.corrodinggames.rts.gameFramework.i.a bZ;
    public y ca;
    public ba cb;
    public com.corrodinggames.rts.game.units.f.c cc;
    public br cd;
    public com.corrodinggames.rts.gameFramework.n.f ce;
    public com.corrodinggames.rts.gameFramework.c cf;
    public com.corrodinggames.rts.gameFramework.g.a cg = new com.corrodinggames.rts.gameFramework.g.a();
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
    public boolean cW = false;
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
    protected z dk = null;
    public String dl;
    public k dm;
    public Paint dn;
    public Paint do;
    public Paint dp;
    public boolean dq = false;
    public boolean dr = false;
    public float ds = 0.0f;
    public boolean dt = false;
    public boolean du = false;
    public boolean dv = false;
    public int dw;
    public float dx = 0.0f;
    public static com.corrodinggames.rts.gameFramework.o dy;
    public static String dz;
    float dA;
    boolean dB = false;
    ArrayList dC = new ArrayList();
    final Handler dD = new Handler(Looper.b());
    public String dE;
    private Runnable a = new l$1(this);
    public String dF;
    public String dG;
    private Runnable b = new l$2(this);
    public n dH = null;
    transient String dI = null;
    Object dJ = new Object();
    String dK;
    String dL;
    public boolean[] dM = new boolean[10];
    protected ConcurrentLinkedQueue dN = new ConcurrentLinkedQueue();
    private boolean[] c = new boolean[KeyEvent.a() + 1];
    private boolean[] d = new boolean[KeyEvent.a() + 1];
    private int e;
    public static boolean dO;
    static byte[] dP;
    static byte[] dQ;
    static byte[] dR;
    static d dS;
    static boolean dT;
    static int dU;
    static boolean dV;
    static u dW;
    static boolean dX;
    static boolean dY;
    public byte dZ = (byte)42;
    public byte ea = (byte)42;
    public final q eb = new q();
    public final q ec = new q();
    public final q ed = new q();
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

    public static final l B() {
        return al;
    }

    public static final boolean C() {
        return be;
    }

    public static final boolean D() {
        return bf;
    }

    public void c(Context context) {
        com.corrodinggames.rts.appFramework.c.a(context);
        this.am = context;
    }

    public static synchronized l a(Context context, n n2) {
        if (al != null) {
            if (n2 != null) {
                l.al.dH = n2;
            }
            al.c(context);
            return al;
        }
        al = dy.a(context);
        l.e("Created new gameEngine of:" + al.getClass().getName());
        if (n2 != null) {
            l.al.dH = n2;
        }
        al.a(context);
        return al;
    }

    public l(Context context) {
        Log.d("RustedWarfare", "GameEngine:GameEngine()");
        if (al != null) {
            throw new RuntimeException("gameEngine already created");
        }
        this.c(context);
        al = this;
    }

    protected void finalize() {
        Log.d("RustedWarfare", "GameEngine:finalize()");
        super.finalize();
    }

    public boolean E() {
        return true;
    }

    public abstract void a(Context var1);

    public void F() {
    }

    public abstract boolean a();

    public abstract boolean a(boolean var1);

    public abstract void a(Activity var1, com.corrodinggames.rts.appFramework.f var2, boolean var3);

    public abstract void b(int var1, int var2);

    public abstract int c(boolean var1);

    public abstract boolean n();

    public abstract boolean p();

    public abstract String l();

    public abstract String m();

    public abstract String r();

    public abstract String t();

    public abstract String u();

    public abstract void s();

    public abstract String v();

    public String G() {
        if (l.av()) {
            return "PC";
        }
        if (aZ) {
            String string = com.corrodinggames.rts.gameFramework.l.a.a();
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

    public abstract void a(boolean var1, boolean var2, s var3);

    public abstract void a(boolean var1, s var2);

    public abstract void e();

    public abstract void g();

    public abstract void x();

    public abstract void a(float var1, int var2);

    public boolean I() {
        return this.bH;
    }

    public synchronized void J() {
        l.e("--- setRunning ---");
        if (!l.av() && !aZ) {
            this.bN.h();
        }
        if (!aW && !bb && this.dk == null) {
            this.dk = new z();
            this.dk.a(true);
            this.dk.start();
        }
    }

    public synchronized void K() {
        l.e("--- setStoppedIfNotInGameThread ---");
        if (Thread.currentThread() != this.dk) {
            this.L();
        }
    }

    public synchronized void L() {
        l.e("--- setStopped ---");
        if (this.dk == null) {
            Log.d("RustedWarfare", "gameThread already null");
            return;
        }
        if (!l.av()) {
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
            l.g("currentThread is game thread");
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
        if (l.C()) {
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
        l.e(string);
        exception.printStackTrace();
    }

    public static String a(String string, String string2) {
        if (ax && !string.contains("\u001b[0m")) {
            string = string2 + string + "\u001b[0m";
        }
        return string;
    }

    public static void a(String string) {
        l.e(l.a("--- ERROR: " + string, "\u001b[31m"));
    }

    public static void b(String string) {
        l.e(l.a(string, "\u001b[33m"));
    }

    public static void a(String string, Throwable throwable) {
        l.b(string);
        l.e("" + throwable.toString());
        l.e("cause:" + throwable.getCause());
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
        l.c(string);
    }

    public static void e(String string) {
        l.c(string);
    }

    public static void b(String string, String string2) {
        l.c(string + ":" + string2);
    }

    public static synchronized void f(String string) {
        l.c(string + " (at " + System.nanoTime() + ")");
    }

    public static void T() {
        StackTraceElement[] stackTraceElementArray;
        for (StackTraceElement stackTraceElement : stackTraceElementArray = new Throwable().getStackTrace()) {
            l.e(stackTraceElement.toString());
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

    public static void g(String string) {
        l.b(string);
        l.T();
    }

    public static long V() {
        return System.currentTimeMillis();
    }

    public static final boolean a(long l2, long l3) {
        long l4 = l.V();
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
            l.e("Density size changed now: " + this.cj + ", refreshing fonts");
            ArrayList arrayList = this.dC;
            synchronized (arrayList) {
                for (m m2 : this.dC) {
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
        for (m m2 : this.dC) {
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
        m m2 = new m(this);
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
            l.e("alert:" + string);
        } else if (string == null) {
            l.g("Cannot show alert, no message text");
        } else {
            this.dE = string;
            this.dD.a(this.a);
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

    public void a(String string, bb bb2) {
        String string2 = null;
        if (bb2 != null) {
            string2 = bb2.b();
        }
        this.c(string, string2);
    }

    public void c(String string, String string2) {
        if (this.dH != null) {
            this.dH.a(string, string2);
        }
        if (aU) {
            if (this.dH == null) {
                l.b("showMessageBox: not showing due to non-android:" + string2);
            }
            return;
        }
        this.bp = true;
        this.dF = string;
        this.dG = string2;
        this.dD.a(this.b);
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
        l$3 l$3 = new l$3(this);
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
            throw new RuntimeException("finding state of 0 doesn't make sense");
        }
        int[] nArray = this.ao.k().e();
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            if (nArray[i2] != n2) continue;
            return i2;
        }
        return -1;
    }

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

    public boolean h(int n2) {
        if (n2 >= this.c.length || n2 < 0) {
            return false;
        }
        return this.c[n2];
    }

    public boolean a(int n2, boolean bl) {
        boolean bl2 = true;
        boolean bl3 = true;
        int n3 = this.ah();
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

    public static String j(int n2) {
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

    public int ah() {
        int n2 = 0;
        if (this.h(59) || this.h(60)) {
            n2 += 2;
        }
        if (this.h(113) || this.h(114)) {
            ++n2;
        }
        if (this.h(57) || this.h(58)) {
            n2 += 4;
        }
        return n2;
    }

    public boolean c(int n2, int n3) {
        boolean bl = false;
        boolean bl2 = false;
        if (n2 >= 0 && n2 < this.c.length) {
            bl = this.c[n2];
        }
        if (n3 >= 0 && n3 < this.c.length) {
            bl2 = this.c[n3];
        }
        return bl || bl2;
    }

    public void b(int n2, boolean bl) {
        if (n2 >= 0 && n2 < this.c.length) {
            this.c[n2] = bl;
            if (bl) {
                this.d[n2] = bl;
            }
        } else {
            l.e("setKeyState: Key out of range:" + n2);
        }
    }

    public void k(int n2) {
        this.dN.add(new t(this, n2));
    }

    public int ai() {
        return this.e;
    }

    protected void aj() {
        p p2;
        this.e = 0;
        while ((p2 = (p)this.dN.poll()) != null) {
            p p3;
            if (p2 instanceof r) {
                p3 = (r)p2;
                if (p3.c >= this.c.length || p3.c < 0) {
                    l.b("updateKeyState", "keyCode (" + p3.c + ") is out of range");
                    continue;
                }
                this.c[p3.c] = !p3.d;
                this.d[p3.c] = !p3.d;
                continue;
            }
            if (!(p2 instanceof t)) continue;
            p3 = (t)p2;
            this.e += ((t)p3).c;
        }
    }

    public static String j(String string) {
        int n2 = string.lastIndexOf("/");
        if (n2 == -1) {
            n2 = string.length();
        }
        return string.substring(0, n2);
    }

    public static String k(String string) {
        int n2 = string.lastIndexOf("/");
        n2 = n2 == -1 ? 0 : ++n2;
        return string.substring(n2);
    }

    public static Integer l(String string) {
        String string2 = l.k(string);
        l.e("getMapLevel for :" + string + " file:" + string2);
        Pattern pattern = Pattern.compile("^l(\\d*);.*");
        Matcher matcher = pattern.matcher(string2);
        if (matcher.matches()) {
            l.e("getMapLevel:" + string + ":" + Integer.parseInt(matcher.group(1)));
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }

    public static String m(String string) {
        String string2;
        String[] stringArray;
        l l2 = l.B();
        Integer n2 = l.l(string);
        if (n2 == null) {
            return null;
        }
        int n3 = string.lastIndexOf("/");
        if (n3 == -1) {
            n3 = string.length();
        }
        if ((stringArray = com.corrodinggames.rts.gameFramework.e.a.a(string2 = string.substring(0, n3), true)) == null) {
            return null;
        }
        for (String string3 : stringArray) {
            Integer n4 = l.l(string3);
            if (n4 == null || n4 <= n2 || l2.ar && !com.corrodinggames.rts.appFramework.i.a(string3, string2 + "/" + string3)) continue;
            return string2 + "/" + string3;
        }
        return null;
    }

    public String ak() {
        return this.dl;
    }

    public String al() {
        String string = this.dl;
        if ((this.dl == null || "".equals(this.dl)) && this.N()) {
            string = this.bX.l();
        }
        return com.corrodinggames.rts.appFramework.i.e(com.corrodinggames.rts.appFramework.i.d(string));
    }

    public String am() {
        return com.corrodinggames.rts.appFramework.i.d(this.dl);
    }

    public ai an() {
        if (com.corrodinggames.rts.appFramework.i.g(this.dl)) {
            return ai.b;
        }
        return ai.a;
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

    public static File ao() {
        com.corrodinggames.rts.gameFramework.e.a.d();
        String string = "/SD/rustedWarfare/crashes.txt";
        if (l.at()) {
            string = "/SD/rustedWarfare/crashes.txt";
        }
        String string2 = com.corrodinggames.rts.gameFramework.e.a.e(string);
        File file = new File(string2);
        return file;
    }

    public static void e(String string, String string2) {
        File file = l.ao();
        try {
            OutputStream outputStream = com.corrodinggames.rts.gameFramework.e.a.a(file, true);
            PrintWriter printWriter = new PrintWriter(outputStream);
            String string3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            printWriter.write("\r\n" + string + " (at " + string3 + " - " + "1.15" + "" + ")\n");
            printWriter.write(string2 + "\r\n");
            printWriter.close();
        }
        catch (Throwable throwable) {
            l.e("Exception in writeCrashToFile");
            throwable.printStackTrace();
        }
    }

    public static void ap() {
        if (!at) {
            return;
        }
        if (aU) {
            return;
        }
        if (dS != null) {
            l.b("setupANRWatchDog: activeANRWatchDog!=null");
            return;
        }
        dS = new d(4000);
        dS.a(new l$4());
        dS.start();
        l.b("setupANRWatchDog: running");
    }

    public static void aq() {
        if (dP == null && l.av()) {
            dP = new byte[2500000];
            l.dP[0] = 2;
            l.dP[l.dP.length - 1] = 5;
        }
        if (aA) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
            if (!(uncaughtExceptionHandler instanceof com.corrodinggames.rts.gameFramework.i)) {
                Thread.currentThread().setUncaughtExceptionHandler(new com.corrodinggames.rts.gameFramework.i(uncaughtExceptionHandler));
            }
        } else {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (!(uncaughtExceptionHandler instanceof com.corrodinggames.rts.gameFramework.i)) {
                Thread.setDefaultUncaughtExceptionHandler(new com.corrodinggames.rts.gameFramework.i(uncaughtExceptionHandler));
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

    public static void n(String string) {
        l l2 = l.B();
        if (l2 != null) {
            if (++dU < 1000) {
                l.b("reportProblem: " + string);
            }
            if (dU < 10) {
                l2.a(string, 1);
            }
        }
    }

    public static boolean at() {
        return !aU;
    }

    public static boolean au() {
        return !aW || aZ;
    }

    public static boolean av() {
        return aW && !aZ;
    }

    public static boolean aw() {
        return aW && !aZ;
    }

    public static boolean ax() {
        return aU && !aW;
    }

    public boolean ay() {
        return this.bX.B || this.cb.j();
    }

    public void a(com.corrodinggames.rts.game.units.am am2, float f2) {
        this.bW.a((int)am2.eo, (int)am2.ep, f2, am2);
        this.bS.i.c(am2);
    }

    public static boolean az() {
        l l2 = l.B();
        if (l2 != null && l2.bQ.teamShaders && (l2.bQ.newRender || !l.at())) {
            return true;
        }
        return aN;
    }

    public static boolean aA() {
        l l2 = l.B();
        if (l2 != null && l2.bQ.shaderEffects && (l2.bQ.newRender || !l.at())) {
            return true;
        }
        return aM;
    }

    public static boolean aB() {
        l l2 = l.B();
        if (l2 != null && l2.bQ.shaderEffects && (l2.bQ.newRender || !l.at())) {
            return true;
        }
        return aM;
    }

    public abstract int b();

    public static void aC() {
        System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
        long l2 = Runtime.getRuntime().maxMemory();
        System.out.println("Maximum memory (bytes): " + (l2 == Long.MAX_VALUE ? "no limit" : Long.valueOf(l2)));
        System.out.println("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
    }

    public Context aD() {
        return this.am;
    }

    public static void f(String string, String string2) {
        l l2 = l.B();
        if (l2 == null) {
            return;
        }
        String string3 = string2;
        if (l2.bS != null && l2.bS.h != null) {
            l2.bS.h.a(string, string3);
        } else {
            l.g("addMessage: interfaceEngine/messageInterface==null");
        }
    }

    public static void a(u u2, Throwable throwable) {
        dQ = null;
        l.e("reportCaughtOutOfMemory:" + (Object)((Object)dW));
        if (dW != null) {
            return;
        }
        dW = u2;
        if (throwable != null) {
            l.c(throwable);
        }
        l.aC();
    }

    public static void c(Throwable throwable) {
        try {
            throwable.printStackTrace();
        }
        catch (Throwable throwable2) {
            l.e("Failed to print stacktrace");
        }
    }

    public void aE() {
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
        if (!dV && dW != null) {
            l.e("Showing out of memory message");
            dV = true;
            string = "";
            String string2 = "trying to load data";
            if (dW == u.a) {
                string2 = "trying to load game textures";
            } else if (dW == u.b) {
                string2 = "trying to create a texture";
            } else if (dW == u.c) {
                string2 = "trying to colour new texture";
            } else if (dW == u.d) {
                string2 = "trying to create texture buffer for on-screen fog fading";
            } else if (dW == u.e) {
                string2 = "trying to create game fonts";
            } else if (dW == u.f) {
                string2 = "trying to load game sounds";
            } else if (dW == u.g) {
                string2 = "trying to load UI textures";
            }
            string = "The game ran out of memory " + string2 + ". ";
            int n3 = this.bZ.h();
            if (n3 > 1) {
                string = string + "This is often caused by large mods, you currently have: " + n3 + " mods. ";
            }
            if (l.av() && !com.corrodinggames.rts.game.i.b) {
                string = string + "You are also using the 32 bit version, switching to the 64 bit version might help. ";
            }
            this.c("Warning: Out Of Memory", string);
        }
    }

    public void aF() {
        try {
            byte[] byArray = new byte[5000000];
            byArray[0] = this.dZ;
            this.ea = byArray[1];
            byArray = null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
            l.e("Low memory detected");
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

    public static boolean o(String string) {
        if (aQ == null) {
            return false;
        }
        return aQ.contains(string);
    }

    public static void p(String string) {
        l l2 = l.B();
        ad ad2 = l2.bX;
        String string2 = "" + string;
        l.b(string2);
        l.T();
        if (++ej < 10) {
            String string3 = string2;
            if (ad2 != null) {
                ad2.m(string3);
            }
        }
    }

    public void a(f f2, c c2) {
        this.cg = new com.corrodinggames.rts.gameFramework.g.a(f2, c2);
        this.cg.a();
    }

    static {
        aw = false;
        ax = false;
        aH = false;
        aI = false;
        aK = null;
        aL = false;
        aM = false;
        aN = false;
        aO = false;
        aP = false;
        aQ = null;
        aT = false;
        aU = false;
        aV = false;
        aW = false;
        aX = false;
        aY = false;
        aZ = false;
        ba = null;
        bb = false;
        bc = true;
        bd = true;
        be = false;
        bf = false;
        dy = new v();
        dz = Build.VERSION.RELEASE;
        dO = false;
        dQ = new byte[1000];
        dR = new byte[1000];
        dT = false;
        dU = 0;
        dW = null;
    }
}
