package com.forexgame.ui.views;

import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.forexgame.controller.Controller;

public class NewsView extends ViewPart {

	public static final String ID = NewsView.class.getCanonicalName();
	private TableViewer viewer;
	
	public NewsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Color whiteColor = workbench.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		parent.setBackground(whiteColor);
		createTable(parent);
		viewer.setInput(Controller.INSTANCE.getNews());
		addDoubleClickListener(workbench);
		addKeyListener(workbench);
	 }

	private void addDoubleClickListener(IWorkbench workbench) {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if(!(selection instanceof StructuredSelection)) return;
				Object object = ((StructuredSelection)selection).getFirstElement();
				showNewsContentView(workbench, object);
			}

		});
	}

	private void addKeyListener(IWorkbench workbench) {
		viewer.getTable().addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				Table table = (Table)e.getSource();
				TableItem[] selection = table.getSelection();
				Object object = selection[0];
				if(e.keyCode == SWT.LF){
					showNewsContentView(workbench, object);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void showNewsContentView(IWorkbench workbench, Object object) {
		String newsHeading = Controller.INSTANCE.getNewsHeading(object);
		String newsContent = Controller.INSTANCE.getNewsContent(object);
		
		try {
			IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
			IViewPart view = activePage.showView(NewsContentView.ID);
			IEditableView newsContentView = (IEditableView)view;
			newsContentView.setTextContent(newsContent);
			newsContentView.setName(newsHeading);
			IWorkbenchPartReference activePartReference = activePage.getActivePartReference();
			if(activePartReference != null) {
				activePage.setPartState(activePartReference, IWorkbenchPage.STATE_MAXIMIZED);
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createTable(Composite parent){
		viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setHeaderVisible(true);
		
		viewer.setContentProvider(new IStructuredContentProvider() {
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				List news = Controller.INSTANCE.getNews();
				return news.toArray();
			}
		});
		
		createColumns(parent);
	}
	
	private void createColumns(Composite parent) {
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(90, true));
		layout.addColumnData(new ColumnWeightData(15, true));
		layout.addColumnData(new ColumnWeightData(30, true));
		viewer.getTable().setLayout(layout);

		TableViewerColumn column = createTableViewerColumn(Messages.NewsView_Heading);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return Controller.INSTANCE.getNewsHeading(element);
			}
		});
		
		column = createTableViewerColumn(Messages.NewsView_Author);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return Controller.INSTANCE.getNewsSource(element);
			}
		});
		
		column = createTableViewerColumn(Messages.NewsView_Date);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return Controller.INSTANCE.getNewsDate(element);
			}
		});		
	}

	private TableViewerColumn createTableViewerColumn(String name) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.CENTER);
		TableColumn column = viewerColumn.getColumn();
		column.setText(name);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
