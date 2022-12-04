import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RestaurantService {
    public static List<Restaurant> restaurants = new ArrayList<>();
    public static void main(String[] args) throws restaurantNotFoundException {

        ZoneId zone1 = ZoneId.of("Asia/Kolkata");
        Restaurant restaurant1 = new Restaurant("Barbi-queue Nation", "Electronic City", LocalTime.now(zone1), LocalTime.of(23,0) );
        restaurant1.addToMenu("Chicken Curry", 300);
        restaurant1.addToMenu("Paneer Curry", 200);
        restaurant1.addToMenu("Rice", 100);
        restaurant1.addToMenu("Dal", 100);
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant("Barbi-queue Nation", "Marathahallli", LocalTime.now(zone1), LocalTime.of(23,0) );
        restaurant2.addToMenu("Chicken Biryani", 300);
        restaurant2.addToMenu("Paneer Curry", 200);
        restaurant2.addToMenu("Rice", 100);
        restaurant2.addToMenu("Dal", 100);
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant("PizzaHut", "ElectronicCity", LocalTime.now(zone1), LocalTime.of(23,0) );
        restaurant3.addToMenu("Chicken Pizza", 300);
        restaurant3.addToMenu("Paneer Pizza", 200);
        restaurant3.addToMenu("Veg Pizza", 100);
        restaurant3.addToMenu("Pan Pizza", 100);
        restaurants.add(restaurant3);
        Restaurant restaurant4 = new Restaurant("Dominos", "ElectronicCity", LocalTime.now(zone1), LocalTime.of(23,0) );
        restaurant4.addToMenu("Chicken Pizza", 300);
        restaurant4.addToMenu("Paneer Pizza", 200);
        restaurant4.addToMenu("Veg Pizza", 100);
        restaurant4.addToMenu("Pan Pizza", 100);
        restaurants.add(restaurant4);

        try {
            System.out.println("Searching for the restaurant: test ");
            Restaurant test = RestaurantService.findRestaurantByName("test");
        }catch(restaurantNotFoundException e) {
            System.out.println("Restaurant Not found exception: " +e.getMessage());
        }
        System.out.println("Number of restaurants in the list: " +restaurants.size());

        List<String> itemList = Arrays.asList("Chicken Pizza1", "Paneer Pizza");
        int totalPrice = RestaurantService.getSelectedItemsTotalPricesFromRestaurant("Dominos", itemList);
        System.out.println("TotalPrice: " +totalPrice);
    }


    public static Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{

        int i = -1;
        boolean found = false;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        for(Restaurant rest: restaurants ) {
            i++;
            if(rest.getName().equals(restaurantName)) {
                found = true;
                System.out.println("Found restaurant at index: " +i);
                return restaurants.get(i);
            }
        }
        if(!found) {
            System.out.println("Restaurant not found");
            throw new restaurantNotFoundException("Restaurant not found");
        }
        System.out.println("returning null");
        return null;
    }


    public static Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }
    public static void addRestaurant(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if(restaurantToBeRemoved != null) {
            restaurants.remove(restaurantToBeRemoved);
        } else {
            throw new restaurantNotFoundException("Restaurant not found");
        }
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static int getSelectedItemsTotalPricesFromRestaurant(String restaurantName, List<String> itemList) throws restaurantNotFoundException {
        int total = 0;

        try {
            Restaurant restaurant = RestaurantService.findRestaurantByName(restaurantName);
            total = restaurant.findTotalPrice(itemList);
        } catch (restaurantNotFoundException | itemNotFoundException e) {
            System.out.println("RestaurantNotFoundException: " + e.getMessage());
        }
        return total;
    }
}
