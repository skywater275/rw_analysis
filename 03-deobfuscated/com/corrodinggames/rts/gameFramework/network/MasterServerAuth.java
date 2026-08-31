/*
 * 02b j/aq.java 直译: master server 认证参数构造
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.List;

public class MasterServerAuth {
    public static MasterServerAuth a = new MasterServerAuth();
    public static int b = 2;
    static int c = 3;
    static int d = 2;
    static int e = 3;
    public static int f = 4;
    static String g = "tx";
    static String h = "_";
    public static int i = 55;
    public static int j = 66;
    public static int k = 100;
    public static boolean l = true;

    public static float a(float f2, float f3, float f4) {
        return f2 + (f3 - f2) * f4;
    }

    public void a(String string, List list) {
        long l2 = GlobalState.V();
        WebAPIClient.a(list, h + "1", "" + l2);
        WebAPIClient.a(list, g + "2", GameUtils.d("_" + string + (b + c)));
        WebAPIClient.a(list, g + "3", GameUtils.d("_" + string + ((long)(b + c) + l2)));
    }

    public void b(String string, List list) {
        WebAPIClient.a(list, g + "3", GameUtils.d("-" + string + (d + e) + f));
    }

    public void c(String string, List list) {
        if (f > 1000) {
            WebAPIClient.a(list, g + "4", GameUtils.d("+" + string + (d + e) + f));
        }
    }
}
