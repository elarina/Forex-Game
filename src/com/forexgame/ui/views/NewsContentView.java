package com.forexgame.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class NewsContentView extends ViewPart implements IEditableView {

	public static final String ID = NewsContentView.class.getCanonicalName();
	private Browser browser; 
	
	public NewsContentView() {

	}

	@Override
	public void createPartControl(Composite parent) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Color whiteColor = workbench.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		parent.setBackground(whiteColor);
		browser = new Browser(parent, SWT.NONE);
		FillLayout layout = new FillLayout();
		browser.setLayoutData(layout);
		addKeyListener();
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		IWorkbenchPage activePage = this.getSite().getWorkbenchWindow().getActivePage();
		IWorkbenchPartReference activePartReference = activePage.getActivePartReference();
		if(activePartReference != null ) {
			activePage.setPartState(activePartReference, IWorkbenchPage.STATE_RESTORED);
		}

		for (IViewReference viewReference: activePage.getViewReferences()) {
			String id = viewReference.getId();
			if(id.equals(NewsView.ID)){
				viewReference.getView(true).setFocus();
			}
		}
	}
	
	@Override
	public void setTextContent(String content){
		browser.setText(content);
	}
	
	@Override
	public void setName(String name) {
		setPartName(name);
	}

	private void addKeyListener(){
		browser.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ESC){
					IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					activePage.hideView(activePage.findView(ID));
				}
			}
		});
	}
}
