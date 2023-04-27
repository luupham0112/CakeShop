package com.phanmem.cakeshop.service;

import com.phanmem.cakeshop.entity.Cart;
import com.phanmem.cakeshop.entity.CartItem;
import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.entity.Product;

import java.util.Set;

public interface ShoppingCartService{
    Cart addItemToCart(Product product, int quantity, Customer customer);

    Cart updateItemInCart(Product product, int quantity, Customer customer);

    Cart deleteItemFromCart(Product product, Customer customer);

    CartItem findCartItem(Set<CartItem> cartItems, Long productId);

    int totalItems(Set<CartItem> cartItems);

    double totalPrice(Set<CartItem> cartItems);
}
