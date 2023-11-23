package annotation;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CarAgent {
    //강한 결합
@Inject
@Named("hyundai")
    private CarMaker maker;


    public CarAgent(@Qualifier("hyundai") CarMaker maker) {

        this.maker = maker;
    }

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
