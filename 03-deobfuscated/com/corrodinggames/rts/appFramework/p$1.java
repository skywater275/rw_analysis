package com.corrodinggames.rts.appFramework;

// 02b appFramework/p$1.java 直译: 服务器列表排序器 (GameServerInfo)
import com.corrodinggames.rts.gameFramework.network.GameServerInfo;
import java.util.Comparator;

final class p$1
implements Comparator {
    public int a(GameServerInfo var1) {
        if (var1.d()) {
            return 0;
        }
        if (var1.x && var1.s.equals("chat")) {
            return 1;
        }
        if (var1.a) {
            return 2;
        }
        if (!var1.s.equals("battleroom")) {
            return 99;
        }
        if (var1.v != -1 && var1.v < var1.protocolVersion) {
            if (var1.x) {
                if (var1.v != 0) {
                    return 3;
                }
                return 4;
            }
            if (var1.h && !var1.x) {
                return 7;
            }
        } else {
            if (var1.x) {
                return 6;
            }
            if (var1.h && !var1.x) {
                return 8;
            }
        }
        return 9;
    }

    public int a(GameServerInfo var1, GameServerInfo var2) {
        Integer var3 = Integer.valueOf(this.a(var1));
        Integer var4 = Integer.valueOf(this.a(var2));
        if (!var1.g()) {
            var3 = Integer.valueOf(var3.intValue() + 20);
        }
        if (!var2.g()) {
            var4 = Integer.valueOf(var4.intValue() + 20);
        }
        int n = var3.compareTo(var4);
        return n != 0 ? n : var1.q.compareTo(var2.q);
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((GameServerInfo) object, (GameServerInfo) object2);
    }
}
