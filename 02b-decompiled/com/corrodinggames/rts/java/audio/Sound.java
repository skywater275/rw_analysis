package com.corrodinggames.rts.java.audio;


public interface Sound {

   long play();

   long play(float var1);

   long play(float var1, float var2, float var3);

   long loop();

   long loop(float var1);

   long loop(float var1, float var2, float var3);

   void stop();

   void pause();

   void resume();

   void dispose();

   void stop(long var1);

   void pause(long var1);

   void resume(long var1);

   void setLooping(long var1, boolean var3);

   void setPitch(long var1, float var3);

   void setVolume(long var1, float var3);

   void setPan(long var1, float var3, float var4);

   int getBytesUsed();
}
