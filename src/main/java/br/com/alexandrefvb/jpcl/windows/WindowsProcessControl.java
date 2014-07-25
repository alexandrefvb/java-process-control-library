package br.com.alexandrefvb.jpcl.windows;

import java.lang.reflect.Field;

import br.com.alexandrefvb.jpcl.ProcessControl;
import br.com.alexandrefvb.jpcl.exception.UnsupportedPlatformException;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

public class WindowsProcessControl implements ProcessControl {

	private static final int PROCESS_QUERY_INFORMATION = 0x0400;

	public String getPID(Process process) {
		try {
			Field handleField = process.getClass().getDeclaredField("handle");
			handleField.setAccessible(true);
			long handle = (Long) handleField.get(process);
			return ""
					+ Kernel32.INSTANCE.GetProcessId(new HANDLE(Pointer
							.createConstant(handle)));
		} catch (Exception e) {
			throw new UnsupportedPlatformException(e);
		}
	}

	public boolean kill(String pid) {
		try {
			HANDLE h = Kernel32.INSTANCE.OpenProcess(
					Kernel32.PROCESS_TERMINATE, true, Integer.parseInt(pid));
			if (h == null) {
				return false;
			}
			return Kernel32.INSTANCE.TerminateProcess(h, 0);
		} catch (Exception e) {
			throw new UnsupportedPlatformException(e);
		}
	}

	public boolean isRunning(String pid) {
		HANDLE h = Kernel32.INSTANCE.OpenProcess(PROCESS_QUERY_INFORMATION,
				true, Integer.parseInt(pid));
		if (h == null) {
			return false;
		}
		IntByReference lpExitCode = new IntByReference();
		if (Kernel32.INSTANCE.GetExitCodeProcess(h, lpExitCode)) {
			return lpExitCode.getValue() == Kernel32.STILL_ACTIVE;
		}
		return false;
	}

}
