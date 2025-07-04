import java.io.Serializable;

public class Subscription implements Serializable {
    private int installFee, nbTv, totalFee;
    private Subscriber subscriber;
    private SubScriptionCycle cycle;
    private String datee;

    public Subscription(int  nbtv, String datee, SubScriptionCycle cycle, Subscriber subscriber) {
        this.nbTv=nbtv;
        this.installFee = this.nbTv*10;         //installfee=10$ fixed
        this.datee = datee;
        this.cycle = cycle;
        this.subscriber = subscriber;
    }

    public int getInstallFee() {
        return installFee;
    }

    public void setInstallFee(int installFee) {
        this.installFee = installFee;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public SubScriptionCycle getCycle() {
        return cycle;
    }

    public void setCycle(SubScriptionCycle cycle) {
        this.cycle = cycle;
    }

    public int getTotalFee() {
        totalFee=installFee+5;
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public int getNbTv() {
        return nbTv;
    }

    public void setNbTv(int nbTv) {
        this.nbTv = nbTv;
    }

    @Override
    public String toString(){
        return "subscription"+" installfee= "+installFee+
                ", nbtv= "+nbTv+",  subscriber= "+subscriber+
                ", today= "+datee+", cycle"+cycle+"\n";
    }
}
