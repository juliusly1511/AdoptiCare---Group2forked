package util;

public class AgeConverter {

    public static String convertAge(double age) {

        // Convert years to total months
        int totalMonths = (int) Math.round(age * 12);

        // Less than 1 year
        if (totalMonths < 12) {
            if (totalMonths == 1) {
                return "1 Month";
            }
            return totalMonths + " Months";
        }

        // Years and remaining months
        int years = totalMonths / 12;
        int months = totalMonths % 12;

        // Exact number of years
        if (months == 0) {
            if (years == 1) {
                return "1 Year";
            }
            return years + " Years";
        }

        // Years and months
        String yearText = (years == 1) ? "Year" : "Years";
        String monthText = (months == 1) ? "Month" : "Months";

        return years + " " + yearText + " " + months + " " + monthText;
    }

    // Testing the converter
    public static void main(String[] args) {

        double[] ages = {
            0.08,
            0.17,
            0.25,
            0.33,
            0.42,
            0.50,
            0.58,
            0.67,
            0.75,
            0.83,
            0.92,
            1.00,
            1.25,
            1.50,
            2.75,
            5.00
        };

        for (double age : ages) {
            System.out.printf("%.2f -> %s%n", age, convertAge(age));
        }
    }
} 
