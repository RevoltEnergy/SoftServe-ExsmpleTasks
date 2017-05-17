package pk.exampletests.Test2;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class, which describes employees with fixed payment.
 */
public class FixedPaymentEmployee extends Employees {

    public FixedPaymentEmployee(int ID, String name, double fixedMonthlyPayment) {
        this.ID = ID;
        this.name = name;
        this.payment = fixedMonthlyPayment;
    }

    @Override
    public double averageMonthlySalary() {
        return this.payment;
    }

    //Static method for reading from stream without FixedPaymentEmployee class instance
    static FixedPaymentEmployee load(BufferedReader reader) throws IOException {
        return new FixedPaymentEmployee(
                Integer.parseInt(reader.readLine()), reader.readLine(), Double.parseDouble(reader.readLine()));
    }
}
