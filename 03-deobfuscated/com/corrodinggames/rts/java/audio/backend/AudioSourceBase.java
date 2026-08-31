/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AudioSourceBase {
    protected InputStream a;
    protected File b;
    protected String c;

    public AudioSourceBase(String string) {
        this.b = new File(string);
        this.c = this.b.getName();
    }

    public AudioSourceBase(InputStream inputStream, String string) {
        this.a = inputStream;
        this.c = string;
        if (this.a == null) {
            throw new RuntimeException("inputStream==null");
        }
    }

    public InputStream a() {
        if (this.a != null) {
            return this.a;
        }
        try {
            return new FileInputStream(this.b);
        }
        catch (FileNotFoundException fileNotFoundException) {
            throw new RuntimeException(fileNotFoundException);
        }
    }

    public String b() {
        String string = this.c;
        int n = string.lastIndexOf(46);
        if (n == -1) {
            return "";
        }
        return string.substring(n + 1);
    }
}
