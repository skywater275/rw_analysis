package com.corrodinggames.rts.appFramework;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.TextView;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.n$1;
import com.corrodinggames.rts.appFramework.n$2;
import com.corrodinggames.rts.appFramework.n$3;
import com.corrodinggames.rts.appFramework.n$4;
import com.corrodinggames.rts.appFramework.n$5;
import com.corrodinggames.rts.appFramework.o;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.j.ai;
import java.util.Iterator;

public class n extends b {

   public static n c;
   boolean d;
   final Handler e;
   public static boolean f = false;
   public boolean g;
   TextView h;
   private Handler k;
   private Runnable l;
   private Runnable m;
   static ae i;
   static AlertDialog j;


   public static boolean l() {
      return c == null?false:c.g;
   }

   public static void m() {
      if(c != null) {
         n var0 = c;
         n$1 var1 = new n$1(var0);
         c.e.a((Runnable)var1);
      }

   }

   public static void d(String var0) {
      n var1 = c;
      if(var1 != null) {
         Message var2 = var1.k.a();
         var2.d().putString("text", var0);
         var1.k.c(var2);
      }
   }

   void n() {
      if(!this.d) {
         com.corrodinggames.rts.gameFramework.l.b("addMessageToChatLogInternal: !onCreateFinished");
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         Spanned var2 = var1.bX.aC.b(true);
         if(this.h == null) {
            throw new RuntimeException("chatLog==null");
         } else if(var2 == null) {
            throw new RuntimeException("chatLogHTML==null");
         } else {
            try {
               this.h.clearFocus();
               this.h.setTextKeepState(var2);
            } catch (NullPointerException var4) {
               com.corrodinggames.rts.gameFramework.l.a("chatLog.setText error", (Throwable)var4);
               var1.a("chatLog.setText error", 1);
            }

         }
      }
   }

   public static void a(String var0, String var1) {
      if(c != null) {
         n var2 = c;
         n$2 var3 = new n$2(var2, var1);
         c.e.a((Runnable)var3);
      }

   }

   public static void o() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      if(var0.bX != null) {
         var0.bX.O();
         var0.bX.d.c();
      }

      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         if(var0.bX == null || !var0.bX.aW) {
            if(c != null) {
               c.e.a(c.l);
            } else {
               com.corrodinggames.rts.gameFramework.l.b("MultiplayerBattleroomActivity:updateUI() lastLoaded==null");
            }

         }
      }
   }

   public static void p() {
      if(c != null) {
         c.e.a(c.m);
         f = false;
      } else {
         com.corrodinggames.rts.gameFramework.l.b("MultiplayerBattleroomActivity:startGame() lastLoaded==null");
         com.corrodinggames.rts.gameFramework.l.T();
         f = true;
      }

   }

   public static void q() {
      o var0 = new o("Starting unit count");
      o var1 = new o("Total unit HP");
      o var2 = new o("Team Credits");
      Iterator var3 = com.corrodinggames.rts.game.n.c().iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.n var4 = (com.corrodinggames.rts.game.n)var3.next();
         int var5 = 0;
         int var6 = 0;
         am[] var7 = am.bE.a();
         int var8 = 0;

         for(int var9 = am.bE.size(); var8 < var9; ++var8) {
            am var10 = var7[var8];
            if(var10.bX == var4) {
               ++var5;
               var6 = (int)((float)var6 + var10.cu);
            }
         }

         if(var5 != 0) {
            var0.a(var4, var5);
            var1.a(var4, var6);
            var2.a(var4, (int)var4.o);
         }
      }

      if(!var0.a()) {
         var1.a();
      }

      var2.a();
   }

   public static void r() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      var0.dm = null;
      if(var0.bX.ay.a == ai.c) {
         if(!var0.bX.C) {
            var0.ca.a(var0.bX.aA, true, false, false);
            var0.bS.h.a((String)null, "Note: Game was started from a saved game.");
         } else {
            var0.ca.c(var0.bX.ay.b, true);
         }

         q();
      } else if(var0.bX.ay.a == ai.b) {
         if(!var0.bX.C) {
            var0.dl = "";
            var0.dm = var0.bX.aB;
            var0.a(true, com.corrodinggames.rts.gameFramework.s.b);
            var0.bS.h.a((String)null, "Note: Game was started from a custom map on server.");
         } else {
            var0.dl = var0.bX.az;
            var0.a(true, com.corrodinggames.rts.gameFramework.s.b);
         }

         q();
      } else {
         var0.dl = var0.bX.az;
         var0.a(true, com.corrodinggames.rts.gameFramework.s.b);
      }

   }

   public static void a(ae var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      Context var2 = var1.aD();
      Builder var3 = new Builder(var2);
      String var4 = "Password Required";
      String var5 = "This server requires a password to join";
      if(var0.b != null) {
         var4 = "Server Question";
         var5 = var0.b;
         var5 = com.corrodinggames.rts.gameFramework.h.a.c(var5);
      }

      if(var0.e != null) {
         var4 = var0.e;
      }

      var3.setTitle(var4);
      var3.setMessage(var5);
      EditText var6 = new EditText(var3.getContext());
      var3.setView(var6);
      if(var0.b != null) {
         var6.setHint("Enter text...");
      } else {
         var6.setHint("Enter password...");
      }

      var3.setPositiveButton(var0.f != null?var0.f:"Submit", new n$3(var6, var0));
      var3.setNegativeButton(var0.g != null?var0.g:"Disconnect", new n$4(var0));
      var3.setOnCancelListener(new n$5(var0));
      AlertDialog var7 = j;
      if(var7 != null) {
         try {
            var7.dismiss();
         } catch (IllegalArgumentException var9) {
            var9.printStackTrace();
         }
      }

      AlertDialog var8 = var3.show();
      i = var0;
      j = var8;
      var6.requestFocus();
   }

}
