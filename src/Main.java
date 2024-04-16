public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PriorityQueue<Bid> buyerQueue = new PriorityQueue<>(new AscendingBidComparator());
        PriorityQueue<Bid> sellerQueue = new PriorityQueue<>(new AscendingBidComparator());
    }
}