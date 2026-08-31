/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.slots.AbstractCommandSlot;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.SellAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.at;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.buildings.AttackBehavior;
import com.corrodinggames.rts.game.units.buildings.BuildBehavior;
import com.corrodinggames.rts.game.units.buildings.MoveBehavior;
import com.corrodinggames.rts.game.units.buildings.RepairBehavior;
import com.corrodinggames.rts.game.units.buildings.UnitBehavior;
import com.corrodinggames.rts.game.units.commands.CommandCenter;
import com.corrodinggames.rts.game.units.commands.ExperimentalBuilding;
import com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.game.units.commands.ExperimentalLandFactory;
import com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit;
import com.corrodinggames.rts.game.units.commands.ExperimentalWallUnit;
import com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit;
import com.corrodinggames.rts.game.units.commands.FabricatorUnit;
import com.corrodinggames.rts.game.units.commands.PowerGeneratorUnit;
import com.corrodinggames.rts.game.units.commands.RepairBayUnit;
import com.corrodinggames.rts.game.units.commands.Structures;
import com.corrodinggames.rts.game.units.commands.UnitActionHelper;
import com.corrodinggames.rts.game.units.debug.FactoryAction1;
import com.corrodinggames.rts.game.units.debug.FactoryAction2;
import com.corrodinggames.rts.game.units.debug.FactoryAction3;
import com.corrodinggames.rts.game.units.debug.FactoryAction4;
import com.corrodinggames.rts.game.units.debug.FactoryAction5;
import com.corrodinggames.rts.game.units.projectiles.Building;
import com.corrodinggames.rts.game.units.projectiles.ExtractorBuilding;
import com.corrodinggames.rts.game.units.projectiles.FactoryBuilding;
import com.corrodinggames.rts.game.units.projectiles.PowerBuilding;
import com.corrodinggames.rts.game.units.projectiles.RadarBuilding;
import com.corrodinggames.rts.game.units.projectiles.RepairBay;
import com.corrodinggames.rts.game.units.projectiles.ShieldBuilding;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.units.projectiles.StorageBuilding;
import com.corrodinggames.rts.game.units.projectiles.SubBuildingType1;
import com.corrodinggames.rts.game.units.projectiles.SubBuildingType2;
import com.corrodinggames.rts.game.units.projectiles.TurretBuilding;
import com.corrodinggames.rts.game.units.projectiles.WallBuilding;
import com.corrodinggames.rts.game.units.special.MeleeBugUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public enum UnitRegistry
implements UnitTypeHandle {
    a {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalLandFactory(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalLandFactory.K();
            }
        
        
            public int c() {
                return 700;
            }
        
        
            public int c(int n2) {
                if (n2 == 2) {
                    return 1200;
                }
                if (n2 == 3) {
                    return 2500;
                }
                return 0;
            }
        
        
            public boolean p() {
                return true;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public int a(UnitInstance am2) {
                if (am2.isAboveMap()) {
                    return 110;
                }
                return 0;
            }
    },
    b {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalBuilding.b();
            }
        
        
            public int c() {
                return 700;
            }
        
        
            public int c(int n2) {
                if (n2 == 2) {
                    return 2000;
                }
                return 0;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.commands.ExperimentalBuilding.a(arrayList, n2);
            }
    },
    c {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalGroundUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit.b();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public int c(int n2) {
                if (n2 == 2) {
                    return 1500;
                }
                return 0;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit.a(arrayList, n2);
            }
    },
    d {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new Structures(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.Structures.b();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public int c(int n2) {
                if (n2 == 2) {
                    return 2000;
                }
                return 0;
            }
        
        
            public float D() {
                return 7.0E-4f;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.commands.Structures.a(arrayList, n2);
            }
        
        
            public int a(UnitInstance am2) {
                return 110;
            }
    },
    e {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new CommandCenter(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.CommandCenter.getQueueCount();
            }
        
        
            public int c() {
                return 3000;
            }
        
        
            public float D() {
                return 5.0E-4f;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.commands.ExperimentalBuilding.a(arrayList, n2);
            }
    },
    f {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new BuildActionSlot(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot.dB();
            }
        
        
            public int c() {
                return 500;
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    g {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new AbstractCommandSlot(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.slots.AbstractCommandSlot.b();
            }
        
        
            public int c() {
                return 600;
            }
        
        
            public float D() {
                return 8.0E-4f;
            }
    },
    h {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FactoryBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.K();
            }
        
        
            public int c() {
                return 500;
            }
        
        
            public float D() {
                return 0.002f;
            }
        
        
            public boolean l() {
                return true;
            }
        
        
            public boolean m() {
                return true;
            }
        
        
            public boolean n() {
                return true;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.a(arrayList, n2);
                Factory.a((ArrayList)null, n2);
            }
    },
    i {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new StorageBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.StorageBuilding.f();
            }
        
        
            public int c() {
                return 350;
            }
        
        
            public float D() {
                return 0.002f;
            }
    },
    j {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new SubBuildingType2(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.SubBuildingType2.f();
            }
        
        
            public int c() {
                return 450;
            }
        
        
            public float D() {
                return 0.002f;
            }
    },
    k {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExtractorBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.ExtractorBuilding.f();
            }
        
        
            public int c() {
                return 900;
            }
        
        
            public float D() {
                return 0.0014f;
            }
    },
    l {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new RepairBehavior(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.buildings.RepairBehavior.f();
            }
        
        
            public int c() {
                return 650;
            }
        
        
            public float D() {
                return 0.0012f;
            }
    },
    m {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new UnitBehavior(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.buildings.UnitBehavior.f();
            }
        
        
            public int c() {
                return 600;
            }
        
        
            public float D() {
                return 0.002f;
            }
    },
    n {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new BuildBehavior(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.buildings.BuildBehavior.f();
            }
        
        
            public int c() {
                return 800;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public int g() {
                return 2;
            }
    },
    o {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FactoryAction4(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.debug.FactoryAction4.f();
            }
        
        
            public int c() {
                return 900;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    p {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FactoryAction3(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.debug.FactoryAction3.f();
            }
        
        
            public int c() {
                return 300;
            }
        
        
            public float D() {
                return 0.005f;
            }
    },
    q {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ShieldBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.ShieldBuilding.f();
            }
        
        
            public int c() {
                return 800;
            }
        
        
            public float D() {
                return 0.0015f;
            }
    },
    r {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new RepairBay(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.RepairBay.f();
            }
        
        
            public int c() {
                return 1300;
            }
        
        
            public float D() {
                return 0.0013f;
            }
        
        
            public int g() {
                return 2;
            }
    },
    s {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new SpecialBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.L();
            }
        
        
            public int c() {
                return 600;
            }
        
        
            public float D() {
                return 0.003f;
            }
    },
    t {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new MeleeBugUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.special.MeleeBugUnit.f();
            }
        
        
            public int c() {
                return 400;
            }
        
        
            public float D() {
                return 0.004f;
            }
    },
    u {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FactoryAction1(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.debug.FactoryAction1.f();
            }
        
        
            public int c() {
                return 1500;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    v {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new PowerBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.PowerBuilding.f();
            }
        
        
            public int c() {
                return 800;
            }
        
        
            public float D() {
                return 0.003f;
            }
    },
    w {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new TurretBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.TurretBuilding.f();
            }
        
        
            public int c() {
                return 800;
            }
        
        
            public float D() {
                return 0.0011f;
            }
        
        
            public int g() {
                return 2;
            }
    },
    x {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new SubBuildingType1(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.SubBuildingType1.f();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public int g() {
                return 2;
            }
    },
    y {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalSubUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit.b();
            }
        
        
            public int c() {
                return 1200;
            }
        
        
            public int c(int n2) {
                if (n2 == 2) {
                    return 2000;
                }
                return 0;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    z {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new MoveBehavior(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.buildings.MoveBehavior.L();
            }
        
        
            public int c() {
                return 800;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public int g() {
                return 2;
            }
    },
    A {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new TreeDecoration(bl);
            }
        
        
            public void b() {
                TreeDecoration.b();
            }
        
        
            public int c() {
                return 0;
            }
        
        
            public float D() {
                return 0.0025f;
            }
    },
    B {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new RepairBayUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.RepairBayUnit.M();
            }
        
        
            public int c() {
                return 1500;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    C {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new UnitActionHelper(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.UnitActionHelper.b();
            }
        
        
            public int c() {
                return 45000;
            }
        
        
            public float D() {
                return 1.0E-4f;
            }
    },
    D {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalHoverUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit.updateState();
            }
        
        
            public int c() {
                return 15000;
            }
        
        
            public float D() {
                return 7.0E-4f;
            }
    },
    E {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new RadarBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.RadarBuilding.f();
            }
        
        
            public int c() {
                return 3900;
            }
        
        
            public float D() {
                return 9.0E-4f;
            }
        
        
            public int g() {
                return 3;
            }
    },
    F {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new WallBuilding(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.WallBuilding.f();
            }
        
        
            public int c() {
                return 14000;
            }
        
        
            public float D() {
                return 2.0E-4f;
            }
        
        
            public int g() {
                return 3;
            }
    },
    G {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalWaterUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit.b();
            }
        
        
            public int c() {
                return 11000;
            }
        
        
            public float D() {
                return 3.5E-4f;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit.a(arrayList, n2);
            }
    },
    H {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new BuildingBase(bl);
            }
        
        
            public void b() {
                BuildingBase.a_();
            }
        
        
            public int c() {
                return 5000;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    I {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new ExperimentalWallUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.ExperimentalWallUnit.b();
            }
        
        
            public int c() {
                return 100;
            }
        
        
            public float D() {
                return 0.003f;
            }
    },
    J {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new PowerGeneratorUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.PowerGeneratorUnit.initTextures();  // 02b h.K() 闈欐€佺汗鐞嗗姞杞?
            }
        
        
            public int c() {
                return 1500;
            }
        
        
            public int c(int n2) {
                if (n2 == 2) {
                    return 3000;
                }
                if (n2 == 3) {
                    return 5000;
                }
                return 0;
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    K {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FactoryAction5(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.debug.FactoryAction5.b();
            }
        
        
            public int c() {
                return 800;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    L {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FactoryAction2(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.debug.FactoryAction2.t_();
            }
        
        
            public int c() {
                return 500;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public boolean l() {
                return true;
            }
        
        
            public boolean m() {
                return false;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
                com.corrodinggames.rts.game.units.debug.FactoryAction2.a(arrayList, n2);
            }
    },
    M {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new AttackBehavior(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.buildings.AttackBehavior.L();
            }
        
        
            public int c() {
                return 2000;
            }
        
        
            public float D() {
                return 0.001f;
            }
        
        
            public int g() {
                return 2;
            }
    },
    N {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new FabricatorUnit(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.commands.FabricatorUnit.K();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public float D() {
                return 0.001f;
            }
    },
    O {
        public boolean C() {
                return false;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new Building(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.projectiles.Building.f();
            }
        
        
            public int c() {
                return 21000;
            }
        
        
            public float D() {
                return 2.0E-4f;
            }
        
        
            public int g() {
                return 3;
            }
    },
    P {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                BuildActionSlot b2 = new BuildActionSlot(bl);
                ((UnitInstance)b2).a_("artillery");
                return b2;
            }
        
        
            public void b() {
            }
        
        
            public int c() {
                return UnitRegistry.f.c() + com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot.dN.getResourceCost();
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    Q {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                BuildActionSlot b2 = new BuildActionSlot(bl);
                ((UnitInstance)b2).a_("flamethrower");
                return b2;
            }
        
        
            public void b() {
            }
        
        
            public int c() {
                return UnitRegistry.f.c() + com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot.dO.getResourceCost();
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    R {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                TimedBomb u2 = new TimedBomb(bl);
                return u2;
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.u.f();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    S {
        public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                FireDecoration ai2 = new FireDecoration(bl);
                return ai2;
            }
        
        
            public void b() {
                FireDecoration.loadTextures();  // 02b ai 静态 b()
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    T {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                AbstractCommandSlot a2 = new AbstractCommandSlot(bl);
                a2.a(2);
                return a2;
            }
        
        
            public void b() {
            }
        
        
            public int c() {
                return UnitRegistry.f.c() + com.corrodinggames.rts.game.units.commands.slots.AbstractCommandSlot.e.getResourceCost();
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    U {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                BuildActionSlot b2 = new BuildActionSlot(bl);
                ((UnitInstance)b2).a_("gunT2");
                return b2;
            }
        
        
            public void b() {
            }
        
        
            public int c() {
                return UnitRegistry.f.c() + com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot.dL.getResourceCost();
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    V {
        public boolean j() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                BuildActionSlot b2 = new BuildActionSlot(bl);
                ((UnitInstance)b2).a_("gunT3");
                return b2;
            }
        
        
            public void b() {
            }
        
        
            public int c() {
                return UnitRegistry.f.c() + com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot.dL.getResourceCost() + com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot.dM.getResourceCost();
            }
        
        
            public float D() {
                return 3.0E-4f;
            }
    },
    W {
        public boolean A() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                WaterUnit f2 = new WaterUnit(bl);
                return f2;
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.f.d_();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    X {
        public boolean A() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                WaterUnit f2 = new WaterUnit(bl);
                f2.q = true;
                return f2;
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.f.d_();
            }
        
        
            public int c() {
                return 1000;
            }
        
        
            public float D() {
                return 6.0E-4f;
            }
    },
    Y {
        public boolean A() {
                return true;
            }
        
        
            public boolean C() {
                return true;
            }
        
        
            public UnitInstance a(boolean bl) {
                return new Factory(bl);
            }
        
        
            public void b() {
                com.corrodinggames.rts.game.units.h.K();
            }
        
        
            public int c() {
                return 500;
            }
        
        
            public float D() {
                return 0.002f;
            }
        
        
            public boolean l() {
                return true;
            }
        
        
            public boolean m() {
                return false;
            }
        
        
            public boolean n() {
                return false;
            }
        
        
            public void a(ArrayList arrayList, int n2) {
            }
    },
    Z {
        public String e() {
                return this.i();
            }


            public String i() {
                return "marker";
            }


            public boolean A() {
                return true;
            }


            public boolean C() {
                return true;
            }


            public UnitInstance a(boolean bl) {
                AmphibiousUnit t2 = new AmphibiousUnit(bl);
                return t2;
            }


            public void b() {
                AmphibiousUnit.b();
            }


            public int c() {
                return 9999;
            }


            public float D() {
                return 1.0f;
            }
    };

    SellAction aa = new SellAction(this);
    int ab = -1;
    String ac;
    String ad;
    public static ArrayList<UnitTypeHandle> ae;
    at[] af;
    public static boolean ag;
    CustomActionBase ah;  // 02b ar.java L78: custom.d.b ah
    private static final /* synthetic */ UnitRegistry[] ai;

    @Override
    public UnitInstance a() {
        return this.a(false);
    }

    public abstract UnitInstance a(boolean var1);

    public abstract void b();

    @Override
    public abstract int c();

    @Override
    public SellAction d() {
        return this.aa;
    }

    @Override
    public String e() {
        if (this.ab != Localization.c || this.ac == null) {
            this.ab = Localization.c;
            String string = "units." + this.name() + ".name";
            this.ac = Localization.a(string, null, new Object[0]);
            if (this.ac == null) {
                if (com.corrodinggames.rts.gameFramework.GlobalState.B().as() && !this.A()) {
                    throw new RuntimeException("Can't find translation text for: " + string);
                }
                this.ac = this.name();
            }
        }
        return this.ac;
    }

    @Override
    public String f() {
        if (this.ab != Localization.c || this.ad == null) {
            this.ab = Localization.c;
            String string = "units." + this.name() + ".description";
            this.ad = Localization.a(string, null, new Object[0]);
            if (this.ad == null) {
                if (com.corrodinggames.rts.gameFramework.GlobalState.B().as() && !this.A()) {
                    throw new RuntimeException("Can't find translation text for: " + string);
                }
                this.ad = "";
            }
        }
        return this.ad;
    }

    @Override
    public int g() {
        return 1;
    }

    public void a(ArrayList arrayList, int n2) {
    }

    @Override
    public void h() {
        at[] atArray = new at[3];
        for (int i = 1; i <= 3; ++i) {
            at at2 = new at();
            this.a(at2.a, i);
            atArray[i - 1] = at2;
        }
        this.af = atArray;
    }

    @Override
    public ArrayList a(int n2) {
        if (n2 > 3) {
            throw new RuntimeException("Tech level:" + n2 + " greater than maxTechLevel");
        }
        return this.af[n2 - 1].a;
    }

    @Override
    public String i() {
        return this.name();
    }

    @Override
    public boolean j() {
        return false;
    }

    @Override
    public boolean k() {
        return this.j();
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public boolean m() {
        return false;
    }

    @Override
    public boolean n() {
        return false;
    }

    @Override
    public MovementTypeEnum o() {
        UnitInstance am2 = UnitInstance.a(this);
        if (am2 == null) {
            throw new RuntimeException("Shared unit is null for:" + this.name());
        }
        return am2.h();
    }

    @Override
    public boolean p() {
        return false;
    }

    @Override
    public be q() {
        return null;
    }

    public static UnitTypeHandle a(String string) {
        return UnitRegistry.a(string, true);
    }

    public static UnitTypeHandle a(String string, boolean bl) {
        UnitTypeHandle object;
        if (bl && (object = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.m(string)) != null) {
            return object;
        }
        for (UnitRegistry ar2 : UnitRegistry.values()) {
            if (!ar2.name().equalsIgnoreCase(string)) continue;
            return ar2;
        }
        ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.n(string);
        if (l2 != null) {
            return l2;
        }
        return null;
    }

    private static String a(String string, float f2) {
        return UnitRegistry.a(string, f2, "");
    }

    private static String a(String string, float f2, String string2) {
        String string3 = "" + f2;
        if (f2 % 1.0f == 0.0f) {
            string3 = "" + (int)f2;
        }
        return UnitRegistry.a(string, string3, string2);
    }

    private static String a(String string, String string2, String string3) {
        return string + ": " + string2 + string3 + "\n";
    }

    private static int a(UnitType y2) {
        GameAction s2;
        ActionId c2 = y2.getDefaultActionType();
        if (c2 != null && (s2 = y2.a(c2)) != null) {
            return s2.getResourceCost();
        }
        return 0;
    }

    public static void r() {
        String string = "output_all_unit_images/";
        new File(string).mkdirs();
        for (int i2 = 0; i2 < 50; ++i2) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("running outputUnitImages()");
        }
        String[] stringArray = new String[]{"carrier", "experimentalGunship", "experimentalGunshipLanded", "mech_gun", "ladybug", "spiderBot", "wall_v", "crystalResource", "test_tank", "missing", "fogRevealer", "supplyDepot", "tankDestroyer", "megaTank", "crystal_mid", "mechFlyingLanded"};
        for (UnitTypeHandle as2 : ae) {
            UnitTypeHandle as3;
            UnitInstance am2 = UnitInstance.a(as2);
            if (!(am2 instanceof UnitType) || as2.i().startsWith("bug") || (as3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(as2)) != null || as2 instanceof ModUnitRegistry && !((ModUnitRegistry)as2).aF) continue;
            UnitType y2 = (UnitType)am2;
            boolean bl2 = false;
            for (String string2 : stringArray) {
                if (!string2.equals(as2.i())) continue;
                bl2 = true;
            }
            if (bl2) continue;
            String string3 = string + as2.i().replace("/", "_").replace("\\", "_") + ".png";
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            int n2 = 100;
            Texture e2 = l2.bO.b(n2, n2, true);
            TextureManagerInterface y3 = l2.bO.b(e2);
            TextureManagerInterface y4 = l2.bO;
            l2.bO = y3;
            float f2 = 0.0f;
            float f3 = 0.0f;
            PlayerState n3 = PlayerState.u(0);
            boolean bl3 = false;
            boolean bl4 = false;
            int n4 = 1;
            boolean bl5 = true;
            UnitRegistry.a(as2, e2.r, e2.s, f2, f3, n3, 20.0f, n2, bl3, bl4, n4, bl5, null);
            l2.bO = y4;
            y3.p();
            l2.bO.a(e2, new File(string3));
        }
    }

    public static void s() {
        for (int i2 = 0; i2 < 50; ++i2) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("running printForHelp()");
        }
        String[] stringArray = new String[]{"carrier", "experimentalGunship", "experimentalGunshipLanded", "mech_gun", "ladybug", "spiderBot", "wall_v", "crystalResource", "test_tank", "missing", "fogRevealer", "supplyDepot", "tankDestroyer", "megaTank", "crystal_mid", "mechFlyingLanded"};
        String string = "";
        ArrayList<UnitTypeHandle> arrayList = new ArrayList<UnitTypeHandle>();
        arrayList.addAll(ae);
        Collections.sort(arrayList, new Comparator<UnitTypeHandle>() {
            @Override
            public int compare(UnitTypeHandle as2, UnitTypeHandle as3) {
                CustomActionBase b2 = as2.u();  // 02b as.java L22: custom.d.b u()
                CustomActionBase b3 = as3.u();  // 02b as.java L22: custom.d.b u()
                int n = b2.a(b3);
                return n;
            }
        });
        for (UnitTypeHandle as2 : arrayList) {
            int n2;
            UnitTypeHandle as3;
            UnitInstance am2 = UnitInstance.a(as2);
            if (!(am2 instanceof UnitType) || as2.i().startsWith("bug") || (as3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(as2)) != null || as2 instanceof ModUnitRegistry && !((ModUnitRegistry)as2).aF || as2 == Y) continue;
            UnitType y2 = (UnitType)am2;
            boolean bl2 = false;
            String[] stringArray2 = stringArray;
            int n3 = stringArray2.length;
            for (n2 = 0; n2 < n3; ++n2) {
                String string2 = stringArray2[n2];
                if (!string2.equals(as2.i())) continue;
                bl2 = true;
            }
            if (bl2) continue;
            string = string + "\n";
            string = string + "<div class=\"unit\">\n";
            string = string + "<img src=\"unit:" + as2.i() + "\" />\n";
            string = string + "<h4>" + as2.e() + "</h4>\n";
            string = string + "<p>" + as2.f().replace("\n", "<br/>") + "</p>\n";
            string = string + "<pre>";
            string = string + UnitRegistry.a("Price", "$" + as2.c(), "");
            int n4 = UnitRegistry.a(y2);
            if (n4 > 0) {
                string = string + UnitRegistry.a("T2 Upgrade Price", "$" + n4, "");
                UnitType y3 = (UnitType)as2.a();
                y3.a(2);
                if (y3.V() == 2 && (n2 = UnitRegistry.a(y3)) > 0) {
                    string = string + UnitRegistry.a("T3 Upgrade Price", "$" + n2, "");
                }
            }
            string = string + UnitRegistry.a("Hp", y2.cv);
            string = string + UnitRegistry.a("Speed", y2.z());
            string = string + UnitRegistry.a("Turn speed", y2.A());
            string = string + UnitRegistry.a("Mass", y2.bN());
            if (y2.l()) {
                string = string + UnitRegistry.a("Shoot Delay", y2.b(0));
                string = string + UnitRegistry.a("Attack Range", y2.m());
                float f2 = 0.0f;
                float f3 = 0.0f;
                float f4 = 0.0f;
                float f5 = 0.0f;
                int n5 = y2.bl();
                for (int i3 = 0; i3 < n5; ++i3) {
                    int n6 = MovementController.a.a;
                    y2.a((UnitInstance)y2, i3);
                    if (n6 == MovementController.a.a) continue;
                    com.corrodinggames.rts.game.MovementController f6 = (MovementController)MovementController.a.get(MovementController.a.a - 1);
                    if (f6.U > f2) {
                        f2 = f6.U;
                    }
                    if (f6.Y > f3) {
                        f3 = f6.Y;
                    }
                    f4 += f6.U;
                    f5 += f6.Y;
                }
                if (f4 != 0.0f) {
                    String string3 = "";
                    if (f4 != f2) {
                        string3 = " (total:" + f4 + ")";
                    }
                    string = string + UnitRegistry.a("Direct Damage", f2, string3);
                }
                if (f5 != 0.0f) {
                    String string4 = "";
                    if (f5 != f3) {
                        string4 = " (total:" + f5 + ")";
                    }
                    string = string + UnitRegistry.a("Area Damage", f3, string4);
                }
            }
            string = string + "</pre>";
            string = string + "</div>\n";
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e(string);
    }

    public static void t() {
        for (UnitRegistry ar2 : UnitRegistry.values()) {
            ar2.name();
            ar2.e();
            ar2.f();
        }
    }

    public static boolean a(UnitTypeHandle as2, float f2, float f3, float f4, float f5, PlayerState n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance am2 = UnitInstance.a(as2);
        if (am2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("isValidHere: Failed to get unit from type:" + as2);
            return false;
        }
        am2.b(n2);
        am2.eq = f5;
        am2.eo = f2;
        am2.ep = f3;
        if (!am2.isFactoryBuilding()) {
            am2.cg = f4;
            if (am2 instanceof UnitType) {
                UnitType y2 = (UnitType)am2;
                y2.j(f4);
            }
        }
        boolean bl2 = true;
        if (am2 instanceof UnitType) {
            UnitType y3 = (UnitType)am2;
            bl2 = y3.c(n2);
        }
        am2.eq = 0.0f;
        am2.cg = 0.0f;
        return bl2;
    }

    public static void a(UnitTypeHandle as2, float f2, float f3, float f4, float f5, PlayerState n2, float f6, float f7, boolean bl2, boolean bl3, int n3, UnitInstance am2) {
        boolean bl4 = true;
        UnitRegistry.a(as2, f2, f3, f4, f5, n2, f6, f7, bl2, bl3, n3, bl4, am2);
    }

    public static void a(UnitTypeHandle as2, float f2, float f3, float f4, float f5, PlayerState n2, float f6, float f7, boolean bl2, boolean bl3, int n3, boolean bl4, UnitInstance am2) {
        UnitType y2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance am3 = UnitInstance.c(as2);
        boolean bl5 = am3.isFactoryBuilding();
        am3.b(n2);
        if (am3 instanceof UnitType) {
            y2 = (UnitType)am3;
            y2.a(n3);
        }
        am3.eq = f5;
        if (am3.h() == MovementTypeEnum.f || am3.h() == MovementTypeEnum.g || am3.h() == MovementTypeEnum.h) {
            am3.eq += 4.0f;
        }
        if (am3.h() == MovementTypeEnum.d) {
            am3.eq += 10.0f;
        }
        if (!bl5) {
            am3.cg = f4;
            if (am3 instanceof UnitType) {
                y2 = (UnitType)am3;
                y2.j(f4);
            }
        } else {
            am3.cg = -90.0f;
        }
        boolean bl6 = true;
        boolean bl7 = am3.cp;
        am3.cp = true;
        am3.cs = false;
        am3.ct = false;
        if (!bl4) {
            am3.ct = true;
        }
        am3.co = false;
        am3.cq = false;
        am3.cr = false;
        if (bl2 || bl3) {
            am3.cq = bl3;
            am3.cr = bl2;
            bl6 = false;
        } else {
            am3.co = true;
        }
        if (!bl6) {
            am3.eo = f2;
            am3.ep = f3;
        } else {
            am3.eo = l2.cw + f2;
            am3.ep = l2.cx + f3;
        }
        float f8 = am3.cj * 2.0f * 0.8f;
        if (am3 instanceof UnitType) {
            float f9;
            UnitType y3 = (UnitType)am3;
            if (y3.M != null && (f9 = (float)y3.et * y3.cD()) > f8) {
                f8 = f9;
            }
        }
        float f10 = 1.0f;
        if (f8 < f6) {
            f10 = f6 / f8;
        }
        if (f8 > f7) {
            f10 = f7 / f8;
        }
        l2.bO.k();
        if (bl6) {
            // empty if block
        }
        if (f10 != 1.0f) {
            l2.bO.a(f10, f10, f2, f3);
        }
        ag = f10 < 1.0f;
        if (am2 != null) {
            EffectManager f11 = am3.dH;
            am3.dH = am2.dH;
            int n4 = am3.cE;
            am3.cE = am2.cE;
            float f12 = am3.cu;
            am3.cu = am2.cu;
            float f13 = am3.cB;
            am3.cB = am2.cB;
            VariableScope variableScope = am3.bw;
            am3.bw = am2.bw;
            am3.d(0.0f);
            am3.c(0.0f);
            am3.a(0.0f, false);
            am3.dH = f11;
            am3.cE = n4;
            am3.cu = f12;
            am3.cB = f13;
            am3.bw = variableScope;
        } else {
            am3.d(0.0f);
            am3.c(0.0f);
            am3.a(0.0f, false);
        }
        l2.bO.l();
        am3.eq = 0.0f;
        am3.cg = !bl5 ? 0.0f : -90.0f;
        if (am3 instanceof UnitType) {
            UnitType y4 = (UnitType)am3;
            y4.j(0.0f);
            y4.a(1);
        }
        am3.cq = false;
        am3.cr = false;
        am3.cp = bl7;
        am3.co = false;
    }

    @Override
    public int b(int n2) {
        int n3 = this.c();
        if (n2 >= 2) {
            n3 += this.c(2);
        }
        if (n2 >= 3) {
            n3 += this.c(2);
        }
        return n3;
    }

    public int c(int n2) {
        return 0;
    }

    @Override
    public CustomActionBase u() {
        int n2 = this.c();
        if (n2 == 0) {
            return CustomActionBase.a;
        }
        if (this.ah == null || this.ah.a() != n2) {
            this.ah = CustomActionBase.a(n2);
        }
        return this.ah;
    }

    @Override
    public CustomActionBase d(int n2) {
        int n3 = this.b(n2);
        return CustomActionBase.a(n3);
    }

    @Override
    public String v() {
        return this.name();
    }

    @Override
    public boolean w() {
        return false;
    }

    @Override
    public UnitConfig x() {
        return null;
    }

    @Override
    public boolean y() {
        return true;
    }

    @Override
    public Texture z() {
        return null;
    }

    @Override
    public int a(UnitInstance am2) {
        return 0;
    }

    public boolean A() {
        return false;
    }

    @Override
    public CustomActionBase B() {
        return null;
    }

    static {
        ai = new UnitRegistry[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
    }
}


