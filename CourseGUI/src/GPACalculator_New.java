
import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//above imports everything necessary for the entirety of the code

public class GPACalculator_New extends JFrame{

	//declaring main fields of the code
	//assuming these should be able to be accessed throughout the entirety oft eh code
	private double GPA = 0;
	private double req = 0;
	
	private JPanel title;
	private JPanel courses;
	private JPanel UI;
	private JPanel inputArea;
	private JPanel selec;
	private JPanel dis;
	
	private JTable table;
	
	public GPACalculator_New() {
		
		//ArrayList<Course> courseList = new ArrayList<Course>();
		//creating main frame
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
		JFrame frame = new JFrame("GPA Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		
		
		
		
		//creating header for the frame
		//http://www.cems.uwe.ac.uk/~jsa/UMLJavaShortCourse09/CGOutput/Unit11/unit11a(0809)/page_05.htm
		//assuming the header/title does not need to be moved, but people would like to know the purpose of the page
		title = new JPanel(new FlowLayout());
		JLabel titleLabel = new JLabel("GPA Calculator: Input Courses, Grades, and Credits");
		titleLabel.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, 100);
		title.add(titleLabel);
		titleLabel.setFont(new Font("", 2,35));
		title.setSize(new Dimension(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/5)));
		frame.add(title,BorderLayout.NORTH);
		
		
		//creating new JTable
		//http://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display
		//https://www.youtube.com/watch?v=22MBsRYuM4Q
		table = new JTable();
		
		Object[] textFields = new String[] {"Course Name", "Grade", "Credit Hours"};
		DefaultTableModel m = new DefaultTableModel();
		m.setColumnIdentifiers(textFields);
		table.setModel(m);
		
		
		//setting up table
 		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Times New Roman", 2,25));
		table.setRowHeight(40);
		table.setBorder(BorderFactory.createCompoundBorder());
		
		
		
		//Creating JScrollPane and adding table to pane
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html
		//assuming scrollable is more reliable because of the unknown number of courses
		//https://stackoverflow.com/questions/9393480/java-jpanel-inside-jscrollpane
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2));
		pane.setBounds(0,0,1000,50);
		
		//https://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html
		//adding to the frame
		frame.add(pane,BorderLayout.CENTER); //adding pane to frame
		
		
		
		//this should represent the bottom half of the screen below the grades
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
		//using box layout to stack text fields on top of each other
		//https://stackoverflow.com/questions/13571142/what-is-a-good-way-to-organize-multiple-jpanels-in-a-jframe
		UI = new JPanel();
		//UI.setSize(new Dimension(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2,500)));
		UI.setLayout(new BoxLayout(UI,BoxLayout.X_AXIS));
		frame.add(UI,BorderLayout.SOUTH);
		
		
		//UI will be the part of the program that the user interacts with
		//the bottom of the page consists of it, and it will contain 3 other Panels
		
		
		
		//Setting the input area
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextField.html
		//assuming that, for the most part, user will use logic when inputting fields
		//ex: will put int value in credit
		//is not going to be taking a double-digit credit course
		inputArea = new JPanel();
		JTextField nameText = new JTextField("Enter Course Name");
		JTextField gradeText = new JTextField("Enter Course Grade");
		JTextField creditText = new JTextField("Enter Course Credits");
		
		
		//changing the layout of the input area
		//using box layout again to stack on top of one another
		inputArea.setLayout(new BoxLayout(inputArea,BoxLayout.Y_AXIS));
		inputArea.add(nameText);
		inputArea.add(gradeText);
		inputArea.add(creditText);
		
		UI.add(inputArea);
		
		
		
		//setting up selection pane for what people would like to do with their inputs
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html
		//assuming the user can interpret how these buttons are linked with the text fields
		selec = new JPanel();
		selec.setLayout(new BoxLayout(selec,BoxLayout.Y_AXIS));
		JButton add = new JButton("Add");
		JButton up15 = new JButton("Add 15 Blank Credits");
		JButton remAll = new JButton("Remove All Courses");
		JButton rem = new JButton("Remove Single Course");
		
		
		//creating panel to hold to JTextField and JButton to submit the desired GPA
		JPanel check = new JPanel();
		check.setLayout(new BoxLayout(check,BoxLayout.X_AXIS));
		
		
		
		//assuming that, because the Check GPA button is directly next to the textfield
		//.. the user will realize that the button references the tect field
		JTextField desiredGPA = new JTextField("Enter Your Desired GPA");
		JButton checkGPA = new JButton("Check GPA");
		check.add(desiredGPA);
		check.add(checkGPA);
		
		
		
		//placing the buttons in the selection field
		//selec is in layout BoxLayout.Y_Axis so they will stack on top of each other
		selec.add(add);
		selec.add(up15);
		selec.add(remAll);
		selec.add(rem);
		selec.add(check);
		//adding the selection panel to the right side of the UI
		UI.add(selec);
		
		
		
		
		//panel to display GPA
		//because GPA is declared throughout the whole class, Layout will continually update as GPA is changed
		dis = new JPanel();
		dis.setLayout(new BoxLayout(dis,BoxLayout.Y_AXIS));
		JLabel currentGPA = new JLabel("Current GPA: " + GPA);
		dis.add(currentGPA);
		
		
		//Adding the Required GPA to display panel and then adding dis to UI
		JLabel reqGPA = new JLabel("Required GPA: " + req);
		dis.add(reqGPA);
		
		UI.add(dis);
		
		
		
		
		//actionListener for add button
		//https://www.youtube.com/watch?v=22MBsRYuM4Q
		Object[] info = new Object[3];
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//ensuring that all inputs are in proper form		
				//references the 3 text inputs
				info[0] = nameText.getText();
				info[1] = gradeText.getText();
				int a = Integer.parseInt(creditText.getText().substring(0, 1));
				info[2] = a;
				
				if(info[0].equals("") || info[0].equals("Enter Course Name")) {
					info[0] = null;
				}
				
				if(info[1].equals("") || info[1].equals("Enter Course Grade")) {
					info[1] = null;
				}
				
				
				if((!info[2].equals("") && !info[2].equals("Enter Course Credits"))) {
					
					m.addRow(info);
				}
				
				//calculating current GPA
				//https://stackoverflow.com/questions/32065024/is-to-iterate-through-a-jtable-and-pass-column-values-to-an-sql-query-in-java
				//creating variables to represent total credits, current GPA, number of empty rows, 
				double totalCount = 0; 
				double cGPA = 0;
				int emptyRows = 0;
				int totalCred = 0;
				//https://stackoverflow.com/questions/32065024/is-to-iterate-through-a-jtable-and-pass-column-values-to-an-sql-query-in-java
				//iterating through the table 
				for(int row = 0; row < m.getRowCount(); row++) {
				
					String b = (String)m.getValueAt(row, 1);
					
					if(b != null) {
						// if there is something contained in the Grade, it will correlate the letter grade to a GPA value
						//this is assuming that the user enters a logical grade value into the interface
						if(b.equals("A")) {
							cGPA = 4.0;
						}
						else if(b.equals("A-")) {
							cGPA = 3.7;
						}
						else if(b.equals("B")) {
							cGPA = 3.0;
						}
						else if(b.equals("B+")) {
							cGPA = 3.3;
						}
						else if(b.equals("B-")) {
							cGPA = 2.7;
						}
						else if(b.equals("C")) {
							cGPA = 2.0;
						}
						else if(b.equals("C+")) {
							cGPA = 2.3;
						}
						else if(b.equals("C-")) {
							cGPA = 1.7;
						}
						else if(b.equals("D")) {
							cGPA = 1.0;
						}
						else if(b.equals("D+")) {
							cGPA = 1.3;
						}
						else if(b.equals("D")) {
							cGPA = 0.7;
						}
						else {
							cGPA = 0.0;
						}
					
					
							totalCount+= cGPA * (int)m.getValueAt(row, 2);
						
						
						
							totalCred +=(int)m.getValueAt(row, 2);
					
					}
					
					
				}
				//assuming that schools only want gpa to 2 digits after decimal
				//https://stackoverflow.com/questions/7548841/round-a-double-to-3-significant-figures
				double finalGPA = totalCount/totalCred;
				GPA = finalGPA;
				BigDecimal big = new BigDecimal(GPA);
				big = big.round(new MathContext(3));
				GPA = big.doubleValue();
				currentGPA.setText("Current GPA: " + GPA);
				
				//giving notices to the user regarding his or her current GPA
				if(GPA > 4) {
					currentGPA.setText("Current GPA: " + GPA + "\nTry adding more Credit Hours");
				}
				if(GPA < 2) {
					currentGPA.setText("Current GPA: " + GPA + "\nTry taking less classes");
				}
				
				
				/*
				Course c1 = new Course(nameText.getText(), gradeText.getText(), Integer.parseInt(creditText.getText()));
				courseList.add(c1);
				
				m.addRow(c1.getName(),c1.get);*/
				
				
				
			}});
		
		

		//adding 15 blank credits
		up15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			//null for first 2 cases	
			Object[] up = {null,null,15};
			m.addRow(up);	
				
				
				
			}
			
			
		});
		
		//Listener for the CheckGPA button
		checkGPA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			
			//accessing desiredGPA text
			String s = (String) desiredGPA.getText();
			
			//keeping track of blankHours and totalCred
			//blank hours are credit hours without class name/grade
			//assumption that if a person leaves the name and grade of a course empty, they are using as a filler
			int blankHours = 0;
			int totalCred = 0;
			
			for(int row = 0; row < m.getRowCount(); row++) {
				
				if(m.getValueAt(row, 0) ==null && m.getValueAt(row, 1) ==null) {
					blankHours+= (int)m.getValueAt(row, 2);
				}
				
					totalCred +=(int)m.getValueAt(row, 2);
				System.out.println(blankHours);
				System.out.println(totalCred);
				
				
				
			}
			
			//take the input, turn it to string, parseDouble
			String in = (String) desiredGPA.getText();
			double des = Double.parseDouble(in);
			
			//calculating the required GPA with the other variables calculated 
			double requiredGPA = (((totalCred * des)-((totalCred-blankHours) * GPA))/(blankHours));
			req = requiredGPA;
			BigDecimal big = new BigDecimal(req);
			big = big.round(new MathContext(3));
			req = big.doubleValue();
			reqGPA.setText("Required GPA: " + req);
				
				
			}
			
		});
		
		
		//action listener to remove all in the list
		//should loop through and also recalculate the GPA
		remAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int row = m.getRowCount() - 1; row >=0 ; row--) {
					m.removeRow(row);
					
				}
			GPA = 0;
			currentGPA.setText("Current GPA: " + GPA);
			//display a different warning based on the GPA 
			if(GPA > 4) {
				currentGPA.setText("Current GPA: " + GPA + "\nTry adding more Credit Hours");
			}
			if(GPA < 2) {
				currentGPA.setText("Current GPA: " + GPA + "\nTry taking less classes");
			}
			
			
			
	
				
			}
			
			
		});
		//removing a single element from the table
		
		rem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html
				//using the getSelectedRow() method from the table class to streamline
				m.removeRow(table.getSelectedRow());
				
				
				//same loop as used in the add listener in order to go through and recalculate the GPA
				//assuming that nothing else in the table has been tampered with 
				double totalCount = 0; 
				double cGPA = 0;
				int emptyRows = 0;
				int totalCred = 0;
				
				for(int row = 0; row < m.getRowCount(); row++) {
				
					String b = (String)m.getValueAt(row, 1);
					// if there is something contained in the Grade, it will correlate the letter grade to a GPA value
					//this is assuming that the user enters a logical grade value into the interface
					if(b != null) {
					
						if(b.equals("A")) {
							cGPA = 4.0;
						}
						else if(b.equals("A-")) {
							cGPA = 3.7;
						}
						else if(b.equals("B")) {
							cGPA = 3.0;
						}
						else if(b.equals("B+")) {
							cGPA = 3.3;
						}
						else if(b.equals("B-")) {
							cGPA = 2.7;
						}
						else if(b.equals("C")) {
							cGPA = 2.0;
						}
						else if(b.equals("C+")) {
							cGPA = 2.3;
						}
						else if(b.equals("C-")) {
							cGPA = 1.7;
						}
						else if(b.equals("D")) {
							cGPA = 1.0;
						}
						else if(b.equals("D+")) {
							cGPA = 1.3;
						}
						else if(b.equals("D")) {
							cGPA = 0.7;
						}
						else {
							cGPA = 0.0;
						}
					
					
							totalCount+= cGPA * (int)m.getValueAt(row, 2);
						
						
						
							totalCred +=(int)m.getValueAt(row, 2);
					
					}
					
					
				}
				//assuming that schools only want gp to 2 digits after decimal
				//https://stackoverflow.com/questions/7548841/round-a-double-to-3-significant-figures
				double finalGPA = totalCount/totalCred;
				GPA = finalGPA;
				BigDecimal big = new BigDecimal(GPA);
				big = big.round(new MathContext(3));
				GPA = big.doubleValue();
				currentGPA.setText("Current GPA: " + GPA);
				/*
				Course c1 = new Course(nameText.getText(), gradeText.getText(), Integer.parseInt(creditText.getText()));
				courseList.add(c1);
				
				m.addRow(c1.getName(),c1.get);*/
				
				if(GPA > 4) {
					currentGPA.setText("Current GPA: " + GPA + "\nTry adding more Credit Hours");
				}
				if(GPA < 2) {
					currentGPA.setText("Current GPA: " + GPA + "\nTry taking less classes");
				}	
					
				}
			
	
			
		});
		
		
		
		 frame.setLocationRelativeTo(null); // Center it on Screen
	     frame.setVisible(true); // Set visibility to true
	}
	
	
	
	
	
	
	
	public static void main(String[]args) {
		//executing the above code in the main method
		new GPACalculator_New();
		
	}
	
	
	
	

}
