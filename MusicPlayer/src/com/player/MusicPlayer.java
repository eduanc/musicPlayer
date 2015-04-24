package com.player;

import com.dto.MusicDTO;

public interface MusicPlayer {
	
	public void play(MusicDTO musica);
	
	public void pause();
	
	public void stop();
}