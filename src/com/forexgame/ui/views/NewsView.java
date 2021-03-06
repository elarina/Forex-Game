package com.forexgame.ui.views;

import java.util.List;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.forexgame.controller.Controller;
import com.forexgame.controller.NewsViewerComparator;
import com.forexgame.model.News;

public class NewsView extends ViewPart {

	public static final String ID = NewsView.class.getCanonicalName();
	private TableViewer viewer;
	private Font boldFont;
	
	NewsViewerComparator comparator = new NewsViewerComparator();
	private Font normalFont;

	
	public NewsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Color whiteColor = workbench.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		parent.setBackground(whiteColor);

		GridLayout layout = new GridLayout(1, true);
		
//		parent.setLayout(layout);
//		
//		Text filtering = new Text(parent, SWT.BORDER);
//		
//		GridData filteringLayoutData = new GridData(SWT.FILL);
//		filteringLayoutData.heightHint = 14;

		createTable(parent);
		
		Table table = viewer.getTable();
//		GridData tableLayoutData = new GridData(SWT.FILL);
//		table.setLayoutData(tableLayoutData );
		viewer.setComparator(comparator);
		viewer.setInput(Controller.INSTANCE.getNews());
		addDoubleClickListener(workbench);
		addKeyListener(workbench);
	 }
	
	@Override
	public void dispose() {
		super.dispose();
		boldFont.dispose();
	}

	private void addDoubleClickListener(IWorkbench workbench) {
		IDoubleClickListener doubleClickListener = new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if(!(selection instanceof StructuredSelection)) return;

				Object object = ((StructuredSelection)selection).getFirstElement();
				showNewsContentView(workbench, object);
			}

		};
		viewer.addDoubleClickListener(doubleClickListener);
	}

	private void addKeyListener(IWorkbench workbench) {
		viewer.getTable().addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				Table table = (Table)e.getSource();
				TableItem[] selection = table.getSelection();
				if(selection.length > 0) {
					Object object = selection[0];
					if(e.keyCode == SWT.LF){
						showNewsContentView(workbench, object);
					}
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
		Controller.INSTANCE.setNewsRead(object, true);
		int oldId = viewer.getTable().getSelectionIndex();
		Controller.INSTANCE.save(object, oldId);
		
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
			view.setFocus();
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewer.refresh();
	}

	private void createTable(Composite parent){
		viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
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
		
		final Device display = parent.getDisplay();
		normalFont = viewer.getTable().getFont();
		FontDescriptor boldFontDescriptor = FontDescriptor.createFrom(normalFont).setStyle(SWT.BOLD);
		boldFont = boldFontDescriptor.createFont(display) ;

		TableViewerColumn column = createTableViewerColumn(Messages.NewsView_Heading, 1);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return Controller.INSTANCE.getNewsHeading(element);
			}
			
			@Override
			public Font getFont(Object element) {
				return defineFont(element);
			}
		});
		
		column = createTableViewerColumn(Messages.NewsView_Author, 2);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return Controller.INSTANCE.getNewsSource(element);
			}
			
			@Override
			public Font getFont(Object element) {
				return defineFont(element);
			}
		});
		
		column = createTableViewerColumn(Messages.NewsView_Date, 3);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return Controller.INSTANCE.getNewsDate(element);
			}
			
			@Override
			public Font getFont(Object element) {
				return defineFont(element);
			}
		});		
	}

	private Font defineFont(Object element) {
		boolean isRead = Controller.INSTANCE.isNewsRead(element);
		return isRead ? normalFont : boldFont ;
	}
	
	private TableViewerColumn createTableViewerColumn(String name, int index) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.CENTER);
		TableColumn column = viewerColumn.getColumn();
		column.setText(name);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(index));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(int index) {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int direction = comparator.getDirection();
				viewer.getTable().setSortDirection(direction);
				super.widgetSelected(e);
				viewer.refresh();
				TableItem[] items = viewer.getTable().getItems();
				Controller.INSTANCE.saveNewsList(items);				
			}
		};
	}

	@Override
	public void setFocus() {
		viewer.getTable().setFocus();
	}

}
