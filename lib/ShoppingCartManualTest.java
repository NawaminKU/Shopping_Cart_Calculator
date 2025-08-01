package lib;

import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2));
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct " + total3);
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: คำนวณมีส่วนลด BOGO โดยจำนวนสินค้า == 1 
        ArrayList<CartItem> BOGOCartOne = new ArrayList<>();
        BOGOCartOne.add(new CartItem("BOGO", "Bread", 25.0, 1));
        BOGOCartOne.add(new CartItem("BOGO", "Milk", 15.0, 1));
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BOGOCartOne);
        if (total4 == 40.0) {
            System.out.println("PASSED: BOGO cart total is correct " + total4);
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 40.0 but got " + total4);
            failedCount++;
        }

        // Test 5: คำนวณมีส่วนลด BOGO โดยจำนวนสินค้า >= 2 
        ArrayList<CartItem> BOGOCartOverOne = new ArrayList<>();
        BOGOCartOverOne.add(new CartItem("BOGO", "Bread", 25.0, 2));
        BOGOCartOverOne.add(new CartItem("BOGO", "Milk", 15.0, 4));
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BOGOCartOverOne);
        if (total5 == 55.0) {
            System.out.println("PASSED: BOGO cart total is correct " + total5);
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 55.0 but got " + total5);
            failedCount++;
        }

        // Test 6: คำนวณแบบมีส่วนลด BULK โดยจำนวนสินค้า < 6 
        ArrayList<CartItem> BULKCartLowerSix = new ArrayList<>();
        BULKCartLowerSix.add(new CartItem("BULK", "Bread", 25.0, 2));
        BULKCartLowerSix.add(new CartItem("BULK", "Milk", 15.0, 4));
        double total6 = ShoppingCartCalculator.calculateTotalPrice(BULKCartLowerSix);
        if (total6 == 110.0) {
            System.out.println("PASSED: BULK cart total is correct " + total6);
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 55.0 but got " + total6);
            failedCount++;
        }

        // Test 7: คำนวณแบบมีส่วนลด BULK โดยจำนวนสินค้า >= 6 
        ArrayList<CartItem> BULKCartOverAndEqualSix = new ArrayList<>();
        BULKCartOverAndEqualSix.add(new CartItem("BULK", "Bread", 25.0, 8));
        BULKCartOverAndEqualSix.add(new CartItem("BULK", "Milk", 15.0, 12));
        double total7 = ShoppingCartCalculator.calculateTotalPrice(BULKCartOverAndEqualSix);
        if (total7 == 342.0) {
            System.out.println("PASSED: BULK cart total is correct " + total7);
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 55.0 but got " + total7);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
