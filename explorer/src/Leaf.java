/*   트리 구조의 최 하단 요소 Leaf class   */
public class Leaf implements Component {
	String tag;// 태그를 저장할 변수 선언
	String value;// 값을 저장할 변수 선언

	public Leaf(String tag, String value) throws UnsupportedOperationException {// 생성자를 통한 변수 초기화
		this.tag = tag;
		this.value = value;
	}
	
	@Override
	public void add(Component component) throws UnsupportedOperationException {// 인터페이스의 add 메소드 구현
		throw new UnsupportedOperationException("Leaf can't have child");// leaf는 자식을 가질 수 없다는 exception 발생
	}
	
	@Override
	public void remove(Component component) throws UnsupportedOperationException {// 인터페이스의 remove 메소드 구현
		throw new UnsupportedOperationException("Leaf can't have child");// leaf는 자식을 가질 수 없다는 exception 발생
	}
	
	@Override
	public Component getChild(int i) {// 인터페이스의 getChild 메소드 구현
		return null;// throw new UnsupportedOperationException("Leaf can't have child");// leaf는 자식을 가질 수 없다는 exception 발생
	}
	
	@Override
	public String getTag() {// tag getter 구현
		return tag;
	}

	@Override
	public String getValue() {// value getter 구현
		return value;
	}
	/*
	 * getter를 인터페이스에 굳이 명시할 필요가 없을거 같아 오버라이딩하지 않고 직접 getter 작성
	 * 
	 * */	

	@Override
	public void print() {// print() 메소드 구현
		System.out.println(getTag() + getValue());// 태그와 값을 합쳐서 출력
	}
	
	@Override
	public void print(int i) {// print(int) 메소드 구현
		for(int j = 0 ; j < i ; j++ ) {// 인자로 받은 값 큼 반복
			System.out.print("   ");// 공백 출력
		}
		System.out.println(getTag() + getValue());// 태그와 값을 합쳐서 출력
	}
}