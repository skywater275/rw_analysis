/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.PointF;
import android.graphics.Typeface;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapLayerDef;
import com.corrodinggames.rts.game.map.MapSpawn;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.effects.HUDElementRenderer;
import com.corrodinggames.rts.gameFramework.effects.HUDManager;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.aicore.AITask;
import com.corrodinggames.rts.gameFramework.aicore.MissionParser;
import com.corrodinggames.rts.gameFramework.aicore.MissionExecutor;
import com.corrodinggames.rts.gameFramework.aicore.MissionEvent;
import com.corrodinggames.rts.gameFramework.aicore.AIWaveParser;
import com.corrodinggames.rts.gameFramework.aicore.AIDifficulty;
import com.corrodinggames.rts.gameFramework.aicore.AISpawnList;
import com.corrodinggames.rts.gameFramework.aicore.SpawnWeight;
import com.corrodinggames.rts.gameFramework.aicore.TaskStatus;
import java.util.ArrayList;
import java.util.Iterator;

public class AIWaveSystem
extends BaseGameObject {
    public static boolean waveEnabled = false;
    int b;
    int c;
    PlayerState d;
    TargetFilter e;
    TargetFilter f = TargetFilter.b;
    public ArrayList<TaskStatus> g = new ArrayList<TaskStatus>();
    public LocalizedString waveName;
    boolean i;
    boolean briefingPending;
    public boolean q;  // 02b ce.q (待确认类型)

    public LocalizedString h;  // 02b n/f.java L38: public bb h (DesktopWindow ce.h 链)
    public boolean aiEnabled;
    public boolean isActive;
    boolean isDefeated;
    boolean isSkirmish;
    boolean shareFogWithAllies;
    boolean classicMode;
    public boolean infiniteWaves;
    public int waveIndex = 0;
    String waveTitleText = null;
    String waveCompositionText = null;
    int waveCycleCounter = 0;
    public int waveDifficultyBase = 2;  // 02b n/f.java L55: int y
    int waveCycleCount = 1;
    int waveSpacingCounter = 0;
    public int waveCounter = 0;
    float z = 3000.0f;
    float A = 0.0f;
    float B = 0.0f;
    AIDifficulty C = AIDifficulty.a;
    ArrayList D = new ArrayList();
    public Paint paint1;
    public Paint paint2;
    public Paint paint3;
    public Paint paint4;
    final boolean boolean11 = true;
    public ArrayList<AITask> pendingSpawnQueue = new ArrayList<AITask>();
    PointF K = new PointF();
    int L = 0;
    float M = 0.0f;
    public boolean boolean12;
    public ArrayList attackTargets = new ArrayList();
    PointF P = new PointF();
    boolean Q = false;
    boolean R = false;
    ArrayList S = new ArrayList();
    ArrayList T = new ArrayList();

    public void reset(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine", "Map warning: " + string);
        NetEngine.registerRelayServer((String)null, "Map error: " + string);
    }


    public void serializeToStream(OutputNetStream as2) {
        as2.a(this.briefingPending);
        as2.a(this.waveIndex);
        as2.a(this.waveCycleCounter);
        as2.a(this.waveDifficultyBase);
        as2.a(this.waveCycleCount);
        as2.a(this.waveSpacingCounter);
        as2.a(this.z);
        as2.a(this.A);
        as2.a(this.B);
        as2.a(this.isDefeated);
        as2.a(6);
        as2.a(this.pendingSpawnQueue.size());
        for (AITask a2 : this.pendingSpawnQueue) {
            as2.b(a2.taskDescription);
            as2.a(a2.isCompleted);
            as2.a(a2.priority);
            as2.a(a2.maxAttempts);
            as2.a(a2.hasStarted);
            as2.a(a2.startDelay);
        }
        as2.a(this.waveCounter);
        as2.a(this.isActive);
    }

    public void reset(InputNetStream k2) {
        this.briefingPending = k2.readBoolean();
        this.waveIndex = k2.readInt();
        this.waveCycleCounter = k2.readInt();
        this.waveDifficultyBase = k2.readInt();
        this.waveCycleCount = k2.readInt();
        this.waveSpacingCounter = k2.readInt();
        this.z = k2.readFloat();
        this.A = k2.readFloat();
        this.B = k2.readFloat();
        this.isDefeated = k2.readBoolean();
        int n2 = k2.readInt();
        if (n2 >= 1) {
            int n3 = k2.readInt();
            for (int i2 = 0; i2 < n3; ++i2) {
                AITask a2;
                String string = k2.j();
                boolean bl = k2.readBoolean();
                int n4 = 0;
                int n5 = 0;
                boolean bl2 = false;
                int n6 = 0;
                if (n2 >= 2) {
                    n4 = k2.readInt();
                    n5 = k2.readInt();
                }
                if (n2 >= 3) {
                    bl2 = k2.readBoolean();
                }
                if (n2 >= 4) {
                    n6 = k2.readInt();
                }
                if ((a2 = this.setupWaveOrigin(string)) == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine:readIn: Could not find saved trigger:" + string + " for de/activation");
                    continue;
                }
                a2.isCompleted = bl;
                a2.priority = n4;
                a2.maxAttempts = n5;
                a2.hasStarted = bl2;
                a2.startDelay = n6;
            }
        }
        if (n2 >= 5) {
            this.waveCounter = k2.readInt();
        }
        this.isActive = n2 >= 6 ? k2.readBoolean() : true;
    }

    public void geti(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine:triggerLog", string);
    }

    public boolean reset() {
        return this.isSkirmish;
    }

    public boolean geti() {
        return this.shareFogWithAllies;
    }

    /*
     * WARNING - void declaration
     */
    public void reset(boolean bl) throws MapException {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        String object5;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.infiniteWaves = false;
        this.b = l2.by - 1000;
        this.c = l2.by - 1000;
        this.paint1 = new Paint();
        this.paint1.a(255, 255, 255, 255);
        this.paint1.a(true);
        this.paint1.a(Paint$Align.b);
        this.paint1.a(Typeface.a(Typeface.c, 1));
        l2.a(this.paint1, 24.0f);
        this.paint3 = new Paint();
        this.paint3.a(255, 255, 255, 255);
        this.paint3.a(true);
        this.paint3.a(Paint$Align.b);
        l2.a(this.paint3, 18.0f);
        this.paint4 = new Paint();
        this.paint4.a(255, 255, 255, 255);
        this.paint4.a(true);
        this.paint4.a(Paint$Align.b);
        l2.a(this.paint4, 14.0f);
        this.paint2 = new Paint();
        this.paint2.a(this.paint4);
        l2.a(this.paint2, 18.0f);
        this.briefingPending = true;
        boolean bl2 = false;
        Object object622 = null;
        if (l2.bL.minimapRenderer == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine", "Error: 'triggers' object layer is missing from this map");
            bl2 = true;
        } else {
            object622 = l2.bL.minimapRenderer.a("map_info");
        }
        if (object622 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine", "Error: map_info is missing from this map");
            bl2 = true;
        }
        if (object622 != null && ((MapSpawn)object622).b("type") == null) {
            this.reset("type is missing from map_info");
            bl2 = true;
        }
        if (bl2) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine", "Defaulting to skirmish");
            this.isSkirmish = true;
            this.e = TargetFilter.f;
            return;
        }
        this.aiEnabled = "survival".equalsIgnoreCase(((MapSpawn)object622).b("type"));
        if (this.aiEnabled) {
            this.isActive = "true".equalsIgnoreCase(((MapSpawn)object622).b("survivalWavesClassic"));
            if (this.isActive) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Classic survial waves selected");
            }
            this.initDefaultWaveUnits();
            this.classicMode = false;
            this.waveCounter = l2.bQ.aiDifficulty;
            if (!this.isActive) {
                this.z = 1200.0f;
                if (this.waveCounter < 0) {
                    this.z = 3000.0f;
                }
            } else {
                this.z = 3000.0f;
            }
        }
        if ((object5 = ((MapSpawn)object622).b("survivalWaves")) != null) {
            this.isInEditorMode(object5);
        }
        if ((object4 = ((MapSpawn)object622).b("startWithMusic")) != null) {
            l2.bN.a((String)object4);
        }
        this.isSkirmish = "skirmish".equalsIgnoreCase(((MapSpawn)object622).b("type"));
        if (this.isSkirmish) {
            this.e = TargetFilter.f;
        }
        this.shareFogWithAllies = "true".equalsIgnoreCase(((MapSpawn)object622).b("shareFogWithAllies"));
        Object object6 = ((MapSpawn)object622).b("winCondition");
        if (object6 == null && !this.isSkirmish) {
            throw new MapException("win condition not set");
        }
        if (object6 != null) {
            if (((String)object6).equalsIgnoreCase("none")) {
                this.e = TargetFilter.a;
            } else if (((String)object6).equalsIgnoreCase("allUnitsAndBuildings")) {
                this.e = TargetFilter.b;
            } else if (((String)object6).equalsIgnoreCase("allBuildings")) {
                this.e = TargetFilter.c;
            } else if (((String)object6).equalsIgnoreCase("mainBuilings")) {
                this.e = TargetFilter.d;
            } else if (((String)object6).equalsIgnoreCase("mainBuildings")) {
                this.e = TargetFilter.d;
            } else if (((String)object6).equalsIgnoreCase("commandCenter")) {
                this.e = TargetFilter.e;
            } else if (((String)object6).equalsIgnoreCase("requiredObjectives")) {
                this.e = TargetFilter.g;
            } else {
                throw new MapException("unknown win condition:" + (String)object6);
            }
        }
        if (this.isSkirmish) {
            this.f = this.e;
        }
        this.waveName = ((MapSpawn)object622).a("introText", (LocalizedString) null);
        if (this.waveName != null) {
            this.waveName.isEmpty("\\n", "\n");  // 03 LocalizedString 语义名 (v19.133f6 修正)
            if (this.waveName.isEmpty()) {  // 03 LocalizedString 语义名 (v19.133f6 修正)
                this.waveName = null;
            }
        }
        if (!l2.isNetworkedOrReplay() && !this.isSkirmish) {
            this.d = PlayerState.u(3);
            if (this.d != null) {
                this.d.r = 0;
            }
        }
        if (l2.isNetworkedOrReplay()) {
            // empty if block
        }
        for (Object object622_304 : l2.bL.minimapRenderer.offsetX) {
            if ("team_info".equalsIgnoreCase(((MapSpawn)object622_304).teamName)) {
                Object object7;
                Object object8;
                int n2 = Integer.parseInt(((MapSpawn)object622_304).a("team", "-2"));
                if (n2 == -2) {
                    throw new RuntimeException("cannot find team for:" + ((MapSpawn)object622_304).unitTypeName);
                }
                object4 = PlayerState.u(n2);
                if (object4 == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("No team loaded for:" + n2 + " skipping");
                    continue;
                }
                object6 = ((MapSpawn)object622_304).c("credits");
                if (object6 != null) {
                    ((PlayerState) object4).o = ((Integer)object6).intValue();
                }
                if ((object3 = ((MapSpawn)object622_304).b("basicAI")) != null && l2.P() && object4 instanceof com.corrodinggames.rts.game.ai.AIStrategy) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Using basic AI:" + n2 + " by map request");
                    object8 = (com.corrodinggames.rts.game.ai.AIStrategy)object4;
                    ((com.corrodinggames.rts.game.ai.AIStrategy)object8).aY = true;  // 02b game/a/a L85 (v19.133f6 字段补缺后)
                }
                if ((object8 = ((MapSpawn)object622_304).b("lockAiDifficulty")) != null && object4 instanceof com.corrodinggames.rts.game.ai.AIStrategy) {
                    int n3;
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Locking lockAiDifficulty:" + n2 + " by map request to: " + (String)object8);
                    object7 = (com.corrodinggames.rts.game.ai.AIStrategy)object4;
                    ((com.corrodinggames.rts.game.ai.AIStrategy)object7).difficultyIndex = n3 = Integer.parseInt((String)object8);  // 02b a.x (v19.133f6 语义名修正)
                    ((com.corrodinggames.rts.game.ai.AIStrategy)object7).y = true;
                    l2.bX.updateAllAINames();
                }
                if ((object7 = ((MapSpawn)object622_304).b("disabledAI")) != null && l2.P() && object4 instanceof com.corrodinggames.rts.game.ai.AIStrategy) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Disabling AI:" + n2 + " by map request");
                    com.corrodinggames.rts.game.ai.AIStrategy a2 = (com.corrodinggames.rts.game.ai.AIStrategy)object4;
                    a2.aiDisabled = true;  // 02b a.aX (v19.133f6 语义名修正)
                }
                if ((object2 = ((MapSpawn)object622_304).b("allyGroup")) != null && l2.P()) {
                    int n4;
                    ((PlayerState) object4).r = n4 = Integer.parseInt((String)object2);
                }
                if ((object = ((MapSpawn)object622_304).b("ai")) != null) {
                    ((PlayerState) object4).U = ((String)object).equalsIgnoreCase("survival");
                }
            }
            if ("camera_start".equalsIgnoreCase(((MapSpawn)object622_304).unitTypeName) && !bl) {
                l2.b(((MapSpawn)object622_304).spawnX, ((MapSpawn)object622_304).spawnY);
                this.infiniteWaves = true;
                Integer n5 = ((MapSpawn)object622_304).c("zoomTo");
                if (n5 != null) {
                    l2.cV = n5.intValue();
                }
            }
            if ("attack_point".equalsIgnoreCase(((MapSpawn)object622_304).unitTypeName)) {
                this.D.add(new PointF(((MapSpawn)object622_304).spawnX, ((MapSpawn)object622_304).spawnY));
            }
            if ("rotate".equalsIgnoreCase(((MapSpawn)object622_304).teamName)) {
                String string = ((MapSpawn)object622_304).b("dir");
                float f2 = Float.parseFloat(string);
                java.util.Iterator iterator3 = UnitInstance.bE.iterator();  // 02b am.bE.iterator (v19.133f6 修正)
                while (iterator3.hasNext()) {
                    object3 = (UnitInstance) iterator3.next();
                    if (!(object3 instanceof UnitType) || ((UnitInstance) object3).isFactoryBuilding() || !((MapSpawn)object622_304).a((UnitInstance) object3)) continue;
                    ((UnitInstance) object3).cg = f2;
                }
            }
            if ("fall".equalsIgnoreCase(((MapSpawn)object622_304).teamName)) {
                for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                    if (!(am2 instanceof UnitType) || am2.isFactoryBuilding() || !((MapSpawn)object622_304).a(am2)) continue;
                    am2.onUnitDeployed();
                }
            }
            if ("set_team".equalsIgnoreCase(((MapSpawn)object622_304).teamName)) {
                String string = ((MapSpawn)object622_304).b("team");
                int n6 = Integer.parseInt(string);
                java.util.Iterator iterator4 = UnitInstance.bE.iterator();  // 02b (v19.133f6 修正)
                while (iterator4.hasNext()) {
                    object3 = (UnitInstance) iterator4.next();
                    if (!(object3 instanceof UnitType) || !((MapSpawn)object622_304).a((UnitInstance) object3)) continue;
                    ((UnitInstance) object3).setTeamById(n6);
                }
            }
            if ("ai_allow_full_use".equalsIgnoreCase(((MapSpawn)object622_304).teamName)) {
                for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                    if (!(am3 instanceof UnitType) || !((MapSpawn)object622_304).a(am3)) continue;
                    ((UnitType)am3).bM = false;
                }
            }
            if (!"disable_unit_ai".equalsIgnoreCase(((MapSpawn)object622_304).teamName)) continue;
            for (UnitInstance am4 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                if (!(am4 instanceof UnitType) || !((MapSpawn)object622_304).a(am4)) continue;
                am4.bN = true;
            }
        }
        for (Object object622_396 : UnitInstance.bE) {

            if (((UnitInstance) object622_396).u() || object622_396 instanceof TreeDecoration || ((UnitInstance) object622_396).isFactoryBuilding() || ((UnitInstance) object622_396).cN != null || ((UnitInstance) object622_396).cO != null) continue;
            UnitInstance var5_20 = null;  // 02b am var25 (v19.133f6 修正)
            float f3 = 4900.0f;
            java.util.Iterator iterator5 = UnitInstance.bE.iterator();  // 02b (v19.133f6 修正)
            while (iterator5.hasNext()) {
                float f4;
                object3 = (UnitInstance) iterator5.next();
                if (!((UnitInstance) object3).canFireAtAirTargets() || object622_396 == object3 || ((UnitInstance) object622_396).player != PlayerState.i && !((UnitInstance) object3).player.d(((UnitInstance) object622_396).player) || !((f4 = com.corrodinggames.rts.gameFramework.GameUtils.a(((UnitInstance) object3).eo, ((UnitInstance) object3).ep, ((UnitInstance) object622_396).eo, ((UnitInstance) object622_396).ep)) < f3) || !((UnitInstance) object3).d((UnitInstance) object622_396, true)) continue;
                var5_20 = (UnitInstance) object3;  // 02b L501: var25 = var28 (v19.133f6 修正)
                f3 = f4;
            }
            if (var5_20 == null) continue;
            var5_20.e((UnitInstance) object622_396, true);  // 02b L508: var25.e (v19.133f6 修正)
        }
        this.pendingSpawnQueue.clear();
        for (Object object622_413 : l2.bL.minimapRenderer.offsetX) {
            if ("team_info".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "point".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "camera_pan".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "camera_start".equalsIgnoreCase(((MapSpawn)object622_413).unitTypeName) || "map_info".equalsIgnoreCase(((MapSpawn)object622_413).unitTypeName) || "attack_point".equalsIgnoreCase(((MapSpawn)object622_413).unitTypeName) || "rotate".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "fall".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "set_team".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "ai_allow_full_use".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "disable_unit_ai".equalsIgnoreCase(((MapSpawn)object622_413).teamName) || "info".equalsIgnoreCase(((MapSpawn)object622_413).teamName)) continue;
            if (((MapSpawn)object622_413).customProperties == null) {
                AIWaveSystem.c("Error: Skipping trigger:" + ((MapSpawn)object622_413).unitTypeName + " - no properties found");
                continue;
            }
            AITask a3 = MissionParser.a(this, (MapSpawn)object622_413);
            if (a3 == null) continue;
            this.pendingSpawnQueue.add(a3);
        }
        for (Object object622_423 : this.pendingSpawnQueue) {
            int n7;

            String string = ((AITask) object622_423).b("activateIds");
            if (string == null) {
                String string2 = ((AITask) object622_423).b("alsoActivate");
            }
            if (string != null) {  // 02b L542: var5 (v19.133f6 修正)
                String[] stringArray = string.split(",");
                int n8 = stringArray.length;
                for (n7 = 0; n7 < n8; ++n7) {
                    Object object10 = stringArray[n7];
                    object2 = this.getCurrentWave((String)object10);
                    if (object2 == null) {
                        ((AITask) object622_423).g("linkedTo target not found: " + string);
                        com.corrodinggames.rts.gameFramework.GlobalState.e("Possible IDs:");
                        for (AITask a4 : this.pendingSpawnQueue) {
                            if (a4.taskName == null) continue;
                            com.corrodinggames.rts.gameFramework.GlobalState.e(a4.taskName);
                        }
                        com.corrodinggames.rts.gameFramework.GlobalState.e("--------");
                        continue;
                    }
                    ((AITask) object2).startPosition.a((AITask) object622_423);
                }
            }
            if ((object4 = ((AITask) object622_423).b("whenActivatedIds")) == null) {
                object4 = ((AITask) object622_423).b("activatedBy");
            }
            if (object4 != null) {
                String[] stringArray2 = ((String)object4).split(",");
                n7 = stringArray2.length;
                for (int i2 = 0; i2 < n7; ++i2) {
                    object2 = stringArray2[i2];
                    object = this.getCurrentWave((String)object2);
                    if (object == null) {
                        ((AITask) object622_423).g("linkedFrom target not found: " + (String)object2);
                        continue;
                    }
                    ((AITask) object622_423).startPosition.a((AITask) object);
                }
            }
            if ((object4 = ((AITask) object622_423).b("deactivatedBy")) == null) continue;
            String[] stringArray3 = ((String)object4).split(",");
            n7 = stringArray3.length;
            for (int i3 = 0; i3 < n7; ++i3) {
                object2 = stringArray3[i3];
                object = this.getCurrentWave((String)object2);
                if (object == null) {
                    ((AITask) object622_423).g("deactivatedBy: target not found: " + (String)object2);
                    continue;
                }
                ((AITask) object622_423).targetPosition.a((AITask) object);
            }
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Found " + this.pendingSpawnQueue.size() + " map triggers");
        for (Object object622_479 : this.pendingSpawnQueue) {
            for (String string : ((AITask) object622_479).mapSpawnRef.a()) {
                ((AITask) object622_479).g("Key was not used: " + string);
            }
        }
        this.reset6();
    }

    public void reset6() {
        for (AITask a2 : this.pendingSpawnQueue) {
            if (a2.triggerEvent != MissionEvent.a) continue;
            boolean bl = false;
            for (TaskStatus m2 : this.g) {
                if (m2.a != a2) continue;
                bl = true;
            }
            if (bl) continue;
            TaskStatus m3 = new TaskStatus();
            m3.a = a2;
            this.g.add(m3);
            com.corrodinggames.rts.gameFramework.GlobalState.e("Found objective: " + m3.a());
        }
    }

    public static void c(String string) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.GlobalState.b("MissionEngine", string);
        NetEngine.registerRelayServer(string, false);
    }

    public AITask getCurrentWave(String string) {
        string = string.trim();
        for (AITask a2 : this.pendingSpawnQueue) {
            if (a2.taskName == null || !a2.taskName.equalsIgnoreCase(string)) continue;
            return a2;
        }
        return null;
    }

    public AITask setupWaveOrigin(String string) {
        string = string.trim();
        for (AITask a2 : this.pendingSpawnQueue) {
            if (!a2.taskDescription.equalsIgnoreCase(string)) continue;
            return a2;
        }
        return null;
    }

    public PointF initDefaultWaveUnits(String string) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapLayerDef i2 = l2.bL.minimapRenderer;
        if (i2 != null) {
            for (Object object533 : i2.offsetX) {
                MapSpawn a2 = (MapSpawn)object533;
                if (!"point".equalsIgnoreCase(a2.teamName) || a2.playerRef == null || !a2.playerRef.equalsIgnoreCase(string)) continue;
                this.K.a(a2.spawnX, a2.spawnY);
                return this.K;
            }
        }
        return null;
    }

    public void a(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    }

    public void geti(float f2) {
        Object object;
        Iterator object2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.i) {
            object2 = this.pendingSpawnQueue.iterator();
            while (object2.hasNext()) {
                AITask a2 = (AITask) object2.next();
                if (a2.triggerEvent != MissionEvent.g || !a2.isCompleted) continue;
                float f3 = (float)a2.b() - l2.cw;
                float f4 = (float)a2.c() - l2.cx;
                f3 *= l2.cX;
                f4 *= l2.cX;
                f3 += a2.progressMin;
                f4 += a2.progressMax;
                if (a2.C) {
                    object = com.corrodinggames.rts.gameFramework.effects.HUDManager.s[9];
                    ((com.corrodinggames.rts.gameFramework.effects.HUDElementRenderer)object).setValue(2, f3, f4, a2.B);
                    f4 -= (float)(((com.corrodinggames.rts.gameFramework.effects.HUDElementRenderer)object).c - 2);
                }
                if (a2.displayMessage == null || (object = a2.displayMessage.getLocalizedText()) == null || ((String)object).equals("")) continue;
                l2.bO.a((String)object, f3, f4, a2.B);
            }
        }
        if (this.aiEnabled && !this.boolean12) {
            boolean bl = true;
            boolean bl2 = false;
            this.B = com.corrodinggames.rts.gameFramework.GameUtils.a(this.B, f2);
            if (this.B == 0.0f && this.A != 0.0f) {
                this.A = com.corrodinggames.rts.gameFramework.GameUtils.a(this.A, f2);
                bl2 = true;
            }
            if (bl) {
                if (bl2) {
                    int n2 = (int)(23.0f + this.paint1.k() / 2.0f);
                    l2.bO.a("- Wave " + this.waveIndex + " -", l2.cF / 2.0f, (float)n2, this.paint1);
                    if (this.waveTitleText != null) {
                        l2.bO.a(this.waveTitleText, l2.cF / 2.0f, (float)n2 + this.paint1.k() + 2.0f, this.paint2);
                    }
                } else {
                    int n3 = (int)(23.0f + this.paint3.k() / 2.0f);
                    String string = "Wave " + (this.waveIndex + 1) + " in " + com.corrodinggames.rts.gameFramework.GameUtils.f(String.valueOf((int)((double)this.z / 60.0)), 3);
                    if (this.isDefeated) {
                        string = "Defeat - Wave " + this.waveIndex;
                    }
                    l2.bO.a(string, l2.cF / 2.0f, (float)n3, this.paint3);
                    if (this.waveCompositionText == null) {
                        object = !this.isActive ? this.geti(false) : this.c(false);
                        this.waveCompositionText = ((AISpawnList) object).toString();
                    }
                    object = this.waveCompositionText;
                    l2.bO.a((String)object, l2.cF / 2.0f, (float)n3 + this.paint3.k() + 2.0f, this.paint4);
                }
            }
        }
        AIWaveParser object3;
        if (this.aiEnabled && this.boolean12 && (object3 = this.getCurrentWave()) != null) {  // 02b n/f.java L1105: g var11 = this.d()
            int n4 = object3.currentWaveIndex - l2.by / 1000;
            int n5 = (int)(23.0f + this.paint3.k() / 2.0f);
            String string = "Wave " + (this.waveIndex + 1) + " in " + com.corrodinggames.rts.gameFramework.GameUtils.f(String.valueOf(n4), 3);
            if (this.isDefeated) {
                string = "Defeat - Wave " + this.waveIndex;
            }
            l2.bO.a(string, l2.cF / 2.0f, (float)n5, this.paint3);
            object = object3.waveName;
            if (object != null) {
                l2.bO.a((String)object, l2.cF / 2.0f, (float)n5 + this.paint3.k() + 2.0f, this.paint4);
            }
        }
    }

    public void isInEditorMode(String string) throws MapException {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Loading survival waves");
        this.boolean12 = true;
        String[] stringArray = string.split("\n");
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        for (String string2 : stringArray) {
            ++n3;
            AIWaveParser g2 = new AIWaveParser(this);
            if (!g2.reset(string2)) continue;
            n2 = g2.currentWaveIndex = n2 + (int)g2.waveInterval;
            com.corrodinggames.rts.gameFramework.GlobalState.e("Adding wave " + n3 + " at " + g2.currentWaveIndex);
            this.attackTargets.add(g2);
        }
    }

    public AIWaveParser getCurrentWave() {
        if (this.waveIndex < this.attackTargets.size()) {
            return (AIWaveParser) this.attackTargets.get(this.waveIndex);
        }
        return null;
    }

    public void setupWaveOrigin() {
        this.R = true;
        int n2 = com.corrodinggames.rts.gameFramework.GameUtils.a(0, this.D.size() - 1, this.waveIndex);
        PointF pointF = (PointF)this.D.get(n2);
        this.P.a(pointF);
    }

    public void initDefaultWaveUnits() {
        this.S.clear();
        this.reset(this.S, "scout", 0.7f);
        this.reset(this.S, UnitRegistry.i, 2.1f);
        this.reset(this.S, "mechGun", 1.0f);
        this.reset(this.S, "lightGunship", 2.8f);
        this.reset(this.S, UnitRegistry.j, 1.9f);
        this.reset(this.S, UnitRegistry.l, 0.8f);
        this.reset(this.S, UnitRegistry.w, 1.0f);
        this.reset(this.S, UnitRegistry.x, 0.8f);
        this.reset(this.S, UnitRegistry.n, 0.7f);
        this.reset(this.S, "plasmaTank", 0.6f);
        this.reset(this.S, "missileAirship", 0.4f);
        this.T.clear();
        this.reset(this.T, UnitRegistry.F, 1.0f);
        this.reset(this.T, UnitRegistry.O, 0.5f);
    }

    public void reset(ArrayList arrayList, String string, float f2) {
        this.reset(arrayList, com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(string, true), f2);
    }

    public void reset(ArrayList arrayList, UnitTypeHandle as2, float f2) {
        UnitTypeHandle as3;
        if (as2 == null) {
            as2 = UnitRegistry.i;
        }
        if ((as3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(as2)) != null) {
            as2 = as3;
        }
        SpawnWeight k2 = new SpawnWeight(this);
        k2.a = as2;
        k2.b = f2;
        arrayList.add(k2);
    }

    public void reset(AISpawnList i2, int n2, float f2) {
        int n3;
        if (n2 < 0) {
            n2 = 0;
        }
        if ((n3 = this.S.size()) == 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("error maxTypeNum: " + n3);
            return;
        }
        int n4 = n2 % n3;
        SpawnWeight k2 = (SpawnWeight) this.S.get(n4);
        int n5 = (int)((double)(n2 + 3) * 0.5 * (double)k2.b * (double)f2);
        if ((n5 = (int)com.corrodinggames.rts.gameFramework.GameUtils.e(n5, 0.8f)) < 1) {
            n5 = 1;
        }
        i2.b(k2.a, n5);
    }

    public AISpawnList geti(boolean bl) {
        int n2;
        AISpawnList i2 = new AISpawnList(this);
        boolean bl2 = false;
        if (this.waveCycleCounter > 50 && (this.waveCycleCounter + 1) % 100 == 0) {
            n2 = this.T.size();
            int n3 = this.waveCycleCounter / 100;
            if (n2 == 0) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("error maxTypeNum: " + n2);
            } else {
                int n4 = n3 % n2;
                SpawnWeight k2 = (SpawnWeight) this.T.get(n4);
                int n5 = (int)((float)n3 * k2.b);
                if (n5 < 1) {
                    n5 = 1;
                }
                i2.b(k2.a, n5);
            }
            bl2 = true;
        }
        n2 = 0;
        if (this.waveCounter > 0) {
            n2 = this.waveCounter;
        }
        this.reset(i2, this.waveCycleCounter + n2, 1.0f);
        if (this.waveCycleCounter > 15 && !bl2) {
            this.reset(i2, (int)((float)(this.waveCycleCounter + n2) * 1.1f) - 11, 0.5f);
        }
        if (bl) {
            ++this.waveCycleCounter;
            ++this.waveDifficultyBase;
        }
        return i2;
    }

    public AISpawnList c(boolean bl) {
        AISpawnList i2 = new AISpawnList(this);
        i2.a = false;
        int n2 = this.waveDifficultyBase;
        UnitRegistry ar2 = null;
        if (this.classicMode) {
            ar2 = UnitRegistry.t;
        } else {
            if (this.waveCycleCounter == 0) {
                ++n2;
                ar2 = UnitRegistry.i;
            }
            if (this.waveCycleCounter == 1) {
                ar2 = UnitRegistry.j;
            }
            if (this.waveCycleCounter == 2) {
                ar2 = UnitRegistry.l;
            }
            if (this.waveCycleCounter == 3) {
                n2 = this.waveCycleCount;
                ar2 = UnitRegistry.w;
            }
            if (this.waveCycleCounter == 4) {
                n2 = this.waveCycleCount;
                ar2 = UnitRegistry.x;
                if (this.waveCycleCount % 2 == 0) {
                    ar2 = UnitRegistry.n;
                }
            }
            if (this.waveCycleCounter == 5) {
                i2.a = true;
                n2 = 1;
                ar2 = UnitRegistry.F;
            }
            if (bl) {
                ++this.waveCycleCounter;
                boolean bl2 = false;
                if (this.waveCycleCount == 1) {
                    if (this.waveCycleCounter > 2) {
                        bl2 = true;
                    }
                } else if (this.waveCycleCount < 5) {
                    if (this.waveCycleCounter > 4) {
                        bl2 = true;
                    }
                } else {
                    if (this.waveCycleCounter > 5) {
                        bl2 = true;
                    }
                    if (this.waveCycleCounter > 4 && this.waveCycleCount % 2 == 0) {
                        bl2 = true;
                    }
                }
                if (bl2) {
                    this.waveCycleCounter = 0;
                    this.waveDifficultyBase += 2;
                    ++this.waveCycleCount;
                }
            }
        }
        i2.a(ar2, n2);
        return i2;
    }

    public void reset(float f2) {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n2 = l2.by;
        this.M = com.corrodinggames.rts.gameFramework.GameUtils.a(this.M, f2);
        if (l2.aq && l2.bH) {
            object = null;
            if (l2.bL.minimapRenderer != null) {
                for (Iterator iterator = l2.bL.minimapRenderer.offsetX.iterator(); iterator.hasNext(); ) {
                    MapSpawn a2 = (MapSpawn) iterator.next();  // 02b n/f.java L1029: 显式强转 (b.a)
                    if (!"camera_pan".equalsIgnoreCase(a2.teamName) || this.L != Integer.parseInt(a2.a("index", "-1"))) continue;
                    object = a2;
                }
            }
            if (object == null) {
                this.L = 0;
            } else {
                float f3 = ((MapSpawn)object).spawnX;
                float f4 = ((MapSpawn)object).spawnY;
                if (f3 < l2.cI + 2.0f) {
                    f3 = l2.cI + 2.0f;
                }
                if (f4 < l2.cJ + 2.0f) {
                    f4 = l2.cJ + 2.0f;
                }
                if (f3 > l2.bL.i() - l2.cI - 2.0f) {
                    f3 = l2.bL.i() - l2.cI - 2.0f;
                }
                if (f4 > l2.bL.j() - l2.cJ - 2.0f) {
                    f4 = l2.bL.j() - l2.cJ - 2.0f;
                }
                float f5 = com.corrodinggames.rts.gameFramework.GameUtils.d(l2.cy + l2.cI, l2.cz + l2.cJ, f3, f4);
                float f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(l2.cy + l2.cI, l2.cz + l2.cJ, f3, f4);
                if (this.M == 0.0f && (f6 < 225.0f || l2.ct)) {
                    ++this.L;
                    this.M = 50.0f;
                }
                float f7 = 0.45f * f2;
                l2.cy += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(f5) * f7;
                l2.cz += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(f5) * f7;
                l2.a(l2.cy, l2.cz);
                l2.Q();
            }
        }
        if (this.aiEnabled) {
            if (!this.boolean12) {
                if (!this.isDefeated) {
                    this.z = com.corrodinggames.rts.gameFramework.GameUtils.a(this.z, f2);
                }
                if (this.z == 0.0f && !this.isDefeated) {
                    AISpawnList i2;
                    ++this.waveIndex;
                    this.A = 180.0f;
                    int n3 = com.corrodinggames.rts.gameFramework.GameUtils.a(0, this.D.size() - 1, this.waveIndex);
                    PointF pointF = (PointF)this.D.get(n3);
                    if (!this.isActive) {
                        this.waveTitleText = this.geti(false).toString();
                        i2 = this.geti(true);
                    } else {
                        this.waveTitleText = this.c(false).toString();
                        i2 = this.c(true);
                    }
                    this.z = 1800.0f;
                    if (!this.isActive) {
                        this.z = this.waveCounter > 0 ? (this.z -= (float)(this.waveCounter * 3 * 60)) : (this.z -= (float)(this.waveCounter * 9 * 60));
                    }
                    i2.a(pointF.a, pointF.b);
                    this.waveCompositionText = null;
                }
            } else if (!this.isDefeated) {
                object = this.getCurrentWave();
                if (object != null) {
                    if (((AIWaveParser) object).currentWaveIndex * 1000 < l2.by) {
                        ((AIWaveParser) object).reset();
                        ++this.waveIndex;
                    }
                } else if (!l2.dq && !l2.cb.j()) {
                    l2.bS.G();
                }
            }
        }
        if (this.briefingPending) {
            this.briefingPending = false;
            if (this.waveName != null) {
                l2.a("Briefing", this.waveName);
            }
        }
        if (n2 > this.b + 250) {
            this.b = n2;
            this.reset(n2);
        }
        if (n2 > this.c + 1000) {
            this.c = n2;
            if (this.checkAutoAttackProximity()) {
                this.checkAutoAttackProximity();
                this.checkAutoAttackProximity();
            }
            boolean bl = false;
            boolean bl2 = false;
            if (l2.bs != null) {
                if (l2.bs.j()) {
                    bl = true;
                }
                if (l2.bs.b()) {
                    bl2 = true;
                }
            }
            if (!(l2.dq || l2.dt || l2.cb.j() || bl2)) {
                boolean bl3 = true;
                boolean bl4 = true;
                if (this.e == TargetFilter.a) {
                    bl3 = false;
                } else if (this.e == TargetFilter.g) {
                    for (TaskStatus m2 : this.g) {
                        if (m2.b()) continue;
                        bl3 = false;
                    }
                } else if (l2.bs != null) {
                    for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                        if (!l2.bs.c(am2.player) || !this.reset(this.e, am2)) continue;
                        bl3 = false;
                        break;
                    }
                }
                if (this.f == TargetFilter.a) {
                    bl4 = false;
                } else if (this.f == TargetFilter.g) {
                    bl4 = false;
                } else if (l2.bs != null) {
                    for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                        if (!l2.bs.d(am3.player) || !this.reset(this.f, am3)) continue;
                        bl4 = false;
                        break;
                    }
                }
                if (bl4 && !bl3) {
                    l2.bS.resign();
                }
                if (bl3) {
                    l2.bS.G();
                    if (l2.by > 1500) {
                        ++l2.bQ.numberOfWins;
                        l2.bQ.save();
                    }
                }
            }
            if (this.aiEnabled && !this.isDefeated) {
                boolean bl5 = true;
                for (UnitInstance am4 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                    if (!(am4 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter) && !am4.bP || am4.isDead || am4.u() || am4.player != l2.bs) continue;
                    bl5 = false;
                }
                if (bl5) {
                    this.isDefeated = true;
                    l2.bS.resign();
                }
            }
        }
    }

    public boolean reset(TargetFilter l2, UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            return false;
        }
        if (am2.isDead || am2.isCapturable()) {
            return false;
        }
        if (l2 == TargetFilter.a) {
            return false;
        }
        if (l2 == TargetFilter.b) {
            return true;
        }
        if (l2 == TargetFilter.c) {
            return am2.isFactoryBuilding();
        }
        if (l2 == TargetFilter.e) {
            return am2 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter || am2.bP;
        }
        if (l2 == TargetFilter.d) {
            return am2.isFactoryBuilding() && am2.isNeutralTeam() && !(am2 instanceof com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot) && !(am2 instanceof com.corrodinggames.rts.game.units.commands.ExperimentalLandFactory);
        }
        if (l2 == TargetFilter.f) {
            if (am2.isNeutralTeam()) {
                return true;
            }
            return am2.ak();
        }
        if (l2 == TargetFilter.g) {
            return false;
        }
        return false;
    }

    public void checkAutoAttackProximity(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Map Script: " + string);
    }

    public void reset(AITask a2) {
        if (this.isInEditorMode()) {
            this.checkAutoAttackProximity("Activiated trigger:" + a2.taskId + " (id:" + a2.taskName + ")");
        }
    }

    public boolean isInEditorMode() {
        return waveEnabled && com.corrodinggames.rts.gameFramework.GlobalState.B().bl;
    }

    public static void i(String string) {
        NetEngine.registerRelayServer("Map ScriptError: " + string, false);
    }

    public void reset(int n2) {
        for (AITask a2 : this.pendingSpawnQueue) {
            if (a2.isCompleted && a2.successEventId != -1 && n2 >= a2.priority + a2.successEventId) {
                a2.isCompleted = false;
                a2.useMapSpawn = false;
            }
            if (!a2.isCompleted && !a2.useMapSpawn && a2.d()) {
                a2.useMapSpawn = true;
            }
            if ((a2.isCompleted || a2.useMapSpawn) && a2.targetPosition.b()) {
                a2.isCompleted = false;
                a2.useMapSpawn = false;
                a2.hasStarted = true;
            }
            if (a2.isCompleted && a2.currentAttempt > 0 && n2 >= a2.priority + a2.currentAttempt) {
                a2.useMapSpawn = true;
            }
            if (!a2.useMapSpawn) continue;
            a2.useMapSpawn = false;
            try {
                MissionExecutor.a(this, a2);
            }
            catch (MapException f2) {
                f2.printStackTrace();
                a2.g("Error activating trigger: " + f2.getMessage());
            }
        }
    }

    public boolean checkAutoAttackProximity() {
        boolean bl = false;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance[]amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        block0: for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != PlayerState.i || !(am2 instanceof UnitType) || !am2.isAlive() || am2.o()) continue;
            int n3 = UnitInstance.bE.size();
            for (int i3 = 0; i3 < n3; ++i3) {
                boolean bl2;
                UnitInstance am3 = amArray[i3];
                if (!l2.isNetworkedOrReplay()) {
                    bl2 = am3.player == l2.bs;
                } else {
                    boolean bl3 = bl2 = !am3.player.w;
                    if (am2.isAirUnit()) {
                        bl2 = true;
                    }
                }
                if (am3.player != null && am3.player.k < 0) {
                    bl2 = false;
                }
                if (!bl2 || am3.player == am2.player || !(am3 instanceof UnitType) || am3.i() || !am3.isAlive() || !(com.corrodinggames.rts.gameFramework.GameUtils.a(am3.eo, am3.ep, am2.eo, am2.ep) < 28900.0f)) continue;
                am2.e(am3.player);
                am2.cJ = 60.0f;
                bl = true;
                continue block0;
            }
        }
        return bl;
    }
}
