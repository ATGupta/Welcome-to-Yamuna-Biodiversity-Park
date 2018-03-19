
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


class EditCheckList extends JFrame {
    
    private int horizontal,vertical;
    private String path,text,speciesKingdom,speciesClass,userStatus;
    private JTextArea area;
    private JButton save;
    
    public EditCheckList(int h, int v, String SK,String SC,String user) {
        super("Edit Check List");
        horizontal=h;vertical=v;
        speciesKingdom=SK;speciesClass=SC;userStatus=user;
        path="Main Content\\"+speciesKingdom+"\\"+speciesClass+"\\Videos";
        setSize(horizontal,vertical);
        
        getContentPane().setBackground(new Color(0,0,0));
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        initialize();
        setText();
        setComponents();
        actionListeners();
        
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void initialize(){
        area=new JTextArea();
        area.setLineWrap(true);
        
        save=new JButton("Save");
        save.setBackground(new Color(255,255,255));
    }
    
    private void setText(){
        text="";
        if(new File(path).exists()==false){
            return;
        }
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog (getContentPane(), "File Read Error", "NOTICE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(;;){
            String a=null;
            try {
                a=br.readLine();
                if(a==null)break;
                text=text+"\n"+a;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog (getContentPane(), "File Read Error", "NOTICE", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
        if(text.compareTo("")!=0)area.setText(text.substring(1));
    }
    
    private void setComponents(){
        JScrollPane s=new JScrollPane(area);
        add(s);
        add(save);
    }
    
    private void actionListeners(){
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                PrintWriter pw=null;
                try {
                    pw=new PrintWriter(new BufferedWriter(new FileWriter(path)));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog (getContentPane(), "File Write Error\nCheck List Emptied", "NOTICE", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    return;
                }
                pw.print(area.getText());
                pw.close();
                
                new SpeciesClass(horizontal,vertical,speciesKingdom,speciesClass,userStatus);
                dispose();
            }
        });
    }
}
