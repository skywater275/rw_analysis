/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.List;
import java.util.concurrent.Callable;

final class UnitTrait$1
implements Callable {
    final /* synthetic */ String a;
    final /* synthetic */ List b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;

    UnitTrait$1(String string, List list, boolean bl, boolean bl2) {
        this.a = string;
        this.b = list;
        this.c = bl;
        this.d = bl2;
    }

    public MasterServerResult a() {  // 02b n$1.java L27: t=MasterServerResult (j/t)
        try {
            WebAPIClient.a("Running doSingleRequest:" + this.a);  // 02b n$1 L29
            return WebAPIClient.a(this.b, this.a, this.c);  // 02b n$1 L30
        }
        catch (Exception exception) {
            GlobalState.e("Error on doSingleRequest:" + this.a + " - " + exception.getMessage());
            if (this.d) {
                exception.printStackTrace();
            }
            return null;
        }
    }

    public /* synthetic */ Object call() {
        return this.a();
    }
}
