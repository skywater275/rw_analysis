/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.game.ProjectileType;

import android.graphics.RectF;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapLayerDef;
import com.corrodinggames.rts.game.map.TilesetDef;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import org.w3c.dom.NodeList;
import com.corrodinggames.rts.gameFramework.utility.EmptyArrays;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import org.w3c.dom.Element;

public class MapSpawn {
    public int spawnIndex;
    public String unitTypeName;
    public String playerRef;
    public String teamName;
    public float spawnX;
    public float spawnY;
    public float spawnWidth;
    public float spawnHeight;
    public float spawnInterval;
    private String spawnId;
    public RectF boundsRect;
    public int maxUnitCount = -1;
    public TilesetDef tilesetRef;
    public int startingCredits = -1;
    public Properties customProperties;
    public CustomArrayList spawnedUnitsList = new CustomArrayList();  // v19.113u: 02b b.a.o = utility.m

    static float a(Element element, String string) throws MapException {
        String string2 = element.getAttribute(string);
        try {
            return Float.parseFloat(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw new MapException("Invalid map: Error reading '" + string + "' invalid float: " + string2, numberFormatException);
        }
    }

    public MapSpawn(Element element, MapEngine b2, MapLayerDef i2) throws MapException {
        String string;
        String string2;
        Object object;
        Element element2;
        Element element3;
        this.unitTypeName = element.getAttribute("name");
        if (this.unitTypeName != null) {
            this.playerRef = this.unitTypeName.trim().toLowerCase(Locale.ENGLISH);
        }
        this.teamName = element.getAttribute("type");
        this.spawnX = Float.parseFloat(element.getAttribute("x"));
        this.spawnY = Float.parseFloat(element.getAttribute("y"));
        if (element.hasAttribute("rotation")) {
            this.spawnInterval = Float.parseFloat(element.getAttribute("rotation")) - 90.0f;
        }
        if (!element.getAttribute("width").equals("")) {
            this.spawnWidth = a(element, "width");  // 02b b/a.java L71: 本类静态方法 a(Element,String)
        }
        if (!element.getAttribute("height").equals("")) {
            this.spawnHeight = a(element, "height");  // 02b L75
        }
        if ((element3 = (Element)element.getElementsByTagName("image").item(0)) != null) {
            this.spawnId = element3.getAttribute("source");
        }
        if ((element2 = (Element)element.getElementsByTagName("properties").item(0)) != null && (object = element2.getElementsByTagName("property")) != null) {
            this.customProperties = new Properties();
            for (int i3 = 0; i3 < ((NodeList)object).getLength(); ++i3) {  // 02b L91: NodeList var6
                Element element4 = (Element)((NodeList)object).item(i3);  // 02b L92
                string2 = element4.getAttribute("name");
                string = "";
                string = element4.hasAttribute("value") ? element4.getAttribute("value") : element4.getTextContent();
                this.customProperties.setProperty(string2, string);
            }
        }
        if (element.hasAttribute("gid")) {
            this.maxUnitCount = Integer.parseInt(element.getAttribute("gid"));
            this.tilesetRef = b2.a(this.maxUnitCount);
            if (this.tilesetRef != null) {
                this.tilesetRef.p = true;
                this.tilesetRef.r = true;
                this.startingCredits = this.maxUnitCount - this.tilesetRef.columns;
            } else {
                throw new RuntimeException("Unable to decode base 64 block, could not find tileId:" + this.maxUnitCount);
            }
        }
        object = this.customProperties;
        this.boundsRect = new RectF(this.spawnX, this.spawnY, this.spawnX + this.spawnWidth, this.spawnY + this.spawnHeight);
        b2.a(this.boundsRect);
        this.spawnX = this.boundsRect.a;
        this.spawnY = this.boundsRect.b;
        this.spawnWidth = this.boundsRect.b();
        this.spawnHeight = this.boundsRect.c();
        float f2 = this.boundsRect.d();
        float f3 = this.boundsRect.e();
        string2 = element.getAttribute("type");
        if (!(string2 == null || string2.equals("") || string2.equals("unit") || string2.equals("comment") || i2.layerClass.equalsIgnoreCase("triggers"))) {
            this.d("Triggers should be on triggers layer");  // 02b L129: this.d(String) 方法 (字段误调用)
        }
        if (object != null) {
            string = ((Properties)object).getProperty("unit");
            String string3 = ((Properties)object).getProperty("customUnit");
            if (string != null || string3 != null) {
                UnitInstance am2;
                String string4 = ((Properties)object).getProperty("team");
                PlayerState n2 = null;
                if (string4 == null) {
                    throw new MapException("Unit object team missing for:" + (string != null ? string : string3));
                }
                if ("none".equalsIgnoreCase(string4)) {
                    n2 = com.corrodinggames.rts.game.PlayerState.u(-1);
                } else {
                    int n3;
                    try {
                        n3 = Integer.valueOf(string4);
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw new MapException("Unit object team invalid: " + numberFormatException.getMessage(), numberFormatException);
                    }
                    n2 = com.corrodinggames.rts.game.PlayerState.u(n3);
                    if (n2 == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("map", "Unit object without team:" + string + " (skipping unit)");
                        return;
                    }
                    if (n2.b()) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("map", "Unit team is marked UnitTypeHandle spectator:" + string + " (skipping unit)");
                        return;
                    }
                }
                if (string3 != null) {
                    com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.n(string3);
                    if (l2 == null) {
                        throw new MapException("Could not find custom unit of:" + string3 + " at x:" + this.spawnX + ", y:" + this.spawnY);
                    }
                    UnitTypeHandle as2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(l2);
                    if (as2 != null) {
                        if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                            l2 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
                        } else {
                            com.corrodinggames.rts.gameFramework.GlobalState.b("replacement not Localization custom unit:" + as2.i());
                        }
                    }
                    if ((am2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2)) == null) {
                        throw new RuntimeException("Metadata unit is null for:" + string3);
                    }
                } else {
                    am2 = null;
                    UnitTypeHandle as3 = UnitRegistry.a(string);
                    if (as3 != null) {
                        am2 = as3.a();
                    } else {
                        throw new MapException("Could not find unit type of:" + string + " at x:" + this.spawnX + ", y:" + this.spawnY);
                    }
                }
                am2.eo = f2;
                am2.ep = f3;
                if (!am2.isFactoryBuilding()) {
                    am2.h(this.spawnInterval);
                }
                if (n2 == null) {
                    throw new MapException("team is null:" + string);
                }
                am2.b(n2);
                if (((Properties)object).getProperty("type") != null) {
                    am2.getAnimationFrameRectFull(((Properties)object).getProperty("type"));
                }
                if (((Properties)object).getProperty("randomRotate") != null && !am2.isFactoryBuilding()) {
                    am2.h(com.corrodinggames.rts.gameFramework.GameUtils.a(am2, -180, 180));
                }
                am2.bO = "builder".equalsIgnoreCase(string) || "builder".equalsIgnoreCase(string3);
                am2.bP = "commandCenter".equalsIgnoreCase(string) || "commandCenter".equalsIgnoreCase(string3);
                am2.bM = true;
                am2.n();
                com.corrodinggames.rts.game.PlayerState.c(am2);
                GameObject.dL();  // 02b L218: gameFramework/w=GameObject (F27)
            }
        }
    }

    public boolean a(UnitInstance am2) {
        return this.boundsRect.b((int)am2.eo, (int)am2.ep);
    }

    public void a(String string) {
        if (!this.spawnedUnitsList.contains(string)) {
            this.spawnedUnitsList.add(string);
        }
    }

    public String[] a() {
        if (this.customProperties == null) {
            return EmptyArrays.emptyStringArray;  // 02b x.h=emptyStringArray (字段保序)
        }
        CustomArrayList m2 = new CustomArrayList();
        Enumeration<?> enumeration = this.customProperties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            if (this.spawnedUnitsList.contains(string)) continue;
            m2.add(string);
        }
        return (String[])m2.toArray(EmptyArrays.emptyStringArray);  // 02b x.h
    }

    public String b(String string) {
        this.a(string);  // 02b L254/L259: this.a(String) 标记已生成 (字段误调用)
        if (this.customProperties == null) {
            return null;
        }
        return this.customProperties.getProperty(string);
    }

    public String a(String string, String string2) {
        this.a(string);  // 02b L254/L259: this.a(String) 标记已生成 (字段误调用)
        if (this.customProperties == null) {
            return null;
        }
        return this.customProperties.getProperty(string, string2);
    }

    public Integer c(String string) throws MapException {
        String string2 = this.a(string, (String)null);  // 02b L264/L277: this.a(String,String)
        if (string2 == null) {
            return null;
        }
        try {
            return Integer.parseInt(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw new MapException(string + ": Unexpected integer value:'" + string2 + "'");
        }
    }

    public LocalizedString a(String string, LocalizedString bb2) {
        String string2;
        Object object2;
        String string3 = this.a(string, (String)null);  // 02b L277: this.a(String,String)
        if (string3 == null) {
            return bb2;
        }
        ArrayList<bc> arrayList = new ArrayList<bc>();
        bc bc2 = new bc(null, string3);
        arrayList.add(bc2);
        String string4 = string + "_";
        CustomArrayList m2 = new CustomArrayList();
        for (Object object2_262 : this.customProperties.keySet()) {
            if (object2_262 instanceof String) {
                string2 = (String)object2_262;
                if (!string2.startsWith(string4)) continue;
                m2.add(string2);
                continue;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.b("createLocaleStringFromProperty: Non string:" + object2_262);
        }
        for (Object object2_271 : m2) {
            string2 = ((String)object2_271).substring(string4.length());
            string2 = string2.toLowerCase(Locale.ROOT);
            com.corrodinggames.rts.gameFramework.GlobalState.b("createLocaleStringFromProperty checking: " + (String)object2_271);
            if (string2.length() > 4) continue;
            String string5 = this.b((String)object2_271);  // 02b L309: this.b(String) 属性读取 (字段误调用)
            com.corrodinggames.rts.gameFramework.GlobalState.b("createLocaleStringFromProperty got: " + string5);
            com.corrodinggames.rts.gameFramework.GlobalState.b("createLocaleStringFromProperty code: " + string2);
            bc bc3 = new bc(string2, string5);
            arrayList.add(bc3);
        }
        bc[] bcArray = arrayList.toArray(new bc[0]);
        object2 = new LocalizedString(bcArray);
        ((LocalizedString) object2).getLocalizedText();  // 02b L319: bb.b()=03 getLocalizedText
        com.corrodinggames.rts.gameFramework.GlobalState.b("createLocaleStringFromProperty final: " + ((LocalizedString) object2).getLocalizedText());  // 02b L320
        com.corrodinggames.rts.gameFramework.GlobalState.b("createLocaleStringFromProperty locate: " + com.corrodinggames.rts.gameFramework.steam.Localization.c());
        return (LocalizedString)object2;  // 02b L322: return var15 (bb 类型)
    }

    public void d(String string) {
        NetEngine.g("(Map trigger: " + this.unitTypeName + ", type:" + this.teamName + "): " + string);  // 02b L327: ad=NetEngine 静态日志
    }

    public String b() {
        return "(Map trigger: " + this.unitTypeName + ", type:" + this.teamName + ")";
    }
}
