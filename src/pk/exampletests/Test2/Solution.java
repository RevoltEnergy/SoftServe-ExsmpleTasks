package pk.exampletests.Test2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    //Collection of employees
    private ArrayList<Employees> employees;

    public static void main(String[] args) {
        Solution solution = new Solution();
        //Stream for input names of files from console.
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("*****************************************************************************************");
        //Filling collection with data.
        solution.employees = solution.fillCollectionWithData();
        System.out.println("*****************************************************************************************");
        //Sorting collection in given order.
        solution.sortCollectionInDescendingOrder(solution.employees);
        System.out.println("*****************************************************************************************");
        //Printing of first five employees.
        solution.firstFiveEmployees(solution.employees);
        System.out.println("*****************************************************************************************");
        //Printing of ID of three last employees.
        solution.idOfThreeLastEmployees(solution.employees);
        System.out.println("*****************************************************************************************");
        //Writing employees into file.
        String fileFormat = solution.writeEmployeesIntoFile(solution.employees, fileNameReader);
        System.out.println("*****************************************************************************************");
        //Reading employees from file.
        solution.readEmployeesFromFile(fileFormat, fileNameReader);
        System.out.println("*****************************************************************************************");
        //Closing of used stream.
        solution.closeStream(fileNameReader);
    }

    /*
    Sort the collection of employees in descending order by the average monthly salary.
    In the case of equal salary – by the name. Write ID, name and monthly salary for all employees from collection.
    */
    private void sortCollectionInDescendingOrder(ArrayList<Employees> employees) {
        Collections.sort(employees);
        System.out.println("Collection sorted in descending order. ID, name and monthly salary for all employees:");
        printCollection(employees);
    }

    /*
    Write information about first five employees from collection (problem a).
    */
    private void firstFiveEmployees(ArrayList<Employees> employees) {
        System.out.println("Information about first five employees from collection:");
        for (int i = 0, n = employees.size(); i < n && i < 5; i++) {
            System.out.println(employees.get(i).toString());
        }
    }

    /*
    Write ID of three last employees from collection (problem b).
    */
    private void idOfThreeLastEmployees(ArrayList<Employees> employees) {
        System.out.println("ID of three last employees from collection:");
        for (int n = employees.size(), k = n - 3, i = (k >= 0 ? k : 0); i < n; i++) {
            System.out.println(employees.get(i).getID());
        }
    }

    /*
    Write code for reading and writing collection of these objects from (into) file.
    */
    private String writeEmployeesIntoFile(ArrayList<Employees> employees, BufferedReader fileNameReader) {

        String fileName;
        try {
            System.out.println("Please, input the file name for writing:");
            fileName = fileNameReader.readLine();
        } catch (IOException e) {
            System.out.println("Writing collection into file: fileName input error. Reason: " + e.getMessage());
            return "";
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            for (Employees e : employees) e.save(writer);
            writer.flush();
            writer.close();
            if (writer.checkError()) System.out.println("Writing collection into file: PrintWriter error.");
        } catch (Exception e) {
            System.out.println("Writing collection into file: saving error. Reason: " + e.getMessage());
            return "";
        }

        System.out.println("All employees were saved into " + fileName);

        return fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()) : "";
    }

    private ArrayList<Employees> readEmployeesFromFile(String fileFormat, BufferedReader fileNameReader) {

        String fileName;
        try {
            System.out.println("Please, input the file name for reading:");
            fileName = fileNameReader.readLine();
            fileNameReader.close();
        } catch (IOException e) {
            System.out.println("Reading collection from file: fileName input error. Reason: " + e.getMessage());
            return null;
        }

        /*Write code for handling the incorrect format of incoming file.*/
        String newFileFormat = fileName.contains(".") ?
                fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()) : "";

        if (!fileFormat.equals(newFileFormat)) {
            System.out.println("Incorrect format of incoming file. \"" + fileFormat + "\" is expected.");
            return null;
        }

        ArrayList<Employees> employees = new ArrayList<>();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            while (fileReader.ready()) {
                String presence = fileReader.readLine();
                Employees employee = null;

                if      (presence.equals("HourlyWageEmployee"))     employee = HourlyWageEmployee.load(fileReader);
                else if (presence.equals("FixedPaymentEmployee"))   employee = FixedPaymentEmployee.load(fileReader);
                employees.add(employee);
            }
        } catch (IOException e) {
            System.out.println("File reading exception. Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Employees loaded from file " + fileName + ":");
        printCollection(employees);

        return employees;
    }

    private ArrayList<Employees> fillCollectionWithData() {
        ArrayList<Employees> employees = new ArrayList<>();
        employees.add(new FixedPaymentEmployee(employees.size() + 1, "Jack Daniel", 2550.5));
        employees.add(new HourlyWageEmployee  (employees.size() + 1,"Jim Beam", 15.53));
        employees.add(new FixedPaymentEmployee(employees.size() + 1,"John Walker", 2780.3));
        employees.add(new FixedPaymentEmployee(employees.size() + 1,"Peter Smirnoff", 2550.5));
        employees.add(new HourlyWageEmployee  (employees.size() + 1,"Jose Antonio de Cuervo", 20.1));
        employees.add(new FixedPaymentEmployee(employees.size() + 1,"Adolph Coors", 2550.5));
        employees.add(new HourlyWageEmployee  (employees.size() + 1,"Arthur Guinness", 18.3));
        employees.add(new HourlyWageEmployee  (employees.size() + 1,"Joseph Seagram", 14.1));

        System.out.println("Employees list:");
        printCollection(employees);

        return employees;
    }

    private void printCollection(ArrayList<Employees> employees) {
        for (Employees e : employees) System.out.println(e.toString());
    }

    private void closeStream(BufferedReader fileNameReader) {
        try {
            fileNameReader.close();
        } catch (IOException e) {
            System.out.println("Stream closing exception. Reason: " + e.getMessage());
        }
    }
}
