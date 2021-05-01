import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.tree.DefaultMutableTreeNode;  

class login implements ActionListener
{
	static JFrame frame;
	static JPanel panel1,panel2;
	static JTabbedPane tabbedpane;
	JTextField textfield1,textfield2;
	JPasswordField passwordfield1,passwordfield2,passwordfield3;
	JLabel label1,label2,label3,label_4,label_5,label_6;
	JButton submit,register;
	
	login()
	{
        frame=new JFrame();
		frame.setTitle("Jail Management");
        frame.setSize(100,200);  
		frame.setLayout(null);
		align();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		actionEvent();
	}
	
	public void align() 
	{
	    tabbedpane =new JTabbedPane();
		panel1 =new JPanel();	   panel2 =new JPanel();
		label3 =new JLabel("Prison Management");     label3.setFont(new Font("TimesNewRoman",Font.PLAIN,36));
		label3.setBounds(400,30,370,80);
		textfield1 =new JTextField();
		textfield1.setToolTipText("Enter Username");
		passwordfield1 =new JPasswordField();   
		passwordfield2 =new JPasswordField();    passwordfield3 =new JPasswordField();
        submit =new JButton("SUBMIT");            
		label1 =new JLabel("USERNAME :");   label2 =new JLabel("PASSWORD :");
		label1.setBounds(370,130,120,30); label2.setBounds(370,170,120,30); 
		textfield1.setBounds(470,130,130,30);
		passwordfield1.setBounds(470,170,130,30);
		label1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label2.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		submit.setBounds(400,210,100,20);                                                              
		textfield1.setFont(new Font("TimesNewRoman",Font.PLAIN,18));
		passwordfield1.setFont(new Font("TimesNewRoman",Font.PLAIN,18));
		panel1.setBackground(Color.gray);  
		panel1.add(label3);  
		panel1.add(label1);   
		panel1.add(label2);      
		panel1.add(textfield1);	   panel1.add(passwordfield1);
		panel1.add(submit);	                                            
		tabbedpane.add("LOGIN",panel1);		tabbedpane.add("SIGN IN",panel2);
		label_4 =new JLabel("USERNAME :");    
		label_5 =new JLabel("PASSWORD :");     label_6 =new JLabel("CON.PASSWORD :");
		label_4.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label_5.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield2 =new JTextField();     
		textfield2.setFont(new Font("TimesNewRoman",Font.PLAIN,18));
		textfield2.setToolTipText("Enter Username");
		register =new JButton("REGISTER");
		passwordfield1.setToolTipText("Enter Password");
		passwordfield2.setToolTipText("Enter Password");
		passwordfield3.setToolTipText("Enter confirm Password");
		label_4.setBounds(370,130,130,30); 
		label_5.setBounds(370,170,130,30);   label_6.setBounds(370,210,130,30);
		textfield2.setBounds(480,130,130,30);    
		passwordfield2.setBounds(480,170,130,30);     passwordfield3.setBounds(480,210,130,30);
		register.setBounds(420,260,100,20); 
		panel2.setBackground(Color.gray);  
		panel2.add(label_4);  
		panel2.add(label_5);      panel2.add(label_6);
        panel2.add(textfield2);	   
		panel2.add(passwordfield2);    panel2.add(passwordfield3);
		panel2.add(register);
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		tabbedpane.setBounds(0,0,1800,1500);  
        frame.add(tabbedpane);		
	}
	
	public void actionEvent()
	{
		submit.addActionListener(this);
		register.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
		{
			String u1= textfield1.getText();
			String p1= passwordfield1.getText();
			
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");
				Statement stmt=con.createStatement();
				if(con!=null)
					System.out.println("Connection Success");
				ResultSet res =stmt.executeQuery("select password from admin where adminname='"+u1+"'");
				
				if(res.next())
				{
					if(!(res.getString("password").equals(p1)))
					{
				        JOptionPane.showMessageDialog(null,"INVAILD USERNAME OR PASSWORD");
					}
					
					else                              
					{
						JOptionPane.showMessageDialog(null,"LOGIN SUCCESSFUL");
						new register();
					}
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"INVAILD USERNAME OR PASSWORD");
				}	
				textfield1.setText("");
				passwordfield1.setText("");
				res.close();
				stmt.close();
				con.close();
			}
			
			catch (SQLException e1) 
			{
                System.out.println(e1);
            }
		}
		
		if(e.getSource()==register)
		{
			String u1= textfield2.getText();
			String p2= passwordfield2.getText();
			String p3= passwordfield3.getText();
			
			try
			{
				if(!(p2.equals(p3)))
                {
					JOptionPane.showMessageDialog(null,"password did not match");
					passwordfield3.setText("");
                }
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");
				Statement stmt=con.createStatement();
				
				if(con!=null)
					System.out.println("Connection Success");
					
				if(p2.equals(p3))
				{
					stmt.executeUpdate("insert into admin values('"+u1+"','"+p2+"')");
				    JOptionPane.showMessageDialog(null," REGISTERED SUCCESSFULLY");
				}
				stmt.close();
				con.close();
			}
			
			catch(SQLIntegrityConstraintViolationException e3)
			{
				JOptionPane.showMessageDialog(null,"Username already exist"); 
			}
			
			catch (SQLException e1) 
			{
                System.out.println(e1);
            }
		}
	}		
	
	public static void main(String []arg)
	{
		new login();
	}
}

class register implements ActionListener
{
	static JFrame frame;
	static JTree tree;
	static JMenuBar menubar;
	static JMenu menu1,menu2,menu3,menu4,menu5;
	static JMenuItem menuitem1,menuitem2,menuitem3,menuitem4,menuitem5;
	
	register()
	{
		frame =new JFrame();
		frame.setTitle("Registration");
        frame.setSize(1000,4000);  
		frame.setLayout(null);
		place();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		actionEvent();
	}
	
	void place()
	{
		menubar =new JMenuBar();
		menu1 =new JMenu("Register");
		menu1.setMnemonic(KeyEvent.VK_R);
		menu2 =new JMenu("Display");
		menu2.setMnemonic(KeyEvent.VK_S);
		menu3 =new JMenu("Delete");
		menu3.setMnemonic(KeyEvent.VK_D);
		menu4 =new JMenu("Contact Us");
		menu4.setMnemonic(KeyEvent.VK_C);
		menu5 =new JMenu("Exit");
		menu5.setMnemonic(KeyEvent.VK_E);
		menuitem1 =new JMenuItem("Register a prisoner",KeyEvent.VK_R);
		menuitem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_DOWN_MASK));
		menuitem2 =new JMenuItem("Prisoner Status",KeyEvent.VK_S);
		menuitem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		menuitem3 =new JMenuItem("Release",KeyEvent.VK_D);
		menuitem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_DOWN_MASK));
		menuitem4 =new JMenuItem("Contact",KeyEvent.VK_C);
		menuitem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK));
		menuitem5 =new JMenuItem("Exit",KeyEvent.VK_E);
		menuitem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_DOWN_MASK));
		menu1.add(menuitem1);
		menu2.add(menuitem2);
		menu3.add(menuitem3);
		menu4.add(menuitem4);
		menu5.add(menuitem5);
		menubar.add(menu1); menubar.add(menu2);   menubar.add(menu3);  menubar.add(menu4);  menubar.add(menu5);
		frame.setJMenuBar(menubar);
	}
	
	public void actionEvent()
	{
		menuitem1.addActionListener(this);
		menuitem2.addActionListener(this);
		menuitem3.addActionListener(this);
		menuitem4.addActionListener(this);
		menuitem5.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== menuitem1)
		{
			new addReg();
		}
		
		if(e.getSource()== menuitem2)
		{
			new display();
		}
		
		if(e.getSource()== menuitem3)
		{
			new delete();
		}
		
		if(e.getSource()== menuitem4)
		{
			new tree();
		}
		
		if(e.getSource()== menuitem5)
		{
			System.exit(0);
		}
	}
}


class addReg implements ActionListener
{
	static JFrame frame;
	JLabel label1,label2,label3,label4,label5,label6,label7;
	JRadioButton radiobutton1,radiobutton2,radiobutton3;
	ButtonGroup buttongroup;
	JTextField textfield1,textfield2,textfield3,textfield4,textfield5;
	JTextArea textarea;
	JComboBox combobox;
	JButton button1,button2,button3;
	
	addReg()
	{
		frame =new JFrame();
		frame.setTitle("New Entry");
        frame.setSize(1000,4000);  
		frame.setLayout(null);
		alignment();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		actionEvent();
	}
	
	void alignment()
	{
		label1 =new JLabel("Prisoner Name:");
		label2 =new JLabel("Prisoner ID:");
		label3 =new JLabel("Gender:");
		label4 =new JLabel("Age:");
		label5 =new JLabel("City:");
		label6 =new JLabel("Description:");
		label7 =new JLabel("Punishment:");
		textfield1 =new JTextField();
		textfield2 =new JTextField();
		textfield3 =new JTextField();
		textfield4 =new JTextField();
		textfield5 =new JTextField();
		radiobutton1 =new JRadioButton("Male");
		radiobutton2 =new JRadioButton("Female");
		radiobutton3 =new JRadioButton("Others");
		textarea =new JTextArea();
		String s[] ={"3 months", "6 months",
			"1 year",   "1 1/2 years",
			"2 years",  "3 years",
			"4 years",  "5 years",
			"Permanent"
		};
		combobox =new JComboBox(s);
		button1 =new JButton("ADD");
		button2 =new JButton("Submit");
		button3 =new JButton("Reset");
		buttongroup =new ButtonGroup();
		label1.setBounds(40,10,130,30);
		textfield1.setBounds(190,10,180,30);
		label2.setBounds(40,40,100,30);
		textfield2.setBounds(190,40,180,30);
		label3.setBounds(40,70,100,30);
		radiobutton1.setBounds(190,70,100,30);
		radiobutton2.setBounds(290,70,100,30);
		radiobutton3.setBounds(390,70,100,30);	
		buttongroup.add(radiobutton1);
		buttongroup.add(radiobutton2);
		buttongroup.add(radiobutton3);
		label4.setBounds(40,110,100,30);
		textfield3.setBounds(190,110,180,30);
		label5.setBounds(40,150,100,30);
		textfield4.setBounds(190,150,180,30);
		label6.setBounds(40,200,100,30);
		textarea.setBounds(190,200,180,100);
		label7.setBounds(40,330,100,30);
		combobox.setBounds(190,330,100,30);
		textfield5.setBounds(310,330,100,30);
		button1.setBounds(430,330,100,30);
		button2.setBounds(100,420,100,30);
		button3.setBounds(300,420,100,30);
		label1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label2.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label3.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label4.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label5.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label6.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label7.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield2.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield3.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield4.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield5.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		radiobutton1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		radiobutton2.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		radiobutton3.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textarea.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		combobox.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		button1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		button2.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		button3.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		frame.add(label1);	frame.add(label2);
		frame.add(label3);	frame.add(label4);	
		frame.add(label5);	frame.add(label6);
		frame.add(label7);	frame.add(combobox);  frame.add(textfield1);
		frame.add(textfield2);	frame.add(textfield3);
		frame.add(textfield4);	frame.add(textfield5); frame.add(radiobutton1);
		frame.add(radiobutton2);frame.add(radiobutton3);
		frame.add(textarea);	frame.add(button1);
		frame.add(button2);		frame.add(button3);
	}
	
	public void actionEvent()
	{
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
			
		if(e.getSource()==button1)
		{
			String s1= textfield5.getText();
			combobox.addItem(s1);
			textfield5.setText("");
		}
		
		if(e.getSource()==button2)
		{
			String s1= textfield1.getText();
			String s2= textfield2.getText();
			String s3= textfield3.getText();
			String s4= textfield4.getText();
			String s5= textarea.getText();
			String s6="Male";
			
			if(radiobutton2.isSelected()==true)
			{
				s6="Female";
			}
			else if(radiobutton3.isSelected()==true)
			{
				s6="Others";
			}
				
			try
			{
				if(s1!=""&&s2!=""&&s3!=""&&s4!=""&&s5!="")
				{
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");
					Statement stmt=con.createStatement();
					if(con!=null)
						System.out.println("Connection Success");
					ResultSet res =stmt.executeQuery("select prisonerid from prisoner where prisonerid='"+s2+"'");	
					if(res.next())
					{
						JOptionPane.showMessageDialog(null,"PRISONER ID ALREADY EXIST");
					}	
				
					else
					{
						stmt.executeUpdate("insert into prisoner values('"+s2+"','"+s1+"','"+s6+"','"+s3+"','"+s4+"','"+s5+"','"+combobox.getSelectedItem()+"')");
						JOptionPane.showMessageDialog(null,"REGISTERED SUCCESSFULLY");
					}	
					textfield1.setText("");
					textfield2.setText("");
					textfield3.setText("");
					textfield4.setText("");
					textarea.setText("");
					radiobutton1.setSelected(true);
					res.close();
					stmt.close();
					con.close();
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"COMPLETE THE FIELDS");
				}
			}	
			
			catch(SQLIntegrityConstraintViolationException e3)
			{
				JOptionPane.showMessageDialog(null,"Prisoner already exist"); 
			}
			
			catch (SQLException e1) 
			{
                System.out.println(e1);
            }										
		}
		
		if(e.getSource()==button3)
		{
			textfield1.setText("");
			textfield2.setText("");
			textfield3.setText("");
			textfield4.setText("");
			textfield5.setText("");
			textarea.setText("");
			radiobutton1.setSelected(true);
		}
	}
	 
	public static void main(String arg[])
	{
		new addReg();
	}	
}


class display implements ActionListener
{
	static JFrame frame;
	JButton button1;
	
	display()
	{
		frame =new JFrame();
		frame.setTitle("Prisoners");
        frame.setSize(1000,4000);  
		display();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		actionEvent();
	}
	
	void display()
	{
		button1 =new JButton("Display");
		button1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		JToolBar toolbar = new JToolBar();  
        toolbar.setRollover(true);  
		toolbar.add(button1);
		frame.add(toolbar,BorderLayout.NORTH);
	}
	
	public void actionEvent()
	{
		button1.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button1)
		{
			String[] header={
                "ID",
                "NAME",
                "GENDER","AGE","CITY","CRIME","PUNISHMENT"
			};
			int num;Object[][] data;
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");
				Statement stmt = con.createStatement();
				ResultSet r1= stmt.executeQuery("select count(*) from prisoner");
				r1.next();
				num=r1.getInt(1);
				data=new Object[num][7];
				ResultSet r=stmt.executeQuery("select * from prisoner");
            
				for(int i=0;i<num;i++)
				{
					r.next();
					data[i][0]=r.getString(1);
					data[i][1]=r.getString(2);
					data[i][2]=r.getString(3);
					data[i][3]=r.getString(4);
					data[i][4]=r.getString(5);
					data[i][5]=r.getString(6);
					data[i][6]=r.getString(7);
				}
				JTable table1=new JTable(data,header);
				JScrollPane sp3=new JScrollPane(table1);
				table1.setFont(new Font("Arial",Font.PLAIN,15));
			    table1.setRowHeight(30);
				table1. getColumnModel(). getColumn(2). setPreferredWidth(110);			  
				table1. getColumnModel(). getColumn(5). setPreferredWidth(150);
				table1.setLocation(200,100);
				table1.setSize(2500,350);
				table1.setRowHeight(30);
				table1.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
				table1.getTableHeader().setBackground(new Color(232, 57, 95));
				table1.getTableHeader().setForeground(new Color(255,255,255));
				table1.setShowVerticalLines(true);
				sp3.setLocation(50,100);
				sp3.setSize(1000,350);
				frame.add(sp3);
				r.close();
				r1.close();
				stmt.close();
				con.close();
			}
			
			catch(SQLException e1)
			{
				System.out.println(e1);
			}
		}
	}
	
	public static void main(String arg[])
	{
		new display();
	}
}


class delete implements ActionListener
{
	static JFrame frame;
	JLabel label1;
	JTextField textfield;
	JButton button1;
	
	delete()
	{
		frame =new JFrame();
		frame.setTitle("Delete Prisoner");
        frame.setSize(1000,4000);  
		frame.setLayout(null);
		deletebyid();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		actionEvent();
	}
	
	void deletebyid()
	{
		label1 =new JLabel("Prisoner ID");
		textfield =new JTextField();
		button1 =new JButton("Delete");
		button1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label1.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		textfield.setFont(new Font("TimesNewRoman",Font.PLAIN,16));
		label1.setBounds(40,10,130,30);
		textfield.setBounds(190,10,90,30);
		button1.setBounds(300,10,100,30);
		frame.add(label1);
		frame.add(textfield);
		frame.add(button1);
	}
	
	public void actionEvent()
	{
		button1.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s=textfield.getText();
		
		if(e.getSource()==button1)
		{
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");
				Statement stmt=con.createStatement();
				if(con!=null)
					System.out.println("Connection Success");
				ResultSet res =stmt.executeQuery("select prisonerid from prisoner where prisonerid='"+s+"'");	
				
				if(res.next())
				{
					stmt.executeUpdate("Delete from prisoner where prisonerid='"+s+"'");
					JOptionPane.showMessageDialog(null,"Prisoner data sucessfully deleted"); 
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"Prisoner data not found"); 
				}
				
				res.close();
				stmt.close();
				con.close();
			}
			
			catch(SQLIntegrityConstraintViolationException e3)
			{
				JOptionPane.showMessageDialog(null,"Prisoner data not found"); 
			}
			
			catch(SQLException e1)
			{
				System.out.print(e1);
			}
		}
	}
	
	public static void main(String arg[])
	{
		new delete();
	}
}

class tree 
{
	static JFrame frame;
	
	tree()
	{
		frame =new JFrame();
		frame.setTitle("For Support");
		frame.setSize(1000,4000);  
		contact();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	void contact()
	{
		DefaultMutableTreeNode Dev=new DefaultMutableTreeNode("Developer");  
		DefaultMutableTreeNode name1=new DefaultMutableTreeNode("Ashwin");  
		DefaultMutableTreeNode name2=new DefaultMutableTreeNode("Hari");  
		Dev.add(name1);   Dev.add(name2);  
		DefaultMutableTreeNode email1=new DefaultMutableTreeNode("awmtechno@gmail.com");  
		DefaultMutableTreeNode email2=new DefaultMutableTreeNode("harifatuous@gmail.com");  
		DefaultMutableTreeNode no1=new DefaultMutableTreeNode("1264-243-9048");  
		DefaultMutableTreeNode no2=new DefaultMutableTreeNode("8594-192-3925");  
		name1.add(email1); name1.add(no1); name2.add(email2); name2.add(no2);      
		JTree tree=new JTree(Dev);  
		tree.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		frame.add(tree);  
	}
	
	public static void main(String arg[])
	{
		new tree();
	}
}




























