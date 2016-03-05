import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.jface.dialogs.MessageDialog;
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
	private Random random;
	private Label p1;
	private Label p2;
	private Label p3;
	private static Canvas canvas;
	static Display d= Display.getDefault();
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
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\gallinagreta\\git\\Slut-Machine\\Slot machine\\slot.png"));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		shell.setSize(500, 533);
		shell.setText("SWT Application");
		shell.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\gallinagreta\\git\\Slut-Machine\\Slot machine\\slot.png"));
		
		oggetti.add(new Casella("mela", new Image(d, "mela.jpg/")));
		oggetti.add(new Casella("banana", new Image(d, "banana.jpg/")));
		oggetti.add(new Casella("pesca", new Image(d, "pesca.jpg/")));
		
		Canvas canvas2 = new Canvas(shell, SWT.NONE);
		canvas2.setBounds(194, 160, 109, 133);
		
		Canvas canvas3 = new Canvas(shell, SWT.NONE);
		canvas3.setBounds(326, 160, 109, 133);
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(65, 160, 109, 133);
		p1 = new Label(shell, SWT.NONE);
		p1.setAlignment(SWT.CENTER);
		p1.setBounds(65, 314, 142, 33);
		p1.setText("1000000");
		
		p2 = new Label(shell, SWT.NONE);
		p2.setAlignment(SWT.CENTER);
		p2.setBounds(225, 314, 44, 33);
		p2.setText("000");
		
		p3 = new Label(shell, SWT.NONE);
		p3.setAlignment(SWT.CENTER);
		p3.setBounds(286, 314, 90, 33);
		p3.setText("00000000");
		
		Label lblSpin = new Label(shell, SWT.NONE);
		lblSpin.setAlignment(SWT.CENTER);
		lblSpin.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblSpin.setBounds(397, 422, 61, 63);
		formToolkit.adapt(lblSpin, true, true);
		lblSpin.setText("SPIN");
		
		Label lblBetMax = new Label(shell, SWT.NONE);
		lblBetMax.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblBetMax.setAlignment(SWT.CENTER);
		lblBetMax.setBounds(258, 422, 55, 52);
		formToolkit.adapt(lblBetMax, true, true);
		lblBetMax.setText("BET MAX");
		
		Label lblBetOne = new Label(shell, SWT.NONE);
		lblBetOne.setAlignment(SWT.CENTER);
		lblBetOne.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblBetOne.setBounds(187, 422, 55, 52);
		formToolkit.adapt(lblBetOne, true, true);
		lblBetOne.setText("BET ONE");
		
		Label lblPalyTable = new Label(shell, SWT.NONE);
		lblPalyTable.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPalyTable.setAlignment(SWT.CENTER);
		lblPalyTable.setBounds(119, 422, 55, 52);
		formToolkit.adapt(lblPalyTable, true, true);
		lblPalyTable.setText("PALY TABLE");
		
		Label lblReset = new Label(shell, SWT.NONE);
		lblReset.setAlignment(SWT.CENTER);
		lblReset.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblReset.setBounds(44, 422, 55, 52);
		formToolkit.adapt(lblReset, true, true);
		lblReset.setText("RESET");
	}
}
