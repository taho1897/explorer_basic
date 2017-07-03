import java.io.BufferedReader;// import BufferedReader
import java.io.File;//import File
import java.io.IOException;//import IOException
import java.io.InputStreamReader;//import InputStreamReader

/*
//import FileReader
//import List
 * */

/*   탐색기 메인 클래스   */
public class Explorer {
	public static void main(String args[]) throws UnsupportedOperationException, NumberFormatException, IOException {//메인 메소드. IO, 숫자 포맷, 지원하지 않는 작업 관련 Exception 처리
		File file = new File("d:/");//탐색기 실행 루트 디렉토리를 파일 객체에 담음
		Component root = new Composite(file.getAbsolutePath());//루트 디렉토리의 절대경로를 루트 요소에 저장
		makeTree(root);//루트요소에서 시작하는 트리 생성 메소드 호출
		fileList(root);//루트의 파일  리스트 메소드 시작
	}
/*   디렉토리 루트를 이용하여 트리 구조를 생성하는 메소드   */
	private static Component makeTree(Component root) throws UnsupportedOperationException { 
		File file = new File(root.getTag());// 디렉토리 루트의 태그를 이용하여 파일객체 생성
		// System.out.println(dirRoot.getTag());
		File[] fileList;// 파일들을 담을 배열 생성
		fileList = file.listFiles();// 디렉토리 루트 파일 객체의 파일리스트 저장
		if (file.listFiles() != null) {// 리스트가 null이 아닌지 확인(디렉토리인지 확인)
			int length = fileList.length;// 리스트의 크기를 저장할 변수 선언

			for (int i = 0; i < length; i++) {// 리스트 크기만큼 반복
				// System.out.println(fileList[i].toString());
				if (fileList[i].isDirectory()) {// 현재 리스트의 요소가 디렉토리인지 확인
					// System.out.println(fileList[i].toString());
					Component directory = new Composite(fileList[i].toString());// 해당 디렉토리를 요소 객체를 생성하여 저장
					root.add(directory);// 디렉토리 루트 객체에 현재 접근중인 하위 디렉토리 추가
					makeTree(directory);// 현재 접근중인 디렉토리로 트리 생성
				} else if (fileList[i].isFile()) {// 현재 리스트의 요소가 파일인지 확인
					// System.out.println(fileList[i].getName());
					Component leaf = new Leaf("F: ", fileList[i].getName());// 해당 파일의 이름을 통해 leaf 객체를 생성
					root.add(leaf);// 디렉토리 루트에 해당 leaf객체 추가

				} else {// 둘다 아닌 뭔가 엿같은 경우가 생겼을 때 2017.06.30
					throw new UnsupportedOperationException("Somethings gone wrong");// 지원하지 않는 동작 Exception 발생
				}
			}
		}
		return root;// 디렉토리 루트 요소 객체 리턴
	}

	/* 파일 목록 메소드. 지원하지 않는 작업, 숫자 포맷, IO Exception 발생 */
	private static void fileList(Component root)
			throws UnsupportedOperationException, NumberFormatException, IOException {
		System.out.println(root.getTag());// 현재 탐색기가 위치한 디렉토리를 출력
		File file = new File(root.getTag());// 목록 출력을 위해 넘겨받은 요소를 파일객체에 저장
		File[] listFiles = file.listFiles();// 파일 배열 객체에 해당 요소의 파일 목록을 저장
		int length = listFiles.length;// 파일 목록 리스트의 길이 저장
		for (int i = 0; i < length; i++) {// 파일 목록 리스트의 길이만큼 반복
			Component component = root.getChild(i);// 현재 접근한 요소를 저장
			if (component.getTag().equals("F: ")) {// 현재 접근한 요소의 태그가 F: , 즉 파일인지 확인
				// System.out.println("oho");
				System.out.println(i + 1 + " " + component.getTag() + component.getValue());// 번호, 태그, 값을 출력
			} else {// 파일이 아니라면(디렉토리라면)
				String route = component.getTag();// 현재 접근한 요소의 태그 저장
				// System.out.println(route);
				int criteria = route.lastIndexOf("\\");// \\, 즉 디렉토리 구분자의 마지막 인덱스를 저장
				System.out.println(i + 1 + " D: " + route.substring(criteria + 1));// 번호, 디렉토리 태그(직접입력), 구분자의 마지막 인덱스 다음까지 스트링에서 잘라내서 출력
			}
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 콘솔에서 텍스트 입력 대기
		System.out.print("파일 혹은 디렉토리를 선택하십시오 : ");// 파일 혹은 디렉토리 선택 안내문 출력
		int select = Integer.parseInt(in.readLine());// 입력받은 내용을 int형으로 변환하여 변수에 저장
		Component component = root.getChild(select - 1);// 인자로 받은 루트의 선택한 메뉴-1번째 자식 요소를 저장
		if (component.getTag().equals("F: ")) {// 현재 접근중인 요소의 태그가 F: (파일)이라면
			String route = root.getTag() + component.getValue();// 경로 생성 및 저장
			File fileRead = new File(route);// 저장한 경로를 이용, 파일 객체 생성
			if (fileRead.canRead()) {// 읽을 수 있는 파일인지 확인
				System.out.println(select + ". " + fileRead.getAbsolutePath());// 선택한 번호와 해당 파일의 경로를 안내
				fileList(root);// 원 디렉토리의 파일 리스트 재생성
				delay();// 딜레이
			} else {// 읽을수 없다면
				System.out.println("읽을 수 없는 파일입니다");// 읽을 수 없는 파일 안내
				fileList(root);// 원 디렉토리의 파일 리스트 재생성
				delay();// 딜레이
			}
		} else {// 현재 접근중인 요소가 디렉토리라면
			System.out.println(component.getTag() + "의 디렉토리로 이동합니다");// 선택한 파일의 절대경로출력 및 이동 안내
			fileList(component);// 접근중인 요소를 이용하여 파일리스트 생성
			delay();// 딜레이

		}
	}

	public static void delay() {// 시간을 지연시킬 딜레이 메소드
		for (int i = 0; i < 900000000; i++) {// 900000000번 반복하여 강제 지연
		}
	}
}