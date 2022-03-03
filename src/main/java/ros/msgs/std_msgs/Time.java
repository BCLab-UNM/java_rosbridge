package ros.msgs.std_msgs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * @author James MacGlashan.
 */
@AutoValue
public abstract class Time {
	@JsonProperty
	public abstract int sec();
	@JsonProperty
	public abstract int nanosec();

	@JsonCreator
	public static Time create(@JsonProperty("sec") int sec, @JsonProperty("nanosec") int nanosec) {
		return new AutoValue_Time(sec, nanosec);
	}

	public static Time now() {
		int timeInSeconds = (int)(System.currentTimeMillis() / 1000);
		return create(timeInSeconds, 0);
	}
}
