package ros.msgs.geometry_msgs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * A Java Bean for the Vector3 ROS geometry_msgs/Twist message type. This can be used both for publishing Twist messages to
 * {@link ros.RosBridge} and unpacking Twist messages received from {@link ros.RosBridge} (see the {@link ros.tools.MessageUnpacker}
 * documentation for how to easily unpack a ROS Bridge message into a Java object).
 * @author James MacGlashan.
 */
@AutoValue
public abstract class Twist {
	@JsonProperty
	public abstract Vector3 linear();
	@JsonProperty
	public abstract Vector3 angular();

	@JsonCreator
	public static Twist create(@JsonProperty("linear") Vector3 linear, @JsonProperty("angular") Vector3 angular) {
		return new AutoValue_Twist(linear, angular);
	}
}
