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


public class SlotMachine {
	private ArrayList<String> oggetti = new ArrayList<String>();
	protected Shell shell;

	private Text text;
	private Text text_1;
	private Text text_2;


	private Text cas1;
	private Text cas2;
	private Text cas3;
	private Random random;

	
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
		shell.setSize(504, 449);
		shell.setText("SWT Application");

		
		oggetti.add("mela");
		oggetti.add("banana");
		oggetti.add("pesca");
		
		cas1 = new Text(shell, SWT.BORDER);
		cas1.setBounds(44, 231, 82, 66);
		
		cas2 = new Text(shell, SWT.BORDER);
		cas2.setBounds(169, 231, 76, 66);
		
		cas3 = new Text(shell, SWT.BORDER);
		cas3.setBounds(320, 231, 76, 66);
		
		

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
					puntata=Integer.parseInt(text_1.getText());
					puntata++;
					text_1.setText(Integer.toString(puntata));
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
					allin=Integer.parseInt(text.getText());
					text_1.setText(Integer.toString(allin));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
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
		
		text = new Text(shell, SWT.BORDER);
		text.setText("3456");
		text.setBounds(10, 246, 216, 53);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setText("0");
		text_1.setBounds(232, 246, 94, 53);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(333, 246, 145, 53);

		btnSpin.setBounds(382, 327, 75, 63);

	}
}
