package com.forexgame.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.forexgame.application.Activator;

public class NewsView extends ViewPart {

	public static final String ID = NewsView.class.getCanonicalName();
	
	public NewsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		Color whiteColor = Activator.getDefault().getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WHITE);
		parent.setBackground(whiteColor);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
