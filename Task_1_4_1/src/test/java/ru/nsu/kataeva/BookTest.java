package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Book tests.
 */
public class BookTest {

    @Test
    public void testAverageEmpty() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        assertEquals(0.0, book.averageScore());
    }

    @Test
    public void testAverage() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.EXCELLENT));
        book.addGrade(new Grade("Physics", GradeType.EXAM, 1, Mark.GOOD));
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, Mark.EXCELLENT));
        assertEquals(4.5, book.averageScore());
    }

    @Test
    public void testBudgetNoGrades() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        assertFalse(book.budget());
    }

    @Test
    public void testBudget() {
        Book book = new Book(false, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 3, Mark.EXCELLENT));
        assertFalse(book.budget());
    }

    @Test
    public void testBudgetBad() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.SATISFACTORY));
        assertFalse(book.budget());
    }

    @Test
    public void testBudgetBadPass() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, Mark.FAIL));
        assertFalse(book.budget());
    }

    @Test
    public void testBudgetOk() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.EXCELLENT));
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, Mark.EXCELLENT));
        assertTrue(book.budget());
    }

    @Test
    public void testRedDiplomBadExam() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.SATISFACTORY));
        assertFalse(book.redDiplom());
    }

    @Test
    public void testRedDiplomBadDiplomWork() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Diploma", GradeType.DIPLOM, 8, Mark.GOOD));
        assertFalse(book.redDiplom());
    }

    @Test
    public void testRedDiplomaOk() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.EXCELLENT));
        book.addGrade(new Grade("Physics", GradeType.EXAM, 2, Mark.GOOD));
        book.addGrade(new Grade("Sport", GradeType.DIFF_PASS, 1, Mark.EXCELLENT));
        book.addGrade(new Grade("Diploma", GradeType.DIPLOM, 8, Mark.EXCELLENT));
        assertTrue(book.redDiplom());
    }

    @Test
    public void testStipendia() {
        Book book = new Book(true, "Ivan", "Ivanov", 1);
        assertFalse(book.bigStipendia());
    }

    @Test
    public void testNotAllExcellent() {
        Book book = new Book(false, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.EXCELLENT));
        book.addGrade(new Grade("Physics", GradeType.EXAM, 1, Mark.GOOD));
        assertFalse(book.bigStipendia());
    }

    @Test
    public void testIncreasedScholarshipOk() {
        Book book = new Book(false, "Ivan", "Ivanov", 1);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, Mark.EXCELLENT));
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, Mark.EXCELLENT));
        assertTrue(book.bigStipendia());
    }
}
