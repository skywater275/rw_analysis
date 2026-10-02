/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.DesktopGameContainer;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

class RenderBatch$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ByteArrayOutputStream b;
    final /* synthetic */ String c;
    final /* synthetic */ DesktopGameContainer d;

    RenderBatch$1(DesktopGameContainer u2, String string, ByteArrayOutputStream byteArrayOutputStream, String string2) {
        this.d = u2;
        this.a = string;
        this.b = byteArrayOutputStream;
        this.c = string2;
    }

    @Override
    public void run() {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(this.a);){
                fileOutputStream.write(this.b.toByteArray());
            }
            GlobalState.e("Screenshot saved: " + this.c);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            GlobalState.n("Failed to write screenshot:" + exception.getMessage());
        }
    }
}
