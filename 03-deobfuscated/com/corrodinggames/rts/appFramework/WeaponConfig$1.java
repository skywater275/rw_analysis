/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class WeaponConfig$1
implements DialogInterface.OnClickListener {
    final /* synthetic */ GlobalState a;
    final /* synthetic */ Runnable b;

    WeaponConfig$1(GlobalState l2, Runnable runnable) {
        this.a = l2;
        this.b = runnable;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        this.a.bQ.storageType = 1;
        this.a.bQ.hasSelectedAStorageType = true;
        com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b.a();
        this.a.bQ.save();
        if (this.b != null) {
            this.b.run();
        }
    }
}
