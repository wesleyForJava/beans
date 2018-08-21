package com.wesley.bean.demo;

public class ObjectsDemo {
    public static void main(String [] args) {
      Student jim = new Student();
        jim.setId(1);
        jim.setName("Jim");
        jim.setAge(13);
        /*System.out.println(jim.toString());*/
        
    }

    public static class Student {
        private int id;
        private String name;
        private int age;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

       /*public String toString() {
            return Objects.toStringHelper(this.getClass())
                    .add("id", id)
                    .add("name", name)
                    .add("age", age)
                    .omitNullValues().toString();
          
        }*/
    }
}
