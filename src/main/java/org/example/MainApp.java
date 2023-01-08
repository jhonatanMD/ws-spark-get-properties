package org.example;


import org.example.business.AppBusiness;
import org.example.business.MyRouteBuilder;

import static spark.Spark.get;

public class MainApp {

    public static void main(String... args) throws Exception {

        get("/hello", (req, res) -> {
            return new MyRouteBuilder().getName();
        });

        get("/get", (req, res) ->  new AppBusiness().getData());
    

    }



}

