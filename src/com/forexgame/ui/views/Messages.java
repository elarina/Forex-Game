package com.forexgame.ui.views;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.forexgame.ui.views.messages"; //$NON-NLS-1$
	public static String NewsView_Date;
	public static String NewsView_Heading;
	public static String NewsView_Author;
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
