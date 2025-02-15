CREATE TABLE city (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      state_id BIGINT NOT NULL,
                      PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_group (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_group_permission (
                                       user_group_id BIGINT NOT NULL,
                                       permission_id BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE payment_method (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                description VARCHAR(255) NOT NULL,
                                PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE permission (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            description VARCHAR(255) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE product (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         description VARCHAR(255) NOT NULL,
                         is_active BIT NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(19,2) NOT NULL,
                         restaurant_id BIGINT NOT NULL,
                         PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE restaurant (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            address_zipcode VARCHAR(255),
                            address_complement VARCHAR(255),
                            address_neighborhood VARCHAR(255),
                            address_number VARCHAR(255),
                            address_street VARCHAR(255),
                            created_at DATETIME(6) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            delivery_fee DECIMAL(19,2) NOT NULL,
                            updated_at DATETIME(6) NOT NULL,
                            address_city_id BIGINT,
                            cuisine_id BIGINT NOT NULL,
                            PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE restaurant_payment_method (
                                           restaurant_id BIGINT NOT NULL,
                                           payment_method_id BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE state (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      email VARCHAR(255) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      registered_at DATETIME NOT NULL,
                      PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_user_group (
                                 user_id BIGINT NOT NULL,
                                 user_group_id BIGINT NOT NULL
) ENGINE=InnoDB;

ALTER TABLE user_group_permission
    ADD CONSTRAINT fk_user_group_permission_permission
        FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE user_group_permission
    ADD CONSTRAINT fk_user_group_permission_user_group
        FOREIGN KEY (user_group_id) REFERENCES user_group (id);

ALTER TABLE product
    ADD CONSTRAINT fk_product_restaurant
        FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

ALTER TABLE restaurant
    ADD CONSTRAINT fk_restaurant_cuisine
        FOREIGN KEY (cuisine_id) REFERENCES cuisine (id);

ALTER TABLE restaurant
    ADD CONSTRAINT fk_restaurant_city
        FOREIGN KEY (address_city_id) REFERENCES city (id);

ALTER TABLE restaurant_payment_method
    ADD CONSTRAINT fk_restaurant_payment_method_payment_method
        FOREIGN KEY (payment_method_id) REFERENCES payment_method (id);

ALTER TABLE restaurant_payment_method
    ADD CONSTRAINT fk_restaurant_payment_method_restaurant
        FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

ALTER TABLE user_user_group
    ADD CONSTRAINT fk_user_user_group_user_group
        FOREIGN KEY (user_group_id) REFERENCES user_group (id);

ALTER TABLE user_user_group
    ADD CONSTRAINT fk_user_user_group_user
        FOREIGN KEY (user_id) REFERENCES user (id);
