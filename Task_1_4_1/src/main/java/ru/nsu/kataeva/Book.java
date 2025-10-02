package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * Setter for isPaidEducation.
     */
    public void setPaidEducation(boolean isPaidEducation) {
        this.isPaidEducation = isPaidEducation;
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
            setPaidEducation(false);
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

        Map<Subject, Grade> finalGrades = grades.stream()
                .filter(Grade::isFinal)
                .collect(Collectors.toMap(
                        Grade::getSubject,
                        grade -> grade,
                        (existing, replacement) -> replacement
                ));

        boolean allPassesOk = grades.stream()
                .filter(Grade::isFinal)
                .filter(g -> g.getSubject().getFinalGradeType() == GradeType.PASS)
                .allMatch(g -> g.getMark() != Mark.FAIL);
        if (!allPassesOk) {
            return false;
        }

        boolean badDiplom = finalGrades.values().stream()
                .anyMatch(g -> g.getType() == GradeType.DIPLOM && g.getMark() != Mark.EXCELLENT);
        if (badDiplom) {
            return false;
        }

        boolean hasSatisfactory = finalGrades.values().stream()
                .filter(g -> g.getType() == GradeType.EXAM || g.getType() == GradeType.DIFF_PASS)
                .anyMatch(g -> g.getMark() == Mark.SATISFACTORY);
        if (hasSatisfactory) {
            return false;
        }

        long currentExcellent = finalGrades.values().stream()
                .filter(Grade::countsForRedDiplom)
                .filter(g -> g.getMark() == Mark.EXCELLENT)
                .count();

        long remainingFinals = grades.stream()
                .map(Grade::getSubject)
                .filter(s -> s.getFinalGradeType() != GradeType.PASS)
                .filter(s -> !finalGrades.containsKey(s))
                .distinct()
                .count();

        long potentialExcellent = currentExcellent + remainingFinals;

        long totalFinals = grades.stream()
                .map(Grade::getSubject)
                .filter(s -> s.getFinalGradeType() != GradeType.PASS)
                .distinct()
                .count();

        return (double) potentialExcellent / totalFinals >= 0.75;
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
