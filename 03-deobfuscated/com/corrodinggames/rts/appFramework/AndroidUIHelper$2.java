/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 */
package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;

import com.corrodinggames.rts.appFramework.s;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class AndroidUIHelper$2
implements DialogInterface.OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ GlobalState b;
    final /* synthetic */ Runnable c;

    AndroidUIHelper$2(Activity activity, GlobalState l2, Runnable runnable) {
        this.a = activity;
        this.b = l2;
        this.c = runnable;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        com.corrodinggames.rts.gameFramework.filesystem.FileAccessFlags b2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(true);
        if (!b2.b) {
            GlobalState.e("Storage setup: Not using SAF, not showing setup folder popup");
            boolean bl = AndroidUIHelper.b(this.a);
            if (bl) {
                this.b.bQ.storageType = 2;
                this.b.bQ.hasSelectedAStorageType = true;
                com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b();
                this.b.bQ.save();
            }
            return;
        }
        if (this.a instanceof s) {
            GlobalState.e("Storage setup: Already on settings page");
            s s2 = (s)this.a;
            s2.l();
            return;
        }
        Intent intent = new Intent((Context)this.a, s.class);
        intent.putExtra("mode", "setupExternalFolder");
        AndroidUIHelper.a(intent);
        this.a.a(intent);
        if (this.a instanceof GameActivity) {
            if (this.c != null) {
                AndroidUIHelper$2$1 c$2$1 = new AndroidUIHelper$2$1(this);  // v19.133f3: c$2$1 幻觉名修正
                ((GameActivity)this.a).a(c$2$1);
            }
        } else {
            GlobalState.b("context not instance CommonActivity");
        }
    }
}
