package test.java;

import hjss.Gender;
import hjss.Learner;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LearnerTest {
    @Test
    public void testRegisterLearner() {
        Learner learner = new Learner("John", Gender.MALE, 8, "1234567890", 2);
        assertTrue(learner.getAge() >= 4 && learner.getAge() <= 11);
        assertTrue(learner.getGradeLevel() >= 1 && learner.getGradeLevel() <= 5);
    }
}