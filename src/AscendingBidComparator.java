import java.util.Comparator;

public class AscendingBidComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid firstBid, Bid secondBid) {
        return Integer.compare(firstBid.getBid(), secondBid.getBid());
    }
}