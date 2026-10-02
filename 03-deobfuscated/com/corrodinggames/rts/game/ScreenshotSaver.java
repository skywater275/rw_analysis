/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import android.graphics.Bitmap;
import android.graphics.Bitmap$CompressFormat;
import com.corrodinggames.rts.game.GameEngine;
import java.io.File;
import java.io.FileOutputStream;

class ScreenshotSaver
implements Runnable {
    public Bitmap a;
    public int b;
    /* synthetic 字段: 字节码 game/k.class 为 ACC_FINAL+ACC_SYNTHETIC 但无 <init> 构造器、
       无 putfield 赋值路径 (c 恒为 null), 去 final 匹配 javap 事实 (同 GLTextureRegion 模式) */
    GameEngine c;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public strictfp void run() {
        ScreenshotSaver k2 = this;
        synchronized (k2) {
            try {
                File file = new File(this.c.h + "image_" + String.format("%07d", this.b) + ".jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                this.a.a(Bitmap$CompressFormat.a, 85, fileOutputStream);
                fileOutputStream.close();
            }
            catch (Exception exception) {
                exception.printStackTrace();
                this.c.bo = false;
                this.c.a("Error saving jpg, recording has stopped. Is there free space remaining on the SD card?", 1);
            }
        }
    }
}
