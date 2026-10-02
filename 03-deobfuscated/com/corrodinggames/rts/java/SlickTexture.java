/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.ImageBuffer
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.opengl.ImageData
 */
package com.corrodinggames.rts.java;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

import android.graphics.Color;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.FileWatcher;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.Slick2DRenderer;
import com.corrodinggames.rts.java.SlickImageData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;

public class SlickTexture
extends Texture {
    public static ArrayList y;
    private Image x;
    ImageData z;
    ByteBuffer A;
    int B;
    String C;
    boolean D = false;
    int E = 1;
    static boolean F;
    boolean G = false;
    boolean H;
    boolean I = false;
    long J = -1L;
    static int K;

    public Image C() {
        return this.x;
    }

    private void a(ImageData imageData) {
        this.z = imageData;
        this.A = this.z instanceof ImageBuffer ? ByteBuffer.wrap(((ImageBuffer)imageData).getRGBA()) : imageData.getImageBufferData();
        this.B = imageData.getDepth() / 8;
        if (this.B == 4) {
            int n = imageData.getTexHeight();
            int n2 = imageData.getTexWidth();
            int n3 = imageData.getHeight();
            int n4 = imageData.getWidth();
            for (int i = 0; i < n3; ++i) {
                for (int j = 0; j < n4; ++j) {
                    int n5 = j * 4 + i * n2 * 4;
                    try {
                        if (this.A.get(n5 + 3) != 0) continue;
                        this.A.put(n5 + 0, (byte)0);
                        this.A.put(n5 + 1, (byte)0);
                        this.A.put(n5 + 2, (byte)0);
                        continue;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw new IndexOutOfBoundsException("offset:" + n5 + " x:" + j + " y:" + i + " height:" + n3 + " width:" + n4 + " texHeight:" + n + " texWidth:" + n2);
                    }
                }
            }
        }
        if (F) {
            // empty if block
        }
    }


    public String a() {
        if (this.name != null) {
            return this.name;
        }
        return this.C;
    }

    public SlickTexture() {
        if (y == null) {
            y = new ArrayList();
        }
        y.add(this);
    }

    public void D() {
        Image image;
        if (this.z == null && this.x != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("reloadFromImageData: slickImageData==null and slickImage!=null. Ignoring");
            return;
        }
        if (this.x != null) {
            try {
                this.x.destroy();
            }
            catch (SlickException slickException) {
                throw new RuntimeException(slickException);
            }
        }
        if ((image = null) == null) {
            image = new Image(this.z);
        }
        this.x = image;
        this.E();
        this.p = this.x.getWidth();
        this.q = this.x.getHeight();
        this.g();
        this.v();
    }

    public void a(Image image, String string) {
        if (this.x != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("this.slickImage!=null");
        }
        this.x = image;
        this.E();
        this.p = this.x.getWidth();
        this.q = this.x.getHeight();
        this.g();
        this.C = string;
        if (this.C != null) {
            File file = new File(this.C);
            if (!file.exists()) {
                throw new RuntimeException(this.C + " does not exist");
            }
            this.J = this.c(true);
        }
    }

    public void a(ImageData imageData, String string, boolean bl) {
        this.a(imageData);
        if (!bl) {
            this.D();
        }
        this.C = string;
        if (this.C != null && !this.C.contains(".rwmod")) {
            File file = new File(this.C);
            if (!file.exists()) {
                throw new RuntimeException(this.C + " does not exist");
            }
            this.J = this.c(true);
        }
        this.g();
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byteBuffer.rewind();
        byteBuffer2.put(byteBuffer);
        byteBuffer.rewind();
        byteBuffer2.flip();
        return byteBuffer2;
    }


    public Texture a(int n2, int n3, boolean bl) {
        SlickTexture s2 = new SlickTexture();
        ImageBuffer imageBuffer = new ImageBuffer(n2, n3);
        String string = null;
        s2.a((ImageData)imageBuffer, string, true);
        if (bl) {
            boolean bl2 = true;
            if (bl2 && this.B == 4) {
                this.H();
                ByteBuffer byteBuffer = this.z.getImageBufferData();
                SlickTexture.a(byteBuffer, s2.A);
            } else {
                this.H();
                for (int i = 0; i < n3; ++i) {
                    for (int j = 0; j < n2; ++j) {
                        s2.a(j, i, this.a(j, i));
                    }
                }
            }
        }
        s2.p = n2;
        s2.q = n3;
        s2.g();
        s2.D = true;
        return s2;
    }


    public void i() {
        if (!this.G) {
            this.j();
        } else if (F && this.A == null) {
            this.j();
        }
    }

    @Override
    public void x() {
        this.H();
    }

    @Override
    public void y() {
    }


    public void j() {
        this.G = true;
        if (this.H) {
            this.G();
            throw new RuntimeException("Cannot buffer pixels, we have discarded the PixelBuffer");
        }
        this.H();
    }

    public void H() {
        if (F && this.A == null) {
            this.J();
        }
    }

    @Override
    public void q() {
        this.I();
    }

    public void I() {
        if (F) {
            this.z = null;
            this.A = null;
        }
    }

    public void J() {
        if (this.x == null) {
            throw new RuntimeException("Cannot buffer pixels, we have no slickImage");
        }
        long l2 = ExtraManager.a();
        SlickImageData t2 = new SlickImageData(this, this.x);
        this.z = t2;
        this.A = ByteBuffer.wrap(t2.a());
        double d = ExtraManager.a(l2);
        com.corrodinggames.rts.gameFramework.GlobalState.e("Recreating image data from texture: " + this.a() + " (" + ExtraManager.a(d) + ")");
    }

    @Override
    public boolean k() {
        if (this.z == null) {
            return F && this.x != null;
        }
        return true;
    }


    public int a(int n2, int n3) {
        try {
            return Slick2DRenderer.a(this.z, this.A, n2, n3, this.B);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            String string = "getPixel out of bounds";
            string = string + " (x:" + n2 + " y:" + n3 + " perPixel:" + this.B + " TexWidth:" + this.z.getTexWidth() + " TexHeight:" + this.z.getTexHeight() + " imageByteBuffer:" + this.A.limit() + " width:" + this.p + " height:" + this.q + ")";
            throw new RuntimeException(string, indexOutOfBoundsException);
        }
    }


    public void a(int n2, int n3, int n4) {
        try {
            Slick2DRenderer.a(this.z, this.A, n2, n3, Color.b(n4), Color.c(n4), Color.d(n4), Color.a(n4), this.B);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            String string = "setPixel out of bounds";
            string = string + " (x:" + n2 + " y:" + n3 + " perPixel:" + this.B + " TexWidth:" + this.z.getTexWidth() + " TexHeight:" + this.z.getTexHeight() + " imageByteBuffer:" + this.A.limit() + " width:" + this.p + " height:" + this.q + ")";
            throw new RuntimeException(string, indexOutOfBoundsException);
        }
    }


    public void p() {
        if (!this.G) {
            return;
        }
        this.G = false;
        this.D();
    }


    public void r() {
    }

    @Override
    public void s() {
        this.H = true;
        this.z = null;
        this.A = null;
    }

    @Override
    public void n() {
        this.I = true;
    }

    @Override
    public void o() {
        this.z = null;
        this.A = null;
        if (y != null) {
            y.remove(this);
        }
        if (this.x != null) {
            try {
                this.x.destroy();
            }
            catch (SlickException slickException) {
                slickException.printStackTrace();
            }
            this.x = null;
        }
    }

    protected void finalize() {
        if (this.I) {
            this.o();
        }
        if (this.x != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("SlickBitmapOrTexture: Leak detection: finalize called with slickImage!=null");
        }
    }


    public Texture h() {
        return this.a(this.p, this.q, true);
    }

    @Override
    protected void e() {
        this.E();
    }

    public void E() {
        if (this.x == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("slickImage==null");
            com.corrodinggames.rts.gameFramework.GlobalState.T();
        }
        if (!this.o) {
            this.E = 2;
            this.x.setFilter(2);
        } else {
            this.E = 1;
            this.x.setFilter(1);
        }
    }

    public void F() {
        Object object;
        if (this.D) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reloadImage: skipping cloned image:" + this.C);
            return;
        }
        if (this.C == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reloadImage: filepath is null, skipping");
            return;
        }
        try {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reloadImage: reloading:" + this.C);
            if (this.x != null) {
                this.x.destroy();
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("slickImage==null cannot free");
            }
            ++K;
            object = new FileInputStream(this.C);
            try {
                this.x = new Image((InputStream)object, "reload_" + K, false);
            }
            catch (NullPointerException nullPointerException) {
                throw new IOException("Failed to reload", nullPointerException);
            }
            this.v();
            this.E();
            this.p = this.x.getWidth();
            this.q = this.x.getHeight();
            this.g();
            ((InputStream)object).close();
        }
        catch (SlickException slickException) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reloadImage: failed");
            slickException.printStackTrace();
        }
        catch (IOException iOException) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reloadImage: failed");
            iOException.printStackTrace();
        }
        object = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (object != null && ((GlobalState) object).bL != null) {
            ((GlobalState) object).bL.g();
        }
    }

    public long c(boolean bl) {
        if (this.C == null) {
            return -2L;
        }
        return new File(this.C).lastModified();
    }

    @Override
    public void t() {
        long l2 = this.c(false);
        if (this.J == -1L) {
            this.J = l2;
        } else if (this.J != l2) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reloadImage: '" + this.C + "' reloading current now:" + l2);
            this.F();
            this.J = l2;
        }
    }

    public void G() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("SlickBitmapOrTexture: " + this.a());
        com.corrodinggames.rts.gameFramework.GlobalState.e(" className:" + this.getClass().getSimpleName());
        com.corrodinggames.rts.gameFramework.GlobalState.e(" filepath:" + this.C);
        com.corrodinggames.rts.gameFramework.GlobalState.e(" slickImage:" + (this.x != null));
        com.corrodinggames.rts.gameFramework.GlobalState.e(" cloned:" + this.D);
    }

    @Override
    public /* synthetic */ Object clone() {
        return this.h();
    }

    static {
        F = true;
        K = 1000;
    }
}
