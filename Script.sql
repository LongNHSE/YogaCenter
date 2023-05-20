CREATE DATABASE YogaCenter
GO
USE YogaCenter
GO

--CREATE TABLE--


CREATE TABLE room(
	[maRoom] NVARCHAR(10) primary key,
	[roomNo] NVARCHAR(10) NOT NULL,
	[status] BIT NOT NULL
	)
CREATE TABLE slot(
	[maSlot] NVARCHAR(10) primary key,
	[timeStart] Time NOT NULL,
	[timeEnd] Time NOT NULL
)


CREATE TABLE loaiLopHoc(
	[maLoaiLopHoc] NVARCHAR(10) primary key,
	[tenLoaiLopHoc] NVARCHAR(25) NOT NULL,
	[hocPhi] DECIMAL(10,2) NOT NULL
	)
CREATE TABLE loaiTaiKhoan(
	[maLoaiTK] NVARCHAR(10) primary key,
	[tenLoaiTK] NVARCHAR(20) NOT NULL,
	[vaitro] NVARCHAR(15) NOT NULL
	)
CREATE TABLE Trainer(
	[maTrainer] NVARCHAR(10) primary key,
	[HoVaTen] NVARCHAR(50) NOT NULL,
	[dob] DATE NOT NULL,
	[phone] NVARCHAR(11) NOT NULL,
	[email] NVARCHAR(25) NOT NULL,
	[salary] DECIMAL(10,2) NOT NULL,
	[username] NVARCHAR(50) NOT NULL,
	[psw] NVARCHAR(75) NOT NULL,
	[soNgayNghi] INTEGER NOT NULL,
	[status] BIT NOT NULL,
	[trainerType] VARCHAR(255) NOT NULL,
	[maLoaiTK] NVARCHAR(10) NOT NULL --CONSTRAINT--
	CONSTRAINT fk_loaiTK_TRAINER FOREIGN KEY([maLoaiTK]) REFERENCES loaiTaiKhoan([maLoaiTK])
	)
CREATE TABLE lopHoc(
	[maLopHoc] NVARCHAR(10) primary key,
	[soLuongHV] INTEGER NOT NULL,
	[soBuoi] INTEGER NOT NULL,
	[maTrainer] NVARCHAR(10) NOT NULL, -- CONSTRAINT --
	[maLoaiLopHoc] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maRoom] NVARCHAR(10) NOT NULL, --CONSTRAINT
	[ngay] NVARCHAR(20) NOT NULL 
	CONSTRAINT fk_maTrainer_lopHoc FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer]),
	CONSTRAINT fk_loaiLopHoc_lopHoc FOREIGN KEY([maLoaiLopHoc]) REFERENCES loaiLopHoc([maLoaiLopHoc]),
	CONSTRAINT fk_maSlot_lopHoc FOREIGN KEY([maSlot]) REFERENCES slot(maSlot),
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
	[psw] NVARCHAR(75) NOT NULL,
	[maLopHoc] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maLoaiTK] NVARCHAR(10) NOT NULL --Constraint--
	CONSTRAINT fk_maLop_HocVien FOREIGN KEY([maLopHoc]) REFERENCES lopHoc([maLopHoc]),
	CONSTRAINT fk_loaiTK_HocVien FOREIGN KEY([maLoaiTK]) REFERENCES loaiTaiKhoan([maLoaiTK])
	)
CREATE TABLE diemDanh(
	[maDiemDanh] NVARCHAR(10) primary key,
	[maHV] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maLopHoc] NVARCHAR(10) NOT NULL,	--CONSTRAINT--
	[maSlot] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[date] DATE NOT NULL DEFAULT GETDATE(),
	[status] BIT NOT NULL,
	CONSTRAINT fk_maHV_diemDanh FOREIGN KEY([maHV]) REFERENCES hocVien([maHV]),	
	CONSTRAINT fk_maLop_diemDanh FOREIGN KEY([maLopHoc]) REFERENCES lopHoc([maLopHoc]),
	CONSTRAINT fk_maCaHoc_diemDanh FOREIGN KEY([maSlot]) REFERENCES slot([maSlot])
	)
CREATE TABLE hoaDon(
	[mahoaDon] NVARCHAR(10) primary key,
	[maHV] NVARCHAR(10) NOT NULL, --CONSTRAINT--
	[maLopHoc] NVARCHAR(10) NOT NULL,	--CONSTRAINT--
	[giaTien] MONEY NOT NULL,
	[ngayThanhToan] DATE NOT NULL DEFAULT GETDATE(),
	CONSTRAINT fk_maHV_hoaDon FOREIGN KEY([maHV]) REFERENCES hocVien([maHV]),
	CONSTRAINT fk_maLop_hoaDon FOREIGN KEY([maLopHoc]) REFERENCES lopHoc([maLopHoc])
	)
CREATE TABLE Don(
	[maDon] NVARCHAR(10) primary key,
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

/*CREATE TABLE hopDongTrainer(
	[maHopDong] NVARCHAR(10) primary key,
	[maTrainer] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[mucLuong] DECIMAL(10,2) NOT NULL,
	CONSTRAINT fk_maTrainer_hopDong FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer])
	)*/
CREATE TABLE bangOT(
	[maOT] NVARCHAR(10) primary key,
	[maTrainer] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[OTRate] DECIMAL(10,2) NOT NULL,
	[OTHours] DECIMAL(5,2) NOT NULL,
	[OTTotal] DECIMAL(10,2) NOT NULL,
	CONSTRAINT fk_maTrainer_OT FOREIGN KEY([maTrainer]) REFERENCES Trainer([maTrainer])
	)
CREATE TABLE paySlip(
	[maPaySlip] NVARCHAR(10) primary key,
	[maHopDong] NVARCHAR(10) NOT NULL,  --CONSTRAINT--
	[maOT] NVARCHAR(10) NULL,
	Deductions DECIMAL(10, 2),
	Total DECIMAL(10, 2),
	[Date] DATE,
	CONSTRAINT fk_maPaySlip_hopDong FOREIGN KEY([maHopDong]) REFERENCES hopDongGiaoVien([maHopDong]),
	CONSTRAINT fk_maPaySlip_OT FOREIGN KEY([maOT]) REFERENCES  bangOT([maOT])
	)