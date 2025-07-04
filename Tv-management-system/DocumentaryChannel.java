public class DocumentaryChannel  extends TvChannel{
    int additionalFee=12;

    public DocumentaryChannel(String s, String l, String c, int p) {
        super(s,l,c,p);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+additionalFee;
    }
}
