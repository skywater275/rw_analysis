/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;

public strictfp class PasswordManager {
    public String passwordHash;
    public int minLength;
    public boolean isRequired;
    public String saltValue;
    public String algorithmName;
    public String displayLabel;

    public void a(String string) {
        GlobalState l2 = GlobalState.B();
        if (this.isRequired) {
            OutputNetStream as2;
            try {
                as2 = new OutputNetStream();
                as2.c(1);
                as2.a(this.minLength);
                as2.c(string);
            }
            catch (RuntimeException iOException) {
                throw new RuntimeException(iOException);
            }
            NetworkPacket au2 = as2.b(118);  // 02b: au=NetworkPacket
            l2.bX.sendIncorrectPassword(au2);  // 02b ad L3867: d(au)=sendPacketToAll (03 误名 sendIncorrectPassword(NetworkPacket) L3504)
            return;
        }
        if (l2.bX.C) {
            GlobalState.a("Cannot enter a password when we are a server");
            return;
        }
        l2.bX.n = string;
        l2.bX.registerAllConnections();
    }

    public void a() {
        GlobalState l2 = GlobalState.B();
        l2.bX.m("exited password");
        l2.bX.closeBattleroom();
    }
}
