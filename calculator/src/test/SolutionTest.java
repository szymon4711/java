package test;

import algorithm.ShutingYard;
import org.junit.jupiter.api.Test;
import solution.Solution;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
    assertEquals(77.0, Solution.solution(ShutingYard.shutingYard("(4+6)*8-3")));
    assertEquals(7.0 , Solution.solution(ShutingYard.shutingYard("((4+6)*8-3)/11")));
    assertEquals(26.0, Solution.solution(ShutingYard.shutingYard("6+5+(7-2)*3")));
    assertEquals(11.0, Solution.solution(ShutingYard.shutingYard("(55/11+3)/2+7")));
    assertEquals(8.0 , Solution.solution(ShutingYard.shutingYard("(11+16)/3-2*0.5")));
    }
}