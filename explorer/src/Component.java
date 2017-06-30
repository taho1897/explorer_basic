/*   탐색기 트리 구조의 각 요소들을 명시한 인터페이스 Component   */
public interface Component {
	public void add(Component component) throws UnsupportedOperationException;//요소 더하기(자식 생성)
	public void remove(Component component) throws UnsupportedOperationException;//요소 빼기(자식 제거)
	public Component getChild(int i) throws UnsupportedOperationException;//i번째 자식요소 받기
	public String getTag();//tag getter
	public String getValue();//value getter
	public void print();//인자 없이 출력
	public void print(int i);//인자를 받아 출력
}