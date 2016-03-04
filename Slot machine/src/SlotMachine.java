import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;


public class SlotMachine {
	private ArrayList<Casella> oggetti = new ArrayList<Casella>();
	protected Shell shell;
	private Text cas3;
	private Random random;
	private Label p1;
	private Label p2;
	private Label p3;
	private static Canvas canvas;
	Display Display;
	private GC gc;	
	private GC gc2;	
	private GC gc3;	
	static final  private Button btnSpin =  null;
	private boolean t1F = false;
	private boolean t2F = false;
	private boolean t3F = false;
	private boolean t1Start = false;
	private boolean t2Start = false;
	private boolean t3Start = false;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	public static void main(String[] args) {
		try {
			SlotMachine window = new SlotMachine();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	
	protected void createContents() {
		Color green = new Color(null,218,253,218);
		shell = new Shell();

		shell.setSize(497, 462);
		shell.setText("SWT Application");
		
		oggetti.add(new Casella("mela", new Image(Display, "mela.jpg/")));
		oggetti.add(new Casella("banana", new Image(Display, "banana.jpg/")));
		oggetti.add(new Casella("pesca", new Image(Display, "pesca.jpg/")));
		
		Canvas canvas2 = new Canvas(shell, SWT.NONE);
		canvas2.setBounds(194, 115, 100, 104);
		
		Canvas canvas3 = new Canvas(shell, SWT.NONE);
		canvas3.setBounds(346, 115, 100, 104);
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(30, 115, 100, 104);
		
		cas3 = new Text(shell, SWT.BORDER);
		cas3.setBounds(346, 10, 76, 66);
		p1 = new Label(shell, SWT.NONE);
		p1.setAlignment(SWT.CENTER);
		p1.setBounds(30, 265, 100, 15);
		p1.setText("1000000");
		
		p2 = new Label(shell, SWT.NONE);
		p2.setAlignment(SWT.CENTER);
		p2.setBounds(194, 265, 100, 15);
		p2.setText("0000000000");
		
		p3 = new Label(shell, SWT.NONE);
		p3.setAlignment(SWT.CENTER);
		p3.setBounds(346, 265, 111, 15);
		p3.setText("00000000000000");
	}
}
