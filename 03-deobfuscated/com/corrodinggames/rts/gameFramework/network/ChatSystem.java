/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.text.Html
 *  android.text.Spanned
 */
package com.corrodinggames.rts.gameFramework.network;

import android.text.Html;
import android.text.Spanned;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.ChatMessage;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatSystem {
    private ConcurrentLinkedQueue a = new ConcurrentLinkedQueue();

    public String a(String string) {
        return GameUtils.smoothstep(string);  // 02b f.java L796: f.i(String)=HTML转义 (03 smoothstep 误名)
    }

    public void a(int n, String string, String string2, PacketDecoder c2) {
        string2 = string2.trim();
        ChatMessage b2 = new ChatMessage(this, n, string, string2, c2);
        this.a.add(b2);
        if (this.a.size() > 45) {
            this.a.poll();
        }
    }

    public int a(PacketDecoder c2, int n) {
        if (c2 == null) {
            return 0;
        }
        int n2 = c2.c;
        int n3 = 0;
        for (ChatMessage b2 : (java.util.Collection<ChatMessage>) (java.util.Collection) this.a) {
            if (b2.d != n2 || GameUtils.a(b2.e, System.nanoTime()) >= (long)n || b2.c.startsWith("-i ") || b2.c.startsWith("-qc ")) continue;  // 02b f.a(long,long)
            ++n3;
            if (b2.c == null) continue;
            if (GameUtils.a(b2.c, '\n') >= 3) {  // 02b f.a(String,char)
                n3 += 2;
            }
            if (GameUtils.a(b2.c, '\n') >= 6) {
                n3 += 2;
            }
            if (GameUtils.a(b2.c, '\n') < 9) continue;
            n3 += 2;
        }
        return n3;
    }

    public String a() {
        String string = "";
        for (ChatMessage b2 : (java.util.Collection<ChatMessage>) (java.util.Collection) this.a) {
            string = string + b2.a() + "\n";
        }
        return string;
    }

    public ConcurrentLinkedQueue b() {
        return this.a;
    }

    public String a(boolean bl) {
        String string = "";
        if (!bl) {
            for (ChatMessage b2 : (java.util.Collection<ChatMessage>) (java.util.Collection) this.a) {
                string = string + b2.b() + "<br/>\n";
            }
        } else {
            for (ChatMessage b3 : (java.util.Collection<ChatMessage>) (java.util.Collection) this.a) {
                string = b3.b() + "<br/>\n" + string;
            }
        }
        return "<pre>" + string + "</pre>";
    }

    public Spanned b(boolean bl) {
        return Html.fromHtml((String)this.a(bl));
    }

    public void c() {
        this.a.clear();
    }
}
