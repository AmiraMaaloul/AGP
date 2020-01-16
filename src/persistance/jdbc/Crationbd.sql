


CREATE TABLE location (
  locationId INTEGER,
  locationLatitude FLOAT,
  locationLongitude FLOAT,
  PRIMARY KEY (locationId)
);


CREATE TABLE hotel (
  hotelId INTEGER,
  hotelName VARCHAR(250),
  hotelPrice  FLOAT,
  address VARCHAR(250),
  star INTEGER,
  locationId INTEGER,
  PRIMARY KEY (hotelId),
  FOREIGN KEY ( locationId) REFERENCES location( locationId)
);


CREATE TABLE site (
  siteId INTEGER,
  siteName VARCHAR(50),
  siteType ENUM('Activity', 'Historical'),
  sitePrice FLOAT ,
  locationId INTEGER,
  PRIMARY KEY (siteId),
  FOREIGN KEY (locationId) REFERENCES location(locationId)
);


CREATE TABLE transport (
  transportId INTEGER,
  transportType ENUM('Boat', 'Bus'),
  priceKM FLOAT,
  locationId INTEGER,
  PRIMARY KEY (transportId),
  FOREIGN KEY ( locationId) REFERENCES location( locationId)
);




