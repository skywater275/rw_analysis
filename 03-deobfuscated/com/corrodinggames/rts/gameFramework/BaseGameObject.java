/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import java.io.IOException;

public abstract class BaseGameObject {
    /* 02b w.java 对应: 子类 UnitInstance 写侧抛 IOException (R8 移除 throws) */
    public abstract void serializeToStream(OutputNetStream var1) throws IOException;
}
