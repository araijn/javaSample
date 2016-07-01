package util;

public class Range {

	private final Integer low;
	private final Integer high;

	public Range(Integer low, Integer high) {
		this.low = low;
		this.high = high;
	}

	public boolean isInRange(int value) {
		if(low != null && value < low) return false;
		if(high != null && high < value) return false;
		return true;
	}
}
