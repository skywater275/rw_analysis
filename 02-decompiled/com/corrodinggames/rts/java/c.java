/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.b;
import org.lwjgl.opengl.Display;

public class c
extends Thread {
    final /* synthetic */ b a;

    public c(b b2) {
        this.a = b2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            Display.processMessages();
        }
    }
}
