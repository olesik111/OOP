package ru.nsu.kataeva;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Grade test.
 */
public class GradeTest {

    @Test
    public void testExamGrade() {
        Grade g = new Grade("Math", GradeType.EXAM, 1, 5);
        assertEquals("Math", g.getSubject());
        assertEquals(GradeType.EXAM, g.getType());
        assertEquals(1, g.getSemester());
        assertEquals(5, g.getValue());
    }

}

