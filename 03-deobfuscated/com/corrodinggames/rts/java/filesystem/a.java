/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.ImageBuffer
 *  org.newdawn.slick.opengl.ImageData
 *  org.newdawn.slick.opengl.Texture
 *  org.newdawn.slick.opengl.TextureImpl
 *  org.newdawn.slick.opengl.renderer.Renderer
 *  org.newdawn.slick.opengl.renderer.SGL
 */
package com.corrodinggames.rts.java.filesystem;

import android.graphics.Rect;
import android.graphics.RectF;
import com.LibRocket$CompiledGeometry;
import com.LibRocket$TextureHolder;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import com.corrodinggames.rts.java.filesystem.b;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.opengl.ImageData;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class a
extends com.corrodinggames.librocket.LibRocketBridge {
    private static SGL k = Renderer.get();
    Graphics j;

    public a() {
        super();
    }

    public void a() {
        throw new RuntimeException("startNewFrame() not supported on SlickLibRocket");
    }

    public void a(Graphics graphics) {
        this.j = graphics;
        super.a();
    }

    @Override
    public boolean GenerateTexture(int n2, byte[] byArray) {
        try {
            b b2 = (b)this.findTextureHolder(n2);
            ImageBuffer imageBuffer = new ImageBuffer(b2.width, b2.height);
            byte[] byArray2 = imageBuffer.getRGBA();
            for (int i = 0; i < byArray.length; ++i) {
                byArray2[i] = byArray[i];
            }
            b2.h = new Image((ImageData)imageBuffer);
            if (b2.h == null) {
                throw new RuntimeException("slickTextureHolder.image==null");
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            GlobalState.a(com.corrodinggames.rts.gameFramework.ResourceDomainEnum.g, (Throwable)outOfMemoryError);
            return false;
        }
        catch (Throwable throwable) {
            ScriptEngine.throwDelayedException("GenerateTexture Failed", throwable);
            return true;
        }
        return true;
    }

    @Override
    public void RenderGeometryPossiblyCompiled(float[] fArray, float[] fArray2, int[] nArray, int[] nArray2, int n2, float f2, float f3, LibRocket$CompiledGeometry libRocket$CompiledGeometry) {
        try {

            float f4;
            int n3;
            if (this.debug) {
                System.out.println("SlickLibRocket:RenderGeometry(" + fArray.length + "," + n2 + ")");
                System.out.println("indices.length=" + nArray2.length + "");
            }
            b b2 = null;
            if (n2 != 0) {
                b2 = (b)this.findTextureHolder(n2);
            }
            RectF rectF = null;
            if (libRocket$CompiledGeometry != null) {
                rectF = (RectF)libRocket$CompiledGeometry.bbox;
            }
            if (rectF == null) {
                rectF = new RectF();
                for (int j = 0; j < nArray2.length; j += 3) {
                    n3 = nArray2[j];
                    for (int i2 = 0; i2 <= 2; ++i2) {
                        n3 = nArray2[j + i2];
                        float f5 = fArray[n3 * 2 + 0];
                        f4 = fArray[n3 * 2 + 1];
                        if (rectF.a()) {
                            rectF.a(f5, f4, f5 + 1.0f, f4 + 1.0f);
                            continue;
                        }
                        rectF.c(f5, f4);
                    }
                }
                rectF.g();
                if (libRocket$CompiledGeometry != null) {
                    libRocket$CompiledGeometry.bbox = rectF;
                }
            }
            RectF rectF2 = new RectF(rectF);
            rectF2.a(f2, f3);
            if (this.h && !GameUtils.a(rectF2, this.g)) {
                n3 = 1;
                if (b2 != null && b2.h == null && b2.b && b2.f == null) {
                    n3 = 0;
                }
                if (n3 != 0) {
                    return;
                }
            }
            if (b2 != null && b2.j != null) {
                System.out.println("Loading image for: " + b2.index);
                b2.h = new Image((ImageData)b2.j);
                if (b2.h == null) {
                    throw new RuntimeException("slickTextureHolder.image==null");
                }
                b2.j = null;
            }
            this.j.pushTransform();
            this.j.setDrawMode(Graphics.MODE_NORMAL);
            this.j.translate(f2, f3);
            float f6 = 1.0f;
            float f7 = 1.0f;
            boolean bl = false;
            f4 = 1.0f;
            boolean bl2 = false;
            org.newdawn.slick.opengl.Texture texture = null;
            if (b2 != null) {
                bl = b2.d;
                f4 = b2.e;
                texture = TextureImpl.getLastBind();
                if (b2.h == null && b2.b) {
                    if (b2.f != null) {
                        com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface object = GlobalState.B().bO;
                        this.j.pushTransform();
                        object.i();
                        float f8 = (float)GlobalState.B().bA / 1000.0f / 10.0f * 360.0f % 360.0f;
                        this.j.translate(-f2, -f3);
                        Rect rect = new Rect(this.f.a, this.f.b, this.f.c, this.f.d);
                        this.j.setClip(null);
                        this.j.setWorldClip(null);
                        object.a(rect);
                        PlayerState n4 = PlayerState.k(0);
                        if (n4 == null) {
                            n4 = PlayerState.i;
                        }
                        UnitRegistry.a(b2.f, rectF2.d(), rectF2.e(), f8, 3.0f, n4, rectF2.c() * 0.6f, rectF2.c(), false, false, 1, null);
                        object.p();
                        f4 = 0.0f;
                        object.j();
                        this.j.popTransform();
                    } else if (this.d < 1) {
                        b2.a();
                        ++this.d;
                    }
                }
                if (b2.h != null) {
                    b2.h.getTexture().bind();
                    f6 = b2.h.getTextureWidth();
                    f7 = b2.h.getTextureHeight();
                    bl2 = true;
                } else if (b2.b) {
                    f4 = 0.1f;
                }
            }
            if (!bl2) {
                TextureImpl.bindNone();
            }
            Color color = new Color(1.0f, 1.0f, 1.0f, f4);
            this.j.setColor(color);
            k.glBegin(4);
            for (int j = 0; j < nArray2.length; j += 3) {
                int n5 = nArray2[j];
                if (!bl) {
                    int n6 = nArray[n5];
                    color.r = (float)(n6 >> 24 & 0xFF) / 255.0f;
                    color.g = (float)(n6 >> 16 & 0xFF) / 255.0f;
                    color.b = (float)(n6 >> 8 & 0xFF) / 255.0f;
                    color.a = (float)(n6 & 0xFF) / 255.0f;
                    color.a *= f4;
                    color.bind();
                }
                for (int i3 = 0; i3 <= 2; ++i3) {
                    n5 = nArray2[j + i3];
                    k.glTexCoord2f(fArray2[n5 * 2 + 0] * f6, fArray2[n5 * 2 + 1] * f7);
                    k.glVertex2f(fArray[n5 * 2 + 0], fArray[n5 * 2 + 1]);
                }
            }
            k.glEnd();
            this.j.popTransform();
        }
        catch (Throwable throwable) {
            ScriptEngine.throwDelayedException("UI Render Failed", throwable);
        }
    }

    @Override
    public LibRocket$TextureHolder getFromTextureHolderFactory() {
        return new b(this);
    }

    @Override
    public void EnableScissorRegion(boolean bl) {
        if (bl) {
            this.j.setWorldClip((float)this.f.a, (float)this.f.b, (float)this.f.b(), (float)this.f.c());
            this.h = true;
        } else {
            this.j.clearWorldClip();
            this.h = false;
        }
    }
}
