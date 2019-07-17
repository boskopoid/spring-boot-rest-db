package com.boskopoid.simpleservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@ManagedResource(
        objectName="PD:category=MBeans,name=testBean",
        description="Managed Bean")
@Component("testMbean")
public class TestMbean {
    private static final Logger logger = LogManager.getLogger(TestMbean.class);

    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);

    private String message = "Simple Message";

    public TestMbean(){
        System.out.println("......TestMbean........");
    }

   @ManagedOperation
    public void resetMessageViaMBean(){
        this.message = "Message RESET";
        System.out.println("resetMessageViaMBean");
   }

   @ManagedOperation
    public void setAppParameters(String param, String value){
       logger.debug("Hello from Log4j 2 - num : {}", () -> num);
        System.out.println("Setting " + param + " to " + value );

   }
    public void show(){
        System.out.println(message);
    }
}
