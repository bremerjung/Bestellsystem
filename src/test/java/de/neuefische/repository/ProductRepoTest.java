package de.neuefische.repository;

import de.neuefische.exceptions.ProductNotFoundException;
import de.neuefische.model.Colour;
import de.neuefische.model.PhysicalProduct;
import de.neuefische.model.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ProductRepoTest {

    @Test
    void testList_empty_map() {
        // given
        Map<String, Product> expected = new HashMap<>();
        ProductRepo productRepo = new ProductRepo();
        productRepo.setProducts(expected);

        // when
        Map<String, Product> actual = productRepo.list();

        // then
        //assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testList_filled_map() {
        // given
        Map<String, Product> expected = new HashMap<>();
        Product product1 = new PhysicalProduct("p1", "Toaster", Colour.GREEN);
        Product product2 = new PhysicalProduct("p2", "Waschmaschine", Colour.WHITE);
        expected.put(product2.getId(), product2);
        expected.put(product2.getId(), product2);
        ProductRepo productRepo = new ProductRepo();
        productRepo.setProducts(expected);

        // when
        Map<String, Product> actual = productRepo.list();

        // then
        //assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGet_positive_case_one_product() {
        // given
        Product expected = new PhysicalProduct("p1", "Toaster", Colour.GREEN);
        ProductRepo productRepo = new ProductRepo();
        Map<String, Product> productMap = new HashMap<>();
        productMap.put(expected.getId(), expected);
        productRepo.setProducts(productMap);

        // when
        Product actual = null;
        try {
            actual = productRepo.get("p1").get(); // TODO try/catch versus assertDoesNotThrow anschauen
        } catch (ProductNotFoundException e) {
            fail("ProductNotFoundException wurde geworfen.");
        }

        // then
        //assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGet_positive_case_several_products() {
        // given
        Product product1 = new PhysicalProduct("p1", "Toaster", Colour.GREEN);
        Product expected = new PhysicalProduct("p2", "Waschmaschine", Colour.WHITE);
        ProductRepo productRepo = new ProductRepo();
        Map<String, Product> productMap = new HashMap<>();
        productMap.put(expected.getId(), expected);
        productRepo.setProducts(productMap);

        // when
        Product actual = null;
        try {
            actual = productRepo.get("p2").get();
        } catch (ProductNotFoundException e) {
            fail("ProductNotFoundException wurde geworfen.");
        }

        // then
        //assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGet_negative_case() {
        // given
        Product product1 = new PhysicalProduct("p1", "Toaster", Colour.GREEN);
        Product product2 = new PhysicalProduct("p2", "Waschmaschine", Colour.WHITE);
        ProductRepo productRepo = new ProductRepo();
        Map<String, Product> productMap = new HashMap<>();
        productMap.put(product2.getId(), product2);
        productRepo.setProducts(productMap);

        // when & then
        //assertThrows(ProductNotFoundException.class, () -> productRepo.get("p3").get());
        assertThatThrownBy(() -> productRepo.get("p3").get()).isInstanceOf(ProductNotFoundException.class);
    }
}