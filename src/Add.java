import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class Add extends JFrame{
	
	private Vector<String> vec;
	private String arr[],entered,path;
	private JComboBox box;
	private JButton home,back,exit,add;
	private ScrollPane scroll;
	private JPanel panel;
	private int horizontal,vertical,lengthOfScroll,lengthOfBox;
	private BufferedReader br;
	private PrintWriter pw;
	private JTextArea text;
	private JLabel label;
	private Timer timer;
	
	public Add(int h,int v,String p){
		super("Add");
	
		horizontal=h;vertical=v;
		lengthOfBox=500;path=p;
		setSize(horizontal,vertical);
		
		initializePanelScroll();
		setPanel();
		initializeEast3Buttons();
		set4MainOfFrame();
		actionListeners();
		
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initializePanelScroll(){
		
		lengthOfScroll=horizontal-150;
		panel=new JPanel();
		scroll=new ScrollPane();
		scroll.setMinimumSize(new Dimension(lengthOfScroll,vertical));
		scroll.setMaximumSize(new Dimension(horizontal-150,vertical));
		
		panel.setBackground(new Color(0,0,0));
		scroll.add(panel);
		
		vec=new Vector<String>();
		
		setArray();
                
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
		
		text=new JTextArea();
		text.setMinimumSize(new Dimension(lengthOfScroll,40));
		text.setMaximumSize(new Dimension(lengthOfScroll,40));
		text.setFont(new Font("Times New Roman",0,20));
		
		label=new JLabel();
		label.setMinimumSize(new Dimension(lengthOfBox,40));
		label.setMaximumSize(new Dimension(lengthOfBox,40));
		label.setFont(new Font("Times New Roman",0,20));
		label.setOpaque(false);
		
		add=new JButton("Add");
		add.setMinimumSize(new Dimension(lengthOfBox,40));
		add.setMaximumSize(new Dimension(lengthOfBox,40));
		add.setFont(new Font("Times New Roman",0,20));
		add.setContentAreaFilled(false);
		add.setForeground(Color.WHITE);
	}
	
    private void setArray(){
        
        vec=new Vector<String>();
        
        File file[]=new File(path).listFiles();
        for(int i=0;i<file.length;i++){
            if(file[i].isDirectory())vec.add(file[i].getAbsolutePath());
        }
        
        arr=new String[vec.size()+1];
        arr[0]="Already Available";
        for(int i=0;i<vec.size();i++){
            arr[i+1]=new File(vec.get(i)).getName();
        }
        box=new JComboBox(arr);
    }
    
    private void setPanel(){
        
        GroupLayout layout=new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
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
                .addComponent(add)))
                
                .addContainerGap(0,Short.MAX_VALUE)));
        
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
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
                .addComponent(add)))
                 
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
				new AdminPage(horizontal,vertical);
				dispose();
			}
		});
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new AdminPage(horizontal,vertical);
				dispose();
			}
		});
		
		text.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {
				checkEntered();
			}
			public void keyTyped(KeyEvent arg0) {}
			
		});
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String check=label.getText();
				if(check.compareTo("Allowed")==0){
					label.setText("Added");
					label.setVerticalAlignment(SwingConstants.CENTER);
					label.setForeground(Color.WHITE);
					
					addEntered();
					printEntered();
				}
			}
		});
		
	}
	
	private void checkEntered(){
		entered=text.getText();
		int t=0;
		for(int i=0;i<vec.size();i++){
			if(arr[i].equalsIgnoreCase(entered)){
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
	
	private void addEntered(){
		String entered=text.getText();
		int i;
		for(i=0;i<vec.size();i++){
			if(entered.compareTo(vec.get(i))<0){
				vec.add(i,entered);
				box.addItem(entered);
				break;
			}
		}
		if(i==vec.size()){
			vec.add(entered);
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
	
	private void printEntered(){
            new File(path+"\\"+entered).mkdir();
	}	
}