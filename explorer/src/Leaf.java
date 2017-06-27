
public class Leaf implements Component{
	String tag;
	String value;
	
	public Leaf (String tag, String value){
		this.tag = tag;
		this.value = value;
	}
	@Override
	public void add(Component component) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Leaf can't have a child");
	}

	@Override
	public void remove(Component component) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Leaf can't have a child");
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getChild(int i) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Leaf can't have a child");
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(tag + value );
	}

	@Override
	public void print(int i) {
		for(int j = 0 ; j < i ; j++){
			System.out.print("  ");
		// TODO Auto-generated method stub
		}
		System.out.println(tag + value );
	}
	

}
