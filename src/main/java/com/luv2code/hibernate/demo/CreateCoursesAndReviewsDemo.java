package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesAndReviewsDemo {
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

            // add a course
            Course tempCourse = new Course("Spring Courses");

            // add a review
            tempCourse.addReview(new Review("Great course !!!"));
            tempCourse.addReview(new Review("The best course ever !!!"));
            tempCourse.addReview(new Review("I love this course !!!"));

            // save the course...cascade all
            System.out.println("Saving the courses !");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }
    }
}
