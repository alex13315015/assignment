package basic;

public class CarAgent {

    //강한 결합
    private Hyundai hyundai;

    public CarAgent() {
        hyundai = new Hyundai();
    }

    public void order() {
        Money money = new Money(50000000);
        System.out.println("제네시스 차 가격: "+money.getAmount());

        Car car = hyundai.sell(money);
        System.out.println("현대에 가서 차 가져옴 : "+car.getName());
    }
}
