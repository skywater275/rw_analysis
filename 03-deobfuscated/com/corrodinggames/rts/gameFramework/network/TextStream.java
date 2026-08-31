/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

public strictfp class TextStream {
    public ByteArrayOutputStream c;
    public BufferedOutputStream bufferedOutput;
    public String fileName;
    public ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
    public PrintStream printStream;
    public boolean isClosed = false;

    /* 02b j/ax.java: bufferedOutput.flush 抛 IOException */
    public void a() throws IOException {
        this.printStream.flush();
        if (this.bufferedOutput != null) {
            this.bufferedOutput.flush();
        }
    }

    /* 02b j/ax.java: printStream.close 抛 IOException */
    public void b() throws IOException {
        if (!this.isClosed) {
            this.printStream.close();
        } else {
            GlobalState.isKeyJustPressed("TODO: Cannot yet close wrapped stream");
        }
    }

    public TextStream(boolean bl) {
        OutputStream outputStream;
        if (bl) {
            this.bufferedOutput = new BufferedOutputStream(this.byteArrayOutput);
            outputStream = this.bufferedOutput;
        } else {
            outputStream = this.byteArrayOutput;
        }
        this.printStream = new PrintStream(outputStream);
    }
}
