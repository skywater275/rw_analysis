/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.res.AssetFileDescriptor
 *  android.graphics.PorterDuff$Mode
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.game.b;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import com.corrodinggames.rts.R$drawable;
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
import com.corrodinggames.rts.game.l;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.o;
import com.corrodinggames.rts.gameFramework.w;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public strictfp final class b {
    static final boolean a = false;
    static final boolean b = false;
    static final boolean c = false;
    public static boolean d = false;
    static ReentrantLock e = new ReentrantLock();
    static boolean f;
    static Paint g;
    static Paint h;
    static Paint i;
    static Paint j;
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
    public static boolean H;
    public static boolean I;
    public static boolean J;
    public static com.corrodinggames.rts.gameFramework.m.e K;
    public static com.corrodinggames.rts.gameFramework.m.y L;
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
    float aa = 0.0f;
    Paint ab;
    Paint ac;
    Paint ad;
    Paint ae;
    Paint af;
    Paint ag;
    HashMap ah;
    float ai;
    float aj = 1.0f;
    int ak = 0;
    public static c al;
    Paint am = new Paint();
    Rect an = new Rect();
    Rect ao = new Rect();
    long ap;
    float aq;
    float ar;

    public static void a() {
        if (f) {
            return;
        }
        e.lock();
    }

    public static void b() {
        if (f) {
            return;
        }
        e.unlock();
    }

    public static void c() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        g.a(150, 255, 255, 255);
        g.a(Paint$Style.b);
        g.a(1.0f);
        l2.a(g, 16.0f);
        h.a(150, 255, 0, 0);
        h.a(Paint$Style.b);
        h.a(1.0f);
        i.a(150, 0, 255, 0);
        i.a(Paint$Style.b);
        i.a(1.0f);
        j.a(150, 255, 0, 0);
        long l3 = br.a();
        com.corrodinggames.rts.gameFramework.m.e e2 = l2.bO.a(R$drawable.fog_smooth);
        int n2 = 20;
        int n3 = 20;
        int n4 = 1;
        K = l2.bO.b((n2 + 2) * 16 + 1, (n3 + 2) * 16 + 1, true);
        com.corrodinggames.rts.game.b.b.K.m = true;
        K.b(true);
        L = l2.bO.b(K);
        com.corrodinggames.rts.gameFramework.m.e e3 = l2.bO.b(n2 + n4, n3 + n4, true);
        com.corrodinggames.rts.gameFramework.m.y y2 = l2.bO.b(e3);
        int n5 = 1;
        int n6 = 2;
        int n7 = 4;
        int n8 = 8;
        int n9 = 16;
        int n10 = 32;
        int n11 = 64;
        int n12 = -128;
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n5), 2, 5, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n6), 0, 5, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n7), 0, 3, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n8), 2, 3, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n9, new int[]{n5, n6}), 1, 0, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n10, new int[]{n6, n7}), 2, 1, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n11, new int[]{n8, n7}), 1, 2, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n12, new int[]{n5, n8}), 0, 1, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n9 + n10, new int[]{n6, n5, n7}), 2, 0, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n10 + n11, new int[]{n7, n8, n6}), 2, 2, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n11 + n12, new int[]{n8, n7, n5}), 0, 2, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n12 + n9, new int[]{n5, n8, n6}), 0, 0, true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n5 + n6), com.corrodinggames.rts.game.b.b.a(new int[]{2, 5, 0, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n6 + n7), com.corrodinggames.rts.game.b.b.a(new int[]{0, 5, 0, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n7 + n8), com.corrodinggames.rts.game.b.b.a(new int[]{0, 3, 2, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n8 + n5), com.corrodinggames.rts.game.b.b.a(new int[]{2, 3, 2, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n9 + n10 + n11, new int[]{n5, n6, n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{2, 0, 2, 2}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n10 + n11 + n12, new int[]{n5, n6, n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{2, 2, 0, 2}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n11 + n12 + n9, new int[]{n5, n6, n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{0, 2, 0, 0}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n12 + n9 + n10, new int[]{n5, n6, n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{0, 0, 2, 0}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n9 + n11), com.corrodinggames.rts.game.b.b.a(new int[]{1, 0, 1, 2}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n12 + n10), com.corrodinggames.rts.game.b.b.a(new int[]{0, 1, 2, 1}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n5 + n7), com.corrodinggames.rts.game.b.b.a(new int[]{2, 5, 0, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(n6 + n8), com.corrodinggames.rts.game.b.b.a(new int[]{0, 5, 2, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n9 + n7, new int[]{n6, n5}), com.corrodinggames.rts.game.b.b.a(new int[]{1, 0, 0, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n11 + n6, new int[]{n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{1, 2, 0, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n12 + n6, new int[]{n5, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{0, 1, 0, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n10 + n5, new int[]{n6, n7}), com.corrodinggames.rts.game.b.b.a(new int[]{2, 1, 2, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n9 + n8, new int[]{n6, n5}), com.corrodinggames.rts.game.b.b.a(new int[]{1, 0, 2, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n11 + n5, new int[]{n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{1, 2, 2, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n12 + n7, new int[]{n5, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{0, 1, 0, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n10 + n8, new int[]{n6, n7}), com.corrodinggames.rts.game.b.b.a(new int[]{2, 1, 2, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n9 + n7 + n8, new int[]{n6, n5}), com.corrodinggames.rts.game.b.b.a(new int[]{1, 0, 0, 3, 2, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n11 + n6 + n5, new int[]{n7, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{1, 2, 0, 5, 2, 5}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n12 + n6 + n7, new int[]{n5, n8}), com.corrodinggames.rts.game.b.b.a(new int[]{0, 1, 2, 5, 2, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.a(n10 + n5 + n8, new int[]{n6, n7}), com.corrodinggames.rts.game.b.b.a(new int[]{2, 1, 0, 5, 0, 3}), true, e3, y2, e2);
        com.corrodinggames.rts.game.b.b.a(com.corrodinggames.rts.game.b.b.b(-1), com.corrodinggames.rts.game.b.b.a(new int[]{1, 4}), true, e3, y2, e2);
        L.p();
        L.q();
        L = null;
        y2.q();
        y2 = null;
        br.a("smoothFog load took:", l3);
        com.corrodinggames.rts.game.b.b.d();
        l = new h(1.0f, false);
        l.a();
        m = new h(0.5f, false);
        m.a();
    }

    public static void d() {
        if (H) {
            return;
        }
        H = true;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        I = l2.bQ.softFogFading;
        if (com.corrodinggames.rts.gameFramework.l.at() && Build.VERSION.SDK_INT > 26) {
            long l3 = Runtime.getRuntime().maxMemory() / 0x100000L;
            com.corrodinggames.rts.gameFramework.l.e("MaxHeapSizeInMB:" + l3);
            if (l3 > 200L) {
                com.corrodinggames.rts.gameFramework.l.e("enabling softFades");
                I = true;
            }
        }
    }

    private static int[] b(int n2) {
        return new int[]{n2};
    }

    private static int[] a(int n2, int ... nArray) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(n2);
        if (nArray.length == 1) {
            arrayList.add(n2 + nArray[0]);
        } else if (nArray.length == 2) {
            arrayList.add(n2 + nArray[0]);
            arrayList.add(n2 + nArray[1]);
            arrayList.add(n2 + nArray[0] + nArray[1]);
        } else if (nArray.length == 3) {
            arrayList.add(n2 + nArray[0]);
            arrayList.add(n2 + nArray[1]);
            arrayList.add(n2 + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[1]);
            arrayList.add(n2 + nArray[0] + nArray[2]);
            arrayList.add(n2 + nArray[1] + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[2]);
        } else if (nArray.length == 4) {
            arrayList.add(n2 + nArray[0]);
            arrayList.add(n2 + nArray[1]);
            arrayList.add(n2 + nArray[2]);
            arrayList.add(n2 + nArray[3]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[2] + nArray[3]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[3]);
            arrayList.add(n2 + nArray[1] + nArray[2] + nArray[3]);
            arrayList.add(n2 + nArray[0] + nArray[1]);
            arrayList.add(n2 + nArray[0] + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[3]);
            arrayList.add(n2 + nArray[1] + nArray[2]);
            arrayList.add(n2 + nArray[1] + nArray[3]);
            arrayList.add(n2 + nArray[2] + nArray[3]);
        } else {
            throw new RuntimeException("unhandled:" + nArray.length);
        }
        int[] nArray2 = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            if (arrayList.get(i2) == null) continue;
            nArray2[i2] = (Integer)arrayList.get(i2);
        }
        return nArray2;
    }

    private static int[] a(int ... nArray) {
        return nArray;
    }

    private static void a(int[] nArray, int n2, int n3, boolean bl, com.corrodinggames.rts.gameFramework.m.e e2, com.corrodinggames.rts.gameFramework.m.y y2, com.corrodinggames.rts.gameFramework.m.e e3) {
        com.corrodinggames.rts.game.b.b.a(nArray, com.corrodinggames.rts.game.b.b.a(new int[]{n2, n3}), bl, e2, y2, e3);
    }

    private static void a(int[] nArray, int[] nArray2, boolean bl, com.corrodinggames.rts.gameFramework.m.e e2, com.corrodinggames.rts.gameFramework.m.y y2, com.corrodinggames.rts.gameFramework.m.e e3) {
        int n2;
        int n3;
        if (bl) {
            y2.o();
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();
        int n4 = 20;
        int n5 = 20;
        rect.a(0, 0, n4, n5);
        com.corrodinggames.rts.gameFramework.m.y y3 = y2;
        for (n3 = 0; n3 < nArray2.length; n3 += 2) {
            n2 = nArray2[n3 + 0] * 20;
            int n6 = nArray2[n3 + 1] * 20;
            rect2.a(n2, n6, n2 + n4, n6 + n5);
            y3.a(e3, rect2, rect, null);
            rect4.a(rect2.c - 1, rect2.b, rect2.c, rect2.d);
            rect3.a(rect.c, rect.b, rect.c + 1, rect.d);
            y3.a(e3, rect4, rect3, null);
            rect4.a(rect2.a, rect2.d - 1, rect2.c, rect2.d);
            rect3.a(rect.a, rect.d, rect.c, rect.d + 1);
            y3.a(e3, rect4, rect3, null);
        }
        y3.p();
        for (n3 = 0; n3 < nArray.length; ++n3) {
            n2 = nArray[n3] + 128;
            com.corrodinggames.rts.game.b.b.a(n2, e2);
        }
    }

    public static void a(int n2, com.corrodinggames.rts.gameFramework.m.e e2) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        rect2.a(0, 0, 20, 20);
        com.corrodinggames.rts.game.b.b.a(n2, rect);
        com.corrodinggames.rts.game.b.h.a(L, e2, rect2, rect, null);
    }

    public static void a(int n2, Rect rect) {
        int n3 = 20;
        int n4 = 20;
        int n5 = n2 % 16;
        int n6 = (int)((float)n2 * 0.0625f);
        int n7 = n5 * (n3 + 2) + 1;
        int n8 = n6 * (n4 + 2) + 1;
        rect.a = n7;
        rect.b = n8;
        rect.c = n7 + n3;
        rect.d = n8 + n4;
    }

    public final short a(g g2) {
        if (this.as >= this.B.length) {
            g[] gArray = new g[com.corrodinggames.rts.gameFramework.f.c(this.B.length + 100, Short.MAX_VALUE)];
            System.arraycopy(this.B, 0, gArray, 0, this.B.length);
            this.B = gArray;
        }
        int n2 = this.as;
        if (this.as < 32766) {
            ++this.as;
        } else {
            com.corrodinggames.rts.gameFramework.l.b("Max unique tile limit reached at: " + this.as);
        }
        this.B[n2] = g2;
        return (short)n2;
    }

    public final g a(short s2) {
        return this.B[s2];
    }

    public g a(g g2, int n2, int n3) {
        if (g2 != null && g2.m != null) {
            int n4 = (n2 * 13 + n3 * 1313) % (g2.m.length + 1);
            if (--n4 >= 0) {
                return g2.m[n4];
            }
        }
        return g2;
    }

    public boolean a(float f2, float f3, n n2) {
        if (this.E) {
            int n3 = (int)(f2 * this.r);
            int n4 = (int)(f3 * this.s);
            if (n2.N != null && this.c(n3, n4) && n2.N[n3][n4] >= 5) {
                return false;
            }
        }
        return true;
    }

    public boolean a(int n2, int n3, n n4) {
        return !this.E || n4.N == null || !this.c(n2, n3) || n4.N[n2][n3] < 5;
    }

    public void a(float f2, float f3) {
        this.T = (int)(f2 * this.r);
        this.U = (int)(f3 * this.s);
    }

    public void a(int n2, int n3) {
        this.T = n2 * this.n;
        this.U = n3 * this.o;
    }

    public void b(int n2, int n3) {
        this.T = n2 * this.n + this.p;
        this.U = n3 * this.o + this.q;
    }

    public PointF a(Point point) {
        this.V.a(point.a * this.n, point.b * this.o);
        return this.V;
    }

    public void b(float f2, float f3) {
        this.a(f2, f3);
        this.a(this.T, this.U);
    }

    public float a(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > this.i()) {
            f2 = this.i();
        }
        return f2;
    }

    public float b(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > this.j()) {
            f2 = this.j();
        }
        return f2;
    }

    public final boolean c(int n2, int n3) {
        return n2 >= 0 && n2 < this.C && n3 >= 0 && n3 < this.D;
    }

    public g c(float f2, float f3) {
        int n2 = (int)(f2 * this.r);
        int n3 = (int)(f3 * this.s);
        if (n2 < 0 || n2 >= this.C || n3 < 0 || n3 >= this.D) {
            return null;
        }
        return this.u.a(n2, n3);
    }

    public g d(int n2, int n3) {
        if (!this.c(n2, n3)) {
            return null;
        }
        return this.u.a(n2, n3);
    }

    public g e(int n2, int n3) {
        if (!this.c(n2, n3)) {
            return null;
        }
        if (this.y == null) {
            return null;
        }
        return this.y.a(n2, n3);
    }

    void a(RectF rectF) {
        if (com.corrodinggames.rts.gameFramework.l.C()) {
            rectF.a *= (float)(this.n / 20);
            rectF.c *= (float)(this.n / 20);
            rectF.b *= (float)(this.o / 20);
            rectF.d *= (float)(this.o / 20);
        }
    }

    public b() {
        if (com.corrodinggames.rts.gameFramework.l.C()) {
            this.n = 60;
            this.o = 60;
        }
        this.p = this.n / 2;
        this.q = this.o / 2;
        this.r = 1.0f / (float)this.n;
        this.s = 1.0f / (float)this.o;
        this.ab = new ag();
        this.ab.a(100, 255, 0, 0);
        this.ab.b(16.0f);
        this.ac = new ag();
        this.ac.a(Paint$Style.b);
        this.ac.a(1.0f);
        this.ac.a(255, 0, 225, 0);
        this.ad = new ag();
        this.ad.a(Paint$Style.b);
        this.ad.a(1.0f);
        this.ad.a(100, 0, 185, 0);
        this.ae = new ag();
        this.ae.a(Paint$Style.b);
        this.ae.a(1.0f);
        this.ae.a(255, 175, 0, 0);
        this.af = new ag();
        this.af.a(155, 175, 0, 0);
        this.ag = new ag();
        this.ag.a(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public static void a(String string, com.corrodinggames.rts.gameFramework.j.as as2) {
        InputStream inputStream = com.corrodinggames.rts.game.b.b.b(string);
        if (inputStream == null) {
            throw new IOException("writeMapStream: Could not find map:" + string);
        }
        int n2 = (int)com.corrodinggames.rts.game.b.b.a(string);
        if (n2 == -1) {
            new IOException("writeMapStream: Failed to get map size");
        }
        if (n2 == 0) {
            new IOException("writeMapStream: Got empty map size");
        }
        com.corrodinggames.rts.gameFramework.l.e("Sending map stream of size: " + n2);
        as2.a(inputStream, n2);
    }

    public static long a(String string) {
        String string2 = "" + string;
        String string3 = com.corrodinggames.rts.gameFramework.e.a.e(string2);
        af af2 = com.corrodinggames.rts.gameFramework.utility.ae.a(string3);
        if (af2 != null && !string3.endsWith(".rwmod")) {
            long l2 = af2.a(string3, false);
            if (l2 == -1L) {
                // empty if block
            }
            return l2;
        }
        if (com.corrodinggames.rts.gameFramework.e.a.c(string2)) {
            AssetManager assetManager = com.corrodinggames.rts.gameFramework.l.B().am.d();
            try {
                AssetFileDescriptor assetFileDescriptor = assetManager.b(string3);
                return assetFileDescriptor.getLength();
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
        }
        File file = new File(string3);
        return file.length();
    }

    public static InputStream b(String string) {
        InputStream inputStream;
        InputStream inputStream2 = com.corrodinggames.rts.game.b.b.d(string);
        if (inputStream2 == null && (inputStream = com.corrodinggames.rts.game.b.b.d(string.replace(".tmx", "") + "_moved")) != null) {
            String string2 = com.corrodinggames.rts.gameFramework.f.a(inputStream);
            string2 = string2.trim();
            com.corrodinggames.rts.gameFramework.l.e("Found moved map at:" + string2);
            inputStream2 = com.corrodinggames.rts.game.b.b.d(string2);
        }
        return inputStream2;
    }

    public static String c(String string) {
        if (string == null) {
            return null;
        }
        String string2 = com.corrodinggames.rts.gameFramework.e.a.e(string);
        return string2;
    }

    public static InputStream d(String string) {
        String string2 = com.corrodinggames.rts.game.b.b.c("" + string);
        com.corrodinggames.rts.gameFramework.l.e("Mapfile: " + string2);
        com.corrodinggames.rts.gameFramework.utility.j j2 = com.corrodinggames.rts.gameFramework.e.a.k(string2);
        return j2;
    }

    public void a(Document document, OutputStream outputStream) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        DOMSource dOMSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(outputStream);
        transformer.transform(dOMSource, streamResult);
    }

    public void a(InputStream inputStream, OutputStream outputStream) {
        Object object;
        Object object2;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setEntityResolver(new b$1(this));
        Document document = documentBuilder.parse(inputStream);
        Element element = document.getDocumentElement();
        String string = element.getAttribute("orientation");
        if (!string.equals("orthogonal")) {
            throw new f("Only orthogonal maps are supported, found: " + string);
        }
        NodeList nodeList = element.getElementsByTagName("SOMETHING");
        for (int i2 = 0; i2 < nodeList.getLength(); ++i2) {
            Element element2 = (Element)nodeList.item(i2);
        }
        NodeList nodeList2 = element.getElementsByTagName("layer");
        for (int i3 = 0; i3 < nodeList2.getLength(); ++i3) {
            Element element3 = (Element)nodeList2.item(i3);
            object2 = element3.getAttribute("name");
            if (!"units".equalsIgnoreCase((String)object2)) continue;
            element3.getParentNode().removeChild(element3);
        }
        NodeList nodeList3 = element.getElementsByTagName("objectgroup");
        for (int i4 = 0; i4 < nodeList3.getLength(); ++i4) {
            object2 = (Element)nodeList3.item(i4);
            object = object2.getAttribute("name");
            if (!"UnitObjects".equalsIgnoreCase((String)object)) continue;
            object2.getParentNode().removeChild((Node)object2);
        }
        Element element4 = document.createElement("objectgroup");
        element4.setAttribute("name", "UnitObjects");
        object2 = com.corrodinggames.rts.game.units.am.bF();
        object = ((o)object2).iterator();
        while (object.hasNext()) {
            am am2 = (am)object.next();
            if (!(am2 instanceof am) || am2 instanceof al && ((al)am2).bM) continue;
            am am3 = am2;
            if (am3.bV || am3.u()) continue;
            com.corrodinggames.rts.game.units.custom.b.n n2 = am3.dn();
            if (am3.cO != null && n2 != null) {
                if (n2.D) continue;
                continue;
            }
            Element element5 = document.createElement("object");
            int n3 = 20;
            if ((float)n3 < am3.cj) {
                n3 = (int)am3.cj;
            }
            element5.setAttribute("name", am3.r().i() + " (t:" + am3.bX.k + ")");
            element5.setAttribute("x", "" + (am3.eo - (float)(n3 / 2)));
            element5.setAttribute("y", "" + (am3.ep - (float)(n3 / 2)));
            element5.setAttribute("width", "" + n3);
            element5.setAttribute("height", "" + n3);
            float f2 = am3.bI() ? am3.cg : am3.cg + 90.0f;
            element5.setAttribute("rotation", "" + f2);
            Integer n4 = this.a(am3.r());
            if (n4 != null) {
                element5.setAttribute("gid", "" + n4);
            }
            Element element6 = document.createElement("properties");
            Element element7 = document.createElement("property");
            element7.setAttribute("name", "unit");
            element7.setAttribute("value", am3.r().i());
            element6.appendChild(element7);
            element7 = document.createElement("property");
            element7.setAttribute("name", "team");
            element7.setAttribute("value", "" + am3.bX.k);
            element6.appendChild(element7);
            element5.appendChild(element6);
            element4.appendChild(element5);
        }
        element.appendChild(element4);
        this.a(document, outputStream);
    }

    public boolean a(String string, String string2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        try {
            this.b(string, string2);
            l2.bS.h.a(null, "Map exported.");
            return true;
        }
        catch (f f2) {
            l2.c("Error exporting map", "Failed to export map. error: " + f2.getMessage());
            return false;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            l2.c("Error exporting map", "Failed to export map. IO error: " + iOException.getMessage());
            return false;
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            noClassDefFoundError.printStackTrace();
            l2.c("Error exporting map", "Failed to export map. Class not found: " + noClassDefFoundError.getMessage());
            return false;
        }
    }

    public void b(String string, String string2) {
        OutputStream outputStream;
        com.corrodinggames.rts.gameFramework.l.e(" --- Saving map:" + string + " to: " + string2);
        InputStream inputStream = com.corrodinggames.rts.game.b.b.b(string);
        if (inputStream == null) {
            throw new IOException("Could not find orginal map: " + string);
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        File file = new File(string2 = com.corrodinggames.rts.gameFramework.e.a.e(string2)).getParentFile();
        if (!com.corrodinggames.rts.gameFramework.e.a.i(file.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.e.a.l(file.getAbsolutePath());
        }
        if (!com.corrodinggames.rts.gameFramework.e.a.f(file.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.l.b("Save Map: Could not create parent directory");
        }
        try {
            outputStream = com.corrodinggames.rts.gameFramework.e.a.b(string2, false);
            if (outputStream == null) {
                throw new IOException("Failed to get save target:" + string2);
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            throw new IOException("Failed to open save target:" + string2);
        }
        try {
            this.a((InputStream)bufferedInputStream, outputStream);
        }
        catch (ParserConfigurationException parserConfigurationException) {
            throw new IOException(parserConfigurationException);
        }
        catch (SAXException sAXException) {
            throw new IOException(sAXException);
        }
        catch (IOException iOException) {
            throw new IOException(iOException);
        }
        catch (TransformerException transformerException) {
            throw new IOException(transformerException);
        }
        try {
            outputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void a(String string, boolean bl) {
        com.corrodinggames.rts.gameFramework.l.e(" --- Loading map ---");
        InputStream inputStream = com.corrodinggames.rts.game.b.b.b(string);
        if (inputStream == null) {
            String string2 = com.corrodinggames.rts.game.b.b.c(string);
            throw new f("Could not find map: " + com.corrodinggames.rts.gameFramework.e.a.d(string2));
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        this.a((InputStream)bufferedInputStream, bl);
        try {
            bufferedInputStream.close();
            inputStream.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public j e(String string) {
        j j2 = null;
        for (j j3 : this.t) {
            if (!string.equals(j3.a)) continue;
            j2 = j3;
        }
        if (j2 == null) {
            j j3;
            int n2 = 1;
            if (this.t.size() > 0) {
                j3 = (j)this.t.get(this.t.size() - 1);
                n2 = j3.l + 100;
                j3.c(n2);
            }
            j3 = new j(this, string, n2 + 1);
            this.t.add(j3);
            j2 = j3;
        }
        if (j2.b == null) {
            j2.c();
        }
        return j2;
    }

    public g a(String string, int n2, int n3) {
        j j2 = this.e(string);
        if (this.ah == null) {
            this.ah = new HashMap();
        }
        boolean bl = true;
        int n4 = j2.a(n2, n3);
        int n5 = j2.l + n4;
        g g2 = (g)this.ah.get(n5);
        if (g2 != null) {
            return g2;
        }
        g g3 = com.corrodinggames.rts.game.b.g.a(this, this.u, j2, n5 - j2.l, (short)0, (short)0, bl);
        this.ah.put(n5, g3);
        return g3;
    }

    public void a(InputStream inputStream, boolean bl) {
        Object object;
        String string;
        Object object2;
        Object object3;
        this.A.clear();
        l.b();
        m.b();
        try {
            Object object42;
            Object object52;
            Object object62;
            Object object7;
            Element element;
            int n2;
            Object object8;
            int n3;
            com.corrodinggames.rts.gameFramework.l.e("---- Loading map data ----");
            object3 = DocumentBuilderFactory.newInstance();
            ((DocumentBuilderFactory)object3).setValidating(false);
            DocumentBuilder documentBuilder = ((DocumentBuilderFactory)object3).newDocumentBuilder();
            documentBuilder.setEntityResolver(new b$2(this));
            Document document = documentBuilder.parse(inputStream);
            object2 = document.getDocumentElement();
            string = object2.getAttribute("orientation");
            if (!string.equals("orthogonal")) {
                throw new f("Only orthogonal maps are supported, found: " + string);
            }
            int n4 = Integer.parseInt(object2.getAttribute("width"));
            int n5 = Integer.parseInt(object2.getAttribute("height"));
            this.C = n4;
            this.D = n5;
            com.corrodinggames.rts.gameFramework.l.e("Map size: " + this.C + ", " + this.D);
            this.ar = 150.0f;
            if (this.E) {
                com.corrodinggames.rts.gameFramework.l.e("Setting up team fog..");
                for (n3 = 0; n3 < com.corrodinggames.rts.game.n.c; ++n3) {
                    object8 = com.corrodinggames.rts.game.n.k(n3);
                    if (object8 == null) continue;
                    ((n)object8).L = this.C;
                    ((n)object8).M = this.D;
                    ((n)object8).N = new byte[this.C][this.D];
                    for (n2 = 0; n2 < this.C; ++n2) {
                        for (int i2 = 0; i2 < this.D; ++i2) {
                            ((n)object8).N[n2][i2] = 10;
                        }
                    }
                }
            } else {
                com.corrodinggames.rts.gameFramework.l.e("No team fog on this map..");
                for (n3 = 0; n3 < com.corrodinggames.rts.game.n.c; ++n3) {
                    object8 = com.corrodinggames.rts.game.n.k(n3);
                    if (object8 == null) continue;
                    ((n)object8).N = null;
                }
            }
            if ((element = (Element)object2.getElementsByTagName("properties").item(0)) != null && (object8 = element.getElementsByTagName("property")) != null) {
                object = new Properties();
                for (n2 = 0; n2 < object8.getLength(); ++n2) {
                    Element element2 = (Element)object8.item(n2);
                    object7 = element2.getAttribute("name");
                    object62 = element2.getAttribute("value");
                    ((Properties)object).setProperty((String)object7, (String)object62);
                }
            }
            object8 = null;
            NodeList nodeList = object2.getElementsByTagName("tileset");
            for (int n6 = 0; n6 < nodeList.getLength(); n6 = (int)((short)(n6 + 1))) {
                object7 = (Element)nodeList.item(n6);
                object62 = new j(this, (Element)object7);
                ((j)object62).n = (short)n6;
                if (object8 != null) {
                    ((j)object8).c(((j)object62).l - 1);
                }
                object8 = object62;
                this.t.add(object62);
            }
            NodeList nodeList2 = object2.getElementsByTagName("layer");
            for (int i3 = 0; i3 < nodeList2.getLength(); ++i3) {
                object62 = (Element)nodeList2.item(i3);
                object52 = object62.getAttribute("name");
                if ("set".equalsIgnoreCase((String)object52) || "set-disabled".equalsIgnoreCase((String)object52)) continue;
                object42 = new e(this, (Element)object62);
                ((e)object42).j = i3;
                this.z.add(object42);
            }
            for (Object object62 : this.z) {
                if (((e)object62).r) {
                    this.u = object62;
                }
                if (((e)object62).k.equalsIgnoreCase("grounddetails")) {
                    this.v = object62;
                }
                if (((e)object62).k.equalsIgnoreCase("grounddetails2")) {
                    this.w = object62;
                }
                if (((e)object62).k.equalsIgnoreCase("Items") || ((e)object62).k.equalsIgnoreCase("Objects")) {
                    this.y = object62;
                }
                if (!((e)object62).k.equalsIgnoreCase("PathingOverride")) continue;
                this.x = object62;
            }
            if (this.u == null) {
                throw new f("'Ground' layer was not found in map, this layer is required");
            }
            if (this.B == null || this.B.length == 0) {
                throw new f("Invalid map, no tiles have been set");
            }
            if (!com.corrodinggames.rts.gameFramework.l.C() && !com.corrodinggames.rts.gameFramework.l.D()) {
                for (int i4 = 0; i4 < this.C; ++i4) {
                    for (int i5 = 0; i5 < this.D; ++i5) {
                        if (this.u.a(i4, i5) != null) continue;
                        throw new f("An empty tile on the Ground layer at " + i4 + "," + i5 + " all tiles must be filled");
                    }
                }
            }
            if (this.y == null) {
                throw new f("'Items' layer was not found in map, this layer is required");
            }
            NodeList nodeList3 = object2.getElementsByTagName("objectgroup");
            int n7 = 0;
            while (n7 < nodeList3.getLength()) {
                object52 = (Element)nodeList3.item(n7);
                object42 = new i((Element)object52, this);
                ((i)object42).a = n7++;
                this.P.add(object42);
            }
            com.corrodinggames.rts.game.b.j.a();
            for (Object object52 : this.t) {
                if (!((j)object52).q) continue;
                ((j)object52).c();
            }
            com.corrodinggames.rts.game.b.j.b();
            for (int i6 = 0; i6 <= 1; ++i6) {
                for (Object object42 : this.z) {
                    boolean bl2;
                    boolean bl3 = object42 == this.u;
                    boolean bl4 = bl2 = i6 == 0;
                    if (bl3 != bl2) continue;
                    ((e)object42).w = false;
                    if (!((e)object42).s) continue;
                    for (int i7 = 0; i7 < this.C; ++i7) {
                        for (int i8 = 0; i8 < this.D; ++i8) {
                            int n8;
                            g g2 = ((e)object42).a(i7, i8);
                            if (g2 == null || g2.c != -2) continue;
                            g2.c = l.a(g2.a, g2.b);
                            if (g2.c >= 0 && (n8 = m.a(g2.a, g2.b)) != g2.c) {
                                throw new RuntimeException("Meta index mismatch: " + n8 + " vs " + g2.c);
                            }
                            if (g2.c >= 0) continue;
                            ((e)object42).w = true;
                        }
                    }
                }
            }
            l.c();
            m.c();
            this.Q = this.f("triggers");
        }
        catch (IOException iOException) {
            throw new f("Failed to parse map", iOException);
        }
        catch (ParserConfigurationException parserConfigurationException) {
            throw new RuntimeException("Failed to parse map", parserConfigurationException);
        }
        catch (SAXException sAXException) {
            com.corrodinggames.rts.gameFramework.l.e(" --- SAXException: Failed to parse map - " + sAXException.getMessage() + " ---");
            try {
                com.corrodinggames.rts.gameFramework.l.e("available:" + inputStream.available());
                inputStream.reset();
                com.corrodinggames.rts.gameFramework.l.e("after reset:" + inputStream.available());
            }
            catch (IOException iOException) {
                com.corrodinggames.rts.gameFramework.l.e("-- error writing debug info --");
                iOException.printStackTrace();
            }
            throw new f("Failed to parse map - " + sAXException.getMessage(), sAXException);
        }
        object3 = null;
        if (this.Q != null) {
            object3 = this.Q.a("map_info");
        }
        boolean bl5 = false;
        boolean bl6 = false;
        object2 = com.corrodinggames.rts.gameFramework.l.B();
        ((com.corrodinggames.rts.gameFramework.l)object2).ce = null;
        string = null;
        object = null;
        if (object3 != null) {
            String string2 = ((a)object3).b("type");
            object = ((a)object3).b("fog");
            if ("mission".equalsIgnoreCase(string2) || "survival".equalsIgnoreCase(string2) || "challenge".equalsIgnoreCase(string2) || "skirmish".equalsIgnoreCase(string2)) {
                string = string2;
            } else {
                com.corrodinggames.rts.gameFramework.l.b("Unknown map type:" + string2);
            }
        } else {
            com.corrodinggames.rts.gameFramework.l.b("Map type not found on mapInfo");
        }
        if (string == null) {
            com.corrodinggames.rts.gameFramework.l.b("Defaulting to skirmish map type");
            string = "skirmish";
        } else {
            com.corrodinggames.rts.gameFramework.l.b("Map type: " + string);
        }
        ((com.corrodinggames.rts.gameFramework.l)object2).ce = new com.corrodinggames.rts.gameFramework.n.f();
        ((com.corrodinggames.rts.gameFramework.l)object2).ce.a(bl);
        if (object != null && !"".equals(object)) {
            if (!((String)object).equalsIgnoreCase("none")) {
                bl5 = true;
                if (((String)object).equalsIgnoreCase("los")) {
                    bl6 = true;
                } else if (!((String)object).equalsIgnoreCase("map")) {
                    com.corrodinggames.rts.gameFramework.l.e("Unknown map fog type: " + (String)object);
                }
            }
        } else if (com.corrodinggames.rts.gameFramework.l.av() && !((com.corrodinggames.rts.gameFramework.l)object2).N()) {
            bl5 = true;
            if (string != null && string.equalsIgnoreCase("skirmish")) {
                bl6 = true;
            }
        }
        if (!bl5) {
            this.E = false;
        }
        if (bl5 && bl6) {
            this.F = true;
        }
        this.W = true;
    }

    public void e() {
    }

    public void a(l l2) {
        if (com.corrodinggames.rts.gameFramework.l.aU && !com.corrodinggames.rts.gameFramework.l.aW) {
            return;
        }
        al.a(l2);
    }

    public void a(y y2, int n2, int n3, int n4, int n5, int n6, int n7, com.corrodinggames.rts.gameFramework.m.y y3, boolean bl, int n8) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        as as2 = l2.bS.ac.i();
        ao ao2 = as2.o();
        for (int i2 = n2; i2 <= n4; ++i2) {
            for (int i3 = n3; i3 <= n5; ++i3) {
                boolean bl2 = com.corrodinggames.rts.game.units.d.d.a(y2, as2, ao2, i2, i3, n8);
                int n9 = i2 * b2.n - n6;
                int n10 = i3 * b2.o - n7;
                this.an.a(n9, n10, n9 + b2.n - 1, n10 + b2.o - 1);
                if (bl) {
                    if (bl2) {
                        y3.b(this.an, b2.ad);
                        continue;
                    }
                    y3.b(this.an, b2.af);
                    y3.b(this.an, b2.ae);
                    continue;
                }
                if (bl2) {
                    y3.b(this.an, b2.ac);
                    continue;
                }
                y3.b(this.an, b2.ae);
            }
        }
    }

    public static void f() {
        al.d();
    }

    public void c(float f2) {
        al.a(f2);
    }

    public void g() {
        al.c();
    }

    public void d(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = com.corrodinggames.rts.gameFramework.l.at();
        if (bl) {
            l2.bO.a(e);
        }
        this.c(f2);
        if (bl) {
            l2.bO.b(e);
        }
        if (this.X) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            int n2 = this.Y * this.n;
            int n3 = this.Z * this.o;
            rect2.a(n2, n3, n2 + this.n, n3 + this.o);
            rect2.a(-com.corrodinggames.rts.gameFramework.l.B().cu, -com.corrodinggames.rts.gameFramework.l.B().cv);
        }
    }

    public void e(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.f(f2);
    }

    public void h() {
        for (Object object : this.t) {
            ((j)object).d();
        }
        this.t.clear();
        for (Object object : this.z) {
            ((e)object).b();
        }
        this.z.clear();
        this.P.clear();
        this.Q = null;
        al.c();
    }

    public j a(int n2) {
        for (int i2 = 0; i2 < this.t.size(); ++i2) {
            j j2 = (j)this.t.get(i2);
            if (!j2.d(n2)) continue;
            return j2;
        }
        return null;
    }

    public Integer a(as as2) {
        String string = as2.i();
        Integer n2 = this.c("unit", string);
        if (n2 == null) {
            n2 = this.c("customUnit", string);
        }
        return n2;
    }

    public Integer c(String string, String string2) {
        for (int i2 = 0; i2 < this.t.size(); ++i2) {
            j j2 = (j)this.t.get(i2);
            Integer n2 = j2.b(string, string2);
            if (n2 == null) continue;
            return n2;
        }
        return null;
    }

    public i f(String string) {
        for (i i2 : this.P) {
            if (!string.equalsIgnoreCase(i2.b)) continue;
            return i2;
        }
        return null;
    }

    public float i() {
        return this.C * this.n;
    }

    public float j() {
        return this.D * this.o;
    }

    public void a(float f2, float f3, int n2, n n3, boolean bl) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.E) {
            com.corrodinggames.rts.gameFramework.n.f f4;
            long l3 = 0L;
            if (a) {
                l3 = br.a();
            }
            boolean bl2 = true;
            boolean bl3 = n3.E;
            if (!(l2.ay() || (f4 = l2.ce) == null || f4.a() || f4.b())) {
                bl2 = false;
            }
            if (!bl2) {
                this.b(f2, f3, n2, n3, bl);
            } else {
                for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                    n n4 = com.corrodinggames.rts.game.n.k(i2);
                    if (n4 == null || n4 != n3 && (n4.w || !n4.d(n3) && !bl3)) continue;
                    this.b(f2, f3, n2, n4, bl);
                }
            }
            if (a) {
                this.ap += br.a() - l3;
            }
        }
    }

    public byte a(int n2, int n3, byte[][] byArray, byte by) {
        byte by2 = 0;
        int n4 = this.C;
        int n5 = this.D;
        if (n2 >= 1) {
            if (byArray[n2 - 1][n3] >= by) {
                by2 = (byte)(by2 - 128);
            }
            if (n3 >= 1 && byArray[n2 - 1][n3 - 1] >= by) {
                by2 = (byte)(by2 + 1);
            }
            if (n3 < n5 - 1 && byArray[n2 - 1][n3 + 1] >= by) {
                by2 = (byte)(by2 + 8);
            }
        }
        if (n3 >= 1) {
            if (byArray[n2][n3 - 1] >= by) {
                by2 = (byte)(by2 + 16);
            }
            if (n2 < n4 - 1 && byArray[n2 + 1][n3 - 1] >= by) {
                by2 = (byte)(by2 + 2);
            }
        }
        if (n2 < n4 - 1 && byArray[n2 + 1][n3] >= by) {
            by2 = (byte)(by2 + 32);
        }
        if (n3 < n5 - 1) {
            if (byArray[n2][n3 + 1] >= by) {
                by2 = (byte)(by2 + 64);
            }
            if (n2 < n4 - 1 && byArray[n2 + 1][n3 + 1] >= by) {
                by2 = (byte)(by2 + 4);
            }
        }
        if (by2 == 127) {
            by2 = -1;
        }
        return by2;
    }

    public void k() {
        this.l();
        for (int i2 = 0; i2 < this.C; ++i2) {
            for (int i3 = 0; i3 < this.D; ++i3) {
                this.M[i2][i3] = 0;
                this.N[i2][i3] = 0;
            }
        }
    }

    public void f(int n2, int n3) {
        this.M[n2][n3] = 0;
        this.N[n2][n3] = 0;
    }

    public void g(int n2, int n3) {
        int n4 = n2 - 1;
        int n5 = n3 - 1;
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        int n6 = n2 + 1;
        int n7 = n3 + 1;
        if (n6 > this.C - 1) {
            n6 = this.C - 1;
        }
        if (n7 > this.D - 1) {
            n7 = this.D - 1;
        }
        for (int i2 = n4; i2 <= n6; ++i2) {
            for (int i3 = n5; i3 <= n7; ++i3) {
                if (this.M[i2][i3] != 0) {
                    this.M[i2][i3] = 127;
                }
                if (this.N[i2][i3] == 0) continue;
                this.N[i2][i3] = 127;
            }
        }
    }

    public void l() {
        boolean bl = false;
        if (this.M == null) {
            bl = true;
        } else if (this.M.length != this.C || this.M[0].length != this.D) {
            com.corrodinggames.rts.gameFramework.l.e("smoothFog_cache: Size mismatch");
            bl = true;
        }
        if (bl) {
            com.corrodinggames.rts.gameFramework.l.e("Building smoothFog_cache");
            this.M = new byte[this.C][this.D];
            this.N = new byte[this.C][this.D];
            for (int i2 = 0; i2 < this.C; ++i2) {
                for (int i3 = 0; i3 < this.D; ++i3) {
                    this.M[i2][i3] = 127;
                    this.N[i2][i3] = 127;
                }
            }
        }
    }

    public void b(float f2, float f3, int n2, n n3, boolean bl) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.E && n3.N != null) {
            this.l();
            int n4 = n2;
            float f4 = (n4 - 5) * (n4 - 5);
            float f5 = (n4 - 3) * (n4 - 3);
            float f6 = n4 * n4;
            float f7 = 1.0f / (f6 - f5) * 10.0f;
            b b2 = this;
            b2.a(f2, f3);
            int n5 = b2.T;
            int n6 = b2.U;
            float f8 = f2 * b2.r;
            float f9 = f3 * b2.s;
            byte[][] byArray = n3.N;
            int n7 = n4 - 1;
            int n8 = n5 - n7;
            int n9 = n6 - n7;
            if (n8 < 0) {
                n8 = 0;
            }
            if (n9 < 0) {
                n9 = 0;
            }
            int n10 = n5 + n7;
            int n11 = n6 + n7;
            if (n10 > this.C - 1) {
                n10 = this.C - 1;
            }
            if (n11 > this.D - 1) {
                n11 = this.D - 1;
            }
            c c2 = al;
            boolean bl2 = false;
            boolean bl3 = n3.q();
            for (int i2 = n8; i2 <= n10; ++i2) {
                for (int i3 = n9; i3 <= n11; ++i3) {
                    byte by;
                    byte by2 = byArray[i2][i3];
                    if (by2 == 0) continue;
                    float f10 = com.corrodinggames.rts.gameFramework.f.a(f8, f9, (float)i2, (float)i3);
                    if (f10 <= f5) {
                        if (by2 <= 0) continue;
                        byArray[i2][i3] = 0;
                        if (!bl3) continue;
                        c2.a(i2, i3, true);
                        bl2 = true;
                        if (f10 <= f4 && bl) {
                            this.f(i2, i3);
                            continue;
                        }
                        this.g(i2, i3);
                        continue;
                    }
                    if (!(f10 <= f6) || by2 <= (by = (byte)((f10 - f5) * f7))) continue;
                    byArray[i2][i3] = by;
                    if (!bl3) continue;
                    c2.a(i2, i3, true);
                    bl2 = true;
                    this.g(i2, i3);
                }
            }
            if (bl2) {
                l2.bW.O = true;
            }
        }
    }

    public void f(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (a) {
            this.aq += f2;
            if (this.aq > 60.0f) {
                this.aq = 0.0f;
                if (this.ap > 0L) {
                    com.corrodinggames.rts.gameFramework.l.e("seeThoughFogOfWarTimes: " + br.b(this.ap));
                    this.ap = 0L;
                }
                if (this.ap < 0L) {
                    com.corrodinggames.rts.gameFramework.l.e("seeThoughFogOfWarTimes negative: " + br.b(this.ap));
                    this.ap = 0L;
                }
            }
        }
        if (this.E && this.F) {
            this.l();
            this.ar += f2;
            if (this.ar > 260.0f) {
                bq bq2;
                int n2;
                this.ar = 0.0f;
                w[] wArray = com.corrodinggames.rts.game.units.am.er.a();
                int n3 = com.corrodinggames.rts.gameFramework.w.er.size();
                boolean bl = false;
                for (n2 = 0; n2 < com.corrodinggames.rts.game.n.c; ++n2) {
                    Object object;
                    int n4;
                    bq2 = com.corrodinggames.rts.game.n.k(n2);
                    if (bq2 == null || ((n)bq2).G) continue;
                    bl = true;
                    for (n4 = 0; n4 < n3; ++n4) {
                        w w2 = wArray[n4];
                        if (!(w2 instanceof y) || !((am)(object = (y)w2)).bI()) continue;
                        ((am)object).g((n)bq2);
                    }
                    if (((n)bq2).N == null) {
                        com.corrodinggames.rts.gameFramework.l.b("fogOfWar_map==null for:" + n2);
                    }
                    n4 = 0;
                    boolean bl2 = ((n)bq2).q();
                    object = ((n)bq2).N;
                    byte[][] byArray = this.N;
                    for (int i2 = 0; i2 < this.C; ++i2) {
                        for (int i3 = 0; i3 < this.D; ++i3) {
                            if (object[i2][i3] >= 5) continue;
                            object[i2][i3] = 5;
                            if (!bl2) continue;
                            al.a(i2, i3, true);
                            n4 = 1;
                            byArray[i2][i3] = 127;
                        }
                    }
                    if (n4 == 0) continue;
                    l2.bW.O = true;
                }
                for (n2 = 0; n2 < n3; ++n2) {
                    bq2 = wArray[n2];
                    if (!(bq2 instanceof y)) continue;
                    y y2 = (y)bq2;
                    if (y2.bV) continue;
                    y2.c(false);
                }
                if (bl) {
                    for (n2 = 0; n2 < n3; ++n2) {
                        y y3;
                        bq2 = wArray[n2];
                        if (!(bq2 instanceof y) || !(y3 = (y)bq2).bI()) continue;
                        y3.cX();
                    }
                }
            }
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(false);
    }

    public void a(k k2) {
        boolean bl = k2.e();
        if (bl) {
            int n2 = k2.f();
            int n3 = k2.f();
            for (int i2 = 0; i2 < n2; ++i2) {
                for (int i3 = 0; i3 < n3; ++i3) {
                    k2.d();
                }
            }
        }
    }

    private InputStream a(String string, String string2, int n2) {
        String[] stringArray = string2.split("/");
        if (stringArray.length >= n2) {
            String string3 = "";
            boolean bl = true;
            for (int i2 = stringArray.length - n2; i2 < stringArray.length; ++i2) {
                if (!bl) {
                    string3 = string3 + "/";
                }
                bl = false;
                string3 = string3 + stringArray[i2];
            }
            return com.corrodinggames.rts.gameFramework.e.a.j(string + string3);
        }
        return null;
    }

    public InputStream d(String string, String string2) {
        InputStream inputStream = null;
        inputStream = com.corrodinggames.rts.gameFramework.e.a.j(string + string2);
        if (inputStream == null) {
            inputStream = this.a(string, string2, 3);
        }
        if (inputStream == null) {
            inputStream = this.a(string, string2, 2);
        }
        if (inputStream == null) {
            inputStream = this.a(string, string2, 1);
        }
        if (inputStream == null) {
            throw new IOException("File could not be found:" + string + string2);
        }
        return inputStream;
    }

    public boolean a(n n2, int n3, int n4) {
        b b2 = this;
        return this.G || !b2.E || n2.N == null || !b2.c(n3, n4) || n2.N[n3][n4] != 10;
    }

    static {
        g = new Paint();
        h = new Paint();
        i = new Paint();
        j = new Paint();
        H = false;
        I = false;
        J = false;
        al = new c();
    }
}
