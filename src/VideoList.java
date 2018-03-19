
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


class VideoList extends JFrame {
    
    private int horizontal,vertical;
    private String path,ret,speciesKingdom,speciesClass,userStatus;
    private JPanel bottomPane,panel;
    private JButton button,back;
    private Vector<String> vec;
    
    public VideoList(int h, int v, String SK,String SC,String user) {
        super("Video List");
        horizontal=h;vertical=v;
        speciesKingdom=SK;speciesClass=SC;userStatus=user;
        path="Main Content\\"+speciesKingdom+"\\"+speciesClass+"\\Videos";
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
        
        vec=new Vector<String>();
    }
    
    private String setPanel(){
        
        if(new File(path).exists()==false){
            JOptionPane.showMessageDialog (getContentPane(), "Videos Not Available", "NOTICE", JOptionPane.ERROR_MESSAGE);
            return "error";
        }
        String a="",b="";
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
            dispose();
            return "error";
        }
        
        int x = (vertical-150)/30;
        
        for(int j=0;;j++){
            
            JPanel pane=new JPanel();
            pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
            pane.setPreferredSize(new Dimension(250,vertical-150));
            pane.setOpaque(false);
            
            for(int i=0;i<x;i++){
                
                try {
                    a=br.readLine();
                    b=br.readLine();
                    if(a==null){
                        j=-1;
                        break;
                    }
                    if(b==null){
                        j=-1;
                        break;
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    return "error";
                }
                
                button=new JButton(b);
                button.setMaximumSize(new Dimension(250,30));
                button.setMinimumSize(new Dimension(250,30));
                button.setBorderPainted(true);
                
                pane.add(button);
                
                button.setForeground(new Color(255,255,255));
                button.setContentAreaFilled(false);
                button.setAlignmentX(button.CENTER_ALIGNMENT);
                String file=a;
                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        new PlayVideo(horizontal,vertical,"Videos\\"+file);
                    }
                });
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
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setOpaque(false);
        scroll.getHorizontalScrollBar().setOpaque(false);
        scroll.getVerticalScrollBar().setOpaque(false);
        scroll.setPreferredSize(new Dimension(horizontal*9/10,vertical-100));
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
