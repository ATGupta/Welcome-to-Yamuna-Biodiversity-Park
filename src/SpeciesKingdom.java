
import java.awt.BorderLayout;
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
import javax.swing.filechooser.FileFilter;


class SpeciesKingdom extends JFrame{
    
    private int horizontal,vertical,textFont,titleWidth;
    private JButton home,exit,button[],back,edit;
    private JPanel panel,pane;
    private JLabel title,background,youRHere;
    private ImageIcon backImage;
    private String ret="",speciesKingdom,userStatus,info;
    private JTextArea txt;
    private JTextPane text;
    private JScrollPane scroll;
    private JFrame frame;
    
    public SpeciesKingdom(int h, int v,String sk, String user) {
        super("Main Content");
        horizontal=h;vertical=v;
        speciesKingdom=sk;userStatus=user;
        textFont=5;
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
        //if(new File("Main Content\\"+speciesKingdom+"\\gif").exists())
        //    setGif();
        actionListeners();
        
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setBackground(){
        backImage=new ImageIcon("Main Content\\"+speciesKingdom+"\\Background Image");
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
        youRHere=new JLabel("You are here : "+speciesKingdom+"        (Click on the image/button to view further details)");
        youRHere.setForeground(new Color(128,128,128));
        
        edit=new JButton("Edit");
        edit.setBackground(new Color(255,255,255));
        edit.setForeground(new Color(255,0,0));
        edit.setVisible(false);
        
        if(new File("Main Content\\"+speciesKingdom+"\\title").exists()){
            titleWidth=new ImageIcon("Main Content\\"+speciesKingdom+"\\title").getIconWidth();
            title=new JLabel(new ImageIcon("Main Content\\"+speciesKingdom+"\\title"));
        }
        else{
            titleWidth=50*("  "+speciesKingdom+"  ").length()*65/100;
            title=setTitleImage(titleWidth,67);
        }
        
        text=new JTextPane();
        text.setOpaque(false);
        text.setEditable(false);
        text.setContentType("text/html");
        
        panel=new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        
        scroll=new JScrollPane(panel);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        
        pane=new JPanel();
        pane.setOpaque(false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
        
        home=new JButton(new ImageIcon("home"));
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        
        exit=new JButton(new ImageIcon("exit"));
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        
        back=new JButton(new ImageIcon("back"));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
    }
    
    private JLabel setTitleImage(int h,int v){
        
        BufferedImage b=new BufferedImage(h,v,BufferedImage.TYPE_INT_RGB);
        for(int r=0;r<h;r++){
            for(int c=0;c<v;c++){
                int red=102,green=51,blue=0;
                if(r<5 || c<5 || (h-r)<5 || (v-c)<5){
                    red=green=blue=255;
                }
                else if(r%10<5 && c%10<5){
                    red=150;green=75;blue=75;
                }
                int rgb=new Color(red,green,blue).getRGB();
                
                b.setRGB(r, c, rgb);
            }
        }
        
        Graphics2D g2d = b.createGraphics();
        g2d.drawImage(b, 0, 0, null);
        g2d.setPaint(new Color(255,255,255));
        g2d.setFont(new Font("Calibri", Font.BOLD, 50));
        String s = speciesKingdom.toUpperCase();
        FontMetrics fm = g2d.getFontMetrics();
        int x = b.getWidth()/2 - fm.stringWidth(s)/2;
        int y = 50;
        g2d.drawString(s, x, y);
        g2d.dispose();
        
        return new JLabel(new ImageIcon(b));
    }
    
    private String setText(){
        BufferedReader br=null;
        String b="<html><font size="+Integer.toString(textFont)+">";
        int bold=0;
        info="";
        try {
            br=new BufferedReader(new FileReader("Main Content//"+speciesKingdom+"\\Info"));
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
        File files[] = new File("Main Content\\"+speciesKingdom).listFiles();
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
            if(userStatus.compareTo("ek")!=0)
                button[i].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if(userStatus.compareTo("as")==0){
                            new Add(horizontal,vertical,"Main Content\\"+speciesKingdom+"\\"+label.getText());
                            dispose();
                        }
                        else if(userStatus.compareTo("rs")==0){
                            new Remove(horizontal,vertical,"Main Content\\"+speciesKingdom+"\\"+label.getText());
                            dispose();
                        }
                        else{
                            new SpeciesClass(horizontal,vertical,speciesKingdom,label.getText(),userStatus);
                            dispose();
                        }
                    }
                });
            pane.add(label);
            label.setAlignmentX(label.CENTER_ALIGNMENT);
            panel.add(pane);
        }
        scroll.setMaximumSize(new Dimension(horizontal,vertical/5+60));
        scroll.setMinimumSize(new Dimension(horizontal,vertical/5+60));
        scroll.setPreferredSize(new Dimension(horizontal,vertical/5+50));
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
        
        pane.add(Box.createRigidArea(new Dimension(40,10)));pane.add(edit);
        
        if(userStatus.compareTo("ek")==0)
            edit.setVisible(true);
        
        JPanel p=new JPanel();
        pane.add(p);
        p.setOpaque(false);
        
        pane.add(home);
        home.setAlignmentX(home.LEFT_ALIGNMENT);
        
        JPanel panel=new JPanel();
        pane.add(panel);
        panel.setOpaque(false);
        
        pane.add(back);
        pane.add(Box.createRigidArea(new Dimension(40,10)));
        back.setAlignmentX(back.CENTER_ALIGNMENT);
        
        JPanel panex=new JPanel();
        pane.add(panex);
        panex.setOpaque(false);
        
        pane.add(exit);
        pane.add(Box.createRigidArea(new Dimension(40,10)));
        exit.setAlignmentX(exit.RIGHT_ALIGNMENT);
    
    }
    
    private void setComponents(){
        
        JPanel pa=new JPanel();
        pa.setLayout(new BorderLayout());
        pa.add(youRHere,BorderLayout.NORTH);
        pa.setMaximumSize(new Dimension(horizontal,20));
        pa.setOpaque(false);
        add(pa);
        
        JPanel p1=new JPanel();
        p1=setGif(p1);
        add(p1);
        
        add(Box.createRigidArea(new Dimension(10,20)));
        
        JScrollPane p=new JScrollPane(text);
        p.setMaximumSize(new Dimension(horizontal*9/10,vertical*2/5+40));
        p.setMinimumSize(new Dimension(horizontal*9/10,vertical*2/5+40));
        p.setPreferredSize(new Dimension(horizontal*9/10,vertical*2/5+40));
        p.setOpaque(false);
        p.getViewport().setOpaque(false);
        p.getVerticalScrollBar().setOpaque(false);
        p.getHorizontalScrollBar().setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder());
        
        add(p);
        p.setAlignmentY(p.CENTER_ALIGNMENT);
        
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setOpaque(false);
        scroll.getHorizontalScrollBar().setOpaque(false);
        add(scroll);
        scroll.setAlignmentX(scroll.CENTER_ALIGNMENT);
        
        add(pane);
        pane.setAlignmentX(pane.CENTER_ALIGNMENT);
        
        add(Box.createRigidArea(new Dimension(10,40)));
    }
    
    private JPanel setGif(JPanel p1){
        p1.setOpaque(false);
        
        JLabel l=new JLabel();
        if(new File("Main Content\\"+speciesKingdom+"\\gif").exists())
            l.setIcon(new ImageIcon("Main Content\\"+speciesKingdom+"\\gif"));
        l.setOpaque(false);
        
        int x=titleWidth;
        
        GroupLayout layout=new GroupLayout(p1);
        p1.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addGroup(layout.createSequentialGroup()
                .addGap(horizontal/2-x/2,horizontal/2-x/2,horizontal/2-x/2)
                .addComponent(title))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(horizontal/2+x/2,horizontal/2+x/2,horizontal/2+x/2)
                .addComponent(l)))
                
                .addContainerGap(0,Short.MAX_VALUE)));
        
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(title))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(l)))
                
                .addContainerGap(0,Short.MAX_VALUE)));
        
        return p1;
    }
    
    private void actionListeners(){
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(userStatus.compareTo("visitor")==0)
                    dispose();
                else{
                    new AdminPage(horizontal,vertical);
                    dispose();
                }
            }
        });
        
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new MainContent(horizontal,vertical,"visitor");
                dispose();
            }
        });
        
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new MainContent(horizontal,vertical,userStatus);
                dispose();
            }
        });
        
        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                edit();
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
                copyFile(file,"Main Content\\"+speciesKingdom+"\\title");
                b1.setText("Replace Title Image");
            }
        });
        
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                PrintWriter pw=null;
                try {
                    pw=new PrintWriter(new BufferedWriter(new FileWriter("Main Content\\"+speciesKingdom+"\\Info")));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame.getContentPane(), "Unable to Save.\nTry again.");
                    return;
                }
                pw.print(txt.getText());
                pw.close();
                dispose();
                frame.dispose();
                new SpeciesKingdom(horizontal,vertical,speciesKingdom,userStatus);
            }
        });
        
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                b3.setText("Replacing");
                String file = launchFileChooser();
                copyFile(file,"Main Content\\"+speciesKingdom+"\\Background Image");
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