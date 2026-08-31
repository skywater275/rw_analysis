package com.corrodinggames.rts.java.audio;

import com.corrodinggames.rts.java.audio.Music$OnCompletionListener;

public interface Music {

   void play();

   void pause();

   void stop();

   boolean isPlaying();

   void setLooping(boolean var1);

   boolean isLooping();

   void setVolume(float var1);

   float getVolume();

   void setPan(float var1, float var2);

   void setPosition(float var1);

   float getPosition();

   void dispose();

   void setOnCompletionListener(Music$OnCompletionListener var1);
}
