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
	public abstract int secs();
	@JsonProperty
	public abstract int nsecs();

	@JsonCreator
	public static Time create(@JsonProperty("secs") int secs, @JsonProperty("nsecs") int nsecs) {
		return new AutoValue_Time(secs, nsecs);
	}

	public static Time now() {
		int timeInSeconds = (int)(System.currentTimeMillis() / 1000);
		return create(timeInSeconds, 0);
	}
}
