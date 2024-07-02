import java.util.ArrayList;
import java.util.List;

public class Phrase
{
	private Phrase parent;

	private List<Phrase> children;

	private int		index;
	private char	symbol;

	public Phrase()
	{
		parent = null;

		children = new ArrayList<>();

		index = 0;
		symbol = 'R';
	}

	public Phrase(Phrase parent, int index, char symbol)
	{
		this.parent = parent;

		children = new ArrayList<>();

		this.index = index;
		this.symbol = symbol;
	}

	public Phrase getParent()
	{
		return parent;
	}

	public char getSymbol()
	{
		return symbol;
	}

	public void addChild(Phrase phrase)
	{
		children.add(phrase);
	}

	public List<Phrase> getChildren()
	{
		return children;
	}

	public int getIndex()
	{
		return index;
	}
}