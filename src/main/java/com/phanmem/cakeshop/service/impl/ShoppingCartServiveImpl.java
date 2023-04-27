package com.phanmem.cakeshop.service.impl;

import com.phanmem.cakeshop.entity.Cart;
import com.phanmem.cakeshop.entity.CartItem;
import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.entity.Product;
import com.phanmem.cakeshop.repository.CartItemRepository;
import com.phanmem.cakeshop.repository.CartRepository;
import com.phanmem.cakeshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiveImpl implements ShoppingCartService {
    @Autowired
    private CartItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addItemToCart(Product product, int quantity, Customer customer) {
        Cart cart = customer.getCart();

        if (cart == null) {
            cart = new Cart();
        }
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem cartItem = findCartItem(cartItems, product.getProductId());
        if (cartItems == null) {
            cartItems = new HashSet<>();
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getProductId());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + ( quantity * product.getPrice()));
                itemRepository.save(cartItem);
            }
        }
        cart.setCartItem(cartItems);

        int totalItems = totalItems(cart.getCartItem());
        double totalPrice = totalPrice(cart.getCartItem());

        cart.setTotalPrices(totalPrice);
        cart.setTotalItems(totalItems);
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    @Override
    public Cart updateItemInCart(Product product, int quantity, Customer customer) {
        Cart cart = customer.getCart();

        Set<CartItem> cartItems = cart.getCartItem();

        CartItem item = findCartItem(cartItems, product.getProductId());

        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());

        itemRepository.save(item);

        int totalItems = totalItems(cartItems);
        double totalPrice = totalPrice(cartItems);

        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteItemFromCart(Product product, Customer customer) {
        Cart cart = customer.getCart();

        Set<CartItem> cartItems = cart.getCartItem();

        CartItem item = findCartItem(cartItems, product.getProductId());

        cartItems.remove(item);

        itemRepository.delete(item);

        double totalPrice = totalPrice(cartItems);
        int totalItems = totalItems(cartItems);

        cart.setCartItem(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return cartRepository.save(cart);
    }

    @Override
    public CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        CartItem cartItem = null;

        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }

    @Override
    public int totalItems(Set<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    @Override
    public double totalPrice(Set<CartItem> cartItems){
        double totalPrice = 0.0;

        for(CartItem item : cartItems){
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}
