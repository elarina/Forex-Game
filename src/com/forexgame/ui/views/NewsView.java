package com.forexgame.ui.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
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
		
		TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(70, true));
		layout.addColumnData(new ColumnWeightData(15, true));
		layout.addColumnData(new ColumnWeightData(15, true));
		
		viewer.getTable().setLayout(layout);
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
				//return collection of news
				return new Object[0];
			}
		});
		
		viewer.setLabelProvider(new ITableLabelProvider() {
			
			@Override
			public void removeListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isLabelProperty(Object element, String property) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getColumnText(Object element, int columnIndex) {
				return element.toString();
			}
			
			@Override
			public Image getColumnImage(Object element, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		TableColumn headingColumn = new TableColumn(viewer.getTable(), SWT.CENTER);
		headingColumn.setText(Messages.NewsView_Heading);
		TableColumn sourceColumn = new TableColumn(viewer.getTable(), SWT.CENTER);
		sourceColumn.setText(Messages.NewsView_Source);
		TableColumn dateColumn = new TableColumn(viewer.getTable(), SWT.CENTER);
		dateColumn.setText(Messages.NewsView_Date);
		
		viewer.setInput(new ArrayList<String>());
	 }
	
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
