import java.util.Comparator;

public class DescendingBidComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid firstBid, Bid secondBid) {
        return Integer.compare(secondBid.getBid(), firstBid.getBid());
    }
}