package interfaces;

public class Client {

    public static void main(String[] args) {
        CarAgent carAgent = new CarAgent();
        CarMaker maker = new Ssangyong();
        carAgent.setMaker(maker);
        carAgent.order();
    }
    
    //인터페이스를 통한 약한 결합
}
