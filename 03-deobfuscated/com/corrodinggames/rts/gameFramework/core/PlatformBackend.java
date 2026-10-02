/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.core;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.core.FilePickerCallback;
import java.io.File;

public class PlatformBackend {
    public String a() {
        return null;
    }

    public boolean b() {
        return true;
    }

    public void a(File file) {
        GlobalState.e("PlatformExtensionBackend:shareFile");
        GlobalState.e("abstract shareFile:" + file.getPath());
    }

    public void a(FilePickerCallback b2) {
    }

    public float c() {
        return 0.0f;
    }
}
