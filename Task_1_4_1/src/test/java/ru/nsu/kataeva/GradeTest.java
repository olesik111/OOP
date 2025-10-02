package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Grade test.
 */
public class GradeTest {

    @Test
    public void testExamGrade() {
        Grade g = new Grade("Math", GradeType.EXAM, 1, Mark.EXCELLENT);
        assertEquals("Math", g.getSubject());
        assertEquals(GradeType.EXAM, g.getType());
        assertEquals(1, g.getSemester());
        assertEquals(5, g.getValue());
        assertEquals(Mark.EXCELLENT, g.getMark());
    }
}
