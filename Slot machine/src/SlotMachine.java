import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Label;


public class SlotMachine {
	private ArrayList<String> oggetti = new ArrayList<String>();
	protected Shell shell;
	private Text cas1;
	private Text cas2;
	private Text cas3;
	private Random random;
	private Label p1;
	private Label p2;
	private Label p3;

	
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
		shell = new Shell();
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				text_1.setTouchEnabled(false);
			}
		});
		shell.setSize(504, 449);
		shell.setText("SWT Application");

		
		oggetti.add("mela");
		oggetti.add("banana");
		oggetti.add("pesca");
		
		cas1 = new Text(shell, SWT.BORDER);
		cas1.setBounds(39, 131, 82, 66);
		
		cas2 = new Text(shell, SWT.BORDER);
		cas2.setBounds(195, 131, 76, 66);
		
		cas3 = new Text(shell, SWT.BORDER);
		cas3.setBounds(360, 131, 76, 66);

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
		btnSpin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				random = new Random();
				cas1.setText(oggetti.get(random.nextInt(3)));
				cas2.setText(oggetti.get(random.nextInt(3)));
				cas3.setText(oggetti.get(random.nextInt(3)));
			}
		});
		btnSpin.setText("SPIN");

		btnSpin.setBounds(382, 327, 75, 63);

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
