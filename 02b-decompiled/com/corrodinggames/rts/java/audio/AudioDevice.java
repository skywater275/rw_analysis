package com.corrodinggames.rts.java.audio;


public interface AudioDevice {

   boolean isMono();

   void writeSamples(short[] var1, int var2, int var3);

   void writeSamples(float[] var1, int var2, int var3);

   int getLatency();

   void dispose();

   void setVolume(float var1);
}
