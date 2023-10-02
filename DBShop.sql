--Khởi tạo databse
Create database DBShop
Go
Use DBShop
Go
Create table Customer(
	CustomerID int primary key,
	[Name] nvarchar(255) not null,
	UserName nvarchar(255) not null,
	[Password] varchar (255) not null,
	Phone nvarchar(255) not null,
	Email nvarchar(255) not null,
	Gender nvarchar(10) not null,
	[Address] nvarchar(255) not null,
	IsAdmin bit not null
)
Create table [Order](
	OrderID int primary key,
	CustomerID int foreign key references Customer(CustomerID),
	Total float not null, /*lấy 10 chữ số, và 2 chữ sau dấu ,*/
	[Date] date not null
)
Create table Product(
	ProductID int primary key,
	[Name] nvarchar(255) not null,
	[Type] nvarchar(255) not null,
	Price float not null,
	ImgURL nvarchar(255)
)
Create table OrderDetail(
	OrderID int foreign key references [Order](OrderID),
	ProductID int foreign key references Product(ProductID),
	Quantity int not null,
	[Sum] float not null
)
-- Chèn dữ liệu vào bảng Customer
INSERT INTO Customer (CustomerID, [Name], UserName, [Password], Phone, Email, Gender, [Address], IsAdmin)
VALUES
  (1, N'Nguyễn Văn A','nguyenvana',lower(convert(varchar(32),hashbytes('MD5','vana123'),2)),'0123456789','nguyenvana@gmail.com','Male',N'TP.Hồ Chí Minh', 1),
  (2, N'Trần Thị B','tranthib',lower(convert(varchar(32), hashbytes('MD5','thib123'),2)),'0789456123','tranthib@gmail.com','Female',N'Cần Thơ', 0),
  (3, N'Lê Minh C','leminhc',lower(convert(varchar(32),hashbytes('MD5','minhc123'),2)),'0789123456','leminhc@gmail.com','Male',N'Hà Nội', 0);


-- Chèn dữ liệu vào bảng Product
INSERT INTO Product (ProductID, [Name],[Type], Price, ImgURL)
VALUES
  (1, N'Áo Sơ Mi Xanh',N'Áo Sơ Mi', 99.99, '\images\1.jpg'),
  (2, N'Áo Sơ Mi Đỏ',N'Áo Sơ Mi', 99.99, '\images\2.jpg'),
  (3, N'Áo Sơ Mi Đen',N'Áo Sơ Mi', 99.99, '\images\3.jpg'),
  (4, N'Áo Sơ Mi Xanh Đen',N'Áo Sơ Mi', 99.99, '\images\4.jpg'),
  (5, N'Áo Sơ Mi Trắng',N'Áo Sơ Mi', 99.99, '\images\5.jpg'),

  (6, N'Hoodie xanh rêu','Hoodie', 49.99, '\images\6.jpg'),
  (7, N'Hoodie hồng','Hoodie', 49.99, '\images\7.jpg'),
  (8, N'Hoodie đen','Hoodie', 49.99, '\images\8.jpg'),
  (9, N'Hoodie xám','Hoodie', 49.99, '\images\9.jpg'),
  (10, N'Hoodie zip xám mũ đen','Hoodie', 49.99, '\images\10.jpg'),


  (11, N'AKG Xám Cam',N'Áo Khoác Gió', 79.99, '\images\11.jpg'),
  (12, N'AKG Đen Xám Trắng',N'Áo Khoác Gió', 79.99, '\images\12.jpg'),
  (13, N'AKG Đen Trắng',N'Áo Khoác Gió', 79.99, '\images\13.jpg'),
  (14, N'AKG Đen Đỏ',N'Áo Khoác Gió', 79.99, '\images\14.jpg'),
  (15, N'AKG Đen Thùi Lùi',N'Áo Khoác Gió', 79.99, '\images\15.jpg'),

  (16, N'Sweater Đen',N'Sweater', 59.99, '\images\16.jpg'),
  (17, N'Sweater Xám',N'Sweater', 59.99, '\images\17.jpg'),
  (18, N'Sweater Rêu Nhạt',N'Sweater', 59.99, '\images\18.jpg'),
  (19, N'Sweater Đỏ',N'Sweater', 59.99, '\images\19.jpg'),
  (20, N'Sweater Nâu Doraemon',N'Sweater', 59.99, '\images\20.jpg'),

  (21, N'Áo Thun Đen',N'Áo Thun', 19.99, '\images\21.jpg'),
  (22, N'Áo Thun Đỏ',N'Áo Thun', 19.99, '\images\22.jpg'),
  (23, N'Áo Thun Hồng',N'Áo Thun', 19.99, '\images\23.jpg'),
  (24, N'Áo Thun Xanh Đen',N'Áo Thun', 19.99, '\images\24.jpg'),
  (25, N'Áo Thun Trắng',N'Áo Thun', 19.99, '\images\25.jpg'),

  (26, N'Combo 3 Áo Thun',N'Combo', 49.97, '\images\26.jpg'),
  (27, N'Combo 3 Áo Sơ Mi',N'Combo', 289.97, '\images\27.jpg'),
  (28, N'Combo 3 Áo Hoodie',N'Combo', 139.97, '\images\28.jpg'),
  (29, N'Combo 3 Áo Khoác Gió',N'Combo', 229.97, '\images\29.jpg');
  
-- Chèn dữ liệu vào bảng OrderDetail
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, [Sum])
VALUES
  (1, 1, 2, 199.98),
  (1, 2, 3, 149.97),
  (2, 3, 1, 79.99);

  -- Chèn dữ liệu vào bảng [Order]
INSERT INTO [Order] (OrderID, CustomerID, Total, [Date])
VALUES
  (1, 1, 100.00, '2023-06-01'),
  (2, 2, 200.00, '2023-06-05'),
  (3, 1, 150.00, '2023-06-08');
