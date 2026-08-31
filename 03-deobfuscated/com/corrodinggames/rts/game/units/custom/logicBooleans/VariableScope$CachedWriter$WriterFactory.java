/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;

public abstract class VariableScope$CachedWriter$WriterFactory {
    /* 02b au.java 覆写抛 checked bo (R8 移除 throws, javap 无 throws 铁证) → 父类需声明 */
    public abstract VariableScope$CachedWriter$WriterElement createWriterElement(String var1, String var2, String var3, String var4) throws bo;
}
