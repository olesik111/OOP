package ru.nsu.kataeva;

public class Grade {
    private final String subject;
    private final GradeType type;
    private final int semester;
    private final int value;


    public Grade(String subject, GradeType type, int semester, int value) {
        this.value = value;
        this.semester = semester;
        this.subject = subject;
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public GradeType getType() {
        return type;
    }

    public int getSemester() {
        return semester;
    }

    public int getValue() {
        return value;
    }
}
