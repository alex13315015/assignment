package xml;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("car-config.xml");
        CarAgent carAgent = context.getBean("carAgent",CarAgent.class);
        System.out.println("carAgent==="+carAgent);
        carAgent.order();

    }
    
    //인터페이스를 통한 약한 결합
}
