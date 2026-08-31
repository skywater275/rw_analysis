package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.scripts.ScriptEngine;

public class h extends com.corrodinggames.rts.appFramework.g {

   public void c(int var1) {
      com.corrodinggames.rts.gameFramework.l.e("InGameActivityJava selectMenuOption: " + var1);
      this.d(var1);
   }

   private void e(String var1) {
      ScriptEngine.getInstance().getRoot().makeSaveGamePopup(var1);
   }

   private void f(String var1) {
      ScriptEngine.getInstance().getRoot().makeExportMapGamePopup(var1);
   }

   public void d(int var1) {
      com.corrodinggames.rts.gameFramework.l var2;
      switch(var1) {
      case 2:
         com.corrodinggames.librocket.a.a().d();
         break;
      case 3:
         com.corrodinggames.rts.gameFramework.l.e("TODO");
         break;
      case 4:
         com.corrodinggames.rts.gameFramework.l.B().ch = !com.corrodinggames.rts.gameFramework.l.B().ch;
         break;
      case 5:
         com.corrodinggames.rts.gameFramework.l.e("TODO");
         break;
      case 6:
         var2 = com.corrodinggames.rts.gameFramework.l.B();
         var2.bl = !var2.bl;
      case 7:
      case 8:
      default:
         break;
      case 9:
         var2 = com.corrodinggames.rts.gameFramework.l.B();
         if(!var2.bo) {
            var2.bo = true;
         } else {
            var2.bo = false;
         }
         break;
      case 10:
         ScriptEngine.getInstance().addScriptToQueue("mp.multiplayerExitPrompt();");
         break;
      case 11:
         var2 = com.corrodinggames.rts.gameFramework.l.B();
         if(var2.ce != null && var2.ce.h != null) {
            var2.a("Briefing", var2.ce.h);
         }
         break;
      case 12:
         this.e((String)null);
         break;
      case 13:
         ScriptEngine.getInstance().addScriptToQueue("makeSendMessagePopup();");
         break;
      case 14:
         var2 = com.corrodinggames.rts.gameFramework.l.B();
         if(var2.bX != null) {
            var2.bX.H();
         }
         break;
      case 15:
         ScriptEngine.getInstance().addScriptToQueue("showMainMenu();");
         break;
      case 16:
         ScriptEngine.getInstance().addScriptToQueue("makeSendTeamMessagePopup();");
         break;
      case 17:
         ScriptEngine.getInstance().addScriptToQueue("mp.reinviteAsk();");
         break;
      case 18:
         this.f((String)null);
         break;
      case 19:
         ScriptEngine.getInstance().addScriptToQueue("mp.surrenderPrompt();");
         break;
      case 20:
         ScriptEngine.getInstance().addScriptToQueue("showMainMenu();");
         break;
      case 21:
         ScriptEngine.getInstance().addScriptToQueue("showBattleroom();");
         break;
      case 22:
         var2 = com.corrodinggames.rts.gameFramework.l.B();
         var2.cU = true;
         var2.bS.u = false;
         break;
      case 23:
         com.corrodinggames.librocket.a.a().e();
      }

   }

   public void m() {
      ScriptEngine.getInstance().addScriptToQueue("showMainMenu();");
   }
}
