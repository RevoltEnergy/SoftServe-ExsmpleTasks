package pk.exampletests.Test1;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.new Customer("Lol", solution.new Rental(2, 10)).getRentalAmount() + " UAH");
    }

    class Customer {
        private String name;
        // Other fields, constructors, get, set, etc.
        private Rental rental;

        public Customer(String name, Rental rental) {
            this.name = name;
            this.rental = rental;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name=name;
        }

        public double getRentalAmount(){
            return rental.amountFor();
        }

        public int getRentalKind() {
            return rental.getKind();
        }

        public void setRentalKind(int kind) {
            this.rental.setKind(kind);
        }

        public int getRentalDays() {
            return rental.getDays();
        }

        public void setRentalDays(int days) {
            this.rental.setDays(days);
        }

        // Other methods.
    }
    class Rental {
        private int kind;
        private int days;
        // Other fields, constructors, get, set, etc.
        private double amount;

        private double amountFor() {
            double result = days * getBasePrice();
            result *= (kind == 1) ? 1.5 : (kind == 2) ? 2 : (kind == 3) ? 2.5 : 1;
            return (days > 7) ? result * 3 : result;
        }

        public Rental(int kind, int days) {
            this.kind = kind;
            this.days = days;
            this.amount = amountFor();
        }

        public double getAmount() {
            return amount;
        }

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
            this.kind = kind;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public double getBasePrice() {
            // Calculation of Price.
            // . . .
            return (kind == 1) ? 1100 : (kind == 2) ? 1200 : (kind == 3) ? 1300 : 1000;
        }
        // Other methods.
    }
}
