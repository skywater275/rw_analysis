/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.DequeList;
import com.corrodinggames.rts.gameFramework.utility.TypedObjectList;
import com.corrodinggames.rts.gameFramework.GameObjectComparator;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public strictfp abstract class GameObject
extends BaseGameObject {
    public long eh;
    public static GameObjectComparator zComparator = new GameObjectComparator();
    public boolean ej = false;  // 02b w.java L11 (boolean1 为幻觉名)
    public boolean ek = false;  // 02b L12 (boolean2 为幻觉名)
    public boolean el;  // 02b L13 (boolean3 为幻觉名)
    public int em = 2;  // 02b L14 (renderLayer 为幻觉名)
    public int en = 0;  // 02b L15 (depthIndex 为幻觉名)
    public float eo;  // 02b w.java L16: eo (worldX 为幻觉名)
    public float ep;  // 02b L17: ep (worldY 为幻觉名)
    public float eq = 0.0f;  // 02b L18: eq (altitude 为幻觉名)
    private static com.corrodinggames.rts.gameFramework.utility.DequeList o = new com.corrodinggames.rts.gameFramework.utility.DequeList();  // 02b w.java L19: utility.o a
    public static final com.corrodinggames.rts.gameFramework.utility.TypedObjectList er = new com.corrodinggames.rts.gameFramework.utility.TypedObjectList("fastGameObjectList");  // 02b L20: utility.s er

    public void S(int n) {
        this.em = n;
    }


    /* 02b w.java L27: 子类写侧链抛 IOException → 父类声明 */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // 02b w.java L27: a(j.as) (PacketBuilder 为幻觉名)
        as2.a(this.ej);
        as2.a(this.ek);
        as2.a(this.em);
    }

    public void a(InputNetStream k2) {
        this.ej = k2.readBoolean();
        this.ek = k2.readBoolean();
        this.em = k2.readInt();
    }

    public GameObject() {
        this(false);
    }

    public GameObject(boolean bl) {
        if (!bl) {
            GlobalState l2 = GlobalState.B();
            if (this.eh != 0L) {
                throw new RuntimeException("ID for GameObject is already set at:" + this.eh);
            }
            this.eh = l2.bX.getNextUnitId();
            if (this.eh == 0L) {
                throw new RuntimeException("Adding object with id:0 class:" + this.getClass().getSimpleName());
            }
            o.a(this);  // 02b L60: a.a((Object)this) (ByteIndexedMap 为幻觉名)
            er.a(this);
        } else {
            this.eh = 0L;
        }
    }

    public abstract void a(float var1);

    public abstract void a(float var1, boolean var2);

    public abstract void d(float var1);  // 02b w.java L72 (getEntityType 为幻觉名)

    public abstract void e(float var1);  // 02b L74 (isValid 为幻觉名)

    public void p(float f2) {  // 02b L76: p(float) 空实现 (getDrawLayer 为幻觉名)
    }

    public abstract boolean c(float var1);  // 02b L78 (getEntityId 为幻觉名)

    public abstract boolean f(float var1);  // 02b L80 (dispose 为幻觉名)

    public boolean a(GlobalState l2) {
        return true;
    }

    public void a() {
        if (this.eh != 0L) {
            o.b(this);  // 02b L88: a.b((Object)this)
            er.remove(this);
        }
        this.ej = true;
    }

    public static com.corrodinggames.rts.gameFramework.GameObject a(long l2, Class clazz, boolean bl) {  // 02b L95: w a(long,Class,boolean)
        if (l2 == -1L) {
            return null;
        }
        com.corrodinggames.rts.gameFramework.GameObject[] wArray = er.a();
        int n2 = er.size();
        for (int j = 0; j < n2; ++j) {
            com.corrodinggames.rts.gameFramework.GameObject w2 = wArray[j];
            if (w2.eh != l2) continue;
            if (clazz.isInstance(w2)) {
                return w2;
            }
            String string = w2.getClass().getName();
            String string2 = clazz.getName();
            string = string.replace("com.corrodinggames.rts.", "");
            string2 = string2.replace("com.corrodinggames.rts.", "");
            com.corrodinggames.rts.gameFramework.network.NetEngine.g("object id:" + l2 + " was found, but with type " + string + " instead of " + string2);
        }
        if (!bl) {
            com.corrodinggames.rts.gameFramework.network.NetEngine.g("getFromId:" + l2 + " was not found");
        }
        return null;
    }

    public static com.corrodinggames.rts.game.units.UnitInstance a(long l2, boolean bl) {  // 02b L125: am a(long,boolean) (MusicController 为幻觉名)
        return (com.corrodinggames.rts.game.units.UnitInstance)a(l2, com.corrodinggames.rts.game.units.UnitInstance.class, bl);
    }

    public static com.corrodinggames.rts.game.units.UnitType b(long l2, boolean bl) {  // 02b L129: y b(long,boolean) (GameSaver 为幻觉名)
        return (com.corrodinggames.rts.game.units.UnitType)a(l2, com.corrodinggames.rts.game.units.UnitType.class, bl);
    }

    public static com.corrodinggames.rts.gameFramework.utility.DequeList dK() {  // 02b L133: utility.o dK() (geto 为幻觉名)
        o.a();
        return o;
    }

    public static void dL() {
        o.a();  // 02b L139: a.a()
        com.corrodinggames.rts.game.units.UnitInstance.bG();  // 02b L140: am.bG() (MusicController 为幻觉名)
    }
}
