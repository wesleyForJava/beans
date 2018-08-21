package com.wesley.bean.emun;

public enum Three {	 
	UNION("1","市级下派"),AREA("2","区级下派"),Street("3","街道下派");
	
	private String level;
	private String levelName;
	Three(String level, String levelName) {
		this.level=level;
		this.levelName=levelName;
	}
	
	public static String getLevel(String levelName) {
		
		for (Three three : Three.values()) {
			if(three.getLevelName()==levelName) {
				return three.getLevel();
			}
		}
		return null;
		
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public static void main(String[] args) {
		String level2 = Three.getLevel("市级下派");
	   System.out.println(level2);
	}
}
