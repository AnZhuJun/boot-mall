package com.damo.bootmall;

import com.damo.bootmall.bean.User;
import com.damo.bootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","NotEmpty");
        if(user.getUsername().length() < 6 || user.getUsername().length() > 32){
            errors.rejectValue("username","Size.user.username");
        }

        if(userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username","Duplicate.user.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty");
        if(user.getPassword().length() < 8 || user.getPassword().length() > 32){
            errors.rejectValue("password","Size.user.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())){
            errors.rejectValue("passwordConfirm","Diff.user.passwordConfirm");
        }
    }
}
