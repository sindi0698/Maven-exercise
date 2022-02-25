package ex2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail implements ConstraintValidator<EmailValid, String> {
    @Override
    public void initialize(EmailValid emailValid) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String regx = "^([a-zA-Z0-9]+)@([a-zA-Z0-9]+)\\.([a-zA-Z0-9]+)";
        Pattern emailPattern = Pattern.compile(regx);
        Matcher emailMatcher = emailPattern.matcher(s);
        return emailMatcher.matches();
    }
}
