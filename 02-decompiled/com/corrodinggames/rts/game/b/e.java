/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.b.h;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.w3c.dom.Element;

public class e {
    private static byte[] x;
    static ag a;
    static ag[] b;
    static ag c;
    static ag d;
    static ag e;
    static ag f;
    static ag g;
    static ag[] h;
    public b i;
    public int j;
    public String k;
    public String l;
    public boolean m;
    public int n;
    public int o;
    public Properties p;
    public short[] q;
    public boolean r;
    public boolean s;
    final Rect t = new Rect();
    final Rect u = new Rect();
    final RectF v = new RectF();
    public boolean w;

    public final g a(int n2, int n3) {
        if (this.q == null) {
            this.q = new short[this.n * this.o];
        }
        return this.i.a(this.q[n2 * this.o + n3]);
    }

    public short[] a() {
        if (this.q == null) {
            this.q = new short[this.n * this.o];
        }
        return this.q;
    }

    public void a(int n2, int n3, g g2, boolean bl) {
        if (this.q == null) {
            this.q = new short[this.n * this.o];
        }
        if (g2 == null) {
            this.q[n2 * this.o + n3] = 0;
            return;
        }
        if (bl) {
            g2 = this.i.a(g2, n2, n3);
        }
        if (g2.i) {
            boolean bl2 = false;
            for (Point point : this.i.A) {
                if (point.a != n2 || point.b != n3) continue;
                com.corrodinggames.rts.gameFramework.l.e("resPools point:" + n2 + ", " + n3 + " already exists");
                bl2 = true;
            }
            if (!bl2) {
                this.i.A.add(new Point(n2, n3));
            }
        }
        if (g2.d == -1) {
            g2.d = this.i.a(g2);
        }
        this.q[n2 * this.o + n3] = g2.d;
    }

    public void a(y y2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean bl, boolean bl2, boolean bl3) {
        ag ag2;
        boolean bl4;
        int n2;
        int n3;
        int n4;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = this.i;
        int n5 = (int)(f4 * b2.r);
        if (n5 < 0) {
            n5 = 0;
        }
        if ((n4 = (int)(f5 * b2.s)) < 0) {
            n4 = 0;
        }
        if ((n3 = (int)((f4 + f6) * b2.r)) > this.n - 1) {
            n3 = this.n - 1;
        }
        if ((n2 = (int)((f5 + f7) * b2.s)) > this.o - 1) {
            n2 = this.o - 1;
        }
        byte[][] byArray = l2.bs.N;
        float f10 = f2 * f8;
        float f11 = f3 * f9;
        float f12 = (float)b2.n * f8;
        float f13 = (float)b2.o * f9;
        boolean bl5 = true;
        byte by = 15;
        if (!bl2) {
            by = 10;
        }
        if (bl4 = b2.G) {
            by = 15;
        }
        if (bl && byArray == null) {
            bl = false;
        }
        ag ag3 = b[5];
        ag ag4 = a;
        ag ag5 = c;
        ag5.c(255);
        if (bl4) {
            ag4 = b[7];
            float f14 = 1.0f - (1.0f - (float)ag3.f() / 255.0f) * (1.0f - (float)ag4.f() / 255.0f);
            ag5.c((int)(f14 * 255.0f));
        }
        boolean bl6 = false;
        if (com.corrodinggames.rts.gameFramework.l.av() && f8 < 1.0f && f9 < 1.0f) {
            bl6 = true;
        }
        if (bl3) {
            // empty if block
        }
        if (!this.r) {
            ag2 = f;
            if (bl6) {
                ag2 = g;
            }
        } else {
            ag2 = d;
            if (bl6) {
                ag2 = e;
            }
        }
        ag ag6 = ag2;
        float f15 = 0.0f;
        float f16 = 0.0f;
        boolean bl7 = false;
        if (!com.corrodinggames.rts.gameFramework.l.av()) {
            bl7 = true;
        } else if (!bl3) {
            if (f8 < 1.0f || f9 < 1.0f) {
                f15 = 0.5f * f8;
            }
        } else if (f8 < 1.0f || f9 < 1.0f) {
            // empty if block
        }
        h h2 = f8 < 0.5f ? com.corrodinggames.rts.game.b.b.m : com.corrodinggames.rts.game.b.b.l;
        short[] sArray = this.a();
        g[] gArray = b2.B;
        RectF rectF = this.v;
        Rect rect = this.u;
        int n6 = this.o;
        boolean bl8 = this.r;
        Rect rect2 = this.t;
        b2.l();
        byte[][] byArray2 = b2.M;
        byte[][] byArray3 = b2.N;
        com.corrodinggames.rts.gameFramework.m.e e2 = com.corrodinggames.rts.game.b.b.K;
        for (int i2 = n5; i2 < n3 + 1; ++i2) {
            for (int i3 = n4; i3 < n2 + 1; ++i3) {
                com.corrodinggames.rts.gameFramework.m.e e3;
                Object object;
                short s2 = sArray[i2 * n6 + i3];
                g g2 = gArray[s2];
                if (g2 == null) continue;
                byte by2 = 0;
                if (bl) {
                    by2 = byArray[i2][i3];
                }
                if (by2 == by) continue;
                float f17 = (float)i2 * f12 + f16;
                float f18 = (float)i3 * f13 + f16;
                float f19 = (float)(i2 + 1) * f12 + f15;
                float f20 = (float)(i3 + 1) * f13 + f15;
                rectF.a(f17 - f10, f18 - f11, f19 - f10, f20 - f11);
                if (bl6 && !bl3) {
                    rectF.b = (int)rectF.b;
                    rectF.a = (int)rectF.a;
                }
                if (!bl3) {
                    object = g2.a;
                    if (!bl7) {
                        if (g2.c >= 0) {
                            Rect rect3 = h2.b(g2.c);
                            e3 = h2.a(g2.c);
                            y2.a(e3, rect3, rectF, (Paint)ag6);
                        } else {
                            g2.a(y2, rectF, f8, ag6);
                        }
                    } else {
                        rect.a((int)(f17 - f10), (int)(f18 - f11), (int)(f19 - f10), (int)(f20 - f11));
                        if (g2.c >= 0) {
                            Rect rect4 = h2.b(g2.c);
                            e3 = h2.a(g2.c);
                            y2.b(e3, rect4, rect, (Paint)ag6);
                        } else {
                            Rect rect5 = ((j)object).b(g2.b);
                            y2.a(((j)object).b, rect5, rect, (Paint)ag6);
                        }
                    }
                }
                if (!bl || !bl8 || !bl2 || by2 == 0 && byArray3[i2][i3] == 0 && byArray2[i2][i3] == 0) continue;
                if (by2 >= 5) {
                    if (bl3 && (by2 == 10 || byArray2[i2][i3] == 0)) {
                        byte by3;
                        int n7;
                        for (n7 = i3 + 1; n7 < n2 && by2 == (by3 = byArray[i2][n7]) && (by2 == 10 || byArray2[i2][n7] == 0); ++n7) {
                        }
                        if (--n7 > i3) {
                            rectF.d += (float)(n7 - i3) * f13;
                            i3 = n7;
                        }
                    }
                    object = by2 == 10 ? ag5 : ag3;
                    rect.a = (int)rectF.a;
                    rect.c = (int)rectF.c;
                    rect.b = (int)rectF.b;
                    rect.d = (int)rectF.d;
                    y2.a(rect, (Paint)object);
                } else {
                    byte by4 = byArray3[i2][i3];
                    if (by4 == 127) {
                        byArray3[i2][i3] = by4 = b2.a(i2, i3, byArray, (byte)5);
                    }
                    if (by4 != 0) {
                        int n8 = by4 + 128;
                        e3 = e2;
                        if (e3 != null) {
                            com.corrodinggames.rts.game.b.b.a(n8, rect2);
                            rect.a((int)(f17 - f10), (int)(f18 - f11), (int)(f19 - f10), (int)(f20 - f11));
                            y2.b(e3, rect2, rect, (Paint)ag3);
                        } else if (!b2.k[by4 + 128]) {
                            com.corrodinggames.rts.gameFramework.l.e("SmoothFog, missing: " + by4);
                            b2.k[by4 + 128] = true;
                        }
                    }
                }
                if (by2 == 10) continue;
                byte by5 = byArray2[i2][i3];
                if (by5 == 127) {
                    byArray2[i2][i3] = by5 = b2.a(i2, i3, byArray, (byte)10);
                }
                if (by5 == 0) continue;
                int n9 = by5 + 128;
                e3 = e2;
                if (e3 != null) {
                    com.corrodinggames.rts.game.b.b.a(n9, rect2);
                    rect.a((int)(f17 - f10), (int)(f18 - f11), (int)(f19 - f10), (int)(f20 - f11));
                    y2.b(e3, rect2, rect, (Paint)ag4);
                    continue;
                }
                if (b2.k[by5 + 128]) continue;
                com.corrodinggames.rts.gameFramework.l.e("SmoothFog, missing: " + by5);
                b2.k[by5 + 128] = true;
            }
        }
    }

    public void b() {
        this.q = null;
        this.p = null;
        this.i = null;
    }

    public e(b b2, String string, int n2, int n3) {
        this.i = b2;
        this.a(string);
        this.n = n2;
        this.o = n3;
        this.a();
    }

    void a(String string) {
        this.k = string;
        Log.d("RustedWarfare", "MapLayer create: " + string);
        if (string != null) {
            this.l = string.toLowerCase(Locale.ENGLISH);
        }
        this.m = this.l.contains("items");
        this.r = this.l.equalsIgnoreCase("ground");
        if (this.m || this.r) {
            this.s = true;
        }
        if (string != null && string.equalsIgnoreCase("grounddetails")) {
            this.s = true;
        }
    }

    public e(b b2, Element element) {
        String string;
        Object object;
        Object object2;
        Object object3;
        this.i = b2;
        this.a(element.getAttribute("name"));
        this.n = Short.parseShort(element.getAttribute("width"));
        this.o = Short.parseShort(element.getAttribute("height"));
        Element element2 = (Element)element.getElementsByTagName("properties").item(0);
        if (element2 != null && (object3 = element2.getElementsByTagName("property")) != null) {
            this.p = new Properties();
            for (int i2 = 0; i2 < object3.getLength(); ++i2) {
                object2 = (Element)object3.item(i2);
                object = object2.getAttribute("name");
                string = object2.getAttribute("value");
                this.p.setProperty((String)object, string);
            }
        }
        if ((object3 = (Element)element.getElementsByTagName("data").item(0)) == null) {
            throw new f("Map is missing <data> element");
        }
        String string2 = object3.getAttribute("encoding");
        object2 = object3.getAttribute("compression");
        try {
            object = object3.getFirstChild();
            string = object.getNodeValue();
            InputStream inputStream = com.corrodinggames.rts.game.b.e.a(string, string2, (String)object2);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            this.a(bufferedInputStream);
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (IOException iOException) {
            throw new f("Unable to decompress base64 block", iOException);
        }
    }

    void a(InputStream inputStream) {
        b b2 = this.i;
        g g2 = null;
        int n2 = -1;
        boolean bl = this.s;
        HashMap<Integer, g> hashMap = new HashMap<Integer, g>();
        for (short s2 = 0; s2 < this.o; s2 = (short)((short)(s2 + 1))) {
            for (short s3 = 0; s3 < this.n; s3 = (short)((short)(s3 + 1))) {
                int n3 = 0;
                n3 |= inputStream.read();
                n3 |= inputStream.read() << 8;
                n3 |= inputStream.read() << 16;
                boolean bl2 = ((n3 |= inputStream.read() << 24) & Integer.MIN_VALUE) != 0;
                boolean bl3 = (n3 & 0x40000000) != 0;
                boolean bl4 = (n3 & 0x20000000) != 0;
                n3 &= 0x1FFFFFFF;
                if (bl2 || bl3 || bl4) {
                    // empty if block
                }
                if (n3 == 0) continue;
                if (n2 == n3 && g2 != null) {
                    this.a(s3, s2, g2, true);
                    continue;
                }
                g g3 = (g)hashMap.get(n3);
                if (g3 != null) {
                    g2 = g3;
                    n2 = n3;
                    this.a(s3, s2, g2, true);
                    continue;
                }
                j j2 = b2.a(n3);
                if (j2 != null) {
                    g2 = com.corrodinggames.rts.game.b.g.a(b2, this, j2, n3 - j2.l, s3, s2, bl);
                    if (g2 != null) {
                        this.a(s3, s2, g2, true);
                        hashMap.put(n3, g2);
                    }
                    n2 = n3;
                    continue;
                }
                throw new f("Unable to decode base64 block, could not find tileId: " + n3);
            }
        }
    }

    public static InputStream a(String string, String string2, String string3) {
        if (!string2.equals("base64")) {
            throw new f("Unsupport tiled map encoding: " + string2 + "," + string3 + " (only gzip base64 is supported, this can be set in Tiled's Preferences)");
        }
        Object object = string.toCharArray();
        byte[] byArray = com.corrodinggames.rts.game.b.e.a(object);
        if ("gzip".equals(string3)) {
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(byArray));
                object = gZIPInputStream;
            }
            catch (IOException iOException) {
                throw new f("Unable to decode block in map", iOException);
            }
        } else if ("".equals(string3)) {
            object = new ByteArrayInputStream(byArray);
        } else if ("zlib".equals(string3)) {
            InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(byArray));
            object = inflaterInputStream;
        } else {
            throw new f("Unsupport tiled map compression: " + string2 + "," + string3 + " (only gzip base64 is supported, this can be set in Tiled's Preferences)");
        }
        return object;
    }

    public static byte[] a(char[] cArray) {
        int n2;
        int n3 = cArray.length;
        byte[] byArray = x;
        for (n2 = 0; n2 < cArray.length; ++n2) {
            if (cArray[n2] <= '\u00ff' && byArray[cArray[n2]] >= 0) continue;
            --n3;
        }
        n2 = n3 / 4 * 3;
        if (n3 % 4 == 3) {
            n2 += 2;
        }
        if (n3 % 4 == 2) {
            ++n2;
        }
        byte[] byArray2 = new byte[n2];
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i2 = 0; i2 < cArray.length; ++i2) {
            int n7;
            int n8 = n7 = cArray[i2] > '\u00ff' ? -1 : byArray[cArray[i2]];
            if (n7 < 0) continue;
            n5 <<= 6;
            n5 |= n7;
            if ((n4 += 6) < 8) continue;
            byArray2[n6++] = (byte)(n5 >> (n4 -= 8) & 0xFF);
        }
        if (n6 != byArray2.length) {
            throw new RuntimeException("Data length appears to be wrong (wrote " + n6 + " should be " + byArray2.length + ")");
        }
        return byArray2;
    }

    static {
        int n2;
        x = new byte[256];
        for (n2 = 0; n2 < 256; ++n2) {
            com.corrodinggames.rts.game.b.e.x[n2] = -1;
        }
        for (n2 = 65; n2 <= 90; ++n2) {
            com.corrodinggames.rts.game.b.e.x[n2] = (byte)(n2 - 65);
        }
        for (n2 = 97; n2 <= 122; ++n2) {
            com.corrodinggames.rts.game.b.e.x[n2] = (byte)(26 + n2 - 97);
        }
        for (n2 = 48; n2 <= 57; ++n2) {
            com.corrodinggames.rts.game.b.e.x[n2] = (byte)(52 + n2 - 48);
        }
        com.corrodinggames.rts.game.b.e.x[43] = 62;
        com.corrodinggames.rts.game.b.e.x[47] = 63;
        a = new ag();
        a.b(-16777216);
        a.a(Paint$Style.a);
        b = new ag[11];
        for (n2 = 0; n2 <= 10; ++n2) {
            com.corrodinggames.rts.game.b.e.b[n2] = new ag();
            b[n2].b(-16777216);
            b[n2].a(Paint$Style.a);
            b[n2].c(n2 * 25);
        }
        c = new ag();
        c.b(-16777216);
        c.a(Paint$Style.a);
        d = new ag();
        d.a(false);
        d.d(false);
        d.b(false);
        e = new ag();
        e.a(true);
        f = new ag();
        f.a(false);
        f.d(false);
        f.b(false);
        g = new ag();
        g.a(true);
        h = new ag[11];
        for (n2 = 0; n2 <= 10; ++n2) {
            ag ag2 = new ag();
            ag2.a(new LightingColorFilter(Color.a(255 - n2 * 25, 255 - n2 * 25, 255 - n2 * 25), 0));
            com.corrodinggames.rts.game.b.e.h[n2] = ag2;
        }
    }
}
