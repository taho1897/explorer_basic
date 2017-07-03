import java.util.ArrayList;
import java.util.List;

/*
 * //ArrayList import
 * //List import
 * */

/*   자식들을 가지는 부모 요소들의 구현   */
public class Composite implements Component {
	String tag;//태그를 저장할 변수를 선언
	List<Component> components = new ArrayList<Component>();//자식들의 목록을 저장할 변수 선언
	
	public Composite(String tag) {//태그를 초기화하는 생성자
		this.tag = tag;
	}
	
	@Override
	public void add(Component component) throws UnsupportedOperationException {//인터페이스의 add 메소드 구현
		components.add(component);// 자식 목록에 요소 추가
	}
	
	@Override
	public void remove(Component component) throws UnsupportedOperationException {//인터페이스의 remove 메소드 구현
		components.remove(component);//자식 목록에서 요소 제거
	}
	
	@Override
	public Component getChild(int i) {//인자를 이용해 i번째 자식 가져오기
		if(components.size() > i) {//자식목록의 크기가 받은 인자값보다 큰지 확인
			return components.get(i);//i번째 자식을 받아 return
		}
		else {
			return null;//작다면 null return
		}
	}
	
	@Override
	public void print() {//선택한 요소가 파일일 때 출력
		System.out.println(getTag() + getValue());//태그와 값을 출력
	}
	@Override
	public void print(int i) {//선택한 요소가 디렉토리일 때 출력
		for(int j = 0 ; j < i ; j++) {//인자로 받은 값 만큼 반복
			System.out.print("   ");//공백 출력
			System.out.print("D: " + getTag());//디렉토리 표시와 태그 출력
		}
		for(int j = 0 ; j < components.size() ; j++) {//자식 요소들의 갯수만큼 반복
			Component component = getChild(j);//현재 자식 요소를 가져옴 
			System.out.print(j + 1 + ". ");//j.형태의 숫자 출력
			System.out.println(component.getValue());//마지막 요소 출력
		}
	}

	public String getTag() {//tag getter
		return this.tag;//태그 값 리턴
	}

	public String getValue() {//value getter
		String text ="";//값을 저장할 변수 선언
		for(int j = 0 ; j < this.components.size() ; j++) {//자식 요소 갯수 만큼 반복
			Component component = components.get(j);//요소 객체에 j번째 자식요소를 저장
			text += component.getTag() + component.getValue();//해당 자식요소의 태그와 값을 누적
		}
		return text;//최종 누적 값을 리턴
	}
}