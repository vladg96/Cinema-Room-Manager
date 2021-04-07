
package cinema;

import java.util.Scanner;

public class Cinema {
    public static int numberOfPurchaseTickets = 0;
    public static int currentIncome = 0;
    public static int totalIncome = 0;
    public static double percentage = 0.0;

    public static void main(String[] args) {
        String[][] cinemaHall = createCinema();
        printMenu(cinemaHall);
    }

    public static String[][] createCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] cinema = new String[rows + 1][seats + 1];
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                }
                if (i == 0 && j > 0) {
                    cinema[i][j] = String.valueOf(j);
                }
                if (i > 0 && j == 0) {
                    cinema[i][j] = String.valueOf(i);
                }
                if (i > 0 && j > 0) {
                    cinema[i][j] = "S";
                }
            }
        }

        return cinema;
    }

    public static void printMenu(String[][] cinema) {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int n = scanner.nextInt();
        switch (n) {
            case 1:
                showTheSeats(cinema);
                break;
            case 2:
                buyTicket(cinema);
                break;
            case 3:
                showStatistics(cinema);
                break;
            case 0:
                break;
        }

    }

    public static void showTheSeats(String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        printMenu(cinema);
    }

    public static void buyTicket(String[][] cinema) {
        System.out.println();
        int ticketPrice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int ticketRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int ticketSeat = scanner.nextInt();

        if (ticketRow > cinema.length - 1 || ticketSeat > cinema[0].length - 1 || ticketRow <= 0 || ticketSeat <= 0) {
            System.out.println();
            System.out.println("Wrong input!");
            buyTicket(cinema);
        }

        else if (cinema[ticketRow][ticketSeat] == "B"){
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinema);
        }

        else {
            if (cinema.length * cinema[0].length < 60) {
                ticketPrice = 10;
                System.out.printf("%nTicket price: $%d%n", ticketPrice);
                numberOfPurchaseTickets++;
                currentIncome += ticketPrice;
            }
            if (cinema.length * cinema[0].length >= 60) {
                if (ticketRow >= cinema.length / 2) {
                    ticketPrice = 8;
                } else {
                    ticketPrice = 10;
                }
                System.out.printf("%nTicket price: $%d%n", ticketPrice);
                numberOfPurchaseTickets++;
                currentIncome += ticketPrice;
            }
            cinema[ticketRow][ticketSeat] = "B";
            printMenu(cinema);
        }

    }

    public static void showStatistics(String[][] cinema) {
        percentage = (double) numberOfPurchaseTickets / ((cinema.length - 1) * (cinema[0].length - 1)) * 100;
        totalIncome = getIncome(cinema.length - 1, cinema[0].length - 1);
        System.out.printf("%nNumber of purchased tickets: %d%n", numberOfPurchaseTickets);
        System.out.printf("Percentage: %.2f%% %n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
        printMenu(cinema);

    }

    public static int getIncome(int rows, int seats){
        if(rows * seats < 60){
            int incomeLess60 = rows * seats * 10;
            return incomeLess60;

        }
        else{
            int incomeMore60 = (rows/2 * seats * 10) + ((rows - rows/2) * seats * 8);
            return incomeMore60;

        }
    }
}