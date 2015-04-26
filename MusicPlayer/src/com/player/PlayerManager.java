package com.player;

import com.dto.MusicDTO;
import com.gui.PlayerGUI;
import com.thread.GenericThread;
import com.thread.ThreadMP3;

public class PlayerManager implements MusicPlayer {

	private GenericThread currentThread;
	
	@SuppressWarnings("deprecation")
	@Override
	public void play(int begin, MusicDTO music) {
		if(this.currentThread == null){
			this.currentThread = new ThreadMP3(begin / 26, music);			
			this.currentThread.start();
			PlayerGUI.startProgressBar(begin);
		} else if(this.currentThread.isAlive()){
			this.currentThread.resume();
			PlayerGUI.resumeProgressBar();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void pause() {
		this.currentThread.suspend();
		PlayerGUI.pauseProgressBar();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() {		
		this.currentThread.stop();
		this.currentThread = null;
		PlayerGUI.stopProgressBar();
	}
	
	@Override
	public void change(MusicDTO music){
		if(this.currentThread != null){
			this.stop();
		}
		
		this.play(0, music);
	}
	
	@Override
	public void autoChange(MusicDTO music){
		this.currentThread = null;
		this.play(0, music);
	}
	
	public void askForNext(){		
		PlayerGUI.autoChangeMusic(1);
	}
}