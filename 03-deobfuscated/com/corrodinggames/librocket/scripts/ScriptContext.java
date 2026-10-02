/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;
import com.corrodinggames.rts.game.GameFlagImpl;
import com.corrodinggames.rts.game.GameFlag;

import com.corrodinggames.librocket.LibRocketContext;
import com.corrodinggames.librocket.LibRocketBridge;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import java.util.HashMap;

public class ScriptContext {
    LibRocketBridge libRocket;
    ScriptEngine scriptEngine;
    LibRocketContext guiEngine;  // 02b ScriptContext: a guiEngine (librocket/a=LibRocketContext); GameFlag 错标修正
    HashMap methods = new HashMap();
}
