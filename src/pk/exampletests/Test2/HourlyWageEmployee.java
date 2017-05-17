package pk.exampletests.Test2;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class, which describes employees with hourly wage.
 */
public class HourlyWageEmployee extends Employees {

    public HourlyWageEmployee(int ID, String name, double hourlyRate) {
        this.ID = ID;
        this.name = name;
        this.payment = hourlyRate;
    }

    @Override
    public double averageMonthlySalary() {
        return Math.round(20.8 * 8 * this.payment * 100) / 100;
    }

    //Static method for reading from stream without HourlyWageEmployee class instance
    static HourlyWageEmployee load(BufferedReader reader) throws IOException {
        return new HourlyWageEmployee(
                Integer.parseInt(reader.readLine()), reader.readLine(), Double.parseDouble(reader.readLine()));
    }
}
