
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ImagePlayer extends JFrame{
    
    private int horizontal,vertical;
    private JScrollPane scroll;
    private JPanel panel;
    private JLabel label;
    private ImageIcon image,icon;
    private int width,height,screenWidth,screenHeight;
    private String location;
    private JButton back,dec,inc;
    private JPanel bottomPane;
    
    public ImagePlayer(int h,int w,String l){
        super("JImagePlayer");
        horizontal=h;vertical=w;
        screenWidth=w;screenHeight=h;
        location=l;
        setSize(horizontal,vertical);
        getContentPane().setBackground(new Color(0,0,0));
        
        initializeComponents();
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setDimensions(height,width);
        setLabel();
        setBottomPane();
        setComponents();
        actionListeners();
        
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void initializeComponents(){
        
        panel=new JPanel();
        scroll=new JScrollPane();
        label=new JLabel();
        
        image=new ImageIcon(location);
        height=image.getIconHeight();width=image.getIconWidth();
        setImageSize();
        
        back=new JButton(new ImageIcon("back"));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        
        if(new File("decrease").exists()){
            dec=new JButton(new ImageIcon("decrease"));
            dec.setContentAreaFilled(false);
            dec.setBorderPainted(false);
        }
        else{
            dec=new JButton("Decrease");
            dec.setBackground(new Color(255,255,255));
            dec.setForeground(new Color(0,0,0));
        }
        
        if(new File("increase").exists()){
            inc=new JButton(new ImageIcon("increase"));
            inc.setContentAreaFilled(false);
            inc.setBorderPainted(false);
        }
        else{
            inc=new JButton("Increase");
            inc.setBackground(new Color(255,255,255));
            inc.setForeground(new Color(0,0,0));
        }
        
        bottomPane=new JPanel();
        bottomPane.setOpaque(false);
        bottomPane.setLayout(new BoxLayout(bottomPane,BoxLayout.X_AXIS));
    }
    
    private void setDimensions(int h,int w){
        if(w>=10000 || h>=10000){
           JOptionPane.showMessageDialog (getContentPane(), "Maximum  possible image size reached...", "NOTICE", JOptionPane.ERROR_MESSAGE); 
           return;
        }
        width=w;height=h;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.OPAQUE);
	Graphics2D g2d = (Graphics2D) bi.createGraphics();
	g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	g2d.drawImage(image.getImage(), 0, 0, width, height, null);
	icon=new ImageIcon(bi);
    }
    
    private void setLabel(){
        label.setIcon(icon);
        
        panel.add(label);
        panel.setOpaque(false);
        
        scroll.setViewportView(panel);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setPreferredSize(new Dimension(horizontal,vertical-100));
        scroll.getHorizontalScrollBar().setOpaque(false);
        
        scroll.getVerticalScrollBar().setOpaque(false);
        
        panel.setAlignmentY(panel.CENTER_ALIGNMENT);
        panel.setAlignmentX(panel.CENTER_ALIGNMENT);
    }
    
    private void setBottomPane(){
        
        bottomPane.add(Box.createRigidArea(new Dimension(40,10)));
        bottomPane.add(inc);
        inc.setAlignmentX(inc.LEFT_ALIGNMENT);
        
        JPanel panel=new JPanel();
        bottomPane.add(panel);
        panel.setOpaque(false);
        
        bottomPane.add(dec);
        bottomPane.add(Box.createRigidArea(new Dimension(40,10)));
        dec.setAlignmentX(dec.RIGHT_ALIGNMENT);
        
        JPanel pane=new JPanel();
        bottomPane.add(pane);
        pane.setOpaque(false);
        
        bottomPane.add(back);
        bottomPane.add(Box.createRigidArea(new Dimension(40,10)));
        back.setAlignmentX(back.CENTER_ALIGNMENT);
    }
    
    private void setComponents(){
        add(scroll);
        add(bottomPane);
    }
    
    private void actionListeners(){
        
        inc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int h=height+height/10,w=width+width/10;
                setDimensions(h,w);
                setLabel();
            }
        });
        
        dec.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int h=height-height/10,w=width-width/10;
                setDimensions(h,w);
                setLabel();
            }
        });
        
        addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==e.VK_B){
                    int h=height+height/10,w=width+width/10;
                    setDimensions(h,w);
                    setLabel();
                }
                if(e.getKeyCode()==e.VK_S){
                    int h=height-height/10,w=width-width/10;
                    setDimensions(h,w);
                    setLabel();
                }
            }
        });
        
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void setImageSize(){
        if(screenWidth<width){
            double t=(double)width/(double)screenWidth;
            t=(double)height/t;
            width=screenWidth;height=(int)t;
        }
        if(screenHeight<height){
            double t=(double)height/(double)screenHeight;
            t=(double)width/t;
            height=screenHeight;width=(int)t;
        }
    }
}
