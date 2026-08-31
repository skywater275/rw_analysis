/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.jcraft.jogg.Packet
 *  com.jcraft.jogg.Page
 *  com.jcraft.jogg.StreamState
 *  com.jcraft.jogg.SyncState
 *  com.jcraft.jorbis.Block
 *  com.jcraft.jorbis.Comment
 *  com.jcraft.jorbis.DspState
 *  com.jcraft.jorbis.Info
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.audio.backend.c;
import com.corrodinggames.rts.java.audio.backend.s;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

public class OggInputStream
extends InputStream {
    private static final int BUFFER_SIZE = 512;
    private int convsize = 2048;
    private byte[] convbuffer;
    private InputStream input;
    private Info oggInfo = new Info();
    private boolean endOfStream;
    private SyncState syncState = new SyncState();
    private StreamState streamState = new StreamState();
    private Page page = new Page();
    private Packet packet = new Packet();
    private Comment comment = new Comment();
    private DspState dspState = new DspState();
    private Block vorbisBlock = new Block(this.dspState);
    byte[] buffer;
    int bytes = 0;
    boolean bigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
    boolean endOfBitStream = true;
    boolean inited = false;
    private int readIndex;
    private byte[] outBuffer;
    private int outIndex;
    private int total;

    public OggInputStream(InputStream inputStream) {
        this(inputStream, null);
    }

    public OggInputStream(InputStream inputStream, OggInputStream oggInputStream) {
        if (oggInputStream == null) {
            this.convbuffer = new byte[this.convsize];
            this.outBuffer = new byte[2048000];
        } else {
            this.convbuffer = oggInputStream.convbuffer;
            this.outBuffer = oggInputStream.outBuffer;
        }
        this.input = inputStream;
        try {
            this.total = inputStream.available();
        }
        catch (IOException iOException) {
            throw new c(iOException);
        }
        this.init();
    }

    public int getLength() {
        return this.total;
    }

    public int getChannels() {
        return this.oggInfo.channels;
    }

    public int getSampleRate() {
        return this.oggInfo.rate;
    }

    private void init() {
        this.initVorbis();
        this.readPCM();
    }

    @Override
    public int available() {
        return this.endOfStream ? 0 : 1;
    }

    private void initVorbis() {
        this.syncState.init();
    }

    private boolean getPageAndPacket() {
        int n = this.syncState.buffer(512);
        if (n == -1) {
            return false;
        }
        this.buffer = this.syncState.data;
        if (this.buffer == null) {
            this.endOfStream = true;
            return false;
        }
        try {
            this.bytes = this.input.read(this.buffer, n, 512);
        }
        catch (Exception exception) {
            throw new c("Failure reading Vorbis.", exception);
        }
        this.syncState.wrote(this.bytes);
        if (this.syncState.pageout(this.page) != 1) {
            if (this.bytes < 512) {
                return false;
            }
            throw new c("Input does not appear to be an Ogg bitstream.");
        }
        this.streamState.init(this.page.serialno());
        this.oggInfo.init();
        this.comment.init();
        if (this.streamState.pagein(this.page) < 0) {
            throw new c("Error reading first page of Ogg bitstream.");
        }
        if (this.streamState.packetout(this.packet) != 1) {
            throw new c("Error reading initial header packet.");
        }
        if (this.oggInfo.synthesis_headerin(this.comment, this.packet) < 0) {
            throw new c("Ogg bitstream does not contain Vorbis audio data.");
        }
        int n2 = 0;
        while (n2 < 2) {
            int n3;
            while (n2 < 2 && (n3 = this.syncState.pageout(this.page)) != 0) {
                if (n3 != 1) continue;
                this.streamState.pagein(this.page);
                while (n2 < 2 && (n3 = this.streamState.packetout(this.packet)) != 0) {
                    if (n3 == -1) {
                        throw new c("Corrupt secondary header.");
                    }
                    this.oggInfo.synthesis_headerin(this.comment, this.packet);
                    ++n2;
                }
            }
            n = this.syncState.buffer(512);
            if (n == -1) {
                return false;
            }
            this.buffer = this.syncState.data;
            try {
                this.bytes = this.input.read(this.buffer, n, 512);
            }
            catch (Exception exception) {
                throw new c("Failed to read Vorbis.", exception);
            }
            if (this.bytes == 0 && n2 < 2) {
                throw new c("End of file before finding all Vorbis headers.");
            }
            this.syncState.wrote(this.bytes);
        }
        this.convsize = 512 / this.oggInfo.channels;
        this.dspState.synthesis_init(this.oggInfo);
        this.vorbisBlock.init(this.dspState);
        return true;
    }

    private void readPCM() {
        boolean bl = false;
        while (true) {
            if (this.endOfBitStream) {
                if (!this.getPageAndPacket()) break;
                this.endOfBitStream = false;
            }
            if (!this.inited) {
                this.inited = true;
                return;
            }
            float[][][] fArrayArray = new float[1][][];
            int[] nArray = new int[this.oggInfo.channels];
            while (!this.endOfBitStream) {
                int n2;
                while (!this.endOfBitStream && (n2 = this.syncState.pageout(this.page)) != 0) {
                    if (n2 == -1) {
                        GlobalState.b("gdx-audio", "Error reading OGG: Corrupt or missing data in bitstream.");
                        continue;
                    }
                    this.streamState.pagein(this.page);
                    while ((n2 = this.streamState.packetout(this.packet)) != 0) {
                        int n3;
                        if (n2 == -1) continue;
                        if (this.vorbisBlock.synthesis(this.packet) == 0) {
                            this.dspState.synthesis_blockin(this.vorbisBlock);
                        }
                        while ((n3 = this.dspState.synthesis_pcmout((float[][][])fArrayArray, nArray)) > 0) {
                            int n4;
                            float[][] fArray = fArrayArray[0];
                            int n5 = n3 < this.convsize ? n3 : this.convsize;
                            for (n4 = 0; n4 < this.oggInfo.channels; ++n4) {
                                int n6 = n4 * 2;
                                int n7 = nArray[n4];
                                for (int i = 0; i < n5; ++i) {
                                    int n8 = (int)((double)fArray[n4][n7 + i] * 32767.0);
                                    if (n8 > Short.MAX_VALUE) {
                                        n8 = Short.MAX_VALUE;
                                    }
                                    if (n8 < Short.MIN_VALUE) {
                                        n8 = Short.MIN_VALUE;
                                    }
                                    if (n8 < 0) {
                                        n8 |= 0x8000;
                                    }
                                    if (this.bigEndian) {
                                        this.convbuffer[n6] = (byte)(n8 >>> 8);
                                        this.convbuffer[n6 + 1] = (byte)n8;
                                    } else {
                                        this.convbuffer[n6] = (byte)n8;
                                        this.convbuffer[n6 + 1] = (byte)(n8 >>> 8);
                                    }
                                    n6 += 2 * this.oggInfo.channels;
                                }
                            }
                            n4 = 2 * this.oggInfo.channels * n5;
                            if (this.outIndex + n4 > this.outBuffer.length) {
                                throw new c("Ogg block too big to be buffered: " + n4 + ", " + (this.outBuffer.length - this.outIndex));
                            }
                            System.arraycopy(this.convbuffer, 0, this.outBuffer, this.outIndex, n4);
                            this.outIndex += n4;
                            bl = true;
                            this.dspState.synthesis_read(n5);
                        }
                    }
                    if (this.page.eos() != 0) {
                        this.endOfBitStream = true;
                    }
                    if (this.endOfBitStream || !bl) continue;
                    return;
                }
                if (this.endOfBitStream) continue;
                this.bytes = 0;
                n2 = this.syncState.buffer(512);
                if (n2 >= 0) {
                    this.buffer = this.syncState.data;
                    try {
                        this.bytes = this.input.read(this.buffer, n2, 512);
                    }
                    catch (Exception exception) {
                        throw new c("Error during Vorbis decoding.", exception);
                    }
                } else {
                    this.bytes = 0;
                }
                this.syncState.wrote(this.bytes);
                if (this.bytes != 0) continue;
                this.endOfBitStream = true;
            }
            this.streamState.clear();
            this.vorbisBlock.clear();
            this.dspState.clear();
            this.oggInfo.clear();
        }
        this.syncState.clear();
        this.endOfStream = true;
    }

    @Override
    public int read() {
        int n2;
        if (this.readIndex >= this.outIndex) {
            this.outIndex = 0;
            this.readPCM();
            this.readIndex = 0;
            if (this.outIndex == 0) {
                return -1;
            }
        }
        if ((n2 = this.outBuffer[this.readIndex]) < 0) {
            n2 = 256 + n2;
        }
        ++this.readIndex;
        return n2;
    }

    public boolean atEnd() {
        return this.endOfStream && this.readIndex >= this.outIndex;
    }

    @Override
    public int read(byte[] byArray, int n2, int n3) {
        for (int i = 0; i < n3; ++i) {
            int n4 = this.read();
            if (n4 < 0) {
                if (i == 0) {
                    return -1;
                }
                return i;
            }
            byArray[i] = (byte)n4;
        }
        return n3;
    }

    @Override
    public int read(byte[] byArray) {
        return this.read(byArray, 0, byArray.length);
    }

    @Override
    public void close() {
        s.a(this.input);
    }
}
