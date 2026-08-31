package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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

public class g extends b {

   f c;
   final Handler d = new Handler(Looper.b());
   ProgressDialog e;
   boolean f = true;


   public void b() {
      com.corrodinggames.rts.gameFramework.l.e("IngameActivity: finish");
      super.b();
      c.a(this, true);
   }

   public void onWindowFocusChanged(boolean var1) {
      super.onWindowFocusChanged(var1);
      if(var1) {
         c.a(this, false, true);
      }

      this.c.a(var1);
   }

   public boolean a(Menu var1) {
      super.a(var1);
      var1.clear();
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var1.add(0, 12, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.save", new Object[0])).setIcon(17301582);
      if(var2.bv && !com.corrodinggames.rts.gameFramework.l.aZ) {
         var1.add(0, 18, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.exportMap", new Object[0])).setIcon(17301582);
      }

      var1.add(0, 2, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.settings", new Object[0])).setIcon(17301577);
      if(!var2.N()) {
         ;
      }

      if(var2.cb != null && var2.cb.j()) {
         var1.add(0, 22, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.hideInterface", new Object[0])).setIcon(17301584);
      }

      if(var2.N()) {
         var1.add(0, 13, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.chat", new Object[0])).setIcon(17301584);
         var1.add(0, 14, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.players", new Object[0])).setIcon(17301661);
         if(var2.bX.C && com.corrodinggames.rts.gameFramework.o.a.a().e()) {
            var1.add(0, 17, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.steam_reinvite", new Object[0])).setIcon(17301584);
         }

         boolean var3 = false;
         if(var2.bs != null && var2.bs.G) {
            var3 = true;
         }

         if(!var3 && !var2.dq) {
            var1.add(0, 19, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.surrender", new Object[0])).setIcon(17301552);
         }

         if(!var2.bX.C) {
            var1.add(0, 10, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.disconnect", new Object[0])).setIcon(17301552);
         } else {
            var1.add(0, 10, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.exitGame", new Object[0])).setIcon(17301552);
         }
      } else {
         if(var2.ce != null && var2.ce.h != null) {
            var1.add(0, 11, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.briefing", new Object[0])).setIcon(17301659);
         }

         var1.add(0, 15, 0, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.exitGame", new Object[0])).setIcon(17301552);
      }

      if(var2 != null && var2.bQ.allowGameRecording) {
         if(!var2.bo) {
            var1.add(0, 9, 0, "Start Recording");
         } else {
            var1.add(0, 9, 0, "Stop Recording");
         }
      }

      return true;
   }

   public void c(int var1) {
      com.corrodinggames.rts.gameFramework.l.e("outer selectMenuOption: " + var1);
      g$1 var2 = new g$1(this, var1);
      this.d.a((Runnable)var2);
   }

   public void d(int var1) {
      com.corrodinggames.rts.gameFramework.l var3;
      switch(var1) {
      case 2:
         Intent var2 = new Intent(this.k(), s.class);
         this.a(var2, 0);
         break;
      case 3:
         (new Builder(this)).setIcon(17301543).setTitle("Skip?").setMessage("Are you sure you want to skip this level?").setPositiveButton("Yes", new g$9(this)).setNegativeButton("No", (OnClickListener)null).show();
         break;
      case 4:
         com.corrodinggames.rts.gameFramework.l.B().ch = !com.corrodinggames.rts.gameFramework.l.B().ch;
         break;
      case 5:
         (new Builder(this)).setIcon(17301543).setTitle("Restart?").setMessage("Are you sure you want to restart this level?").setPositiveButton("Yes", new g$10(this)).setNegativeButton("No", (OnClickListener)null).show();
         break;
      case 6:
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         var3.bl = !var3.bl;
      case 7:
      case 8:
      case 17:
      default:
         break;
      case 9:
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(!var3.bo) {
            var3.bo = true;
         } else {
            var3.bo = false;
         }
         break;
      case 10:
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         String var8 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.titleDisconnect", new Object[0]);
         String var5 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.messageDisconnect", new Object[0]);
         String var6 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]);
         if(var3.bX.C) {
            var8 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.title", new Object[0]);
            var5 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.messageEndGame", new Object[0]);
            var6 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.exitGame", new Object[0]);
         }

         Builder var7 = (new Builder(this)).setIcon(17301543).setTitle(var8).setMessage(var5).setPositiveButton(var6, new g$13(this)).setNegativeButton(com.corrodinggames.rts.gameFramework.h.a.a("menus.common.back", new Object[0]), (OnClickListener)null);
         if(var3.bX.C) {
            var7.setNeutralButton(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.returnToBattleroom", new Object[0]), new g$14(this));
         }

         var7.show();
         break;
      case 11:
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(var3.ce != null && var3.ce.h != null) {
            var3.a("Briefing", var3.ce.h);
         }
         break;
      case 12:
         g$11 var4 = new g$11(this, this);
         if(!c.a(this, var4)) {
            var4.run();
         }
         break;
      case 13:
         this.a(false);
         break;
      case 14:
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(var3.bX != null) {
            var3.bX.H();
         }
         break;
      case 15:
         (new Builder(this)).setIcon(17301543).setTitle("Exit?").setMessage("Are you sure you want to exit this game?").setPositiveButton("Yes", new g$15(this)).setNegativeButton("No", (OnClickListener)null).show();
         break;
      case 16:
         this.a(true);
         break;
      case 18:
         if(c.b((Activity)this)) {
            this.e((String)null);
         }
         break;
      case 19:
         (new Builder(this)).setIcon(17301543).setTitle("Disconnect?").setMessage("Are you sure you want to surrender this game?").setPositiveButton("Surrender", new g$12(this)).setNegativeButton("No", (OnClickListener)null).show();
         break;
      case 20:
         this.b();
         break;
      case 21:
         this.b();
         n.o();
         n.m();
         break;
      case 22:
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         var3.cU = true;
         var3.bS.u = false;
         break;
      case 23:
         com.corrodinggames.rts.gameFramework.l.e("TODO display leaderboard settings");
      }

   }

   private void a(boolean var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      Builder var3 = new Builder(this);
      if(!var1) {
         var3.setTitle("Send Message");
      } else {
         var3.setTitle("Send Team Message");
      }

      LayoutInflater var4 = LayoutInflater.from(this);
      View var5 = var4.inflate(R$layout.alert_chat, (ViewGroup)null);
      var3.setView(var5);
      TextView var6 = (TextView)var5.findViewById(R$id.chat_messages);
      EditText var7 = (EditText)var5.findViewById(R$id.chat_text);
      var6.setText(var2.bX.aC.a());
      var7.setText("");
      var7.requestFocus();
      var3.setPositiveButton(var1?"Send Team":"Send", new g$16(this, var7, var1));
      var3.setNeutralButton("Send & Ping Map", new g$2(this, var7, var1));
      var3.setNegativeButton("Cancel", new g$3(this));
      var3.show();
   }

   private void e(String var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      Builder var3 = new Builder(this);
      var3.setTitle("Export Map");
      var3.setMessage("Enter a name to export the map as");
      EditText var4 = new EditText(this);
      if(var1 == null) {
         String var5 = com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy");
         var5 = var5.replace(".", "");
         String var6 = "New " + var2.al() + " (" + var5 + " " + com.corrodinggames.rts.gameFramework.f.a("HH.mm.ss") + ")";
         var6 = var6.replace("  ", " ");
         var4.setText(var6);
      } else {
         var4.setText(var1);
      }

      var3.setView(var4);
      var3.setPositiveButton("Ok", new g$4(this, var4, var2));
      var3.setNegativeButton("Cancel", new g$5(this));
      var3.show();
   }

   private void f(String var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      Builder var3 = new Builder(this);
      var3.setTitle("Save Game");
      var3.setMessage("Enter a name to save the game under");
      EditText var4 = new EditText(this);
      if(var1 == null) {
         String var5 = com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy");
         var5 = var5.replace(".", "");
         var4.setText(var2.al() + " (" + var5 + " " + com.corrodinggames.rts.gameFramework.f.a("HH.mm.ss") + ")");
      } else {
         var4.setText(var1);
      }

      var3.setView(var4);
      var3.setPositiveButton("Ok", new g$6(this, var4));
      var3.setNegativeButton("Cancel", new g$7(this));
      var3.show();
   }

   public void d(String var1) {
      this.a(0);
      h var2 = new h(this);
      var2.a = var1;
      (new Thread(var2)).start();
   }

   public void l() {
      g$8 var1 = new g$8(this);
      this.d.a((Runnable)var1);
   }

   private void n() {
      try {
         Intent var1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.corrodinggames.rts"));
         this.a(var1);
      } catch (ActivityNotFoundException var5) {
         String var2 = "Failed to open Android Market";
         byte var3 = 0;
         Toast var4 = Toast.makeText(this.g(), var2, var3);
         var4.show();
      }

   }

   public void m() {}

   // $FF: synthetic method
   static void a(g var0, String var1) {
      var0.f(var1);
   }

   // $FF: synthetic method
   static void b(g var0, String var1) {
      var0.e(var1);
   }

   // $FF: synthetic method
   static void a(g var0) {
      var0.n();
   }
}
