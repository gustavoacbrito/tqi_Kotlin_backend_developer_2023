CREATE TABLE tb_category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_tb_category PRIMARY KEY (id)
);
CREATE TABLE tb_product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    unit          VARCHAR(255)          NOT NULL,
    price         DOUBLE                NOT NULL,
    category_id   BIGINT                NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_tb_product PRIMARY KEY (id)
);

ALTER TABLE tb_product
    ADD CONSTRAINT FK_TB_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES tb_category (id);

CREATE TABLE tb_cart
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    total_sale_price DOUBLE                NOT NULL,
    CONSTRAINT pk_tb_cart PRIMARY KEY (id)
);

CREATE TABLE tb_cart_item
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    product_id       BIGINT                NULL,
    quantity         INT                   NOT NULL,
    total_items_cost DOUBLE                NOT NULL,
    cart_id          BIGINT                NULL,
    CONSTRAINT pk_tb_cart_item PRIMARY KEY (id)
);

ALTER TABLE tb_cart_item
    ADD CONSTRAINT FK_TB_CART_ITEM_ON_CART FOREIGN KEY (cart_id) REFERENCES tb_cart (id);

ALTER TABLE tb_cart_item
    ADD CONSTRAINT FK_TB_CART_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES tb_product (id);

CREATE TABLE tb_sale
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    payment_option_enum VARCHAR(255)          NOT NULL,
    cart_id             BIGINT                NULL,
    created_at          datetime              NOT NULL,
    CONSTRAINT pk_tb_sale PRIMARY KEY (id)
);

ALTER TABLE tb_sale
    ADD CONSTRAINT FK_TB_SALE_ON_CART FOREIGN KEY (cart_id) REFERENCES tb_cart (id);