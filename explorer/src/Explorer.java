import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Explorer {
	public static void main(String[] args)throws UnsupportedOperationException, NumberFormatException, IOException{
		File file = new File("d:/");
		Composite root = new Composite(file.getAbsolutePath());
		root = makeTree(root);
		fileList(root);
	}

	private static Composite makeTree(Composite dirRoot) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		File file = new File(dirRoot.getTag());
		File[] list;
		list = file.listFiles();
		if(list != null){
			int size = list.length;
			
			for(int i = 0 ; i < size ; i++){
				if(list[i].isDirectory()){
					Composite directory = new Composite(list[i].getPath());
					dirRoot.add(directory);
					directory = makeTree(directory);
				}
				else if(list[i].isFile()){
					Leaf leaf = new Leaf("F: ",list[i].getName());
					dirRoot.add(leaf);
				}
			}
		}
		return dirRoot;
	}
	public static void fileList(Composite root) throws UnsupportedOperationException, NumberFormatException, IOException{
		System.out.println("  현재 디렉토리 : " + root.getTag());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		File file = new File(root.getTag());
		File[] ls = file.listFiles();
		int size = ls.length;
		for (int i = 0 ; i < size ; i++){
			Component temp = root.getChild(i);
			if(temp.getTag().equalsIgnoreCase("F: ")){
				System.out.println(i+1 + ". " + temp.getTag() + temp.getValue());
			}
			else{
				String tag = temp.getTag();
				int criteria = tag.lastIndexOf("\\");
				System.out.println(i+1 + ". D: " + tag.substring(criteria+1));
			}
		}
		System.out.println("보고싶은 파일 혹은 폴더의 번호를 입력하세요. : ");
		int menu = Integer.parseInt(br.readLine().toString());
		
		Component temp = root.getChild(menu-1);
		if (temp.getTag().equalsIgnoreCase("F: ")){
			String path = root.getTag() +  "/" + temp.getValue();
			File tempfile = new File(path);
			if(tempfile.canRead()){
				System.out.println(menu + "를 선택했습니다. 절대 경로는" + tempfile + "입니다.");
				fileList(root);
				delay();
			}
			else{
				System.out.println("읽기 불가능 한 파일입니다.");
				fileList(root);
				delay();
			}
		}
		else{
			System.out.println(temp.getTag() + "를 선택했습니다. 그 폴더로 이동합니다.");
			fileList((Composite)temp);
			delay();
		}
	}
	public static void delay(){
		for (long i = 0 ; i < 900000000 ; i++){
		}
	}
}
