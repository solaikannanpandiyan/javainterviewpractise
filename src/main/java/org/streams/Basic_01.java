package org.streams;

import org.streams.DAO.DataStreamer;
import org.streams.DAO.SoftwareEmployee;

public class Basic_01 {
    public static void main(String[] args) {
        DataStreamer<SoftwareEmployee> employeeStream = new DataStreamer<>(SoftwareEmployee::generateRandom);

        employeeStream.generateDataFromCache(10,"Employees");

    }
}