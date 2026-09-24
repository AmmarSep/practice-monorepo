package Amigoscode.Approach;


import java.util.List;
import java.util.stream.Collectors;

import static Amigoscode.Approach.DeclarativeApproach.Gender.FEMALE;
import static Amigoscode.Approach.DeclarativeApproach.Gender.MALE;


public class DeclarativeApproach {
    public static void main(String[] args) {
//        Person people= new Person(List.of())
        List<Person> people = List.of(
                new Person("Ali", MALE, 23),
                new Person("Abdullah", MALE, 85),
                new Person("Angelina", FEMALE, 54),
                new Person("Kavya", FEMALE, 23),
                new Person("Fathima", FEMALE, 16));

        //declarative approach
        List<Person> females2 = people.stream().filter(pers -> FEMALE.equals(pers.gender)).collect(Collectors.toList());
        females2.forEach(System.out::println);
        // imperative approach
        /*List<Person> females = new ArrayList<>();
        for (Person per : people) {
            if (FEMALE.equals(per.gender)) {
                females.add(per);
            }
           *//* for(Person female: females)
            {
                System.out.println(female);
            }*//*
        }
        for (Person female : females) {
            System.out.println(female);
        }*/

    }

    static class Person {
        private final String name;
        private final Gender gender;
        int age;

        Person(String ism, Gender sex, int ageGroup) {
            this.name = ism;
            this.gender = sex;
            this.age = ageGroup;
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

    enum Gender {
        MALE, FEMALE
    }
}
