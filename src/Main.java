import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public class Main
{
	private static boolean encode;

	public static void main(String[] args) throws IOException
	{
		String mode = args[0];
		String filename = args[1];

		if ("encode".equals(mode))
		{
			encode();
		} else if ("decode".equals(mode))
		{
			decode();
		} else
		{
			System.out.println(System.lineSeparator() + "Invalid mode. Use 'encode' or 'decode' as first argument.");
		}

		handleFiles(filename);
	}

	private static void handleFiles(String filename) throws IOException
	{
		Charset charset = Charset.defaultCharset();
		File file = new File(filename);

		String newContent;

		try (InputStream in = new FileInputStream(file); Reader reader = new InputStreamReader(in, charset); Reader buffer = new BufferedReader(reader))
		{
			newContent = handleCharacters(buffer);
		}

		String newFilename = "";
		String mode = getEncode() ? "encoded" : "decoded";

		int index = filename.lastIndexOf('.');
		if (index > 0)
		{
			newFilename = filename.substring(0, index) + "-" + mode + "_by_LZ78.txt";
		}

		try (OutputStream out = new FileOutputStream(newFilename); Writer writer = new OutputStreamWriter(out, charset))
		{
			writer.write(newContent);
		}
	}

	private static String handleCharacters(Reader reader) throws IOException
	{
		StringBuilder stringBuilder = new StringBuilder();
		int r;
		while ((r = reader.read()) != -1)
		{
			char ch = (char) r;
			stringBuilder.append(ch);
		}

		String mode = getEncode() ? "encoding" : "decoding";
		String originalContent = stringBuilder.toString();

		System.out.println(System.lineSeparator() + "Original content of file for " + mode + ":" + System.lineSeparator() + originalContent + System.lineSeparator());

		String newContent = getEncode() ? LZ78.encode(originalContent) : LZ78.decode(originalContent);
		mode = getEncode() ? "encoded" : "decoded";
		System.out.println("New " + mode + " content of file:" + System.lineSeparator() + newContent);

		return newContent;
	}

	public static boolean getEncode()
	{
		return encode;
	}

	public static void encode()
	{
		encode = true;
	}

	public static void decode()
	{
		encode = false;
	}
}