package javabean;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CarConfig.class);

        CarAgent carAgent = context.getBean("carAgent", CarAgent.class);
        System.out.println("carAgent===" + carAgent);
        carAgent.order();

        System.out.println("==============================================");

        CarMaker maker = context.getBean("ssangyong",Ssangyong.class);
        carAgent.setMaker(maker);
        carAgent.order();
    }
    
    //인터페이스를 통한 약한 결합
}
