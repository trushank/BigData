import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class Grants {
	public static void main(String args[]){
		File root=new File("C:\\Users\\Trushank\\Desktop\\Part1");
		ArrayList<String> files=new ArrayList<String>();
		File[] root1=root.listFiles();
		for(int i=0;i<root1.length;i++){
//			if(root1[i].isDirectory())
//			System.out.println(root1[i].toString());
			File[] temp=root1[i].listFiles();
			for(int j=0;j<temp.length;j++){
		//		System.out.println(temp[j].toString());
				if(temp[j].isDirectory()){
				File[] fileNames=temp[j].listFiles();
				for(int x=0;x<fileNames.length;x++){
					files.add(fileNames[x].toString());
				}
				}
			}
		}
		
	//	System.out.println(files.toString());
		try {
			PrintWriter out=new PrintWriter(new File("sponser.txt"));
			PrintWriter out1=new PrintWriter(new File("application.txt"));
		for(int i=0;i<files.size();i++){
			try {
				Scanner src=new Scanner(new File(files.get(i)));
				while(src.hasNextLine()){
					String next=src.nextLine();
					if(next.contains("Sponsor")){
						if(!next.contains("	      ")){
						//	next=next.substring(0, (next.indexOf("	      ")-1));
							next=next.substring(next.indexOf(": ")+2);
							out.println(next);
						}
						
					}
					else if(next.contains("Fld Applictn")){
						next=next.substring(next.indexOf(": ")+2);
						next=next.trim();
						out1.println(next);
						while(src.hasNextLine()){
							String temp=src.nextLine();
							temp=temp.trim();
							
							if(temp.length()==0) continue;
							if(temp.contains("Program Ref")){
								break;
							}
							else{
								//temp=temp.substring(13);
								out1.println(temp);
							}
						}
					}
				}
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File sponser=new File("sponser.txt");
		File application=new File("application.txt");
		HashMap<String, Integer> sp=new HashMap<String,Integer>();
		HashMap<String, Integer> app=new HashMap<String,Integer>();
		try{
		Scanner srcSP=new Scanner(sponser);
		Scanner srcApp=new Scanner(application);
		while(srcSP.hasNextLine()){
			String temp=srcSP.nextLine();
			
			if(sp.containsKey(temp)){
				int count=sp.get(temp);
				sp.remove(temp);
				sp.put(temp,++count);
			}else{
				sp.put(temp, 1);
			}
		}
		srcSP.close();
		while(srcApp.hasNextLine()){
			String temp=srcApp.nextLine();
			if(temp.length()==0) continue;
			//System.out.println(++cntr);
			if(app.containsKey(temp)){
				int count=app.get(temp);
				app.remove(temp);
				app.put(temp,++count);
			}else{
				app.put(temp, 1);
				
			}
		}
		srcApp.close();
		
		KeyValue[] App=new KeyValue[app.size()];
		Set<String> appKeys=app.keySet();
		Iterator<String> appItr=appKeys.iterator();
		int x=0;
		while(appItr.hasNext()){
			String temp=appItr.next();
			App[x++]=new KeyValue(temp, app.get(temp));
		}
		
		for(int i=0;i<App.length;i++){
			for(int j=i+1;j<App.length;j++){
				if(App[i].compare(App[j])==-1){
					KeyValue temp=App[i];
					App[i]=App[j];
					App[j]=temp;
				}
			}
		}
		PrintWriter appCount=new PrintWriter(new File("APPCount.txt"));
		for(int i=0;i<25;i++){
			appCount.println(App[i]);
		}
		appCount.close();
		PrintWriter spCount=new PrintWriter(new File("SPCount.txt"));
		KeyValue[] Spon=new KeyValue[sp.size()];
		Set<String> SponKeys=sp.keySet();
		Iterator<String> SponItr=SponKeys.iterator();
		int x1=0;
		while(SponItr.hasNext()){
			String temp =SponItr.next();
			Spon[x1++]=new KeyValue(temp, sp.get(temp));
		}
	//	System.out.println("**************************"+x1);
		for(int i=0;i<Spon.length;i++){
			for(int j=i+1;j<Spon.length;j++){
				if(Spon[i].compare(Spon[j])==-1){
					KeyValue temp=Spon[i];
					Spon[i]=Spon[j];
					Spon[j]=temp;
				}
			}
		}
		for(int i=0;i<10;i++){
			spCount.println(Spon[i]);
		}
		spCount.close();
		//System.out.println(app.toString());
		//System.out.println(sp.toString());
		}
		catch(Exception e){
			
			System.out.println(e.toString());
		}
		
	}

}
class KeyValue{
	String key;
	int value;
	public KeyValue(String key, int value){
		this.key=key;
		this.value=value;
	}
	
	public int compare(KeyValue a){
		if(a.value>this.value)
			return -1;
		else if(a.value<this.value)
			return 1;
		else
			return 0;
	}
	public String toString(){
		return (key+":"+value);
	}
}
