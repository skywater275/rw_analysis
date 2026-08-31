/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.res.AssetFileDescriptor
 *  android.graphics.PorterDuff$Mode
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.game.map;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

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
import com.corrodinggames.rts.game.map.MapSpawn;
import com.corrodinggames.rts.game.map.MapRenderer;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.map.MapException;
import javax.xml.transform.TransformerConfigurationException;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.map.TileDrawer;
import com.corrodinggames.rts.game.map.MapLayerDef;
import com.corrodinggames.rts.game.map.TilesetDef;
import com.corrodinggames.rts.game.Projectile;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.DequeList;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

public strictfp final class MapEngine {
    public int T;  // 02b b.b.java L112
    public int U;  // 02b b.b.java L113
    public ArrayList A;
    static final boolean a = false;
    static final boolean b = false;
    static final boolean c = false;
    public static boolean d = false;
    static ReentrantLock mapLock = new ReentrantLock();
    static boolean f;
    static Paint g;
    static Paint h;
    static Paint i;
    static Paint j;
    boolean[] k = new boolean[256];
    public static TileDrawer l;
    public static TileDrawer tileRendererB;
    public int tilePixelWidth = 20;
    public int tilePixelHeight = 20;
    public int selectedTileX;
    public int selectedTileY;
    public float float1;
    public float float2;
    public ArrayList tilesets = new ArrayList();
    public TMXMapLoader groundLayer = null;
    public TMXMapLoader groundDetailsLayer = null;
    public TMXMapLoader groundDetails2Layer = null;
    public TMXMapLoader pathingOverrideLayer;
    public TMXMapLoader itemsLayer = null;
    public ArrayList layers = new ArrayList();
    public ArrayList resourcePoints = new ArrayList();
    private int renderMode = 1;
    public MapLayer[] mapWidth = new MapLayer[0];
    public int mapHeight;
    public boolean R;  // 02b b/b.java L110 (鍦板浘缂栬緫鏍囧織)
    public boolean S;  // 02b L111
    public boolean W;  // 02b b/b.java L115 (鍦板浘鍔犺浇瀹屾垚鏍囧織; GameLauncher$3 寮曠敤)
    public boolean X;  // 02b L116

    public int tileWidth;
    public boolean tileHeight = true;
    public boolean fogGrid = false;
    public boolean visibilityGrid = false;
    public static boolean blockingGrid;
    public static boolean buildBlockingGrid;
    public static boolean tilesetList;
    public static com.corrodinggames.rts.gameFramework.rendering.Texture layerList;
    public static com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface tilesetTexture;
    public byte[][] fogCacheBufferA;
    public byte[][] fogCacheBufferB;
    Rect O = new Rect();
    protected ArrayList objectGroups = new ArrayList();
    public MapLayerDef minimapRenderer;
    public boolean placementCheck = true;  // 02b b/b.java L97 E (放置网格检查; ExperimentalUnit 引用)
    public boolean ignorePlacementCheck = false;  // 02b b/b.java L99 G (跳过放置检查)
    public boolean showGridOverlay;
    public boolean showFogOverlay;
    public int scrollPixelX;
    public int scrollPixelY;
    public PointF cameraFocus = new PointF();
    public boolean cameraLocked;
    public boolean fogDirty;
    public int visibleTilesX;
    public int visibleTilesY;
    float aa = 0.0f;
    Paint ab;
    Paint ac;
    Paint ad;
    Paint ae;
    Paint af;
    Paint ag;
    HashMap tileCache;
    float ai;
    float aj = 1.0f;
    int ak = 0;
    public static MapRenderer commandProcessor;
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
        mapLock.lock();
    }

    public static void b() {
        if (f) {
            return;
        }
        mapLock.unlock();
    }

    public static void c() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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
        long l3 = ExtraManager.a();
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.loadImageFromResource(R$drawable.fog_smooth);
        int n2 = 20;
        int n3 = 20;
        int n4 = 1;
        layerList = l2.bO.b((n2 + 2) * 16 + 1, (n3 + 2) * 16 + 1, true);
        layerList.m = true;
        layerList.setKeepInGpuMemory(true);
        tilesetTexture = l2.bO.b(layerList);
        com.corrodinggames.rts.gameFramework.rendering.Texture e3 = l2.bO.b(n2 + n4, n3 + n4, true);
        com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2 = l2.bO.b(e3);
        int n5 = 1;
        int n6 = 2;
        int n7 = 4;
        int n8 = 8;
        int n9 = 16;
        int n10 = 32;
        int n11 = 64;
        int n12 = -128;
        a(b(n5), 2, 5, true, e3, y2, e2);
        a(b(n6), 0, 5, true, e3, y2, e2);
        a(b(n7), 0, 3, true, e3, y2, e2);
        a(b(n8), 2, 3, true, e3, y2, e2);
        a(a(n9, new int[]{n5, n6}), 1, 0, true, e3, y2, e2);
        a(a(n10, new int[]{n6, n7}), 2, 1, true, e3, y2, e2);
        a(a(n11, new int[]{n8, n7}), 1, 2, true, e3, y2, e2);
        a(a(n12, new int[]{n5, n8}), 0, 1, true, e3, y2, e2);
        a(a(n9 + n10, new int[]{n6, n5, n7}), 2, 0, true, e3, y2, e2);
        a(a(n10 + n11, new int[]{n7, n8, n6}), 2, 2, true, e3, y2, e2);
        a(a(n11 + n12, new int[]{n8, n7, n5}), 0, 2, true, e3, y2, e2);
        a(a(n12 + n9, new int[]{n5, n8, n6}), 0, 0, true, e3, y2, e2);
        a(b(n5 + n6), a(new int[]{2, 5, 0, 5}), true, e3, y2, e2);
        a(b(n6 + n7), a(new int[]{0, 5, 0, 3}), true, e3, y2, e2);
        a(b(n7 + n8), a(new int[]{0, 3, 2, 3}), true, e3, y2, e2);
        a(b(n8 + n5), a(new int[]{2, 3, 2, 5}), true, e3, y2, e2);
        a(a(n9 + n10 + n11, new int[]{n5, n6, n7, n8}), a(new int[]{2, 0, 2, 2}), true, e3, y2, e2);
        a(a(n10 + n11 + n12, new int[]{n5, n6, n7, n8}), a(new int[]{2, 2, 0, 2}), true, e3, y2, e2);
        a(a(n11 + n12 + n9, new int[]{n5, n6, n7, n8}), a(new int[]{0, 2, 0, 0}), true, e3, y2, e2);
        a(a(n12 + n9 + n10, new int[]{n5, n6, n7, n8}), a(new int[]{0, 0, 2, 0}), true, e3, y2, e2);
        a(b(n9 + n11), a(new int[]{1, 0, 1, 2}), true, e3, y2, e2);
        a(b(n12 + n10), a(new int[]{0, 1, 2, 1}), true, e3, y2, e2);
        a(b(n5 + n7), a(new int[]{2, 5, 0, 3}), true, e3, y2, e2);
        a(b(n6 + n8), a(new int[]{0, 5, 2, 3}), true, e3, y2, e2);
        a(a(n9 + n7, new int[]{n6, n5}), a(new int[]{1, 0, 0, 3}), true, e3, y2, e2);
        a(a(n11 + n6, new int[]{n7, n8}), a(new int[]{1, 2, 0, 5}), true, e3, y2, e2);
        a(a(n12 + n6, new int[]{n5, n8}), a(new int[]{0, 1, 0, 5}), true, e3, y2, e2);
        a(a(n10 + n5, new int[]{n6, n7}), a(new int[]{2, 1, 2, 5}), true, e3, y2, e2);
        a(a(n9 + n8, new int[]{n6, n5}), a(new int[]{1, 0, 2, 3}), true, e3, y2, e2);
        a(a(n11 + n5, new int[]{n7, n8}), a(new int[]{1, 2, 2, 5}), true, e3, y2, e2);
        a(a(n12 + n7, new int[]{n5, n8}), a(new int[]{0, 1, 0, 3}), true, e3, y2, e2);
        a(a(n10 + n8, new int[]{n6, n7}), a(new int[]{2, 1, 2, 3}), true, e3, y2, e2);
        a(a(n9 + n7 + n8, new int[]{n6, n5}), a(new int[]{1, 0, 0, 3, 2, 3}), true, e3, y2, e2);
        a(a(n11 + n6 + n5, new int[]{n7, n8}), a(new int[]{1, 2, 0, 5, 2, 5}), true, e3, y2, e2);
        a(a(n12 + n6 + n7, new int[]{n5, n8}), a(new int[]{0, 1, 2, 5, 2, 3}), true, e3, y2, e2);
        a(a(n10 + n5 + n8, new int[]{n6, n7}), a(new int[]{2, 1, 0, 5, 0, 3}), true, e3, y2, e2);
        a(b(-1), a(new int[]{1, 4}), true, e3, y2, e2);
        tilesetTexture.p();
        tilesetTexture.q();
        tilesetTexture = null;
        y2.q();
        y2 = null;
        ExtraManager.a("smoothFog load took:", l3);
        d();
        l = new TileDrawer(1.0f, false);
        l.a();
        tileRendererB = new TileDrawer(0.5f, false);
        tileRendererB.a();
    }

    public static void d() {
        if (blockingGrid) {
            return;
        }
        blockingGrid = true;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        buildBlockingGrid = l2.bQ.softFogFading;
        if (com.corrodinggames.rts.gameFramework.GlobalState.at() && Build.VERSION.SDK_INT > 26) {
            long l3 = Runtime.getRuntime().maxMemory() / 0x100000L;
            com.corrodinggames.rts.gameFramework.GlobalState.e("MaxHeapSizeInMB:" + l3);
            if (l3 > 200L) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("enabling softFades");
                buildBlockingGrid = true;
            }
        }
    }

    public static int[] b(int n2) {
        return new int[]{n2};
    }

    public static int[] a(int n2, int ... nArray) {
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

    public static int[] a(int ... nArray) {
        return nArray;
    }

    private static void a(int[] nArray, int n2, int n3, boolean bl, com.corrodinggames.rts.gameFramework.rendering.Texture e2, com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2, com.corrodinggames.rts.gameFramework.rendering.Texture e3) {
        a(nArray, a(new int[]{n2, n3}), bl, e2, y2, e3);
    }

    private static void a(int[] nArray, int[] nArray2, boolean bl, com.corrodinggames.rts.gameFramework.rendering.Texture e2, com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2, com.corrodinggames.rts.gameFramework.rendering.Texture e3) {
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
        com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y3 = y2;
        for (n3 = 0; n3 < nArray2.length; n3 += 2) {
            n2 = nArray2[n3 + 0] * 20;
            int n6 = nArray2[n3 + 1] * 20;
            rect2.a(n2, n6, n2 + n4, n6 + n5);
            y3.loadImageFromResource(e3, rect2, rect, null);
            rect4.a(rect2.c - 1, rect2.b, rect2.c, rect2.d);
            rect3.a(rect.c, rect.b, rect.c + 1, rect.d);
            y3.loadImageFromResource(e3, rect4, rect3, null);
            rect4.a(rect2.a, rect2.d - 1, rect2.c, rect2.d);
            rect3.a(rect.a, rect.d, rect.c, rect.d + 1);
            y3.loadImageFromResource(e3, rect4, rect3, null);
        }
        y3.p();
        for (n3 = 0; n3 < nArray.length; ++n3) {
            n2 = nArray[n3] + 128;
            a(n2, e2);
        }
    }

    public static void a(int n2, com.corrodinggames.rts.gameFramework.rendering.Texture e2) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        rect2.a(0, 0, 20, 20);
        a(n2, rect);
        TileDrawer.a(tilesetTexture, e2, rect2, rect, null);
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

    public final short a(MapLayer g2) {
        if (this.renderMode >= this.mapWidth.length) {
            MapLayer[] gArray = new MapLayer[GameUtils.c(this.mapWidth.length + 100, Short.MAX_VALUE)];
            System.arraycopy(this.mapWidth, 0, gArray, 0, this.mapWidth.length);
            this.mapWidth = gArray;
        }
        int n2 = this.renderMode;
        if (this.renderMode < 32766) {
            ++this.renderMode;
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Max unique tile limit reached at: " + this.renderMode);
        }
        this.mapWidth[n2] = g2;
        return (short)n2;
    }

    public final MapLayer a(short s2) {
        return this.mapWidth[s2];
    }

    public MapLayer a(MapLayer g2, int n2, int n3) {
        if (g2 != null && g2.tileGrid != null) {
            int n4 = (n2 * 13 + n3 * 1313) % (g2.tileGrid.length + 1);
            if (--n4 >= 0) {
                return g2.tileGrid[n4];
            }
        }
        return g2;
    }

    public boolean a(float f2, float f3, PlayerState n2) {
        if (this.tileHeight) {
            int n3 = (int)(f2 * this.float1);
            int n4 = (int)(f3 * this.float2);
            if (n2.N != null && this.c(n3, n4) && n2.N[n3][n4] >= 5) {
                return false;
            }
        }
        return true;
    }

    public boolean a(int n2, int n3, PlayerState n4) {
        return !this.tileHeight || n4.N == null || !this.c(n2, n3) || n4.N[n2][n3] < 5;
    }

    public void a(float f2, float f3) {
        this.scrollPixelX = (int)(f2 * this.float1);
        this.scrollPixelY = (int)(f3 * this.float2);
    }

    public void a(int n2, int n3) {
        this.scrollPixelX = n2 * this.tilePixelWidth;
        this.scrollPixelY = n3 * this.tilePixelHeight;
    }

    public void b(int n2, int n3) {
        this.scrollPixelX = n2 * this.tilePixelWidth + this.selectedTileX;
        this.scrollPixelY = n3 * this.tilePixelHeight + this.selectedTileY;
    }

    public PointF a(Point point) {
        this.cameraFocus.a(point.a * this.tilePixelWidth, point.b * this.tilePixelHeight);
        return this.cameraFocus;
    }

    public void b(float f2, float f3) {
        this.a(f2, f3);
        this.a(this.scrollPixelX, this.scrollPixelY);
    }

    public float a(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > this.getMapWidthPixels()) {
            f2 = this.getMapWidthPixels();
        }
        return f2;
    }

    public float b(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > this.getMapHeightPixels()) {
            f2 = this.getMapHeightPixels();
        }
        return f2;
    }

    public final boolean c(int n2, int n3) {
        return n2 >= 0 && n2 < this.mapHeight && n3 >= 0 && n3 < this.tileWidth;
    }

    public MapLayer c(float f2, float f3) {
        int n2 = (int)(f2 * this.float1);
        int n3 = (int)(f3 * this.float2);
        if (n2 < 0 || n2 >= this.mapHeight || n3 < 0 || n3 >= this.tileWidth) {
            return null;
        }
        return this.groundLayer.a(n2, n3);
    }

    public MapLayer d(int n2, int n3) {
        if (!this.c(n2, n3)) {
            return null;
        }
        return this.groundLayer.a(n2, n3);
    }

    public MapLayer e(int n2, int n3) {
        if (!this.c(n2, n3)) {
            return null;
        }
        if (this.itemsLayer == null) {
            return null;
        }
        return this.itemsLayer.a(n2, n3);
    }

    void a(RectF rectF) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.C()) {
            rectF.a *= (float)(this.tilePixelWidth / 20);
            rectF.c *= (float)(this.tilePixelWidth / 20);
            rectF.b *= (float)(this.tilePixelHeight / 20);
            rectF.d *= (float)(this.tilePixelHeight / 20);
        }
    }

    public MapEngine() {
        if (com.corrodinggames.rts.gameFramework.GlobalState.C()) {
            this.tilePixelWidth = 60;
            this.tilePixelHeight = 60;
        }
        this.selectedTileX = this.tilePixelWidth / 2;
        this.selectedTileY = this.tilePixelHeight / 2;
        this.float1 = 1.0f / (float)this.tilePixelWidth;
        this.float2 = 1.0f / (float)this.tilePixelHeight;
        this.ab = new UniquePaint();
        this.ab.a(100, 255, 0, 0);
        this.ab.b(16.0f);
        this.ac = new UniquePaint();
        this.ac.a(Paint$Style.b);
        this.ac.a(1.0f);
        this.ac.a(255, 0, 225, 0);
        this.ad = new UniquePaint();
        this.ad.a(Paint$Style.b);
        this.ad.a(1.0f);
        this.ad.a(100, 0, 185, 0);
        this.ae = new UniquePaint();
        this.ae.a(Paint$Style.b);
        this.ae.a(1.0f);
        this.ae.a(255, 175, 0, 0);
        this.af = new UniquePaint();
        this.af.a(155, 175, 0, 0);
        this.ag = new UniquePaint();
        this.ag.a(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public static void a(String string, com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        InputStream inputStream = b(string);
        if (inputStream == null) {
            throw new IOException("writeMapStream: Could not find map:" + string);
        }
        int n2 = (int)a(string);
        if (n2 == -1) {
            new IOException("writeMapStream: Failed to get map size");
        }
        if (n2 == 0) {
            new IOException("writeMapStream: Got empty map size");
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Sending map stream of size: " + n2);
        as2.a(inputStream, n2);
    }

    public static long a(String string) throws IOException {
        String string2 = "" + string;
        String string3 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(string2);
        af af2 = com.corrodinggames.rts.gameFramework.utility.ae.a(string3);
        if (af2 != null && !string3.endsWith(".rwmod")) {
            long l2 = af2.a(string3, false);
            if (l2 == -1L) {
                // empty if block
            }
            return l2;
        }
        if (com.corrodinggames.rts.gameFramework.filesystem.FileLoader.reset(string2)) {
            AssetManager assetManager = com.corrodinggames.rts.gameFramework.GlobalState.B().am.d();
            try {
                AssetFileDescriptor assetFileDescriptor = assetManager.b(string3);
                return assetFileDescriptor.getLength();
            }
            catch (RuntimeException iOException) {
                throw new RuntimeException(iOException);
            }
        }
        File file = new File(string3);
        return file.length();
    }

    public static InputStream b(String string) {
        InputStream inputStream;
        InputStream inputStream2 = d(string);
        if (inputStream2 == null && (inputStream = d(string.replace(".tmx", "") + "_moved")) != null) {
            String string2 = GameUtils.a(inputStream);
            string2 = string2.trim();
            com.corrodinggames.rts.gameFramework.GlobalState.e("Found moved map at:" + string2);
            inputStream2 = d(string2);
        }
        return inputStream2;
    }

    public static String c(String string) {
        if (string == null) {
            return null;
        }
        String string2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(string);
        return string2;
    }

    public static InputStream d(String string) {
        String string2 = c("" + string);
        com.corrodinggames.rts.gameFramework.GlobalState.e("Mapfile: " + string2);
        com.corrodinggames.rts.gameFramework.utility.AssetStream j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getj2(string2);
        return j2;
    }

    public void a(Document document, OutputStream outputStream) throws TransformerConfigurationException, TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        DOMSource dOMSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(outputStream);
        transformer.transform(dOMSource, streamResult);
    }

    public void a(InputStream inputStream, OutputStream outputStream) throws MapException, ParserConfigurationException, SAXException, IOException, TransformerException {
        Object object;
        Object object2;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setEntityResolver(new MapEngine$1(this));
        Document document = documentBuilder.parse(inputStream);
        Element element = document.getDocumentElement();
        String string = element.getAttribute("orientation");
        if (!string.equals("orthogonal")) {
            throw new MapException("Only orthogonal maps are supported, found: " + string);
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
            object = ((Element)object2).getAttribute("name");
            if (!"UnitObjects".equalsIgnoreCase((String)object)) continue;
            ((Element)object2).getParentNode().removeChild((Node)object2);
        }
        Element element4 = document.createElement("objectgroup");
        element4.setAttribute("name", "UnitObjects");
        object2 = com.corrodinggames.rts.game.units.UnitInstance.bF();
        Iterator iterator2 = ((DequeList) object2).iterator();
        while (iterator2.hasNext()) {
            UnitInstance am2 = (UnitInstance) iterator2.next();
            if (!(am2 instanceof UnitInstance) || am2 instanceof TreeDecoration && ((TreeDecoration) am2).bM) continue;
            UnitInstance am3 = am2;
            if (am3.isDead || am3.u()) continue;
            com.corrodinggames.rts.game.units.custom.animation.UnitTrait n2 = am3.dn();
            if (am3.cO != null && n2 != null) {
                if (n2.D) continue;
                continue;
            }
            Element element5 = document.createElement("object");
            int n3 = 20;
            if ((float)n3 < am3.cj) {
                n3 = (int)am3.cj;
            }
            element5.setAttribute("name", am3.r().i() + " (t:" + am3.player.k + ")");
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
            element7.setAttribute("value", "" + am3.player.k);
            element6.appendChild(element7);
            element5.appendChild(element6);
            element4.appendChild(element5);
        }
        element.appendChild(element4);
        this.a(document, outputStream);
    }

    public boolean a(String string, String string2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        try {
            this.b(string, string2);
            l2.bS.selectionGroup.a(null, "Map exported.");
            return true;
        }
        catch (MapException f2) {
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

    public void b(String string, String string2) throws MapException, IOException {
        OutputStream outputStream;
        com.corrodinggames.rts.gameFramework.GlobalState.e(" --- Saving map:" + string + " to: " + string2);
        InputStream inputStream = b(string);
        if (inputStream == null) {
            throw new IOException("Could not find orginal map: " + string);
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        File file = new File(string2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(string2)).getParentFile();
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b.g(file.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.filesystem.FileLoader.l(file.getAbsolutePath());
        }
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled3(file.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Save Map: Could not create parent directory");
        }
        try {
            outputStream = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(string2, false);
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

    /* 02b b.java L780: 仅抛 f(MapException), 方法体 IOException 已内部 try-catch 消化;
       去过度 throws IOException (GameEngine 调用点 L608 无 catch(IOException) 铁证) */
    public void a(String string, boolean bl) throws MapException {
        com.corrodinggames.rts.gameFramework.GlobalState.e(" --- Loading map ---");
        InputStream inputStream = b(string);
        if (inputStream == null) {
            String string2 = c(string);
            throw new MapException("Could not find map: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b.e(string2));
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

    /* 02b b.java L800: 仅抛 f (调 j.c() 链) → 去过度 IOException */
    public TilesetDef e(String string) throws MapException {
        TilesetDef j2 = null;
        for (TilesetDef j3 : (java.util.Collection<TilesetDef>) (java.util.Collection) this.tilesets) {
            if (!string.equals(j3.tilesetName)) continue;
            j2 = j3;
        }
        if (j2 == null) {
            TilesetDef j3;
            int n2 = 1;
            if (this.tilesets.size() > 0) {
                j3 = (TilesetDef) this.tilesets.get(this.tilesets.size() - 1);
                n2 = j3.columns + 100;
                j3.c(n2);
            }
            j3 = new TilesetDef(this, string, n2 + 1);
            this.tilesets.add(j3);
            j2 = j3;
        }
        if (j2.tilesetTexture == null) {
            j2.c();
        }
        return j2;
    }

    /* 02b b.java L832: 仅抛 f (调 e(String) 链) → 去过度 IOException */
    public MapLayer a(String string, int n2, int n3) throws MapException {
        TilesetDef j2 = this.e(string);
        if (this.tileCache == null) {
            this.tileCache = new HashMap();
        }
        boolean bl = true;
        int n4 = j2.a(n2, n3);
        int n5 = j2.columns + n4;
        MapLayer g2 = (MapLayer) this.tileCache.get(n5);
        if (g2 != null) {
            return g2;
        }
        MapLayer g3 = MapLayer.a(this, this.groundLayer, j2, n5 - j2.columns, (short)0, (short)0, bl);
        this.tileCache.put(n5, g3);
        return g3;
    }

    public void a(InputStream inputStream, boolean bl) throws MapException {
        Object object;
        String string;
        Object object2;
        Object object3;
        this.resourcePoints.clear();
        l.b();
        tileRendererB.b();
        try {
            Object object42;
            Object object52;
            Object object62;
            Object object7;
            Element element;
            int n2;
            Object object8;
            int n3;
            com.corrodinggames.rts.gameFramework.GlobalState.e("---- Loading map data ----");
            object3 = DocumentBuilderFactory.newInstance();
            ((DocumentBuilderFactory)object3).setValidating(false);
            DocumentBuilder documentBuilder = ((DocumentBuilderFactory)object3).newDocumentBuilder();
            documentBuilder.setEntityResolver(new MapEngine$2(this));
            Document document = documentBuilder.parse(inputStream);
            object2 = document.getDocumentElement();
            string = ((Element)object2).getAttribute("orientation");
            if (!string.equals("orthogonal")) {
                throw new MapException("Only orthogonal maps are supported, found: " + string);
            }
            int n4 = Integer.parseInt(((Element)object2).getAttribute("width"));
            int n5 = Integer.parseInt(((Element)object2).getAttribute("height"));
            this.mapHeight = n4;
            this.tileWidth = n5;
            com.corrodinggames.rts.gameFramework.GlobalState.e("Map size: " + this.mapHeight + ", " + this.tileWidth);
            this.ar = 150.0f;
            if (this.tileHeight) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Setting up team fog..");
                for (n3 = 0; n3 < com.corrodinggames.rts.game.n.c; ++n3) {
                    object8 = com.corrodinggames.rts.game.n.k(n3);
                    if (object8 == null) continue;
                    ((PlayerState) object8).L = this.mapHeight;
                    ((PlayerState) object8).M = this.tileWidth;
                    ((PlayerState) object8).N = new byte[this.mapHeight][this.tileWidth];
                    for (n2 = 0; n2 < this.mapHeight; ++n2) {
                        for (int i2 = 0; i2 < this.tileWidth; ++i2) {
                            ((PlayerState) object8).N[n2][i2] = 10;
                        }
                    }
                }
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("No team fog on this map..");
                for (n3 = 0; n3 < com.corrodinggames.rts.game.n.c; ++n3) {
                    object8 = com.corrodinggames.rts.game.n.k(n3);
                    if (object8 == null) continue;
                    ((PlayerState) object8).N = null;
                }
            }
            if ((element = (Element)((Element)object2).getElementsByTagName("properties").item(0)) != null && (object8 = element.getElementsByTagName("property")) != null) {
                object = new Properties();
                for (n2 = 0; n2 < ((NodeList) object8).getLength(); ++n2) {
                    Element element2 = (Element)((NodeList) object8).item(n2);
                    object7 = element2.getAttribute("name");
                    object62 = element2.getAttribute("value");
                    ((Properties)object).setProperty((String)object7, (String)object62);
                }
            }
            object8 = null;
            NodeList nodeList = ((Element)object2).getElementsByTagName("tileset");
            for (int n6 = 0; n6 < nodeList.getLength(); n6 = (int)((short)(n6 + 1))) {
                object7 = (Element)nodeList.item(n6);
                object62 = new TilesetDef(this, (Element)object7);
                ((TilesetDef) object62).n = (short)n6;
                if (object8 != null) {
                    ((TilesetDef) object8).c(((TilesetDef) object62).columns - 1);
                }
                object8 = object62;
                this.tilesets.add(object62);
            }
            NodeList nodeList2 = ((Element)object2).getElementsByTagName("layer");
            for (int i3 = 0; i3 < nodeList2.getLength(); ++i3) {
                object62 = (Element)nodeList2.item(i3);
                object52 = ((Element)object62).getAttribute("name");
                if ("set".equalsIgnoreCase((String)object52) || "set-disabled".equalsIgnoreCase((String)object52)) continue;
                object42 = new TMXMapLoader(this, (Element)object62);
                ((TMXMapLoader) object42).renderOrder = i3;
                this.layers.add(object42);
            }
            for (Object object62_902 : this.layers) {
                if (((TMXMapLoader) object62_902).r) {
                    this.groundLayer = (TMXMapLoader) object62_902;
                }
                if (((TMXMapLoader) object62_902).backgroundColor.equalsIgnoreCase("grounddetails")) {
                    this.groundDetailsLayer = (TMXMapLoader) object62_902;
                }
                if (((TMXMapLoader) object62_902).backgroundColor.equalsIgnoreCase("grounddetails2")) {
                    this.groundDetails2Layer = (TMXMapLoader) object62_902;
                }
                if (((TMXMapLoader) object62_902).backgroundColor.equalsIgnoreCase("Items") || ((TMXMapLoader) object62_902).backgroundColor.equalsIgnoreCase("Objects")) {
                    this.itemsLayer = (TMXMapLoader) object62_902;
                }
                if (!((TMXMapLoader) object62_902).backgroundColor.equalsIgnoreCase("PathingOverride")) continue;
                this.pathingOverrideLayer = (TMXMapLoader) object62_902;
            }
            if (this.groundLayer == null) {
                throw new MapException("'Ground' layer was not found in map, this layer is required");
            }
            if (this.mapWidth == null || this.mapWidth.length == 0) {
                throw new MapException("Invalid map, no tiles have been set");
            }
            if (!com.corrodinggames.rts.gameFramework.GlobalState.C() && !com.corrodinggames.rts.gameFramework.GlobalState.D()) {
                for (int i4 = 0; i4 < this.mapHeight; ++i4) {
                    for (int i5 = 0; i5 < this.tileWidth; ++i5) {
                        if (this.groundLayer.a(i4, i5) != null) continue;
                        throw new MapException("An empty tile on the Ground layer at " + i4 + "," + i5 + " all tiles must be filled");
                    }
                }
            }
            if (this.itemsLayer == null) {
                throw new MapException("'Items' layer was not found in map, this layer is required");
            }
            NodeList nodeList3 = ((Element)object2).getElementsByTagName("objectgroup");
            int n7 = 0;
            while (n7 < nodeList3.getLength()) {
                object52 = (Element)nodeList3.item(n7);
                object42 = new MapLayerDef((Element)object52, this);
                ((MapLayerDef) object42).layerName = n7++;
                this.objectGroups.add(object42);
            }
            TilesetDef.a();
            for (Object object52_944 : this.tilesets) {
                if (!((TilesetDef) object52_944).q) continue;
                ((TilesetDef) object52_944).c();
            }
            TilesetDef.b();
            for (int i6 = 0; i6 <= 1; ++i6) {
                for (Object object42_950 : this.layers) {
                    boolean bl2;
                    boolean bl3 = object42_950 == this.groundLayer;
                    boolean bl4 = bl2 = i6 == 0;
                    if (bl3 != bl2) continue;
                    ((TMXMapLoader) object42_950).w = false;
                    if (!((TMXMapLoader) object42_950).s) continue;
                    for (int i7 = 0; i7 < this.mapHeight; ++i7) {
                        for (int i8 = 0; i8 < this.tileWidth; ++i8) {
                            int n8;
                            MapLayer g2 = ((TMXMapLoader) object42_950).a(i7, i8);
                            if (g2 == null || g2.layerHeight != -2) continue;
                            g2.layerHeight = l.a(g2.tilesetDef, g2.layerWidth);
                            if (g2.layerHeight >= 0 && (n8 = tileRendererB.a(g2.tilesetDef, g2.layerWidth)) != g2.layerHeight) {
                                throw new RuntimeException("Meta index mismatch: " + n8 + " vs " + g2.layerHeight);
                            }
                            if (g2.layerHeight >= 0) continue;
                            ((TMXMapLoader) object42_950).w = true;
                        }
                    }
                }
            }
            l.c();
            tileRendererB.c();
            this.minimapRenderer = this.f("triggers");
        }
        catch (IOException iOException) {
            throw new MapException("Failed to parse map", iOException);
        }
        catch (ParserConfigurationException parserConfigurationException) {
            throw new RuntimeException("Failed to parse map", parserConfigurationException);
        }
        catch (SAXException sAXException) {
            com.corrodinggames.rts.gameFramework.GlobalState.e(" --- SAXException: Failed to parse map - " + sAXException.getMessage() + " ---");
            try {
                com.corrodinggames.rts.gameFramework.GlobalState.e("available:" + inputStream.available());
                inputStream.reset();
                com.corrodinggames.rts.gameFramework.GlobalState.e("after reset:" + inputStream.available());
            }
            catch (IOException iOException) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("-- error writing debug info --");
                iOException.printStackTrace();
            }
            throw new MapException("Failed to parse map - " + sAXException.getMessage(), sAXException);
        }
        object3 = null;
        if (this.minimapRenderer != null) {
            object3 = this.minimapRenderer.a("map_info");
        }
        boolean bl5 = false;
        boolean bl6 = false;
        object2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        ((com.corrodinggames.rts.gameFramework.GlobalState)object2).ce = null;
        string = null;
        object = null;
        if (object3 != null) {
            String string2 = ((MapSpawn) object3).b("type");
            object = ((MapSpawn) object3).b("fog");
            if ("mission".equalsIgnoreCase(string2) || "survival".equalsIgnoreCase(string2) || "challenge".equalsIgnoreCase(string2) || "skirmish".equalsIgnoreCase(string2)) {
                string = string2;
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Unknown map type:" + string2);
            }
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Map type not found on mapInfo");
        }
        if (string == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Defaulting to skirmish map type");
            string = "skirmish";
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Map type: " + string);
        }
        ((com.corrodinggames.rts.gameFramework.GlobalState)object2).ce = new com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem();
        ((com.corrodinggames.rts.gameFramework.GlobalState)object2).ce.reset(bl);
        if (object != null && !"".equals(object)) {
            if (!((String)object).equalsIgnoreCase("none")) {
                bl5 = true;
                if (((String)object).equalsIgnoreCase("los")) {
                    bl6 = true;
                } else if (!((String)object).equalsIgnoreCase("map")) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Unknown map fog type: " + (String)object);
                }
            }
        } else if (com.corrodinggames.rts.gameFramework.GlobalState.av() && !((com.corrodinggames.rts.gameFramework.GlobalState)object2).N()) {
            bl5 = true;
            if (string != null && string.equalsIgnoreCase("skirmish")) {
                bl6 = true;
            }
        }
        if (!bl5) {
            this.tileHeight = false;
        }
        if (bl5 && bl6) {
            this.fogGrid = true;
        }
        this.cameraLocked = true;
    }

    public void e() {
    }

    public void a(Projectile l2) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU && !com.corrodinggames.rts.gameFramework.GlobalState.aW) {
            return;
        }
        commandProcessor.a(l2);
    }

    public void a(UnitType y2, int n2, int n3, int n4, int n5, int n6, int n7, com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y3, boolean bl, int n8) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        UnitTypeHandle as2 = l2.bS.ac.i();
        MovementTypeEnum ao2 = as2.o();
        for (int i2 = n2; i2 <= n4; ++i2) {
            for (int i3 = n3; i3 <= n5; ++i3) {
                boolean bl2 = com.corrodinggames.rts.game.units.commands.BuildSlot.a(y2, as2, ao2, i2, i3, n8);
                int n9 = i2 * b2.tilePixelWidth - n6;
                int n10 = i3 * b2.tilePixelHeight - n7;
                this.an.a(n9, n10, n9 + b2.tilePixelWidth - 1, n10 + b2.tilePixelHeight - 1);
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
        commandProcessor.d();
    }

    public void c(float f2) {
        commandProcessor.a(f2);
    }

    public void g() {
        commandProcessor.c();
    }

    public void d(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = com.corrodinggames.rts.gameFramework.GlobalState.at();
        if (bl) {
            l2.bO.a(mapLock);
        }
        this.c(f2);
        if (bl) {
            l2.bO.b(mapLock);
        }
        if (this.fogDirty) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            int n2 = this.visibleTilesX * this.tilePixelWidth;
            int n3 = this.visibleTilesY * this.tilePixelHeight;
            rect2.a(n2, n3, n2 + this.tilePixelWidth, n3 + this.tilePixelHeight);
            rect2.a(-com.corrodinggames.rts.gameFramework.GlobalState.B().cu, -com.corrodinggames.rts.gameFramework.GlobalState.B().cv);
        }
    }

    public void e(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.f(f2);
    }

    public void disposeAll() {
        for (Object object : this.tilesets) {
            ((TilesetDef) object).d();
        }
        this.tilesets.clear();
        for (Object object : this.layers) {
            ((TMXMapLoader) object).b();
        }
        this.layers.clear();
        this.objectGroups.clear();
        this.minimapRenderer = null;
        commandProcessor.c();
    }

    public TilesetDef a(int n2) {
        for (int i2 = 0; i2 < this.tilesets.size(); ++i2) {
            TilesetDef j2 = (TilesetDef) this.tilesets.get(i2);
            if (!j2.d(n2)) continue;
            return j2;
        }
        return null;
    }

    public Integer a(UnitTypeHandle as2) {
        String string = as2.i();
        Integer n2 = this.c("unit", string);
        if (n2 == null) {
            n2 = this.c("customUnit", string);
        }
        return n2;
    }

    public Integer c(String string, String string2) {
        for (int i2 = 0; i2 < this.tilesets.size(); ++i2) {
            TilesetDef j2 = (TilesetDef) this.tilesets.get(i2);
            Integer n2 = j2.b(string, string2);
            if (n2 == null) continue;
            return n2;
        }
        return null;
    }

    public MapLayerDef f(String string) {
        for (MapLayerDef i2 : (java.util.Collection<MapLayerDef>) (java.util.Collection) this.objectGroups) {
            if (!string.equalsIgnoreCase(i2.layerClass)) continue;
            return i2;
        }
        return null;
    }

    public float getMapWidthPixels() {
        return this.mapHeight * this.tilePixelWidth;
    }

    public float getMapHeightPixels() {
        return this.tileWidth * this.tilePixelHeight;
    }

    public float i() {  // 02b b.i(): 鍦板浘鍍忕礌瀹?
        return (float)(this.mapHeight * this.tilePixelWidth);
    }

    public float j() {  // 02b b.j(): 鍦板浘鍍忕礌楂?
        return (float)(this.tileWidth * this.tilePixelHeight);
    }

    public void a(float f2, float f3, int n2, PlayerState n3, boolean bl) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.tileHeight) {
            com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem f4;
            long l3 = 0L;
            if (a) {
                l3 = ExtraManager.a();
            }
            boolean bl2 = true;
            boolean bl3 = n3.E;
            if (!(l2.O() || (f4 = l2.ce) == null || f4.reset() || f4.geti())) {
                bl2 = false;
            }
            if (!bl2) {
                this.b(f2, f3, n2, n3, bl);
            } else {
                for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                    PlayerState n4 = com.corrodinggames.rts.game.PlayerState.k(i2);
                    if (n4 == null || n4 != n3 && (n4.w || !n4.d(n3) && !bl3)) continue;
                    this.b(f2, f3, n2, n4, bl);
                }
            }
            if (a) {
                this.ap += ExtraManager.a() - l3;
            }
        }
    }

    public byte a(int n2, int n3, byte[][] byArray, byte by) {
        byte by2 = 0;
        int n4 = this.mapHeight;
        int n5 = this.tileWidth;
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

    public void clearAllFog() {
        this.rebuildFogCache();
        for (int i2 = 0; i2 < this.mapHeight; ++i2) {
            for (int i3 = 0; i3 < this.tileWidth; ++i3) {
                this.fogCacheBufferA[i2][i3] = 0;
                this.fogCacheBufferB[i2][i3] = 0;
            }
        }
    }

    public void f(int n2, int n3) {
        this.fogCacheBufferA[n2][n3] = 0;
        this.fogCacheBufferB[n2][n3] = 0;
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
        if (n6 > this.mapHeight - 1) {
            n6 = this.mapHeight - 1;
        }
        if (n7 > this.tileWidth - 1) {
            n7 = this.tileWidth - 1;
        }
        for (int i2 = n4; i2 <= n6; ++i2) {
            for (int i3 = n5; i3 <= n7; ++i3) {
                if (this.fogCacheBufferA[i2][i3] != 0) {
                    this.fogCacheBufferA[i2][i3] = 127;
                }
                if (this.fogCacheBufferB[i2][i3] == 0) continue;
                this.fogCacheBufferB[i2][i3] = 127;
            }
        }
    }

    public void rebuildFogCache() {
        boolean bl = false;
        if (this.fogCacheBufferA == null) {
            bl = true;
        } else if (this.fogCacheBufferA.length != this.mapHeight || this.fogCacheBufferA[0].length != this.tileWidth) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("smoothFog_cache: Size mismatch");
            bl = true;
        }
        if (bl) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Building smoothFog_cache");
            this.fogCacheBufferA = new byte[this.mapHeight][this.tileWidth];
            this.fogCacheBufferB = new byte[this.mapHeight][this.tileWidth];
            for (int i2 = 0; i2 < this.mapHeight; ++i2) {
                for (int i3 = 0; i3 < this.tileWidth; ++i3) {
                    this.fogCacheBufferA[i2][i3] = 127;
                    this.fogCacheBufferB[i2][i3] = 127;
                }
            }
        }
    }

    public void b(float f2, float f3, int n2, PlayerState n3, boolean bl) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.tileHeight && n3.N != null) {
            this.rebuildFogCache();
            int n4 = n2;
            float f4 = (n4 - 5) * (n4 - 5);
            float f5 = (n4 - 3) * (n4 - 3);
            float f6 = n4 * n4;
            float f7 = 1.0f / (f6 - f5) * 10.0f;
            MapEngine b2 = this;
            b2.a(f2, f3);
            int n5 = b2.scrollPixelX;
            int n6 = b2.scrollPixelY;
            float f8 = f2 * b2.float1;
            float f9 = f3 * b2.float2;
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
            if (n10 > this.mapHeight - 1) {
                n10 = this.mapHeight - 1;
            }
            if (n11 > this.tileWidth - 1) {
                n11 = this.tileWidth - 1;
            }
            MapRenderer c2 = commandProcessor;
            boolean bl2 = false;
            boolean bl3 = n3.isLocalPlayer();
            for (int i2 = n8; i2 <= n10; ++i2) {
                for (int i3 = n9; i3 <= n11; ++i3) {
                    byte by;
                    byte by2 = byArray[i2][i3];
                    if (by2 == 0) continue;
                    float f10 = GameUtils.a(f8, f9, (float)i2, (float)i3);
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
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (a) {
            this.aq += f2;
            if (this.aq > 60.0f) {
                this.aq = 0.0f;
                if (this.ap > 0L) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("seeThoughFogOfWarTimes: " + ExtraManager.b(this.ap));
                    this.ap = 0L;
                }
                if (this.ap < 0L) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("seeThoughFogOfWarTimes negative: " + ExtraManager.b(this.ap));
                    this.ap = 0L;
                }
            }
        }
        if (this.tileHeight && this.fogGrid) {
            this.rebuildFogCache();
            this.ar += f2;
            if (this.ar > 260.0f) {
                PlayerState bq2;
                int n2;
                this.ar = 0.0f;
                com.corrodinggames.rts.gameFramework.GameObject[] wArray = com.corrodinggames.rts.gameFramework.GameObject.er.a();
                int n3 = com.corrodinggames.rts.gameFramework.GameObject.er.size();
                boolean bl = false;
                for (n2 = 0; n2 < com.corrodinggames.rts.game.n.c; ++n2) {
                    Object object;
                    int n4;
                    bq2 = com.corrodinggames.rts.game.PlayerState.k(n2);
                    if (bq2 == null || ((PlayerState) bq2).G) continue;
                    bl = true;
                    for (n4 = 0; n4 < n3; ++n4) {
                        com.corrodinggames.rts.gameFramework.GameObject w2 = wArray[n4];
                        if (!(w2 instanceof UnitType) || !((UnitInstance) (object = (UnitType)w2)).isFactoryBuilding()) continue;
                        ((UnitInstance) object).setDiscoveredBy(bq2);
                    }
                    if (((PlayerState) bq2).N == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("fogOfWar_map==null for:" + n2);
                    }
                    n4 = 0;
                    boolean bl2 = ((PlayerState) bq2).isLocalPlayer();
                    byte[][] byArray3 = ((PlayerState) bq2).N;
                    byte[][] byArray = this.fogCacheBufferB;
                    for (int i2 = 0; i2 < this.mapHeight; ++i2) {
                        for (int i3 = 0; i3 < this.tileWidth; ++i3) {
                            if (byArray3[i2][i3] >= 5) continue;
                            byArray3[i2][i3] = 5;
                            if (!bl2) continue;
                            commandProcessor.a(i2, i3, true);
                            n4 = 1;
                            byArray[i2][i3] = 127;
                        }
                    }
                    if (n4 == 0) continue;
                    l2.bW.O = true;
                }
                for (n2 = 0; n2 < n3; ++n2) {
                    com.corrodinggames.rts.gameFramework.GameObject w3 = wArray[n2];
                    if (!(w3 instanceof UnitType)) continue;
                    UnitType y2 = (UnitType)w3;
                    if (y2.isDead) continue;
                    y2.c(false);
                }
                if (bl) {
                    for (n2 = 0; n2 < n3; ++n2) {
                        UnitType y2;
                        com.corrodinggames.rts.gameFramework.GameObject w3 = wArray[n2];
                        if (!(w3 instanceof UnitType) || !(y2 = (UnitType)w3).bI()) continue;
                        y2.cX();
                    }
                }
            }
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(false);
    }

    public void a(InputNetStream k2) {  // 02b b.a(j.k): 甯ф暟鎹鍙?
        boolean bl = k2.e();  // 02b k.e()
        if (bl) {
            int n2 = k2.f();  // 02b k.f()
            int n3 = k2.f();
            for (int i2 = 0; i2 < n2; ++i2) {
                for (int i3 = 0; i3 < n3; ++i3) {
                    k2.d();  // 02b k.d()
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
            return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string + string3);
        }
        return null;
    }

    public InputStream d(String string, String string2) throws IOException {
        InputStream inputStream = null;
        inputStream = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string + string2);
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

    public boolean a(PlayerState n2, int n3, int n4) {
        MapEngine b2 = this;
        return this.visibilityGrid || !b2.tileHeight || n2.N == null || !b2.c(n3, n4) || n2.N[n3][n4] != 10;
    }

    static {
        g = new Paint();
        h = new Paint();
        i = new Paint();
        j = new Paint();
        blockingGrid = false;
        buildBlockingGrid = false;
        tilesetList = false;
        commandProcessor = new MapRenderer();
    }

    // v19.115w 琛ョ己: javap b.al/b.I/b.J 閾佽瘉 (MapRenderer 寮曠敤)
    public static boolean I;

    // v19.115w 琛ョ己: javap b.al/b.I/b.J 閾佽瘉 (MapRenderer 寮曠敤)
    public static boolean J;

    // v19.115w 琛ョ己: javap b.al/b.I/b.J 閾佽瘉 (MapRenderer 寮曠敤)
    public static com.corrodinggames.rts.game.map.MapRenderer al;
}
