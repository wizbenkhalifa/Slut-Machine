import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class SlotMachine {

	protected Shell shell;

	
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
		
		Button btnReset = new Button(shell, SWT.NONE);
		btnReset.setBounds(10, 327, 75, 63);
		btnReset.setText("RESET");
		
		Button btnPlayTable = new Button(shell, SWT.NONE);
		btnPlayTable.setText("PLAY TABLE");
		btnPlayTable.setBounds(89, 327, 75, 63);
		
		Button btnBetOne = new Button(shell, SWT.NONE);
		btnBetOne.setText("BET ONE");
		btnBetOne.setBounds(170, 327, 75, 63);
		
		Button btnBetMax = new Button(shell, SWT.NONE);
		btnBetMax.setText("BET MAX");
		btnBetMax.setBounds(251, 327, 75, 63);
		
		Button btnSpin = new Button(shell, SWT.NONE);
		btnSpin.setText("SPIN");
		btnSpin.setBounds(382, 327, 75, 63);

	}
}
