
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;


class MainContent extends JFrame{
    
    private int horizontal,vertical,textFont,x;
    private JButton admin,exit,button[],edit,map;
    private JPanel panel,pane;
    private JLabel title,background;
    private ImageIcon backImage;
    private String ret="",userStatus,info;
    private JTextArea txt;
    private JTextPane text;
    private JFrame frame;
    private Timer labelTimer;
    
    public MainContent(int h, int v,String user) {
        super("Main Content");
        horizontal=h;vertical=v;
        textFont=5;
        userStatus=user;
        setSize(horizontal,vertical);
        setUndecorated(true);
        
        setBackground();
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        initializeFrameComponents();
        ret=setText();
        //if(ret.compareTo("error")==0)return;
        setPanel();
        setPane();
        setComponents();
        actionListeners();
        
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setBackground(){
        backImage=new ImageIcon("Main Content\\Background Image");
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
                
                red = red+(255-red)*15/20;
                blue = blue+(255-blue)*15/20;
                green = green+(255-green)*15/20;
                
                p = new Color(red,green,blue).getRGB();
                
                bi.setRGB(j, i, p);
            }
        }
        
        ImageIcon finalIcon=new ImageIcon(bi);
        return finalIcon;
    }
    
    private void initializeFrameComponents(){
        
        edit=new JButton("Edit");
        edit.setBackground(new Color(255,255,255));
        edit.setForeground(new Color(255,0,0));
        edit.setVisible(false);
        
        if(new File("Main Content\\title").exists()){
            title=new JLabel(new ImageIcon("Main Content\\title"));
            title.setOpaque(false);
        }
        else{
            title=new JLabel("YAMUNA BIODIVERSITY PARK");
            title.setFont(new Font("Calibri",1,vertical/20));
            title.setForeground(new Color(128,64,64));
            title.setOpaque(false);
        }
        
        if(new File("Main Content\\viewmap").exists()){
            map=new JButton(new ImageIcon("Main Content\\viewmap"));
            map.setContentAreaFilled(false);
            map.setBorderPainted(false);
        }
        else{
            map=new JButton("View Map");
            map.setForeground(new Color(0,0,0));
            map.setContentAreaFilled(false);
        }
        
        text=new JTextPane();
        text.setOpaque(false);
        //text.setFont(new Font("Lucida Console",0,textFont));
        text.setEditable(false);
        text.setContentType("text/html");
        //text.setLineWrap(true);
        //text.setWrapStyleWord(true);
        
        panel=new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        
        pane=new JPanel();
        pane.setOpaque(false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
        
        if(new File("admin").exists()){
            admin=new JButton(new ImageIcon("admin"));
            admin.setContentAreaFilled(false);
            admin.setBorderPainted(false);
        }
        else{
            admin=new JButton("Enter Admin");
            admin.setBackground(new Color(255,255,255));
            admin.setForeground(new Color(100,100,100));
        }
        
        if(userStatus.compareTo("visitor")!=0)admin.setVisible(false);
        
        exit=new JButton(new ImageIcon("exit"));
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
    }
    
    private String setText(){
        BufferedReader br=null;
        String b="<html><font size="+Integer.toString(textFont)+">";
        int bold=0;
        info="";
        try {
            br=new BufferedReader(new FileReader("Main Content//Info"));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
            return "error";
        }
        for(;;){
            String a="";
            try {
                a=br.readLine();
                if(a==null)break;
                info=info+"\n"+a;
                if(a.length()==0)bold=1;
                else if(bold==1)bold=2;
                else bold=0;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.", "NOTICE", JOptionPane.ERROR_MESSAGE);
                return "error";
            }
            if(bold==2)b=b+"<p><b>"+a+"<br></b>";
            else b=b+"<p>"+a+"<br>";
            //setTextWrap(a,horizontal*9/10,vertical/2,textFont);
        }
        b=b+"</html>";
        text.setText(b);
        return "";
    }
    
    private void setTextWrap(String t,int h,int v,int font){
        String a="";
        int noc=(int)(h/(font*0.56))-10;//-10 kyoki horizontal scroll aa rha tha - unwanted
        StringTokenizer st=new StringTokenizer(t);
        int not=st.countTokens();
        int o=8;
        for(int i=0;i<not;i++){
            String b=st.nextToken();
            o=o+b.length()+1;
            if(o>=noc){
                o=b.length()+1;
                a=a+"\n";
            }
            a=a+b+" ";
        }
        //text.append("\t"+a+"\n\n");
    }
    
    private void setPanel(){
        
        File files[] = new File("Main Content").listFiles();
        int n=files.length;
        button=new JButton[n];
        
        for(int i=0;i<n;i++){
            if(files[i].isDirectory()==false)continue;
            ImageIcon image = new ImageIcon(files[i].getAbsolutePath()+"//Background Image");
            image = setPanelImageSize(image);
            button[i]=new JButton(image);
            JLabel label=new JLabel(files[i].getName().toUpperCase());
            label.setFont(new Font("Times New Roman",1,17));
            JPanel pane=new JPanel();
            pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
            pane.setOpaque(false);
            pane.add(button[i]);
            button[i].setContentAreaFilled(false);
            button[i].setBorderPainted(false);
            button[i].setAlignmentX(button[i].CENTER_ALIGNMENT);
            if(userStatus.compareTo("ehp")!=0)
                button[i].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        labelTimer.stop();
                        if(userStatus.compareTo("ac")==0){
                            new Add(horizontal,vertical,"Main Content\\"+label.getText());
                            dispose();
                        }
                        else if(userStatus.compareTo("rc")==0){
                            new Remove(horizontal,vertical,"Main Content\\"+label.getText());
                            dispose();
                        }
                        else{
                            new SpeciesKingdom(horizontal,vertical,label.getText(),userStatus);
                            dispose();
                        }
                    }
                });
            pane.add(label);
            label.setAlignmentX(label.CENTER_ALIGNMENT);
            panel.add(pane);
        }
    }
    
    private ImageIcon setPanelImageSize(ImageIcon icon){
        int height = vertical/5;
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
    
    private void setPane(){
        
        pane.add(Box.createRigidArea(new Dimension(40,10)));
        pane.add(admin);
        admin.setAlignmentX(admin.LEFT_ALIGNMENT);
        
        JPanel p=new JPanel();
        pane.add(p);
        p.setOpaque(false);
        
        JLabel a=new JLabel("   Click on the image/button to view further details   ".toUpperCase());
        a.setFont(new Font("Lucida Console",3,14));
        a.setForeground(new Color(0,0,0));
        pane.add(a);
        initiateLabelTimer(a);
        
        JPanel p2=new JPanel();
        pane.add(p2);
        p2.setOpaque(false);
        
        pane.add(edit);
        if(userStatus.compareTo("ehp")==0)
            edit.setVisible(true);
        
        JPanel p3=new JPanel();
        pane.add(p3);
        p3.setOpaque(false);
        
        pane.add(exit);
        pane.add(Box.createRigidArea(new Dimension(40,10)));
        exit.setAlignmentX(exit.RIGHT_ALIGNMENT);
    }
    
    private void initiateLabelTimer(final JLabel a){
        x=0;
        int t=500;
        labelTimer=new Timer(t,new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(x==0){
                    a.setText("   Click on the image/button to view further details   ".toUpperCase());
                    x=1;
                }
                else if(x==3){
                    a.setText("                                                       ");
                    x=0;
                }
                else{
                    x++;
                }
                
            }
        });
        labelTimer.start();
    }
    
    private void setComponents(){
        
        add(Box.createRigidArea(new Dimension(10,40)));
        
        JPanel p1=new JPanel();
        p1=setMap(p1);
        p1.setOpaque(false);
        add(p1);
        
        add(Box.createRigidArea(new Dimension(10,40)));
        
        JScrollPane p=new JScrollPane(text);
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(horizontal*9/10,vertical/2));
        p.setMinimumSize(new Dimension(horizontal*9/10,vertical/2));
        p.setPreferredSize(new Dimension(horizontal*9/10,vertical/2));
        p.setBorder(BorderFactory.createEmptyBorder());
        p.getViewport().setOpaque(false);
        p.getVerticalScrollBar().setOpaque(false);
        p.getHorizontalScrollBar().setOpaque(false);
        add(p);
        p.setAlignmentY(text.CENTER_ALIGNMENT);
        
        
        JScrollPane p2=new JScrollPane(panel);
        p2.setOpaque(false);
        p2.setMaximumSize(new Dimension(horizontal,vertical/5+40));
        p2.setMinimumSize(new Dimension(horizontal,vertical/5+40));
        p2.setPreferredSize(new Dimension(horizontal,vertical/5+40));
        p2.setBorder(BorderFactory.createEmptyBorder());
        p2.getViewport().setOpaque(false);
        p2.getVerticalScrollBar().setOpaque(false);
        p2.getHorizontalScrollBar().setOpaque(false);
        add(p2);
        p2.setAlignmentX(p2.CENTER_ALIGNMENT);
        
        add(pane);
        pane.setAlignmentX(pane.CENTER_ALIGNMENT);
        
        add(Box.createRigidArea(new Dimension(10,40)));
    }
    
    private JPanel setMap(JPanel p){
        
        int x;
        if(new File("Main Content\\title").exists())
            x=new ImageIcon("Main Content\\title").getIconWidth();
        else
            x=title.getText().length()*(50*7/10+1);
        
        GroupLayout layout=new GroupLayout(p);
        p.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addGroup(layout.createSequentialGroup()
                .addGap(horizontal/2-x/2,horizontal/2-x/2,horizontal/2-x/2)
                .addComponent(title))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(horizontal/2+x/2+20,horizontal/2+x/2+20,horizontal/2+x/2+20)
                .addComponent(map)))
                
                .addContainerGap(0,Short.MAX_VALUE)));
        
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(title))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(map)))
                
                .addContainerGap(0,Short.MAX_VALUE)));
        
        return p;
    }
    
    private void actionListeners(){
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                labelTimer.stop();
                if(userStatus.compareTo("visitor")==0)
                    dispose();
                else{
                    new AdminPage(horizontal,vertical);
                    dispose();
                }
            }
        });
        
        admin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                labelTimer.stop();
                new PasswordPage(horizontal,vertical);
                dispose();
            }
        });
        
        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });
        
        map.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new ImagePlayer(horizontal,vertical,"Main Content\\map");
            }
        });
    }
    
    private void edit(){
        frame = new JFrame ("Edit");
        frame.setSize(horizontal,vertical);
        frame.getContentPane().setBackground(new Color(0,0,0));
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        
        setEditPage();
        
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
    }
    
    private void setEditPage(){
        JButton b1=new JButton("Replace Title Image");
        if(info.length()>0)txt=new JTextArea(info.substring(1));
        else txt=new JTextArea(info);
        txt.setLineWrap(true);
        JButton b2=new JButton("Save Data");
        JButton b3=new JButton("Replace Background Image");
        frame.add(b1);
        b1.setAlignmentX(b1.CENTER_ALIGNMENT);
        JScrollPane s=new JScrollPane(txt);
        frame.add(s);
        frame.add(b2);
        b2.setAlignmentX(b2.CENTER_ALIGNMENT);
        frame.add(b3);
        b3.setAlignmentX(b3.CENTER_ALIGNMENT);
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                b1.setText("Replacing");
                String file = launchFileChooser();
                copyFile(file,"Main Content\\title");
                b1.setText("Replace Title Image");
            }
        });
        
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                PrintWriter pw=null;
                try {
                    pw=new PrintWriter(new BufferedWriter(new FileWriter("Main Content\\Info")));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame.getContentPane(), "Unable to Save.\nTry again.");
                    return;
                }
                pw.print(txt.getText());
                pw.close();
                dispose();
                frame.dispose();
                new MainContent(horizontal,vertical,userStatus);
            }
        });
        
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                b3.setText("Replacing");
                String file = launchFileChooser();
                copyFile(file,"Main Content\\Background Image");
                b3.setText("Replace Background Image");
            }
        });
    }
    
    private String launchFileChooser(){
        String file = "";
        
        JFileChooser chooser=new JFileChooser();
        
        chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
            if(f.isDirectory())return true;
                String file=f.getName();
                int i=file.lastIndexOf('.');
                if(i>0 && i<file.length())
                    file=file.substring(i+1);
                    else file="";
                if(file.equalsIgnoreCase("jpg"))return true;
                if(file.equalsIgnoreCase("jpeg"))return true;
                if(file.equalsIgnoreCase("png"))return true;
                if(file.equalsIgnoreCase("gif"))return true;
                return false;
            }
            public String getDescription() {
                return "Image Files";
            }
        });
        
        int i = chooser.showDialog(null,"Select the image to import");
        File f=chooser.getSelectedFile();
        if(f!=null){
            file=f.getAbsolutePath();
        }
        else return "";
        
        return file;
    }
    
    private void copyFile(String file,String toFile){
        
        new File(toFile).delete();
        
        try {
            File f=new File(file);
            File f2=new File(toFile);
            FileInputStream fr = new FileInputStream(file);
            FileOutputStream fw=new FileOutputStream(toFile);
            byte []buffer=new byte[4096];
            int i;
            while((i=fr.read(buffer))!=-1){
            	int j=(int) (100*f2.length()/ f.length());
            	fw.write(buffer,0,i);
            }
            
            fr.close();
            fw.close();
            JOptionPane.showMessageDialog(frame.getContentPane(), "File copied.");
        } catch (IOException e) {
            new File(toFile).delete();
            JOptionPane.showMessageDialog(frame.getContentPane(), "File NOT copied.");
        }
    }
}