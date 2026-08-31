/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.pm.Signature
 *  android.os.Debug
 *  android.util.DisplayMetrics
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.gameFramework.CommandController;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.game.units.actions.StopAction;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.GameEngine$a;
import com.corrodinggames.rts.game.ScreenshotSaver;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.ProjectileWeapon;
import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import com.corrodinggames.rts.gameFramework.ReplayEngine;
import com.corrodinggames.rts.gameFramework.SaveFileHandler;
import com.corrodinggames.rts.gameFramework.UnitGroup;
import com.corrodinggames.rts.gameFramework.StatsManager;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GamePhase;
import com.corrodinggames.rts.gameFramework.commands.DebugServer;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.audio.DataFieldInt;
import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;
import com.corrodinggames.rts.gameFramework.FileWatcher;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.TeamColorTexture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManager;
import com.corrodinggames.rts.gameFramework.rendering.NullSpriteBatchBackend;
import com.corrodinggames.rts.gameFramework.utility.DequeList;
import com.corrodinggames.rts.gameFramework.utility.ResultState;
import com.corrodinggames.rts.gameFramework.utility.Result;
import com.corrodinggames.rts.gameFramework.utility.TypedObjectList;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.CollisionEngine;
import com.corrodinggames.rts.gameFramework.effects.DrawEffect;

public strictfp class GameEngine
extends GlobalState {

    public static boolean b;  // 02b game/i.java: static boolean b (32 位检测)
    public static String a;  // 02b game/i.java L59: static String a (javap 铁证)
    public static boolean c;  // 02b game/i.java L61: static boolean c (isMac, javap 铁证)

    @Override
    public int z() {  // 02b i.z() L2410-2412: return this.d
        return this.netEngine;
    }
    public Paint do_;
    public static String renderer;
    public static String f;  // 02b L64
    public static boolean gameWorld;
    public static boolean mapEngine;
    int netEngine;
    public NetEngine bX;
    public String h;  // 02b i.java L66
    TextureManagerInterface bO;
    SettingsEngine bQ;
    ExtraManager cd;
    public float globalState = 1.0f;
    public static String inputHandler;
    ScreenshotSaver[] cameraController = new ScreenshotSaver[6];  // 02b k[] (L65)
    String dI;
    public boolean i = false;
    public MapEngine bL;
    public int isExiting = 0;
    public ConcurrentLinkedQueue k = new ConcurrentLinkedQueue();
    public ChatLog aC;  // 02b ad.aC: j/a 聊天日志 (ChatLog)
    Paint l;
    Paint m;
    Paint n;
    Paint o;
    Paint p;
    int q = 0;
    int r = 0;
    int s = 0;
    float t = 16.0f;
    public String string4 = "0fps";
    Rect v = new Rect();
    public ArrayList w = new ArrayList();
    Paint x;
    Paint y;
    Paint z;
    public Paint uiPaint = new Paint();
    public UnitGroup textPaint;
    public SaveFileHandler selectionBoxPaint;
    public com.corrodinggames.rts.gameFramework.effects.CloudRenderer minimapPaint = new com.corrodinggames.rts.gameFramework.effects.CloudRenderer();  // 02b d.b (L88)
    GameFlag E;  // 02b game.a (L89)
    boolean F;
    float stepAccumulator = 0.0f;
    public float targetFps = 1.0f;
    public float deltaTime;
    public float deltaTimeBackup;
    ResourceLoader shaderBase;
    ResourceLoader shaderDisplacement;
    boolean M;
    com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface savedGraphicsLayer;  // T0 javap: m.y N (L98)
    Texture waterTexture1;
    Texture waterTexture2;
    Texture waterTexture3;
    float R = 0.0f;
    Rect isGameOver = new Rect();
    RectF gameStartTime = new RectF();
    public Texture screenshotTexture = null;
    public Texture screenTransitionState = null;
    com.corrodinggames.rts.gameFramework.utility.TypedObjectList visibleObjects = new com.corrodinggames.rts.gameFramework.utility.TypedObjectList("allOnScreenObjects");  // 02b utility.s (L107)
    com.corrodinggames.rts.gameFramework.utility.TypedObjectList visibleObjectsDirty = new com.corrodinggames.rts.gameFramework.utility.TypedObjectList("allOnScreenObjectsDirty");  // 02b utility.s (L108)
    Matrix Y = new Matrix();
    public ArrayList arrayList2 = new ArrayList();
    public ArrayList arrayList3 = new ArrayList();
    Timer ab;
    boolean ac;
    Object ad = new Object();
    int ae = 0;
    com.corrodinggames.rts.game.units.UnitInstance flyoverPrevUnit;
    com.corrodinggames.rts.game.units.UnitInstance flyoverNextUnit;
    float flyoverLerp;
    boolean flyoverTransitioning;

    public GameEngine(Context context) {
        super(context);
    }


    public boolean a() {
        if (this.bS.u) {
            return true;
        }
        return this.dH != null && this.dH.b();
    }

    @Override
    public boolean a(boolean bl) {
        if (!bl || this.cb.j()) {
            if (this.bS.u) {
                return true;
            }
            if (this.bp) {
                return true;
            }
            if (this.aq && !this.bH) {
                return true;
            }
            if (this.bF && this.dH != null && this.dH.b()) {
                return true;
            }
        }
        if (bl && !this.bX.aW) {
            return true;
        }
        return this.bX.shouldGameBePaused();
    }


    public int b() {
        return this.s;
    }


    public boolean c() {
        return this.eh;
    }


    public boolean d() {
        return this.ei;
    }


    public synchronized void a(Context context) {
        Log.d("RustedWarfare", "--- ----------------- ----");
        Log.d("RustedWarfare", "--- GameEngine:init() ----");
        Log.d("RustedWarfare", "--- ----------------- ----");
        if (this.bi) {
            Log.d("RustedWarfare", "GameEngine init has already been called");
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Version:" + this.r());  // 02b L171: this.r()
        if (GameEngine.C() && this.getClass().equals(i.class)) {
            throw new RuntimeException("inSpace but class is:" + this.getClass());
        }
        System.gc();
        this.h("Asset Index");
        this.bK = new com.corrodinggames.rts.gameFramework.utility.i(context);  // 02b L177: new utility/i (资产索引器) (v19.133f2 修正)
        long l2 = com.corrodinggames.rts.gameFramework.br.a();
        this.cd = new ExtraManager(this);
        this.cd.a(GamePhase.C);
        if (aU) {
            this.ci = 1.0f;
        } else {
            DisplayMetrics displayMetrics = context.e().getDisplayMetrics();
            this.ci = context.e().getDisplayMetrics().density;
            com.corrodinggames.rts.gameFramework.GlobalState.e("densityScaleRaw: " + this.ci);
            this.a(displayMetrics.widthPixels, displayMetrics.heightPixels);  // 02b L187: a(int,int)
        }
        this.ci *= this.globalState;
        com.corrodinggames.rts.gameFramework.GlobalState.e("densityScaleRaw*densityScaleMultiplier: " + this.ci);
        if (com.corrodinggames.rts.gameFramework.GlobalState.b(context)) {
            this.ar = true;
        }
        this.E = new GameFlagImpl();
        this.bo = false;
        this.h("InputController");
        this.bT = new KeyBindingManager();
        this.bT.digitToKeycode();  // 02b ac.a() L302 (键位初始化)
        this.h("SettingsEngine");
        this.bQ = SettingsEngine.getInstance(context);
        this.bQ.loadMainExternalFolder(true);
        com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b();
        int n2 = 3;
        if (aZ) {
            n2 = 1;
        }
        if (this.bQ.numIncompleteLoadAttempts > 1 || this.bQ.numLoadsSinceRunningGameOrNormalExit > n2) {
            this.ee = true;
            if (this.bQ.numIncompleteLoadAttempts > 2 || this.bQ.numLoadsSinceRunningGameOrNormalExit > 4) {
                this.bQ.forceEnglish = true;
                this.ef = true;
            }
            if (this.bQ.numIncompleteLoadAttempts > 3) {
                this.bQ.newRender = false;
            }
            if (this.bQ.numIncompleteLoadAttempts > 4 || this.bQ.numLoadsSinceRunningGameOrNormalExit > 5) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Extra safe mode");
                this.eh = true;
            }
            if (this.bQ.numIncompleteLoadAttempts > 5) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Extra safe mode x2");
                this.ei = true;
            }
            if (this.bQ.numIncompleteLoadAttempts > 6) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Extra safe mode x3");
                this.bQ.newRender = false;
                this.bQ.shaderEffects = false;
                this.bQ.teamShaders = false;
            }
            if (this.bQ.newRender && this.bQ.numLoadsSinceRunningGameOrNormalExit > 15) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Disabling opengl mode");
                this.bQ.newRender = false;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("starting game in safe mode, numIncompleteLoadAttempts:" + this.bQ.numIncompleteLoadAttempts + " numLoadsSinceRunningGameOrNormalExit:" + this.bQ.numLoadsSinceRunningGameOrNormalExit);
        }
        if (aO) {
            this.ee = true;
            this.eg = "<forced by command line>";
        }
        if (aP) {
            this.ee = true;
            this.eh = true;
            this.ei = true;
            this.eg = "<forced by command line>";
        }
        ++this.bQ.numLoadsSinceRunningGameOrNormalExit;
        ++this.bQ.numIncompleteLoadAttempts;
        boolean bl = this.bQ.save();
        if (!bl && aZ) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("starting game in safe mode, failed to save settings");
            this.eg = "failing to write preferences data";
            this.ee = true;
        }
        com.corrodinggames.rts.gameFramework.commands.DebugServer.a();
        this.cj = this.W();  // 02b L268: this.W()
        com.corrodinggames.rts.gameFramework.GlobalState.e("densityScale(): " + this.cj);
        long l3 = com.corrodinggames.rts.gameFramework.br.a();
        com.corrodinggames.rts.gameFramework.steam.Localization.a();
        com.corrodinggames.rts.gameFramework.br.a("Locale.init took:", l3);
        PlayerState.recalculateCapacities();
        this.l = new Paint();
        this.m = new Paint();
        this.m.a(255, 255, 255, 255);
        this.m.a(true);
        this.a(this.m, 16.0f);
        this.n = new Paint();
        this.n.a(255, 255, 255, 255);
        this.n.a(true);
        this.a(this.n, 16.0f);
        this.o = new Paint();
        this.o.a(100, 255, 0, 0);
        this.a(this.o, 16.0f);
        this.p = new Paint();
        this.p.a(100, 0, 255, 0);
        this.a(this.p, 16.0f);
        this.dn = new Paint();
        this.do_ = new Paint();
        this.do_.a(Paint$Align.b);
        this.do_.a(true);
        this.do_.a(Typeface.a(Typeface.c, 0));
        this.a(this.do_, 16.0f);
        this.dp = new Paint();
        this.dp.a(255, 230, 255, 230);
        this.dp.a(true);
        this.dp.a(Paint$Align.b);
        this.a(this.dp, 18.0f);
        this.x = new Paint();
        this.x.b(-1);
        this.x.c(100);
        this.y = new Paint();
        this.y.b(-7829368);
        this.y.c(240);
        this.y.a(Paint$Style.b);
        this.y.a(1.0f);
        long l4 = com.corrodinggames.rts.gameFramework.br.a();
        this.h("AudioEngine");
        SoundRegistry.b();
        this.bM = new SoundRegistry();
        this.bM.a(context);
        com.corrodinggames.rts.gameFramework.br.a("AudioEngine took:", l4);
        this.h("MusicController");
        this.bN = new com.corrodinggames.rts.gameFramework.MusicController();  // 02b L315: new am()
        this.bN.a(context);
        if (bh != null) {
            e("init(): using Graphics instance");
            this.bO = bh;
        } else if (bg != null) {
            e("init(): using GraphicsSlick2d");
            try {
                this.bO = (com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface)bg.newInstance();  // 02b L324: (m.y)
            }
            catch (InstantiationException instantiationException) {
                throw new RuntimeException(instantiationException);
            }
            catch (IllegalAccessException illegalAccessException) {
                throw new RuntimeException(illegalAccessException);
            }
        } else {
            this.bO = aU ? new NullSpriteBatchBackend() : new TextureManager();
        }
        this.h("graphics.init");
        this.bO.a(context);
        this.bO.b();
        com.corrodinggames.rts.gameFramework.FileWatcher.a();  // 02b L339: j.a() (javap 静态无参)
        this.h("Fonts");
        this.Y();
        this.h("effects.init");
        this.bR = new com.corrodinggames.rts.gameFramework.effects.HUDManager();  // 02b L343: new d.c()
        this.bR.a(context);
        this.h("minimapHandler");
        this.bW = new com.corrodinggames.rts.gameFramework.ui.Minimap();  // 02b L346: new f.o()
        this.bW.a(context);
        if (ck != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("We have an initial screen size, can do early setup of image buffers");
            this.h("Map Buffers");
            this.b(GameEngine.ck.a, GameEngine.ck.b);  // 02b L351: b(ck.a, ck.b)
            this.onBackPressed();
            com.corrodinggames.rts.game.map.MapEngine.d();  // 02b L353: game.b.b.d()
            com.corrodinggames.rts.game.map.MapEngine.f();  // 02b L354
            this.bW.e();
            boolean bl2 = com.corrodinggames.rts.gameFramework.GlobalState.isShaderEffectsSupported();
            if (bl2) {
                this.h("Setting up postprocessing");
                boolean bl3 = this.i();
                if (!bl3) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Failed to setup postprocessing");
                }
            }
        }
        this.h("PathEngine");
        this.bU = new com.corrodinggames.rts.gameFramework.pathfinding.PathFinder();  // 02b L367: new k.l()
        this.h("GroupController");
        this.bV = new ProjectileWeapon();
        this.h("CollisionEngine");
        this.bP = new CollisionEngine();
        this.h("InterfaceEngine");
        this.bS = new com.corrodinggames.rts.gameFramework.ui.InGameUI();  // 02b L373: new f.g()
        this.bS.a(context);
        this.selectionBoxPaint = com.corrodinggames.rts.gameFramework.SaveFileHandler.c(context);  // 02b L375: be.c(var1)
        this.h("NetworkEngine");
        this.bX = new NetEngine();
        this.bX.emptyPlaceholderF();
        this.h("StatsHandler");
        this.bY = new StatsManager();
        this.h("ModEngine");
        this.bZ = new com.corrodinggames.rts.gameFramework.mods.VersionChecker();  // 02b L382: new i.a()
        this.bZ.a();
        if (this.ee) {
            this.bZ.g();
        }
        this.h("CommandController");
        this.cf = new CommandController();
        this.h("GameSaver");
        this.ca = new com.corrodinggames.rts.gameFramework.GameSaver();  // 02b L391: new y()
        this.h("ReplayEngine");
        this.cb = new ReplayEngine();
        this.cb.a(context);
        this.h("UnitGeoIndex");
        this.cc = new com.corrodinggames.rts.game.units.pathfinding.SpatialGrid();  // 02b L396: new units.f.c()
        this.h("Precalculating map fog");
        com.corrodinggames.rts.game.map.MapEngine.c();  // 02b L398
        this.h("ScorchMark.load");
        Projectile.b();
        this.h("Projectile.load");
        com.corrodinggames.rts.game.MovementController.c();  // 02b L402: f.c()
        this.h("Emitter.load");
        DrawEffect.b();
        this.h("Unit.loadAllUnits");
        long l5 = com.corrodinggames.rts.gameFramework.br.a();
        com.corrodinggames.rts.game.units.UnitInstance.bH();
        com.corrodinggames.rts.gameFramework.br.a("loadAllUnits took:", l5);
        this.h("Loading custom unit data");
        long l6 = com.corrodinggames.rts.gameFramework.br.a();
        com.corrodinggames.rts.game.units.custom.ag.h();
        this.h("getAllUnitsChecksum");
        com.corrodinggames.rts.gameFramework.br.a("CustomUnits took:", l6);
        long l7 = com.corrodinggames.rts.gameFramework.br.a();
        this.netEngine = com.corrodinggames.rts.game.units.UnitInstance.bM();
        com.corrodinggames.rts.gameFramework.br.a("allUnitsChecksum took:", l7);
        this.z = new Paint();
        this.z.a(50, 255, 255, 255);
        this.F();
        System.gc();
        this.bi = true;
        com.corrodinggames.rts.gameFramework.GlobalState.e("Init completed");
        com.corrodinggames.rts.gameFramework.br.a("Loading took:", l2);
        this.cd.b(GamePhase.C);
        this.cd.a(true, true);
        long l8 = com.corrodinggames.rts.gameFramework.br.a();
        this.h("Loading map data");
        if (!com.corrodinggames.rts.gameFramework.GlobalState.ay) {
            this.x();
        }
        com.corrodinggames.rts.gameFramework.br.a("loadAMenuMap took:", l8);
        this.h("Last setup");
        ap();
        this.bX.m();
        this.h("init complete");
        if (aE) {
            com.corrodinggames.rts.game.units.UnitRegistry.s();
            System.exit(0);
        }
        if (aF) {
            com.corrodinggames.rts.game.units.UnitRegistry.r();
            System.exit(0);
        }
        this.bj = true;
    }

    public void a(int n2, int n3) {  // 02b L452
        float f2 = 1.0f;
        float f3 = GameUtils.b(0.0f, 0.0f, (float)n2, (float)n3);
        float f4 = 1131.0f;
        f2 = f3 / f4;
        com.corrodinggames.rts.gameFramework.GlobalState.e("defaultViewpointZoomDensity: " + f2);
        if (f2 < 0.5f) {
            f2 = 0.5f;
        }
        if (f2 > 3.0f) {
            f2 = 3.0f;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("defaultViewpointZoomDensity after limit: " + f2);
        this.cY = 1.0f;
        if ((double)GameUtils.c(f2 - 1.0f) > 0.1) {
            this.cY = f2;
            if (this.cY > 2.0f) {
                this.cY = 2.0f;
            }
            if (this.cY < 0.5f) {
                this.cY = 0.5f;
            }
            this.cX = this.cV * this.cY;
        }
    }


    public void e() {  // 02b e() L483-486: K() + f()
        this.K();
        this.endGame();
    }

    public void endGame() {
        this.b(false);  // 02b f() L488: b(false)
        this.bG = false;
        this.bH = false;
        this.bF = false;
        this.bp = false;
        this.bS.u = false;
    }


    public synchronized void a(boolean bl, com.corrodinggames.rts.gameFramework.GameStateEnum s2) {  // 02b a(boolean,s) (ProjectileType2 为幻觉名)
        this.K();  // 02b L498: this.K()
        this.a(bl, false, s2);
    }


    public void a(boolean bl2, boolean bl3, com.corrodinggames.rts.gameFramework.GameStateEnum s2) {
        int n2;
        int n3;
        block81: {
            this.bC = this.bQ.teamUnitCapSinglePlayer;
            if (this.bC < 1) {
                this.bC = 1;
            }
            this.bB = this.bC;
            this.b(bl3);  // 02b a(boolean,boolean,s) L?
            PlayerState.updateAllUnitCapacities();
            this.bo = false;
            System.gc();
            this.bI = true;
            this.bG = false;
            this.bp = false;
            this.bF = false;
            this.by = 0;
            this.ch = false;
            this.bX.a(1L);
            this.bx = 0;
            this.bJ = 0;
            com.corrodinggames.rts.gameFramework.GameUtils.a();
            this.bX.emptyPlaceholderT();
            if (!bl3) {
                this.dq = false;
                this.dr = false;
                this.ds = 0.0f;
                this.du = false;
                this.dt = false;
            }
            this.isExiting = 0;
            if (!bl3) {
                this.cV = 1.0f;
            }
            this.dx = 0.0f;
            if (!this.cb.j()) {
                if (!this.bX.B) {
                    com.corrodinggames.rts.game.units.custom.ag.b(true);
                } else {
                    com.corrodinggames.rts.game.units.custom.ag.d();
                }
            }
            if (!this.bX.B) {
                if (!this.cb.j() && bl2) {
                    this.bs = new HumanPlayer(0);
                    this.bs.v = "Player";
                    for (int i2 = 1; i2 < 8; ++i2) {
                        new com.corrodinggames.rts.game.ai.AIStrategy(i2);  // 02b L553: new game.a.a(var4)
                    }
                    this.bX.updateAllAINames();
                }
            } else {
                this.bs = this.bX.z;
                if (this.bs == null) {
                    throw new RuntimeException("cannot find player's team");
                }
                if (this.bs != PlayerState.u(this.bs.k)) {
                    com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("Stale playerTeam");
                }
            }
            this.ce = null;
            this.bL = new MapEngine();
            try {
                if (this.dm != null) {
                    InputStream inputStream = this.dm.w();
                    try {
                        inputStream.reset();
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                    this.bL.a(inputStream, bl3);
                    break block81;
                }
                this.bL.a(this.ak(), bl3);
            }
            catch (com.corrodinggames.rts.game.map.MapException f2) {  // 02b game.b.f (L586)
                Object object;
                f2.printStackTrace();
                this.a("Error loading map: " + f2.getMessage(), 1);
                if (aT) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Crashing on allowed map error because automated testing is active");
                    throw new RuntimeException(f2);
                }
                if (!this.bX.B && this.ao != null && (object = this.ao.i()) != null) {
                    ((InGameActivity)object).m();
                }
                object = a(f2);
                e("Map Load Warning", (String)object);
                this.bI = false;
                return;
            }
        }
        if (!this.bL.cameraLocked) {
            e("map did not load, returning");
            this.bI = false;
            return;
        }
        this.bL.visibilityGrid = false;
        PlayerState.e();
        for (n3 = 0; n3 < PlayerState.c; ++n3) {
            PlayerState n4 = PlayerState.u(n3);
            if (n4 == null) continue;
            n4.processResourceTick();
        }
        if (!bl3) {
            ModUnitRegistry.do_F();
        }
        if (!this.bX.B && !this.cb.j()) {
            this.bX.ay.h = 1.0f;
            this.bX.ay.q = GameUtils.a(1, 1000000000);
        }
        this.bJ = this.bX.ay.q;
        e("global Seed: " + this.bJ);
        if (this.bX.B || this.cb.j()) {
            int n5;
            int n6;
            if (!this.bX.F) {
                this.bB = this.bX.aw;
                this.bC = this.bX.ax;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("Unit cap is now: " + this.bC);
            if (this.bX.ay.d == 0) {
                this.bL.tileHeight = false;
                this.bL.fogGrid = false;
            } else if (this.bX.ay.d == 1) {
                this.bL.tileHeight = true;
                this.bL.fogGrid = false;
            } else if (this.bX.ay.d == 2) {
                this.bL.tileHeight = true;
                this.bL.fogGrid = true;
            }
            this.bL.visibilityGrid = this.bX.ay.e;
            n3 = 10;
            if (this.bX.ay.e) {
                n3 = 10;
            }
            for (n6 = 0; n6 < PlayerState.c; ++n6) {
                PlayerState n7 = PlayerState.u(n6);
                if (n7 == null) continue;
                if (n7.N == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Fog null for team: " + n7.k);
                    continue;
                }
                for (int i3 = 0; i3 < this.bL.mapHeight; ++i3) {
                    for (n5 = 0; n5 < this.bL.tileWidth; ++n5) {
                        n7.N[i3][n5] = (byte)n3;
                    }
                }
            }
            n6 = this.bX.k();
            for (n2 = 0; n2 < PlayerState.c; ++n2) {
                Object object;
                Object object2;
                PlayerState n8 = PlayerState.u(n2);
                if (n8 == null) continue;
                n8.o = n6;
                if (n8.w) {
                    if (!n8.y) {
                        n8.x = n8.z != null ? n8.z : this.bX.ay.f;
                    } else {
                        n8.c("aiDifficulty is locked");
                    }
                }
                n8.I = this.bX.ay.l;
                n5 = 0;
                boolean bl4 = false;
                int n9 = this.bX.ay.g;
                if (n8.A != null) {
                    n9 = n8.A;
                }
                if (n9 == 1) continue;
                boolean bl5 = true;
                boolean bl6 = true;
                Float f3 = null;
                Float f4 = null;
                Float f5 = null;
                Float f6 = null;
                if (n9 == 5 || n9 == 4 || n9 > 10) {
                    bl6 = false;
                }
                if (n9 == 5 || n9 == 4 || n9 == 3 || n9 > 10) {
                    bl5 = false;
                }
                if (n9 == 9) {
                    bl6 = false;
                    bl5 = false;
                }
                for (java.util.Iterator iteratorBf = com.corrodinggames.rts.game.units.UnitInstance.bF().iterator(); iteratorBf.hasNext(); ) {
            com.corrodinggames.rts.game.units.UnitInstance am2 = (com.corrodinggames.rts.game.units.UnitInstance)iteratorBf.next();
                    if (!(am2 instanceof com.corrodinggames.rts.game.units.UnitInstance)) continue;
                    object2 = am2;
                    if (((com.corrodinggames.rts.game.units.UnitInstance)object2).bV || ((com.corrodinggames.rts.game.units.UnitInstance)object2).player != n8) continue;
                    if (((com.corrodinggames.rts.game.units.UnitInstance)object2).bO && n5 == 0) {
                        n5 = 1;
                        f3 = Float.valueOf(((com.corrodinggames.rts.game.units.UnitInstance)object2).eo);
                        f4 = Float.valueOf(((com.corrodinggames.rts.game.units.UnitInstance)object2).ep);
                        if (!bl5) {
                            ((com.corrodinggames.rts.game.units.UnitInstance)object2).ci();
                            continue;
                        }
                    }
                    if (!((com.corrodinggames.rts.game.units.UnitInstance)object2).bP || bl4) continue;
                    bl4 = true;
                    f5 = Float.valueOf(((com.corrodinggames.rts.game.units.UnitInstance)object2).eo);
                    f6 = Float.valueOf(((com.corrodinggames.rts.game.units.UnitInstance)object2).ep);
                    if (bl6) continue;
                    ((com.corrodinggames.rts.game.units.UnitInstance)object2).ci();
                }
                if (f3 == null) {
                    f3 = f5;
                    f4 = f6;
                }
                if (f3 == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("placementLocation==null for team:" + n8.k);
                    continue;
                }
                float f7 = f3.floatValue();
                float f8 = f4.floatValue();
                if (n9 == 2) {
                    int n10;
                    for (n10 = 0; n10 <= 2; ++n10) {
                        if (n10 == 1) continue;
                        object = com.corrodinggames.rts.game.units.UnitRegistry.h.a();  // 02b L759: UnitRegistry.h()
                        ((com.corrodinggames.rts.game.units.UnitInstance)object).b(n8);
                        ((com.corrodinggames.rts.game.units.UnitInstance)object).eo = f7 - 50.0f + (float)(n10 * 50);
                        ((com.corrodinggames.rts.game.units.UnitInstance)object).ep = f8;
                        PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)object);
                    }
                    for (n10 = 0; n10 <= 2; ++n10) {
                        object = com.corrodinggames.rts.game.units.UnitRegistry.w.a();  // 02b L768: UnitRegistry.w()
                        ((com.corrodinggames.rts.game.units.UnitInstance)object).b(n8);
                        ((com.corrodinggames.rts.game.units.UnitInstance)object).eo = f7 - 50.0f + (float)(n10 * 50);
                        ((com.corrodinggames.rts.game.units.UnitInstance)object).ep = f8 + 50.0f;
                        PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)object);
                    }
                    continue;
                }
                if (n9 == 3 || n9 == 4) {
                    for (int i4 = 0; i4 <= 2; ++i4) {
                        object = com.corrodinggames.rts.game.units.UnitRegistry.a("combatEngineer");
                        if (object == null) {
                            com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("Could not find: combatEngineer on network.setup.startingUnits==3");
                            continue;
                        }
                        com.corrodinggames.rts.game.units.UnitInstance am3 = ((com.corrodinggames.rts.game.units.UnitTypeHandle)object).a();
                        am3.b(n8);
                        am3.eo = f7 - 50.0f + (float)(i4 * 50);
                        am3.ep = f8 + 50.0f;
                        PlayerState.c(am3);
                    }
                    continue;
                }
                if (n9 == 5) {
                    object2 = com.corrodinggames.rts.game.units.UnitRegistry.a("experimentalSpider");
                    if (object2 == null) {
                        com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("Could not find: experimentalSpider on network.setup.startingUnits==5");
                        continue;
                    }
                    object = ((com.corrodinggames.rts.game.units.UnitTypeHandle)object2).a();
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).b(n8);
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).eo = f7;
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).ep = f8;
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).cg = 90.0f;
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).eq = 2.0f;
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).dc();
                    PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)object);
                    continue;
                }
                if (n9 == 9 || n9 <= 10) continue;
                object2 = ModUnitRegistry.getCreditCost(n9);
                if (object2 == null) {
                    com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("Could not find starting unit on startingUnits==" + n9);
                    continue;
                }
                object = ((com.corrodinggames.rts.game.units.UnitTypeHandle)((ModUnitRegistry)object2)).a();
                ((com.corrodinggames.rts.game.units.UnitInstance)object).b(n8);
                ((com.corrodinggames.rts.game.units.UnitInstance)object).eo = f7;
                ((com.corrodinggames.rts.game.units.UnitInstance)object).ep = f8;
                if (!((com.corrodinggames.rts.game.units.UnitInstance)object).bI()) {
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).cg = 90.0f;
                }
                if (((ModUnitRegistry)object2).eI) {
                    ((com.corrodinggames.rts.game.units.UnitInstance)object).dc();
                    if (object instanceof CustomUnitType) {
                        ((CustomUnitType)object).dB();
                    }
                }
                PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)object);
            }
        }
        if (!(bl3 || this.ce != null && this.ce.q)) {
            this.a(0.0f, 0.0f);
            n3 = 0;
            int n11 = 0;
            n2 = 0;
            for (com.corrodinggames.rts.game.units.UnitInstance am4 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                if (am4 instanceof TreeDecoration) {
                    ++n11;
                } else {
                    ++n3;
                }
                if (am4.player != this.bs || !am4.bP) continue;  // 02b am.bX
                this.b(am4.eo, am4.ep);  // 02b L860: this.b(eo,ep)
                n2 = 1;
            }
            if (n2 == 0) {
                for (com.corrodinggames.rts.game.units.UnitInstance am5 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                    if (am5.player != this.bs || am5.t() || am5.u()) continue;  // 02b am.bX
                    this.b(am5.eo, am5.ep);  // 02b L860
                }
            }
            e("there are " + n3 + " units on this map and " + n11 + " trees");
        }
        this.textPaint = com.corrodinggames.rts.gameFramework.SaveFileHandler.c(this.am).b(this.ak());  // 02b L868: B=bf
        this.bU.a(this.bL, bl3);
        this.bW.a(this.bL, bl3);
        this.cf.a();
        this.bV.a();
        if (!bl3) {
            com.corrodinggames.rts.gameFramework.effects.GameHUD.a();
        }
        this.ca.a(bl3);
        this.bS.a(bl3);
        if (!bl3) {
            this.bS.y();
            this.updateVisibleObjects();
            if (this.bv) {
                this.bS.y();
            }
        } else {
            this.bS.y();
        }
        this.cc.a(this.bL);
        if (!bl3) {
            this.bN.c();
        }
        this.bY.a();
        for (com.corrodinggames.rts.game.units.UnitInstance am6 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (!(am6 instanceof UnitType)) continue;
            UnitType y2 = (UnitType)am6;  // 02b L901: y.c(false)
            y2.c(false);
        }
        this.textPaint.isAttacking = true;
        this.selectionBoxPaint.a(this.am);
        this.bG = true;
        this.bH = false;
        this.bI = false;
        if (s2 != com.corrodinggames.rts.gameFramework.GameStateEnum.a && !this.bQ.hasPlayedGameOrSeenHelp) {  // 02b s.a
            this.bQ.hasPlayedGameOrSeenHelp = true;
            this.bQ.save();
        }
        for (int i5 = 0; i5 < 5; ++i5) {
            System.gc();
        }
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            Log.a("RustedWarfare", "getNativeHeapSize" + String.valueOf(Debug.getNativeHeapSize()));
            Log.a("RustedWarfare", "getNativeHeapAllocatedSize" + String.valueOf(Debug.getNativeHeapAllocatedSize()));
            Log.a("RustedWarfare", "getNativeHeapFreeSize" + String.valueOf(Debug.getNativeHeapFreeSize()));
            Log.a("RustedWarfare", "Runtime.getRuntime().maxMemory()" + String.valueOf(Runtime.getRuntime().maxMemory()));
        }
        if (this.dk != null) {
            this.dk.a();
        }
        this.stepAccumulator = 0.0f;
        if (this.bX.F && this.bX.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Disabling network for singleplayer");
            this.bX.B = false;
        }
        if (!ax()) {
            if (s2 == com.corrodinggames.rts.gameFramework.GameStateEnum.c) {  // 02b s.c
                com.corrodinggames.rts.gameFramework.GlobalState.e("Not starting replay recording as we are loading a save");
            } else {
                this.cb.a(bl3);
            }
        }
        if (com.corrodinggames.rts.gameFramework.pathfinding.PathFinder.m) {  // 02b k/l.m
            // empty if block
        }
    }

    private void updateVisibleObjects() {
        this.bS.y();
        for (com.corrodinggames.rts.game.units.UnitInstance am2 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am2.player != this.bs || !(am2 instanceof UnitType) || !am2.ak() || !am2.s_() || !am2.bT() || am2.u() || am2.t()) continue;  // 02b am.bX
            com.corrodinggames.rts.gameFramework.GlobalState.e("selectAnyOnScreenBuilder: found builder");
            this.bS.j(am2);
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("selectAnyOnScreenBuilder: no builder found");
    }


    public void g() {
        com.corrodinggames.rts.gameFramework.utility.DequeList o2 = com.corrodinggames.rts.gameFramework.GameObject.dK();  // 02b w.dK()
        for (Object object : o2) {
            ((com.corrodinggames.rts.gameFramework.GameObject)object).a();  // 02b L975: (w)var3.a()
        }
        com.corrodinggames.rts.game.units.UnitInstance.bF();
        com.corrodinggames.rts.gameFramework.w.dK();
        int n2 = o2.size();
        if (n2 != 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("SHOULD_NOT_HAPPEN: we still had " + n2 + " objects in gameObjectListForLogic after removeAll");
            for (Object w2 : o2) {
                String string = "Object: " + ((com.corrodinggames.rts.gameFramework.GameObject)w2).eh;
                if (w2 instanceof com.corrodinggames.rts.game.units.UnitInstance) {
                    string = ((com.corrodinggames.rts.game.units.UnitInstance)w2).c();
                }
                com.corrodinggames.rts.gameFramework.GlobalState.a("Remaining object: " + string);
            }
            if (com.corrodinggames.rts.gameFramework.GlobalState.B().aa()) {
                throw new RuntimeException("We still had " + n2 + " objects in gameObjectListForLogic after removeAll");
            }
        }
        com.corrodinggames.rts.game.units.UnitInstance.bF().clear();
        com.corrodinggames.rts.gameFramework.w.dK().clear();
        CustomUnitType.dD();
        this.visibleObjects.clear();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(boolean bl2) {
        Object object = this.aj;
        synchronized (object) {
            if (this.ao != null) {
                this.ao.l();
            }
            this.bq = false;
            if (!bl2) {
                this.cb.e();  // 02b ba.e() L236: 停止并清理 (stopIfRecording 为幻觉)
            }
            this.bU.c();
            this.g();  // 02b L1017: this.g()
            if (!av()) {
                this.bN.f();
            }
            this.bR.a(bl2);
            if (this.bL != null) {
                this.bL.disposeAll();
                this.bL = null;
            }
            if (this.ce != null) {
                this.ce = null;
            }
            if (this.cc != null) {
                this.cc.b();
            }
            this.flyoverPrevUnit = null;
            this.flyoverNextUnit = null;
            this.isExiting = 0;
            PlayerState.onResourceChanged();
            this.a(com.corrodinggames.rts.gameFramework.audio.DataFieldProvider.a, com.corrodinggames.rts.gameFramework.audio.DataFieldInt.a);  // 02b g.f.a/g.c.a (L1040)
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void a(float f2, int n2) {
        Object object = this.aj;
        synchronized (object) {
            this.b(f2, n2);  // 02b L1047: a(float,int) -> b(float,int)
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(float f2, int n2) {
        float f3;
        float f4;
        float f5;
        if (this.bx == 2) {
            this.aF();
        } else if (this.bx % 10000 == 0 && this.bx != 0) {
            this.aF();
        }
        if (aL && !this.aS && at() && Debug.getNativeHeapAllocatedSize() > 0xC800000L) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("getNativeHeapAllocatedSize: " + GameUtils.g((int)Debug.getNativeHeapAllocatedSize()));
            this.aS = true;
        }
        this.aE();
        this.eb.a();
        this.ec.b();
        this.cd.a(GamePhase.a);
        this.bX.m(f2);
        this.ao = this.ap;
        if (!this.ao.b()) {
            return;
        }
        this.cd.a(GamePhase.b);
        while (this.k.peek() != null) {
            Runnable runnable = (Runnable)this.k.poll();
            runnable.run();
        }
        if (!this.bG) {
            if (this.aq) {
                return;
            }
            Log.d("RustedWarfare", "game running without a loaded level!!!");
            this.h();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return;
        }
        this.bq = true;
        if (!this.F && this.bx > 5) {
            this.F = true;
            boolean bl2 = false;
            if (this.bQ.numIncompleteLoadAttempts > 1) {
                bl2 = true;
            }
            this.bQ.numIncompleteLoadAttempts = 0;
            if (this.ee) {
                this.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
            }
            this.bQ.save();
            if (this.ee && (this.ef || this.bZ.c() > 0)) {
                if (this.eg != null) {
                    this.c("Safe mode", "Started game in safe mode due to " + this.eg + ". Mods have been disabled.");  // 02b L1106
                } else if (bl2) {
                    this.c("Safe mode", "Started game in safe mode due to failed loading attempts. Mods have been disabled.");  // 02b L1108
                } else {
                    this.c("Safe mode", "Started game in safe mode due to multiple loads without starting a game or exiting. Mods have been disabled.");  // 02b L1110
                }
            }
        }
        if (!this.bH && this.bG && this.bQ.numLoadsSinceRunningGameOrNormalExit != 0) {
            this.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
            this.bQ.save();
        }
        this.ca.b();
        float f6 = this.cV * this.cY;
        if (f6 != this.cX) {
            f5 = this.da / this.cX + this.cy;
            f4 = this.db / this.cX + this.cz;
            this.cX = f6;
            this.onBackPressed();
            if (this.cZ) {
                f3 = this.da / this.cX + this.cy;
                float f7 = this.db / this.cX + this.cz;
                this.a(this.cy - (f3 - f5), this.cz - (f7 - f4));
                this.cZ = false;
            }
        }
        if (this.cr != 0.0f || this.cs != 0.0f) {
            f5 = 3.0f * f2;
            f4 = 0.0f;
            if (this.cr > 0.0f) {
                f4 = GameUtils.g(this.cr, f5);
            }
            if (this.cr < 0.0f) {
                f4 = GameUtils.f(this.cr, -f5);  // 02b f.f(float,float)
            }
            f4 += 0.15f * this.cr;
            f3 = 0.0f;
            if (this.cs > 0.0f) {
                f3 = GameUtils.g(this.cs, f5);
            }
            if (this.cs < 0.0f) {
                f3 = GameUtils.f(this.cs, -f5);  // 02b f.f(float,float)
            }
            f3 += 0.15f * this.cs;
            if (GameUtils.c(this.cr) <= f5) {
                f4 = this.cr;
                this.cr = 0.0f;
            } else {
                this.cr -= f4;
            }
            if (GameUtils.c(this.cs) <= f5) {
                f3 = this.cs;
                this.cs = 0.0f;
            } else {
                this.cs -= f3;
            }
            this.cy += f4;
            this.cz += f3;
            this.a(this.cy, this.cz);
            }
        if (this.cR != this.cS) {
            this.onBackPressed();
        }
        if (f2 > 3.0f) {
            f2 = 3.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (this.bu >= 0.0f) {
            f2 = this.bu;
        }
        this.bA = (int)((float)this.bA + f2 * 16.666666f);
        this.bX.c(f2);  // 02b L1497: bX.c(var1)
        this.q += n2;
        ++this.r;
        if (this.r >= 40) {
            if (this.q == 0) {
                this.q = 1;
            }
            this.s = (int)((float)(this.r * 1000 / this.q) + 0.5f);
            this.t = (float)this.q / (float)this.r;
            this.q = 0;
            this.r = 0;
            if (this.bQ.showFps) {
                this.string4 = this.s + "fps";
            }
        }
        for (int i2 = 0; i2 < this.dM.length; ++i2) {
            this.dM[i2] = true;
        }
        this.dh = GameUtils.a(this.dh, 0.1f * f2);
        this.di = GameUtils.a(this.di, 0.1f * f2);
        this.dh = GameUtils.b(this.dh, 5.0f);
        this.di = GameUtils.b(this.di, 5.0f);
        this.bS.a(f2);
        com.corrodinggames.rts.game.map.MapEngine.f();  // 02b L354
        if (this.bX.B) {
            float f8 = f2;
            if (this.cb.v != 1) {
                f8 *= (float)this.cb.v;
            }
            this.bX.c(f8);  // 02b L1497: bX.c(var1)
            if (!this.a(true) && !this.bX.Y) {
                this.stepAccumulator += f8;
                while (this.stepAccumulator > this.bX.c()) {
                    if (this.bX.shouldGameBePaused()) {
                        this.bX.Y = true;
                        break;
                    }
                    this.stepAccumulator -= this.bX.c();
                    this.bX.a(this.bX.c(), false);
                    if (this.bX.Y) break;
                    this.a(this.bX.c());
                }
                if (!this.bX.C) {
                    if (this.bX.af || this.bX.ad) {
                        if (this.bX.af && this.bX.ad && this.bx < this.bX.X - this.bX.Q - 5) {
                            this.bX.sendIncorrectPassword("nearly within frame range");
                            this.bX.af = false;
                        }
                        if (this.bx > this.bX.X - 6) {
                            this.bX.sendIncorrectPassword("we have back within frame range");
                            this.bX.af = false;
                            this.bX.ad = false;
                        }
                    }
                    if (!this.bX.ad && this.bx < this.bX.X - this.bX.Q - 10) {
                        this.bX.sendIncorrectPassword("we are slightly out of frame range, speeding up");
                        this.bX.ad = true;
                    }
                    if (!this.bX.af && this.bx < this.bX.X - this.bX.Q - 30) {
                        this.bX.sendIncorrectPassword("we are out of frame range, fast forwarding (" + this.bx + "->" + this.bX.X + ")");
                        this.bX.af = true;
                    }
                    if (!this.bX.af && this.bX.ad) {
                        this.bX.ae += f2;
                        if (this.bX.ae > this.bX.c() * 3.0f) {
                            this.bX.ae = 0.0f;
                            this.bX.a(this.bX.c(), true);
                            if (!this.bX.Y) {
                                this.a(this.bX.c());
                            }
                        }
                    }
                    if (this.bX.af) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                    if (this.bx < this.bX.X - 90) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                    if (this.bx < this.bX.X - 120) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                    if (this.bx < this.bX.X - 600) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                }
            }
        } else if (this.cb.i()) {
            float f9 = f2;
            if (this.cb.v != 1) {
                f9 *= (float)this.cb.v;
            }
            if (this.bt != 1.0f) {
                f9 *= this.bt;
            }
            if (!this.a(false)) {
                this.stepAccumulator += f9;
                while (this.stepAccumulator > this.bX.c()) {
                    this.stepAccumulator -= this.bX.c();
                    if (this.bX.shouldGameBePaused()) break;
                    this.a(this.bX.c());
                }
            }
            if (this.stepAccumulator > 100.0f) {
                this.stepAccumulator = 100.0f;
            }
            if (this.stepAccumulator < 0.0f) {
                this.stepAccumulator = 0.0f;
            }
        } else if (!this.a(false)) {
            this.a(f2);
        }
        if (this.a(false)) {
            try {
                Thread.sleep(2L);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.bU.a(f2);
        this.bM.b(f2);
        this.bN.a(f2);
        this.bT.createEditableBinding();  // 02b ac.b() L336 (控制器数量检查)
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().a(f2);
        this.cd.b(GamePhase.b);
        this.cd.a(GamePhase.c);
        if (!this.dv) {
            if (this.bO.a()) {
                this.a((Renderer)null, f2);
            } else if (this.ao.n()) {
                Renderer l2 = this.ao.b(true);
                this.a(l2, f2);
            } else {
                com.corrodinggames.rts.appFramework.AppFramework f10 = this.ao;
                this.ao.a(f2, n2);
                if (f10.c() && !f10.e()) {
                    Object object = f10.g();
                    synchronized (object) {
                        if (f10.c() && !f10.e()) {
                            this.cd.a(GamePhase.w);
                            Renderer l3 = f10.b(true);
                            this.cd.b(GamePhase.w);
                            try {
                                if (!f10.e()) {
                                    if (l3 != null) {
                                        if (l3.c()) {
                                            com.corrodinggames.rts.gameFramework.GlobalState.e("gameengine draw: bufferedCanvas drawn on");
                                        }
                                        l3.a(true);
                                    }
                                    if (l3 == null) {
                                        com.corrodinggames.rts.gameFramework.GlobalState.f("GameEngine gameViewCanvas is null after lockCanvas - " + f10.hashCode());
                                    }
                                    this.a(l3, f2);
                                    this.bO.a((Renderer)null);
                                }
                            }
                            finally {
                                if (l3 != null) {
                                    try {
                                        f10.a(l3, true);
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        illegalArgumentException.printStackTrace();
                                        com.corrodinggames.rts.gameFramework.GlobalState.f("GameEngine catch currentGameView - " + f10.hashCode());
                                        com.corrodinggames.rts.gameFramework.GlobalState.f("GameEngine catch currentGameView.gameThreadSync - " + f10.g().hashCode());
                                        f10.h();
                                    }
                                    catch (IllegalStateException illegalStateException) {
                                        illegalStateException.printStackTrace();
                                        com.corrodinggames.rts.gameFramework.GlobalState.f("GameEngine catch currentGameView - " + f10.hashCode());
                                        com.corrodinggames.rts.gameFramework.GlobalState.f("GameEngine catch currentGameView.gameThreadSync - " + f10.g().hashCode());
                                    }
                                }
                            }
                        }
                    }
                }
                this.ao.b(f2, n2);
            }
        }
        this.dv = false;
        this.Z();  // 02b L1424
        this.cd.b(GamePhase.c);
        if (this.du) {
            this.du = false;
            Integer n3 = GlobalState.extractMapLevel(this.dl);  // 02b L1428
            String string = null;
            if (n3 != null) {
                string = GlobalState.findNextMapLevel(this.dl);  // 02b L1431
            }
            if (this.bX.B) {
                string = null;
                new GameEngine$a(this).start();  // 02b L1436
            }
            if (string != null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("gotoNextLevel: Loading next level: " + string);
                this.dl = string;
                this.bS.selectionGroup.b();  // 02b L1442: bS.h.b()
                this.a(true, false, com.corrodinggames.rts.gameFramework.GameStateEnum.b);  // 02b L1443: s.b
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("gotoNextLevel: No next level, finishing");
                this.bG = false;
                InGameActivity g2 = this.ao.i();
                if (g2 != null) {
                    g2.b();
                    g2.m();
                } else {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("gotoNextLevel: Error getInGameActivity==null");
                }
            }
        }
        if (!this.aq && this.bE && !this.i) {
            e("starting method trace");
            Debug.startMethodTracing((String)"lukeTrace", (int)110000000);
            this.i = true;
        }
        this.bF = true;
        this.ed.a();
        this.cd.b(GamePhase.b);
        this.cd.b();
    }

    public void captureScreenshot() {
        InGameActivity g2 = this.ao.i();
        if (g2 != null) {
            if (!g2.c()) {
                g2.b();
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.b("stopAndClose: inGameActivity is isFinishing");
            }
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("stopAndClose: Error getInGameActivity==null");
        }
    }

    public void a(float f2) {  // 02b L1485 updateAllGame
        Object object2;
        int n2;
        int n3;
        if (this.ay() && f2 < 0.1f) {
            com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("updateAllGame1: deltaSpeed:" + f2 + " frame:" + this.bx + " network.currentStepRate:" + this.bX.c());
        }
        if (this.bt != 1.0f && !this.bX.B && !this.cb.i()) {
            f2 *= this.bt;
        }
        this.deltaTime = (f2 *= this.targetFps) + 2.0f;
        this.deltaTimeBackup = f2;
        this.bX.c(f2);
        this.by = (int)((float)this.by + f2 * 16.666666f);
        this.cf.c();
        this.cb.a(f2);
        ++this.bx;
        PlayerState.g(f2);  // 02b L1502: n.g(var1)
        if (this.bL != null) {
            this.bL.e(f2);
        }
        if (this.ay() && f2 < 0.1f) {
            com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("updateAllGame2: deltaSpeed:" + f2 + " frame:" + this.bx);
        }
        com.corrodinggames.rts.game.units.UnitInstance.bF();
        com.corrodinggames.rts.gameFramework.utility.DequeList o2 = com.corrodinggames.rts.gameFramework.GameObject.dK();  // 02b w.dK()
        Object[] objectArray = o2.b();
        int n4 = o2.size();
        boolean bl2 = this.ay();
        for (n3 = 0; n3 < n4; ++n3) {
            com.corrodinggames.rts.gameFramework.GameObject w2 = (com.corrodinggames.rts.gameFramework.GameObject)objectArray[n3];  // 02b L1519: w
            if (bl2 && f2 != this.deltaTimeBackup) {
                com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("JIT bug detected, attempting to correct. before object:" + w2.eh + " frame:" + this.bx + " deltaSpeed:" + f2, true);  // 02b L1521: ad.h
                f2 = this.deltaTimeBackup;
            }
            w2.a(f2);
        }
        if (this.ay() && f2 < 0.1f) {
            com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("updateAllGame3: deltaSpeed:" + f2 + " frame:" + this.bx);
        }
        n3 = o2.headNode.size();  // 02b L1532: var2.a
        for (n2 = 0; n2 < n3; ++n2) {
            com.corrodinggames.rts.gameFramework.utility.Result r2 = (com.corrodinggames.rts.gameFramework.utility.Result) o2.headNode.get(n2);  // 02b L1536: utility.r
            if (r2.a != ResultState.a) continue;  // 02b L1537: var8.a == utility.q.a
            com.corrodinggames.rts.gameFramework.GameObject w3 = (com.corrodinggames.rts.gameFramework.GameObject)r2.b;
            if (!w3.ej) continue;
            w3.a(f2);  // 02b L1538-1540: w var9
        }
        this.cd.a(GamePhase.m);
        this.cc.a();
        this.cd.b(GamePhase.m);
        com.corrodinggames.rts.game.units.y.g(f2);
        CustomUnitType.s(f2);
        CustomUnitType.a(f2, 0);
        ++this.isExiting;
        if (this.isExiting >= 1000) {
            this.isExiting = 0;
            n2 = 0;
            for (Object object2_1417 : com.corrodinggames.rts.game.units.UnitInstance.bF()) {
                if (!((com.corrodinggames.rts.game.units.UnitInstance)object2_1417).bV || object2_1417 instanceof TreeDecoration) continue;
                ++n2;
            }
            int n5 = 70;
            if (n2 > 70) {
                com.corrodinggames.rts.gameFramework.utility.DequeList o3 = com.corrodinggames.rts.game.units.UnitInstance.bF();
                Iterator iterator = o3.iterator();  // 02b L1566-1567
                while (iterator.hasNext()) {
                    com.corrodinggames.rts.game.units.UnitInstance am2 = (com.corrodinggames.rts.game.units.UnitInstance)iterator.next();
                    if (!(am2 instanceof com.corrodinggames.rts.game.units.UnitInstance)) continue;
                    com.corrodinggames.rts.game.units.UnitInstance am3 = am2;
                    if (!am3.bV || am3 instanceof TreeDecoration || am3.bW >= (long)(this.by - 30000) || n2 <= 70) continue;
                    am3.a();
                    --n2;
                }
            }
        }
        this.cd.a(GamePhase.l);
        PlayerState.f(f2);
        this.cd.b(GamePhase.l);
        com.corrodinggames.rts.gameFramework.effects.GameHUD.a(f2);  // 02b L1582: d.a.a(var1)
        this.bR.a(f2);
        this.minimapPaint.reset(f2);  // 02b L1584: D.a(var1)
        com.corrodinggames.rts.gameFramework.utility.y.a(f2);
        if (this.ce != null) {
            this.ce.geti(f2);  // 02b L1587: ce.c(var1)
        }
        this.cd.a(GamePhase.o);
        this.bV.a(f2);
        this.cd.b(GamePhase.o);
        this.cd.a(GamePhase.n);
        this.bW.a(f2);
        this.cd.b(GamePhase.n);
        this.bU.b(f2);
        if (this.cg != null) {
            this.cg.b();
        }
        this.bY.b();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(Renderer l2, float f2) {  // 02b L1604
        Object object = this.ak;
        synchronized (object) {
            this.b(l2, f2);  // 02b L1607: this.b(var1,var2)
        }
    }

    public boolean togglePause() {
        if (this.shaderBase == null) {
            this.shaderBase = new ResourceLoader("assets/shaders/post_base.frag");
        }
        if (this.shaderDisplacement == null) {
            this.shaderDisplacement = new ResourceLoader("assets/shaders/post_displacement.frag");
        }
        this.shaderBase.a(this.bO);
        this.shaderDisplacement.a(this.bO);
        if (this.shaderBase.g || this.shaderDisplacement.g) {
            if (!this.M) {
                this.M = true;
                com.corrodinggames.rts.gameFramework.GlobalState.e("setupPostprocessing: failed");
            }
            return false;
        }
        return true;
    }

    public void a(ResourceLoader j2) {  // 02b L1634
        if (this.savedGraphicsLayer != null) {
            throw new RuntimeException("Layer already enabled");
        }
        this.savedGraphicsLayer = this.bO;
        this.bO = j2.b;
        this.bO.i();
        this.bO.a(new Rect(0, 0, this.bO.m(), this.bO.n()));
        this.bO.b(j2.f, j2.e);
    }

    public void b(ResourceLoader j2) {
        if (this.savedGraphicsLayer == null) {
            throw new RuntimeException("Layer not enabled");
        }
        this.bO.j();
        this.bO.p();
        this.bO = this.savedGraphicsLayer;
        this.savedGraphicsLayer = null;
        this.bO.b(j2.f, j2.e);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(Renderer l2, float f2) {
        int n2;
        boolean bl2;
        if (l2 == null) {
            b("drawAll", "canvas is null, not may not be available yet");
            return;
        }
        if (aB) {
            return;
        }
        this.bO.a(l2);
        this.bO.a(this.ao.d());
        this.bO.g();
        ++this.bz;
        com.corrodinggames.rts.gameFramework.rendering.TeamColorTexture.G = 0.0f;  // 02b L1666: m.h.G
        if (this.du) {
            this.bO.clearScreen(Color.a(0, 0, 0));  // 02b L1668: b(int)
            this.bO.a("Loading..", this.co, this.cp, this.dp);
            return;
        }
        float f3 = this.cn;
        if (f3 != 1.0f) {
            this.bO.i();
            this.bO.a(f3, f3);
        }
        if ((bl2 = com.corrodinggames.rts.gameFramework.GlobalState.isShaderEffectsSupported()) && this.h(113) && this.h(44)) {
            bl2 = false;
        }
        if (bl2) {
            boolean bl3 = this.i();  // 02b L1683: boolean var5 = this.i()
            if (!bl3) {
                bl2 = false;
            }
        }
        if (bl2) {
            this.a(this.shaderBase);
            try {
                this.bO.clearScreen(Color.a(0, 0, 0));  // 02b L1693
                this.cd.a(GamePhase.d);
                this.drawGameWorld((Renderer)null, f2);  // 02b L1695: c((m.l)null,var2)
                this.cd.b(GamePhase.d);
            }
            finally {
                this.b(this.shaderBase);  // 02b L1698: b(this.K)
            }
            this.shaderBase.b();
            if (!this.shaderDisplacement.a()) {
                this.a(this.shaderDisplacement);
                try {
                    this.bO.clearScreen(Color.a(128, 128, 255));  // 02b L1707
                    this.R();  // 02b L1708
                    n2 = this.bR.a(f2, 3);
                    this.bR.l = null;
                }
                finally {
                    this.b(this.shaderDisplacement);  // 02b L1712: b(this.L)
                }
                if (n2 > 0) {
                    float f4 = this.bO.s();
                    this.shaderDisplacement.d.a("screenBase", this.shaderBase.a);  // 02b L1717: K.a
                    this.shaderDisplacement.d.b("screenBaseSize", this.shaderBase.a);
                    this.shaderDisplacement.d.a("u_resolution", this.cl, this.cm);
                    this.shaderDisplacement.d.a("u_offsetBy", 0.2f * this.cX);
                    this.shaderDisplacement.d.a("u_uiScaling", f4);
                    this.shaderDisplacement.b();
                }
            }
        } else {
            this.cd.a(GamePhase.d);
            this.drawGameWorld(l2, f2);  // 02b L1727: c(var1,var2)
            this.cd.b(GamePhase.d);
        }
        if (!this.A()) {  // 02b L1731
            this.cd.a(GamePhase.f);
            this.drawUiOverlay(l2, f2);  // 02b L1733: d(var1,var2)
            this.cd.b(GamePhase.f);
        }
        if (this.bQ.showFps && this.cT == 0.0f && !this.cU && !this.cS) {
            this.bO.a(this.string4, 100.0f, 35.0f, this.m);
        }
        if (f != null) {
            this.bO.a(f, 100.0f, 85.0f, this.m);
        }
        if (!this.aq && (this.bO.d() != null || com.corrodinggames.rts.gameFramework.GlobalState.aW)) {
            this.bS.c(f2);
        }
        if (!this.A()) {  // 02b L1749
            this.bR.a(f2, 4);
        }
        CustomUnitType.dE();
        this.bO.h();
        if (f3 != 1.0f) {
            l2.a();
        }
    }

    public boolean getGameState() {
        if (!this.bQ.showUnitIcons) {
            return false;
        }
        if ((double)this.cX < 0.7 && this.cE >= this.bL.i() - 5.0f && this.cB >= this.bL.j() - 5.0f) {
            return true;
        }
        if (GameEngine.C()) {
            return (double)this.cX < 0.1;
        }
        if (av()) {
            return (double)this.cX < 0.27;
        }
        return (double)this.cX < 0.4;
    }

    public void b(float f2) {
        boolean bl2 = false;
        if (this.cQ.a < 0 || this.cQ.b < 0 || (float)this.cQ.c > this.bL.i() || (float)this.cQ.d > this.bL.j()) {  // 02b L1769: cQ.a
            bl2 = true;
        }
        if (bl2) {
            this.bO.clearScreen(Color.a(0, 0, 0));  // 02b L1774
        }
    }

    public void drawGameWorld(float f2) {
    }

    public void drawGameWorld(Renderer l2, float f2) {
        com.corrodinggames.rts.gameFramework.GameObject w2;
        int n2;
        int n3;
        com.corrodinggames.rts.gameFramework.GameObject wArray[];  // 02b L1869: w[] var14
        int n4;
        if (!this.bG) {
            return;
        }
        this.cd.a(GamePhase.h);
        this.visibleObjectsDirty.b();
        this.dw = 0;
        boolean bl2 = false;
        com.corrodinggames.rts.gameFramework.GameObject[] wArray2 = com.corrodinggames.rts.game.units.UnitInstance.er.a();
        int n5 = com.corrodinggames.rts.gameFramework.w.er.size();
        for (n4 = 0; n4 < n5; ++n4) {
            w2 = wArray2[n4];
            boolean bl3 = w2.el;  // 02b L1792: boolean var8 = var7.el
            boolean bl4 = w2.a(this);  // 02b L1793: var9 = var7.a((l)this)
            w2.el = bl4;
            if (bl3 != bl4) {
                bl2 = true;
            }
            if (!bl4) continue;
            this.visibleObjectsDirty.a(w2);  // 02b L1800: this.X.a(var7)
        }
        if (this.visibleObjects.size() != this.visibleObjectsDirty.size()) {
            bl2 = true;
        }
        this.cd.b(GamePhase.h);
        this.cd.a(GamePhase.i);
        if (bl2) {
            com.corrodinggames.rts.gameFramework.utility.TypedObjectList s2 = this.visibleObjects;
            this.visibleObjects = this.visibleObjectsDirty;
            this.visibleObjectsDirty = s2;
        }
        if (!this.getGameState()) {  // 02b L1816: !this.j()
            Collections.sort(this.visibleObjects, com.corrodinggames.rts.gameFramework.w.ei);
        }
        this.cd.b(GamePhase.i);
        this.cd.a(GamePhase.q);
        this.cd.a(GamePhase.s);
        this.bO.i();
        this.bO.a(this.cK);
        this.cd.b(GamePhase.s);
        this.cd.a(GamePhase.r);
        this.b(f2);  // 02b L1827: b(var2)
        this.cd.b(GamePhase.r);
        if (this.bQ.renderFancyWater) {
            if (this.waterTexture1 == null) {
                this.waterTexture1 = this.bO.loadImageFromResource(R$drawable.water_cloud);  // 02b L1831: a(int)
            }
            if (this.waterTexture2 == null) {
                this.waterTexture2 = this.bO.loadImageFromResource(R$drawable.water_layer1);
            }
            if (this.waterTexture3 == null) {
                this.waterTexture3 = this.bO.loadImageFromResource(R$drawable.water_layer2);
            }
            this.isGameOver.a(this.cK);
            this.R += 0.05f * f2;
            if (this.R > 100.0f) {
                this.R -= 100.0f;
            }
            this.bO.a(this.waterTexture1, this.isGameOver, null, this.cu / 6, this.cv / 6, 1, 1);
            this.isGameOver.a(this.cL);
            this.gameStartTime.a(this.cL);
            this.bO.i();
            this.R();  // 02b L1852
            this.bO.a(this.waterTexture3, this.gameStartTime, null, (float)this.cu + this.R, (float)this.cv + this.R, 0, 0);
            this.bO.a(this.waterTexture2, this.gameStartTime, null, (float)this.cu, (float)this.cv, 0, 0);
            this.bO.j();
        }
        this.cd.a(GamePhase.t);
        if (this.bL != null && this.ar()) {
            this.bL.d(f2);
        }
        this.cd.b(GamePhase.t);
        this.R();  // 02b L1864
        this.bO.a(this.cL);
        n4 = this.getGameState() ? 1 : 0;  // 02b L1866: this.j()
        this.bU.c(f2);
        this.cd.b(GamePhase.q);
        wArray = this.visibleObjects.a();
        n3 = this.visibleObjects.size();
        this.dc = true;
        this.dd = true;
        this.de = true;
        this.df = true;
        this.dg = true;
        if ((double)this.cX < 0.45) {
            this.de = false;
            this.dc = false;
            this.dg = false;
        }
        if ((double)this.cX < 0.3) {
            this.df = false;
            this.dd = false;
        }
        if (n4 == 0) {
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                if (w2.em != 0) continue;
                w2.c(f2);
            }
        }
        com.corrodinggames.rts.gameFramework.effects.GameHUD.b(f2);  // 02b L1898: d.a.b(var2)
        this.cd.a(GamePhase.g);
        this.bR.b(f2);
        this.bR.a(f2, 1);
        this.cd.b(GamePhase.g);
        this.cd.a(GamePhase.p);
        if (n4 != 0) {
            if (this.bS.q() == 0) {
                com.corrodinggames.rts.game.units.UnitInstance.bI.a(255, 195, 195, 195);
                com.corrodinggames.rts.game.units.UnitInstance.bJ.a(255, 255, 255, 255);
            } else {
                com.corrodinggames.rts.game.units.UnitInstance.bI.a(175, 175, 175, 175);
                com.corrodinggames.rts.game.units.UnitInstance.bJ.a(255, 255, 255, 255);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                if (w2.f(f2)) continue;
                w2.c(f2);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                w2.a(f2, true);
                w2.p(f2);
            }
        } else {
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                w2.d(f2);
            }
            for (n2 = 0; n2 < n5; ++n2) {
                w2 = wArray2[n2];
                if (!w2.el) {
                    if (!(w2 instanceof com.corrodinggames.rts.game.units.UnitInstance)) continue;
                    com.corrodinggames.rts.game.units.UnitInstance am2 = (com.corrodinggames.rts.game.units.UnitInstance)w2;
                    if (!am2.cG || am2.bX != this.bs && !am2.cf()) continue;
                }
                w2.e(f2);
                if (w2.el) continue;
                w2.p(f2);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                if (w2.em == 0 || w2.em == 10) continue;
                w2.c(f2);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                w2.a(f2, false);
                w2.p(f2);
            }
            PlayerState.h(f2);
        }
        this.de = true;
        this.df = true;
        this.cd.b(GamePhase.p);
        this.cd.a(GamePhase.g);
        this.bR.a(f2, 2);
        this.cd.b(GamePhase.g);
        for (n2 = 0; n2 < n3; ++n2) {
            w2 = wArray[n2];
            if (w2.em != 10) continue;
            w2.c(f2);
        }
        this.minimapPaint.b(f2);
        if (this.ce != null) {
            this.ce.a(f2);
        }
        this.c(f2);  // 02b L1985: c(var2)
        com.corrodinggames.rts.gameFramework.utility.y.b(f2);
        this.cc.c(f2);
        this.cd.a(GamePhase.e);
        this.bO.j();
        this.cd.b(GamePhase.e);
    }

    public void drawUiOverlay(Renderer l2, float f2) {
        this.bS.b(f2);
        if (this.ce != null) {
            this.ce.geti(f2);  // 02b L1831: ce.b(var2)
        }
        this.bW.e(f2);
        if (this.bQ.showFps && this.cT == 0.0f) {
            this.cd.c();
        }
        if (this.ch) {
            this.bO.a("Look Mode", this.co, this.cp, this.dp);
        }
        if (this.bm) {
            int n2 = 20;
            for (int i2 = 0; i2 < PlayerState.c; ++i2) {
                PlayerState n3 = PlayerState.u(i2);
                if (n3 == null || !(n3 instanceof com.corrodinggames.rts.game.ai.AIStrategy)) continue;
                com.corrodinggames.rts.game.ai.AIStrategy a2 = (com.corrodinggames.rts.game.ai.AIStrategy)n3;
                this.bO.a(a2.k + "| c:" + a2.o, 20.0f, (float)n2, this.dn);
                n2 += 20;
            }
        }
    }

    public void onBackPressed() {
        float f2;
        this.cj = this.W();  // 02b L268: this.W()
        this.X();  // 02b L2026: this.X()
        this.co = this.cl / 2.0f;
        this.cp = this.cm / 2.0f;
        this.cq = (int)(this.cm / 3.0f);
        if (av()) {
            this.cq = (int)(this.cm / 2.5f);
        }
        if (this.cq > (f2 = (float)((int)(this.cl / 3.0f)))) {
            this.cq = f2;
        }
        int n2 = (int)(250.0f * this.cj);
        this.cq = GameUtils.b(this.cq, 60.0f, (float)n2);
        float f3 = this.cy + this.cI;
        float f4 = this.cz + this.cJ;
        if (this.cS) {
            this.cF = this.cl;
            this.cG = this.cl;
        } else {
            this.cG = this.cl - this.cq + 1.0f;
            this.cF = com.corrodinggames.rts.gameFramework.ui.InGameUI.bO ? this.cl : this.cG;  // 02b L2048: f.g.bO
        }
        if (this.cF < 1.0f) {
            this.cF = 1.0f;
        }
        if (this.cG < 1.0f) {
            this.cG = 1.0f;
        }
        if (this.cR != this.cS) {
            f3 = !this.cS ? (f3 -= this.cq / 2.0f / this.cX) : (f3 += this.cq / 2.0f / this.cX);
        }
        this.cR = this.cS;
        this.cH = this.cm;
        this.cA = this.cF / this.cX;
        this.cB = this.cH / this.cX;
        this.cE = this.cG / this.cX;
        this.cI = this.cA / 2.0f;
        this.cJ = this.cB / 2.0f;
        this.cK.a(0, 0, (int)this.cF, (int)this.cH);
        this.cL.a(0, 0, (int)this.cA + 1, (int)this.cB + 1);
        this.cM.a(0.0f, 0.0f, this.cA + 1.0f, this.cB + 1.0f);
        this.a(f3 - this.cI, f4 - this.cJ);
    }


    public void b(int n2, int n3) {
        this.a(n2, n3, 1.0f);
    }

    public void a(int n2, int n3, float f2) {  // 02b L2088
        this.cl = n2;
        this.cm = n3;
        this.cn = f2;
        this.onBackPressed();
    }


    public String extractMapLevel() {  // 02b i.l() L2095
        if (com.corrodinggames.rts.gameFramework.GlobalState.aX) {
            return "com.corrodinggames.rts.java";
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            return "com.corrodinggames.rts.gdx";
        }
        if (aU) {
            return "com.corrodinggames.rts.server";
        }
        if (this.am == null) {
            return "<null context>";
        }
        return this.am.h();
    }




    public boolean n() {
        return this.r().contains("p");  // 02b L2119: this.r()
    }


    public int c(boolean bl2) {
        if (aU || bl2) {
            return 176;
        }
        try {
            PackageInfo packageInfo = this.am.f().getPackageInfo(this.am.h(), 0);
            int n2 = packageInfo.versionCode;
            return n2;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            throw new RuntimeException(nameNotFoundException);
        }
    }

    public String onMatchComplete() {
        if (!at()) {
            return null;
        }
        try {
            PackageInfo packageInfo = this.am.f().getPackageInfo(this.am.h(), 64);
            Signature[] signatureArray = packageInfo.signatures;
            int n2 = signatureArray.length;
            int n3 = 0;
            if (n3 < n2) {
                Signature signature = signatureArray[n3];
                String string = GameUtils.b(signature.toByteArray());
                return string;
            }
            return null;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            throw new RuntimeException(nameNotFoundException);
        }
    }


    public boolean p() {
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aZ) {
            if (this.returnToMenu()) {
                return true;
            }
            if (aV) {
                return true;
            }
        }
        return false;
    }

    public boolean returnToMenu() {
        return com.corrodinggames.rts.game.units.UnitType.class.getSimpleName().equals("OrderableUnit");  // 02b L2174: units.y.class
    }


    public String r() {
        String string = this.t();  // 02b L2178: this.t()
        if ("" != null && !"".equals("")) {
            string = string + "-";
        }
        return string;
    }


    public void setGameSpeed() {
        renderer = null;
        this.t();  // 02b L2188: this.t()
    }


    public String t() {
        if (renderer != null) {  // 02b L2192: a != null
            return renderer;
        }
        String string = "v" + this.u();  // 02b L2195: this.u()
        if (!com.corrodinggames.rts.gameFramework.GlobalState.as || aV) {
            string = "DEBUG BUILD - " + string;
        } else if (com.corrodinggames.rts.gameFramework.GlobalState.at) {
            string = "TESTING BUILD - " + string;
        } else if (string.contains("p")) {
            string = "BETA VERSION - " + string;
        }
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aZ && this.returnToMenu()) {
            string = "RAW - " + string;
        }
        renderer = string;
        return renderer;
    }


    public String u() {
        return "1.15";
    }


    public String l() {  // 02b game/i.java L2095-2097 (简化: aU/am 分支省略)
        return com.corrodinggames.rts.gameFramework.GlobalState.aX ? "com.corrodinggames.rts.java" : (com.corrodinggames.rts.gameFramework.GlobalState.aY ? "com.corrodinggames.rts.gdx" : "<null context>");
    }

    public String m() {  // 02b game/i.java L2099-2112 (简化)
        if (com.corrodinggames.rts.gameFramework.GlobalState.aX) {
            return "java";
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            return "java-gdx";
        }
        return "<null context>";
    }

    public String v() {
        return "1.15";
    }

    public synchronized void w() {
        this.ac = false;
        if (this.ab != null) {
            this.ab.cancel();
            this.ab = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public synchronized void a(Activity activity, com.corrodinggames.rts.appFramework.AppFramework f2, boolean bl2) {
        Object object = this.ad;
        synchronized (object) {
            if (!aU) {
                f2.a();
            }
            this.an = activity;
            this.cS = this.aq = bl2;
            if (!(!bl2 || this.bG || this.bI || com.corrodinggames.rts.gameFramework.GlobalState.ay || this.bX.B)) {
                this.x();
            }
            com.corrodinggames.rts.appFramework.AppFramework f3 = this.ap;
            if (this.ao == null) {
                this.ao = f2;
            }
            this.ap = f2;
            if (f3 != null && f3 != f2) {
                f3.j();
            }
            if (f2 != null) {
                f2.m();
            }
            if (this.bS != null) {
                this.bS.e();
            }
            this.w();
            this.J();  // 02b L2265: this.J()
        }
    }


    public synchronized void x() {
        if (this.ae > 20) {
            return;
        }
        int n2 = 3;
        int n3 = this.bQ.nextBackgroundMap++;
        if (this.bQ.nextBackgroundMap > 3) {
            this.bQ.nextBackgroundMap = 1;
        }
        this.bQ.save();
        n3 = GameUtils.b(n3, 1, 3);
        this.dm = null;
        this.dl = "maps/menu_background/menu" + n3 + ".tmx";
        try {
            PlayerState.b(10, true);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        for (int i2 = 0; i2 < PlayerState.c; ++i2) {
            com.corrodinggames.rts.game.ai.AIStrategy a2 = new com.corrodinggames.rts.game.ai.AIStrategy(i2);  // 02b L2289: new game.a.a(var3)
            if (i2 != 0) continue;
            this.bs = a2;
        }
        this.a(false, com.corrodinggames.rts.gameFramework.GameStateEnum.a);  // 02b L2295: s.a
        this.bH = true;
        this.bS.y();
        if (!this.bG) {
            com.corrodinggames.rts.gameFramework.GlobalState.g("Menu load failed");  // 02b L2299: l.g
            ++this.ae;
        }
    }

    void drawUiOverlay(float f2) {
        if (this.aq && !this.bH) {
            if (this.flyoverNextUnit == null) {
                this.flyoverNextUnit = this.y();  // 02b L2309: this.y()
                if (this.flyoverPrevUnit == this.flyoverNextUnit) {
                    this.flyoverNextUnit = null;
                }
            }
            if (this.flyoverPrevUnit == null) {
                this.flyoverPrevUnit = this.flyoverNextUnit;
                this.flyoverNextUnit = null;
            }
            if (this.flyoverLerp != 0.0f && this.flyoverNextUnit != null) {
                this.a(f2, this.flyoverNextUnit.eo, this.flyoverNextUnit.ep, this.flyoverLerp * 0.5f);
            }
            if (this.flyoverPrevUnit != null) {
                boolean bl2 = this.a(f2, this.flyoverPrevUnit.eo, this.flyoverPrevUnit.ep, (1.0f - this.flyoverLerp) * 0.5f);
                float f3 = GameUtils.a(this.cy + this.cI, this.cz + this.cJ, this.flyoverPrevUnit.eo, this.flyoverPrevUnit.ep);
                if (f3 < 6400.0f) {
                    bl2 = true;
                }
                if (bl2) {
                    this.flyoverTransitioning = true;
                }
            }
            if (this.flyoverTransitioning) {
                this.flyoverLerp += 0.01f * f2;
                if (this.flyoverLerp >= 1.0f) {
                    this.flyoverLerp = 0.0f;
                    this.flyoverPrevUnit = null;
                    this.flyoverTransitioning = false;
                }
            }
        }
    }

    com.corrodinggames.rts.game.units.UnitInstance a(PlayerState n2) {
        int n3 = 0;
        for (com.corrodinggames.rts.game.units.UnitInstance am2 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am2.u() || am2.bX != n2 && n2 != null) continue;
            ++n3;
        }
        if (n3 > 0) {
            int n4 = GameUtils.a(0, n3 - 1);
            int n5 = 0;
            for (com.corrodinggames.rts.game.units.UnitInstance am3 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                if (am3.u() || am3.bX != n2 && n2 != null) continue;
                if (n5 == n4) {
                    return am3;
                }
                ++n5;
            }
        }
        return null;
    }

    com.corrodinggames.rts.game.units.UnitInstance y() {
        com.corrodinggames.rts.game.units.UnitInstance am2 = this.a(this.bs);
        if (am2 != null) {
            return am2;
        }
        return this.a((PlayerState) null);
    }

    public boolean a(float f2, float f3, float f4, float f5) {  // 02b L2384
        float f6 = GameUtils.d(this.cy + this.cI, this.cz + this.cJ, f3, f4);
        float f7 = GameUtils.a(this.cy + this.cI, this.cz + this.cJ, f3, f4);
        float f8 = 15.0f;
        float f9 = f5 * f2;
        if (f8 < f9 + 1.0f) {
            f8 = f9 + 1.0f;
        }
        if (f7 < f8 * f8 || this.ct) {
            return true;
        }
        this.cC += GameUtils.cosFast(f6) * f9;
        this.cD += GameUtils.sinFast(f6) * f9;
        if (GameUtils.c(this.cC) >= 1.0f || GameUtils.c(this.cD) >= 1.0f) {
            this.cy += this.cC;
            this.cz += this.cD;
            this.cC = 0.0f;
            this.cD = 0.0f;
            this.a(this.cy, this.cz);
        }
        return false;
    }



    static {
        inputHandler = null;
    }

    public boolean i() {  // 02b i.java L1611-1632: postprocessing 着色器设置
        if (this.shaderBase == null) {
            this.shaderBase = new ResourceLoader("assets/shaders/post_base.frag");
        }
        if (this.shaderDisplacement == null) {
            this.shaderDisplacement = new ResourceLoader("assets/shaders/post_displacement.frag");
        }
        this.shaderBase.a(this.bO);
        this.shaderDisplacement.a(this.bO);
        if (!this.shaderBase.g && !this.shaderDisplacement.g) {
            return true;
        }
        if (!this.M) {
            this.M = true;
            com.corrodinggames.rts.gameFramework.GlobalState.e("setupPostprocessing: failed");
        }
        return false;
    }


    @Override
    public void isKeyJustPressed() {  // GlobalState 抽象 (02b 对应方法待战役对齐)
    }


    @Override
    public String getBuildNumber() {  // 02b i.v() L2219-2221: return "1.15"
        return "1.15";
    }


    @Override
    public void s() {  // 02b i.s() L2186-2189: renderer = null; t()
        renderer = null;
        this.t();
    }


    @Override
    public String getVersion() {  // 02b i.u() L2215-2217: return "1.15"
        return "1.15";
    }

    public void h() {  // 02b i.h() L1471-1483: stopAndClose (简化)
        if (this.ao != null) {
            com.corrodinggames.rts.appFramework.InGameActivity appFramework = this.ao.i();  // 02b L1447: appFramework.g
            if (appFramework != null && !appFramework.c()) {
                appFramework.b();
            }
        }
    }


    @Override
    public String findNextMapLevel() {  // 02b i.m() L2099-2112
        if (com.corrodinggames.rts.gameFramework.GlobalState.aX) {
            return "java";
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            return "java-gdx";
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            return "dedicatedServer";
        }
        if (this.am == null) {
            return "<null context>";
        }
        try {
            android.content.pm.PackageManager packageManager = this.am.f();
            return packageManager.getInstallerPackageName(this.extractMapLevel());
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return "IllegalArgumentException: " + illegalArgumentException.getMessage();
        }
    }


    public void c(float f2) {}  // 02b i.c(float) L1779 空实现


    public void a(org.newdawn.slick.Graphics graphics) {  // 02b game/i.a(Graphics) 简化 TODO (v19.133f)
    }

    public void showPlayerListPopup() {  // 02b ad.java L1246-1270 (H 与 GlobalState.H() 冲突改名) 显示玩家列表
        GlobalState var1 = GlobalState.B();
        String var2 = "";
        java.util.ArrayList var3 = PlayerState.a(true);
        java.util.Iterator var4 = var3.iterator();
        while (var4.hasNext()) {
            PlayerState var5 = (PlayerState) var4.next();
            if (var5 != null) {
                String var6 = "unnamed";
                if (var5.v != null) {
                    var6 = var5.v;
                }
                String var7 = " " + var5.y();
                String var8 = "- ";
                var2 = var2 + var8 + var5.N().toLowerCase() + " [Team " + var5.h() + "] - " + var6 + var7 + "\n";
            }
        }
        GlobalState.e("showPlayerListPopup(): Showing playlist messagebox.");
        var1.c("Players", var2);
    }

}
