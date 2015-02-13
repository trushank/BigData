import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Binner {

	public static void main(String[] args) {
		int whch = 15;
		int lw[][] = { { 0, 11, 22, 23, 32 }, { 6, 13, 24, 32 },
				{ 0, 261, 404, 494, 637, 780 }, { 0, 16, 26, 35 },
				{ -1, 2, 3, 4, 5, 6, 8, 10, 12, 16 },
				{ -1, 1, 2, 3, 4, 5, 6, 7, 8 }, { 0, 1801, 4001 },
				{ 0, 11, 17, 33 }, { 0, 13, 24, 32 }, { 0, 11, 17, 33 } };

		int up[][] = { { 10, 21, 22, 29, 47 }, { 12, 23, 31, 138 },
				{ 260, 403, 493, 636, 780, 1300 }, { 15, 25, 34, 130 },
				{ 1, 2, 3, 4, 5, 6, 8, 10, 12, 16 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 1800, 4000, 7800 },
				{ 10, 16, 32, 110 }, { 12, 23, 31, 200 }, { 10, 16, 32, 200 } };

		int num[] = { 0, 1, 2, 3, 4, 5, 7, 10, 15, 16 };

		// String classes[]={"low","low-medium","medium","medium-high","high"};
		String classes[][] = {
				{ "low", "low-medium", "medium", "medium-high", "high" },
				{ "poor", "average", "good", "excellent" },
				{ "green", "healthy", "average", "below average", "unhealthy",
						"hazardous" },
				{ "Poor", "Normal", "Average", "Excellent" },
				{ "Electric", "Two", "Three", "Four", "Five", "Six", "Eight",
						"Ten", "Twelve", "Sixteen" },
				{ "Electric", "One", "Two", "Three", "Four", "Five", "Six",
						"Seven", "Eight" },
				{ "Economy", "Standard", "Expensive" },
				{ "Guzzler", "Average", "Economic", "Green" },
				{ "Guzzler", "Average", "Economic", "Green" },
				{ "Guzzler", "Average", "Economic", "Green" } };
		try {
			Scanner src = new Scanner(new File("vehicles.csv"));
			String titles = src.nextLine();
			String fileName[] = titles.split(",");
			PrintWriter out = new PrintWriter(new File("vehicles1.csv"));
			out.println(titles);
			int y = 0;
			while (src.hasNextLine()) {
				String temp[] = src.nextLine().split(",");

				for (int i = 0; i < up.length; i++) {
					whch = num[i];
					int t = 0;
					try {
						if (whch == 16 && temp[whch].equals("20.6")) {
							System.out.println();
						}
						t = (int) Double.parseDouble(temp[whch]);
						if (whch == 16)
							System.out.println(t);
					} catch (NumberFormatException e) {
						if (whch == 16)
							System.out.println("Missing Value in:"
									+ (fileName[whch]));
						t = -1;
					}
					for (int j = 0; j < up[i].length; j++) {
						if (up[i][j] >= t && lw[i][j] <= t) {
							temp[whch] = classes[i][j];
							// if(whch==16)
							// System.out.println(classes[i][j]);
							break;
						}
					}
				}
				for (int i = 0; i < temp.length; i++) {
					if (i == temp.length - 1)
						out.print(temp[i]);
					else
						out.print(temp[i] + ",");

				}
				out.println();

			}
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
