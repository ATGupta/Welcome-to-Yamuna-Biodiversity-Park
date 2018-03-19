
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class MainClass extends JFrame {
    
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        int h=(int)width,v=(int)height;
        new HomePage(h,v);
    }
    
}
