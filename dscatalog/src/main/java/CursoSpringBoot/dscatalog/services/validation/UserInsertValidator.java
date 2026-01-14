package CursoSpringBoot.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import CursoSpringBoot.dscatalog.entities.User;
import CursoSpringBoot.dscatalog.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import CursoSpringBoot.dscatalog.dto.UserInsertDTO;
import CursoSpringBoot.dscatalog.exceptions.handler.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;


public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();


        User user = repository.findByEmail(dto.getEmail());


        if (user != null){
            list.add(new FieldMessage("email", "Email já existe"));

        }



        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
