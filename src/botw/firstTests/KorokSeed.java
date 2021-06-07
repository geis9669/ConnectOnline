package botw.firstTests;

public class KorokSeed {
	private String info;
	
	public KorokSeed(String info){
		this.info = info;
	}
	
	public boolean containsString(String str) {
		int index = info.indexOf(str);
		return index > -1;
//		return info.contains(str);
	}
	
	public boolean equals(Object other){
		
		if(this == other){
			return true;
		}
		
		if(!(other instanceof KorokSeed)){
			return false;
		}
		KorokSeed o = (KorokSeed) other;
		return info.equals(o.info);
	}
	
	public int hashCode(){
		return info.hashCode();
	}

}
