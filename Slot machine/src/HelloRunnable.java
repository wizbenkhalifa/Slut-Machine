import java.util.ArrayList;
import java.util.Random;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;

public class HelloRunnable implements Runnable {
	protected String s;

    public void run() {
        System.out.println(s);
    }

}