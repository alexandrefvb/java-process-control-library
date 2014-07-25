package br.com.alexandrefvb.jpcl.linux;

import com.sun.jna.Native;

public interface LibC {
	
	LibC INSTANCE = (LibC) Native.loadLibrary("c", LibC.class);
	
	int kill(int pid, int signal);

}
