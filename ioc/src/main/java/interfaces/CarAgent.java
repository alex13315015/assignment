package interfaces;

public class CarAgent {

    //강한 결합
    private CarMaker maker;

//    public CarAgent() {
//        maker = new Hyundai();
//    }

    public void setMaker(CarMaker maker) {
        this.maker = maker;
    }

    public void order() {
        Money money = new Money(50000000);
        System.out.println("차 가격: "+money.getAmount());

        Car car = maker.sell(money);
        System.out.println("차 가져옴 : "+car.getName());
    }
}
