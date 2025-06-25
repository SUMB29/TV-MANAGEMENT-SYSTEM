public class MovieChannel  extends TvChannel{
    int additionalFee=15;

    public MovieChannel(String s, String l, String c, int p) {
        super(s,l,c,p);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+additionalFee;
    }
}
