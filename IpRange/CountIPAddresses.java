package CodeDojo.IpRange;

import java.util.Stack;

public class CountIPAddresses {
	private static Stack<Long> ipToStack(String ip) {
		Stack<Long> ipStack = new Stack<Long>();
		final String[] ipElements = ip.split("\\.");
		for (String element : ipElements) {
			ipStack.add(Long.parseLong(element));
		}
		return ipStack;
	}

	public static long ipsBetween(String start, String end) {
		Stack<Long> startIp = ipToStack(start);
		Stack<Long> endIp = ipToStack(end);
		long result = 0;
		long multiplicand = 1;
		int size = startIp.size();
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				multiplicand = multiplicand * 256;
			}
			long subFrom = endIp.pop();
			long sub = startIp.pop();
			boolean borrow = false;
			if (subFrom < sub) {
				borrow = true;
				subFrom += 256;
			}
			long diffrence = subFrom - sub;
			result += diffrence * multiplicand;
			if (startIp.size() > 0 && borrow) {
				long borrowAdd = startIp.pop();
				borrowAdd++;
				startIp.push(borrowAdd);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(CountIPAddresses.ipsBetween("10.0.0.0", "10.0.0.50") + " 50");
		System.out.println(CountIPAddresses.ipsBetween("20.0.0.10", "20.0.1.0") + " 246");
		System.out.println(CountIPAddresses.ipsBetween("1.0.0.0", "10.0.1.0") + " 256");
	}
}
