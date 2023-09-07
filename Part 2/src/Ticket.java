public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket() {
    }
    public Ticket(int row, int seat,double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price=price;
        this.person = person;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    //this method is used to assign a value to the ticket's price also this will return the price
    private void updateTheTicketList(int row_num){
        if(row_num==1){
            setPrice(100);
        }else if(row_num==2){
            setPrice(200);
        }else if(row_num==3){
            setPrice(300);
        }
    }
    public void print(){
        updateTheTicketList(row);
        System.out.println("|---------------------------------|");
        System.out.println("Name    : "+person.getName());
        System.out.println("Surname : "+person.getSurName());
        System.out.println("E-mail  : "+person.geteMail());
        System.out.println("|---------------------------------|");
        System.out.println("\tRow Number   : "+getRow());
        System.out.println("\tSeat Number  : "+getSeat());
        System.out.println("\tTicket Price : "+getPrice());
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }

}
