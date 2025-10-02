package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Grade test.
 */
public class GradeTest {

    @Test
    public void testExamGrade() {
        Subject subj = new Subject("Math", 1, GradeType.EXAM);
        Grade g = new Grade(subj, GradeType.EXAM, 1, Mark.EXCELLENT);

        assertEquals(subj, g.getSubject());
        assertEquals("Math", g.getSubject().getName());
        assertEquals(GradeType.EXAM, g.getType());
        assertEquals(1, g.getSemester());
        assertEquals(5, g.getValue());
        assertEquals(Mark.EXCELLENT, g.getMark());
    }
}
