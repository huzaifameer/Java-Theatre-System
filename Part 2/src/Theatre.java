import java.io.*;
import java.util.*;

public class Theatre {
    private static final int[] row_1=new int[12]; //Array for the 1st row
    private static final int[] row_2=new int[16]; //Array for the 2nd row
    private static final int[] row_3=new int[20]; //Array for the 3rd row
    private static final ArrayList<int[]> rows=new ArrayList<>(); //Creating an Array List type-->int[]
    private static final ArrayList<Ticket> tickets_details=new ArrayList<>();//Creating an array list to add the person_information Arrays

    public static void main(String[] args){
        //Adding all the arrays into an Array List
        rows.add(row_1);
        rows.add(row_2);
        rows.add(row_3);
        //Assigning 0 as the default value to all the seats
        for (int[] i:rows) {
            for (int j=0;j<i.length;j++){
                i[j]=0;
            }
        }
        printHomeTemplate(); //Printing the home template
    }

    //Creating the method to print the home template
    private static void printHomeTemplate(){
        do {
            System.out.println("Please select an option : ");
            System.out.println();
            System.out.println("1). Buy a ticket");
            System.out.println("2). Print seating area");
            System.out.println("3). Cancel ticket");
            System.out.println("4). List available seats ");
            System.out.println("5). Save to file ");
            System.out.println("6). Load from file ");
            System.out.println("7). Print ticket information and total price");
            System.out.println("8). Sort tickets by price");
            System.out.println("0). Quit");
            System.out.println();
            selectOption();
        }while (true);
    }
    //Creating the method to get an option through the switch case.
    private static void selectOption(){
        int option=getOption();//This statement will assign the returning value from the getOption()

        //Calling the switch-case
        switch (option){
            case 0:
                System.exit(0);//Exit from the program
            case 1:
                buyTicket();//Method to buy a ticket
                break;
            case 2:
                print_seating_area();//Method to print the seating area
                break;
            case 3:
                cancel_ticket();//Method to cancel a booked ticket
                break;
            case 4:
                show_available();//Method to list the available seats
                break;
            case 5:
                save();//This method will write the 3 rows' details in a text file
                break;
            case 6:
                loadFromFile();//This method will write the 3 rows' details through the written text file
                break;
            case 7:
                System.out.println();
                show_tickets_info();
                break;
            case 8:
                System.out.println();
                sort_tickets();
                break;
        }
    }
    //Creating the method to get a valid option between 1-9
    private static int getOption(){
        Scanner input=new Scanner(System.in);
        do {
            System.out.print("Enter an Option : ");
            int option;
            while (true) {
                try {
                    option = Integer.parseInt(input.nextLine());//this line will get an integer as an option
                    break;//if the input is an integer it'll break the loop from here
                } catch (NumberFormatException e) {
                    System.out.println("It,s not an option! Please try again..");
                    System.out.print("Enter an Option : ");
                }
            }
            if (option>9 | option<0){
                System.out.println("Wrong option selected! Please try again..");
                continue;
            }
            return option;
        }while (true);
    }
    //Creating the method to buy a ticket
    private static void buyTicket(){
        Ticket ticket=new Ticket();
        System.out.println();
        Scanner input=new Scanner(System.in);
        Person person=new Person();
        //getting the person's details
        System.out.print("Enter person's name : ");
        person.setName(input.nextLine());
        System.out.print("Enter the surname   : ");
        person.setSurName(input.nextLine());
        System.out.print("Enter the e-mail    : ");
        person.seteMail(input.nextLine());
        System.out.println();

        // passing the Person's parameterized constructor with the values to the setPerson()....
        ticket.setPerson(person);
        Scanner row_seat= new Scanner(System.in);
        do {
            System.out.print("Input the row number  : ");
            int rowNum;
            while (true) {
                try {
                    rowNum = Integer.parseInt(row_seat.nextLine());//this line will get an integer as the row
                    break;//if the input is an integer it'll break the loop from here
                } catch (NumberFormatException e) {
                    System.out.println("It,s not a row ! Please try again..");
                    System.out.print("Input the row number : ");
                }
            }
            if (rowNum < 0 | rowNum > 3) {
                System.out.println("Error: This row number does not exist. Please select 1-3.’");
                continue;
            }
            ticket.setRow(rowNum);//setting the row number to the Ticket class's row(variable)
            break;
        }while (true);
        do {
            System.out.print("Input the seat number : ");
            int seatNum;
            while (true) {
                try {
                    seatNum = Integer.parseInt(row_seat.nextLine());//this line will get an integer as the row
                    break;//if the input is an integer it'll break the loop from here
                } catch (NumberFormatException e) {
                    System.out.println("It,s not a seat ! Please try again..");
                    System.out.print("Input the seat number : ");
                }
            }
            if (!((ticket.getRow() == 1 && (seatNum < 13 & seatNum > 0)) | (ticket.getRow() == 2 && (seatNum < 17 & seatNum > 0)) | (ticket.getRow() == 3 && (seatNum < 21 & seatNum > 0)))) {
                //validating the correct range
                System.out.println("Seat does not exists! Please enter a valid seat number.");
                continue;
            }else if (rows.get(ticket.getRow() - 1)[seatNum - 1] != 0) {
                //checking the availability
                System.out.println("Error: This seat is already taken. Please select another seat.");
                continue;
            } else {
                System.out.println("The seat no " + seatNum + " of row " + ticket.getRow() + " is available !");
                System.out.println("Your seat has been booked successfully.");
                System.out.println();
            }
            ticket.setSeat(seatNum);//setting the seat number to the Ticket class's seat(variable)
            break;
        }while (true);

        //-------------------------------------------------------
        //Adding the seat into the row array
        if (ticket.getRow() == 1) {
            addTheBooking(ticket.getSeat(), row_1);
        }else if (ticket.getRow() == 2) {
            addTheBooking(ticket.getSeat(), row_2);
        }else {
            addTheBooking(ticket.getSeat(), row_3);
        }
        //adding the ticket object to the array list
        tickets_details.add(ticket);
    }
    //Creating the method to add the booking into the arrays
    private static void addTheBooking(int seatNum, int[] row) {
        for (int i = 0; i< row.length; i++){
            if (i==(seatNum-1)){
                row[i]=1;
            }
        }
    }
    //Creating the method to print the seating area
    private static void print_seating_area(){
        System.out.println();
        System.out.format("%20s","***********\n");
        System.out.format("%20s","*  Stage  *\n");
        System.out.format("%20s","***********\n");
        System.out.println();
        for (int[] Row:rows){
            String printingRow= "";
            for (int k : Row) {
                if (k == 0) {
                    printingRow+="O";
                }else {
                    printingRow+="X";
                }
            }
            System.out.format("%12s",printingRow.substring(0,printingRow.length()/2));
            System.out.format("%-15s","   "+printingRow.substring((printingRow.length()/2)), printingRow.length()+1);
            System.out.println();
        }
        System.out.println();
    }
    //Creating the method to cancel a booked seat
    private static void cancel_ticket(){
        System.out.println();
        System.out.println("Enter the required details to cancel a booked ticket.");
        Scanner input=new Scanner(System.in);
        int rowNum;
        int seatNum;
        do {
            System.out.print("Input the row number  : ");
            while (true) {
                try {
                    rowNum =Integer.parseInt(input.nextLine());//this line will get an integer as the row
                    break;//if the input is an integer it'll break the loop from here
                } catch (NumberFormatException e) {
                    System.out.println("It,s not a row ! Please try again..");
                    System.out.print("Input the row number : ");
                }
            }
            if (rowNum < 0 | rowNum > 3) {
                System.out.println("Error: This row number does not exist. Please select 1-3.’");
                continue;
            }
            break;
        }while (true);
        do {
            try {
                System.out.print("Input the seat number : ");
                seatNum = Integer.parseInt(input.nextLine());
                if (rows.get(rowNum - 1)[seatNum - 1] == 0) {
                    System.out.println("Error: This seat is not booked. Please select the correct seat.");
                    break;
                } else {
                    cancelBooking(rowNum,seatNum, row_1);
                    cancelBooking(rowNum,seatNum, row_2);
                    cancelBooking(rowNum,seatNum, row_3);
                    System.out.println("Your seat has been cancelled successfully.");
                    System.out.println();
                }
            }catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
                System.out.println("Theres an error, please try out with the valid input..");
                //continue;
            }
            break;
        }while (true);
    }
    //Creating the method to add the canceled seat into the array
    private static void cancelBooking(int rowNum,int seatNum, int[] row) {
        for (int i = 0; i< row.length; i++){
            if (i==(seatNum-1)){
                row[i]=0;//assigning the seat value as 0.
            }
        }
        //------removing the ticket object from the system--------//
        tickets_details.removeIf(element -> element.getRow() == rowNum && element.getSeat() == seatNum);
    }
    //Creating the method to print the available seats as a list
    private static void show_available(){
        System.out.println();
        int i=1;
        for (int[] Row:rows){
            System.out.print("Seats available in row "+i+" : ");
            for (int element=0;element<Row.length;element++){
                if (Row[element]==0) {
                    System.out.print((element+1)+", ");
                }
            }
            i+=1;
            System.out.print("\b\b");
            System.out.println();
        }
        System.out.println();
    }
    //Creating the method to write the seat n row details in a text file
    private static void save() {
        System.out.println();
        int rowCount=1;
        try {
            //creating the text file named "Theatre_Rows.txt"
            FileWriter text_file_writer = new FileWriter("Theatre_Rows.txt");
            text_file_writer.write("Booked seats - 1\nAvailable seats - 0\n\n");//explanation for the bookings
            for (int[] row : rows) {
                text_file_writer.write("Row Number "+rowCount+" : ");//printing the row number
                for (int seat : row) {
                    text_file_writer.write(seat + " ");//printing the seat's state
                }
                text_file_writer.write("\n");
                rowCount+=1;//moving to the next row by increasing the value
            }
            text_file_writer.close();//closing the text file after writing the data
            System.out.println("All the data saved successfully !");//Displaying the message
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //if there's an error the error will display through the sout
        }
    }
    //Creating the method to write the seat n row details that provided by the text file
    private static void loadFromFile() {
        System.out.println();
        try {
            FileReader readTextFile = new FileReader("Theatre_Rows.txt");//getting the file to read
            int code = readTextFile.read();//assigning the first character to an int type variable
            while(code!=-1){
                //reading the file until the last character
                System.out.print((char)code);
                code=readTextFile.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //if there's an error the error will display through the "sout" statement.
        }
        System.out.println();
    }
    private static void show_tickets_info(){
        double total=0;//creating a variable to calculate and assign the total
        //using a loop to print the data's which are in the array list.
        for(Ticket m:tickets_details){
            m.print();//calling the "print" public method which has defined in the Ticket class
            total+=m.getPrice();
            System.out.println();
        }
        System.out.println("Total Value of the Tickets : "+total);
        System.out.println();
    }
    private static void sort_tickets(){

    }
}
