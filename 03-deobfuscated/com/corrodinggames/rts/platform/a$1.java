/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform;

import com.corrodinggames.rts.platform.a;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

strictfp final class a$1
implements Runnable {
    void a$1() {
    }

    @Override
    public void run() {
        for (String string : (java.util.Collection<String>) (java.util.Collection) a.e) {
            GlobalState.e("Running debug script:" + string);
            try {
                String string2;
                FileReader fileReader = new FileReader(string);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((string2 = bufferedReader.readLine()) != null) {
                    if ((string2 = string2.trim()).equals("") || string2.startsWith("#")) continue;
                    GlobalState.e("Running: " + string2);
                    String string3 = a.b("script " + string2);  // 02b a/a$1 L33: a.b(String)
                    GlobalState.e("got: " + string3.trim());
                }
                bufferedReader.close();
                fileReader.close();
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            GlobalState.e("End of:" + string);
        }
    }
}
