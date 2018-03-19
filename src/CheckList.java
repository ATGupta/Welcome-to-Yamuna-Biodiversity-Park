
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


class CheckList extends JFrame {
    
    private int horizontal,vertical;
    private String path,ret,speciesKingdom,speciesClass,a,userStatus;
    private JPanel bottomPane,panel;
    private JButton back,button;
    
    public CheckList(int h, int v, String SK,String SC,String user) {
        super("Video List");
        horizontal=h;vertical=v;
        speciesKingdom=SK;speciesClass=SC;userStatus=user;
        path="Main Content\\"+speciesKingdom+"\\"+speciesClass;
        setSize(horizontal,vertical);
        
        getContentPane().setBackground(new Color(0,0,0));
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        initializeComponents();
        ret=setPanel();
        if(ret.compareTo("error")==0)return;
        setBottomPane();
        setComponents();
        actionListeners();
        
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void initializeComponents(){
        panel=new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        
        bottomPane=new JPanel();
        bottomPane.setOpaque(false);
        bottomPane.setLayout(new BoxLayout(bottomPane,BoxLayout.X_AXIS));
        
        back=new JButton(new ImageIcon("back"));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
    }
    
    private String setPanel(){
        
        if(new File(path+"\\Check List").exists()==false){
           JOptionPane.showMessageDialog (getContentPane(), "Check List Not Available", "NOTICE", JOptionPane.ERROR_MESSAGE);
            return "error";
        }
        
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(path+"\\Check List"));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
            dispose();
            return "error";
        }
        
        int x = (vertical-150)/30;
        
            
        int bold=1;
        for(int j=0;;j++){
            
            JPanel pane=new JPanel();
            pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
            pane.setPreferredSize(new Dimension(250,vertical-150));
            pane.setOpaque(false);
            
            for(int i=0;i<x;i++){
                
                try {
                    a=br.readLine();
                    if(a==null){
                        j=-1;
                        break;
                    }
                    if(a.length()==0)bold=1;
                    else if (bold==1)bold=2;
                    else bold=0;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    return "error";
                }
                
                button=new JButton(a);
                if(bold==2)button.setFont(new Font("Times New Roman",1,20));
                else button.setFont(new Font("Times New Roman",0,15));
                button.setForeground(new Color(255,255,255));
                button.setMaximumSize(new Dimension(250,30));
                button.setMinimumSize(new Dimension(250,30));
                
                final String b=a;
                if((new File(path+"\\"+a).exists()==false)||(a.compareTo("")==0))
                    button.setEnabled(false);
                else if(userStatus.compareTo("ec")==0){}
                else{
                    button.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            if(userStatus.compareTo("visitor")==0){
                                new SpeciesView(horizontal,vertical,speciesKingdom,speciesClass,b,"vcl");
                                dispose();
                            }
                            else{
                                new SpeciesView(horizontal,vertical,speciesKingdom,speciesClass,b,"edcl");
                                dispose();
                            }
                        }
                    });
                }
                
                pane.add(button);
                
                button.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
                button.setContentAreaFilled(false);
                button.setAlignmentX(button.CENTER_ALIGNMENT);
            }
            panel.add(pane);
            if(j==-1)break;
        }
        return "";
    }
    
    private void setBottomPane(){
        
        JPanel panel=new JPanel();
        bottomPane.add(panel);
        panel.setOpaque(false);
        
        bottomPane.add(back);
        bottomPane.add(Box.createRigidArea(new Dimension(40,10)));
        back.setAlignmentX(back.CENTER_ALIGNMENT);
        
        bottomPane.add(Box.createRigidArea(new Dimension(40,10)));
    }
    
    private void setComponents(){
        JScrollPane scroll=new JScrollPane(panel);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setPreferredSize(new Dimension(horizontal*9/10,vertical-100));
        scroll.getHorizontalScrollBar().setOpaque(false);
        scroll.getVerticalScrollBar().setOpaque(false);
        add(scroll);
        
        add(Box.createRigidArea(new Dimension(10,40)));
        add(bottomPane);
        bottomPane.setAlignmentX(bottomPane.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(10,40)));
    }
    
    private void actionListeners(){
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
