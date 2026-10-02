/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.commands;
import com.corrodinggames.rts.gameFramework.ByteIndexedMap;

import com.corrodinggames.rts.gameFramework.commands.DebugServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public strictfp class DebugSession
implements Runnable {
    Socket a;
    final /* synthetic */ DebugServer b;

    public DebugSession(DebugServer a2, Socket socket) {
        this.b = a2;
        this.a = socket;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(this.a.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.a.getInputStream()));
            while (this.b.i) {
                String string = bufferedReader.readLine();
                if (string == null) {
                    break;
                }
                String string2 = com.corrodinggames.rts.gameFramework.commands.DebugServer.a(string);
                printWriter.print(string2);
                printWriter.flush();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            try {
                this.a.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }
}
