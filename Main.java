import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

class PriorityQueue {

    int currentSize;
    Node root;
    Node[] array = new Node[25];


    public PriorityQueue() {
        this.currentSize = 0;
        this.root = null;

        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }


    public PriorityQueue(int currentSize, Node root, Node[] array) {
        this.currentSize = currentSize;
        this.root = root;
        this.array = array;
    }


    public int getCurrentSize() {
        return currentSize;
    }

    public Node getRoot() {
        return root;
    }

    public Node[] getArray() {
        return array;
    }


    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setArray(Node[] array) {
        this.array = array;
    }


    public void clear() {
        currentSize = 0;
        root = null;

        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }


    public boolean isEmpty() {
        return currentSize == 0;
    }


    public boolean add(Node x) {
        if (currentSize == array.length) {
            return false;
        }


        array[currentSize] = x;
        currentSize++;


        heapifyUp(currentSize - 1);

        return true;
    }


    public Node remove() {
        if (isEmpty()) {
            return null;
        }

        Node removedNode = array[0];
        array[0] = array[currentSize - 1];
        array[currentSize - 1] = null;
        currentSize--;

        percolateDown(0);

        return removedNode;
    }

    private void heapifyUp(int index) {
        while (index > 0 && array[index].arrival_time.before(array[parent(index)].arrival_time)) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void percolateDown(int hole) {
        int child;
        Node tmp = array[hole];
        while (hole * 2 + 1 < currentSize) {
            child = hole * 2 + 1;
            if (child != currentSize - 1 && array[child + 1].arrival_time.before(array[child].arrival_time))
                child++;
            if (array[child].arrival_time.before(tmp.arrival_time))
                array[hole] = array[child];
            else
                break;
            hole = child;
        }
        array[hole] = tmp;
    }


    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        Node temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

public class Main {
    public static void main(String[] args) {
        PriorityQueue minHeap = new PriorityQueue();


        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\weshicksstan\\Downloads\\flights.txt"))) {
            String line;
            // Contains flag to skip the first line (header). Code snippet from an AI source
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // This pre-processes the raw data and creates a Node. Code snippet from an AI source
                Node node = createNodeFromLine(line);
                if (node != null) {
                    minHeap.add(node);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Order of incoming flights:");
        while (!minHeap.isEmpty()) {
            Node removedNode = minHeap.remove();
            System.out.println("Flight No: " + removedNode.flight_no + ", Arrival Time: " + removedNode.arrival_time);
        }
    }

    /* This method creates a Node from a line of data in the text file. It then extracts flight number,
    departure timestamp, and flight duration from the parts. Also calculates the arrival time. Code snippet from an AI source. */
    private static Node createNodeFromLine(String line) {
        try {

            String[] parts = line.split(",");


            String flightNo = parts[0];
            String departureTimestampString = parts[1];
            String flightDuration = parts[2];

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date departureTimestamp = sdf.parse(departureTimestampString);
            long durationMillis = parseDurationString(flightDuration);
            Date arrivalTime = new Date(departureTimestamp.getTime() + durationMillis);


            return new Node(flightNo, arrivalTime);
        } catch (ParseException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error processing data: " + e.getMessage());
            return null;
        }
    }

    // This is a helper method to parse the flight duration string in the format "hh:mm:ss" and return the duration in milliseconds. Code snippet from an online source
    private static long parseDurationString(String durationString) {
        String[] parts = durationString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return (hours * 3600 + minutes * 60 + seconds) * 1000;
    }
}
  