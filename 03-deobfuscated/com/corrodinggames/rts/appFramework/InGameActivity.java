/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog$Builder
 *  android.app.ProgressDialog
 *  android.content.ActivityNotFoundException
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.net.Uri
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.View
 *  android.widget.EditText
 *  android.widget.TextView
 *  android.widget.Toast
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.gameFramework.GameUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.corrodinggames.rts.R$id;
import com.corrodinggames.rts.R$layout;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.appFramework.AppFramework;
import com.corrodinggames.rts.appFramework.InGameActivity$1;
import com.corrodinggames.rts.appFramework.InGameActivity$10;
import com.corrodinggames.rts.appFramework.InGameActivity$11;
import com.corrodinggames.rts.appFramework.InGameActivity$12;
import com.corrodinggames.rts.appFramework.InGameActivity$13;
import com.corrodinggames.rts.appFramework.InGameActivity$14;
import com.corrodinggames.rts.appFramework.InGameActivity$15;
import com.corrodinggames.rts.appFramework.InGameActivity$16;
import com.corrodinggames.rts.appFramework.InGameActivity$2;
import com.corrodinggames.rts.appFramework.InGameActivity$3;
import com.corrodinggames.rts.appFramework.InGameActivity$4;
import com.corrodinggames.rts.appFramework.InGameActivity$5;
import com.corrodinggames.rts.appFramework.InGameActivity$6;
import com.corrodinggames.rts.appFramework.InGameActivity$7;
import com.corrodinggames.rts.appFramework.InGameActivity$8;
import com.corrodinggames.rts.appFramework.InGameActivity$9;
import com.corrodinggames.rts.appFramework.UIRunnable;
import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.appFramework.s;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class InGameActivity
extends GameActivity {
    AppFramework f;
    final Handler handler = new Handler(Looper.b());
    ProgressDialog e;
    boolean f2_var = true;

    @Override
    public void b() {
        GlobalState.e("IngameActivity: finish");
        super.b();
        AndroidUIHelper.a((Activity)this, true);
    }

    @Override
    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (bl) {
            AndroidUIHelper.a((Activity)this, false, true);
        }
        this.f.a(bl);
    }

    @Override
    public boolean a(Menu menu) {
        super.a(menu);
        menu.clear();
        GlobalState l2 = GlobalState.B();
        menu.add(0, 12, 0, (CharSequence)Localization.a("menus.ingame.save", new Object[0])).setIcon(17301582);
        if (l2.bv && !GlobalState.aZ) {  // 02b l.aZ 静态字段
            menu.add(0, 18, 0, (CharSequence)Localization.a("menus.ingame.exportMap", new Object[0])).setIcon(17301582);
        }
        menu.add(0, 2, 0, (CharSequence)Localization.a("menus.ingame.settings", new Object[0])).setIcon(17301577);
        if (!l2.N()) {
            // empty if block
        }
        if (l2.cb != null && l2.cb.j()) {
            menu.add(0, 22, 0, (CharSequence)Localization.a("menus.ingame.hideInterface", new Object[0])).setIcon(17301584);
        }
        if (l2.N()) {
            menu.add(0, 13, 0, (CharSequence)Localization.a("menus.ingame.chat", new Object[0])).setIcon(17301584);
            menu.add(0, 14, 0, (CharSequence)Localization.a("menus.ingame.players", new Object[0])).setIcon(17301661);
            if (l2.bX.C && com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().e()) {  // 02b o/a.a().e() (o/a=SteamEngine)
                menu.add(0, 17, 0, (CharSequence)Localization.a("menus.ingame.steam_reinvite", new Object[0])).setIcon(17301584);
            }
            boolean bl = false;
            if (l2.bs != null && l2.bs.G) {
                bl = true;
            }
            if (!bl && !l2.dq) {
                menu.add(0, 19, 0, (CharSequence)Localization.a("menus.ingame.surrender", new Object[0])).setIcon(17301552);
            }
            if (!l2.bX.C) {
                menu.add(0, 10, 0, (CharSequence)Localization.a("menus.ingame.disconnect", new Object[0])).setIcon(17301552);
            } else {
                menu.add(0, 10, 0, (CharSequence)Localization.a("menus.ingame.exitGame", new Object[0])).setIcon(17301552);
            }
        } else {
            if (l2.ce != null && l2.ce.waveName != null) {
                menu.add(0, 11, 0, (CharSequence)Localization.a("menus.ingame.briefing", new Object[0])).setIcon(17301659);
            }
            menu.add(0, 15, 0, (CharSequence)Localization.a("menus.ingame.exitGame", new Object[0])).setIcon(17301552);
        }
        if (l2 != null && l2.bQ.allowGameRecording) {
            if (!l2.bo) {
                menu.add(0, 9, 0, (CharSequence)"Start Recording");
            } else {
                menu.add(0, 9, 0, (CharSequence)"Stop Recording");
            }
        }
        return true;
    }

    public void c(int n2) {
        GlobalState.e("outer selectMenuOption: " + n2);
        InGameActivity$1 g$1 = new InGameActivity$1(this, n2);
        this.handler.a(g$1);
    }

    public void d(int n2) {
        switch (n2) {
            case 4: {
                GlobalState.B().ch = !GlobalState.B().ch;
                break;
            }
            case 2: {
                Intent intent = new Intent(this.k(), s.class);
                this.a(intent, 0);
                break;
            }
            case 3: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Skip?").setMessage((CharSequence)"Are you sure you want to skip this level?").setPositiveButton((CharSequence)"Yes", (DialogInterface.OnClickListener)new InGameActivity$9(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 6: {
                GlobalState l2 = GlobalState.B();
                l2.bl = !l2.bl;
                break;
            }
            case 5: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Restart?").setMessage((CharSequence)"Are you sure you want to restart this level?").setPositiveButton((CharSequence)"Yes", (DialogInterface.OnClickListener)new InGameActivity$10(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 12: {
                InGameActivity g2 = this;
                InGameActivity$11 g$11 = new InGameActivity$11(this, g2);
                if (AndroidUIHelper.a((Activity)this, g$11)) break;
                g$11.run();
                break;
            }
            case 18: {
                if (!AndroidUIHelper.b(this)) break;
                this.e(null);
                break;
            }
            case 9: {
                GlobalState l3 = GlobalState.B();
                if (!l3.bo) {
                    l3.bo = true;
                    break;
                }
                l3.bo = false;
                break;
            }
            case 19: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Disconnect?").setMessage((CharSequence)"Are you sure you want to surrender this game?").setPositiveButton((CharSequence)"Surrender", (DialogInterface.OnClickListener)new InGameActivity$12(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 10: {
                GlobalState l4 = GlobalState.B();
                String string = Localization.a("menus.ingame.multiplayerClose.titleDisconnect", new Object[0]);
                String string2 = Localization.a("menus.ingame.multiplayerClose.messageDisconnect", new Object[0]);
                String string3 = Localization.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]);
                if (l4.bX.C) {
                    string = Localization.a("menus.ingame.multiplayerClose.title", new Object[0]);
                    string2 = Localization.a("menus.ingame.multiplayerClose.messageEndGame", new Object[0]);
                    string3 = Localization.a("menus.ingame.exitGame", new Object[0]);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)string).setMessage((CharSequence)string2).setPositiveButton((CharSequence)string3, (DialogInterface.OnClickListener)new InGameActivity$13(this)).setNegativeButton((CharSequence)Localization.a("menus.common.back", new Object[0]), null);
                if (l4.bX.C) {
                    builder.setNeutralButton((CharSequence)Localization.a("menus.ingame.multiplayerClose.returnToBattleroom", new Object[0]), (DialogInterface.OnClickListener)new InGameActivity$14(this));
                }
                builder.show();
                break;
            }
            case 15: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Exit?").setMessage((CharSequence)"Are you sure you want to exit this game?").setPositiveButton((CharSequence)"Yes", (DialogInterface.OnClickListener)new InGameActivity$15(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 11: {
                GlobalState l5 = GlobalState.B();
                if (l5.ce == null || l5.ce.waveName == null) break;
                l5.a("Briefing", l5.ce.waveName);
                break;
            }
            case 13: {
                this.a(false);
                break;
            }
            case 16: {
                this.a(true);
                break;
            }
            case 14: {
                GlobalState l6 = GlobalState.B();
                if (l6.bX == null) break;
                ((com.corrodinggames.rts.game.GameEngine) l6).showPlayerListPopup();  // 02b ad.H() L1246 (bX=NetEngine, 功能在 GameEngine)
                break;
            }
            case 20: {
                this.b();
                break;
            }
            case 21: {
                this.b();
                DialogHelper.o();  // 02b n.o()
                DialogHelper.m();
                break;
            }
            case 22: {
                GlobalState l7 = GlobalState.B();
                l7.cU = true;
                l7.bS.u = false;
                break;
            }
            case 23: {
                GlobalState.e("TODO display leaderboard settings");
            }
        }
    }

    private void a(boolean bl) {
        GlobalState l2 = GlobalState.B();
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        if (!bl) {
            builder.setTitle((CharSequence)"Send Message");
        } else {
            builder.setTitle((CharSequence)"Send Team Message");
        }
        LayoutInflater layoutInflater = LayoutInflater.from((Context)this);
        View view = layoutInflater.inflate(R$layout.alert_chat, null);
        builder.setView(view);
        TextView textView = (TextView)view.findViewById(R$id.chat_messages);
        EditText editText = (EditText)view.findViewById(R$id.chat_text);
        textView.setText((CharSequence)((com.corrodinggames.rts.game.GameEngine) l2).aC.a());  // 02b ad.aC 聊天日志 (bX=NetEngine)
        editText.setText((CharSequence)"");
        editText.requestFocus();
        builder.setPositiveButton((CharSequence)(bl ? "Send Team" : "Send"), (DialogInterface.OnClickListener)new InGameActivity$16(this, editText, bl));
        builder.setNeutralButton((CharSequence)"Send & Ping Map", (DialogInterface.OnClickListener)new InGameActivity$2(this, editText, bl));
        builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface.OnClickListener)new InGameActivity$3(this));
        builder.show();
    }

    private void e(String string) {
        GlobalState l2 = GlobalState.B();
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle((CharSequence)"Export Map");
        builder.setMessage((CharSequence)"Enter Localization name to export the map as");
        EditText editText = new EditText((Context)this);
        if (string == null) {
            String string2 = GameUtils.a("d MMM yyyy");
            string2 = string2.replace(".", "");
            String string3 = "New " + l2.getDisplayMapName() + " (" + string2 + " " + GameUtils.a("HH.mm.ss") + ")";
            string3 = string3.replace("  ", " ");
            editText.setText((CharSequence)string3);
        } else {
            editText.setText((CharSequence)string);
        }
        builder.setView((View)editText);
        builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new InGameActivity$4(this, editText, l2));
        builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface.OnClickListener)new InGameActivity$5(this));
        builder.show();
    }

    private void f(String string) {
        GlobalState l2 = GlobalState.B();
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle((CharSequence)"Save Game");
        builder.setMessage((CharSequence)"Enter Localization name to save the game under");
        EditText editText = new EditText((Context)this);
        if (string == null) {
            String string2 = GameUtils.a("d MMM yyyy");
            string2 = string2.replace(".", "");
            editText.setText((CharSequence)(l2.getDisplayMapName() + " (" + string2 + " " + GameUtils.a("HH.mm.ss") + ")"));
        } else {
            editText.setText((CharSequence)string);
        }
        builder.setView((View)editText);
        builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new InGameActivity$6(this, editText));
        builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface.OnClickListener)new InGameActivity$7(this));
        builder.show();
    }

    public void d(String string) {
        this.a(0);
        UIRunnable h2 = new UIRunnable(this);
        h2.a = string;
        new Thread(h2).start();
    }

    public void l() {
        InGameActivity$8 g$8 = new InGameActivity$8(this);
        this.handler.a(g$8);
    }

    private void n() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)"market://details?R$id=com.corrodinggames.rts"));
            this.a(intent);
        }
        catch (ActivityNotFoundException activityNotFoundException) {
            String string = "Failed to open Android Market";
            int n2 = 0;
            Toast toast = Toast.makeText((Context)this.g(), (CharSequence)string, (int)n2);
            toast.show();
        }
    }

    public void m() {
    }

    static /* synthetic */ void a(InGameActivity g2, String string) {
        g2.f(string);
    }

    static /* synthetic */ void b(InGameActivity g2, String string) {
        g2.e(string);
    }

    static /* synthetic */ void a(InGameActivity g2) {
        g2.n();
    }
}
