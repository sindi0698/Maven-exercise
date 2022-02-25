package ex2;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        try {
            Class<?> userClass = Class.forName("ex2.User");
            Constructor<?> userConstructor = userClass.getConstructor(String.class, String.class, String.class, int.class, String.class);
            Object user = userConstructor.newInstance("Sindi", "Dhima", "Altin", 23, "sindi@gmail.com");
            System.out.println("Printing user with reflection: ");
            System.out.println(user);
            System.out.println("Printing annotation checks: ");
            checkIfAnnotationPresent(user);

            Field emailField = getEmailFieldAttribute(user);
            String usersValidEmail = "sindi@sindi.com";
            String usersInvalidEmal = "sindi.sindi@com";

            emailField.setAccessible(true);
            emailField.set(user, usersValidEmail);
            System.out.println(user);


//            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//            Validator validator = factory.getValidator();
//            Set<ConstraintViolation<Object>> violations = validator.validate(user);
//            System.out.println(violations.iterator().next().getMessage());

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkIfAnnotationPresent(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof FriendlyName) {
                    System.out.println(field.getName() + " attribute has " + ((FriendlyName) annotation).key());
                }
                if (annotation instanceof EmailValid) {
                    System.out.println(field.getName() + " attribute has " + ((EmailValid) annotation).key());
                }
                if (!(field.isAnnotationPresent(EmailValid.class) || field.isAnnotationPresent(FriendlyName.class)))
                    System.out.println(field.getName() + " doesn't have annotations FriendlyName or EmailValid");
            }
        }
    }

    public static Field getEmailFieldAttribute(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Field emailField = null;
        for (Field field : fields) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof EmailValid) {
                    emailField = field;
                    break;
                }
            }
        }
        return emailField;
    }
}