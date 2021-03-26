package ros.msgs.std_msgs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * A generic specified Java Bean for capturing many of the primitive data-type messages used by ROS in the std_msgs
 * package. The class has a single public data member called "data" that belongs to the specified primitive type.
 * @author James MacGlashan.
 */
@AutoValue
public abstract class PrimitiveMsg <T> {
	@JsonProperty
	public abstract T data();

	@JsonCreator
	public static <T> PrimitiveMsg<T> create(@JsonProperty("data") T data) {
		return new AutoValue_PrimitiveMsg<>(data);
	}
}
