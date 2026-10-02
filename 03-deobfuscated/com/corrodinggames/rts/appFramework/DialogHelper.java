/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.text.Spanned
 *  android.view.View
 *  android.widget.EditText
 *  android.widget.TextView
 */
package com.corrodinggames.rts.appFramework;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.ShaderProgram;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.UnitTrait$1;
import com.corrodinggames.rts.appFramework.UnitTrait$2;
import com.corrodinggames.rts.appFramework.UnitTrait$3;
import com.corrodinggames.rts.appFramework.UnitTrait$4;
import com.corrodinggames.rts.appFramework.UnitTrait$5;
import com.corrodinggames.rts.appFramework.o;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.ProjectileType2;

public class DialogHelper
extends GameActivity {
    public static DialogHelper c;
    boolean d;
    /* synthetic 字段: 字节码 appFramework/n.class 无 <init> 构造器 (全部方法 static)、
       final Handler e 无赋值路径 (恒为 null), 去 final 匹配 javap 事实 (同 GLTextureRegion 模式) */
    Handler e;
    public static boolean f;
    public boolean g;
    TextView h;
    private Handler k;
    private Runnable l;
    private Runnable m;
    static ShaderProgram i;
    static AlertDialog j;

    public static void m() {  // 02b n.m() L42-49: 对话框刷新 (n$1 Runnable; 简化版)
        if (c != null) {
            // 02b n$1: 刷新对话框内容 (完整体待 DialogHelper 战役)
        }
    }

    void n() {  // 02b n.java L60-81: 聊天日志刷新 (03 简化 PENDING)
        if (!this.d) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("addMessageToChatLogInternal: !onCreateFinished");
        }
    }

    public static void o() {
        // 02b n.o() L92: startServer+updateUI
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bX != null) {
            l2.bX.startServer();
            l2.bX.d.c();
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            return;
        }
        if (l2.bX != null && l2.bX.aW) {
            return;
        }
        if (c != null) {
            c.e.a(c.l);
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("MultiplayerBattleroomActivity:updateUI() lastLoaded==null");
        }
    }

    public static boolean l() {
        // 02b appFramework/n.java L38-40: 閻庣數顢婇惁钘夘浖閸℃ɑ笑闁告熬绠戝﹢顏堝及閸撗佷粵
        return c == null ? false : c.g;
    }

    public static void d(String string) {
        // 02b appFramework/n.java L51-58: 闁告瑦鍨块埀顑跨劍缁夌兘骞侀姘厒 Handler
        DialogHelper dialogHelper = c;
        if (dialogHelper != null) {
            Message message = dialogHelper.k.a();
            message.d().putString("text", string);
            dialogHelper.k.c(message);
        }
    }

    public static void a(String string, String string2) {
        // 02b appFramework/n.java L83-90: 鐎殿喚鎳撻崵顓㈠棘閸ャ劍鎷遍弶鍫熸尭閸欏棛鈧數顢婇惁钘夘浖?
        if (c != null) {
            DialogHelper dialogHelper = c;
            DialogHelper$2 dialogHelper2 = new DialogHelper$2(dialogHelper, string2);
            c.e.a((Runnable) dialogHelper2);
        }
    }
    public static void a(PasswordManager passwordManager) {
        // 02b appFramework/n.java L189-231 缂佺姭鍋撻柛?TODO: 閻庨潧妫涢悥婊勬綇閹惧啿寮抽悗鐢殿攰閻﹁棄顩?
        // (AlertDialog + EditText + n$3/n$4/n$5 闁搞儳鍋犻惃? DialogHelper 闁瑰瓨锚閻掞妇鎮伴妷銉ュ伎)
    }
    public static void p() {  // 02b n.p() L111: startGame
        if (c != null) {
            c.e.a(c.m);  // 02b n.java L113
            f = false;
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("MultiplayerBattleroomActivity:startGame() lastLoaded==null");
            com.corrodinggames.rts.gameFramework.GlobalState.T();
            f = true;
        }
    }

    public static void q() {  // 02b n.q() L123: 缁熻鏄剧ず
        o o2 = new o("Starting unit count");  // 02b: o var0 = new o() (n 楠炴槒顫庨崥宥勬叏濮?
        o o3 = new o("Total unit HP");
        o o4 = new o("Team Credits");
        for (PlayerState n2 : (java.util.Collection<PlayerState>) (java.util.Collection) PlayerState.c()) {
            int n3 = 0;
            int n4 = 0;
            UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
            int n5 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
            for (int n6 = 0; n6 < n5; ++n6) {
                UnitInstance am2 = amArray[n6];
                if (am2.player != n2) continue;
                ++n3;
                n4 = (int)((float)n4 + am2.hp);
            }
            if (n3 == 0) continue;
            o2.a(n2, n3);
            o3.a(n2, n4);
            o4.a(n2, (int)n2.credits);
        }
        if (!o2.a()) {
            o3.a();
        }
        o4.a();
    }

    public static void r() {  // 02b n.r() L158: 浠庡瓨妗ｅ紑濮嬫父鎴?
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.dm = null;
        if (l2.bX.ay.a == GameModeEnum.c) {
            if (!l2.bX.C) {
                l2.ca.a(l2.bX.inputNetStream, true, false, false);  // 02b: bX.aA (aA=inputNetStream)
                l2.bS.h.a(null, "Note: Game was started from Localization saved game.");
            } else {
                l2.ca.c(l2.bX.ay.b, true);
            }
            DialogHelper.q();  // 02b: q() (缂佺喕顓搁弰鍓с仛)
        } else if (l2.bX.ay.a == GameModeEnum.b) {
            if (!l2.bX.C) {
                l2.dl = "";
                l2.dm = l2.bX.aB;
                l2.a(true, com.corrodinggames.rts.gameFramework.GameStateEnum.b);
                l2.bS.h.a(null, "Note: Game was started from Localization custom map on server.");
            } else {
                l2.dl = l2.bX.az;
                l2.a(true, com.corrodinggames.rts.gameFramework.GameStateEnum.b);
            }
            DialogHelper.q();  // 02b: q() (缂佺喕顓搁弰鍓с仛)
        } else {
            l2.dl = l2.bX.az;
            l2.a(true, com.corrodinggames.rts.gameFramework.GameStateEnum.b);
        }
    }

    static {
        f = false;
    }
}
