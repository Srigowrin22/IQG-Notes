package training.iqgateway.utils;

public class Validation {

	public String validateTime(String time) {
		String timeString = null;
		if (time != null && time.matches("\\d{2}:\\d{2}:\\d{2}")) {
			try {
				java.util.Date date = new java.text.SimpleDateFormat("HH:mm:ss").parse(time);
				timeString = new java.text.SimpleDateFormat("hh:mm a").format(date);
			} catch (Exception e) {
				timeString = time;
			}
		} else {
			timeString = time;
		}
		return timeString;
	}
}
