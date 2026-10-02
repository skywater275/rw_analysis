/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.Comparator;

public strictfp class GameObjectComparator
implements Comparator {
    void x() {
    }

    public int a(GameObject w2, GameObject w3) {
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
        return this.a((GameObject) object, (GameObject) object2);
    }
}
