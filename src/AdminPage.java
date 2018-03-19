
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AdminPage extends JFrame{
	
	private JButton ehp,ak,rk,ek,ac,rc,ec,as,rs,es;
	private JButton home,back,exit;
	private JScrollPane scroll;
	private JPanel panel;
	private int horizontal,vertical,lengthOfScroll;
	
	public AdminPage(int h,int v){
		super("Admin Page");
		horizontal=h;vertical=v;
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
        scroll=new JScrollPane(panel);
        scroll.setMinimumSize(new Dimension(lengthOfScroll,vertical));
        scroll.setMaximumSize(new Dimension(horizontal-150,vertical));
        
        panel.setBackground(new Color(0,0,0));
        
        ehp=new JButton("Edit Home Page");
        ak=new JButton("Add Kingdom");
        rk=new JButton("Remove Kingdom");
        ek=new JButton("Edit Kingdom");
        ac=new JButton("Add Class");
        rc=new JButton("Remove Class");
        ec=new JButton("Edit Class");
        as=new JButton("Add Species");
        rs=new JButton("Remove Species");
        es=new JButton("Edit Species");
        
        ehp.setMinimumSize(new Dimension(lengthOfScroll,40));
        ak.setMinimumSize(new Dimension(lengthOfScroll,40));
        rk.setMinimumSize(new Dimension(lengthOfScroll,40));
        ek.setMinimumSize(new Dimension(lengthOfScroll,40));
        ac.setMinimumSize(new Dimension(lengthOfScroll,40));
        rc.setMinimumSize(new Dimension(lengthOfScroll,40));
        ec.setMinimumSize(new Dimension(lengthOfScroll,40));
        as.setMinimumSize(new Dimension(lengthOfScroll,40));
        rs.setMinimumSize(new Dimension(lengthOfScroll,40));
        es.setMinimumSize(new Dimension(lengthOfScroll,40));
        
        ehp.setMaximumSize(new Dimension(lengthOfScroll,40));
        ak.setMaximumSize(new Dimension(lengthOfScroll,40));
        rk.setMaximumSize(new Dimension(lengthOfScroll,40));
        ek.setMaximumSize(new Dimension(lengthOfScroll,40));
        ac.setMaximumSize(new Dimension(lengthOfScroll,40));
        rc.setMaximumSize(new Dimension(lengthOfScroll,40));
        ec.setMaximumSize(new Dimension(lengthOfScroll,40));
        as.setMaximumSize(new Dimension(lengthOfScroll,40));
        rs.setMaximumSize(new Dimension(lengthOfScroll,40));
        es.setMaximumSize(new Dimension(lengthOfScroll,40));
        
        ehp.setContentAreaFilled(false);
        ak.setContentAreaFilled(false);
        rk.setContentAreaFilled(false);
        ek.setContentAreaFilled(false);
        ac.setContentAreaFilled(false);
        rc.setContentAreaFilled(false);
        ec.setContentAreaFilled(false);
        as.setContentAreaFilled(false);
        rs.setContentAreaFilled(false);
        es.setContentAreaFilled(false);
        
        ehp.setFont(new Font("Times New Roman",0,20));
        ak.setFont(new Font("Times New Roman",0,20));
        rk.setFont(new Font("Times New Roman",0,20));
        ek.setFont(new Font("Times New Roman",0,20));
        ac.setFont(new Font("Times New Roman",0,20));
        rc.setFont(new Font("Times New Roman",0,20));
        ec.setFont(new Font("Times New Roman",0,20));
        as.setFont(new Font("Times New Roman",0,20));
        rs.setFont(new Font("Times New Roman",0,20));
        es.setFont(new Font("Times New Roman",0,20));
        
        ehp.setForeground(Color.WHITE);
        ak.setForeground(Color.WHITE);
        rk.setForeground(Color.WHITE);
        ek.setForeground(Color.WHITE);
        ac.setForeground(Color.WHITE);
        rc.setForeground(Color.WHITE);
        ec.setForeground(Color.WHITE);
        as.setForeground(Color.WHITE);
        rs.setForeground(Color.WHITE);
        es.setForeground(Color.WHITE);
    }
	
    private void setPanel(){
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(ehp);
        panel.add(ak);
        panel.add(rk);
        panel.add(ek);
        panel.add(ac);
        panel.add(rc);
        panel.add(ec);
        panel.add(as);
        panel.add(rs);
        panel.add(es);
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
                new MainContent(horizontal,vertical,"visitor");
                dispose();
            }
        });
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        
        ehp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"ehp");
                dispose();
            }
        });
        
        ak.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new Add(horizontal,vertical,"Main Content");
                dispose();
            }
        });
        
        rk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new Remove(horizontal,vertical,"Main Content");
                dispose();
            }
        });
        
        ek.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"ek");
                dispose();
            }
        });
        
        ac.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"ac");
                dispose();
            }
        });
        
        rc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"rc");
                dispose();
            }
        });
        
        ec.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"ec");
                dispose();
            }
        });
        
        as.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"as");
                dispose();
            }
        });
        
        rs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"rs");
                dispose();
            }
        });
        
        es.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                new MainContent(horizontal,vertical,"es");
                dispose();
            }
        });
    }
}