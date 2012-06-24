package util;

public class POSNode {
	private String word;
	private String pos;
	
	public POSNode(String word, String pos){
		this.word = word;
		this.pos = pos;
	}
	
	public String getWord(){
		return word;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
