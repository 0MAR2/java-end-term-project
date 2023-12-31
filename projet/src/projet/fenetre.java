package projet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class fenetre extends JFrame {
	JButton B=new JButton("admin");
	JButton B2=new JButton("client");
	Connection con;
	public fenetre() {
		setTitle("Acceuil");
		setSize(350,300);
		setResizable(false);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE );
		B.addActionListener(new l());
		B2.addActionListener(new l2());
		JPanel p=new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
		p.add(B,gbc);
		gbc.gridy = 1;
		p.add(B2,gbc);
		setContentPane(p);
		
		
	}
	public  class DB{
		public void createConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","6627");
				System.out.println("done");
			}catch(ClassNotFoundException ex) {
				Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
			}catch(SQLException ex) {
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}
		
	}
	public static void main(String[] args) {
		fenetre f= new fenetre();
		fenetre.DB mydb=f.new DB();
		mydb.createConnection();
		f.setVisible(true);
	}
	class l implements ActionListener{
		JFrame admin;
		JPanel p;
		JPanel p2;
		JPanel p3;
		JComboBox c1;
		JLabel l1;
		JTable t;
		DefaultTableModel tm;
		JButton b;
		public void actionPerformed(ActionEvent e) {
			admin=new JFrame("Admin");
			p= new JPanel();
			p.setLayout(new BorderLayout());
			p2= new JPanel();
			p2.setLayout(new GridBagLayout());
			p3= new JPanel();
			p3.setLayout(new GridBagLayout());
			c1=new JComboBox();
			c1.addItem("IMC");
			c1.addItem("BOUBLI");
			c1.addItem("SPARK");
			c1.addItem("ROBOTIQUE");
			c1.addItem("ORENDA");
			c1.addItem("J2I");
			c1.addItem("IM");
			c1.addItem("BD");
			c1.addItem("CM");
			c1.addItem("MIME");
			c1.addItem("AV");
			c1.addActionListener(new l3());
			l1=new JLabel("Choix:");
			b=new JButton("Terminer");
			b.addActionListener(new l4());
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.insets = new Insets(5, 5, 5, 5);
			p2.add(l1,gbc);
			gbc.gridx = 1;
			p2.add(c1,gbc);
			p.add(p2,BorderLayout.NORTH);
			String[] columnNames = {"Nom:", "Prénom:","Spécialité:","Club:"};
			tm = new DefaultTableModel(columnNames,0);
			tm.addRow(new Object[]{"Nom:", "Prénom:", "Specialité:", "Club:"});
			t = new JTable(tm);
			p.add(t,BorderLayout.CENTER);
			p3.add(b,gbc);
			p.add(p3,BorderLayout.SOUTH);
			admin.getContentPane().add(p);
			admin.setSize(700 , 400);
			admin.setResizable(false);
			admin.setVisible(true);
		}
	 	class l3 implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 try {
				 
				 tm.setRowCount(0);
				 tm.addRow(new Object[]{"Nom:", "Prénom:", "Specialité:", "Club:"});
				 String selected=c1.getSelectedItem().toString();
				 Statement stmt=con.createStatement();
				 ResultSet rs=stmt.executeQuery("select * from students WHERE Club ='"+selected+"'OR Specialité='"+selected+"';");
				 while(rs.next()) {
					 String nom=rs.getString("Nom");
					 String prenom=rs.getString("Prénom");
					 String specialite=rs.getString("Specialité");
					 String club=rs.getString("Club");
					 tm.addRow(new Object[]{nom, prenom, specialite, club});
				 }
				 
				 stmt.close();
				}catch(SQLException ex) {
					Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
					}
		 }
	 	}
	 	class l4 implements ActionListener{
			 public void actionPerformed(ActionEvent e) {
				 admin.dispose();
			 }
		}
	 }
	class l2 implements ActionListener{
		JFrame client;
		JTextField T;
		JTextField T2;
		JComboBox c;
		JComboBox c1;
		JPanel p;
		JPanel p2;
		JPanel p3;
		JLabel l1;
		JLabel l2;
		JLabel l3;
		JLabel l4;
		JButton b;
		JButton b2;
		 public void actionPerformed(ActionEvent e) {
				client=new JFrame("Client");
				p= new JPanel();
				p.setLayout(new BorderLayout());
				p2= new JPanel();
				p2.setLayout(new GridBagLayout());
				p3= new JPanel();
				p3.setLayout(new GridBagLayout());
				l1=new JLabel("Prenom:");
				l2=new JLabel("Nom:");
				l3=new JLabel("Specialité:");
				l4=new JLabel("Club:");
				T=new JTextField();
				T2=new JTextField();
				c=new JComboBox();
				c.addItem("IM");
				c.addItem("BD");
				c.addItem("CM");
				c.addItem("MIME");
				c.addItem("AV");
				c1=new JComboBox();
				c1.addItem("IMC");
				c1.addItem("BOUBLI");
				c1.addItem("SPARK");
				c1.addItem("ROBOTIQUE");
				c1.addItem("ORENDA");
				c1.addItem("J2I");
				b=new JButton("OK");
				b2=new JButton("Annuler");
				GridBagConstraints gbc = new GridBagConstraints();
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.insets = new Insets(5, 5, 5, 5);
		        gbc.fill = GridBagConstraints.HORIZONTAL;
				p2.add(l1,gbc);
				gbc.gridx = 1;
				p2.add(T,gbc);
				gbc.gridx = 0;
				gbc.gridy = 1;
				p2.add(l2,gbc);
				gbc.gridx = 1;
				p2.add(T2,gbc);
				gbc.gridx = 0;
				gbc.gridy = 2;
				p2.add(l3,gbc);
				gbc.gridx = 1;
				p2.add(c,gbc);
				gbc.gridx = 0;
				gbc.gridy = 3;
				p2.add(l4,gbc);
				gbc.gridx = 1;
				p2.add(c1,gbc);
				p.add(p2,BorderLayout.CENTER);
				b.addActionListener(new l3());
				b2.addActionListener(new l4());
				gbc.gridx = 0;
				gbc.gridy = 0;
				p3.add(b,gbc);
				gbc.gridx = 1;
				p3.add(b2,gbc);
				p.add(p3,BorderLayout.SOUTH);
				client.getContentPane().add(p);
				client.setSize(500 , 300);
				client.setResizable(false);
				client.setVisible(true);
			}
		 class l3 implements ActionListener{
			 public void actionPerformed(ActionEvent e) {
				 try {
					 Statement stmt=con.createStatement();
					 String prenom=T.getText();
					 String nom=T2.getText();
					 if (!prenom.isEmpty() && !nom.isEmpty()) {
						 String specialité=c.getSelectedItem().toString();
						 String club=c1.getSelectedItem().toString();
						 String insert="INSERT INTO students VALUES('"+nom+"','"+prenom+"','"+specialité+"','"+club+"')";
						 stmt.execute(insert);
						 JOptionPane.showMessageDialog(client,"succée");
						 client.dispose();
						 }
					 else {
						 JOptionPane.showMessageDialog(client, "le nom et le prénom ne peuvent pas être vides","Erreur",JOptionPane.ERROR_MESSAGE);
					 }
					 stmt.close();
					}catch(SQLException ex) {
						Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
						}
			 }
		 }
		 class l4 implements ActionListener{
			 public void actionPerformed(ActionEvent e) {
				 client.dispose();
			 }
		}
	}
}