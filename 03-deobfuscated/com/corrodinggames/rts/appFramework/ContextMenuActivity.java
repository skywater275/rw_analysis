/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.MenuItem
 *  android.view.View
 *  android.widget.AdapterView$AdapterContextMenuInfo
 */
package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.GameStateEnum;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.ProjectileType2;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextMenuActivity
extends GameActivity {
    boolean c;
    String d;

    @Override
    public void b() {
        super.b();
        AndroidUIHelper.a((Activity)this, true);
    }

    public static String d(String string) {  // 02b appFramework/i.java L25-41: 路径名提取
        if (string == null) {
            return null;
        }
        int n2;
        if (string.contains("/MOD|")) {
            n2 = string.indexOf("/MOD|");
            return string.substring(n2);
        }
        if (string.contains("/NEW_PATH|")) {
            n2 = string.indexOf("/NEW_PATH|");
            return string.substring(n2);
        }
        String[] stringArray = string.split("/");
        return stringArray[stringArray.length - 1];
    }

    public static boolean g(String string) {  // 02b appFramework/i.java L67-69
        return string.contains("SD/");
    }

    public static String e(String string) {
        if (string == null) {
            return null;
        }
        if (string.contains("/MOD|")) {
            int n = string.indexOf("/MOD|");
            return string.substring(n);
        }
        if (string.contains("/NEW_PATH|")) {
            int n = string.indexOf("/NEW_PATH|");
            return string.substring(n);
        }
        String[] stringArray = string.split("/");
        return stringArray[stringArray.length - 1];
    }

    public static boolean a(String string, String string2) {
        String string3;
        Pattern pattern = Pattern.compile(".*\\[(.*)\\].*");
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches() && ((string3 = matcher.group(1)).toLowerCase(Locale.ENGLISH) + "|").contains("demo|")) {
            return true;
        }
        string3 = string2.replace(".tmx", "");
        return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(string3 = string3 + "_demo");
    }

    public static String getString2(String string) {
        return AndroidUIHelper.b(string);
    }

    public static boolean f(String string) {
        return string.contains("skirmish/");
    }

    public static boolean isEnabled2(String string) {
        return string.contains("SD/");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void a(String string, boolean bl, int n2, int n3, boolean bl2, boolean bl3) {
        GlobalState l2 = GlobalState.B();
        l2.bS.g();
        if (bl || bl3) {
            int n4 = 0;
            l2.L();
            GlobalState l3 = l2;
            synchronized (l3) {
                int n5;
                int n6;
                int n7;
                l2.dm = null;
                l2.dl = string;
                int n8 = PlayerState.c - 1;
                int n9 = AndroidUIHelper.a(string);
                GlobalState.e("Max teams on map: " + string + " = " + n9);
                if (n9 > 0 && n9 - 1 < n8) {
                    n8 = n9 - 1;
                }
                PlayerState.F();  // v19.113k: 02 n.F() 玩家数组重置 (旧误替换 DialogHelper — 短名 n 冲突)
                l2.bs = new HumanPlayer(0);  // v19.113k: 02 new e(0) 本地玩家 (旧误替换 AppState)
                l2.bs.v = "Player";
                for (n7 = 0; n7 <= 1; ++n7) {
                    for (n6 = 1; n6 <= n8; ++n6) {
                        PlayerState n10;  // v19.113k: 02 n 玩家槽
                        int n11 = n5 = n6 % 2 == 0 || n7 == 1 ? 1 : 0;
                        if (n4 >= n3 || n5 == 0 || (n10 = PlayerState.k(n6)) != null) continue;  // v19.113m: 02 i.java:110 玩家已存在则跳过
                        n10 = new AIStrategy(n6);  // v19.113k: 02 new a.a(n6) AI 玩家 (旧误替换 AppContext)
                        n10.v = "AI";
                        n10.r = 0;
                        ++n4;
                    }
                }
                GlobalState.e("Allies: " + n4 + "/" + n3);
                n7 = 0;
                n6 = n2 - n3;
                for (n5 = 0; n5 <= 1; ++n5) {
                    for (int i2 = 1; i2 <= n8; ++i2) {
                        PlayerState n12;
                        boolean bl4;
                        boolean bl5 = bl4 = i2 % 2 == 1 || n5 == 1;
                        if (!bl2) {
                            bl4 = true;
                        }
                        if (n7 >= n6 || !bl4 || (n12 = PlayerState.k(i2)) != null) continue;
                        n12 = new AIStrategy(i2);
                        n12.v = "AI";
                        ++n7;
                        if (!bl2) continue;
                        n12.r = 1;
                    }
                }
                l2.bX.aq();
                if (!bl3) {
                    l2.a(false, GameStateEnum.b);
                }
            }
        }
        l2.L();
        GlobalState l4 = l2;
        synchronized (l4) {
            l2.dm = null;
            l2.dl = string;
        }
        if (!bl3) {
            l2.a(true, GameStateEnum.b);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        String string;
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)contextMenuInfo;
        View view2 = adapterContextMenuInfo.targetView;
        String string2 = (String)view2.getTag();
        GlobalState l2 = GlobalState.B();
        String string3 = ContextMenuActivity.e(string2);
        com.corrodinggames.rts.gameFramework.mods.ModInfo b2 = string2 != null ? l2.bZ.h(string2) : null;
        this.d = string2;
        contextMenu.setHeaderTitle((CharSequence)string3);
        MenuItem menuItem = contextMenu.add(0, view2.getId(), 0, (CharSequence)"Export");
        if (b2 != null) {
            menuItem.setTitle((CharSequence)"Export (Standalone maps only)");
            menuItem.setEnabled(false);
        }
        MenuItem menuItem2 = contextMenu.add(2, view2.getId(), 0, (CharSequence)"Delete");
        if (b2 != null) {
            menuItem2.setTitle((CharSequence)"Delete (Standalone maps only)");
            menuItem2.setEnabled(false);
        }
        if (b2 != null) {
            MenuItem menuItem3 = contextMenu.add(4, view2.getId(), 0, (CharSequence)("From Mod: " + b2.b()));
            menuItem3.setEnabled(false);
        }
        if (b2 == null && this.c) {
            string = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.n(string2);
            MenuItem menuItem3 = contextMenu.add(3, view.getId(), 0, (CharSequence)("Storage: " + string));
            if (menuItem3 != null) {
                menuItem3.setEnabled(false);
            }
        }
    }
}
