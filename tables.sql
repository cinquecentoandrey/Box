CREATE TABLE Box (
	box_id smallint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	box_name varchar(40) NOT NULL,
	box_lenght smallint NOT NULL,
	box_width smallint NOT NULL,
	box_height smallint NOT NULL,
	box_price real NOT NULL,
	box_in_stock smallint,
	box_on_order smallint,
	
	CONSTRAINT box_pkey PRIMARY KEY(box_id)
);

CREATE TABLE Customer (
	customer_id smallint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	company_name varchar(40) NOT NULL,
	contact_name varchar(40),
	contact_title varchar(30),
	address varchar(30),
	city varchar(30),
	postal_code varchar(10),
	email varchar(40),
	country varchar(20),
	phone varchar(20),
	
	CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);

CREATE TABLE Orders (
	order_id smallint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	customer_id smallint,
	order_date date,
	delivery_date date,
	ship_address varchar(60),
	ship_city varchar(30),
	ship_postal_code varchar(30),
	ship_country varchar(16),
	status boolean DEFAULT false,
	
	CONSTRAINT orders_pkey PRIMARY KEY (order_id),
	CONSTRAINT orders_customer_id_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id)
);

CREATE TABLE Order_details (
	order_details_id smallint NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	order_id smallint,
	box_id smallint,
	box_price real NOT NULL,
	quantity smallint NOT NULL,
	discount real NOT NULL,
	
	CONSTRAINT order_details_pkey PRIMARY KEY (order_details_id),
	CONSTRAINT order_details_box_id_fkey FOREIGN KEY (box_id)
        REFERENCES public.box (box_id).
	 CONSTRAINT order_details_order_id_fkey FOREIGN KEY (order_id)
        REFERENCES public.orders (order_id)
);