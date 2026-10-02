/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public final class ObjectPool {
    public final CustomArrayList freeList = new CustomArrayList();
    public int b;
    public Class c;

    public ObjectPool(Class clazz) {
        this.c = clazz;
    }

    public final Rect a(Rect rect) {
        if (this.b >= this.freeList.a) {
            this.freeList.add(new Rect());
        }
        Rect rect2 = (Rect)this.freeList.a(this.b);
        rect2.b = rect.b;
        rect2.d = rect.d;
        rect2.a = rect.a;
        rect2.c = rect.c;
        ++this.b;
        return rect2;
    }

    public final RectF a(RectF rectF) {
        if (this.b >= this.freeList.a) {
            this.freeList.add(new RectF());
        }
        RectF rectF2 = (RectF)this.freeList.a(this.b);
        rectF2.b = rectF.b;
        rectF2.d = rectF.d;
        rectF2.a = rectF.a;
        rectF2.c = rectF.c;
        ++this.b;
        return rectF2;
    }

    public final Paint a(Paint paint) {
        if (paint == null) {
            return null;
        }
        if (this.b >= this.freeList.a) {
            this.freeList.add(new Paint());
        }
        Paint paint2 = (Paint)this.freeList.a(this.b);
        paint2.a(paint);
        ++this.b;
        return paint2;
    }
}
