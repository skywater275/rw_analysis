/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;
import java.io.IOException;
import com.corrodinggames.rts.game.units.ExperimentalLandUnit;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.TilesetDef;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.debug.FactoryAction4;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.Properties;

public strictfp final class MapLayer {
    public TilesetDef tilesetDef;
    public int layerWidth;
    public int layerHeight = -2;
    public short layerOpacity = (short)-1;
    public boolean layerVisible;
    public boolean layerLocked;
    public boolean isObjectLayer;
    public boolean isImageLayer;
    public boolean isTileLayer;
    public byte layerTypeByte;
    public boolean hasProperties;
    public boolean needsRedraw;
    public MapLayer[] tileGrid;
    static final Rect n = new Rect();

    public static boolean a(MapLayer g2, MapLayer g3) {
        if (g2 == g3) {
            return true;
        }
        if (g2 == null) {
            return false;
        }
        if (g3 == null) {
            return false;
        }
        if (g2.tilesetDef != g3.tilesetDef) {
            return false;
        }
        return g2.layerWidth == g3.layerWidth;
    }

    public MapLayer a() {
        MapLayer g2 = new MapLayer();
        g2.tilesetDef = this.tilesetDef;
        g2.layerWidth = this.layerWidth;
        g2.layerVisible = this.layerVisible;
        g2.layerLocked = this.layerLocked;
        g2.isObjectLayer = this.isObjectLayer;
        g2.isImageLayer = this.isImageLayer;
        g2.isTileLayer = this.isTileLayer;
        g2.layerTypeByte = this.layerTypeByte;
        g2.hasProperties = this.hasProperties;
        g2.needsRedraw = this.needsRedraw;
        return g2;
    }

    public static void a(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b(string);  // 02b b/g.java L58: l.b(var0)
        com.corrodinggames.rts.gameFramework.GlobalState.B().a("Missing unit data while loading map: " + string, 1);
        try {
            Thread.sleep(2L);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    /* 02b b/g.java L69: 仅抛 f; 方法体无 IOException 抛 → 去过度 IOException */
    public static MapLayer a(MapEngine b2, TMXMapLoader e2, TilesetDef j2, int n2, short s2, short s3, boolean bl) throws MapException {
        MapLayer[] gArray;
        Properties properties = j2.a(n2);  // 02b L70: j.l 默认 false 简化
        if (properties != null) {
            String string8 = properties.getProperty("showFog");
            if (string8 != null) {
                int n3 = Integer.parseInt(string8);
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                b2.a(s2, s3);
                float f2 = b2.scrollPixelX + b2.selectedTileX;
                float f3 = b2.scrollPixelY + b2.selectedTileY;
                l2.bL.a(f2, f3, n3, l2.bs, false);
                return null;
            }
            String string = properties.getProperty("unit");
            String string2 = properties.getProperty("customUnit");
            if (string != null || string2 != null) {
                String string3 = properties.getProperty("team");
                PlayerState n4 = null;
                if ("none".equalsIgnoreCase(string3)) {
                    n4 = com.corrodinggames.rts.game.PlayerState.u(-1);
                } else {
                    if (string3 == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("map", "warning: unit has no team property:" + string + " at: " + s2 + "," + s3);
                        return null;
                    }
                    n4 = com.corrodinggames.rts.game.PlayerState.u(Integer.valueOf(string3));
                    if (n4 == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("map", "skipping unit without player:" + string + " at: " + s2 + "," + s3 + " team:" + string3);
                        return null;
                    }
                    if (n4.b()) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("map", "Unit team is marked UnitTypeHandle spectator:" + string + " (skipping unit)");
                        return null;
                    }
                }
                UnitInstance am2 = null;
                if (string2 != null) {
                    com.corrodinggames.rts.game.units.custom.ModUnitRegistry l3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.n(string2);
                    if (l3 == null) {
                        String string4 = "Could not find custom unit of:" + string2 + " at x:" + s2 + ", y:" + s3;
                        a(string4);  // 02b L116: 本类静态 a(String)
                        throw new MapException(string4);
                    }
                    UnitTypeHandle as2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(l3);
                    if (as2 != null) {
                        if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                            l3 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
                        } else {
                            com.corrodinggames.rts.gameFramework.GlobalState.b("replacement not a custom unit:" + as2.i());  // 02b L125: var15.i()
                        }
                    }
                    if ((am2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l3)) == null) {
                        String string5 = "Metadata unit is null for:" + string2;
                        a(string5);
                        throw new MapException(string5);
                    }
                } else {
                    UnitTypeHandle as3 = com.corrodinggames.rts.game.units.UnitRegistry.a(string);  // 02b L136: UnitRegistry.a
                    if (as3 != null) {
                        am2 = as3.a();
                    }
                    if (am2 == null && "scoutShip".equalsIgnoreCase(string)) {
                        am2 = null;  // 02b L142: new h.d(false) 侦察船 — Factory$d 未建, 深水区简化 TODO
                    }
                    if (am2 == null) {
                        String string6 = "Could not find unit:" + string + " at: " + s2 + "," + s3;
                        a(string6);
                        throw new MapException(string6);
                    }
                }
                b2.a(s2, s3);
                am2.eo = (float)b2.scrollPixelX + am2.getMapOriginX();
                am2.ep = (float)b2.scrollPixelY + am2.getMapOriginY();
                if (n4 == null) {
                    throw new MapException("team has not been set for:" + string);
                }
                am2.b(n4);  // 02b L159: am.b(n)
                if (properties.getProperty("type") != null) {
                    am2.a_(properties.getProperty("type"));  // 02b L161: am.a_(String)
                }
                if (properties.getProperty("randomRotate") != null) {
                    am2.cg = 0.0f;  // 02b L165: f.a 随机旋转 — 简化 TODO
                }
                am2.bO = "builder".equalsIgnoreCase(string) || "builder".equalsIgnoreCase(string2);
                am2.bP = "commandCenter".equalsIgnoreCase(string) || "commandCenter".equalsIgnoreCase(string2);
                am2.bM = true;
                am2.n();
                com.corrodinggames.rts.game.PlayerState.c(am2);
                com.corrodinggames.rts.gameFramework.GameObject.dL();  // 02b L173
                return null;
            }
            if (e2 != null && e2.l.equals("units")) {  // 02b L177: var1.l
                Log.d("RustedWarfare", "non unit on units layer at:" + s2 + "," + s3);  // 02b L178: Log.d
                return null;
            }
        }
        MapLayer object = new MapLayer();
        ((MapLayer) object).tilesetDef = j2;
        j2.p = true;
        if (e2 != null && !e2.r) {
            j2.r = true;
        }
        if (bl) {
            j2.q = true;
        }
        ((MapLayer) object).layerWidth = n2;
        if (properties != null) {
            if (properties.getProperty("water") != null) {
                ((MapLayer) object).layerVisible = true;
            }
            if (properties.getProperty("water-bridge") != null) {
                ((MapLayer) object).layerLocked = true;
            }
            if (properties.getProperty("lava") != null || properties.getProperty("lava-cliff") != null) {
                ((MapLayer) object).isObjectLayer = true;
                if (properties.getProperty("lava-cliff") != null) {
                    ((MapLayer) object).isImageLayer = true;
                }
            }
            if (properties.getProperty("cliff-soft") != null) {
                ((MapLayer) object).isImageLayer = true;
            }
            if (properties.getProperty("cliff") != null) {
                ((MapLayer) object).isImageLayer = true;
            }
            if (properties.getProperty("large-cliff") != null) {
                ((MapLayer) object).hasProperties = true;
            }
            if (properties.getProperty("trees") != null) {
                ((MapLayer) object).hasProperties = true;
            }
            if (properties.getProperty("res_pool") != null) {
                ((MapLayer) object).isTileLayer = true;
            }
            if (properties.getProperty("tree") != null) {
                // empty if block
            }
            if (properties.getProperty("small-rock") != null) {
                ((MapLayer) object).layerTypeByte = (byte)40;
            }
            if (properties.getProperty("large-rock") != null) {
                ((MapLayer) object).layerTypeByte = (byte)-1;
            }
            if (properties.getProperty("block-land") != null) {
                ((MapLayer) object).layerTypeByte = (byte)-1;
            }
            if (properties.getProperty("block-buildings") != null) {
                ((MapLayer) object).needsRedraw = true;
            }
        }
        int n5 = 0;
        int n6 = 0;
        if (((MapLayer) object).tilesetDef != null) {
            String string11 = ((MapLayer) object).tilesetDef.sourcePath;  // 02b L255: j.c
            if (string11 != null) {
                if (((MapLayer) object).layerWidth == 0 && string11.equals("shallowwater.png")) {
                n5 = 5;
            }
            if (((MapLayer) object).layerWidth == 0 && string11.equals("deepwater.png")) {
                n5 = 2;
            }
            if (((MapLayer) object).layerWidth == 0 && string11.equals("water.png")) {
                n5 = 2;
            }
            if (((MapLayer) object).layerWidth == 0 && string11.equals("longgrass.png")) {
                n5 = 3;
            }
            if (((MapLayer) object).layerWidth == 0 && string11.equals("mountain.png")) {
                n5 = 3;
            }
            if (((MapLayer) object).layerWidth == 7 && string11.equals("stone.png")) {
                n5 = 4;
                n6 = 23;
            }
            if (((MapLayer) object).layerWidth != 0 || string11.equals("lava.png")) {
                // empty if block
            }
            if (((MapLayer) object).layerWidth == 0 && string11.equals("snow.png")) {
                n5 = 2;
            }
            }
        }
        if (properties != null && properties.getProperty("randomTileBy") != null) {
            try {
                n5 = Integer.parseInt(properties.getProperty("randomTileBy"));
            }
            catch (NumberFormatException numberFormatException) {
                throw new MapException("(x:" + s2 + "y:" + s3 + ") - randomTileBy: Unexpected integer value:'" + properties.getProperty("randomTileBy") + "'");
            }
            if (properties.getProperty("randomTileFixedOffset") != null) {
                try {
                    n6 = Integer.parseInt(properties.getProperty("randomTileFixedOffset"));
                }
                catch (NumberFormatException numberFormatException) {
                    throw new MapException("(x:" + s2 + "y:" + s3 + ") - randomTileFixedOffset: Unexpected integer value:'" + properties.getProperty("randomTileBy") + "'");
                }
            }
        }
        if (n5 > 0) {
            gArray = new MapLayer[n5];
            for (int i2 = 0; i2 < n5; ++i2) {
                gArray[i2] = ((MapLayer) object).a();
                gArray[i2].layerWidth += i2 + 1 + n6;
            }
            ((MapLayer) object).tileGrid = gArray;
        }
        return object;
    }

    // 02b b/e.java L354-361: e(b,String,int,int) — 深水区简化 (a(String)/a() 解析链 TODO)
    public MapLayer() {
    }

    public MapLayer(MapEngine b2, String string, int n2, int n3) {
    }

    void g() {
    }

    public void a(TextureManagerInterface y2, RectF rectF, float f2, Paint paint) {
        TilesetDef j2 = this.tilesetDef;
        Rect rect = j2.b(this.layerWidth);  // 02b L324: j.b(int)
        y2.a(j2.tilesetTexture, rect, rectF, paint);  // 02b L325: j.b (Texture 字段)  // 02b L325: j.b (Texture 字段)
    }

    public /* synthetic */ Object clone() {
        return this.a();  // 02b b/g.java L329-331
    }

    public boolean g;  // 02b b/g.java L28
    public boolean h;  // 02b b/g.java L29

}
