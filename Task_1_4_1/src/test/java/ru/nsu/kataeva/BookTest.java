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
        Book book = new Book(true);
        assertEquals(0.0, book.averageScore());
    }

    @Test
    public void testAverage() {
        Book book = new Book(true);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 5));
        book.addGrade(new Grade("Physics", GradeType.EXAM, 1, 4));
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, 5));
        assertEquals(4.5, book.averageScore());
    }

    @Test
    public void testBudgetNoGrades() {
        Book book = new Book(true);
        assertFalse(book.budget());
    }

    @Test
    public void testBudget() {
        Book book = new Book(false);
        book.addGrade(new Grade("Math", GradeType.EXAM, 3, 5));
        assertFalse(book.budget());
    }

    @Test
    public void testBudgetBad() {
        Book book = new Book(true);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 3));
        assertFalse(book.budget());
    }

    @Test
    public void testBudgetBadPass() {
        Book book = new Book(true);
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, 2));
        assertFalse(book.budget());
    }

    @Test
    public void testBudgetOk() {
        Book book = new Book(true);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 5));
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, 5));
        assertTrue(book.budget());
    }

    @Test
    public void testRedDiplomBadExam() {
        Book book = new Book(true);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 3));
        assertFalse(book.redDiplom());
    }


    @Test
    public void testRedDiplomBadDiplomWork() {
        Book book = new Book(true);
        book.addGrade(new Grade("Diploma", GradeType.DIPLOM, 1, 4));
        assertFalse(book.redDiplom());
    }

    @Test
    public void testRedDiplomaOk() {
        Book book = new Book(true);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 5));
        book.addGrade(new Grade("Physics", GradeType.EXAM, 2, 4));
        book.addGrade(new Grade("Sport", GradeType.DIFF_PASS, 1, 5));
        book.addGrade(new Grade("Diploma", GradeType.DIPLOM, 8, 5));
        assertTrue(book.redDiplom());
    }

    @Test
    public void testStipendia() {
        Book book = new Book(true);
        assertFalse(book.bigStipendia());
    }

    @Test
    public void testNotAllExcellent() {
        Book book = new Book(true);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 5));
        book.addGrade(new Grade("Physics", GradeType.EXAM, 1, 4));
        assertFalse(book.bigStipendia());
    }

    @Test
    public void testIncreasedScholarshipOk() {
        Book book = new Book(false);
        book.addGrade(new Grade("Math", GradeType.EXAM, 1, 5));
        book.addGrade(new Grade("Sport", GradeType.PASS, 1, 5));
        assertTrue(book.bigStipendia());
    }
}

