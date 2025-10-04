package model;

import java.time.LocalDate;

public class Person {

    private String firstName;
    private String lastName;
    private String personNumber;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName, String personNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
    }

    public Person(String firstName, String lastName, String personNumber, LocalDate dateOfBirth, String mobileNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformation() {

        return "Name: " + firstName + " " + lastName + "\n"
                + "Person Number: " + personNumber + "\n"
                + "Date of Birth: " + dateOfBirth + "\n"
                + "Mobile Number: " + mobileNumber + "\n"
                + "Email: " + email;

    }
}
