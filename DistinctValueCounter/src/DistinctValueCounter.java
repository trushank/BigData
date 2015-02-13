import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import edu.rit.numeric.ListXYSeries;
import edu.rit.numeric.plot.Plot;
import edu.rit.numeric.plot.Strokes;
import edu.rit.numeric.plot.impl.XYPlotSeries;


public class DistinctValueCounter {
	public static void main(String args[]){
		// whch  decides what colm u want
		//int whch=0;
		int num[]={0,4,14,15,22,23,24,25,26,28,30,31,34,43,46,47,48,49,57,58,60,63,64};
		for(int i1=0;i1<num.length;i1++){
		boolean dec=true;
		int whch=num[i1];
		System.out.println(whch);
		try {
			Scanner src=new Scanner(new File("vehicles.csv"));
			HashMap<String,Integer> hash=new HashMap<String,Integer>();
			String heads=src.nextLine();
			String[] headers=heads.split(",");
			
			while(src.hasNextLine()){
				String line=src.nextLine();
				String[] values=line.split(",");
				String curr=values[whch];
				//System.out.println(values.length);
				if(dec){
					if(curr.equals("NA")||curr.equals("-")||curr.equals("")){
						curr="-1";
					}
					try{
					curr=Integer.toString((int)Float.parseFloat(curr));
					
					}catch(NumberFormatException e){}
				}
				//System.out.println(curr);
				if(hash.containsKey(curr)){
					int temp=hash.get(curr);
					hash.remove(curr);
					hash.put(curr, ++temp);
				}
				else{
					hash.put(curr, 1);
				}
			}
			//System.out.println(hash.keySet());
		
			ListXYSeries xy=new ListXYSeries();
			Iterator<String> keys=hash.keySet().iterator();
			int[] x =new int[hash.size()];
			PrintWriter pw = new PrintWriter(new File (whch+" "+headers[whch] + ".txt"));
			try{
			for(int i = 0; i < x.length && keys.hasNext(); i++){
				x[i] =  Integer.parseInt(keys.next());
			}
			Arrays.sort(x);
			System.out.println(Arrays.toString(x));

			for(int i = 0; i < x.length; i++){
				//System.out.println(x[i] + "," + hash.get(Integer.toString(x[i])) );
				pw.println(x[i] + "," + hash.get(Integer.toString(x[i])));
				xy.add(x[i],hash.get(Integer.toString(x[i])));
			}
			
			}catch(NumberFormatException e){
				System.out.println("EXCEPTION"+e);
				Iterator<String> keys1=hash.keySet().iterator();
				
				while(keys1.hasNext()){
					String q=keys1.next();
					pw.println(q+","+hash.get(q));
				}
				pw.flush();
				pw.close();
			}
			if(xy.isEmpty()){
			System.out.println((whch+" "+headers[whch])+" is empty");	
			}
			new Plot()
			.rightMargin(36)

			.xAxisTitle("Value")
			.yAxisTitle("Occurance")
			.seriesDots(null)
			.seriesStroke(Strokes.solid(2))
			.seriesColor(Color.RED)
			.xySeries(xy)
			.seriesDots(null)
			.seriesStroke(Strokes.solid(2))
			.seriesColor(Color.BLUE)
			.xySeries(xy)
			.frameTitle(whch+" "+headers[whch])
			.plotTitle(whch+" "+headers[whch])
			.labelPosition(Plot.RIGHT)
			.labelOffset(6)
			.labelColor(Color.RED)
			.label(whch+" "+headers[whch], xy.maxX(),
				xy.maxY())

			.getFrame().setVisible(true);
			
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			
		}
		}
	}
}
