CREATE TABLE orders (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        subtotal DECIMAL(10,2) NOT NULL,
                        tax_delivery DECIMAL(10,2) NOT NULL,
                        total_price DECIMAL(10,2) NOT NULL,

                        restaurant_id BIGINT NOT NULL,
                        user_client_id BIGINT NOT NULL,
                        payment_method_id BIGINT NOT NULL,

                        address_city_id BIGINT(20) NOT NULL,
                        address_zipcode VARCHAR(9) NOT NULL,
                        address_street VARCHAR(100) NOT NULL,
                        address_number VARCHAR(20) NOT NULL,
                        address_complement VARCHAR(60) NULL,
                        address_neighborhood VARCHAR(60) NOT NULL,

                        status VARCHAR(10) NOT NULL,
                        creation_date DATETIME NOT NULL,
                        confirmation_date DATETIME NULL,
                        cancel_date DATETIME NULL,
                        delivery_date DATETIME NULL,

                        PRIMARY KEY (id),

                        CONSTRAINT fk_order_address_city FOREIGN KEY (address_city_id) REFERENCES city (id),
                        CONSTRAINT fk_order_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
                        CONSTRAINT fk_order_user_client FOREIGN KEY (user_client_id) REFERENCES user (id),
                        CONSTRAINT fk_order_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_order (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            quantity SMALLINT(6) NOT NULL,
                            unitary_price DECIMAL(10,2) NOT NULL,
                            total_price DECIMAL(10,2) NOT NULL,
                            observation VARCHAR(255) NULL,
                            order_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,

                            PRIMARY KEY (id),
                            UNIQUE KEY uk_item_order_product (order_id, product_id),

                            CONSTRAINT fk_item_order_order FOREIGN KEY (order_id) REFERENCES orders (id),
                            CONSTRAINT fk_item_order_product FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
