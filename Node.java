
public class Node {

    String data;
    int time;
    double cost;

    Node(String data) {
        this.data = data;
    }

    Node(String data, int time, double cost) {
        this.data = data;
        this.time = time;
        this.cost = cost;
    }
}
