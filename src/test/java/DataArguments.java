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
			{ "Let x=2 y=3 in Let z = x+y+5 in Let qwerty = z in qwerty end end end + Let x=10 in Let y=(x+x)-x n=0 m=0 in y end end", "20"}, // #18 //Has to create 5 frames.
			
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
			{ "3<>3", "false"}, // #36
			{ "2<>3", "true"}, // #37
//			{ "false > true", "0"}, // TODO to be fixed, probably, with type checking.
			
			// Logical operations
			{ "true && true", "true" }, // #38
			{ "true && false", "false" }, // #39
			{ "false && true", "false"}, // #40
			{ "false && false", "false"}, // #41
			{ "true || true", "true" }, // #42
			{ "true || false", "true" }, // #43
			{ "false || true", "true" }, // #44
			{ "false || false", "false" }, // #45
			{ "true ^ true", "false" }, // #46
			{ "true ^ false", "true" }, // #47
			{ "false ^ true", "true" }, // #48
			{ "false ^ false", "false" }, // #49
			{ "2>=2 && 5<10", "true" }, // #50
			{ "3>4 && 4<3", "false" }, // #51
			{ "2==2 || 0==1", "true" }, // #52
			{ "2==2 || 2>=2", "true" }, // #53
			{ "3>5 || 5<3", "false" }, // #54
			{ "3==0 ^ 5==5", "true" }, // #55
			{ "5<=2 ^ 2==3", "false" }, // #56
			{ "50<=100 ^ 123>=1", "false" }, // #57
			{ "5<10 || 2==2 && 3==57", "true" }, // #58
			{ "5==2 && 2>3 || 3>3", "false" }, // #59
			{ "3-2==5-4 ^ 10-10>5 && 5>Let x=5 in Let y=3 in x-y end end", "true" }, // #60
			{ "2==2 || (Let x=2 y=3 in Let z=x+y in z+x+y end end==10) && (2<3) ^ 5<=4", "true"}, // #61
			
			// Conditional operations
			{ "if true then true else false", "true"}, // #62
			{ "if false then true else false", "false"}, // #63
			{ "if (true && false) then 1 else 2", "2"}, // #64
			{ "if true && false then 1 else 2", "2"}, // #65
			{ "if Let x=1 y=2 z=3 in Let q=x+y+z in Let x=x in q+q end end end > 10 then true else false", "true" }, // #66
			{ "if true ^ true then 9001 else true || false", "true"}, // #67
			{ "if true ^ true then 9001 else true && false", "false"}, // #68
			{ "if true ^ true then 9001 else 2==2 ^ 3<=Let y=3 in y+y+3 end", "false"}, // #69
			
			// Sequences
			{"2+2", "4"}, // #70
			{"2+2;2+2", "4"}, // #71
			{"2+2;4+4", "8"}, // #72
			{"2+2;8-4", "4"}, // #73
			{"4+4;2+2", "4"}, // #74
			{"2+2;10>5", "true"}, // #75
			{"10>5;5*2", "10"}, // #76
			{"10+10;2==3;6/3;2+2;5>10", "false"}, // #77
			{"(2+2;5>2)", "true"}, // #78
			{"if 2+2;2==2 then 4 else 0", "4"}, // #79
			{"Let x=2 in x+2;x end", "2"}, // #80			
			
			// Strings
			{"print hello", "hello"}, // #81
			{"print hello world", "hello world"} // #82
		});
	}
}
