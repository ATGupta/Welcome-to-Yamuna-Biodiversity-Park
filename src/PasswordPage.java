
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

class PasswordPage extends JFrame{
	
        private int horizontal,vertical;
	private JPasswordField password;
	private JLabel label;
	private JButton button,back;
	
	public PasswordPage(int h,int v){
		super("Enter Password");
                horizontal=h;vertical=v;
		setSize(horizontal,vertical);
		
                getContentPane().setBackground(new Color(0,0,0));
		label=new JLabel("Enter password");
                label.setForeground(new Color(255,255,255));
		button=new JButton("Enter");
                button.setBackground(new Color(255,255,255));
		password=new JPasswordField();
		password.setMaximumSize(new Dimension(200,15));
                back=new JButton(new ImageIcon("back"));
                back.setContentAreaFilled(false);
                back.setBorder(BorderFactory.createEmptyBorder());
		
		GroupLayout layout=new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					
					.addGroup(layout.createSequentialGroup()
					.addGap(600,600,600)
					.addComponent(label))
					
					.addGroup(layout.createSequentialGroup()
					.addGap(560,560,560)
					.addComponent(password))
					
					.addGroup(layout.createSequentialGroup()
					.addGap(620,620,620)
					.addComponent(button))
					
					.addGroup(layout.createSequentialGroup()
					.addGap(horizontal-200,horizontal-200,horizontal-200)
					.addComponent(back)))
			
			.addContainerGap(0,Short.MAX_VALUE))
		);
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					
					.addGroup(layout.createSequentialGroup()
					.addGap(250,250,250)
					.addComponent(label))
					
					.addGroup(layout.createSequentialGroup()
					.addGap(300,300,300)
					.addComponent(password))
					
					.addGroup(layout.createSequentialGroup()
					.addGap(400,400,400)
					.addComponent(button))
					
					.addGroup(layout.createSequentialGroup()
					.addGap(vertical-100,vertical-100,vertical-100)
					.addComponent(back)))
			
			.addContainerGap(0,Short.MAX_VALUE))
		);
		
		password.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==arg0.VK_ENTER){
					String a="naiyvrebdyiptaa";
					char b[]=password.getPassword();
					int tt=0;
					if(a.length()==b.length)
						for(int i=0;i<a.length();i++){
							if(a.charAt(i)!=b[i]){
								tt=1;
								break;
							}
						}
					if(a.length()!=b.length || tt==1){
						JOptionPane.showMessageDialog (getContentPane(), "Wrong Password Entered", "NOTICE", JOptionPane.ERROR_MESSAGE);
					}
					else{
						dispose();
						new AdminPage(horizontal,vertical);
					}
					password.setText("");
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String a="naiyvrebdyiptaa";
				char b[]=password.getPassword();
				int tt=0;
				if(a.length()==b.length)
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)!=b[i]){
							tt=1;
							break;
						}
					}
				if(a.length()!=b.length || tt==1){
					JOptionPane.showMessageDialog (getContentPane(), "Wrong Password Entered", "NOTICE", JOptionPane.ERROR_MESSAGE);
				}
				else{
					dispose();
					new AdminPage(horizontal,vertical);
				}
				password.setText("");
			}
		});
                
                back.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        new MainContent(horizontal,vertical,"visitor");
                        dispose();
                    }
                });
		
                setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}