/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a;

import com.corrodinggames.rts.a.a;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

strictfp final class a$1
implements Runnable {
    a$1() {
    }

    @Override
    public void run() {
        for (String string : a.e) {
            l.e("Running debug script:" + string);
            try {
                String string2;
                FileReader fileReader = new FileReader(string);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((string2 = bufferedReader.readLine()) != null) {
                    if ((string2 = string2.trim()).equals("") || string2.startsWith("#")) continue;
                    l.e("Running: " + string2);
                    String string3 = a.b("script " + string2);
                    l.e("got: " + string3.trim());
                }
                bufferedReader.close();
                fileReader.close();
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            l.e("End of:" + string);
        }
    }
}
