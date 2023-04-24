package app.business.exceptions;

public class GoodNotFoundException extends CustomException {
    public GoodNotFoundException(String goodName) {
        super(goodName + " не съществува в списъка със стоки!");
    }
}
