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
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.f;
import com.corrodinggames.rts.appFramework.g$1;
import com.corrodinggames.rts.appFramework.g$10;
import com.corrodinggames.rts.appFramework.g$11;
import com.corrodinggames.rts.appFramework.g$12;
import com.corrodinggames.rts.appFramework.g$13;
import com.corrodinggames.rts.appFramework.g$14;
import com.corrodinggames.rts.appFramework.g$15;
import com.corrodinggames.rts.appFramework.g$16;
import com.corrodinggames.rts.appFramework.g$2;
import com.corrodinggames.rts.appFramework.g$3;
import com.corrodinggames.rts.appFramework.g$4;
import com.corrodinggames.rts.appFramework.g$5;
import com.corrodinggames.rts.appFramework.g$6;
import com.corrodinggames.rts.appFramework.g$7;
import com.corrodinggames.rts.appFramework.g$8;
import com.corrodinggames.rts.appFramework.g$9;
import com.corrodinggames.rts.appFramework.h;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.appFramework.s;
import com.corrodinggames.rts.gameFramework.h.a;
import com.corrodinggames.rts.gameFramework.l;

public class g
extends b {
    f c;
    final Handler d = new Handler(Looper.b());
    ProgressDialog e;
    boolean f = true;

    @Override
    public void b() {
        l.e("IngameActivity: finish");
        super.b();
        com.corrodinggames.rts.appFramework.c.a((Activity)this, true);
    }

    @Override
    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (bl) {
            com.corrodinggames.rts.appFramework.c.a((Activity)this, false, true);
        }
        this.c.a(bl);
    }

    @Override
    public boolean a(Menu menu) {
        super.a(menu);
        menu.clear();
        l l2 = l.B();
        menu.add(0, 12, 0, (CharSequence)a.a("menus.ingame.save", new Object[0])).setIcon(17301582);
        if (l2.bv && !l.aZ) {
            menu.add(0, 18, 0, (CharSequence)a.a("menus.ingame.exportMap", new Object[0])).setIcon(17301582);
        }
        menu.add(0, 2, 0, (CharSequence)a.a("menus.ingame.settings", new Object[0])).setIcon(17301577);
        if (!l2.N()) {
            // empty if block
        }
        if (l2.cb != null && l2.cb.j()) {
            menu.add(0, 22, 0, (CharSequence)a.a("menus.ingame.hideInterface", new Object[0])).setIcon(17301584);
        }
        if (l2.N()) {
            menu.add(0, 13, 0, (CharSequence)a.a("menus.ingame.chat", new Object[0])).setIcon(17301584);
            menu.add(0, 14, 0, (CharSequence)a.a("menus.ingame.players", new Object[0])).setIcon(17301661);
            if (l2.bX.C && com.corrodinggames.rts.gameFramework.o.a.a().e()) {
                menu.add(0, 17, 0, (CharSequence)a.a("menus.ingame.steam_reinvite", new Object[0])).setIcon(17301584);
            }
            boolean bl = false;
            if (l2.bs != null && l2.bs.G) {
                bl = true;
            }
            if (!bl && !l2.dq) {
                menu.add(0, 19, 0, (CharSequence)a.a("menus.ingame.surrender", new Object[0])).setIcon(17301552);
            }
            if (!l2.bX.C) {
                menu.add(0, 10, 0, (CharSequence)a.a("menus.ingame.disconnect", new Object[0])).setIcon(17301552);
            } else {
                menu.add(0, 10, 0, (CharSequence)a.a("menus.ingame.exitGame", new Object[0])).setIcon(17301552);
            }
        } else {
            if (l2.ce != null && l2.ce.h != null) {
                menu.add(0, 11, 0, (CharSequence)a.a("menus.ingame.briefing", new Object[0])).setIcon(17301659);
            }
            menu.add(0, 15, 0, (CharSequence)a.a("menus.ingame.exitGame", new Object[0])).setIcon(17301552);
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
        l.e("outer selectMenuOption: " + n2);
        g$1 g$1 = new g$1(this, n2);
        this.d.a(g$1);
    }

    public void d(int n2) {
        switch (n2) {
            case 4: {
                l.B().ch = !l.B().ch;
                break;
            }
            case 2: {
                Intent intent = new Intent(this.k(), s.class);
                this.a(intent, 0);
                break;
            }
            case 3: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Skip?").setMessage((CharSequence)"Are you sure you want to skip this level?").setPositiveButton((CharSequence)"Yes", (DialogInterface.OnClickListener)new g$9(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 6: {
                l l2 = l.B();
                l2.bl = !l2.bl;
                break;
            }
            case 5: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Restart?").setMessage((CharSequence)"Are you sure you want to restart this level?").setPositiveButton((CharSequence)"Yes", (DialogInterface.OnClickListener)new g$10(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 12: {
                g g2 = this;
                g$11 g$11 = new g$11(this, g2);
                if (com.corrodinggames.rts.appFramework.c.a((Activity)this, g$11)) break;
                g$11.run();
                break;
            }
            case 18: {
                if (!com.corrodinggames.rts.appFramework.c.b(this)) break;
                this.e(null);
                break;
            }
            case 9: {
                l l3 = l.B();
                if (!l3.bo) {
                    l3.bo = true;
                    break;
                }
                l3.bo = false;
                break;
            }
            case 19: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Disconnect?").setMessage((CharSequence)"Are you sure you want to surrender this game?").setPositiveButton((CharSequence)"Surrender", (DialogInterface.OnClickListener)new g$12(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 10: {
                l l4 = l.B();
                String string = a.a("menus.ingame.multiplayerClose.titleDisconnect", new Object[0]);
                String string2 = a.a("menus.ingame.multiplayerClose.messageDisconnect", new Object[0]);
                String string3 = a.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]);
                if (l4.bX.C) {
                    string = a.a("menus.ingame.multiplayerClose.title", new Object[0]);
                    string2 = a.a("menus.ingame.multiplayerClose.messageEndGame", new Object[0]);
                    string3 = a.a("menus.ingame.exitGame", new Object[0]);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)string).setMessage((CharSequence)string2).setPositiveButton((CharSequence)string3, (DialogInterface.OnClickListener)new g$13(this)).setNegativeButton((CharSequence)a.a("menus.common.back", new Object[0]), null);
                if (l4.bX.C) {
                    builder.setNeutralButton((CharSequence)a.a("menus.ingame.multiplayerClose.returnToBattleroom", new Object[0]), (DialogInterface.OnClickListener)new g$14(this));
                }
                builder.show();
                break;
            }
            case 15: {
                new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"Exit?").setMessage((CharSequence)"Are you sure you want to exit this game?").setPositiveButton((CharSequence)"Yes", (DialogInterface.OnClickListener)new g$15(this)).setNegativeButton((CharSequence)"No", null).show();
                break;
            }
            case 11: {
                l l5 = l.B();
                if (l5.ce == null || l5.ce.h == null) break;
                l5.a("Briefing", l5.ce.h);
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
                l l6 = l.B();
                if (l6.bX == null) break;
                l6.bX.H();
                break;
            }
            case 20: {
                this.b();
                break;
            }
            case 21: {
                this.b();
                n.o();
                n.m();
                break;
            }
            case 22: {
                l l7 = l.B();
                l7.cU = true;
                l7.bS.u = false;
                break;
            }
            case 23: {
                l.e("TODO display leaderboard settings");
            }
        }
    }

    private void a(boolean bl) {
        l l2 = l.B();
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
        textView.setText((CharSequence)l2.bX.aC.a());
        editText.setText((CharSequence)"");
        editText.requestFocus();
        builder.setPositiveButton((CharSequence)(bl ? "Send Team" : "Send"), (DialogInterface.OnClickListener)new g$16(this, editText, bl));
        builder.setNeutralButton((CharSequence)"Send & Ping Map", (DialogInterface.OnClickListener)new g$2(this, editText, bl));
        builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface.OnClickListener)new g$3(this));
        builder.show();
    }

    private void e(String string) {
        l l2 = l.B();
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle((CharSequence)"Export Map");
        builder.setMessage((CharSequence)"Enter a name to export the map as");
        EditText editText = new EditText((Context)this);
        if (string == null) {
            String string2 = com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy");
            string2 = string2.replace(".", "");
            String string3 = "New " + l2.al() + " (" + string2 + " " + com.corrodinggames.rts.gameFramework.f.a("HH.mm.ss") + ")";
            string3 = string3.replace("  ", " ");
            editText.setText((CharSequence)string3);
        } else {
            editText.setText((CharSequence)string);
        }
        builder.setView((View)editText);
        builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new g$4(this, editText, l2));
        builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface.OnClickListener)new g$5(this));
        builder.show();
    }

    private void f(String string) {
        l l2 = l.B();
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle((CharSequence)"Save Game");
        builder.setMessage((CharSequence)"Enter a name to save the game under");
        EditText editText = new EditText((Context)this);
        if (string == null) {
            String string2 = com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy");
            string2 = string2.replace(".", "");
            editText.setText((CharSequence)(l2.al() + " (" + string2 + " " + com.corrodinggames.rts.gameFramework.f.a("HH.mm.ss") + ")"));
        } else {
            editText.setText((CharSequence)string);
        }
        builder.setView((View)editText);
        builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new g$6(this, editText));
        builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface.OnClickListener)new g$7(this));
        builder.show();
    }

    public void d(String string) {
        this.a(0);
        h h2 = new h(this);
        h2.a = string;
        new Thread(h2).start();
    }

    public void l() {
        g$8 g$8 = new g$8(this);
        this.d.a(g$8);
    }

    private void n() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)"market://details?id=com.corrodinggames.rts"));
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

    static /* synthetic */ void a(g g2, String string) {
        g2.f(string);
    }

    static /* synthetic */ void b(g g2, String string) {
        g2.e(string);
    }

    static /* synthetic */ void a(g g2) {
        g2.n();
    }
}
