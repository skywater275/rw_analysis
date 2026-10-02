/*
 * Decompiled with CFR 0.152.
 * 02 原稿: java/audio/a/e.java (v19.109 批次17: long→int 恢复 + 方法名按 javap 重排)
 * javap 验证: 全部 19 方法参数/字段为 int; 8参方法键写入 n; 探测用 h/i → f/g
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.java.audio.backend.j;
import com.corrodinggames.rts.java.audio.backend.m;
import java.util.Iterator;

public class AudioManager
implements Iterable {
    public int sampleRate;
    long[] b;  // 02b a/i.java: long[] b (long 键)
    Object[] c;
    int d;
    int e;
    Object f;
    boolean g;
    private float volumeLevel;
    private int bufferSize;
    private int channelCount;
    private int audioFormat;
    private int audioSessionId;
    private int streamType;
    private j audioPlayerA;
    private j audioPlayerB;

    public AudioManager() {
        this(51, 0.8f);
    }

    public AudioManager(int n, float f) {
        if (n < 0) {
            throw new IllegalArgumentException("initialCapacity must be >= 0: " + n);
        }
        if ((n = m.b((int)Math.ceil((float)n / f))) > 0x40000000) {
            throw new IllegalArgumentException("initialCapacity is too large: " + n);
        }
        this.d = n;
        if (f <= 0.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0: " + f);
        }
        this.volumeLevel = f;
        this.audioFormat = (int)((float)this.d * f);
        this.channelCount = this.d - 1;
        this.bufferSize = 63 - Integer.numberOfTrailingZeros(this.d);
        this.audioSessionId = Math.max(3, (int)Math.ceil(Math.log(this.d)) * 2);
        this.streamType = Math.max(Math.min(this.d, 8), (int)Math.sqrt(this.d) / 8);
        this.b = new long[this.d + this.audioSessionId];  // 02b L48: new long[]
        this.c = new Object[this.b.length];
    }

    public Object a(long l, Object object) {  // 02b a/i L55 (v19.133f4 long 键修正)
        if (l == 0) {
            Object object2 = this.f;
            this.f = object;
            if (!this.g) {
                this.g = true;
                ++this.sampleRate;
            }
            return object2;
        }
        long[] lArray = this.b;
        int n2 = (int)(l & (long)this.channelCount);  // 02b L67: (int)(var1 & (long)j)
        long l2 = lArray[n2];
        if (l2 == l) {
            Object object3 = this.c[n2];
            this.c[n2] = object;
            return object3;
        }
        int n3 = this.h(l);
        long l3 = lArray[n3];
        if (l3 == l) {
            Object object4 = this.c[n3];
            this.c[n3] = object;
            return object4;
        }
        int n4 = this.i(l);
        long l4 = lArray[n4];
        if (l4 == l) {
            Object object5 = this.c[n4];
            this.c[n4] = object;
            return object5;
        }
        int n5 = this.d + this.e;  // 02b L88/L90: var15 = var14 + this.e (非 n+this.e)
        for (int n6 = this.d; n6 < n5; ++n6) {
            if (lArray[n6] != l) continue;
            Object object6 = this.c[n6];
            this.c[n6] = object;
            return object6;
        }
        if (l2 == 0) {
            lArray[n2] = audioSessionId;
            this.c[n2] = object;
            if (this.sampleRate++ >= this.audioFormat) {
                this.g(this.d << 1);
            }
            return null;
        }
        if (l3 == 0) {
            lArray[n3] = audioSessionId;
            this.c[n3] = object;
            if (this.sampleRate++ >= this.audioFormat) {
                this.g(this.d << 1);
            }
            return null;
        }
        if (l4 == 0) {
            lArray[n4] = audioSessionId;
            this.c[n4] = object;
            if (this.sampleRate++ >= this.audioFormat) {
                this.g(this.d << 1);
            }
            return null;
        }
        this.a(l, object, n2, l2, n3, l3, n4, l4);
        return null;
    }

    private void b(long l, Object object) {  // 02b L132: b(long,Object)
        if (l == 0) {
            this.f = object;
            this.g = true;
            return;
        }
        int n = (int)(l & (long)this.channelCount);  // 02b L67 模式
        long l2 = this.b[n];
        if (l2 == 0) {
            this.b[n] = audioSessionId;
            this.c[n] = object;
            if (this.sampleRate++ >= this.audioFormat) {
                this.g(this.d << 1);
            }
            return;
        }
        int n2 = this.h(l);
        long l3 = this.b[n2];
        if (l3 == 0) {
            this.b[n2] = audioSessionId;
            this.c[n2] = object;
            if (this.sampleRate++ >= this.audioFormat) {
                this.g(this.d << 1);
            }
            return;
        }
        int n3 = this.i(l);
        long l4 = this.b[n3];
        if (l4 == 0) {
            this.b[n3] = audioSessionId;
            this.c[n3] = object;
            if (this.sampleRate++ >= this.audioFormat) {
                this.g(this.d << 1);
            }
            return;
        }
        this.a(l, object, n, l2, n2, l3, n3, l4);
    }

    private void a(long n, Object object, int n2, long n3, int n4, long n5, int n6, long n7) {  // 02b L174: long,Object,int,long,int,long,int,long
        Object object2;
        long n8;
        long[] nArray = this.b;  // 02b L174 区: long 键链
        Object[] objectArray = this.c;
        int n9 = this.channelCount;
        int n10 = 0;
        int n11 = this.streamType;
        while (true) {
            switch (m.a(2)) {
                case 0: {
                    n8 = n3;
                    object2 = objectArray[n2];
                    nArray[n2] = n;
                    objectArray[n2] = object;
                    break;
                }
                case 1: {
                    n8 = n5;
                    object2 = objectArray[n4];
                    nArray[n4] = n;
                    objectArray[n4] = object;
                    break;
                }
                default: {
                    n8 = n7;
                    object2 = objectArray[n6];
                    nArray[n6] = n;
                    objectArray[n6] = object;
                }
            }
            n2 = (int)(n8 & (long)n9);
            n3 = nArray[n2];
            if (n3 == 0) {
                nArray[n2] = n8;
                objectArray[n2] = object2;
                if (this.sampleRate++ >= this.audioFormat) {
                    this.g(this.d << 1);
                }
                return;
            }
            n4 = this.h(n8);
            n5 = nArray[n4];
            if (n5 == 0) {
                nArray[n4] = n8;
                objectArray[n4] = object2;
                if (this.sampleRate++ >= this.audioFormat) {
                    this.g(this.d << 1);
                }
                return;
            }
            n6 = this.i(n8);
            n7 = nArray[n6];
            if (n7 == 0) {
                nArray[n6] = n8;
                objectArray[n6] = object2;
                if (this.sampleRate++ >= this.audioFormat) {
                    this.g(this.d << 1);
                }
                return;
            }
            if (++n10 == n11) break;
            n = n8;
            object = object2;
        }
        this.c(n8, object2);
    }

    private void c(long l, Object object) {  // 02b L251: c(long,Object)
        if (this.e == this.audioSessionId) {
            this.g(this.d << 1);
            this.a(l, object);
            return;
        }
        int n = this.d + this.e;
        this.b[n] = audioSessionId;  // 02b L257: int var4 索引 (非 long n)
        this.c[n] = object;  // 02b L258
        ++this.e;
        ++this.sampleRate;
    }

    public Object a(long l) {  // 02b a/i L264 (v19.133f4 修正)
        if (l == 0) {
            if (!this.g) {
                return null;
            }
            return this.f;
        }
        int n = (int)(l & (long)this.channelCount);  // 02b L67 模式
        if (this.b[n] != l && this.b[n = this.h(l)] != l && this.b[n = this.i(l)] != l) {
            return this.d(l, null);
        }
        return this.c[n];
    }

    private Object d(long n, Object object) {  // 02b L283: d(long,Object)
        long[] lArray = this.b;
        int n2 = this.d + this.e;
        for (int n3 = this.d; n3 < n2; ++n3) {
            if (lArray[n3] != n) continue;
            return this.c[n3];
        }
        return object;
    }

    public Object b(long l) {  // 02b a/i L296 (v19.133f4 修正)
        if (l == 0) {
            if (!this.g) {
                return null;
            }
            Object object = this.f;
            this.f = null;
            this.g = false;
            --this.sampleRate;
            return object;
        }
        int n = (int)(l & (long)this.channelCount);  // 02b L67 模式
        if (this.b[n] == l) {
            this.b[n] = 0;
            Object object = this.c[n];
            this.c[n] = null;
            --this.sampleRate;
            return object;
        }
        n = this.h(l);
        if (this.b[n] == l) {
            this.b[n] = 0;
            Object object = this.c[n];
            this.c[n] = null;
            --this.sampleRate;
            return object;
        }
        n = this.i(l);
        if (this.b[n] == l) {
            this.b[n] = 0;
            Object object = this.c[n];
            this.c[n] = null;
            --this.sampleRate;
            return object;
        }
        return this.c(l);
    }

    Object c(long n) {  // 02b b(long) L333: this.c(var1) — c(long)
        long[] lArray = this.b;
        int n2 = this.d + this.e;
        for (int n3 = this.d; n3 < n2; ++n3) {
            if (lArray[n3] != n) continue;
            Object object = this.c[n3];
            this.d(n3);
            --this.sampleRate;
            return object;
        }
        return null;
    }

    void d(int n) {
        --this.e;
        int n2 = this.d + this.e;
        if (n < n2) {
            this.b[n] = this.b[n2];
            this.c[n] = this.c[n2];
            this.c[n2] = null;
        } else {
            this.c[n] = null;
        }
    }

    public void a() {
        if (this.sampleRate == 0) {
            return;
        }
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n = this.d + this.e;
        while (n-- > 0) {
            lArray[n] = 0;
            objectArray[n] = null;
        }
        this.sampleRate = 0;
        this.e = 0;
        this.f = null;
        this.g = false;
    }

    public boolean d(long l) {  // 02b a/i L385 (v19.133f4 修正)
        if (l == 0) {
            return this.g;
        }
        int n = (int)(l & (long)this.channelCount);  // 02b L67 模式
        if (this.b[n] != l && this.b[n = this.h(l)] != l && this.b[n = this.i(l)] != l) {
            return this.f(l);
        }
        return true;
    }

    private boolean f(long n) {  // 02b L404: e(long)
        long[] lArray = this.b;
        int n2 = this.d + this.e;
        for (int n3 = this.d; n3 < n2; ++n3) {
            if (lArray[n3] != n) continue;
            return true;
        }
        return false;
    }

    private void g(int n) {
        int n2 = this.d + this.e;
        this.d = n;
        this.audioFormat = (int)((float)n * this.volumeLevel);
        this.channelCount = n - 1;
        this.bufferSize = 63 - Integer.numberOfTrailingZeros(n);
        this.audioSessionId = Math.max(3, (int)Math.ceil(Math.log(n)) * 2);
        this.streamType = Math.max(Math.min(n, 8), (int)Math.sqrt(n) / 8);
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        this.b = new long[n + this.audioSessionId];  // 02b L427: new long[]
        this.c = new Object[n + this.audioSessionId];
        int n3 = this.sampleRate;
        this.sampleRate = this.g ? 1 : 0;
        this.e = 0;
        if (n3 > 0) {
            for (int audioFormat = 0; audioFormat < n2; ++audioFormat) {
                long l = lArray[audioFormat];
                if (l == 0) continue;
                this.b(l, objectArray[audioFormat]);
            }
        }
    }

    private int h(long n) {  // 02b L443: f(long)
        n *= -1262997959L;
        return (int)((n ^ n >>> this.bufferSize) & (long)this.channelCount);  // 02b L444-445
    }

    private int i(long n) {  // 02b L448: g(long)
        n *= -825114047L;
        return (int)((n ^ n >>> this.bufferSize) & (long)this.channelCount);  // 02b L449-450
    }

    public int hashCode() {
        int n = 0;
        if (this.g && this.f != null) {
            n += this.f.hashCode();
        }
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n2 = this.d + this.e;
        for (int audioFormat = 0; audioFormat < n2; ++audioFormat) {
            long l = lArray[audioFormat];
            if (l == 0) continue;
            n += (int)(l ^ l >>> 32) * 31;
            Object object = objectArray[audioFormat];
            if (object == null) continue;
            n += object.hashCode();
        }
        return n;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof AudioManager)) {
            return false;
        }
        AudioManager i2 = (AudioManager) object;
        if (i2.sampleRate != this.sampleRate) {
            return false;
        }
        if (i2.g != this.g) {
            return false;
        }
        if (this.g && (i2.f == null ? this.f != null : !i2.f.equals(this.f))) {
            return false;
        }
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n = this.d + this.e;
        for (int audioFormat = 0; audioFormat < n; ++audioFormat) {
            Object object2;
            long l = lArray[audioFormat];
            if (l == 0 || !((object2 = objectArray[audioFormat]) == null ? !i2.d(l) || i2.a(l) != null : !object2.equals(i2.a(l)))) continue;  // 02b L508: var2.d(var7)
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.sampleRate == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n = lArray.length;
        while (n-- > 0) {
            long audioSessionId = lArray[n];
            if (audioSessionId == 0) continue;
            stringBuilder.append(audioSessionId);
            stringBuilder.append('=');
            stringBuilder.append(objectArray[n]);
            break;
        }
        while (n-- > 0) {
            long audioSessionId = lArray[n];
            if (audioSessionId == 0) continue;
            stringBuilder.append(", ");
            stringBuilder.append(audioSessionId);
            stringBuilder.append('=');
            stringBuilder.append(objectArray[n]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Iterator iterator() {
        return this.b();
    }

    public j b() {
        if (this.audioPlayerA == null) {
            this.audioPlayerA = new j(this);
            this.audioPlayerB = new j(this);
        }
        if (!this.audioPlayerA.e) {
            this.audioPlayerA.b();
            this.audioPlayerA.e = true;
            this.audioPlayerB.e = false;
            return this.audioPlayerA;
        }
        this.audioPlayerB.b();
        this.audioPlayerB.e = true;
        this.audioPlayerA.e = false;
        return this.audioPlayerB;
    }
}
