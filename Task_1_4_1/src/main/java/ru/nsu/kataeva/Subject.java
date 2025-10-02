package ru.nsu.kataeva;

/**
 * Represents a subject with its final semester.
 */
public class Subject {
    private final String name;
    private final int finalSemester;
    private final GradeType finalGradeType;
    /**
     * Constructor.
     *
     * @param name name of subject
     * @param finalSemester the final semester of this subject
     */
    public Subject(String name, int finalSemester, GradeType finalGradeType) {
        if (finalSemester < 1) {
            throw new IllegalArgumentException("Final semester must be >= 1, but was: " + finalSemester);
        }
        this.name = name;
        this.finalSemester = finalSemester;
        this.finalGradeType = finalGradeType;
    }

    /**
     * Get name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the final semester of subject.
     */
    public int getFinalSemester() {
        return finalSemester;
    }

    /**
     * Get the final type of passing.
     */
    public GradeType getFinalGradeType() {
        return finalGradeType;
    }
}
