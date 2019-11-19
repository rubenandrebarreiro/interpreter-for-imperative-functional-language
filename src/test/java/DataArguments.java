package test.java;

import java.util.Arrays;
import java.util.Collection;

public class DataArguments {

	public static Collection<Object[]> data() {
		
		return Arrays.asList(new Object[][] {     
			
			// Arithmetic operations
			{ "2+2", "4" }, // #0
			{ "4-2", "2" }, // #1
			{ "-2+12", "10" }, // #2
			{ "5*5", "25" }, // #3
			{ "6/2", "3" }, // #4
			{ "-5*2", "-10" }, // #5
			{ "3*2/4", "0" }, // #6
			{ "(3*2)/4", "1" }, // #7
			{ "(20+5000)*0+3-1+3*(-5*(-1))", "17"}, // #8
			{ "(20+5000)*0+((3-1+3*(-5*(-1))))", "17"}, // #9
		
			// Let operations
			{ "Let x=2 in x end", "2"}, // #10
			{ "Let x=2 in 5 end", "5"}, // #11
			{ "Let x=2 y=5 in x*y end", "10"}, // #12
			{ "Let x=10 y=5 z=x+y in z*((-2)+60) end", "870"}, // #13
			{ "Let x=5 in Let y=5 in x*y end end", "25" }, // #14
			{ "Let x=5 in Let y=5 in Let x=2 in y-x end end end", "3" }, // #15
			{ "Let x=2 y=3 in Let z=x+y in Let q=10 x=10 e=q+x in x+e-(z*2) end end end", "20" },  // #16 //10+20-(5*2)
			{ "Let x=2 y=3 in Let x=20 in x+y end +x end", "25" }, // #17
			
			// Truth values
			{ "true", "true" }, // #19
			{ "false", "false" }, // #20
			
			// Relational operations
			{ "3>4", "false"}, // #21
			{ "10>8", "true"}, // #22
			{ "10>10", "false"}, // #23
			{ "10>=10", "true"}, // #24
			{ "10>=9", "true" }, // #25
			{ "10>=11", "false" }, // #26
			{ "10<10", "false"}, // #27
			{ "5<10", "true" }, // #28
			{ "15<10", "false"}, // #29
			{ "10<=10", "true"}, // #30
			{ "2<=5", "true" }, // #31
			{ "26<=0", "false"}, // #32
			{ "0==0", "true"}, // #33
			{ "1==1", "true"},  // #34
			{ "3==1", "false"}, // #35
			
			// Logical operations
			{ "true && true", "true" }, // #36
			{ "true && false", "false" }, // #37
			{ "false && true", "false"}, // #38
			{ "false && false", "false"}, // #39
			{ "true || true", "true" }, // #40
			{ "true || false", "true" }, // #41
			{ "false || true", "true" }, // #42
			{ "false || false", "false" }, // #43
			{ "true ^ true", "false" }, // #44
			{ "true ^ false", "true" }, // #45
			{ "false ^ true", "true" }, // #46
			{ "false ^ false", "false" }, // #47
			{ "2=>2 && 5<10", "true" }, // #48
			{ "3>4 && 4<3", "false" }, // #49
			{ "2==2 || 0==1", "true" }, // #50
			{ "2==2 || 2>=2", "true" }, // #51
			{ "3>5 || 5<3", "false" }, // #52
			{ "3==0 ^ 5==5", "true" }, // #53
			{ "5<=2 ^ 2==3", "false" }, // #54
			{ "50<=100 ^ 123>=1", "false" }, // #55
			{ "5<10 || 2==2 && 3==57", "true" }, // #56
			{ "5==2 && 2>3 || 3>3", "false" }, // #57
			{ "3-2==5-4 ^ 10-10>5 && 5>Let x=5 in Let y=3 in x-y end end", "true" }, // #58
			{ "2==2 || (Let x=2 y=3 in Let z=x+y in z+x+y end end==10) && (2<3) ^ 5<=4", "true"}, // #59
			
			// Conditional operations
			{ "if true then true else false", "true"}, // #60
			{ "if false then true else false", "false"}, // #61
			{ "if (true && false) then 1 else 2", "2"}, // #62
			{ "if true && false then 1 else 2", "2"}, // #63
			{ "if Let x=1 y=2 z=3 in Let q=x+y+z in Let x=x in q+q end end end > 10 then true else false", "true" }, // #64
			{ "if true ^ true then 9001 else true || false", "true"}, // #65
			{ "if true ^ true then 9001 else true && false", "false"}, // #66
			{ "if true ^ true then 9001 else 2==2 ^ 3<=Let y=3 in y+y+3 end", "false"} // #67

		});
	}
}
