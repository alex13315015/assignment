package xml;

public class Ssangyong implements CarMaker {

    @Override
    public Car sell(Money money) {
        System.out.println("쌍용 자동차 돈주면 차를 팝니다." + money.getAmount());
        Car car = new Car("티볼리");
        System.out.println("쌍용에서 만든 "+car.getName());
        return car;
    }
}
