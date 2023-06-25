
use master
go

drop database YogaCenter
go

CREATE DATABASE YogaCenter
GO
USE YogaCenter
GO

--CREATE TABLE--
ALTER DATABASE YogaCenter SET COMPATIBILITY_LEVEL = 160;


CREATE TABLE room(
	[maRoom] NVARCHAR(10) primary key,
	[status] BIT NOT NULL
	)

CREATE TABLE slot(
	[maSlot] NVARCHAR(10) primary key,
	[timeStart] Time NOT NULL,
	[timeEnd] Time NOT NULL
)
CREATE TABLE lopHocImg (
    [maAnh] NVARCHAR(25) primary key,
	[tenAnh] NVARCHAR(50) NULL,
    [image] VARBINARY(MAX) NOT NULL,
    [maLoaiLopHoc] NVARCHAR(25)NULL,
    [maHV] NVARCHAR(25) NULL,
    [maLopHoc] NVARCHAR(25) NULL,
	[maTrainer] NVARCHAR(25) NULL,
);

CREATE TABLE loaiLopHoc(
	[maLoaiLopHoc] NVARCHAR(10) primary key,
	[tenLoaiLopHoc] NVARCHAR(25) NOT NULL,
	[description] NVARCHAR(max) null,
	[hocPhi] DECIMAL(10,2) NOT NULL
	)

CREATE TABLE Trainer(
	[maTrainer] NVARCHAR(10) primary key,
	[Ho] NVARCHAR(10) NOT NULL,
	[Ten] NVARCHAR(25) NOT NULL,
	[dob] DATE NOT NULL,
	[phone] NVARCHAR(11) NOT NULL,
	[email] NVARCHAR(25) NOT NULL,
	[salary] DECIMAL(10,2) NOT NULL,
	[username] NVARCHAR(50) NOT NULL,
	[psw] NVARCHAR(75) NOT NULL,
	[soNgayNghi] INTEGER NOT NULL,
	[status] BIT NOT NULL,
	[trainerType] VARCHAR(255) NOT NULL,
	[maLoaiTK] NVARCHAR(10) NOT NULL
	)

CREATE TABLE lopHoc(
	[maLopHoc] NVARCHAR(10) primary key,
	[soLuongHV] INTEGER NOT NULL,
	[soBuoi] INTEGER NOT NULL,
	[maLoaiLopHoc] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maRoom] NVARCHAR(10) NOT NULL, --CONSTRAINT
	[soLuongHvHienTai] int not null,
	[ngay] NVARCHAR(20) NOT NULL 

	CONSTRAINT fk_loaiLopHoc_lopHoc FOREIGN KEY([maLoaiLopHoc]) REFERENCES loaiLopHoc([maLoaiLopHoc]),
	
	CONSTRAINT fk_maRoom_lopHoc FOREIGN KEY([maRoom]) REFERENCES room(maRoom)
	)

CREATE TABLE [admin](
	[maAdmin] NVARCHAR(10) primary key,
	[username] NVARCHAR(50) NOT NULL,
	[psw] NVARCHAR(75) NOT NULL,
	)

CREATE TABLE hocVien(
	[maHV] NVARCHAR(10) primary key,
	[Ho] NVARCHAR(10) NOT NULL,
	[Ten] NVARCHAR(25) NOT NULL,
	[dob] DATE NOT NULL,
	[username] NVARCHAR(50) NOT NULL,
	[phone] NVARCHAR(11) NOT NULL,
	[psw] NVARCHAR(75) NOT NULL,
	[gender]nvarchar(10)NOT NULL,
	[maLoaiTK] NVARCHAR(10) NOT NULL, --Constraint--
	[email] NVARCHAR(50) NOT NULL	
	)

create TABLE ScheduleHV(
[maHV] nvarchar(10) not null,--CONSTRAINT--
[maLopHoc] NVARCHAR(10) NOT NULL,--CONSTRAINT--
[ngayHoc] Date NOT NULL,
[maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
[thu] nvarchar(20) NOT NULl
primary key(maLopHoc,maHV,ngayHoc)

CONSTRAINT fk_maSlot_ScheduleHV FOREIGN KEY([maSlot]) REFERENCES slot(maSlot),
constraint fk_maLopHoc_ScheduleHV foreign key([maLopHoc]) references [lopHoc]([maLopHoc]),
constraint fk_maHocVien_ScheduleHV foreign key([maHV]) references [hocVien]([maHV])
)
CREATE TABLE ScheduleTrainer(
[maTrainer] nvarchar(10) not null,--CONSTRAINT--
[maLopHoc] NVARCHAR(10) NOT NULL,--CONSTRAINT--
[ngayHoc] Date NOT NULL,
[maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
[thu] nvarchar(20) NOT NULl
primary key(maLopHoc,maTrainer,ngayHoc)

constraint fk_maLopHoc_ScheduleTR foreign key([maLopHoc]) references [lopHoc]([maLopHoc]),
CONSTRAINT fk_maSlot_ScheduleTR FOREIGN KEY([maSlot]) REFERENCES slot(maSlot),
constraint fk_maTrainer_ScheduleTR foreign key([maTrainer]) references [Trainer]([maTrainer])
)
CREATE TABLE ScheduleTemp(

[maLopHoc] NVARCHAR(10) NOT NULL,--CONSTRAINT--
[ngayHoc] Date NOT NULL,
[maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
[thu] nvarchar(20) NOT NULl
primary key(maLopHoc,ngayHoc)

constraint fk_maLopHoc_ScheduleT foreign key([maLopHoc]) references [lopHoc]([maLopHoc]),
CONSTRAINT fk_maSlot_ScheduleT FOREIGN KEY([maSlot]) REFERENCES slot(maSlot),

)

/*CREATE TABLE temp(

)*/

CREATE TABLE hoaDon(
	[mahoaDon] NVARCHAR(10) primary key,
	[maHV] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maLopHoc] NVARCHAR(10) NOT NULL,	--CONSTRAINT--
	[giaTien] MONEY NOT NULL,
	[ngayThanhToan] DATE NOT NULL DEFAULT GETDATE(),
	CONSTRAINT fk_maHV_hoaDon FOREIGN KEY([maHV]) REFERENCES hocVien([maHV]),
	CONSTRAINT fk_maLop_hoaDon FOREIGN KEY([maLopHoc]) REFERENCES lopHoc([maLopHoc])
	)

CREATE TABLE Request(
	[maRequest] NVARCHAR(10) primary key,
	[maHV] NVARCHAR(10),  --CONSTRAINT--
	[maTrainer] NVARCHAR(10),  --CONSTRAINT-- 
	[maLopHoc] NVARCHAR(10) ,--CONSTRAINT--
	[maAdmin] NVARCHAR(10), --CONSTRAINT--
	[noiDung] NVARCHAR(255) NOT NULL,
	[ngayGui] DATE NOT NULL DEFAULT GETDATE(),
	[status] BIT NOT NULL
	CONSTRAINT fk_maHV_Don FOREIGN KEY([maHV]) REFERENCES hocVien([maHV]),
	CONSTRAINT fk_maTrainer_Don FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer]),
	CONSTRAINT fk_maLop_Don FOREIGN KEY([maLopHoc]) REFERENCES lopHoc([maLopHoc]),
	CONSTRAINT fk_admin_Don FOREIGN KEY([maAdmin]) REFERENCES [admin]([maAdmin])
	)

CREATE TABLE danhGia(
	[maDanhGia] NVARCHAR(10) primary key,
	[maHV] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[maTrainer] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[noiDung] NVARCHAR(255) NOT NULL,
	[grade] DECIMAL(3,1) NOT NULL,
	CONSTRAINT fk_maHV_danhGia FOREIGN KEY([maHV]) REFERENCES hocVien([maHV]),
	CONSTRAINT fk_maTrainer_danhGia FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer])
	)

CREATE TABLE hopDongGiaoVien(
	[maHopDong] NVARCHAR(10) primary key,
	[maTrainer] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[mucLuong] DECIMAL(10,2) NOT NULL,
	CONSTRAINT fk_maTrainer_hopDong FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer])
	)

/*
CREATE TABLE hopDongTrainer(
	[maHopDong] NVARCHAR(10) primary key,
	[maTrainer] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[mucLuong] DECIMAL(10,2) NOT NULL,
	CONSTRAINT fk_maTrainer_hopDong FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer])
	)
	*/

/*
CREATE TABLE bangOT(
	[maOT] NVARCHAR(10) primary key,
	[maTrainer] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[OTRate] DECIMAL(10,2) NOT NULL,
	[OTHours] DECIMAL(5,2) NOT NULL,
	[OTTotal] DECIMAL(10,2) NOT NULL,
	CONSTRAINT fk_maTrainer_OT FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer])
	)
	*/

CREATE TABLE paySlip(
	[maPaySlip] NVARCHAR(10) primary key,
	[maHopDong] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	Deductions DECIMAL(10, 2),
	Total DECIMAL(10, 2),
	[Date] DATE,
	CONSTRAINT fk_maPaySlip_hopDong FOREIGN KEY([maHopDong]) REFERENCES hopDongGiaoVien([maHopDong])
	)

INSERT INTO dbo.lopHocImg (maAnh, tenAnh, URLAnh)
VALUES('YG001', 'Surya Kriya', 'https://sundariyogacenter.com/admin/sanpham/Surya-Kriya-Inside_4018_anh1.jpg')
INSERT INTO dbo.lopHocImg (maAnh, tenAnh, URLAnh)
VALUES('YG002', 'Angamardana', 'https://sundariyogacenter.com/admin/sanpham/Angamardana-Ben-Trong_4021_anh1.jpg')
INSERT INTO dbo.lopHocImg (maAnh, tenAnh, URLAnh)
VALUES('YG003', 'Yogasanas', 'https://sundariyogacenter.com/admin/sanpham/Yogasanas-Ben-Trong_4016_anh1.jpg')
INSERT INTO dbo.lopHocImg (maAnh, tenAnh, URLAnh)
VALUES('YG004', 'Bhuta Shuddhi', 'https://sundariyogacenter.com/admin/sanpham/Bhuta-Shuddhi-Ben-Trong_4020_anh1.jpg')
INSERT INTO dbo.lopHocImg (maAnh, tenAnh, URLAnh)
VALUES('YG005', 'Surya Shakti', 'https://sundariyogacenter.com/admin/sanpham/Surya-Shakti-Ben-Trong_4026_anh1.jpg')
INSERT INTO dbo.lopHocImg (maAnh, tenAnh, URLAnh)
VALUES('YG006', 'Khóa Thiền Định', 'https://sundariyogacenter.com/admin/sanpham/Thien-Dinh-Ben-Trong_4013_anh1.jpg')