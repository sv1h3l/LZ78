import java.util.List;

public class Trie
{
	private int		index;
	private Phrase	rootPhrase;

	public Trie()
	{
		rootPhrase = new Phrase();
		index = 0;
	}

	public Object[] addNewSymbolForEncoding(String value, boolean justPrintLastSymbols)
	{
		char[] symbols = value.toCharArray();

		return containsTrieSymbolForEncoding(0, symbols, rootPhrase, justPrintLastSymbols);
	}

	private Object[] containsTrieSymbolForEncoding(int possition, char[] symbols, Phrase parentPhrase, boolean justPrintLastSymbols)
	{
		for (Phrase phrase : parentPhrase.getChildren())
		{
			if (symbols[possition] == phrase.getSymbol())
			{

				if (symbols.length - 1 == possition)
				{
					return justPrintLastSymbols ? new Object[] { true, parentPhrase.getIndex(), symbols[possition] } : new Object[] { false, -1, 0 };

				} else
				{
					possition++;
					return containsTrieSymbolForEncoding(possition, symbols, phrase, justPrintLastSymbols);
				}
			}
		}

		incIndex();
		parentPhrase.addChild(new Phrase(parentPhrase, index, symbols[possition]));
		return new Object[] { true, parentPhrase.getIndex(), symbols[possition] };
	}

	public String addNewSymbolForDecoding(int indexValue, char lastSymbol)
	{
		Phrase phrase = containsTrieSymbolForDecoding(indexValue, lastSymbol, rootPhrase);

		StringBuilder partOfNewContent = new StringBuilder();
		partOfNewContent.append(lastSymbol);
		for (; phrase.getParent() != null;)
		{
			partOfNewContent.append(phrase.getSymbol());
			phrase = phrase.getParent();
		}

		return new StringBuilder(partOfNewContent.toString()).reverse().toString();
	}

	public Phrase containsTrieSymbolForDecoding(int indexValue, char lastSymbol, Phrase parentPhrase)
	{
		if (indexValue == parentPhrase.getIndex())
		{
			incIndex();
			parentPhrase.addChild(new Phrase(parentPhrase, index, lastSymbol));

			return parentPhrase;
		} else
		{
			List<Phrase> children = parentPhrase.getChildren();

			if (children.isEmpty())
			{
				return null;
			} else
			{
				for (Phrase phrase : children)
				{
					Phrase returnPhrase = containsTrieSymbolForDecoding(indexValue, lastSymbol, phrase);
					if (returnPhrase != null)
						return returnPhrase;
				}
			}

		}
		return null;
	}

	public void incIndex()
	{
		index++;
	}

	public int getIndex()
	{
		return index;
	}

	public Phrase getRootPhrase()
	{
		return rootPhrase;
	}
}