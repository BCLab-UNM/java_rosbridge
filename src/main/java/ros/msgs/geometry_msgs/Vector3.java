package ros.msgs.geometry_msgs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * A Java Bean for the Vector3 ROS geometry_msgs/Vector3 message type. This can be used both for publishing Vector3 messages to
 * {@link ros.RosBridge} and unpacking Vector3 messages received from {@link ros.RosBridge} (see the {@link ros.tools.MessageUnpacker}
 * documentation for how to easily unpack a ROS Bridge message into a Java object).
 * @author James MacGlashan.
 */
@AutoValue
public abstract class Vector3 {
	@JsonProperty
	public abstract double x();
	@JsonProperty
	public abstract double y();
	@JsonProperty
	public abstract double z();

	@JsonCreator
	public static Vector3 create(@JsonProperty("x") double x, @JsonProperty("y") double y, @JsonProperty("z") double z) {
		return new AutoValue_Vector3(x, y, z);
	}
}
