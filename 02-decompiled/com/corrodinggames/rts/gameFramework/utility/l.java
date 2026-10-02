/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import java.io.IOException;
import java.io.Reader;

public class l
extends Reader {
    private Reader a;
    private char[] b;
    private int c;
    private int d;
    private int e = -1;
    private int f = -1;

    public l(Reader reader) {
        this(reader, 8192);
    }

    public l(Reader reader, int n) {
        super(reader);
        if (n <= 0) {
            throw new IllegalArgumentException("size <= 0");
        }
        this.a = reader;
        this.b = new char[n];
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void close() {
        Object object = this.lock;
        synchronized (object) {
            if (!this.c()) {
                this.a.close();
                this.b = null;
            }
        }
    }

    private int b() {
        int n;
        if (this.e == -1 || this.c - this.e >= this.f) {
            int n2 = this.a.read(this.b, 0, this.b.length);
            if (n2 > 0) {
                this.e = -1;
                this.c = 0;
                this.d = n2;
            }
            return n2;
        }
        if (this.e == 0 && this.f > this.b.length) {
            n = this.b.length * 2;
            if (n > this.f) {
                n = this.f;
            }
            char[] cArray = new char[n];
            System.arraycopy(this.b, 0, cArray, 0, this.b.length);
            this.b = cArray;
        } else if (this.e > 0) {
            System.arraycopy(this.b, this.e, this.b, 0, this.b.length - this.e);
            this.c -= this.e;
            this.d -= this.e;
            this.e = 0;
        }
        n = this.a.read(this.b, this.c, this.b.length - this.c);
        if (n != -1) {
            this.d += n;
        }
        return n;
    }

    private boolean c() {
        return this.b == null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void mark(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        Object object = this.lock;
        synchronized (object) {
            this.d();
            this.f = n;
            this.e = this.c;
        }
    }

    private void d() {
        if (this.c()) {
            throw new IOException("BufferedReader is closed");
        }
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int read() {
        Object object = this.lock;
        synchronized (object) {
            this.d();
            if (this.c < this.d || this.b() != -1) {
                return this.b[this.c++];
            }
            return -1;
        }
    }

    public static void a(int n, int n2, int n3) {
        if ((n2 | n3) < 0 || n2 > n || n - n2 < n3) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int read(char[] cArray, int n, int n2) {
        Object object = this.lock;
        synchronized (object) {
            int n3;
            this.d();
            l.a(cArray.length, n, n2);
            int n4 = n2;
            while (n4 > 0) {
                int n5;
                n3 = this.d - this.c;
                if (n3 > 0) {
                    n5 = n3 >= n4 ? n4 : n3;
                    System.arraycopy(this.b, this.c, cArray, n, n5);
                    this.c += n5;
                    n += n5;
                    n4 -= n5;
                }
                if (n4 == 0 || n4 < n2 && !this.a.ready()) break;
                if ((this.e == -1 || this.c - this.e >= this.f) && n4 >= this.b.length) {
                    n5 = this.a.read(cArray, n, n4);
                    if (n5 <= 0) break;
                    n4 -= n5;
                    this.e = -1;
                    break;
                }
                if (this.b() != -1) continue;
                break;
            }
            // MONITOREXIT @DISABLED, blocks:[0, 1] lbl24 : MonitorExitStatement: MONITOREXIT : var4_4
            n3 = n2 - n4;
            return n3 > 0 || n3 == n2 ? n3 : -1;
        }
    }

    public String a() {
        Object object = this.lock;
        synchronized (object) {
            int n;
            this.d();
            if (this.c == this.d && this.b() == -1) {
                return null;
            }
            for (n = this.c; n < this.d; ++n) {
                char c = this.b[n];
                if (c > '\r') continue;
                if (c == '\n') {
                    String string = new String(this.b, this.c, n - this.c);
                    this.c = n + 1;
                    return string;
                }
                if (c != '\r') continue;
                String string = new String(this.b, this.c, n - this.c);
                this.c = n + 1;
                if ((this.c < this.d || this.b() != -1) && this.b[this.c] == '\n') {
                    ++this.c;
                }
                return string;
            }
            n = 0;
            StringBuilder stringBuilder = new StringBuilder(80);
            stringBuilder.append(this.b, this.c, this.d - this.c);
            while (true) {
                this.c = this.d;
                if (n == 10) {
                    return stringBuilder.toString();
                }
                if (this.b() == -1) {
                    return stringBuilder.length() > 0 || n != 0 ? stringBuilder.toString() : null;
                }
                for (int i = this.c; i < this.d; ++i) {
                    int n2 = this.b[i];
                    if (n == 0) {
                        if (n2 != 10 && n2 != 13) continue;
                        n = n2;
                        continue;
                    }
                    if (n == 13 && n2 == 10) {
                        if (i > this.c) {
                            stringBuilder.append(this.b, this.c, i - this.c - 1);
                        }
                        this.c = i + 1;
                        return stringBuilder.toString();
                    }
                    if (i > this.c) {
                        stringBuilder.append(this.b, this.c, i - this.c - 1);
                    }
                    this.c = i;
                    return stringBuilder.toString();
                }
                if (n == 0) {
                    stringBuilder.append(this.b, this.c, this.d - this.c);
                } else {
                    stringBuilder.append(this.b, this.c, this.d - this.c - 1);
                }
                try {
                    Thread.sleep(5L);
                }
                catch (InterruptedException interruptedException) {}
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean ready() {
        Object object = this.lock;
        synchronized (object) {
            this.d();
            return this.d - this.c > 0 || this.a.ready();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void reset() {
        Object object = this.lock;
        synchronized (object) {
            this.d();
            if (this.e == -1) {
                throw new IOException("Invalid mark");
            }
            this.c = this.e;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long skip(long l2) {
        if (l2 < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + l2);
        }
        Object object = this.lock;
        synchronized (object) {
            this.d();
            if (l2 < 1L) {
                return 0L;
            }
            if ((long)(this.d - this.c) >= l2) {
                this.c = (int)((long)this.c + l2);
                return l2;
            }
            this.c = this.d;
            for (long i = (long)(this.d - this.c); i < l2; i += (long)(this.d - this.c)) {
                if (this.b() == -1) {
                    return i;
                }
                if ((long)(this.d - this.c) >= l2 - i) {
                    this.c = (int)((long)this.c + (l2 - i));
                    return l2;
                }
                this.c = this.d;
            }
            return l2;
        }
    }
}
