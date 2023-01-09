package com.jpaexample.spring.data.jpa.tutorial.repository;

import com.jpaexample.spring.data.jpa.tutorial.entity.Guardian;
import com.jpaexample.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("mohamedsiddhik@gmail.com")
                .firstName("mohamed")
                .lastName("siddhik")
                //.guardianName("ismail")
                //.guardianEmail("ismail@gmail.com")
                //.guardianMobile("9876543210")
                .build();

        studentRepository.save(student);
    }
    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("gani")
                .email("gani@gmail.com")
                .mobile("6783783547")
                .build();

        Student student = Student.builder()
                .firstName("abdul")
                .emailId("abdulrahman@gmail.com")
                .lastName("rahman")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("StudentList : "+ studentList);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("ab");
        System.out.println("STudnets : " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("gani");
        System.out.println("Guardian Name : " + students );
    }

    //JPQL
    @Test
    public void printGetStudentByEmailAddress(){

        Student students = studentRepository.getStudentByEmailAddress("abdulrahman@gmail.com");
        System.out.println("Student : " +students);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress("mohamedsiddhik@gmail.com");
        System.out.println("FirstName  : " + firstName );
    }

    @Test
    public void printGetStudentByEmailAddressNative(){

        Student students = studentRepository.getStudentByEmailAddressNative("abdulrahman@gmail.com");
        System.out.println("Student : " +students);
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam(){
        Student students = studentRepository.getStudentByEmailAddressNativeNamedParam("mohamedsiddhik@gmail.com");
        System.out.println("Students : " + students);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId(
                "mohamed abubakr",
                "mohamedsiddhik@gmail.com"
        );
    }
}