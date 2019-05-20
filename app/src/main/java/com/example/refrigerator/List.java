package com.example.refrigerator;

public class List {
	private int id;
    private String name;
    private String buyday;
    private String shelflife;
    private String party;
    private int k;

    public List(int id, String name, String buyday, String shelflife, String party) {
    	this.id = id;
        this.name = name;
        this.buyday = buyday;
        this.shelflife = shelflife;
        this.party = party;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBuyday() {
        return buyday;
    }
    public void setBuyday(String buyday) {
        this.buyday = buyday;
    }
    public String getShelflife() {
        return shelflife;
    }
    public void setShelflife(String shelflife) {
        this.shelflife = shelflife;
    }
    public String getParty() { return party; }
    public void setParty(String party) {
        this.party = party;
    }
        
    public String toString(){
    	String result = "국회의원 이름 : "+ name + "\n관심날짜 : " + buyday + "\n임기만료일자 : " + shelflife + "\n소속 정당 : " + party + "";
    	return result;
    }
}
