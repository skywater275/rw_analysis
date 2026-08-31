/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;
import com.corrodinggames.rts.game.NetworkPlayer;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.map.MapRenderer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public strictfp class MapLayerRenderer {
    public TextureManagerInterface mapEngine;
    int renderWidth;
    int renderHeight;
    public com.corrodinggames.rts.gameFramework.rendering.Texture offsetX;
    public com.corrodinggames.rts.gameFramework.rendering.Texture offsetY;
    public TextureManagerInterface startTileX;
    public float startTileY;
    public Paint endTileX = new UniquePaint();
    public int endTileY;
    public int tileWidth;
    public boolean tileHeight = true;
    public boolean currentLayer = true;
    public int currentTileset = 0;
    public boolean viewRect = false;
    public final Rect dirtyRect = new Rect();
    public final Rect globalState = new Rect();
    public final RectF q = new RectF();
    public final Rect r = new Rect();
    final /* synthetic */ MapRenderer s;

    public void a() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.offsetY = l2.bO.a(this.offsetX.p, this.offsetX.q, true);
        if (this.offsetY != null && !this.offsetY.A()) {
            this.offsetY.a("fadeOutBitmap");
        }
        if (this.offsetY == null || this.offsetY.A()) {
            throw new OutOfMemoryError("Failed to create fade out bitmap");
        }
        this.offsetY.b(true);
        this.startTileX = l2.bO.b(this.offsetY);
    }

    public Rect b() {
        this.r.a(this.c(), this.d(), this.c() + this.s.i, this.d() + this.s.i);
        return this.r;
    }

    public MapLayerRenderer(MapRenderer c2, int n2, int n3) {
        this.s = c2;
        this.endTileY = n2;
        this.tileWidth = n3;
    }

    public int c() {
        return this.s.f + this.endTileY * this.s.k;
    }

    public int d() {
        return this.s.g + this.tileWidth * this.s.k;
    }
}
