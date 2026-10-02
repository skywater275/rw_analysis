/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;
import com.corrodinggames.rts.game.units.PathState;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.map.TileDrawer;
import com.corrodinggames.rts.game.map.TilesetDef;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
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

public class TMXMapLoader {
    private static byte[] x;
    static UniquePaint layerIndex;  // 02b b/e.java L37: ag a
    static UniquePaint[] tileWidth;
    static UniquePaint tileHeight;  // 02b L39: ag c
    static UniquePaint mapWidth;  // 02b L40: ag d
    static UniquePaint mapHeight;  // 02b L41: ag e
    static UniquePaint nextObjectId;  // 02b L42: ag f
    static UniquePaint currentTileset;  // 02b L43: ag g
    static UniquePaint[] currentLayer;
    public MapEngine orientation;
    public int renderOrder;
    public String backgroundColor;
    public String basePath;
    public boolean encoding;
    public int n;
    public int o;
    public Properties p;
    public short[] q;
    public String l;  // 02b b/e.java: String l (图层名)
    public boolean r;
    public boolean s;
    final Rect t = new Rect();
    final Rect u = new Rect();
    final RectF v = new RectF();
    public boolean w;

    public final MapLayer a(int n2, int n3) {
        if (this.q == null) {
            this.q = new short[this.n * this.o];
        }
        return this.orientation.a(this.q[n2 * this.o + n3]);
    }

    public short[] a() {
        if (this.q == null) {
            this.q = new short[this.n * this.o];
        }
        return this.q;
    }

    public void a(int n2, int n3, MapLayer g2, boolean bl) {
        if (this.q == null) {
            this.q = new short[this.n * this.o];
        }
        if (g2 == null) {
            this.q[n2 * this.o + n3] = 0;
            return;
        }
        if (bl) {
            g2 = this.orientation.a(g2, n2, n3);
        }
        if (g2.isTileLayer) {  // 02b b/g.i
            boolean bl2 = false;
            for (Object object2 : this.orientation.A) {
                Point point = (Point) object2;
                if (point.a != n2 || point.b != n3) continue;
                com.corrodinggames.rts.gameFramework.GlobalState.e("resPools point:" + n2 + ", " + n3 + " already exists");
                bl2 = true;
            }
            if (!bl2) {
                this.orientation.A.add(new Point(n2, n3));
            }
        }
        if (g2.layerOpacity == -1) {
            g2.layerOpacity = this.orientation.a(g2);
        }
        this.q[n2 * this.o + n3] = g2.layerOpacity;
    }

    public void a(TextureManagerInterface y2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean bl, boolean bl2, boolean bl3) {
        UniquePaint ag2;
        boolean bl4;
        int n2;
        int n3;
        int n4;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = this.orientation;
        int n5 = (int)(f4 * b2.float1);
        if (n5 < 0) {
            n5 = 0;
        }
        if ((n4 = (int)(f5 * b2.float2)) < 0) {
            n4 = 0;
        }
        if ((n3 = (int)((f4 + f6) * b2.float1)) > this.n - 1) {
            n3 = this.n - 1;
        }
        if ((n2 = (int)((f5 + f7) * b2.float2)) > this.o - 1) {
            n2 = this.o - 1;
        }
        byte[][] byArray = l2.bs.N;
        float f10 = f2 * f8;
        float f11 = f3 * f9;
        float f12 = (float)b2.tilePixelWidth * f8;
        float f13 = (float)b2.tilePixelHeight * f9;
        boolean bl5 = true;
        byte by = 15;
        if (!bl2) {
            by = 10;
        }
        if (bl4 = b2.visibilityGrid) {
            by = 15;
        }
        if (bl && byArray == null) {
            bl = false;
        }
        UniquePaint ag3 = tileWidth[5];  // 02b: b[5]
        UniquePaint ag4 = layerIndex;
        UniquePaint ag5 = tileHeight;
        ag5.c(255);
        if (bl4) {
            ag4 = tileWidth[7];
            float f14 = 1.0f - (1.0f - (float)ag3.f() / 255.0f) * (1.0f - (float)ag4.f() / 255.0f);
            ag5.c((int)(f14 * 255.0f));
        }
        boolean bl6 = false;
        if (com.corrodinggames.rts.gameFramework.GlobalState.av() && f8 < 1.0f && f9 < 1.0f) {
            bl6 = true;
        }
        if (bl3) {
            // empty if block
        }
        if (!this.r) {
            ag2 = nextObjectId;
            if (bl6) {
                ag2 = currentTileset;
            }
        } else {
            ag2 = mapWidth;
            if (bl6) {
                ag2 = mapHeight;
            }
        }
        UniquePaint ag6 = ag2;
        float f15 = 0.0f;
        float f16 = 0.0f;
        boolean bl7 = false;
        if (!com.corrodinggames.rts.gameFramework.GlobalState.av()) {
            bl7 = true;
        } else if (!bl3) {
            if (f8 < 1.0f || f9 < 1.0f) {
                f15 = 0.5f * f8;
            }
        } else if (f8 < 1.0f || f9 < 1.0f) {
            // empty if block
        }
        TileDrawer h2 = f8 < 0.5f ? com.corrodinggames.rts.game.map.MapEngine.tileRendererB : com.corrodinggames.rts.game.map.MapEngine.l;  // 02b L180: b.m/b.l
        short[] sArray = this.a();  // 02b L70: a()
        MapLayer[] gArray = b2.mapWidth;
        RectF rectF = this.v;
        Rect rect = this.u;
        int n6 = this.o;
        boolean bl8 = this.r;
        Rect rect2 = this.t;
        b2.rebuildFogCache();
        byte[][] byArray2 = b2.fogCacheBufferA;
        byte[][] byArray3 = b2.fogCacheBufferB;
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = com.corrodinggames.rts.game.map.MapEngine.layerList;  // 02b b.b.K
        for (int i2 = n5; i2 < n3 + 1; ++i2) {
            for (int i3 = n4; i3 < n2 + 1; ++i3) {
                com.corrodinggames.rts.gameFramework.rendering.Texture e3;
                Object object;
                short s2 = sArray[i2 * n6 + i3];
                MapLayer g2 = gArray[s2];
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
                    object = g2.tilesetDef;  // 02b b/g.a
                    if (!bl7) {
                        if (g2.layerHeight >= 0) {
                            Rect rect3 = h2.b(g2.layerHeight);
                            e3 = h2.a(g2.layerHeight);
                            y2.a(e3, rect3, rectF, (Paint)ag6);
                        } else {
                            g2.a(y2, rectF, f8, ag6);
                        }
                    } else {
                        rect.a((int)(f17 - f10), (int)(f18 - f11), (int)(f19 - f10), (int)(f20 - f11));
                        if (g2.layerHeight >= 0) {
                            Rect rect4 = h2.b(g2.layerHeight);
                            e3 = h2.a(g2.layerHeight);
                            y2.b(e3, rect4, rect, (Paint)ag6);
                        } else {
                            Rect rect5 = ((TilesetDef) object).b(g2.layerWidth);
                            y2.a(((TilesetDef) object).tilesetTexture, rect5, rect, (Paint)ag6);
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
                    y2.clearScreen(rect, (Paint)object);
                } else {
                    byte by4 = byArray3[i2][i3];
                    if (by4 == 127) {
                        byArray3[i2][i3] = by4 = b2.a(i2, i3, byArray, (byte)5);
                    }
                    if (by4 != 0) {
                        int n8 = by4 + 128;
                        e3 = e2;
                        if (e3 != null) {
                            com.corrodinggames.rts.game.map.MapEngine.a(n8, rect2);
                            rect.a((int)(f17 - f10), (int)(f18 - f11), (int)(f19 - f10), (int)(f20 - f11));
                            y2.b(e3, rect2, rect, (Paint)ag3);
                        } else if (!b2.k[by4 + 128]) {
                            com.corrodinggames.rts.gameFramework.GlobalState.e("SmoothFog, missing: " + by4);
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
                    com.corrodinggames.rts.game.map.MapEngine.a(n9, rect2);
                    rect.a((int)(f17 - f10), (int)(f18 - f11), (int)(f19 - f10), (int)(f20 - f11));
                    y2.b(e3, rect2, rect, (Paint)ag4);
                    continue;
                }
                if (b2.k[by5 + 128]) continue;
                com.corrodinggames.rts.gameFramework.GlobalState.e("SmoothFog, missing: " + by5);
                b2.k[by5 + 128] = true;
            }
        }
    }

    public void b() {
        this.q = null;
        this.p = null;
        this.orientation = null;
    }

    /* 字节码 b/e.class 4参构造器方法体含 throw f(MapException) 铁证 (R8 移除 throws) */
    public TMXMapLoader(MapEngine b2, String string, int n2, int n3) throws MapException {
        this.orientation = b2;
        this.a(string);
        this.n = n2;
        this.o = n3;
        this.a();
    }

    void a(String string) {
        this.backgroundColor = string;
        Log.d("RustedWarfare", "MapLayer create: " + string);
        if (string != null) {
            this.basePath = string.toLowerCase(Locale.ENGLISH);
        }
        this.encoding = this.basePath.contains("items");
        this.r = this.basePath.equalsIgnoreCase("ground");
        if (this.encoding || this.r) {
            this.s = true;
        }
        if (string != null && string.equalsIgnoreCase("grounddetails")) {
            this.s = true;
        }
    }

    public TMXMapLoader(MapEngine b2, Element element) throws MapException, IOException {
        String string;
        Object object;
        Object object2;
        this.orientation = b2;
        this.a(element.getAttribute("name"));
        this.n = Short.parseShort(element.getAttribute("width"));
        this.o = Short.parseShort(element.getAttribute("height"));
        Element element2 = (Element)element.getElementsByTagName("properties").item(0);
        if (element2 != null) {
            org.w3c.dom.NodeList nodeList = element2.getElementsByTagName("property");
            if (nodeList != null) {
                this.p = new Properties();
                for (int i2 = 0; i2 < nodeList.getLength(); ++i2) {
                    object2 = (Element)nodeList.item(i2);
                    object = ((Element)object2).getAttribute("name");
                    string = ((Element)object2).getAttribute("value");
                    this.p.setProperty((String)object, string);
                }
            }
        }
        Element element3 = (Element)element.getElementsByTagName("data").item(0);
        if (element3 == null) {
            throw new MapException("Map is missing <data> element");
        }
        String string2 = element3.getAttribute("encoding");
        object2 = element3.getAttribute("compression");
        try {
            org.w3c.dom.Node node = element3.getFirstChild();
            string = node.getNodeValue();
            InputStream inputStream = TMXMapLoader.a(string, string2, (String)object2);
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
            throw new MapException("Unable to decompress base64 block", iOException);
        }
    }

    void a(InputStream inputStream) throws MapException, IOException {
        MapEngine b2 = this.orientation;
        MapLayer g2 = null;
        int n2 = -1;
        boolean bl = this.s;
        HashMap<Integer, com.corrodinggames.rts.game.map.MapLayer> hashMap = new HashMap<Integer, com.corrodinggames.rts.game.map.MapLayer>();  // 02b raw (g=MapLayer)
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
                MapLayer g3 = (MapLayer) hashMap.get(n3);
                if (g3 != null) {
                    g2 = g3;
                    n2 = n3;
                    this.a(s3, s2, g2, true);
                    continue;
                }
                TilesetDef j2 = b2.a(n3);
                if (j2 != null) {
                    g2 = com.corrodinggames.rts.game.map.MapLayer.a(b2, this, j2, n3 - j2.columns, s3, s2, bl);
                    if (g2 != null) {
                        this.a(s3, s2, g2, true);
                        hashMap.put(n3, g2);
                    }
                    n2 = n3;
                    continue;
                }
                throw new MapException("Unable to decode base64 block, could not find tileId: " + n3);
            }
        }
    }

    /* 02b e.java L481: 仅抛 f; IOException 已内部 catch 消化 → 去过度 IOException */
    public static InputStream a(String string, String string2, String string3) throws MapException {
        if (!string2.equals("base64")) {
            throw new MapException("Unsupport tiled map encoding: " + string2 + "," + string3 + " (only gzip base64 is supported, this can be set in Tiled's Preferences)");
        }
        char[] cArray = string.toCharArray();
        byte[] byArray = TMXMapLoader.a(cArray);
        InputStream inputStream;
        if ("gzip".equals(string3)) {
            try {
                inputStream = new GZIPInputStream(new ByteArrayInputStream(byArray));
            }
            catch (IOException iOException) {
                throw new MapException("Unable to decode block in map", iOException);
            }
        } else if ("".equals(string3)) {
            inputStream = new ByteArrayInputStream(byArray);
        } else if ("zlib".equals(string3)) {
            inputStream = new InflaterInputStream(new ByteArrayInputStream(byArray));
        } else {
            throw new MapException("Unsupport tiled map compression: " + string2 + "," + string3 + " (only gzip base64 is supported, this can be set in Tiled's Preferences)");
        }
        return inputStream;
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
            x[n2] = -1;
        }
        for (n2 = 65; n2 <= 90; ++n2) {
            x[n2] = (byte)(n2 - 65);
        }
        for (n2 = 97; n2 <= 122; ++n2) {
            x[n2] = (byte)(26 + n2 - 97);
        }
        for (n2 = 48; n2 <= 57; ++n2) {
            x[n2] = (byte)(52 + n2 - 48);
        }
        x[43] = 62;
        x[47] = 63;
        layerIndex = new UniquePaint();
        layerIndex.b(-16777216);  // 02b: ag a
        layerIndex.a(Paint$Style.a);
        tileWidth = new UniquePaint[11];
        for (n2 = 0; n2 <= 10; ++n2) {
            tileWidth[n2] = new UniquePaint();
            tileWidth[n2].b(-16777216);
            tileWidth[n2].a(Paint$Style.a);
            tileWidth[n2].c(n2 * 25);
        }
        tileHeight = new UniquePaint();
        tileHeight.b(-16777216);  // 02b: ag c
        tileHeight.a(Paint$Style.a);
        mapWidth = new UniquePaint();
        mapWidth.a(false);  // 02b: ag d
        mapWidth.d(false);
        mapWidth.b(false);
        mapHeight = new UniquePaint();
        mapHeight.a(true);  // 02b: ag e
        nextObjectId = new UniquePaint();
        nextObjectId.a(false);  // 02b: ag f
        nextObjectId.d(false);
        nextObjectId.b(false);
        currentTileset = new UniquePaint();
        currentTileset.a(true);  // 02b: ag g
        currentLayer = new UniquePaint[11];
        for (n2 = 0; n2 <= 10; ++n2) {
            UniquePaint ag2 = new UniquePaint();
            ag2.a(new LightingColorFilter(Color.a(255 - n2 * 25, 255 - n2 * 25, 255 - n2 * 25), 0));
            currentLayer[n2] = ag2;  // 02b: ag[] h
        }
    }
}