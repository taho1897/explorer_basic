
public interface Component {
	public void add(Component component) throws UnsupportedOperationException;
	public void remove(Component component) throws UnsupportedOperationException;
	public Component getChild(int i) throws UnsupportedOperationException;
	public String getTag();
	public String getValue();
	public void print();
	public void print(int i);
}