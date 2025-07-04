import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
// import java.lang.reflect.Array;

//TV MANAGEMENT SYSTEM

public class MainScreen extends JFrame{

    //panels  declarations & initializations
    //panel-1   (user registrations)
    JPanel subscriberPanel;
    JTextField subName, subLastName, subMobile, subCity;
    JLabel nameLBL, lastLBL, mobileLBL, cityLBL;

    //panel-2   (Cycle)
    JTextField startCycleFLD, endCycleFLD, numberTvFLD;
    JLabel todayLBL, startCycleLBL, endCycleLBL, numberTvLBL;
    JPanel cyclePanel;
    SimpleDateFormat df;
    Date currentDate;

    //panel-3  (Channel's Packages)
    JCheckBox sportsCHKBX, moviesCHKBX, docCHKBX;
    JPanel packagesPanel;

    //panel-4  (Package Details)
    JTextArea channelsAreaS, channelsAreaM, channelsAreaD;
    JPanel detailsPanel;

    //panel-5  (Check & payments)
    JLabel installFeeLBL, packageFeeLBL, totalFeeLBL;
    JPanel feePanel;

    //panel-6  (Table- Data of Subscription)
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;

    //panel-7  (Action panel)
    JButton saveBTN, loadBTN, newBTN;
    JPanel p7ActionPanel;

    //classes & objects
    Subscriber subscriber;
    Subscription subscription;
    int packageSelectedPrice=0;
    int totalPrice;

    //saving
    ArrayList<Subscription> listToSave=new ArrayList<Subscription>();
    File file;



    public  MainScreen(){

        //panel-1
        subscriberPanel=new JPanel();
        Border panel1Title=BorderFactory.createTitledBorder("Subscriber Details");
        subscriberPanel.setBorder(panel1Title);

        subscriberPanel.setBounds(15,15,300,200);
        subscriberPanel.setLayout(new GridLayout(4,2));

        //JLabel
        nameLBL=new JLabel("NAME ");
        lastLBL=new JLabel("LAST NAME ");
        mobileLBL=new JLabel("MOBILE ");
        cityLBL=new JLabel("CITY ");

        //JTextFields
        subName=new JTextField();
        subName.setOpaque(false);
        subLastName=new JTextField();
        subLastName.setOpaque(false);
        subMobile=new JTextField();
        subMobile.setOpaque(false);
        subCity=new JTextField();
        subCity.setOpaque(false);

        //adding components to JPanel
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);
        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);

         //panel-2
        cyclePanel=new JPanel();
        cyclePanel.setBounds(15,230,300,500);
        cyclePanel.setLayout(new GridLayout(10,1));

        Border cycleBorder=BorderFactory.createTitledBorder("Cycle ");
        cyclePanel.setBorder(cycleBorder);

        //components of cyclePanel
        todayLBL=new JLabel();
        df=new SimpleDateFormat("dd/MM/yyyy");
        currentDate=new Date();
        todayLBL.setText("Today: "+df.format(currentDate));

        //startcyledate
        startCycleLBL=new JLabel("START CYCLE DATE (DD/MM/YYYY) ");
        startCycleFLD=new JTextField();

        //endcycledate
        endCycleLBL=new JLabel("END CYCLE DATE (DD/MM/YYYY) ");
        endCycleFLD=new JTextField();

        //number of TVs
        numberTvLBL=new JLabel("NUMBER OF TVs ");
        numberTvFLD=new JTextField();

        //making opacity for fields
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTvFLD.setOpaque(false);

        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTvLBL);
        cyclePanel.add(numberTvFLD);

        //panel-3
        packagesPanel=new JPanel();
        packagesPanel.setBounds(330,15,300,200);
        packagesPanel.setLayout(new GridLayout(5,1));

        Border pBorder=BorderFactory.createTitledBorder("Available Packages ");
        packagesPanel.setBorder(pBorder);

        JLabel packagesLBL=new JLabel("PLEASE SELECT YOUR PACKAGES ");

        sportsCHKBX=new JCheckBox("SPORTS PACKAGE ");
        moviesCHKBX=new JCheckBox("MOVIES PACKAGE ");
        docCHKBX=new JCheckBox("DOCUMENTARY PACKAGE ");

        JButton subscribeBTN=new JButton("SUBSCRIBE ");

        packagesPanel.add(packagesLBL);
        packagesPanel.add(sportsCHKBX);
        packagesPanel.add(moviesCHKBX);
        packagesPanel.add(docCHKBX);
        packagesPanel.add(subscribeBTN);

        //checkbox item listners
        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sportsCHKBX.isSelected()){
                    DisplaySportsChannels();
                }else {
                    channelsAreaS.setText("");
                }
            }
        });
        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(moviesCHKBX.isSelected()){
                    DisplayMoviesChannels();
                }else {
                    channelsAreaM.setText("");
                }
            }
        });
       docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(docCHKBX.isSelected()){
                    DisplayDocumentaryChannels();
                }else {
                    channelsAreaD.setText("");
                }
            }
        });
       subscribeBTN.addActionListener((new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try{
                   GetSubScriberData();
               }catch(Exception e1){
                   System.out.println(e1);
               }
           }
       }));

        //panel-4
        detailsPanel=new JPanel();
        detailsPanel.setBounds(330,230,300,500);
        detailsPanel.setLayout(new GridLayout(3,1));

        Border p4Border=BorderFactory.createTitledBorder("Available Channels ");
        detailsPanel.setBorder(p4Border);

        channelsAreaS=new JTextArea(5,1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);

        channelsAreaM=new JTextArea(5,1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);

        channelsAreaD=new JTextArea(5,1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);

        detailsPanel.add(channelsAreaS);
        detailsPanel.add(channelsAreaM);
        detailsPanel.add(channelsAreaD);

        //panel-5
        feePanel=new JPanel();
        feePanel.setBounds(645,15,200,200);
        feePanel.setLayout(new GridLayout(3,1));

        Border blackline5=BorderFactory.createTitledBorder("FEE & CHECK ");
        feePanel.setBorder(blackline5);

        installFeeLBL=new JLabel("INSTALLATION FEE: ");
        packageFeeLBL=new JLabel("PACKAGE FEE:  ");
        totalFeeLBL=new JLabel("TOTAL AMOUNT TO PAY: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);

        //panel-6
        p6Panel=new JPanel();
        p6Panel.setBounds(645,230,515,500);
        p6Panel.setLayout(new GridLayout(3,1));

        Border b6=BorderFactory.createTitledBorder("OUR CUSTOMERS ");
        p6Panel.setBorder(b6);

        //TABLE
        //1-TABLE MODEL
        tableModel=new DefaultTableModel();
        //2-COLUMNS
        table=new JTable(tableModel);
        tableModel.addColumn("FIRST NAME ");
        tableModel.addColumn("LAST NAME ");
        tableModel.addColumn("PHONE NUMBER  ");
        tableModel.addColumn("START CYCLE  ");
        tableModel.addColumn("END CYCLE  ");
        tableModel.addColumn("TOTAL FEE  ");
        //3-SCROLL PANE
        JScrollPane scrollPane=new JScrollPane(table);
        p6Panel.add(scrollPane);


        //panel-7
        p7ActionPanel=new JPanel();
        p7ActionPanel.setBounds(860,15,300,200);

        Border b7=BorderFactory.createTitledBorder("ACTION TAB ");
        p7ActionPanel.setBorder(b7);
        p7ActionPanel.setLayout(new GridLayout(4,1));

        saveBTN=new JButton("SAVE SUBSCRIPTIONS");
        loadBTN=new JButton("LOADS SUBSCRIPTIONS");
        newBTN=new JButton("NEW SUBSCRIPTIONS");

        p7ActionPanel.add(newBTN );
        p7ActionPanel.add(saveBTN );
        p7ActionPanel.add(loadBTN );

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });
        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription>k=LoadDataFromDisk();
            }
        });

        setLayout(null);      //null layout for JFrame
        add(subscriberPanel);         //panel-1
        add(cyclePanel);              //panel-2
        add(packagesPanel);           //panel-3
        add(detailsPanel);            //panel-4
        add(feePanel);                //panel-5
        add(p6Panel);                 //panel-6
        add(p7ActionPanel);           //panel-7
    }


    //methods
    private int DisplaySportsChannels(){
        SportsChannel m1=new SportsChannel("AFN SPORTS","EN","SPRT",5);
        SportsChannel m2=new SportsChannel("BEIN SPORTS","FR","SPRT",3);
        SportsChannel m3=new SportsChannel("ELEVEN SPORTS","EN","SPRT",8);
        SportsChannel m4=new SportsChannel("NBA TV","EN","SPRT",6);
        SportsChannel m5=new SportsChannel("NFL NETWORK","AR","SPRT",3);
        SportsChannel m6=new SportsChannel("THE SKY CHANNEL","USA","SPRT",1);

        ArrayList<SportsChannel> sChannels=new ArrayList<>();
        sChannels.add(m1);
        sChannels.add(m2);
        sChannels.add(m3);
        sChannels.add(m4);
        sChannels.add(m5);
        sChannels.add(m6);

        int packagePrice=0;
        String sChannelStr="";

        for(int i=0;i<sChannels.size();i++){
            sChannelStr+="  "+sChannels.get(i).getChannelName()+
                    "  "+sChannels.get(i).getLanguage()+"  "+
                    sChannels.get(i).getPrice()+"\n";
            packagePrice+=sChannels.get(i).getPrice();
        }
        channelsAreaS.setText(sChannelStr);
        return packagePrice;
    }

    private int DisplayMoviesChannels(){
        MovieChannel m1=new MovieChannel("MBC BUNDLE","SP","EN",3);
        MovieChannel m2=new MovieChannel("CINEMA ONE","USA","EN",2);
        MovieChannel m3=new MovieChannel("CINEMA PRO","UK","EN",3);
        MovieChannel m4=new MovieChannel("CINEMA 1","USA","EN",4);
        MovieChannel m5=new MovieChannel("MOVIE HOME","UK","EN",5);
        MovieChannel m6=new MovieChannel("FILM4","GR","EN",1);

        ArrayList<MovieChannel> mChannels=new ArrayList<>();
        mChannels.add(m1);
        mChannels.add(m2);
        mChannels.add(m3);
        mChannels.add(m4);
        mChannels.add(m5);
        mChannels.add(m6);

        int packagePrice=0;
        String mChannelStr="";

        for(int i=0;i<mChannels.size();i++){
            mChannelStr+="  "+mChannels.get(i).getChannelName()+
                    "  "+mChannels.get(i).getLanguage()+"  "+
                    mChannels.get(i).getPrice()+"\n";
            packagePrice+=mChannels.get(i).getPrice();
        }
        channelsAreaM.setText(mChannelStr);
        return packagePrice;
    }

    private int DisplayDocumentaryChannels(){
        DocumentaryChannel m1=new DocumentaryChannel("NAT GEO","SP","DOC",3);
        DocumentaryChannel m2=new DocumentaryChannel("PBS AMERICA","EN","DOC",2);
        DocumentaryChannel m3=new DocumentaryChannel("AL JAZEERA DOCUMENTARY","IN","DOC",3);
        DocumentaryChannel m4=new DocumentaryChannel("CANAL D","USA","EN",4);
        DocumentaryChannel m5=new DocumentaryChannel("DISCOVERY HISTORIA","AR","DOC",5);
        DocumentaryChannel m6=new DocumentaryChannel("WORLD DOCUMENTARY","GR","DOC",1);

        ArrayList<DocumentaryChannel> documentaryChannels=new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);

        int packagePrice=0;
        String docChannelStr="";

        for(int i=0;i<documentaryChannels.size();i++){
            docChannelStr+="  "+documentaryChannels.get(i).getChannelName()+
                    "  "+documentaryChannels.get(i).getLanguage()+"  "+
                    documentaryChannels.get(i).getPrice()+"\n";
            packagePrice+=documentaryChannels.get(i).getPrice();
        }
        channelsAreaD.setText(docChannelStr);
        return packagePrice;
    }

    private void SaveSubscriptionToDisk(){
        listToSave.add(subscription);
        file=new File("d:\\myfile.dat");
        try{
            OutputStream os=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(os);
            oos.writeObject(listToSave);   //saving the list of subscriptions

            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void NewSubscription(){
        //all fields are empty
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");
        startCycleFLD.setText("");
        endCycleFLD.setText("");
       numberTvFLD.setText("");

       installFeeLBL.setText("Installation fee : ");
       packageFeeLBL.setText("packages fee: ");
       totalFeeLBL.setText("Total amount to pay: ");

       moviesCHKBX.setSelected(false);
       docCHKBX.setSelected(false);
       sportsCHKBX.setSelected(false);
    }

    private ArrayList<Subscription> LoadDataFromDisk(){
        ArrayList<Subscription> s=new ArrayList<>();
        file=new File("d:\\myfile.dat");
        try{
            InputStream is=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(is);

            s=(ArrayList) ois.readObject();

            ois.close();
            is.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Subscription sub:s){
            DisplaySubscriptionsInTable(sub);
        }
        return s;
    }

    private void DisplaySubscriptionsInTable(Subscription sub){
        //displaying data from disk into table
        tableModel.addRow(new Object[]{
                sub.getSubscriber().getFname(),
                sub.getSubscriber().getLname(),
                sub.getSubscriber().getPhone(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        });
    }

    private void GetSubScriberData() throws ParseException {
    Date currentdate=new Date();
        //subscriber data
        subscriber=new Subscriber(subName.getText(),
                subLastName.getText(),subCity.getText(),Integer.parseInt(subMobile.getText()));
        //cycle
        Date startCycle=df.parse(startCycleFLD.getText());
        Date endCycle=df.parse(endCycleFLD.getText());

        SubScriptionCycle cycle=new SubScriptionCycle(df.format(startCycle),df.format(endCycle));

        //subscription
        subscription=new Subscription(Integer.parseInt(numberTvFLD.getText()),df.format(currentdate),cycle,subscriber);
        installFeeLBL.setText("Installation Fee: "+subscription.getTotalFee()+"$");
        showPrice();
    }

    private void showPrice(){
        if(docCHKBX.isSelected()){
            packageSelectedPrice+=DisplayDocumentaryChannels();
        }else if(moviesCHKBX.isSelected()){
            packageSelectedPrice+=DisplayMoviesChannels();
        }else if(sportsCHKBX.isSelected()){
            packageSelectedPrice+=DisplaySportsChannels();
        }
        packageFeeLBL.setText("PACKAGES FEE: "+packageSelectedPrice);
        totalPrice+=subscription.getTotalFee()+packageSelectedPrice;
        totalFeeLBL.setText("TOTAL AMOUNT TO PAY: "+totalPrice);
    }



    public static void main(String[] args) {
        MainScreen ms=new MainScreen();
        ms.setVisible(true);
        ms.setBounds(20,10,1200,800);
    }
}
