import java.util.*;
import java.lang.*;
import java.io.*;
public class Main
{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number_partition;
        //number of partition
        int number_process;
        //number of process
        ArrayList<partition>Partition=new ArrayList<partition>();
        ArrayList<Process>process=new ArrayList<Process>();
        System.out.print("please enter number of partition :");
        number_partition=sc.nextInt();
        for(int i=0;i<number_partition;i++){
            System.out.print("please enter name and size of partition :");
            String s;s=sc.next();
            int x=sc.nextInt();
            partition p=new partition(s,x,"External fragment");
            Partition.add(p);
        }
        System.out.print("please enter number of Process :");
        number_process=sc.nextInt();
        for(int i=0;i<number_process;i++){
            System.out.print("please enter name and size of Process :");
            String s;s=sc.next();
            int x=sc.nextInt();
            Process p=new Process(s,x,0);
            process.add(p);
        }
        ArrayList<partition>Partition1=new ArrayList<partition>();//store data of partition and change every case
        // Partition1=Partition;
        for(int i=0;i<Partition.size();i++){
            String S=Partition.get(i).name;
            String S1=Partition.get(i).nullprocess;
            int N=Partition.get(i).size;
            partition p1=new partition(S,N,S1);
            Partition1.add(p1);
        }
        ArrayList<Process>process1=new ArrayList<Process>();//store data of process;
        for(int i=0;i<process.size();i++){
            String S=process.get(i).name;
            int N1=process.get(i).take;
            int N=process.get(i).size;
            Process d1=new Process(S,N,N1);
            process1.add(d1);
        }
        while (true){
            System.out.print("Select the policy you want to apply:\n1. First fit\n2. Worst fit\n3. Best fit\n4.exit\nSelect policy:");
            int choice=sc.nextInt();
            if(choice==1){//First fit
                for(int i=0;i<process1.size();i++){
                    int diff=-1;
                    String s="Partition"+Partition1.size();
                    for(int j=0;j<Partition1.size();j++){
                        if(Partition1.get(j).size>=process1.get(i).size && Partition1.get(j).nullprocess=="External fragment"){
                            if(Partition1.get(j).size==process1.get(i).size){
                                Partition1.get(j).nullprocess=process1.get(i).name;
                                process1.get(i).take=1;
                                break;
                            }
                            else{
                                diff=Partition1.get(j).size - process1.get(i).size;
                                Partition1.get(j).size=process1.get(i).size;
                                Partition1.get(j).nullprocess=process1.get(i).name;
                                process1.get(i).take=1;

                                break;
                            }
                        }
                    }
                    if(diff>0){
                        partition p1=new partition(s,diff,"External fragment");
                        Partition1.add(p1);
                    }
                }
                for(int i=0;i<Partition1.size();i++){
                    System.out.println(Partition1.get(i).name +" ("+Partition1.get(i).size +") =>"+ Partition1.get(i).nullprocess);
                }
                for(int i=0;i<process1.size();i++){
                    if(process1.get(i).take==0){
                        System.out.println(process1.get(i).name +" can not be allocated");
                    }
                }

            }
            else if(choice==2){//Worst fit
                //we want to search big size in ArrayList and add new
                for(int i=0;i<process1.size();i++){
                    //loop to get max size par in parsize
                    int mx=-1;//get max number size in Partition
                    int ind=-1;//get index of max size in ArrayList
                    String s="Partition"+Partition1.size();
                    for(int j=0;j<Partition1.size();j++){
                        if(Partition1.get(j).nullprocess=="External fragment"){
                            if(mx<Partition1.get(j).size){
                                mx=Partition1.get(j).size;
                                ind=j;
                            }
                        }
                    }
                    if(ind>-1){
                        if(mx==process1.get(i).size){
                            process1.get(i).take=1;
                            Partition1.get(ind).nullprocess=process1.get(i).name;
                        }
                        else if(mx>process1.get(i).size){
                            process1.get(i).take=1;
                            int diff=mx-process1.get(i).size;
                            Partition1.get(ind).size=process1.get(i).size;
                            Partition1.get(ind).nullprocess=process1.get(i).name;
                            partition d=new partition(s,diff,"External fragment");
                            Partition1.add(d);
                        }
                    }
                }
                for(int i=0;i<Partition1.size();i++){
                    System.out.println(Partition1.get(i).name +" ("+Partition1.get(i).size +") =>"+ Partition1.get(i).nullprocess);
                }
                for(int i=0;i<process1.size();i++){
                    if(process1.get(i).take==0){
                        System.out.println(process1.get(i).name +" can not be allocated");
                    }
                }
            }
            else if(choice==3){//best fit
                //this search the least difference between Partition and process and put process in Partition
                for(int i=0;i<process1.size();i++){
                    int ind=-1;//search index of lower_bound
                    int diff=-1;//initial diff and want min
                    String s="Partition"+Partition1.size();
                    for(int j=0;j<Partition1.size();j++){
                        if(Partition1.get(j).size>=process1.get(i).size && Partition1.get(j).nullprocess=="External fragment"){
                            if(diff==-1){
                                diff=(Partition1.get(j).size-process1.get(i).size);
                                ind=j;
                            }
                            else if(diff>(Partition1.get(j).size-process1.get(i).size)){
                                diff=(Partition1.get(j).size-process1.get(i).size);
                                ind=j;
                            }
                        }
                    }
                    if(ind>-1){
                        if(Partition1.get(ind).size==process1.get(i).size){
                            process1.get(i).take=1;
                            Partition1.get(ind).nullprocess=process1.get(i).name;
                        }
                        else{
                            process1.get(i).take=1;
                            Partition1.get(ind).size=process1.get(i).size;
                            Partition1.get(ind).nullprocess=process1.get(i).name;
                            partition d=new partition(s,diff,"External fragment");
                            Partition1.add(d);
                        }
                    }
                }

                for(int i=0;i<Partition1.size();i++){
                    System.out.println(Partition1.get(i).name +" ("+Partition1.get(i).size +") =>"+ Partition1.get(i).nullprocess);
                }
                for(int i=0;i<process1.size();i++){
                    if(process1.get(i).take==0){
                        System.out.println(process1.get(i).name +" can not be allocated");
                    }
                }

            }
            else{
                break;
            }
            System.out.println("Do you want to compact? 1.yes 2.no");
            int x=sc.nextInt();
            int counter=0;
            int sz=Partition1.size();
            if(x==1){
                ArrayList<partition>Partition2=new ArrayList<partition>();//use after compact
                for(int i=0;i<sz;i++){
                    if(Partition1.get(i).nullprocess=="External fragment"){
                        counter+=Partition1.get(i).size;//counter sum every Partition no process
                    }
                    else{
                        String s=Partition1.get(i).name;
                        x=Partition1.get(i).size;
                        String nameproc=Partition1.get(i).nullprocess;
                        partition d1=new partition(s,x,nameproc);
                        Partition2.add(d1);
                    }
                }
                String s="Partition"+sz;
                sz++;
                if(counter>0){
                    partition d1=new partition(s,counter,"External fragment");
                    Partition2.add(d1);
                }
                else{//if no External fragment in Partition
                    sz--;
                }
                for(int i=0;i<process1.size();i++){
                    int diff=-1;
                    s="Partition"+sz;
                    if(process1.get(i).take==0){
                        if(Partition2.get(Partition2.size()-1).size==process1.get(i).size && Partition2.get(Partition2.size()-1).nullprocess=="External fragment"){
                            Partition2.get(Partition2.size()-1).nullprocess=process1.get(i).name;
                            process1.get(i).take=1;
                            break;
                        }
                        else if(Partition2.get(Partition2.size()-1).size>process1.get(i).size && Partition2.get(Partition2.size()-1).nullprocess=="External fragment"){
                            diff=Partition2.get(Partition2.size()-1).size-process1.get(i).size;
                            Partition2.get(Partition2.size()-1).nullprocess=process1.get(i).name;
                            Partition2.get(Partition2.size()-1).size=process1.get(i).size;
                            partition d1=new partition(s,diff,"External fragment");
                            process1.get(i).take=1;
                            Partition2.add(d1);
                            sz++;
                        }
                    }

                }
                for(int i=0;i<Partition2.size();i++){
                    System.out.println(Partition2.get(i).name +" ("+Partition2.get(i).size +") =>"+ Partition2.get(i).nullprocess);
                }
                for(int i=0;i<process1.size();i++){
                    if(process1.get(i).take==0){
                        System.out.println(process1.get(i).name +" can not be allocated");
                    }
                }

            }
            process1.clear();Partition1.clear();
            for(int i=0;i<Partition.size();i++){//reload in Partition1 every obj of Partition
                String S=Partition.get(i).name;
                String S1=Partition.get(i).nullprocess;
                int N=Partition.get(i).size;
                partition p1=new partition(S,N,S1);
                Partition1.add(p1);
            }
            for(int i=0;i<process.size();i++){//reload in Partition1 every obj of Partition
                String S=process.get(i).name;
                int N1=process.get(i).take;
                int N=process.get(i).size;
                Process d1=new Process(S,N,N1);
                process1.add(d1);
            }
        }
    }
}