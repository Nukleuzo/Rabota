package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("Задание 1 - количество несовершеннолетних");
        Long count = persons.stream()
                .filter(number -> number.getAge() < 18)
                .count();
        System.out.println(count + " человек ");

        System.out.println("Задание 2 - фамилии призывников");
        List<String> prizyvnik = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        prizyvnik.stream().limit(15).forEach(System.out::println);

        System.out.println("Задание 3 - фамилии работоспособных с высшим образованием");
        List<String> rabota = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getSex() == Sex.MAN ? x.getAge() < 65 : x.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        rabota.stream().limit(150).forEach(System.out::println);
    }
}