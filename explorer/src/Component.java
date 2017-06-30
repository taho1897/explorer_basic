/*   탐색기 트리 구조의 각 요소들을 명시한 인터페이스 Component   */
public interface Component {
	public void add(Component component) throws UnsupportedOperationException;// 요소를 받아 요소 더하기(자식 생성)
	public void remove(Component component) throws UnsupportedOperationException;// 요소를 받아 요소 빼기(자식 제거)
	public Component getChild(int i);// i번째 자식요소 받기
/* 2017.06.30
 * //	public String getTag();// tag getter
 * //	public String getValue();// value getter
 *  getter를 인터페이스에 명시해야 할 이유가 딱히 생각이 나지 않아 일단 주석 처리
 *  추가)component 관련 작동 처리시 값을 받아와야 해서 인터페이스에 명시를 해야함
 *  */
	public String getTag();// tag getter
	public String getValue();// value getter
	public void print();// 인자 없이 출력
	public void print(int i);// 인자를 받아 출력
}	