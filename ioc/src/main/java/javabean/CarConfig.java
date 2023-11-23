package javabean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {

    @Bean(name="hyundai")
    public CarMaker hyundai() {
        CarMaker maker = new Hyundai();
        return maker;
    }

    @Bean
    public CarMaker kia() {
        CarMaker maker = new Kia();
        return maker;
    }

    @Bean
    public CarMaker ssangyong() {
        CarMaker maker = new Ssangyong();
        return maker;
    }

    @Bean(name="carAgent")
    public CarAgent carAgent() {
        CarAgent carAgent = new CarAgent(hyundai());
        return carAgent;
    }
}
