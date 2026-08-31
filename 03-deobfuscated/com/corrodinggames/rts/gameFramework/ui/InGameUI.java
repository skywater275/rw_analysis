/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.game.units.custom.effects.EffectRenderer;
import com.corrodinggames.rts.gameFramework.ads;
import com.corrodinggames.rts.gameFramework.ui.panels.SettingsPanel;
import com.corrodinggames.rts.gameFramework.KeyTrigger;
import com.corrodinggames.rts.gameFramework.ui.panels.ReplayPanel;
import com.corrodinggames.rts.gameFramework.VersionInfo;
import com.corrodinggames.rts.gameFramework.GameTimerScheduler;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.TeamChatAction;
import com.corrodinggames.rts.game.units.actions.MapPingAction;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.PathState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.PathfindingHelper;
import com.corrodinggames.rts.gameFramework.utility.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.UnitInfoPanel;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer;
import com.corrodinggames.rts.gameFramework.ui.UnitStateTracker;
import com.corrodinggames.rts.gameFramework.ui.WaypointManager;
import com.corrodinggames.rts.gameFramework.ui.ActionCooldown;
import com.corrodinggames.rts.gameFramework.ui.TextFormatter;
import com.corrodinggames.rts.gameFramework.ui.h;
import com.corrodinggames.rts.gameFramework.ui.CameraMode;
import com.corrodinggames.rts.gameFramework.ui.ScreenUtils;
import com.corrodinggames.rts.gameFramework.ui.ResourceDisplay;
import com.corrodinggames.rts.gameFramework.ui.MessagePanel;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.mods.ModInfo;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;

import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;

public final class InGameUI
extends BaseGameObject {
    public float ah;
    public float ag;
    public com.corrodinggames.rts.game.units.UnitInstance aa;  // 02b f/g.java L85: am aa (MusicController 为幻觉名)
    public Paint aA;
    public Paint aC;
    public int aX;
    public float aU;
    public static boolean a = false;

    // v19.112d 补插 (02b f/g.java L3896: a(j.as) 写方法)
    @Override
    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream var1) {
        this.g.serializeToStream(var1);  // 03 ActionPanel 写方法已改名
        var1.c(1);
        var1.a(this.ad);
    }
    public boolean b = true;
    public boolean c = false;
    public float d = 0.0f;
    public boolean e = false;
    com.corrodinggames.rts.game.units.Factory f;
    public ActionPanel g;
    public MessagePanel selectionGroup;
    public ToastMessage h;  // 02b f/g.java: f.m h (ToastMessage 显示容器)
    public WaypointManager i;
    public ResourceDisplay j;
    public RateGameDialog k;
    com.corrodinggames.rts.game.units.actions.AttackMoveAction l = new com.corrodinggames.rts.game.units.actions.AttackMoveAction();  // 02b L48: units.a.e (c_4)
    com.corrodinggames.rts.game.units.actions.GuardAction m = new com.corrodinggames.rts.game.units.actions.GuardAction();  // 02b L49: units.a.f (c_8)
    com.corrodinggames.rts.game.units.actions.PatrolAction n = new com.corrodinggames.rts.game.units.actions.PatrolAction();
    com.corrodinggames.rts.game.units.actions.PatrolAction o = new com.corrodinggames.rts.game.units.actions.PatrolAction();  // 02b L51: units.a.i (c_9=PatrolAction; AttackAction 为幻觉名)
    public com.corrodinggames.rts.game.units.actions.PingAction p = new com.corrodinggames.rts.game.units.actions.PingAction();
    com.corrodinggames.rts.game.units.actions.MapPingAction uiStateA = new com.corrodinggames.rts.game.units.actions.MapPingAction();  // 02b L53: units.a.r (Map Ping)
    com.corrodinggames.rts.game.units.actions.TeamChatAction uiStateB = new com.corrodinggames.rts.game.units.actions.TeamChatAction();  // 02b L54: units.a.q (Team Chat)
    com.corrodinggames.rts.gameFramework.ui.panels.l uiLayout = new com.corrodinggames.rts.gameFramework.ui.panels.a();  // 02b f/g.java L55: f.a.l s = new f.a.a() (UIContextMenu 为幻觉包名)
    boolean isDragging = false;
    public boolean u = false;
    double v;
    float selectionStartX = 0.0f;
    public float x = 0.0f;
    public float y = 0.0f;
    float z = 40.0f;
    float A = 40.0f;
    int B = 0;
    boolean C;
    boolean D;
    float buildMenuX;
    public float buildMenuY;
    public float buildMenuTargetX;
    boolean isMultiSelect = false;
    boolean I = false;
    boolean J = false;
    boolean K = false;
    boolean L = false;
    boolean M = false;
    float N = 0.0f;
    float lastTouchY = 0.0f;
    float P = 0.0f;
    float Q = 0.0f;
    float R = 0.0f;
    float S = 0.0f;
    boolean T = false;
    boolean U = false;
    boolean V = false;
    public UnitInstance W;
    public float X;
    public int Y;
    public float Z;
    public UnitInstance hoveredUnit;
    public final boolean touchInputEnabled = true;
    public com.corrodinggames.rts.game.units.actions.GameAction ac;
    public int activePlayerIndex;
    public boolean showResourcePanel;
    public float af;
    public float resourcePanelY;
    public float minimapX;
    public boolean showMinimapControls;
    public float aj;
    public float ak;
    public float actionPanelWidth;
    public float am;
    public float an;
    public float ao;
    public boolean ap;
    public float aq;
    public float ar;
    public int selectedActionIndex;
    public final Paint defaultPaint = new Paint();
    public Paint selectionBoxPaint;
    public Paint av;
    public Paint hpBarGreenPaint;
    public Paint ax;
    public Paint ay;
    public Paint az;
    public Paint energyBarPaint;
    public Paint aB;
    public Paint buttonBgPaint;
    public Paint aD;
    public Paint aE;
    public Paint aF;
    public Paint aG;
    public Paint aH;
    public Paint aI;
    public Paint aJ;
    Paint textSmallPaint;
    Paint aL;
    Paint aM;
    Paint aN;
    Paint aO;
    Paint aP;
    com.corrodinggames.rts.gameFramework.rendering.UniquePaint aQ;
    com.corrodinggames.rts.gameFramework.rendering.UniquePaint aR;
    com.corrodinggames.rts.gameFramework.rendering.UniquePaint aS;
    public float aT;
    public float float30 = 0.0f;
    public float aV = 0.0f;
    public float float32 = 0.0f;
    int int5;
    public float aY = 0.0f;
    public boolean aZ;
    com.corrodinggames.rts.gameFramework.rendering.Texture ba = null;
    com.corrodinggames.rts.gameFramework.rendering.Texture uiUnitPanelConfig = null;
    com.corrodinggames.rts.gameFramework.rendering.Texture uiCommandPanelConfig = null;
    boolean boolean23;
    float float34;
    Paint bf;
    Paint paint25;
    com.corrodinggames.rts.gameFramework.rendering.Texture bh = null;
    com.corrodinggames.rts.gameFramework.rendering.Texture bi = null;
    public com.corrodinggames.rts.gameFramework.rendering.Texture bj = null;
    public com.corrodinggames.rts.gameFramework.rendering.Texture bk = null;
    public com.corrodinggames.rts.gameFramework.rendering.Texture bl = null;
    com.corrodinggames.rts.gameFramework.rendering.Texture bm = null;
    public com.corrodinggames.rts.gameFramework.rendering.Texture bn;
    public com.corrodinggames.rts.gameFramework.rendering.Texture bo;
    com.corrodinggames.rts.gameFramework.ui.panels.e bp;  // 02b L156: f.a.e
    com.corrodinggames.rts.gameFramework.ui.panels.e bq;
    com.corrodinggames.rts.gameFramework.ui.panels.e br;
    com.corrodinggames.rts.gameFramework.ui.panels.e bs;
    com.corrodinggames.rts.gameFramework.ui.panels.e bt;
    com.corrodinggames.rts.gameFramework.ui.panels.e bu;
    final Rect bv = new Rect();
    final Rect bw = new Rect();
    final Rect bx = new Rect();
    final Rect by = new Rect();
    final Rect bz = new Rect();
    final Paint bA = new Paint();
    final Paint bB = new Paint();
    final Paint bC = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    public final Paint bD = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    final Paint bE = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    final Paint bF = new Paint();
    String bG;
    String bH;
    LocalizedString bI;  // 02b f/g.java L175: bb bI (custom/bb=LocalizedString) (v19.133d)
    String bJ;
    String bK;
    String bL;
    public ArrayList bM = new ArrayList();
    private int cf;
    private int cg;
    private int ch;
    private float ci;
    private int cj;
    private int ck;
    private int cl;
    public boolean bN = false;
    public static boolean bO = false;
    public static boolean bP = false;
    public static boolean bQ = false;
    public static boolean bR;
    com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel bS = com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel.b(-1, -1);  // 02b L192: f.a.c
    EffectRenderer bT = new EffectRenderer();
    long bU = -1L;
    long bV = -1L;
    long bW;
    boolean bX;
    public UnitRegistry bY = new UnitRegistry();
    public UnitRegistry bZ = new UnitRegistry();
    public static UnitInstance ca;
    Paint cb = new Paint();
    Rect cc = new Rect();
    static int cd;
    static boolean ce;

    public int ad;  // 02b f/g.java L92: int ad (v19.117 补)

    float b() {
        return Math.min(this.selectionStartX * 2.5f, 290.0f) + 10.0f;
    }

    float c() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f2 = 0.7f;
        if (com.corrodinggames.rts.gameFramework.GlobalState.av()) {
            f2 = 0.9f;
        }
        if (l2.cX < 1.0f) {
            float f3 = l2.cX;
            if ((double)f3 < 0.4) {
                f3 = 0.4f;
            }
            f2 *= f3;
        }
        return f2;
    }

    public void a(String string) {
        // 02b f/g.java L247-249: 显示消息 (无超时)
        this.g.a(string);
    }
    public void a(com.corrodinggames.rts.gameFramework.ui.panels.f f2) {
        // 02b f/g.java L4622-4628 简化: 显示重连对话框面板 (MinimapPanel c/d 缺失, 仅 u_())
        f2.u_();
    }
    public void b(String string, int n2) {
        this.g.b(string, n2);
    }

    public void b(String string) {
        this.g.a(string, 100);
    }

    public void c(String string) {
        this.g.a(string, 50);
    }

    public void d(String string) {
        this.g.a(string, 5);
    }

    public void d() {
        this.U = false;
        this.V = false;
        this.I = false;
    }

    public void clearActionPanel() {
        if (this.g != null) {
            this.g.a();
        }
    }

    public void setupDebugModes() {
        bO = false;
        bP = false;
        bQ = false;
        if (com.corrodinggames.rts.gameFramework.GlobalState.av()) {
            bO = true;
            bP = true;
            a = true;
            bQ = true;
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            bO = true;
            bP = true;
            bQ = true;
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.at() && !com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.classicInterface) {
            bO = true;
            bP = true;
            bQ = true;
        }
    }

    public void clearSelection() {
        this.selectionGroup.b();
        this.i.b();
        this.bX = false;  // 02b f/g.java L536: this.bX = false (player 为幻觉名)
    }

    public void h() {
        if (this.f != null) {
            this.l(this.f);
            this.f.ci();
            this.f = null;
        }
    }

    public com.corrodinggames.rts.game.units.Factory getDebugFactory() {
        return this.f;
    }

    public void a(float f2, float f3, float f4, float f5) {  // 02b f/g.java L4590-4593 (this.a(cc) 深水区简化)
        this.cc.a((int)f2, (int)f3, (int)(f2 + f4), (int)(f3 + f5));
    }

    public boolean a(int n2, int n3, int n4, int n5, CameraMode cameraMode, boolean bl) {  // 02b f/g.java L4595-4599
        this.a((float)n2, (float)n3, (float)n4, (float)n5);
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        return (bl && this.I || this.U) && this.bx.b((int)this.x, (int)this.y);
    }

    public void a(com.corrodinggames.rts.game.units.Factory factory) {  // 02b f/g.java L1101-1103: a(h){this.f=var1}
        this.f = factory;
    }

    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream inputNetStream, boolean bl) {  // 02b f/g.java L3902-3909 (this.g.a 深水区简化 TODO)
        byte by = inputNetStream.d();
        if (by >= 1) {
            this.ad = inputNetStream.f();
        }
    }

    public boolean b(com.corrodinggames.rts.gameFramework.GlobalState l2) {  // 02b f/g.java L1109: b(l) (ScoreEntry 为幻觉名)
        if (!l2.bQ.keyboardSupport) {
            return false;
        }
        return l2.c(113, 114);
    }

    public boolean c(com.corrodinggames.rts.gameFramework.GlobalState l2) {  // 02b L1113: c(l)
        if (!l2.bQ.keyboardSupport) {
            return false;
        }
        return l2.c(57, 58);
    }

    public void b(float f2) {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.Z += 0.2f * f2;
        if (this.Z > 360.0f) {
            this.Z -= 360.0f;
        }
        this.bx.a((int)(l2.cl - l2.cq), 0, (int)l2.cl, (int)l2.cm);
        if (!bO) {
            if (this.bN) {
                this.bA.a();
                this.bA.b(Color.a(255, 33, 40, 52));
                this.bA.a(Paint$Style.a);
                l2.bO.b(this.bx, this.bA);
            } else {
                l2.bO.a(this.bl, this.bx, null);
            }
            this.bA.a();
            this.bA.b(Color.a(255, 0, 0, 0));
            this.bA.a(Paint$Style.b);
            l2.bO.b(this.bx, this.bA);
        }
        this.cf = 0;
        this.ch = 0;
        this.cg = 0;
        this.ck = this.cl;
        this.cl = 0;
        if (l2.cb.j() || l2.bs != null && l2.bs.b()) {
            object = this.s();  // 02b L1157: var3 = this.s() (uiLayout 为幻觉名)
            if (object != null) {
                this.a(l2, ((UnitInstance) object).player, false, true);  // 02b L1159: this.a(var2, var3.bX, false, true)
            }
        } else {
            object = this.t();  // 02b L1148: var3 = this.t() (isDragging 为幻觉名)
            if (l2.bs != null && l2.bs != com.corrodinggames.rts.game.PlayerState.i && !l2.bs.b() && !l2.cb.j()) {
                this.a(l2, l2.bs, false, true);
            }
            if (object != null && l2.bs != ((UnitInstance) object).player && this.m((UnitInstance) object)) {
                this.a(l2, ((UnitInstance) object).player, true, true);
            }
        }
        if (l2.bv && !l2.cb.j()) {
            object = "";
            if (l2.bv) {
                object = (String)object + "Editor Active\n";
            }
            if (l2.bt != 1.0f) {
                object = (String)object + "Game Speed: " + l2.bt + "x\n";
            }
            if (l2.bw) {
                object = (String)object + "Invincible Units\n";
            }
            boolean bl = false;
            java.util.Iterator iterator = com.corrodinggames.rts.game.PlayerState.c().iterator();  // 02b L1178: n.c() raw 迭代
            while (iterator.hasNext()) {
                com.corrodinggames.rts.game.PlayerState n2 = (com.corrodinggames.rts.game.PlayerState)iterator.next();
                if (!(n2 instanceof com.corrodinggames.rts.game.ai.AIStrategy)) continue;
                com.corrodinggames.rts.game.ai.AIStrategy a2 = (com.corrodinggames.rts.game.ai.AIStrategy)n2;
                bl = a2.updateCooldown > 0.0f;  // 02b L1184: bG=updateCooldown (字段保序 zip)
            }
            if (bl) {
                object = (String)object + "AIs frozen\n";
            }
            this.bA.a();
            this.bA.b(Color.a(0, 0, 0, 0));
            this.bA.a(Paint$Style.a);
            float f3 = 70.0f * l2.cj;
            float f4 = 40.0f;
            if (l2.cl < 600.0f && l2.cm > 650.0f) {
                f3 = 10.0f;
                f4 = 60.0f * l2.cj;
            }
            l2.bO.a((String)object, f3, f4, this.ay, this.bA, 6.0f);
        }
        this.emptyUpdate();
        this.uiLayout.f();
    }

    public void emptyUpdate() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    }

    public boolean drawNoButton() {
        return this.a(com.corrodinggames.rts.gameFramework.ui.h.b, true);
    }

    public boolean b(boolean bl) {
        return this.a(bl ? com.corrodinggames.rts.gameFramework.ui.h.a : com.corrodinggames.rts.gameFramework.ui.h.b, false);
    }

    public boolean l() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.ac != null) {
            if (this.ac.e() == com.corrodinggames.rts.game.units.actions.ActionTargetType.b) {
                this.ac = null;
                this.showResourcePanel = false;
                this.showMinimapControls = false;
                this.hoveredUnit = null;
                this.ap = false;
                ++this.activePlayerIndex;
            } else {
                this.ac = null;
            }
            this.selectedActionIndex = 0;
            return true;
        }
        return false;
    }

    public void c(float f2) {
        int n2;
        int n3;
        int n4;
        float f3;
        float f4;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Point point = l2.bW.c(this.x, this.y);
        if (point != null) {
            f4 = point.a;
            f3 = point.b;
        } else {
            f4 = this.x / l2.cX + l2.cw;
            f3 = this.y / l2.cX + l2.cx;
        }
        this.af = GameUtils.a(this.af, f2);
        this.bx.a((int)(l2.cl - l2.cq), 0, (int)l2.cl, (int)l2.cm);
        if (!bO && (this.U || this.I) && this.bx.b((int)this.x, (int)this.y)) {
            this.aZ = true;
        }
        this.g.a(f2);
        this.g.b(f2);
        this.X += f2;
        if (!l2.A()) {
            n4 = this.g.d(f2);
            this.g.a(f2, n4);
            this.g.e(f2);
            this.selectionGroup.a(f2, com.corrodinggames.rts.gameFramework.ui.MessagePanel.a);
            this.i.a(f2);
            n3 = Math.max((int)((float)this.cf + this.ci * 2.0f), 130);
            this.j.a(f2, n3);
            if (this.u) {
                this.g.c(f2);
            }
            this.k.drawDialog(f2);  // 02b f/f.java L117: b(float) (RateGameDialog 语义名)
            this.g.a(f2, true);
        }
        this.a(f2, f4, f3, point);
        if (!l2.A() && !this.u) {
            this.g.c(f2);
        }
        n4 = 0;
        if (!this.T) {
            n3 = 1;
            n2 = 1;
            boolean bl = true;
            if (com.corrodinggames.rts.gameFramework.GlobalState.av() && l2.bQ.mouseSupport) {
                if (l2.bQ.mouseOrders == 0) {
                    n3 = 1;
                } else {
                    n3 = 0;
                    n2 = 0;
                    bl = false;
                    if (l2.bQ.mouseOrders == 1) {
                        if (l2.e(1)) {
                            n2 = 1;
                        } else if (l2.e(2)) {
                            bl = true;
                        }
                    } else if (l2.e(2)) {
                        n2 = 1;
                    } else if (l2.e(1)) {
                        bl = true;
                    }
                }
            }
            float f5 = f4;
            float f6 = f3;
            if (this.I && point != null && this.J) {
                boolean bl2 = false;
                if (n3 == 0 && !bl) {
                    bl2 = true;
                }
                if (this.q() == 0 || !this.hasTransporterSelected()) {  // 02b L1544: q()==0 (uiStateA 为幻觉名)
                    bl2 = true;
                }
                if (n3 != 0 && this.selectionStartX > 20.0f) {
                    bl2 = true;
                }
                if (bl2) {
                    l2.b(f5, f6);
                    n4 = 1;
                }
            }
            if ((this.C || point != null && (n3 != 0 || bl)) && n4 == 0 && this.ac == null && this.U) {
                if (this.selectionStartX > 30.0f) {
                    if (this.a() && point == null) {
                        float f7 = this.b();
                        this.clearFullSelection();
                        this.b(f5, f6, f7 /= l2.cX);
                        this.E();  // 02b L1565: E() (buildMenuX 为幻觉名)
                    }
                } else {
                    l2.cU = false;
                    if (n3 == 0) {
                        if (n2 != 0) {
                            UnitInstance am2 = null;
                            if (point == null) {
                                am2 = this.a(f5, f6, true);
                            }
                            this.a(am2);
                        } else if (bl) {
                            UnitInstance am3 = null;
                            if (point == null) {
                                am3 = this.a(f5, f6, false);
                            }
                            boolean bl3 = false;
                            if (am3 == null) {
                                bl3 = true;
                            } else if (!this.a(am3, false, f5, f6, point)) {
                                bl3 = true;
                            }
                            if (bl3) {
                                this.c(f5, f6, point);
                            }
                        }
                    } else {
                        UnitInstance am4 = null;
                        UnitInstance am5 = null;
                        if (point == null) {
                            am4 = this.a(f5, f6, true);
                            am5 = this.a(f5, f6, false);
                        }
                        if (am4 == null && am5 == null) {
                            this.c(f5, f6, point);
                        } else if (am5 != null) {
                            if (!this.a(am5, true, f5, f6, point)) {
                                if (!am5.t()) {
                                    this.a(am5);
                                } else if (am4 != null) {
                                    this.a(am4);
                                }
                            }
                        } else {
                            this.a(am4);
                        }
                    }
                }
            }
        }
        if (this.ac == null && this.I && !this.T && !this.J && !this.aZ) {
            this.selectionBoxPaint.a(Paint$Style.a);
            this.selectionBoxPaint.a(1.0f);
            if (this.selectionStartX > 20.0f && this.a()) {
                float f8 = this.b();
                this.selectionBoxPaint.a(100, 0, 255, 0);
                l2.bO.a(this.x, this.y, f8, this.selectionBoxPaint);
                this.selectionBoxPaint.a(Paint$Style.b);
                this.selectionBoxPaint.a(1.0f);
                this.selectionBoxPaint.a(200, 0, 255, 0);
                l2.bO.a(this.x, this.y, f8, this.selectionBoxPaint);
            }
        }
        if (l2.bk && l2.ac() && l2.ae() > 0) {
            Paint paint = new Paint();
            paint.c(100);
            for (n2 = 0; n2 < l2.ae(); ++n2) {
                l2.bO.i();
                l2.bO.a(0.7f, 0.7f, l2.b(n2), l2.c(n2));
                l2.bO.a(this.bm, l2.b(n2), l2.c(n2), paint);
                l2.bO.j();
            }
        }
        if (!this.I) {
            this.selectionStartX = 0.0f;
            this.T = false;
        }
        this.isMultiSelect = this.I;
        l2.ad();
        if (ce) {
            K();  // 02b f/g.java L4630: 静态 K() (ui.g 为幻觉包名, 本类自身)
            ce = false;
        }
    }

    public boolean m() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return com.corrodinggames.rts.gameFramework.GlobalState.aw() && l2.bQ.mouseSupport && !this.n() && !this.p();
    }

    public boolean n() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (com.corrodinggames.rts.gameFramework.GlobalState.aw() && l2.bQ.mouseSupport && this.U && !this.T && !this.aZ) {
            int n2 = 1;
            int n3 = 2;
            if (l2.bQ.mousePlacement == 2) {
                n2 = 2;
                n3 = 1;
            }
            if (l2.e(n2)) {
                // empty if block
            }
            if (l2.e(n3)) {
                return true;
            }
        }
        return false;
    }

    public boolean o() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (com.corrodinggames.rts.gameFramework.GlobalState.av() && l2.bQ.mouseSupport && (this.U || this.I)) {
            int n2 = 1;
            int n3 = 2;
            if (l2.bQ.mousePlacement == 2) {
                n2 = 2;
                n3 = 1;
            }
            if (l2.e(n2)) {
                return true;
            }
            if (l2.e(n3)) {
                // empty if block
            }
        }
        return false;
    }

    public boolean p() {
        if (this.U && !this.T && !this.aZ) {
            return this.o();
        }
        return false;
    }

    public int q() {
        return this.int5;
    }

    public com.corrodinggames.rts.game.PlayerState r() {  // 02b L2573: r() 返回 n (Notification 为幻觉名)
        UnitType y2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            y2 = (UnitType) am2;
            if (y2.player != l2.bs) continue;
            return y2.player;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType) || !this.m(y2 = (UnitType) am2)) continue;
            return y2.player;
        }
        return l2.bs;
    }

    public com.corrodinggames.rts.game.units.UnitType s() {  // 02b L2604: s() 返回 y (StatsPanel 为幻觉名)
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            return y2;
        }
        return null;
    }

    public com.corrodinggames.rts.game.units.UnitType t() {  // 02b L2620: t() 返回 y
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            UnitType y2;
            if (!(am2 instanceof UnitType) || !this.m(y2 = (UnitType) am2)) continue;
            return y2;
        }
        return null;
    }

    public boolean b(com.corrodinggames.rts.game.units.actions.GameAction s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.BuildAction) {
            com.corrodinggames.rts.game.units.actions.BuildAction g2 = (com.corrodinggames.rts.game.units.actions.BuildAction)s2;
            return g2.b(g2.buildUnitType);  // 02b L2749: var8.a(var8.b, true) b=public y 字段
        }
        com.corrodinggames.rts.game.units.actions.ActionId c2 = s2.N();  // 02b units/a/c.java=ActionId (ActionConfig 为幻觉名)
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            GameAction s3;
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.b(y2)) continue;
            return true;
        }
        return false;
    }

    public boolean c(com.corrodinggames.rts.game.units.actions.GameAction s2) {
        boolean bl2 = false;
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.BuildAction) {
            com.corrodinggames.rts.game.units.actions.BuildAction g2 = (com.corrodinggames.rts.game.units.actions.BuildAction)s2;
            return g2.g(g2.buildUnitType);
        }
        com.corrodinggames.rts.game.units.actions.ActionId c2 = s2.N();  // 02b units/a/c.java=ActionId (ActionConfig 为幻觉名)
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            GameAction s3;
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null) continue;
            if (!s3.g(y2)) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
    }

    public String d(com.corrodinggames.rts.game.units.actions.GameAction s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.BuildAction) {
            com.corrodinggames.rts.game.units.actions.BuildAction g2 = (com.corrodinggames.rts.game.units.actions.BuildAction)s2;
            return g2.j(g2.buildUnitType);
        }
        com.corrodinggames.rts.game.units.actions.ActionId c2 = s2.N();  // 02b units/a/c.java=ActionId (ActionConfig 为幻觉名)
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            String string;
            GameAction s3;
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.g(y2) || (string = s3.j(y2)) == null) continue;
            return string;
        }
        return null;
    }

    public UnitRegistry clearActionPanel(com.corrodinggames.rts.game.units.actions.GameAction s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.BuildAction) {
            com.corrodinggames.rts.game.units.actions.BuildAction g2 = (com.corrodinggames.rts.game.units.actions.BuildAction)s2;
            this.bY.clear();
            if (g2.buildUnitType != null) {
                this.bY.a(g2.buildUnitType);
            }
            return this.bY;
        }
        return this.bZ;
    }

    public String setupDebugModes(com.corrodinggames.rts.game.units.actions.GameAction s2) {
        UnitRegistry u2 = this.clearActionPanel(s2);
        com.corrodinggames.rts.game.units.actions.ActionId c2 = s2.N();  // 02b units/a/c.java=ActionId (ActionConfig 为幻觉名)
        String string = null;
        boolean bl2 = false;
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) u2) {
            GameAction s3;
            UnitType y2;
            if (!(am2 instanceof UnitType) || !this.m(y2 = (UnitType) am2) || (s3 = y2.a(c2)) == null) continue;
            if (s3.B() != null && !s3.B().b(y2)) {
                String string2 = s3.B().a((UnitInstance) y2, 4, true);
                if (string2 == null) continue;
                string = string2;
                continue;
            }
            bl2 = true;
        }
        if (bl2) {
            return null;
        }
        return string;
    }

    public boolean canAnySelectedBuild() {
        if (this.int5 == 0) {
            return false;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG) continue;
            if (!this.m(y2)) {
                return false;
            }
            ArrayList arrayList = y2.N();
            boolean bl2 = false;
            if (arrayList != null) {
                for (com.corrodinggames.rts.game.units.actions.GameAction s2 : (java.util.Collection<com.corrodinggames.rts.game.units.actions.GameAction>) (java.util.Collection) arrayList) {
                    if (s2.e() != com.corrodinggames.rts.game.units.actions.ActionTargetType.d) continue;
                    bl2 = true;
                }
            }
            if (bl2) continue;
            return false;
        }
        return true;
    }

    public void b(float f2, float f3, Point point) {
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.hasTransporterSelected()) {
            if (l2.bQ.quickRally && this.canAnySelectedBuild()) {
                this.b(f2, f3);
                return;
            }
            return;
        }
        com.corrodinggames.rts.gameFramework.Command e3 = this.createCommandAll();
        e3.h = true;
        e3.a(f2, f3);
        this.a(e3);
        if (!this.a(com.corrodinggames.rts.game.units.PathState.b)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.f, 0.2f);
        }
        if ((e2 = l2.bR.a(f2, f3, 0.0f, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e)) != null) {
            e2.ap = 8;
            e2.W = e2.V = 30.0f;
            e2.r = true;
            e2.E = 2.0f;
            e2.G = 2.8f * this.c();
            e2.F = 1.6f * this.c();
            e2.H = true;
        }
        if (point != null) {
            Point point2 = l2.bW.b(point.a, point.b);
            com.corrodinggames.rts.gameFramework.effects.HUDElement e4 = l2.bR.a((float)point2.a, (float)point2.b, 0.0f, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
            if (e4 != null) {
                e4.ar = (short)4;
                e4.ap = 8;
                e4.V = 35.0f;
                e4.W = e2.V;
                e4.r = true;
                e4.E = 2.0f;
                e4.G = 1.3f;
                e4.F = 0.6f;
            }
        }
    }

    public void c(float f2, float f3, Point point) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.D && l2.bQ.doubleClickToAttackMove && this.hasFactorySelected() && this.hasTransporterSelected()) {
            this.d(f2, f3, point);
        } else {
            this.b(f2, f3, point);
        }
    }

    public void d(float f2, float f3, Point point) {
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e3 = this.createCommandAll();
        e3.h = true;
        e3.b(f2, f3);
        this.a(e3);
        if (!this.a(com.corrodinggames.rts.game.units.PathState.b)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.f, 0.2f);
        }
        if ((e2 = l2.bR.a(f2, f3, 0.0f, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e)) != null) {
            e2.aq = 17;
            e2.ap = 2;
            e2.W = e2.V = 30.0f;
            e2.r = true;
            e2.E = 2.0f;
            e2.Z = 1.0f;
            e2.G = 1.9f * this.c();
            e2.F = 3.5f * this.c();
            e2.H = true;
        }
        if (point != null) {
            Point point2 = l2.bW.b(point.a, point.b);
            com.corrodinggames.rts.gameFramework.effects.HUDElement e4 = l2.bR.a((float)point2.a, (float)point2.b, 0.0f, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
            if (e4 != null) {
                e4.ar = (short)4;
                e4.ap = 9;
                e4.V = 35.0f;
                e4.W = e2.V;
                e4.r = true;
                e4.E = 2.0f;
                e4.G = 1.3f;
                e4.F = 0.6f;
            }
        }
    }

    public void issueStopCommand() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        e2.h();
        this.a(e2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.f, 0.2f);
    }

    public void b(com.corrodinggames.rts.game.units.actions.GameAction s2, float f2, float f3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PointF pointF = new PointF(f2, f3);
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        if (!s2.I()) {
            this.a(e2, s2);
        } else {
            this.a(e2, s2, false);
        }
        e2.a(s2.N(), pointF, null);
        this.a(s2, pointF, null, e2);
        if (!s2.getDescription(f2, f3)) {  // 02b L3145: !var1.a(var2,var3) — 03 语义名
            l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.f, 0.2f);
            com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(f2, f3, 0.0f, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
            if (e3 != null) {
                e3.ap = 9;
                e3.W = e3.V = 60.0f;
                e3.r = true;
                e3.E = 2.0f;
                e3.G = 3.8f * this.c();
                e3.F = 2.0f * this.c();
                e3.H = true;
                e3.Z = 1.5f;
            }
        }
    }

    public void b(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        this.a(e2);
        e2.d(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.f, 0.2f);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        if (e3 != null) {
            e3.ap = 12;
            e3.W = e3.V = 25.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.2f * this.c();
            e3.F = 1.8f * this.c();
        }
    }

    public void b(float f2, float f3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommand();
        this.a(e2);
        PointF pointF = new PointF(f2, f3);
        e2.a(pointF);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.f, 0.2f);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(f2, f3, 0.0f, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        if (e3 != null) {
            e3.ap = 8;
            e3.W = e3.V = 65.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.Z = 2.0f;
            e3.G = 2.0f * this.c();
            e3.F = 1.5f * this.c();
        }
    }

    public com.corrodinggames.rts.gameFramework.Command createCommand() {  // 02b f/g.java L3328: w()
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.b(l2.bs);  // 02b L3330
        if (l2.bX.B) {
            e2.p = l2.bs;
        }
        return e2;
    }

    public com.corrodinggames.rts.gameFramework.Command createCommandAll() {  // 02b L3338: x()
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommand();
        if (this.a(l2)) {
            e2.e = true;
        }
        return e2;
    }

    public void c(UnitInstance am2) {
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e3 = this.createCommandAll();
        e3.a(am2);
        this.a(e3);
        if (!this.a(com.corrodinggames.rts.game.units.PathState.a)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.d, 1.0f);
        }
        if ((e2 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e)) != null) {
            e2.b = am2;
            e2.I = 0.0f;
            e2.J = 0.0f;
            e2.K = 0.0f;
            e2.ap = 9;
            e2.W = e2.V = 35.0f;
            e2.r = true;
            e2.E = 1.5f;
            e2.H = true;
            e2.Z = 0.8f;
            e2.G = 1.9f * this.c();
            e2.F = 3.3f * this.c();
        }
        if ((e2 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e)) != null) {
            e2.b = am2;
            e2.I = 0.0f;
            e2.J = 0.0f;
            e2.K = 0.0f;
            e2.aq = 17;
            e2.ap = 0;
            e2.W = e2.V = 25.0f;
            e2.r = true;
            e2.E = 1.0f;
            e2.H = true;
            e2.Z = 0.8f;
            e2.G = 2.2f * this.c();
            e2.F = 1.1f * this.c();
        }
    }

    public void d(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        this.a(e2);
        e2.b(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.d, 1.0f);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        if (e3 != null) {
            e3.ap = 10;
            e3.W = e3.V = 35.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.5f * this.c();
            e3.F = 2.2f * this.c();
        }
    }

    public void clearActionPanel(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        this.a(e2);
        e2.c(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.d, 1.0f);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        if (e3 != null) {
            e3.aq = 17;
            e3.ap = 1;
            e3.W = e3.V = 40.0f;
            e3.r = true;
            e3.E = 1.0f;
            e3.H = true;
            e3.Z = 0.0f;
            e3.G = 1.2f * this.c();
            e3.F = 1.9f * this.c();
        }
    }

    public void setupDebugModes(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        this.a(e2);
        e2.e(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.d, 1.0f);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        if (e3 != null) {
            e3.ap = 11;
            e3.W = e3.V = 25.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.8f * this.c();
            e3.F = 1.6f * this.c();
        }
    }

    public void clearSelection(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = this.createCommandAll();
        this.a(e2);
        e2.f(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.d, 1.0f);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        if (e3 != null) {
            e3.ap = 11;
            e3.W = e3.V = 25.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.8f * this.c();
            e3.F = 1.6f * this.c();
        }
    }

    public void b(float f2, float f3, float f4) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        for (com.corrodinggames.rts.gameFramework.GameObject w2 : com.corrodinggames.rts.gameFramework.GameObject.er) {  // 02b L3622: w.er (EffectConfig 为幻觉名)
            float f5;
            if (!(w2 instanceof UnitInstance)) continue;
            UnitInstance am2 = (UnitInstance) w2;
            if (am2.isDead || am2.cN != null || am2.player != l2.bs || !((f5 = GameUtils.a(f2, f3, am2.eo, am2.ep - am2.eq)) < f4 * f4)) continue;
            this.emptyUpdate(am2);
        }
    }

    public void h(UnitInstance am2) {
        this.W = null;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        for (com.corrodinggames.rts.gameFramework.GameObject w2 : com.corrodinggames.rts.gameFramework.GameObject.er) {  // 02b L3622: w.er (EffectConfig 为幻觉名)
            if (!(w2 instanceof UnitInstance)) continue;
            UnitInstance am3 = (UnitInstance) w2;
            if (am3.isDead || am3.cN != null || am3.player != am2.player || !am3.s_() || !com.corrodinggames.rts.gameFramework.ui.ActionPanel.a(am3, am2) || am3.player != l2.bs && !am3.canMove()) continue;
            this.emptyUpdate(am3);
        }
    }

    public void clearFullSelection() {
        this.W = null;
        for (com.corrodinggames.rts.gameFramework.GameObject w2 : com.corrodinggames.rts.gameFramework.GameObject.er) {  // 02b L3622: w.er (EffectConfig 为幻觉名)
            if (!(w2 instanceof UnitInstance)) continue;
            UnitInstance am2 = (UnitInstance) w2;
            am2.cG = false;
        }
        this.int5 = 0;
        ++this.Y;
        this.bZ.clear();
        K();  // 02b L3635: 本类静态 K()
    }

    public boolean getDebugFactory(UnitInstance am2) {
        boolean bl2;
        if (am2.t()) {
            return false;
        }
        PlayerState n2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bs;
        return n2 == null || !(bl2 = n2.c(am2.player)) || am2.isTargetableForAI();
    }

    public boolean emptyUpdate(UnitInstance am2) {
        if (am2.cG) {
            return true;
        }
        if (!this.getDebugFactory(am2)) {
            return false;
        }
        this.drawNoButton(am2);
        this.a(com.corrodinggames.rts.game.units.PathState.c, am2);  // 02b L2988: a(ag,am)
        return true;
    }

    public void drawNoButton(UnitInstance am2) {
        if (!am2.cG) {
            if (!this.getDebugFactory(am2)) {
                return;
            }
            am2.cG = true;
            am2.cH = com.corrodinggames.rts.gameFramework.GlobalState.B().bA;
            ++this.int5;
            if (!(am2 instanceof com.corrodinggames.rts.game.units.Factory)) {
                ca = am2;
            }
            ++this.Y;
            this.bZ.a(am2);
            K();  // 02b L3635: 本类静态 K()
        }
    }

    public static com.corrodinggames.rts.gameFramework.mods.ModInfo getLastSelectedCustomType() {  // 02b L3686: z() 返回 i/b=ModInfo (HUDOverlay 为幻觉名)
        UnitInstance am2 = ca;
        if (am2 == null) {
            return null;
        }
        com.corrodinggames.rts.game.units.UnitTypeHandle as2 = am2.r();  // 02b L3691: as var1 (PacketBuilder 为幻觉名)
        if (as2 == null || !(as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry)) {
            return null;
        }
        com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
        return l2.J;
    }

    public void l(UnitInstance am2) {
        if (am2.cG) {
            am2.cG = false;
            --this.int5;
            this.bZ.remove(am2);
            ++this.Y;
            K();  // 02b L3635: 本类静态 K()
        }
    }

    public boolean hasBuilderSelected() {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !this.m(y2)) continue;
            return true;
        }
        return false;
    }

    public boolean hasFactorySelected() {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !this.m(y2) || !y2.l()) continue;
            return true;
        }
        return false;
    }

    public boolean hasTransporterSelected() {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !y2.aS() || !this.m(y2)) continue;
            return true;
        }
        return false;
    }

    public boolean canAllBuild() {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return true;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || y2.i()) continue;
            return false;
        }
        return true;
    }

    public boolean m(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (am2.isAutoReclaimActive()) {
            return false;
        }
        if (am2.player == l2.bs) {
            return true;
        }
        if (am2.player != null && am2.player.b(l2.bs)) {  // 02b L3807: var1.bX.b(var2.bs)
            return true;
        }
        return l2.bv || l2.cb.j();
    }

    public boolean n(UnitInstance am2) {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am3 instanceof UnitType)) continue;  // 02b L3818: instanceof y (StatsPanel 为幻觉名)
            UnitType y2 = (UnitType) am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !am2.d(y2, false)) continue;
            return true;
        }
        return false;
    }

    public boolean o(UnitInstance am2) {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am3 instanceof UnitType)) continue;  // 02b L3818: instanceof y (StatsPanel 为幻觉名)
            UnitType y2 = (UnitType) am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !y2.d(am2, false)) continue;
            return true;
        }
        return false;
    }

    public boolean p(UnitInstance am2) {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am3 instanceof UnitType)) continue;  // 02b L3818: instanceof y (StatsPanel 为幻觉名)
            UnitType y2 = (UnitType) am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !y2.a(am2)) continue;
            return true;
        }
        return false;
    }

    public boolean q(UnitInstance am2) {
        if (this.q() == 0) {  // 02b L1544: q()==0
            return false;
        }
        for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.bZ) {
            if (!(am3 instanceof UnitType)) continue;  // 02b L3818: instanceof y (StatsPanel 为幻觉名)
            UnitType y2 = (UnitType) am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !com.corrodinggames.rts.game.units.PathfindingHelper.a(y2, am2)) continue;
            return true;
        }
        return false;
    }

    public void E() {
    }

    public boolean F() {
        return false;
    }



    public void a(String string, int n2) {
        this.g.a(string, n2);
    }

    public void g() {  // 02b f/g.java L533-537: 更新 ToastMessage/WaypointManager 队列 + bX 标志 (ContextMenuActivity bS.g() 链)
        this.h.b();
        this.i.b();
        this.bX = false;
    }

    public void G() {
        this.l();
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.dq = true;
        l2.bY.c();
        if (l2.by < 1500 && l2.bS.f != null) {
            l2.dr = true;
        }
        this.k.updateDialog(0.0f);  // 02b f/f.java L60: a(float)
        this.k.c();
    }

    public void resign() {
        this.l();
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.dt = true;
        l2.bY.c();
        this.k.updateDialog(0.0f);  // 02b f/f.java L60: a(float)
        this.k.c();
    }

    public void setPingAction() {
        this.l();
        this.hoveredUnit = null;
        this.ac = this.p;
    }

    public boolean isNotPlacing() {
        return !this.T;
    }

    public boolean b(int n2, int n3, int n4, int n5, String string, CameraMode i2, boolean bl2, int n6) {
        return this.a(n2, n3, n4, n5, string, i2, bl2, n6, this.buttonBgPaint, true, null);  // 02b L4567: a(int,int,int,int,String,i,bl,int,Paint,bl,h)
    }

    public boolean b(int n2, int n3, int n4, int n5, CameraMode i2) {
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        return this.V && this.bx.b((int)this.x, (int)this.y);
    }

    public float r(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (am2.cH < l2.bA && am2.cH + 200 > l2.bA) {
            float f2 = 1.0f - (float)(l2.bA - am2.cH) / 200.0f;
            return f2 * 6.0f;
        }
        return com.corrodinggames.rts.gameFramework.GlobalState.B().dx;
    }

    public static void invalidateUI() {
        ++cd;
        ce = true;
    }

    static {
        cd = 1;
    }


   // 02b f.g.i(am) L3638 简化: 单位可选性判定 (依赖 UnitInstance.t()/cg()/bX + PlayerState.c 盟友判定, 待 InGameUI 战役完整化)
   public boolean i(com.corrodinggames.rts.game.units.UnitInstance var1) {
      return true;
   }

   // 02b f.g.j(am) L3654 简化: 单位交互 (02b: cG/i/k/a(ag.c,am); ag 枚举 03 未映射)
   public boolean j(com.corrodinggames.rts.game.units.UnitInstance var1) {
      return true;
   }

   // 02b f.g.k(am) L3666 简化: 选中处理 (02b 依赖 cG/cH/bA/aX/Y/bZ/ca/K(), 待战役)
   public void k(com.corrodinggames.rts.game.units.UnitInstance var1) {
   }

   // 02b f.g.a(f,f,n,a.j) javap 签名: 发送地图 Ping 指令 (02b FF 丢方法, 简化空实现)
   public void a(float var1, float var2, com.corrodinggames.rts.game.PlayerState var3,
                  com.corrodinggames.rts.game.units.actions.MapPingAction var4) {
   }

    public static void K() {  // 02b f/g.java L4630-4633: 请求 UI 重绘
        ++cd;
        ce = true;
    }


    public void a(Context context) {  // 02b f/g.java L334 (InGameUI)
    }


    public void a(boolean bl) {  // 02b f/g.java L281 (InGameUI)
    }

    public void y() {  // 02b f/g.y() (InGameUI)
    }


    public void a(float f2) {  // 02b f/g.a(float) (InGameUI 帧更新)
    }


    public void e() {  // 02b f/g.java L274-279
        if (this.g != null) {
            this.g.a();
        }
    }


    // ===== v19.117 InGameUI 战役补方法 (02b f/g.java 对照; 深水区简化 TODO) =====

    public boolean a() {  // 02b f/g.java L207
        if (GlobalState.aw()) {
            return false;
        }
        return GlobalState.B().bQ.useCircleSelect;
    }

    public boolean a(GlobalState l2) {  // 02b L1105
        return l2.bQ.keyboardSupport ? l2.c(59, 60) : false;
    }

    public boolean a(PathState g2) {  // 02b L2972
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>)(java.util.Collection) this.bZ) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (this.m(y2) && this.a(g2, (UnitInstance) y2)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(PathState g2, UnitInstance am2) {  // 02b L2988 (bx 字段检查简化)
        if (am2 instanceof UnitType) {
            UnitType y2 = (UnitType) am2;
            if ((g2 == PathState.a || g2 == PathState.b) && GlobalState.V() - this.bU < 1000L) {
                return true;
            }
            if (g2 == PathState.c && GlobalState.V() - this.bV < 1000L) {
                return true;
            }
            if (y2.a(g2)) {
                if (g2 == PathState.a || g2 == PathState.b) {
                    this.bU = GlobalState.V();
                }
                if (g2 == PathState.c) {
                    this.bV = GlobalState.V();
                }
                return true;
            }
        }
        return false;
    }

    public void a(com.corrodinggames.rts.gameFramework.Command e2) {  // 02b L2558
        for (GameObject w2 : (java.util.Collection<GameObject>)(java.util.Collection) com.corrodinggames.rts.gameFramework.GameObject.er) {
            if (!(w2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) w2;
            if (y2.cG && this.m(y2)) {
                e2.a(y2);
            }
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.Command e2, com.corrodinggames.rts.game.units.actions.GameAction s2, boolean bl) {  // 02b L2636
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.BuildAction) {
            com.corrodinggames.rts.game.units.actions.BuildAction g2 = (com.corrodinggames.rts.game.units.actions.BuildAction)s2;
            e2.a(g2.buildUnitType);
            return;
        }
        com.corrodinggames.rts.game.units.actions.ActionId c2 = s2.N();
        for (GameObject w2 : (java.util.Collection<GameObject>)(java.util.Collection) com.corrodinggames.rts.gameFramework.GameObject.er) {
            if (!(w2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) w2;
            if (y2.cG && this.m(y2)) {
                com.corrodinggames.rts.game.units.actions.GameAction s3 = y2.a(c2);
                if (s3 != null && s3.b(y2)) {
                    e2.a(y2);
                }
            }
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.Command e2, com.corrodinggames.rts.game.units.actions.GameAction s2) {  // 02b L2722
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.BuildAction) {
            com.corrodinggames.rts.game.units.actions.BuildAction g2 = (com.corrodinggames.rts.game.units.actions.BuildAction)s2;
            e2.a(g2.buildUnitType);
            return;
        }
        com.corrodinggames.rts.game.units.actions.ActionId c2 = s2.N();
        for (GameObject w2 : (java.util.Collection<GameObject>)(java.util.Collection) com.corrodinggames.rts.gameFramework.GameObject.er) {
            if (!(w2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) w2;
            if (y2.cG && this.m(y2)) {
                com.corrodinggames.rts.game.units.actions.GameAction s3 = y2.a(c2);
                if (s3 != null && s3.b(y2)) {
                    e2.a(y2);
                }
            }
        }
    }

    public UnitInstance a(float f2, float f3, boolean bl) {  // 02b L3536 (cV/do/cg 03 缺, 简化)
        GlobalState l2 = GlobalState.B();
        UnitInstance am2 = null;
        float f4 = -1.0f;
        float f5 = 10.0f / l2.cX;
        float f6 = 5.0f / l2.cX;
        float f7 = 5.0f / l2.cX;
        PlayerState n2 = this.r();
        for (UnitInstance am3 : (java.util.Collection<UnitInstance>)(java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (bl) {
                if (am3.t()) continue;
            }
            if (!am3.isDead && am3.cN == null) {
                float f8 = GameUtils.a(f2, f3, am3.eo, am3.ep - am3.eq);
                float f9 = 0.0f;  // 02b: do() 简化
                if (!am3.cG) {
                    f9 += f5;
                } else {
                    f9 += f6;
                }
                boolean bl2 = n2.c(am3.player);
                if (bl2) {
                    f9 += f7;
                }
                if (f8 < f9 * f9 && (am2 == null || f8 < f4)) {  // 02b: cg() 检查简化
                    am2 = am3;
                    f4 = f8;
                }
            }
        }
        if (am2 != null && am2.player != l2.bs && !am2.cf()) {
            return null;
        }
        return am2;
    }

    public void a(UnitInstance am2) {  // 02b L2414 (依赖简化)
        if (am2 != null) {
            this.W = am2;
            this.X = 0.0f;
        }
    }

    public boolean a(UnitInstance am2, boolean bl, float f2, float f3, Point point) {  // 02b L2434 深水区简化 TODO
        return false;
    }

    public void a(GlobalState l2, PlayerState n2, boolean bl, boolean bl2) {  // 02b L1213 资源链深水区简化 TODO
    }

    public boolean a(com.corrodinggames.rts.gameFramework.ui.h h2, boolean bl) {  // 02b L1407 按钮绘制简化 TODO
        return false;
    }

    public void a(float f2, float f3, float f4, Point point) {  // 02b L1661 点击处理深水区简化 TODO
    }

    public void a(com.corrodinggames.rts.game.units.actions.GameAction s2, PointF pointF, UnitInstance am2, com.corrodinggames.rts.gameFramework.Command e2) {  // 02b L3125 (02b 亦空壳)
    }

    public boolean a(int n2, int n3, int n4, int n5, String string, CameraMode i2, boolean bl, int n6, Paint paint, boolean bl2, com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel h2) {  // 02b L4567 简化 TODO
        return false;
    }

    public void a(Rect var1) {  // 02b f/g.java L4579: 矩形点击选中
        if (var1.b((int)this.z, (int)this.A)) {
            this.L = true;
            this.M = true;
            if (this.V) {
                this.K = true;
            }
        }
    }


    // === v19.133d ActionPanel 战役补缺 (02b f/g.java 直译) ===

    // === v19.133f8 AIStrategyNode 战役补缺 (02b f/g.java L3911-4032 直译) ===

    // 02b f/g.java L3973-4032: 位置可放置检查 (blockout 内联辅助; var4 分支为绘制副作用, 深水区简化 TODO)
    public boolean a(UnitType unitType, float f2, float f3, boolean bl, boolean bl2, UnitInstance unitInstance) {
        GlobalState l2 = GlobalState.B();
        float f4 = unitType.eo;
        float f5 = unitType.ep;
        unitType.eo = f2;
        unitType.ep = f3;
        boolean bl3 = unitType.c(l2.bs);
        if (com.corrodinggames.rts.gameFramework.effects.GameHUD.a(l2.bs, unitType, this.ad)) {
            bl3 = false;
        }
        float f6;
        if (unitInstance != null && unitInstance instanceof UnitType) {
            UnitType unitType2 = (UnitType) unitInstance;
            if (!unitType2.isUsable()) {
                f6 = GameUtils.a(unitType2.eo, unitType2.ep, unitType.eo, unitType.ep);
                float f7 = unitType2.f(unitType.r());
                boolean bl4;
                if (f7 > 800000.0f) {
                    bl4 = true;
                } else {
                    bl4 = f6 <= f7 * f7;
                }
                if (!bl4) {
                    bl3 = false;
                }
            }
        }
        boolean bl5 = unitType.cp;
        unitType.cp = true;
        unitType.cs = bl3;
        unitType.ct = !bl3;
        unitType.cr = bl2;
        if (bl) {
            // 02b L4008-4025: var4 分支 — 单位移动/重绘副作用 (深水区简化 TODO; AIStrategyNode 调用时 bl=false 不触发)
        }
        unitType.eo = f4;
        unitType.ep = f5;
        unitType.cr = false;
        unitType.cp = bl5;
        return bl3;
    }

    // 02b f/g.java L3911-3971: blockout — 沿直线从 (f2,f3) 到 (f4,f5) 扫描可放置点, 结果加入 arrayList
    public void a(UnitType unitType, float f2, float f3, float f4, float f5, boolean bl, ArrayList arrayList, UnitInstance unitInstance) {
        GlobalState l2 = GlobalState.B();
        float f6 = unitType.eo;
        float f7 = unitType.ep;
        UnitType unitType2 = null;
        UnitInstance unitInstance2 = UnitInstance.isVisibleToTeam(unitType.r());
        if (!(unitInstance2 instanceof UnitType)) {
            GlobalState.e("buildingBlockoutUnit not OrderableUnit is: " + unitInstance2.getClass().getName());
        } else {
            unitType2 = (UnitType) unitInstance2;
        }
        boolean bl2 = false;
        l2.bL.b(f2, f3);
        f2 = (float) l2.bL.scrollPixelX;
        f3 = (float) l2.bL.scrollPixelY;
        f2 += unitType.cZ();
        f3 += unitType.da();
        f4 += unitType.cZ();
        f5 += unitType.da();
        float f8 = GameUtils.b(f2, f3, f4, f5);
        float f9 = GameUtils.d(f2, f3, f4, f5);
        int n2 = 0;
        for (float f10 = 0.0f; f10 <= f8; f10 += (float) l2.bL.selectedTileX) {  // 02b bL.p = 瓦片X坐标
            float f11 = f2 + GameUtils.k(f9) * f10;
            float f12 = f3 + GameUtils.j(f9) * f10;
            f11 -= unitType.cZ();
            f12 -= unitType.da();
            l2.bL.b(f11, f12);
            f11 = (float) l2.bL.scrollPixelX;
            f12 = (float) l2.bL.scrollPixelY;
            f11 += unitType.cZ();
            f12 += unitType.da();
            unitType.eo = f11;
            unitType.ep = f12;
            boolean bl3 = false;
            if ((!bl2 || unitType2 == null || !com.corrodinggames.rts.gameFramework.effects.GameHUD.a(unitType, unitType2) && !unitType.r(unitType2)) && !bl3) {
                boolean bl4 = this.a(unitType, f11, f12, bl, false, unitInstance);
                if (arrayList != null && bl4) {
                    arrayList.add(new PointF(f11, f12));
                }
                if (bl4) {
                    ++n2;
                    if (n2 >= 29) {
                        return;
                    }
                }
                bl2 = true;
                if (unitType2 != null) {
                    unitType2.eo = f11;
                    unitType2.ep = f12;
                }
            }
        }
        unitType.eo = f6;
        unitType.ep = f7;
    }

    public boolean a(float var1, float var2) {  // 02b f/g.java L269: 鼠标在屏幕内判断
        GlobalState var3 = GlobalState.B();
        return bO && !this.g.ap ? var3.bW.c(var1, var2) == null : var1 < var3.cl - var3.cq;
    }

    public void a(String var1, Rect var2, Paint var3, Paint var4) {  // 02b f/g.java L4062: 多行文本
        GlobalState var5 = GlobalState.B();
        String[] var6 = var1.split("\n");
        int var7 = 0;
        for (String var11 : var6) {
            Paint var12 = var7 == 0 ? var3 : var4;
            int var13 = TextFormatter.a(var12);
            var5.bO.a(var11, (float)var2.d(), (float)(var2.b + var13 / 2 + var7 * var13), var12);
            ++var7;
        }
    }

    public void a(Rect var1, Paint var2, Paint var3) {  // 02b f/g.java L4471: 按钮背景
        GlobalState var4 = GlobalState.B();
        if (bO) {
            var4.bO.a(this.bl, var1, var3, var1.a, var1.b, 0, 0);
            if (var2 != null) {
                int var5 = var2.f() + 0;
                if (var5 > 255) {
                    var5 = 255;
                }
                var2.c(var5);
            }
        }
        if (var2 != null) {
            var4.bO.b(var1, var2);
        }
    }

    public void a(Rect var1, int var2, boolean var3) {  // 02b f/g.java L4491: 边框
        GlobalState var4 = GlobalState.B();
        this.bF.b(var2);
        this.bF.a(Paint$Style.b);
        this.bF.a(1.0f);
        var4.bO.b(var1, this.bF);
        if (this.bN) {
            this.bF.b(Color.a(255, 116, 136, 160));
            byte var5 = 1;
            if (var3 && var1.b() > 100) {
                var5 = 2;
            }
            this.bF.a((float)var5);
            this.bz.a(var1);
            this.bz.d -= var5;
            this.bz.b += var5;
            this.bz.a += var5;
            this.bz.c -= var5;
            var4.bO.b(this.bz, this.bF);
        }
    }

    public void a(int var1, int var2, int var3, int var4, String var5, int var6, Paint var7) {  // 02b f/g.java L4540: 按钮文字
        GlobalState var8 = GlobalState.B();
        this.bx.a(var1, var2, var1 + var3, var2 + var4);
        if (GlobalState.aW) {
            var8.bO.a(var5, (float)this.bx.d(), (float)(this.bx.e() + var8.bO.a(var5, var7) / 2), var7);
        } else {
            var8.bO.a(var5, (float)this.bx.d(), (float)this.bx.e() - (var7.l() + var7.m()) / 2.0f, var7);
        }
    }

    public void a(int var1, int var2, int var3, int var4, String var5, int var6, Paint var7, boolean var8, com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel var9, com.corrodinggames.rts.gameFramework.ui.panels.e var10) {  // 02b f/g.java L4515: 按钮
        GlobalState var11 = GlobalState.B();
        this.bx.a(var1, var2, var1 + var3, var2 + var4);
        this.bF.b(var6);
        if (var9 != null) {
            var9.a(var11.bO, this.bx);  // 02b 3参 a(y,Rect,i) 简化 (v19.133d)
        } else if (!var8) {
            this.bF.a(Paint$Style.a);
            var11.bO.b(this.bx, this.bF);
        } else {
            this.a(this.bx, (Paint)null, this.bF);
        }
        if (var9 == null) {
            int var12 = Color.a(255, 0, 0, 0);
            if (bO) {
                var12 = Color.a(100, 0, 0, 0);
            }
            this.a(this.bx, var12, false);
        }
        this.a(var1, var2, var3, var4, var5, var6, var7);
    }

    public boolean a(int var1, int var2, int var3, int var4, String var5, CameraMode i2, boolean var7, int var8) {  // 02b f/g.java L4555
        return this.a(var1, var2, var3, var4, var5, i2, var7, var8, this.aC, false, (com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel)null);
    }

    public boolean a(int var1, int var2, int var3, int var4, String var5, CameraMode i2, boolean var7, int var8, Paint var9, com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel var10) {  // 02b f/g.java L4563
        return this.a(var1, var2, var3, var4, var5, i2, var7, var8, var9, false, var10);
    }

    public boolean a(com.corrodinggames.rts.game.units.actions.GameAction s2) {  // 02b f/g.java L2771 深水区简化 TODO (v19.133d)
        return false;
    }

    public boolean a(com.corrodinggames.rts.game.units.actions.GameAction s2, boolean bl2, com.corrodinggames.rts.game.units.UnitInstance am2, boolean bl3, boolean bl4, float f2, boolean bl5) {  // 02b f/g.java L4089 深水区简化 TODO (v19.133d)
        return false;
    }

}
