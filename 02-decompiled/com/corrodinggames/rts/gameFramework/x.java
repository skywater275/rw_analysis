/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.w;
import java.util.Comparator;

strictfp class x
implements Comparator {
    x() {
    }

    public int a(w w2, w w3) {
        if (w2.em > w3.em) {
            return 1;
        }
        if (w2.em < w3.em) {
            return -1;
        }
        if (w2.en > w3.en) {
            return 1;
        }
        if (w2.en < w3.en) {
            return -1;
        }
        if (w2.ep > w3.ep) {
            return 1;
        }
        if (w2.ep < w3.ep) {
            return -1;
        }
        return 0;
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((w)object, (w)object2);
    }
}
