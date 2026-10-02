/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.res.AssetFileDescriptor
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AssetStream
extends InputStream {
    InputStream a;
    String b;
    String c;
    boolean d;
    String e;

    public boolean a() {
        if (this.a instanceof FileInputStream) {
            return true;
        }
        return !GlobalState.av() && this.c != null;
    }

    public FileDescriptor b() throws IOException {
        if (this.a instanceof FileInputStream) {
            FileInputStream fileInputStream = (FileInputStream)this.a;
            return fileInputStream.getFD();
        }
        if (!GlobalState.av() && this.c != null) {
            Context context = AndroidUIHelper.a();
            AssetManager assetManager = context.d();
            AssetFileDescriptor assetFileDescriptor = assetManager.b(this.c);
            return assetFileDescriptor.getFileDescriptor();
        }
        throw new RuntimeException("AssetInputStream: unexpected stream for: " + this.b);
    }

    private AssetStream() {
    }

    public AssetStream(InputStream inputStream, String string, String string2) throws FileNotFoundException {
        if (inputStream == null) {
            throw new FileNotFoundException();
        }
        this.a = inputStream;
        this.b = string;
        this.c = string2;
        this.e = GlobalState.U();
    }

    public AssetStream(FileInputStream fileInputStream, String string) throws FileNotFoundException {
        if (fileInputStream == null) {
            throw new FileNotFoundException();
        }
        this.a = fileInputStream;
        this.b = string;
        this.e = GlobalState.U();
    }

    public AssetStream(InputStream inputStream, String string) throws FileNotFoundException {
        if (inputStream == null) {
            throw new FileNotFoundException();
        }
        this.a = inputStream;
        this.b = string;
        this.e = GlobalState.U();
    }

    public long c() {
        if (!GlobalState.av()) {
            return -1L;
        }
        if (this.b == null) {
            return -2L;
        }
        File file = new File(this.b);
        return file.lastModified();
    }

    public String d() {
        return this.b;
    }

    @Override
    public int available() throws IOException {
        return this.a.available();
    }

    @Override
    public void close() throws IOException {
        this.d = true;
        this.a.close();
    }

    protected void finalize() {
        if (!this.d) {
            GlobalState.b("AssetInputStream was finalized with being closed");
            GlobalState.b(this.e);
        }
    }

    public boolean equals(Object object) {
        return this.a.equals(object);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override
    public void mark(int n2) {
        this.a.mark(n2);
    }

    @Override
    public boolean markSupported() {
        return this.a.markSupported();
    }

    @Override
    public int read() throws IOException {
        return this.a.read();
    }

    @Override
    public int read(byte[] byArray, int n2, int n3) throws IOException {
        return this.a.read(byArray, n2, n3);
    }

    @Override
    public int read(byte[] byArray) throws IOException {
        return this.a.read(byArray);
    }

    @Override
    public void reset() throws IOException {
        this.a.reset();
    }

    @Override
    public long skip(long l2) throws IOException {
        return this.a.skip(l2);
    }

    public String toString() {
        return this.a.toString();
    }
}
