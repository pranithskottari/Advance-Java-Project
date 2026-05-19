package model;

public class Patient {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String date;
    private String ailment;
    private String doctor;

    // ✅ FOR ADD (NO ID)
    public Patient(String name, int age, String gender, String date, String ailment, String doctor) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.date = date;
        this.ailment = ailment;
        this.doctor = doctor;
    }

    // ✅ FOR UPDATE / DISPLAY (WITH ID)
    public Patient(int id, String name, int age, String gender, String date, String ailment, String doctor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.date = date;
        this.ailment = ailment;
        this.doctor = doctor;
    }

    // GETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDate() { return date; }
    public String getAilment() { return ailment; }
    public String getDoctor() { return doctor; }
}