
public class LZ78
{
	private static final int INVALID_INDEX = -1;

	public static String encode(String originalContent)
	{
		StringBuilder encodedContent = new StringBuilder();
		Trie dictionary = new Trie();

		int actualIndex = 1;
		StringBuilder symbolsStringBuilder = new StringBuilder();
		for (char symbol : originalContent.toCharArray())
		{
			symbolsStringBuilder.append(symbol);

			Object[] multipleReturnValues = dictionary.addNewSymbolForEncoding(symbolsStringBuilder.toString(), false);

			if ((boolean) multipleReturnValues[0])
			{
				symbolsStringBuilder.setLength(0);

				int index = (int) multipleReturnValues[1];
				char nextSymbol = (char) multipleReturnValues[2];

				encodedContent.append(index + " " + nextSymbol + " ");
			} else if (!(boolean) multipleReturnValues[0] && actualIndex == originalContent.length())
			{
				Object[] lastReturnValues = dictionary.addNewSymbolForEncoding(symbolsStringBuilder.toString(), true);

				int index = (int) lastReturnValues[1];
				char nextSymbol = (char) lastReturnValues[2];

				encodedContent.append(index + " " + nextSymbol + " ");
			}

			actualIndex++;
		}

		return encodedContent.toString();
	}

	public static String decode(String originalContent)
	{
		StringBuilder decodedContent = new StringBuilder();
		Trie dictionary = new Trie();

		int firstSymbol = INVALID_INDEX;

		boolean afterMiddleWhiteSpace = false, skipWhiteSpace = false;

		for (char symbol : originalContent.toCharArray())
		{
			if (skipWhiteSpace)
			{
				skipWhiteSpace = false;
				continue;
			} else if (firstSymbol == INVALID_INDEX)
			{
				firstSymbol = symbol - (int) '0';
				continue;
			} else if (firstSymbol != INVALID_INDEX && symbol != ' ' && !afterMiddleWhiteSpace)
			{
				firstSymbol = (firstSymbol * 10) + symbol - (int) '0';
				continue;
			} else if (symbol == ' ' && !afterMiddleWhiteSpace)
			{
				afterMiddleWhiteSpace = true;
				continue;
			}

			decodedContent.append(dictionary.addNewSymbolForDecoding(firstSymbol, symbol));

			firstSymbol = INVALID_INDEX;
			afterMiddleWhiteSpace = false;
			skipWhiteSpace = true;
		}

		return decodedContent.toString();
	}
}