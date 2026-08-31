/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.R;
import android.graphics.Point;
import android.graphics.PointF;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.units.UnitActionEnum;
import com.corrodinggames.rts.game.units.Position;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class UnitQueryFilter {
    static Position[] a;
    static int b;
    static int c;
    static com.corrodinggames.rts.game.map.MapLayer d;

    public static void a(UnitActionEnum r2, PointF pointF) {
        com.corrodinggames.rts.game.map.MapLayer g2;
        float f2 = pointF.a;
        float f3 = pointF.b;
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        if (b2 == null) {
            GlobalState.e("setTerrainType called without map loaded");
            return;
        }
        int n2 = (int)(f2 * b2.float1);
        int n3 = (int)(f3 * b2.float2);
        if (!b2.c(n2, n3)) {
            GlobalState.e("setTerrainType out of map range");
            return;
        }
        if (b2.groundLayer == null) {
            GlobalState.e("setTerrainType mainLayer missing");
            return;
        }
        if (!b2.R) {
            if (b2.S) {
                return;
            }
            try {
                if (b2.groundDetailsLayer == null) {
                    b2.groundDetailsLayer = new TMXMapLoader(b2, "grounddetails", b2.tileWidth, b2.mapHeight);  // 02b b/e 构造 (TMXMapLoader=02b b/e)
                    b2.layers.add(b2.groundDetailsLayer);
                }
                if (b2.groundDetails2Layer == null) {
                    b2.groundDetails2Layer = new TMXMapLoader(b2, "grounddetails2", b2.tileWidth, b2.mapHeight);
                    b2.layers.add(b2.groundDetails2Layer);
                }
                b2.groundLayer.w = true;
                b2.groundDetailsLayer.w = true;
                b2.groundDetails2Layer.w = true;
            }
            catch (MapException f4) {
                f4.printStackTrace();
                l2.c("Failed to edit map", f4.getMessage());
                b2.S = true;
                return;
            }
        }
        String string = r2.getRidgeTexturePath();  // 02b r.b()
        try {
            g2 = b2.a(string, 0, 0);
        }
        catch (MapException f5) {
            f5.printStackTrace();
            return;
        }
        if (g2 == null) {
            GlobalState.e("setTerrainType mapTile==null");
            return;
        }
        com.corrodinggames.rts.game.map.MapLayer g3 = b2.groundLayer.a(n2, n3);
        if (b == n2 && c == n3 && com.corrodinggames.rts.game.map.MapLayer.a(d, g2)) {
            return;
        }
        GlobalState.e("setTerrainType changing " + g3.layerWidth + " to " + g2.layerWidth + " at:" + n3 + "," + n3);
        b2.groundLayer.a(n2, n3, g2, false);
        b2.groundDetailsLayer.a(n2, n3, null, false);
        b2.groundDetails2Layer.a(n2, n3, null, false);
        b = n2;
        c = n3;
        d = g2;
        ArrayList<Point> arrayList = new ArrayList<Point>();
        arrayList.add(new Point(n2, n3));
        for (int i2 = 0; i2 <= 4; ++i2) {
            ArrayList arrayList2 = new ArrayList();
            for (Point point : arrayList) {
                UnitQueryFilter.a(r2, g2, point.a, point.b, arrayList2);
            }
            arrayList = arrayList2;
        }
        b2.g();
        l2.bU.a(b2, false);
    }

    public static void a(UnitActionEnum r2, com.corrodinggames.rts.game.map.MapLayer g2, int n2, int n3, ArrayList arrayList) {
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (int i3 = -1; i3 <= 1; ++i3) {
                boolean bl;
                String string;
                int n4 = n2 + i2;
                int n5 = n3 + i3;
                if (!b2.c(n4, n5) || i2 == 0 && i3 == 0 || (string = r2.getTerrainTexturePath()) == null || !(bl = UnitQueryFilter.a(r2, g2, n4, n5, i2, i3, string))) continue;  // 02b r.a()
                arrayList.add(new Point(n4, n5));
            }
        }
    }

    public static void a(int[] nArray, Position s2) {
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            int n2 = nArray[i2] + 128;
            a[n2] = s2;
        }
    }

    public static void a() {
        a = new Position[256];
        int n2 = 1;
        int n3 = 2;
        int n4 = 4;
        int n5 = 8;
        int n6 = 16;
        int n7 = 32;
        int n8 = 64;
        int n9 = -128;
        int n10 = -1;
        UnitQueryFilter.a(UnitQueryFilter.a(n2), new Position(2, 2));
        UnitQueryFilter.a(UnitQueryFilter.a(n3), new Position(0, 2));
        UnitQueryFilter.a(UnitQueryFilter.a(n4), new Position(0, 0));
        UnitQueryFilter.a(UnitQueryFilter.a(n5), new Position(2, 0));
        UnitQueryFilter.a(UnitQueryFilter.a(n6, n2, n3), new Position(1, 2));
        UnitQueryFilter.a(UnitQueryFilter.a(n7, n3, n4), new Position(0, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n8, n5, n4), new Position(1, 0));
        UnitQueryFilter.a(UnitQueryFilter.a(n9, n2, n5), new Position(2, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n6 + n7, n3, n2, n4), new Position(0, 6));
        UnitQueryFilter.a(UnitQueryFilter.a(n7 + n8, n4, n5, n3), new Position(0, 4));
        UnitQueryFilter.a(UnitQueryFilter.a(n8 + n9, n5, n4, n2), new Position(2, 4));
        UnitQueryFilter.a(UnitQueryFilter.a(n9 + n6, n2, n5, n3), new Position(2, 6));
        UnitQueryFilter.a(UnitQueryFilter.a(n2 + n3), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n3 + n4), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n4 + n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n5 + n2), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n6 + n7 + n8, n2, n3, n4, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n7 + n8 + n9, n2, n3, n4, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n8 + n9 + n6, n2, n3, n4, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n9 + n6 + n7, n2, n3, n4, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n6 + n8, n2, n3, n5, n4), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n9 + n7, n2, n3, n5, n4), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n2 + n4), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n3 + n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n6 + n4, n3, n2), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n8 + n3, n4, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n9 + n3, n2, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n7 + n2, n3, n4), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n6 + n4 + n5, n3, n2), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n8 + n3 + n2, n4, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n9 + n3 + n4, n2, n5), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n7 + n2 + n5, n3, n4), new Position(1, 1));
        UnitQueryFilter.a(UnitQueryFilter.a(n10), new Position(1, 1));
    }

    private static int[] a(int n2) {
        return new int[]{n2};
    }

    private static int[] a(int n2, int ... nArray) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(n2);
        if (nArray.length == 1) {
            arrayList.add(n2 + nArray[0]);
        } else if (nArray.length == 2) {
            arrayList.add(n2 + nArray[0]);
            arrayList.add(n2 + nArray[1]);
            arrayList.add(n2 + nArray[0] + nArray[1]);
        } else if (nArray.length == 3) {
            arrayList.add(n2 + nArray[0]);
            arrayList.add(n2 + nArray[1]);
            arrayList.add(n2 + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[1]);
            arrayList.add(n2 + nArray[0] + nArray[2]);
            arrayList.add(n2 + nArray[1] + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[2]);
        } else if (nArray.length == 4) {
            arrayList.add(n2 + nArray[0]);
            arrayList.add(n2 + nArray[1]);
            arrayList.add(n2 + nArray[2]);
            arrayList.add(n2 + nArray[3]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[2] + nArray[3]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[1] + nArray[3]);
            arrayList.add(n2 + nArray[1] + nArray[2] + nArray[3]);
            arrayList.add(n2 + nArray[0] + nArray[1]);
            arrayList.add(n2 + nArray[0] + nArray[2]);
            arrayList.add(n2 + nArray[0] + nArray[3]);
            arrayList.add(n2 + nArray[1] + nArray[2]);
            arrayList.add(n2 + nArray[1] + nArray[3]);
            arrayList.add(n2 + nArray[2] + nArray[3]);
        } else {
            throw new RuntimeException("unhandled:" + nArray.length);
        }
        int[] nArray2 = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            if (arrayList.get(i2) == null) continue;
            nArray2[i2] = (Integer)arrayList.get(i2);
        }
        return nArray2;
    }

    public static boolean a(UnitActionEnum r2, com.corrodinggames.rts.game.map.MapLayer g2, int n2, int n3, int n4, int n5, String string) {
        com.corrodinggames.rts.game.map.MapLayer g3;
        Position s2;
        boolean bl = false;
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        byte by = UnitQueryFilter.b(r2, g2, n2, n3);
        if (a == null) {
            UnitQueryFilter.a();
        }
        if ((s2 = a[by + 128]) == null) {
            return false;
        }
        if (s2.a == 1 && s2.b == 1) {
            b2.groundLayer.a(n2, n3, g2, false);
            g3 = null;
            bl = true;
        } else {
            try {
                g3 = b2.a(string, s2.a, s2.b);
            }
            catch (MapException f2) {
                f2.printStackTrace();
                return false;
            }
        }
        UnitQueryFilter.a(r2, g2, g3, n2, n3);
        return bl;
    }

    public static void a(UnitActionEnum r2, com.corrodinggames.rts.game.map.MapLayer g2, com.corrodinggames.rts.game.map.MapLayer g3, int n2, int n3) {
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        com.corrodinggames.rts.game.map.MapLayer g4 = b2.groundLayer.a(n2, n3);
        com.corrodinggames.rts.game.map.MapLayer g5 = b2.groundDetailsLayer.a(n2, n3);
        com.corrodinggames.rts.game.map.MapLayer g6 = b2.groundDetails2Layer.a(n2, n3);
        UnitActionEnum r3 = UnitQueryFilter.a(g5);
        UnitActionEnum r4 = UnitQueryFilter.a(g6);
        if (r3 == r2) {
            b2.groundDetailsLayer.a(n2, n3, null, false);
            g5 = null;
            r3 = null;
        }
        if (r4 == r2) {
            b2.groundDetails2Layer.a(n2, n3, null, false);
            g6 = null;
            r4 = null;
        }
        if (g5 == null && g6 != null) {
            b2.groundDetailsLayer.a(n2, n3, g6, false);
            b2.groundDetails2Layer.a(n2, n3, null, false);
            g5 = g6;
            g6 = null;
            r3 = r4;
            r4 = null;
        }
        if (com.corrodinggames.rts.game.map.MapLayer.a(g4, g2)) {
            return;
        }
        if (g3 == null) {
            return;
        }
        if (g5 != null) {
            if (g6 != null) {
                b2.groundDetailsLayer.a(n2, n3, g6, false);
            }
            b2.groundDetails2Layer.a(n2, n3, g3, false);
        } else {
            b2.groundDetailsLayer.a(n2, n3, g3, false);
        }
    }

    public static UnitActionEnum a(com.corrodinggames.rts.game.map.MapLayer g2) {
        if (g2 == null) {
            return null;
        }
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        for (UnitActionEnum r2 : UnitActionEnum.values()) {
            String string = g2.tilesetDef.tilesetName;  // 02b b/g.a.a (j.a=tilesetName)
            if (string != null && string.equals(r2.getRidgeTexturePath())) {
                return r2;
            }
            if (string == null || !string.equals(r2.getTerrainTexturePath())) continue;
            return r2;
        }
        return null;
    }

    public static boolean a(UnitActionEnum r2, com.corrodinggames.rts.game.map.MapLayer g2, int n2, int n3) {
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        if (!b2.c(n2, n3)) {
            return false;
        }
        com.corrodinggames.rts.game.map.MapLayer g3 = b2.groundLayer.a(n2, n3);
        return com.corrodinggames.rts.game.map.MapLayer.a(g3, g2);
    }

    public static byte b(UnitActionEnum r2, com.corrodinggames.rts.game.map.MapLayer g2, int n2, int n3) {
        byte by = 0;
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        int n4 = b2.tileWidth;
        int n5 = b2.mapHeight;
        if (n2 >= 1) {
            if (UnitQueryFilter.a(r2, g2, n2 - 1, n3)) {
                by = (byte)(by - 128);
            }
            if (n3 >= 1 && UnitQueryFilter.a(r2, g2, n2 - 1, n3 - 1)) {
                by = (byte)(by + 1);
            }
            if (n3 < n5 - 1 && UnitQueryFilter.a(r2, g2, n2 - 1, n3 + 1)) {
                by = (byte)(by + 8);
            }
        }
        if (n3 >= 1) {
            if (UnitQueryFilter.a(r2, g2, n2, n3 - 1)) {
                by = (byte)(by + 16);
            }
            if (n2 < n4 - 1 && UnitQueryFilter.a(r2, g2, n2 + 1, n3 - 1)) {
                by = (byte)(by + 2);
            }
        }
        if (n2 < n4 - 1 && UnitQueryFilter.a(r2, g2, n2 + 1, n3)) {
            by = (byte)(by + 32);
        }
        if (n3 < n5 - 1) {
            if (UnitQueryFilter.a(r2, g2, n2, n3 + 1)) {
                by = (byte)(by + 64);
            }
            if (n2 < n4 - 1 && UnitQueryFilter.a(r2, g2, n2 + 1, n3 + 1)) {
                by = (byte)(by + 4);
            }
        }
        return by;
    }
}
