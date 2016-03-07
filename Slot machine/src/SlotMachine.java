import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class SlotMachine {
	private ArrayList<Casella> oggetti = new ArrayList<Casella>();
	private int Credito;
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
	private boolean on = false;
	
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
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				if(!on){
					on = true;
					JFrame frame = new JFrame("Credito");
				    String name = JOptionPane.showInputDialog(frame, "Inerisci credito");
				    try {
						Credito = Integer.parseInt(name);
					} catch (NumberFormatException e) {
						MessageDialog.openWarning(shell, "Errore", "valore inserito non valido");
						on = false;
					}
				    on = true;
				}
			}
		});
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
		canvas.setBounds(30, 115, 100, 104);

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
				try {
					p2.setText(Integer.toString(Integer.parseInt(p2.getText()++)));
				} catch (NumberFormatException e1) {
					MessageDialog.openError(shell, "Errore", "Valore non valido");
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
							for(int j=0; j<random.nextInt(20 - 15)+15; j++, k++){
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
							for(int i=-80; i<oggetti.get(k).getImage().getBounds().height/2-45; i++){
								gc.drawImage(oggetti.get(k).getImage(), 0, i);
								//t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
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
							int k = 0;
							long t = 5;
							for(int j=0; j<random.nextInt(10 - 5)+5; j++, k++){
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
							}
							k--;
							for(int i=-80; i<oggetti.get(k).getImage().getBounds().height/2-45; i++){
								gc2.drawImage(oggetti.get(k).getImage(), 0, i);
								//t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
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
							for(int j=0; j<random.nextInt(20 - 15)+15; j++, k++){
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
							}
							k--;
							for(int i=-80; i<oggetti.get(k).getImage().getBounds().height/2-45; i++){
								gc3.drawImage(oggetti.get(k).getImage(), 0, i);
								//t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t3F = true;
						}
						
					};
					
					if(!t3Start){
						tCas3.start();
					}
					
					//--------------------------------------------------------------------------------*/
					
					Thread tCas4 = null;
					tCas4 = new Thread () {
						@SuppressWarnings("deprecation")
						@Override
						public void run () {
							while(true){
								System.out.println(t3F);
								if(t3F && t2F && t1F){
									System.out.println("entrato");
									org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable() {
										@Override
										public void run() {
											btnSpin.setEnabled(true);
										}
									});
									t1Start = false;
									t2Start = false;
									t3Start = false;
									t3F = false;
									t2F= false ;
									t1F = false;
									tCas1.interrupt();
									tCas2.interrupt();
									tCas3.interrupt();
									break;
								}
							}
							this.interrupt();
						}
					};
					tCas4.start();
					}
		});
		btnSpin.setBounds(333, 61, 75, 63);
		btnSpin.setBounds(382, 327, 75, 63);
		btnSpin.setText("SPIN");
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
		lblSpin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
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
						for(int j=0; j<random.nextInt(20 - 15)+15; j++, k++){
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
						for(int i=-80; i<oggetti.get(k).getImage().getBounds().height/2-45; i++){
							gc.drawImage(oggetti.get(k).getImage(), 0, i);
							//t++;
							try {
								Thread.sleep(t);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
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
						int k = 0;
						long t = 5;
						for(int j=0; j<random.nextInt(10 - 5)+5; j++, k++){
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
						}
						k--;
						for(int i=-80; i<oggetti.get(k).getImage().getBounds().height/2-45; i++){
							gc2.drawImage(oggetti.get(k).getImage(), 0, i);
							//t++;
							try {
								Thread.sleep(t);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
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
						for(int j=0; j<random.nextInt(20 - 15)+15; j++, k++){
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
						}
						k--;
						for(int i=-80; i<oggetti.get(k).getImage().getBounds().height/2-45; i++){
							gc3.drawImage(oggetti.get(k).getImage(), 0, i);
							//t++;
							try {
								Thread.sleep(t);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						t3F = true;
					}
					
				};
				
				if(!t3Start){
					tCas3.start();
				}
				
				//--------------------------------------------------------------------------------*/
				
				Thread tCas4 = null;
				tCas4 = new Thread () {
					@SuppressWarnings("deprecation")
					@Override
					public void run () {
						while(true){
							System.out.println(t3F);
							if(t3F && t2F && t1F){
								System.out.println("entrato");
								org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable() {
									@Override
									public void run() {
										btnSpin.setEnabled(true);
									}
								});
								t1Start = false;
								t2Start = false;
								t3Start = false;
								t3F = false;
								t2F= false ;
								t1F = false;
								tCas1.interrupt();
								tCas2.interrupt();
								tCas3.interrupt();
								break;
							}
						}
						this.interrupt();
					}
				};
				tCas4.start();
				}
		});
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
