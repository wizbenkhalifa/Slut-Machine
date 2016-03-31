import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

public class SlotMachine {
	private ArrayList<Casella> oggetti = new ArrayList<Casella>();
	private static int credito;
	private static int bet;
	private static int vincita;
	protected Shell finestra;
	protected Shell shlGiocoDemmerda;
	private Random random;
	private static Label p1;
	private static Label p2;
	private static Label p3;
	static Display d = Display.getDefault();
	private GC gc;
	private GC gc2;
	private GC gc3;
	private boolean t1F = false;
	private boolean t2F = false;
	private boolean t3F = false;
	private boolean t1Start = false;
	private boolean t2Start = false;
	private boolean t3Start = false;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private boolean on = false;
	private int[] k = { 0, 0, 0 };
	private int[] i = { 0, 0, 0 };
	JFrame frame = new JFrame("Credito");
	File audioFile = new File(""); 
	
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
		shlGiocoDemmerda.open();
		shlGiocoDemmerda.layout();
		while (!shlGiocoDemmerda.isDisposed()) {
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
				p3.setText(Integer.toString(vincita));
			}
		});
	}

	protected void createContents() {
		Color green = new Color(null, 218, 253, 218);
		shlGiocoDemmerda = new Shell();
		shlGiocoDemmerda.setImage(SWTResourceManager.getImage(SlotMachine.class, "img/slot.png"));
		shlGiocoDemmerda.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlGiocoDemmerda.setSize(500, 533);
		shlGiocoDemmerda.setText("Slot Machine");
		shlGiocoDemmerda.setBackgroundImage(SWTResourceManager.getImage(SlotMachine.class, "/img/slot.png"));
		
		Canvas canvas2 = new Canvas(shlGiocoDemmerda, SWT.NONE);
		canvas2.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				random = new Random();
				gc2 = new GC(canvas2);
				gc2.drawImage(oggetti.get(k[1] =random.nextInt(7)).getImage(), 0, 0);
			}
		});
		canvas2.setBounds(192, 158, 116, 135);

		Canvas canvas3 = new Canvas(shlGiocoDemmerda, SWT.NONE);
		canvas3.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				random = new Random();
				gc3 = new GC(canvas3);
				gc3.drawImage(oggetti.get(k[2] =random.nextInt(7)).getImage(), 0, 0);
			}
		});
		canvas3.setBounds(326, 158, 110, 135);
		
		Canvas canvas1 = new Canvas(shlGiocoDemmerda, SWT.NONE);
		canvas1.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				random = new Random();
				gc = new GC(canvas1);
				gc.drawImage(oggetti.get(k[0] = random.nextInt(7)).getImage(), 0, 0);
			}
		});
		canvas1.setBounds(65, 158, 110, 135);
		
		oggetti.add(new Casella("banana", new Image(d, "src/img/mela.png/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/bar.png/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/anguria.png/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/limone.png/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/ciliegia.png/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/banana.png/")));
		oggetti.add(new Casella("pesca", new Image(d, "src/img/pesca.png/")));
		
		shlGiocoDemmerda.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				if (!on) {
				JFrame frame = new JFrame("Credito");
					try {
						on = true;
						credito = Integer.parseInt(JOptionPane.showInputDialog(frame, "Inerisci credito"));
					} catch (NumberFormatException e) {
						MessageDialog.openWarning(shlGiocoDemmerda, "Errore", "valore inserito non valido");
						on = false;
					}
					aggiornaCredito();
				}
			}
		});

		p1 = new Label(shlGiocoDemmerda, SWT.NONE);
		p1.setAlignment(SWT.CENTER);
		p1.setBounds(65, 314, 142, 33);
		p1.setText("00000000000000");

		p2 = new Label(shlGiocoDemmerda, SWT.NONE);
		p2.setAlignment(SWT.CENTER);
		p2.setBounds(225, 314, 44, 33);
		p2.setText("000");

		p3 = new Label(shlGiocoDemmerda, SWT.NONE);
		p3.setAlignment(SWT.CENTER);
		p3.setBounds(286, 314, 92, 33);
		p3.setText("00000000");

		Button reset = new Button(shlGiocoDemmerda, SWT.NONE);
		reset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				credito += bet;
				bet = 0;
				vincita = 0;
				aggiornaCredito();
			}
		});
		reset.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/reset.png"));
		reset.setBounds(49, 428, 43, 43);
		formToolkit.adapt(reset, true, true);

		Button payt = new Button(shlGiocoDemmerda, SWT.NONE);
		payt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(shlGiocoDemmerda, "PlayTable", " - Bar x 3 -> x10\n- Anguria x 3 -> x5");

			}
		});
		payt.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/paytable.png"));
		payt.setBounds(120, 428, 45, 44);
		formToolkit.adapt(payt, true, true);

		Button beto = new Button(shlGiocoDemmerda, SWT.NONE);
		beto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (bet < 99 && credito > 0) {
					bet += 1;
					credito -= 1;
					aggiornaCredito();
				}
			}
		});
		beto.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/betone.png"));
		beto.setBounds(192, 428, 42, 40);
		formToolkit.adapt(beto, true, true);

		Button betm = new Button(shlGiocoDemmerda, SWT.NONE);
		betm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (bet < 99 && credito >= 99) {
					bet = 99;
					credito -= 99;
					aggiornaCredito();
				}
			}
		});
		betm.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/betmax.png"));
		betm.setBounds(263, 428, 45, 42);
		formToolkit.adapt(betm, true, true);

		Button spin = new Button(shlGiocoDemmerda, SWT.NONE);
		spin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (bet > 0) {
					 try { 
						 File audioFile = new File("spin.wav");
						 AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
						Clip clip = AudioSystem.getClip();
						 clip.open(audioStream);
				         clip.start();
					 }catch(UnsupportedAudioFileException | IOException e2) { // TODO
						 e2.printStackTrace(); 
					 } catch (LineUnavailableException e1){// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
					int d[] = new int[3];
					spin.setEnabled(false);
					spin.setVisible(false);
					beto.setEnabled(false);
					beto.setVisible(false);
					betm.setEnabled(false);
					betm.setVisible(false);
					reset.setEnabled(false);
					reset.setVisible(false);
					Thread tCas1;
					tCas1 = new Thread() {
						@Override
						public void run() {
							t1Start = true;
							random = new Random();
							//k[0] = 0;
							long t = 2;
							for (int j = 0; j < random.nextInt(20 - 15) + 15; j++, k[0]++) {
								if (k[0] > 6) {
									k[0] = 0;
								}
								for (i[0] = -120; i[0] < oggetti.get(k[0]).getImage().getBounds().height+190; i[0]++) {
									Display.getDefault().asyncExec(new Runnable() {
										public void run() {
											gc.drawImage(oggetti.get(k[0]).getImage(), 0, i[0]);
										}
									});
									try {
										Thread.sleep(t);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
							if(k[0]>6){
								k[0] = 0;
							}
							d[0] = k[0];
							for (i[0] = -120; i[0] < oggetti.get(k[0]).getImage().getBounds().height / 2 - 60; i[0]++) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										gc.drawImage(oggetti.get(k[0]).getImage(), 0, i[0]);
									}
								});
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t1F = true;
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
							//k[1] = 0;
							long t = 2;
							for (int j = 0; j < random.nextInt(10 - 5) + 5; j++, k[1]++) {
								if (k[1] > 6) {
									k[1] = 0;
								}
								for (i[1] = -120; i[1] < oggetti.get(k[1]).getImage().getBounds().height + 190; i[1]++) {
									Display.getDefault().asyncExec(new Runnable() {
										public void run() {
											gc2.drawImage(oggetti.get(k[1]).getImage(), 0, i[1]);
										}
									});
									try {
										Thread.sleep(t);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
							if(k[1]>6){
								k[1] = 0;
							}
							d[1] = k[1];
							for (i[1] = -120; i[1] < oggetti.get(k[1]).getImage().getBounds().height / 2 - 60; i[1]++) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										gc2.drawImage(oggetti.get(k[1]).getImage(), 0, i[1]);
									}
								});
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t2F = true;
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
							//k[2] = 0;
							long t = 3;
							for (int j = 0; j < random.nextInt(20 - 15) + 15; j++, k[2]++) {
								if (k[2] > 6) {
									k[2] = 0;
								}

								for (i[2] = -120; i[2] < oggetti.get(k[2]).getImage().getBounds().height + 190; i[2]++) {
									Display.getDefault().asyncExec(new Runnable() {
										public void run() {
											gc3.drawImage(oggetti.get(k[2]).getImage(), 0, i[2]);
										}
									});
									try {
										Thread.sleep(t);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
							if(k[2]>6){
								k[2] = 0;
							}
							d[2] = k[2];
							for (i[2] = -120; i[2] < oggetti.get(k[2]).getImage().getBounds().height / 2 - 60; i[2]++) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										gc3.drawImage(oggetti.get(k[2]).getImage(), 0, i[2]);
									}
								});
								try {
									Thread.sleep(t);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							t3F = true;
						}
					};

					if (!t3Start) {
						tCas3.start();
					}

					// --------------------------------------------------------------------------------*/

					Thread tCas4 = null;
					tCas4 = new Thread() {
						@Override
						public void run() {
							while (true) {
								System.out.println("t1: " + t1F + " t2: " + t2F+ " t3: " + t3F);
								if (t3F && t2F && t1F) {
									System.out.println("o");
									org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable() {
										@Override
										public void run() {
											beto.setEnabled(true);
											beto.setVisible(true);
											betm.setEnabled(true);
											betm.setVisible(true);
											reset.setEnabled(true);
											reset.setVisible(true);
											spin.setEnabled(true);
											spin.setVisible(true);
										}
									});
									t1Start = false;
									t2Start = false;
									t3Start = false;
									t3F = false;
									t2F = false;
									t1F = false;
									/*tCas1.stop();
									tCas2.stop();
									tCas3.stop();*/
									if (oggetti.get(d[0]).compareTo(oggetti.get(d[1]), oggetti.get(d[2]))) {
										try { 
											File audioFile = new File("jackpot.mp3");
											 AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
											 Clip clip = AudioSystem.getClip();
											 clip.open(audioStream);
									         clip.start();
										 }catch(UnsupportedAudioFileException | IOException e2) { // TODO
											 e2.printStackTrace(); 
										 } catch (LineUnavailableException e1){// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
										vincita = bet * 2;
										credito += vincita;
										vincita = 0;
										bet = 0;
									} else if(oggetti.get(d[0]).equals(oggetti.get(1)) && oggetti.get(d[0]).compareTo(oggetti.get(d[1]), oggetti.get(d[2]))){
										try { 
											File audioFile = new File("jackpot.mp3");
											 AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
											 Clip clip = AudioSystem.getClip();
											 clip.open(audioStream);
									         clip.start();
										 }catch(UnsupportedAudioFileException | IOException e2) { // TODO
											 e2.printStackTrace(); 
										 } catch (LineUnavailableException e1){// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										vincita = bet * 10;
										credito += vincita;
										bet = 0;
										vincita = 0;
									} else if(oggetti.get(d[0]).equals(oggetti.get(2)) && oggetti.get(d[0]).compareTo(oggetti.get(d[1]), oggetti.get(d[2]))){
										try { 
											File audioFile = new File("jackpot.mp3");
											 AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
											 Clip clip = AudioSystem.getClip();
											 clip.open(audioStream);
									         clip.start();
										 }catch(UnsupportedAudioFileException | IOException e2) { // TODO
											 e2.printStackTrace(); 
										 } catch (LineUnavailableException e1){// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										vincita = bet * 5;
										credito += vincita;
										vincita = 0;
										bet = 0;
									}else{
										System.out.println("o");
										bet = 0;
										vincita = 0;
									}
									
									if (credito <= 0) {
										credito = Integer.parseInt(JOptionPane.showInputDialog(frame,
												"Credito esaurito, reinserisci per continuare: "));
									}
										aggiornaCredito();
									break;
								}
							}
						}
					};
					
					tCas4.start();
				} else {
					MessageDialog.openWarning(shlGiocoDemmerda, "Avviso", "Devi inserire il credito");
				}
			}
		});
		spin.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/spin.png"));
		spin.setBounds(401, 425, 50, 50);
		formToolkit.adapt(spin, true, true);
	}
}
