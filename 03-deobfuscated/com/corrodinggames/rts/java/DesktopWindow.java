/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.LibRocketContext;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class DesktopWindow
extends com.corrodinggames.rts.appFramework.InGameActivity {

    public void c(int n2) {
        GlobalState.e("InGameActivityJava selectMenuOption: " + n2);
        this.d(n2);
    }

    private void e(String string) {
        ScriptEngine.getInstance().getRoot().makeSaveGamePopup(string);
    }

    private void f(String string) {
        ScriptEngine.getInstance().getRoot().makeExportMapGamePopup(string);
    }


    public void d(int n2) {
        switch (n2) {
            case 4: {
                GlobalState.B().ch = !GlobalState.B().ch;
                break;
            }
            case 2: {
                LibRocketContext.a().d();
                break;
            }
            case 3: {
                GlobalState.e("TODO");
                break;
            }
            case 6: {
                GlobalState l2 = GlobalState.B();
                l2.bl = !l2.bl;
                break;
            }
            case 5: {
                GlobalState.e("TODO");
                break;
            }
            case 12: {
                this.e(null);
                break;
            }
            case 18: {
                this.f(null);
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
                ScriptEngine.getInstance().addScriptToQueue("mp.surrenderPrompt();");
                break;
            }
            case 10: {
                ScriptEngine.getInstance().addScriptToQueue("mp.multiplayerExitPrompt();");
                break;
            }
            case 17: {
                ScriptEngine.getInstance().addScriptToQueue("mp.reinviteAsk();");
                break;
            }
            case 15: {
                ScriptEngine.getInstance().addScriptToQueue("showMainMenu();");
                break;
            }
            case 11: {
                GlobalState l4 = GlobalState.B();
                if (l4.ce == null || l4.ce.h == null) break;
                l4.a("Briefing", l4.ce.h);
                break;
            }
            case 13: {
                ScriptEngine.getInstance().addScriptToQueue("makeSendMessagePopup();");
                break;
            }
            case 16: {
                ScriptEngine.getInstance().addScriptToQueue("makeSendTeamMessagePopup();");
                break;
            }
            case 14: {
                GlobalState l5 = GlobalState.B();
                if (l5.bX == null) break;
                l5.bX.showPlayerListPopup();
                break;
            }
            case 20: {
                ScriptEngine.getInstance().addScriptToQueue("showMainMenu();");
                break;
            }
            case 21: {
                ScriptEngine.getInstance().addScriptToQueue("showBattleroom();");
                break;
            }
            case 22: {
                GlobalState l6 = GlobalState.B();
                l6.cU = true;
                l6.bS.u = false;
                break;
            }
            case 23: {
                LibRocketContext.a().e();
            }
        }
    }


    public void m() {
        ScriptEngine.getInstance().addScriptToQueue("showMainMenu();");
    }
}
