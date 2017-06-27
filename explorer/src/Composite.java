import java.util.ArrayList;
import java.util.List;


public class Composite implements Component{
	String tag;
	List<Component> components = new ArrayList<Component>();
	
	public Composite(String tag){
		this.tag = tag;
	}
	@Override
	public void add(Component component) throws UnsupportedOperationException {
		components.add(component);
	}

	@Override
	public void remove(Component component)	throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		components.remove(component);
	}

	@Override
	public Component getChild(int i) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		if(components.size()>i)
			return components.get(i);
		return null;
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public String getValue() {
		String values = "";
		for(int j = 0 ; j < components.size(); j++){
			Component component = components.get(j);
			values +=  component.getTag() + component.getValue(); 
		}
		
		return values;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(tag + this.getValue());
	}

	@Override
	public void print(int i) {
		// TODO Auto-generated method stub
		for (int j = 0 ; j < i ; j++){
			System.out.print("  ");
		}
		System.out.println("D: " + tag);
		for (int j = 0 ; j < components.size(); j++){
			Component component = components.get(j);
			System.out.print(j + 1 +". ");
			component.print(i + 1);
		}
	}

}
