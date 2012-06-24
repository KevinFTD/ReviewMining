package model;

public class ZOLReview implements Review {
	
	private String advantage;
	private String disadvantage;
	private String summary;
	
	public ZOLReview(String adv, String dis, String sum){
		this.advantage = adv;
		this.disadvantage = dis;
		this.summary = sum;
	}
	
	public String getContent(){
		return advantage;
	}
	
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getDisadvantage() {
		return disadvantage;
	}
	public void setDisadvantage(String disadvantage) {
		this.disadvantage = disadvantage;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
