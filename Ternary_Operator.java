import java.util.Scanner;
public class Ternary_Operator {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the temperature in  celsius : ") ;
        int temp = input.nextInt();
        String weather = (temp<=0)?"Very cold":
        temp>0 && temp<20?"Cool":
        temp>=20 && temp<30?"Sunny":
        temp>=30 && temp<40?"Hot":
        temp>=40?"Very Hot":"Enter a valid temperature";
System.out.println(weather);


input.close();

    }
    
}
