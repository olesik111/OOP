package ru.nsu.kataeva;

/**
 * Grade class realization.
 */
public class Grade {
    private final String subject;
    private final GradeType type;
    private final int semester;
    private final Mark mark;

    /**
     * Constructor.
     *
     * @param subject subject.
     * @param type type.
     * @param semester semester.
     * @param mark mark.
     */
    public Grade(String subject, GradeType type, int semester, Mark mark) {
        if (semester < 1) {
            throw new IllegalArgumentException("Semester must be >= 1, but was: " + semester);
        }
        this.mark = mark;
        this.semester = semester;
        this.subject = subject;
        this.type = type;
    }

    /**
     * Get subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get type.
     */
    public GradeType getType() {
        return type;
    }

    /**
     * Get semester.
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Get value.
     */
    public Mark getMark() {
        return mark;
    }

    public int getValue() {
        return mark.getValue();
    }
}
