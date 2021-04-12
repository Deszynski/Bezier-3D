package grafika_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bezier_3D {

	private Point points[][][];
	private FileWriter fileWriter;
	private File file;
	private int sum;
	
	public Bezier_3D(String fileReader, String fileWriter)
	{
		this.file = new File(fileReader);
		try {
			this.fileWriter = new FileWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void Coordinates()
	{
		Scanner s = new Scanner(System.in);
		String platy = s.next();
		sum = Integer.parseInt(platy);
		points = new Point[sum][4][4];
		
		for(int i = 0; i < sum; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				for(int k = 0; k < 4; k++)
				{
						points[i][j][k] = new Point(Double.parseDouble(s.next()), Double.parseDouble(s.next()), Double.parseDouble(s.next()));
				}
			}
		}
	}
	
	public static double silnia(int n)
	{
	    long temp = 1;
	    for (int i = 2; i <= n; i++)
	    {
	        temp = temp * i;
	    }
	    return temp;
	}
	
	public static double Bernstein(int i, int n, double x)
	{
		double f1 = silnia(n) / (silnia(n - i) * silnia(i));
		double f2 = Math.pow((1 -x), (n - i));
		double f3 = Math.pow(x, i);
		return f1 * f2 * f3;
	}
	
	
	
	public void Calc() throws IOException
	{
		double point_x;
		double point_y;
		double point_z;
			
		try 
		{
			fileWriter.write("x, y, z" + System.lineSeparator());
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		for(int i = 0; i < sum; i++)
		{
			for(double j = 0.0; j <= 1.0; j += 0.03)
			{
				for(double k = 0.0; k <= 1.0; k += 0.03)
				{
					point_x = 0;
					point_y = 0;
					point_z = 0;
					
					for(int l = 0; l < 4; l++)
					{
						for(int m = 0; m < 4; m++)
						{
							point_x += points[i][m][l].x * Bernstein(m, 3, k) * Bernstein(l, 3, j);
							point_y += points[i][m][l].y * Bernstein(m, 3, k) * Bernstein(l, 3, j);
							point_z += points[i][m][l].z * Bernstein(m, 3, k) * Bernstein(l, 3, j);
						
						}
					}
					
					fileWriter.write(point_x + ", " + point_y + ", " + point_z + System.lineSeparator());
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		Bezier_3D czajnik = new Bezier_3D("czajnik.txt","czajnik-info.txt");
		czajnik.Coordinates();
		czajnik.Calc();

			
		Bezier_3D kubek = new Bezier_3D("kubek.txt","kubek-info.txt");
		kubek.Coordinates();
		kubek.Calc();
		
		Bezier_3D lyzka = new Bezier_3D("lyzka.txt","lyzka_info.txt");
		lyzka.Coordinates();
		lyzka.Calc();
	}	
}
