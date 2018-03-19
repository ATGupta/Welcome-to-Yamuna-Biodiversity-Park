
/*
 *We thank all the persons who are responsible for the success of the project, especially the following ones:
 *
 *
 *Dr. S.P. Agarwal, Principal, Ramanujan College (University of Delhi)
 *Mrs. Bhavya Ahuja, Assistant Professor, Department of Computer Science, Ramanujan College (Unicersity of Delhi)
 *Mrs. Ravneet Kaur, Assistant Professor, Department of English, Ramanujan College (University of Delhi)
 *Dr. Madhu Kaushik, Professor of Hindi, Ramanujan College (University of Delhi)
 *Nibedita Ayan, Department of Computer Science, Ramanujan College (University of Delhi)
 *Sanjil Goyal, Department of Computer Science, Ramanujan College (University of Delhi)
 *
 *
 *
 *  - Nivedita Rai and Arya Tanmay Gupta, Department of Computer Science, Ramanujan College (University of Delhi)
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class Acknowledgements extends JFrame {
    
    private int horizontal,vertical,titleWidth;
    private JLabel title;
    private JButton back;
    private JPanel panel;
    
    public Acknowledgements(int h, int v) {
        super("Acknowledgements");
        horizontal=h;vertical=v;
        setSize(horizontal,vertical);
        
        getContentPane().setBackground(new Color(0,0,0));
        initialize();
        setComponents();
        actionListeners();
        
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void initialize(){
        titleWidth=50*"  Acknowledgements  ".length()*65/100;
        title=setTitleImage(titleWidth,67);
        
        back=new JButton(new ImageIcon("back"));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        
        panel=new JPanel();
        panel.setOpaque(false);
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
        String s = "Acknowledgements".toUpperCase();
        FontMetrics fm = g2d.getFontMetrics();
        int x = b.getWidth()/2 - fm.stringWidth(s)/2;
        int y = 50;
        g2d.drawString(s, x, y);
        g2d.dispose();
        
        return new JLabel(new ImageIcon(b));
    }
    
    private void setComponents(){
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        
        add(Box.createRigidArea(new Dimension(10,30)));
        
        add(title);
        title.setAlignmentX(title.CENTER_ALIGNMENT);
        
        add(Box.createRigidArea(new Dimension(20,40)));
        
        add(panelIntro());
        
        add(Box.createRigidArea(new Dimension(20,40)));
        
        setPanel();
        panel.setMaximumSize(new Dimension(horizontal,vertical/2));
        panel.setMinimumSize(new Dimension(horizontal,vertical/2));
        add(panel);
        
        add(Box.createRigidArea(new Dimension(20,50)));
        
        add(addAryan());
        
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        p.add(back,BorderLayout.EAST);
        //back.add(Box.createRigidArea(new Dimension(40,10)));
        add(p);
    }
    
    private JPanel panelIntro(){
        JPanel intro=new JPanel();
        intro.setLayout(new BoxLayout(intro,BoxLayout.Y_AXIS));
        intro.setOpaque(false);
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("We thank all the persons who are responsible for the success of the project,");
        l1.setForeground(new Color(255,255,255));
        intro.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l2=new JLabel();
        l2.setOpaque(false);
        l2.setText("especially the following ones:");
        l2.setForeground(new Color(255,255,255));
        intro.add(l2);
        l2.setAlignmentX(l2.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l2.setFont(new Font("Times New Roman",0,18));
        
        return intro;
    }
    
    private void setPanel(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new FlowLayout());
        p.add(addPrincipal());
        p.add(Box.createRigidArea(new Dimension(100,20)));
        p.add(addBhavya());
        
        JPanel p2=new JPanel();
        p2.setOpaque(false);
        p2.setLayout(new FlowLayout());
        p2.add(addRavneet());
        p2.add(Box.createRigidArea(new Dimension(100,20)));
        p2.add(addMadhu());
        
        JPanel p3=new JPanel();
        p3.setOpaque(false);
        p3.setLayout(new FlowLayout());
        p3.add(addNibedita());
        p3.add(Box.createRigidArea(new Dimension(100,20)));
        p3.add(addSanjil());
        
        panel.add(p);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(p2);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(p3);
        
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    }
    
    private JPanel addPrincipal(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("Dr. S.P. Agarwal,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l2=new JLabel();
        l2.setOpaque(false);
        l2.setText("Principal,");
        l2.setForeground(new Color(255,255,255));
        p.add(l2);
        l2.setAlignmentX(l2.CENTER_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l2.setFont(new Font("Times New Roman",0,18));
        l3.setFont(new Font("Times New Roman",0,18));
        
        return p;
    }
    
    private JPanel addBhavya(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("Mrs. Bhavya Ahuja,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l4=new JLabel();
        l4.setOpaque(false);
        l4.setText("Assitant Professor,");
        l4.setForeground(new Color(255,255,255));
        p.add(l4);
        l4.setAlignmentX(l4.CENTER_ALIGNMENT);
        
        JLabel l2=new JLabel();
        l2.setOpaque(false);
        l2.setText("Department of Computer Science,");
        l2.setForeground(new Color(255,255,255));
        p.add(l2);
        l2.setAlignmentX(l2.CENTER_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l2.setFont(new Font("Times New Roman",0,18));
        l3.setFont(new Font("Times New Roman",0,18));
        l4.setFont(new Font("Times New Roman",0,18));
        
        return p;
    }
    
    private JPanel addRavneet(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("Mrs. Ravneet Kaur,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l4=new JLabel();
        l4.setOpaque(false);
        l4.setText("Assitant Professor,");
        l4.setForeground(new Color(255,255,255));
        p.add(l4);
        l4.setAlignmentX(l4.CENTER_ALIGNMENT);
        
        JLabel l2=new JLabel();
        l2.setOpaque(false);
        l2.setText("Department of English,");
        l2.setForeground(new Color(255,255,255));
        p.add(l2);
        l2.setAlignmentX(l2.CENTER_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l2.setFont(new Font("Times New Roman",0,18));
        l3.setFont(new Font("Times New Roman", 0, 18));
        l4.setFont(new Font("Times New Roman",0,18));
        
        return p;
    }
    
    private JPanel addMadhu(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("Dr. Madhu Kaushik,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l4=new JLabel();
        l4.setOpaque(false);
        l4.setText("Professor of Hindi,");
        l4.setForeground(new Color(255,255,255));
        p.add(l4);
        l4.setAlignmentX(l4.CENTER_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l4.setFont(new Font("Times New Roman",0,18));
        l3.setFont(new Font("Times New Roman",0,18));
        
        return p;
    }
    
    private JPanel addNibedita(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("Nibedita Ayan,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l4=new JLabel();
        l4.setOpaque(false);
        l4.setText("Department of Computer Science,");
        l4.setForeground(new Color(255,255,255));
        p.add(l4);
        l4.setAlignmentX(l4.CENTER_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l4.setFont(new Font("Times New Roman",0,18));
        l3.setFont(new Font("Times New Roman",0,18));
        
        return p;
    }
    
    private JPanel addSanjil(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("Sanjil Goyal,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.CENTER_ALIGNMENT);
        
        JLabel l4=new JLabel();
        l4.setOpaque(false);
        l4.setText("Department of Computer Science,");
        l4.setForeground(new Color(255,255,255));
        p.add(l4);
        l4.setAlignmentX(l4.CENTER_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.CENTER_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",0,18));
        l4.setFont(new Font("Times New Roman",0,18));
        l3.setFont(new Font("Times New Roman",0,18));
        
        return p;
    }
    
    private JPanel addAryan(){
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel l1=new JLabel();
        l1.setOpaque(false);
        l1.setText("- Nivedita Rai and Arya Tanmay Gupta,");
        l1.setForeground(new Color(255,255,255));
        p.add(l1);
        l1.setAlignmentX(l1.RIGHT_ALIGNMENT);
        
        JLabel l4=new JLabel();
        l4.setOpaque(false);
        l4.setText("Department of Computer Science,");
        l4.setForeground(new Color(255,255,255));
        p.add(l4);
        l4.setAlignmentX(l4.RIGHT_ALIGNMENT);
        
        JLabel l3=new JLabel();
        l3.setOpaque(false);
        l3.setText("Ramanujan College (University of Delhi)");
        l3.setForeground(new Color(255,255,255));
        p.add(l3);
        l3.setAlignmentX(l3.RIGHT_ALIGNMENT);
        
        l1.setFont(new Font("Times New Roman",1,20));
        l4.setFont(new Font("Times New Roman",1,20));
        l3.setFont(new Font("Times New Roman",1,20));
        
        JPanel pp=new JPanel();
        pp.setOpaque(false);
        
        JPanel lol=new JPanel();
        lol.setOpaque(false);
        lol.setMinimumSize(new Dimension(horizontal/2,10));
        lol.setMaximumSize(new Dimension(horizontal/2,10));
        lol.setPreferredSize(new Dimension(horizontal/2,10));
        pp.add(lol);
        
        pp.add(p);
        
        return pp;
    }
    
    private void actionListeners(){
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
