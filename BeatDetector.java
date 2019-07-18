import java.util.Arrays;
import java.util.List;
import java.io.*;
import javax.sound.sampled.*;

public class BeatDetector
{
	public File input;
	public FileInputStream fis;
	public byte[] bytearray;
	public AudioInputStream stream;
	public ByteArrayInputStream bytestream;

	public BeatDetector(String filename)
	{
		try
		{
			input = new File(System.getProperty("user.dir") + "/" + filename);
			fis = new FileInputStream(input);
			stream = AudioSystem.getAudioInputStream(input);
			bytearray = new byte[fis.available()];
			fis.read(bytearray);
			// Human-readable printout of the values of every audio sample in the input file
			print_audio();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public Tuple fft(byte[] input)
	{
		int n = input.length;
		if (n == 1)
		{
			byte[] imaginary = new byte[input.length];
			Arrays.fill(imaginary, (byte) 0);
			return new Tuple(input, imaginary);
		}
		else
		{
			byte[] real_out = new byte[input.length];
			Arrays.fill(real_out, (byte) 0);
			byte[] imaginary_out = new byte[input.length];
			Arrays.fill(imaginary_out, (byte) 0);
			int m = n / 2;
			byte[] even = new byte[m];
			for (int i = 0; i < m; i++) { even[i] = input[2 * i]; }
			byte[] odd = new byte[m];
			for (int i = 0; i < m; i++) { odd[i] = input[2 * i + 1]; }
			even = fft(even).real;
			odd = fft(odd).real;
			for (int i = 0; i < m/2; i++)
			{
				int theta = (-2 * (int) Math.PI * i) / n;
				real_out[i] = (byte) (even[i] - (int) Math.cos(theta) * odd[i]);
				imaginary_out[i] = (byte) (0 - (int) Math.sin(theta) * 0);
			}
			for (int i = m/2; i < n; i++)
			{
				int theta = (-2 * (int) Math.PI * i) / n;
				real_out[i] = (byte) (even[i] - (int) Math.cos(theta) * odd[i]);
				imaginary_out[i] = (byte) (0 - (int) Math.sin(theta) * 0);
			}
			return new Tuple(real_out, imaginary_out);
		}
	}

	public void print_audio()
	{
		System.out.print("[");
		for (int i = 0; i < bytearray.length; i++)
		{
			System.out.print(" " + bytearray[i]);
		}
		System.out.println("]");
	}

	public static void main(String args[])
	{
		new BeatDetector(args[0]);
	}

	public class Tuple
	{
		public final byte[] real;
		public final byte[] imaginary;
		public Tuple(byte[] real, byte[] imaginary)
		{
			this.real = real;
			this.imaginary = imaginary;
		}
	}
}
