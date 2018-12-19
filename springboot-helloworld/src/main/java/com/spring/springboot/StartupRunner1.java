package com.spring.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Frankie Yang on 2018/1/3.
 */
public class StartupRunner1 implements CommandLineRunner {
    protected final Logger logger = LoggerFactory.getLogger(StartupRunner1.class);

//    @Order(value = 1)
    @Override
    public void run(String... strings) throws Exception {
        logger.info("hello 1");
        System.out.println("hello 1");
    }

    public static void main(String[] args){
        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);

//        System.out.println("rrr: " +
//            nums.stream().filter(num -> num != null).peek(System.out::println));

        System.out.println("rrr: " + nums.stream()
                .filter(num -> num != null)
                .distinct()
                .collect(Collectors.toList())
                );

        System.out.println("\n2\t");
        nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .forEach(System.out::print);

        /*System.out.println("\n3\t");
        nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::print)
                .skip(2)
                .forEach(System.out::print);*/

        System.out.println("\n3b\t");
        nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .skip(2)
                .forEach(System.out::print);

        /*System.out.println("\n4\t");
        nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::print)
                .skip(2)
                .limit(4)
                .forEach(System.out::print);*/

        System.out.println("\n4b\t");
        nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .skip(2)
                .limit(4)
                .forEach(System.out::print);

        System.out.println("\n5\t");
        nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::print)
                .skip(2)
                .limit(4)
                .sum();

        /*System.out.println("sum is:" + nums.stream()
                .filter(num -> num != null).distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::println)
                .skip(2)
                .limit(4)
                .sum());*/
    }

}