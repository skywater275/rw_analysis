/*
 * v19.133f39 整写: 02b gameFramework/at.java 直译 (音乐源文件夹枚举 a/b/c)
 * 修复: CFR extends Enum 非法语法 → 标准 enum; 常量匿名子类内联 (删 $1/$2/$3);
 *       getString2 误名 → d() (02b at L58 抽象); 变量污染 ByteIndexedMap/ByteSlot/CommandController → 常量 a/b/c;
 *       this.a() 误调 → this.d() (02b L21/L24); at4 → e (02b L15); GameUtils.a → MusicController.a (02b am L34);
 *       删手写 values/valueOf (编译器生成)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import java.util.ArrayList;

enum MusicFolder {

    a("starting", 0) {
        String d() {
            return "music/starting";
        }
    },
    b("buildup", 1) {
        String d() {
            return "music/buildup";
        }
    },
    c("attacked", 2) {
        String d() {
            return "music/attacked";
        }
    };

    String[] d;
    // $FF: synthetic field
    private static final MusicFolder[] e = new MusicFolder[]{a, b, c};

    private MusicFolder(String var1, int var2) {}

    void a() {
        this.d = FileLoader.a(this.d(), false);
        if (this.d == null) {
            this.d = new String[0];
            GlobalState.n("Failed to open music folder: " + this.d());
        } else {
            GlobalState var1 = GlobalState.B();
            ArrayList<String> var2 = new ArrayList<String>();
            String[] var3 = this.d;
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String var6 = var3[var5];
                var6 = FileLoader.o(var6);
                if (MusicController.a(this.a(var6), true) != null) {
                    GlobalState.e("Loaded track:" + var6);
                    var2.add(var6);
                } else {
                    GlobalState.b("Skipping track:" + var6);
                }

                var1.a("music", false);
            }

            this.d = var2.toArray(new String[0]);
        }
    }

    String[] b() {
        return this.d;
    }

    static void c() {
        a.a();
        b.a();
        c.a();
    }

    abstract String d();

    String a(String var1) {
        return this.d() + "/" + var1;
    }
}
