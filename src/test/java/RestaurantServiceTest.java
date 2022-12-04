import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        service.addRestaurant(restaurant);
    }
    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        String restaurantTest = "Amelie's cafe";
        Restaurant foundRestaurant = service.findRestaurantByName(restaurantTest);
        assertNotNull(foundRestaurant);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("RestaurantNotAdded"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<< Testcases for getSelectedItemsTotalPricesFromRestaurant >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void getSelectedItemsTotalPricesFromRestaurant_should_return_positive_value_if_items_are_found() throws restaurantNotFoundException {

        List<String> itemList = Arrays.asList("Sweet corn soup", "Vegetable lasagne");
        int totalPrice = service.getSelectedItemsTotalPricesFromRestaurant("Amelie's cafe", itemList);
        assertTrue(totalPrice>0);
    }
    @Test
    public void getSelectedItemsTotalPricesFromRestaurant_should_return_zero_if_items_are_not_found() throws restaurantNotFoundException {

        List<String> itemList = Arrays.asList("Sweet corn soup not in menu", "Vegetable lasagne not in menu");
        int totalPrice = service.getSelectedItemsTotalPricesFromRestaurant("Amelie's cafe", itemList);
        assertTrue(totalPrice == 0);
    }

}