package com.poc.code.demo.reflection.annotations;

public class DemoAnnotationReflection {
    public void checkIfAnnotationApplied() {
        DemoAnnotation demoAnnotation = DemoClass.class.getAnnotation(DemoAnnotation.class);
        if (demoAnnotation != null) {
            System.out.println("Demo annotation applied");
        } else {
            System.out.println("Demo annotation not applied");
        }
    }
}
