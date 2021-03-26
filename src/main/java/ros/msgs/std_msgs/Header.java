package ros.msgs.std_msgs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.auto.value.AutoValue;

/**
 * @author James MacGlashan.
 */
@AutoValue
public abstract class Header {

	@JsonProperty
	public abstract int seq();
	@JsonProperty
	public abstract Time stamp();
	@JsonProperty
	public abstract String frame_id();

	@JsonCreator
	public static Header create(@JsonProperty("seq") int seq, @JsonProperty("stamp") Time stamp, @JsonProperty("frame_id") String frame_id) {
		return new AutoValue_Header(seq, stamp, frame_id);
	}
}
