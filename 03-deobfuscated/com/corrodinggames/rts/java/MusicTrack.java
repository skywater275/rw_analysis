/*
 * v19.133f2: 02b java/m.java 全文直译 (桌面曲目加载器, extends ar=GameTimer)
 * 混淆引用还原: e/a→FileLoader / audio.a.a→AudioSourceBase / l→ResourceLoader
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.java.ResourceLoader;
import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.audio.backend.AudioSourceBase;

public class MusicTrack extends GameTimer {  // 02b java/m extends ar

    ResourceLoader a;  // 02b l a
    Music c;  // 02b Music c

    public MusicTrack(String string, ResourceLoader l2) {  // 02b m(String, l)
        super(string, l2);
        this.a = l2;
        synchronized (l2.f()) {
            this.a = l2;
            String string2 = FileLoader.e(string);  // 02b e/a.java L167: e(String)
            if (string2.contains(".rwmod")) {
                this.c = l2.b.newMusic(new AudioSourceBase(FileLoader.k(string), string2));  // 02b e/a.k(String) + audio/a/a(InputStream,String)
            } else {
                this.c = l2.b.newMusic(new AudioSourceBase(string2));  // 02b audio/a/a(String)
            }
        }
    }
}
