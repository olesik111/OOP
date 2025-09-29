package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.List;

/**
 * Grade book realization.
 */
public class Book {
    private final List<Grade> grades = new ArrayList<>();
    private final boolean isPaidEducation;

    /**
     * Constructor.
     *
     * @param isPaidEducation is education paid.
     */
    public Book(boolean isPaidEducation) {
        this.isPaidEducation = isPaidEducation;
    }

    /**
     * Add grade.
     *
     * @param grade grade to add.
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * The average score of grade book.
     */
    public double averageScore() {
        double res = 0.0;
        int cnt = 0;
        for (Grade grade : grades) {
            if ((!(grade.getType() == GradeType.PASS))) {
                res += grade.getValue();
                cnt++;
            }
        }
        if (cnt == 0) {
            return 0;
        }
        return res / cnt;
    }

    /**
     * Check for possibility to transfer to budget.
     */
    public boolean budget() {
        if (!isPaidEducation) {
            return false;
        }
        if (grades.isEmpty()) {
            return false;
        }

        int maxSemester = 0;
        for (Grade g : grades) {
            if (g.getSemester() > maxSemester) {
                maxSemester = g.getSemester();
            }
        }
        int prevSem = maxSemester - 1;

        for (Grade grade : grades) {
            if (grade.getSemester() == maxSemester || grade.getSemester() == prevSem) {
                if (grade.getType() == GradeType.EXAM && grade.getValue() < 4) {
                    return false;
                }
                if (grade.getValue() == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if you can get a red diploma.
     */
    public boolean redDiplom() {
        double res = 0.0;
        int cnt = 0;

        if (grades.isEmpty()) {
            return false;
        }

        for (Grade grade : grades) {
            if (grade.getType() == GradeType.DIPLOM && grade.getValue() != 5) {
                return false;
            }

            cnt++;

            if (grade.getValue() < 4) {
                return false;
            }

            if ((!(grade.getType() == GradeType.PASS))) {
                if (grade.getValue() == 5) {
                    res++;
                }
            }
        }
        return 0.75 <= res / cnt;
    }

    /**
     * Check if you can get big stipendia.
     */
    public boolean bigStipendia(){
        if (isPaidEducation) {
            return false;
        }
        if (grades.isEmpty()) {
            return false;
        }

        int maxSemester = 0;
        for (Grade g : grades) {
            if (g.getSemester() > maxSemester) {
                maxSemester = g.getSemester();
            }
        }

        for (Grade grade : grades) {
            if (grade.getValue() != 5) {
                return false;
            }
        }
        return true;
    }
}
