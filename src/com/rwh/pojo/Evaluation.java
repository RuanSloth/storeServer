package com.rwh.pojo;

public class Evaluation {
    private Integer id;
    private int score;
    private String e_desc;
    private String photoone;
    private String phototwo;
    private String photothree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getE_desc() {
        return e_desc;
    }

    public void setE_desc(String desc) {
        this.e_desc = desc;
    }

    public String getPhotoone() {
        return photoone;
    }

    public void setPhotoone(String photoone) {
        this.photoone = photoone;
    }

    public String getPhototwo() {
        return phototwo;
    }

    public void setPhototwo(String phototwo) {
        this.phototwo = phototwo;
    }

    public String getPhotothree() {
        return photothree;
    }

    public void setPhotothree(String photothree) {
        this.photothree = photothree;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", score=" + score +
                ", desc='" + e_desc + '\'' +
                '}';
    }

    public Evaluation() {
    }

    public Evaluation(int score, String desc, String photoone, String phototwo, String photothree) {
        this.id = (int) ((Math.random() * (99999 - 10000)) + 10000);
        this.score = score;
        this.e_desc = desc;
        this.photoone = photoone;
        this.phototwo = phototwo;
        this.photothree = photothree;
    }

    public Evaluation(int score, String desc) {
        this.id = (int) ((Math.random() * (99999 - 10000)) + 10000);
        this.score = score;
        this.e_desc = desc;
    }

    public Evaluation(int score, String desc, String photoone) {
        this.id = (int) ((Math.random() * (99999 - 10000)) + 10000);
        this.score = score;
        this.e_desc = desc;
        this.photoone = photoone;
    }

    public Evaluation(int score, String desc, String photoone, String phototwo) {
        this.id = (int) ((Math.random() * (99999 - 10000)) + 10000);
        this.score = score;
        this.e_desc = desc;
        this.photoone = photoone;
        this.phototwo = phototwo;
    }
}
