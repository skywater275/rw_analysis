/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a;

import com.corrodinggames.rts.a.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public strictfp class b
implements Runnable {
    Socket a;
    final /* synthetic */ a b;

    public b(a a2, Socket socket) {
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
            while (this.b.b) {
                String string = bufferedReader.readLine();
                if (string == null) {
                    break;
                }
                String string2 = com.corrodinggames.rts.a.a.b(string);
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
