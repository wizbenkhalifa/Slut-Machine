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
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;

public class SlotMachine {
	private ArrayList<Casella> oggetti = new ArrayList<Casella>();
	private static int credito;
	private static int bet;
	private static int vincita;
	protected Shell shell;
	private Random random;
	private static Label p1;
	private static Label p2;
	private Label p3;
	private static Canvas canvas;
	static Display d = Display.getDefault();
	private GC gc;
	private GC gc2;
	private GC gc3;
	static final private Button btnSpin = null;
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

	public static void aggiornaCredito() {
		org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				p1.setText(Integer.toString(credito));
				p2.setText(Integer.toString(bet));
			}
		});
	}

	protected void createContents() {
		Color green = new Color(null, 218, 253, 218);
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(SlotMachine.class, "img/slot.png"));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		shell.setSize(500, 533);
		shell.setText("SWT Application");
		shell.setBackgroundImage(SWTResourceManager.getImage(SlotMachine.class, "/img/slot.png"));
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				if (!on) {
					JFrame frame = new JFrame("Credito");
					try {
						credito = Integer.parseInt(JOptionPane.showInputDialog(frame, "Inerisci credito"));
						on = true;
					} catch (NumberFormatException e) {
						MessageDialog.openWarning(shell, "Errore", "valore inserito non valido");
						on = false;
					}
					aggiornaCredito();
				}
			}
		});

		oggetti.add(new Casella("mela", new Image(d, "src/img/mela.jpg/")));
		oggetti.add(new Casella("banana", new Image(d, "src/img/banana.jpg/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/pesca.jpg/")));

		Canvas canvas2 = new Canvas(shell, SWT.NONE);
		canvas2.setBounds(194, 160, 109, 133);

		Canvas canvas3 = new Canvas(shell, SWT.NONE);
		canvas3.setBounds(326, 160, 109, 133);

		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(30, 115, 100, 104);
		canvas.setBounds(65, 160, 109, 133);

		p1 = new Label(shell, SWT.NONE);
		p1.setAlignment(SWT.CENTER);
		p1.setBounds(65, 314, 142, 33);
		p1.setText("00000000000000");

		p2 = new Label(shell, SWT.NONE);
		p2.setAlignment(SWT.CENTER);
		p2.setBounds(225, 314, 44, 33);
		p2.setText("000");

		p3 = new Label(shell, SWT.NONE);
		p3.setAlignment(SWT.CENTER);
		p3.setBounds(286, 314, 92, 33);
		p3.setText("00000000");

		Label lblSpin = new Label(shell, SWT.NONE);
		lblSpin.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSpin.setAlignment(SWT.CENTER);
		lblSpin.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblSpin.setBounds(403, 35, 61, 63);
		formToolkit.adapt(lblSpin, true, true);
		lblSpin.setText("SPIN");

		Label lblBetMax = new Label(shell, SWT.NONE);
		lblBetMax.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblBetMax.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblBetMax.setAlignment(SWT.CENTER);
		lblBetMax.setBounds(323, 35, 55, 52);
		formToolkit.adapt(lblBetMax, true, true);
		lblBetMax.setText("BET MAX");

		Label lblBetOne = new Label(shell, SWT.NONE);
		lblBetOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(bet < 99 && credito > 0){
					bet += 1;
					credito -= 1;
					aggiornaCredito();
				}
			}
		});
		lblBetOne.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblBetOne.setAlignment(SWT.CENTER);
		lblBetOne.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblBetOne.setBounds(92, 35, 55, 52);
		formToolkit.adapt(lblBetOne, true, true);
		lblBetOne.setText("BET ONE");

		Label lblReset = new Label(shell, SWT.NONE);
		lblReset.addTouchListener(new TouchListener() {
			public void touch(TouchEvent arg0) {
				System.out.println("reset");
			}
		});
		lblReset.setImage(SWTResourceManager.getImage(SlotMachine.class, "src/img/reset.png"));
		lblReset.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		lblReset.setAlignment(SWT.CENTER);
		lblReset.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblReset.setBounds(214, 35, 55, 52);
		formToolkit.adapt(lblReset, true, true);

		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/reset.png"));
		btnNewButton.setBounds(49, 428, 43, 43);
		formToolkit.adapt(btnNewButton, true, true);
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openError(shell, "Coglione!!!!!", "NON GIOCARE D'AZZARDO!!!");
				
			}
		});
		btnNewButton_1.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/paytable.png"));
		btnNewButton_1.setBounds(120, 428, 45, 44);
		formToolkit.adapt(btnNewButton_1, true, true);
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(bet < 99 && credito > 0){
					bet += 1;
					credito -= 1;
					aggiornaCredito();
				}
			}
		});
		btnNewButton_2.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/betone.png"));
		btnNewButton_2.setBounds(192, 428, 42, 40);
		formToolkit.adapt(btnNewButton_2, true, true);
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(bet<99 && credito >= 99){
					bet = 99;
					credito -= 99;
					aggiornaCredito();
				}
			}
		});
		btnNewButton_3.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/betmax.png"));
		btnNewButton_3.setBounds(263, 428, 45, 42);
		formToolkit.adapt(btnNewButton_3, true, true);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (bet > 0) {
					System.out.println("cioa");
					Thread tCas1;
					gc = new GC(canvas);
					gc2 = new GC(canvas2);
					gc3 = new GC(canvas3);
					tCas1 = new Thread() {
						@Override
						public void run() {
							t1Start = true;
							random = new Random();
							int k = 0, l;
							long t = 2;
							for (int j = 0; j < random.nextInt(20 - 15) + 15; j++, k++) {
								if (k > 2) {
									k = 0;
								}
								for (int i = -80; i < oggetti.get(k).getImage().getBounds().height + 30; i++) {
									gc.drawImage(oggetti.get(k).getImage(), 0, i);
									// t++;
									try {
										Thread.sleep(t);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
								System.out.println(k);
							}
							k--;
							for (int i = -80; i < oggetti.get(k).getImage().getBounds().height / 2 - 45; i++) {
								gc.drawImage(oggetti.get(k).getImage(), 0, i);
								// t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t1F = true;
							this.stop();
						}
					};
					if (!t1Start) {
						tCas1.start();
					}

					// --------------------------------------------------------------------------------------------------

					Thread tCas2;
					tCas2 = new Thread() {
						@Override
						public void run() {
							t2Start = true;
							random = new Random();
							int k = 0;
							long t = 5;
							for (int j = 0; j < random.nextInt(10 - 5) + 5; j++, k++) {
								if (k > 2) {
									k = 0;
								}

								if (j % 5 == 0) {
									t++;
								}
								for (int i = -80; i < oggetti.get(k).getImage().getBounds().height + 40; i++) {
									gc2.drawImage(oggetti.get(k).getImage(), 0, i);
									try {
										Thread.sleep(t);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
							k--;
							for (int i = -80; i < oggetti.get(k).getImage().getBounds().height / 2 - 45; i++) {
								gc2.drawImage(oggetti.get(k).getImage(), 0, i);
								// t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t2F = true;
							this.stop();
						}
					};
					if (!t2Start) {
						tCas2.start();
					}

					// -----------------------------------------------------------------------------------

					Thread tCas3;
					tCas3 = new Thread() {
						@Override
						public void run() {
							t3Start = true;
							random = new Random();
							int k = 0, l;
							long t = 3;
							for (int j = 0; j < random.nextInt(20 - 15) + 15; j++, k++) {
								if (k > 2) {
									k = 0;
								}

								for (int i = -80; i < oggetti.get(k).getImage().getBounds().height + 30; i++) {
									gc3.drawImage(oggetti.get(k).getImage(), 0, i);
									// t++;
									try {
										Thread.sleep(t);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
							k--;
							for (int i = -80; i < oggetti.get(k).getImage().getBounds().height / 2 - 45; i++) {
								gc3.drawImage(oggetti.get(k).getImage(), 0, i);
								// t++;
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t3F = true;
							this.stop();
						}
					};

					if (!t3Start) {
						tCas3.start();
					}

					// --------------------------------------------------------------------------------*/

					Thread tCas4 = null;
					tCas4 = new Thread() {
						@SuppressWarnings("deprecation")
						@Override
						public void run() {
							while (true) {
								if (t3F && t2F && t1F) {
									/*org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable() {
										@Override
										public void run() {
											btnSpin.setEnabled(true);
										}
									});*/
									t1Start = false;
									t2Start = false;
									t3Start = false;
									t3F = false;
									t2F = false;
									t1F = false;
									tCas1.stop();
									tCas2.stop();
									tCas3.stop();
									if(oggetti.get(0).compareTo(oggetti.get(1), oggetti.get(2))){
										vincita = bet * 2;
										credito += bet;
										aggiornaCredito();
									}else{
										bet = 0;
										aggiornaCredito();
									}
									break;
								}
							}
							//this.destroy();
						}
					};
					tCas4.start();
				} else {
					MessageDialog.openWarning(shell, "Avviso", "Devi inserire il credito");
				}
			}
		});
		button.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/spin.png"));
		button.setBounds(401, 425, 50, 50);
		formToolkit.adapt(button, true, true);
	}
}
