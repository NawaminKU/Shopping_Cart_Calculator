package lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * ------------------------------------
     * กระบวนการทำงาน
     * # Normal --
     * - Test 1: ตะกร้าเป็น null = 0.0
     * - Test 2: ตะกร้าว่าง = 0.0
     * - Test 3: คำนวณแบบไม่มีส่วนลด นั่นคือ price * quantity = total
     * # BOGO --
     * - Test 4: คำนวณมีส่วนลด BOGO โดยจำนวนสินค้า == 1 นั่นคือ [price * (quantity*2)] / 2 = total 
     * - Test 5: คำนวณมีส่วนลด BOGO โดยจำนวนสินค้า >= 2 นั่นคือ [pric * quantity] / 2 = total
     * # BULK --
     * - Test 6: คำนวณแบบมีส่วนลด BULK โดยจำนวนสินค้า < 6 นั่นคือ price * quantity = total
     * - Test 7: คำนวณแบบมีส่วนลด BULK โดยจำนวนสินค้า >= 6 นั่นคือ (price * quantity) - 10% = total
     * ------------------------------------
     * @param ArrayList<CartItem> items ที่ต้องตรวจสอบ
     * @return 0.0, total
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // TODO: เขียนโค้ด Implementation ที่นี่
        if (items == null || items.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (CartItem item : items) {
            // ข้ามรายการที่มีราคา หรือจำนวนติดลบ
            if (item.price() < 0 || item.quantity() < 0) {
                continue;
            }

            double price = item.price();
            int quantity = item.quantity();
            String sku = item.sku();

            switch (sku) {
                case "NORMAL":
                    total += price * quantity;
                    break;

                case "BOGO":
                    if (quantity == 1) {
                        total += (price * (quantity*2))/2;
                    } else if (quantity >= 2) {
                        total += (price * quantity)/2;
                    }
                    break;

                case "BULK":
                    double subtotal = 0.0;
                    if (quantity < 6) {
                        subtotal += price * quantity;
                    } else {
                        subtotal += (price * quantity) * 0.9;
                    }
                    total += subtotal;
                    break;
            }
        }

        return total;
    }
}