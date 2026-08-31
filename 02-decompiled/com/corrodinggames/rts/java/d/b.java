/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.ImageBuffer
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.opengl.ImageData
 *  org.newdawn.slick.opengl.PNGImageData
 */
package com.corrodinggames.rts.java.d;

import com.corrodinggames.librocket.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.java.d.a;
import com.corrodinggames.rts.java.e;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.PNGImageData;

public class b
extends c {
    Image h;
    boolean i;
    ImageBuffer j;
    final /* synthetic */ a k;

    public b(a a2) {
        this.k = a2;
        super(a2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean a() {
        InputStream inputStream;
        af af2 = ae.a(this.a);
        if (af2 != null) {
            inputStream = af2.b(this.a, true);
            if (inputStream == null) {
                l.g("Failed to open zipped file: " + this.a);
                return false;
            }
        } else {
            try {
                inputStream = new FileInputStream(this.a);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return false;
            }
        }
        try {
            PNGImageData pNGImageData;
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);){
                pNGImageData = new PNGImageData();
                pNGImageData.loadImage((InputStream)bufferedInputStream);
            }
            this.h = new Image((ImageData)pNGImageData);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            l.a(u.g, (Throwable)outOfMemoryError);
            this.h = com.corrodinggames.rts.java.e.r.C();
            this.i = true;
        }
        catch (IOException iOException) {
            l.a("Exception loading image: " + this.a, (Throwable)iOException);
            this.h = com.corrodinggames.rts.java.e.s.C();
            this.i = true;
        }
        catch (UnsupportedOperationException unsupportedOperationException) {
            unsupportedOperationException.printStackTrace();
            l.a("Exception loading image: " + this.a, (Throwable)unsupportedOperationException);
            this.h = com.corrodinggames.rts.java.e.s.C();
            this.i = true;
        }
        this.width = this.h.getWidth();
        this.height = this.h.getHeight();
        if (this.c && (this.width > 500 || this.height > 500)) {
            l.e("Map thumbnail is too large. Size:(" + this.width + "," + this.height + ") (max:500 pixels)");
            this.h = com.corrodinggames.rts.java.e.t.C();
            this.i = true;
            this.width = this.h.getWidth();
            this.height = this.h.getHeight();
        }
        return true;
    }

    @Override
    public void remove() {
        if (this.h != null && !this.i) {
            try {
                this.h.destroy();
            }
            catch (SlickException slickException) {
                slickException.printStackTrace();
            }
        }
        this.a = null;
        this.j = null;
        this.h = null;
        this.i = false;
    }
}
