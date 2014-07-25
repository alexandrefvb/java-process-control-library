package br.com.alexandrefvb.jpcl;

import br.com.alexandrefvb.jpcl.exception.UnsupportedPlatformException;
import br.com.alexandrefvb.jpcl.linux.LinuxProcessControl;
import br.com.alexandrefvb.jpcl.windows.WindowsProcessControl;

import com.sun.jna.Platform;

public class ProcessControlFactory {

	public ProcessControl create() {

		if (Platform.isWindows()) {
			return new WindowsProcessControl();
		}

		if (Platform.isLinux()) {
			return new LinuxProcessControl();
		}
		throw new UnsupportedPlatformException();
	}
}
