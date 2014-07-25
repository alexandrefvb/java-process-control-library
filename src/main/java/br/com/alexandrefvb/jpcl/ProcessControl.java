package br.com.alexandrefvb.jpcl;

public interface ProcessControl {

	String getPID(Process process);

	boolean kill(String pid);

	boolean isRunning(String pid);
}
