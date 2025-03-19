package org.streams;

import org.streams.DAO.DataStreamer;
import org.streams.DAO.SoftwareEmployee;

public class Master_05 {

    public static void main(String[] args) {
        DataStreamer<SoftwareEmployee> employeeStream = new DataStreamer<>(SoftwareEmployee::generateRandom);

        employeeStream.generateDataFromCache(10,"Employees").forEach(System.out::println);

    }
}
