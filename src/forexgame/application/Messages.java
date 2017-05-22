package forexgame.application;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "forexgame.application.messages"; //$NON-NLS-1$
	public static String ApplicationWorkbenchWindowAdvisor_rcp_application;
	public static String View_One;
	public static String View_Three;
	public static String View_Two;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
