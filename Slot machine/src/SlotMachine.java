import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SlotMachine {
	private ArrayList<String> oggetti = new ArrayList<String>();
	protected Shell shell;
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		oggetti.add("mela");
		oggetti.add("banana");
		oggetti.add("pesca");
		
		cas1 = new Text(shell, SWT.BORDER);
		cas1.setBounds(10, 32, 82, 66);
		
		cas2 = new Text(shell, SWT.BORDER);
		cas2.setBounds(155, 32, 76, 66);
		
		cas3 = new Text(shell, SWT.BORDER);
		cas3.setBounds(289, 32, 76, 66);
		
		Button btnProva = new Button(shell, SWT.NONE);
		btnProva.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				random = new Random();
				cas1.setText(oggetti.get(random.nextInt(3)));
				cas2.setText(oggetti.get(random.nextInt(3)));
				cas3.setText(oggetti.get(random.nextInt(3)));
				//if()
			}
		});
		btnProva.setBounds(44, 145, 75, 25);
		btnProva.setText("prova");

	}
}
