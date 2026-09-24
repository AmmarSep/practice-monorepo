package VariousStreamPrograms;

import java.util.List;
import java.util.stream.Collectors;


public class _streams {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Ali", Gender.MALE, 23),
                new Person("Abdullah", Gender.MALE, 85),
                new Person("Angelina", Gender.FEMALE, 54),
                new Person("Kavya", Gender.FEMALE, 23),
                new Person("Fathima", Gender.FEMALE, 16));
        people.stream()
                .map(person -> person.gender).collect(Collectors.toSet()).forEach(System.out::println);

    }
    static class Person{
        private final String name;
        private  final Gender gender;
        int age;
        Person(String ism, Gender sex, int ageGroup){
            this.name= ism;
            this.gender = sex;
            this.age= ageGroup;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    ", age=" + age +
                    '}';
        }
    }
    enum Gender{
        MALE, FEMALE
    }
}
