package snk_atm_banking_system;

import java.util.*;
import javax.swing.*;
import java.io.*;
public class main {
    public static Scanner sc;
    public static ArrayList<String> name=new ArrayList<>();
    public static ArrayList<String> pin=new ArrayList<>();
    public static ArrayList<String> fund=new ArrayList<>();
    public static boolean first=true;
    
    public static void main(String[] args) {
        main_menu();
    }    
    
    public static void main_menu(){
        ImageIcon i1=new ImageIcon("bank.png");
        String[] options={"Login","Sign In","Exit"};
        int choice= JOptionPane.showOptionDialog(null,"Welcome to SNK ATM BANKING!!","ATM Machine", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,i1,options,0);
        if(choice==0){
           login();
        }else if(choice==1){
           signIn();
        }
        else{
            System.exit(0);
        }
    }
    
    public static void login(){
        while(first){
            loading();
            first=false;
        }
        ImageIcon i2=new ImageIcon("id.png");
        ImageIcon i3=new ImageIcon("padlock.png");
        String namInp=(String)JOptionPane.showInputDialog(null,"enter your username:-","client info",JOptionPane.WARNING_MESSAGE,i2,null,null);
        String pinInp=(String)JOptionPane.showInputDialog(null,"enter current password:-","client privacy",JOptionPane.WARNING_MESSAGE,i3,null,null);
        
        
        for(int i=0;i<name.size();i++){
            if(name.get(i).trim().equals(namInp)&&pin.get(i).trim().equals(pinInp)){
                JOptionPane.showMessageDialog(null,"Successfully Login!!","access granted",JOptionPane.INFORMATION_MESSAGE);
                bankOption(name.get(i));
            }    
        }
        JOptionPane.showMessageDialog(null,"Unable to Login!!","error",JOptionPane.ERROR_MESSAGE);
        main_menu();
    }    
        
    
    
    
    public static void signIn(){
        while(first){
            loading();
            first=false;
        }
        
        ImageIcon i2=new ImageIcon("id.png");
        ImageIcon i3=new ImageIcon("padlock.png");
        String nameInp=(String)JOptionPane.showInputDialog(null,"enter the username:-","privacy protected",JOptionPane.WARNING_MESSAGE,i2,null,null);
        String pinInp=(String)JOptionPane.showInputDialog(null,"enter your desired password:-","privacy protected",JOptionPane.WARNING_MESSAGE,i3,null,null);
        String fundInp="0";
        name.add(nameInp);
        pin.add(pinInp);
        fund.add(fundInp);
        
        try{
            FileWriter x=new FileWriter("userInfo.txt");
            for(int i=0;i<name.size();i++){
                x.write(name.get(i)+" "+pin.get(i)+" "+fund.get(i)+"\n");
            }
        x.close();
        }catch(Exception e){
            main_menu();
        }
        
        JOptionPane.showMessageDialog(null,"your account has been succesfully created","welcome",JOptionPane.INFORMATION_MESSAGE);
        main_menu();
    }
    
    public static void loading(){
        try{
            sc=new Scanner(new File("userInfo.txt"));
        }catch(Exception e){
            main_menu();
        }
        while(sc.hasNext()){
            name.add(sc.next());
            pin.add(sc.next());
            fund.add(sc.next());
        }
        sc.close();
    }
    
    public static void bankOption(String user){
        ImageIcon i4=new ImageIcon("cash.png");
        ImageIcon i5=new ImageIcon("balance.png");
        String[] features={"Check Balance","Withdraw Money","Deposit Money","Go to Main Menu"};
        String des=(String)JOptionPane.showInputDialog(null,"Hello, "+user+"!!\nwhat operation do you wanna proceed?","profile:- "+user,JOptionPane.WARNING_MESSAGE,i4,features,features[0]);
        int indx=name.indexOf(user);
        String naam=name.get(indx);
        String pass=pin.get(indx);
        double money=Double.parseDouble(fund.get(indx));
        
        if(des.equals("Check Balance")){
            JOptionPane.showMessageDialog(null,"Your bank Balance=\nTk."+fund.get(indx),"profile:- "+user,JOptionPane.WARNING_MESSAGE,i5);
            bankOption(user);
        }
        else if(des.equals("Withdraw Money")){
            ImageIcon i6=new ImageIcon("withdraw.png");
            String moneyInp=(String)JOptionPane.showInputDialog(null,user+",how much money(Tk) you wanna withdraw?","withdraw money",JOptionPane.WARNING_MESSAGE,i6,null,null);
            double withdraw=Double.parseDouble(moneyInp);
            if(money>withdraw){
                money=money-withdraw;
                String toSave=Double.toString(money);
                
                name.remove(indx);
                pin.remove(indx);
                fund.remove(indx);
                name.add(naam);
                pin.add(pass);
                fund.add(toSave);
                
                 try{
                    FileWriter z=new FileWriter("userInfo.txt");
                    for(int k=0;k<name.size();k++){
                        z.write(name.get(k)+" "+pin.get(k)+" "+fund.get(k)+"\n");
                    }
                    z.close();
                }catch(Exception e){
                    main_menu();
                }
                 
                JOptionPane.showMessageDialog(null,"after wthdrawing Tk."+moneyInp+" remaining balance=\nTk."+toSave,"profile:- "+user,JOptionPane.WARNING_MESSAGE,i5);
                bankOption(user);
            }    
            else{
                JOptionPane.showMessageDialog(null,"not sufficient balance!!","transiction stopped",JOptionPane.ERROR_MESSAGE);
                bankOption(user);
            }
            
        }
        else if(des.equals("Deposit Money")){
            ImageIcon i7=new ImageIcon("deposite.png");
            String moneyInp=(String)JOptionPane.showInputDialog(null,user+",amount of money(Tk) you wanna deposite?","deposite money",JOptionPane.WARNING_MESSAGE,i7,null,null);
            double deposite=Double.parseDouble(moneyInp);
            
            if(deposite>0){
                money=money+deposite;
                String toSave=Double.toString(money);
                
                name.remove(indx);
                pin.remove(indx);
                fund.remove(indx);
                name.add(naam);
                pin.add(pass);
                fund.add(toSave);
                
                try{
                    FileWriter z=new FileWriter("userInfo.txt");
                    for(int k=0;k<name.size();k++){
                        z.write(name.get(k)+" "+pin.get(k)+" "+fund.get(k)+"\n");
                    }
                    z.close();
                }catch(Exception e){
                    main_menu();
                }
                 
                JOptionPane.showMessageDialog(null,"after depositing Tk."+moneyInp+" total balance=\nTk."+toSave,"profile:- "+user,JOptionPane.WARNING_MESSAGE,i5);
                bankOption(user);
                
            }
            else{
                JOptionPane.showMessageDialog(null,"operation not possible!!","transiction stopped",JOptionPane.ERROR_MESSAGE);
                bankOption(user);
            }
        }
        else{
            main_menu();
        }
    }
}
