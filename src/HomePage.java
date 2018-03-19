
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


class HomePage extends JFrame{
    
    private int horizontal,vertical,n,timerTime,t,x;
    private JPanel panel;
    private JLabel background,title,label[];
    private ImageIcon backImage;
    private JButton enter,ack;
    private String userStatus;
    private Timer timer,buttonTimer;
    private File files[];
    
    public HomePage(int h,int v){
        super("Home Page");
        horizontal=h;vertical=v;
        userStatus="visitor";
        timerTime=3;
        setSize(horizontal,vertical);
        
        setBackground();
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        initializeFrameComponents();
        setPanel();
        runTimer();
        setComponents();
        actionListeners();
        
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void setBackground(){
        backImage=new ImageIcon("Home Page\\Background Image");
        backImage=setBackImageSize(backImage);
        backImage=setBackImageColor(backImage);
        background=new JLabel(backImage);
        setContentPane(background);
    }
    
    private ImageIcon setBackImageSize(ImageIcon icon){
        
        BufferedImage bi = new BufferedImage(horizontal, vertical, BufferedImage.OPAQUE);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(icon.getImage(), 0, 0, horizontal, vertical, null);
        
        ImageIcon finalIcon=new ImageIcon(bi);
        return finalIcon;
    }
    
    private ImageIcon setBackImageColor(ImageIcon icon){
        int r=icon.getIconHeight();
        int c=icon.getIconWidth();
        
        BufferedImage bi = new BufferedImage(c, r, BufferedImage.OPAQUE);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(icon.getImage(), 0, 0, c, r, null);
        
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                int p = bi.getRGB(j, i);
                int red = new Color(p).getRed();
                int blue = new Color(p).getBlue();
                int green = new Color(p).getGreen();
                
                red = red+(255-red)*7/20;
                blue = blue+(255-blue)*7/20;
                green = green+(255-green)*7/20;
                
                p = new Color(red,green,blue).getRGB();
                
                bi.setRGB(j, i, p);
            }
        }
        
        ImageIcon finalIcon=new ImageIcon(bi);
        return finalIcon;
    }
    
    private void initializeFrameComponents(){
        panel=new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        
        if(new File("Main Content\\WTYBP").exists()){
            title=new JLabel(new ImageIcon("Main Content\\WTYBP"));
            title.setOpaque(false);
        }
        else{
            title=new JLabel("WELCOME TO YAMUNA BIODIVERSITY PARK");
            title.setFont(new Font("Calibri",1,50));
            title.setForeground(new Color(128,64,64));
            title.setOpaque(false);
	}
        
        if(new File("enter").exists()){
            ImageIcon image=new ImageIcon("enter");
            enter=new JButton(image);
            enter.setContentAreaFilled(false);
            enter.setBorderPainted(false);
        }
        else{
            enter=new JButton(setButtonImage());
            enter.setContentAreaFilled(false);
            enter.setBorderPainted(false);
            initiateButtonTimer();
        }
        
        ack=new JButton("  Acknowledgements  ");
        ack.setContentAreaFilled(false);
        ack.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
    }
    
    private ImageIcon setButtonImage(){
        int h=200,v=50;
        
        BufferedImage b=new BufferedImage(h,v,BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = b.createGraphics();
        g2d.drawImage(b, 0, 0, null);
        g2d.setPaint(new Color(255,255,255));
        g2d.setFont(new Font("Calibri", Font.BOLD, 50));
        String s = "Enter".toUpperCase();
        FontMetrics fm = g2d.getFontMetrics();
        int x = b.getWidth()/2 - fm.stringWidth(s)/2;
        int y = 40;
        g2d.drawString(s, x, y);
        g2d.dispose();
        
        return new ImageIcon(b);
    }
    
    private void setPanel(){
        
        files = new File("Home Page").listFiles();
        
        n=files.length-1;
        label=new JLabel[8];
        for(int i=0;i<8;i++){
            ImageIcon image = new ImageIcon(files[i%n].getAbsolutePath());
            image = setPanelImageSize(image);
            label[i%n]=new JLabel(image);
            panel.add(label[i%n]);
        }
    }
    
    private ImageIcon setPanelImageSize(ImageIcon icon){
        int height = 200;
        int width = icon.getIconWidth();
        double t=(double)icon.getIconHeight()/(double)height;
        t=(double)width/t;
        width=(int)t;
        
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.OPAQUE);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
        
        ImageIcon finalIcon=new ImageIcon(bi);
        return finalIcon;
    }
    
    private void runTimer(){
        t=0;
        timer=new Timer(timerTime*1000,new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                panel.removeAll();
                for(int i=t;i<t+8;i++){
                    ImageIcon image = new ImageIcon(files[i%n].getAbsolutePath());
                    image = setPanelImageSize(image);
                    label[i-t].setIcon(image);
                    panel.add(label[i-t]);
                }
                panel.setVisible(true);
                t++;
                if(t==99)t=0;
            }
        });
        timer.start();
    }
    
    private void setComponents(){
        add(Box.createRigidArea(new Dimension(20,20)));
        add(title);
        title.setAlignmentX(title.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(30,30)));
        add(panel);
        panel.setAlignmentX(panel.CENTER_ALIGNMENT);
        add(enter);
        enter.setAlignmentX(enter.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(ack);
        
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(horizontal,30));
        p.setMinimumSize(new Dimension(horizontal,30));
        p.setPreferredSize(new Dimension(horizontal,30));
        JPanel a=new JPanel();
        a.setOpaque(false);
        a.setMaximumSize(new Dimension(horizontal*3/4,10));
        a.setMinimumSize(new Dimension(horizontal*3/4,10));
        a.setPreferredSize(new Dimension(horizontal*3/4,10));
        p.add(a);
        p.add(ack);
        add(p);
        
        add(Box.createRigidArea(new Dimension(20,25)));
    }
    
    private void initiateButtonTimer(){
        x=0;
        buttonTimer=new Timer(2000,new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(x==0){
                    enter.setIcon(new ImageIcon(new BufferedImage(200,50,BufferedImage.TYPE_INT_RGB)));
                    x=1;
                }
                else{
                    enter.setIcon(setButtonImage());
                    x=0;
                }
                
            }
        });
        buttonTimer.start();
    }
    
    private void actionListeners(){
        
        enter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(new File("enter").exists()==false)
                    buttonTimer.stop();
                new MainContent(horizontal,vertical,userStatus);
            }
        });
        
        ack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new Acknowledgements(horizontal,vertical);
            }
        });
    }
}