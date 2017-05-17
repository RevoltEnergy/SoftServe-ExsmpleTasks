package pk.exampletests.Test2;

import java.io.*;

/**
 * Abstract class, which describes common features of any type of employee.
 */
abstract class Employees implements Comparable<Employees> {
    //ID of employee
    int ID = 0;
    //Name of employee
    String name;
    //Hourly or monthly payment of employee
    double payment;

    //Abstract method for counting of average monthly salary of employee
    abstract double averageMonthlySalary();

    int getID() {
        return ID;
    }

    /*Algorithm for sorting in descending order by the average monthly salary
    and by the name in case of equal salary.*/
    @Override
    public int compareTo(Employees e) {
        return e.averageMonthlySalary() - this.averageMonthlySalary() > 0.0 ?  1 :
               e.averageMonthlySalary() - this.averageMonthlySalary() < 0.0 ? -1 :
               this.name.compareTo(e.name);
    }

    /*Complete information about a particular employee.*/
    @Override
    public String toString() {
        String employeeType = this.getClass().getSimpleName().equals("FixedPaymentEmployee")
                ? "fixed payment" : "hourly wage  ";
        return "Employee with " + employeeType + " {" +
                " id = " + ID +
                ", name = '" + name + '\'' +
                ", average monthly salary = " + averageMonthlySalary() +
                '}';
    }

    /*Write employee data into stream*/
    void save(PrintWriter writer) {
        writer.println(this.getClass().getSimpleName());
        writer.println(this.ID);
        writer.println(this.name);
        writer.println(this.payment);
        writer.flush();
    }
}
