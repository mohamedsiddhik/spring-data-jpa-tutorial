package com.jpaexample.spring.data.jpa.tutorial.repository;

import com.jpaexample.spring.data.jpa.tutorial.entity.Course;
import com.jpaexample.spring.data.jpa.tutorial.entity.Student;
import com.jpaexample.spring.data.jpa.tutorial.entity.Teacher;
import net.bytebuddy.TypeCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses : " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("priyanka")
                .lastName("singh")
                .build();

        Course course = Course.builder()
                .title("python")
                .credit(8)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

//    @Test
//    public void findAllPagination(){
//        Pageable firstPageWithThreeRecords =
//                (Pageable) PageRequest.of(0,3);
//        Pageable firstPageWithTwoRecords =
//                (Pageable) PageRequest.of(1,2);
//
//        List<Course> courses =
//                courseRepository.findAll((Sort) firstPageWithThreeRecords).getContent();

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);
        Pageable firstPageWithTwoRecordsPage =
                PageRequest.of(1,2);

        List<Course> courses = courseRepository.findAll(firstPageWithTwoRecordsPage).getContent();

        Long totalElements = courseRepository.findAll(firstPageWithTwoRecordsPage).getTotalElements();

        int totalPages = courseRepository.findAll(firstPageWithTwoRecordsPage).getTotalPages();

        System.out.println("Total Pages : " + totalPages);
        System.out.println("Total Elements : " + totalElements);
        System.out.println("Courses : " + courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0,2,Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));
        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("Courses : " + courses);

    }

    @Test
    public void printFindByTitleContaining(){

        PageRequest firstPageTenRecords =
                PageRequest.of(0,10);

        List<Course> courses =
                courseRepository.findByTitleContaining("D",firstPageTenRecords).getContent();

        System.out.println("Courses : " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("morgan")
                .build();
        Student student = Student.builder()
                .firstName("abhishek")
                .lastName("singh")
                .emailId("abhisheksingh@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(9)
                .teacher(teacher)
                .build();

         course.addStudents(student);
         courseRepository.save(course);


    }
}