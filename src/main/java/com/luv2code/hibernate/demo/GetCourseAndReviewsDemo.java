package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCourseAndReviewsDemo {
    public static void main(String[] args) {
        // create Session Factory
        // create session
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetails.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            // start a transaction
            session.beginTransaction();

            // get the course
            int courseId = 10;
            Course tempCourse = session.get(Course.class, courseId);

            // print the course
            System.out.println(tempCourse);

            // print the reviews
            System.out.println(tempCourse.getReviews());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }
    }
}
