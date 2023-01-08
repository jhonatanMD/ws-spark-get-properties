package org.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class  GetValueImpl{

    private static final String FILE_PROPERTY= "src/main/resources/application.properties";
    private static final String FILE_YAML= "src/main/resources/application.yaml";
    private static final String FILE_YML="src/main/resources/application.yml";
    public static void run(Object o) throws Exception {


       Boolean flag = false;

        Properties properties = new Properties();

        if(new File(FILE_PROPERTY).exists())
            properties.load(new FileInputStream(FILE_PROPERTY));
        if(new File(FILE_YAML).exists()) {
            flag = Boolean.TRUE;
            properties.load(new FileInputStream(FILE_YAML));
        }
        if(new File(FILE_YML).exists()){
            flag = Boolean.TRUE;
            properties.load(new FileInputStream(FILE_YML));
        }



       // Reflections reflections = new Reflections("org.example", new SubTypesScanner(false));
     //   Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

      //  for (Class<?> qlass : allClasses) {

            Class<?> c = o.getClass();
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(GetValue.class)) {

                    String value = field.getAnnotation(GetValue.class).value();

                    if(flag) {
                        String[] split =  value.split("\\.");
                        value = split[split.length - 1];
                    }
                    field.setAccessible(true);

                    if(properties.get(value) == null)
                        throw new Exception("No se encontro ruta " + value);
                   if(field.getType() == Integer.class)
                        field.set(o,Integer.parseInt(getValueProperty(properties.get(value).toString())));
                   if(field.getType() == Long.class)
                       field.set(o,Long.parseLong(getValueProperty(properties.get(value).toString())));
                   if(field.getType() == Boolean.class)
                        field.set(o,Boolean.valueOf(getValueProperty(properties.get(value).toString())));
                   if(field.getType() == Byte.class)
                        field.set(o,Byte.valueOf(getValueProperty(properties.get(value).toString())));
                   if(field.getType() == String.class)
                       field.set(o,getValueProperty(properties.get(value).toString()));

                    field.setAccessible(false);
                }
            }

    //    }
        /*
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.yml"));
        System.out.println(properties.get("person.name"));*/
    }


    private static String getValueProperty(String value){

        String responseValue;

        String[] split = value.matches("^<\\w+:\\w+>") ? value.replace("<","").replace(">","").split(":") : new String[]{value};

        if(split.length == 2)
            responseValue = System.getenv(split[0]) == null ? split[1] : System.getenv(split[0]);
        else
            responseValue = value;

        return responseValue;
    }
}
