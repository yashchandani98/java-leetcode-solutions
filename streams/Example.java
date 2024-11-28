package streams;

import java.util.*;
import java.util.stream.*;
public class Example {

    static class Employee {
        int id;
        String name;
        String city;

        // Constructor
        public Employee(int id, String name, String city) {
            this.id = id;
            this.name = name;
            this.city = city;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', city='" + city + "'}";
        }
    }
    public static void main(String[] args) {
        /**
         * To print frequency of each word
         *
         * **/

        String text = "Hello Epam, how are you ?";

        // Process the string to count the frequency of each word
        Map<String, Long> wordFrequency = Arrays.stream(text.split("\\W+"))
                                            .map(String::toLowerCase)
                                            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        wordFrequency.forEach((word, count) -> System.out.println(word + ": " + count));


        String s = "asdfaghjklkjhgfdsa";

        /**
         * Stream to find the first repeating character
         * **/
        Optional<Character> firstRepeatingChar = s.chars()  // Convert string to IntStream
                                                .mapToObj(c -> (char) c)  // Convert int to Character
                                                .filter(c -> s.indexOf(c) != s.lastIndexOf(c)) // Check for repeating character
                                                .findFirst();  // Get the first occurrence

        firstRepeatingChar.ifPresent(System.out::println);  //



        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "Noida"),
                new Employee(2, "Bob", "Noida"),
                new Employee(3, "Charlie", "Delhi"),
                new Employee(4, "David", "Noida"),
                new Employee(5, "Eve", "Mumbai")
        );

        /**
         * Java 8 Stream to filter and sort employees from Noida
         * **/
        List<Employee> noidaEmployeesSorted = employees.stream()
                .filter(e -> "Noida".equalsIgnoreCase(e.getCity())) // Filter employees from Noida
                .sorted(Comparator.comparing(Employee::getName).reversed()) // Sort by name in reverse order
                .toList(); // Collect the results into a list

        // Print the sorted list
        noidaEmployeesSorted.forEach(System.out::println);
    }
}
