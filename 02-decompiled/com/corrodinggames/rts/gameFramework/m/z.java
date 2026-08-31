/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.m;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.m.a;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.y;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;

public class z
implements y {
    e a;
    e b;

    @Override
    public y a(e e2) {
        return this.b(e2);
    }

    @Override
    public y b(e e2) {
        return new z();
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public void a(Context context) {
    }

    @Override
    public void b() {
        this.a = new e();
        this.b = new e();
    }

    @Override
    public l d() {
        return null;
    }

    @Override
    public void a(l l2) {
    }

    @Override
    public void a(a a2) {
    }

    @Override
    public e a(int n) {
        return this.a(n, true);
    }

    @Override
    public e a(int n, boolean bl) {
        e e2 = new e();
        return e2;
    }

    @Override
    public e a(InputStream inputStream, boolean bl) {
        e e2 = new e();
        return e2;
    }

    @Override
    public e a(int n, int n2, boolean bl) {
        return this.b(n, n2, bl);
    }

    @Override
    public e b(int n, int n2, boolean bl) {
        e e2 = new e();
        return e2;
    }

    @Override
    public void e() {
    }

    @Override
    public void a(e e2, float f, float f2, float f3, Paint paint) {
    }

    @Override
    public void a(e e2, Rect rect, float f, float f2, float f3, Paint paint) {
    }

    @Override
    public void a(e e2, Rect rect, Rect rect2, Paint paint) {
    }

    @Override
    public void b(e e2, Rect rect, Rect rect2, Paint paint) {
        this.a(e2, rect, rect2, paint);
    }

    @Override
    public void a(Rect rect, Paint paint) {
        this.b(rect, paint);
    }

    @Override
    public void a(boolean bl) {
    }

    @Override
    public void f() {
    }

    @Override
    public void a(e e2, Rect rect, RectF rectF, Paint paint) {
    }

    @Override
    public void a(e e2, float f, float f2, Paint paint) {
    }

    @Override
    public void a(e e2, float f, float f2, Paint paint, float f3, float f4) {
    }

    @Override
    public void b(e e2, float f, float f2, Paint paint) {
    }

    @Override
    public void a(e e2, Rect rect, Paint paint) {
    }

    @Override
    public void a(e e2, Rect rect, Paint paint, int n, int n2, int n3, int n4) {
    }

    @Override
    public void a(e e2, RectF rectF, Paint paint, float f, float f2, int n, int n2) {
    }

    @Override
    public void b(int n) {
    }

    @Override
    public void a(int n, PorterDuff.Mode mode) {
    }

    @Override
    public void a(String string, float f, float f2, Paint paint, Paint paint2, float f3) {
    }

    @Override
    public void a(String string, float f, float f2, Paint paint) {
    }

    @Override
    public void b(Rect rect, Paint paint) {
    }

    @Override
    public void a(RectF rectF, Paint paint) {
    }

    @Override
    public void g() {
    }

    @Override
    public void h() {
    }

    @Override
    public void c(Rect rect, Paint paint) {
    }

    @Override
    public void a(Rect rect) {
    }

    @Override
    public void a(RectF rectF) {
    }

    @Override
    public void a(float f, float f2, float f3, Paint paint) {
    }

    @Override
    public void b(float f, float f2, float f3, Paint paint) {
        this.a(f, f2, f3, paint);
    }

    @Override
    public void a(float[] fArray, int n, int n2, Paint paint) {
    }

    @Override
    public void i() {
    }

    @Override
    public void j() {
    }

    @Override
    public void k() {
        this.i();
    }

    @Override
    public void l() {
        this.j();
    }

    @Override
    public void a(float f, float f2, float f3) {
    }

    @Override
    public void a(float f, float f2) {
    }

    @Override
    public void a(float f, float f2, float f3, float f4) {
    }

    @Override
    public void b(float f, float f2) {
    }

    @Override
    public void a(m m2) {
        m2.a(this);
    }

    @Override
    public void a(float f, float f2, float f3, float f4, Paint paint) {
    }

    @Override
    public int m() {
        return 0;
    }

    @Override
    public int n() {
        return 0;
    }

    @Override
    public void a(int n, int n2) {
    }

    @Override
    public void o() {
    }

    @Override
    public void a(Paint paint) {
    }

    @Override
    public void a(ae ae2) {
    }

    @Override
    public void p() {
    }

    @Override
    public void q() {
    }

    @Override
    public int a(String string, Paint paint) {
        return 1;
    }

    @Override
    public int b(String string, Paint paint) {
        return 1;
    }

    @Override
    public e r() {
        return this.b;
    }

    @Override
    public void a(e e2, File file) {
        throw new RuntimeException("writeImageToFile not yet supported");
    }

    @Override
    public void a(Lock lock) {
    }

    @Override
    public void b(Lock lock) {
    }

    @Override
    public float s() {
        return 1.0f;
    }
}
