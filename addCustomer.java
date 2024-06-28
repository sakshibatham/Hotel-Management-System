import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class addCustomer extends JFrame implements ActionListener {

    JComboBox comboId;
    JRadioButton rbmale, rbfemale;
    JTextField tfnumber, tfname, tfcountry, tfadvance;
    Choice croom;
    JLabel checkinTime;
    JButton add, Back;
    addCustomer(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("New Customer Form");
        text.setFont(new Font("Raleway", Font.BOLD,20));
        text.setBounds(100, 20, 300,30);
        add(text);

        JLabel lblid = new JLabel("ID");
        lblid.setFont(new Font("Raleway", Font.PLAIN,16));
        lblid.setBounds(35, 80, 300,30);
        add(lblid);

        String options[] = {"Aadhar Card", "Passport", "Driving License","Voter Id", "Ration Card"};
        comboId = new JComboBox(options);
        comboId.setBounds(200,80,150,25);
        comboId.setBackground(Color.WHITE);
        add(comboId);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setFont(new Font("Raleway", Font.PLAIN,16));
        lblnumber.setBounds(35, 120, 100,20);
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Raleway", Font.PLAIN,16));
        lblname.setBounds(35, 160, 100,20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Raleway", Font.PLAIN,16));
        lblgender.setBounds(35, 200, 100,20);
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200,200,60,25);
//        rbmale.setFont(new Font("Raleway",Font.PLAIN,14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(270,200,100,25);
//        rbfemale.setFont(new Font("Raleway",Font.PLAIN,14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setFont(new Font("Raleway", Font.PLAIN,16));
        lblcountry.setBounds(35, 240, 100,20);
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Raleway", Font.PLAIN,16));
        lblroom.setBounds(35, 280, 150,20);
        add(lblroom);

        croom = new Choice();

        try{
            conn conn = new conn();
            String query  ="select * from room where availabilty = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        croom.setBounds(200,280,150,25);
        add(croom);

        JLabel lbltime = new JLabel("Checkin time");
        lbltime.setFont(new Font("Raleway", Font.PLAIN,16));
        lbltime.setBounds(35, 320, 150,20);
        add(lbltime);

        Date date = new Date();

        checkinTime = new JLabel("" + date);
        checkinTime.setFont(new Font("Raleway", Font.PLAIN,10));
        checkinTime.setBounds(200, 320, 150,25);
        add(checkinTime);

        JLabel lbladvance = new JLabel("Deposit");
        lbladvance.setFont(new Font("Raleway", Font.PLAIN,16));
        lbladvance.setBounds(35, 360, 150,20);
        add(lbladvance);

        tfadvance = new JTextField();
        tfadvance.setBounds(200,360,150,25);
        add(tfadvance);

        add = new JButton("Add");
        add.setBounds(50,410,120,30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        Back = new JButton("Back");
        Back.setBounds(200,410,120,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);

        setBounds(350,200,800,550);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add){
            String id = (String) comboId.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;

            if (rbmale.isSelected()){
                gender = "Male";
            } else if (rbfemale.isSelected()) {
                gender = "Female";
            }

            String country = tfcountry.getText();
            String room  = (String) croom.getSelectedItem();
            String time = checkinTime.getText();
            String deposit = tfadvance.getText();

            try{
                String query = "insert into customer values('"+id+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"','"+deposit+"')";
                String query2 = "update room set availabilty = 'Occupied' where roomnumber = '"+room+"'";

                conn conn = new conn();
                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "New customer added successfully");
                setVisible(false);
                new Reception();

            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource() == Back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new addCustomer();
    }
}
