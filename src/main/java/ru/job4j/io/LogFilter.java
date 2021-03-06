package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                       .filter(LogFilter::check404)
                       .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(s -> out.write(s + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean check404(String str) {
        var arr = str.split(" ");
        return "404".equals(arr[arr.length - 2]);
    }

    public static void main(String[] args) {
        List<String> log = filter("input.txt");
        save(log, "404.txt");
    }

}
