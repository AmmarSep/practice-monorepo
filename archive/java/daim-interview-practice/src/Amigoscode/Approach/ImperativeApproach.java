package Amigoscode.Approach;


import java.util.ArrayList;
import java.util.List;

import static Amigoscode.Approach.ImperativeApproach.Gender.FEMALE;
import static Amigoscode.Approach.ImperativeApproach.Gender.MALE;


public class ImperativeApproach {
    public static void main(String[] args) {
//        Person people= new Person(List.of())
        List<Person> people = List.of(
                new Person("Ali", MALE, 23),
                new Person("Abdullah", MALE, 85),
                new Person("Angelina", FEMALE, 54),
                new Person("Kavya", FEMALE, 23),
                new Person("Fathima", FEMALE, 16));
        // imperative approach
        List<Person> females = new ArrayList<>();
        for (Person per : people) {
            if (FEMALE.equals(per.gender)) {
                females.add(per);
            }
           /* for(Person female: females)
            {
                System.out.println(female);
            }*/
        }
        for (Person female : females) {
            System.out.println(female);
        }
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
