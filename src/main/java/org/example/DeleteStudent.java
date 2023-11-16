package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdateStudent {
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
            // enter Id
            int studentId =3;
            session =factory.getCurrentSession();
            session.beginTransaction();
            // Display number of ID
            System.out.println("\n the student ID : "+studentId);
            student mysyudent = session.get(student.class,studentId);
            // Display Information of ID
            System.out.println("data of student : "+mysyudent);
            System.out.println("Updating student .....  ");
            //Update Column
            mysyudent.setFirstName("mahmoud");
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Update all Email of Student ");
            session.createQuery("UPDATE student  set email = 'sm@gmail.com'")
                            .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done !");
        }finally {
            factory.close();
        }
        con.close();
    }
}
