import java.util.Date;

class Node {
    
    String flight_no; 
    Date arrival_time; 

    
    public Node() {
        this.flight_no = "";
        this.arrival_time = new Date();
    }

    
    public Node(String flight_no, Date arrival_time) {
        this.flight_no = flight_no;
        this.arrival_time = arrival_time;
    }

    
    public String getFlightNo() {
        return flight_no;
    }

    public Date getArrivalTime() {
        return arrival_time;
    }

    
    public void setFlightNo(String flight_no) {
        this.flight_no = flight_no;
    }

    public void setArrivalTime(Date arrival_time) {
        this.arrival_time = arrival_time;
    }
}
