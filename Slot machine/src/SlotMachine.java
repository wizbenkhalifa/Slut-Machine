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
		p1.setText(Integer.toString(credito));
		p2.setText(Integer.toString(bet));
	}

	protected void createContents() {
		Color green = new Color(null, 218, 253, 218);
		shell = new Shell();
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				if (!on) {
					on = true;
					JFrame frame = new JFrame("Credito");
					String name = JOptionPane.showInputDialog(frame, "Inerisci credito");
					try {
						credito = Integer.parseInt(name);
					} catch (NumberFormatException e) {
						MessageDialog.openWarning(shell, "Errore", "valore inserito non valido");
						on = false;
					}
					on = true;
				}
			}
		});
		shell.setImage(SWTResourceManager.getImage(SlotMachine.class, "img/slot.png"));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		shell.setSize(500, 533);
		shell.setText("SWT Application");
		shell.setBackgroundImage(SWTResourceManager.getImage(SlotMachine.class, "/img/slot.png"));

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
		p1.setText("1000000");

		p2 = new Label(shell, SWT.NONE);
		p2.setAlignment(SWT.CENTER);
		p2.setBounds(225, 314, 44, 33);
		p2.setText("000");

		p3 = new Label(shell, SWT.NONE);
		p3.setAlignment(SWT.CENTER);
		p3.setBounds(286, 314, 92, 33);
		p3.setText("00000000");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/reset.png"));
		btnNewButton.setBounds(49, 428, 43, 43);
		formToolkit.adapt(btnNewButton, true, true);
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/paytable.png"));
		btnNewButton_1.setBounds(120, 428, 45, 44);
		formToolkit.adapt(btnNewButton_1, true, true);
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/betone.png"));
		btnNewButton_2.setBounds(192, 428, 42, 40);
		formToolkit.adapt(btnNewButton_2, true, true);
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/betmax.png"));
		btnNewButton_3.setBounds(263, 428, 45, 42);
		formToolkit.adapt(btnNewButton_3, true, true);
		
		Button button = new Button(shell, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(SlotMachine.class, "/img/spin.png"));
		button.setBounds(401, 425, 50, 50);
		formToolkit.adapt(button, true, true);
	}
}
