package com.example.WebLab3.main;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.io.Console;
import com.example.WebLab3.mbeans.AreaCounter;
import com.example.WebLab3.mbeans.PointCounter;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            Console console = new Console(scanner);

            MBeanServer server = ManagementFactory.getPlatformMBeanServer();

            ObjectName areaCounterMBeanName = new ObjectName("com.example.WebLab3.mbeans:type=AreaCounter");
            ObjectName pointCounterMBeanName = new ObjectName("com.example.WebLab3.mbeans:type=PointCounter");

            AreaCounter areaCounter = new AreaCounter();
            PointCounter pointCounter = new PointCounter();

            server.registerMBean(areaCounter, areaCounterMBeanName);
            server.registerMBean(pointCounter, pointCounterMBeanName);


            while (true) {
                double rValue = console.readDouble("Enter r value: ");
                double xValue = console.readDouble("Enter x value: ");
                double yValue = console.readDouble("Enter y value: ");

                areaCounter.calculateArea(rValue);

                Hit hit = new Hit();
                hit.setX(xValue);
                hit.setY(yValue);
                hit.setR(rValue);
                pointCounter.persistHit(hit);

                System.out.println(hit.toString());
            }
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }
}
