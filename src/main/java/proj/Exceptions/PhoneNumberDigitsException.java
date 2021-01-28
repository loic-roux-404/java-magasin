package main.java.proj.Exceptions;

public class PhoneNumberDigitsException extends FormException {
    public PhoneNumberDigitsException() {
        super("Numéro de téléphone devant faire 10 chiffres");
    }

}
