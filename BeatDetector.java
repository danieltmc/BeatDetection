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
			
			print_audio();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	public print_audio()
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
}
