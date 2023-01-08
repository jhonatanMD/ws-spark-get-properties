package org.example.business;


import org.example.util.GetValue;
import org.example.util.GetValueImpl;

public class MyRouteBuilder  {


    @GetValue("person.name")
    private String name;

    @GetValue("person.lastname")
    private String lastname;

    public MyRouteBuilder() throws Exception {
        GetValueImpl.run(this);
    }

    public String getName(){
        return name + " " + lastname;
    }


}
