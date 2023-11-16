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
        SessionFactory factory = new Configuration()
                .configure("hibernae.cfg.xml")
                .addAnnotatedClass(student.class )
                .buildSessionFactory();

        // Create Session
        Session session = factory.getCurrentSession();
        try {
            // create object :
            student st = new student("khled" , "salman" , "sm0593969@gmail.com");

            //Start Transaction :
            session.beginTransaction();

            // Save the data :
            System.out.println(st);
            session.save(st);


            // Commit The Transaction :
            session.getTransaction().commit();

            // find id
            System.out.println("save sudent . Generated ID: " + st.getId());
            // now get anew session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the Id : primary key
            System.out.println( st.getId());
            student mystuden = session.get(student.class, st.getId());
            System.out.println("GET Complte : "+ st);
            session.getTransaction().commit();
            System.out.println("Done !");
        }finally {
            factory.close();
        }
        con.close();
    }
}
