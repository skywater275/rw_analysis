/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public class ScriptEngine$Action {
    public String script;
    public boolean tryToCatchCrash;
    public String caughtCrash;
    public boolean completed;
    public int framesDelay;

    public void run(ScriptEngine scriptEngine) {
        block6: {
            try {
                scriptEngine.processScript(this.script);
            }
            catch (Exception exception) {
                if (this.tryToCatchCrash) {
                    l.a("caught script crash", (Throwable)exception);
                    this.caughtCrash = f.a(exception);
                    break block6;
                }
                throw new RuntimeException(exception);
            }
            finally {
                this.completed = true;
            }
        }
    }

    public String waitForCompletionOrCrash(boolean bl) {
        for (int i = 0; i < 3000; ++i) {
            if (this.completed) {
                return this.caughtCrash;
            }
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (!bl) continue;
            i = 0;
        }
        return "Time Out";
    }
}
