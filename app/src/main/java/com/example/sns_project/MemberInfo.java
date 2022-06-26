package com.example.sns_project;
//유저 정보
public class MemberInfo {
    private String name;        //이름
    private String dep;         //학과
    private String depNumber;       //학번
    private String photoUrl;           //이미지 주소

    public MemberInfo(String name, String dep, String depNumber, String photoUrl){ //프로필 이미지가 있을때
        this.name = name;
        this.dep=dep;
        this.depNumber=depNumber;
        this.photoUrl = photoUrl;
    }

    public MemberInfo(String name, String dep, String depNumber){ // 프로필 이미지가 없을때
        this.name = name;
        this.dep = dep;
        this.depNumber = depNumber;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDep() {return dep;}
    public void setDep(String dep) {this.dep = dep;}
    public String getDepNumber() {return depNumber;}
    public void setDepNumber(String depNumber) { this.depNumber = depNumber;}
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}
