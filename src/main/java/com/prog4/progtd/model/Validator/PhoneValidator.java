package com.prog4.progtd.model.Validator;

import com.prog4.progtd.Service.InvalidPhoneNumberException;
import com.prog4.progtd.model.Phone;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Component

public class PhoneValidator implements Consumer<String> {
    private final Set<String> allowedCountryCodes = new HashSet<>();

    public PhoneValidator() {
        allowedCountryCodes.add("+261");
        allowedCountryCodes.add("+33");
        allowedCountryCodes.add("+1");
    }
    @Override
    public void accept(String phoneNumber) {

        if (phoneNumber == null || ( phoneNumber.length() != 12 && phoneNumber.length() != 13 && phoneNumber.length() != 14)) {
            throw new InvalidPhoneNumberException("phone number is mandatory" + phoneNumber.length() +"masstay");
        }

    }
}


