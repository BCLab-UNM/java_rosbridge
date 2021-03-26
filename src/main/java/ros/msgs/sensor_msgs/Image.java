package ros.msgs.sensor_msgs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import ros.msgs.std_msgs.Header;

import java.awt.image.BufferedImage;

/**
 * Implementation of ROS sensor_msgs/Image.msg:
 * <a href="http://docs.ros.org/api/sensor_msgs/html/msg/Image.html">http://docs.ros.org/api/sensor_msgs/html/msg/Image.html</a>.
 * This class can also decode the ROS Image into a Java Buffered Image for images that are encoded in either
 * bgr8, rgb8, or mono8, by using the {@link #toBufferedImage()} method.
 * @author James MacGlashan.
 */
@AutoValue
public abstract class Image {

	@JsonProperty
	public abstract Header header();
	@JsonProperty
	public abstract int height();
	@JsonProperty
	public abstract int width();
	@JsonProperty
	public abstract String encoding();
	@JsonProperty
	public abstract int is_bigendian();
	@JsonProperty
	public abstract int step();
	@JsonProperty
	public abstract byte[] data();

	@JsonCreator
	public static Image create(@JsonProperty("header") Header header,
							   @JsonProperty("height") int height,
							   @JsonProperty("width") int width,
							   @JsonProperty("encoding") String encoding,
							   @JsonProperty("is_bigendian") int is_bigendian,
							   @JsonProperty("step") int step,
							   @JsonProperty("data") byte[] data) {
		return new AutoValue_Image(header, height, width, encoding, is_bigendian, step, data);
	}



	/**
	 * Constructs a {@link BufferedImage} from this ROS Image, provided the encoding is either rgb8, bgr8, or mono8.
	 * If it is not one of those encodings, then a runtime exception will be thrown.
	 * @return a {@link BufferedImage} representation of this image.
	 */
	public BufferedImage toBufferedImage(){

		if(encoding().equals("bgr8") || encoding().equals("rgb8")){
			return this.toBufferedImageFromRGB8();
		}
		else if(encoding().equals("mono8")){
			return this.toBufferedImageFromMono8();
		}


		throw new RuntimeException("ROS Image does not currently decode " + encoding() + ". See Java doc for support types.");
	}


	/**
	 * Constructs a {@link BufferedImage} from this ROS Image assuming the encoding is mono8
	 * @return a {@link BufferedImage} representation of this image.
	 */
	protected BufferedImage toBufferedImageFromMono8(){
		int w = width();
		int h = height();

		BufferedImage i = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < h; ++y) {
			for(int x = 0; x < w; ++x) {

				//row major
				int index = (y * w) + x;
				// combine to RGB format
				int anded = data()[index++] & 0xFF;
				int rgb = anded |
						(anded << 8) |
						(anded << 16) |
						0xFF000000;

				i.setRGB(x, y, rgb);
			}
		}

		return i;
	}


	/**
	 * Constructs a {@link BufferedImage} representation from this ROS Image assuming the encoding is either rgb8 or bgr8.
	 * @return a {@link BufferedImage} representation of this image.
	 */
	protected BufferedImage toBufferedImageFromRGB8(){

		int w = width();
		int h = height();

		BufferedImage i = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {

				//row major, consecutive channels
				int index = (y * w * 3) + (x * 3);
				// combine to RGB format
				int rgb;
				if(encoding().equals("bgr8")){
					rgb = ((data()[index++] & 0xFF)) |
							((data()[index++] & 0xFF) << 8) |
							((data()[index++] & 0xFF) << 16) |
							0xFF000000;
				}
				else if(encoding().equals("rgb8")){
					rgb = ((data()[index++] & 0xFF) << 16) |
							((data()[index++] & 0xFF) << 8) |
							((data()[index++] & 0xFF)) |
							0xFF000000;
				}
				else{
					throw new RuntimeException("ROS Image toBufferedImageFromRGB8 does not decode " + encoding());
				}
				i.setRGB(x, y, rgb);
			}
		}

		return i;

	}

}
