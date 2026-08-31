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
    private int lineNumber = -1;
    private int lastCharValue = -1;

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
    public void close() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            if (!this.isEnabled()) {
                this.a.close();
                this.b = null;
            }
        }
    }

    private int getint() throws IOException {
        int n;
        if (this.lineNumber == -1 || this.c - this.lineNumber >= this.lastCharValue) {
            int n2 = this.a.read(this.b, 0, this.b.length);
            if (n2 > 0) {
                this.lineNumber = -1;
                this.c = 0;
                this.d = n2;
            }
            return n2;
        }
        if (this.lineNumber == 0 && this.lastCharValue > this.b.length) {
            n = this.b.length * 2;
            if (n > this.lastCharValue) {
                n = this.lastCharValue;
            }
            char[] cArray = new char[n];
            System.arraycopy(this.b, 0, cArray, 0, this.b.length);
            this.b = cArray;
        } else if (this.lineNumber > 0) {
            System.arraycopy(this.b, this.lineNumber, this.b, 0, this.b.length - this.lineNumber);
            this.c -= this.lineNumber;
            this.d -= this.lineNumber;
            this.lineNumber = 0;
        }
        n = this.a.read(this.b, this.c, this.b.length - this.c);
        if (n != -1) {
            this.d += n;
        }
        return n;
    }

    private boolean isEnabled() {
        return this.b == null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void mark(int n) throws IOException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        Object object = this.lock;
        synchronized (object) {
            this.d();
            this.lastCharValue = n;
            this.lineNumber = this.c;
        }
    }

    private void d() throws IOException {  // 02b utility/l L97
        if (this.isEnabled()) {
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
    public int read() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            this.d();
            if (this.c < this.d || this.getint() != -1) {
                return this.b[this.c++];
            }
            return -1;
        }
    }

    public static void getString(int n, int n2, int n3) {
        if ((n2 | n3) < 0 || n2 > n || n - n2 < n3) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void a(int n, int n2, int n3) {  // 02b utility/l L115-119
        if ((n2 | n3) < 0 || n2 > n || n - n2 < n3) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int read(char[] cArray, int n, int n2) throws IOException {
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
                if ((this.lineNumber == -1 || this.c - this.lineNumber >= this.lastCharValue) && n4 >= this.b.length) {
                    n5 = this.a.read(cArray, n, n4);
                    if (n5 <= 0) break;
                    n4 -= n5;
                    this.lineNumber = -1;
                    break;
                }
                if (this.getint() != -1) continue;
                break;
            }
            // MONITOREXIT @DISABLED, blocks:[0, 1] lbl24 : MonitorExitStatement: MONITOREXIT : var4_4
            n3 = n2 - n4;
            return n3 > 0 || n3 == n2 ? n3 : -1;
        }
    }

    public String getString() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            int n;
            this.d();
            if (this.c == this.d && this.getint() == -1) {
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
                if ((this.c < this.d || this.getint() != -1) && this.b[this.c] == '\n') {
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
                if (this.getint() == -1) {
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
    public boolean ready() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            this.d();
            return this.d - this.c > 0 || this.a.ready();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void reset() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            this.d();
            if (this.lineNumber == -1) {
                throw new IOException("Invalid mark");
            }
            this.c = this.lineNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long skip(long l2) throws IOException {
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
                if (this.getint() == -1) {
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
