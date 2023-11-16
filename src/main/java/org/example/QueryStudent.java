package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class readStudent {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/hbstudent?useSSL=false";
        String user = "root";
        String pass = "5826";

        Connection con = DriverManager.getConnection(url, user , pass);
        if (con != null)
            System.out.println("DON");


        // Create Session factory
        SessionFactory s = new Configuration()
                .configure("hibernae.cfg.xml")
                .addAnnotatedClass(student.class )
                .buildSessionFactory();

        // Create Session
        Session sec = s.getCurrentSession();
        try {
            // create object :
            student st = new student("khled" , "salman" , "sm0593969@gmail.com");

            //Start Transaction :
            sec.beginTransaction();

            // Save the data :
            System.out.println(st);
            sec.save(st);


            // Commit The Transaction :
            sec.getTransaction().commit();

            // find id
            System.out.println("save sudent . Generated ID: " + st.getId());
            // now get anew session and start transaction
            sec = s.getCurrentSession();
            sec.beginTransaction();

            // retrieve student based on the Id : primary key
            System.out.println( st.getId());
            student mystuden = sec.get(student.class, st.getId());
            System.out.println("GET Complte : "+ st);
            sec.getTransaction().commit();
            System.out.println("Done !");
        }finally {
            s.close();
        }
        con.close();
    }
}
