package model;

import java.time.LocalDate;

public class Student extends Person {

    public Student() {

    }

    public Student(String firstName, String lastName, String personNumber) {
        super(firstName, lastName, personNumber);

    }

    public Student(String firstName, String lastName, String personNumber, LocalDate dateOfBirth, String mobileNumber, String email) {
        super(firstName, lastName, personNumber, dateOfBirth, mobileNumber, email);
    }

    @Override
    public String getInformation() {
        return super.getInformation() + "\n" + "Role: Student";

    }

}
