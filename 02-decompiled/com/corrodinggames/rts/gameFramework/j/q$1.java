/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.q;
import com.corrodinggames.rts.gameFramework.j.s;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;

class q$1
extends s {
    final /* synthetic */ int a;
    final /* synthetic */ l b;
    final /* synthetic */ q c;

    q$1(q q2, int n, l l2) {
        this.c = q2;
        this.a = n;
        this.b = l2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    void a(BufferedReader bufferedReader, int n2, String string) {
        String string2;
        l l2 = l.B();
        String string3 = bufferedReader.readLine();
        if (string3 == null || !string3.contains("CORRODINGGAMES")) {
            String string4 = n2 + ": Unknown header from the master server: '" + com.corrodinggames.rts.gameFramework.f.a(string3, 30) + "'";
            l.b("LoadFromMasterServer", string4);
            this.d = string4;
            try {
                String string5 = "";
                string5 = string5 + string3 + "\n";
                l.e("----------- Full response ----------");
                l.b("LoadFromMasterServer", "line:" + string3);
                String string6 = "";
                while ((string6 = bufferedReader.readLine()) != null) {
                    l.b("LoadFromMasterServer", "line:" + string6);
                    string5 = string5 + string6 + "\n";
                }
                l.e("------------------------------------");
                n.g = string5;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }
        l.b("LoadFromMasterServer", n2 + ": Starting load");
        int n3 = 0;
        while ((string2 = bufferedReader.readLine()) != null) {
            Object object;
            String string7;
            String[] stringArray = string2.split(",", -1);
            if (stringArray.length <= 21) {
                l.b("LoadFromMasterServer", n2 + ": columns.length too short at:" + stringArray.length);
                l.b("LoadFromMasterServer", n2 + ": short line is:" + string2);
                continue;
            }
            String string8 = stringArray[0];
            String string9 = stringArray[1];
            String string10 = stringArray[2];
            String string11 = stringArray[3];
            String string12 = stringArray[4];
            String string13 = stringArray[5];
            String string14 = stringArray[6];
            String string15 = stringArray[7];
            String string16 = stringArray[8];
            String string17 = stringArray[9];
            String string18 = stringArray[10];
            String string19 = stringArray[11];
            String string20 = stringArray[12];
            String string21 = stringArray[13];
            String string22 = stringArray[15];
            String string23 = stringArray[16];
            String string24 = stringArray[17];
            String string25 = stringArray[18];
            String string26 = stringArray[19];
            String string27 = stringArray[20];
            String string28 = stringArray[21];
            String string29 = null;
            String string30 = null;
            if (string11 != null && string11.startsWith("url:") && Boolean.parseBoolean(string24)) {
                string29 = string11.substring(4);
                string30 = string9;
                string7 = string29 + ";" + string30;
                object = com.corrodinggames.rts.gameFramework.f.c(string7);
                if (!((String)object).equals(string12)) {
                    l.e("Skipping " + string25);
                    continue;
                }
            }
            if (string25 == null || string25.trim().length() == 0) {
                string25 = string8;
            }
            try {
                string7 = string25;
                object = n.f;
                synchronized (object) {
                    g g2 = n.c(string7);
                    g2.c = string11;
                    g2.d = string12;
                    g2.e = string29;
                    g2.f = string30;
                    g2.g = Integer.valueOf(string13);
                    g2.h = Boolean.parseBoolean(string14);
                    g2.m = Boolean.parseBoolean(string16);
                    g2.j = string10;
                    try {
                        g2.l = Integer.parseInt(g2.j);
                    }
                    catch (NumberFormatException numberFormatException) {
                        l.b("game_version_int:" + numberFormatException.getMessage());
                    }
                    g2.n = string15;
                    g2.q = string17;
                    g2.r = string18;
                    g2.s = string19;
                    g2.k = string20;
                    g2.a = Boolean.parseBoolean(string21);
                    g2.t = string22;
                    g2.u = string23;
                    g2.y = Boolean.parseBoolean(string26);
                    if ("".equals(string27)) {
                        string27 = null;
                    }
                    g2.z = string27;
                    if (!string28.trim().equals("")) {
                        g2.A = Integer.valueOf(string28);
                    }
                    try {
                        g2.v = Integer.parseInt(g2.t);
                    }
                    catch (NumberFormatException numberFormatException) {
                        l.b("game_player_count_int:" + numberFormatException.getMessage());
                    }
                    try {
                        g2.w = Integer.parseInt(g2.u);
                    }
                    catch (NumberFormatException numberFormatException) {
                        l.b("game_max_player_count_int:" + numberFormatException.getMessage());
                    }
                    g2.x = Boolean.parseBoolean(string24);
                    if (g2.p < this.a) {
                        g2.p = this.a;
                    }
                    if (n.b(g2.b) == null) {
                        l2.bX.bi.add(g2);
                    }
                    ++n3;
                }
            }
            catch (NumberFormatException numberFormatException) {
                l.b("LoadFromMasterServer", n2 + ": failed to load server");
                numberFormatException.printStackTrace();
            }
        }
        l.b("LoadFromMasterServer", "[" + n2 + "]: Found " + n3 + " servers");
        if (n3 == 0) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        this.e = true;
        this.c.a.run();
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        n.a(this.a, n2);
        l.b("LoadFromMasterServer", n2 + ": Completed load from master server without error");
    }

    @Override
    void a() {
        if (!this.e) {
            this.b.bX.bh = this.d;
            this.c.a.run();
        }
    }
}
