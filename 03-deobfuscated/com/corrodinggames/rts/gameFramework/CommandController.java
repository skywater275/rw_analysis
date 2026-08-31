/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import java.io.IOException;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Iterator;

public strictfp class CommandController {
    static final boolean a = false;
    public ArrayList<Command> b = new ArrayList<Command>();
    public ArrayList<Command> c = new ArrayList<Command>();
    public ArrayList<Command> d = new ArrayList<Command>();
    static int e;

    public Command gete3() {
        Command e2 = new Command(this);
        if (a) {
            GlobalState.e("Tracing source");
            e2.b = GlobalState.a(new Exception("Test"));
        }
        return e2;
    }

    public Command gete4(com.corrodinggames.rts.game.PlayerState n2) {
        if (n2 == null) {
            throw new RuntimeException("team==null");
        }
        GlobalState l2 = GlobalState.B();
        Command e2 = new Command(this);
        e2.i = n2;
        e2.d = l2.by;
        if (a) {
            GlobalState.e("Tracing source");
            e2.b = GlobalState.a(new Exception("Test"));
        }
        if (!l2.bX.B) {
            if (!e2.l()) {
                GlobalState.b("Command failed prepareAndCheckOnServer()");
            }
            this.b.add(e2);
        } else {
            this.d.add(e2);
        }
        return e2;
    }

    /* 02b c.java L75: 无 throws; 内部 cb.a(e,int) 已去 throws → 同步去除 */
    public void c() {
        GlobalState l2 = GlobalState.B();
        e = 0;
        if (!l2.bX.B) {
            this.d();
        } else {
            this.processTickCommands();
        }
    }

    /* 02b c.java L86: 无 throws (同 c()) */
    public void d() {
        GlobalState l2 = GlobalState.B();
        int n2 = l2.bx;
        int n3 = 0;
        for (Command e2 : this.b) {
            l2.cb.a(e2, n2);
            e2.k();
            ++n3;
        }
        this.b.clear();
        if (n3 > 0) {
            l2.cb.c();
        }
    }

    /* 02b c.java e(): 无 throws (同 c()) */
    public void processTickCommands() {
        GlobalState l2 = GlobalState.B();
        int n2 = l2.bx;
        int n3 = 0;
        Iterator iterator = this.b.iterator();
        while (iterator.hasNext()) {
            Command e2 = (Command) iterator.next();
            if (e2.c != n2) continue;
            l2.cb.a(e2, n2);
            e2.k();
            iterator.remove();
            ++n3;
        }
        if (n3 > 0) {
            l2.cb.c();
        }
    }


   // 02b c.a(String) L17-26: 限速日志 (Command L679/681/697 调用; 02b 静态 e 计数)
   public static void a(String string) {
      ++e;
      if (e == 5) {
         GlobalState.e("(Rate Limiting...)");
      }
      if (e < 5) {
         GlobalState.e(string);
      }
   }

    // v19.115t 批3 补缺: 02b c.java L44 铁证 a(n) → 委托 b(n); L48 b(n) 创建 Command
    public Command b() {  // 02b c.java L34-42: b() 无参 (ReplayEngine h() 调用)
        Command var1 = new Command(this);
        if (a) {
            GlobalState.e("Tracing source");
            var1.b = GlobalState.a((Throwable)(new Exception("Test")));
        }
        return var1;
    }

    public Command a(com.corrodinggames.rts.game.PlayerState var1) {
        return this.b(var1);
    }

    public Command b(com.corrodinggames.rts.game.PlayerState var1) {
        // 02b c.java L48-127 简化: 创建 Command + 设置 team/帧 (完整体待 CommandController 战役)
        if (var1 == null) {
            throw new RuntimeException("team==null");
        }
        Command var3 = new Command(this);
        var3.i = var1;
        var3.d = GlobalState.B().by;
        return var3;
    }


    public void a() {  // 02b c.java L28 (CommandController)
    }

}
