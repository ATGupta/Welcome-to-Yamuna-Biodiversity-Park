import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileFilter;


public class EditVideos extends JFrame{
    
    private JButton home,back,exit,add,remove,addVideo,removeVideo;
    private JScrollPane scroll;
    private JPanel panel;
    private int horizontal,vertical,lengthOfScroll,lengthOfBox;
    private Vector<String> title,videoAdd;
    private JLabel label;
    private String path,arr[],entered,speciesKingdom,speciesClass,userStatus;
    private JComboBox box;
    private JTextArea text;
    private Timer timer;
    private JFileChooser chooser;
	
	public EditVideos(int h,int v,String SK,String SC,String user){
		super("Admin Page");
		horizontal=h;vertical=v;
                lengthOfBox=500;
                speciesKingdom=SK;speciesClass=SC;userStatus=user;
                path="Main Content\\"+speciesKingdom+"\\"+speciesClass+"\\Videos";
		setSize(horizontal,vertical);
		
		String a=initializePanelScroll();
                if(a.compareTo("error")==0)
                    return;
		setPanel();
		initializeEast3Buttons();
		set4MainOfFrame();
		actionListeners();
		
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
    private String initializePanelScroll(){
        
        lengthOfScroll=horizontal-150;
        panel=new JPanel();
        scroll=new JScrollPane(panel);
        scroll.setMinimumSize(new Dimension(lengthOfScroll,vertical));
        scroll.setMaximumSize(new Dimension(horizontal-150,vertical));
        
        title=new Vector<String>();
        videoAdd=new Vector<String>();
        
        String a=setArray();
        if(a.compareTo("error")==0){
            JOptionPane.showMessageDialog (getContentPane(), "No Videos present", "NOTICE", JOptionPane.ERROR_MESSAGE);
            return "error";
        }
        
        box.setMinimumSize(new Dimension(lengthOfBox,40));
        box.setMaximumSize(new Dimension(lengthOfBox,40));
        box.setFont(new Font("Times New Roman",0,20));
        box.setOpaque(false);
        box.setRenderer(new DefaultListCellRenderer(){
            public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
            JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            result.setOpaque(false);
            return result;
        }});
        box.setVisible(false);
        
        addVideo=new JButton("Add Video");
        addVideo.setMinimumSize(new Dimension(lengthOfBox,40));
        addVideo.setMaximumSize(new Dimension(lengthOfBox,40));
        addVideo.setFont(new Font("Times New Roman",0,20));
        addVideo.setContentAreaFilled(false);
        addVideo.setForeground(Color.WHITE);
        addVideo.setVisible(false);
        
        removeVideo=new JButton("Remove Video");
        removeVideo.setMinimumSize(new Dimension(lengthOfBox,40));
        removeVideo.setMaximumSize(new Dimension(lengthOfBox,40));
        removeVideo.setFont(new Font("Times New Roman",0,20));
        removeVideo.setContentAreaFilled(false);
        removeVideo.setForeground(Color.WHITE);
        removeVideo.setVisible(false);
        
        label=new JLabel();
        label.setMinimumSize(new Dimension(lengthOfBox,40));
        label.setMaximumSize(new Dimension(lengthOfBox,40));
        label.setFont(new Font("Times New Roman",0,20));
        label.setOpaque(false);
        label.setVisible(false);
        
        text=new JTextArea();
        text.setMinimumSize(new Dimension(lengthOfScroll,40));
        text.setMaximumSize(new Dimension(lengthOfScroll,40));
        text.setFont(new Font("Times New Roman",0,20));
        text.setVisible(false);
        
        panel.setBackground(new Color(0,0,0));
        
        add=new JButton("Add Video(s)");
        remove=new JButton("Remove Video(s)");
        
        add.setMinimumSize(new Dimension(lengthOfScroll,40));
        remove.setMinimumSize(new Dimension(lengthOfScroll,40));
        
        add.setMaximumSize(new Dimension(lengthOfScroll,40));
        remove.setMaximumSize(new Dimension(lengthOfScroll,40));
        
        add.setContentAreaFilled(false);
        remove.setContentAreaFilled(false);
        
        add.setFont(new Font("Times New Roman",0,20));
        remove.setFont(new Font("Times New Roman",0,20));
        
        add.setForeground(Color.WHITE);
        remove.setForeground(Color.WHITE);
        
        return "";
    }
	
    private String setArray(){
        
        if(new File(path).exists()){
        
            BufferedReader br=null;
            try {
                br=new BufferedReader(new FileReader(path));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
                return "error";
            }
            
            for(;;){
                String a=null;
                try {
                    a=br.readLine();
                    if(a==null)break;
                    videoAdd.add(a);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
                    return "error";
                }
                try {
                    a=br.readLine();
                    if(a==null)break;
                    title.add(a);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog (getContentPane(), "Data not retrieved successfully.\nFile not Found.", "NOTICE", JOptionPane.ERROR_MESSAGE);
                    return "error";
                }
            }
        }
        arr=new String[title.size()+1];
        arr[0]="Already Available";
        for(int i=0;i<title.size();i++){
            arr[i+1]=title.get(i);
        }
        box=new JComboBox(arr);
        return "";
    }
    
    private void setPanel(){
        
        GroupLayout layout=new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(add))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(remove))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2)
                .addComponent(box))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(text))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2)
                .addComponent(label))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2)
                .addComponent(addVideo))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2,lengthOfScroll/2-lengthOfBox/2)
                .addComponent(removeVideo)))
                
                .addContainerGap(0,Short.MAX_VALUE)));
        
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addGroup(layout.createSequentialGroup()
                .addGap(0,0,0)
                .addComponent(add))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(40,40,40)
                .addComponent(remove))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(100,100,100)
                .addComponent(box))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(160,160,160)
                .addComponent(text))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(220,220,220)
                .addComponent(label))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(280,280,280)
                .addComponent(addVideo))
                
                .addGroup(layout.createSequentialGroup()
                .addGap(280,280,280)
                .addComponent(removeVideo)))
                 
                .addContainerGap(0,Short.MAX_VALUE)));
        
    }
	
    private void initializeEast3Buttons(){
        
        home=new JButton("Home");
        back=new JButton("Back");
        exit=new JButton("Exit");
        home.setMaximumSize(new Dimension(150,vertical/3));
        back.setMaximumSize(new Dimension(150,vertical/3));
        exit.setMaximumSize(new Dimension(150,vertical-2*vertical/3));
        
        home.setMinimumSize(new Dimension(150,vertical/3));
        back.setMinimumSize(new Dimension(150,vertical/3));
        exit.setMinimumSize(new Dimension(150,vertical-2*vertical/3));
        
        home.setBackground(new Color(255,255,255));
        back.setBackground(new Color(255,255,255));
        exit.setBackground(new Color(255,255,255));
    }
	
	private void set4MainOfFrame(){
		
		GroupLayout layout2=new GroupLayout(getContentPane());
		getContentPane().setLayout(layout2);
		
		layout2.setHorizontalGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout2.createSequentialGroup()
				.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
						
				.addGroup(layout2.createSequentialGroup()
				.addGap(horizontal-150,horizontal-150,horizontal-150)
				.addComponent(home))
				
				.addGroup(layout2.createSequentialGroup()
				.addGap(horizontal-150,horizontal-150,horizontal-150)
				.addComponent(back))
				
				.addGroup(layout2.createSequentialGroup()
				.addGap(horizontal-150,horizontal-150,horizontal-150)
				.addComponent(exit))
				
				.addGroup(layout2.createSequentialGroup()
				.addGap(0,0,0)
				.addComponent(scroll)))
				
				.addContainerGap(0,Short.MAX_VALUE)));
		
		layout2.setVerticalGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout2.createSequentialGroup()
				.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
						
				.addGroup(layout2.createSequentialGroup()
				.addGap(0,0,0)
				.addComponent(home))
				
				.addGroup(layout2.createSequentialGroup()
				.addGap(vertical/3,vertical/3,vertical/3)
				.addComponent(back))
				
				.addGroup(layout2.createSequentialGroup()
				.addGap(2*vertical/3,2*vertical/3,2*vertical/3)
				.addComponent(exit))
					
				.addGroup(layout2.createSequentialGroup()
				.addGap(0,0,0)
				.addComponent(scroll)))
					
				.addContainerGap(0,Short.MAX_VALUE)));
		
    }
    
    private void actionListeners(){
        
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"visitor");
                dispose();
            }
        });
        
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new SpeciesClass(horizontal,vertical,speciesKingdom,speciesClass,userStatus);
                dispose();
            }
        });
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new SpeciesClass(horizontal,vertical,speciesKingdom,speciesClass,userStatus);
                dispose();
            }
        });
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                add.setVisible(false);
                remove.setVisible(false);
                text.setVisible(true);
                label.setVisible(true);
                addVideo.setVisible(true);
                box.setVisible(true);
            }
        });
        
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                add.setVisible(false);
                remove.setVisible(false);
                box.setVisible(true);
                removeVideo.setVisible(true);
                label.setVisible(true);
            }
        });
        
        text.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent arg0) {}
            public void keyReleased(KeyEvent arg0) {
                checkEntered();
            }
            public void keyTyped(KeyEvent arg0) {}
        });
        
        addVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String check=label.getText();
                if(check.compareTo("Allowed")==0){
                    addVideo.setText("Copying file");
                    addVideo.setEnabled(false);
                    label.setText("Added");
                    label.setVerticalAlignment(SwingConstants.CENTER);
                    label.setForeground(Color.WHITE);
                    
                    chooseFile();
                    
                    addVideo.setEnabled(true);
                    addVideo.setText("Add Video");
                }
            }
        });
        
        removeVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String check=label.getText();
                    
                label.setText("Removed");
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setForeground(Color.WHITE);
                
                removeEntered();
                
                addVideo.setEnabled(true);
                addVideo.setText("Add Video");
            }
        });
    }
    
    private void checkEntered(){
        entered=text.getText();
        int t=0;
        for(int i=0;i<title.size();i++){
            if(title.get(i).equalsIgnoreCase(entered)){
                t=1;break;
            }
        }
        if(t==1 || entered.compareTo("")==0){
            label.setText("Not Allowed");
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setForeground(Color.RED);
        }
        else {
            label.setText("Allowed");
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setForeground(Color.GREEN);
        }
    }
    
    private void chooseFile(){
        
        String file = "";
        
        chooser=new JFileChooser();
        
        chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
            if(f.isDirectory())return true;
                String file=f.getName();
                int i=file.lastIndexOf('.');
                if(i>0 && i<file.length())
                    file=file.substring(i+1);
                    else file="";
                if(file.equalsIgnoreCase("mp4"))return true;
                if(file.equalsIgnoreCase("mkv"))return true;
                if(file.equalsIgnoreCase("mpg"))return true;
                if(file.equalsIgnoreCase("mpeg"))return true;
                if(file.equalsIgnoreCase("avi"))return true;
                if(file.equalsIgnoreCase("wmv"))return true;
                if(file.equalsIgnoreCase("flv"))return true;
                return false;
            }
            public String getDescription() {
                return "Media Files";
            }
        });
        
        int i = chooser.showDialog(null,"Select the video to import");
        File f=chooser.getSelectedFile();
        if(f!=null){
            file=f.getAbsolutePath();
        }
        else {
            return;
        }
        
        String toFile=getTheNameOfFile(file);
        
        addEntered(toFile);
        
        copyFile(file,("Videos//"+toFile));
        
        setTextFileAdd(toFile);
    }
    
    private String getTheNameOfFile(String file){
        
        int x=file.lastIndexOf('.');
        if(x>0 && x<file.length())file=file.substring(x);
        else file="";
        
        File list[]=new File("Videos").listFiles();
        String files[]=new String[list.length];
        
        for(int j=0;j<list.length;j++){
            files[j]=list[j].getName();
            int y=files[j].lastIndexOf('.');
            if(y>0 && y<files[j].length())files[j]=files[j].substring(0,y);
            else files[j]=list[j].getName();
        }
        
        int i=0;
        for(;;){
            i++;
            int tt=0;
            for(int j=0;j<list.length;j++){
                if(Integer.toString(i).compareTo(files[j])!=0)tt=1;
                else {
                    tt=0;break;
                }
            }
            if(tt==1)break;
        }
        
        String toFile=Integer.toString(i);//+file;it contains siffix - not needed
        
        return toFile;
    }
    
    private void copyFile(String file,String toFile){
        
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
        } catch (IOException e) {
            new File(toFile).delete();
            JOptionPane.showMessageDialog(getContentPane(), "File NOT copied.");
        }
    }
    
    private void setTextFileAdd(String toFile){
        PrintWriter pw=null;
        
        try {
            pw=new PrintWriter(new BufferedWriter(new FileWriter(path)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(getContentPane(), "File NOT copied");
            new File("Videos\\"+toFile).delete();
            return;
        }
        for(int i=0;i<title.size();i++){
            if(i!=0)
            pw.println();
            pw.println(videoAdd.get(i));
            pw.print(title.get(i));
        }
        pw.close();
    }
	
    private void addEntered(String toFile){
        String entered=text.getText();
        int i;
        for(i=0;i<title.size();i++){
            if(entered.compareTo(title.get(i))<0){
                title.add(i,entered);
                videoAdd.add(i,toFile);
                box.addItem(entered);
                break;
            }
		}
		if(i==title.size()){
			title.add(entered);
			box.addItem(entered);
        }
        
        timer=new Timer(2000,new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                label.setText("Not Allowed");
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setForeground(Color.RED);
                
                text.setText("");
                timer.stop();
            }
        });
        timer.start();
    }
    
    private void removeEntered(){
        entered=(String)box.getSelectedItem();
        if(entered.compareTo("Already Available")!=0){
            for(int i=0;i<title.size();i++){
                if(title.get(i).compareTo(entered)==0){
                    new File("Videos\\"+videoAdd.get(i)).delete();
                    title.remove(i);
                    videoAdd.remove(i);
                }
            }
            box.removeItem(entered);
        }
        
        timer=new Timer(2000,new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                label.setText("");
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setForeground(Color.RED);
                
                timer.stop();
            }
        });
        timer.start();
    
        setTextFileRemove();
    }
    
    private void setTextFileRemove(){
        PrintWriter pw=null;
        
        try {
            pw=new PrintWriter(new BufferedWriter(new FileWriter(path)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(getContentPane(), "File not deleted properly.\nMay give errors in future.");
            return;
        }
        for(int i=0;i<title.size();i++){
            if(i!=0)
            pw.println();
            pw.println(videoAdd.get(i));
            pw.print(title.get(i));
        }
        pw.close();
    }
}