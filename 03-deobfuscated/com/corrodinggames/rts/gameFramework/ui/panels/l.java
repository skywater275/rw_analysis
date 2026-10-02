/*
 * v19.117 新建: 02b gameFramework.f.a.l.java 直译 (UI 布局抽象基类)
 * 引用点: InGameUI.uiLayout (02b f/g.java L55: f.a.l s = new f.a.a())
 */
package com.corrodinggames.rts.gameFramework.ui.panels;

import android.graphics.PointF;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.util.Iterator;

public abstract class l {

    static final PointF e = new PointF();
    static final RectF f = new RectF();
    float g;
    float h;
    float i = 50.0f;
    float j = 50.0f;
    float k;
    float l;
    float m;
    float n;
    float o;
    float p;
    float q;
    float r;
    boolean s = false;
    boolean t = false;
    boolean u;
    l v;
    CustomArrayList w = new CustomArrayList();
    m x;
    float y;
    float z;
    static final PointF A = new PointF();
    k B;

    public l() {
        this.x = com.corrodinggames.rts.gameFramework.ui.panels.m.b;  // v19.133f3: float 字段 m 遮蔽枚举修正
    }

    public String a() {
        return this.getClass().getSimpleName();
    }

    public TextureManagerInterface d() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bO;
    }

    public RectF a(RectF rectF, float f2, float f3) {
        rectF.a = 0.0f + f2;
        rectF.b = 0.0f + f3;
        rectF.c = 0.0f + this.i + f2;
        rectF.d = 0.0f + this.j + f3;
        return rectF;
    }

    public RectF a(RectF rectF) {
        A.a = this.g;
        A.b = this.h;
        if (this.v != null) {
            this.v.a(A);
        }
        rectF.a = 0.0f + A.a;
        rectF.b = 0.0f + A.b;
        rectF.c = 0.0f + this.i + A.a;
        rectF.d = 0.0f + this.j + A.b;
        return rectF;
    }

    public void b() {
        Iterator iterator = this.w.iterator();
        while (iterator.hasNext()) {
            l l2 = (l)iterator.next();
            l2.b();
        }
        this.y = 0.0f;
        this.z = 0.0f;
        if (this.x != com.corrodinggames.rts.gameFramework.ui.panels.m.a) {  // v19.133f3
            Iterator iterator2;
            l l3;
            float f2;
            float f3;
            if (this.x == com.corrodinggames.rts.gameFramework.ui.panels.m.b) {  // v19.133f3
                f2 = 0.0f;
                f3 = 0.0f;
                for (iterator2 = this.w.iterator(); iterator2.hasNext(); f3 += l3.h()) {
                    l3 = (l)iterator2.next();
                    if (l3.i > f2) {
                        f2 = l3.g();
                    }
                }
                this.y = f3;
                this.z = f2;
                b(this.z * 0.5f, this.y * 0.5f, this.w);
            } else {
                if (this.x != com.corrodinggames.rts.gameFramework.ui.panels.m.c) {  // v19.133f3
                    throw new RuntimeException("Unknown layout style:" + this.x);
                }
                f2 = 0.0f;
                f3 = 0.0f;
                for (iterator2 = this.w.iterator(); iterator2.hasNext(); f3 += l3.g()) {
                    l3 = (l)iterator2.next();
                    if (l3.j > f2) {
                        f2 = l3.h();
                    }
                }
                this.y = f2;
                this.z = f3;
                a(this.z * 0.5f, this.y * 0.5f, this.w);
            }
        }
        this.s = false;
    }

    public static void a(float f2, float f3, CustomArrayList customArrayList) {
        float f4 = f3;
        float f5 = 0.0f;
        Iterator iterator;
        l l2;
        for (iterator = customArrayList.iterator(); iterator.hasNext(); f5 += l2.g()) {
            l2 = (l)iterator.next();
        }
        float f6 = f2 - f5 * 0.5f;
        iterator = customArrayList.iterator();
        while (iterator.hasNext()) {
            l2 = (l)iterator.next();
            f6 += l2.q;
            l2.g = f6;
            f6 += l2.i;
            f6 += l2.r;
            l2.d(f4);
        }
    }

    public static void b(float f2, float f3, CustomArrayList customArrayList) {
        float f4 = f2;
        float f5 = 0.0f;
        Iterator iterator;
        l l2;
        for (iterator = customArrayList.iterator(); iterator.hasNext(); f5 += l2.h()) {
            l2 = (l)iterator.next();
        }
        float f6 = f3 - f5 * 0.5f;
        iterator = customArrayList.iterator();
        while (iterator.hasNext()) {
            l2 = (l)iterator.next();
            f6 += l2.o;
            l2.h = f6;
            f6 += l2.j;
            f6 += l2.p;
            l2.c(f4);
        }
    }

    public void a(PointF pointF) {
        if (this.v != null) {
            this.v.a(pointF);
        }
        pointF.a += this.g;
        pointF.b += this.h;
    }

    public void a(l l2) {
        l2.b(this);
    }

    public void b(l l2) {
        this.a(l2, false);
    }

    public void a(l l2, boolean bl) {
        if (this.v != l2) {
            if (this.v != null) {
                this.v.w.remove(this);
            }
            this.v = l2;
            if (l2 != null) {
                if (!bl) {
                    l2.w.add(this);
                } else {
                    l2.w.add(0, this);
                }
            }
            this.e();
        }
    }

    public void e() {
        this.s = true;
        if (this.v != null) {
            this.v.e();
        }
    }

    public void b(float f2) {
        if (this.w.size() > 0) {
            Iterator iterator = this.w.iterator();
            while (iterator.hasNext()) {
                l l2 = (l)iterator.next();
                l2.b(f2);
            }
        }
    }

    public void f() {
        A.a = this.g;
        A.b = this.h;
        if (this.v != null) {
            this.v.a(A);
        }
        this.a(A.a, A.b);
        if (this.w.size() > 0) {
            Iterator iterator = this.w.iterator();
            while (iterator.hasNext()) {
                l l2 = (l)iterator.next();
                l2.f();
            }
        }
    }

    public void a(float f2, float f3) {
        if (this.t) {
            TextureManagerInterface y2 = this.d();
            RectF rectF = this.a(new RectF(), f2, f3);
            ChatPanel.m.a(y2, rectF);  // 02b L254: h.m.a(var3, var4)
        }
    }

    public void a(k k2) {
        this.B = k2;
    }

    public boolean a(BuildMenuPanel c2) {
        if (c2.a() && this.c(c2)) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("UI click " + this.a());
            return this.B != null ? this.B.a(c2) : false;
        }
        if (c2.b()) {
            if (this.c(c2)) {
                this.u = true;
            } else {
                this.u = false;
            }
            return false;
        }
        return false;
    }

    public boolean b(BuildMenuPanel c2) {
        if (this.w.size() > 0) {
            Iterator iterator = this.w.iterator();
            while (iterator.hasNext()) {
                l l2 = (l)iterator.next();
                if (l2.b(c2)) {
                    return true;
                }
            }
        }
        return this.a(c2);
    }

    public boolean c(BuildMenuPanel c2) {
        this.a(f);
        return f.b((float)c2.a, (float)c2.b);
    }

    public void c(float f2) {
        this.g = f2 - this.i * 0.5f;
    }

    public void d(float f2) {
        this.h = f2 - this.j * 0.5f;
    }

    public void e(float f2) {
        this.o = f2;
        this.p = f2;
        this.q = f2;
        this.r = f2;
    }

    public void f(float f2) {
        this.k = f2;
        this.l = f2;
        this.m = f2;
        this.n = f2;
    }

    public float g() {
        return this.q + this.i + this.r;
    }

    public float h() {
        return this.o + this.j + this.p;
    }

    public void i() {
        this.b((l)null);
    }
}
