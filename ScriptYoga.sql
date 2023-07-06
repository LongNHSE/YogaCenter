
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
CREATE TABLE [description](
	maDescription  NVARCHAR(10) primary key,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
)
CREATE TABLE [descriptionIMG] (
    [maAnh] NVARCHAR(25) primary key,
	[tenAnh] NVARCHAR(50) NULL,
    [image] VARBINARY(MAX) NOT NULL,
    maDescription NVARCHAR(10)NULL,
	CONSTRAINT fk_Description_descriptionIMG FOREIGN KEY([maDescription]) REFERENCES [description]([maDescription])
);
CREATE TABLE loaiLopHoc(
	[maLoaiLopHoc] NVARCHAR(10) primary key,
	[tenLoaiLopHoc] NVARCHAR(25) NOT NULL,
	[maDescription] NVARCHAR(10) not null,
	[hocPhi] DECIMAL(10,2) NOT NULL
	CONSTRAINT fk_Description_loaiLopHoc FOREIGN KEY([maDescription]) REFERENCES [description]([maDescription]),
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
	[maLoaiTK] NVARCHAR(10) NOT NULL,
	[gender] NVARCHAR(10) NOT NULL
	)

CREATE TABLE lopHoc(
	[maLopHoc] NVARCHAR(10) primary key,
	[soLuongHV] INTEGER NOT NULL,
	[soBuoi] INTEGER NOT NULL,
	[maLoaiLopHoc] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maRoom] NVARCHAR(10) NOT NULL, --CONSTRAINT
	[soLuongHvHienTai] int not null,
	[ngay] NVARCHAR(20) NOT NULL,
	[status] bit null
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
[thu] nvarchar(20) NOT NULl,
[status] bit null
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
[thu] nvarchar(20) NOT NULl,
[status] bit null
primary key(maLopHoc,maTrainer,ngayHoc)

constraint fk_maLopHoc_ScheduleTR foreign key([maLopHoc]) references [lopHoc]([maLopHoc]),
CONSTRAINT fk_maSlot_ScheduleTR FOREIGN KEY([maSlot]) REFERENCES slot(maSlot),
constraint fk_maTrainer_ScheduleTR foreign key([maTrainer]) references [Trainer]([maTrainer])
)
CREATE TABLE ScheduleTemp(

[maLopHoc] NVARCHAR(10) NOT NULL,--CONSTRAINT--
[ngayHoc] Date NOT NULL,
[maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
[thu] nvarchar(20) NOT NULl,
[status] bit null
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






CREATE TABLE Semester (
    quarterID INT PRIMARY KEY,
    startDate DATE,
    endDate DATE,
    courses VARCHAR(255)
);

CREATE TABLE Attendance (
  attendanceID nvarchar(20) primary key,
  [maHV] NVARCHAR(10) not null,
  [maLopHoc] NVARCHAR(10) not null,
  [ngayHoc] Date NOT NULL,
  [maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
  [status] NVARCHAR(20) null
constraint fk_maLopHoc_Attendance foreign key([maLopHoc]) references [lopHoc]([maLopHoc]),
constraint fk_maHocVien_Attendance foreign key([maHV]) references [hocVien]([maHV])
);



CREATE TABLE paySlip(
	[maPaySlip] NVARCHAR(10) primary key,
	[maHopDong] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	Deductions DECIMAL(10, 2),
	Total DECIMAL(10, 2),
	[Date] DATE,
	CONSTRAINT fk_maPaySlip_hopDong FOREIGN KEY([maHopDong]) REFERENCES hopDongGiaoVien([maHopDong])
	)


CREATE TABLE blogCategories(
	[maCate] NVARCHAR(10) primary key,
	[tenCate] NVARCHAR(50) NOT NULL,
)
CREATE TABLE blogPost(
	[maBlog] NVARCHAR(10) primary key,
	[tieuDe] NVARCHAR(50) NOT NULL,
	[noiDung] NVARCHAR(MAX) NOT NULL,
	[ngayTaoPost] DATE NOT NULL,
	[maHV] NVARCHAR(10) NOT NULL, --CONSTRAINT
	[status] BIT NOT NULL DEFAULT 0
	CONSTRAINT fk_maHV_Blog FOREIGN KEY ([maHV]) REFERENCES hocVien([maHV])
)
	CREATE TABLE blogImg(
	[maAnh] NVARCHAR(10) primary key,
	[tenAnh] NVARCHAR(20) NOT NULL,
	[image] VARBINARY(MAX) NOT NULL,
	[maBlog] NVARCHAR(10) NOT NULL, --CONSTRAINT
	CONSTRAINT fk_maBlog_blogImg FOREIGN KEY ([maBlog]) REFERENCES blogPost([maBlog])
)

	--ALTER TABLE lopHoc
--ADD [status] bit NULL;

--ALTER TABLE loaiLopHoc
--ADD [maDescription] NVARCHAR(10) NULL;

-- Add foreign key constraint
--ALTER TABLE loaiLopHoc
--ADD CONSTRAINT fk_Description_loaiLopHoc FOREIGN KEY ([maDescription]) REFERENCES [description] ([maDescription]);
--ALTER TABLE loaiLopHoc
--DROP COLUMN [description];



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


ALTER TABLE [dbo].[ScheduleHV]
ADD [status] bit NULL;
ALTER TABLE [dbo].[ScheduleTemp]
ADD [status] bit NULL;
ALTER TABLE [dbo].[ScheduleTrainer]
ADD [status] bit NULL;