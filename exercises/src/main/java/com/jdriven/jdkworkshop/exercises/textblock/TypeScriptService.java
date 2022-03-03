package com.jdriven.jdkworkshop.exercises.textblock;

public class TypeScriptService {

    /*
    It is so easy to make mistakes when you're concatenating strings like this

    Use one of those fancy text blocks to make the type script readable
     */
    public String getTypeScript() {
        return "                class Person {\n" +
                "                    private name: string;\n" +
                "                    private age: number;\n" +
                "                    private salary: number;\n\n" +
                "                    constructor(name: string, age: number, salary: number) {\n" +
                "                        this.name = name;\n" +
                "                        this.age = age;\n" +
                "                        this.salary = salary;\n" +
                "                    }\n" +
                "                    toString(): string {\n" +
                "                        return `${this.name} (${this.age}) (${this.salary})`; // As of version 1.4\n" +
                "                    }\n" +
                "                }";
    }
}
