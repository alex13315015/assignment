package javabean;

public class Hyundai implements CarMaker {
@Override
    public Car sell(Money money) {
        System.out.println("현대 자동차 돈주면 차를 팝니다." + money.getAmount());
        Car car = new Car("제네시스");
        System.out.println("현대에서 만든 "+car.getName());
        return car;
    }
}
