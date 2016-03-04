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

		Button btnReset = new Button(shell, SWT.NONE);
		btnReset.setBounds(10, 327, 75, 63);
		btnReset.setText("RESET");
		
		Button btnPlayTable = new Button(shell, SWT.NONE);
		btnPlayTable.setText("PLAY TABLE");
		btnPlayTable.setBounds(91, 327, 75, 63);
		
		Button btnBetOne = new Button(shell, SWT.NONE);
		btnBetOne.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int puntata;
				try {
					puntata=Integer.parseInt(p2.getText());
					puntata++;
					p2.setText(Integer.toString(puntata));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBetOne.setText("BET ONE");
		btnBetOne.setBounds(170, 327, 75, 63);
		
		Button btnBetMax = new Button(shell, SWT.NONE);
		btnBetMax.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int allin;
				try {
					allin=Integer.parseInt(p1.getText());
					p2.setText(Integer.toString(allin));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnBetMax.setText("BET MAX");
		btnBetMax.setBounds(251, 327, 75, 63);
		
		Button btnSpin = new Button(shell, SWT.NONE);
		btnSpin.setTouchEnabled(true);
		
		btnSpin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnSpin.setEnabled(false);
				final Thread tCas1;
				gc = new GC(canvas);
				gc2 = new GC(canvas2);
				gc3 = new GC(canvas3);
				tCas1 = new Thread () {
					@Override
					public void run () {
						t1Start = true;
						random = new Random();
						int k = 0, l;
						long t = 2;
						for(int j=0; j<random.nextInt(40 - 15)+15; j++, k++){
							if(k>2){
								k = 0;
							}
							for(int i=-80; i<oggetti.get(k).getImage().getBounds().height+30; i++){
								gc.drawImage(oggetti.get(k).getImage(), 0, i);
								//t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							System.out.println(k);
						}
						k--;
						gc.drawImage(oggetti.get(k).getImage(), 0, 0);
						t1F = true;
					}
				};
				if(!t1Start){
					tCas1.start ();
				}
				
				//--------------------------------------------------------------------------------------------------
				
				final Thread tCas2;
				tCas2 = new Thread () {
					@Override
					public void run () {
						t2Start = true;
						random = new Random();
						int k = 0, l;
						long t = 5;
						for(int j=0; j<random.nextInt(20 - 15)+15; j++, k++){
							if(k>2){
								k = 0;
							}
							
							if(j%5==0){
								t++;
							}
							for(int i=-80; i<oggetti.get(k).getImage().getBounds().height+30; i++){
								gc2.drawImage(oggetti.get(k).getImage(), 0, i);
								//t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							System.out.println(k);
						}
						k--;
						gc2.drawImage(oggetti.get(k).getImage(), 0, 0);
						t2F = true;
					}
				};
				if(!t2Start){
					tCas2.start ();
				}
				
				//-----------------------------------------------------------------------------------
				
				final Thread tCas3;
				tCas3 = new Thread () {
					@Override
					public void run () {
						t3Start = true;
						random = new Random();
						int k = 0, l;
						long t = 3;
						for(int j=0; j<random.nextInt(40 - 15)+15; j++, k++){
							if(k>2){
								k = 0;
							}
							
							for(int i=-80; i<oggetti.get(k).getImage().getBounds().height+30; i++){
								gc3.drawImage(oggetti.get(k).getImage(), 0, i);
								//t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							System.out.println(k);
						}
						k--;
						gc3.drawImage(oggetti.get(k).getImage(), 0, 0);
						t3F = true;
					}
					
				};
				
				if(!t3Start){
					tCas3.start();
				}
				
				//--------------------------------------------------------------------------------*/
				
				Thread tCas4 = null;
				tCas4 = new Thread () {
					@Override
					public void run () {
						while(true){
							if(t3F && t2F && t1F){
								Display.getDefault().asyncExec(new Runnable() {
									@Override
									public void run() {
					
										btnSpin.setEnabled(true);
									}
								});
								t1Start = false;
								t2Start = false;
								t3Start = false;
								break;
							}
						}
					}
				};
				tCas4.start();
				}
		});
		btnSpin.setBounds(333, 61, 75, 63);
		btnSpin.setBounds(382, 327, 75, 63);
		btnSpin.setText("SPIN");
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
