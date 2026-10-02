/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.ServerStatus;

public class ServerResult {
    public String resultString;
    public String errorString;
    public ServerStatus resultStatus;

    public void a(String string) {
        this.resultString = string;
    }

    public void a(String string, ServerStatus x2, Exception exception) {
        this.errorString = string;
        this.resultStatus = x2;
    }
}
