/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ANRError;
import com.corrodinggames.rts.gameFramework.utility.e;
import com.corrodinggames.rts.gameFramework.GameSaver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

strictfp final class GlobalState$4
implements e {
    GlobalState$4() {
    }

    @Override
    public void a(ANRError a2) {
        if (GlobalState.dT) {
            GlobalState.b("activeANRWatchDog: ANR already detected");
        }
        GlobalState.dT = true;
        GlobalState.b("activeANRWatchDog: ANR detected");
        String string = GlobalState.a(a2);
        WebAPIClient.a("detectedANR", string);
        try {
            Thread.sleep(400L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        try {
            File file = GameSaver.a("lastFreeze", "", true);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print(string);
            printStream.close();
            fileOutputStream.close();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }
}
