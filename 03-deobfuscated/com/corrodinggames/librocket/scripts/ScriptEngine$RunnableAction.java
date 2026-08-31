/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class ScriptEngine$RunnableAction
extends ScriptEngine$Action {
    Runnable runnable;

    ScriptEngine$RunnableAction(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run(ScriptEngine scriptEngine) {
        block6: {
            try {
                this.runnable.run();
            }
            catch (Exception exception) {
                if (this.tryToCatchCrash) {
                    GlobalState.a("caught script crash", (Throwable)exception);
                    this.caughtCrash = com.corrodinggames.rts.gameFramework.GameUtils.a(exception);
                    break block6;
                }
                throw new RuntimeException(exception);
            }
            finally {
                this.completed = true;
            }
        }
    }
}
