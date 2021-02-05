//package mk.finki.ukim.wp.lab.repository.inMemoryRepository;
//
//import mk.finki.ukim.wp.lab.model.Order;
//import mk.finki.ukim.wp.lab.model.User;
//import mk.finki.ukim.wp.lab.model.exceptions.InvalidOrderException;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import mk.finki.ukim.wp.lab.model.User;
//
//@Repository
//public class OrderRepository {
//
//    List<Order> orderList = new ArrayList<>();
//    private long id = 0;
//
//    public Order placeOrder(String balloonColor, String balloonSize, User user){
//        if (balloonColor == null || balloonColor.isEmpty() || balloonSize == null || balloonSize.isEmpty()){
//            throw new InvalidOrderException();
//        }
//
//
//        Order o = new Order(balloonColor, balloonSize,user);
//        id += 1;
//        orderList.add(o);
//        return o;
//
//    }
//
//    public List<Order> findAll(){
//        return orderList;
//    }
//
////    public List<Order> findOrdersByNameAndAddress(String name, String address){
////        return orderList.stream().filter(r -> r.getClientName().equals(name) && r.getClientAddress().equals(address)).collect(Collectors.toList());
////    }
//
//}
