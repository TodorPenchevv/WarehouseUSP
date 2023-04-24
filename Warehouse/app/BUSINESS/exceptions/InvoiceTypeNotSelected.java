package app.business.exceptions;

public class InvoiceTypeNotSelected extends CustomException {
    public InvoiceTypeNotSelected() {
        super("Изберете дали фактурата е доставка или изписване!");
    }
}
