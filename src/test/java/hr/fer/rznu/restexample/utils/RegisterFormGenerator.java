package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.dto.RegisterForm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RegisterFormGenerator {

    public static RegisterForm createRegisterForm() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setFirstName("Karlo");
        registerForm.setLastName("Jezic");
        registerForm.setPassword("1234");
        registerForm.setUsername("kjezic");
        return registerForm;
    }

    public static RegisterForm createRealRegisterForm() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setFirstName("Jurica");
        registerForm.setLastName("Kenda");
        registerForm.setPassword("123");
        registerForm.setUsername("jkenda");
        return registerForm;
    }

    public static RegisterForm createBadRegisterForm() {
        return new RegisterForm();
    }


}
