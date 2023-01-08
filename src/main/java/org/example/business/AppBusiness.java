package org.example.business;


import org.example.util.GetValue;
import org.example.util.GetValueImpl;

public class AppBusiness {


    @GetValue("person.age")
    private Integer age;

    @GetValue("person.country")
    private String country;

    @GetValue("number1")
    private Integer n1;

    @GetValue("number2")
    private Integer n2;
    public AppBusiness() throws Exception {
        GetValueImpl.run(this);
    }

    public String getData(){
        System.out.println(n1 + n2);
        return country + " " + age;
    }


}
