package com.example.task_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class Task3Application {

    public static void main(String[] args) {
        SpringApplication.run(Task3Application.class, args);

        String path = "D://test2.txt";
        StringBuilder stringBuilder;

        try {
            List<String> strings = Files.readAllLines(Path.of(path));
            for (int i = 0; i < strings.size(); i++) {
                int count = 0;
                int errorCount = 0;
                int characterCount = 0;

                stringBuilder = new StringBuilder(strings.get(i));

                for (int j = 0; j < stringBuilder.length(); j++) {

                    if (Character.isUpperCase(stringBuilder.charAt(j)) || stringBuilder.charAt(j) == '_') {
                        count++;
                    }

                    if (stringBuilder.charAt(j) == '_' && Character.isUpperCase(stringBuilder.charAt(j + 1))) {
                        errorCount++;
                    }

                    if (Character.isUpperCase(stringBuilder.charAt(0))) {
                        errorCount++;
                    }

                    if (stringBuilder.charAt(j) == '_') {
                        characterCount++;
                    }

                    if (stringBuilder.charAt(j) == '_' && errorCount == 0) {
                        stringBuilder.deleteCharAt(j);
                        stringBuilder.replace(j, j + 1, String.valueOf(Character.toUpperCase(stringBuilder.charAt(j))));
                        System.out.println("'" + strings.get(i) + "' -> " + stringBuilder);
                    }

                }
                    if (count == 0) {
                        System.out.println("'" + strings.get(i) + "' -> " + " unchanged");
                    }

                    if (errorCount > 0) {
                        System.out.println("'" + strings.get(i) + "' -> " + " Error!");
                    }

                   if (errorCount == 0 && characterCount == 0 && count > 0) {
                       String result = strings.get(i);
                       Pattern pattern = Pattern.compile("\\B([A-Z])");
                       Matcher matcher = pattern.matcher(result);
                       StringBuilder strb = new StringBuilder();
                       while (matcher.find()) {
                           matcher.appendReplacement(strb, "_$0");
                       }
                       matcher.appendTail(strb);
                       result = strb.toString().toLowerCase();

                       System.out.println("'" + strings.get(i) + "' -> " + result);
                   }
            }
            } catch(IOException e){
                throw new RuntimeException(e);
            }
    }
}