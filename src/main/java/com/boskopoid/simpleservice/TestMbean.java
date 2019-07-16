package com.boskopoid.simpleservice;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource(
        objectName="PD:category=MBeans,name=testBean",
        description="Managed Bean")
@Component("testMbean")
public class TestMbean {

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
        System.out.println("Setting " + param + " to " + value );
   }
    public void show(){
        System.out.println(message);
    }
}
