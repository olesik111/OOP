package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Grade book realization.
 */
public class Book {
    private final List<Grade> grades = new ArrayList<>();
    private boolean isPaidEducation;
    private final String firstName;
    private final String lastName;
    private final int group;

    /**
     * Constructor.
     *
     * @param isPaidEducation is education paid.
     * @param firstName       name.
     * @param lastName        surname.
     * @param group           group.
     */
    public Book(boolean isPaidEducation, String firstName, String lastName, int group) {
        this.isPaidEducation = isPaidEducation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    /**
     * Add grade to the book.
     *
     * @param grade grade to add.
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Average score of book.
     */
    public double averageScore() {
        return grades.stream()
                .filter(g -> g.getType() != GradeType.PASS)
                .mapToInt(g -> g.getMark().getValue())
                .average()
                .orElse(0.0);
    }

    /**
     * If you can study for free.
     */
    public boolean budget() {
        if (!isPaidEducation || grades.isEmpty()) {
            return false;
        }

        int maxSemester = grades.stream()
                .mapToInt(Grade::getSemester)
                .max()
                .orElse(0);
        int prevSem = maxSemester - 1;

        boolean possible = grades.stream()
                .filter(g -> g.getSemester() == maxSemester || g.getSemester() == prevSem)
                .noneMatch(g ->
                        (g.getType() == GradeType.EXAM
                                && g.getMark().getValue() < Mark.GOOD.getValue())
                                || g.getMark() == Mark.FAIL);

        if (possible) {
            this.isPaidEducation = false;
        }
        return possible;
    }

    /**
     * If you have a possibility to get a red diploma now.
     */
    public boolean redDiplom() {
        if (grades.isEmpty()) {
            return false;
        }

        Map<String, Grade> finalGrades = new HashMap<>();
        grades.forEach(grade -> {
            Grade current = finalGrades.get(grade.getSubject());
            if (current == null || grade.getSemester() > current.getSemester()) {
                finalGrades.put(grade.getSubject(), grade);
            }
        });

        if (finalGrades.values().stream()
                .anyMatch(g -> g.getType() == GradeType.DIPLOM && g.getMark() != Mark.EXCELLENT)) {
            return false;
        }

        if (finalGrades.values().stream()
                .filter(g -> g.getType() != GradeType.PASS)
                .anyMatch(g -> g.getMark().getValue() < Mark.GOOD.getValue())) {
            return false;
        }

        long total = finalGrades.values().stream()
                .filter(g -> g.getType() != GradeType.PASS)
                .count();

        if (total == 0) {
            return false;
        }

        long excellent = finalGrades.values().stream()
                .filter(g -> g.getType() != GradeType.PASS)
                .filter(g -> g.getMark() == Mark.EXCELLENT)
                .count();

        return (double) excellent / total >= 0.75;
    }

    /**
     * If you can get a bigger stipendia.
     */
    public boolean bigStipendia() {
        if (isPaidEducation || grades.isEmpty()) {
            return false;
        }

        int maxSemester = grades.stream()
                .mapToInt(Grade::getSemester)
                .max()
                .orElse(0);

        return grades.stream()
                .filter(g -> g.getSemester() == maxSemester)
                .allMatch(g -> g.getMark() == Mark.EXCELLENT);
    }
}
