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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
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
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if(!(selection instanceof StructuredSelection)) return;
				
				Object object = ((StructuredSelection)selection).getFirstElement();
				String newsHeading = Controller.INSTANCE.getNewsHeading(object);
				String newsContent = Controller.INSTANCE.getNewsContent(object);
				
				try {
					IViewPart view = workbench.getActiveWorkbenchWindow().getActivePage().showView(NewsContentView.ID);
					IEditableView newsContentView = (IEditableView)view;
					newsContentView.setTextContent(newsContent);
					newsContentView.setName(newsHeading);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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
		
		column = createTableViewerColumn(Messages.NewsView_Source);
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
				return Controller.INSTANCE.getNewsDate(element).toString();
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
