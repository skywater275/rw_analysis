/*
 * D-3-9 修复 (2026-09-21): `utility.u`=UnitInstanceList 「最小版替代」损伤修复 (F23 整写重建)。
 *
 * 修复依据 (三方对照, 原始输出见 build/_d3_variants/d9_evidence_javap.txt):
 *   ① 原版字节码 (RustedWarfare/game-lib.jar, javap -p ...utility.u) —— 权威基线:
 *        头部: public final u [原版类名] extends java.util.AbstractList
 *              implements java.io.Serializable, java.lang.Cloneable, java.util.RandomAccess
 *        (本注释刻意不把原版类声明写成常见形式: tools/utils/b2_reverse_map_check.py 用
 *         非锚定的首个「类声明关键字 + 名字」匹配提取 03 类名, 会被注释里的原版声明误命中)
 *        字段 3: public static final am[] a / public int b / transient am[] c
 *        方法 35 (+<clinit>)
 *   ② 02b FernFlower 全文: 02b-decompiled/com/corrodinggames/rts/gameFramework/utility/u.java (453 行)
 *   ③ REV-05 画像 (handoff-REV-05.md §4.5 表 A 第 34 项 / §4.5-1 引用广度 TOP1):
 *        ★成员丢失(字段 -2) + ★成员丢失(方法 -30) + ★层级坍缩 (AbstractList→ArrayList);
 *        引用广度 35/34 为 W3 全组最高; D-3-8 唯一性确认仅本族 don't match=0 (根因唯一)
 *
 * 原损伤 (最小版替代): 以 ArrayList 承载底层数组语义 —— a() 返回副本而非底层数组、
 *   b(am) 会被 ArrayList 提升 modCount (原版刻意不提升, 供迭代中追加)、
 *   get/remove/set/add 走 ArrayList 实现而非原版越界检查与 modCount 语义,
 *   与 35 个引用者 (am=UnitInstance / pathfinding / ai 等) 行为不一致 → 回放帧 172 校验和分叉。
 *
 * 修复策略: 整类整写为 02b 直译, 成员名保持原版混淆名 (a/b/c/...), 使反向构建
 *   (tools/fixers/build_reverse_jar.py) 产出的 u.class，其成员集 / 层级 / 修饰符与原版
 *   字节码逐项一致 (D-3-9 验收标准 ③)。
 *
 * 命名与映射: 本类在 03 编译主线称 UnitInstanceList
 *   (class-discoveries.csv L1243: class,...,u,UnitInstanceList;
 *    b2-03-reverse.csv L1566: file_03=.../UnitInstanceList.java → obf_fqn=...utility.u)。
 *   反向构建据此生成 build/reverse-src/.../utility/u.java (即 03 侧不存在 u.java)。
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.game.units.UnitInstance;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class UnitInstanceList
extends AbstractList
implements Serializable, Cloneable, RandomAccess {
    public static final UnitInstance[] a = new UnitInstance[0];
    public int b;
    transient UnitInstance[] c = a;

    public UnitInstanceList() {
        // 02b u L21-23: 构造器只把底层数组指向空数组 a
        this.c = a;
    }

    public UnitInstance[] a() {
        // 02b u L25-27: 返回底层数组本体 —— 调用方会原地追加 (最小版返回副本 = 语义损伤点)
        return this.c;
    }

    public boolean a(UnitInstance var1) {
        // 02b u L29-43: 追加; 满则扩容 (<6 加 12, 否则加 size>>1); ++modCount
        UnitInstance[] var2 = this.c;
        int var3 = this.b;
        if (var3 == var2.length) {
            UnitInstance[] var4 = new UnitInstance[var3 + (var3 < 6 ? 12 : var3 >> 1)];
            System.arraycopy(var2, 0, var4, 0, var3);
            var2 = var4;
            this.c = var4;
        }
        var2[var3] = var1;
        this.b = var3 + 1;
        ++this.modCount;
        return true;
    }

    public final void b(UnitInstance var1) {
        // 02b u L45-57: 追加但 **不** ++modCount (原版刻意保留, 供迭代中追加)
        UnitInstance[] var2 = this.c;
        int var3 = this.b;
        if (var3 == var2.length) {
            UnitInstance[] var4 = new UnitInstance[var3 + (var3 < 6 ? 12 : var3 >> 1)];
            System.arraycopy(var2, 0, var4, 0, var3);
            var2 = var4;
            this.c = var4;
        }
        var2[var3] = var1;
        this.b = var3 + 1;
    }

    public void a(int var1, UnitInstance var2) {
        // 02b u L59-79: 指定位置插入; 越界走 a(int,int) 统一报错; 满则按 c(size) 扩容; ++modCount
        UnitInstance[] var3 = this.c;
        int var4 = this.b;
        if (var1 > var4 || var1 < 0) {
            a(var1, var4);
        }
        if (var4 < var3.length) {
            System.arraycopy(var3, var1, var3, var1 + 1, var4 - var1);
        } else {
            UnitInstance[] var5 = new UnitInstance[c(var4)];
            System.arraycopy(var3, 0, var5, 0, var1);
            System.arraycopy(var3, var1, var5, var1 + 1, var4 - var1);
            var3 = var5;
            this.c = var5;
        }
        var3[var1] = var2;
        this.b = var4 + 1;
        ++this.modCount;
    }

    private static int c(int var0) {
        // 02b u L81-84: 扩容目标容量
        int var1 = var0 < 6 ? 12 : var0 >> 1;
        return var0 + var1;
    }

    public boolean addAll(Collection var1) {
        // 02b u L86-108
        UnitInstance[] var2 = (UnitInstance[])var1.toArray();
        int var3 = var2.length;
        if (var3 == 0) {
            return false;
        }
        UnitInstance[] var4 = this.c;
        int var5 = this.b;
        int var6 = var5 + var3;
        if (var6 > var4.length) {
            int var7 = c(var6 - 1);
            UnitInstance[] var8 = new UnitInstance[var7];
            System.arraycopy(var4, 0, var8, 0, var5);
            var4 = var8;
            this.c = var8;
        }
        System.arraycopy(var2, 0, var4, var5, var3);
        this.b = var6;
        ++this.modCount;
        return true;
    }

    public boolean addAll(int var1, Collection var2) {
        // 02b u L110-139
        int var3 = this.b;
        if (var1 > var3 || var1 < 0) {
            a(var1, var3);
        }
        UnitInstance[] var4 = (UnitInstance[])var2.toArray();
        int var5 = var4.length;
        if (var5 == 0) {
            return false;
        }
        UnitInstance[] var6 = this.c;
        int var7 = var3 + var5;
        if (var7 <= var6.length) {
            System.arraycopy(var6, var1, var6, var1 + var5, var3 - var1);
        } else {
            int var8 = c(var7 - 1);
            UnitInstance[] var9 = new UnitInstance[var8];
            System.arraycopy(var6, 0, var9, 0, var1);
            System.arraycopy(var6, var1, var9, var1 + var5, var3 - var1);
            var6 = var9;
            this.c = var9;
        }
        System.arraycopy(var4, 0, var6, var1, var5);
        this.b = var7;
        ++this.modCount;
        return true;
    }

    static IndexOutOfBoundsException a(int var0, int var1) {
        // 02b u L141-143: 越界统一报错出口 (返回类型为异常, 方法体直接 throw)
        throw new IndexOutOfBoundsException("Invalid index " + var0 + ", size is " + var1);
    }

    public void clear() {
        // 02b u L145-152
        if (this.b != 0) {
            Arrays.fill(this.c, 0, this.b, (Object)null);
            this.b = 0;
            ++this.modCount;
        }
    }

    public Object clone() {
        // 02b u L154-162: 浅拷贝 + 底层数组独立克隆
        try {
            UnitInstanceList var1 = (UnitInstanceList)super.clone();
            var1.c = (UnitInstance[])this.c.clone();
            return var1;
        } catch (CloneNotSupportedException var2) {
            throw new AssertionError();
        }
    }

    public UnitInstance a(int var1) {
        // 02b u L164-170: 按下标取元素 (越界走 a(int,int))
        if (var1 >= this.b) {
            a(var1, this.b);
        }
        return this.c[var1];
    }

    public final int size() {
        // 02b u L172-174
        return this.b;
    }

    public final boolean isEmpty() {
        // 02b u L176-178
        return this.b == 0;
    }

    public boolean contains(Object var1) {
        // 02b u L180-199: null 安全线性查找
        UnitInstance[] var2 = this.c;
        int var3 = this.b;
        int var4;
        if (var1 != null) {
            for (var4 = 0; var4 < var3; ++var4) {
                if (var1.equals(var2[var4])) {
                    return true;
                }
            }
        } else {
            for (var4 = 0; var4 < var3; ++var4) {
                if (var2[var4] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object var1) {
        // 02b u L201-220
        UnitInstance[] var2 = this.c;
        int var3 = this.b;
        int var4;
        if (var1 != null) {
            for (var4 = 0; var4 < var3; ++var4) {
                if (var1.equals(var2[var4])) {
                    return var4;
                }
            }
        } else {
            for (var4 = 0; var4 < var3; ++var4) {
                if (var2[var4] == null) {
                    return var4;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object var1) {
        // 02b u L222-240
        UnitInstance[] var2 = this.c;
        int var3;
        if (var1 != null) {
            for (var3 = this.b - 1; var3 >= 0; --var3) {
                if (var1.equals(var2[var3])) {
                    return var3;
                }
            }
        } else {
            for (var3 = this.b - 1; var3 >= 0; --var3) {
                if (var2[var3] == null) {
                    return var3;
                }
            }
        }
        return -1;
    }

    public UnitInstance b(int var1) {
        // 02b u L242-257: 按下标移除并返回被移除元素; ++modCount
        UnitInstance[] var2 = this.c;
        int var3 = this.b;
        if (var1 >= var3) {
            a(var1, var3);
        }
        UnitInstance var4 = var2[var1];
        int var5 = var1 + 1;
        --var3;
        System.arraycopy(var2, var5, var2, var1, var3 - var1);
        var2[var3] = null;
        this.b = var3;
        ++this.modCount;
        return var4;
    }

    public boolean remove(Object var1) {
        // 02b u L259-291: null 安全线性删除首个匹配; ++modCount
        UnitInstance[] var2 = this.c;
        int var3 = this.b;
        int var4;
        if (var1 != null) {
            for (var4 = 0; var4 < var3; ++var4) {
                if (var1.equals(var2[var4])) {
                    int var5 = var4 + 1;
                    --var3;
                    System.arraycopy(var2, var5, var2, var4, var3 - var4);
                    var2[var3] = null;
                    this.b = var3;
                    ++this.modCount;
                    return true;
                }
            }
        } else {
            for (var4 = 0; var4 < var3; ++var4) {
                if (var2[var4] == null) {
                    int var5 = var4 + 1;
                    --var3;
                    System.arraycopy(var2, var5, var2, var4, var3 - var4);
                    var2[var3] = null;
                    this.b = var3;
                    ++this.modCount;
                    return true;
                }
            }
        }
        return false;
    }

    protected void removeRange(int var1, int var2) {
        // 02b u L293-311: 区间删除 (三条独立越界消息)
        if (var1 != var2) {
            UnitInstance[] var3 = this.c;
            int var4 = this.b;
            if (var1 >= var4) {
                throw new IndexOutOfBoundsException("fromIndex " + var1 + " >= size " + this.b);
            } else if (var2 > var4) {
                throw new IndexOutOfBoundsException("toIndex " + var2 + " > size " + this.b);
            } else if (var1 > var2) {
                throw new IndexOutOfBoundsException("fromIndex " + var1 + " > toIndex " + var2);
            } else {
                System.arraycopy(var3, var2, var3, var1, var4 - var2);
                int var5 = var2 - var1;
                Arrays.fill(var3, var4 - var5, var4, (Object)null);
                this.b = var4 - var5;
                ++this.modCount;
            }
        }
    }

    public UnitInstance b(int var1, UnitInstance var2) {
        // 02b u L313-322: 按下标替换并返回旧值 (原版 **不** 提升 modCount)
        UnitInstance[] var3 = this.c;
        if (var1 >= this.b) {
            a(var1, this.b);
        }
        UnitInstance var4 = var3[var1];
        var3[var1] = var2;
        return var4;
    }

    public Object[] toArray() {
        // 02b u L324-329
        int var1 = this.b;
        Object[] var2 = new Object[var1];
        System.arraycopy(this.c, 0, var2, 0, var1);
        return var2;
    }

    public Object[] toArray(Object[] var1) {
        // 02b u L331-344: 容量不足按运行时组件类型重建
        int var2 = this.b;
        if (var1.length < var2) {
            Object[] var3 = (Object[])Array.newInstance(var1.getClass().getComponentType(), var2);
            var1 = var3;
        }
        System.arraycopy(this.c, 0, var1, 0, var2);
        if (var1.length > var2) {
            var1[var2] = null;
        }
        return var1;
    }

    public Iterator iterator() {
        // 02b u L346-348 原版体为 `return new v(this, (u$1)null);` (v=utility.v 原版迭代器)。
        // ⚠ D-3-9 已裁决的**唯一残留偏差**: 反向构建无法用源码复现该构造调用 —
        //   ① v.class 只由 game-lib.jar 提供 (v.java 在 build-skip.txt 冻结, 不可编译);
        //   ② v(u,u$1) 是 javac 生成的**合成**访问器构造器, 依 JLS「合成成员不参与名字
        //      解析」, javac 只看得见 private v(u) → 实测报错:
        //      "constructor v in class v cannot be applied to given types; required: u"
        //      (见 build/_d3_variants/d9_build_reverse_first_fail.txt)。
        // 故此处退回 AbstractList 默认迭代器: 成员集/签名仍与原版逐项一致 (验收标准 ③ 达标),
        // 元素访问与删除均经本类已还原的 a(int)/b(int)/size() 覆写, modCount 同源;
        // 差异仅剩「CME 判定实现」与「原版 v.remove() 不递减剩余计数」这一处原版自身缺陷路径。
        // 更高保真的两条替代路线 (均需 ARC 授权, 本轮受 build-skip 冻结约束未采用) 见交接单 §七。
        return super.iterator();
    }

    public int hashCode() {
        // 02b u L350-361: List 规范 31 进制
        UnitInstance[] var1 = this.c;
        int var2 = 1;
        int var3 = 0;
        for (int var4 = this.b; var3 < var4; ++var3) {
            UnitInstance var5 = var1[var3];
            var2 = 31 * var2 + (var5 == null ? 0 : var5.hashCode());
        }
        return var2;
    }

    public boolean equals(Object var1) {
        // 02b u L363-406: List 规范相等 (RandomAccess 与非 RandomAccess 双分支)
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof List)) {
            return false;
        } else {
            List var2 = (List)var1;
            int var3 = this.b;
            if (var2.size() != var3) {
                return false;
            } else {
                UnitInstance[] var4 = this.c;
                if (var2 instanceof RandomAccess) {
                    for (int var5 = 0; var5 < var3; ++var5) {
                        UnitInstance var6 = var4[var5];
                        Object var7 = var2.get(var5);
                        if (var6 == null) {
                            if (var7 != null) {
                                return false;
                            }
                        } else if (!var6.equals(var7)) {
                            return false;
                        }
                    }
                } else {
                    Iterator var9 = var2.iterator();
                    for (int var10 = 0; var10 < var3; ++var10) {
                        UnitInstance var11 = var4[var10];
                        Object var8 = var9.next();
                        if (var11 == null) {
                            if (var8 != null) {
                                return false;
                            }
                        } else if (!var11.equals(var8)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
    }

    public Object remove(int var1) {
        // 02b u L408-411: 桥方法 → b(int)
        return this.b(var1);
    }

    public void add(int var1, Object var2) {
        // 02b u L413-416: 桥方法 → a(int, am)
        this.a(var1, (UnitInstance)var2);
    }

    public Object set(int var1, Object var2) {
        // 02b u L418-421: 桥方法 → b(int, am)
        return this.b(var1, (UnitInstance)var2);
    }

    public Object get(int var1) {
        // 02b u L423-426: 桥方法 → a(int)
        return this.a(var1);
    }

    public boolean add(Object var1) {
        // 02b u L428-431: 桥方法 → a(am)
        return this.a((UnitInstance)var1);
    }

    static int a(UnitInstanceList var0) {
        // 02b u L433-436: 合成访问器 (v 读 u.modCount)
        return var0.modCount;
    }

    static int b(UnitInstanceList var0) {
        // 02b u L438-441
        return var0.modCount;
    }

    static int c(UnitInstanceList var0) {
        // 02b u L443-446
        return var0.modCount;
    }

    static int d(UnitInstanceList var0) {
        // 02b u L448-451: ++modCount 并返回新值 (v.remove 用)
        return ++var0.modCount;
    }
}
