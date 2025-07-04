public class SportsChannel  extends TvChannel{
    int additionalFee=10;

    public SportsChannel(String s, String l, String c, int p) {
        super(s,l,c,p);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+additionalFee;
    }
}