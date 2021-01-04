package nauka;
import java.util.Scanner;
import java.io.Closeable;
import java.util.Arrays;

public class riseset {
	public static double anglecheck(double angle) {
		while (angle > 360) {
			angle -= 360;
		}
		while (angle < 0) {
			angle += 360;
		}
		return angle;
	}
	public static double[] hdectohms(double angle) {
		double[] arr = new double[3];
		double h = Math.floor(angle);
		double m = Math.floor((angle - h) * 60);
		double s = (((angle - h) * 60) - m) * 60;
		arr[0] = h;
		arr[1] = m;
		arr[2] = s;
		return arr;
	}
	
	public static void calcJ() {
		Scanner scan = new Scanner(System.in);
		/////////////////////////////////INPUT
		System.out.println("Latitude: ");
		double lat = scan.nextDouble();
		if (lat > 90 || lat < -90) {
			System.out.println("*ERROR*");
		}
		System.out.println("Longitude: ");
		double lon = scan.nextDouble();
		if (lon > 180 || lon < -180) {
			System.out.println("*ERROR*");
		}
		System.out.println("GMT: ");
		double gmt = scan.nextDouble();
		if (gmt > 12 || gmt < -12) {
			System.out.println("*ERROR*");
		}
		System.out.println("EoT: ");
		double eot = scan.nextDouble();

		System.out.println("Declination of Sun: ");
		double decl = scan.nextDouble();
		
		scan.close();
		////////////////////////////////////////////
		
		//Liczymy J*
		double Jstardec = 12 + gmt - lon / 15.0 + eot / 60.0;
		double []Jstar = hdectohms(Jstardec);

		//Liczymy w
		double cosw = (Math.sin(Math.toRadians(-0.83)) - Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(decl))) / (double) (Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(decl)));
		double w = Math.toDegrees((Math.acos(cosw)));
		double Jrisedec = Jstardec - w / 15.0;
		double Jsetdec = Jstardec + w / 15.0;
		
		//Liczymy w1
		double cosw1 = (Math.sin(Math.toRadians(-6)) - Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(decl))) / (double) (Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(decl)));
		double w1 = Math.toDegrees((Math.acos(cosw1)));
		double Jrisedec1 = Jstardec - w1 / 15.0;
		double Jsetdec1 = Jstardec + w1 / 15.0;
		
		//Liczymy w2
		double cosw2 = (Math.sin(Math.toRadians(-12)) - Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(decl))) / (double) (Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(decl)));
		double w2 = Math.toDegrees((Math.acos(cosw2)));
		double Jrisedec2 = Jstardec - w2 / 15.0;
		double Jsetdec2 = Jstardec + w2 / 15.0;
		
		//Liczymy w3
		double cosw3 = (Math.sin(Math.toRadians(-18)) - Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(decl))) / (double) (Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(decl)));
		double w3 = Math.toDegrees((Math.acos(cosw3)));
		double Jrisedec3 = Jstardec - w3 / 15.0;
		double Jsetdec3 = Jstardec + w3 / 15.0;
		
		double []Jrise = hdectohms(Jrisedec);
		double []Jset = hdectohms(Jsetdec);
		
		double []Jrise1 = hdectohms(Jrisedec1);
		double []Jset1 = hdectohms(Jsetdec1);
		
		double []Jrise2 = hdectohms(Jrisedec2);
		double []Jset2 = hdectohms(Jsetdec2);
		
		double []Jrise3 = hdectohms(Jrisedec3);
		double []Jset3 = hdectohms(Jsetdec3);
		
		double daylength = Jsetdec - Jrisedec;
		double nightlength = 24 - daylength;
				
		System.out.println((int) Jrise3[0] + " : " + (int) Jrise3[1] + " : " + String.format("%1.1f", Jrise3[2]) + " Astronomical sunrise");		
		System.out.println((int) Jrise2[0] + " : " + (int) Jrise2[1] + " : " + String.format("%1.1f", Jrise2[2]) + " Nautical sunrise");		
		System.out.println((int) Jrise1[0] + " : " + (int) Jrise1[1] + " : " + String.format("%1.1f", Jrise1[2]) + " Civil sunrise");		
		System.out.println((int) Jrise[0] + " : " + (int) Jrise[1] + " : " + String.format("%1.1f", Jrise[2]) + " Sunrise time");
		System.out.println((int) Jstar[0] + " : " + (int) Jstar[1] + " : " + String.format("%1.1f", Jstar[2]) + " Transit time");		
		System.out.println((int) Jset[0] + " : " + (int) Jset[1] + " : " + String.format("%1.1f", Jset[2]) + " Sunset time");		
		System.out.println((int) Jset1[0] + " : " + (int) Jset1[1] + " : " + String.format("%1.1f", Jset1[2]) + " Civil sunset");	
		System.out.println((int) Jset2[0] + " : " + (int) Jset2[1] + " : " + String.format("%1.1f", Jset2[2]) + " Nautical sunset");	
		System.out.println((int) Jset3[0] + " : " + (int) Jset3[1] + " : " + String.format("%1.1f", Jset3[2]) + " Astronomical sunset");	
		System.out.println("");
		System.out.println("Daylength: " + String.format("%2.2f", daylength) + ", Nightlength: " + String.format("%2.2f", nightlength) + ", Day%: " + String.format("%2.2f", (daylength / 24.0)) + ", Night%: " + String.format("%2.2f", (nightlength / 24.0)));
	
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		calcJ();


		long end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end - start) + "ms");
	}
}
