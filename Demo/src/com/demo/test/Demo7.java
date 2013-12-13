package com.demo.test;

import java.io.Serializable;
import java.lang.reflect.*;

public class Demo7 {
        public static void main(String[] args) throws Exception {
                Class clazz = Person.class;

                Constructor con = clazz.getConstructor(String.class,int.class);
                Person p1 = (Person)con.newInstance("zz",25);
                System.out.println(p1);
                
                Person p2 = (Person)clazz.getConstructor().newInstance(null);
                clazz.getMethod("setName", String.class).invoke(p2, "zl");
                System.out.println(p2);
                
                Field field = clazz.getDeclaredField("age");
                field.setAccessible(true);
                if(field.getType()==int.class)
                        field.set(p2,20);
                System.out.println(field.get(p2));
                
        }
}

@SuppressWarnings("serial")
//实现标记接口
class Person implements Serializable {
        private String name;
        private int age;
        
        public String getName(){
                return this.name;
        }
        public void setName(String name){
               this.name = name;
        }
        
        public void getAge(int age){
                this.age = age;
        }
        public int setAge(){
                return age;
        }
        public Person(){}
        public Person(String name,int age){
                super();
                this.name = name;
                this.age = age;
        }
        //覆盖toString()方法
        @Override
        public String toString(){
                return "name: "+this.name+"; age: "+this.age;
        }
}
