package MyTemporaryTYS;

public class VakitInspection {

    public Integer yoklamaId;
    public Student student;
    public String isAbsentToString;
    public String vakit;
    public String tarih;

    public VakitInspection(Integer yoklamaId, Student student, String isAbsentToString, String vakit, String tarih) {
        this.yoklamaId = yoklamaId;
        this.student = student;
        this.isAbsentToString = isAbsentToString;
        this.vakit = vakit;
        this.tarih = tarih;
    }

    @Override
    public String toString() {
        return student.studentId + " " + yoklamaId + " " + student.name + " " + student.surname + " " + isAbsentToString + " " + vakit + " " + tarih;
    }
}