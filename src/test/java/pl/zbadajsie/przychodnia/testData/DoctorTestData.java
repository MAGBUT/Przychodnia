package pl.zbadajsie.przychodnia.testData;

import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Specialization;
import pl.zbadajsie.przychodnia.model.User;

import java.util.Set;

public class DoctorTestData {
    public static Doctor someDoctor1(){
        return Doctor.builder()
                .description("description1")
                .person(getPerson1())
                .specialization(Set.of(Specialization.builder().name("dentysta").build()))
                .build();
    }

    private static Person getPerson1() {
        return Person.builder()
                .user(getUser1())
                .name("name1")
                .surname("surname1")
                .pesel("1234567890")
                .build();
    }

    private static User getUser1() {
        return User.builder()
                .userName("userName1")
                .password("password1")
                .email("email@email.pl1")
                .build();
    }

    public static Doctor someDoctor2(){
        return Doctor.builder()
                .description("description2")
                .person(getPerson2())
                .specialization(Set.of(Specialization.builder().name("dentysta").build()))
                .build();
    }

    private static Person getPerson2() {
        return Person.builder()
                .user(getUser2())
                .name("name2")
                .surname("surname2")
                .pesel("1234567890")
                .build();
    }

    private static User getUser2() {
        return User.builder()
                .userName("userName2")
                .password("password2")
                .email("email@email.pl2")
                .build();
    }
    public static Doctor someDoctor3(){
        return Doctor.builder()
                .description("description3")
                .person(getPerson3())
                .specialization(Set.of(Specialization.builder().name("dentysta").build()))
                .build();
    }

    private static Person getPerson3() {
        return Person.builder()
                .user(getUser3())
                .name("name3")
                .surname("surname3")
                .pesel("1234567890")
                .build();
    }

    private static User getUser3() {
        return User.builder()
                .userName("userName3")
                .password("password3")
                .email("email@email.pl3")
                .build();
    }
}
