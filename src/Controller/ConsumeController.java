package Controller;

import Service.ConsumeService;

public class ConsumeController {

	private ConsumeService service = new ConsumeService();
	
	public String[] consumeTag(){
		String[][] arr = service.consumeTag();
		int size = arr.length;
		String[] tag = new String[size];
		for(int i = 0; i < tag.length; i++) {
			tag[i] = arr[i][1];
		}
		
		return tag;
		
		
	}
}
