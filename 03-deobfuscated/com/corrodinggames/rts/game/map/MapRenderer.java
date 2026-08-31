/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.game.map;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.NeutralPlayer;
import com.corrodinggames.rts.game.units.PathState;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.MapLayerRenderer;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.Projectile;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.rendering.NullSpriteBatchBackend;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;

public strictfp final class MapRenderer {
    int layerRenderer = 7;
    public com.corrodinggames.rts.gameFramework.rendering.Texture renderWidth = null;
    public TextureManagerInterface renderHeight = null;
    MapLayerRenderer[][] d = null;
    public UniquePaint e = new UniquePaint();
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    float l;
    float m = 1.0f;
    boolean n;
    Rect o = new Rect();
    int p = 0;

    public void a() {
        float f2;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.m = f2 = this.g();
        if (this.m > 1.0f) {
            // empty if block
        }
        this.i = (int)((float)this.h / this.m);
        this.k = (int)((float)this.j / this.m);
        this.l = 1.0f / (float)this.k;
        this.f = l2.cu - this.i / 2;
        this.g = l2.cv - this.i / 2;
        int n2 = 20;
        float f3 = 1.0f / (float)n2;
        this.f = (int)((float)this.f * f3) * n2;
        this.g = (int)((float)this.g * f3) * n2;
        for (int j = 0; j < this.layerRenderer; ++j) {
            for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
                MapLayerRenderer d2 = this.d[j][i2];
                d2.tileHeight = true;
                d2.viewRect = false;
            }
        }
    }

    public void b() {
        for (int j = 0; j < this.layerRenderer; ++j) {
            int n2 = 0;
            while (n2 < this.layerRenderer) {
                MapLayerRenderer d2 = this.d[j][n2];
                d2.endTileY = j;
                d2.tileWidth = n2++;
            }
        }
    }

    public void a(int n2) {
        MapLayerRenderer[] dArray = new MapLayerRenderer[this.layerRenderer];
        if (n2 > 0) {
            int n3;
            for (n3 = 0; n3 < this.layerRenderer; ++n3) {
                dArray[n3] = this.d[n3][0];
            }
            for (n3 = 1; n3 < this.layerRenderer; ++n3) {
                for (int j = 0; j < this.layerRenderer; ++j) {
                    this.d[j][n3 - 1] = this.d[j][n3];
                }
            }
            for (n3 = 0; n3 < this.layerRenderer; ++n3) {
                this.d[n3][this.layerRenderer - 1] = dArray[n3];
            }
            for (n3 = 0; n3 < this.layerRenderer; ++n3) {
                this.d[n3][this.layerRenderer - 1].tileHeight = true;
            }
        } else {
            int n4;
            for (n4 = 0; n4 < this.layerRenderer; ++n4) {
                dArray[n4] = this.d[n4][this.layerRenderer - 1];
            }
            for (n4 = this.layerRenderer - 2; n4 >= 0; --n4) {
                for (int j = 0; j < this.layerRenderer; ++j) {
                    this.d[j][n4 + 1] = this.d[j][n4];
                }
            }
            for (n4 = 0; n4 < this.layerRenderer; ++n4) {
                this.d[n4][0] = dArray[n4];
            }
            for (n4 = 0; n4 < this.layerRenderer; ++n4) {
                this.d[n4][0].tileHeight = true;
            }
        }
        this.b();
    }

    public void b(int n2) {
        MapLayerRenderer[] dArray = new MapLayerRenderer[this.layerRenderer];
        if (n2 > 0) {
            int n3;
            for (n3 = 0; n3 < this.layerRenderer; ++n3) {
                dArray[n3] = this.d[0][n3];
            }
            for (n3 = 1; n3 < this.layerRenderer; ++n3) {
                for (int j = 0; j < this.layerRenderer; ++j) {
                    this.d[n3 - 1][j] = this.d[n3][j];
                }
            }
            for (n3 = 0; n3 < this.layerRenderer; ++n3) {
                this.d[this.layerRenderer - 1][n3] = dArray[n3];
            }
            for (n3 = 0; n3 < this.layerRenderer; ++n3) {
                this.d[this.layerRenderer - 1][n3].tileHeight = true;
            }
        } else {
            int n4;
            for (n4 = 0; n4 < this.layerRenderer; ++n4) {
                dArray[n4] = this.d[this.layerRenderer - 1][n4];
            }
            for (n4 = this.layerRenderer - 2; n4 >= 0; --n4) {
                for (int j = 0; j < this.layerRenderer; ++j) {
                    this.d[n4 + 1][j] = this.d[n4][j];
                }
            }
            for (n4 = 0; n4 < this.layerRenderer; ++n4) {
                this.d[0][n4] = dArray[n4];
            }
            for (n4 = 0; n4 < this.layerRenderer; ++n4) {
                this.d[0][n4].tileHeight = true;
            }
        }
        this.b();
    }

    public MapLayerRenderer a(int n2, int n3) {
        if (n2 < 0 || n2 >= this.layerRenderer) {
            return null;
        }
        if (n3 < 0 || n3 >= this.layerRenderer) {
            return null;
        }
        if (this.d == null) {
            return null;
        }
        return this.d[n2][n3];
    }

    public void a(int n2, int n3, boolean bl) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        int n4 = b2.tilePixelWidth;
        int n5 = b2.tilePixelHeight;
        int n6 = n2 * n4;
        int n7 = n3 * n5;
        int n8 = n6 - this.f;
        int n9 = n7 - this.g;
        this.a(n8 - n4, n9 - n5, 3 * n4, 3 * n5, bl);
    }

    public void c() {
        if (this.d != null) {
            for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
                for (int i3 = 0; i3 < this.layerRenderer; ++i3) {
                    MapLayerRenderer d2 = this.d[i2][i3];
                    d2.tileHeight = true;
                }
            }
        }
    }

    public void a(int n2, int n3, int n4, int n5, boolean bl) {
        int n6 = (int)((float)n2 * this.l);
        int n7 = (int)((float)n3 * this.l);
        MapLayerRenderer d2 = this.a(n6, n7);
        if (d2 != null) {
            MapLayerRenderer d3;
            if (bl) {
                d2.currentLayer = true;
            } else {
                d2.tileHeight = true;
            }
            boolean bl2 = false;
            boolean bl3 = false;
            if (n2 + n4 >= d2.endTileY * this.k + this.i) {
                bl2 = true;
            }
            if (n3 + n5 >= d2.tileWidth * this.k + this.i) {
                bl3 = true;
            }
            if (bl2 && (d3 = this.a(n6 + 1, n7)) != null) {
                if (bl) {
                    d3.currentLayer = true;
                } else {
                    d3.tileHeight = true;
                }
            }
            if (bl3 && (d3 = this.a(n6, n7 + 1)) != null) {
                if (bl) {
                    d3.currentLayer = true;
                } else {
                    d3.tileHeight = true;
                }
            }
            if (bl2 && bl3 && (d3 = this.a(n6 + 1, n7 + 1)) != null) {
                if (bl) {
                    d3.currentLayer = true;
                } else {
                    d3.tileHeight = true;
                }
            }
        }
    }

    public void a(Projectile l2) {
        RectF rectF = l2.c();
        for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
            for (int i3 = 0; i3 < this.layerRenderer; ++i3) {
                boolean bl;
                MapLayerRenderer d2;
                Rect rect;
                if (this.d == null || !GameUtils.a(rect = (d2 = this.d[i2][i3]).b(), rectF)) continue;
                boolean bl2 = bl = this.m != 1.0f;
                if (bl) {
                    // empty if block
                }
                l2.a(d2.mapEngine, d2.c(), d2.d(), this.m);
                d2.offsetX.p();
                if (!bl) continue;
            }
        }
    }

    public void a(int n2, int n3, TextureManagerInterface y2) {
        boolean bl;
        MapLayerRenderer d2 = this.d[n2][n3];
        boolean bl2 = bl = this.m != 1.0f;
        if (bl) {
            // empty if block
        }
        Rect rect = d2.b();
        GameUtils.a(rect, 95.0f);
        GameObject[] wArray = GameObject.er.a();
        int n4 = GameObject.er.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            GameObject w2 = wArray[i2];
            if (!(w2 instanceof Projectile)) continue;
            Projectile l2 = (Projectile) w2;
            if (!rect.b((int)l2.eo, (int)l2.ep)) continue;
            l2.a(y2, d2.c(), d2.d(), this.m);
        }
        if (bl) {
            // empty if block
        }
    }

    public void b(int n2, int n3, TextureManagerInterface y2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        if (l2.bS.F()) {
            boolean bl;
            int n4;
            int n5;
            int n6;
            int n7 = this.f + n2 * this.k;
            int n8 = this.g + n3 * this.k;
            int n9 = n7;
            int n10 = n8;
            int n11 = this.i;
            int n12 = this.i;
            int n13 = b2.groundLayer.n;
            int n14 = b2.groundLayer.o;
            int n15 = (int)((float)n9 * b2.float1);
            if (n15 < 0) {
                n15 = 0;
            }
            if ((n6 = (int)((float)n10 * b2.float2)) < 0) {
                n6 = 0;
            }
            if ((n5 = (int)((float)(n9 + n11) * b2.float1)) > n13 - 1) {
                n5 = n13 - 1;
            }
            if ((n4 = (int)((float)(n10 + n12) * b2.float2)) > n14 - 1) {
                n4 = n14 - 1;
            }
            if ((double)this.m < 0.4) {
                return;
            }
            boolean bl2 = bl = this.m != 1.0f;
            if (bl) {
                y2.i();
                y2.D(this.m, this.m);
            }
            if (bl) {
                y2.j();
            }
        }
    }

    public void b(int n2, int n3) {
        MapLayerRenderer d2 = com.corrodinggames.rts.game.map.MapEngine.al.d[n2][n3];
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        d2.viewRect = true;
        this.renderHeight.clearScreen(-16777216);
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bW.J;
        if (e2 != null) {
            Rect rect = new Rect();
            RectF rectF = new RectF();
            float f2 = (float)(this.f + n2 * this.k) / (float)(b2.tilePixelWidth * b2.mapHeight);
            float f3 = (float)(this.g + n3 * this.k) / (float)(b2.tilePixelHeight * b2.tileWidth);
            float f4 = (float)(this.f + (n2 + 1) * this.k) / (float)(b2.tilePixelWidth * b2.mapHeight);
            float f5 = (float)(this.g + (n3 + 1) * this.k) / (float)(b2.tilePixelHeight * b2.tileWidth);
            rect.a((int)(f2 * (float)e2.p), (int)(f3 * (float)e2.q), (int)(f4 * (float)e2.p), (int)(f5 * (float)e2.q));
            rectF.a(0.0f, 0.0f, this.h, this.h);
            this.renderHeight.loadImageFromResource(e2, rect, rectF, (Paint)this.e);
        }
        this.renderHeight.p();
        if (com.corrodinggames.rts.gameFramework.GlobalState.aW) {
            d2.mapEngine.a(0, PorterDuff.Mode.CLEAR);
        }
        d2.mapEngine.b(this.renderWidth, 0.0f, 0.0f, null);
        d2.offsetX.p();
    }

    public void c(int n2, int n3) {
        this.c(n2, n3, this.renderHeight);
    }

    public void c(int n2, int n3, TextureManagerInterface y2) {
        int n4;
        MapLayerRenderer d2 = com.corrodinggames.rts.game.map.MapEngine.al.d[n2][n3];
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        boolean bl = false;
        if (l2.bQ.renderFancyWater) {
            bl = true;
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.C() || com.corrodinggames.rts.gameFramework.GlobalState.D()) {
            bl = true;
        }
        if (bl) {
            y2.a(0, PorterDuff.Mode.CLEAR);
        } else {
            n4 = 0;
            if (com.corrodinggames.rts.gameFramework.GlobalState.C()) {
                n4 = 1;
            }
            if (com.corrodinggames.rts.gameFramework.GlobalState.aX) {
                n4 = 1;
            }
            if (com.corrodinggames.rts.gameFramework.ui.InGameUI.bO) {
                // empty if block
            }
            if (b2.tileHeight) {
                // empty if block
            }
            if (n4 != 0) {
                y2.clearScreen(-16777216);
            }
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aX) {
            y2.a(0, PorterDuff.Mode.CLEAR);
        }
        n4 = this.f + n2 * this.k;
        int n5 = this.g + n3 * this.k;
        boolean bl2 = true;
        boolean bl3 = false;
        boolean bl4 = true;
        boolean bl5 = false;
        boolean bl6 = false;
        boolean bl7 = false;
        if (!b2.groundLayer.w) {
            bl6 = true;
        }
        if (b2.tileHeight) {
            bl7 = true;
        }
        if (com.corrodinggames.rts.game.map.MapEngine.d) {
            bl6 = false;
            bl7 = false;
        }
        if (bl6) {
            y2.a(true);
        }
        b2.groundLayer.a(y2, n4, n5, n4, n5, this.i, this.i, this.m, this.m, b2.tileHeight, false, false);
        if (b2.groundDetailsLayer != null) {
            if (bl6 && b2.groundDetailsLayer.w) {
                y2.i();
                com.corrodinggames.rts.gameFramework.GlobalState.e("Ending blit early");
            }
            b2.groundDetailsLayer.a(y2, n4, n5, n4, n5, this.i, this.i, this.m, this.m, b2.tileHeight, false, false);
        }
        if (b2.groundDetails2Layer != null) {
            if (bl6 && b2.groundDetails2Layer.w) {
                y2.i();
                com.corrodinggames.rts.gameFramework.GlobalState.e("Ending blit early");
            }
            b2.groundDetails2Layer.a(y2, n4, n5, n4, n5, this.i, this.i, this.m, this.m, b2.tileHeight, false, false);
        }
        for (Object obj2 : b2.layers) {
            TMXMapLoader e2 = (TMXMapLoader)obj2;
            if (!e2.encoding) continue;
            if (bl6 && e2.w) {
                y2.i();
                com.corrodinggames.rts.gameFramework.GlobalState.e("Ending blit early");
            }
            e2.a(y2, n4, n5, n4, n5, this.i, this.i, this.m, this.m, b2.tileHeight, false, false);
        }
        this.a(n2, n3, y2);
        if (b2.tileHeight) {
            if (bl7) {
                y2.a(false);
            }
            b2.groundLayer.a(y2, n4, n5, n4, n5, this.i, this.i, this.m, this.m, b2.tileHeight, true, true);
        }
        if (bl6 || bl7) {
            y2.i();
        }
        if (l2.bS.F()) {
            this.b(n2, n3, y2);
        }
        d2.tileHeight = false;
        d2.currentLayer = false;
        d2.currentTileset = 0;
        d2.viewRect = false;
        y2.p();
        if (bl || com.corrodinggames.rts.gameFramework.GlobalState.aW) {
            d2.mapEngine.a(0, PorterDuff.Mode.CLEAR);
        }
        d2.mapEngine.b(this.renderWidth, 0.0f, 0.0f, null);
        d2.offsetX.p();
        ++d2.renderHeight;  // v19.115w: 02b FernFlower 幻觉段清理 (javap b 无 c/h 静态), d2.c→renderHeight
    }

    public void d() {
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU && !com.corrodinggames.rts.gameFramework.GlobalState.aX && !com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n2 = Math.max((int)l2.cF, (int)l2.cH) + 3;
        if (this.d != null && this.h * this.layerRenderer < n2 + this.h + 1) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("map", "screen must have changed size, layerBufferSize too small at " + this.layerRenderer + ", adding to LayerBitmapBuffer");
            com.corrodinggames.rts.gameFramework.GlobalState.b("map", "new viewpoint:" + l2.cF + ", " + l2.cH);
            this.c(this.layerRenderer + 1);
        }
        if (this.d == null) {
            int n3;
            com.corrodinggames.rts.gameFramework.GlobalState.b("map", "setupLayerBuffers for size:" + n2);
            long l3 = System.nanoTime();
            if (com.corrodinggames.rts.gameFramework.GlobalState.aX || com.corrodinggames.rts.gameFramework.GlobalState.aY) {
                this.h = 1024;
                this.layerRenderer = (int)((float)n2 / (float)this.h + 1.5f);
            } else {
                n2 = Math.max(600, n2);
                this.h = n2 / (this.layerRenderer - 2) + 7 + 4;
                n3 = 20;
                float f2 = 1.0f / (float)n3;
                this.h = (int)((float)this.h * f2 + 0.5f) * n3;
            }
            if (this.h * this.layerRenderer < n2 + this.h + 1) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("layerBufferSize is too small");
                com.corrodinggames.rts.gameFramework.GlobalState.b("layerBufferCount:" + this.layerRenderer);
                com.corrodinggames.rts.gameFramework.GlobalState.b("(layerBufferSize*(layerBufferCount):" + this.h * this.layerRenderer);
                com.corrodinggames.rts.gameFramework.GlobalState.b("longest+layerBufferSize+1:" + (n2 + this.h + 1));
                com.corrodinggames.rts.gameFramework.GlobalState.b("longest:" + n2);
                if (com.corrodinggames.rts.gameFramework.GlobalState.aX || com.corrodinggames.rts.gameFramework.GlobalState.aY) {
                    ++this.layerRenderer;
                } else {
                    this.h += 100;
                }
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("layerBufferSize:" + this.h);
            this.j = this.h - 4;
            com.corrodinggames.rts.gameFramework.GlobalState.b("layerBuffer:" + this.layerRenderer + "x" + this.layerRenderer + " = " + this.layerRenderer * this.layerRenderer + (com.corrodinggames.rts.game.map.MapEngine.I ? " x2 for soft fade " : ""));
            this.d = new MapLayerRenderer[this.layerRenderer][this.layerRenderer];
            n3 = 0;
            if (l2.bQ.renderFancyWater) {
                n3 = 1;
            }
            if (com.corrodinggames.rts.gameFramework.GlobalState.C() || com.corrodinggames.rts.gameFramework.GlobalState.D()) {
                n3 = 1;
            }
            if (this.h <= 0) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("layerBuffer buffer size was too small at: " + this.h);
                this.h = 512;
            }
            this.renderWidth = n3 != 0 ? l2.bO.a(this.h, this.h, true) : l2.bO.a(this.h, this.h, false);
            this.renderWidth.b(true);
            this.renderHeight = l2.bO.b(this.renderWidth);
            this.f();
            long l4 = System.nanoTime();
            com.corrodinggames.rts.gameFramework.GlobalState.e("----- layerBuffers create in:" + (double)(l4 - l3) / 1000000.0 + " ms");
        }
    }

    public void c(int n2) {
        if (n2 < this.layerRenderer) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("newLayerBufferCount:" + n2);
            return;
        }
        MapLayerRenderer[][] dArray = new MapLayerRenderer[n2][n2];
        for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
            for (int i3 = 0; i3 < this.layerRenderer; ++i3) {
                dArray[i2][i3] = this.d[i2][i3];
            }
        }
        this.d = dArray;
        this.layerRenderer = n2;
        this.f();
    }

    public void e() {
        com.corrodinggames.rts.game.map.MapEngine.I = false;
        com.corrodinggames.rts.game.map.MapEngine.J = true;
        for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
            for (int i3 = 0; i3 < this.layerRenderer; ++i3) {
                MapLayerRenderer d2 = this.d[i2][i3];
                if (d2 == null) continue;
                if (d2.startTileX != null) {
                    d2.startTileX.q();
                    d2.startTileX = null;
                }
                if (d2.offsetY == null) continue;
                d2.offsetY.o();
                d2.offsetY = null;
            }
        }
    }

    public void f() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        ArrayList<MapLayerRenderer> arrayList = null;
        boolean bl = false;
        for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
            for (int i3 = 0; i3 < this.layerRenderer; ++i3) {
                MapLayerRenderer d2 = this.d[i2][i3];
                if (d2 != null) continue;
                d2 = new MapLayerRenderer(this, i2, i3);
                d2.renderWidth = this.p++;
                this.d[i2][i3] = d2;
                if (this.h <= 0) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("initMissingLayerBufferImages: layerBuffer buffer size was too small at: " + this.h);
                    this.h = 512;
                }
                d2.offsetX = bl ? l2.bO.r() : (l2.bQ.renderFancyWater ? l2.bO.a(this.h, this.h, true) : l2.bO.a(this.h, this.h, false));
                d2.offsetX.b(true);
                if (d2.offsetX.A()) {
                    if (!bl) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("initMissingLayerBufferImages: Failed to create map buffer at :" + this.h + "px");
                    }
                    d2.mapEngine = new NullSpriteBatchBackend();
                } else {
                    try {
                        d2.mapEngine = l2.bO.b(d2.offsetX);
                    }
                    catch (OutOfMemoryError outOfMemoryError) {
                        if (!bl) {
                            com.corrodinggames.rts.gameFramework.GlobalState.a(ResourceDomainEnum.b, (Throwable)outOfMemoryError);
                        }
                        bl = true;
                        d2.mapEngine = new NullSpriteBatchBackend();
                    }
                }
                if (arrayList == null) {
                    arrayList = new ArrayList<MapLayerRenderer>();
                }
                arrayList.add(d2);
            }
        }
        if (bl && com.corrodinggames.rts.game.map.MapEngine.I) {
            this.e();
        }
        if (arrayList != null) {
            for (MapLayerRenderer d3 : arrayList) {
                if (!com.corrodinggames.rts.game.map.MapEngine.I) continue;
                try {
                    d3.a();
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.e();
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Not enough free memory to enable smooth fog fading");
                    System.gc();
                    break;
                }
            }
        }
        this.a();
    }

    public float g() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.cX > 1.0f) {
            return 1.0f;
        }
        return l2.cX;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(float f2) {
        float f3;
        float f4;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        Long l3 = null;
        boolean bl = false;
        float f5 = this.g();
        boolean bl2 = false;
        float f6 = f5 / this.m;
        if (GameUtils.c(f6 - 1.0f) < 0.01f) {
            f6 = 1.0f;
        }
        if ((double)f5 > 0.6) {
            float f7 = 0.3f;
            if (com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                f7 = 0.1f;
            }
            if (f5 - this.m > f7) {
                bl2 = true;
            }
            if (f5 == 1.0f && this.m != 1.0f) {
                bl2 = true;
            }
        }
        if (f6 != 1.0f) {
            int n2 = 10;
            f4 = 0.03f;
            f3 = 0.03f;
            if (f5 < 0.3f) {
                n2 = 20;
                f4 = 0.09f;
            } else if (f5 < 0.5f) {
                n2 = 20;
                f4 = 0.07f;
            }
            if (f5 > 1.3f) {
                n2 = 7;
            }
            if (!com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                n2 += 10;
            }
            if (GameUtils.c(b2.aj - f5) > f3) {
                b2.aj = l2.cX;
                b2.ak = 0;
            } else {
                ++b2.ak;
            }
            if (b2.ak < 3) {
                b2.ai = 0.0f;
            } else if (GameUtils.c(f5 - this.m) > f4) {
                b2.ai += 1.0f;
            }
            if (b2.ai > (float)n2) {
                b2.ai = 0.0f;
                bl2 = true;
            }
        }
        if ((float)l2.cu + l2.cA + 4.0f > (float)(this.f + this.layerRenderer * this.k)) {
            this.f += this.k;
            this.a(1);
        }
        if (l2.cu - 1 < this.f) {
            this.f -= this.k;
            this.a(-1);
        }
        if ((float)l2.cv + l2.cB + 4.0f > (float)(this.g + this.layerRenderer * this.k)) {
            this.g += this.k;
            this.a(1);
        }
        if (l2.cv - 1 < this.g) {
            this.g -= this.k;
            this.a(-1);
        }
        if ((float)l2.cu + l2.cA + 4.0f > (float)(this.f + this.layerRenderer * this.k)) {
            bl2 = true;
        }
        if (l2.cu - 1 < this.f) {
            bl2 = true;
        }
        if ((float)l2.cv + l2.cB + 4.0f > (float)(this.g + this.layerRenderer * this.k)) {
            bl2 = true;
        }
        if (l2.cv - 1 < this.g) {
            bl2 = true;
        }
        if (bl2) {
            this.a();
        }
        if (GameUtils.c((f6 = l2.cX / this.m) - 1.0f) < 1.0E-4f) {
            f6 = 1.0f;
        }
        float f8 = l2.cF / f6 + 2.0f;
        f4 = l2.cH / f6 + 2.0f;
        if (f6 != 1.0f) {
            l2.bO.k();
            l2.bO.b(f6, f6);
            b2.ao.a(l2.cK);
            b2.ao.c = (int)((float)b2.ao.a + (float)b2.ao.b() / f6) + 2;
            b2.ao.d = (int)((float)b2.ao.b + (float)b2.ao.c() / f6) + 2;
            l2.bO.a(b2.ao);
        }
        f3 = ((float)this.f - l2.cw) * this.m;
        float f9 = ((float)this.g - l2.cx) * this.m;
        float f10 = (int)f3;
        float f11 = (int)f9;
        int n3 = 0;
        boolean bl3 = false;
        if (com.corrodinggames.rts.gameFramework.GlobalState.av() && (double)l2.cX < 0.3) {
            bl3 = true;
        }
        this.e.a(bl3);
        this.e.d(bl3);
        this.e.b(bl3);
        boolean bl4 = false;
        try {
            for (int i2 = 0; i2 < this.layerRenderer; ++i2) {
                for (int i3 = 0; i3 < this.layerRenderer; ++i3) {
                    int n4;
                    MapLayerRenderer d2 = this.d[i2][i3];
                    int n5 = (int)(f10 + (float)(i2 * this.k) * this.m);
                    int n6 = (int)(f11 + (float)(i3 * this.k) * this.m);
                    if (d2.currentLayer && !this.n) {
                        ++d2.currentTileset;
                    }
                    d2.globalState.a(n5 + 1, n6 + 1, n5 + this.h - 2, n6 + this.h - 2);
                    if (!((float)d2.globalState.a <= f8) || !((float)d2.globalState.b <= f4)) continue;
                    if ((float)d2.globalState.c > f8) {
                        d2.globalState.c = (int)f8;
                    }
                    if ((float)d2.globalState.d > f4) {
                        d2.globalState.d = (int)f4;
                    }
                    int n7 = (int)((0.0f - l2.cw) * this.m);
                    int n8 = (int)((0.0f - l2.cx) * this.m);
                    int n9 = (int)((b2.i() - l2.cw) * this.m);
                    int n10 = (int)((b2.j() - l2.cx) * this.m);
                    if (d2.globalState.a < n7) {
                        d2.globalState.a = n7;
                    }
                    if (d2.globalState.b < n8) {
                        d2.globalState.b = n8;
                    }
                    if (d2.globalState.c > n9) {
                        d2.globalState.c = n9;
                    }
                    if (d2.globalState.d > n10) {
                        d2.globalState.d = n10;
                    }
                    if (d2.globalState.a()) continue;
                    boolean bl5 = false;
                    boolean bl6 = true;
                    if (d2.tileHeight) {
                        bl5 = true;
                        bl6 = false;
                    }
                    if (d2.currentLayer) {
                        n4 = 10;
                        if (n3 > 3) {
                            n4 += 2;
                        }
                        if (n3 > 6) {
                            n4 += 2;
                        }
                        if (d2.currentTileset > n4) {
                            d2.currentTileset = 0;
                            bl5 = true;
                            ++n3;
                        }
                    }
                    if (bl5) {
                        bl = true;
                        n4 = 0;
                        long l4 = ExtraManager.a();
                        if (l3 == null) {
                            l3 = l4;
                        } else {
                            int n11 = 200;
                            if (this.n) {
                                n11 = 30;
                            }
                            if (ExtraManager.a(l3, l4) > (double)n11) {
                                n4 = 1;
                                this.n = true;
                            }
                        }
                        if (n4 != 0 && d2.tileHeight && !d2.viewRect) {
                            this.b(i2, i3);
                        }
                        if (n4 == 0) {
                            if (com.corrodinggames.rts.game.map.MapEngine.I) {
                                if (d2.offsetY != null && d2.offsetY.p != d2.offsetX.p) {
                                    com.corrodinggames.rts.gameFramework.GlobalState.e("wrong sized fadeOutBitmap width:" + d2.offsetY.p + " vs " + d2.offsetX.p);
                                    d2.offsetY.o();
                                    d2.offsetY = null;
                                }
                                if (d2.offsetY == null) {
                                    try {
                                        d2.a();
                                    }
                                    catch (OutOfMemoryError outOfMemoryError) {
                                        outOfMemoryError.printStackTrace();
                                        com.corrodinggames.rts.gameFramework.GlobalState.a(ResourceDomainEnum.b, (Throwable)outOfMemoryError);
                                        this.e();
                                        com.corrodinggames.rts.gameFramework.GlobalState.b("Not enough free memory to keep smooth fog fading");
                                        System.gc();
                                    }
                                    if (com.corrodinggames.rts.game.map.MapEngine.I && d2.offsetY == null) {
                                        l2.i("Disabling smooth fog fading due to error");
                                        this.e();
                                        com.corrodinggames.rts.gameFramework.GlobalState.b("fadeOutBitmap == null");
                                        System.gc();
                                    }
                                }
                            }
                            if (com.corrodinggames.rts.game.map.MapEngine.I) {
                                if (d2.startTileY > 0.0f) {
                                    // empty if block
                                }
                                com.corrodinggames.rts.gameFramework.rendering.Texture e2 = d2.offsetX;
                                d2.offsetX = d2.offsetY;
                                d2.offsetY = e2;
                                TextureManagerInterface y2 = d2.mapEngine;
                                d2.mapEngine = d2.startTileX;
                                d2.startTileX = y2;
                                d2.startTileY = bl6 ? 1.0f : 0.0f;
                            } else {
                                d2.startTileY = 0.0f;
                            }
                            if (com.corrodinggames.rts.gameFramework.GlobalState.at() && !bl4) {
                                com.corrodinggames.rts.game.map.MapEngine.a();
                                bl4 = true;
                            }
                            l2.bO.i();
                            this.c(i2, i3);
                            l2.bO.j();
                            // v19.115w: 02b FernFlower 幻觉清理 (javap MapEngine 无 a 静态字段)
                        }
                    }
                    d2.dirtyRect.a(d2.globalState);
                    d2.dirtyRect.a(-n5, -n6);
                    d2.q.a(d2.globalState);
                    d2.q.a(-f10, -f11);
                    d2.q.a(f3, f9);
                    if (d2.startTileY > 0.0f) {
                        // v19.115w: 02b Paint.a(boolean)/c(int) 反编译幻觉 — 删除 (endTileX alpha 设置)
                        l2.bO.loadImageFromResource(d2.offsetY, d2.dirtyRect, d2.q, (Paint)this.e);
                        if ((double)d2.startTileY < 0.98) {
                            l2.bO.loadImageFromResource(d2.offsetX, d2.dirtyRect, d2.q, d2.endTileX);
                        }
                        d2.startTileY -= 0.1f * f2;
                        continue;
                    }
                    if (d2.offsetX.A()) {
                        l2.bO.a(d2.offsetX, d2.q, (Paint)this.e, 0.0f, 0.0f, 0, 0);
                        continue;
                    }
                    l2.bO.loadImageFromResource(d2.offsetX, d2.dirtyRect, d2.q, (Paint)this.e);
                }
            }
        }
        finally {
            if (bl4) {
                com.corrodinggames.rts.game.map.MapEngine.b();
            }
        }
        if (f6 != 1.0f) {
            l2.bO.l();
        }
        if (!bl) {
            this.n = false;
        }
    }
}