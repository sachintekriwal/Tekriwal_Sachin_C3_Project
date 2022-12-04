import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    @Mock
    RestaurantService restaurantList;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurantList.addRestaurant(restaurant);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        String name = "Amelie's cafe";
        boolean ret = restaurant.isRestaurantOpen(name);
        assertTrue(ret);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("20:00:00");
        Restaurant restaurant1 =new Restaurant("PizzaHut","Chennai",openingTime,closingTime);
        restaurantList.addRestaurant(restaurant1);
        boolean ret = restaurant1.isRestaurantOpen("PizzaHut");
        assertFalse(ret);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<< Testcases for findTotalPrice >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void findTotalPrice_should_return_positive_value_if_items_are_found_in_the_menu() throws itemNotFoundException {

        List<String> itemList = Arrays.asList("Sweet corn soup", "Vegetable lasagne");
        int totalPrice = restaurant.findTotalPrice(itemList);
        assertTrue(totalPrice>0);
    }
    @Test
    public void findTotalPrice_should_return_zero_value_if_items_are_found_in_the_menu() throws itemNotFoundException {

        List<String> itemList = Arrays.asList("Sweet corn soup not in the menu", "Vegetable lasagne not in the menu");
        int totalPrice = restaurant.findTotalPrice(itemList);
        assertTrue(totalPrice>0);
    }
}