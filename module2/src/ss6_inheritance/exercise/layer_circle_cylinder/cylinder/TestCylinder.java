package ss6_inheritance.exercise.layer_circle_cylinder.cylinder;

public class TestCylinder {
    public static void main(String[] args) {
        Cylinder cylinder =new Cylinder();
        System.out.println(cylinder);
        cylinder=new Cylinder(1,"yellow",2);
        System.out.println(cylinder);
    }
}
