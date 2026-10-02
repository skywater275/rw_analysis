/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.filesystem;

import java.io.IOException;
import java.io.InputStream;

class InputStreamHolder {
    public InputStream a;

    public InputStreamHolder(InputStream inputStream) {
        this.a = inputStream;
    }

    public void a() {
        try {
            if (this.a != null) {
                this.a.close();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
