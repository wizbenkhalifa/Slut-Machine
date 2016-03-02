import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

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
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.wb.swt.SWTResourceManager;

import java.awt.*;
import javax.swing.*;

import java.applet.Applet;


public class SlotMachine {
	private ArrayList<Casella> oggetti = new ArrayList<Casella>();
	protected Shell shell;
	private Text cas1;
	private Text cas2;
	private Text cas3;
	private Random random;
	private Label p1;
	private Label p2;
	private Label p3;
	Display Display;
	private GC gc;	
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
		shell.setSize(939, 462);
		shell.setText("SWT Application");
		oggetti.add(new Casella("mela", new Image(Display, "mela.jpg/")));
		oggetti.add(new Casella("banana", new Image(Display, "banana.jpg/")));
		oggetti.add(new Casella("pesca", new Image(Display, "pesca.jpg/")));
		
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(504, 12, 100, 104);
		
		cas1 = new Text(shell, SWT.BORDER);
		cas1.setBounds(30, 10, 82, 66);
		
		cas2 = new Text(shell, SWT.BORDER);
		cas2.setBounds(194, 10, 76, 66);
		
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
		btnSpin.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		btnSpin.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		
		btnSpin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				random = new Random();
				int k =0, l, t = 5;
				gc = new GC(canvas);
				for(int j=0; j<random.nextInt(40 - 15)+15; j++, k++){
					if(k>2){
						k = 0;
					}
					for(int i=0; i<oggetti.get(k).getImage().getBounds().height-10; i++){
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
				System.out.println("fuori" + k);
				gc.drawImage(oggetti.get(k).getImage(), 0, 0);
				cas1.setText(oggetti.get(random.nextInt(3)).getNome());
				cas2.setText(oggetti.get(random.nextInt(3)).getNome());
				cas3.setText(oggetti.get(random.nextInt(3)).getNome());
			}
		});
		btnSpin.setText("SPIN");
		btnSpin.setBounds(333, 61, 75, 63);
		btnSpin.setBounds(382, 327, 75, 63);
		
		p1 = new Label(shell, SWT.NONE);
		p1.setAlignment(SWT.CENTER);
		p1.setBounds(10, 265, 216, 15);
		p1.setText("1000000");
		
		p2 = new Label(shell, SWT.NONE);
		p2.setAlignment(SWT.CENTER);
		p2.setBounds(232, 265, 94, 15);
		p2.setText("0000000000");
		
		p3 = new Label(shell, SWT.NONE);
		p3.setAlignment(SWT.CENTER);
		p3.setBounds(333, 265, 145, 15);
		p3.setText("00000000000000");
	}
}
