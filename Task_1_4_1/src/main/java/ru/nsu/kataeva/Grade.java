package ru.nsu.kataeva;

/**
 * Grade class realization.
 */
public class Grade {
    private final String subject;
    private final GradeType type;
    private final int semester;
    private final int value;


    /**
     * Constructor.
     *
     * @param subject subject.
     * @param type type.
     * @param semester semester.
     * @param value value.
     */
    public Grade(String subject, GradeType type, int semester, int value) {
        this.value = value;
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
    public int getValue() {
        return value;
    }
}
