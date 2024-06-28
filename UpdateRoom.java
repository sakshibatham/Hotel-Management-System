import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame implements ActionListener {
    Choice ccustomer;
    JTextField tfroom, tfavailable, tfClean;
    JButton check, update, Back;
    UpdateRoom(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 25));
        text.setBounds(30,20,250,30);
        text.setForeground(Color.BLUE);
        add(text);

        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30,80,100,20);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(200,80,150,25);
        add(ccustomer);

        try{
            conn conn = new conn();
            ResultSet rs = conn.s.executeQuery("select * from customer");
            while (rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30,130,100,20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200,130,150,25);
        add(tfroom);

        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setBounds(30,180,100,20);
        add(lblavailable);

        tfavailable = new JTextField();
        tfavailable.setBounds(200,180,150,25);
        add(tfavailable);

        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setBounds(30,230,100,20);
        add(lblclean);

        tfClean = new JTextField();
        tfClean.setBounds(200,230,150,25);
        add(tfClean);

        check = new JButton("Check");
        check.setBounds(30,300,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150,300,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        Back = new JButton("Back");
        Back.setBounds(270,300,100,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);

        setBounds(300,200,980,500);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String id = ccustomer.getSelectedItem();
            String query = "select * from customer where number = '"+id+"'";
            try{
                conn conn = new conn();
                ResultSet rs = conn.s.executeQuery(query);
                while (rs.next()){
                    tfroom.setText(rs.getString("room"));
                }

                ResultSet rs2 = conn.s.executeQuery("Select * from room where roomnumber = '"+tfroom.getText()+"'");
                while (rs2.next()){
                    tfavailable.setText(rs2.getString("availabilty"));
                    tfClean.setText(rs2.getString("cleaning_status"));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String available = tfavailable.getText();
            String status = tfClean.getText();

            try{
                conn c = new conn();
                c.s.executeUpdate("update room set availabilty = '"+available+"', cleaning_status = '"+status+"' where roomnumber = '"+room+"'");

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
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
        new UpdateRoom();
    }
}

