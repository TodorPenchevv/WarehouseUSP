package app.business.validators;

import app.business.exceptions.PartnerMailExistsException;
import app.business.repository.PartnerRepository;
import app.business.exceptions.CustomException;
import app.business.exceptions.InvalidEmailException;
import app.orm.Partner;

import java.util.List;
import java.util.regex.Pattern;

public class Email implements Validator {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public void validate() throws CustomException {
        if(!validFormat()) {
            throw new InvalidEmailException();
        }
        if(!unique()) {
            throw new PartnerMailExistsException();
        }
    }

    private boolean validFormat() {
        String basicPattern = "^(.+)@(\\S+)$";
        String strictPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        String unicodePattern = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        String topLevelDomainPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
                + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String RFC5322Pattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        String dotsPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String OwaspPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return patternMatches(email, basicPattern)
                && patternMatches(email, strictPattern)
                && patternMatches(email, unicodePattern)
                && patternMatches(email, topLevelDomainPattern)
                && patternMatches(email, RFC5322Pattern)
                && patternMatches(email, dotsPattern)
                && patternMatches(email, OwaspPattern);
    }
    //Source:
    //https://www.baeldung.com/java-email-validation-regex

    private boolean unique() {
        List<Partner> partners = PartnerRepository.findByMail(email);
        return partners.isEmpty();
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
