import java.io.BufferedReader;// import BufferedReader
import java.io.File;//import File
import java.io.FileReader;//import FileReader
import java.io.IOException;//import IOException
import java.io.InputStreamReader;//import InputStreamReader
import java.util.List;//import List
/*   탐색기 메인 클래스   */
public class Explorer {
	//메인 메소드. IO, 숫자 포맷, 지원하지 않는 작업 관련 Exception 처리
	public static void main(String[] args) throws UnsupportedOperationException, NumberFormatException, IOException {
		File file = new File("d:/");//탐색기 실행 루트 디렉토리를 파일 객체에 담음
		Composite root = new Composite(file.getAbsolutePath());//루트 디렉토리의 절대경로를 루트 요소에 저장
		root = makeTree(root);//루트요소에서 시작하는 트리 생성 메소드 호출
		fileList(root);//루트의 파일  리스트 메소드 시작
	}

	private static Composite makeTree(Composite dirRoot) throws UnsupportedOperationException {//디렉토리 루트를 이용하여 트리 생성
		File file = new File(dirRoot.getTag());//디렉토리 루트의 태그를 이용하여 파일객체 생성
		//System.out.println(dirRoot.getTag());
		File[] list;//파일들을 담을 배열 생성
		list = file.listFiles();//디렉토리 루트 파일 객체의 파일리스트 저장
		if(list != null) {//리스트가 null이 아닌지 확인(디렉토리인지 확인)
			int size = list.length;//리스트의 크기를 저장할 변수 선언
			
			for(int i = 0 ; i < size ; i++) {//리스트 크기만큼 반복
				if(list[i].isDirectory()) {//현재 리스트의 요소가 디렉토리인지 확인
					Composite directory = new Composite(list[i].getPath());//해당 디렉토리를 요소 객체를 생성하여 저장
					dirRoot.add(directory);//디렉토리 루트 객체에 현재 접근중인 하위 디렉토리 추가
					directory = makeTree(directory);//현재 접근중인 디렉토리로 트리 생성
				}
				else if(list[i].isFile()){//현재 리스트의 요소가 파일인지 확인
					Leaf leaf = new Leaf("F: ", list[i].getName());//해당 파일의 이름을 통해 leaf 객체를 생성
					dirRoot.add(leaf);//디렉토리 루트에 해당 leaf객체 추가
				}
				else {//둘다 아닌 뭔가 엿같은 경우가 생겼을 때
					throw new UnsupportedOperationException("Somthings wrong here...");//지원하지 않는 동작 Exception 바랭
				}
			}
		}
		return dirRoot;//디렉토리 루트 요소 객체 리턴
	}
	/*   파일 목록 메소드. 지원하지 않는 작업, 숫자 포맷, IO Exception 발생   */
	public static void fileList(Composite root) throws UnsupportedOperationException, NumberFormatException, IOException{
		System.out.println("  현재 디렉토리 : " + root.getTag());//현재 탐색기가 위치한 디렉토리를 출력
		
		File file = new File(root.getTag());//목록 출력을 위해 넘겨받은 요소를 파일객체에 저장
		File[] ls = file.listFiles();//파일 배열 객체에 해당 요소의 파일 목록을 저장
		int size = ls.length;//파일 목록 리스트의 길이 저장
		for (int i = 0 ; i < size ; i++) {//파일 목록 리스트의 길이만큼 반복
			Component temp = root.getChild(i);//현재 접근한 요소를 저장
			if(temp.getTag().equalsIgnoreCase("F: ")) {//현재 접근한 요소의 태그가 F: , 즉 파일인지 확인
				System.out.println(i+1 + ". " + temp.getTag() + temp.getValue());//번호, 태그, 값을 출력
			}
			else {//파일이 아니라면(디렉토리라면)
				String tag = temp.getTag();//현재 접근한 요소의 태그 저장
				int criteria = tag.lastIndexOf("\\");//\\, 즉 디렉토리 구분자의 마지막 인덱스를 저장
				System.out.println(i+1 + ". D: " + tag.substring(criteria+1));//번호, 디렉토리 태그(직접 입력), 구분자의 마지막 인덱스 다음까지 스트링에서 잘라내서 출력
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//콘솔에서 텍스트 입력 대기
		System.out.println("보고싶은 파일 혹은 폴더의 번호를 입력하세요. : ");//파일 혹은 디렉토리 선택 안내문 출력
		// int menu;//입력받은 텍스트를 저장할 변수 선언
//		try {
//			Integer.parseInt(br.readLine().toString());//입력받은 텍스트를 int로 변환을 try
//		} catch (NumberFormatException e) {//숫자 포맷 Exception catch
//			e.printStackTrace();//exception 출력
//		} finally {
//			
//		}
		int menu = Integer.parseInt(br.readLine().toString());//입력받은 내용을 int형으로 변환하여 변수에 저장
		Component temp = root.getChild(menu-1);//인자로 받은 루트의 선택한 메뉴-1번째 자식 요소를 저장
		if (temp.getTag().equalsIgnoreCase("F: ")) {//현재 접근중인 요소의 태그가 F: (파일)이라면
			String path = root.getTag() +  "/" + temp.getValue();//경로 생성 및 저장
			File tempfile = new File(path);//저장한 경로를 이용, 파일 객체 생성
			if(tempfile.canRead()) {//읽을 수 있는 파일인지 확인
				System.out.println(menu + "를 선택했습니다. 절대 경로는" + tempfile + "입니다.");//선택한 번호와 해당 파일의 경로를 안내
				fileList(root);//원 디렉토리의 파일 리스트 재생성
				delay();//딜레이
			}
			else {//읽을수 없다면
				System.out.println("읽기 불가능한 파일입니다.");//읽을 수 없는 파일 안내
				fileList(root);//원 디렉토리의 파일 리스트 재생성
				delay();//딜레이
			}
		}
		else {//현재 접근중인 요소가 디렉토리라면
			System.out.println(temp.getTag() + "를 선택했습니다. 해당 폴더로 이동합니다.");//선택한 파일의 절대경로출력 및 이동 안내
			fileList((Composite)temp);//접근중인 요소를 이용하여 파일리스트 생성
			delay();//딜레이
		}
	}
	public static void delay() {//시간을 지연시킬 딜레이 메소드
		for (long i = 0 ; i < 900000000 ; i++) {//900000000번 반복하여 강제 지연
		}
	}
}
