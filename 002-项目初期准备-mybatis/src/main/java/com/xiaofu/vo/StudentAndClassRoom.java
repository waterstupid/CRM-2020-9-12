package com.xiaofu.vo;

public class StudentAndClassRoom {
    // student
    private Integer sid;
    private String sname;
    private Integer sage;
    private String saddress;
    private Integer sclassroomId;

    // classroom
    private Integer cid;
    private String cname;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public Integer getSclassroomId() {
        return sclassroomId;
    }

    public void setSclassroomId(Integer sclassroomId) {
        this.sclassroomId = sclassroomId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "StudentAndClassRoom{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", saddress='" + saddress + '\'' +
                ", sclassroomId=" + sclassroomId +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public StudentAndClassRoom(Integer sid, String sname, Integer sage, String saddress, Integer sclassroomId, Integer cid, String cname) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.saddress = saddress;
        this.sclassroomId = sclassroomId;
        this.cid = cid;
        this.cname = cname;
    }
}
