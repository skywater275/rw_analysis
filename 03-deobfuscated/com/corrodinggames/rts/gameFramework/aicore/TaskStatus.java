/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;
import com.corrodinggames.rts.gameFramework.aicore.AITask;

public class TaskStatus {
    AITask a;

    public String a() {
        if (this.a.displayMessage == null) {
            return "<null>";
        }
        return this.a.displayMessage.getLocalizedText();
    }

    public boolean b() {
        return this.a.isCompleted;
    }
}
