package CodeDojo.IpRange;

public class CountIPAddresses {
	public static long ipsBetween(String start, String end) {
		final String[] startIp = start.split("\\.");
		final String[] endIp = end.split("\\.");
		long result = 0;
		long multiplicand = 1;
		int size = startIp.length;
		int power = 0;
		for (int i = size - 1 ; i >= 0; i--) {
			if (power > 0) {
				multiplicand = multiplicand * 256;
			}
			long subFrom = Long.parseLong(endIp[i]);
			long sub = Long.parseLong(startIp[i]);
			boolean borrow = false;
			if (subFrom < sub) {
				borrow = true;
				subFrom += 256;
			}
			long diffrence = subFrom - sub;
			result += diffrence * multiplicand;
			if (i > 0 && borrow) {
				Long borrowAdd = Long.parseLong(startIp[i-1]);
				borrowAdd++;
				startIp[i-1] = borrowAdd.toString();
			}
			power++;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(CountIPAddresses.ipsBetween("10.0.0.0", "10.0.0.50") + " 50");
		System.out.println(CountIPAddresses.ipsBetween("20.0.0.10", "20.0.1.0") + " 246");
		System.out.println(CountIPAddresses.ipsBetween("1.0.0.0", "10.0.1.0") + " 256");
	}
}
