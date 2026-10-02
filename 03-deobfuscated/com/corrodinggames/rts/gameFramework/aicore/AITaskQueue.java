/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;
import com.corrodinggames.rts.gameFramework.aicore.AITask;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class AITaskQueue {
    CustomArrayList<AITask> a = new CustomArrayList<AITask>();
    boolean b;

    public void a(AITask a2) {
        this.a.add(a2);
    }

    public boolean a() {
        return this.a.a > 0;
    }

    public boolean b() {
        boolean bl = false;
        boolean bl2 = true;
        for (AITask a2 : this.a) {
            if (a2.isCompleted) {
                bl = true;
                continue;
            }
            bl2 = false;
        }
        if (this.b && !bl2) {
            bl = false;
        }
        return bl;
    }
}
