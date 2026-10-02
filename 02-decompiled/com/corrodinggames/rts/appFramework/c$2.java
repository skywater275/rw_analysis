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
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.c$2$1;
import com.corrodinggames.rts.appFramework.s;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.l;

final class c$2
implements DialogInterface.OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ l b;
    final /* synthetic */ Runnable c;

    c$2(Activity activity, l l2, Runnable runnable) {
        this.a = activity;
        this.b = l2;
        this.c = runnable;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        com.corrodinggames.rts.gameFramework.e.b b2 = com.corrodinggames.rts.gameFramework.e.a.a(true);
        if (!b2.b) {
            l.e("Storage setup: Not using SAF, not showing setup folder popup");
            boolean bl = com.corrodinggames.rts.appFramework.c.b(this.a);
            if (bl) {
                this.b.bQ.storageType = 2;
                this.b.bQ.hasSelectedAStorageType = true;
                com.corrodinggames.rts.gameFramework.e.a.b();
                this.b.bQ.save();
            }
            return;
        }
        if (this.a instanceof s) {
            l.e("Storage setup: Already on settings page");
            s s2 = (s)this.a;
            s2.l();
            return;
        }
        Intent intent = new Intent((Context)this.a, s.class);
        intent.putExtra("mode", "setupExternalFolder");
        com.corrodinggames.rts.appFramework.c.a(intent);
        this.a.a(intent);
        if (this.a instanceof b) {
            if (this.c != null) {
                c$2$1 c$2$1 = new c$2$1(this);
                ((b)this.a).a(c$2$1);
            }
        } else {
            l.b("context not instance CommonActivity");
        }
    }
}
