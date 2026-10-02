package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.s;
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

public class OggInputStream extends InputStream {

   private static final int BUFFER_SIZE = 512;
   private int convsize;
   private byte[] convbuffer;
   private InputStream input;
   private Info oggInfo;
   private boolean endOfStream;
   private SyncState syncState;
   private StreamState streamState;
   private Page page;
   private Packet packet;
   private Comment comment;
   private DspState dspState;
   private Block vorbisBlock;
   byte[] buffer;
   int bytes;
   boolean bigEndian;
   boolean endOfBitStream;
   boolean inited;
   private int readIndex;
   private byte[] outBuffer;
   private int outIndex;
   private int total;


   public OggInputStream(InputStream var1) {
      this(var1, (OggInputStream)null);
   }

   public OggInputStream(InputStream var1, OggInputStream var2) {
      this.convsize = 2048;
      this.oggInfo = new Info();
      this.syncState = new SyncState();
      this.streamState = new StreamState();
      this.page = new Page();
      this.packet = new Packet();
      this.comment = new Comment();
      this.dspState = new DspState();
      this.vorbisBlock = new Block(this.dspState);
      this.bytes = 0;
      this.bigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      this.endOfBitStream = true;
      this.inited = false;
      if(var2 == null) {
         this.convbuffer = new byte[this.convsize];
         this.outBuffer = new byte[2048000];
      } else {
         this.convbuffer = var2.convbuffer;
         this.outBuffer = var2.outBuffer;
      }

      this.input = var1;

      try {
         this.total = var1.available();
      } catch (IOException var4) {
         throw new c(var4);
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

   public int available() {
      return this.endOfStream?0:1;
   }

   private void initVorbis() {
      this.syncState.init();
   }

   private boolean getPageAndPacket() {
      int var1 = this.syncState.buffer(512);
      if(var1 == -1) {
         return false;
      } else {
         this.buffer = this.syncState.data;
         if(this.buffer == null) {
            this.endOfStream = true;
            return false;
         } else {
            try {
               this.bytes = this.input.read(this.buffer, var1, 512);
            } catch (Exception var5) {
               throw new c("Failure reading Vorbis.", var5);
            }

            this.syncState.wrote(this.bytes);
            if(this.syncState.pageout(this.page) != 1) {
               if(this.bytes < 512) {
                  return false;
               } else {
                  throw new c("Input does not appear to be an Ogg bitstream.");
               }
            } else {
               this.streamState.init(this.page.serialno());
               this.oggInfo.init();
               this.comment.init();
               if(this.streamState.pagein(this.page) < 0) {
                  throw new c("Error reading first page of Ogg bitstream.");
               } else if(this.streamState.packetout(this.packet) != 1) {
                  throw new c("Error reading initial header packet.");
               } else if(this.oggInfo.synthesis_headerin(this.comment, this.packet) < 0) {
                  throw new c("Ogg bitstream does not contain Vorbis audio data.");
               } else {
                  int var2 = 0;

                  while(var2 < 2) {
                     label88:
                     while(true) {
                        if(var2 < 2) {
                           int var3 = this.syncState.pageout(this.page);
                           if(var3 != 0) {
                              if(var3 == 1) {
                                 this.streamState.pagein(this.page);

                                 while(var2 < 2) {
                                    var3 = this.streamState.packetout(this.packet);
                                    if(var3 == 0) {
                                       continue label88;
                                    }

                                    if(var3 == -1) {
                                       throw new c("Corrupt secondary header.");
                                    }

                                    this.oggInfo.synthesis_headerin(this.comment, this.packet);
                                    ++var2;
                                 }
                              }
                              continue;
                           }
                        }

                        var1 = this.syncState.buffer(512);
                        if(var1 == -1) {
                           return false;
                        }

                        this.buffer = this.syncState.data;

                        try {
                           this.bytes = this.input.read(this.buffer, var1, 512);
                        } catch (Exception var4) {
                           throw new c("Failed to read Vorbis.", var4);
                        }

                        if(this.bytes == 0 && var2 < 2) {
                           throw new c("End of file before finding all Vorbis headers.");
                        }

                        this.syncState.wrote(this.bytes);
                        break;
                     }
                  }

                  this.convsize = 512 / this.oggInfo.channels;
                  this.dspState.synthesis_init(this.oggInfo);
                  this.vorbisBlock.init(this.dspState);
                  return true;
               }
            }
         }
      }
   }

   private void readPCM() {
      boolean var1 = false;

      while(true) {
         if(this.endOfBitStream) {
            if(!this.getPageAndPacket()) {
               this.syncState.clear();
               this.endOfStream = true;
               return;
            }

            this.endOfBitStream = false;
         }

         if(!this.inited) {
            this.inited = true;
            return;
         }

         float[][][] var2 = new float[1][][];
         int[] var3 = new int[this.oggInfo.channels];

         while(!this.endOfBitStream) {
            label124:
            while(true) {
               int var4;
               if(!this.endOfBitStream) {
                  var4 = this.syncState.pageout(this.page);
                  if(var4 != 0) {
                     if(var4 != -1) {
                        this.streamState.pagein(this.page);

                        while(true) {
                           var4 = this.streamState.packetout(this.packet);
                           if(var4 == 0) {
                              if(this.page.eos() != 0) {
                                 this.endOfBitStream = true;
                              }

                              if(!this.endOfBitStream && var1) {
                                 return;
                              }
                              continue label124;
                           }

                           if(var4 != -1) {
                              if(this.vorbisBlock.synthesis(this.packet) == 0) {
                                 this.dspState.synthesis_blockin(this.vorbisBlock);
                              }

                              int var5;
                              while((var5 = this.dspState.synthesis_pcmout(var2, var3)) > 0) {
                                 float[][] var6 = var2[0];
                                 int var7 = var5 < this.convsize?var5:this.convsize;

                                 int var8;
                                 for(var8 = 0; var8 < this.oggInfo.channels; ++var8) {
                                    int var9 = var8 * 2;
                                    int var10 = var3[var8];

                                    for(int var11 = 0; var11 < var7; ++var11) {
                                       int var12 = (int)((double)var6[var8][var10 + var11] * 32767.0D);
                                       if(var12 > 32767) {
                                          var12 = 32767;
                                       }

                                       if(var12 < -32768) {
                                          var12 = -32768;
                                       }

                                       if(var12 < 0) {
                                          var12 |= '\u8000';
                                       }

                                       if(this.bigEndian) {
                                          this.convbuffer[var9] = (byte)(var12 >>> 8);
                                          this.convbuffer[var9 + 1] = (byte)var12;
                                       } else {
                                          this.convbuffer[var9] = (byte)var12;
                                          this.convbuffer[var9 + 1] = (byte)(var12 >>> 8);
                                       }

                                       var9 += 2 * this.oggInfo.channels;
                                    }
                                 }

                                 var8 = 2 * this.oggInfo.channels * var7;
                                 if(this.outIndex + var8 > this.outBuffer.length) {
                                    throw new c("Ogg block too big to be buffered: " + var8 + ", " + (this.outBuffer.length - this.outIndex));
                                 }

                                 System.arraycopy(this.convbuffer, 0, this.outBuffer, this.outIndex, var8);
                                 this.outIndex += var8;
                                 var1 = true;
                                 this.dspState.synthesis_read(var7);
                              }
                           }
                        }
                     }

                     l.b("gdx-audio", "Error reading OGG: Corrupt or missing data in bitstream.");
                     continue;
                  }
               }

               if(!this.endOfBitStream) {
                  this.bytes = 0;
                  var4 = this.syncState.buffer(512);
                  if(var4 >= 0) {
                     this.buffer = this.syncState.data;

                     try {
                        this.bytes = this.input.read(this.buffer, var4, 512);
                     } catch (Exception var13) {
                        throw new c("Error during Vorbis decoding.", var13);
                     }
                  } else {
                     this.bytes = 0;
                  }

                  this.syncState.wrote(this.bytes);
                  if(this.bytes == 0) {
                     this.endOfBitStream = true;
                  }
               }
               break;
            }
         }

         this.streamState.clear();
         this.vorbisBlock.clear();
         this.dspState.clear();
         this.oggInfo.clear();
      }
   }

   public int read() {
      if(this.readIndex >= this.outIndex) {
         this.outIndex = 0;
         this.readPCM();
         this.readIndex = 0;
         if(this.outIndex == 0) {
            return -1;
         }
      }

      int var1 = this.outBuffer[this.readIndex];
      if(var1 < 0) {
         var1 += 256;
      }

      ++this.readIndex;
      return var1;
   }

   public boolean atEnd() {
      return this.endOfStream && this.readIndex >= this.outIndex;
   }

   public int read(byte[] var1, int var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = this.read();
         if(var5 < 0) {
            if(var4 == 0) {
               return -1;
            }

            return var4;
         }

         var1[var4] = (byte)var5;
      }

      return var3;
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public void close() {
      s.a(this.input);
   }
}
