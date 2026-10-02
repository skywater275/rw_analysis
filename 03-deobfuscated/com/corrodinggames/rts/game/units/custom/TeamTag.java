/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.util.ArrayList;
import java.util.Locale;

public final class TeamTag {
    final String a;
    public static ArrayList b = new ArrayList();
    public static final TeamTag[] c = new TeamTag[0];
    public static UnitConfig d = new UnitConfig(c);

    private TeamTag(String string) {
        this.a = string;
    }

    public String toString() {
        return this.a;
    }

    public static UnitConfig deserializeTags(String string) {
        return deserializeTags(string, null);
    }

    public static UnitConfig deserializeTags(String string, UnitConfig h2) {
        if (string == null) {
            return h2;
        }
        if (string.trim().equals("")) {
            return h2;
        }
        ArrayList<TeamTag> arrayList = new ArrayList<TeamTag>();
        for (String string2 : string.split(",")) {
            TeamTag g2;
            if ((string2 = string2.trim()).equals("") || arrayList.contains(g2 = TeamTag.intern(string2))) continue;  // 02b: g.c(var0)
            arrayList.add(g2);
        }
        if (arrayList.size() == 0) {
            return h2;
        }
        UnitConfig h3 = new UnitConfig(arrayList.toArray(new TeamTag[0]));
        return h3;
    }

    public static TeamTag parseSingleTag(String string) throws bo {
        if ((string = string.trim()).contains(",")) {
            throw new bo("Expected single tag, got:" + string);
        }
        return TeamTag.intern(string);
    }

    public static TeamTag intern(String string) {
        string = string.trim();
        string = string.toLowerCase(Locale.ROOT);
        for (TeamTag g2 : (java.util.Collection<TeamTag>) (java.util.Collection) b) {
            if (!g2.a.equals(string)) continue;
            return g2;
        }
        TeamTag g3 = new TeamTag(string);
        b.add(g3);
        return g3;
    }

    public static void serializeTags(UnitConfig h2, OutputNetStream as2) {  // v19.113g: 02 g.a(h, j.as) 写方向 (旧误名 deserializeTags)
        if (h2 == null) {
            as2.b((String)null);
        } else if (h2.a.length == 0) {
            as2.b("");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl = true;
            for (TeamTag g2 : h2.a) {
                if (!bl) {
                    stringBuilder.append(",");
                }
                bl = false;
                stringBuilder.append(g2.a);
            }
            as2.b(stringBuilder.toString());
        }
    }

    public static UnitConfig deserializeTags(InputNetStream k2) {  // 02 铁证: g.a(j.k)→h
        String string = k2.j();
        if (string == null) {
            return null;
        }
        UnitConfig h2 = deserializeTags(string, d);
        return h2;
    }

    public static boolean deserializeTags(UnitConfig h2, UnitConfig h3) {
        if (h3 == null) {
            return false;
        }
        TeamTag[] gArray = h2.a;
        int n2 = gArray.length;
        TeamTag[] gArray2 = h3.a;
        int n3 = gArray2.length;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                if (gArray[i] != gArray2[j]) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean deserializeTags(TeamTag g2, UnitConfig h2) {  // 02b custom.g.a(custom.g, custom.h): tag 存在检查
        if (h2 == null) {
            return false;
        }
        TeamTag[] gArray = h2.a;
        int n2 = gArray.length;
        for (int i = 0; i < n2; ++i) {
            if (gArray[i] != g2) continue;
            return true;
        }
        return false;
    }

    public static boolean b(UnitConfig h2, UnitConfig h3) {  // 02b custom.g.b(h,h): 子集检查 (var2 全部 tag 均在 var3 中)
        if (h3 == null) {
            return h2 == null || h2.b() == 0;
        }
        TeamTag[] gArray = h2.a;
        TeamTag[] gArray2 = h3.a;
        for (TeamTag g2 : gArray) {
            boolean bl = false;
            for (TeamTag g3 : gArray2) {
                if (g2 != g3) continue;
                bl = true;
                break;
            }
            if (bl) continue;
            return false;
        }
        return true;
    }

    public static boolean parseSingleTag(UnitConfig h2, UnitConfig h3) {
        if (h3 == null) {
            return h2 == null || h2.b() == 0;  // 02: var0.b()
        }
        TeamTag[] gArray = h2.a;
        int n2 = gArray.length;
        TeamTag[] gArray2 = h3.a;
        int n3 = gArray2.length;
        for (int i = 0; i < n2; ++i) {
            boolean bl = false;
            for (int j = 0; j < n3; ++j) {
                if (gArray[i] != gArray2[j]) continue;
                bl = true;
                break;
            }
            if (bl) continue;
            return false;
        }
        return true;
    }



   // 02b custom.g.a(String, h) 简化: 解析 tag 集 (03 UnitConfig 结构待战役)
   public static UnitConfig a(String var0, UnitConfig var1) {
      return var1;
   }

    public static boolean a(TeamTag g2, UnitConfig h2) {
        // v19.115r logicBooleans 批2 补缺: javap custom.g 静态 a(g,h) 铁证 (FirstUnitReference 等调用点)
        // 02b g.a(g,h): TeamTag 与单位标签匹配 — 简化 TODO
        return false;
    }

        public static boolean a(UnitConfig h2, UnitConfig h3) {  // 02b g.java L123: a(h,h) 标签交集 (v19.133f5 补缺)
        if (h3 == null) {
            return false;
        }
        TeamTag[] teamTags = h2.a;
        TeamTag[] teamTags2 = h3.a;
        for (int i2 = 0; i2 < teamTags.length; ++i2) {
            for (int i3 = 0; i3 < teamTags2.length; ++i3) {
                if (teamTags[i2] == teamTags2[i3]) {
                    return true;
                }
            }
        }
        return false;
    }

public static void a(UnitConfig h2, OutputNetStream as2) {  // 02b custom/g.java L87: a(h,as) 写侧
        if (h2 == null) {
            as2.b((String)null);
        } else if (h2.a.length == 0) {
            as2.b("");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl = true;
            TeamTag[] teamTagArray = h2.a;
            int n = teamTagArray.length;
            for (int i = 0; i < n; ++i) {
                TeamTag g2 = teamTagArray[i];
                if (!bl) {
                    stringBuilder.append(",");
                }
                bl = false;
                stringBuilder.append(g2.a);
            }
            as2.b(stringBuilder.toString());
        }
    }
}
