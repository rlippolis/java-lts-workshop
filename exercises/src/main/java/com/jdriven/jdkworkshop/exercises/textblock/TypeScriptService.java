package com.jdriven.jdkworkshop.exercises.textblock;

public class TypeScriptService {

    /*
    It is so easy to make mistakes when you're concatenating strings like this

    Use one of those fancy text blocks to make the type script readable
     */
    public String getTypeScript() {
        return
                """
                class Person {
                    private name: string;
                    private age: number;
                    private salary: number;

                    constructor(name: string, age: number, salary: number) {
                        this.name = name;
                        this.age = age;
                        this.salary = salary;
                    }

                    toString(): string {
                        return `${this.name} (${this.age}) (${this.salary})`; // As of version 1.4
                    }
                }
                """;
    }
}
